package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iPlayDAO;
import xupt.se.ttms.model.Play;


public class PlaySrv {
	private iPlayDAO schDAO=DAOFactory.creatPlayDAO();
	public List<Play> Fetch(String condt){
		return schDAO.select(condt);		
	}
	
	public List<Play> FetchAll(){
		return schDAO.select("");		
	}
}
