package second_section;

public interface FileAsDB {
    public abstract void writeToFile(String file, boolean extend_file);

    public abstract void readFromFile(String file, boolean extend_list);

}
