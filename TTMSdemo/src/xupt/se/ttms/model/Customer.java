package xupt.se.ttms.model;

public class Customer {
	private int id=0; 
	private String uid="";
	private int gender=0;
	private String phone="";
	private String email="";
	private String password="";
	private int status=0;
	private String name="";
	public Customer() {
		id=0;
	}
	public Customer(int ID,int gender,String phone ,String password,int status,String email,String name,String uid) {
		id=ID;
		this.gender=gender;
		this.status=status;
		this.phone=phone;
		this.password=password;
		this.email=email;
		this.name=name;
		this.uid=uid;
	}
	public int getId() {
		return id;
	}
	public int getGender() {
		return gender;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public int getStatus() {
		return status;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setID(int ID){
		this.id=ID;
	}
	
	public int getID(){
		return id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
}
