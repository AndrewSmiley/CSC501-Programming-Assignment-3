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
     * This is our insertion sort method, we pass in the Integer array because this is java, we are working with passing by reference
     * the array will be sorted when this method is complete
     * @param array the array to sort
     * @return int the number of the operations the sorting took
     */
    public static int insertionSort(Integer[] array){
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
        return 0;
    }

    /**
     * This is our bubble sort method. Takes an integer array and returns the number of operations in the sort
     * @param array the array to sort
     * @return int the number of operations to execute the sort
     */
    public static int bubbleSort(Integer[] array){
        int n = array.length; //the length of the array
        int temp = 0; // the bubble item
        //our outer loop. iterate through the array from 0=>n-1
        for (int i = 0; i < n; i++) {
            /*our inner loop which allows us to bubble up the values
             *the n-i part decrements on each pass of the outer loop because on each completion of the inner loop
             * n-i is in the correct position
             */
            for (int j = 1; j < (n - i); j++) {
                //check to see if we need to bubble up
                if (array[j - 1] > array[j]) {
                    temp = array[j - 1]; //if we do, store the value we need to bubble
                    array[j - 1] = array[j];// move the item that is < our current element to the position of our current element
                    array[j] = temp; //bubble the current element
                }
            }
        }
        return 0;
    }

    /**
     * Our selection sort method
     * @param array
     * @return
     */
    public static int selectionSort(Integer[] array){

        return 0;
    }


    public static boolean validate(Integer[] array){
        boolean valid = true;
        int val = array[0];
        for (int i = 1; i < array.length; i++){
            if(val > array[i]){
                return false;
            }
        }
        return true;
    }
}
