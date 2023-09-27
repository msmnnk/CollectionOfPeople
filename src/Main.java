import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize the collection with 4 hard-coded users
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 20));
        people.add(new Person("Bob", 25));
        people.add(new Person("Carol", 30));
        people.add(new Person("Dave", 35));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Show collection details");
            System.out.println("2. Show individual user details");
            System.out.println("3. Add a new user");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                displayCollectionDetails(people);
            } else if (choice == 2) {
                displayIndividualUserDetails(people, scanner);
            } else if (choice == 3) {
                addNewUser(people, scanner);
                displayCollectionDetails(people);
            } else if (choice == 4) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }

    private static void displayCollectionDetails(List<Person> people) {
        // Calculate and display the average age
        double averageAge = people.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0.0);
        System.out.println("The average age is: " + averageAge);

        // Find and display the oldest person
        Person oldestPerson = people.stream()
                .max((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()))
                .orElse(null);
        if (oldestPerson != null) {
            System.out.println("The oldest person is: " + oldestPerson.getName() + ", age " + oldestPerson.getAge());
        }

        // Find and display the youngest person
        Person youngestPerson = people.stream()
                .min((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()))
                .orElse(null);
        if (youngestPerson != null) {
            System.out.println("The youngest person is: " + youngestPerson.getName() + ", age " + youngestPerson.getAge());
        }
    }

    private static void displayIndividualUserDetails(List<Person> people, Scanner scanner) {
        System.out.println("Select a user by number:");
        for (int i = 0; i < people.size(); i++) {
            System.out.println((i + 1) + ". " + people.get(i).getName());
        }
        int userChoice = scanner.nextInt();
        if (userChoice >= 1 && userChoice <= people.size()) {
            Person selectedPerson = people.get(userChoice - 1);
            System.out.println("Name: " + selectedPerson.getName());
            System.out.println("Age: " + selectedPerson.getAge());
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static void addNewUser(List<Person> people, Scanner scanner) {
        System.out.println("Enter the name of the new user:");
        scanner.nextLine();  // Consume the newline character
        String name = scanner.nextLine();
        System.out.println("Enter the age of the new user:");
        int age = scanner.nextInt();
        people.add(new Person(name, age));
        System.out.println("User added successfully.");
    }
}