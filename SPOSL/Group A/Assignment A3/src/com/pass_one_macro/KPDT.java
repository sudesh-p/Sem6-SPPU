package com.pass_one_macro;

import java.util.HashMap;

public class KPDT {
	HashMap<Integer,HashMap<String,String>>kpdt;
	
	KPDT(){
		kpdt=new HashMap<>();
	}
	
	public void insert(String parameter,String value,int kpdtp){
		if(!kpdt.containsKey(kpdtp)){
			kpdt.put(kpdtp,new HashMap<String,String>());
		}
		kpdt.get(kpdtp).put(parameter, value);
	}
	
	public String get(String parameter,int pointer){
		if(kpdt.containsKey(pointer)){
			if(kpdt.get(pointer).containsKey(parameter)){
				return kpdt.get(pointer).get(parameter);
			}
			return "Invalid Parameter";
		}
		return "Invalid KPD Parameter";
	}
	
	public void print(){
		for (Integer count: kpdt.keySet()){
            HashMap<String,String>temp = kpdt.get(count);  
            System.out.print(count+" ");
        	for (String param: temp.keySet()){
                    String value = temp.get(param);  
                    System.out.print(param + " " + value+"\n");  
        	} 
        } 
	} 
}
