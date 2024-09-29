package second_section;

public abstract class DBObject implements Cloneable {
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

    @Override
    protected DBObject clone() throws CloneNotSupportedException {
        return (DBObject) super.clone();
    }

    public abstract String writeObject();
    
    public String toString()
    {
    	return this.writeObject();
    }

}
