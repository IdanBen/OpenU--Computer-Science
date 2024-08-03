
/**
 * This class represents a car rental company by using a linked list.
 * @author Idan Benayoun
 * @version 2023
 */
public class Company
{
    private RentNode _head;

    /**
     * A constructor that intializes the head of the list as null.
     */
    public Company()
    {
        _head = null;
    }

    /**
     * A constructor that adds a rent to a company to it's specific location in a car rental company list.
     * The rents are arranged by their pickup date, if 2 rentals have the same pickup date the one with longer rental period will be first in order.
     * If the new rent already exists in the car rental company's data it will not be added.
     * @param name A given rentor's name.
     * @param car A given rented car.
     * @param pickDate The given's rental's starting date.
     * @param returnDate The given's rental's ending date.
     * @return True if Rent added successfully else (if Company already has the Rent) return false.
     */
    public boolean addRent(String name, Car car, Date pickDate, Date returnDate){

        RentNode current;
        RentNode temp;
        Rent tempRent = new Rent (name,car,pickDate,returnDate); //a new Rent with the specific info.
        RentNode rentNode = new RentNode(tempRent); // a new RentNode pointing to the new Rent.
        //special case for head node,incase Company is empty or that the given new Rent should be first in order.
        if(_head == null || _head.getRent().getPickDate().after(pickDate) || 
        (_head.getRent().getPickDate().equals(pickDate) && _head.getRent().howManyDays()<tempRent.howManyDays())){
            rentNode.setNext(_head);
            _head = rentNode;
            return true;
        }
        else{
            current = _head;
            //loop for finding the right location of the node
            while(current != null){
                //case: rent already exists in the Company
                if(current.getRent().equals(tempRent)){
                    return false;
                }
                //case: Company only has 1 rent(which should be before the new added rent in the correct order)
                else if(current.getNext() == null){
                    current.setNext(rentNode);
                    return true;
                }
                //case: 2 rents have the same pickup date
                else if(current.getNext().getRent().getPickDate().equals(pickDate)){
                    if(current.getNext().getRent().howManyDays()<tempRent.howManyDays()){
                        temp = current.getNext();
                        current.setNext(rentNode);
                        rentNode.setNext(temp);
                        return true;
                    }
                }
                //case: if the next rent's pickup date is after the given's rent pickup date.
                else if(current.getNext().getRent().getPickDate().after(pickDate)){
                    temp = current.getNext();
                    current.setNext(rentNode);
                    rentNode.setNext(temp);
                    return true;
                }
                else
                    current = current.getNext();
            }
            return false;
        }
    }
    /**
     * This method removes the first Rent of the Company whose it's return date equals to a given date.
     * In case which there isn't a matching Rent it does nothing.
     * @param d A given date.
     * @return True if a Rent was removed successfully else (if there wasn't a matching Rent) return false.
     */
    public boolean removeRent(Date d){
        RentNode behind = _head;
        RentNode prev = null;
        //special case: the matching Rent is in the first node.
        if(behind != null && behind.getRent().getReturnDate().equals(d)){
            _head = behind.getNext();
            return true;
        }
        //loop searching for the rent needed to be removed while keeping track of the previous node.
        while(behind != null && !behind.getRent().getReturnDate().equals(d) ) {
            prev = behind;
            behind = behind.getNext();
        }
        //case: there isn't a matching rent
        if(behind == null)
            return false;
        //unlinking the node from the linked list.
        prev.setNext(behind.getNext());  
        return true;
    }

    /**
     * This method calculates and returns the Company's number of rentals.
     * @return The number of rents in the company (as integer).
     */
    public int getNumOfRents(){
        RentNode temp = _head;
        int count = 0;
        //loop running until the end of the list.
        while(temp != null){
            count++;
            temp = temp.getNext();
        }
        return count;
    }

    /**
     * This method calculates and returns the Company profit of all the represented rentals.
     * @return The total profit of all the rentals shown in the list.
     */
    public int getSumOfPrices(){
        RentNode temp = _head;
        int sumP = 0;
        //loop running until the end of the list.
        while (temp != null){
            sumP += temp.getRent().getPrice();
            temp = temp.getNext();
        }
        return sumP;
    }

