import java.util.Arrays;
import java.util.NoSuchElementException;

public class dHeap<T extends Comparable<? super T>> implements HeapInterface<T> {

    private T[] heap;   // backing array
    private int d;      // branching factor
    private int nelems; // number of elements
    private boolean isMaxHeap; // indicates whether heap is max or min
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DOUBLER = 2;

    /**
     * Initializes a binary max heap with capacity = 10
     */
    @SuppressWarnings("unchecked")
    public dHeap() {
        this(DOUBLER, DEFAULT_CAPACITY, true);
    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int heapSize) {
        this.d = DOUBLER;
        this.isMaxHeap = true;
        this.nelems = 0;
        this.heap = (T[]) new Comparable[heapSize];
    }

    /**
     * Initializes a d-ary heap (with a given value for d), with a given initial
     * capacity.
     *
     * @param d         The number of child nodes each node in the heap should have.
     * @param heapSize  The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     * @throws IllegalArgumentException if d is less than one.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap) throws IllegalArgumentException {
        if (d < 1) {
            throw new IllegalArgumentException();
        }
        this.d = d;
        this.isMaxHeap = isMaxHeap;
        this.nelems = 0;
        this.heap = (T[]) new Comparable[heapSize];
    }

    /**
     * returns the number of heap elements
     * @return number of heap elements
     * */
    @Override
    public int size() {
        return nelems;
    }

    /**
     * removes element from the heap
     * @throws NoSuchElementException if removing
     * from an empty heap
     * @return root
     * */

    @Override
    public T remove() throws NoSuchElementException {
        if (nelems == 0) {
            throw new NoSuchElementException();
        }
        T root = heap[0];
        heap[0] = heap[nelems - 1];
        heap[nelems - 1] = null;
        nelems--;
        trickleDown(0);
        return root;
    }

    /**
     * adds element to the heap
     * @param item the element to add to heap
     * @throws NullPointerException if adding
     * null data to heap
     * */

    @Override
    public void add(T item) throws NullPointerException {
        if (item == null) {
            throw new NullPointerException();
        }
        if (nelems == heap.length) {
            resize();
        }

        heap[nelems] = item;
        bubbleUp(nelems);
        nelems++;
    }

    /**
     * clears the heap
     * */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        heap = (T[]) new Comparable[DEFAULT_CAPACITY];
        nelems = 0;
    }

    /**
     * returns the root of the heap
     * @throws NoSuchElementException if heap is empty
     * @return root of the heap
     * */
    @Override
    public T element() throws NoSuchElementException {
        if (nelems == 0) {
            throw new NoSuchElementException();
        }
        return heap[0];
    }

    /**
     * helper method that finds the parent's index
     * @param index - the given index we need to find
     *              the parent of
     * @return int the parents index
     */
    private int parent(int index) {
        return (index - 1) / d;
    }

    /**
     * helper method that swaps indices
     *
     * @param idx1 - 1st index
     * @param idx2 - 2nd index
     */
    private void swap(int idx1, int idx2) {
        T temp = heap[idx1];
        heap[idx1] = heap[idx2];
        heap[idx2] = temp;

    }

    /**
     * helper method that moves elements up the tree
     * @param idx - the given index
     */
    private void bubbleUp(int idx) {
        if (idx > 0 && compareIndex(idx, parent(idx)) > 0) {
            swap(idx, parent(idx));
            //recusive call
            bubbleUp(parent(idx));
        }
    }

    /**
     * helper method that compares the nodes
     * @param idx1 - index of 1st node
     * @param idx2 - index of 2nd node
     * @return final comparison of the nodes
     */

    private int compareIndex(int idx1, int idx2) {
        if (isMaxHeap) { //bruh this took forever omg
            return -heap[idx2].compareTo(heap[idx1]);
        } else { //swaps the values before making comparison to satisfy
            //min heap property (does this work ASK TA) ????
            return heap[idx2].compareTo(heap[idx1]);
        }
    }


    /**
     * helper method that finds the parent's index
     *
     * @param idx - the given index we need to find
     *            the parent of
     */
    private void trickleDown(int idx) {
        // if isLeaf, return; - basically exit out of the code if it is a leaf

        if (!compareTo(idx)) { // basically inverse logic of compareTo
            int child1 = minChildFinder(idx);
            //swap
            swap(idx, child1);
            //recursive call
            trickleDown(child1);
        }
    }

    /**
     * helper method that compares node children
     * and checks max/min heaps
     *
     * @param idx index of the node we are comparing
     * @return true if max/min heap, false if not
     */

    private boolean compareTo(int idx) {
        for (int i = 0; i < d; i++) {
            // check if there are any more children to compare
            // return true if satisfies heap property;
            //no more children to compare
            int child = d*idx+1+i;
            boolean isLeaf = (child > nelems - 1);
            if(isLeaf){
                break;
            }

            // (max heap) check whether value at current node idx < value of
            // its children - if true, then violates maxheap property
            if (compareIndex(idx, d * idx + 1 + i) < 0) {
                return false;
            }
            //            if (d * idx + 1 + i >= nelems){
//                break;
//            }

        }
        return true;
    }

    /**
     * helper method that finds the index to swap
     * @param idx - the node's index
     * @return index of the child of max/min heap
     */
    private int minChildFinder(int idx) {
        int currChildIndex = d * idx + 1;
        //start at 1 since 1st child looked at in initialization
        for (int i = 1; i < d; i++) {
            if (d * idx + i + 1 < nelems
                    && compareIndex(d * idx + i + 1, currChildIndex) > 0) {
                currChildIndex = d * idx + 1 + i;
            }
        }
        return currChildIndex;
    }


    /**
     * helper method that doubles the size of
     * the array before adding if it is full
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = heap.length * DOUBLER;
        heap = Arrays.copyOf(heap, newCapacity);
    }

}
