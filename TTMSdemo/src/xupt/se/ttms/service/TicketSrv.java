package xupt.se.ttms.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.dao.PlayDAO;
import xupt.se.ttms.dao.ScheduleDAO;
import xupt.se.ttms.dao.SeatDAO;
import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iTicketDAO;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Ticket;

public class TicketSrv {
private iTicketDAO tDAO=DAOFactory.creatTicketDAO();
	ScheduleDAO scDAO = new ScheduleDAO();
	PlayDAO pDAO = new PlayDAO();
	public int add(int sched_id,List<Seat> s,String price){
		int flag=0;
		  for(int j=0;j<s.size();j++){	
			  Ticket t=new Ticket();
			  t.setScheduleid(sched_id);
			  t.setPrice(price);
			  t.setSeatid(s.get(j).getId());
			  t.setStatus(0);
			  t.setID(0);
			  t.setLocktime("1970-01-01 08:00:01");
			  //System.out.print(t.getSeatid());
			  flag=tDAO.insert(t);
			  if(flag==0) break;
	        }
		return flag; 		
	}
	
	public int modify(Ticket t){
		//System.out.println(t.getLocktime());
		return tDAO.update(t); 		
	}
	
	public int modifyStatus(int status,int id){
		//System.out.println(t.getLocktime());
		return tDAO.updateStatus(status, id);	
	}
	
	public int delete(int ID){
		return tDAO.delete(ID); 		
	}
	
	public List<Ticket> Fetch(String condt){
		return tDAO.select(condt);		
	}
	
	public List<Ticket> FetchAll(){
		return tDAO.select("");		
	}
	public boolean FetchSched(String condt){
		return tDAO.selectsched_id(condt);		
	}
	
	public int FetchLocktime(String condt){
		return tDAO.selectlocktime(condt);	
	}
	
	public Ticket FetchSeat(String seatid,String schedid){
		Ticket t = new Ticket();
		int playid=0;
		t = tDAO.selectseat_id(seatid);
		t.setPrice(scDAO.selectsched_id(schedid).getPrice());
		t.setTime(scDAO.selectsched_id(schedid).getTime());
		t.setPlayid(scDAO.selectsched_id(schedid).getPlayid());
		playid=t.getPlayid();
		t.setPlayname(pDAO.selectplay_id(playid).getPlayname());
		t.setStudioname("VIP厅");
		return t;		
	}
	public int FetchSeat(String seatid,int schedid){
		return tDAO.selectticketid(Integer.valueOf(seatid), schedid);
	}
}