    /**
     * This method calculates and returns the total rental days of the Company.
     * @return The total rental days of the Company.
     */
    public int getSumOfDays(){
        RentNode temp = _head;
        int sumD = 0;
        //loop running until the end of the list
        while(temp != null){
            sumD += temp.getRent().howManyDays();
            temp = temp.getNext();
        }
        return sumD;
    }

    /**
     * This method calculates and returns the length of the Company's average rental days.
     * It calculates it by dividing the total rental days by the total number of rentals.
     * @return The length of the Company's average rental days (if there aren't any rentals return 0).
     */
    public double averageRent(){
        //variables that represent the total rental days and number of rentals
        int sumOfDays = getSumOfDays();
        int numOfRents = getNumOfRents();
        //casting from into to double.
        double sum = sumOfDays;;
        double num = numOfRents;
        //case: there aren't any rentals in the Company.
        if(sumOfDays == 0){
            return 0;
        }
        double averageDays = sum/num;
        return averageDays;
    }

    /**
     * This is a private method which returns the latest return date of all the company's rentals.
     * @return The latest return date as Date object (if list is empty than null).
     * 
     */
    private Date lastReturnDate(){
        RentNode current = _head;
        //case: the company is empty
        if(current == null){
            return null;
        }
        //creating a new date variable for the first rent and a temporary date which will be updated each time the loop runs
        Date lastReturn = new Date(current.getRent().getReturnDate());
        Date tempDate = null;
        //loop: while did not pass the end of the list
        while(current != null){
            //updating the temporary variable with the new node's return date
            tempDate = new Date(current.getRent().getReturnDate());
            //comparing with the previous return date
            if(tempDate.after(lastReturn)){
                //updating the new return date
                lastReturn = new Date (tempDate);
            }
            //promoting to the next node
            current = current.getNext();

        }
        return lastReturn;
    }

