package sorting;

public class InformationSort {
    private final int comparisons;
    private final int swaps;
    private final long time;
    private final long memory;

    public InformationSort(int comparisons, int swaps, long time, long memory) {
        this.comparisons = comparisons;
        this.swaps = swaps;
        this.time = time;
        this.memory = memory;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getSwaps() {
        return swaps;
    }

    public long getTime() {
        return time;
    }

    public long getMemory() {
        return memory;
    }
}
