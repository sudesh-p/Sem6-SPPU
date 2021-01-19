package firstPass;
import java.util.HashMap;

public class Mnemonic {
	HashMap<String, Opcode> Mnemonomap = new HashMap<>();
	HashMap<String, String> regMnemonic = new HashMap<>(); 
	HashMap<String, String> condCode = new HashMap<>();
	
	Mnemonic(){
		//	AD
		Opcode ad= new Opcode("AD","01");
		Mnemonomap.put("start", ad);
		ad = new Opcode("AD","02");
		Mnemonomap.put("end", ad);
		ad = new Opcode("AD","03");
		Mnemonomap.put("origin", ad);
		ad = new Opcode("AD","04");
		Mnemonomap.put("equ", ad);
		ad = new Opcode("AD","05");
		Mnemonomap.put("ltorg", ad);
		//	DL
		Opcode dl= new Opcode("DL","01");
		Mnemonomap.put("dc", dl);
		dl = new Opcode("DL","02");
		Mnemonomap.put("ds", dl);
		//	IS
		Opcode is= new Opcode("IS","00");
		Mnemonomap.put("stop", is);
		is= new Opcode("IS","01");
		Mnemonomap.put("add", is);
		is= new Opcode("IS","02");
		Mnemonomap.put("sub", is);
		is= new Opcode("IS","03");
		Mnemonomap.put("mult", is);
		is= new Opcode("IS","04");
		Mnemonomap.put("mover", is);
		is= new Opcode("IS","05");
		Mnemonomap.put("movem", is);
		is= new Opcode("IS","06");
		Mnemonomap.put("comp", is);
		is= new Opcode("IS","07");
		Mnemonomap.put("bc", is);
		is= new Opcode("IS","08");
		Mnemonomap.put("div", is);
		is= new Opcode("IS","09");
		Mnemonomap.put("read", is);
		is= new Opcode("IS","10");
		Mnemonomap.put("print", is);
		// REGISTERS
		regMnemonic.put("areg","1");
		regMnemonic.put("breg","2");
		regMnemonic.put("creg","3");
		regMnemonic.put("dreg","4");
		//	CONDITIONS
		condCode.put("LT", "1");
		condCode.put("LE", "2");
		condCode.put("EQ", "3");
		condCode.put("GT", "4");
		condCode.put("GE", "5");
		condCode.put("ANY","6");
	}
	
	public Opcode getMnemonic(String key){
		if(Mnemonomap.containsKey(key)){
			return Mnemonomap.get(key);
		}
		else{
			return null;
		}
	}
	
	public boolean checkOpcode(String key){
		if(Mnemonomap.containsKey(key)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean checkRegister(String key){
		if(regMnemonic.containsKey(key)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String getRegister(String key){
		if(regMnemonic.containsKey(key)){
			return regMnemonic.get(key);
		}
		else{
			return null;
		}
	}
	
	public String getCondition(String key){
		if(condCode.containsKey(key)){
			return condCode.get(key);
		}
		else{
			return null;
		}
	}
}
