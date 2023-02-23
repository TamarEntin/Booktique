package entities;

public class BooksInOrders extends Entity 
	{
    private String bookInOrderID;
    private String orderID;
    private String bookName;
    private String category;

    public BooksInOrders(){}

    public BooksInOrders(String orderID, String bookName, String category)
    {
        this.bookInOrderID = Entity.id;
        this.orderID = orderID;
        this.bookName = bookName;
        this.category = category;
    }

    public BooksInOrders(String bookInOrderID, String orderID, String bookName, String category) 
    {
        this.bookInOrderID = bookInOrderID;
        this.orderID = orderID;
        this.bookName = bookName;
        this.category = category;
    }

    @Override
    public String toString() 
    {
        return "BooksInOrders{" +
                "BookInOrderID=" + bookInOrderID +
                ", orderID=" + orderID +
                ", bookName='" + bookName + '\'' +
                ", category=" + category +
                '}';
    }

    public String getOrderID() 
    {
        return orderID;
    }

    public void setOrderID(String orderID) 
    {
        this.orderID = orderID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBookInOrderID() {
        return bookInOrderID;
    }

    public void setBookInOrderID(int bookInOrderID) {
        bookInOrderID = bookInOrderID;
    }
}
