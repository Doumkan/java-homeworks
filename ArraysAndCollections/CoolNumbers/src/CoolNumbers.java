import java.util.*;

public class CoolNumbers {

    public static List<String> generateCoolNumbers() {

        String letters = "АВЕКМНОРСТУХ";
        List<String> carNumbers = new ArrayList<>();

        for (int j = 0; j < 2_000_000; j++) {

            StringBuilder s = new StringBuilder();
            Random rand = new Random();

            //generate the first letter
            char first = letters.charAt(rand.nextInt(letters.length()));
            s.append(first);

            //generate one number and add it three times
            char digit1 = (char) (Math.random() * 10 + '0');
            s.append(String.valueOf(digit1).repeat(3));

            //generate second pair of letters
            for (int i = 0; i < 2; i++) {
                char second = letters.charAt(rand.nextInt(letters.length()));
                s.append(second);
            }
            //generate second pair of numbers
            for (int i = 0; i < 2; i++) {
                char digit2 = (char) (Math.random() * 10 + '0');
                s.append(digit2);
            }
            String number = s.toString();
            carNumbers.add(number);
        }
        return carNumbers;
    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {

        long startTime = System.nanoTime();

        for (int i = 0; i < list.size(); i++) {
            if (list.contains(number)) {
                long endTime = System.nanoTime() - startTime;
                System.out.println("Brute force search: number found, search took " + endTime + " ns");
                return true;
            }
        }
        long endTime = System.nanoTime() - startTime;
        System.out.println("Brute force search: number not found, search took " + endTime + " ns");
        return false;
    }

    public static boolean binarySearchInList(List<String> sortedList, String number) {
        long startTime = System.nanoTime();
        long endTime = System.nanoTime() - startTime;
        int index = Collections.binarySearch(sortedList, number);
        if (index >= 0) {
            System.out.println("Binary search: number found, search took " + endTime + " ns");
        } else {
            System.out.println("Binary search: number not found, search took " + endTime + " ns");
        }
        return index != -1;
    }

    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
        long startTime = System.nanoTime();
        long endTime = System.nanoTime() - startTime;
        if (hashSet.contains(number)) {
            System.out.println("Search in HashSet: number found, search took " + endTime + " ns");
        } else {
            System.out.println("Search in HashSet: number not found, search took " + endTime + " ns");
        }
        return hashSet.contains(number);
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
        long startTime = System.nanoTime();
        long endTime = System.nanoTime() - startTime;
        if (treeSet.contains(number)) {
            System.out.println("Search in TreeSet: number found, search took " + endTime + " ns");
        } else {
            System.out.println("Search in TreeSet: number not found, search took " + endTime + " ns");
        }
        return treeSet.contains(number);
    }
}
