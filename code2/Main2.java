package second_section;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main2 {
	 private static DepartmentList departmentList;
	    private static EmployeeList employeeList;
	    private static StudentCounsellingList studentCounsellingList;
	    private static StudentPerformanceList studentPerformanceList;
	    
	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String basePath="C:\\Users\\user\\eclipse-workspace\\FinalAP1\\src\\first_section";
		
		departmentList = new DepartmentList();
		String departmentFile=basePath+"\\Department_Information.txt";
		departmentList.readFromFile(departmentFile,true);
		 System.out.println(departmentList.getList());
        
        employeeList = new EmployeeList();
        String employeeFile=basePath+"\\Employee_Information.txt";
        employeeList.readFromFile(employeeFile,true);
        System.out.println(employeeList.getList());
        
        studentCounsellingList = new StudentCounsellingList();
        String counsellingFile=basePath+"\\Student_Counceling_Information.txt";
        studentCounsellingList.readFromFile(counsellingFile,true);
        System.out.println(studentCounsellingList.getList());
        		
      studentPerformanceList = new StudentPerformanceList();
      String performanceFile=basePath+"\\Student_Performance_Data.txt";
      studentPerformanceList.readFromFile(performanceFile,true);
		System.out.println(studentPerformanceList.getList()); 
        
        String deptId = "IDEPT4670";
        String empId = "IU449901";
        String stuId = "SID20131143";
        Date dobStart = dateFormat.parse("1996-02-05T00:00:00Z");
        Date dobEnd = dateFormat.parse("1996-02-05T00:00:00Z");
        Date admissionStart =dateFormat.parse("1996-02-05T00:00:00Z");
        Date admissionEnd = dateFormat.parse("1996-02-05T00:00:00Z");
        
        // 1. Retrieve the information based on given IDs
        retrieveInfoById(deptId, empId, stuId);

        // 2. Retrieve all students' information for a given department, date of birth within a range, and date of admission within a range
        retrieveStudentsByDOBAndAdmissionDate(deptId, dobStart, dobEnd, admissionStart, admissionEnd);

        // 3. Perform statistical operations on student performance info for specific students
        performStudentPerformanceStats(stuId);
        
     // 4. Retrieve Student and Employee Information for a Specific Department
        retrieveStudentsAndEmployeesInDepartment(deptId);	
        
        // 5. Perform statistical operations on the department
	    performDepartmentStats(deptId);
	    
        
}
	
	 public static void retrieveInfoById(String deptId, String empId , String stuId) {
	        Department department = (Department) departmentList.getInstance(deptId);
	        Employee employee = (Employee) employeeList.getInstance(empId );
	        StudentCounselling counselling = (StudentCounselling) studentCounsellingList.getInstance(stuId);
	        StudentPerformance performance = (StudentPerformance) studentPerformanceList.getInstance(stuId);

	        System.out.println("Department Info: " + department);
	        System.out.println("Employee Info: " + employee);
	        System.out.println("Student Counselling Info: " + counselling);
	        System.out.println("Student Performance Info: " + performance);
	    }

	    public static void retrieveStudentsByDOBAndAdmissionDate(String deptId, Date dobStart, Date dobEnd, Date admissionStart, Date admissionEnd) {
	        ArrayList<StudentCounselling> studentsByDOB = studentCounsellingList.getListByBirthDateRange(dobStart, dobEnd);
	        ArrayList<StudentCounselling> studentsByAdmission = studentCounsellingList.getListByAdmissionDateRange(admissionStart, admissionEnd);

	        System.out.println("Students in Department " + deptId + " with DOB within range:");
	        for (StudentCounselling student : studentsByDOB) {
	            if (student.getDepartmentAdmissionId().equals(deptId)) {
	                System.out.println(student);
	            }
	        }

	        System.out.println("Students in Department " + deptId + " with Admission Date within range:");
	        for (StudentCounselling student : studentsByAdmission) {
	            if (student.getDepartmentAdmissionId().equals(deptId)) {
	                System.out.println(student);
	            }
	        }
	    }

	    public static void performStudentPerformanceStats(String studentId) {
	        ArrayList<StudentPerformance> performances = studentPerformanceList.getListById(studentId);
	        
	        if (performances.isEmpty()) {
	            System.out.println("No performance data found for student ID: " + studentId);
	            return;
	        }

	        StudentPerformanceList specificStudentPerformanceList = new StudentPerformanceList(performances);
	        StudentStats studentStats = null;

	        try {
	            studentStats = new StudentStats(specificStudentPerformanceList);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        if (studentStats != null) {
	            System.out.println("Student Performance Stats for " + studentId + ":");
	            System.out.println("Number of papers: " + studentStats.getNumberOfPapers());
	            System.out.println("Number of semesters: " + studentStats.getNumberOfSemesters());
	            System.out.println("Average marks: " + studentStats.getAverageMark());
	            System.out.println("Sum of marks: " + studentStats.getTotalMarks());
	            System.out.println("Max marks: " + studentStats.getMaxMark());
	            System.out.println("Min marks: " + studentStats.getMinMark());
	        }
	    }

	    public static void performDepartmentStats(String deptId) {
	        // Calculate the number of employees in the department
	        int numberOfEmployees = employeeList.getListByDepartmentId(deptId).size();

	        // Calculate the number of students in the department
	        int numberOfStudents = studentCounsellingList.getListByAdmissionDepartmentID(deptId).size();

	        // Print the statistics
	        System.out.println("Department Stats for " + deptId + ":");
	        System.out.println("Number of Employees: " + numberOfEmployees);
	        System.out.println("Number of Students: " + numberOfStudents);
	    }

	    public static void retrieveStudentsAndEmployeesInDepartment(String deptId) {
	        // Retrieve and print student information for the specified department
	        ArrayList<StudentCounselling> students = studentCounsellingList.getListByAdmissionDepartmentID(deptId);
	        System.out.println("Students in Department " + deptId + ":");
	        for (StudentCounselling student : students) {
	            System.out.println(student);
	        }

	        // Retrieve and print employee information for the specified department
	        ArrayList<Employee> employees = employeeList.getListByDepartmentId(deptId);
	        System.out.println("Employees in Department " + deptId + ":");
	        for (Employee employee : employees) {
	            System.out.println(employee);
	        }
	    }
}
