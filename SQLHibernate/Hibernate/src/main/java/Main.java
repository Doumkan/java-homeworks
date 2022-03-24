import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        createNewTable(session);

        transaction.commit();
        sessionFactory.close();
    }

    private static void createNewTable(Session session) {

        session.createSQLQuery("create table LinkedPurchaseList (course_id INT, student_id INT);")
                .executeUpdate();

        Query fetchData = session.createQuery("select courseName, studentName from model.PurchaseList");

        List<Object[]> courseNames = (List<Object[]>) fetchData.list();

        for (Object[] name : courseNames) {
            String courseName = (String) name[0];
            String studentName = (String) name[1];

            String hqlCourse = "select id from Course where name = '" + courseName + "'";
            String hqlStudent = "select id from Student where name = '" + studentName + "'";

            Integer courseId = (Integer) session.createQuery(hqlCourse).uniqueResult();
            Integer studentId = (Integer) session.createQuery(hqlStudent).uniqueResult();

            Query insert = session.createSQLQuery("insert into LinkedPurchaseList(course_id, student_id) values(:a, :b);");

            insert.setParameter("a", courseId);
            insert.setParameter("b", studentId);

            insert.executeUpdate();
        }
    }
}