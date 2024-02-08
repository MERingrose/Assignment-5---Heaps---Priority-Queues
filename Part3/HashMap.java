package Part3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class HashMap<K, V> implements Map<K, V> {

    int numElements = 0, size;
    protected double loadFactor;
    final double DEFAULT_LOAD_FACTOR = 0.70;
    final int DEFAULT_SIZE = 11;
    ArrayList<V> hashMap;

    /*
     * creates a HashMap with the provided initial size and load factor.
     * 
     * @
     * 
     */
    public HashMap(int initialSize, double loadFactor) throws IllegalArgumentException {
        if (initialSize < 0 || loadFactor < 0.00 || loadFactor > 1.00)

            size = initialSize;
        this.loadFactor = loadFactor;
        hashMap = new ArrayList<V>(size);
    }

    /*
     * Creates a HashMap with a default capacity of 11 and load factor of 0.70
     * 
     */
    public HashMap() {
        this.size = DEFAULT_SIZE;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        hashMap = new ArrayList<V>(size);
    }

    /**
     * Returns the number of elements in the Hash Map
     */
    @Override
    public int size() {

        return numElements;
    }

    /**
     * returns true if the number of elements in the hash map is zero
     */
    @Override
    public boolean isEmpty() {

        return (numElements == 0);
    }

    @Override
    public boolean containsKey(Object key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'containsKey'");
    }

    @Override
    public boolean containsValue(Object value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'containsValue'");
    }

    @Override
    public V get(Object key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public V put(K key, V value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'put'");
    }

    @Override
    public V remove(Object key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'putAll'");
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    @Override
    public Set<K> keySet() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keySet'");
    }

    @Override
    public Collection<V> values() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'values'");
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'entrySet'");
    }

}
