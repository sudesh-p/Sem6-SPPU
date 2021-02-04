import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
public class Priority {
	private ArrayList<Process> pq=new ArrayList<Process>();
	private ArrayList<Process> aq=new ArrayList<Process>();
	private ArrayList<Process> completedProcess=new ArrayList<Process>();
	private int size;
	private double avgtat,avgwt;
	Scanner sc=new Scanner(System.in);
		
	Priority(){
		size=0;
		avgtat=avgwt=0.0;
	}

	public void calculateCT(){
		int time=0;
		Process temp;
		while(completedProcess.size()!=size){
			if(!aq.isEmpty()) {
				temp=aq.remove(0);
				time=time+temp.bt;
				temp.ct=time;
				completedProcess.add(temp);
				updateAQ(time);
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
		//sort aq
		Collections.sort(aq,Process.BTComparator);
	}
		
	public void getSnapshot(){
		char choice='y';
		int id=0,at=0,bt=0,pt=0;
		while(choice=='y'){
			System.out.println("Enter Process Id: ");
			id=sc.nextInt();
			System.out.println("Enter Process Arrival Time: ");
			at=sc.nextInt();
			System.out.println("Enter Process Burst Time: ");
			bt=sc.nextInt();
			System.out.println("Enter Process Priority: ");
			pt=sc.nextInt();
			Process p = new Process(id,at,bt,pt);
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
			System.out.println("Pno\tAT\tBT\tCT\tPT\tTAT\tWT");
			while (itr.hasNext()) { 
				temp=itr.next();
				System.out.println(temp.pid+"\t"+temp.at+"\t"+temp.bt+"\t"+temp.ct+"\t"+temp.pct+"\t"+temp.tat+"\t"+temp.wt);
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
