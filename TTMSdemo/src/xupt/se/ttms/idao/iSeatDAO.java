package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Seat;

public interface iSeatDAO {
	public int insert(Seat stu);
	public int update(Seat stu);
	public int delete(int ID);
	public List<Seat> select(String condt);
}
