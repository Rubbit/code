package xupt.se.ttms.webview.schedule;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.service.ScheduleSrv;

@WebServlet("/ScheduleServlet")
public class ScheduleServlet extends HttpServlet
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
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        String name=request.getParameter("name");
        System.out.println("查询拍片计划：" + name);

        List<Schedule> result=null;
        // TOTO 不能在控制器层拼接SQL，应在DAO类中完成
        if(name != null && name.length() > 0)
            result=new ScheduleSrv().Fetch("studio.studio_name LIKE '%" + name + "%'");
        else
            result=new ScheduleSrv().FetchAll();
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Schedule s : result)
            {
                json=new JSONObject();
                json.put("playname", s.getPlayname());
                json.put("studioname", s.getStudioname());
                json.put("schedtime", s.getTime());
                json.put("schedprice", s.getPrice());
                json.put("status", s.getStatus());
                json.put("id", s.getId());
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
