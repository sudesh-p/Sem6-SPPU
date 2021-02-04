import java.util.Scanner;


public class Scheduling {
	public static void main(String[]argv){
		Scanner sc=new Scanner(System.in);
		int choice=0;
		do{
			System.out.println("\n\nMenu\n\n1.FCFS\n\n2.SJF\n\n3.Priority\n\n4.Round Robin\n\n0.Exit\n\nOption: ");
			choice=sc.nextInt();
			switch(choice){
				case 1:
					System.out.println("Enter number of processes: ");
					int size=sc.nextInt();
					FCFS fcfs=new FCFS(size);
					fcfs.driverFunc();
				break;
				
				case 2:
					SJF sjf=new SJF();
					sjf.driverFunc();
				break;
				
				case 3:
					Priority p=new Priority();
					p.driverFunc();
				break;
				
				case 4:
					RoundRobin rr=new RoundRobin();
					rr.driverFunc();
				break;
					
				case 0:
					sc.close();
				break;
			}
		}while(choice!=0);
	}
}
