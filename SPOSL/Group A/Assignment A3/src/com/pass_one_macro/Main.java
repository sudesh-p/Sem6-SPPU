package com.pass_one_macro;

public class Main {
	public static void main(String[] args) {
		FileIO file=new FileIO();
		file.readFile();
		PassOne firstPass = new PassOne(file);
		firstPass.normalize();
		System.out.println("\nMDT:");
		firstPass.generateMDT();
		firstPass.printData();
	}
}
