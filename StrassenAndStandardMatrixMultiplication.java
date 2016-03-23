package mvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class StrassenAndStandardMatrixMultiplication {
	public  static  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public  StrassenAndStandardMatrixMultiplication() throws IOException {
		int n=0;
		float[][] a, b, c;

		System.out.print("Enter the number of rows : ");
		try {
			n = Integer.parseInt(br.readLine());
		
		
		 if (!isPowerOf2(n)) {
		        System.out.println("Please enter Input Row Size as a Power of 2.");
		    }
		 else
		 {
		a = new float[n][n];
		b = new float[n][n];
		c = new float[n][n];

		a = create(n);
		System.out.print("Matrix A is\n");
		printArray(a);
		System.out.print("Matrix B is \n");
		b = create(n);
		printArray(b);

		System.out.print("\n\nMatrix A X Matrix B is:\nBy Standard method:\n");
		long t1 = System.currentTimeMillis();
		c = standardMultiplication(a, b);
		
		long t2 = System.currentTimeMillis();
		printArray(c);
		System.out.print("Time taken to solve Standard method: "+(t2 - t1 ));
		
		
		
		System.out.print("\n\nBy Strassen Matrix Multiplication method:\n");
		
		
		t1 = System.currentTimeMillis();
		c = strassenMultiplication(a, b);
	
		t2 = System.currentTimeMillis();
		printArray(c);
		System.out.print("Time taken to solve Strassen Matrix Multiplication: "+(t2 - t1 ));
		
		 br.close();
		 }
		} 
		
		 
		 catch (NumberFormatException e) {
				
			  System.out.println("Please enter a valid number.");
				 br.close();
			}
	}

	public float[][] classicalMatrixMultiplication(float[][] a, float[][] b) {
		int n = a.length;
		float[][] c = new float[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				for (int k = 0; k < n; k++)
					c[i][j] += a[i][k] * b[k][j];
		return c;
	}

	
	public float[][] standardMultiplication(float[][] A, float[][] B) {
		int n = A.length;
		float[][] result = new float[n][n];

		if (n == 1) {
			result[0][0] = A[0][0] * B[0][0];
		} else {
			float[][] A11 = new float[n / 2][n / 2];
			float[][] A12 = new float[n / 2][n / 2];
			float[][] A21 = new float[n / 2][n / 2];
			float[][] A22 = new float[n / 2][n / 2];

			float[][] B11 = new float[n / 2][n / 2];
			float[][] B12 = new float[n / 2][n / 2];
			float[][] B21 = new float[n / 2][n / 2];
			float[][] B22 = new float[n / 2][n / 2];

			/** Dividing matrix A into 4 halves **/
			
			divideArray(A, A11, 0, 0);
			divideArray(A, A12, 0, n / 2);
			divideArray(A, A21, n / 2, 0);
			divideArray(A, A22, n / 2, n / 2);
			
			
			
			/** Dividing matrix B into 4 halves **/
			
			divideArray(B, B11, 0, 0);
			divideArray(B, B12, 0, n / 2);
			divideArray(B, B21, n / 2, 0);
			divideArray(B, B22, n / 2, n / 2);

						
			
			float C11[][] = addMatrices(standardMultiplication(A11,B11),standardMultiplication(A12,B21));
			float C12[][] = addMatrices(standardMultiplication(A11,B12),standardMultiplication(A12,B22));
			float C21[][] = addMatrices(standardMultiplication(A21,B11),standardMultiplication(A22,B21));
			float C22[][] = addMatrices(standardMultiplication(A21,B12),standardMultiplication(A22,B22));
			
			
			joinMatrix(C11, result, 0, 0);
			joinMatrix(C12, result, 0, n / 2);
			joinMatrix(C21, result, n / 2, 0);
			joinMatrix(C22, result, n / 2, n / 2);
			
			
			
		}
		return result;
	}
	public float[][] strassenMultiplication(float[][] A, float[][] B) {
		int n = A.length;
		float[][] result = new float[n][n];

		if (n == 1) {
			result[0][0] = A[0][0] * B[0][0];
		} else {
			float[][] A11 = new float[n / 2][n / 2];
			float[][] A12 = new float[n / 2][n / 2];
			float[][] A21 = new float[n / 2][n / 2];
			float[][] A22 = new float[n / 2][n / 2];

			float[][] B11 = new float[n / 2][n / 2];
			float[][] B12 = new float[n / 2][n / 2];
			float[][] B21 = new float[n / 2][n / 2];
			float[][] B22 = new float[n / 2][n / 2];

			
			/** Dividing matrix A into 4 halves **/
			
			divideArray(A, A11, 0, 0);
			divideArray(A, A12, 0, n / 2);
			divideArray(A, A21, n / 2, 0);
			divideArray(A, A22, n / 2, n / 2);
			
			
			
			
			/** Dividing matrix B into 4 halves **/
			
			divideArray(B, B11, 0, 0);
			divideArray(B, B12, 0, n / 2);
			divideArray(B, B21, n / 2, 0);
			divideArray(B, B22, n / 2, n / 2);

			
			
			float S1[][] = subtractMatrices(B12, B22);
			float S2[][] = addMatrices(A11, A12);
			float S3[][] = addMatrices(A21, A22);
			float S4[][] = subtractMatrices(B21, B11);
			float S5[][] = addMatrices(A11, A22);
			float S6[][] = addMatrices(B11, B22);
			float S7[][] = subtractMatrices(A12, A22);
			float S8[][] = addMatrices(B21, B22);
			float S9[][] = subtractMatrices(A11, A21);
			float S10[][] = addMatrices(B11, B12);
			

			/* Computing P1-P7 */
			
			
			float[][] P1 = strassenMultiplication(A11, S1);
			float[][] P2 = strassenMultiplication(S2, B22);
			float[][] P3 = strassenMultiplication(S3, B11);
			float[][] P4 = strassenMultiplication(A22, S4);
			float[][] P5 = strassenMultiplication(S5, S6);
			float[][] P6 = strassenMultiplication(S7, S8);
			float[][] P7 = strassenMultiplication(S9, S10);

			
			
			float C11[][] = addMatrices(subtractMatrices(addMatrices(P5, P4), P2), P6);
			float C12[][] = addMatrices(P1, P2);
			float C21[][] = addMatrices(P3, P4);
			float C22[][] = subtractMatrices(subtractMatrices(addMatrices(P5, P1), P3), P7);
			
			
			
			/** join 4 halves into one result matrix **/
			
			joinMatrix(C11, result, 0, 0);
			joinMatrix(C12, result, 0, n / 2);
			joinMatrix(C21, result, n / 2, 0);
			joinMatrix(C22, result, n / 2, n / 2);
		}
		return result;
	}

	public float[][] addMatrices(float[][] A, float[][] B) {
		int n = A.length;

		float[][] result = new float[n][n];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				result[i][j] = A[i][j] + B[i][j];

		return result;
	}

	public float[][] subtractMatrices(float[][] A, float[][] B) {
		int n = A.length;

		float[][] result = new float[n][n];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				result[i][j] = A[i][j] - B[i][j];

		return result;
	}

	public void divideArray(float[][] parent, float[][] child, int div1, int div2) {
		for (int i1 = 0, i2 = div1; i1 < child.length; i1++, i2++)
			for (int j1 = 0, j2 = div2; j1 < child.length; j1++, j2++) {
				child[i1][j1] = parent[i2][j2];
			}
	}

	public void joinMatrix(float[][] child, float[][] parent, int div1, int div2) {
		for (int i1 = 0, i2 = div1; i1 < child.length; i1++, i2++)
			for (int j1 = 0, j2 = div2; j1 < child.length; j1++, j2++) {
				parent[i2][j2] = child[i1][j1];
			}
	}

	public void printArray(float[][] A) {
		int n = A.length;

		System.out.println();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(A[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) throws IOException {
		new StrassenAndStandardMatrixMultiplication();
	}

	public static float[][] create(int rowSize) {
		Random random = new Random();
		float[][] matrix = new float[rowSize][rowSize];

		for (int i = 0; i < matrix.length; i++) {
			for (int k = 0; k < matrix[0].length; k++) {
				matrix[i][k] = (float) Math.abs((random.nextFloat() * 10));
			}
		}
		return matrix;
	}
	
	private static boolean isPowerOf2(final int n) {
	    if (n <= 0) {
	        return false;
	    }
	    return (n & (n - 1)) == 0;
	    }
}