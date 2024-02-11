package albumcollection;
import java.util.Scanner;

/**
 * Collection Manager is the User Interface for a Collection of albums.
 * Users can add, delete, rate an album. Users can also sort albums in the collection
 * by date, rating, and genre.
 */
public class CollectionManager {

    /**
     * This class holds the valid commands that a collectionManager accepts
     */
    private enum Command {A, D, R, PD, PG, PR, Q}
    private Collection collection;

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
    public void run(){
        boolean runCM = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Collection Manager is up running\n");
        while (scanner.hasNextLine() && runCM) {
            String line = scanner.nextLine();
            String[] tokenArray = line.split(",");
            Command command;
            if (!validateCommand(tokenArray[0])){
                System.out.println("Invalid command!");
                continue;
            }
            command = Command.valueOf(tokenArray[0]);
            switch (command){
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
     * @param commandStr An input command by the user.
     * @return True if input is a valid Command, false otherwise.
     */
    private Boolean validateCommand(String commandStr){
        for (Command command : Command.values()){
            if (commandStr.equals(command.name())){
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an album to the collection.
     * Prior to adding it validates an artist's dob and album's release date
     * Also checks if the album to be added already exist in the collection
     * @param tokenArray An array of tokens inputted by user.
     */
    private void add(String[] tokenArray){
        String title = tokenArray[1];
        Date dobDate = new Date(tokenArray[3]);
        if (!dobDate.isValid()){
            System.out.println("Artist DOB: " + dobDate.toString() + " is invalid.");
            return;
        }
        Date releaseDate = new Date(tokenArray[5]);
        if (!releaseDate.isValid()){
            System.out.println("Date Released: " + releaseDate.toString() + " is invalid.");
            return;
        }
        Artist artist = new Artist(tokenArray[2], dobDate);
        String genre = tokenArray[4];
        Album newAlbum = new Album(title, artist,genre,releaseDate);
        if (collection.add(newAlbum)){
            System.out.println(title + "(" + artist.toString() + ") added to the colletion.");
        }else {
            System.out.println(title + "(" + artist.toString() + ") is already in the collection.");
        }
    }

    /**
     * Deletes an album from the collection.
     * Checks if album to be deleted exist in the collection.
     * @param tokenArray An array of tokens inputted by user.
     */
    private void delete(String[] tokenArray){
        String title = tokenArray[1];
        Date dob = new Date(tokenArray[3]);
        Artist artist = new Artist(tokenArray[2], dob);
        Album deleteAlbum = new Album(title,artist);
        if (collection.remove(deleteAlbum)){
            System.out.println(title + "(" + artist.toString() + ") removed from the collection.");
        } else {
            System.out.println(title + "(" + artist.toString() + ") is not in the collection.");
        }
    }

    /**
     * Rates an album from 1 to 5 in the collection.
     * Checks if rating inputed is between 1-5
     * @param tokenArray An array of tokens inputted by user.
     */
    private void rate(String[] tokenArray){
        int star = Integer.parseInt(tokenArray[4]);
        if (star > 5 || star < 1){
            System.out.println("Invalid rating, rating scale is 1 to 5");
            return;
        }
        String title = tokenArray[1];
        Date dob = new Date(tokenArray[3]);
        Artist artist = new Artist(tokenArray[2], dob);
        Album rateAlbum = new Album(title,artist);
        if (collection.contains(rateAlbum)){
            collection.rate(rateAlbum,star);
            System.out.println("You rate " + star + " for " + title + "(" + artist.toString() + ")");

        } else {
            System.out.println(title + "(" + artist.toString() + ") is not in the collection.");
        }
    }
}
