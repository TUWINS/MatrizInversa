import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.FileReader;  
import java.io.FileWriter;  
import java.io.IOException; 
import java.util.Arrays;

public class MatrizInversa {
    
    /* matriz cuadrada (n x n) */
    public static double[][] invertirMatriz(double[][] matrix) {
        int n = matrix.length;  /* Obtiene el tamaño de la matriz (n x n) */
        double[][] augmented = new double[n][2 * n];  /* Crea una matriz aumentada de n filas y 2n columnas (matriz original + identidad) */
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, augmented[i], 0, n);  /* Copia la fila i de la matriz original a las primeras n columnas */
            augmented[i][i + n] = 1;  /* Agrega la identidad en las últimas n columnas (único para formar la matriz aumentada) */
        }
        
        // Eliminación gaussiana con pivoteo parcial único
        for (int i = 0; i < n; i++) {
            int max = i;  // Inicializa el índice del pivote máximo en la columna i
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

        // Sustitución hacia atrás para obtener la inversa
        double[][] inverse = new double[n][n];  // Crea la matriz inversa
        for (int i = n - 1; i >= 0; i--) {  // Empieza desde la última fila
            for (int j = 0; j < n; j++) {  // Para cada columna de la inversa
                inverse[i][j] = augmented[i][j + n] / augmented[i][i];  // Calcula el elemento de la inversa dividiendo por el pivote
            }
        }
        
        return inverse;  // Retorna la matriz inversa calculada
    }
    
    public static void main(String[] args) {  // Método principal para ejecutar el programa
        try {
            // Lee la matriz desde el archivo de entrada
            BufferedReader br = new BufferedReader(new FileReader("input_matrix.txt"));
            String firstLine = br.readLine();  // Lee la primera línea para determinar el tamaño
            int n = firstLine.split(" ").length;  // Calcula n como el número de elementos en la primera línea
            double[][] matrix = new double[n][n];  // Crea la matriz de tamaño n x n
            matrix[0] = Arrays.stream(firstLine.split(" ")).mapToDouble(Double::parseDouble).toArray();  // Convierte la primera línea a double
            for (int i = 1; i < n; i++) {  // Lee las filas restantes
                matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();  // Convierte cada línea a fila double
            }
            br.close();  // Cierra el lector

            // Calcula la inversa usando el método único
            double[][] inv = invertirMatriz(matrix);  // Corregido: llama al método correcto
            // Escribe la matriz inversa en el archivo de salida
            BufferedWriter bw = new BufferedWriter(new FileWriter("output_matrix.txt"));
            for (double[] row : inv) {  // Para cada fila de la inversa (quitado espacio extra)
                for (double val : row) {  // Para cada valor en la fila
                    bw.write(val + " ");  // Escribe el valor seguido de espacio
                }
                bw.write("\n");  // Nueva línea para la siguiente fila
            }
            bw.close();  // Cierra el escritor
            System.out.println("Matriz Inversa única completada. Resultado en output_matrix.txt");
        } catch (Exception e) {  // Captura excepciones (IO o no invertible)
            System.err.println("Error único: " + e.getMessage());  // Imprime mensaje de error único
        }
    }
}
