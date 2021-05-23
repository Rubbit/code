package xupt.se.ttms.webview.seat;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Ticket;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.util.ApiResponse;
import xupt.se.util.ApiResponseEnum;
import xupt.se.util.ApiResponseUtil;

@WebServlet("/SeatServlet")
public class SeatServlet extends HttpServlet
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
        if(type.equalsIgnoreCase("search"))
            search(request, response);
        else if(type.equalsIgnoreCase("chance"))
            try
            {
                chance(request, response);
            }
            catch(ParseException e)
            {
                e.printStackTrace();
            }
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("----------------->SeatServlet.search");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        String name=request.getParameter("name");
        System.out.println("查询座位：" + name);

        List<Seat> result=null;
        if(name != null && name.length() > 0)
            result=new SeatSrv().Fetch("studio.studio_name LIKE '%" + name + "%'");
        else
            result=new SeatSrv().FetchAll();
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Seat s : result)
            {
                json=new JSONObject();
                json.put("id", s.getID());
                json.put("name", s.getName());
                json.put("row", s.getRow());
                json.put("col", s.getCol());
                json.put("status", s.getStatus());
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

    private void chance(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException
    {
        System.out.println("----------------->SeatServlet.chance");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        String schedid=request.getParameter("schedid");

        List<Ticket> result=null;
        if(schedid != null && schedid.length() > 0)
            result=new SeatSrv().FetchSchedid(schedid);
        else
        {
            // TODO 有问题，包含错误信息的json串
            ApiResponse apiResponse=ApiResponseUtil.getApiResponse(ApiResponseEnum.FAIL);
            JSONObject json=new JSONObject();
            try
            {
                json.put("resCode", apiResponse.getResCode());
                json.put("resMsg", apiResponse.getResMsg());
                json.put("data", "");
                // response.sendRedirect("schedule.html");
                out.write(json.toString());
                out.flush();
                out.close();
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
        }
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Ticket s : result)
            {
                json=new JSONObject();
                json.put("id", s.getSeatid());
                json.put("ticketid", s.getID());
                json.put("playname", s.getPlayname());
                json.put("row", s.getRow());
                json.put("col", s.getCol());
                json.put("seatstatus", s.getSeatstatus());
                json.put("status", s.getStatus());
                json.put("studioname", s.getStudioname());
                json.put("price", s.getPrice());
                json.put("time", s.getTime());
                SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date=df.parse(s.getTime());
                Date afterDate=new Date(date.getTime() + 7200000);
                // System.out.println(df.format(afterDate));
                json.put("time1", String.valueOf(df.format(afterDate)));
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

}
