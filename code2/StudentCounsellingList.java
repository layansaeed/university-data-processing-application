package second_section;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class StudentCounsellingList extends DBObjectList<StudentCounselling> {
    public StudentCounsellingList() {
        super(new ArrayList<StudentCounselling>());
    }

    public StudentCounsellingList(ArrayList<StudentCounselling> list) {
        super(list);
    }
    
 //here

    public ArrayList<StudentCounselling> getListByChoiceDepartmentID(String id) {
        return super.getList().stream()
                .filter(obj -> obj.getDepartmentChoiceId().equals(id))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<StudentCounselling> getListByAdmissionDepartmentID(String id) {
        return super.getList().stream()
                .filter(obj -> obj.getDepartmentAdmissionId().equals(id))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<StudentCounselling> getListByBirthDateRange(Date from, Date to) {
        return super.getList().stream()
                .filter(obj -> !obj.getDateOfBirth().before(from) && !obj.getDateOfBirth().after(to))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<StudentCounselling> getListByAdmissionDateRange(Date from, Date to) {
        return super.getList().stream()
                .filter(obj -> !obj.getDateOfAdmission().before(from) && !obj.getDateOfAdmission().after(to))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
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
