package firstPass;
public class Opcode {
	String type="",mnemonic="";

	Opcode(String t,String m){
		type=t;
		mnemonic=m;
	}
	
	public String getType(){
		return type;
	}
	
	public String getMnemonic(){
		return mnemonic;
	}
	
	public String returnIC(){
		 return ("("+this.getType()+","+this.getMnemonic()+")");
	}
}

