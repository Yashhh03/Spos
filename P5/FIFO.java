package tr;

import java.util.*;

public class FIFO{
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter the number of Pages: ");
		int noofPages=sc.nextInt();
		int pages[]=new int[noofPages];
		System.out.println("Enter the reference String: ");
		for(int i=0;i<noofPages;i++)
		{
			pages[i]=sc.nextInt();
		}
		
		System.out.println("Enter the Frames Size: ");
		int capacity=sc.nextInt();
		
		HashSet<Integer>frames=new HashSet<>(capacity);
		Queue<Integer>index=new LinkedList<>();
		int pageFaults=0,hits=0;
		
		for(int i=0;i<noofPages;i++)
		{
			if(frames.size()<capacity)
			{
				if(!frames.contains(pages[i]))
				{
					frames.add(pages[i]);
					index.add(pages[i]);
					pageFaults++;
					printPage(index);
				}
				else
				{
					hits++;
				}
			}
			else
			{
				if(!frames.contains(pages[i]))
				{
					int val=index.peek();
					index.poll();
					frames.remove(val);
					frames.add(pages[i]);
					index.add(pages[i]);
					pageFaults++;
					printPage(index);
				}
				else
				{
					hits++;
				}
			}	
		}System.out.println("Page Faults: "+pageFaults);
		System.out.println("Hits: "+hits);
		sc.close();
	}
	private static void printPage(Queue<Integer>index)
	{
		for(int j:index)
			System.out.print(j+" ");
		System.out.println();
	}
}