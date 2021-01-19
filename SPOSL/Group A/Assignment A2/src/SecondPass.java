import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class SecondPass {
	private int lc=0;
	private HashMap<Integer,ICline>machineCode = new HashMap<>();
	private FileIO file=new FileIO();
	private HashMap<String,String>symbolTable;
	private HashMap<String,String>literalTable;

	SecondPass(){
		symbolTable=file.getSymbols();
		literalTable=file.getLiterals();
	}
	
	public void getProgram() {
        String l, file;
        ICline line = new ICline();
        FileReader fileReader;
        BufferedReader bufferedReader;
        Scanner sc = new Scanner(System.in), tokenizer;
        file = "program.txt";
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            while ((l = bufferedReader.readLine()) != null) {
                tokenizer = new Scanner(l);
                line = new ICline();
                if(tokenizer.hasNext()) {
                	if((lc=tokenizer.nextInt())>=0){
                    line.setCode(tokenizer.next().substring(4,6));
                    if(tokenizer.hasNext()) {
                    	line=assignOperands(line,tokenizer.next());
                    	 if(tokenizer.hasNext()) {
                         	line=assignOperands(line,tokenizer.next());
                         }
                    }
                    machineCode.put(lc, line);
                }
            }
        } 
        }catch (FileNotFoundException e) {
            System.out.println("Error: " + file + " not found. File is either missing or not present in default directory.");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
    }
	
	public ICline assignOperands(ICline line, String op){
		int len;
		if(op.contains("S")){
			line.setOperand2(symbolTable.get(op.substring(3,(op.length()-1))));
			//Symbol
		}
		
		else if(op.contains("C")){
			if(line.getCode().equals("01")){
				line.setCode("00");
				line.setOperand2(op.substring(3,(op.length()-1)));
			}else if(line.getCode().equals("02")){
				line.setCode("| ");
				line.setOperand1(" ");
				line.setOperand2(" |");
				machineCode.put(lc, line);
				len=Integer.parseInt(op.substring(3,(op.length()-1)));
				for(int i=0;i<len-1;i++){
					line=new ICline();
					lc++;
					line.setCode("| ");
					line.setOperand1(" ");
					line.setOperand2(" |");
					machineCode.put(lc, line);
				}
			}
			//constant
		}
		
		else if(op.contains("L")){
			line.setOperand2(literalTable.get(op.substring(3,(op.length()-1))));
			//Literal
		}
		
		else{
			if(!line.getCode().equals("07"))
				line.setOperand1(op.substring(1,(op.length()-1)));
			//Register
		}
		return line;
	}
	
	public void display(){
		for(Integer i:machineCode.keySet()){
			System.out.print(i+" + ");
			machineCode.get(i).print();
			System.out.print("\n");
		}
	}
}
