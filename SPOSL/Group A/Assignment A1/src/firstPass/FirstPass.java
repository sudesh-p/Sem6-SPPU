package firstPass;
import java.util.ArrayList;
import java.util.HashMap;

public class FirstPass {
	int lc=0,symbc=0,litc=0,temp_litc=0;
	HashMap<String, ArrayList<Integer>> symbolTable = new HashMap<>(); 
	HashMap<Integer,Literal> literalTable = new HashMap<>(); 
	HashMap<String,	Integer> tempLiteralTable = new HashMap<>(); 
	ArrayList<Integer> poolTable = new ArrayList<>();
	HashMap<Integer, String> intermediateCode=new HashMap<>(); 
	HashMap<Integer, ProgramLine> program;			
	Mnemonic mnemonic = new Mnemonic();

	void process(HashMap<Integer, ProgramLine> program) {
		this.program = program;
		normalize();
		for (ProgramLine i : program.values()) {
			System.out.println(i.get("label") + "\t" + i.get("opcode") + "\t"
			        + i.get("first operand") + "\t" + i.get("second operand"));
		}
		generateIntermediateCode();
	}

	void normalize() {
		for (ProgramLine curr : program.values()) {
			// Setting correct attributes for each line
			if (mnemonic.checkOpcode(curr.get("label").toLowerCase())) {
				curr.set("second operand", curr.get("first operand"));
				curr.set("first operand", curr.get("opcode"));
				curr.set("opcode", curr.get("label"));
				curr.set("label", "");
			}
			if (mnemonic.checkOpcode(curr.get("opcode").toLowerCase()))
				curr.set("opcode", curr.get("opcode").toLowerCase());
			if (mnemonic.checkRegister(curr.get("first operand").toLowerCase()))
				curr.set("first operand", curr.get("first operand")
				        .toLowerCase());
			if (mnemonic.checkRegister(curr.get("second operand").toLowerCase()))
				curr.set("second operand", curr.get("second operand")
				        .toLowerCase());
		}
	}
	
	public void generateIntermediateCode(){
		String opcode="",firstOp="",secondOp="",label="",ic="",temp="";
		int literal=0,ad_lc=1000;
		ArrayList<Integer>symbol,addr;
		Opcode op;
		Literal lit_container;
		poolTable.add(1);
		System.out.println("\nIntermediate Code\n");
		//DC,DS
		if(program.get(1).get("first operand")!=""){
			lc=Integer.parseInt(program.get(1).get("first operand"));
		}
		for (ProgramLine curr : program.values()) {
			//OPCODE
			opcode=curr.get("opcode");
			op=mnemonic.getMnemonic(opcode);
			ic=op.returnIC();
			
			//LABEL
			if((label=curr.get("label"))!=""&&opcode!="eq"){
				if(!symbolTable.containsKey(label)){
					symbc++;
					addr=new ArrayList<Integer>();
					addr.add(symbc);
					addr.add(lc);
					symbolTable.put(label,addr);
				}
				else {
					symbol=symbolTable.get(label);
					addr=new ArrayList<Integer>();
					addr.add(symbol.get(0));
					addr.add(lc);
					symbolTable.put(label,addr);
				}
			}
			
			if(!op.getType().equals("AD")||opcode.equals("end")) {
				
				//FIRST OPERAND
				firstOp=curr.get("first Operand");
				if(!firstOp.equals("")) {
					if((temp=specialFunctions(opcode,firstOp,ic))!=null) {
						ic=temp;
					}
					else {
						if(firstOp.contains("=")) {
							if(!tempLiteralTable.containsKey(firstOp)) {
								litc++;
								tempLiteralTable.put(firstOp,litc);
							}
							literal=tempLiteralTable.get(firstOp);
							ic=ic+" (L,"+literal+")";
						}
						else if(!mnemonic.checkRegister(firstOp)){
							if(!symbolTable.containsKey(firstOp)){
								symbc++;
								addr=new ArrayList<Integer>();
								addr.add(symbc);
								addr.add(lc);
								symbolTable.put(firstOp,addr);
							}
							symbol=symbolTable.get(firstOp);
							ic=ic+" (S,"+symbol.get(0)+")";
						}
						else if(mnemonic.checkRegister(firstOp)){
							ic=ic+" ("+mnemonic.getRegister(firstOp)+")";
						}
					}
				}
				
				//SECOND OPERAND
				if((secondOp=curr.get("second Operand"))!="") {
					if(secondOp.contains("=")) {
						if(secondOp.contains("=")) {
							if(!tempLiteralTable.containsKey(secondOp)) {
								litc++;
								tempLiteralTable.put(secondOp,litc);
							}
							literal=tempLiteralTable.get(secondOp);
							ic=ic+" (L,"+literal+")";
						}
					}
					else if(!mnemonic.checkRegister(secondOp)){
						if(!symbolTable.containsKey(secondOp)){
							symbc++;
							addr=new ArrayList<Integer>();
							addr.add(symbc);
							addr.add(lc);
							symbolTable.put(secondOp,addr);
						}
						symbol=symbolTable.get(secondOp);
						ic=ic+" (S,"+symbol.get(0)+")";
					}
					else if(mnemonic.checkRegister(secondOp)){
						ic=ic+" ("+mnemonic.getRegister(secondOp)+")";
					}
				}
			}
			
			//AD INSTRUCTIONS
			if(op.getType().equals("AD")){
				ic=adFunctions(opcode,curr,ic);
				if(opcode.equals("ltorg")||opcode.equals("end")) {
					ad_lc++;
					System.out.println(-1+" "+ic);
					intermediateCode.put(ad_lc,ic);
					for(String lit:tempLiteralTable.keySet()) {
						temp_litc++;
						lit_container=new Literal(lit,lc);
						literalTable.put(temp_litc, lit_container);
						ic="(DL,01) (C,"+lit.substring(2,lit.length()-1)+")";
						System.out.println(lc+" "+ic);
						intermediateCode.put(lc,ic);
						lc++;
					}
					tempLiteralTable.clear();
					if(opcode.equals("ltorg")){
						poolTable.add(litc+1);
					}
					continue;
				}
			}
			if(op.getType().equals("AD")){
				ad_lc++;
				System.out.println(-1+" "+ic);
				intermediateCode.put(ad_lc,ic);
			}
			else if(!op.getType().equals("AD")&&!opcode.equals("ds")) {
				System.out.println(lc+" "+ic);
				intermediateCode.put(lc,ic);
				lc++;
			}
			else if(opcode.equals("ds")) {
				System.out.println(lc+" "+ic);
				intermediateCode.put(lc,ic);
				lc=lc+Integer.parseInt(firstOp);
			}
		}
	}
	
