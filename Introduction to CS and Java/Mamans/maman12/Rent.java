
/**
 * Maman 12 - Using a class to represent a rent 
 * 
 * @author Idan Benayoun
 * @version 2022
 */
public class Rent
{
    private String _name;
    private Car _car;
    private Date _pickDate;
    private Date _returnDate;
    //constants in the class
    private final int TYPE_A_PRICE = 100;
    private final int TYPE_B_PRICE = 150;
    private final int TYPE_C_PRICE = 180;
    private final int TYPE_D_PRICE = 240;
    private final int DAYS_PER_WEEK = 7;
    private final double DISCOUNT = 0.9;
    //constructors
    /**
     * Constructor 1 - Creates a new rent object
     * The return date must be at least a day after the pickup date, 
     * otherwise it is set to the next day after the pickup date
     * @param _name - gets the name of the client
     * @param _car - gets the car object
     * @param _pickDate - gets the pickup date of the car
     * @param _returnDate - gets the return date of the car
     */
    public Rent(String name,Car car,Date pick,Date ret){
        _name = name;
        _car = car;
        _pickDate = pick;
        if(ret.after(_pickDate)){
            _returnDate = ret;
        }
        else{
            _returnDate = new Date (pick.getDay()+1,pick.getMonth(),pick.getYear());
        }

    }

    /**
     * Constructor 2 - copy constructor for rent
     */
    public Rent(Rent other){
        if(other!=null){
            this._name = other._name;
            this._car = new Car (other._car.getId(),other._car.getType(),other._car.getBrand()
            ,other._car.isManual());
            this._pickDate = new Date (other._pickDate.getDay(),other._pickDate.getMonth(),
                other._pickDate.getYear());
            this._returnDate = new Date (other._returnDate.getDay(),other._returnDate.getMonth()
            ,other._returnDate.getYear());
        }
    }

    //getters and setters

    /**
     * Gets the name
     * 
     * @return the name
     */
    public String getName(){
        return _name;
    }

    /** Gets the car
     * 
     * @return the car
     */
    public Car getCar(){
        return _car;
    }

    /** Gets the pickup date
     * 
     * @return the pickup date
     */
    public Date getPickDate(){
        return _pickDate;
    }

    /** Gets the return date
     * 
     * @return the return date
     */
    public Date getReturnDate(){
        return _returnDate;
    }

    /** Returns the rental total price
     * @return the rental total price
     */
    public int getPrice(){
        int rentDays = this.howManyDays();
        int amountOfWeeksDiscount = rentDays / DAYS_PER_WEEK;
        int numDaysNoDiscount = rentDays % DAYS_PER_WEEK;
        // Price calculation according to the type of car
        if(this._car.getType() == 'A')
        {
            return (int) (( DAYS_PER_WEEK * amountOfWeeksDiscount * TYPE_A_PRICE ) * (DISCOUNT) + ( numDaysNoDiscount * TYPE_A_PRICE));
        }
        else if(this._car.getType() == 'B')
        {
            return (int) (( DAYS_PER_WEEK * amountOfWeeksDiscount * TYPE_B_PRICE ) * (DISCOUNT) + ( numDaysNoDiscount * TYPE_B_PRICE));
        }
        else if(this._car.getType() == 'C')
        {
            return (int) (( DAYS_PER_WEEK * amountOfWeeksDiscount * TYPE_C_PRICE ) * (DISCOUNT) + ( numDaysNoDiscount * TYPE_C_PRICE));
        }
        return  (int) (( DAYS_PER_WEEK * amountOfWeeksDiscount * TYPE_D_PRICE ) * ( DISCOUNT) + ( numDaysNoDiscount * TYPE_D_PRICE));

    }

    /** Sets the name
     * 
     * @param name - the given new name (you can assume that the name is valid)
     */
    public void setName(String name){
        _name=name;
    }

    /** Sets the car
     * 
     * @param car - the given new car (you can assume that the car is not null)
     */
    public void setCar(Car car){
        _car = new Car(car.getId(),car.getType(),car.getBrand(),car.isManual());
    }

