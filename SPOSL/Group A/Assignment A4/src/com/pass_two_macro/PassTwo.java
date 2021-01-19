package com.pass_two_macro;

import java.util.HashMap;
import java.util.Scanner;

public class PassTwo {
	private HashMap<Integer, String> program,mdt,expansionCode;
	private int programSize,mdtSize;
	private HashMap<String,MNT> MNTable;
	private HashMap<String,APTab> APTable;
	private HashMap<Integer,KPDT>KPDTable;
	private FileIO file;
	
	PassTwo(FileIO file){
		APTable=new HashMap<>();
		expansionCode=new HashMap<>();
		this.file=file;
		init();
	}
	
	public void init() {
		program=file.returnProgram();
		mdt=file.returnMDT();
		programSize=file.returnProgramSize();
		mdtSize=file.returnMDTSize();
		MNTable=file.returnMNT();
		KPDTable=file.returnKPDT();
	}
	
	public void PassTwoProcess() {
		Scanner tokenizer;
		String temp="",macroName;
		HashMap<String,Integer>pnt;
		int param_count=-1;
		int count=0;
		for (int i = 1; i <= programSize; i++) {
			tokenizer = new Scanner(program.get(i));
			while(tokenizer.hasNext()){
				temp=tokenizer.next();
				if(MNTable.containsKey(temp)) {
					macroName=temp;
					pnt=file.getPNTable(macroName);
					APTab apt = new APTab();
					while(tokenizer.hasNext()){	
						temp=tokenizer.next();
						if(temp.contains("=")){
							String [] keyWordParam = temp.split("=");
							param_count=pnt.get(keyWordParam[0]);
							count++;
							if(param_count==-1) {
								System.out.println("Invalid Parameter Found");
								break;
							}else {
								apt.insert(param_count, keyWordParam[1]);
							}
						}else{
							if (temp.charAt(temp.length() - 1) == ',') {
								temp = temp.substring(0, temp.length() - 1);
							}
							count++;
							if(!apt.contains(count)) {
								apt.insert(count, temp);
							}else {
								apt.insert(++count, temp);
							}
						}
					}	
					APTable.put(macroName, apt);
					generateExpansionCode(macroName,apt);
				}
			}
		}
	}
	
	public void generateExpansionCode(String macroName,APTab apt) {
		String line,opcode,operand1,operand2;
		MNT mnt = MNTable.get(macroName);
		int param=0,mdtp=0,kpdtp=0;
		Scanner tokenizer;
		kpdtp = mnt.get("kpdtp");
		mdtp=mnt.get("mdtp");
		KPDT kpdt=KPDTable.get(kpdtp);
		
		for (int i = mdtp; i < mdtSize; i++) {
			if(mdt.get(i).toLowerCase().equals("mend")){
				break;
			}else {
				line=mdt.get(i);
				opcode = operand1 = operand2 = "";
				tokenizer = new Scanner(line);
				opcode=tokenizer.next();
				if(tokenizer.hasNext()) {
					operand1=tokenizer.next();
					if(operand1.contains("P")) {
						param=Integer.parseInt(operand1.substring(3, 4));
						if(apt.contains(param)) {
							operand1=apt.get(param);
						}else {
							operand1=kpdt.get(operand1, kpdtp);
						}
					}
					if(tokenizer.hasNext()) {
						operand2=tokenizer.next();
						if(operand2.contains("(P,")) {
							param=Integer.parseInt(operand2.substring(3, 4));
							if(apt.contains(param)) {
								operand2=apt.get(param);
							}else {
								operand2=kpdt.get(operand2, kpdtp);
							}
						}
					}
				}
			}
			setExpansionCode(opcode,operand1,operand2,i);
		}
	}
	
	public void setExpansionCode(String opcode,String operand1,String operand2,int i) {
		String line="";
		if(operand1!=""&&operand2!="") {
			line=opcode+" "+operand1+", "+operand2;
		}else if(operand1==""){
			line=opcode;
		}else if(operand1!=""&&operand2=="") {
			line=opcode+" "+operand2;
		}
		System.out.println("+ "+line);
		expansionCode.put(i, line);
	}
	
	public void printData() {
		System.out.println("\nActual Parameter Table: ");
		for (String temp: APTable.keySet()){
			APTable.get(temp).print();  
		} 
	}
}
