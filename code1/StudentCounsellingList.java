package first_section;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;
public class StudentCounsellingList {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private ArrayList<StudentCounselling> list;
    public StudentCounsellingList(){
        this.list = new ArrayList<StudentCounselling>();
    }

    public StudentCounsellingList(ArrayList<StudentCounselling> list){
        this.list = list;
    }

    public void add(StudentCounselling object){
        this.list.add(object);
    }

    public void remove(String id){
        this.list.removeIf(obj -> obj.getId().equals(id));
    }

    public StudentCounselling getInstance(String id) {
        // Return the first instance that has this id
        int size = this.list.size();
        for (int i=0;i<size;i++) {
            if (list.get(i).getId().equals(id)) {
                return list.get(i);
            }
        }
        return null;
    }

    public ArrayList<StudentCounselling> getList(){
        return this.list;
    }

    public ArrayList<StudentCounselling> getListById(String id){
        // Return the all instances that have this id
        return this.list.stream()
                .filter(obj -> obj.getId().equals(id))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void setList(ArrayList<StudentCounselling> list){
        this.list = list;
    }
// here 
    public ArrayList<StudentCounselling> getListByChoiceDepartmentID(String id) {
        return this.getList().stream()
                .filter(obj -> obj.getDepartmentChoiceId().equals(id))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<StudentCounselling> getListByAdmissionDepartmentID(String id) {
        return this.getList().stream()
                .filter(obj -> obj.getDepartmentAdmissionId().equals(id))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<StudentCounselling> getListByBirthDateRange(Date from, Date to) {
        return this.getList().stream()
                .filter(obj -> !obj.getDateOfBirth().before(from) && !obj.getDateOfBirth().after(to))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<StudentCounselling> getListByAdmissionDateRange(Date from, Date to) {
        return this.getList().stream()
                .filter(obj -> !obj.getDateOfAdmission().before(from) && !obj.getDateOfAdmission().after(to))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void writeToFile(String file, boolean extend_file){
        try {
            FileWriter myWriter = new FileWriter(file, extend_file);
            for(StudentCounselling item: list){
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
                this.list = new ArrayList<StudentCounselling>();
            }
            while (s!= null){
                String[] attrs= s.split("\\|");
                StudentCounselling student_counselling = new StudentCounselling(
                        attrs[0],dateFormat.parse(attrs[1]),dateFormat.parse(attrs[2]),attrs[3],attrs[4]
                );
                this.list.add(student_counselling);
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
