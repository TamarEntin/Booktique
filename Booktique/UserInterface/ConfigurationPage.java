import entities.Configuration;
import entities.User;
import enums.ResponseStatus;
import jtableModel.ManageConfigurationModel;
import serviceHost.ServiceCommand;
import services.requests.SearchConfigurationRequest;
import services.requests.UpdateConfigurationRequest;
import services.responses.SearchConfigurationResponse;
import services.responses.UpdateConfigurationResponse;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class ConfigurationPage implements IFinishedCommand{
    private User user;
    private ServiceCommand sc;
    private JFrame frame;
    private ManageConfigurationModel model;
    private Vector<Configuration> configsToUpdate;
    private IUpdateFrameCommand menuCommand;

    public ConfigurationPage(IUpdateFrameCommand command, User user)
    {
        this.user = user;
        sc = ServiceCommand.getInstance();
        configsToUpdate = new Vector<Configuration>();
        this.menuCommand = command;
    }

    private JTable manageConfigurationsTable() {

        SearchConfigurationRequest request = new SearchConfigurationRequest(user);
        SearchConfigurationResponse response = sc.execute(request);

        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
            model = new ManageConfigurationModel(response.getConfiguration());
            JTable configurationsTable = new JTable(convert(model.getConfigurations()), model.getColumnNames())
            {
                @Override
                public boolean isCellEditable(int row, int col) {
                    if( col == 0 || col == 1 )
                        return false;
                    return true;
                }
            };

            configurationsTable.getModel().addTableModelListener (new TableModelListener() {
                public void tableChanged(TableModelEvent event) {
                    int row = configurationsTable.getSelectedRow();
                    Configuration conf = new Configuration();
                    conf.setConfigId((String) configurationsTable.getModel().getValueAt(row, 0));
                    conf.setConfigKey((String) configurationsTable.getModel().getValueAt(row, 1));
                    conf.setConfigValue((String) configurationsTable.getModel().getValueAt(row, 2));
                    conf.setDescription((String) configurationsTable.getModel().getValueAt(row, 3));
                    configUpdated(conf);
                }
            });
            return configurationsTable;
        }
        return new JTable();
    }

    public JFrame configurationFrame()
    {
        frame = new JFrame();

        final JTable table = manageConfigurationsTable();
        JPanel btnPnl = new JPanel(new BorderLayout());
        JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton updateUsers = new JButton("Save");
        updateUsers.setEnabled(true);

        bottombtnPnl.add(updateUsers);

        updateUsers.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e) {
                UpdateConfigurationRequest request = new UpdateConfigurationRequest(user, configsToUpdate);
                UpdateConfigurationResponse response = sc.execute(request);
                if(response.getStatus() != ResponseStatus.OK.errorCode())
                {
                    JOptionPane.showMessageDialog(null,response.getErrorMessage()); //Display Message
                }
                else {
                    JOptionPane.showMessageDialog(null,"Updated Configuration Successfully"); //Display Message
                }
            }
        });

        btnPnl.add(bottombtnPnl, BorderLayout.CENTER);

        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        // Force the scrollbars to always be displayed
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel upPnl = new JPanel(new BorderLayout());
        final JLabel titleLabel = new JLabel("<html><h2>Mange Configurations</h2></html>");
        upPnl.add(titleLabel);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        frame.add(upPnl, BorderLayout.NORTH);
        frame.add(table.getTableHeader(), BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(btnPnl, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
        return frame;
    }

    public String[][] convert(List<Configuration> configurations)
    {
        String [][] stringM = new String[configurations.size()][5];
        for (int i = 0; i < configurations.size(); i ++){
            Configuration config = configurations.get(i);
            String [] configsArray = new String[5];
            configsArray[0] = config.getConfigId();
            configsArray[1] = config.getConfigKey();
            configsArray[2] = config.getConfigValue();
            configsArray[3] = String.valueOf(config.getDescription());
            stringM[i] = configsArray;
        }
        return stringM;
    }

    private void configUpdated(Configuration configToUpdate)
    {
        if (configToUpdate == null) return;
        boolean isInserted = false;
        Vector<Configuration> usersToRemove = new Vector<Configuration>();
        Vector<Configuration> usersToAdd = new Vector<Configuration>();
        for (Configuration config: configsToUpdate) {
            if (config.getConfigId().equalsIgnoreCase(configToUpdate.getConfigId()))
            {
                usersToRemove.add(config);
                usersToAdd.add(configToUpdate);
                isInserted = true;
            }
        }

        if (isInserted)
        {
            configsToUpdate.removeAll(usersToRemove);
            configsToUpdate.addAll(usersToAdd);
        }
        else
        {
            configsToUpdate.add(configToUpdate);
        }
    }

    @Override
    public void finishedCommand() {
        configurationFrame();
        this.menuCommand.updateFrame(frame);
    }
}
