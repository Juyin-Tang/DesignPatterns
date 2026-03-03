public class SortContext {
    private SortingStrategy strategy;


    public SortContext(SortingStrategy strategy) {
        this.strategy = strategy;
    }


    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }


    public void sortArray(int[] array) {
        if (strategy == null) {
            throw new IllegalStateException("No strategy set.");
        }
        strategy.sort(array);
    }
}