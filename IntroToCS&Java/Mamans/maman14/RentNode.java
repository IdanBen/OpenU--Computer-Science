
/**
 * This class represent a single node of Rent type.
 *
 * @author Idan Benayoun
 * @version 2023
 */
public class RentNode
{   //the class'es fields
    private Rent _rent;
    private RentNode _next;
    
    //constructors
    /**
     * A constructor that recieves a Rent object and stores it in a node format.
     * The _next field is intialized as null.
     * @param r A given rent object.
     */
    public RentNode(Rent r){
        this._rent = new Rent(r);
        this._next = null;
    }
    /**
     * A constructor that recieves a Rent and RentNode objects and intializes the fields.
     * @param r A given Rent object.
     * @param next A given RentNode object.
     */
    public RentNode(Rent r, RentNode next){
        this._rent = new Rent(r);
        this._next = next;
    }
    /**
     * Copy constructor for RentNode class.
     *@param other A given RentNode object.
     */
    public RentNode(RentNode other){
        _rent = new Rent(other._rent);
        _next = other._next;
    }
    
    //methods in RentNode class
    /**
     * This method returns a copy of the rent that is stored in the node.
     * @return Copy of the rent stored in the element.
     */
    public Rent getRent(){
        return new Rent (_rent);
    }
    
    /**
     * This method returns a pointer to the next node.
     * @return A pointer to the next node.
     */
    public RentNode getNext(){
        return _next;
    }
    /**
     * This method recieves a Rent object and updates the rent attribute of the element.
     * @param r A given Rent object.
     */
    public void setRent(Rent r){
        this._rent = new Rent(r);
    }
    /**
     * This method recieves a RentNode object and updates the pointer attribute to the next element.
     * @param next A given new pointer.
     */
    public void setNext(RentNode next){
        this._next = next;
    }

}
