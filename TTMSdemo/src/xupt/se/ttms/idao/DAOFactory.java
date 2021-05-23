package xupt.se.ttms.idao;
import xupt.se.ttms.dao.*;

public class DAOFactory {
	private static iStudioDAO stuDao;
	public static synchronized iStudioDAO creatStudioDAO(){
		if(null==stuDao)
			stuDao = new StudioDAO();
		return stuDao;
	}	
	private static iSeatDAO seatDao;
	public static synchronized iSeatDAO creatSeatDAO(){
		if(null==seatDao)
			seatDao = new SeatDAO();
		return seatDao;
	}	
	private static iCustomerDAO cusdao;
	public static synchronized iCustomerDAO creatCustomerDAO(){
		if(null==cusdao)
			cusdao = new CusDAO();
		return cusdao;
	}	
	private static iEmployeeDAO empdao;
	public static synchronized iEmployeeDAO creatEmployeeDAO(){
		if(null==empdao)
			empdao = new EmpDAO();
		return empdao;
	}	
	private static iScheduleDAO schdao;
	public static synchronized iScheduleDAO creatScheduleDAO(){
		if(null==schdao)
			schdao = new ScheduleDAO();
		return schdao;
	}	
	private static iPlayDAO playdao;
	public static synchronized iPlayDAO creatPlayDAO(){
		if(null==playdao)
			playdao = new PlayDAO();
		return playdao;
	}	
	private static iTicketDAO tdao;
	public static synchronized iTicketDAO creatTicketDAO(){
		if(null==tdao)
			tdao = new TicketDAO();
		return tdao;
	}	
	private static iSaleDAO sdao;
	public static synchronized iSaleDAO creatSaleDAO(){
		if(null==sdao)
			sdao = new SaleDAO();
		return sdao;
	}	
	private static iSale_itemDAO sidao;
	public static synchronized iSale_itemDAO creatSale_itemDAO(){
		if(null==sidao)
			sidao = new Sale_itemDAO();
		return sidao;
	}	
/*	private static iCustomerDAO cusDao;
	public static synchronized iCustomerDAO creatCustomerDAO(){
		if(null==cusDaouDao)
			cusDao = new CustomerDAO();
		return stcusDaouDao;
	}	*/
}
