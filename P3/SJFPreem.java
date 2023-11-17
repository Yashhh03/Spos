import java.util.*;

public class SJFPreem{
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
        int f[]=new int[n];
        int k[]=new int[n];
        int st=0, tot=0;
        int temp;
        float avgwt=0, avgtat=0;
        
        
        for(int i=0;i<n;i++)
        {
            PID[i]=i+1;
            System.out.println("Enter process" + (i+1)+ " Arrival time: ");
            AT[i]=sc.nextInt();
            System.out.println("Enter process" + (i+1) + "Burst time: ");
            BT[i]=sc.nextInt();
            k[i]=BT[i];
            f[i]=0;
        }
        
        while(true)
        {
            int min=99,c=n;
            if(tot==n)
            break;
            
            for(int i=0;i<n;i++)
            {
                if((AT[i]<=st)&&(f[i]==0)&&(BT[i]<min))
                {
                    min=BT[i];
                    c=i;
                    
                }
            }
            if(c==n)
            st++;
            else
            {
                BT[c]--;
                st++;
                if(BT[c]==0)
                {
                    CT[c]=st;
                    f[c]=1;
                    tot++;
                }
            }
        }
        
        
        for(int i=0;i<n;i++)
        {
            
            TAT[i]=CT[i]-AT[i];
            WT[i]=TAT[i]-k[i];
            avgtat +=TAT[i];
            avgwt +=WT[i];
        }
        
        System.out.println("\nProcess\t\tArrival\t\tBurst\t\tCompilation\t\tTurnT\t\tWaitT");
        
        for(int i=0;i<n;i++)
        {
            System.out.println(PID[i] + "\t\t" + AT[i] + "\t\t" + k[i] + "\t\t" + CT[i] + "\t\t" + TAT[i] + "\t\t" + WT[i]);
        }
        sc.close();
        System.out.println("Average Wait time: " + (avgwt/n));
        System.out.println("Average Turn time: " + (avgtat/n));
    }
}
