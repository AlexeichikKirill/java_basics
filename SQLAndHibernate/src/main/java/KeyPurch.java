import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class KeyPurch implements Serializable {

    @Column(name = "student_name")
    private Student studentName;

    @Column(name = "course_name")
    private Course courseName;

    public KeyPurch(Student studentName, Course courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
    }

    public Student getStudentName() {
        return studentName;
    }

    public void setStudentName(Student studentName) {
        this.studentName = studentName;
    }

    public Course getCourseName() {
        return courseName;
    }

    public void setCourseName(Course courseName) {
        this.courseName = courseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyPurch keyPurch = (KeyPurch) o;
        return Objects.equals(studentName, keyPurch.studentName) && Objects.equals(courseName, keyPurch.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentName, courseName);
    }
}
