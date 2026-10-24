public class IrregularStudent extends Student {

    public IrregularStudent(String name, String studentId, int age, String course, double gwa) {
        super(name, studentId, age, course, gwa);
    }

    @Override
    public void displayStudentType() {
        System.out.println("Irregular Student");
    }
}
