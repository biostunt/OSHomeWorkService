package Controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class SODKController<T> {
    public SODKController(){}
    public T getObject(String filepath,int pos){
        T object = null;
        try{
            ObjectInputStream in =  new ObjectInputStream(
                    new FileInputStream(filepath));
            ArrayList<T> arrayList = (ArrayList<T>) in.readObject();
            object = arrayList.get(pos);
            in.close();
        }catch (Exception e){
            System.out.println("Error while getting object. Path:" + filepath);
        }
        return object;
    }
    private ArrayList<T> getAllObject(String filepath){
        ArrayList<T> arrayList = new ArrayList<T>();
        try{
            ObjectInputStream in =  new ObjectInputStream(new FileInputStream(filepath));
            arrayList = (ArrayList<T>) in.readObject();
            in.close();
        } catch (FileNotFoundException e){
            System.out.println("File didnt Found. Path:" + filepath);
        } catch (EOFException e){
            System.out.println("Unexpected EOF. Path:" + filepath);
        } catch (Exception e){
            System.out.println("Error while getting allObjects. Path:" + filepath);
        }
        return arrayList;
    }
    public int getArraySize(String filepath){
        return getAllObject(filepath).size();
    }
}
