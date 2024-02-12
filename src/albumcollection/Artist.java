package albumcollection;

/**
 * This class holds the information of an Artist for an Album.
 * The artist class contains a string for the name of the artist.
 * Also, an object of Date representing the Artist birthday.
 */
public class Artist implements Comparable<Artist> {
    private String name;
    private Date born;

    /**
     * Initializes an artist object
     * @param name The name of the artist
     * @param born The date of birth of the artist
     */
    public Artist(String name, Date born) {
        this.name = name;
        this.born = born;
    }

    /**
     * Compares artist objects by comparing an artist's name lexicographically.
     * DOB is used as a tie-breaker.
     * @param that the object to be compared.
     * @return 0 if items are equal.
     * -1 if this.artist < that.artist
     * 1 if this.artist > that.artist
     */
    @Override
    public int compareTo(Artist that) {
        if (this.name.toLowerCase().compareTo(that.name.toLowerCase()) > 0) return 1;
        if (this.name.toLowerCase().compareTo(that.name.toLowerCase()) < 0) return -1;
        if(this.born.compareTo(that.born) == 1) return 1;
        if(this.born.compareTo(that.born) == -1) return -1;
        else return 0;
    }

    /**
     * Overrides equals methods. Artist are equals if they have the same name and DOB
     * @param obj An artist object. Obj will be cast into an artist obj
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Artist)){
             return false;
        }
        Artist that = (Artist) obj;
        if (this.compareTo(that) == 0){
            return true;
        }
        return false;

    }

    /**
     * @return A string in the format Artist: (artist) DOB: (DOB)
     */
    @Override
    public String toString() {
        return name + ":" + born;
    }

    /**
     * @return name of artist
     */
    public String getName() {
        return name;
    }


    public static void main(String[] args) {
        testArtistSameNameDifferentCaseLesserDate();
        testArtistlexicographicalOrderLesserThan();
        testArtistSameNameSameDate();
        testArtistlexicographicalOrderGreaterThan();
        testArtistSameNameGreaterDate();
    }

    private static void testArtistSameNameDifferentCaseLesserDate(){
        int expectedOutput = -1; // Ignores name capitalization and instead compares dates. 05/24 > 03/24
        int actualOutput;
        Date testDate1 = new Date("03/24/2000");
        Artist test1 = new Artist("STEVEN Marquez", testDate1);
        Date testDate2 = new Date("05/24/2000");
        Artist test2 = new Artist("Steven Marquez", testDate2);
        actualOutput = test1.compareTo(test2);
        printTestResults(test1, test2, expectedOutput, actualOutput);
    }

    private static void testArtistlexicographicalOrderLesserThan(){
        int expectedOutput = -1; // K comes before S so this.date < that.date
        int actualOutput;
        Date testDate1 = new Date("08/24/2003");
        Artist test1 = new Artist("steven Marquez", testDate1);
        Date testDate2 = new Date("02/24/1999");
        Artist test2 = new Artist("Katiusca Suriel", testDate2);
        actualOutput = test1.compareTo(test2);
        printTestResults(test1, test2, expectedOutput, actualOutput);
    }

    private static void testArtistSameNameSameDate(){
        int expectedOutput = 0; // If its the same name then it will return 0;
        int actualOutput;
        Date testDate1 = new Date("05/24/2000");
        Artist test1 = new Artist("Steven Marquez", testDate1);
        Date testDate2 = new Date("05/24/2000");
        Artist test2 = new Artist("Steven Marquez", testDate2);
        actualOutput = test1.compareTo(test2);
        printTestResults(test1, test2, expectedOutput, actualOutput);
    }

    private static void testArtistlexicographicalOrderGreaterThan(){
        int expectedOutput = 1; // Joe is > john alphabetically.
        int actualOutput;
        Date testDate1 = new Date("05/24/2000");
        Artist test1 = new Artist("John Mark", testDate1);
        Date testDate2 = new Date("1/24/2000");
        Artist test2 = new Artist("joe Smith", testDate2);
        actualOutput = test1.compareTo(test2);
        printTestResults(test1, test2, expectedOutput, actualOutput);
    }
    private static void testArtistSameNameGreaterDate(){
        int expectedOutput = 1; // 2/24/2023 > 05/24/2000
        int actualOutput;
        Date testDate1 = new Date("2/24/2023");
        Artist test1 = new Artist("lily noor", testDate1);
        Date testDate2 = new Date("03/12/2005");
        Artist test2 = new Artist("lily noor", testDate2);
        actualOutput = test1.compareTo(test2);
        printTestResults(test1, test2, expectedOutput, actualOutput);
    }

    private static void printTestResults(Artist test1, Artist test2, int expectedOutput, int actualOutput) {
        System.out.println("Testing Artists:\n" + test1.toString() + "\n" + test2.toString());
        System.out.println("Expected output: " + expectedOutput);
        System.out.println("Actual output: " + actualOutput);
        if (actualOutput == expectedOutput) {
            System.out.println("Test passed\n-------------------------");
        }else{
            System.out.println("Test Failed\n-------------------------");
        }
    }
}
