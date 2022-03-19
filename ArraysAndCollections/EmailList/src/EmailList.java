import java.util.*;

public class EmailList {
    TreeSet<String> emailsList = new TreeSet<>();

    //adds valid email
    public void add(String email) {
        if (email.matches("^\\w+@[a-z0-9]+\\.[a-z]+")) {
            String validEmail = email.substring(email.indexOf(' ') + 1, email.length());
            validEmail = validEmail.toLowerCase();
            emailsList.add(validEmail);
            System.out.println(email);
        } else {
            System.out.println(Main.WRONG_EMAIL_ANSWER);
        }
    }

    public static String getDomain(String email) {
        String domain = email.substring(email.indexOf("@") + 1);
        return domain;
    }

    //returns email-list sorted alphabetically
    public List<String> getSortedEmails() {
        List<String> result = new ArrayList<>();
        result.addAll(emailsList);
        System.out.println(emailsList);
        return result;
    }

    public TreeSet<String> getSortedDomains() {
        Comparator<String> domainComp = new DomainComparator().thenComparing(new EmailComparator());
        TreeSet<String> sortedDomains = new TreeSet(domainComp);
        sortedDomains.addAll(emailsList);
        System.out.println(sortedDomains);
        return sortedDomains;
    }
}

class EmailComparator implements Comparator<String> {
    public int compare(String a, String b) {
        return a.compareTo(b);
    }
}

class DomainComparator implements Comparator<String> {
    public int compare(String a, String b) {
        return EmailList.getDomain(a).compareTo(EmailList.getDomain(b));
    }
}
