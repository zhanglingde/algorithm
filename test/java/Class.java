import java.util.List;

/**
 * @author zhangling
 * @date 2022/12/6 10:06 AM
 */
public class Class {
    private String name;

    private List<Student> studentList;

    private List<String> strList;

    public List<String> getStrList() {
        return strList;
    }

    public void setStrList(List<String> strList) {
        this.strList = strList;
    }

    public Class() {
    }

    @Override
    public String toString() {
        return "Class{" +
                "name='" + name + '\'' +
                '}';
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class(String name) {
        this.name = name;
    }
}
