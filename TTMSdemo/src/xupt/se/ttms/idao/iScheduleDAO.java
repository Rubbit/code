package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Schedule;

public interface iScheduleDAO {
	public List<Schedule> select(String condt);
}
