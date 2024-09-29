package first_section;

public abstract class DBObject {
    protected String id;

    public DBObject(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract String writeObject();
    
    public String toString()
    {
    	return this.writeObject();
    }

}
