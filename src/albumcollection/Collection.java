package albumcollection;

/**
 * The Collection class represents a list of albums in an array
 * The class updates and changes the list of albums as needed
 *
 * @author Ryan Colling
 */
public class Collection {
    private static final int INITIAL_CAPACITY = 4;

    private Album[] albums;
    private int size;

    /**
     * Searches for an album in the list
     *
     * @param album the album being searched for
     * @return the index of the album in the list if found, returns -1 otherwise
     */
    private int find(Album album) {

        final int NOT_FOUND = -1;

        int index = NOT_FOUND;

        for (int i = 0; i < size; i++) {
            if (albums[i] != null && albums[i].equals(album)) {
                index = i;
                return index;
            }
        }

        return NOT_FOUND;
    }

    /**
     * Increases capacity of the list by 4 as needed
     */
    private void grow() {

        int growSize = 0;

        if (albums == null) {
            growSize = 4;
            Album[] growAlbums = new Album[growSize];
            albums = growAlbums;
            return;
        } else {
            growSize = albums.length + 4;
        }

        Album[] growAlbums = new Album[growSize];

        for (int i = 0; i < albums.length; i++) {
            growAlbums[i] = albums[i];
        }

        albums = growAlbums;

    }

    /**
     * Checks to see if an album is contained in the list
     *
     * @param album the album being searched for
     * @return true if the album is in the list, false if otherwise
     */
    public boolean contains(Album album) {

        int search = find(album);

        if (search < 0) {
            return false;
        }

        return true;

    }

    /**
     * Adds an album to the end of the list
     *
     * @param album the album being added to the list
     * @return true if successfully added, false if already in album
     */
    public boolean add(Album album) {

        int search = find(album);
        if (search != -1) {
            return false;
        }

        if (albums == null || size == albums.length) {
            grow();
        }

        if (albums[albums.length - 1] != null) {
            grow();
        }

        int i = 0;
        while (albums[i] != null) {
            i++;
        }

        albums[i] = album;
        size++;
        return true;
    }

    /**
     * Removes requested album from the list
     *
     * @param album the album to be removed
     * @return true if successfully removed, false if it doesn't exist
     */
    public boolean remove(Album album) {

        int albIndex = find(album);

        if (albIndex == -1) {
            return false;
        }

        for (int i = albIndex; i < albums.length - 1; i++) { //removes album by shifting all preceding albums
            albums[i] = albums[i + 1];
        }

        albums[albums.length - 1] = null;

        return true;
    }

    /**
     * Adds a rating to an album in the collection
     *
     * @param album  the album in the collection being rated
     * @param rating the rating to be assigned to the album
     */
    public void rate(Album album, int rating) {

        int index = find(album);

        if (index < 0) {
            System.out.println("No rate");
            return;
        }

        albums[index].rate(rating);
    }

    public Album getMatchingAlbum(Album newAlbum) {
        return albums[0]; //temp
    }

    /**
     * Prints out the collection in proper format
     */
    public void printCollection() {

        for (int i = 0; i < size; i++) {
            if (albums[i] != null) {
                System.out.println(albums[i].toString());
            }
        }
    }

    /**
     * Sorts album collection by dates released, then title
     * Prints out sorted collection
     */
    public void printByDate() {

        Album holdAlbum = null;

        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size - 1; i++) {

                if (albums[i] != null && albums[i + 1] != null) {

                    Date date1 = albums[i].getReleased();
                    Date date2 = albums[i + 1].getReleased();

                    if (date1.compareTo(date2) > 0) {

                        holdAlbum = albums[i];
                        albums[i] = albums[i + 1];
                        albums[i + 1] = holdAlbum;

                    } else if (date1.compareTo(date2) == 0) {

                        String firstStr = albums[i].getTitle();
                        String secStr = albums[i + 1].getTitle();

                        if (firstStr.compareTo(secStr) > 0) { // 1 means first str larger than sec str
                            holdAlbum = albums[i];
                            albums[i] = albums[i + 1];
                            albums[i + 1] = holdAlbum;

                        }
                    }
                }
            }
        }

        System.out.println("* Collection sorted by Released Date/Title *");
        printCollection();
        System.out.println("* end of list *");

    }

    /**
     * Sorts album collection by genre, then artist name and DOB
     * Prints out sorted collection
     */
    public void printByGenre() { //

        Album holdAlbum = null;

        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size - 1; i++) {

                if (albums[i] != null && albums[i + 1] != null) {

                    String firstG = albums[i].genreToString();
                    String secG = albums[i + 1].genreToString();

                    if (firstG.compareTo(secG) > 0) {
                        holdAlbum = albums[i];
                        albums[i] = albums[i + 1];
                        albums[i + 1] = holdAlbum;

                    } else if (firstG.compareTo(secG) == 0) {
                        Artist firstArt = albums[i].getArtist();
                        Artist secArt = albums[i + 1].getArtist();

                        if (firstArt.compareTo(secArt) > 0) { //compares name and DOB
                            holdAlbum = albums[i];
                            albums[i] = albums[i + 1];
                            albums[i + 1] = holdAlbum;
                        }
                    }
                }
            }
        }

        System.out.println("* Collection sorted by Genre/Artist *");
        printCollection();
        System.out.println("* end of list *");
    }

    /**
     * Sorts album collection by average rating, then title
     * Prints out sorted collection
     */
    public void printByRating() {

        Album holdAlbum = null;

        for (int j = 0; j < size; j++) { //sorting algorithm

            for (int i = 0; i < size - 1; i++) {

                if (albums[i] != null && albums[i + 1] != null) {

                    Double rate1 = albums[i].avgRatings();
                    Double rate2 = albums[i+1].avgRatings();

                    if (rate1 < rate2) {

                        holdAlbum = albums[i];
                        albums[i] = albums[i + 1];
                        albums[i + 1] = holdAlbum;

                    } else if (rate1.equals(rate2)) {
                        String firstStr = albums[i].getTitle();
                        String secStr = albums[i + 1].getTitle();

                        if (firstStr.compareTo(secStr) > 0) { // 1 means first str larger than sec str
                            holdAlbum = albums[i];
                            albums[i] = albums[i + 1];
                            albums[i + 1] = holdAlbum;

                        }
                    }
                }
            }
        }

        System.out.println("* Collection sorted by Rating/Title *");
        printCollection();
        System.out.println("* end of list *");

    }

}
