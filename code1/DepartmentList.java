package first_section;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class DepartmentList{
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private ArrayList<Department> list;
    public DepartmentList(){
        this.list = new ArrayList<Department>();
    }

    public DepartmentList(ArrayList<Department> list){
        this.list = list;
    }

    public void add(Department object){
        this.list.add(object);
    }

    public void remove(String id){
        this.list.removeIf(obj -> obj.getId().equals(id));
    }

    public Department getInstance(String id) {
        // Return the first instance that has this id
        int size = this.list.size();
        for (int i=0;i<size;i++) {
            if (list.get(i).getId().equals(id)) {
                return list.get(i);
            }
        }
        return null;
    }
    public ArrayList<Department> getList(){
        return this.list;
    }

    public ArrayList<Department> getListById(String id){
        // Return the all instances that have this id
        return this.list.stream()
                .filter(obj -> obj.getId().equals(id))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void setList(ArrayList<Department> list){
        this.list = list;
    }
    
    public void writeToFile(String file, boolean extend_file){
        try {
            FileWriter myWriter = new FileWriter(file, extend_file);
            for(Department item: list){
                myWriter.write(item.writeObject());
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readFromFile(String file, boolean extend_list) {
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader(file);
            br = new BufferedReader(fr);
            String s = br.readLine();

            if(!extend_list){
                this.list = new ArrayList<Department>();
            }
            while (s!= null){
                String[] attrs= s.split("\\|");
                Department department = new Department(
                        attrs[0],attrs[1],dateFormat.parse(attrs[2])
                );
                this.list.add(department);
                s = br.readLine();
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred when reading.");
            e.printStackTrace();
        }
        catch (ParseException e) {
            System.out.println("An error occurred when parsing.");
            e.printStackTrace();
        }
        finally {
            try {
                if (br != null)
                    br.close();
            }
            catch (IOException e) {
                System.out.println("An error occurred when closing.");
                e.printStackTrace();
            }
        }
    }
}