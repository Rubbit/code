package xupt.se.ttms.idao;

import java.util.List;


import xupt.se.ttms.model.Sale_item;

public interface iSale_itemDAO {
	public int insert(Sale_item s);
	public int update(Sale_item s);
	public int delete(int ID);
	public List<Sale_item> select(String condt);
	int insertT(List<Sale_item> slist);
}
