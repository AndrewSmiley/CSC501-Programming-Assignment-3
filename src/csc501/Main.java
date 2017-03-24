package csc501;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
	// write your code here
        /**
         a. Insertion
         b. Bubble
         c. Selection
         d. Merge
         e. Quick
         */
        //this is our array of elements from the text file
        Integer numbers[] = null;
        //the number of items in the text file
        int size = 0;

        //attempt to open the file
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input1.txt")));
            //reference for our line
            String line;

            //the line count
            int lineCount = 0;
            while ((line = br.readLine()) != null) {

                if (lineCount == 0) {
                    size = Integer.parseInt(line);
                    numbers = new Integer[size];
                    lineCount++;

                } else {
                    //just add to numbers the line count. This is lineCount-2 because line 1 is the number of lines in the
                    //file
                    numbers[lineCount - 1] = Integer.parseInt(line);
                    lineCount++;
                }
            }

            //close the file
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Sorting.bubbleSort(numbers);
//        for(int i : numbers){
//            System.out.println(i);
//        }

        System.out.println("The size is: "+size);
        System.out.println("Array Validity: "+ (Sorting.validate(numbers) ? "Valid" :"Invalid"));
    }


}
