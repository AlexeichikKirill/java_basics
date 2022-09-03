import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Entity
public class LinkedPurchaseList {

    @EmbeddedId
    private KeyP id;

    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;

    @Column(name = "course_name", insertable = false, updatable = false)
    private String courseName;

    public void setId(KeyP id) {
        this.id = id;
    }

    public KeyP getId() {
        return id;
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
