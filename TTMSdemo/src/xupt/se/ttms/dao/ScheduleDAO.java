package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iScheduleDAO;
import xupt.se.ttms.model.Customer;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Ticket;
import xupt.se.util.DBUtil;

public class ScheduleDAO implements iScheduleDAO {

	public Ticket selectsched_id(String condt) {
		DBUtil db=null;
        Ticket t=new Ticket();
        try
        {
            String sql="select sched_time,studio_id,play_id,sched_ticket_price from schedule  ";
            condt.trim();
            sql+=" where sched_id= " + condt;
            db=new DBUtil();
            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return null;
            }
            ResultSet rst=db.execQuery(sql);
            if(rst != null)
            {
                while(rst.next())
                {
                	t.setPrice(rst.getString("sched_ticket_price"));
                    t.setTime(rst.getString("sched_time"));
                    t.setPlayid(rst.getInt("play_id"));
                    t.setStudioid(rst.getInt("studio_id"));
                }

            }
            db.close(rst);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {

        }
        //System.out.print("座位号+++"+t.getSeatid());
        return t;
	}
	
	
	
	
	@Override
    public List<Schedule> select(String condt)
    {
        DBUtil db=null;
        List<Schedule> schList=null;
        schList=new LinkedList<Schedule>();
        try
        {
            String sql="select schedule.sched_id,schedule.studio_id, schedule.play_id, sched_time, sched_ticket_price,play_name,studio_name,play_introduction,play_image from schedule,studio,play where schedule.studio_id=studio.studio_id and play.play_id=schedule.play_id ";
            condt.trim();
            if(!condt.isEmpty())
                sql+=" and " + condt;
            db=new DBUtil();
            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return null;
            }
            ResultSet rst=db.execQuery(sql);
            if(rst != null)
            {
                while(rst.next())
                {
                    Schedule sch=new Schedule();
                    sch.setId(rst.getInt("sched_id"));
                    sch.setPlayname(rst.getString("play_name"));
                    sch.setStudioname(rst.getString("studio_name"));
                    sch.setTime(rst.getString("sched_time"));
                    sch.setPrice(rst.getString("sched_ticket_price"));
                    sch.setPlayimage(rst.getString("play_image"));
                    sch.setPlayintro(rst.getString("play_introduction"));
                    schList.add(sch);
                }
            }
            db.close(rst);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {

        }

        return schList;
    }
}
