package first_section;

import java.util.Date;

public class Employee extends DBObject {
    private Date birth_date;
    private Date join_date;
    private String department_id;

    public Employee(String id, Date birth_date, Date join_date, String department_id) {
        super(id);
        this.birth_date = birth_date;
        this.join_date = join_date;
        this.department_id = department_id;
    }

    public Date getBirthDate() {
        return birth_date;
    }

    public void setBirthDate(Date birth_date) {
        this.birth_date = birth_date;
    }

    public Date getJoinDate() {
        return join_date;
    }

    public void setJoinDate(Date join_date) {
        this.join_date = join_date;
    }

    public String getDepartmentId() {
        return department_id;
    }

    public void setDepartmentId(String department_id) {
        this.department_id = department_id;
    }

    @Override
    public String writeObject() {
        return this.id + "|" + this.birth_date + "|" + this.join_date + "|" + this.department_id;
    }
}
