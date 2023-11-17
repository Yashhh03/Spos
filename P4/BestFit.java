import java.util.Scanner;

public class BestFit {
    public static void bestFit(int[] blockSize, int m, int[] processSize, int n) {
        int[] allocation = new int[n];
        for (int i = 0; i < n; i++) {
            int bestIdx = -1;
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (bestIdx == -1 || blockSize[bestIdx] > blockSize[j]) {
                        bestIdx = j;
                    }
                }
            }

            if (bestIdx != -1) {
                allocation[i] = bestIdx;
                blockSize[bestIdx] -= processSize[i];
            }
        }

        System.out.println("\nProcess No.\tProcess Size\tBlock no.");
        for (int i = 0; i < n; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1)
                System.out.print(allocation[i] + 1);
            else
                System.out.print("Not Allocated");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of memory blocks: ");
        int m = scanner.nextInt();
        int[] blockSize = new int[m];

        System.out.println("Enter the memory block sizes:");
        for (int i = 0; i < m; i++) {
            blockSize[i] = scanner.nextInt();
        }

        System.out.println("Enter the number of processes: ");
        int n = scanner.nextInt();
        int[] processSize = new int[n];

        System.out.println("Enter the process sizes:");
        for (int i = 0; i < n; i++) {
            processSize[i] = scanner.nextInt();
        }

        scanner.close();

        System.out.println("\nBest Fit Memory Allocation \n");
        bestFit(blockSize, m, processSize, n);
    }
}
