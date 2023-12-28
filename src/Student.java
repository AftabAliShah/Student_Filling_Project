public class Student {
    private String id;
    private String name;
    private String age;
    private String rollNumber;
    private String standard;

    public Student() {
    }

    public Student(String id, String name, String age, String rollNumber, String standard) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.rollNumber = rollNumber;
        this.standard = standard;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", rollNumber='" + rollNumber + '\'' +
                ", standard='" + standard + '\'' +
                '}';
    }
}
