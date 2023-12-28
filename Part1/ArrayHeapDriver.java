package Part1;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * ArrayHeapDriver exercises the ArrayHeap and HeapSort Classes
 * 
 */

public class ArrayHeapDriver {

    public static void main(String[] args) {

        // instantiate a random object
        Random rando = new Random();

        // instantiate two valid arrays with Integer and String objects
        Integer[] intArr = new Integer[20];
        String[] strArr = new String[] { "hello", "my", "name", "is", "mickeljohn", "do", "you", "have", "a",
                "nickel" };

        // instantiate an invalid array of Dog objects
        Dog[] dogArr = new Dog[4];

        // fill dog array
        dogArr[0] = new Dog("Baker", 9);
        dogArr[1] = new Dog("cat", 9);
        dogArr[2] = new Dog("Zeus", 2);
        dogArr[3] = new Dog();

        // fill Integer array
        for (int i = 0; i < 20; i++) {
            intArr[i] = rando.nextInt(200);
        }

        // print out integer array
        System.out.println(Arrays.toString(intArr));

        // instantiate ModifiedHeapSort objects for each data type
        ModifiedHeapSort<Dog> dogHeapSort = new ModifiedHeapSort<Dog>();
        ModifiedHeapSort<Integer> modHeapSort = new ModifiedHeapSort<Integer>();
        ModifiedHeapSort<String> strModHeapSort = new ModifiedHeapSort<String>();

        // sort array - dogArr is wrapped in try-catch block
        modHeapSort.sort(intArr);
        strModHeapSort.sort(strArr);
        try {
            dogHeapSort.sort(dogArr);
        } catch (Exception e) {
            System.out.println(e);
        }

        // print out sorted valid arrays
        System.out.println(Arrays.toString(intArr));
        System.out.println(Arrays.toString(strArr));
    }

}
