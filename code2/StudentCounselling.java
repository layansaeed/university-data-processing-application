package second_section;
import java.util.Date;

public class StudentCounselling extends DBObject {
    private Date date_of_admission;
    private Date date_of_birth;
    private String department_choice_id;
    private String department_admission_id;

    public StudentCounselling(String student_id, Date date_of_admission, Date date_of_birth,
                              String department_choice_id, String department_admission_id) {
        super(student_id);
        this.date_of_admission = date_of_admission;
        this.date_of_birth = date_of_birth;
        this.department_choice_id = department_choice_id;
        this.department_admission_id = department_admission_id;
    }

    public StudentCounselling(StudentCounsellingBuilder builder) {
        super(builder.getId());
        this.date_of_admission = builder.getDateOfAdmission();
        this.date_of_birth = builder.getDateOfBirth();
        this.department_choice_id = builder.getDepartmentChoiceId();
        this.department_admission_id = builder.getDepartmentAdmissionId();
    }

    public Date getDateOfAdmission() {
        return date_of_admission;
    }

    public void setDateOfAdmission(Date date_of_admission) {
        this.date_of_admission = date_of_admission;
    }

    public Date getDateOfBirth() {
        return date_of_birth;
    }

    public void setDateOfBirth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getDepartmentChoiceId() {
        return department_choice_id;
    }

    public void setDepartmentChoiceId(String department_choice_id) {
        this.department_choice_id = department_choice_id;
    }

    public String getDepartmentAdmissionId() {
        return department_admission_id;
    }

    public void setDepartmentAdmissionId(String department_admission_id) {
        this.department_admission_id = department_admission_id;
    }

    @Override
    public String writeObject() {
        return this.id + "|" + this.date_of_admission + "|" + this.date_of_birth + "|" + this.department_choice_id +
                "|" + department_admission_id;
    }
}
