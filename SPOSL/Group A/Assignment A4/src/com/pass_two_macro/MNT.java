package com.pass_two_macro;

public class MNT {
	private String name;
	private int pp,kp,mdtp,kpdtp;
		
	MNT(){
		name="";
		pp=-1;
		kp=-1;
		mdtp=-1;
		kpdtp=-1;
	}
		
	MNT(String name,int pp,int kp,int kpdtp,int mdtp){
		this.name=name;
		this.pp=pp;
		this.kp=kp;
		this.kpdtp=kpdtp;
		this.mdtp=mdtp;
	}
		
	public void setName(String name){
		this.name=name;
	}
		
	public String getName(){
		return this.name;
	}
		
	public void set(String attribute, int value){
		switch(attribute.toLowerCase()){
		case "pp":
			this.pp=value;
			break;
			
		case "kp":
			this.kp=value;
			break;
				
		case "mdtp":
			this.mdtp=value;
			break;
			
		case "kpdtp":
			this.kpdtp=value;
			}
	}
		
	public int get(String attribute){
		switch(attribute.toLowerCase()){
		case "pp":
			return this.pp;
			
		case "kp":
			return this.kp;
				
		case "mdtp":
			return this.mdtp;
			
		case "kpdtp":
			return this.kpdtp;
				
		default:
			return -1;
		}
	}
	
	public void print(){
		System.out.println("name: "+name);
		System.out.println("pp: "+pp);
		System.out.println("kp: "+kp);
		System.out.println("mdtp: "+mdtp);
		System.out.println("kpdtp: "+kpdtp);
	}
}
