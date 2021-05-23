package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Sale;

public interface iSaleDAO {
	public int insert(Sale s);
	public int update(Sale s);
	public int delete(int ID);
	public List<Sale> select(String condt);
	int selectSale_id(String condt);
}
