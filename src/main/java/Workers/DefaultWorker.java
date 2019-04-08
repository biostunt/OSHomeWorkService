package Workers;

import Controller.JSONController;
import Controller.SODKController;
import Controller.SQLController;
import Controller.SocketExtension;

import java.util.ArrayList;

public abstract class DefaultWorker<T> extends SocketExtension implements Runnable {

    protected int maxCount = 1;
    protected int currCount = 0;
    protected boolean isFinished;

    protected String tableName;
    protected String filename;
    protected String directory;
    protected String filepath;


    protected SQLController sql;
    protected ArrayList<String> tableRows;


    public DefaultWorker(SQLController sql, String filename) {
        super();
        this.tableName = getTableName(filename);
        this.filename = filename;
        this.directory = getDir();
        this.filepath = directory + filename;
        this.sql = sql;
    }
    public void run(){
        while(!isFinished){
            take();
            workCall();
            release();
        }
        workCall();
    }
    protected void workCall(){
        while(currCount < maxCount && maxCount != 0){
            upload();
        }
        System.out.println(filename + " " + currCount);
        setMaxCount();
        setFinishStatement();
    }
    protected void upload(){
        currCount++;
    }
    private String getTableName(String filename){
        String key = "";
        for(int i = 0; i < filename.lastIndexOf(".dat");i++){
            key += filename.toCharArray()[i];
        }
        return key;
    }
    private void setMaxCount(){

        maxCount = new SODKController<T>().getArraySize(filepath);
    }
    private void setFinishStatement(){
        String statDir = directory + "..\\statements\\" + tableName + ".json";
        isFinished = (Integer.parseInt(new JSONController().getJsonStatement(statDir,"isFinished"))) == 1;
    }
}
