/** Triangle1 = The program gets 3 lengths of the user's triangle sides,
and calculates the triangle's perimeter and area
 *@Author: Idan Benayoun
 *@Version:1
 */
import java.util.Scanner;
public class Triangle1
{ // Start of class Triangle1
    public static void main (String [] args)
    { //Start of method main
        Scanner scan = new Scanner (System.in); //Setting up a scan kind of variable to recieve the user's input
        System.out.println ("This program calculates the area "
            + "and the perimeter of a given triangle. ");
        System.out.println ("Please enter the three lengths"
            + " of the triangle's sides");
        int a = scan.nextInt(); //Variables that get the user's triangle's lengths
        int b = scan.nextInt();
        int c = scan.nextInt();
        int perimeter = a+b+c; //Calculates the perimeter
        double area = Math.sqrt(perimeter/2*(perimeter/2-a)*(perimeter/2-b)
                *(perimeter/2-c)); //Calculates the area
        System.out.println("The perimeter of the traingle is:" +perimeter);
        System.out.println("The area of the traingle is:" +area);
    } // end of method main
} //end of class Triangle1