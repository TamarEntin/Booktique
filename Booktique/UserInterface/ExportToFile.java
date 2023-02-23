import jtableModel.IJTableModel;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportToFile {

    public static boolean exportToTextFile(JTable table, IJTableModel model, String fileName)
    {
        String _FileLocation = "C:\\Users\\USER\\eclipse-workspace\\Booktique\\Output";
        String _FileStorgeName = fileName + ".txt";
        String filepath = _FileLocation + "\\"+ _FileStorgeName;

        if (!setUpRepository(_FileLocation,_FileStorgeName))
            return false;

        File file = new File(filepath);
        try{
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for(int i = 0; i < table.getColumnCount(); i++)
            {
                bw.write(model.getColumnNames()[i] + "\t");
            }
            bw.newLine();

            for (int i = 0; i < table.getRowCount(); i++)
            {
                for (int j = 0; j< table.getColumnCount(); j++)
                {
                    bw.write(table.getValueAt(i,j).toString() + "\t");
                }
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
        catch(Exception e)
        {
            return  false;
        }
        return true;
    }

    private static boolean setUpRepository(String _FileLocation, String _FileStorgeName)
    {
        File files = new File(_FileLocation);

        if (!files.exists()) {
            if (!files.mkdirs()) {
                return false;
            }
        }

        File repositoryFullPath = new File(_FileLocation + "\\"+ _FileStorgeName);
        try {
            if (repositoryFullPath.createNewFile())
                return true;
        }
        catch (IOException e) {
            return false;
        }

        return true;
    }

}
