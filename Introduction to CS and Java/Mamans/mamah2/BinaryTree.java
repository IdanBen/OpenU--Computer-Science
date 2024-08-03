
/**
 * Write a description of class BinaryTree here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BinaryTree
{
    private Node root;
    private BinaryTree _leftSon,_rightSon;
    
    public void addLeftSon(Node node){
        root.setLeftSon(node);
        
    }
    public void addRightSon(Node node){
        root.setLeftSon(node);
    }
    /**
     * Constructor for objects of class BinaryTree
     */
    public static int f(Node root)
    {
        if(root == null)
            return 0;
        return f(root.getLeftSon()) +1 + f(root.getRightSon()); 
    }
    
    public static boolean g(Node root){
        if(root == null)
            return true;
        if(root.getNumber() % 2  ==1)
            return false;
        return g(root.getLeftSon()) && g(root.getRightSon());   
        
    }
    
    public static int secret(Node root){
        if(g(root)){
            return f(root);
        }
        return Math.max(secret(root.getLeftSon()),secret(root.getRightSon()));
    }
}
