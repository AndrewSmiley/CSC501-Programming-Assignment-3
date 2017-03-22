package csc501;

/**
 * Created by Andrew on 3/21/17.
 */

/**
 * This class contains all of our sorting functions, insertion, bubble, selection, merge and quick sorts
 * All the methods are static and take an integer array argument which allows us to execute the search without
 * needing to instanciate the class. I may go ahead and move all of these to the main class
 */
public class Sorting {
    /**
     * This is our insertion sort method
     * @param array
     */
    public static void insertionSort(int[] array){
        for (int i = 1; i < array.length; i++){
            int k = array[i]; // store the element we are currently trying to find a home for
            int j= i-1; // store the current index we are on in our outer loop, i-1 because i is the index of the element we want
            // attempt to find a home for k
            while(j >= 0 && k< array[j]){
                //do the swapping
                array[j+1]=array[j];
                //decrement
                j--;
            }
            array[j+1]=k; //finally, give k a home
        }
    }
}
