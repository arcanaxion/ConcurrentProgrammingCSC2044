public class Teacher {
    // attributes
    private String name;
    private String email;

    // constructors
    public Teacher(String name, String email) {
        setName(name);
        setEmail(email);
    }

    public Teacher(){
        this("John Doe", "johndoe@email.com");
    }

    // setter/getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
