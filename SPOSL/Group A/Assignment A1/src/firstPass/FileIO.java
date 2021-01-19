package firstPass;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class FileIO {
	private HashMap<Integer, ProgramLine> program;

	public void getProgram() {
        int lineNo = 1;
        String l, file;
        ProgramLine line = new ProgramLine();
        FileReader fileReader;
        BufferedReader bufferedReader;
        Scanner sc = new Scanner(System.in), tokenizer;

        program = new HashMap<>();
        System.out.print("Enter name of file : ");
        file = sc.next();
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            while ((l = bufferedReader.readLine()) != null) {
                tokenizer = new Scanner(l);
                line = new ProgramLine();
                if(tokenizer.hasNext()) {
                    line.set("label", tokenizer.next());
                    if(tokenizer.hasNext()) {
                        line.set("opcode", tokenizer.next().replace(",", ""));
                        if(tokenizer.hasNext()) {
                            line.set("first operand", tokenizer.next().replace(",", ""));
                            if(tokenizer.hasNext()) {
                                line.set("second operand", tokenizer.next());
                            }
                        }
                    }
                }
                program.put(lineNo, line);
                lineNo += 1;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + file + " not found. File is either missing or not present in default directory.");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
    }
	
	public HashMap<Integer, ProgramLine> returnProgram(){
		return program;
	}
}
