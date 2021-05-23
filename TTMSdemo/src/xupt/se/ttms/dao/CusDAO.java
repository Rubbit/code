package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iCustomerDAO;
import xupt.se.ttms.model.Customer;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;
import xupt.se.util.DBUtil;

public class CusDAO implements iCustomerDAO {

	 @Override
	    public int insert(Customer cus)
	    {
	        try
	        {
	            String sql="insert into customer(cus_name, cus_gender, cus_telnum, cus_email,cus_uid,cus_pwd,cus_status )"
	                    + " values('" + cus.getName() + "', " + cus.getGender() + ", " + cus.getPhone() + ", '"
	                    + cus.getEmail() + "','"+cus.getUid()+"','"+cus.getPassword()+"','"+cus.getStatus()+"' )";
	            DBUtil db=new DBUtil();
	            db.openConnection();
	            ResultSet rst=db.getInsertObjectIDs(sql);
	            if(rst != null && rst.first())
	            {
	                cus.setID(rst.getInt(1));
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
	public int update(Customer cus) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int ID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
    public List<Customer> select(String condt)
    {
        DBUtil db=null;
        List<Customer> cusList=null;
        cusList=new LinkedList<Customer>();
        try
        {
            String sql="select cus_name, cus_gender, cus_telnum, cus_email,cus_uid,cus_pwd,cus_status from customer ";
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
                    Customer cus=new Customer();
                    cus.setUid(rst.getString("cus_uid"));
                    cus.setName(rst.getString("cus_name"));
                    cus.setGender(rst.getInt("cus_gender"));
                    cus.setPhone(rst.getString("cus_telnum"));
                    cus.setPassword(rst.getString("cus_pwd"));
                    cus.setStatus(rst.getInt("cus_status"));
                    cus.setEmail(rst.getString("cus_email"));
                    cusList.add(cus);
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

        return cusList;
    }
	@Override
	public int selectcus_id(String condt) {
		int id=0;
		DBUtil db=null;
        try
        {
            String sql="select  cus_id from customer ";
            condt.trim();
            sql+="where " + condt;
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
                    id=rst.getInt("cus_id");
                    
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
        return id;
	}
}
