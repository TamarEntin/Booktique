package jtableModel;

import entities.BookStock;

import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.Vector;

public class ManageBooksModel extends AbstractTableModel implements IJTableModel {

    private final static String[] columnNames = {"Book ID", "Book Name", "Author Name", "Quantity", "Category"};
    private Vector<BookStock> books;

    public String[] getColumnNames(){ return columnNames;}

    public ManageBooksModel(Vector<BookStock> data) {
        books = data;
        fireTableRowsInserted(books.size() - 1, books.size() - 1);
    }

    public ManageBooksModel() {
        books = new Vector<BookStock>();
    }

    public Vector<String> getColumns() {
        return new Vector<String>(Arrays.asList(columnNames));
    }

    public Vector<BookStock> getBooks() {
        return this.books;
    }

    public void addElement(BookStock e) {
        // Adds the element in the last position in the list
        books.add(e);
        fireTableRowsInserted(books.size() - 1, books.size() - 1);
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return books.get(rowIndex).getId();
            case 1:
                return books.get(rowIndex).getBookName();
            case 2:
                return books.get(rowIndex).getAuthorName();
            case 3:
                return books.get(rowIndex).getQuantity();
            case 4:
                return books.get(rowIndex).getCategory();
        }
        return null;
    }

    public boolean isCellEditable(int row, int column){
        return false;
    }
}
