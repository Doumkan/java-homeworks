import java.util.*;

public class PhoneBook {
    private String realName = "";
    private String realNumber = "";

    TreeMap<String, String> phonebook = new TreeMap<>();

    /*
    checks that the name and phone number are correct
    if the number is already in the list, overwrite the name
    */
    public void addContact(String phone, String name) {
        if (realName.equals("")) {
            getNameByPhone(phone);
        } else if (realNumber.equals("")) {
            getPhonesByName(name);
        } else {
            phonebook.put(realNumber, realName);
            System.out.println(realName + " - " + realNumber);
            System.out.println("Contact has been saved");
        }
        realName = "";
        realNumber = "";
    }

    public boolean ifContainsNumber(String phone) {
        realNumber = phone;
        if (phonebook.containsKey(phone)) {
            return true;
        } else if (realName.equals("")) {
            System.out.println("This number is not in the phone book.\nEnter the name of the for the number " + phone);
            return false;
        } else {
            return true;
        }
    }

    public boolean ifContainsName(String name) {
        realName = name;
        if (phonebook.containsValue(name)) {
            return true;
        } else if (realNumber.equals("")) {
            System.out.println("This name is not in the phone book.\nEnter the number for the name " + name);
            return false;
        } else {
            return true;
        }
    }

    /*
    format of a contact "Name - Phone"
    if the contact is not found - return an empty string
    */
    public String getNameByPhone(String phone) {
        String nameByPhone = (phonebook.get(phone) + " - " + phone);
        System.out.println(nameByPhone);
        return nameByPhone;
    }

    /*
    format of a contact "Name - Phone"
    if the contact is not found - return an empty TreeSet
    */
    public Set<String> getPhonesByName(String name) {
        String phoneByName;
        Set<String> phonesByName = new TreeSet<>();
        for (Map.Entry<String, String> entry : phonebook.entrySet()) {
            if (entry.getValue().equals(name)) {
                phoneByName = name + " - " + entry.getKey();
                phonesByName.add(phoneByName);
                System.out.println(phoneByName);
            }
        }
        return phonesByName;
    }

    public Set<String> getAllContacts() {
        TreeSet<String> book = new TreeSet<>();
        if (!phonebook.isEmpty()) {
            phonebook.forEach((key, value) -> book.add(value + " - " + key));
        }
        return book;
    }
}
