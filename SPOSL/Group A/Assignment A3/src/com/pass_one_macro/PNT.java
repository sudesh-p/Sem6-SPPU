package com.pass_one_macro;

import java.util.HashMap;

public class PNT {
	HashMap<String,Integer>pnt;
	private int parameter_count;
	
	PNT(){
		parameter_count=0;
		pnt=new HashMap<>();
	}
	
	public void insert(String parameter){
		parameter_count++;
		pnt.put(parameter, parameter_count);
	}
	
	public int get(String parameter){
		if(pnt.containsKey(parameter)){
			return pnt.get(parameter);
		}else{
			return -1;
		}
	}
	
	public int getParameterCount() {
		return parameter_count;
	}
	
	public boolean contains(String parameter) {
		if(pnt.containsKey(parameter)) {
			return true;
		}
		return false;
	}
	
	public void print(){
		for (String param: pnt.keySet()){
            int count = pnt.get(param);  
            System.out.println(count + " " + param);  
		} 
	}
}
