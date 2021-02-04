import java.util.Scanner;


public class FCFS {
	private int n;
	private double avgtat,avgwt;
	Process p[];
	private Scanner sc=new Scanner(System.in);
	
	FCFS(int size){
		n=size;
		p=new Process[n];
	}
	
	public void getData(){
		for(int i=0;i<n;i++){
			p[i]=new Process();
			System.out.println("Enter Arrival time for process "+i+" :");
			p[i].at=sc.nextInt();
			System.out.println("Enter Bus time for process "+i+" :");
			p[i].bt=sc.nextInt();
		}
	}
	
	public void getCT(){
		p[0].ct=p[0].bt+p[0].at;
		for(int i=1;i<n;i++){
			if(p[i].at<=p[i-1].ct){
				p[i].ct=p[i].bt+p[i-1].ct;
			}
			else{
				p[i].ct=p[i].bt+p[i].at;
			}
		}
	}
	
	public void getTAT(){
		for(int i=0;i<n;i++){
			p[i].tat=p[i].ct-p[i].at;
		}
		for(int i=0;i<n;i++){
			avgtat=p[i].tat+avgtat;
		}
		avgtat=avgtat/n;
	}
	
	
	public void getWT(){
		for(int i=0;i<n;i++){
			p[i].wt=p[i].tat-p[i].bt;
		}
		for(int i=0;i<n;i++){
			avgwt=p[i].wt+avgwt;
		}
		avgwt=avgwt/n;
	}
	
	public void display(){
		System.out.println("Pno\tAT\tBT\tCT\tTAT\tWT");
		for(int i=0;i<n;i++){
			System.out.println(i+"\t"+p[i].at+"\t"+p[i].bt+"\t"+p[i].ct+"\t"+p[i].tat+"\t"+p[i].wt);
		}
		System.out.println("Average TAT: "+ avgtat);
		System.out.println("Average WT: "+ avgwt);
	}
	
	public void clear(){
		p=null;
	}
	
	public void driverFunc(){
		getData();
		getCT();
		getTAT();
		getWT();
		display();
		clear();
	}
}
