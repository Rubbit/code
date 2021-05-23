package xupt.se.ttms.webview.play;

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

import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.ScheduleSrv;

/**
 * Servlet implementation class PlayServlet
 */
@WebServlet("/PlayServlet")
public class PlayServlet extends HttpServlet {
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
        PrintWriter out = response.getWriter();
        String name=request.getParameter("playname");
        System.out.println("查询影片：" + name);

        List<Play> result=null;
        if(name != null && name.length() > 0)
            result=new PlaySrv().Fetch("play_name LIKE '%" + name + "%'");
        else
            result=new PlaySrv().FetchAll();
        String jsonStr="";
        try
        {	JSONArray array = new JSONArray();
        	JSONObject json;
        	for(Play p : result)
            {
        	json = new JSONObject();
            json.put("playname", p.getPlayname());
            json.put("playimage", p.getPlayimage());
            array.put(json);
            }
        	jsonStr = array.toString();
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
