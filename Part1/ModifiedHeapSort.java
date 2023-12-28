package Part1;

import java.util.Arrays;

/**
 * Heapsort that builds array in place using existing array
 * 
 * @author Mark Ringrose - TRU ID: t00698437
 *         COMP 2231
 * 
 */
public class ModifiedHeapSort<T> {

    public void sort(T arr[]) throws IllegalArgumentException {
        if (!(arr[0] instanceof Comparable)) {
            throw new IllegalArgumentException("Type not Comparable");
        }
        int N = arr.length;

        // Build heap in array
        for (int i = N / 2 - 1; i >= 0; i--)
            heapify(arr, N, i);

        System.out.println(Arrays.toString(arr));
        // One by one extract elements from heap and put on the end
        for (int x = N - 1; x > 0; x--) {

            T temp = arr[0];
            arr[0] = arr[x];
            arr[x] = temp;

            // call heapify on the reduced heap
            heapify(arr, x, 0);
        }
    }

    /**
     * private method which recursively compares and rearranges array
     * 
     * @param arr  array to be heapified
     * @param N    number of elements in the array
     * @param root root element
     */
    @SuppressWarnings("unchecked")
    private void heapify(T arr[], int N, int root) {
        int largest = root; // Initialize largest as root
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        // If left child value is larger than root value set largest to left
        if (left < N && ((Comparable<T>) arr[left]).compareTo(arr[largest]) > 0)
            largest = left;

        // If right child is larger than largest set largest to right
        if (right < N && ((Comparable<T>) arr[right]).compareTo(arr[largest]) > 0)
            largest = right;

        // If largest is not the root swap the root with the largest
        if (largest != root) {
            T swap = arr[root];
            arr[root] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify sub-tree
            heapify(arr, N, largest);
        }
    }

}
