package entities;

import java.util.Date;
import java.util.UUID;

public class BorrowedBook extends Entity {

    private String borrowID;
    private String userID;
    private String bookID;
    private boolean isExtended;
    private Date startBorrowRequest; //user borrow
    private Date finalBorrowDate; //user should return before this date
    private Date endBorrowRequest; //user return before approve
    private Date endBorrowOfficial; //librarian approve
    private int status;

    public Date getStartBorrowRequest() {
        return startBorrowRequest;
    }

    public void setStartBorrowRequest(Date startBorrowRequest) {
        this.startBorrowRequest = startBorrowRequest;
    }

    public Date getFinalBorrowDate() {
        return finalBorrowDate;
    }

    public void setFinalBorrowDate(Date finalBorrowDate) {
        this.finalBorrowDate = finalBorrowDate;
    }

    public BorrowedBook(){}

    public BorrowedBook(String userID, String bookID, boolean isExtended, Date endBorrowRequest, Date endBorrowOfficial, int status) {
        this.borrowID = UUID.randomUUID().toString();
        this.userID = userID;
        this.bookID = bookID;
        this.isExtended = isExtended;
        this.endBorrowRequest = endBorrowRequest;
        this.endBorrowOfficial = endBorrowOfficial;
        this.status = status;
    }

    public BorrowedBook(String borrowID, String userID, String bookID, boolean isExtended, Date endBorrowRequest, Date endBorrowOfficial, int status) {
        this.borrowID = borrowID;
        this.userID = userID;
        this.bookID = bookID;
        this.isExtended = isExtended;
        this.endBorrowRequest = endBorrowRequest;
        this.endBorrowOfficial = endBorrowOfficial;
        this.status = status;
    }

    public BorrowedBook(String borrowID, String userID, String bookID, boolean isExtended, Date startBorrowRequest, Date finalBorrowDate, Date endBorrowRequest, Date endBorrowOfficial, int status) {
        this.borrowID = borrowID;
        this.userID = userID;
        this.bookID = bookID;
        this.isExtended = isExtended;
        this.startBorrowRequest = startBorrowRequest;
        this.finalBorrowDate = finalBorrowDate;
        this.endBorrowRequest = endBorrowRequest;
        this.endBorrowOfficial = endBorrowOfficial;
        this.status = status;
    }

    @Override
    public String toString() {
        return "BorrowedBook{" +
                "borrowID=" + borrowID +
                ", userID='" + userID + '\'' +
                ", bookID=" + bookID +
                ", isExtended=" + isExtended +
                ", startBorrowRequest=" + startBorrowRequest +
                ", finalBorrowDate=" + finalBorrowDate +
                ", endBorrowRequest=" + endBorrowRequest +
                ", endBorrowOfficial=" + endBorrowOfficial +
                ", status=" + status +
                '}';
    }

    public String getBorrowID() {
        return borrowID;
    }

    public void setBorrowID(String borrowID) {
        this.borrowID = borrowID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public boolean isExtended() {
        return isExtended;
    }

    public void setExtended(boolean extended) {
        isExtended = extended;
    }

    public Date getEndBorrowRequest() {
        return endBorrowRequest;
    }

    public void setEndBorrowRequest(Date endBorrowRequest) {
        this.endBorrowRequest = endBorrowRequest;
    }

    public Date getEndBorrowOfficial() {
        return endBorrowOfficial;
    }

    public void setEndBorrowOfficial(Date endBorrowOfficial) {
        this.endBorrowOfficial = endBorrowOfficial;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
