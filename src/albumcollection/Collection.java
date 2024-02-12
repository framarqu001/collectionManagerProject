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
     * Adds a rating to an album
     *
     * @param album  the album being rated
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
     * Prints out the collection in proper format (Rating is WIP)
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

                    Date date1 = albums[i].getDate();
                    Date date2 = albums[i + 1].getDate();


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


    public static void main(String[] args) {
        testAdd();

    }


    private static void testAdd() {
        Collection albums = new Collection();
        Date testDate1 = new Date("1/1/2000");

        Artist test1 = new Artist("Taylor Swift", testDate1);
        Artist test2 = new Artist("Ken Lamar", testDate1);
        Artist test3 = new Artist("MJ", testDate1);
        Artist test4 = new Artist("Pop Smoke", testDate1);
        Artist test5 = new Artist("Juice", testDate1);
        Artist test6 = new Artist("Green Day", testDate1);

        Album alb1 = new Album("Midnights",test1);
        Album alb2 = new Album("Butterfly",test2);
        Album alb3 = new Album("Thriller",test3);
        Album alb4 = new Album("Woo",test4);
        Album alb5 = new Album("Love Race",test5);
        Album alb6 = new Album("DNA",test2);
        Album alb7 = new Album("Can't Die",test5);
        Album alb8 = new Album("Kelce",test1);
        Album alb9 = new Album("Brain Stew",test6);
        Album alb10 = new Album("Not here",test1);

        albums.add(alb1);
        albums.add(alb2);
        albums.add(alb3);
        albums.add(alb4);
        albums.add(alb5);
        albums.add(alb6);
        albums.add(alb7);
        albums.add(alb8);
        albums.add(alb9);

        System.out.println(albums.size);

        System.out.println(albums.contains(alb10));

    }

    private static void testSort() {
        Collection albums = new Collection();

        Date artb1 = new Date("12/13/1989");
        Artist art1 = new Artist("Taylor Swift", artb1);
        String gen1 = "POP";
        Date rel1 = new Date("3/25/2022");
        Rating rate1 = new Rating(1);
        Album alb1 = new Album("Midnights", art1, gen1, rel1);

        Date artb2 = new Date ("6/12/1985");
        Artist art2 = new Artist ("Chris Young", artb2);
        String gen2 = "COUNTRY";
        Date rel2 = new Date("10/20/2017");
        Album alb2 = new Album("Losing Sleep", art2, gen2, rel2);


        Date artb3 = new Date ("1/1/1997");
        Artist art3 = new Artist ("Coldplay", artb3);
        String gen3 = "JAZZ";
        Date rel3 = new Date("5/16/2014");
        Album alb3 = new Album("Ghost Stories", art3, gen3, rel3);

        Date artb4 = new Date ("1/19/1955");
        Artist art4 = new Artist ("Simon Rattle", artb4);
        String gen4 = "CLASSICAL";
        Date rel4 = new Date("9/14/1999");
        Album alb4 = new Album("Wondeful Town", art4, gen4, rel4);

        albums.add(alb1);
        albums.add(alb2);
        albums.add(alb3);
        albums.add(alb4);

    }


}
