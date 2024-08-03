/**
 * Maman 12 - Using a class to represent a given date in the Gregorian Calendar
 * 
 * @author Idan Benayoun
 * @version 2022
 */
public class Date
{

    private int _day;
    private int _month;
    private int _year;
    //constants in the class
    private static final int MIN_DAY = 1;
    private static final int MAX_DAY = 31;
    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 12;
    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int MARCH = 3;
    private static final int APRIL = 4;
    private static final int MAY = 5;
    private static final int JUNE = 6;
    private static final int JULY = 7;
    private static final int AUGUST = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    private static final int NOVEMBER = 11;
    private static final int DECEMBER  = 12;
    private static final int MIN_YEAR = 1000;
    private static final int MAX_YEAR = 9999;
    private static final int DEAFULT_DAY = 1;
    private static final int DEAFULT_MONTH = 1;
    private static final int DEAFULT_YEAR = 2000;
    private static final int LEAP_FEBRUARY = 29;

    //constructors
    /**
     * Constructor 1 - Creates a new date object
     * @param _day - gets the day in the month (1-31)
     * @param _month - gets the month(1-21)
     * @param _year - gets the year (1000-9999)
     */
    public Date(int day, int month, int year)
    {
        if (isDateRight(day,month,year)){
            this._day = day;
            this._month = month;
            this._year = year;
        }
        else{
            this._day = DEAFULT_DAY;
            this._month = DEAFULT_MONTH;
            this._year = DEAFULT_YEAR;
        }

    } //end of date method
    /**
     * Constructor 2 - copy contrusctor for date
     * If the given date is valid - creates a new Date object, otherwise creates the date 1/1/2000
     * @param _day - gets the day
     * @param _month - gets the month
     * @param _year - gets the month
     */
    public Date (Date other){
        if (other != null){
            this._day = other._day;
            this._month = other._month;
            this._year = other._year;
        }

    } //end of day method
    //getters and setters
    /**Gets the day
     * @return the day
     */
    public int getDay(){
        return _day;
    } //end of getDay method
    /**Gets the month
     * @return the month
     */
    public int getMonth(){
        return _month;
    } //end of getMonth method
    /**Gets the year
     * @return the year
     */
    public int getYear(){
        return _year;
    } //end of getYear method

    /** Sets the day
     * @param dayToSet - the value to be set
     */
    public void setDay(int dayToSet){
        if(isDateRight(dayToSet,this._month,this._year))
            this._day = dayToSet;
    } //end of setDay method

    /** Sets the month
     * @parm monthToSet - the value to be set
     */
    public void setMonth(int monthToSet){
        if(isDateRight(this._day,monthToSet,this._year))
            this._month = monthToSet;
    } //end of setMonth method

    /** Sets the year
     * @param yearToSet - the value to be set
     */
    public void setYear(int yearToSet){
        if(isDateRight(this._day,this._month,yearToSet))
            this._year = yearToSet;
    } //end of setYear method

    /**
     * Checks if two dates are equal
     * @param other - the given date
     * @return true if the dates are equal
     */
    public boolean equals (Date other){
        if(this._day == other._day && this._month == other._month && this._year == other._year)
            return true;
        return false;    
    } //end of equals method

    /**
     * Checks if this date comes before a given date
     * @param other - the given date
     * @retun true if this date comes before other
     */
    public boolean before(Date other){
        if(this._year < other._year)
            return true;
        else if (this._year == other._year){
            if(this._month < other._month)
                return true;
            if(this._month == other._month && this._day < other._day)
                return true;
        }
        return false;
    } //end of before method

    /**
     * Checks if this date comes after a given date
     * @param other - the given date
     * @return true if this date come after the other date
     */
    public boolean after(Date other){
        return other.before(this);
    } //end of after method

    /**
     * Checks the difference (in days) between 2 dates
     * @param other - the given date
     * @return int that represents the difference between the dates in days
     */
    public int difference(Date other){
        if(before(other))
            return calculateDate(other._day,other._month,other._year) - calculateDate(this._day,
                this._month,this._year);
        else if(after(other))
            return calculateDate(this._day,this._month,this._year) - calculateDate(other._day,
                other._month,other._year);

        return 0;

    } //end of difference method

    /**
     * Returns a String that represents this date
     * in the following format:
     * day/month/year (28/2/1992)
     */
    public String toString(){
        if(this._day < 10 || this._month < 10)
        {
            if(this._day >= 10)
                return this._day+"/0"+this._month+"/"+this._year;
            if(this._month >= 10)
                return "0"+this._day+"/"+this._month+"/"+this._year;
            return "0"+this._day+"/0"+this._month+"/"+this._year;
        }
        return this._day+"/"+this._month+"/"+this._year;
    } //end of toString method

    /**
     * Returns the date tomorrow after the current date
     */
    public Date tomorrow(){

        int tomorrowDay = this._day;
        int tomorrowMonth = this._month;
        int tomorrowYear = this._year;
        if(isDateRight(this._day+1, this._month, this._year))
        {
            tomorrowDay++;
        }
        else if(isDateRight(MIN_DAY, this._month + 1, this._year))
        {
            tomorrowDay = MIN_DAY;
            tomorrowMonth++;
        }
        else
        {
            tomorrowDay = MIN_DAY;
            tomorrowMonth = 1;
            tomorrowYear++;
        }
        Date tomorrow = new Date(tomorrowDay,  tomorrowMonth, tomorrowYear);
        return tomorrow;
    }// end of tomorrow method

    // computes the day number since the beginning of the Christian counting of years
    private int calculateDate ( int day, int month, int year)
    {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62);
    } //end of calculateDate method

    /**
     * Checks if the date is valid
     * @param _day - gets the day in the month (1-31)
     * @param _month - gets the month(1-21)
     * @param _year - gets the year (1000-9999)
     */
    private boolean isDateRight(int day, int month, int year)
    {
        if (day < MIN_DAY || day > MAX_DAY || month < MIN_MONTH || month > MAX_MONTH 
        || year < MIN_YEAR || year > MAX_YEAR)
            return false;

        else if(day == LEAP_FEBRUARY && month == FEBRUARY){
            if(year%4 == 0){
                if(year%100 == 0){
                    if (year%400 == 0)
                        return true; //year divides in 4, 100 and 400
                    return false; // year divides in 4 and 100 but not in 400

                }
                return true; // year divides in 4 but not in 100
            }
            return false; // year does not divide in 4
        }
        else if(day == 30){
            if(month == APRIL || month == JUNE || month == SEPTEMBER || month ==NOVEMBER)
                return true;
            return false;

        }
        else if (day==31){
            if(month == JANUARY || month == MARCH || month == MAY || month == JULY
            || month == AUGUST || month == OCTOBER || month == DECEMBER)
                return true;
            return false;
        }
        return true;
    } //end of isDateRight method
}

        

