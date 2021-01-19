package firstPass;

public class Literal {
	private String literal;
	private int address;

	Literal(String lit,int lc){
		literal=lit;
		address=lc;
	}
	
	public int getAddress(){
		return address;
	}
	
	public String getLiteral(){
		return literal;
	}
}
