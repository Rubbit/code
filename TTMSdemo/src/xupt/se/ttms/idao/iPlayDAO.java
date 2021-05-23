package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Play;

public interface iPlayDAO {
	public List<Play> select(String condt);
}
