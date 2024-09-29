package first_section;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import java.io.FileWriter;

public class StudentPerformanceList {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private ArrayList<StudentPerformance> list;

    public StudentPerformanceList(){
        this.list = new ArrayList<StudentPerformance>();
    }

    public StudentPerformanceList(ArrayList<StudentPerformance> list){
        this.list = list;
    }

    public void add(StudentPerformance object){
        this.list.add(object);
    }

    public void remove(String id){
        this.list.removeIf(obj -> obj.getId().equals(id));
    }

    public StudentPerformance getInstance(String id) {
        // Return the first instance that has this id
        int size = this.list.size();
        for (int i=0;i<size;i++) {
            if (list.get(i).getId().equals(id)) {
                return list.get(i);
            }
        }
        return null;
    }

    public ArrayList<StudentPerformance> getList(){
        return this.list;
    }

    public ArrayList<StudentPerformance> getListById(String id){
        // Return the all instances that have this id
        return this.list.stream()
                .filter(obj -> obj.getId().equals(id))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void setList(ArrayList<StudentPerformance> list){
        this.list = list;
    }

    public int getNumberOfPapers(String studentID) {
        int count = 0;
        for (StudentPerformance obj : this.getList()) {
            if (obj.getId().equals(studentID)) {
                count++;
            }
        }
        return count;
    }

    public int getNumberOfSemesters(String studentID) {
        HashSet<String> semesters = new HashSet<String>();
        for (StudentPerformance obj : this.getList()) {
            if (obj.getId().equals(studentID)) {
                String semesterName = obj.getSemesterName();
                semesters.add(semesterName);
            }
        }
        return semesters.size();
    }

    public double getAverageMark(String studentID) {
        int sum = 0;
        int count = 0;
        for (StudentPerformance obj : this.getList()) {
            if (obj.getId().equals(studentID)) {
                sum += obj.getMark();
                count++;
            }
        }
        return count == 0 ? 0 : (double) sum / count;
    }
    public void writeToFile(String file, boolean extend_file){
        try {
            FileWriter myWriter = new FileWriter(file, extend_file);
            for(StudentPerformance item: list){
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
                this.list = new ArrayList<StudentPerformance>();
            }
            while (s!= null){
                String[] attrs= s.split("\\|");
                StudentPerformance performance = new StudentPerformance(
                        attrs[0],attrs[1],attrs[2],attrs[3], Integer.parseInt(attrs[4]));
                this.list.add(performance);
                s = br.readLine();
            }  
        }
        catch (IOException e) {
            System.out.println("An error occurred when reading.");
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