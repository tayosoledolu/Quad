/*Hi Vick!.........
**For the System of linear equations Accordion in the left border of the app
    **Inversion method is included in this class
    **Gaussian Elimination is among the classes I gave you earlier you can implement it with 
        GaussianElimination.lsolve(double[][] A,double[] b) for a problem A*x=b
    **GaussJordanElimination is also among the classes I gave you earlier you can implement it with
        GaussJordanElimination gaussJordanElimination = new GaussJordanElimination(double[][] a, number of variables);
        gaussJordanElimination.solve();       for a problem A*x=b
    **Cholesky uses choleskyDecomposition class and you can implement it with
        CholeskyDecomposition choleskyDecomposition = new CholeskyDecomposition(A).solve(b);   for a problem A*x=b
    **LUdecomposition uses LUdeomposition class and you can implemen it with
        LUDecomposition lUDecomposition = new LUDecomposition(A).solve(B);      for a problem A*x=b

**For Matrix reduction and decomposition Accordion
    **Echelon/Gaussian method uses the rowswap(double[][] a) method in this class
    **Lower Triangular Factor uses LUDecomposition lUDecomposition = new LUDecomposition(A).getL();
    **Upper Triangular Factor uses LUDecomposition lUDecomposition = new LUDecomposition(A).getU();

**For Linear Programming, it uses the simplex.java class (we've discussed earlier)

**After the GUI for the accordion don't forget their usage on the console too.
*/

package numerical_linear_algebra;

public class ammend {
    /* Inversion Method
    ** NOTE that this methods finds a solution to the system A*x=b
    ** by solving the system x=inv(A)*b
    */
   
    public static Matrix inversion(Matrix A, Matrix b){
        Matrix a = A.inverse();
        return a.arrayTimes(b);
    }
    
    /* Echelon Method */

    static int Count;
     public static  Matrix rowSwap(double[][] a)
    {
       
        if (a[0][ 0] == 0)
        {
            Count++;
            double[] primaryRow = new double[a.length];
            for (int i = 0; i < a.length; i++)
            {
                primaryRow[i] = a[0][ i];
            }
            for (int j = 0; j < a.length; j++)
            {
                int i = 1;
                for (i=1; i < a.length; i++)
                {
                    if (a[i][j] != 0)
                    {

                        for (int m = 0; m < primaryRow.length; m++)
                        {
                            a[0][ m] = a[i][m];
                            a[i][ m] = primaryRow[m];
                        }
                        break;
                    }
                }
                if (i != a.length)
                {
                    break;
                }
            }
        }
        rowOperations(a);
        Matrix A=new Matrix(a);
        return A;
        }
     private static  void rowOperations(double[][] a)
        {
            double Variable = 0;
            for (int i = 0, j = 0; i < a.length - 1; )
            {
                if (j == 0)
                {
                    while (j < a.length && a[i][ j] == 0)
                    {
                        j++;
                    }
                }
                if (j == a.length)
                {
                    i++;
                    j = 0;
                }
                else
                {

                    for (int l = i + 1; l < a.length; l++)
                    {

                        for (int m = 0; m < a.length; m++)
                        {
                            if (m == 0)
                            {
                                Variable = ((1 / a[i][ j]) * a[l][ j]);
                                if ((Variable * a[i][ j] > 0 && a[l][ j] > 0)|| (Variable * a[i][ j] < 0 && a[l][ j] < 0))
                                    Variable = Variable * (-1);

                            }

                            a[l][ m] = Math.round(Variable * a[i][ m] + a[l][ m]);
                        

                        }
                    }
                    i++;
                    j = 0;
                }
            }

        }
     
    /*Main Method to test for the Echelon method*/   
//public static void main(String [] args){
//    Scanner input = new Scanner(System.in);     
//    System.out.println("Enter the number of rows ");     
//    int x = input.nextInt(); 
//    System.out.print("Enter the number of columns ");     
//     int y = input.nextInt();     
//     
//     double[][]a  =  new double [x][y]; 
// 
//    System.out.println( "Enter " + a.length + " rows and " + a[0].length + " columns: "); 
//                for (int f = 0; f < a.length; f++)  
//                    for (int g = 0; g < a[f].length; g++) 
//                    a[f][g] = input.nextDouble();
//                rowSwap(a);
//}
}
    
   