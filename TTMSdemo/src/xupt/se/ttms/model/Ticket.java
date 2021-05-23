package xupt.se.ttms.model;

public class Ticket
{
    private int id=0;
    private int seatid=0;
    private int playid=0;
    private int studioid=0;
    private int row=0;
    private int col=0;
    private int seatstatus=0;
    private int scheduleid=0;
    private int status=0;
    private String price="";
    private String time="";
    private String playname="";
    private String playimage="";
    private String locktime="";
    private String studioname="";

    public Ticket()
    {
        id=0;
    }

    public Ticket(int ID, int status, int row, int col, int seatstatus, int studioid, String studioname, int playid,
            int seatid, int scheduleid, String playimage, String price, String playname, String time, String locktime)
    {
        id=ID;
        this.status=status;
        this.seatstatus=seatstatus;
        this.row=row;
        this.col=col;
        this.studioid=studioid;
        this.seatid=seatid;
        this.playid=playid;
        this.studioname=studioname;
        this.scheduleid=scheduleid;
        this.playimage=playimage;
        this.price=price;
        this.playname=playname;
        this.time=time;
        this.locktime=locktime;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public int getSeatstatus()
    {
        return seatstatus;
    }

    public void setRow(int row)
    {
        this.row=row;
    }

    public void setCol(int col)
    {
        this.col=col;
    }

    public void setSeatstatus(int seatstatus)
    {
        this.seatstatus=seatstatus;
    }

    public int getStudioid()
    {
        return studioid;
    }

    public void setStudioid(int studioid)
    {
        this.studioid=studioid;
    }

    public int getPlayid()
    {
        return playid;
    }

    public void setPlayid(int playid)
    {
        this.playid=playid;
    }

    public String getStudioname()
    {
        return studioname;
    }

    public void setStudioname(String studioname)
    {
        this.studioname=studioname;
    }

    public void setID(int ID)
    {
        this.id=ID;
    }

    public int getID()
    {
        return id;
    }

    public int getId()
    {
        return id;
    }

    public int getSeatid()
    {
        return seatid;
    }

    public int getScheduleid()
    {
        return scheduleid;
    }

    public int getStatus()
    {
        return status;
    }

    public String getPrice()
    {
        return price;
    }

    public String getTime()
    {
        return time;
    }

    public String getPlayname()
    {
        return playname;
    }

    public String getPlayimage()
    {
        return playimage;
    }

    public void setId(int id)
    {
        this.id=id;
    }

    public void setSeatid(int seatid)
    {
        this.seatid=seatid;
    }

    public void setScheduleid(int scheduleid)
    {
        this.scheduleid=scheduleid;
    }

    public void setStatus(int status)
    {
        this.status=status;
    }

    public void setPrice(String price)
    {
        this.price=price;
    }

    public void setTime(String time)
    {
        this.time=time;
    }

    public void setPlayname(String playname)
    {
        this.playname=playname;
    }

    public void setPlayimage(String playimage)
    {
        this.playimage=playimage;
    }

    public String getLocktime()
    {
        return locktime;
    }

    public void setLocktime(String locktime)
    {
        this.locktime=locktime;
    }
}
