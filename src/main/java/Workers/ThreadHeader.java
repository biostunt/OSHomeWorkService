package Workers;


import Controller.SODKController;
import Controller.SQLController;
import SampleClasses.Header;

import java.util.ArrayList;

public class ThreadHeader extends DefaultWorker<Header> {

    public ThreadHeader(String filename, SQLController sql){
        super(sql,filename);
        initializeTableRows();
    }

    private void initializeTableRows(){
        tableRows = new ArrayList<String>();
        tableRows.add("id");
        tableRows.add("datapostid");
        tableRows.add("href");
        tableRows.add("author");
    }

    public void work(){
        Header object = (new SODKController<Header>()).getObject(filepath,currCount);
        if(object != null){
            ArrayList<String> values = new ArrayList<String>();
            values.add(object.id);
            values.add(object.dataPostId);
            values.add(object.href);
            values.add(object.author);
            sql.addToTable(tableName,tableRows,values);
            currCount++;
        }

    }


}
