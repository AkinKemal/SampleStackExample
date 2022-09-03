import java.util.ArrayList;

public final class VectorStack<T> implements StackInterface<T> {

    private ArrayList<T> stack; // Last element is the top entry in stack
    private boolean initialized = false;
    private static final int DEFAULT_INITIAL_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;
    public int counter;

    public VectorStack() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public VectorStack(int initialCapacity) {
        checkCapacity(initialCapacity);

        //your implementation
        stack = new ArrayList<T>();
        counter = -1;

        initialized = true;
    }

    private void checkCapacity(int desiredCapacity) {
        if (desiredCapacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a stack " + "whose capacity exceeds " + "allowed maximum.");
    }

    public void push(T newEntry) {

        // your implementation
        if (counter <= MAX_CAPACITY) {
            stack.add(newEntry);
            counter++;
        }
    }

    public T pop() {
        //checkInitialization();
        if (!isEmpty()) {
            //your implementation
            T data = stack.get(counter);
            stack.remove(counter);
            counter--;
            return data;
        } else {
            System.out.println("Silinecek eleman yok!");
            return null;
        }
    }

    public T peek() {
        //your implementation
        if (!isEmpty()) {
            T data = stack.get(counter);
            return data;
        } else {
            return null;
        }
    }

    public boolean isEmpty() {

        //your implementation
        return counter == -1;
    } // end isEmpty

    public void clear() {
        stack.clear();
    }


    public int getSize() {
        return counter + 1;
    }

    public String toString() {

        String result = "Stack[ ";

        for (int index = 0; index < stack.size(); index++) {
            result += stack.get(index) + " ";
        }

        result += "]*Top*";
        return result;
    }
}