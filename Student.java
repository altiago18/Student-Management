public abstract class Student {

    private String name;
    private String studentId;
    private int age;
    private String course;
    private double gwa;

    Student(String name, String studentId, int age, String course, double gwa) {
        this.name = name;
        this.studentId = studentId;
        this.age = age;
        this.course = course;
        this.gwa = gwa;
    }

      public abstract void displayStudentType();

    // Getters
    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    public double getGwa() {
        return gwa;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setGwa(double gwa) {
        this.gwa = gwa;
    }


}


