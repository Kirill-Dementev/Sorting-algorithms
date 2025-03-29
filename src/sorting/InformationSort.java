package sorting;

public class InformationSort {
    private final long comparisons;
    private final long swaps;
    private final long time;
    private final long memory;

    public InformationSort(long comparisons, long swaps, long time, long memory) {
        this.comparisons = comparisons;
        this.swaps = swaps;
        this.time = time;
        this.memory = memory;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }

    public long getTime() {
        return time;
    }

    public long getMemory() {
        return memory;
    }
}
