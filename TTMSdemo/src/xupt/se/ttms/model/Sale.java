package xupt.se.ttms.model;

public class Sale {
	private int id=0;
	private int empid=0;
	private int cusid=0;
	private int status=0;
	private String saletime="";
	private String salepayment="";
	private String salechange="";
	private int saletype=0;
	private int salesort=0;
	public Sale() {
		id=0;
	}
	public Sale(int ID,int status ,int empid,int cusid,String saletime,String salepayment,String salechange,int saletype,int salesort) {
		id=ID;
		this.status=status;
		this.empid=empid;
		this.cusid=cusid;
		this.saletime=saletime;
		this.salechange=salechange;
		this.salepayment=salepayment;
		this.salesort=salesort;
		this.saletype=saletype;
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
	public int getEmpid() {
		return empid;
	}
	public int getCusid() {
		return cusid;
	}
	public int getStatus() {
		return status;
	}
	public String getSaletime() {
		return saletime;
	}
	public String getSalepayment() {
		return salepayment;
	}
	public String getSalechange() {
		return salechange;
	}
	public int getSaletype() {
		return saletype;
	}
	public int getSalesort() {
		return salesort;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public void setCusid(int cusid) {
		this.cusid = cusid;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setSaletime(String saletime) {
		this.saletime = saletime;
	}
	public void setSalepayment(String salepayment) {
		this.salepayment = salepayment;
	}
	public void setSalechange(String salechange) {
		this.salechange = salechange;
	}
	public void setSaletype(int saletype) {
		this.saletype = saletype;
	}
	public void setSalesort(int salesort) {
		this.salesort = salesort;
	}
}
