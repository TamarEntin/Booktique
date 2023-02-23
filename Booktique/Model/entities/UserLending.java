package entities;

import java.util.Date;

public class UserLending {

    private String userId;
    private String userName;
    private String borrowID;
    private String bookId;
    private String bookName;
    private String authorName;
    private String category;
    private boolean isExtended;
    private Date startBorrowRequest; //user borrow
    private Date finalBorrowDate; //user should return before this date
    private int status;

    public UserLending(String userId, String username, String borrowID, String bookId, String bookName, 
    		String authorName,
                       String category, boolean isExtended, Date startBorrowRequest, Date finalBorrowDate, 
                       int status) {
        this.userName = username;
        this.userId = userId;
        this.borrowID = borrowID;
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.category = category;
        this.isExtended = isExtended;
        this.startBorrowRequest = startBorrowRequest;
        this.finalBorrowDate = finalBorrowDate;
        this.status = status;
    }

    public UserLending(String borrowID, String bookId, String bookName, String authorName, 
    		String category, boolean isExtended, Date startBorrowRequest, Date finalBorrowDate, int status) 
    {
        this.borrowID = borrowID;
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.category = category;
        this.isExtended = isExtended;
        this.startBorrowRequest = startBorrowRequest;
        this.finalBorrowDate = finalBorrowDate;
        this.status = status;
    }

    public String getBorrowID() {
        return borrowID;
    }

    public void setBorrowID(String borrowID) {
        this.borrowID = borrowID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isExtended() {
        return isExtended;
    }

    public void setExtended(boolean extended) {
        isExtended = extended;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
