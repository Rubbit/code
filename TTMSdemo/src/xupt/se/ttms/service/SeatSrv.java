package xupt.se.ttms.service;

import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.dao.PlayDAO;
import xupt.se.ttms.dao.SeatDAO;
import xupt.se.ttms.dao.StudioDAO;
import xupt.se.ttms.dao.TicketDAO;
import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iPlayDAO;
import xupt.se.ttms.idao.iScheduleDAO;
import xupt.se.ttms.idao.iSeatDAO;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.idao.iTicketDAO;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Ticket;

// 大改，通过视图完成，通过前端传过来的schedid串来进行票和相关信息查询
public class SeatSrv
{
    // TODO 都应该用DAOFactory的creatXXXDAO方法创建，并且应该在下面的方法中创建，这里创建占内存
    // 变量名定义不好理解，不规范
    // 实例化过程在成员方法中完成
    private iSeatDAO stuDAO=DAOFactory.creatSeatDAO();
    private iScheduleDAO scheduleDAO;
    private iSeatDAO seatDAO=new SeatDAO();
    private iPlayDAO playDAO=new PlayDAO();
    private iStudioDAO stDAO=new StudioDAO();
    private iTicketDAO tDAO=new TicketDAO();

    public int add(Seat stu)
    {
        return stuDAO.insert(stu);
    }

    public int modify(Seat stu)
    {
        return stuDAO.update(stu);
    }

    public int delete(int ID)
    {
        return stuDAO.delete(ID);
    }

    public List<Seat> Fetch(String condt)
    {
        return stuDAO.select(condt);
    }

    public List<Seat> FetchAll()
    {
        return stuDAO.select("");
    }

    public List<Ticket> FetchSchedid(String condt)
    {
        scheduleDAO=DAOFactory.creatScheduleDAO();

        List<Ticket> tList=null;
        tList=new LinkedList<Ticket>();
        Ticket t=new Ticket();
        String playname="";
        String studioname="";
        t=seDAO.selectsched_id(condt);
        playname=pDAO.selectplayid(t.getPlayid());
        studioname=stDAO.selectstudioid(t.getStudioid());
        tList=sDAO.selectschedid();
        for(Ticket k : tList)
        {
            k.setScheduleid(Integer.valueOf(condt));
            // System.out.print("座位和排片计划id"+k.getSeatid()+k.getScheduleid()+"\n");
            k.setStudioid(t.getStudioid());
            k.setTime(t.getTime());
            k.setPrice(t.getPrice());
            k.setStudioname(studioname);
            k.setPlayname(playname);
            System.out.println("获取座位状态" + tDAO.selectseatid(k.getSeatid(), k.getScheduleid()));
            k.setStatus(tDAO.selectseatid(k.getSeatid(), k.getScheduleid()));
            k.setID(tDAO.selectticketid(k.getSeatid(), k.getScheduleid()));
        }
        return tList;
    }
}
