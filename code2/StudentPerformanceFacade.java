package second_section;

import java.util.ArrayList;

public class StudentPerformanceFacade {
	
	
    public void getStats(StudentPerformanceList students_performances, String student_id) { 
        ArrayList<StudentPerformance> performances_list = students_performances.getListById(student_id);
        StudentPerformanceList performances = new StudentPerformanceList(performances_list);
        try {
            StudentStats stats = new StudentStats(performances); 
            System.out.println(stats);

        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}




