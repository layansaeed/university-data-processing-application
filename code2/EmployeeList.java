package second_section;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EmployeeList extends DBObjectList<Employee> {

    public EmployeeList(){
        super(new ArrayList<Employee>());
    }

    public EmployeeList(ArrayList<Employee> list){
        super(list);
    }

    public ArrayList<Employee> getListByDepartmentId(String id){
        ArrayList<Employee> employees = new ArrayList<Employee>();
        for (Employee obj : this.list) {
            if (obj.getDepartmentId().equals(id)) {
                employees.add(obj);
            }
        }
        return employees;
    }

    @Override
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