package albumcollection;

import java.util.Scanner;

/**
 * Collection Manager is the User Interface for a Collection of albums.
 * Users can add, delete, rate an album. Users can also sort albums in the collection
 * by date, rating, and genre.
 *
 * @author Francisco Marquez
 */
public class CollectionManager {
    /**
     * This class holds the valid commands that a collectionManager accepts
     */
    private enum Command {A, D, R, PD, PG, PR, Q}

    private final int COMMAND = 0;
    private final int TITLE = 1;
    private final int ARTIST = 2;
    private final int DOB = 3;
    private final int GENRE = 4;
    private final int STAR = 4;
    private final int RELEASED = 5;

    private final Collection collection;

    /**
     * Initializes an empty collection.
     */
    public CollectionManager() {
        this.collection = new Collection();
    }

    /**
     * This method waits for users input in order to manage a collection.
     * Run parses through user input and stores tokens delimited by commas inside a tokenArray.
     */
    public void run() {
        boolean runCM = true; //Collection manager quits when false.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Collection Manager is up running\n");
        while (scanner.hasNextLine() && runCM) {
            String line = scanner.nextLine();
            if (line.isEmpty()) continue; // skip this loop iteration if line is empty
            String[] tokenArray = line.split(",");
            Command command;
            if (!validateCommand(tokenArray[COMMAND])) {
                System.out.println("Invalid command!");
                continue;
            }
            command = Command.valueOf(tokenArray[COMMAND]);
            switch (command) {
                case A:
                    add(tokenArray);
                    break;
                case D:
                    delete(tokenArray);
                    break;
                case R:
                    rate(tokenArray);
                    break;
                case PD:
                    collection.printByDate();
                    break;
                case PG:
                    collection.printByGenre();
                    break;
                case PR:
                    collection.printByRating();
                    break;
                case Q:
                    runCM = false;
                    break;
            }
        }
        System.out.println("Collection Manager Terminated");
    }

    /**
     * Validates if user input is a command in enum Command.Case-sensitive.
     *
     * @param commandStr An input command by the user.
     * @return True if input is a valid Command, false otherwise.
     */
    private Boolean validateCommand(String commandStr) {
        for (Command command : Command.values()) {
            if (commandStr.equals(command.name())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an album to the collection.
     * Prior to adding it validates an artist's dob and album's release date
     * Also checks if the album to be added already exist in the collection
     *
     * @param tokenArray An array of tokens inputted by user.
     */
    private void add(String[] tokenArray) {
        String title = tokenArray[TITLE];
        Date dobDate = new Date(tokenArray[DOB]);
        if (!dobDate.isValid()) {
            System.out.println("Artist DOB: " + dobDate + " is invalid.");
            return;
        }
        Date releaseDate = new Date(tokenArray[RELEASED]);
        if (!releaseDate.isValid()) {
            System.out.println("Date Released: " + releaseDate + " is invalid.");
            return;
        }
        Artist artist = new Artist(tokenArray[ARTIST], dobDate);
        String genre = tokenArray[GENRE];
        Album newAlbum = new Album(title, artist, genre, releaseDate);
        if (collection.add(newAlbum)) {
            System.out.println(title + "(" + artist + ") added to the collection.");
        } else {
            System.out.println(title + "(" + artist + ") is already in the collection.");
        }
    }

    /**
     * Deletes an album from the collection.
     * Checks if album to be deleted exist in the collection.
     *
     * @param tokenArray An array of tokens inputted by user.
     */
    private void delete(String[] tokenArray) {
        String title = tokenArray[TITLE];
        Date dob = new Date(tokenArray[DOB]);
        Artist artist = new Artist(tokenArray[ARTIST], dob);
        Album deleteAlbum = new Album(title, artist);
        if (collection.remove(deleteAlbum)) {
            System.out.println(title + "(" + artist + ") removed from the collection.");
        } else {
            System.out.println(title + "(" + artist + ") is not in the collection.");
        }
    }

    /**
     * Rates an album from 1 to 5 in the collection.
     * Checks if rating inputted is between 1-5
     *
     * @param tokenArray An array of tokens inputted by user.
     */
    private void rate(String[] tokenArray) {
        final int MAX_STAR = 5;
        final int MIN_STAR = 1;
        int star = Integer.parseInt(tokenArray[STAR]);
        if (star > MAX_STAR || star < MIN_STAR) {
            System.out.println("Invalid rating, rating scale is 1 to 5");
            return;
        }
        String title = tokenArray[TITLE];
        Date dob = new Date(tokenArray[DOB]);
        Artist artist = new Artist(tokenArray[ARTIST], dob);
        Album rateAlbum = new Album(title, artist);
        if (collection.contains(rateAlbum)) {
            collection.rate(rateAlbum, star);
            System.out.println("You rate " + star + " for " + title + ":" + collection.getMatchingAlbum(rateAlbum).getReleased() + "(" + artist.getName() + ")");

        } else {
            System.out.println(title + "(" + artist + ") is not in the collection.");
        }
    }


}
