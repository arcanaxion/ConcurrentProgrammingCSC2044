public class Student {
    // attributes
    private String name;
    private int age;
    private double averageMarks;
    private Teacher teacher;

    // constructors
    public Student(String name, int age, double averageMarks, Teacher teacher) {
        setName(name);
        setAge(age);
        setAverageMarks(averageMarks);
        setTeacher(teacher);
    }

    public Student(Teacher teacher) {
        this("Jane Doe", 24, 76.5427, teacher);
    }

    // setter/getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getAverageMarks() {
        return averageMarks;
    }

    public void setAverageMarks(double averageMarks) {
        this.averageMarks = averageMarks;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
