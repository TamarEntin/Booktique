package entities;

public class CategoryReport {
    private String categoryName;
    private int numberOfBooks;


    public CategoryReport(String categoryName, int numberOfBooks) {
        this.categoryName = categoryName;
        this.numberOfBooks = numberOfBooks;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }
}
