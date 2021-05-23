package xupt.se.ttms.webview.cus;

import java.io.IOException;
import java.io.PrintWriter;
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

import xupt.se.ttms.model.Customer;
import xupt.se.ttms.service.CusSrv;

// 顾客访问入口
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet
{
    private static final long serialVersionUID=1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("------------------------->CustomerServlet");
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
        else if(type.equalsIgnoreCase("login"))
            login(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Customer cus=null;
        int id=0;
        try
        {
            String name=request.getParameter("cusname");
            String password=request.getParameter("password");
            String phone=request.getParameter("phone");
            String gender1=request.getParameter("gender");
            int gender=0;
            if(gender1.equals("男"))
                gender=1;
            int status=1;
            String email=request.getParameter("email");
            String uid=request.getParameter("cusuid");
            cus=new Customer(id, gender, phone, password, status, email, name, uid);
            System.out.print(cus.getUid());
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out=response.getWriter();

            // TODO 以标准格式返回ApiResponse构成的JSON数据到前端
            if(new CusSrv().add(cus) == 1)
                out.write("数据添加成功");
            else
                out.write("数据添加失败，请重试");

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
    {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        List<Customer> result=null;
        result=new CusSrv().FetchAll();
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Customer cus : result)
            {
                json=new JSONObject();
                json.put("uid", cus.getID());
                json.put("name", cus.getName());
                json.put("gender", cus.getGender());
                json.put("phone", cus.getPhone());
                json.put("email", cus.getEmail());
                json.put("status", cus.getStatus());
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
            // TODO 以标准格式返回ApiResponse构成的JSON数据到前端，该jsonStr应放到ApiResponse的data中
            out.println(jsonStr);
            out.flush();
            out.close();
        }
        System.out.print(jsonStr);

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setCharacterEncoding("UTF-8");
        // name改成id，好理解
        String userId=request.getParameter("userid");
        String password=request.getParameter("password");

        System.out.println(userId);
        System.out.println(password);
        List<Customer> result=null;
        // TODO CusDAO中应该定义一个判断用户名和密码的方法，这边通过Service层调用该DAO，不应该将拼接sql写在控制器层
        // 将数据访问逻辑和业务逻辑分离
        result=new CusSrv().Fetch(" cus_uid = '" + userId + "' and cus_pwd = '" + password + "'");
        if(!result.isEmpty())
        {
            HttpSession session=request.getSession();
            // TODO 如果后续加入权限内容，这里放入session中的就应该是权限信息
            // TODO userName改成id，好理解
            session.setAttribute("userId", userId);
            response.sendRedirect("customer/index.html");
        }
        else
        {
            response.sendRedirect("customer/login.html");
        }
    }

}
