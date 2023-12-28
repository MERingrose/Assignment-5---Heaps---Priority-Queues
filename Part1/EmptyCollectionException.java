package Part1;
/**
 * EmptyCollectionException is thrown when the collection is empty
 * 
 */

public class EmptyCollectionException extends RuntimeException {

    public EmptyCollectionException() {
    }

    public EmptyCollectionException(String collection) {
        super("the " + collection + "is empty.");
    }

}
