package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Customer;


public interface iCustomerDAO {
	public int insert(Customer cus);
	public int update(Customer cus);
	public int delete(int ID);
	public List<Customer> select(String condt);
	int selectcus_id(String condt);
}
