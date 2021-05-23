package xupt.se.ttms.model;

public class Play {
	private int id=0;
	private int playid=0;
	private int status=0;
	private String price="";
	private String playintro="";
	private String playname="";
	private String playimage="";
	public Play() {
		id=0;
	}
	public Play(int ID,int status ,int playid,String playimage,String price,String playname,String playintro) {
		id=ID;
		this.status=status;
		this.playid=playid;
		this.playimage=playimage;
		this.price=price;
		this.playname=playname;
		this.playintro=playintro;
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
	public void setId(int id) {
		this.id = id;
	}
	public int getPlayid() {
		return playid;
	}
	public int getStatus() {
		return status;
	}
	public String getPrice() {
		return price;
	}
	public String getPlayintro() {
		return playintro;
	}
	public String getPlayname() {
		return playname;
	}
	public String getPlayimage() {
		return playimage;
	}
	public void setPlayid(int playid) {
		this.playid = playid;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public void setPlayintro(String playintro) {
		this.playintro = playintro;
	}
	public void setPlayname(String playname) {
		this.playname = playname;
	}
	public void setPlayimage(String playimage) {
		this.playimage = playimage;
	}
}
