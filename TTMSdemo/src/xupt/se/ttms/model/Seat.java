package xupt.se.ttms.model;

public class Seat {
	private int id=0; 
	private int studioid=0;
	private int status=0;
	private int row=0;
	private int col=0;
	private String name="";
	public Seat() {
		id=0;
	}
	public Seat(int ID,int studioid,int status ,int row,int col,String name) {
		id=ID;
		this.studioid=studioid;
		this.status=status;
		this.row=row;
		this.col=col;
		this.name=name;
	}
	public void setID(int ID){
		this.id=ID;
	}
	
	public int getID(){
		return id;
	}
	public int getStudioid() {
		return studioid;
	}
	public int getId() {
		return id;
	}
	public int getStatus() {
		return status;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public void setStudioid(int studioid) {
		this.studioid = studioid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
