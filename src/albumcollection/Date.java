package albumcollection;
import java.util.Calendar;

/**
 * The {@code Date} class represents a date to be used In Collection Manager.
 * The Class contains logic to determine if created date is a valid Calendar date.
 * @author Francisco Marquez
 */
public class Date implements Comparable<Date> {

    /**
     * This enum represents the valid months and days for the Date Class.
     * Each month has a month value and the amount of days associated with that month.
     */
    private enum Month {
        JANUARY(1, 31), FEBRUARY(2, 28), MARCH(3, 31), APRIL(4, 30), MAY(5, 31), JUN(6, 30), JULY(7, 31),
        AUGUST(8, 31), SEPTEMBER(9, 30), OCTOBER(10, 31), NOVEMBER(11, 30), DECEMBER(12, 31), LEAP_FEBRUARY(2, 29);
        private final int monthNum;
        private final int monthDays;

        /**
         * Constructs an enum Month
         * @param monthNum the value associated with a month
         * @param monthDays the amount of days in a month
         */
        Month(int monthNum, int monthDays) {
            this.monthNum = monthNum;
            this.monthDays = monthDays;
        }

        /**
         * @return the month's number. Ranging from 1-12
         */
        public int getMonth() {
            return monthNum;
        }

        /**
         * @return the days in a month.
         */
        public int getDays() {
            return monthDays;
        }
    }

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int MIN_YEAR = 1900;
    public static final int MONTH_OFFSET = 1; //Calendar.Month indexes month from 0
    private int day;
    private int month;
    private int year;

    /**
     * Constructs a Date object.
     * Parses token and stores the month, day, and year.
     * @param token A string in the format of "mm/dd/yyyy
     */
    public Date(String token){
        String[] dateToken = token.split("/");
        this.month = Integer.parseInt(dateToken[0]);
        this.day = Integer.parseInt(dateToken[1]);
        this.year = Integer.parseInt(dateToken[2]);
    }

    /**
     * Checks if the Month and Day of the Date object is a valid calendar day.
     * @return true if month and day are valid values, false otherwise
     */
    private boolean validateMonthDay() {
        Month enumMonth = null;
        for (Month element : Month.values()) {
            if (element.getMonth() == month) {
                enumMonth = element;
                break;
            }
        }
        if (enumMonth == null) return false;
        if (enumMonth == Month.FEBRUARY) {
            if (leapYear()) {
                enumMonth = Month.LEAP_FEBRUARY;
            }
        }
        if (day <= enumMonth.getDays() && day > 0) { // do i need to change 0?
            return true;
        }

        return false;

    }

    /**
     * Checks if the Date object's year is considered a leap year.
     * @return true if leap year, false otherwise.
     */
    private boolean leapYear() {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL != 0 || year % QUATERCENTENNIAL == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the Date object contains a valid date.
     * To be a valid date it must not be before the year 1900.
     * Must be date that exist on the Calendar.
     * Cannot be today or a future day.
     * @return true if valid, false otherwise.
     */
    public boolean isValid () {
        if (year < MIN_YEAR) return false;
        if (!validateMonthDay()) return false;
        Calendar calendar = Calendar.getInstance();
        int calYear = calendar.get(Calendar.YEAR);
        int calMonth = calendar.get(Calendar.MONTH) + MONTH_OFFSET;
        int calDay = calendar.get(Calendar.DAY_OF_MONTH);
        Date todayDate = new Date(calMonth, calDay, calYear);
        if (compareTo(todayDate) != -1) return false; // This.Date is greater >= today's date, so date is invalid
        return true; //placeholder
    }

    /**
     * Compares Date objects based on date order.
     * Dates that come before other dates or consider less than.
     * @param that the date object to be compared to.
     * @return 0 if the items are equal.
     * -1 if this.date < that.date.
     * 1 if this.date > that.date
     */
    @Override
    public int compareTo (Date that){
        if (this.year  < that.year) return -1;
        if (this.year > that.year) return 1;
        if (this.month  < that.month) return -1;
        if (this.month > that.month) return 1;
        if (this.day  < that.day) return -1;
        if (this.day > that.day) return 1;
        return 0;
    }

    /**
     * @return A string in the format of mm/dd/year
     */
    @Override
    public String toString() {
        return (month + "/" + day + "/" + year);
    }

    public static void main(String[] args) {
        testDaysInFeb_Leap();
        testDaysInFeb_NonLeap();
        testDateMonth();
        testDaysInJune31();
        testDaysInJune30();
        testYearBefore1900();
        testCurrentOrFutureDate();
    }

    private static void testDaysInFeb_Leap() {
        boolean expectedOutput = true; // if leap year then there is a 29th day
        boolean actualOutput;

        Date test = new Date("2/29/2000");
        actualOutput = test.isValid();
        System.out.println("Testing if date is valid date in February for a leap year");
        printTestResults(test, expectedOutput, actualOutput);
    }

    private static void testDaysInFeb_NonLeap() {
        boolean expectedOutput = false; // if non leap year then theres not a 29th day
        boolean actualOutput;
        Date test = new Date("2/29/2013");
        actualOutput = test.isValid();
        System.out.println("Testing if date is valid date in February for a nonleap year");
        printTestResults(test, expectedOutput, actualOutput);
    }

    private static void testDateMonth() {
        boolean expectedOutput = false; // if month not in range 1-12 then invalid month.
        boolean actualOutput;
        Date test = new Date("13/2/2005");
        actualOutput = test.isValid();
        System.out.println("Testing if 13 is a valid month value");
        printTestResults(test, expectedOutput, actualOutput);
    }

    private static void testDaysInJune31() {
        boolean expectedOutput = false; // June should only have 30 days
        boolean actualOutput;
        Date test = new Date("6/31/2012");
        actualOutput = test.isValid();
        System.out.println("Testing if 31 is a valid date for June");
        printTestResults(test, expectedOutput, actualOutput);
    }

    private static void testDaysInJune30() {
        boolean expectedOutput = true; // June can have a 30th day
        boolean actualOutput;
        Date test = new Date("6/30/2012");
        actualOutput = test.isValid();
        System.out.println("Testing if 30 is a valid date for June");
        printTestResults(test, expectedOutput, actualOutput);
    }

    private static void testYearBefore1900() {
        boolean expectedOutput = false; // Date cannot have a year before 1900
        boolean actualOutput;
        Date test = new Date("2/5/1899");
        actualOutput = test.isValid();
        System.out.println("Testing if 1899 is a valid year");
        printTestResults(test, expectedOutput, actualOutput);
    }

    private static void testCurrentOrFutureDate() {
        boolean expectedOutput = false; // Date cannot be current or future date.
        boolean actualOutput;
        Date test = new Date("05/24/2025");
        actualOutput = test.isValid();
        System.out.println("Testing if 05/24/2025 is a valid date");
        printTestResults(test, expectedOutput, actualOutput);
    }

    private static void printTestResults(Date date, boolean expectedOutput, boolean actualOutput) {
        System.out.println("Testing Date: " + date.toString());
        System.out.println("Expected output: " + expectedOutput);
        System.out.println("Actual output: " + actualOutput);
        if (actualOutput == expectedOutput) {
            System.out.println("Test passed\n-------------------------");
        }else{
            System.out.println("Test Failed\n-------------------------");
        }
    }

}

