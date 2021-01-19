package com.pass_two_macro;
public class Main {
	public static void main(String[] args) {
		FileIO file=new FileIO();
		file.readFile();
		file.readMNT();
		file.readMDT();
		file.readKPDTable();
		PassTwo secondPass = new PassTwo(file);
		secondPass.init();
		System.out.println("Expansion Code: ");
		secondPass.PassTwoProcess();
		secondPass.printData();
	}
}
