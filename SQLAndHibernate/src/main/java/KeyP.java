import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class KeyP implements Serializable {

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;

    public KeyP(String studentName, String courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyP keyP = (KeyP) o;
        return Objects.equals(studentName, keyP.studentName) && Objects.equals(courseName, keyP.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentName, courseName);
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
