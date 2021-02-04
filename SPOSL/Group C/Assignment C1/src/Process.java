import java.util.Comparator;


public class Process {
	int pid,at,bt,ct,tat,wt,pct;
	
	Process(){
		pid=wt=tat=ct=bt=at=pct=0;
	}
	
	Process(int id,int a,int b,int p){
		pid=id;
		at=a;
		bt=b;
		ct=0;
		tat=0;
		wt=0;
		pct=p;
	}
	
	Process(int id,int a,int b){
		pid=id;
		at=a;
		bt=b;
		ct=0;
		tat=0;
		wt=0;
		pct=bt;
	}
	
	public void setTAT(int t){
		tat=t;
	}
	
	public void setWT(int w){
		wt=w;
	}
	
	public int getBT(){
		return bt;
	}
	
	public static Comparator<Process> BTComparator = new Comparator<Process>() {
		@Override
		public int compare(Process p0, Process p1) {
			if(p0.pct-p1.pct==0){
				return p0.at-p1.at;
			}
			return p0.pct-p1.pct;
		}};
		
	public static Comparator<Process> ATComparator = new Comparator<Process>() {
		@Override
		public int compare(Process p0, Process p1) {
			return p0.at-p1.at;
		}};		
}
