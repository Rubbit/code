package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iSale_itemDAO;
import xupt.se.ttms.model.Sale_item;

public class Sale_itemSrv {
	private iSale_itemDAO sDAO=DAOFactory.creatSale_itemDAO();
	public int add(Sale_item s){
		return sDAO.insert(s); 		
	}
	
	public int addlist(List<Sale_item> slist){
		return sDAO.insertT(slist); 		
	}
	
	public int modify(Sale_item s){
		return sDAO.update(s); 		
	}
	
	public int delete(int ID){
		return sDAO.delete(ID); 		
	}
	
	public List<Sale_item> Fetch(String condt){
		return sDAO.select(condt);		
	}
	
	public List<Sale_item> FetchAll(){
		return sDAO.select("");		
	}
}
