package second_section;
import java.util.Date;

public class StudentCounsellingBuilder {
    private String id;
    private Date date_of_admission;
    private Date date_of_birth;
    private String department_choice_id;
    private String department_admission_id;

    public String  getId() {
        return id;
    }

    public StudentCounsellingBuilder hasId(String id) {
        this.id = id;
        return this;
    }

    public Date getDateOfAdmission() {
        return date_of_admission;
    }

    public StudentCounsellingBuilder hasDateOfAdmission(Date date_of_admission) {
        this.date_of_admission = date_of_admission;
        return this;
    }

    public Date getDateOfBirth() {
        return date_of_birth;
    }

    public StudentCounsellingBuilder hasDateOfBirth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
        return this;
    }

    public String getDepartmentChoiceId() {
        return department_choice_id;
    }

    public StudentCounsellingBuilder hasDepartmentChoiceId(String department_choice_id) {
        this.department_choice_id = department_choice_id;
        return this;
    }

    public String getDepartmentAdmissionId() {
        return department_admission_id;
    }

    public StudentCounsellingBuilder hasDepartmentAdmissionId(String department_admission_id) {
        this.department_admission_id = department_admission_id;
        return this;
    }

    public StudentCounselling build(){
        return new StudentCounselling(this);
    }

}
