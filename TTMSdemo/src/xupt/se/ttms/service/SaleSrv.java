package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iSaleDAO;
import xupt.se.ttms.model.Sale;

public class SaleSrv {
private iSaleDAO sDAO=DAOFactory.creatSaleDAO();
	
	public int add(Sale s){
		return sDAO.insert(s); 		
	}
	
	public int modify(Sale s){
		return sDAO.update(s ); 		
	}
	
	public int delete(int ID){
		return sDAO.delete(ID); 		
	}
	
	public List<Sale> Fetch(String condt){
		return sDAO.select(condt);		
	}
	
	public List<Sale> FetchAll(){
		return sDAO.select("");		
	}
	public int FetchSale_id(String condt){
		return sDAO.selectSale_id(condt);		
	}
}
