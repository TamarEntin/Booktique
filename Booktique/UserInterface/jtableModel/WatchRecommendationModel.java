package jtableModel;

import entities.BookStock;
import entities.Recommendation;

import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.Vector;

public class WatchRecommendationModel extends AbstractTableModel implements IJTableModel{
    private final static String[] columnNames = {"Book ID", "Book Name", "Author Name", "Quantity", "Category"};
    private Vector<Recommendation> recommendations;

    public WatchRecommendationModel(Vector<Recommendation> data) {
        recommendations = data;
        fireTableRowsInserted(recommendations.size() - 1, recommendations.size() - 1);
    }

    @Override
    public String[] getColumnNames() {
        return columnNames;
    }

    public WatchRecommendationModel() {
        recommendations = new Vector<Recommendation>();
    }

    public Vector<String> getColumns() {
        return new Vector<String>(Arrays.asList(columnNames));
    }

    public Vector<Recommendation> getBooks() {
        return this.recommendations;
    }

    public void addElement(Recommendation e) {
        // Adds the element in the last position in the list
        recommendations.add(e);
        fireTableRowsInserted(recommendations.size() - 1, recommendations.size() - 1);
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return recommendations.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
         /*   case 0:
                return recommendations.get(rowIndex).getBookID();
            case 1:
                return recommendations.get(rowIndex).getUserID();
            case 2:
                return recommendations.get(rowIndex).getAuthorName();
            case 3:
                return recommendations.get(rowIndex).getQuantity();
            case 4:
                return recommendations.get(rowIndex).getCategory();*/
        }
        return null;
    }
    
    public boolean isCellEditable(int row, int column){
        return false;
    }
}
