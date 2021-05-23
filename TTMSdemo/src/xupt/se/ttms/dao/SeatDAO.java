package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iSeatDAO;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;
import xupt.se.util.DBUtil;

public class SeatDAO implements iSeatDAO {

	@Override
	public int insert(Seat stu) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Seat stu) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int ID) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Ticket> selectschedid() {
		 DBUtil db=null;
	        List<Ticket> stuList=null;
	        stuList=new LinkedList<Ticket>();
	        try
	        {
	            String sql="select seat_id,  seat_row, seat_column, seat_status from seat";
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
	                    Ticket t = new Ticket();
	                    t.setSeatid(rst.getInt("seat_id"));
	                    t.setCol(rst.getInt("seat_column"));
	                    t.setRow(rst.getInt("seat_row"));
	                    t.setSeatstatus(rst.getInt("seat_status"));
	                    stuList.add(t);
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

	        return stuList;
	}
	
	@Override
	public List<Seat> select(String condt) {
		 DBUtil db=null;
	        List<Seat> stuList=null;
	        stuList=new LinkedList<Seat>();
	        try
	        {
	            String sql="select seat_id, seat.studio_id, seat_row, seat_column, seat_status,studio_name from seat,studio  where seat.studio_id=studio.studio_id ";
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
	                    Seat stu=new Seat();
	                    stu.setID(rst.getInt("seat_id"));
	                    stu.setStudioid(rst.getInt("studio_id"));
	                    stu.setRow(rst.getInt("seat_row"));
	                    stu.setCol(rst.getInt("seat_column"));
	                    stu.setStatus(rst.getInt("seat_status"));
	                    //System.out.print(rst.getInt("seat_status"));
	                    stu.setName(rst.getString("studio_name"));
	                    stuList.add(stu);
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

	        return stuList;
	}
	 public static void main(String[] args)
	    {
	        Studio stu=new Studio();
	        stu.setName("No1");
	        // frmStuMgr.setVisible(true);

	    }

}
