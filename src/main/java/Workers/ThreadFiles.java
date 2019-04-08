package Workers;

import Controller.SODKController;
import Controller.SQLController;
import SampleClasses.Files;

import java.util.ArrayList;

public class ThreadFiles extends DefaultWorker<Files> {

    public ThreadFiles(String filename, SQLController sql){
        super(sql,filename);
        initializeTableRows();
    }

    private void initializeTableRows(){
        tableRows = new ArrayList<String>();
        tableRows.add("id");
        tableRows.add("url");
    }

    public void work(){
        Files object = (new SODKController<Files>()).getObject(filepath,currCount);
        if(object != null){
            if(object.url != null){
                for(int i = 0 ; i < object.url.length; i++){
                    ArrayList<String> values = new ArrayList<String>();
                    values.add(object.id);
                    values.add(object.url[i]);
                    sql.addToTable(tableName,tableRows,values);
                }
            }
            currCount++;
        }

    }

}
