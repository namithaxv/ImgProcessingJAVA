import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

class dHeapTester {
    private dHeap<Integer> maxHeap;
    private dHeap<Integer> minHeap;

    @BeforeEach
    public void setUp() {
        maxHeap = new dHeap<>(4, 10, true);
        minHeap = new dHeap<>(3, 10, false);
    }

    @Test
    public void testMaxHeapAdd() {
        // testing add and size method
        maxHeap.add(6);
        maxHeap.add(2);
        maxHeap.add(8);
        assertEquals(3, maxHeap.size());
        maxHeap.add(1);
        maxHeap.add(9);
        maxHeap.add(3);
        assertEquals(6, maxHeap.size());
        maxHeap.add(25);
        maxHeap.add(7);
        assertEquals(25, maxHeap.element());
        // adding null data
        Assertions.assertThrows(NullPointerException.class, () -> {
            maxHeap.add(null);
        });
    }

    @Test
    public void testMaxHeapRemove() {
        maxHeap.add(5);
        maxHeap.add(3);
        maxHeap.add(2);
        assertEquals(3, maxHeap.size());
        maxHeap.remove();
        assertEquals(3, maxHeap.element());
        maxHeap.clear();
        //removing from an empty heap
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            maxHeap.remove();
        });
        int[] array = new int[]{4, 5, 6, 7, 8};
        for (int i = 0; i < array.length; i++) {
            maxHeap.add(array[i]);
        }
        maxHeap.remove();
        assertEquals(7, maxHeap.element());
        maxHeap.remove();
        assertEquals(6, maxHeap.element());
        maxHeap.remove();
        assertEquals(5, maxHeap.element());
        maxHeap.remove();
        assertEquals(4, maxHeap.element());
        maxHeap.clear();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            maxHeap.remove();
        });

    }

    @Test
    public void clearTester() {
        maxHeap.add(5);
        maxHeap.add(3);
        maxHeap.add(2);
        maxHeap.add(7);
        assertEquals(4, maxHeap.size());
        maxHeap.clear();
        assertEquals(0, maxHeap.size());
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
        for (int i = 0; i < array.length; i++) {
            maxHeap.add(i);
        }
        assertEquals(7, maxHeap.size());
        maxHeap.clear();
        assertEquals(0, maxHeap.size());
    }

    @Test
    public void testMinHeapAdd() {
        int[] array = new int[]{1, 2, 3, 4, 5};
        for (int i = 0; i < array.length; i++) {
            minHeap.add(array[i]);
        }
        assertEquals(1, minHeap.element());
        minHeap.clear();
        assertEquals(0, minHeap.size());
        minHeap.add(8);
        minHeap.add(6);
        assertEquals(6, minHeap.element());
        minHeap.add(1);
        assertEquals(1, minHeap.element());
        minHeap.clear();
        Assertions.assertThrows(NullPointerException.class, () -> {
            minHeap.add(null);
        });

    }

    @Test
    public void testMinHeapRemove() {
        int[] array = new int[]{1, 2, 3, 4, 5};
        for (int i = 0; i < array.length; i++) {
            minHeap.add(array[i]);
        }
        minHeap.remove();
        assertEquals(2, minHeap.element());
        minHeap.remove();
        assertEquals(3, minHeap.element());
        minHeap.remove();
        assertEquals(4, minHeap.element());
        minHeap.clear();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            minHeap.remove();
        });
        int[] array2 = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        for (int i = 0; i < array.length; i++) {
            minHeap.add(array[i]);
        }
        minHeap.remove();
        assertEquals(2, minHeap.element());
        minHeap.clear();
        assertEquals(0, minHeap.size());
    }
}



