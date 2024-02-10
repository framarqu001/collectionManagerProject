package albumcollection;
import java.util.Scanner;

public class CollectionManager {
    private enum Command {A, D, R, PD, PG, PR, Q}
    private Collection collection;

    private void add(String[] tokenArray){
        String title = tokenArray[1];
        Date dobDate = new Date(tokenArray[3]);
        Date releaseDate = new Date(tokenArray[5]);
        Artist newArtist = new Artist(tokenArray[2], dobDate);
        Genre genre = setGenre(tokenArray[4]);
        Album newAlbum = new Album(title, newArtist,genre,releaseDate);
        collection.add(newAlbum);
    }

    private Genre setGenre(String genre){
        for (Genre element: Genre.values()){
            if (genre.toLowerCase().equals(element.name().toLowerCase())){
                return element;
            }
        }
        return Genre.UNKNOWN;
    }

    private void delete(String[] tokenArray){

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
        Boolean runCM = true;
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
