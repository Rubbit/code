package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.List;

import xupt.se.ttms.idao.iSaleDAO;
import xupt.se.ttms.model.Sale;
import xupt.se.util.DBUtil;

public class SaleDAO implements iSaleDAO {

	@Override
	public int insert(Sale s) {
		
		try
        {
            String sql="insert into sale( cus_id, sale_time, sale_payment,sale_change,sale_type,sale_status,sale_sort )"
                    + " values("  + s.getCusid() + ",'" + s.getSaletime() + "','"
                    + s.getSalepayment() + "','"+s.getSalechange()+"',"+s.getSaletype()+","+s.getStatus()+","+s.getSalesort()+")";
            DBUtil db=new DBUtil();
            db.openConnection();
            ResultSet rst=db.getInsertObjectIDs(sql);
            if(rst != null && rst.first())
            {
                s.setID(rst.getInt(1));
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
	public int update(Sale s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int ID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Sale> select(String condt) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int selectSale_id(String condt) {
		int id=0;
		DBUtil db=null;
        try
        {
            String sql="select  sale_id from sale ";
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
                    id=rst.getInt("sale_id");
                    
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
