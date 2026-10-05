public class fibonacci extends Thread {
    private final int n;

    
    public fibonacci(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        if (n <= 0) {
            System.out.println("N must be greater than 0.");
            return;
        }

        System.out.println("First " + n + " Fibonacci number(s):");

        long prev = 1;
        long curr = 1;

        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                System.out.print(prev);
            } else if (i == 2) {
                System.out.print(", " + curr);
            } else {
                long next = prev + curr;
                System.out.print(", " + next);
                prev = curr;
                curr = next;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int N = 10; 
        
        fibonacci thread = new fibonacci(N);
        thread.start();
    }
}