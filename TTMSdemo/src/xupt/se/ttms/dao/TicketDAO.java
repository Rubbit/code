package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iTicketDAO;
import xupt.se.ttms.model.Ticket;
import xupt.se.util.DBUtil;

public class TicketDAO implements iTicketDAO
{

    @Override
    public int insert(Ticket t)
    {
        try
        {
            String sql="insert into ticket(seat_id, sched_id, ticket_price, ticket_status,ticket_locktime )" + " values('"
                    + t.getSeatid() + "', " + t.getScheduleid() + ", " + t.getPrice() + ", '" + t.getStatus() + "', '"
                    + t.getLocktime() + "')";
            // System.out.print(sql);
            DBUtil db=new DBUtil();
            db.openConnection();
            ResultSet rst=db.getInsertObjectIDs(sql);
            if(rst != null && rst.first())
            {
                t.setID(rst.getInt(1));
            }
            db.close(rst);
            db.close();
            return 1;

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int update(Ticket t)
    {
        // TODO Auto-generated method stub
        int rtn=0;
        try
        {

            String sql="update ticket set  ticket_locktime ='" + t.getLocktime() + "' , ticket_status = '" + t.getStatus()
                    + "'";
            sql+=" where seat_id = " + t.getSeatid();
            DBUtil db=new DBUtil();
            db.openConnection();
            rtn=db.execCommand(sql);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return rtn;
    }

    @Override
    public int updateStatus(int status, int id)
    {
        // TODO Auto-generated method stub
        int rtn=0;
        try
        {

            String sql="update ticket set  ticket_status ='" + status + "'";
            sql+=" where ticket_id = " + id;
            DBUtil db=new DBUtil();
            db.openConnection();
            rtn=db.execCommand(sql);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return rtn;
    }

    @Override
    public int delete(int ID)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Ticket> select(String condt)
    {
        DBUtil db=null;
        List<Ticket> tList=null;
        tList=new LinkedList<Ticket>();
        try
        {
            String sql="select seat_id, play_name, play_image, ticket_locktime, ticket.sched_id,ticket_status,sched_time from ticket,schedule,play  where schedule.sched_id=ticket.sched_id and schedule.play_id=play.play_id";
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
                    Ticket t=new Ticket();
                    t.setPlayname(rst.getString("play_name"));
                    t.setPlayimage(rst.getString("play_image"));
                    t.setLocktime(rst.getString("ticket_locktime"));
                    t.setScheduleid(rst.getInt("sched_id"));
                    t.setSeatid(rst.getInt("seat_id"));
                    t.setStatus(rst.getInt("ticket_status"));
                    t.setTime(rst.getString("sched_time"));
                    tList.add(t);
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

        return tList;
    }

    public int selectseatid(int seat_id, int sched_id)
    {
        DBUtil db=null;
        // TODO 没有票，默认都是0，逻辑不对
        int t=0;
        try
        {
            String sql="select ticket_status from ticket  ";
            sql+=" where seat_id = " + seat_id + " and sched_id = " + sched_id;
            System.out.println("--------------------->" + sql);
            db=new DBUtil();
            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return 0;
            }
            ResultSet rst=db.execQuery(sql);
            if(rst != null)
            {
                while(rst.next())
                {
                    t=rst.getInt("ticket_status");
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
        // System.out.print("zhuangtai"+t+"\n");
        return t;
    }

    @Override
    public int selectticketid(int seat_id, int sched_id)
    {
        DBUtil db=null;
        int t=0;
        try
        {
            String sql="select ticket_id from ticket  ";
            sql+=" where seat_id = " + seat_id + " and sched_id = " + sched_id;
            db=new DBUtil();
            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return 0;
            }
            ResultSet rst=db.execQuery(sql);
            if(rst != null)
            {
                while(rst.next())
                {
                    t=rst.getInt("ticket_id");
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
        // System.out.print("zhuangtai"+t+"\n");
        return t;
    }

    @Override
    public Ticket selectseat_id(String condt)
    {
        DBUtil db=null;
        Ticket t=new Ticket();
        try
        {
            String sql="select ticket.ticket_status,ticket.ticket_id,seat_id,ticket_locktime, ticket.sched_id from ticket  ";
            condt.trim();
            sql+=" where seat_id= " + condt;
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

                    t.setLocktime(rst.getString("ticket_locktime"));
                    t.setScheduleid(rst.getInt("sched_id"));
                    t.setSeatid(rst.getInt("seat_id"));
                    t.setStatus(rst.getInt("ticket_status"));
                    t.setID(rst.getInt("ticket_id"));

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
        // System.out.print("座位号+++"+t.getSeatid());
        return t;
    }

    @Override
    public boolean selectsched_id(String condt)
    {
        DBUtil db=null;
        boolean b=false;
        try
        {
            String sql="select ticket_locktime, ticket.sched_id from ticket ";
            condt.trim();
            if(!condt.isEmpty())
                sql+=" where " + condt;
            db=new DBUtil();
            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return false;
            }
            ResultSet rst=db.execQuery(sql);
            while(rst.next())
            {
                b=true;
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

        return b;
    }

    @Override
    public int selectlocktime(String condt)
    {
        int param=0;
        DBUtil db=null;
        try
        {
            String sql="select dict_value from data_dict ";
            condt.trim();
            if(!condt.isEmpty())
                sql+=" where " + condt;
            db=new DBUtil();
            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return 0;
            }
            ResultSet rst=db.execQuery(sql);
            if(rst != null)
            {
                while(rst.next())
                {
                    param=rst.getInt("dict_value");
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

        return param;
    }
}
