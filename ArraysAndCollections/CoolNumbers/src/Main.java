/*
This is a console application to understand the difference between different collections
different types of search in them.

The task consists in writing a program generating 2 million car numbers with a certain pattern and add
the into different collections. After that I wrote a method for each collection to find out which
is the fastest and the slowest.
 */

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> numbers = CoolNumbers.generateCoolNumbers();
        CoolNumbers.bruteForceSearchInList(numbers, pickRandomNumber(numbers));

        List<String> sortedList = numbers.stream().sorted().collect(Collectors.toList());
        CoolNumbers.binarySearchInList(sortedList, pickRandomNumber(numbers));

        HashSet<String> hashSet = new HashSet<>(numbers);
        CoolNumbers.searchInHashSet(hashSet, pickRandomNumber(numbers));

        TreeSet<String> treeSet = new TreeSet<>(numbers);
        CoolNumbers.searchInTreeSet(treeSet, pickRandomNumber(numbers));
    }

    private static String pickRandomNumber(List<String> numbers) {
        Random random = new Random();
        return numbers.get(random.nextInt(numbers.size()));
    }
}
