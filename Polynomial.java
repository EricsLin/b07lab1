import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Polynomial { 

    double[] coefficients;
    int[] exponents;

    public Polynomial () {
        this.coefficients = new double[] {0};
        this.exponents = new int[] {0};
    }
    
    public Polynomial(File file) {
    	Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
    	String content = scanner.nextLine();
    	
    	
    	String[] terms = content.split("[+-]");
    	
    	coefficients = new double[terms.length];
    	exponents = new int[terms.length];
    	
    	for (int i = 0; i < terms.length; i++) {
    		
    		if (terms[i].length() == 0) {
    			continue;
    		}
    		
    		String[] values = terms[i].split("x");
    		
    		coefficients[i] = Integer.parseInt(values[0]);
    		if (values.length > 1) {
    			exponents[i] = Integer.parseInt(values[1]);
    		} else if (terms[i].contains("x")) {
    			exponents[i] = 1;
    		} else {
    			exponents[i] = 0;
    		}
    		
    		int term_count = 0;
    		
    		for (int j = 0; j < content.length(); j++) {
    			if (content.charAt(j) == '+' || content.charAt(j) == '-') {
    				term_count++;
    				
    				if (term_count == i  && content.charAt(j) == '-') {
    					coefficients[i] *= -1;
    					break;
    				}
    			}
    		}
    	}
    	
    	scanner.close();
    }

    public Polynomial (double[] coefficients, int[] exponents) {
        this.coefficients = coefficients;
        this.exponents = exponents;
    }

    public Polynomial add(Polynomial to_add) {
    	
    	int new_length = to_add.coefficients.length + coefficients.length;
    	
    	double[] coefficient_sum = new double[new_length];
    	int[] exponent_sum = new int[new_length];
    	
    	for (int i = 0; i < new_length; i++) {
    		coefficient_sum[i] = i < coefficients.length ? coefficients[i] : to_add.coefficients[i-coefficients.length];
    		exponent_sum[i] = i < exponents.length ? exponents[i] : to_add.exponents[i-exponents.length];
    	}
    	
    	
        return new Polynomial(coefficient_sum, exponent_sum);
    }
    
    public Polynomial multiply(Polynomial to_multiply) {
    	
    	int max_length = coefficients.length * to_multiply.coefficients.length;
    	
    	double[] new_coefficient = new double[max_length];
      	int[] new_exponent = new int[max_length];
    
      	for (int i = 0; i < coefficients.length; i++) {
      		for (int j = 0; j < to_multiply.coefficients.length; j++) {
      			new_coefficient[i*to_multiply.coefficients.length + j] = coefficients[i] * to_multiply.coefficients[j];
      			new_exponent[i*to_multiply.coefficients.length + j] = exponents[i] + to_multiply.exponents[j];
      		}
      	}
      	
      	double[] condensed_coefficient = new double[max_length];
      	int[] condensed_exponent = new int[max_length];
      	boolean calculate_zero = false;
      	
      	for (int i = 0; i < max_length; i++) {
      		if (!hasExponent(condensed_exponent, new_exponent[i]) || (!calculate_zero && new_exponent[i] == 0)) {
      			double total = 0;
      			
      			for (int j = 0; j < max_length; j++) {
      				if (new_exponent[j] == new_exponent[i]) {
      					total += new_coefficient[j];
      				}
      			}
      			
      			condensed_coefficient[i] = total;
      			condensed_exponent[i] = new_exponent[i];
      			
      			if (new_exponent[i] == 0) {
      				calculate_zero = true;
      			}
      		}
      	}
      	
      	int non_zero = 0;
      	
      	for (int i = 0; i < max_length; i++) {
      		if (condensed_coefficient[i] != 0) {
      			non_zero++;
      		}
      	}
      	
      	double[] final_coefficient = new double[non_zero];
      	int[] final_exponent = new int[non_zero];
      	int counter = 0;
      	
      	for (int i = 0; i < max_length; i++) {
      		if (condensed_coefficient[i] != 0) {
      			final_coefficient[counter] = condensed_coefficient[i];
      			final_exponent[counter] = condensed_exponent[i];
      			counter++;
      		}
      	}
      	
      	return new Polynomial(final_coefficient, final_exponent);
      	
    }

    public double evaluate(double x) {
        double total = 0;

        for (int i = 0; i < coefficients.length; i++) {
            total += Math.pow(x, exponents[i]) * coefficients[i];
        }

        return total;
    }

    public boolean hasRoot(double x) {
        return this.evaluate(x) == 0;
    }
    
    private boolean hasExponent(int[] arr, int exponent) {
    	for (int i = 0; i < arr.length; i++) {
    		if (arr[i] == exponent) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public void saveToFile(String name) {
    	String textToSave = "";
    	for (int i = 0; i < coefficients.length; i++) {
    		textToSave += coefficients[i];
    		
    		if (exponents[i] > 0) {
    			textToSave += "x";
    			
    			if (exponents[i] > 1) {
    				textToSave += exponents[i];
    			}
    		}
    	}
    	FileWriter output;
		try {
			output = new FileWriter(name, false);
			output.write(textToSave);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
    	
    }

}