import java.sql.*;

public class Transaction{
    int id;
    int cid;
    int lid;
    Date startDate;
    Date endDate;

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

}