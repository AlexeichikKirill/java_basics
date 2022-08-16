import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchase_list")
public class PurchaseList {

    @EmbeddedId
    private KeyPurch id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_name", insertable = false, updatable = false)
    private Student studentName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_name", insertable = false, updatable = false)
    private Course courseName;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public KeyPurch getId() {
        return id;
    }

    public void setId(KeyPurch id) {
        this.id = id;
    }
}
