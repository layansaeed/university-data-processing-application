package first_section;

public class StudentPerformance extends DBObject {
    private String semester_name;
    private String paper_id;
    private String paper_name;
    private int mark;

    public StudentPerformance(String student_id, String semester_name, String paper_id, String paper_name, int mark) {
        super(student_id);
        this.semester_name = semester_name;
        this.paper_id = paper_id;
        this.paper_name = paper_name;
        this.mark = mark;
    }

    public String getSemesterName() {
        return semester_name;
    }

    public void setSemesterName(String semester_name) {
        this.semester_name = semester_name;
    }

    public String getPaperId() {
        return paper_id;
    }

    public void setPaperId(String paper_id) {
        this.paper_id = paper_id;
    }

    public String getPaperName() {
        return paper_name;
    }

    public void setPaperName(String paper_name) {
        this.paper_name = paper_name;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String writeObject() {
        return this.id + "|" + this.semester_name + "|" + this.paper_id + "|" + this.paper_name + "|" + this.mark;
    }
}
