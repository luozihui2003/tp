package grocery.location;

import exceptions.emptyinput.EmptyInputException;
import exceptions.nosuch.NoSuchObjectException;
import git.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores all the user's saved locations.
 */
public class LocationList {
    // ATTRIBUTES
    private static List<Location> locations = new ArrayList<>();

    // METHODS
    /**
     * Adds a new location.
     *
     * @param name Name of location.
     * @throws EmptyInputException Exception thrown if user does not input a location name.
     */
    public static void addLocation(String name) throws EmptyInputException {
        if (name == null || name.isBlank()) {
            throw new EmptyInputException("location");
        }

        Location location = new Location(name.strip());
        locations.add(location);
        Ui.printLocationAdded(name.strip());
    }

    /**
     * Removes a location from tracking.
     *
     * @param name Name of location.
     * @throws EmptyInputException Thrown if user does not input a location name.
     * @throws NoSuchObjectException Thrown if the location does not exist.
     */
    public static void removeLocation(String name) throws EmptyInputException, NoSuchObjectException {
        if (name == null || name.isBlank()) {
            throw new EmptyInputException("location");
        }

        Location location = findLocation(name);
        location.clearLocation();
        locations.remove(location);
        Ui.printLocationRemoved(name.strip());
    }

    /**
     * Returns the desired location.
     *
     * @param name Name of location
     * @throws NoSuchObjectException Thrown if the location does not exist.
     */
    public static Location findLocation(String name) throws NoSuchObjectException {
        if (locations.isEmpty()) {
            throw new NoSuchObjectException("location (" + name + ")");
        }

        int index = -1;
        for (Location loc : locations) {
            if(loc.getName().equalsIgnoreCase(name)) {
                index = locations.indexOf(loc);
                break;
            }
        }

        if (index != -1) {
            return locations.get(index);
        } else {
            throw new NoSuchObjectException("location (" + name + ")");
        }
    }

    /**
     * Lists all locations being tracked.
     */
    public static void listLocations() {
        Ui.printLocationList(locations);
    }

}