    /** Sets the pickup date.
     * The pick up date must be at least one day before the return date,
     * otherwise - don't change the pick up date
     * @param pickDate - the given new pickup date (you can assume that the pick up date is not null)
     */
    public void setPickDate(Date pickDate){
        if(pickDate.before(_returnDate))
            _pickDate = new Date(pickDate.getDay(),pickDate.getMonth(),pickDate.getYear());

    }

    /** Sets the return date.
     * The return date must be at least one day after the pick up date,
     * otherwise - don't change the return date
     * @param returnDate - the given new return date (you can assume that the return date is not null)
     */
    public void setReturnDate(Date returnDate){
        if(returnDate.after(_pickDate))
            _returnDate = new Date(returnDate.getDay(),returnDate.getMonth(),returnDate.getYear());

    }

    /** Method checks if 2 rents are the same
     * 
     * @param other - the given other rent
     * @return true if the rents are the same, else false
     */
    public boolean equals(Rent other){
        if(this._name == other._name && this._car.equals(other._car) && 
        this._pickDate.equals(other._pickDate) && this._returnDate.equals(other._returnDate)){
            return true;
        }
        else{
            return false;
        }
    } // end of equals method
    /** Method returns the number of rent days
     * 
     * @return the number of rent days
     */
    public int howManyDays(){
        return this._pickDate.difference(this._returnDate);
    } //end of method HowManyDays
    /**Method tries to upgrade the rentor's car to a better one.
     * If the given car is better than the current car of the rent,
     * upgrade it and return the upgrade additional cost, otherwise - don't upgrade
     * @param newCar - the new car to be updated
     * @return the upgrade cost
     */
    public int upgrade (Car newCar){
        if(newCar.better(this._car)){
            int priceBeforeUpgrade = this.getPrice();
            this._car = new Car(newCar.getId(),newCar.getType(),newCar.getBrand(),
                newCar.isManual());
            int increase = this.getPrice() - priceBeforeUpgrade;
            return increase;
        }
        else{
            return 0;
        }
    } //end of method upgrade
    /**Method checks if there is a double listing of a rent for the same person
     * and car with an overlap in the rental days
     * 
     * @parm other - the other rent
     * @return the unified rent or null
     */
    public Rent overlap (Rent other){
        if(other._name.equals(this._name) && other._car.equals(this._car)){ //checks if name and car is same
            if(other._pickDate.equals(this._returnDate) ||
            other._pickDate.before(this._returnDate)){ 
                //checks if the other pickDate is before or equal to the return date 
                //in the following terms i check between the minimum between both pickup dates and the maximum between both return dates
                if(this._pickDate.after(other._pickDate)!=true &&
                this._returnDate.before(other._returnDate)!=true){
                    return new Rent(this.getName(),this.getCar(),this.getPickDate(),
                        this.getReturnDate());

                }
                else if(this._pickDate.after(other._pickDate)!=true &&
                other._returnDate.before(this._returnDate)!=true){
                    return new Rent(this.getName(),this.getCar(),this.getPickDate(),
                        other.getReturnDate());
                }
                else if(other._pickDate.after(this._pickDate)!=true &&
                this._returnDate.before(this._returnDate)!=true){
                    return new Rent(this.getName(),this.getCar(),other.getPickDate(),
                        this.getReturnDate());
                }
                else{
                    return new Rent(this.getName(),this.getCar(),other.getPickDate(),
                        other.getReturnDate());
                }
            }
            return null; //returns null if the second term is false
        }
        return null; //returns null if the first term is false
    }

    /** Returns a String that represents this name.
     * @return String that represents this rent
     * in the following format:
     * Name: From: To: Type: Day: Price
     */
    public String toString(){
        return "Name:"+this._name+" From:"+this._pickDate.toString()+
        " To:"+this._returnDate.toString()+" Type:"+this._car.getType()+
        " Days:"+this.howManyDays()+" Price:"+this.getPrice();
    }
}

