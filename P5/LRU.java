package tr;

import java.util.*;

public class LRU{
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
		HashMap<Integer,Integer>index=new HashMap<>();
		int pageFaults=0,hits=0;
		
		for(int i=0;i<noofPages;i++)
		{
			if(frames.size()<capacity)
			{
				if(!frames.contains(pages[i]))
				{
					frames.add(pages[i]);
					pageFaults++;
					index.put(pages[i],i);
				}
				else
				{
					hits++;
					index.put(pages[i], i);
				}
			}
			else
			{
				if(!frames.contains(pages[i]))
				{
					int LRU=Integer.MAX_VALUE;
					int val=Integer.MIN_VALUE;
					Iterator<Integer>itr=frames.iterator();
					while(itr.hasNext())
					{
						int temp=itr.next();
						if(index.get(temp)<LRU)
						{
							LRU=index.get(temp);
							val=temp;
						}
					}
					frames.remove(val);
					frames.add(pages[i]);
					pageFaults++;
					index.put(pages[i],i);
				}
				else
				{
					hits++;
					index.put(pages[i], i);
				}
			}
			frames.forEach(System.out::print);
			System.out.println();
		}
		System.out.println("Page Faults: "+pageFaults);
		System.out.println("Hits: "+hits);
		sc.close();
	}
}