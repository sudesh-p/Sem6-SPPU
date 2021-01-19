import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class FileIO {
	private HashMap<String,String>symbolTable;
	private HashMap<String,String>literalTable;
	
	FileIO(){
		symbolTable=new HashMap<>();
		literalTable=new HashMap<>();
	}
	
	public HashMap<String,String> getSymbols() {
        String l, file,no="",addr = "";
        FileReader fileReader;
        BufferedReader bufferedReader;
        Scanner sc = new Scanner(System.in), tokenizer;
        file="symbol.txt";
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            while ((l = bufferedReader.readLine()) != null) {
                tokenizer = new Scanner(l);
                if(tokenizer.hasNext()) {
                	no=tokenizer.next();
                	if(tokenizer.hasNext()) {
                    	addr=tokenizer.next();
                    }
                }
                symbolTable.put(no,addr);
            }
        }catch (FileNotFoundException e) {
            System.out.println("Error: " + file + " not found. File is either missing or not present in default directory.");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
        return symbolTable;
    }
	
	public HashMap<String,String> getLiterals() {
        String l, file,no="",addr = "";
        FileReader fileReader;
        BufferedReader bufferedReader;
        Scanner sc = new Scanner(System.in), tokenizer;
        file="literal.txt";
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            while ((l = bufferedReader.readLine()) != null) {
                tokenizer = new Scanner(l);
                if(tokenizer.hasNext()) {
                	no=tokenizer.next();
                	if(tokenizer.hasNext()) {
                    	addr=tokenizer.next();
                    }
                }
                literalTable.put(no,addr);
            }
        }catch (FileNotFoundException e) {
            System.out.println("Error: " + file + " not found. File is either missing or not present in default directory.");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
        return literalTable;
    }
}
