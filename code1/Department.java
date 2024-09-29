package first_section;

import java.util.Date;

public class Department extends DBObject {
    private String name;
    private Date establishment_date;

    public Department(String id, String name, Date establishment_date) {
        super(id);
        this.name = name;
        this.establishment_date = establishment_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEstablishmentDate() {
        return establishment_date;
    }

    public void setEstablishmentDate(Date establishment_date) {
        this.establishment_date = establishment_date;
    }

    @Override
    public String writeObject() {
        return this.id + "|" + this.name + "|" + this.establishment_date;
    }
}
