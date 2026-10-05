public class SumMillionMonitor {

    public static class SumAccumulator {
        private long totalSum = 0;

        // Método sincronizado que actúa como monitor
        public synchronized void addPartialSum(long partialSum) {
            this.totalSum += partialSum;
        }

        public synchronized long getTotalSum() {
            return this.totalSum;
        }
    }

    // Hilo trabajador
    public static class WorkerThread extends Thread {
        private final long start;
        private final long end;
        private final SumAccumulator accumulator;

        public WorkerThread(long start, long end, SumAccumulator accumulator) {
            this.start = start;
            this.end = end;
            this.accumulator = accumulator;
        }

        @Override
        public void run() {
            long partialSum = 0;
            for (long i = start; i <= end; i++) {
                partialSum += i;
            }
            accumulator.addPartialSum(partialSum);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long N = 1_000_000;
        int numThreads = 4;
        long Size = N / numThreads;

        SumAccumulator accumulator = new SumAccumulator();
        WorkerThread[] threads = new WorkerThread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            long start = i * Size + 1;
            long end = (i == numThreads - 1) ? N : (i + 1) * Size;

            threads[i] = new WorkerThread(start, end, accumulator);
            threads[i].start();
        }

        for (WorkerThread thread : threads) {
            thread.join();
        }

        // 3. Mostrar el resultado final acumulado
        System.out.println("Suma total de los primeros " + N + " números: " + accumulator.getTotalSum());
    }
}