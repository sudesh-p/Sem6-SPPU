import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
public class RoundRobin {
	private ArrayList<Process> pq=new ArrayList<Process>();
	private ArrayList<Process> aq=new ArrayList<Process>();
	private ArrayList<Process> completedProcess=new ArrayList<Process>();
	private int size,tq;
	private double avgtat,avgwt;
	Scanner sc=new Scanner(System.in);
	
	RoundRobin(){
		tq=size=0;
		avgtat=avgwt=0.0;
	}

	public void calculateCT(){
		int time=0,flag,temptq;
		Process temp;
		System.out.println(size);
		while(completedProcess.size()!=size){
			if(!aq.isEmpty()) {
			temp=aq.remove(0);
			flag=0;
			temptq=0;
				while(flag==0){
					temptq++;
					temp.pct--;
					time++;
					updateAQ(time);
					if(temp.pct!=0){
						if(temptq==tq){
							aq.add(temp);
							break;
						}
					}
					else{
						temp.ct=time;
						completedProcess.add(temp);
						flag=1;
					}
				}
			}
			else {
				time++;
				updateAQ(time);
			}
		}
	}
	
	public void updateAQ(int t){
		Process temp;
		//check for processes with arrival time = t in pq
		//transfer these processes to aq
		Iterator<Process>itr=pq.iterator();
		while (itr.hasNext()) { 
			temp=itr.next();
			if(temp.at<=t){
				aq.add(temp);
				itr.remove();
			}
        } 
	}
	
	public void getSnapshot(){
		char choice='y';
		int id=0,at=0,bt=0;
		System.out.println("Enter Time Quantum: ");
		tq=sc.nextInt();
		while(choice=='y'){
			System.out.println("Enter Process Id: ");
			id=sc.nextInt();
			System.out.println("Enter Process Arrival Time: ");
			at=sc.nextInt();
			System.out.println("Enter Process Burst Time: ");
			bt=sc.nextInt();
			Process p = new Process(id,at,bt);
			pq.add(p);
			size++;
			System.out.println("Enter Another? ");
			choice=sc.next().charAt(0);
		}
		Collections.sort(pq,Process.ATComparator);
		updateAQ(0);
	}
	
	public void calculateTAT(){
		Process temp;
		avgtat=0;
		Iterator<Process>itr=completedProcess.iterator();
		while (itr.hasNext()) { 
			temp=itr.next();
			temp.tat=temp.ct-temp.at;
			avgtat=avgtat+temp.tat;
        } 
		avgtat=avgtat/completedProcess.size();
	}
	
	
	public void calculateWT(){
		Process temp;
		Iterator<Process>itr=completedProcess.iterator();
		while (itr.hasNext()) { 
			temp=itr.next();
			temp.wt=temp.tat-temp.bt;
			avgwt=avgwt+temp.wt;
        } 
		avgwt=avgwt/completedProcess.size();
	}
	
	
	public void display(){
		Process temp;
		Iterator<Process>itr=completedProcess.iterator();
		System.out.println("Pno\tAT\tBT\tCT\tTAT\tWT");
		while (itr.hasNext()) { 
			temp=itr.next();
			System.out.println(temp.pid+"\t"+temp.at+"\t"+temp.bt+"\t"+temp.ct+"\t"+temp.tat+"\t"+temp.wt);
		}
		System.out.println("Average TAT: "+ avgtat);
		System.out.println("Average WT: "+ avgwt);
	}
	
	public void clear() {
		pq.clear();
		aq.clear();
		completedProcess.clear();		
	}
	
	public void driverFunc(){
		getSnapshot();
		calculateCT();
		calculateTAT();
		calculateWT();
		display();
		clear();
	}
}
