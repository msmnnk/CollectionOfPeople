import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Group group = new Group();
        group.addPerson(new Person("Alice", 20))
                .addPerson(new Person("Bob", 25))
                .addPerson(new Person("Carol", 30))
                .addPerson(new Person("Dave", 35));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Show collection details");
            System.out.println("2. Show individual user details");
            System.out.println("3. Add a new user");
            System.out.println("4. Delete a user");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                displayCollectionDetails(group);
            } else if (choice == 2) {
                displayIndividualUserDetails(group, scanner);
            } else if (choice == 3) {
                addNewUser(group, scanner);
                displayCollectionDetails(group);
            } else if (choice == 4) {
                deleteSelectedUser(group, scanner);
                displayCollectionDetails(group);
            } else if (choice == 5) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }

    private static void displayCollectionDetails(Group group) {
        System.out.println("Average age: " + group.getAverageAge());
        System.out.println("Oldest person: " + group.getOldest().getName() + ", age " + group.getOldest().getAge());
        System.out.println("Youngest person: " + group.getYoungest().getName() + ", age " + group.getYoungest().getAge());
    }

    private static void displayIndividualUserDetails(Group group, Scanner scanner) {
        System.out.println("Select a user by number to see details:");
        int index = 1;
        for (Person person : group.getPeople()) {
            System.out.println(index + ". " + person.getName());
            index++;
        }
        int selection = scanner.nextInt();
        if (selection >= 1 && selection <= group.getPeople().size()) {
            Person selectedPerson = group.getPeople().get(selection - 1);
            System.out.println("Name: " + selectedPerson.getName());
            System.out.println("Age: " + selectedPerson.getAge());
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private static void addNewUser(Group group, Scanner scanner) {
        System.out.println("Enter the name of the new user:");
        scanner.nextLine();  // Consume the newline character
        String name = scanner.nextLine();

        int age = -1;
        while (true) {
            System.out.println("Enter the age of the new user:");
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                if (age >= 0) {
                    break;
                } else {
                    System.out.println("Age cannot be negative. Please enter a valid age.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid age.");
                scanner.next();  // Consume the invalid token
            }
        }
        group.addPerson(new Person(name, age));
        System.out.println("User added successfully.");
    }

    private static void deleteSelectedUser(Group group, Scanner scanner) {
        System.out.println("Select a user by number to delete:");
        int index = 1;
        for (Person person : group.getPeople()) {
            System.out.println(index + ". " + person.getName());
            index++;
        }
        int selection = scanner.nextInt();
        if (selection >= 1 && selection <= group.getPeople().size()) {
            group.getPeople().remove(selection - 1);
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("Invalid selection.");
        }
    }
}
