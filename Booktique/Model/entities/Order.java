package entities;

import java.util.Date;
import java.util.UUID;

public class Order extends Entity {

    private String orderID;
    private float price;
    private Date orderCreateDate;
    private Date orderCheckedDate;
    private boolean isCanceled;
    private String librarianID;
    private String bookName;
    private String authorName;
    private int quantity;

    public Order(){}

    public Order(float price, Date orderCreateDate, Date orderCheckedDate, boolean isCanceled, String librarianID, String bookName, String authorName, int quantity) {
        this.orderID = UUID.randomUUID().toString();
        this.price = price;
        this.orderCreateDate = orderCreateDate;
        this.orderCheckedDate = orderCheckedDate;
        this.isCanceled = isCanceled;
        this.librarianID = librarianID;
        this.bookName = bookName;
        this.authorName = authorName;
        this.quantity = quantity;
    }

    public Order(String orderID, float price, Date orderCreateDate, Date orderCheckedDate, boolean isCanceled, String librarianID, String bookName, String authorName, int quantity) {
        this.orderID = orderID;
        this.price = price;
        this.orderCreateDate = orderCreateDate;
        this.orderCheckedDate = orderCheckedDate;
        this.isCanceled = isCanceled;
        this.librarianID = librarianID;
        this.bookName = bookName;
        this.authorName = authorName;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", price=" + price +
                ", orderCreateDate=" + orderCreateDate +
                ", orderCheckedDate=" + orderCheckedDate +
                ", isCanceled=" + isCanceled +
                ", librarianID=" + librarianID +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getOrderCreateDate() {
        return orderCreateDate;
    }

    public void setOrderCreateDate(Date orderCreateDate) {
        this.orderCreateDate = orderCreateDate;
    }

    public Date getOrderCheckedDate() {
        return orderCheckedDate;
    }

    public void setOrderCheckedDate(Date orderCheckedDate) {
        this.orderCheckedDate = orderCheckedDate;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public String getLibrarianID() {
        return librarianID;
    }

    public void setLibrarianID(String librarianID) {
        this.librarianID = librarianID;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
