package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iScheduleDAO;
import xupt.se.ttms.model.Schedule;


public class ScheduleSrv {
	private iScheduleDAO schDAO=DAOFactory.creatScheduleDAO();
	public List<Schedule> Fetch(String condt){
		return schDAO.select(condt);		
	}
	
	public List<Schedule> FetchAll(){
		return schDAO.select("");		
	}
}
