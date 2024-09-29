package second_section;

public class StudentPerformanceBuilder {
    private String id;
    private String semester_name;
    private String paper_id;
    private String paper_name;
    private int mark;

    public String  getId() {
        return id;
    }

    public StudentPerformanceBuilder hasId(String id) {
        this.id = id;
        return this; //this --> object of builder
    }

    public String getSemesterName() {
        return semester_name;
    }

    public StudentPerformanceBuilder hasSemesterName(String semester_name) {
        this.semester_name = semester_name;
        return this;
    }

    public String getPaperId() {
        return paper_id;
    }

    public StudentPerformanceBuilder hasPaperId(String paper_id) {
        this.paper_id = paper_id;
        return this;
    }

    public String getPaperName() {
        return paper_name;
    }

    public StudentPerformanceBuilder hasPaperName(String paper_name) {
        this.paper_name = paper_name;
        return this;
    }

    public int getMark() {
        return mark;
    }

    public StudentPerformanceBuilder hasMark(int mark) {
        this.mark = mark;
        return this;
    }

    public StudentPerformance build(){
        return new StudentPerformance(this);
    }
}
