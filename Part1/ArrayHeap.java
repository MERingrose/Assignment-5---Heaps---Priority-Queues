package Part1;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * ArrayHeap provides an array implementation of a minheap.
 * 
 * @author Java Foundations
 * @version 4.0
 */
public class ArrayHeap<T> extends ArrayBinaryTree<T> implements HeapADT<T> {
    /**
     * Creates an empty heap.
     */
    public ArrayHeap() {
        super();
    }


    /**
     * Adds the specified element to this heap in the appropriate
     * position according to its key value.
     *
     * @param obj the element to be added to the heap
     */
    public void addElement(T obj) {
        if (count == tree.length)
            expandCapacity();
        tree[count] = obj;
        count++;
        modCount++;
        if (count > 1)
            heapifyAdd();
    }

    /**
     * private method to expand capacity of array should it be required.
     */
    @SuppressWarnings("unchecked")
    private void expandCapacity() {
        int newSize = tree.length * 2;
        T[] newTree = (T[]) new Object[newSize];
        System.arraycopy(tree, 0, newTree, 0, tree.length);
        tree = newTree;
    }

    /**
     * Reorders this heap to maintain the ordering property after
     * adding a node.
     */
    @SuppressWarnings("unchecked")
    private void heapifyAdd() {
        T temp;
        int next = count - 1;
        temp = tree[next];
        while ((next != 0) && (((Comparable<T>) temp).compareTo(tree[(next - 1) / 2]) < 0)) {
            tree[next] = tree[(next - 1) / 2];
            next = (next - 1) / 2;
        }
        tree[next] = temp;
    }

    /**
     * Remove the element with the lowest value in this heap and
     * returns a reference to it. Throws an EmptyCollectionException if
     * the heap is empty.
     *
     * @return a reference to the element with the lowest value in this heap
     * @throws EmptyCollectionException if the heap is empty
     */
    public T removeMin() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("ArrayHeap");
        T minElement = tree[0];
        tree[0] = tree[count - 1];
        heapifyRemove();
        count--;
        modCount--;
        return minElement;
    }

    /**
     * Reorders this heap to maintain the ordering property
     * after the minimum element has been removed.
     */
    @SuppressWarnings("unchecked")
    private void heapifyRemove() {

        T temp;
        int node = 0;
        int left = 1;
        int right = 2;
        int next;

        if ((tree[left] == null) && (tree[right] == null))
            next = count;
        else if (tree[right] == null)
            next = left;
        else if (((Comparable<T>) tree[left]).compareTo(tree[right]) < 0)
            next = left;
        else
            next = right;
        temp = tree[node];

        while ((next < count) && (((Comparable<T>) tree[next]).compareTo(temp) < 0)) {
            tree[node] = tree[next];
            node = next;
            left = 2 * node + 1;
            right = 2 * (node + 1);
            if ((tree[left] == null) && (tree[right] == null))
                next = count;
            else if (tree[right] == null)
                next = left;
            else if (((Comparable<T>) tree[left]).compareTo(tree[right]) < 0)
                next = left;
            else
                next = right;
        }
        tree[node] = temp;
    }

    public T getRootElement() {
        if (isEmpty())
            throw new EmptyCollectionException("ArrayHeap");
    
        return tree[0];
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }
    
    public int size() {
        return count;
    }
    
    public boolean contains(T targetElement) {
        return find(targetElement) != null;
    }
    
    @SuppressWarnings("unchecked")
    public T find(T targetElement) {
        T result = null;
    
        for (int i = 0; i < count && result == null; i++) {
            if (((Comparable<T>) targetElement).compareTo(tree[i]) == 0) {
                result = tree[i];
            }
        }
    
        return result;
    }
    
    public Iterator<T> iterator() {
        return iteratorLevelOrder();
    }
    
    public Iterator<T> iteratorInOrder() {
        throw new UnsupportedOperationException("This heap does not support in-order traversal");
    }
    
    public Iterator<T> iteratorPreOrder() {
        throw new UnsupportedOperationException("This heap does not support pre-order traversal");
    }
    
    public Iterator<T> iteratorPostOrder() {
        throw new UnsupportedOperationException("This heap does not support post-order traversal");
    }
    
    public Iterator<T> iteratorLevelOrder() {
        ArrayList<T> tempList = new ArrayList<T>();
    
        for (int i = 0; i < count; i++) {
            tempList.add(tree[i]);
        }
    
        return tempList.iterator();
    }
    
    public T findMin() {
        return getRootElement();
    }
}
