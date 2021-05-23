package xupt.se.ttms.webview.sale;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xupt.se.ttms.model.Sale;
import xupt.se.ttms.model.Sale_item;
import xupt.se.ttms.service.CusSrv;
import xupt.se.ttms.service.SaleSrv;
import xupt.se.ttms.service.Sale_itemSrv;
import xupt.se.ttms.service.TicketSrv;

/**
 * Servlet implementation class SaleServlet
 */
@WebServlet("/SaleServlet")
public class SaleServlet extends HttpServlet
{
    private static final long serialVersionUID=1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String type=request.getParameter("type");

        // 根据请求操作类型，执行相应的增、删、该、查
        if(type.equalsIgnoreCase("add"))
            add(request, response);
        else if(type.equalsIgnoreCase("delete"))
            delete(request, response);
        else if(type.equalsIgnoreCase("update"))
            update(request, response);
        else if(type.equalsIgnoreCase("search"))
            search(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Sale s=null;
        int id=0;
        try
        {
            CusSrv csrv=new CusSrv();
            SaleSrv salesrv=new SaleSrv();
            Sale_itemSrv sale_itemsrv=new Sale_itemSrv();
            TicketSrv tsrv=new TicketSrv();
            HttpSession session=request.getSession();
            String cusuid=(String) session.getAttribute("userName");
            // TODO 价格不应该由前端传过来
            String price=request.getParameter("price");
            String conid="";
            int schedid=0;
            // TODO 前端应该传过来ticket_id串，写sale、sale_item只和ticket_id有关，其他如票价可查出来，客户id在session中有
            schedid=Integer.valueOf(request.getParameter("schedid"));
            String[] res=null;
            String[] ticketid=null;
            boolean flag=false;
            conid=request.getParameter("conid");
            if(conid != null && conid.length() > 0)
            {
                for(int i=0; i < conid.length(); i++)
                {
                    if(conid.charAt(i) == ',')
                        flag=true;
                }
                if(flag)
                    res=conid.split(",");
                else
                {
                    res=new String[1];
                    res[0]=conid;
                }
            }
            System.out.println("ticketid=" + ticketid);
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int cusid=csrv.FetchCusid("cus_uid =  '" + cusuid + "'");
            System.out.println("cusid=" + cusid);
            // System.out.println(df.format(System.currentTimeMillis()));
            String time=String.valueOf(df.format(System.currentTimeMillis()));

            s=new Sale(id, 1, 1, cusid, time, price, "0", 1, 1);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            for(int i=0; i < res.length; i++)
            {
                int t=0;
                System.out.println("res=" + res[i]);
                t=tsrv.FetchSeat(res[i], schedid);
                System.out.println("\nticketid=" + t);
                if(tsrv.modifyStatus(9, t) == 1)
                    out.write("数据添加成功");
                else
                    out.write("数据添加失败，请重试");

            }
            List<Sale_item> slist=new LinkedList<Sale_item>();
            int saleid=salesrv.FetchSale_id("cus_id =  '" + cusid + "'");
            for(int i=0; i < res.length; i++)
            {
                if(i == 0)
                {
                    if(salesrv.add(s) == 1)
                        out.write("数据添加成功");
                    else
                        out.write("数据添加失败，请重试");

                }
                int t=0;
                System.out.println("res=" + res[i]);
                t=tsrv.FetchSeat(res[i], schedid);
                System.out.println("\nticketid=" + t);
                Sale_item sale_item=new Sale_item(id, t, saleid, price);
                /*
                 * if(sale_itemsrv.add(sale_item)==1 ) out.write("数据添加成功"); else
                 * out.write("数据添加失败，请重试");
                 */
                slist.add(sale_item);
            }
            if(sale_itemsrv.addlist(slist) == 1)
                out.write("数据添加成功sale_item");
            else
            {
                out.write("数据添加失败，请重试sale_item\n");
                for(int i=0; i < res.length; i++)
                {
                    int t=0;
                    System.out.println("res=" + res[i]);
                    t=tsrv.FetchSeat(res[i], schedid);
                    System.out.println("\nticketid=" + t);
                    if(tsrv.modifyStatus(0, t) == 1)
                        out.write("ticket0\n");
                    else
                        out.write("数据添加失败，请重试");

                }
            }
            out.print("成功");
            out.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("操作错误，请重试");
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {}

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {}

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {}
}
