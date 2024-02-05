package albumcollection;
import java.util.Scanner;

public class CollectionManager {
    enum Command {A, D, R, PD, PG, PR, Q}
    public static final int VALID_INPUTS = 6;
    Collection collection;

    public CollectionManager() {
        this.collection = new Collection();
    }

    public static void run(){
        // Proccess command line arguments and operations to collections
        Scanner scanner = new Scanner(System.in);
        System.out.println("Collection Manager is now Running!\n");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokenArray = line.split(",");
            if (tokenArray.length != VALID_INPUTS){
                System.out.println("Invalid command");
                continue;
            }
            String commandStr = tokenArray[0];
            Command command;
            try {
                // Attempt to convert the command string to the enum value
                command = Command.valueOf(commandStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid command: " + commandStr);
                continue;
            }
            String albumStr = tokenArray[1];
            String artistStr = tokenArray[2];
            String dob = tokenArray[3];
            String genreStr = tokenArray[4];
            String dateStr = tokenArray[5];


        }

    }
}
