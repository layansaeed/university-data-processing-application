package first_section;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class EmployeeList {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private ArrayList<Employee> list;
    public EmployeeList(){
        this.list = new ArrayList<Employee>();
    }

    public EmployeeList(ArrayList<Employee> list){
        this.list = list;
    }

    public void add(Employee object){
        this.list.add(object);
    }

    public void remove(String id){
        this.list.removeIf(obj -> obj.getId().equals(id));
    }

    public Employee getInstance(String id) {
        // Return the first instance that has this id
        int size = this.list.size();
        for (int i=0;i<size;i++) {
            if (list.get(i).getId().equals(id)) {
                return list.get(i);
            }
        }
        return null;
    }

    public ArrayList<Employee> getList(){
        return this.list;
    }

    public ArrayList<Employee> getListById(String id){
        // Return the all instances that have this id
        return this.list.stream()
                .filter(obj -> obj.getId().equals(id))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void setList(ArrayList<Employee> list){
        this.list = list;
    }


    public Employee getEmployeeByDepartment(String id){
        for (Employee obj : this.list) {
            if (obj.getDepartmentId().equals(id)) {
                return obj;
            }
        }
        return null;
    }

    public void writeToFile(String file, boolean extend_file){
        try {
            FileWriter myWriter = new FileWriter(file, extend_file);
            for(Employee item: list){
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
                this.list = new ArrayList<Employee>();
            }
            while (s!= null){
                String[] attrs= s.split("\\|");
                Employee employee = new Employee(
                        attrs[0],dateFormat.parse(attrs[1]),dateFormat.parse(attrs[2]),attrs[3]
                );
                this.list.add(employee);
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