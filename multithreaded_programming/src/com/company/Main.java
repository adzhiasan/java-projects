package com.company;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {

        Matrix a = new Matrix(5, 5);
        Matrix b = new Matrix(5, 5);
        Matrix result = new Matrix(a.getDimensionN(), b.getDimensionM());

        Random random = new Random();
        a.randomFill(random, 10);
        b.randomFill(random, 10);

        PrintMatrix(a);
        PrintMatrix(b);

        CyclicBarrier barrier = new CyclicBarrier(a.getDimensionN(), new Runnable() {
            @Override
            public void run() {
                PrintMatrix(result);
            }
        });

        for (int i =0; i<a.getDimensionN(); i++)
        {
            new MatrixMultuplier(barrier, a, b, result, i).start();
        }
    }

    private static void PrintMatrix(Matrix result){
        for(int i =0; i < result.getDimensionN(); i++){
            for(int j = 0; j < result.getDimensionM(); j++){
                System.out.print(result.getByIndex(i, j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}

class MatrixMultuplier extends  Thread
{
    private CyclicBarrier barrier;
    private Matrix  a, b, c;
    private int i;

    public MatrixMultuplier(CyclicBarrier barrier, Matrix a, Matrix b, Matrix c, int i)
    {
        this.barrier = barrier;
        this.a = a;
        this.b = b;
        this.c = c;
        this.i = i;
    }

    @Override
    public void run()
    {
        for (int j = 0; j < b.getDimensionM(); j++){
            for (int k = 0; k < a.getDimensionM(); k++){
                c.setByIndex(i, j,  c.getByIndex(i, j) + a.getByIndex(i, k) * b.getByIndex(k, j));
            }
        }
        try
        {
            barrier.await();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
