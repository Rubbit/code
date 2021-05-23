package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iCustomerDAO;
import xupt.se.ttms.model.Customer;

public class CusSrv {
private iCustomerDAO cusDAO=DAOFactory.creatCustomerDAO();
	
	public int add(Customer cus){
		return cusDAO.insert(cus); 		
	}
	
	public int modify(Customer cus){
		return cusDAO.update(cus); 		
	}
	
	public int delete(int ID){
		return cusDAO.delete(ID); 		
	}
	
	public List<Customer> Fetch(String condt){
		return cusDAO.select(condt);		
	}
	
	public List<Customer> FetchAll(){
		return cusDAO.select("");		
	}
	public int FetchCusid(String condt){
		return cusDAO.selectcus_id(condt);		
	}
}
