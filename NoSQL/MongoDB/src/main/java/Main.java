import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class Main {
    public static void main(String[] args) throws IOException {
        MongoClient client = MongoClients.create("mongodb://localhost:27017");

        MongoDatabase database = client.getDatabase("students_db");
        MongoCollection<Document> students = database.getCollection("students");

        parseCsvFile(students);

        //TODO: get total number of students in database
        getAllNumberOfStudents(students);

        //TODO: get number of students older than 40 y.o.
        getNumberOfOldStudents(students);

        //TODO: get the name of the youngest student
        getNameYoungestStudent(students);

        //TODO: get the courses list of the oldest student
        getCoursesOldestStudent(students);
    }

    //parses csv document and adds it to the mongo DB
    private static void parseCsvFile(MongoCollection<Document> students) throws IOException {

        File csvData = new File("src/main/resources/mongo.csv");
        CSVParser parser = CSVParser.parse(csvData, StandardCharsets.UTF_8, CSVFormat.DEFAULT);

        parser.getRecords().forEach(csvRecord -> {
            Document student = new Document("name", csvRecord.get(0))
                    .append("age", Integer.parseInt(csvRecord.get(1)))
                    .append("courses", getCoursesList(csvRecord.get(2)));
            students.insertOne(student);
        });
    }

    private static List<String> getCoursesList(String names) {
        List<String> courses = new ArrayList<>();
        String[] coursesNames = names.split(",");
        Collections.addAll(courses, coursesNames);

        return courses;
    }

    private static void getAllNumberOfStudents(MongoCollection<Document> students) {
        System.out.println(students.countDocuments());
    }

    private static void getNumberOfOldStudents(MongoCollection<Document> students) {
        Bson ageFilter = gt("age", 40);
        List<Document> oldStudents = new ArrayList<>();
        students.find(ageFilter).forEach(oldStudents::add);
        System.out.println(oldStudents.size());
    }

    private static void getNameYoungestStudent(MongoCollection<Document> students) {
        Document youngest = students.find().sort(ascending("age"))
                .projection(Projections.fields(Projections.include("name"), Projections.excludeId()))
                .first();

        System.out.println(youngest.values());
    }

    private static void getCoursesOldestStudent(MongoCollection<Document> students) {
        Collection<Object> coursesOldestStudent = students.find().sort(descending("age"))
                .projection(Projections.fields(Projections.include("courses"), Projections.excludeId()))
                .first().values();

        System.out.println(coursesOldestStudent);
    }

}
