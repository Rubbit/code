package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iPlayDAO;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Ticket;
import xupt.se.util.DBUtil;

public class PlayDAO implements iPlayDAO {

	public String selectplayid(int condt) {
		DBUtil db=null;
        String t="";
        try
        {
            String sql="select play_name from play  ";
            sql+=" where play_id= " + condt;
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
                	t = rst.getString("play_name");
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
	
	public Ticket selectplay_id(int condt) {
		DBUtil db=null;
        Ticket t=new Ticket();
        try
        {
            String sql="select play_name from play  ";
            sql+=" where play_id= " + condt;
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
                	t.setPlayname(rst.getString("play_name"));
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
    public List<Play> select(String condt)
    {
        DBUtil db=null;
        List<Play> schList=null;
        schList=new LinkedList<Play>();
        try
        {
            String sql="select play_name,play_image from play  ";
            condt.trim();
            if(!condt.isEmpty())
                sql+=" where " + condt;
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
                	Play sch=new Play();
                    sch.setPlayname(rst.getString("play_name"));        
                    sch.setPlayimage(rst.getString("play_image"));
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
