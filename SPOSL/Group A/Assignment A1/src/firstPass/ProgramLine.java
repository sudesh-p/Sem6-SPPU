package firstPass;

public class ProgramLine {
	private String label;
	private String opcode;
	private String firstOperand;
	private String secondOperand;

	ProgramLine() {
		label = opcode = firstOperand = secondOperand = "";
	}

	ProgramLine(String label, String opcode, String firstOperand,String secondOperand) {
		this.label = label;
		this.opcode = opcode;
		this.firstOperand = firstOperand;
		this.secondOperand = secondOperand;
	}

	String get(String attribute) {
		switch (attribute.toLowerCase()) {
			case "label":
				return label;

			case "opcode":
				return opcode;

			case "first operand":
				return firstOperand;

			case "second operand":
				return secondOperand;

			default:
				return "Invalid attribute";
		}
	}

	Boolean set(String attribute, String value) {
		switch (attribute.toLowerCase()) {
			case "label":
				this.label = value;
				return Boolean.TRUE;

			case "opcode":
				this.opcode = value;
				return Boolean.TRUE;

			case "first operand":
				this.firstOperand = value;
				return Boolean.TRUE;

			case "second operand":
				this.secondOperand = value;
				return Boolean.TRUE;

			default:
				return Boolean.FALSE;
		}
	}
}