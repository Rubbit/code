package xupt.se.ttms.webview.ticket;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Ticket;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.service.TicketSrv;

/**
 * Servlet implementation class TicketServlet
 */
@WebServlet("/TicketServlet")
public class TicketServlet extends HttpServlet
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
        else if(type.equalsIgnoreCase("search"))
            search(request, response);
        else if(type.equalsIgnoreCase("sale"))
            try
            {
                sale(request, response);
            }
            catch(ParseException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        else if(type.equalsIgnoreCase("buy"))
            try
            {
                buy(request, response);
            }
            catch(ParseException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out=response.getWriter();
        response.setContentType("text/html;charset=utf-8");
        int id=Integer.valueOf(request.getParameter("id"));
        String condt="sched_id=" + id;
        System.out.print(condt);
        boolean b=false;
        try
        {
            b=new TicketSrv().FetchSched(condt);
            System.out.print(b);
            if((b))
            {
                System.out.print("已有该时段的电子票生成！！！");
                out.write("已有该时段的电子票生成！！！");
            }
            else
            {
                SeatSrv se=new SeatSrv();
                List<Seat> s=new LinkedList<Seat>();
                s=se.Fetch("seat_status = 1");
                String price="35";
                int sched_id=Integer.valueOf(request.getParameter("id"));
                if(new TicketSrv().add(sched_id, s, price) == 1)
                    out.write("数据添加成功");
                else
                    out.write("数据添加失败，请重试");

                out.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("操作错误，请重试");
        }

    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        // HttpSession session=request.getSession();
        // if((String) session.getAttribute("userName") == null)
        // {
        // out.write(0);
        // out.flush();
        // out.close();
        // return;
        // }
        String conid="";
        conid=request.getParameter("conid");
        boolean flag=false;
        String[] res=null;
        String seatid="";
        seatid=request.getParameter("id");
        System.out.println("查询票：" + seatid);
        System.out.println("ticket.seat_id=  '" + seatid + "'");
        List<Ticket> result=null;

        if(seatid != null && seatid.length() > 0)
            result=new TicketSrv().Fetch("ticket.seat_id=  '" + seatid + "'");
        else
            result=new TicketSrv().FetchAll();
        System.out.print(result.size());
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Ticket t : result)
            {
                json=new JSONObject();
                json.put("playname", t.getPlayname());
                json.put("time", t.getTime());
                json.put("playimage", t.getPlayimage());
                json.put("status", t.getStatus());
                json.put("locktime", t.getLocktime());
                json.put("seatid", t.getSeatid());
                json.put("scheduleid", t.getScheduleid());
                array.put(json);
            }
            jsonStr=array.toString();
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        finally
        {
            out.println(jsonStr);
            out.flush();
            out.close();
        }
        System.out.print(jsonStr);
    }

    private void buy(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException
    {
        // System.out.print("SUC!!");
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HttpSession session=request.getSession();
        if((String) session.getAttribute("userName") == null)
        {
            // TODO不能跳转
            response.sendRedirect("login.html");
            return;
        }
        TicketSrv tsrv=new TicketSrv();
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        String conid="";
        String locktime="";
        int invalidtime=0;
        invalidtime=tsrv.FetchLocktime("dict_id = 4 ") - 10;
        System.out.println('\n' + invalidtime);
        String schedid=request.getParameter("schedid");
        conid=request.getParameter("conid");
        System.out.println(conid + '\n');
        boolean flag=false;
        String[] res=null;
        List<Ticket> result=null;
        result=new LinkedList<Ticket>();
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
            // System.out.println("ticket.seat_id= '" + res[0] + "'");
            for(int i=0; i < res.length; i++)
            {
                // System.out.println("要购买的票的座位id：" + res[i]);
                Ticket t=new Ticket();
                t=tsrv.FetchSeat(res[i], schedid);
                System.out.println(t.getLocktime() + "\n" + session.getAttribute("locktime"));
                while(t.getStatus() == 9)
                {
                    response.sendRedirect("seats.html");
                    return;
                }
                while(t.getStatus() == 1)
                {

                    Date d1=df.parse(t.getLocktime());
                    Date d2=new Date(d1.getTime() + 60000 * invalidtime);
                    Date d3=new Date(System.currentTimeMillis());
                    System.out.println("分钟数" + ((d3.getTime() - d2.getTime()) / (60 * 1000)) % 60);
                    System.out.println("\n" + session.getAttribute("locktime") == null);
                    if(session.getAttribute("locktime") == null)
                    {
                        System.out.println("\n新用户\n");
                        // System.out.println(df.format(System.currentTimeMillis()));
                        t.setLocktime(String.valueOf(df.format(System.currentTimeMillis())));
                        t.setStatus(1);
                        locktime=t.getLocktime();
                        // System.out.println(t.getLocktime()+t.getSeatid());
                        System.out.println(tsrv.modify(t));
                        result.add(t);
                        // System.out.println("\nlocktime=="+(df.parse(t.getLocktime()).getTime()-df.parse((String)session.getAttribute("locktime")).getTime()));
                        break;
                    }
                    else if(((d3.getTime() - d2.getTime()) / (60 * 1000)) % 60 >= 0
                            || (d3.getTime() - d2.getTime()) / (60 * 60 * 1000) % 24 >= 1
                            || (d3.getTime() - d2.getTime()) / (24 * 60 * 60 * 1000) >= 1)
                    {
                        System.out.println("\n进入 if\n");
                        // System.out.println(df.format(System.currentTimeMillis()));
                        t.setLocktime(String.valueOf(df.format(System.currentTimeMillis())));
                        t.setStatus(1);
                        locktime=t.getLocktime();
                        // System.out.println(t.getLocktime()+t.getSeatid());
                        System.out.println(tsrv.modify(t));
                        result.add(t);
                        break;
                    }
                    else if(df.parse(t.getLocktime()).getTime()
                            - df.parse((String) session.getAttribute("locktime")).getTime() >= 0)
                    {
                        System.out.println("\n进入else if\n");
                        result.add(t);
                        break;
                    }
                    else
                    {
                        System.out.println("\n进入else\n");
                        response.sendRedirect("seats.html");
                        return;
                    }
                    // System.out.println(df.format(d2));
                }
                while(t.getStatus() == 0)
                {
                    // System.out.println(df.format(System.currentTimeMillis()));
                    t.setLocktime(String.valueOf(df.format(System.currentTimeMillis())));
                    t.setStatus(1);
                    locktime=t.getLocktime();
                    // System.out.println(t.getLocktime()+t.getSeatid());
                    System.out.println(tsrv.modify(t));
                    result.add(t);
                }
            }

        }
        else
        {
            // TODO跳转不对
            response.sendRedirect("seats.html");
            return;
        }
        // System.out.print(result.size());
        String jsonStr="";

        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Ticket t : result)
            {
                json=new JSONObject();
                json.put("playname", t.getPlayname());
                json.put("time", t.getTime());
                Date date=df.parse(t.getTime());
                Date afterDate=new Date(date.getTime() + 7200000);
                System.out.println(df.format(afterDate));
                json.put("time1", String.valueOf(df.format(afterDate)));
                json.put("playimage", t.getPlayimage());
                json.put("status", t.getStatus());
                json.put("locktime", t.getLocktime());
                json.put("seatid", t.getSeatid());
                json.put("scheduleid", t.getScheduleid());
                json.put("ticketid", t.getID());
                session.setAttribute("locktime", t.getLocktime());
                System.out.println("locktime==" + session.getAttribute("locktime") + t.getLocktime());
                array.put(json);
            }
            jsonStr=array.toString();
            System.out.print(jsonStr);

            // response.getWriter().print(jsonStr);
            // response.getWriter().print(jsonStr);

        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        finally
        {
            // request.setAttribute("json", jsonStr);
            // request.getRequestDispatcher("order.html").forward(request, response);

            out.println(jsonStr);
            out.flush();
            out.close();
        }
        // System.out.print(jsonStr);
        // session.setAttribute("json", jsonStr);
        // request.setAttribute("json", jsonStr);
        // request.setAttribute("aa", "aa");
        // response.sendRedirect("order.jsp");
        // return;
    }

    private void sale(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException
    {
        // System.out.print("SUC!!");
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HttpSession session=request.getSession();
        if((String) session.getAttribute("userName") == null)
        {
            response.sendRedirect("login.html");
            return;
        }
        TicketSrv tsrv=new TicketSrv();
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        String conid="";
        String locktime="";
        int invalidtime=0;
        invalidtime=tsrv.FetchLocktime("dict_id = 4 ") - 10;
        System.out.println('\n' + invalidtime);
        String schedid=request.getParameter("schedid");
        conid=request.getParameter("conid");
        System.out.println(conid + '\n' + schedid);
        boolean flag=false;
        String[] res=null;
        List<Ticket> result=null;
        result=new LinkedList<Ticket>();
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
            // System.out.println("ticket.seat_id= '" + res[0] + "'");
            for(int i=0; i < res.length; i++)
            {
                // System.out.println("要购买的票的座位id：" + res[i]);
                Ticket t=new Ticket();
                t=tsrv.FetchSeat(res[i], schedid);
                System.out.println(t.getLocktime() + "\n" + t.getSeatid() + t.getTime());
            }

        }
        else
        {
            response.sendRedirect("seats.html");
            return;
        }
        // System.out.print(result.size());
        String jsonStr="";

        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Ticket t : result)
            {
                json=new JSONObject();
                json.put("playname", t.getPlayname());
                json.put("time", t.getTime());
                Date date=df.parse(t.getTime());
                Date afterDate=new Date(date.getTime() + 7200000);
                System.out.println(df.format(afterDate));
                json.put("time1", String.valueOf(df.format(afterDate)));
                json.put("playimage", t.getPlayimage());
                json.put("status", t.getStatus());
                json.put("locktime", t.getLocktime());
                json.put("seatid", t.getSeatid());
                json.put("scheduleid", t.getScheduleid());
                json.put("ticketid", t.getID());
                session.setAttribute("locktime", t.getLocktime());
                System.out.println("locktime==" + session.getAttribute("locktime") + t.getLocktime());
                array.put(json);
            }
            jsonStr=array.toString();
            System.out.print(jsonStr);

            // response.getWriter().print(jsonStr);
            // response.getWriter().print(jsonStr);

        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        finally
        {
            // request.setAttribute("json", jsonStr);
            // request.getRequestDispatcher("order.html").forward(request, response);

            /// out.println(jsonStr);
            // out.flush();
            // out.close();
        }
        // System.out.print(jsonStr);
        session.setAttribute("json", jsonStr);
        // request.setAttribute("json", jsonStr);
        // request.setAttribute("aa", "aa");
        // response.sendRedirect("order.jsp");
        // return;
    }
}
