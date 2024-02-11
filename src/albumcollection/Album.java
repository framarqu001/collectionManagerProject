package albumcollection;

/**
 * This class represents an album in a music collection.
 * An album contains a title, artist, genre, release date, and list of ratings
 */
public class Album {
    private String title;
    private Artist artist;
    private Genre genre;
    private Date released;
    private Rating ratings;

    /**
     * Initializes an album object
     * @param title The title of the Album
     * @param artist Arist who created the Album.
     * @param genre  Genre categorizes the Album into  be Pop, Country, Classical, Jazz, or Unknown
     * @param released The release date of an album
     */
    public Album(String title, Artist artist, String genre, Date released) {
        this.title = title;
        this.artist = artist;
        this.genre = setGenre(genre);
        this.released = released;
        this.ratings = null; //Ratings are added later
    }

    /**
     * initializes an album object. This constructor is used to find an album to delete
     * @param title The title of the Album
     * @param artist Arist who created the Album.
     */
    public Album(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
    }


    private Genre setGenre(String genre){
        for (Genre element: Genre.values()){
            if (genre.toLowerCase().equals(element.name().toLowerCase())){
                return element;
            }
        }
        return Genre.UNKNOWN;
    }

    /**
     * Adds a rating to the album. Ratings are a linked-list of rating nodes.
     * An album object initializes with ratings set to null until the rate method is called. If rate is called
     * when ratings is null, the method creates a new rating node which is the head node. If the head is already exist,
     * the method iterates the linked-list to add a rating to end of the list.
     * @param star The rating to be added to an album. Ratings are integers from 1 to 5
     */
    public void rate(int star) {
        if (ratings == null){
            ratings = new Rating(star);
            return;
        }
        Rating currentNode = ratings;
        while (currentNode.getNext() != null){
            currentNode = currentNode.getNext();
        }

        currentNode.setNext(new Rating(star));
    }

    public double avgRatings() {
        if (ratings == null){
            return 0.0;
        }

        double total = 0;
        int count = 0;
        Rating currentNode = ratings;
        while (currentNode != null){
            count++;
            total += currentNode.getStar();
            currentNode = currentNode.getNext();
        }

        return total/count;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Album)){
            return false;
        }
        Album that = (Album) obj;
        if (this.artist.equals(that.artist) && (this.title.toLowerCase().equals(that.title.toLowerCase()))){
            return true;
        }
        return false;
    }

    private int[] nodeRatings(){
        final int TOTAL_STARS = 5;
        final int OFFSET = 1; // count of each star value will be stored at index of [starvalue - 1]
        int[] nodeRatings = new int[TOTAL_STARS];
        Rating currentNode = ratings;
        while (currentNode != null){
            int starValue = currentNode.getStar();
            nodeRatings[starValue-OFFSET] += 1; //increasing count
            currentNode = currentNode.getNext();
        }
        return nodeRatings;
    }

    @Override
    public String toString() {
        int[] rateArr = nodeRatings();
        if (ratings == null){
            return"[" + title + "] Released " + released + "[" + artist + "][" + genre.name() + "]Rating: none";
        }
        return"[" + title + "] Released " + released + "[" + artist + "][" + genre.name() + "]Rating:" +
                "*(" + rateArr[0] + ")" + "**(" + rateArr[1] + ")" + "***(" + rateArr[2] + ")" +
                "****(" + rateArr[3] + ")" + "*****(" + rateArr[4] + ")";
    }


    public String getTitle() {
        return title;
    }

    /**
     * @return name of the genre of the album
     */
    public Genre getGenre() {
        return genre;
    }


    /**
     * @return date (obj) of album
     */
    public Date getDate() {
        return released;
    }


    /**
     * @return date (str) of the album
     */
    public String getDateStr() {
        return released.toString();
    }



    public static void main(String[] args) {
        Date dob = new Date(05,24,2000);
        Date released = new Date(2,8,2024);
        Artist artist = new Artist("taylor", dob);
        Album test = new Album("Fearless", artist, "pop",released);
        System.out.println(test);
        test.rate(4);
        test.rate(4);
        test.rate(3);
        test.rate(1);
        test.rate(5);
        System.out.println(test);
    }
}
