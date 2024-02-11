package albumcollection;

/**
 * The Collection class represents a list of albums in an array
 * The class updates and changes the list of albums as needed
 * @author Ryan Colling
 */
public class Collection {
    private static final int INITIAL_CAPACITY = 4;

    private Album[] albums;
    private int size;

    /**
     * Searches for an album in the list
     * @param album the album being searched for
     * @return the index of the album in the list if found, returns -1 otherwise
     */
    private int find(Album album) {

        final int NOT_FOUND = -1;

        int index = NOT_FOUND;

        for (int i = 0; i < size; i++) {
            if (albums[i].equals(album)) {
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

    int growSize = size + 4;
    Album[] growAlbums = new Album[growSize];

    for (int i = 0; i < size; i++) {
        growAlbums[i] = albums[i];
    }

    albums = growAlbums;
    size = growSize;

    }

    /**
     * Checks to see if an album is contained in the list
     * @param album the album being searched for
     * @return true if the album is in the list, false if otherwise
     */
    public boolean contains(Album album) {

        int search = find(album);

        if (search == -1) {
            return false;
        }

        return true;

    }

    /**
     * Adds an album to the end of the list
     * @param album the album being added to the list
     * @return true if successfully added, false if already in album
     */
    public boolean add(Album album) {

        int search = find(album);
        if (search != -1) {
            return false;
        }

        if (albums[size-1] != null) {
            grow();
        }

        int i = 0;
        while (albums[i] != null) {
            i++;
        }

        albums[i] = album;
        return true;
    }

    /**
     * Removes requested album from the list
     * @param album the album to be removed
     * @return true if successfully removed, false if it doesn't exist
     */
    public boolean remove(Album album) {

        int albIndex = find(album);

        if (albIndex == -1) {
            return false;
        }

        for (int i = albIndex; i < size-1; i++) { //removes album by shifting all preceding albums
            albums[i] = albums[i+1];
        }

        albums[size-1] = null;

        return true; //place holder
    }

    /**
     * Adds a rating to an album
     * @param album the album being rated
     * @param rating the rating to be assigned to the album
     */
    public void rate(Album album, int rating) {

        album.rate(rating);

    }

    public Album getMatchingAlbum(Album newAlbum){
        return albums[0]; //temp
    }

    /**
     * Sorts album collection by dates released, then title
     * Prints out sorted collection
     */
    public void printByDate() {

        Album holdAlbum = null;

        for (int  j = 0; j < size; j++) {
            for (int i = 0; i < size-1; i++) {
                Date date1 = albums[i].getDate();
                Date date2 = albums[i+1].getDate();

                if(date1.compareTo(date2) > 0) {

                    holdAlbum = albums[i];
                    albums[i] = albums[i+1];
                    albums[i+1] = holdAlbum;

                } else if(date1.compareTo(date2) == 0) {

                    String firstStr = albums[i].getTitle();
                    String secStr = albums[i+1].getTitle();

                    if(firstStr.compareTo(secStr) > 0) { // 1 means first str larger than sec str
                        holdAlbum = albums[i];
                        albums[i] = albums[i+1];
                        albums[i+1] = holdAlbum;

                    }

                }


            }



        }

        for(int i = 0; i < size; i++) { //need to properly format
            System.out.println("[" + albums[i].getTitle() + "] Released " + albums[i].getDateStr() + " Rating " + albums[i].avgRatings());
        }

    }

    /**
     * Sorts album collection by genre, then artist name and DOB
     * Prints out sorted collection
     */
    public void printByGenre() { //

        Album holdAlbum = null;

        for(int j = 0; j < size; j++) {
            for (int i = 0; i < size-1; i++) {
                Genre firstG = albums[i].getGenre();
                Genre secG = albums[i+1].getGenre();


                /* Work in progress need to fix
                if (firstStr.compareTo(secStr) > 0) {
                    holdAlbum = albums[i];
                    albums[i] = albums[i+1];
                    albums[i+1] = holdAlbum;

                }
                else if (firstStr.compareTo(secStr) == 0) {
                    //waiting for artist class, need to compare artist names and DOB
                } */

            }

        }

        for(int i = 0; i < size; i++) { //need to properly format
            System.out.println("[" + albums[i].getTitle() + "] Released " + albums[i].getDateStr() + " Rating " + albums[i].avgRatings());
        }

    }

    /**
     * Sorts album collection by average rating, then title
     * Prints out sorted collection
     */
    public void printByRating() {

        Album holdAlbum = null;

        for (int j = 0; j < size; j++) { //sorting algorithm

            for (int i = 0; i < size - 1; i++) {

                if (albums[i].avgRatings() < albums[i+1].avgRatings()) {
                    holdAlbum = albums[i];
                    albums[i] = albums[i+1];
                    albums[i+1] = holdAlbum;
                }
                else if (albums[i].avgRatings() == albums[i+1].avgRatings()) {
                    String firstStr = albums[i].getTitle();
                    String secStr = albums[i+1].getTitle();

                    if(firstStr.compareTo(secStr) > 0) { // 1 means first str larger than sec str
                        holdAlbum = albums[i];
                        albums[i] = albums[i+1];
                        albums[i+1] = holdAlbum;

                    }

                }

            }

        }

        for(int i = 0; i < size; i++) { //need to properly format
            System.out.println("[" + albums[i].getTitle() + "] Released " + albums[i].getDateStr() + " Rating" + albums[i].avgRatings());
        }

    }


}
