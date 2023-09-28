import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;

public class Group {

    private List<Person> people;

    public Group() {
        this.people = new ArrayList<>();
    }

    public Group addPerson(Person person) {
        this.people.add(person);
        return this;
    }

    public float getAverageAge() {
        if (people.isEmpty()) {
            return 0;
        }
        OptionalDouble average = people.stream()
                .mapToInt(Person::getAge)
                .average();
        return (float) average.orElse(0.0);
    }

    public Person getOldest() {
        if (people.isEmpty()) {
            return null;
        }
        return people.stream()
                .max(Comparator.comparingInt(Person::getAge))
                .orElse(null);
    }

    public Person getYoungest() {
        if (people.isEmpty()) {
            return null;
        }
        return people.stream()
                .min(Comparator.comparingInt(Person::getAge))
                .orElse(null);
    }

    public List<Person> getPeople() {
        return people;
    }
}
