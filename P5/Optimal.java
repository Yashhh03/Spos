package tr;

import java.util.*;

public class Optimal{
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
		
		HashSet<Integer>frames=new HashSet<>();
		HashMap<Integer,Integer>index=new HashMap<>();
		int pageFaults=0,hits=0;
		
		for(int i=0;i<noofPages;i++)
		{
			if(frames.size()<capacity)
			{
				if(!frames.contains(pages[i]))
				{
					pageFaults++;
					frames.add(pages[i]);
					
					int farthest=nextIndex(pages,i);
					index.put(pages[i],farthest);
				}
				else
				{
					hits++;
					index.put(pages[i], nextIndex(pages,i));
				}
			}
			else
			{
				if(!frames.contains(pages[i]))
				{
					int farthest=-1;
					int val=0;
					Iterator<Integer>itr=frames.iterator();
					while(itr.hasNext())
					{
						int temp=itr.next();
						if(index.get(temp)>farthest)
						{
							farthest=index.get(temp);
							val=temp;
						}
					}
					frames.remove(val);
					index.remove(farthest);
					frames.add(pages[i]);
					pageFaults++;
					int nextUse=nextIndex(pages,i);
					index.put(pages[i],nextUse);
				}
				else
				{
					hits++;
					index.put(pages[i], nextIndex(pages,i));
				}
			}
			frames.forEach(System.out::print);
			System.out.println();
		}
		System.out.println("Page Faults: "+pageFaults);
		System.out.println("Hits: "+hits);
		sc.close();
	}
	
	public static int nextIndex(int pages[],int curIndex)
	{
		int farthest=curIndex;
		int currentPage=pages[curIndex];
		int j=farthest;
		for(j=farthest+1;j<pages.length;j++)
		{
			if(pages[j]==currentPage)
			{
				farthest=j;
				return farthest;
			}
		}
		return Integer.MAX_VALUE;
	}
}