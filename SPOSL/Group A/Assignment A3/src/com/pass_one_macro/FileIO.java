package com.pass_one_macro;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
public class FileIO {
	private HashMap<Integer, String> program;
	private int programSize;

	FileIO(){
		program = new HashMap<>();
		programSize=0;
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
			System.out
			        .println("Error: "
			                + file
			                + " not found. File is either missing or not present in default directory.");
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
}