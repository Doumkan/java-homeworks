package model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PurchaseList {

    @EmbeddedId
    private PurchaseKey purchaseKey;

    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;

    @Column(name = "course_name", insertable = false, updatable = false)
    private String courseName;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public PurchaseKey getPurchaseKey() {
        return purchaseKey;
    }

    public void setPurchaseKey(PurchaseKey purchaseKey) {
        this.purchaseKey = purchaseKey;
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


    public long getPrice() {
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

}
