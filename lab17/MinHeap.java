import java.util.ArrayList;
import java.util.NoSuchElementException;

/* A MinHeap class of Comparable elements backed by an ArrayList. */
public class MinHeap<E extends Comparable<E>> {

    /* An ArrayList that stores the elements in this MinHeap. */
    private ArrayList<E> contents;
    private int size;
    // TODO: YOUR CODE HERE (no code should be needed here if not 
    // implementing the more optimized version)

    /* Initializes an empty MinHeap. */
    public MinHeap() {
        contents = new ArrayList<>();
        contents.add(null);
    }

    /* Returns the element at index INDEX, and null if it is out of bounds. */
    private E getElement(int index) {
        if (index >= contents.size()) {
            return null;
        } else {
            return contents.get(index);
        }
    }

    /* Sets the element at index INDEX to ELEMENT. If the ArrayList is not big
       enough, add elements until it is the right size. */
    private void setElement(int index, E element) {
        while (index >= contents.size()) {
            contents.add(null);
        }
        contents.set(index, element);
    }

    /* Swaps the elements at the two indices. */
    private void swap(int index1, int index2) {
        E element1 = getElement(index1);
        E element2 = getElement(index2);
        setElement(index2, element1);
        setElement(index1, element2);
    }

    /* Prints out the underlying heap sideways. Use for debugging. */
    @Override
    public String toString() {
        return toStringHelper(1, "");
    }

    /* Recursive helper method for toString. */
    private String toStringHelper(int index, String soFar) {
        if (getElement(index) == null) {
            return "";
        } else {
            String toReturn = "";
            int rightChild = getRightOf(index);
            toReturn += toStringHelper(rightChild, "        " + soFar);
            if (getElement(rightChild) != null) {
                toReturn += soFar + "    /";
            }
            toReturn += "\n" + soFar + getElement(index) + "\n";
            int leftChild = getLeftOf(index);
            if (getElement(leftChild) != null) {
                toReturn += soFar + "    \\";
            }
            toReturn += toStringHelper(leftChild, "        " + soFar);
            return toReturn;
        }
    }

    /* Returns the index of the left child of the element at index INDEX. */
    private int getLeftOf(int index) {
        return 2 * index;
    }

    /* Returns the index of the right child of the element at index INDEX. */
    private int getRightOf(int index) {
        return 2 * index + 1;
    }

    /* Returns the index of the parent of the element at index INDEX. */
    private int getParentOf(int index) {
        return index / 2;
    }

    /* Returns the index of the smaller element. At least one index has a
       non-null element. If the elements are equal, return either index. */
    private int min(int index1, int index2) {
        if (getElement(index1) == null) return index2;
        if (getElement(index2) == null) return index1;
        if (getElement(index1).compareTo(getElement(index2)) < 0) {
            return index1;
        }
        return index2;
    }

    /* Returns but does not remove the smallest element in the MinHeap. */
    public E findMin() {
        return getElement(1);
    }

    /* Bubbles up the element currently at index INDEX. */
    private void bubbleUp(int index) {
        swap(index, getParentOf(index));
    }

    /* Bubbles down the element currently at index INDEX. */
    private void bubbleDown(int index) {
        int minIndex = min(getLeftOf(index), getRightOf(index));
        swap(index, minIndex);
    }

    /* Returns the number of elements in the MinHeap. */
    public int size() {
        return this.size;
    }

    /* Inserts ELEMENT into the MinHeap. If ELEMENT is already in the MinHeap,
       throw an IllegalArgumentException.*/
    public void insert(E element) {
        setElement(size + 1, element);
        int currentIndex = size + 1;
        E parent = getElement(getParentOf(currentIndex));
        while (parent != null && element.compareTo(parent) < 0) {
            bubbleUp(currentIndex);
            currentIndex = getParentOf(currentIndex);
//            System.out.println("====> currentIndex " + currentIndex);
            parent = getElement(getParentOf(currentIndex));
        }
        this.size++;
    }

    /* Returns and removes the smallest element in the MinHeap. */
    public E removeMin() {
        E prevMin = findMin();
        E lastEl = getElement(this.size);
        setElement(1, lastEl);
        this.contents.remove(this.size);
        int currentIndex = 1;
        E minChild = getElement(min(getLeftOf(currentIndex), getRightOf(currentIndex)));
        while (minChild != null && getElement(currentIndex).compareTo(minChild) > 0) {
            bubbleDown(currentIndex);
            currentIndex = min(getLeftOf(currentIndex), getRightOf(currentIndex));
            minChild = getElement(min(getLeftOf(currentIndex), getRightOf(currentIndex)));
        }
        if (this.size == 0) return prevMin;
        this.size--;
        return prevMin;
    }

    /* Replaces and updates the position of ELEMENT inside the MinHeap, which
       may have been mutated since the initial insert. If a copy of ELEMENT does
       not exist in the MinHeap, throw a NoSuchElementException. Item equality
       should be checked using .equals(), not ==. */
    public void update(E element) {
        for (int i = 0; i <= this.contents.size(); i++) {
            if (this.contents.get(i).equals(element)) {
                this.contents.set(i, element);
                return;
            }
        }

        throw new NoSuchElementException();
    }

    /* Returns true if ELEMENT is contained in the MinHeap. Item equality should
       be checked using .equals(), not ==. */
    public boolean contains(E element) {
        for (int i = 1; i <= this.contents.size(); i++) {
            if (this.contents.get(i).equals(element)) {
                return true;
            }
        }

        return false;
    }
}
