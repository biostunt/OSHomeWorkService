package Workers;

import Controller.SODKController;
import Controller.SQLController;
import SampleClasses.Content;

import java.util.ArrayList;

public class ThreadContent extends DefaultWorker<Content> {

    public ThreadContent(String filename, SQLController sql){
        super(sql,filename);
        initializeTableRows();
    }
    private void initializeTableRows(){
        tableRows = new ArrayList<String>();
        tableRows.add("id");
        tableRows.add("text");
    }

    public void work(){
        Content object = (new SODKController<Content>()).getObject(filepath,currCount);
        if(object != null){
            ArrayList<String> values = new ArrayList<String>();
            values.add(object.id);
            values.add(object.text);
            sql.addToTable(tableName,tableRows,values);
            currCount++;
        }

    }


}
