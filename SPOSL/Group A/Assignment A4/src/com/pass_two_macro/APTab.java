package com.pass_two_macro;

import java.util.HashMap;

public class APTab {
	HashMap<Integer,String>apt;
		
	APTab(){
		apt=new HashMap<>();
	}
		
	public void insert(int parameter_count,String parameter){
		apt.put(parameter_count,parameter);
	}
		
	public String get(int parameter){
		if(apt.containsKey(parameter)){
			return apt.get(parameter);
		}else{
			return "";
		}
	}
	
	public boolean contains(int parameter) {
		if(apt.containsKey(parameter)) {
			return true;
		}else{
			return false;
		}
	}
	
	public void print() {
		for (Integer param: apt.keySet()){
	        String value = apt.get(param);  
	        System.out.print(param + " " + value+"\n");  
		} 
	}
}
