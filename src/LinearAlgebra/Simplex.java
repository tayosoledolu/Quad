package LinearAlgebra;

// The Following solves a linear programming problem
// Please the read below.
/************************************************USAGE*************************************************************
 * 1.Create an instance of the simplex class
 * 2.Fill in the table with the standardized form of the problem by calling simplex.fillTable()
 * 3.Create a while loop and call the simplex.compute() method until it returns ERROR.IS_OPTIMAL or ERROR.UNBOUNDED 
 * ****************************************************************************************************************/

public class Simplex {
    private int rows, cols; // row and column
    private double[][] table; // simplex tableau
    private boolean solutionIsUnbounded = false;

    public static enum ERROR{
        NOT_OPTIMAL,
        IS_OPTIMAL,
        UNBOUNDED
    };
    
    public Simplex(int numOfConstraints, int numOfUnknowns){
        rows = numOfConstraints+1; // row number + 1 
        cols = numOfUnknowns+1;   // column number + 1
        table = new double[rows][]; // create a 2d array
        
        // initialize references to arrays
        for(int i = 0; i < rows; i++){
            table[i] = new double[cols];
        }
    }
    
    // prints out the simplex tableau
    public void print(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                String value = String.format("%.2f", table[i][j]);
                System.out.print(value + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    // fills the simplex tableau with coefficients
    public void fillTable(double[][] data){
        for(int i = 0; i < table.length; i++){
            System.arraycopy(data[i], 0, this.table[i], 0, data[i].length);
        }
    }
    
    // computes the values of the simplex tableau
    // should be use in a loop to continously compute until
    // an optimal solution is found
    static int pivot=0;
    public void setpivot(int pivot){
        this.pivot=pivot;
    }
    public static  int getpivot(){
        return pivot;
    }
    public ERROR compute(){
        // step 1
        if(checkOptimality()){
            return ERROR.IS_OPTIMAL; // solution is optimal
        }
        
        // step 2
        // find the entering column
        int pivotColumn = findEnteringColumn();
        setpivot(pivotColumn);
        //System.out.println("Pivot Column: "+pivotColumn);
        
        // step 3
        // find departing value
       double[] ratios = calculateRatios(pivotColumn);
        if(solutionIsUnbounded == true)
            return ERROR.UNBOUNDED;
        int pivotRow = findSmallestValue(ratios);
        //System.out.println("Pivot row: "+ pivotRow);
        
        // step 4
        // form the next tableau
        formNextTableau(pivotRow, pivotColumn);
        
        // since we formed a new table so return NOT_OPTIMAL
        return ERROR.NOT_OPTIMAL;
    }
    
    // Forms a new tableau from precomuted values.
    private void formNextTableau(int pivotRow, int pivotColumn){
        double pivotValue = table[pivotRow][pivotColumn];
        double[] pivotRowVals = new double[cols];
        double[] pivotColumnVals = new double[cols];
        double[] rowNew = new double[cols];
        
        // divide all entries in pivot row by entry inpivot column
        // get entry in pivot row
        System.arraycopy(table[pivotRow], 0, pivotRowVals, 0, cols);
        
        // get entry inpivot colum
        for(int i = 0; i < rows; i++)
            pivotColumnVals[i] = table[i][pivotColumn];
        
        // divide values in pivot row by pivot value
        for(int  i = 0; i < cols; i++)
            rowNew[i] =  pivotRowVals[i] / pivotValue;
        
        // subtract from each of the other rows
        for(int i = 0; i < rows; i++){
            if(i != pivotRow){
                for(int j = 0; j < cols; j++){
                    double c = pivotColumnVals[i];
                    table[i][j] = table[i][j] - (c * rowNew[j]);
                }
            }
        }
        
        // replace the row
        System.arraycopy(rowNew, 0, table[pivotRow], 0, rowNew.length);
    }
    
    // calculates the pivot row ratios
    private double[] calculateRatios(int column){
        double[] positiveEntries = new double[rows];
        double[] res = new double[rows];
        int allNegativeCount = 0;
        for(int i = 0; i < rows; i++){
            if(table[i][column] > 0){
                positiveEntries[i] = table[i][column];
            }
            else{
                positiveEntries[i] = 0;
                allNegativeCount++;
            }
            //System.out.println(positiveEntries[i]);
        }
        
        if(allNegativeCount == rows)
            this.solutionIsUnbounded = true;
        else{
            for(int i = 0;  i < rows; i++){
                double val = positiveEntries[i];
                if(val > 0){
                    res[i] = table[i][cols -1] / val;
                }
            }
        }
        
        return res;
    }
    
    // finds the next entering column
    private int findEnteringColumn(){
        double[] values = new double[cols];
        int location = 0;
        
        int pos, count = 0; 
        for(pos = 0; pos < cols-1; pos++){
            if(table[rows-1][pos] < 0){
                //System.out.println("negative value found");
                count++;
            }
        }
        
        if(count > 1){
            for(int i = 0; i < cols-1; i++)
                values[i] = Math.abs(table[rows-1][i]);
            location = findLargestValue(values);
        } else location = count - 1;
        
        return location;
    }
    
    
    // finds the smallest value in an array
    private int findSmallestValue(double[] data){
        double minimum ;
        int c, location = 0;
        minimum = data[0];
        
        for(c = 1; c < data.length; c++){
            if(data[c] > 0){
                if(Double.compare(data[c], minimum) < 0){
                    minimum = data[c];
                    location  = c;
                }
            }
        }
        
        return location;
    }
    
    // finds the largest value in an array
    private int findLargestValue(double[] data){
        double maximum = 0;
        int c, location = 0;
        maximum = data[0];
        
        for(c = 1; c < data.length; c++){
            if(Double.compare(data[c], maximum) > 0){
                maximum = data[c];
                location  = c;
            }
        }
        
        return location;
    }
    
    // checks if the table is optimal
    public boolean checkOptimality(){
        boolean isOptimal = false;
        int vCount = 0;
        
        for(int i = 0; i < cols-1; i++){
            double val = table[rows-1][i];
            if(val >= 0){
                vCount++;
            }
        }
        
        if(vCount == cols-1){
            isOptimal = true;
        }
        
        return isOptimal;
    }

    // returns the simplex tableau
    public double[][] getTable() {
        return table;
    }
    
    public static double[][] result_(double[][] standardized) throws Exception{
        // row and column do not include
        // right hand side values
        // and objective row
        String result="";
        boolean quit = false;
        
        Simplex simplex = new Simplex(standardized.length - 1, standardized[0].length - 1);
        
        simplex.fillTable(standardized);
        
        //if table is not optimal re-iterate
            int i=0;
            while(!quit){
            i++;
            if(i>50)break;
            Simplex.ERROR err = simplex.compute();

            if(err == Simplex.ERROR.IS_OPTIMAL){
                quit = true;
                return simplex.getTable();
            }
            else if(err == Simplex.ERROR.UNBOUNDED){
                quit = true;
                throw new Exception("---Solution is unbounded---");
            }
            
        }
        return null;
    }
    
    
    public static void result(double [][] standardized) throws Exception{
        // row and column do not include
        // right hand side values
        // and objective row
           
        boolean quit = false;
        
        Simplex simplex = new Simplex(standardized.length - 1, standardized[0].length - 1);
        
        simplex.fillTable(standardized);

        // print it out
//        System.out.println("---Starting set---");
//        simplex.print();
//        simplex.getTable();
//        System.out.println(Matrix.display(new Matrix(simplex.getTable())));
        
        // if table is not optimal re-iterate
        int i=0;
        while(!quit){
            i++;
            if(i>100)break;
            Simplex.ERROR err = simplex.compute();

            if(err == Simplex.ERROR.IS_OPTIMAL){
                simplex.print();
                quit = true;
            }
            else if(err == Simplex.ERROR.UNBOUNDED){
//                System.out.println("---Solution is unbounded---");
//                quit = true;
                quit = true;
                throw new Exception("---Solution is unbounded---");
            }
            
        }
    }
//    public static void main(String[] args) throws Exception {
//        // Example problem:
//        // maximize 3x + 5y 
//        // subject to x +  y = 4 and
//        //            x + 3y = 6
//        int m , n;
//        java.util.Scanner input = new java.util.Scanner(System.in);
//        System.out.println("Enter the number of Constraints: ");
//        m = input.nextInt();
//        System.out.println("Enter the number of Unknowns: ");
//        n = input.nextInt();  
//        System.out.println("Enter a ready Standardized array of Linear Program row by row: ");
//        
//        double[][] arr = new double[m][n];
//        
//        for(int i = 0; i < arr.length; i++){
//            for(int j = 0; j < arr[0].length; j++)
//                arr[i][j] = input.nextDouble();
//        }
//         System.out.println(Matrix.display(new Matrix(result_(arr))));
//    }   
}
    