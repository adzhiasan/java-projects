package com.company;
import java.util.Random;

public class Matrix {
    private int [][] matrix;
    private int dimensionN;
    private int dimensionM;

    public Matrix(int n, int m){
        this.dimensionN = n;
        this.dimensionM = m;

        this.matrix = new int[n][m];
    }

    public void randomFill(Random random, int value){
        for (int i = 0; i < dimensionN; i++){
            for (int j = 0; j < dimensionM; j++){
                matrix[i][j] = random.nextInt(value);
            }
        }
    }

   public int getByIndex(int i, int j){
       return  matrix[i][j];
   }

   public void setByIndex(int i, int j, int value){
        matrix[i][j] = value;
   }

    public int getDimensionN() {
        return dimensionN;
    }

    public int getDimensionM() {
        return dimensionM;
    }

    public static Matrix multiply(Matrix a, Matrix b) throws  ArithmeticException{
        if(a.getDimensionN() != b.getDimensionM())
            throw new ArithmeticException("Матрицы невозможно перемножить!");

        Matrix result = new Matrix(a.getDimensionN(), b.getDimensionM());

        for (int i = 0; i < a.getDimensionN(); i++){
            for (int j = 0; j < b.getDimensionM(); j++){
                for (int k = 0; k < a.getDimensionM(); k++){
                     result.matrix[i][j] += a.matrix[i][k] * b.matrix[k][j];
                }
            }
        }
        return result;
    }
}
