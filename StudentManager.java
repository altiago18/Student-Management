public class StudentManager {

    private Student[] students;
    private int count;

    public StudentManager() {
        students = new Student[100];
        count = 0;
    }

    // ADD STUDENT
    public boolean addStudent(Student student) {

        // Check if student ID already exists
        if (searchStudent(student.getStudentId()) != null) {
            return false;
        }

        if (count < students.length) {
            students[count] = student;
            count++;
            return true;
        }

        return false;
    }

    // VIEW STUDENTS
    public Student[] getStudents() {
        return students;
    }

    public int getCount() {
        return count;
    }

    // SEARCH STUDENT
    public Student searchStudent(String studentId) {

        for (int i = 0; i < count; i++) {

            if (students[i].getStudentId().equals(studentId)) {
                return students[i];
            }
        }

        return null;
    }

    // UPDATE STUDENT
    public boolean updateStudent(
            String studentId,
            String name,
            int age,
            String course,
            double gwa) {

        for (int i = 0; i < count; i++) {

            if (students[i].getStudentId().equals(studentId)) {

                students[i].setName(name);
                students[i].setAge(age);
                students[i].setCourse(course);
                students[i].setGwa(gwa);

                return true;
            }
        }

        return false;
    }

    // DELETE STUDENT
    public boolean deleteStudent(String studentId) {

        for (int i = 0; i < count; i++) {

            if (students[i].getStudentId().equals(studentId)) {

                // Shift students to the left
                for (int j = i; j < count - 1; j++) {
                    students[j] = students[j + 1];
                }

                students[count - 1] = null;
                count--;

                return true;
            }
        }

        return false;
    }
}