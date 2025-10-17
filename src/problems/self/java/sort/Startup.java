package problems.self.java.sort;

//The class 'Startup' can be converted to a record class used for storing data :
//To learn about records: https://docs.oracle.com/en/java/javase/17/language/records.html
/*
record Startup(int id, String name, String registeredCity, String domain, float lastFYRevenue,
               char creditRating) implements Comparable<Startup> {
    @Override
    public int compareTo(Startup st) {
        return this.id - st.id;
    }
}
*/

import java.util.Comparator;
import java.util.Objects;

//Comparable is used to set a default natural order sorting within the class
public class Startup implements Comparable<Startup> {
    private final int id;
    private final String name;
    private final String registeredCity;
    private final String domain;
    private final float lastFYRevenue;
    private final char creditRating;

    public Startup(int id,
                   String name,
                   String registeredCity,
                   String domain,
                   float lastFYRevenue,
                   char creditRating) {
        this.id = id;
        this.name = name;
        this.registeredCity = registeredCity;
        this.domain = domain;
        this.lastFYRevenue = lastFYRevenue;
        this.creditRating = creditRating;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegisteredCity() {
        return registeredCity;
    }

    public String getDomain() {
        return domain;
    }

    public float getLastFYRevenue() {
        return lastFYRevenue;
    }

    public char getCreditRating() {
        return creditRating;
    }

    @Override
    public int compareTo(Startup st) {
        return this.id - st.id;
    }

    @Override
    public String toString() {
        return "Startup{ " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registeredCity='" + registeredCity + '\'' +
                ", domain='" + domain + '\'' +
                ", lastFYRevenue=" + lastFYRevenue +
                ", creditRating=" + creditRating +
                " }";
    }

    //If hashcode() and equals() method are not overridden in Startup class,
    //then there can be multiple objects with same properties when storing Startup objects as keys in the map,
    //since the map cannot differentiate distinct keys based on class properties.
    //Overriding equals() is not mandatory even while overriding hasCode()
    //because it is ACCEPTABLE for 2 unequal objects to have the same hash code
    //By default the equals() method checks for memory reference for 2 objects to be equal, if not explicitly defined.
    //So 2 objects with overridden hashcode() with same property values will have the same hash codes
    //but since they have different memory references and equals() is not overridden, they will not be equal
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Startup startup = (Startup) o;
        return id == startup.id && Float.compare(lastFYRevenue, startup.lastFYRevenue) == 0 && creditRating == startup.creditRating && Objects.equals(name, startup.name) && Objects.equals(registeredCity, startup.registeredCity) && Objects.equals(domain, startup.domain);
    }

    //We must override hashCode() in every class that overrides equals()
    //otherwise the class objects will be treated equal on the parameters defined in equals() method
    //but there will be different hash codes for each object even though they are equal,
    //resulting in a violation of the general contract for Object.hashCode().
    //This will prevent class from functioning properly in conjunction with all hash-based collections,
    //including HashMap, HashSet, and Hashtable
    @Override
    public int hashCode() {
        return Objects.hash(id, name, registeredCity, domain, lastFYRevenue, creditRating);
    }
}

//Comparator is used to set a custom order sorting outside the class
abstract class CustomSorter implements Comparator<Startup> {}

//Instead of extending CustomFieldSorter class we could directly implement Comparator<Startup>
//and override compare() and reversed() as below
/*class StartupNameSort implements Comparator<Startup> {
    @Override
    public int compare(Startup o1, Startup o2) {
        return o1.getName().compareTo(o2.getName());
    }

    @Override
    public Comparator<Startup> reversed() {
        return Comparator.super.reversed();
    }
}*/

//This is done and not the above direct Comparator<> implementation approach
//because I wanted to use a common polymorphic class during getStartups() function call in CustomSortMap class
class StartupNameSorter extends CustomSorter {
    @Override
    public int compare(Startup o1, Startup o2) {
        return o1.getName().compareTo(o2.getName());
    }

    @Override
    public Comparator<Startup> reversed() {
        return super.reversed();
    }
}

class StartupRegisteredCitySorter extends CustomSorter {
    @Override
    public int compare(Startup o1, Startup o2) {
        return o1.getRegisteredCity().compareTo(o2.getRegisteredCity());
    }

    @Override
    public Comparator<Startup> reversed() {
        return super.reversed();
    }
}

class StartupDomainSorter extends CustomSorter {
    @Override
    public int compare(Startup o1, Startup o2) {
        return o1.getDomain().compareTo(o2.getDomain());
    }

    @Override
    public Comparator<Startup> reversed() {
        return super.reversed();
    }
}

class StartupLastFYRevenueSorter extends CustomSorter {
    @Override
    public int compare(Startup o1, Startup o2) {
        return Float.compare(o1.getLastFYRevenue(), o2.getLastFYRevenue());
    }

    @Override
    public Comparator<Startup> reversed() {
        return super.reversed();
    }
}

class StartupCreditRatingSorter extends CustomSorter {
    @Override
    public int compare(Startup o1, Startup o2) {
        return Character.compare(o1.getCreditRating(), o2.getCreditRating());
    }

    @Override
    public Comparator<Startup> reversed() {
        return super.reversed();
    }
}
