package module6.mediantracker;

import java.sql.SQLOutput;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Median tracker will keep track of median values of given integer inputs.
 *
 * Example:
 *  Input: 1 5 3 2 4
 *  Ordered: 1 2 3 4 5
 *  Median: 3
 *
 *  Input: 1 1 3 1
 *  Ordered: 1 1 1 3
 *  Median: (1+1)/2.0 = 1.0
 */
public class MedianTracker {
    // Keeps track of all the elements which are bigger than or equal to
    // top most element of the minHeap
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    // Keeps track of all the elements which are lesser than the
    // top most element of the minHeap
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return - Integer.compare(o1, o2);
        }
    });

    public void insert(int num) {
        // In the beginning both minHeap and maxHeap will be empty
        // so let's add the first element to minHeap
        if (minHeap.peek() == null) {
            minHeap.add(num); // Add the first element to maxHeap
            return;
        }
        // Subsequently, check if num is greater than the top most value
        // of minHeap, and store it in minHeap if it's so, cause all bigger
        // elements will be kept in minHeap
        if (num >= minHeap.peek()) {
            minHeap.add(num);
            // At this point in time we'll ensure that difference of number of elements
            // in maxHeap and minHeap are not more than 1, if it is, then we'll have
            // to take out the top element from the maxHeap and insert it into minHeap
            if (minHeap.stream().count() > maxHeap.stream().count() + 1) {
                maxHeap.add(minHeap.poll());
            }
        } else {
            // All smaller elements go maxHeap
            maxHeap.add(num);
            // The difference of two heaps can't be more than 1
            if (maxHeap.stream().count() > minHeap.stream().count() + 1) {
                minHeap.add(maxHeap.poll());
            }
        }
    }

    // In case of odd number it's the middle element
    // In case of even number of elements, it's the average of two numbers
    public double getMedian() {
        if (minHeap.stream().count() > maxHeap.stream().count()) {
            assert(minHeap.stream().count() == maxHeap.stream().count()+1);
            return minHeap.peek();
        } else if (minHeap.stream().count() < maxHeap.stream().count()) {
            assert(maxHeap.stream().count() == minHeap.stream().count()+1);
            return maxHeap.peek();
        } else {
            return ((minHeap.peek() + maxHeap.peek())/ 2.0);
        }
    }

    public static void printArray(Integer[] arr) {
        System.out.print("Input array: ");
        for (int x : arr) {
            System.out.print(x);
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void testMedianTracker() {
        MedianTracker mt = new MedianTracker();
        Integer [] arr = {1, 1, 3, 1, 7, 4, 5, -1, 8, 9, 9, 0};
        printArray(arr);
        System.out.print("Median after each input: ");
        for (Integer i : arr) {
            mt.insert(i);
            System.out.print(mt.getMedian());
            System.out.print(" ");
        }
        System.out.println();
        System.out.println();

        mt = new MedianTracker();
        arr = new Integer[] {1, 2, 3, 4};
        printArray(arr);
        System.out.print("Median after each input: ");
        for (Integer i : arr) {
            mt.insert(i);
            System.out.print(mt.getMedian());
            System.out.print(" ");
        }
        System.out.println();
        System.out.println();

        mt = new MedianTracker();
        arr = new Integer[] {5, 4, 3, 2, 1};
        printArray(arr);
        System.out.print("Median after each input: ");
        for (Integer i : arr) {
            mt.insert(i);
            System.out.print(mt.getMedian());
            System.out.print(" ");
        }
        System.out.println();
        System.out.println();

        mt = new MedianTracker();
        arr = new Integer[] {1, 1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6};
        printArray(arr);
        System.out.print("Median after each input: ");
        for (Integer i : arr) {
            mt.insert(i);
            System.out.print(mt.getMedian());
            System.out.print(" ");
        }
        System.out.println();
        System.out.println();
    }

    public static void main(String [] args) {
        testMedianTracker();
    }
}
