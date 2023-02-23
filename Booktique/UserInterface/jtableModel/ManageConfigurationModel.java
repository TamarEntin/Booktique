package jtableModel;

import entities.Configuration;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ManageConfigurationModel extends AbstractTableModel implements IJTableModel
{
    private final static String[] columnNames = {"Config ID", "Config Key", "Config Value", "Description"};

    public List<Configuration> getConfigurations() {
        return configurations;
    }

    private List<Configuration> configurations;

    public ManageConfigurationModel(List<Configuration> configurations)
    {
        this.configurations = configurations;
    }

    @Override
    public int getRowCount() {
        return columnNames.length;
    }

    @Override
    public int getColumnCount() {
        return configurations.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return configurations.get(rowIndex).getConfigId();
            case 1:
                return configurations.get(rowIndex).getConfigKey();
            case 2:
                return configurations.get(rowIndex).getConfigValue();
            case 3:
                return configurations.get(rowIndex).getDescription();
        }
        return null;
    }

    @Override
    public String[] getColumnNames() {
        return columnNames;
    }
}
