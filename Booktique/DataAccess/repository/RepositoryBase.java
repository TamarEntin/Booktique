package repository;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public abstract class RepositoryBase<TEntity> {

    protected String _FileLocation = "C:\\Users\\USER\\eclipse-workspace\\Booktique\\REPOSITORY";
    protected String _FileStorgeName = this.getClass().getName() + ".txt";
    protected Date _lastUpdatedRepository;

    protected Vector<TEntity> loadData() {
        if (!setUpRepository())
            return null;

        try {
            String filepath = _FileLocation + "\\"+ _FileStorgeName;

            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();
            if (obj == null)
                return null;

            Vector<TEntity> data = (Vector<TEntity>) obj;

            System.out.println("The Object has been read from the file");
            objectIn.close();

            _lastUpdatedRepository = new Date();
             return data;
        } catch (Exception ex) {
            //ex.printStackTrace();
            return null;
        }
    }

    protected boolean saveData(Vector<TEntity> data) {
        if (!setUpRepository())
            return false;

        FileOutputStream fileOut = null;
        ObjectOutputStream objectOut = null;

        try {

            String filepath = _FileLocation + "\\"+ _FileStorgeName;

            //fileOut = new FileOutputStream("c:\\temp\\address.ser");
            fileOut = new FileOutputStream(filepath);
            objectOut = new ObjectOutputStream(fileOut);

            //data.forEach(item -> objectOut.writeObject(item));
            objectOut.writeObject(data);

            System.out.println("Done");

        } catch (Exception ex) {

            ex.printStackTrace();

        } finally {

            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            if (objectOut != null) {
                try {
                    objectOut.close();
                } catch (IOException e) {
                    //e.printStackTrace();
                    return false;
                }
            }

        }
        return true;
    }

    private boolean setUpRepository()
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
