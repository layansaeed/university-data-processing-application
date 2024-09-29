package second_section;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class DepartmentList extends DBObjectList<Department> {
	

    public DepartmentList(){
        super(new ArrayList<Department>());  
    }

    public DepartmentList(ArrayList<Department> list){
        super(list);
    }

    @Override
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