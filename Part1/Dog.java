/**
 * Dummy class with non-comparable objects to demonstrate exception throwing
 * 
 * 
 * 
 */

package Part1;

public class Dog {
    private int age;
    private String name;

    public Dog() {
        this.age = 1;
        this.name = "Fido";

    }

    public Dog(String name, int age) {
        this.age = age;
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    public String bark() {
        return "Woof";
    }
}
