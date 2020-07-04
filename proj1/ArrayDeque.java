import java.util.Objects;

public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private int nextFront;
    private int nextBack;
    private T[] array;

    private int addOne(int i) {
        return (i + 1) % this.array.length;
    }

    private int minusOne(int i) {
        return (i - 1 + this.array.length) % this.array.length;
    }

    /**
     *Creates an empty array deque, again the starting size should be 8
     */
    public ArrayDeque(){
        this.array = (T[]) new Object[8];
        this.size = 0;
        this.nextFront = 0;
        this.nextBack = 1;
    }

    /**
     * Expands array
     */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int tailSize = this.array.length - this.nextFront - 1;
        System.arraycopy(this.array, this.nextFront + 1, a, 0, tailSize);
        System.arraycopy(this.array, 0, a, tailSize, this.nextBack);
        this.array = a;
        this.nextFront = this.array.length - 1;
        this.nextBack = this.size;
    }

    /**
     * Adds an item of type T to the nextFront of the deque.
     */
    public void addFirst(T item) {
        if (this.size == this.array.length) {
            resize(this.array.length * 2);
        }
        this.array[this.nextFront] = item;
        this.nextFront = minusOne(this.nextFront);
        this.size++;
    }

    /**
     * Adds an item of type T to the nextBack of the deque.
     */
    public void addLast(T item) {
        if (this.size == this.array.length) {
            resize(this.array.length * 2);
        }
        this.array[this.nextBack] = item;
        this.nextBack = addOne(this.nextBack);
        this.size++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     * This method should be deleted and migrated to Deque.java
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return this.size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        int index = addOne(nextFront);
        for (int i = 0; i < this.size; i++) {
                System.out.print(this.array[index] + " ");
                index = addOne(index);
        }
        System.out.println(" ");
    }

    // delete after project is complete
    public void printWholeDeque() {
        for (int i = 0; i < this.array.length; i++) {
            System.out.print(this.array[i] + " ");
        }
        System.out.println();
    }

    /**
     * Shrinks array
     */
    private void shrink() {
        T[] a = (T[]) new Object[this.array.length/2];
        System.arraycopy(this.array, this.nextFront + 1, a,0, this.size);
        this.array = a;
        this.nextFront = this.array.length - 1;
        this.nextBack = this.size;
    }
    /**
     * Removes and returns the item at the nextFront of the deque.
     * If no such item exists, returns null
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
       } else {
            int newFront = addOne(this.nextFront);
            T frontValue = this.array[newFront];
            this.array[newFront] = null;
            size--;
            this.nextFront = newFront;
            if (this.array.length > 8 && this.size <= this.array.length * 0.25) {
                shrink();
            }
            return frontValue;
       }
    }

    /**
     * Removes and returns the item at the nextBack of the deque.
     * If no such item exists, returns null
     */
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        } else {
            int newBack = minusOne(this.nextBack);
            T backValue = this.array[newBack];
            this.array[newBack] = null;
            size--;
            this.nextBack = newBack;
            if (this.array.length > 8 && this.size <= this.array.length * 0.25) {
                shrink();
            }
            return backValue;
        }
    }

    /**
     * Gets the item at the given index, where 0 is the nextFront,
     * 1 is the next item, and so forth. If no such item exists,
     * returns null. Must not alter the deque
     */
    public T get(int index) {
        if (index >= this.size || index < 0) {
            return null;
        }
        int frontIndex = addOne(this.nextFront);
        int actualIndex = (frontIndex + index) % this.array.length;
        return this.array[actualIndex];
    }
}
