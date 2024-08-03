
/**
 * Maman 12 - Using a class to represent a given car
 * 
 * @author Idan Benayoun
 * @version 2022
 */
public class Car
{

    private int _id;
    private char _type;
    private String _brand;
    private boolean _isManual;
    //constants in the class
    private static final int MIN_ID=0000001;
    private static final int DEFAULT_ID=9999999;
    private static final char MIN_TYPE='A';
    private static final char MAX_TYPE='D';

    //constructors
    /**
     * Constructor 1 - Creates a new car object
     * ID should be 7 digits number, otherwise it is set to 9999999
     * Type should be between 'A'-'D' or else set to 'A'
     * @param _id - gets the car id (7 digits), otherwise set to deafult (9999999)
     * @param _type - gets the type of the car (A-D), otherwise set to 'A'
     * @param _brand - gets the brand of the car
     * @param _isManual - gets the
     */
    public Car(int id, char type, String brand, boolean isManual)
    {
        //checks if id is valid
        if(id >= MIN_ID && id <= DEFAULT_ID){
            this._id=id;
        }
        else{
            this._id = DEFAULT_ID;
        }
        //checks if type is valid
        if(type >= MIN_TYPE && type <= MAX_TYPE){
            this._type = type;
        }
        else{
            this._type = MIN_TYPE;
        }
        this._brand=brand;
        this._isManual=isManual;
    } //end of car method

    /**
     * Constructor 2 - copy constructor for car
     */
    public Car(Car other){
        //checks if the object isn't null
        if(other!=null){
            this._id = other._id;
            this._type = other._type;
            this._brand = other._brand;
            this._isManual = other._isManual;
        }
    } //end of car method

    //getters and setters

    /**Gets the id
     * 
     * @return the id
     */

    public int getId(){
        return this._id;
    } //end of getId method

    /**Gets the type
     * 
     * @return the type
     */
    public char getType(){
        return this._type;
    } //end of getType method

    /**Gets the brand
     * 
     * @return the brand
     */
    public String getBrand(){
        return this._brand;
    } // end of getBrand method

    /**Gets the isManual flag
     * 
     * @return the isManual flag
     */
    public boolean isManual(){
        return this._isManual;
    } //end of getIsManual method

    /** Sets the id (only if the given id is valid)
     * @param id - the given new id
     */
    public void setId(int id){
        if(id >= MIN_ID && id <= DEFAULT_ID)
            this._id=id;
    } // end of setId method

    /** Sets the type (only if the given type is valid)
     * @param type - the given new type
     */
    public void setType(char type){
        if(type >= MIN_TYPE && type <= MAX_TYPE)
            this._type=type;
    } //end of setType method

    /** Sets the brand 
     * @param brand - the given new brand
     */
    public void setBrand(String brand){
        this._brand=brand;
    } // end of setBrand method

    /** Sets the isManual flag 
     * @param manual - the given new isManual flag
     */
    public void setIsManual(boolean manual){
        this._isManual=manual;
    } //end of setIsManual method

    /** Returns string object that represents this car
     * @return String that represents this car
     * in the following format:
     * id: type: brand: gear: (id:1234567 type:A brand:Ferrari gear:manual)
     */
    public String toString(){
        if(this._isManual==true)
            return "id:"+this._id+" type:"+this._type+" brand:"+this._brand+" gear:manual";
        else
            return "id:"+this._id+" type:"+this._type+" brand:"+this._brand+" gear:auto";
    } //end of toString method

    /**
     * Checks if two cars are equal 
     * @param other - the given car
     * cars are considered equal if they have the same type, brand and gear
     * @return true if the cars are equal
     */
    public boolean equals (Car other){
        if(this._type == other._type && this._brand == other._brand && 
        this._isManual == other._isManual){
            return true;
        }
        else{
            return false;
        }
    } //end of equals method

    /**
     * Checks if a car is better than a given other car
     * a car is considered better than another car if its type is higher.
     * if both cars have the same type, an automated car is better than a manual car.
     * @param other - the given car
     * @return true if this car is better than the other car, else false
     */
    public boolean better (Car other){
        if(this._type > other._type){
            return true;
        }
        else if(this._type == other._type){
            if(this._isManual == false && other._isManual == true)
                return true;
            else 
                return false;

        }
        return false;
    } //end of better method

    /**
     * Checks if the a car is worse than a given other car
     * a car is considered worse than another car if its type is lower.
     * if both cars have the same type, a manual car is worse than an automated car.
     * @param other - the given car
     * @return true if this car is worse than the other car, else false
     */
    public boolean worse(Car other){
        return other.better(this);
    } // end of worse method

}
