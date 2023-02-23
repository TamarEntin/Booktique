package entities;

import java.util.Date;

public class EmployeeOrder {

    private String bookName;
    private String authorName;
    private String employeeName;
    private Date orderCreatedDate;
    private int quantity;
    private float price;

    public EmployeeOrder(String bookName, String authorName, String employeeName, Date orderCreatedDate, int quantity, float price) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.employeeName = employeeName;
        this.orderCreatedDate = orderCreatedDate;
        this.quantity = quantity;
        this.price = price;
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getOrderCreatedDate() {
        return orderCreatedDate;
    }

    public void setOrderCreatedDate(Date orderCreatedDate) {
        this.orderCreatedDate = orderCreatedDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
