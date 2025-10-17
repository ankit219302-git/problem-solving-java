package problems.self.java.sort;

import java.util.Map;
import java.util.TreeMap;

public class CustomSortMap {
    public static void main(String[] args) {
        String sortingChoice = "cr";
        switch (sortingChoice.toLowerCase()) {
            case "id" : {
                Map<Startup, Integer> startups = getStartups(null, false);
                System.out.println("--Sorted startup map (based on Id)--");
                printSortedMap(startups);
                break;
            }
            case "name" : {
                Map<Startup, Integer> startups = getStartups(new StartupNameSorter(), false);
                System.out.println("--Sorted startup map (based on Name)--");
                printSortedMap(startups);
                break;
            }
            case "rc" : {
                Map<Startup, Integer> startups = getStartups(new StartupRegisteredCitySorter(), false);
                System.out.println("--Sorted startup map (based on Registered City)--");
                printSortedMap(startups);
                break;
            }
            case "dn" : {
                Map<Startup, Integer> startups = getStartups(new StartupDomainSorter(), true);
                System.out.println("--Sorted startup map (reversed) (based on Domain)--");
                printSortedMap(startups);
                break;
            }
            case "lfyr" : {
                Map<Startup, Integer> startups = getStartups(new StartupLastFYRevenueSorter(), false);
                System.out.println("--Sorted startup map (based on Last FY Revenue)--");
                printSortedMap(startups);
                break;
            }
            //This will only print 3 values since startup objects with duplicate credit ratings will be removed
            case "cr" : {
                Map<Startup, Integer> startups = getStartups(new StartupCreditRatingSorter(), true);
                System.out.println("--Sorted startup map (reversed) (based on Credit Rating)--");
                printSortedMap(startups);
                break;
            }
            default: {
                Map<Startup, Integer> startups = getStartups(null, false);
                System.out.println("--Sorted startup map (based on default Id)--");
                printSortedMap(startups);
            }
        }
    }

    private static Map<Startup, Integer> getStartups(CustomSorter startupSorter, boolean isReversed) {
        Startup startUp1 = new Startup(1,
                "Ark Inc.",
                "Bangalore",
                "Space Exploration",
                12.4f,
                'B');
        Startup startUp2 = new Startup(2,
                "Sun Systems.",
                "Kolkata",
                "Programming Product",
                9.7f,
                'A');
        Startup startUp3 = new Startup(3,
                "Delta Logistics",
                "Delhi",
                "Logistics",
                15.9f,
                'C');
        Startup startUp4 = new Startup(4,
                "Kors Labs",
                "Mumbai",
                "Pharmaceutical",
                62.3f,
                'C');
        Startup startUp5 = new Startup(5,
                "Hilltop Hotels",
                "Noida",
                "Hospitality",
                124f,
                'B');

        //Double brace initialization of Map
        return new TreeMap<>((isReversed) ? startupSorter.reversed() : startupSorter) {{
            put(startUp1, 101);
            put(startUp2, 102);
            put(startUp3, 103);
            put(startUp4, 104);
            //This object has all the fields equal in value as startup5
            //If hashcode() and equals() method are not overridden in Startup class,
            //then there will be 2 objects with same properties in the map
            //If overridden startup5 will replace startup object considering it a duplicate key
            Startup startUp = new Startup(5,
                    "Hilltop Hotels",
                    "Noida",
                    "Hospitality",
                    124f,
                    'B');
            put(startUp, 106);
            //this will fetch the value 106
            System.out.println("Value in map for 'startUp' key (before startUp5 insertion) = " + get(startUp));
            put(startUp5, 105);
            //this will fetch the value 105 even though we are using the same startup object as key,
            //because with hashcode() and equals() overriding the map can now recognize that
            //startup and startup5 objects are equal and hence only the last inserted key-value pair is kept
            //which is (startUp5, 105) in this case
            System.out.println("Value in map for 'startUp' key (after startUp5 insertion) = " + get(startUp) + "\n");

            //Never true because when 2 objects, even though equal, are created using the 'new' keyword,
            //they have different addresses in memory and '==' compares memory references
            System.out.println("startUp == startUp5 : " + (startUp == startUp5));
            //If equals() is overridden, this will be true
            System.out.println("startUp.equals(startUp5) : " + (startUp.equals(startUp5)));
            //If hashCode() is overridden, this will be true
            System.out.println("startUp.hashCode() == startUp5.hashCode() : " + (startUp.hashCode() == startUp5.hashCode()));
            System.out.println("startUp.hashCode() = " + startUp.hashCode());
            System.out.println("startUp5.hashCode() = " + startUp5.hashCode());
            //If equals() is overridden, this will be true
            System.out.println("startUp.equals(startUp5) : " + startUp.equals(startUp5) + "\n");
        }};
    }

    private static void printSortedMap(Map<Startup, Integer> startups) {
        for (Map.Entry<Startup, Integer> startupEntry : startups.entrySet()) {
            //This will print custom string based on overridden toString() method
            //instead of printing class reference
            System.out.println(startupEntry.getKey() + " :: " + startupEntry.getValue());
        }
    }
}
