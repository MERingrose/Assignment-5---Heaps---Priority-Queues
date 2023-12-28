package Part1;
import java.util.Iterator;

/**
 * ArrayBinaryTree implements the BinaryTreeADT interface using an array
 *
 * @author Java Foundations
 * @version 4.0
 */
public class ArrayBinaryTree<T> implements BinaryTreeADT<T>, Iterable<T> {
    private static final int DEFAULT_CAPACITY = 50;

    protected int count;
    protected T[] tree;
    protected int modCount;

    /**
     * Creates an empty binary tree.
     */
    @SuppressWarnings("unchecked")
    public ArrayBinaryTree() {
        count = 0;
        tree = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the element which will become the root of the new tree
     */
    @SuppressWarnings("unchecked")
    public ArrayBinaryTree(T element) {
        count = 1;
        tree = (T[]) new Object[DEFAULT_CAPACITY];
        tree[0] = element;
    }

    @Override
    public T getRootElement() {
        return tree[0];
    }


    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean contains(T targetElement) {
        return find(targetElement) != null;
    }

    @Override
    public T find(T targetElement) {
        for (int i = 0; i < count; i++) {
            if (targetElement.equals(tree[i])) {
                return tree[i];
            }
        }
        return null;
    }

    @Override
    public Iterator<T> iterator() {
      
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        
        throw new UnsupportedOperationException("Unimplemented method 'iteratorInOrder'");
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        
        throw new UnsupportedOperationException("Unimplemented method 'iteratorPreOrder'");
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        
        throw new UnsupportedOperationException("Unimplemented method 'iteratorPostOrder'");
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        
        throw new UnsupportedOperationException("Unimplemented method 'iteratorLevelOrder'");
    }
}