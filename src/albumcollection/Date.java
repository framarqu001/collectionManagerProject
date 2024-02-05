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
         * @return the month number. Ranging from 1-12
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

    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Checks if the Month and Day of the date object is Valid.
     * Iterates through Month Enum to check if curren
     * @return
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

    private boolean leapYear() {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL != 0 || year % QUATERCENTENNIAL == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isValid () {
        if (year < MIN_YEAR) return false;
        if (!validateMonthDay()) return false;
        Calendar calendar = Calendar.getInstance();
        int calYear = calendar.get(Calendar.YEAR);
        int calMonth = calendar.get(Calendar.MONTH) + MONTH_OFFSET;
        int calDay = calendar.get(Calendar.DAY_OF_MONTH);
        Date todayDate = new Date(calYear, calMonth, calDay);
        if (compareTo(todayDate) >-1) return false; // This.Date is greater >= today's date, so date invalid
        return true; //placeholder
    }

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

    public static void main(String[] args) {
        Date test = new Date(2024,3,5);
        System.out.println(test.isValid());

        Calendar calendar = Calendar.getInstance();
        int calYear = calendar.get(Calendar.YEAR);
        int calMonth = calendar.get(Calendar.MONTH) + MONTH_OFFSET;
        int calDay = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println(calYear + " " + calMonth + " " + calDay);
    }
}

