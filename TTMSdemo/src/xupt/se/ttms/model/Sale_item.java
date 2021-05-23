package xupt.se.ttms.model;

public class Sale_item {
	private int id=0;
	private int ticketid=0;
	private int saleid=0;
	private String price="";
	public Sale_item() {
		id=0;
	}
	public Sale_item(int ID,int ticketid ,int saleid,String price) {
		id=ID;
		this.ticketid=ticketid;
		this.saleid=saleid;
		this.price=price;
	}
	public void setID(int ID){
		this.id=ID;
	}
	
	public int getID(){
		return id;
	}
	public int getId() {
		return id;
	}
	public int getTicketid() {
		return ticketid;
	}
	public int getSaleid() {
		return saleid;
	}
	public String getPrice() {
		return price;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTicketid(int ticketid) {
		this.ticketid = ticketid;
	}
	public void setSaleid(int saleid) {
		this.saleid = saleid;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
