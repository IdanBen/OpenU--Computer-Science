/** Triangle2 = The program gets 3 lengths from the user
and checks if those lengths can represent a triangle and if it does, what kind of triangle. 
 *@Author: Idan Benayoun
 *@Version:1
 */
import java.util.Scanner;
public class Triangle2
{ // Start of class Triangle2
    public static void main (String [] args)
    { //Start of method main
        Scanner scan = new Scanner (System.in); //Setting up a scan kind of variable to recieve the user's input
        System.out.println ("This program checks if 3 lengths can create a triangle "
            + "and if it can what kind of triangle. ");
        System.out.println ("Please enter the three lengths"
            + " of the triangle's sides");
        int a = scan.nextInt(); //Variables that get the user's  lengths
        int b = scan.nextInt();
        int c = scan.nextInt();

        if (a+b>c && a+c>b && b+c>a) //A condition to check if 3 lengths can represent a triangle,
        // leading to conditions checking what kind of triangle it is
        {
            if ( a==b && b==c ){ //Checks if all the three lengths are equal
                System.out.println ("The numbers: " +a+ ", " +b+ " and " +c+ " represernt  an equilateral triangle");
            }
            else if ( (a==b && b!=c) || (a==c && b!=c) || (b==c && b!=a)){ //Checks if only 2 lengths are equal
                System.out.println ("The numbers:"+a+ ", " +b+ " and " +c+ " represent an isosceles triangle");
            }
            else if (Math.pow(a,2)==Math.pow(b,2)+Math.pow(c,2) || Math.pow(b,2)==Math.pow(a,2) +Math.pow(c,2) || Math.pow(c,2) == Math.pow(a,2) +Math.pow(b,2)){
                // Checks if the 3 lengths can form Pythagoras' theorem
                System.out.println ("The numbers:"+a+ ", " +b+ " and " +c+ " represent a right angle triangle");
            }
            else 
                System.out.println ("The numbers:" +a+ ", " +b+ " and " +c+ " represent a common triangle");
        }

        else
            System.out.println ("The numbers: " +a+ ", " +b+ " and " +c+ " cannot represent a triangle");


    } // end of method main
} //end of class Triangle2