package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Ticket;

public interface iTicketDAO {
	public int insert(Ticket t);
	public int update(Ticket t);
	public int delete(int ID);
	public List<Ticket> select(String condt); 
	public boolean selectsched_id(String condt);
	public Ticket selectseat_id(String condt);
	public int selectlocktime(String condt);
	int updateStatus(int status,int id);
	int selectticketid(int seat_id, int sched_id);
}
