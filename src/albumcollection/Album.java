package albumcollection;

public class Album {
    private String title;
    private Artist artist;
    private Genre genre;
    private Date released;
    private Rating ratings;


    public Album(String title, Artist artist, Genre genre, Date released) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.released = released;
        this.ratings = null; //Ratings are added later
    }


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

    public static void main(String[] args) {
    }
}
