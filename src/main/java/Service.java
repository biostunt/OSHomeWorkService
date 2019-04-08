import Controller.JSONController;
import Controller.SQLController;
import Workers.ThreadContent;
import Workers.ThreadFiles;
import Workers.ThreadHeader;

public class Service {

    private SQLController sql;


    public Service(){}

    public void start(){
        System.out.println("Service Started.");
        openSQLConnection();
        runWorkers();
        closeSQLConnection();
        System.out.println("Service updated DB.");
    }

    private void runWorkers(){
        String[] settings = (new JSONController()).getThreadSettings();
        Thread header = new Thread(new ThreadHeader(settings[0],sql));
        Thread content = new Thread(new ThreadContent(settings[1],sql));
        Thread files = new Thread(new ThreadFiles(settings[2],sql));
        header.start();
        content.start();
        files.start();
        try{
            header.join();
            content.join();
            files.join();
        } catch (Exception e){}
    }
    private void openSQLConnection(){
        sql = new SQLController();
        sql.connect();
        sql.ClearDataFromTables();
    }
    private void closeSQLConnection(){
        sql.close();
    }

}
