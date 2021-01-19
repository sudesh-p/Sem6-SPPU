package com.pass_one_macro;
import java.util.HashMap;
import java.util.Scanner;

public class PassOne {
	private HashMap<Integer, String> program,mdt;
	private int programSize;
	private HashMap<String,MNT> mnTable;
	private HashMap<String,PNT> pnTable;
	private KPDT kpdtab;
	
	PassOne(FileIO file){
		program=file.returnProgram();
		programSize=file.returnProgramSize();
		mdt=new HashMap<>();
		mnTable = new HashMap<>(); 
		pnTable = new HashMap<>();
		kpdtab = new KPDT();
		mdt=new HashMap<>();
	}
	
	public void normalize() {
		String opcode, operand1, operand2,macroName,temp;
		Scanner tokenizer,sc=new Scanner(System.in);
		int flag=0,kpdtp=51,kp=0,mdtp=1;
		
		for (int i = 1; i <= programSize; i++) {
			opcode = operand1 = operand2 = "";
			if(program.get(i).toLowerCase().equals("macro")){
				opcode="macro";
				kp=0;
				flag=1;
			}else if(flag==1){
				tokenizer = new Scanner(program.get(i));
				PNT pnt = new PNT();
				macroName=tokenizer.next();
				while(tokenizer.hasNext()){
					if((temp=tokenizer.next()).contains("=")){
						kp++;
						String [] keyWordParam = temp.split("=");
						kpdtab.insert(keyWordParam[0],keyWordParam[1], kpdtp);
						pnt.insert(keyWordParam[0]);
					}else{
						if (temp.charAt(temp.length() - 1) == ',') {
							temp = temp.substring(0, temp.length() - 1);
						}
						pnt.insert(temp);
					}
				}
				MNT mnt=new MNT(macroName,(pnt.getParameterCount()-kp),kp,kpdtp,mdtp);
				mnTable.put(macroName, mnt);
				pnTable.put(macroName, pnt);
				kpdtp=kpdtp+kp;
				opcode=macroName;
				flag=0;
			}else{
				tokenizer = new Scanner(program.get(i));
				opcode = tokenizer.next();
				if (tokenizer.hasNext()) {
					operand1 = tokenizer.next();
					// Removing comma at the end
					if (operand1.charAt(operand1.length() - 1) == ',') {
						operand1 = operand1.substring(0, operand1.length() - 1);
					}
					operand1 = " " + operand1;
					if (tokenizer.hasNext()) {
						operand2 = tokenizer.next();
						operand2 = " " + operand2;
					}
				}
				mdtp++;
			}
			program.put(i, opcode + operand1 + operand2);
		}
		sc.close();
	}
	
	public void generateMDT() {
		String macroName,line,opcode,operand1,operand2;
		int param=0,mdtp=0;
		Scanner tokenizer;
		PNT pnt;
		for (int i = 1; i < programSize; i++) {
			if(program.get(i).toLowerCase().equals("macro")){
				i++;
				macroName=program.get(i);
				pnt = pnTable.get(macroName);
				while(!program.get(i).toLowerCase().equals("mend")){
					line = program.get(i);
					opcode = operand1 = operand2 = "";
					tokenizer = new Scanner(line);
					opcode=tokenizer.next();
					if(tokenizer.hasNext()) {
						operand1=tokenizer.next();
						if(pnt.contains(operand1)) {
							param=pnt.get(operand1);
							operand1="(P,"+param+")";
						}
						if(tokenizer.hasNext()) {
							operand2=tokenizer.next();
							if(pnt.contains(operand2)) {
								param=pnt.get(operand2);
								operand2="(P,"+param+")";
							}
						}
					}
					if(line!=macroName&&line.toLowerCase()!="macro") {
						mdtp++;
						setMDTLine(mdtp,opcode,operand1,operand2);
					}
					i++;
				}
				mdtp++;
				setMDTLine(mdtp,"MEND","","");
			}
		}
	}
	
	public void setMDTLine(int i,String opcode,String operand1,String operand2) {
		String line="";
		if(operand1!=""&&operand2!="") {
			line=opcode+" "+operand1+", "+operand2;
		}else if(operand1==""){
			line=opcode;
		}else if(operand1!=""&&operand2=="") {
			line=opcode+" "+operand2;
		}
		System.out.println(i+" "+line);
		mdt.put(i, line);
	}		
	
	public void printData() {
		System.out.println("\nMacro Name Table: ");
		for (String temp: mnTable.keySet()){
			mnTable.get(temp).print();  
		} 
		System.out.println("\nParameter Name Table: ");
		for (String temp: pnTable.keySet()){
			pnTable.get(temp).print();  
		} 
		System.out.println("\nKeyword Parameter Table: ");
		kpdtab.print();
	}
}