/**
 * Maman 13
 * @author Idan Benayoun
 * @version 2023
 */

public class Ex13
{
    /**
     * Exercise 1
     * The method returns the minimum number of swaps required to make a certain sequence 
     * of 2n bits (which contains exactly n 0's and n 1's) alternated.
     * The time complexity of this method is O(n) - n is the length of the given string.
     * The space complexity of this method is O(1) - since we don't define a new array within
     * the method the space complexity remains constant.
     * @param s 2n bits sequence which contains n 0's and n 1's.
     * @return The minimum swaps required to make it alternting sequence.
     */
    public static int alternating (String s)
    {
        //counting the number of 0's in odd and even positions, in this exercise we swap 1's and 0's
        //so basically we need to swap all the 0's only to odd or even positions.

        int odd_0 = 0, even_0 = 0; //counters for odd and even positions of 0's
        for(int i=0; i<s.length(); i++){ //for loop which scans all the sequence
            if(s.charAt(i) == '0'){ //
                if(i%2==0) //if the 0 is at even position add 1 to even_0 counter
                    even_0++;
                else //else the 0 must be at odd position, and we add 1 to odd_0 counter
                    odd_0++;
            }
        }
        //calculates the minimum swaps required
        return Math.min(even_0,odd_0);
    }

    /**
     * Exercise 2 
     * Part A - the method "what" returns the length (as a number) of the longest sequence of numbers
     * of the given int array 'a', which the sum of all it's elements is even.
     * 
     * Part B - the time complexity of "what" is O(n^3) and the space complexity is O(1).
     * 
     * Part D - the new time complexity is O(N) - there is 1 for loop which runs n times.
     * the space complexity is O(1) - since we don't define a new array within the method the space
     * complexity remains costant, we only define 2 ints and 1 boolean.
     * @param a  Int type array
     * @return Length of longest sequence whose sum is even.
     */

    public static int what (int[] a) {
        boolean oddFound = false;
        int firstOddIndex = 0, oddsCounter = 0, lastOddIndex = 0;
        //checks how many odd numbers the array contains and locates the first one
        for(int i = 0; i < a.length; i++) {
            if(Math.abs(a[i] % 2) == 1) {
                if(!oddFound) { 
                    oddFound = true;
                    firstOddIndex = i;
                }
                oddsCounter++;
                lastOddIndex = i;
            }
        }
        //if there aren't any odd numbers in the array
        //or there is an even number of odd numbers, then the sum of all the array is even.
        if(!oddFound || oddsCounter % 2 == 0) {
            return a.length;
        }
        return Math.max(lastOddIndex, a.length - firstOddIndex - 1);
        //there is odd number of odd numbers, so we need to take the longer sequence.
        //the first sequence starts at 0 until the last odd number (NOT included).
        //the second sequence starts from the end of the array until at the first odd number found (NOT included). 

    }

    /**
     * Exercise 3
     * Checking if there is a legal path from the first to the last cell of an array,
     * jumping left or right based on the value stored in the cells.
     * @param a Given array filled with real positive integers.
     * @return True if there is a legal path and otherwise false.
     */

    public static boolean isWay(int[] a) {
        //create a new boolean array in order to keep track of visited indexes
        boolean[] visitedIndex = new boolean[a.length];
        return isWay(a, 0, visitedIndex);
    }
    /**
     * Recursive step to check if there's a legal path
     * @param a Given array filled with real positive integers.
     * @param currentIndex  Integer which indicates the current location in the array.
     * @param visitedInde Additional array which keeps track of visited indexes.
     * @return If there is a valid path or now.
     */

    private static boolean isWay(int[] a, int currentIndex, boolean[] visitedIndex) {
        //if reached the last cell in the array return true
        if (currentIndex == a.length - 1) {
            return true;
        }
        //if jumped out of the array's boundries return false
        if (currentIndex < 0 || currentIndex >= a.length) {
            return false;
        }
        //if the index was already visited return false, in order to avoid creating infinte loop
        if (visitedIndex[currentIndex]) {
            return false;
        }
        visitedIndex[currentIndex] = true; //setting the current index as visited
        int nextIndex = currentIndex + a[currentIndex]; //promoting a jump to the right
        if (isWay(a, nextIndex, visitedIndex)) { //recursive case: checking if the next step is valid
            return true;
        }
        nextIndex = currentIndex - a[currentIndex]; //promoting a jump to the left
        if (isWay(a, nextIndex, visitedIndex)) { //recursive case: checking if the next step is valid
            return true;
        }
        visitedIndex[currentIndex] = false; //backtracking
        return false;
    }
    /**
     * This method returns the number of steps required in the shortest route of
     * a prince to a villain (represented by -1) which he wishes to fight in a square 2D array.
     * The prince may take a step to the north,south,east or west but NOT diagonally.
     * A legal step may be climbing 1 floor or going down 1 or 2 floors, otherwise he automatically fails.
     * When the prince is within 1 step from the villian, he may jump to him regardless to the villain's height.
     * @param drm the given square 2D array.
     * @param i the given row index.
     * @param j the given column index.
     * @return the number of steps in the shortest route or -1 if there isn't a valid route.
     */
    
    public static int prince(int[][] drm, int i, int j){
        int result = prince(drm,i,j,1,drm[i][j]);
        if (result == Integer.MAX_VALUE){
            return -1;
        }
        else
            return result;
    }
    /**
     * The recursive method for finding the shortest route to the villain.
     * @param drm The given square 2D array.
     * @param i The given row index.
     * @param j The given column index.
     * @param stepsCounter Counter for the number of steps made in a specific route.
     * @param height Variable that represents the height of the floor.
     * @return The number of steps required in the shortest route.
     */
    
    private static int prince(int[][] drm, int i, int j, int stepsCounter,int height){
        //base case to check if the prince does an illegal move
        if(i<0 || j<0 || i>drm.length -1 || j>drm.length -1 || drm[i][j] == Integer.MAX_VALUE
        || height > drm[i][j]+2 || drm[i][j] > height+1){
            return Integer.MAX_VALUE;
        }
        //checks if the prince is on the villain's roof.
        if(drm[i][j] == -1){
            return stepsCounter;
        }
        //checks if the villain is on a nearby roof.
        if(isVillainNext(drm,i,j)){
            return stepsCounter+1;
        }
        //temporary varible which holds he cell's value.
        int cellValue = drm[i][j];
        drm[i][j] = Integer.MAX_VALUE; //temporarly storing a high value in the cell.
        int case1 = prince(drm,i+1,j,stepsCounter+1,cellValue);
        int case2 = prince(drm,i,j+1,stepsCounter+1,cellValue);
        int case3 = prince(drm,i-1,j,stepsCounter+1,cellValue);
        int case4 = prince(drm,i,j-1,stepsCounter+1,cellValue);
        drm[i][j] = cellValue;
        //return the shortest route between the possible cases.
        return Math.min(Math.min(case1,case2),Math.min(case3,case4));
    }
    
    /**
     * This is a boolean method that checks if the prince is on a roof next to the villain's.
     * @param drm The given square 2D array.
     * @param i The given row index.
     * @pram j The given column index.
     * @return True if the prince is on a nearby roof, false otherwise.
     */
        private static boolean isVillainNext(int[][] drm, int i, int j)
    {
        if(( i+1<=drm.length -1 && drm[i+1][j]  == -1) || (i-1 >= 0 && drm[i-1][j] == -1) 
        || (j-1 >= 0 && drm[i][j-1] == -1) || (j+1 <= drm[0].length -1 && drm[i][j+1] == -1))
        {
            return true;
        }
        return false;
    }
}


    
