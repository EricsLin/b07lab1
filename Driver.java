import java.io.File;
import java.io.FileNotFoundException;

public class Driver {
    public static void main(String[] args) {
        Polynomial p = new Polynomial();
        System.out.println("p(3) = " + p.evaluate(3));

        double[] c1 = {6, 5};
        int[] e1 = {3, 0};    
        Polynomial p1 = new Polynomial(c1, e1);

        double[] c2 = {-2, -9};
        int[] e2 = {1, 0};   
        Polynomial p2 = new Polynomial(c2, e2);

        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));

        if (s.hasRoot(1)) {
            System.out.println("1 is a root of s");
        } else {
            System.out.println("1 is not a root of s");
        }
        
        Polynomial m = p1.multiply(p2);
        System.out.println("m(0.1) = " + m.evaluate(0.1));

        System.out.println("p1(1) = " + p1.evaluate(1));
        System.out.println("p1(2) = " + p1.evaluate(2));
        System.out.println("p2(1) = " + p2.evaluate(1));
        System.out.println("p2(2) = " + p2.evaluate(2));

        System.out.println("p1 has root at 1: " + p1.hasRoot(1));
        System.out.println("p2 has root at 4.5: " + p2.hasRoot(4.5));

        Polynomial zeroPoly = new Polynomial(new double[]{0}, new int[]{0});
        System.out.println("Zero polynomial at 5 = " + zeroPoly.evaluate(5));

        double[] c3 = {1, 0, -1};
        int[] e3 = {2, 1, 0};
        Polynomial p3 = new Polynomial(c3, e3);
        System.out.println("p3(1) = " + p3.evaluate(1));
        System.out.println("p3(2) = " + p3.evaluate(2));

        Polynomial p4 = p1.multiply(p3);
        System.out.println("p4(1) = " + p4.evaluate(1));

        double[] c4 = {6, 2, 7};
        int[] e4 = {0, 3, 8};
        Polynomial p5 = new Polynomial(c4, e4);
        System.out.println("p5(1) = " + p5.evaluate(1));
        System.out.println("p5 has root at 2: " + p5.hasRoot(2));
        
        File file = new File("test.txt");
        Polynomial p6 = new Polynomial(file);
		System.out.println("p6(1) = " + p6.evaluate(1));
		p6.saveToFile("test2.txt");
		
        
    }
}
