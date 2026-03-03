import java.util.Random;

public class Test {
    public static void main(String[] args) {
        int smallSize = 30;
        int largeSize = 50000;


        int[] smallArray = generateRandomArray(smallSize);
        int[] largeArray = generateRandomArray(largeSize);

        // Create three strategy objects
        SortingStrategy bubble = new BubbleSortStrategy();
        SortingStrategy selection = new SelectionSortStrategy();
        SortingStrategy insertion = new InsertionSortStrategy();

        // Test on small array
        System.out.println("===== Small Array (" + smallSize + " elements) =====");
        testStrategy(smallArray, bubble, "Bubble Sort");
        testStrategy(smallArray, selection, "Selection Sort");
        testStrategy(smallArray, insertion, "Insertion Sort");

        // Test on large array
        System.out.println("\n===== Large Array (" + largeSize + " elements) =====");
        testStrategy(largeArray, bubble, "Bubble Sort");
        testStrategy(largeArray, selection, "Selection Sort");
        testStrategy(largeArray, insertion, "Insertion Sort");
    }


    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000);
        }
        return arr;
    }


    private static void testStrategy(int[] original, SortingStrategy strategy, String name) {
        int[] copy = original.clone();
        long start = System.nanoTime();
        strategy.sort(copy);
        long end = System.nanoTime();
        long timeMs = (end - start) / 1_000_000;
        System.out.println(name + " took " + timeMs + " ms to sort " + original.length + " elements.");
    }
}