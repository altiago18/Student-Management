public class RegularStudent extends Student {
    
    public RegularStudent(String name, String studentId, int age, String course, double gwa) {
        super(name, studentId, age, course, gwa);
    }

@Override
public void displayStudentType() {
    System.out.println("Regular Student");
}

}
