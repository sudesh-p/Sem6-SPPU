public class ICline {
	private String code;
	private String operand1;
	private String operand2;
	
	
	
	ICline(){
		code="00";
		operand1="0";
		operand2="000";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOperand1() {
		return operand1;
	}

	public void setOperand1(String register) {
		this.operand1 = register;
	}

	public String getOperand2() {
		return operand2;
	}

	public void setOperand2(String value) {
		this.operand2 = value;
	}
	
	public void print(){
		System.out.print(" "+code+" "+operand1+" "+operand2);
	}
}
