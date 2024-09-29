package second_section;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public abstract class DBObjectList<T extends DBObject> implements Cloneable, FileAsDB{
    protected final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    protected ArrayList<T> list;

    public DBObjectList(){
        this.list = new ArrayList<T>();
    }

    public DBObjectList(ArrayList<T> list){
        this.list = list;
    }

    public void add(T object){
        this.list.add(object);
    }

    public void remove(String id){
        this.list.removeIf(obj -> obj.getId().equals(id));
    }

    public DBObject getInstance(String id) {
        // Return the first instance that has this id
        Iterator<T> it = this.getIterator();

        while (it.hasNext()) {
            T obj = it.next();
            if (obj.getId().equals(id)) {
                return obj;
            }
        }
        return null;
    }

    public Iterator<T> getIterator(){
        return this.list.iterator();
    }

    public ArrayList<T> getList(){
        return this.list;
    }

    public ArrayList<T> getListById(String id){
        // Return the all instances that have this id
        return this.list.stream()
                .filter(obj -> obj.getId().equals(id))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void setList(ArrayList<T> list){
        this.list = list;
    }

    @Override
    public void writeToFile(String file, boolean extend_file){
        try {
            FileWriter myWriter = new FileWriter(file, extend_file);
            for(T item: list){
                myWriter.write(item.writeObject());
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public abstract void readFromFile(String file, boolean extend_list);

    @Override
    public DBObjectList<T> clone() {
        try {
            // Call Object's clone method to create a shallow copy
            DBObjectList<T> cloned = (DBObjectList<T>) super.clone();

            // Deep copy the list field
            cloned.list = new ArrayList<T>();
            Iterator<T> it = this.getIterator();

            while (it.hasNext()) {
                T item = it.next();
                cloned.list.add((T) item.clone());
            }

            return cloned;
        } catch (CloneNotSupportedException e) {
            // This should never happen since we are Cloneable
            throw new AssertionError();
        }
    }
    
    
}
