package albumcollection;
import java.util.Scanner;

public class CollectionManager {
    private enum Command {A, D, R, PD, PG, PR, Q}
    private Collection collection;

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



    private void delete(String[] tokenArray){
        String title = tokenArray[1];
        Date dob = new Date(tokenArray[3]);
        Artist artist = new Artist(tokenArray[2], dob);
        Album deleteAlbum = new Album(title,artist);
        if (collection.contains(deleteAlbum)){
            collection.remove(deleteAlbum);
            System.out.println(title + "(" + artist.toString() + ") removed from the collection.");
        } else {
            System.out.println(title + "(" + artist.toString() + ") is not in the collection.");
        }
    }

    private void rate(String[] tokenArray){

    }

    private void displayReleaseSort(){

    }

    private void displayGenreSort(){

    }

    private void displayRatingSort(){

    }
    private Boolean validateCommand(String commandStr){
        for (Command command : Command.values()){
            if (commandStr.equals(command.name())){
                return true;
            }
        }
        return false;
    }

    public CollectionManager() {
        this.collection = new Collection();
    }

    public void run(){
        boolean runCM = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Collection Manager is up running\n");
        while (scanner.hasNextLine() && runCM) {
            String line = scanner.nextLine();
            String[] tokenArray = line.split(",");
            Command command;
            if (!validateCommand(tokenArray[0])){
                System.out.println("invalid command");
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
                    displayReleaseSort();
                    break;
                case PG:
                    displayGenreSort();
                    break;
                case PR:
                    displayRatingSort();
                    break;
                case Q:
                    runCM = false;
                    break;
            }
        }
        System.out.println("Collection Manager Terminated");
    }
}