    /**
     * This method searches for the car with the latest return date in the Company's data.
     * If there are 2 or more Rents with the same car's return date, the one which shows up first in the list is returned.
     * If there aren't any rents null is returned.
     * @return The car with the latest return date (otherwise if empty null).
     */
    public Car lastCarRent(){
        RentNode current = _head;
        Date latestReturn = lastReturnDate();
        //case: list is empty
        if(latestReturn == null){
            return null;
        }
        //loop: searches for the rent with the last return date, runs until the last node 
        while(current != null){
            //case: rent with the desired return date was found(it will always return the one who shows up first)
            if(current.getRent().getReturnDate().equals(latestReturn)){
                return current.getRent().getCar();
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * This is a private method which searches for the Rent with longest period of rent time.
     * @return The number of rental days of the longest rent.
     */
    private int getLongestRent(){
        RentNode current = _head;
        if(current == null){
            return 0;
        }
        int temp = 0;
        int maxRentDays = current.getRent().howManyDays();
        //loop: runs until the end of the list searching for the maximum rental days period.
        while(current != null){
            temp = current.getRent().howManyDays();
            //if found a longer rent: updating the variable with the latest maximum rental days preiod.
            if(temp > maxRentDays){
                maxRentDays = temp;
            }
            current = current.getNext();
        }
        return maxRentDays;
    }

    /**
     * This method searches for the rent with the longest rental preiod.
     * If there are two or more rents with the same maximum rental period the one showing first is returned.
     * If there aren't any rents null is returned.
     * @return The rent object which contains the longest rental period. (If company is empty than null)
     */
    public Rent longestRent(){
        int maxRentDays = getLongestRent();
        RentNode current = _head;
        //case: company is empty
        if(current == null){
            return null;
        }
        //loop: searching for the desired rent, always returning the first one who fits the description
        while (current != null){
            if(current.getRent().howManyDays() == maxRentDays){
                return current.getRent();
            }
            current = current.getNext();

        }
        return null;
    }

    /**
     * This method searches for the most popular cars' rating between all the rentals. (A<B<C<D)
     * If there are two or more rates that are winning as the most popular rate, the higher rate always wins.
     * If there aren't any Rents char 'N' is returned.
     * @return The most popular car rate between the company's renters.(otherwise 'N'.
     */
    public char mostCommonRate(){
        RentNode current = _head;
        //case: company is empty
        if(current == null){
            return 'N';
        }
        //an array that will store the number of cars with each rate.
        int [] popularRate = new int[4];
        for(int i = 0; i<popularRate.length; i++){
            popularRate[i] = 0;
        }
        //loop: searching until the end of the list and adding accordingly to the right rate counter.
        while(current != null){
            char typeOfRate = current.getRent().getCar().getType();
            if(typeOfRate == 'A'){
                popularRate[0]++;
            }
            else if(typeOfRate == 'B'){
                popularRate[1]++;
            }
            else if(typeOfRate == 'C'){
                popularRate[2]++;
            }
            else if(typeOfRate == 'D'){
                popularRate[3]++;
            }
            current = current.getNext();
        }
        //case: checking if there any rate with the same car count.
        if (popularRate[3] >= popularRate[2] && popularRate[3] >= popularRate[1] && popularRate[3] >= popularRate[0]) {
            return 'D';
        } else if (popularRate[2] >= popularRate[1] && popularRate[2] >= popularRate[0]) {
            return 'C';
        } else if (popularRate[1] >= popularRate[0]) {
            return 'B';
        } else if (popularRate[0] > 0) {
            return 'A';
        } else {
            return 'N';
        }
    }

    /**
     * This is a private method used for searching for a specific located node.
     * @return The specific node in the linked list.
     */
    private RentNode getPointer(int index){
        if (index >= getNumOfRents() || index < 0){
            throw new IndexOutOfBoundsException();
        }
        RentNode temp = this._head;
        for(int i = 0; i<index; i++){
            temp = temp.getNext();
        }
        return temp;

    }

    /**
     * This is a method checks if another Company given as a parameter is absolutely included in the company
     * which the method works on.
     * You can assume there aren't 2 different rents from different lists with the same pickup and return date.
     * If the company given as a parameter is empty return false.
     * @return True - if the Company given as a parameter is absolutely included (otherwise false).
     */
    public boolean includes(Company other)
    {   
        //a pointer for the head of each list
        RentNode current = _head;
        RentNode currentOther = other._head;
        //case: company given as parameter is empty.
        if(currentOther == null){
            return true;
        }
        //loop: searching if two nodes are the same as long until the end of the list of the company which the method works on ends.
        while(current != null){
            //case: two rents are equal
            if(currentOther.getRent().equals(current.getRent())){
                //promotes to the next node in the compan which was given as a parameter
                currentOther = currentOther.getNext();
                //case: reached the end of the list which was given as a parameter
                if(currentOther == null){
                    return true;
                }
            }
            else{
                currentOther = currentOther;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * This method merges two rental lists of Company type while maintaining the list's order.
     * It can be assumed that there aren't 2 or more different rentals from different lists that have the same dates information.
     */
    public void merge(Company other){
        if(other == null)
            return;
        //case: the given list is already included in the list,don't do anything
        if(!this.includes(other)){
            for (int i = 0; i < other.getNumOfRents(); i++) {
                //if it isn't included: try adding each of the rents.
                addRent(other.getPointer(i).getRent().getName(), other.getPointer(i).getRent().getCar(),
                other.getPointer(i).getRent().getPickDate(), other.getPointer(i).getRent().getReturnDate());
                other.removeRent(other.getPointer(i).getRent().getReturnDate());
            }
        }
    }

    /**
     * This method returns a description of all the rentals existing in the Company.
     * @return String representing the Company's rentals list.
     */
    public String toString(){
        if(_head == null){
            return "The company has 0 rents.";
        }
        String rentsDetails = "The company has " + getNumOfRents() + " rents: \n";
        RentNode temp = _head;
        while(temp != null){
            rentsDetails += temp.getRent().toString() + "\n";
            temp = temp.getNext();
        }
        return rentsDetails;
    }
}

