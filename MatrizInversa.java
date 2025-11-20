
public class MatrizInversa {
    
     /*matriz cuadrada (n x n) */
    public static double[][] inverse(double[][] matrix) {
        int n = matrix.length;  /* Obtiene el tamaño de la matriz (n x n)*/
        double[][] augmented = new double[n][2 * n];  /* Crea una matriz aumentada de n filas y 2n columnas (matriz original + identidad) */
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, augmented[i], 0, n);  /* Copia la fila i de la matriz original a las primeras n columnas */
            augmented[i][i + n] = 1;  /* Agrega la identidad en las últimas n columnas (único para formar la matriz aumentada) */
        }
                    for (int j = i + 1; j < n; j++) {  // Busca el pivote máximo en la columna i para estabilidad numérica
                if (Math.abs(augmented[j][i]) > Math.abs(augmented[max][i])) max = j;  // Actualiza si encuentra uno mayor
            }
            double[] temp = augmented[i];  // Intercambia la fila i con la fila del pivote máximo
            augmented[i] = augmented[max];
            augmented[max] = temp;
            if (Math.abs(augmented[i][i]) < 1e-10) throw new IllegalArgumentException("Matriz no invertible única");  // Verifica si la matriz es singular (único)
            // Elimina los elementos debajo del pivote en la columna i
            for (int j = i + 1; j < n; j++) {
                double factor = augmented[j][i] / augmented[i][i];  // Calcula el factor para eliminación
                for (int k = i; k < 2 * n; k++) {  // Aplica la eliminación a toda la fila
                    augmented[j][k] -= factor * augmented[i][k];  // Resta el múltiplo de la fila pivote
                }
            }
        }
    }
}
