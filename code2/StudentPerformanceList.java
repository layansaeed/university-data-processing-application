package second_section;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentPerformanceList extends DBObjectList<StudentPerformance> {

    public StudentPerformanceList() {
        super(new ArrayList<StudentPerformance>());
    }

    public StudentPerformanceList(ArrayList<StudentPerformance> list) {
        super(list);
    }

    public int getNumberOfPapers(String studentID) {
        int count = 0;
        for (StudentPerformance obj : super.getList()) {
            if (obj.getId().equals(studentID)) {
                count++;
            }
        }
        return count;
    }

    public int getNumberOfSemesters(String studentID) {
        HashSet<String> semesters = new HashSet<String>();
        for (StudentPerformance obj : super.getList()) {
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
        for (StudentPerformance obj : super.getList()) {
            if (obj.getId().equals(studentID)) {
                sum += obj.getMark();
                count++;
            }
        }
        return count == 0 ? 0 : (double) sum / count;
    }

    @Override
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
                        attrs[0],attrs[1],attrs[2],attrs[3],Integer.parseInt(attrs[4])
                );
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