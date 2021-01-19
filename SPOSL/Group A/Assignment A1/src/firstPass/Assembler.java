package firstPass;

public class Assembler {
	public static void main(String [] argv){
		FileIO fio= new FileIO();
		fio.getProgram();
		FirstPass pass1 = new FirstPass();
		pass1.process(fio.returnProgram());
		pass1.print();
	}
}
