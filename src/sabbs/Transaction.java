package sabbs;
import java.sql.*;

public class Transaction{
    private int id;
    private int cid;
    private int lid;
    private Date startDate;
    private Date endDate;

    private String address;
    private String region;


    public Transaction(int cid, int lid, Date startDate, Date endDate) {
        this.cid = cid;
        this.lid = lid;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public Transaction(int cid, int lid, Date startDate, Date endDate, String region, String address) {
        this.cid = cid;
        this.lid = lid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.region = region;
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s, \"%s\", \"%s\")", cid, lid, startDate, endDate);
    }

    public int getId() {
        return id;
    }

    public int getCid() {
        return cid;
    }

    public int getLid() {
        return lid;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getAddress() {
        return address;
    }

    public String getRegion() {
        return region;
    }
}
