package xupt.se.ttms.model;

public class Schedule {
	private int id=0;
	private int studioid=0;
	private int playid=0;
	private String time="";
	private int status=0;
	private String price="";
	private String studioname="";
	private String playname="";
	private String playimage="";
	private String playintro="";
	public Schedule() {
		id=0;
	}
	public Schedule(int ID,int studioid,int status ,int playid,String time,String price,String studioname,String playimage,String playintro) {
		id=ID;
		this.studioid=studioid;
		this.status=status;
		this.playid=playid;
		this.time=time;
		this.price=price;
		this.studioname=studioname;
		this.playimage=playimage;
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
	public int getStudioid() {
		return studioid;
	}
	public int getPlayid() {
		return playid;
	}
	public String getTime() {
		return time;
	}
	public int getStatus() {
		return status;
	}
	public String getPrice() {
		return price;
	}
	public void setStudioid(int studioid) {
		this.studioid = studioid;
	}
	public void setPlayid(int playid) {
		this.playid = playid;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStudioname() {
		return studioname;
	}
	public void setStudioname(String studioname) {
		this.studioname = studioname;
	}
	public String getPlayname() {
		return playname;
	}
	public void setPlayname(String playname) {
		this.playname = playname;
	}
	public String getPlayimage() {
		return playimage;
	}
	public String getPlayintro() {
		return playintro;
	}
	public void setPlayimage(String playimage) {
		this.playimage = playimage;
	}
	public void setPlayintro(String playintro) {
		this.playintro = playintro;
	}
}
