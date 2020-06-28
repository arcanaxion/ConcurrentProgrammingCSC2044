
package Solution.Question1;

public class MainClass {
    
    public static void main(String[] args) {
        Printer printer = new Printer();
       // Computer c = new Computer(printer);
        
        Computer A = new Computer(printer);
        Computer B = new Computer(printer);
        
        Thread p = new Thread(printer);
        Thread t1 = new Thread(A);
        Thread t2 = new Thread(B);
        
        t1.setName("Computer A");
        t2.setName("Computer B");
        
        p.start();
        t1.start();
        t2.start();
    }
}
