import java.util.Scanner;

public class RREF {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of columns: ");
        int columns = scanner.nextInt();
        
        double[][] matrix = new double[rows][columns];
        
        System.out.println("Enter the matrix elements:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        
        reduceToEchelonForm(matrix);
        
        System.out.println("Matrix in echelon form:");
        printMatrix(matrix);
        
        scanner.close();
    }
    
    public static void reduceToEchelonForm(double[][] matrix) {
        // stores the number of rows and columns
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        
        // keeps track of the current leading column
        int lead = 0;

        // iterates over each row of the matrix
        for (int r = 0; r < rowCount; r++) {
            // if lead >= colCount, the method returns as we reached the last column
            if (lead >= colCount) {
                return;
            }
            // A nested while loop searches for a non-zero pivot element in the current column of the current row. 
            // If it encounters a row where the pivot element is zero, it moves to the next row.
            int i = r;
            while (matrix[i][lead] == 0) {
                i++;
                if (i == rowCount) {
                    i = r;
                    lead++;
                    // If it reaches the last row and still hasn't found a non-zero pivot, it resets the loop to the current row and increments lead.
                    if (lead == colCount) {
                        return;
                    }
                }
            }
            // Once a non-zero pivot is found, it swaps the current row (matrix[r]) with the row containing the pivot element (matrix[i]).
            double[] temp = matrix[r];
            matrix[r] = matrix[i];
            matrix[i] = temp;
            
            double lv = matrix[r][lead];
            // normalizing pivot row
            // It divides the pivot row by the value of the pivot element (lv) to make the pivot element equal to 1.
            for (int j = 0; j < colCount; j++) {
                matrix[r][j] /= lv;
            }
            
            // Zeroing Out Other Rows
            for (int i2 = 0; i2 < rowCount; i2++) {
                // The loop iterates over all rows except the pivot row (i2 != r).
                if (i2 != r) {
                    double factor = matrix[i2][lead];
                    for (int j = 0; j < colCount; j++) {
                        matrix[i2][j] -= factor * matrix[r][j];
                    }
                }
            }
            // lead is incremented to move on to the next column
            lead++;
        }
    }
    // takes the matrix as a parameter and prints its elements row by row, separated by spaces.
    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}