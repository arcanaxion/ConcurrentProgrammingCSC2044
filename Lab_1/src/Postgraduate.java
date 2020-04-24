public class Postgraduate extends Student {
    // attributes
    private String supervisor;

    // constructors
    public Postgraduate(String name, int age, double averageMarks, Teacher teacher, String supervisor) {
        super(name, age, averageMarks, teacher);
        setSupervisor(supervisor);
    }

    public Postgraduate(Teacher teacher, String supervisor) {
        this("Peter Jackson", 65, 76.3453, teacher, supervisor);
    }

    // setter/getter
    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
}
