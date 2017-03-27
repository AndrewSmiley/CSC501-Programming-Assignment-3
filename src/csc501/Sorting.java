package csc501;

/**
 * Created by Andrew on 3/21/17.
 */

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

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
        int operations =0; // start tracking the number of operations
        //iterate through the end of the array
        for (int i = 1; i < array.length; i++){
            int k = array[i]; // store the element we are currently trying to find a home for
            int j= i-1; // store the current index we are on in our outer loop, i-1 because i is the index of the element we want
            // attempt to find a home for k
            while(j >= 0 && k< array[j]){
                operations++;
                //do the swapping
                array[j+1]=array[j];
                //decrement
                j--;
            }
            array[j+1]=k; //finally, give k a home
        }
        return operations; //return the number of operations the sorting took
    }

    /**
     * This is our bubble sort method. Takes an integer array and returns the number of operations in the sort. "Bubbles up"
     * the largest values
     * @param array the array to sort
     * @return int the number of operations to execute the sort
     */
    public static int bubbleSort(Integer[] array){
        int operations =0; //the number of operations in the array
        int n = array.length; //the length of the array and dynamic nuber of elements to sort
        int temp = 0; // the bubble item, instanciate outside of the loop
        boolean sorted = false; //boolean to determine whether the array is sorted
        //our outer loop. continue to iterate until we have a sorted array
        while(!sorted){
            sorted=true; //assume that this is the last pass
            for (int i = 0; i < n-1; i++) { //iterate through n-1, assuming that the eleemnt hit on the last pass was sorted
                //check to see if we need to bubble up
                operations++;//increaes operations because we do this check
                if (array[i + 1] < array[i]) {
                    temp = array[i]; //if we do, store the value we need to bubble
                    array[i] = array[i+1];// move the item that is < our current element to the position of our current element
                    array[i+1] = temp; //bubble the current element
                    sorted=false;//since we had to do a bubble, we can assume that it is not sorted
                }
            }
            n--; //decrease n because n+1 is already sorted
        }
        return operations; //return number of operations
    }

    /**
     * Our selection sort method, takes an array argument to sort and returns the number of operations
     * the sorting took
     * @param array the original array we wanted to sort
     * @return int the number of operations we want to
     */
    public static int selectionSort(Integer[] array){
        int operations =0; // start tracking the number of operations
        int swap; //this is our temporary storage value when we swap elements
        int min; // the smallest item we have found
        int minIndex; // the index of the smallest value
        //iterate through the n-2
        for(int i=0;i<array.length-1;i++)
        {
            //assume the arrray is already sorted and the first value in the array is the smallest
            swap=array[i]; //just in case save for swapping
            min = array[i]; // assign minimum value and minimum index
            minIndex = i;

            //look for the smallest item through n-1
            for(int k=i+1;k<array.length;k++){
                // since we are doing a comparison, increment the number of operations
                operations++;
                //see if the element at our inner index is < than the minimum index
                if(array[k]<min) {
                    // if it is, swap the value
                    min=array[k];
                    minIndex=k;   //update the minimum index
                }
            }

            //save the minimum
            array[i]=min;
            array[minIndex]=swap; //update with the old minimum value
        }

        return operations; //return the number of operations
    }

    /**
     * This is our merge sort merge method. This method will take the lhs and the rhs of the 'original' (pre-split) array and merge them
     * back into the original array in sorted order
     * @param array the original array we wanted to sort
     * @param left the left hand side of the orginal array to sort
     * @param operations the current operational count.
     * @param right the right hand side of the original array to sort
     */
    public static void merge(Integer[] array, Integer[] left, Integer [] right, AtomicInteger operations ){
        //set these all to the first index
        //these are all the starting indexes of the lhs, rhs and starting array respectively
        //since they are the starting index, set to 0
        int leftIndex = 0;
        int rightIndex = 0;
        int arrayIndex = 0;
        //while we haven't reached the end of one of the arrays
        while(leftIndex < left.length && rightIndex < right.length) {
            //if the left array index is less than the right array index
            //then place that index element in the orginal array
            //cool use of post-fix operators
            if(left[leftIndex] < right[rightIndex]){
                array[arrayIndex++] = left[leftIndex++];
                operations.addAndGet(1);//increment the number of operations the sorting took
            }
            else {
                //otherwise, put the right index element in that spot
                array[arrayIndex++] = right[rightIndex++];
                operations.addAndGet(1); //increment the number of operations the sorting took
            }
        }
        //clear out the remaining elements from each array, starting with the left, one of these will not be executed
        while(leftIndex < left.length){
            array[arrayIndex++] = left[leftIndex++]; //add the left value to the array and increase the index at each
            operations.addAndGet(1); //increment the number of operations the sorting took
        }

        //now the right
        while(rightIndex < right.length){
            array[arrayIndex++] = right[rightIndex++]; //add the right value to the array and increase the index at each
            operations.addAndGet(1); //increment the number of operations the sorting took
        }
    }

    /**
     * This method is our merge sort method. Takes an integer array argument to sort. Returns an AtomicInteger representing the
     * number of operations the sorting took. This method utilizes an AtomicInteger to count the number of operations in the overall sorting,
     * this is because in java i cannot pass an integer value by reference, thus I use an Atomic integer, as per this suggestion
     * http://stackoverflow.com/questions/3326112/java-best-way-to-pass-int-by-reference
     * @param array the array to sort
     * @param operations the current operational count. When first called, this should be 0
     * @return the number of operations the sorting took
     */
    public static AtomicInteger mergeSort(Integer[] array, AtomicInteger operations){
        //if  the length of the array, is greater than one, then we can split the array
        if(array.length>1) {
            //Split the array in half in two parts
            //create the new arrays first, then do an array copy to transfer the values over,
            Integer[] left = new Integer[array.length / 2];
            Integer[] right = new Integer[array.length - left.length];
            //now we do the actual copying
            //since we are using array copy, add the number of elements transferred to the number of operations
            System.arraycopy(array, 0, left, 0, left.length);
            operations.addAndGet(left.length);
            System.arraycopy(array, left.length, right, 0, right.length);
            operations.addAndGet(right.length);

            //recurse on the left and right hand sides
            mergeSort(left, operations); // assign the value of operations to the value returned by the method
            mergeSort(right, operations);// assign the value of operations to the value returned by the method
            //finally, merge the two split halves, which are sorted
            merge(array, left, right, operations); //return back to the caller
        }
        return  operations;

    }


    /**
     * This is our partition method to help us determine where our pivot point is located to be used in correlation with
     * QuickSort method. This method utilizes an AtomicInteger to count the number of operations in the overall sorting,
     * this is because in java i cannot pass an integer value by reference, thus I use an Atomic integer, as per this suggestion
     * http://stackoverflow.com/questions/3326112/java-best-way-to-pass-int-by-reference
     * @param array the original array we wanted to sort
     * @param left the low index around the pivot point
     * @param right the high index around the pivot point
     * @param operations the number of operations in the overall sort, again, this is an AtomicInteger for reference value
     * @return int the pivot point of the array
     */
    public static int partition(Integer[] array, int left, int right, AtomicInteger operations){
        int low = left; //copy our low index
        int high = right; // copy our high index
        int tmp; //this is our temporary storage location
        //get an arbitrary pivot value
        int pivot = array[(left + right) / 2];
        //run until our low index is < our high index
        while (low <= high) {
            //check to see if the element at our current low index is  < the value at our pivot index
            //if it's not, increment the low index and the number of operations, repeat
            while (array[low] < pivot){
                low++; //increase the value of the low index
                operations.addAndGet(1); //increase the value of
            }
           // check to see if the element at our current high index is > the value at our pivot index, if it's not
            //then decrease the value of the high index and increment the number of operations, repeat as necessary
            while (array[high] > pivot){
                high--; //decrement high value
                operations.addAndGet(1); //increment operational count
            }

            //if low index is <= to the high index, swap the elements at the locations and decrement the high index,
            //increment the low index
            if (low <= high) {
                tmp = array[low]; //store the low index value in a temporary variable
                array[low] = array[high];// assign the high index value to the low index
                array[high] = tmp; //put the low index value at the high index
                low++; //increment low
                high--; //decrement high
            }
        }

        return low; //return low, which is now our pivot value
    }

    /**
     * This is our quicksort method. First, it determines our pivot point and then conditionally recurse
     * @param array the array we are attempting to sort
     * @param low the low index of the pivot point
     * @param high the high index of hte pivot point
     * @return int the number of operations the sorting took
     */
    public static AtomicInteger quickSort(Integer[] array, int low, int high, AtomicInteger operations){
        //get our pivot point
        int pivot = partition(array, low, high, operations);
        //if the low index is less than our pivot point -1 as determined by our pivot method, then recurse for the left
        //side of the array
        if (low < pivot - 1){
            quickSort(array, low, pivot - 1, operations);
        }
        //if the pivot point is < high index, then recurse for the right hand side of the array
        if (pivot < high){
            quickSort(array, pivot, high, operations);
        }
        //reutn the number of operations the sorting took
        return operations;
    }

    /**
     * Simple validation function to determine if our sorting has been successful, I may or may not use this in the final
     * product
     * @param array the array to determine the validity of
     * @return boolean true if the array is valid, false otherwise
     */
    public static boolean validate(Integer[] array){
        //get the starting value
        int val = array[0];
        //iterate to the end of the array
        for (int i = 1; i < array.length; i++){
            //if the value is greate
            if(val > array[i]){
                return false;
            }
            val = array[i];
        }
        return true;
    }
}
