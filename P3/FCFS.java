import java.util.*;

public class FCFS{
    public static void main(String[] args){
        
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of process: ");
        int n=sc.nextInt();
        
        int PID[]=new int[n];
        int AT[]=new int[n];
        int BT[]=new int[n];
        int CT[]=new int[n];
        int TAT[]=new int[n];
        int WT[]=new int[n];
        int temp;
        float avgwt=0, avgtat=0;
        
        
        for(int i=0;i<n;i++)
        {
            System.out.println("Enter process" + (i+1)+ " Arrival time: ");
            AT[i]=sc.nextInt();
            System.out.println("Enter process" + (i+1) + "Burst time: ");
            BT[i]=sc.nextInt();
            PID[i]=i+1;
        }
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n-(i+1);j++)
            {
                if(AT[j]>AT[j+1])
                {
                    temp=AT[j];
                    AT[j]=AT[j+1];
                    AT[j+1]=temp;
                    temp=BT[j];
                    BT[j]=BT[j+1];
                    BT[j+1]=temp;
                    temp=PID[j];
                    PID[j]=PID[j+1];
                    PID[j+1]=temp;
                }
            }
        }
        
        
        for(int i=0;i<n;i++)
        {
            if(i==0)
            {
                CT[i]=AT[i]+BT[i];
            }
            else{
                if(AT[i]>CT[i-1])
                {
                    CT[i]=AT[i]+BT[i];
                }
                else
                {
                    CT[i]=CT[i-1]+BT[i];
                }
            }
            TAT[i]=CT[i]-AT[i];
            WT[i]=TAT[i]-BT[i];
            avgtat +=TAT[i];
            avgwt +=WT[i];
        }
        
        System.out.println("\nProcess\t\tArrival\t\tBurst\t\tCompilation\t\tTurnT\t\tWaitT");
        
        for(int i=0;i<n;i++)
        {
            System.out.println(PID[i] + "\t\t" + AT[i] + "\t\t" + BT[i] + "\t\t" + CT[i] + "\t\t" + TAT[i] + "\t\t" + WT[i]);
        }
        sc.close();
        System.out.println("Average Wait time: " + (avgwt/n));
        System.out.println("Average Turn time: " + (avgtat/n));
    }
}
