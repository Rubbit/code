package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import xupt.se.ttms.idao.iSale_itemDAO;
import xupt.se.ttms.model.Sale_item;
import xupt.se.util.DBUtil;

public class Sale_itemDAO implements iSale_itemDAO {

	@Override
	public int insert(Sale_item s) {
		
		try
        {
            String sql="insert into sale_item( ticket_id, sale_ID, sale_item_price)"
                    + " values("  + s.getTicketid() + "," + s.getSaleid() + ","+ s.getPrice() + ")";
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
	public int insertT(List<Sale_item> slist) {
		ResultSet rst=null;
		List<String> list = new ArrayList<String>();
		try
        {
			for(int i=0;i<slist.size();i++) {
            list.add("insert into sale_item( ticket_id, sale_ID, sale_item_price)"
                    + " values("  + slist.get(i).getTicketid() + "," + slist.get(i).getSaleid() + ","+ slist.get(i).getPrice() + ")");
			}
            DBUtil db=new DBUtil();
            db.openConnection();
            rst=db.getInsertObjectIDsT(list);
            if(rst != null && rst.first())
            {
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
	public int update(Sale_item s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int ID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Sale_item> select(String condt) {
		// TODO Auto-generated method stub
		return null;
	}

}