	public String adFunctions(String opcode,ProgramLine line,String ic){
		String tempOp="",tempLab="",constant="";
		int index=0,addr=0;
		ArrayList<Integer> tempList,list;
		switch(opcode) {
		case "start":
			if(line.get("first Operand")!="") {
				ic=ic+" "+"(C,"+line.get("first Operand")+")";
			}
			return ic;
		
		case "end":
			return ic;
			
		case "origin":
		if((tempOp=line.get("first Operand"))!="") {
			if(specialChar(tempOp)) {
				if(tempOp.contains("+")) {
					index=tempOp.indexOf("+");
					constant=tempOp.substring(index);
					tempOp=tempOp.substring(0,index);
					addr=Integer.parseInt(constant.substring(1));
				}
				else if(tempOp.contains("-")) {
					index=tempOp.indexOf("-");
					constant=tempOp.substring(index);
					tempOp=tempOp.substring(0,index);
					addr=0-Integer.parseInt(constant.substring(1));
				}
			}
			tempList=symbolTable.get(tempOp);
			lc=tempList.get(1)+addr;
			ic=ic+" (S,"+tempList.get(0)+")"+constant;
		}
		return ic;
			
		case "equ":
			tempLab=line.get("label");
			tempOp=line.get("first Operand");
			if(specialChar(tempOp)) {
				if(tempOp.contains("+")) {
					index=tempOp.indexOf("+");
					constant=tempOp.substring(index);
					tempOp=tempOp.substring(0,index);
					addr=Integer.parseInt(constant.substring(1));
				}
				else if(tempOp.contains("-")) {
					index=tempOp.indexOf("-");
					constant=tempOp.substring(index);
					tempOp=tempOp.substring(0,index);
					addr=0-Integer.parseInt(constant.substring(1));
				}
			}
			tempList=symbolTable.get(tempOp);
			if(symbolTable.containsKey(tempLab)) {
				list=symbolTable.get(tempLab);
				symbc=list.get(0);
			}
			else {
				symbc++;
			}
			list=new ArrayList<Integer>();
			list.add(symbc);
			list.add((tempList.get(1)+addr));
			symbolTable.put(tempLab,list);
			ic=ic+" (S,"+tempList.get(0)+")"+constant;
			return ic;
		
		case "ltorg":
			return ic;
		
		default:
			return null;
		}
		
	}
	
	public String specialFunctions(String opcode,String firstOp,String ic){
		if(opcode.equals("dc")){
			ic=ic+" (C,"+firstOp.substring(1,firstOp.length()-1)+")";
		}
		else if(opcode.equals("ds")){
			ic=ic+" (C,"+firstOp+")";
		}
		else  if(opcode.equals("bc")){
			ic=ic+" ("+mnemonic.getCondition(firstOp)+")";
		}
		else {
			return null;
		}
		return ic;
	}

	public boolean specialChar(String operand) {
		if(operand.contains("+")||operand.contains("-")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void print(){
		Literal temp_lit;
		System.out.println("\nSymbol Table:\n");
		for(String i:symbolTable.keySet()){
			System.out.println(i+" "+symbolTable.get(i));
		}
		System.out.println("\nLiteral Table:\n");
		for(int i:literalTable.keySet()){
			temp_lit=literalTable.get(i);
			System.out.println(temp_lit.getLiteral()+" "+temp_lit.getAddress());
		}
		System.out.println("\nPool Table:\n");
		for(int i=0;i<poolTable.size();i++){
			System.out.println("#"+poolTable.get(i));
		}
	}
}
