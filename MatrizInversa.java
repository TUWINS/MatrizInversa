
public class MatrizInversa {
    
     /*matriz cuadrada (n x n) */
    public static double[][] inverse(double[][] matrix) {
        int n = matrix.length;  /* Obtiene el tamaño de la matriz (n x n)*/
        double[][] augmented = new double[n][2 * n];  /* Crea una matriz aumentada de n filas y 2n columnas (matriz original + identidad) */
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, augmented[i], 0, n);  /* Copia la fila i de la matriz original a las primeras n columnas */
            augmented[i][i + n] = 1;  /* Agrega la identidad en las últimas n columnas (único para formar la matriz aumentada) */
        }
    }
}
