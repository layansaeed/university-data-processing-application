package second_section;

import java.util.ArrayList;

public class StudentStats {
    private StudentPerformanceList performances;
    private String student_id;
    private int number_of_papers;
    private int number_of_semesters;
    private int total_marks;
    private int max_mark;
    private int min_mark;
    private double average_mark;

    private void updateStats() throws Exception{
        this.setStudentId();
        this.setNumberOfPapers();
        this.setNumberOfSemesters();
        this.setTotalMarks();
        this.setMaxMark();
        this.setMinMark();
        this.setAverageMark();
    }

    public StudentStats(StudentPerformanceList performances) throws Exception{ 
        this.setPerformances(performances);
    }
    public StudentPerformanceList getPerformances() {
        return performances;
    }

    public void setPerformances(StudentPerformanceList performances) throws Exception{
        this.performances = (StudentPerformanceList) performances.clone();
        this.updateStats();
    }

    public String getStudentId() {
        return student_id;
    }

    private void setStudentId() throws Exception{
        if((int) performances.list.stream().map(StudentPerformance::getId).distinct().count() > 1){
            throw new Exception("The list contains different students");
        }
        this.student_id = this.performances.list.get(0).getId();
    }

    public int getNumberOfPapers () {
        return number_of_papers;
    }

    private void setNumberOfPapers () {
        this.number_of_papers = (int) performances.list.stream().map(StudentPerformance::getPaperName).distinct().count();
    }

    public int getNumberOfSemesters() {
        return number_of_semesters;
    }

    private void setNumberOfSemesters() {
        this.number_of_semesters = (int) performances.list.stream().map(StudentPerformance::getSemesterName).distinct().count();
    }

    public int getTotalMarks() {
        return total_marks;
    }

    private void setTotalMarks() {
        this.total_marks = performances.list.stream().mapToInt(StudentPerformance::getMark).sum();
    }

    public int getMaxMark() {
        return max_mark;
    }

    private void setMaxMark() {
        this.max_mark = performances.list.stream().mapToInt(StudentPerformance::getMark).max().orElse(0);
    }

    public int getMinMark() {
        return min_mark;
    }

    private void setMinMark() {
        this.min_mark = performances.list.stream().mapToInt(StudentPerformance::getMark).min().orElse(0);
    }

    public double getAverageMark() {
        return average_mark;
    }

    private void setAverageMark() {
        this.average_mark = performances.list.stream().mapToInt(StudentPerformance::getMark).average().orElse(0.0);
    }

    @Override
    public String toString() {
        return "StudentStats [" + student_id + "] {" +
                "performances=" + performances +
                ", number_of_papers=" + number_of_papers +
                ", number_of_semesters=" + number_of_semesters +
                ", total_marks=" + total_marks +
                ", max_mark=" + max_mark +
                ", min_mark=" + min_mark +
                ", average_mark=" + average_mark +
                '}';
    }
}
