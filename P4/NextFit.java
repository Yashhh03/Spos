import java.util.Arrays;
import java.util.Scanner;

public class NextFit {

    public static void NextFit(int[] blockSize, int m, int[] processSize, int n) {
        int[] allocation = new int[n];
        int j = 0;

        Arrays.fill(allocation, -1);

        for (int i = 0; i < n; i++) {
            int count = 0;
            while (count < m) {
                if (blockSize[j] >= processSize[i]) {
                    allocation[i] = j;
                    blockSize[j] -= processSize[i];
                    break;
                }
                j = (j + 1) % m;
                count++;
            }
        }

        System.out.println("\nProcess No.\tProcess Size\tBlock no.");
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1) {
                System.out.print(allocation[i] + 1);
            } else {
                System.out.print("Not Allocated");
            }
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

        System.out.println("\nNext Fit Memory Allocation \n");
        NextFit(blockSize, m, processSize, n);
    }
}
