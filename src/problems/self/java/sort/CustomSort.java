package problems.self.java.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CustomSort {
    public static void main(String[] args) {
        List<Startup> startups = getStartups();
        String sortingChoice = "lfyr";
        switch (sortingChoice.toLowerCase()) {
            case "id" : {
                System.out.println("--Sorted startup list (based on Id)--");
                Collections.sort(startups);
                printSortedList(startups);
                break;
            }
            case "name" : {
                System.out.println("--Sorted startup list (based on Name)--");
                Collections.sort(startups, new StartupNameSorter());
                // (OR)
                // startups.sort(new StartupNameSort());
                printSortedList(startups);
                break;
            }
            case "rc" : {
                System.out.println("--Sorted startup list (based on Registered City)--");
                startups.sort(new StartupRegisteredCitySorter());
                printSortedList(startups);
                break;
            }
            case "dn" : {
                System.out.println("--Sorted startup list (based on Domain)--");
                Collections.sort(startups, new StartupDomainSorter());
                printSortedList(startups);
                break;
            }
            case "lfyr" : {
                System.out.println("--Sorted startup list (in reverse) (based on Last FY Revenue)--");
                //Sorting in reverse based on overridden function
                startups.sort(new StartupLastFYRevenueSorter().reversed());
                printSortedList(startups);
                break;
            }
            case "cr" : {
                System.out.println("--Sorted startup list (based on Credit Rating)--");
                Collections.sort(startups, new StartupCreditRatingSorter());
                printSortedList(startups);
                break;
            }
            default: {
                System.out.println("--Sorted startup list (based on default Id)--");
                Collections.sort(startups);
                printSortedList(startups);
            }
        }
    }

    private static List<Startup> getStartups() {
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
                "Hilltop hotels",
                "Noida",
                "Hospitality",
                124f,
                'B');
        return new ArrayList<>(Arrays.asList(startUp1, startUp2, startUp3, startUp4, startUp5));
    }

    private static void printSortedList(List<Startup> startups) {
        for (Startup startup : startups) {
            //This will print custom string based on overridden toString() method
            //instead of printing class reference
            System.out.println(startup);
        }
    }
}