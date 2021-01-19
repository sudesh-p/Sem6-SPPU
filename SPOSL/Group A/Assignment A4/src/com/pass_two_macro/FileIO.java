package com.pass_two_macro;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class FileIO {
	private HashMap<Integer, String> program,mdt;
	private HashMap<Integer,KPDT> KPDTable;
	private HashMap<String,MNT> MNTable;
	private int programSize,mdtSize;

	FileIO(){
		program = new HashMap<>();
		MNTable=new HashMap<>();
		KPDTable=new HashMap<>();
		mdt=new HashMap<>();
		mdtSize=0;
		programSize=0;
	}
	
	public void readMNT() {
		String file="mnt.txt",line;
		String [] mntValues;
		BufferedReader bufferedReader;
		MNT mnt=null;
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			while ((line = bufferedReader.readLine()) != null) {
				if(line.toLowerCase().contains("name")) {
					mnt=new MNT();
					mntValues = line.split("=");
					if(mntValues[0].toLowerCase().equals("name")) {
						mnt.setName(mntValues[1]);
					}
					while((line = bufferedReader.readLine()) != null&&!line.toLowerCase().contains("name")){
						mntValues = line.split("=");
						mnt.set(mntValues[0].toLowerCase(),Integer.parseInt(mntValues[1]));
					}
					MNTable.put(mnt.getName(), mnt);
				}
			}
		}catch (FileNotFoundException fnfe) {
			System.out.println("Error: " + file + " not found. File is missing.");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readFile() {
		String file, s;
		int lineNo = 0;
		Scanner sc = new Scanner(System.in);
		BufferedReader bufferedReader;

		System.out.print("Enter name of file : ");
		file = sc.next();
		
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			while ((s = bufferedReader.readLine()) != null) {
				program.put(++lineNo, s);
			}
			programSize = lineNo;
			sc.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("Error: "+ file + " not found. File is missing.");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	public void readMDT() {
		String file="mdt.txt", line;
		int lineNo = 0;
		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			while ((line = bufferedReader.readLine()) != null) {
				String [] mdtLine=line.split(" ");
				mdt.put(Integer.parseInt(mdtLine[0]),setMDTLine(mdtLine));
				lineNo++;
			}
			mdtSize = lineNo;
		} catch (FileNotFoundException fnfe) {
			System.out
			        .println("Error: "
			                + file
			                + " not found. File is missing.");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	public String setMDTLine(String [] temp) {
		String line="";
		for(int i=1;i<temp.length;i++) {
			if(temp[i].charAt(temp[i].length()-1)==',') {
				temp[i] = temp[i].substring(0, temp[i].length() - 1);
			}
			if(i!=temp.length-1) {
				line=line+temp[i]+" ";
			}else {
				line=line+temp[i];
			}
			
		}
		return line;
	}
	
	public HashMap<String,Integer> getPNTable(String macroName) {
		String file=macroName+".txt",line;
		HashMap<String,Integer> pnt=new HashMap<>();
		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			while ((line = bufferedReader.readLine()) != null) {
				String [] params=line.split(" ");
				pnt.put(params[1],Integer.parseInt(params[0]));
			}
		} catch (FileNotFoundException fnfe) {
			System.out
			        .println("Error: "
			                + file
			                + " not found. File is missing");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pnt;
	}
	
	public void readKPDTable() {
		String file="kpdt.txt",line;
		BufferedReader bufferedReader;
		KPDT kpdt=null;
		int kpdtp=0;
		
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			while ((line = bufferedReader.readLine()) != null) {
				String [] params=line.split(" ");
				if(!params[2].isEmpty()) {
					kpdt=new KPDT();
					kpdtp=Integer.parseInt(params[0]);
				}
				kpdt.insert(params[1], params[2], kpdtp);
				if(kpdt!=null) {
					KPDTable.put(kpdtp,kpdt);
				}
			}
		} catch (FileNotFoundException fnfe) {
			System.out
			        .println("Error: "
			                + file
			                + " not found. File is missing");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<Integer,String> returnProgram(){
		return program;
	}
	
	public int returnProgramSize(){
		return programSize;
	}
	
	public HashMap<Integer,String> returnMDT(){
		return mdt;
	}
	
	public int returnMDTSize(){
		return mdtSize;
	}
	
	public HashMap<String,MNT> returnMNT(){
		return MNTable;
	}
	
	public HashMap<Integer,KPDT> returnKPDT(){
		return KPDTable;
	}
}
