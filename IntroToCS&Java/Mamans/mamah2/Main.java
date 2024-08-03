
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date) 
 */
public class Main
{
    public static void main(String[]args){
        BNode bt = new Node(26);
        Node n1 = new Node(26);
        Node n3 = new Node(46);
        Node n4 = new Node(45);
        Node n5 = new Node(10);
        Node n6 = new Node(50);
        Node n7 = new Node(22);
        Node n8 = new Node(20);
        Node n9 = new Node(30);
        Node n10 = new Node(21);
        n1.setLeftSon(n3);
        n1.setRightSon(n7);
        n3.setLeftSon(n4); 
        n3.setRightSon(n5);
        n5.setRightSon(n6);
        n7.setLeftSon(n8);
        n8.setLeftSon(n9);
        n8.setRightSon(n10);
        bt.root = n1;
        
        int x = f(n1);
        
    }
}
