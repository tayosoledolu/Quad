/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numerical_linear_algebra;

import LinearAlgebra.GaussJordanElimination;
import static LinearAlgebra.GaussJordanElimination.test;
import LinearAlgebra.GaussianElimination;
import static LinearAlgebra.Simplex.result_;
import static LinearAlgebra.Simplex.getpivot;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author vicksemmanuel
 */
public class Console {
    private static Map<String,String> variableList=new TreeMap<>();
    private static Map<String,String> funcList=new TreeMap<>();
    static LinkedList<double[][]> list=new LinkedList<>();
    private static String variableName;
    public Console(){
        String[] functions={"echelon","inversion","lusolve","qrsolve","sum","pos","neg","cholesky","getu","getl","isnonsingular","cos","sin","min","max","sort","ones","twos","threes","fours","fives","sixs","sevens","eights","nines","tens","zeros","rand","tan","gaussian","gaussjordan","gausstest","sqrt","det","inverse","rank","identity","eigenvect","trace","simplex","eigenval","normone","normtwo","normf","norminf","coldim","rowdim","lowerfact","upperfact","holdervect","economyfact","singmat","leftsing","rightsing"};
        for(String temp:functions){
                funcList.put(temp,temp);
        }
    }
   /*    Inversion Method
        Echelon/Gaussian Method
        
    */
    public static String getVariableName() {
        return variableName;
    }
    public static String twoParametersManipulation(String x){
        String exp=x;
        try{
            exp=checkForGaussJordanTest(exp);
            exp=checkForGaussJordanElimination(exp);
            exp=checkForGaussian(exp);
            exp=checkForLUDecomposition(exp);
            exp=checkForCholeskyDecomposition(exp);
            exp=checkForQRDecomposition(exp);
            exp=checkForInversion(exp);
            exp=checkForRand(exp);
            exp=checkForIdentity(exp);
            exp=checkForOnes(exp);
            exp=checkForTens(exp);
            exp=checkForZeros(exp);
            exp=checkForTwos(exp);
            exp=checkForThrees(exp);
            exp=checkForFours(exp);
            exp=checkForFives(exp);
            exp=checkForSixs(exp);
            exp=checkForSevens(exp);
            exp=checkForEights(exp);
            exp=checkForNines(exp);
        }catch(Exception e){
            throw new RuntimeException("Error in computation, check guide");
        }
        return exp;
    }
    public static Map<String,String> getVariableList(){
        return variableList;
    }
    public static String checkForInversion(String x){
        String expression=x,foo="";
        Pattern meek=Pattern.compile("inversion\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
        Matcher matcher=meek.matcher(expression);
        StringBuffer tt=new StringBuffer(expression);
        int nextStart=0,nextEnd=0;
        int ink=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            ink++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        int k=0, j=0;
        if(x.contains("inversion")){
            if(x.matches(".*inversion\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\).*")){
                //Pattern meek=Pattern.compile("inversion\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
                Matcher matcher_=meek.matcher(x);
                while(matcher_.find()){
                    int start=matcher_.start();
                    int end=matcher_.end();
                    String manipulate=matcher_.group().trim();
                    String mani_holder=manipulate;
                    manipulate=manipulate.replace("inversion(", "");
                    manipulate=manipulate.substring(0, manipulate.length()-1);
                    int replaceLength=0,findLength=0;
                    j++;
                    int distance=acc.get(k);
                    k++;
                    
                    
                    
                    if(j==1){
                        findLength=mani_holder.length();
                        
                        
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        double[][] hus=ammend.inversion(jeep, jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command inversion accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
							
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        double[][] hus=ammend.inversion(jeep, jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command inversion accepts only two variables");
                        }else if(manipulate.contains("],")){
                            Pattern comma_meek=Pattern.compile("\\]\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\],");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+"]";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());


                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        double[][] hus=ammend.inversion(jeep, jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command lusove accepts only two variables");
                        }else if(manipulate.contains(",[")){
                            Pattern comma_meek=Pattern.compile("\\,\\[");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\[");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="["+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        double[][] hus=ammend.inversion(jeep, jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command inversion accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        double[][] hus=ammend.inversion(jeep, jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command inversion accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(start,end,foo));

                        nextStart=start+(replaceLength)+distance;
                        //nextEnd=nextStart+findLength;
                    }
                    if(j>1){
                        findLength=mani_holder.length();
                        nextEnd=nextStart+findLength;
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        double[][] hus=ammend.inversion(jeep, jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command inversion accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        double[][] hus=ammend.inversion(jeep, jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command inversion accepts only two variables");
                        }else if(manipulate.contains("],")){
                            Pattern comma_meek=Pattern.compile("\\]\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\],");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+"]";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        double[][] hus=ammend.inversion(jeep, jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command inversion accepts only two variables");
                        }else if(manipulate.contains(",[")){
                            Pattern comma_meek=Pattern.compile("\\,\\[");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\[");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="["+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        double[][] hus=ammend.inversion(jeep, jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command inversion accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        double[][] hus=ammend.inversion(jeep, jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command inversion accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo));
                        nextStart+=replaceLength+distance;
                        nextEnd=nextStart+findLength;
                    }
                }
            }else throw new RuntimeException("Incorrect inversion declaration");
        }
        return tt.toString();

    }
    public static String checkForQRDecomposition(String x){
        String expression=x,foo="";
        Pattern meek=Pattern.compile("qrsolve\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
        Matcher matcher=meek.matcher(expression);
        StringBuffer tt=new StringBuffer(expression);
        int nextStart=0,nextEnd=0;
        int ink=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            ink++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        int k=0, j=0;
        if(x.contains("qrsolve")){
            if(x.matches(".*qrsolve\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\).*")){
                //Pattern meek=Pattern.compile("qrsolve\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
                Matcher matcher_=meek.matcher(x);
                while(matcher_.find()){
                    int start=matcher_.start();
                    int end=matcher_.end();
                    String manipulate=matcher_.group().trim();
                    String mani_holder=manipulate;
                    manipulate=manipulate.replace("qrsolve(", "");
                    manipulate=manipulate.substring(0, manipulate.length()-1);
                    int replaceLength=0,findLength=0;
                    j++;
                    int distance=acc.get(k);
                    k++;
                    
                    
                    
                    if(j==1){
                        findLength=mani_holder.length();
                        
                        
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        QRDecomposition A=new QRDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command qrsolve accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
							
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        QRDecomposition A=new QRDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command qrsolve accepts only two variables");
                        }else if(manipulate.contains("],")){
                            Pattern comma_meek=Pattern.compile("\\]\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\],");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+"]";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());


                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        QRDecomposition A=new QRDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command lusove accepts only two variables");
                        }else if(manipulate.contains(",[")){
                            Pattern comma_meek=Pattern.compile("\\,\\[");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\[");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="["+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        QRDecomposition A=new QRDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command qrsolve accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        QRDecomposition A=new QRDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command qrsolve accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(start,end,foo));

                        nextStart=start+(replaceLength)+distance;
                        //nextEnd=nextStart+findLength;
                    }
                    if(j>1){
                        findLength=mani_holder.length();
                        nextEnd=nextStart+findLength;
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        QRDecomposition A=new QRDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command qrsolve accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        QRDecomposition A=new QRDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command qrsolve accepts only two variables");
                        }else if(manipulate.contains("],")){
                            Pattern comma_meek=Pattern.compile("\\]\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\],");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+"]";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        QRDecomposition A=new QRDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command qrsolve accepts only two variables");
                        }else if(manipulate.contains(",[")){
                            Pattern comma_meek=Pattern.compile("\\,\\[");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\[");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="["+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        QRDecomposition A=new QRDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command qrsolve accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        QRDecomposition A=new QRDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command qrsolve accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo));
                        nextStart+=replaceLength+distance;
                        nextEnd=nextStart+findLength;
                    }
                }
            }else throw new RuntimeException("Incorrect qrsolve declaration");
        }
        return tt.toString();

    }
    public static String checkForCholeskyDecomposition(String x){
        String expression=x,foo="";
        Pattern meek=Pattern.compile("cholesky\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
        Matcher matcher=meek.matcher(expression);
        StringBuffer tt=new StringBuffer(expression);
        int nextStart=0,nextEnd=0;
        int ink=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            ink++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        int k=0, j=0;
        if(x.contains("cholesky")){
            if(x.matches(".*cholesky\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\).*")){
                //Pattern meek=Pattern.compile("cholesky\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
                Matcher matcher_=meek.matcher(x);
                while(matcher_.find()){
                    int start=matcher_.start();
                    int end=matcher_.end();
                    String manipulate=matcher_.group().trim();
                    String mani_holder=manipulate;
                    manipulate=manipulate.replace("cholesky(", "");
                    manipulate=manipulate.substring(0, manipulate.length()-1);
                    int replaceLength=0,findLength=0;
                    j++;
                    int distance=acc.get(k);
                    k++;
                    
                    
                    
                    if(j==1){
                        findLength=mani_holder.length();
                        
                        
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        CholeskyDecomposition A=new CholeskyDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command cholesky accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
							
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        CholeskyDecomposition A=new CholeskyDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command cholesky accepts only two variables");
                        }else if(manipulate.contains("],")){
                            Pattern comma_meek=Pattern.compile("\\]\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\],");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+"]";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());


                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        CholeskyDecomposition A=new CholeskyDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command lusove accepts only two variables");
                        }else if(manipulate.contains(",[")){
                            Pattern comma_meek=Pattern.compile("\\,\\[");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\[");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="["+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        CholeskyDecomposition A=new CholeskyDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command cholesky accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        CholeskyDecomposition A=new CholeskyDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command cholesky accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(start,end,foo));

                        nextStart=start+(replaceLength)+distance;
                        //nextEnd=nextStart+findLength;
                    }
                    if(j>1){
                        findLength=mani_holder.length();
                        nextEnd=nextStart+findLength;
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        CholeskyDecomposition A=new CholeskyDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command cholesky accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        CholeskyDecomposition A=new CholeskyDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command cholesky accepts only two variables");
                        }else if(manipulate.contains("],")){
                            Pattern comma_meek=Pattern.compile("\\]\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\],");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+"]";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        CholeskyDecomposition A=new CholeskyDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command cholesky accepts only two variables");
                        }else if(manipulate.contains(",[")){
                            Pattern comma_meek=Pattern.compile("\\,\\[");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\[");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="["+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        CholeskyDecomposition A=new CholeskyDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command cholesky accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        CholeskyDecomposition A=new CholeskyDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command cholesky accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo));
                        nextStart+=replaceLength+distance;
                        nextEnd=nextStart+findLength;
                    }
                }
            }else throw new RuntimeException("Incorrect cholesky declaration");
        }
        return tt.toString();

    }
    public static String checkForLUDecomposition(String x){
        String expression=x,foo="";
        Pattern meek=Pattern.compile("lusolve\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
        Matcher matcher=meek.matcher(expression);
        StringBuffer tt=new StringBuffer(expression);
        int nextStart=0,nextEnd=0;
        int ink=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            ink++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        int k=0, j=0;
        if(x.contains("lusolve")){
            if(x.matches(".*lusolve\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\).*")){
                //Pattern meek=Pattern.compile("lusolve\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
                Matcher matcher_=meek.matcher(x);
                while(matcher_.find()){
                    int start=matcher_.start();
                    int end=matcher_.end();
                    String manipulate=matcher_.group().trim();
                    String mani_holder=manipulate;
                    manipulate=manipulate.replace("lusolve(", "");
                    manipulate=manipulate.substring(0, manipulate.length()-1);
                    int replaceLength=0,findLength=0;
                    j++;
                    int distance=acc.get(k);
                    k++;
                    
                    
                    
                    if(j==1){
                        findLength=mani_holder.length();
                        
                        
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        LUDecomposition A=new LUDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command lusolve accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
							
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        LUDecomposition A=new LUDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command lusolve accepts only two variables");
                        }else if(manipulate.contains("],")){
                            Pattern comma_meek=Pattern.compile("\\]\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\],");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+"]";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());


                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        LUDecomposition A=new LUDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command lusove accepts only two variables");
                        }else if(manipulate.contains(",[")){
                            Pattern comma_meek=Pattern.compile("\\,\\[");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\[");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="["+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        LUDecomposition A=new LUDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command lusolve accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        LUDecomposition A=new LUDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command lusolve accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(start,end,foo));

                        nextStart=start+(replaceLength)+distance;
                        //nextEnd=nextStart+findLength;
                    }
                    if(j>1){
                        findLength=mani_holder.length();
                        nextEnd=nextStart+findLength;
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        LUDecomposition A=new LUDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command lusolve accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        LUDecomposition A=new LUDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command lusolve accepts only two variables");
                        }else if(manipulate.contains("],")){
                            Pattern comma_meek=Pattern.compile("\\]\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\],");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+"]";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        LUDecomposition A=new LUDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command lusolve accepts only two variables");
                        }else if(manipulate.contains(",[")){
                            Pattern comma_meek=Pattern.compile("\\,\\[");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\[");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="["+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        LUDecomposition A=new LUDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command lusolve accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        LUDecomposition A=new LUDecomposition(jeep);
                                        double[][] hus=A.solve(jeep2).getArray();
					foo=Console.format(Console.evaluate(Console.format(hus)).getArray());
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command lusolve accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo));
                        nextStart+=replaceLength+distance;
                        nextEnd=nextStart+findLength;
                    }
                }
            }else throw new RuntimeException("Incorrect lusolve declaration");
        }
        return tt.toString();

    }
    public static String checkForGaussJordanTest(String x){
        String expression=x,foo="";
        Pattern meek=Pattern.compile("gausstest\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
        Matcher matcher=meek.matcher(expression);
        StringBuffer tt=new StringBuffer(expression);
        int nextStart=0,nextEnd=0;
        int ink=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            ink++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        int k=0, j=0;
        if(x.contains("gausstest")){
            if(x.matches(".*gausstest\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\).*")){
                //Pattern meek=Pattern.compile("gaussian\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
                Matcher matcher_=meek.matcher(x);
                while(matcher_.find()){
                    int start=matcher_.start();
                    int end=matcher_.end();
                    String manipulate=matcher_.group().trim();
                    String mani_holder=manipulate;
                    manipulate=manipulate.replace("gausstest(", "");
                    manipulate=manipulate.substring(0, manipulate.length()-1);
                    int replaceLength=0,findLength=0;
                    j++;
                    int distance=acc.get(k);
                    k++;
                    
                    
                    
                    if(j==1){
                        findLength=mani_holder.length();
                        
                        
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            foo=Console.format(Console.evaluate(test(jeep.getArray(),jeep2.getArray()[0])).getArray());
                                        }else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            foo=Console.format(Console.evaluate(test(jeep.getArray(),jeep2.getArray()[0])).getArray());
                                        }else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else if(manipulate.contains("],")){
                            Pattern comma_meek=Pattern.compile("\\]\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\],");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+"]";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());


                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            foo=Console.format(Console.evaluate(test(jeep.getArray(),jeep2.getArray()[0])).getArray());
                                        }else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else if(manipulate.contains(",[")){
                            Pattern comma_meek=Pattern.compile("\\,\\[");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\[");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="["+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            foo=Console.format(Console.evaluate(test(jeep.getArray(),jeep2.getArray()[0])).getArray());
                                        }else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            foo=Console.format(Console.evaluate(test(jeep.getArray(),jeep2.getArray()[0])).getArray());
                                        }else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                    /*
                                        -1.0	
                                        2.0	
                                        2.0	
                                    */
                                   //GaussianElimination.lsolve(jeep, b)

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(start,end,foo));

                        nextStart=start+(replaceLength)+distance;
                        //nextEnd=nextStart+findLength;
                    }
                    if(j>1){
                        findLength=mani_holder.length();
                        nextEnd=nextStart+findLength;
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            foo=Console.format(Console.evaluate(test(jeep.getArray(),jeep2.getArray()[0])).getArray());
                                        }else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            foo=Console.format(Console.evaluate(test(jeep.getArray(),jeep2.getArray()[0])).getArray());
                                        }else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else if(manipulate.contains("],")){
                            Pattern comma_meek=Pattern.compile("\\]\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\],");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+"]";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());


                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            foo=Console.format(Console.evaluate(test(jeep.getArray(),jeep2.getArray()[0])).getArray());
                                        }else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else if(manipulate.contains(",[")){
                            Pattern comma_meek=Pattern.compile("\\,\\[");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\[");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="["+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            foo=Console.format(Console.evaluate(test(jeep.getArray(),jeep2.getArray()[0])).getArray());
                                        }else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            foo=Console.format(Console.evaluate(test(jeep.getArray(),jeep2.getArray()[0])).getArray());
                                        }else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                    /*
                                        -1.0	
                                        2.0	
                                        2.0	
                                    */
                                   //GaussianElimination.lsolve(jeep, b)

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo));
                        nextStart+=replaceLength+distance;
                        nextEnd=nextStart+findLength;
                    }
                }
            }else throw new RuntimeException("Incorrect gausstest declaration");
        }
        return tt.toString();

    }
    public static String checkForGaussJordanElimination(String x){
        
        String expression=x,foo="";
        Pattern meek=Pattern.compile("gaussjordan\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
        Matcher matcher=meek.matcher(expression);
        StringBuffer tt=new StringBuffer(expression);
        int nextStart=0,nextEnd=0;
        int ink=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            ink++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        int k=0, j=0;
        if(x.contains("gaussjordan")){
            if(x.matches(".*gaussjordan\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\).*")){
                //Pattern meek=Pattern.compile("gaussian\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
                Matcher matcher_=meek.matcher(x);
                while(matcher_.find()){
                    int start=matcher_.start();
                    int end=matcher_.end();
                    String manipulate=matcher_.group().trim();
                    String mani_holder=manipulate;
                    manipulate=manipulate.replace("gaussjordan(", "");
                    manipulate=manipulate.substring(0, manipulate.length()-1);
                    int replaceLength=0,findLength=0;
                    j++;
                    int distance=acc.get(k);
                    k++;
                    
                    
                    
                    if(j==1){
                        findLength=mani_holder.length();
                        
                        
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            GaussJordanElimination gaussJordanElimination = new GaussJordanElimination(jeep.getArray(),jeep2.getArray()[0]);
                                            foo=Console.format(Console.evaluate(gaussJordanElimination.show()).getArray());
                                            gaussJordanElimination.solve();
										}else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            GaussJordanElimination gaussJordanElimination = new GaussJordanElimination(jeep.getArray(),jeep2.getArray()[0]);
                                            foo=Console.format(Console.evaluate(gaussJordanElimination.show()).getArray());
                                            gaussJordanElimination.solve();
										}else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else if(manipulate.contains("],")){
                            Pattern comma_meek=Pattern.compile("\\]\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\],");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+"]";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());


                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            GaussJordanElimination gaussJordanElimination = new GaussJordanElimination(jeep.getArray(),jeep2.getArray()[0]);
                                            foo=Console.format(Console.evaluate(gaussJordanElimination.show()).getArray());
                                            gaussJordanElimination.solve();
										}else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else if(manipulate.contains(",[")){
                            Pattern comma_meek=Pattern.compile("\\,\\[");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\[");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="["+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            GaussJordanElimination gaussJordanElimination = new GaussJordanElimination(jeep.getArray(),jeep2.getArray()[0]);
                                            foo=Console.format(Console.evaluate(gaussJordanElimination.show()).getArray());
                                            gaussJordanElimination.solve();
										}else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            GaussJordanElimination gaussJordanElimination = new GaussJordanElimination(jeep.getArray(),jeep2.getArray()[0]);
                                            foo=Console.format(Console.evaluate(gaussJordanElimination.show()).getArray());
                                            gaussJordanElimination.solve();
										}else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                    /*
                                        -1.0	
                                        2.0	
                                        2.0	
                                    */
                                   //GaussianElimination.lsolve(jeep, b)

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(start,end,foo));

                        nextStart=start+(replaceLength)+distance;
                        //nextEnd=nextStart+findLength;
                    }
                    if(j>1){
                        findLength=mani_holder.length();
                        nextEnd=nextStart+findLength;
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            GaussJordanElimination gaussJordanElimination = new GaussJordanElimination(jeep.getArray(),jeep2.getArray()[0]);
                                            foo=Console.format(Console.evaluate(gaussJordanElimination.show()).getArray());
                                            gaussJordanElimination.solve();
										}else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            GaussJordanElimination gaussJordanElimination = new GaussJordanElimination(jeep.getArray(),jeep2.getArray()[0]);
                                            foo=Console.format(Console.evaluate(gaussJordanElimination.show()).getArray());
                                            gaussJordanElimination.solve();
										}else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else if(manipulate.contains("],")){
                            Pattern comma_meek=Pattern.compile("\\]\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\],");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+"]";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());


                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            GaussJordanElimination gaussJordanElimination = new GaussJordanElimination(jeep.getArray(),jeep2.getArray()[0]);
                                            foo=Console.format(Console.evaluate(gaussJordanElimination.show()).getArray());
                                            gaussJordanElimination.solve();
										}else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else if(manipulate.contains(",[")){
                            Pattern comma_meek=Pattern.compile("\\,\\[");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\[");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="["+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            GaussJordanElimination gaussJordanElimination = new GaussJordanElimination(jeep.getArray(),jeep2.getArray()[0]);
                                            foo=Console.format(Console.evaluate(gaussJordanElimination.show()).getArray());
                                            gaussJordanElimination.solve();
										}else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            GaussJordanElimination gaussJordanElimination = new GaussJordanElimination(jeep.getArray(),jeep2.getArray()[0]);
                                            foo=Console.format(Console.evaluate(gaussJordanElimination.show()).getArray());
                                            gaussJordanElimination.solve();
										}else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                   //GaussianElimination.lsolve(jeep, b)

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo));
                        nextStart+=replaceLength+distance;
                        nextEnd=nextStart+findLength;
                    }
                }
            }else throw new RuntimeException("Incorrect gaussjordan declaration");
        }
        return tt.toString();

    }
    public static String  checkForGaussian(String x){
        String expression=x,foo="";
        Pattern meek=Pattern.compile("gaussian\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
        Matcher matcher=meek.matcher(expression);
        StringBuffer tt=new StringBuffer(expression);
        int nextStart=0,nextEnd=0;
        int ink=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            ink++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        int k=0, j=0;
        if(x.contains("gaussian")){
            if(x.matches(".*gaussian\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\).*")){
                //Pattern meek=Pattern.compile("gaussian\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
                Matcher matcher_=meek.matcher(x);
                while(matcher_.find()){
                    int start=matcher_.start();
                    int end=matcher_.end();
                    String manipulate=matcher_.group().trim();
                    String mani_holder=manipulate;
                    manipulate=manipulate.replace("gaussian(", "");
                    manipulate=manipulate.substring(0, manipulate.length()-1);
                    int replaceLength=0,findLength=0;
                    j++;
                    int distance=acc.get(k);
                    k++;
                    
                    
                    
                    if(j==1){
                        findLength=mani_holder.length();
                        
                        
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            double[][] tv={GaussianElimination.lsolve(jeep.getArray(), jeep2.getArray()[0])};
                                            foo=Console.format(new Matrix(tv).transpose().getArray());
                                        }else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            double[][] tv={GaussianElimination.lsolve(jeep.getArray(), jeep2.getArray()[0])};
                                            foo=Console.format(new Matrix(tv).transpose().getArray());
                                        }else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else if(manipulate.contains("],")){
                            Pattern comma_meek=Pattern.compile("\\]\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\],");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+"]";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());


                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            double[][] tv={GaussianElimination.lsolve(jeep.getArray(), jeep2.getArray()[0])};
                                            foo=Console.format(new Matrix(tv).transpose().getArray());
                                        }else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else if(manipulate.contains(",[")){
                            Pattern comma_meek=Pattern.compile("\\,\\[");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\[");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="["+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            double[][] tv={GaussianElimination.lsolve(jeep.getArray(), jeep2.getArray()[0])};
                                            foo=Console.format(new Matrix(tv).transpose().getArray());
                                        }else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            double[][] tv={GaussianElimination.lsolve(jeep.getArray(), jeep2.getArray()[0])};
                                            foo=Console.format(new Matrix(tv).transpose().getArray());
                                        }else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                    /*
                                        -1.0	
                                        2.0	
                                        2.0	
                                    */
                                   //GaussianElimination.lsolve(jeep, b)

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(start,end,foo));

                        nextStart=start+(replaceLength)+distance;
                        //nextEnd=nextStart+findLength;
                    }
                    if(j>1){
                        findLength=mani_holder.length();
                        nextEnd=nextStart+findLength;
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            double[][] tv={GaussianElimination.lsolve(jeep.getArray(), jeep2.getArray()[0])};
                                            foo=Console.format(new Matrix(tv).transpose().getArray());
                                        }else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            double[][] tv={GaussianElimination.lsolve(jeep.getArray(), jeep2.getArray()[0])};
                                            foo=Console.format(new Matrix(tv).transpose().getArray());
                                        }else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else if(manipulate.contains("],")){
                            Pattern comma_meek=Pattern.compile("\\]\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\],");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+"]";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());


                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            double[][] tv={GaussianElimination.lsolve(jeep.getArray(), jeep2.getArray()[0])};
                                            foo=Console.format(new Matrix(tv).transpose().getArray());
                                        }else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else if(manipulate.contains(",[")){
                            Pattern comma_meek=Pattern.compile("\\,\\[");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\[");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="["+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());

                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            double[][] tv={GaussianElimination.lsolve(jeep.getArray(), jeep2.getArray()[0])};
                                            foo=Console.format(new Matrix(tv).transpose().getArray());
                                        }else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep2.getArray().length==1 && (jeep.getArray()[0].length==jeep2.getArray()[0].length)){
                                            double[][] tv={GaussianElimination.lsolve(jeep.getArray(), jeep2.getArray()[0])};
                                            foo=Console.format(new Matrix(tv).transpose().getArray());
                                        }else{
                                            throw new RuntimeException(" Error in dimension, check guide");
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide");
                                    }

                                   //GaussianElimination.lsolve(jeep, b)

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command gausstest accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo));
                        nextStart+=replaceLength+distance;
                        nextEnd=nextStart+findLength;
                    }
                }
            }else throw new RuntimeException("Incorrect gaussian declaration");
        }
        return tt.toString();
    }
    private static String require_once(String expression){ 
        Pattern meek=Pattern.compile("[\\+\\-\\/\\*\\^]+");
        Matcher matcher_=meek.matcher(expression);
        Matcher matcher=meek.matcher(expression);
        StringBuffer tt=new StringBuffer(expression);
        int nextStart=0,nextEnd=0;
        int i=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            i++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        
        int k=0, j=0;
            while(matcher_.find()){
                int start=matcher_.start();
                int end=matcher_.end();
                String p=matcher_.group().trim();
                String foo;
                int replaceLength=0,findLength=0;
                j++;
                int distance=acc.get(k);
                k++;
                if(j==1){
                    if(start>0){
                        if(expression.substring(start-1, start).matches("[A-Za-z\\]]+")){
                              findLength=p.length();
                              foo=" "+p+" ";
                              replaceLength=foo.toString().length();
                              tt=new StringBuffer(tt.replace(start,end,foo.toString()));
                              nextStart=start+(replaceLength)+distance;
                        }
                    }
                }
                if(j>1){
                    if(start>0){
                        if(expression.substring(start-1, start).matches("[A-Za-z\\]]+")){
                            findLength=p.length();
                            nextEnd=nextStart+findLength;
                            foo=" "+p+" ";
                            replaceLength=foo.toString().length();
                            tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo.toString()));
                            nextStart+=replaceLength+distance;
                            nextEnd=nextStart+findLength;
                        }
                    }
                }
            }
        return tt.toString();
    }
    public static void updateValues(){
        Set<String> s=variableList.keySet();  
        for(String key : s){
                String values=variableList.get(key);
                if(values.endsWith("'")){
                    values="("+variableList.get(key)+")";
                }
                if(!values.startsWith("(")){
                        values="("+variableList.get(key);
                }
                if(!values.endsWith(")")){
                    values=variableList.get(key)+")";
                }
                
                variableList.put(key,values);
        }
        for(String key : s){
                String values=variableList.get(key);
                if(values.endsWith("'")){
                    values="("+variableList.get(key)+")";
                }
                
                if(!values.endsWith(")")){
                    values=variableList.get(key)+")";
                }
                
                if(!values.startsWith("(")){
                        values="("+variableList.get(key);
                }
                
                variableList.put(key,values);
        }
        
    }
    private static String check(String expression){
        expression=require_once(expression);
        StringBuffer p=null;
        expression=expression.toUpperCase();
        if(expression.contains("=")){
                String t=expression.substring(0, expression.indexOf("="));
                String value=expression.substring(expression.indexOf("=")+1,expression.length());
                p=new StringBuffer(value);
                boolean check=false;
                for(int i=0;i<p.length();i++){
                    String x=Character.toString(p.charAt(i));
                    check=x.matches("\\w+");
                    if(check)break;
                }
                if(check){
                    Pattern pattern=Pattern.compile("[A-Za-z]+");
                    Matcher matcher=pattern.matcher(value);
                    
                    while(matcher.find()){
                        int start=matcher.start();
                        int end=matcher.end();
                        String temp=matcher.group().trim();
                        for(String x:funcList.keySet()){
                                    if(funcList.containsKey(temp.toLowerCase())){
                                        p=p.replace(start, end, temp.toLowerCase());
                                    }
                             }
                    }
                    
                    Pattern pattern2=Pattern.compile("[A-Za-z]+");
                    Matcher match=pattern2.matcher(p.toString());
                    while(match.find()){
                            String temp=match.group().trim();
                            if(funcList.containsKey(temp)){
                            }
                            else if(variableList.containsKey(temp)){
                                p=new StringBuffer(p.toString().replace(temp, variableList.get(temp)));
                                variableName=t;
                           }
                           else{
                               throw new RuntimeException("Variable not declared '"+temp+"'");
                           }
                        }
                    
                    
                }
                variableName=t;
         }else{
            variableName="false";
            p=new StringBuffer(expression);
                boolean check=false;
                for(int i=0;i<p.length();i++){
                    String x=Character.toString(p.charAt(i));
                    check=x.matches("\\w+");
                    if(check)break;
                }
                if(check){
                    Pattern pattern=Pattern.compile("[A-Za-z]+");
                    Matcher matcher=pattern.matcher(expression);
                    
                    while(matcher.find()){
                        int start=matcher.start();
                        int end=matcher.end();
                        String temp=matcher.group().trim();
                        for(String x:funcList.keySet()){
                                    if(funcList.containsKey(temp.toLowerCase())){
                                        p=p.replace(start, end, temp.toLowerCase());
                                    }
                             }
                    }
                    
                    Pattern pattern2=Pattern.compile("[A-Za-z]+");
                    Matcher match=pattern2.matcher(p.toString());
                    while(match.find()){
                            String temp=match.group().trim();
                            if(funcList.containsKey(temp)){
                            }
                            else if(variableList.containsKey(temp)){
                                p=new StringBuffer(p.toString().replace(temp, variableList.get(temp)));
                           }
                           else{
                               throw new RuntimeException("Variable not declared '"+temp+"'");
                           }
                        }
                    
                    
                }
        }
       return p.toString(); 
    }  
    private static String manipulate(String exp){
        exp=check(exp);
        DecimalFormat df = new DecimalFormat("####0.00");
        Pattern  pattern=Pattern.compile("(((\\[|[0-9.])([0-9.;,\\/\\+\\-\\*\\[\\]]+)?([a-z0-9.;\\/\\,\\+\\-\\*\\(\\)]+|[a-z0-9.\\[\\];\\/\\,\\+\\-\\*\\(\\)]+)?([0-9.;,\\+\\-\\/\\*\\[\\]]+)?(\\]|[0-9.]))|[0-9.]+)");
        Matcher matcher_=pattern.matcher(exp);
        int i=0;
        while(matcher_.find()){
            i++;
        }
        if(i<=1){
            String temp=exp;
            if(temp.matches("^[A-Za-z0-9\\(\\.\\)\\*\\+\\-\\/]+$")){
                try{
                    exp=df.format(eval(exp));
                }catch(Exception e){
                    throw new RuntimeException("Unidentified error");
                }
            }
            
        }
        if(i>1){
            exp=checkers(exp);
        }
        return exp;
    }
    private static String recheck(String expression){
        expression=manipulate(expression);
        StringBuffer t=new StringBuffer(expression);
        StringBuffer tt=new StringBuffer(expression);
        Pattern  pattern=Pattern.compile("(((\\[|[0-9.])([0-9.;,\\/\\+\\-\\*\\[\\]\\(\\)]+)?([a-z0-9.;\\/\\,\\+\\-\\*\\(\\)]+|[a-z0-9.\\[\\];\\/\\,\\+\\-\\*\\(\\)]+)?([0-9.;,\\+\\-\\/\\*\\[\\]\\(\\)]+)?(\\]|[0-9.]))|[0-9.]+)");//(((\\[|[0-9.])([0-9.;,\\+\\-\\*\\[\\]]+)?([a-z0-9.;\\,\\+\\-\\*\\(\\)]+|[a-z0-9.\\[\\];\\,\\+\\-\\*\\(\\)]+)?([0-9.;,\\+\\-\\*\\[\\]]+)?(\\]|[0-9.]))|[0-9.]+)
        Matcher matcher=pattern.matcher(t);
        Matcher matcher_=pattern.matcher(t);
        int nextStart=0,nextEnd=0;
        int i=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            i++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        if(i>1){
            int k=0, j=0;
            while(matcher_.find()){
                String p=matcher_.group().trim();
                double[][] foo;
                int replaceLength=0,findLength=0;
                j++;
                int distance=acc.get(k);
                k++;
                if(j==1){
                    int start=matcher_.start();
                    int end=matcher_.end();
                    findLength=p.length();
                    foo=matricesGeneration(p);
                    replaceLength=foo.toString().length();
                    list.add(foo);
                    tt=new StringBuffer(tt.replace(start,end,foo.toString()));
                    
                    nextStart=start+(replaceLength)+distance;
                    //nextEnd=nextStart+findLength;
                }
                if(j>1){
                    findLength=p.length();
                    nextEnd=nextStart+findLength;
                    foo=matricesGeneration(p);
                    replaceLength=foo.toString().length();
                    list.add(foo);
                    tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo.toString()));
                    nextStart+=replaceLength+distance;
                    nextEnd=nextStart+findLength;  
                }
            }
        }else{
            while(matcher_.find()){
                String p=matcher_.group().trim();
                int start=matcher_.start();
                int end=matcher_.end();
                double[][] foo=matricesGeneration(p);
                list.add(foo);
                tt=new StringBuffer(tt.replace(start,end,foo.toString()));
            }
        }
        tt=new StringBuffer(checkers(tt.toString()));
        return tt.toString();
    }
    private static String checkers(String expression){
        Pattern patt=Pattern.compile("[\\)]{2}");
        Matcher matcher_=patt.matcher(expression);
        expression=expression.replace("))", ")");
        return expression;
    }
    private static double[][] matricesGeneration(String A){
        String[] array=A.split(";");
        String[] temp=array;
        String[] holder;
        String collect="";
        double[][] matrices = null;
        if(array.length==1){
            for(int i=0;i<array.length;i++){
                 collect+=array[i];
            }
            collect=collect.replace("[", "");
            collect=collect.replace("]", "");
            collect=collect.replace(" ", ",");
            holder=collect.split(",");
            matrices=new double[1][holder.length];
            for(int i=0;i<matrices.length;i++){
                for(int j=0;j<matrices[0].length;j++){
                    matrices[i][j]=eval(holder[j]);
                }
            }
        }else{
            for(int i=0;i<array.length;i++){
                 collect+=array[i]+"\n";
            }
            collect=collect.replace("[", "");
            collect=collect.replace("]", "");
            collect=collect.replace(" ", ",");
            holder=collect.split("\n");
            String[][] check=new String[holder.length][1];
            int len=0;
            for(int i=0;i<holder.length;i++){
                    check[i]=holder[i].split(",");
                    len=check[i].length;
            }
            boolean checker=false;
            for(int i=0;i<holder.length;i++){
                    if(len!=check[i].length){
                        throw new RuntimeException("Error in vertical dimension");
                    }else{
                        checker=true;
                    }
            }
            
            if(checker){
                matrices=new double[holder.length][len];
                    for(int i=0;i<matrices.length;i++){
                        for(int j=0;j<matrices[0].length;j++){
                            matrices[i][j]=eval(check[i][j]);
                        }
                    }
            }
            
            
        }
        return matrices;
    }
    public static String format(double[][] A){
        String form="[";
        for(int i=0;i<A.length;i++){
            form+="[";
            for(int j=0;j<A[0].length;j++){
                form+=""+A[i][j]+",";
            }
            form+="];";
        }
        form+="]";
        form=form.replace(";]", "]");
        form=form.replace(",]", "]");
        return form;
    }
    public static double eval(final String str) {
    return new Object() {
        int pos = -1, ch;

        void nextChar() {
            ch = (++pos < str.length()) ? str.charAt(pos) : -1;
        }

        boolean eat(int charToEat) {
            while (ch == ' ') nextChar();
            if (ch == charToEat) {
                nextChar();
                return true;
            }
            return false;
        }

        double parse() {
            nextChar();
            double x = parseExpression();
            if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
            return x;
        }

        // Grammar:
        // expression = term | expression `+` term | expression `-` term
        // term = factor | term `*` factor | term `/` factor
        // factor = `+` factor | `-` factor | `(` expression `)`
        //        | number | functionName factor | factor `^` factor

        double parseExpression() {
            double x = parseTerm();
            for (;;) {
                if      (eat('+')) x += parseTerm(); // addition
                else if (eat('-')) x -= parseTerm(); // subtraction
                else return x;
            }
        }

        double parseTerm() {
            double x = parseFactor();
            for (;;) {
                if      (eat('*')) x *= parseFactor(); // multiplication
                else if (eat('/')) x /= parseFactor(); // division
                else return x;
            }
        }

        double parseFactor() {
            if (eat('+')) return parseFactor(); // unary plus
            if (eat('-')) return -parseFactor(); // unary minus

            double x;
            int startPos = this.pos;
            if (eat('(')) { // parentheses
                x = parseExpression();
                eat(')');
            } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                x = Double.parseDouble(str.substring(startPos, this.pos));
            } else if (ch >= 'a' && ch <= 'z') { // functions
                while (ch >= 'a' && ch <= 'z') nextChar();
                String func = str.substring(startPos, this.pos);
                x = parseFactor();
                if (func.equals("sqrt")) x = Math.sqrt(x);
                else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                else if(func.equals("pi")) x=Math.PI;
                else if(func.equals("abs")) x=Math.abs(x);
                else if(func.equals("acos")) x=Math.acos(x);
                else if(func.equals("ulp")) x=Math.ulp(x);
                else if(func.equals("toRadians")) x=Math.toRadians(x);
                else if(func.equals("toIntExact")) x=Math.toIntExact((long)x);
                else if(func.equals("toDegrees")) x=Math.toDegrees(x);
                else if(func.equals("tanh")) x=Math.tanh(x);
                else if(func.equals("sinh")) x=Math.sinh(x);
                else if(func.equals("signum")) x=Math.signum(x);
                else if(func.equals("round")) x=Math.round(x);
                else if(func.equals("rint")) x=Math.rint(x);
                else if(func.equals("random")) x=Math.random();
                else if(func.equals("nextUp")) x=Math.nextUp(x);
                else if(func.equals("nextDown")) x=Math.nextDown(x);
                else if(func.equals("negateExact")) x=Math.negateExact((long)x);
                else if(func.equals("log1p")) x=Math.log1p(x);
                else if(func.equals("log")) x=Math.log10(x);
                else if(func.equals("ln")) x=Math.log(x);
                else if(func.equals("incrementExact")) x=Math.incrementExact((long)x);
                else if(func.equals("getExponent")) x=Math.getExponent(x);
                else if(func.equals("floor")) x=Math.floor(x);
                else if(func.equals("expm1")) x=Math.expm1(x);
                else if(func.equals("exp")) x=Math.exp(x);
                else if(func.equals("decrementExact")) x=Math.decrementExact((long)x);
                else if(func.equals("cosh")) x=Math.cosh(x);
                else if(func.equals("ceil")) x=Math.ceil(x);
                else if(func.equals("cbrt")) x=Math.cbrt(x);
                else if(func.equals("atan")) x=Math.atan(x);
                else if(func.equals("asin")) x=Math.asin(x);  
                else if(func.equals("e")) x=Math.E;
                else throw new RuntimeException("Unknown function: " + func);
            } else {
                throw new RuntimeException("Unexpected: " + (char)ch);
            }

            if(eat('^')) x = Math.pow(x, parseFactor()); // exponentiation
            if(eat('b')) x=Math.log10(x)/Math.log10(parseFactor());
            if(eat('!')) x=factorial(x);
            if(eat('√')) x=anyRoot(parseFactor(),x);
            
            return x;
        }
    }.parse();
}
    public static Matrix evaluate(final String expression) {
        String str=recheck(expression);
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            Matrix parse() {
                nextChar();
                Matrix x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            Matrix parseExpression() {
                Matrix x = parseTerm();
                for (;;) {
                    if      (eat('+')) x=x.plus(parseTerm()); // addition
                    else if (eat('-')) x=x.minus(parseTerm()); // subtraction
                    else return x;
                }
            }

            Matrix parseTerm() {
                Matrix x = parseFactor();
                for (;;) {
                    if      (eat('*')) x = x.times(parseFactor()) ; // multiplication
                    else if (eat('/')) x = x.arrayRightDivide(parseFactor()); // division
                    else return x;
                }
            }

            Matrix parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return parseFactor().uminus(); // unary minus

                Matrix x = null;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ( ch == '[') { // numbers
                    int i=0;
                    while (i++<12) nextChar();
                    x=new Matrix(list.remove());
                    //Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Matrix.sqrt(x);
                    else if (func.equals("sin")) x = Matrix.sin(x);
                    else if (func.equals("cos")) x = Matrix.cos(x);
                    else if (func.equals("tan")) x = Matrix.tan(x);
                    else if (func.equals("det")) x=new Matrix(new double[][]{{x.det()}});
                    else if (func.equals("inverse")) x=x.inverse();
                    else if (func.equals("rank")) x=new Matrix(new double[][]{{x.rank()}});
                    else if (func.equals("trace")) x=new Matrix(new double[][]{{x.trace()}});
                    else if (func.equals("eigenvect")) x=x.eig().getV();
                    else if (func.equals("eigenval")) x=x.eig().getD();
                    else if (func.equals("normone")) x=new Matrix(new double[][]{{x.norm1()}});
                    else if (func.equals("normtwo")) x=new Matrix(new double[][]{{x.norm2()}});
                    else if (func.equals("normf")) x=new Matrix(new double[][]{{x.normF()}});
                    else if (func.equals("norminf")) x=new Matrix(new double[][]{{x.normInf()}});
		    else if (func.equals("coldim")) x=new Matrix(new double[][]{{x.getColumnDimension()}});
                    else if (func.equals("rowdim")) x=new Matrix(new double[][]{{x.getRowDimension()}});
		    else if (func.equals("lowerfact")) x=x.chol().getL();
                    else if (func.equals("upperfact")) x=x.lu().getU();
                    else if (func.equals("holdervect")) x=x.qr().getH();
                    else if (func.equals("economyfact")) x=x.qr().getQ();
                    else if (func.equals("singmat")) x=x.svd().getS();
                    else if (func.equals("leftsing")) x=x.svd().getU();
                    else if (func.equals("rightsing")) x=x.svd().getV();
                    else if (func.equals("min")) x=Matrix.min(x);
                    else if (func.equals("max")) x=Matrix.max(x);
                    else if (func.equals("sort")) x=Matrix.sort(x);
                    else if (func.equals("sum")) x=Matrix.sum(x);
                    else if (func.equals("neg")) x=Matrix.neg(x);
                    else if (func.equals("pos")) x=Matrix.pos(x);
                    else if (func.equals("echelon")) x=ammend.rowSwap(x.getArray());
                    else if (func.equals("getu")) {
                        LUDecomposition A=new LUDecomposition (x);
                        x=A.getU();
                    }else if (func.equals("getl")) {
                        LUDecomposition A=new LUDecomposition (x);
                        x=A.getL();
                    }else if (func.equals("isnonsingular")) {
                        LUDecomposition A=new LUDecomposition (x);
                        if(A.isNonsingular()){
                            x=new Matrix(new double[][]{{1}});
                        }else{
                             x=new Matrix(new double[][]{{0}});
                        }
                    }
                    else if (func.equals("simplex")) {
                        try{
                            x=new Matrix(result_(x.getArray()));
                        }catch(Exception e){
                            throw new RuntimeException("Pivot Column: "+getpivot());
                        }
                    }else if(func.equals("gaussian")){
                        System.out.println(Matrix.display(x)+"\t"+Matrix.display(parseFactor()));
//                        if(eat(',')){
////                            x=new Matrix(new double[][]{lsolve(x.getArray(), parseFactor().getArray()[0])}).transpose();
//                              System.out.println(Matrix.display(x)+"\t"+Matrix.display(parseFactor()));
//                              System.out.println("yea");
//                        }else{
//                            System.out.println("no");
//                        }
                    }
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Matrix.pow(x, parseFactor()); // exponentiation
                if (eat('\'')) x=x.transpose();
                if (eat('~')) x=x.solve(parseFactor());
		if (eat('~')) x=x.solve(parseFactor());
                if (eat('&')) x=x.lu().solve(parseFactor());
                return x;
            }
        }.parse();
    }
    public static String decimal2Fraction(double decimal){
        int digitsAfterPoint = String.valueOf(decimal).length() - String.valueOf(decimal).indexOf('.') + 1;
        BigInteger numerator = BigInteger.valueOf((long)(decimal*Math.pow(10, digitsAfterPoint)));
        BigInteger deniminator = BigInteger.valueOf((long)(Math.pow(10, digitsAfterPoint)));
        int gcd = numerator.gcd(deniminator).intValue();
        if(gcd > 1){
            return String.valueOf(numerator.intValue()/gcd) + "/" + String.valueOf(deniminator.intValue()/gcd);
        }
        else{
            return  String.valueOf(numerator) + "/" + String.valueOf(deniminator);
        }
    }
    public static long factorial(double num){
        long product = 1;
        for(int i = 1; i <= num; i++)
            product *= i;
        
        return product;
    }
    public static double anyRoot(double number, double power){
        double pow = 1/power;
        double calc = Math.pow(number, pow);
        return calc;
    }
    private static String checkForRand(String x){
        String expression=x,foo="";
        Pattern meek=Pattern.compile("rand\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\)");
        Matcher matcher=meek.matcher(expression);
        StringBuffer tt=new StringBuffer(expression);
        int nextStart=0,nextEnd=0;
        int ink=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            ink++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        int k=0, j=0;
        if(x.contains("rand")){
            if(x.matches(".*rand\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\).*")){
                //Pattern meek=Pattern.compile("gaussian\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
                Matcher matcher_=meek.matcher(x);
                while(matcher_.find()){
                    int start=matcher_.start();
                    int end=matcher_.end();
                    String manipulate=matcher_.group().trim();
                    String mani_holder=manipulate;
                    manipulate=manipulate.replace("rand(", "");
                    manipulate=manipulate.substring(0, manipulate.length()-1);
                    int replaceLength=0,findLength=0;
                    j++;
                    int distance=acc.get(k);
                    k++;
                    
                    
                    
                    if(j==1){
                        findLength=mani_holder.length();
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.rand(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on rand");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on rand");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, rand accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.rand(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on rand");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on rand");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, rand accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.rand(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on rand");
                                               }
                                        }
                                        
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on rand");
                                    }
                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, rand accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(start,end,foo));

                        nextStart=start+(replaceLength)+distance;
                        //nextEnd=nextStart+findLength;
                    }
                    if(j>1){
                        findLength=mani_holder.length();
                        nextEnd=nextStart+findLength;
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.rand(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on rand");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on rand");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, rand accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.rand(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on rand");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on rand");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, rand accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    System.out.println(y+"\t"+z);
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    System.out.println(y+"\t"+z);
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.rand(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on rand");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on rand");
                                    }

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, rand accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo));
                        nextStart+=replaceLength+distance;
                        nextEnd=nextStart+findLength;
                    }
                }
            }else throw new RuntimeException("Incorrect rand declaration");
        }
        return tt.toString();

    }
    private static String checkForIdentity(String x){
        String expression=x,foo="";
        Pattern meek=Pattern.compile("idty\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\)");
        Matcher matcher=meek.matcher(expression);
        StringBuffer tt=new StringBuffer(expression);
        int nextStart=0,nextEnd=0;
        int ink=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            ink++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        int k=0, j=0;
        if(x.contains("idty")){
            if(x.matches(".*idty\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\).*")){
                //Pattern meek=Pattern.compile("gaussian\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
                Matcher matcher_=meek.matcher(x);
                while(matcher_.find()){
                    int start=matcher_.start();
                    int end=matcher_.end();
                    String manipulate=matcher_.group().trim();
                    String mani_holder=manipulate;
                    manipulate=manipulate.replace("idty(", "");
                    manipulate=manipulate.substring(0, manipulate.length()-1);
                    int replaceLength=0,findLength=0;
                    j++;
                    int distance=acc.get(k);
                    k++;
                    
                    
                    
                    if(j==1){
                        findLength=mani_holder.length();
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.idty(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on idty");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on idty");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, idty accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.idty(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on idty");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on rand");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, idty accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.idty(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on idty");
                                               }
                                        }
                                        
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on idty");
                                    }
                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, idty accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(start,end,foo));

                        nextStart=start+(replaceLength)+distance;
                        //nextEnd=nextStart+findLength;
                    }
                    if(j>1){
                        findLength=mani_holder.length();
                        nextEnd=nextStart+findLength;
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.idty(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on idty");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on idty");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, idty accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.idty(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on idty");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on idty");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, idty accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.idty(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on idty");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on idty");
                                    }

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, idty accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo));
                        nextStart+=replaceLength+distance;
                        nextEnd=nextStart+findLength;
                    }
                }
            }else throw new RuntimeException("Incorrect rand declaration");
        }
        return tt.toString();

    }
    private static String checkForOnes(String x){
        String expression=x,foo="";
        Pattern meek=Pattern.compile("ones\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\)");
        Matcher matcher=meek.matcher(expression);
        StringBuffer tt=new StringBuffer(expression);
        int nextStart=0,nextEnd=0;
        int ink=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            ink++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        int k=0, j=0;
        if(x.contains("ones")){
            if(x.matches(".*ones\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\).*")){
                //Pattern meek=Pattern.compile("gaussian\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
                Matcher matcher_=meek.matcher(x);
                while(matcher_.find()){
                    int start=matcher_.start();
                    int end=matcher_.end();
                    String manipulate=matcher_.group().trim();
                    String mani_holder=manipulate;
                    manipulate=manipulate.replace("ones(", "");
                    manipulate=manipulate.substring(0, manipulate.length()-1);
                    int replaceLength=0,findLength=0;
                    j++;
                    int distance=acc.get(k);
                    k++;
                    
                    
                    
                    if(j==1){
                        findLength=mani_holder.length();
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.ones(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on ones");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on ones");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, ones accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.ones(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on ones");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on rand");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, ones accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.ones(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on ones");
                                               }
                                        }
                                        
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on ones");
                                    }
                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, ones accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(start,end,foo));

                        nextStart=start+(replaceLength)+distance;
                        //nextEnd=nextStart+findLength;
                    }
                    if(j>1){
                        findLength=mani_holder.length();
                        nextEnd=nextStart+findLength;
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.ones(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on ones");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on ones");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, ones accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.ones(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on ones");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on ones");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, ones accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.ones(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on ones");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on ones");
                                    }

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, ones accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo));
                        nextStart+=replaceLength+distance;
                        nextEnd=nextStart+findLength;
                    }
                }
            }else throw new RuntimeException("Incorrect rand declaration");
        }
        return tt.toString();

    }
    private static String checkForTens(String x){
        String expression=x,foo="";
        Pattern meek=Pattern.compile("tens\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\)");
        Matcher matcher=meek.matcher(expression);
        StringBuffer tt=new StringBuffer(expression);
        int nextStart=0,nextEnd=0;
        int ink=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            ink++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        int k=0, j=0;
        if(x.contains("tens")){
            if(x.matches(".*tens\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\).*")){
                //Pattern meek=Pattern.compile("gaussian\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
                Matcher matcher_=meek.matcher(x);
                while(matcher_.find()){
                    int start=matcher_.start();
                    int end=matcher_.end();
                    String manipulate=matcher_.group().trim();
                    String mani_holder=manipulate;
                    manipulate=manipulate.replace("tens(", "");
                    manipulate=manipulate.substring(0, manipulate.length()-1);
                    int replaceLength=0,findLength=0;
                    j++;
                    int distance=acc.get(k);
                    k++;
                    
                    
                    
                    if(j==1){
                        findLength=mani_holder.length();
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.tens(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on tens");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on tens");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, tens accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.tens(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on tens");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on rand");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, tens accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.tens(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on tens");
                                               }
                                        }
                                        
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on tens");
                                    }
                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, tens accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(start,end,foo));

                        nextStart=start+(replaceLength)+distance;
                        //nextEnd=nextStart+findLength;
                    }
                    if(j>1){
                        findLength=mani_holder.length();
                        nextEnd=nextStart+findLength;
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.tens(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on tens");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on tens");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, tens accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.tens(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on tens");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on tens");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, tens accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.tens(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on tens");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on tens");
                                    }

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, tens accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo));
                        nextStart+=replaceLength+distance;
                        nextEnd=nextStart+findLength;
                    }
                }
            }else throw new RuntimeException("Incorrect rand declaration");
        }
        return tt.toString();

    }
    private static String checkForZeros(String x){
        String expression=x,foo="";
        Pattern meek=Pattern.compile("zeros\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\)");
        Matcher matcher=meek.matcher(expression);
        StringBuffer tt=new StringBuffer(expression);
        int nextStart=0,nextEnd=0;
        int ink=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            ink++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        int k=0, j=0;
        if(x.contains("zeros")){
            if(x.matches(".*zeros\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\).*")){
                //Pattern meek=Pattern.compile("gaussian\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
                Matcher matcher_=meek.matcher(x);
                while(matcher_.find()){
                    int start=matcher_.start();
                    int end=matcher_.end();
                    String manipulate=matcher_.group().trim();
                    String mani_holder=manipulate;
                    manipulate=manipulate.replace("zeros(", "");
                    manipulate=manipulate.substring(0, manipulate.length()-1);
                    int replaceLength=0,findLength=0;
                    j++;
                    int distance=acc.get(k);
                    k++;
                    
                    
                    
                    if(j==1){
                        findLength=mani_holder.length();
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.zeros(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on zeros");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on zeros");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, zeros accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.zeros(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on zeros");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on rand");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, zeros accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.zeros(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on zeros");
                                               }
                                        }
                                        
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on zeros");
                                    }
                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, zeros accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(start,end,foo));

                        nextStart=start+(replaceLength)+distance;
                        //nextEnd=nextStart+findLength;
                    }
                    if(j>1){
                        findLength=mani_holder.length();
                        nextEnd=nextStart+findLength;
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.zeros(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on zeros");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on zeros");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, zeros accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.zeros(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on zeros");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on zeros");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, zeros accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.zeros(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on zeros");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on zeros");
                                    }

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, zeros accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo));
                        nextStart+=replaceLength+distance;
                        nextEnd=nextStart+findLength;
                    }
                }
            }else throw new RuntimeException("Incorrect rand declaration");
        }
        return tt.toString();

    }
    private static String checkForTwos(String x){
        String expression=x,foo="";
        Pattern meek=Pattern.compile("twos\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\)");
        Matcher matcher=meek.matcher(expression);
        StringBuffer tt=new StringBuffer(expression);
        int nextStart=0,nextEnd=0;
        int ink=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            ink++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        int k=0, j=0;
        if(x.contains("twos")){
            if(x.matches(".*twos\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\).*")){
                //Pattern meek=Pattern.compile("gaussian\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
                Matcher matcher_=meek.matcher(x);
                while(matcher_.find()){
                    int start=matcher_.start();
                    int end=matcher_.end();
                    String manipulate=matcher_.group().trim();
                    String mani_holder=manipulate;
                    manipulate=manipulate.replace("twos(", "");
                    manipulate=manipulate.substring(0, manipulate.length()-1);
                    int replaceLength=0,findLength=0;
                    j++;
                    int distance=acc.get(k);
                    k++;
                    
                    
                    
                    if(j==1){
                        findLength=mani_holder.length();
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.twos(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on twos");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on twos");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, twos accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.twos(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on twos");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on rand");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, twos accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.twos(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on twos");
                                               }
                                        }
                                        
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on twos");
                                    }
                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, twos accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(start,end,foo));

                        nextStart=start+(replaceLength)+distance;
                        //nextEnd=nextStart+findLength;
                    }
                    if(j>1){
                        findLength=mani_holder.length();
                        nextEnd=nextStart+findLength;
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.twos(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on twos");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on twos");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, twos accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.twos(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on twos");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on twos");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, twos accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.twos(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on twos");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on twos");
                                    }

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, twos accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo));
                        nextStart+=replaceLength+distance;
                        nextEnd=nextStart+findLength;
                    }
                }
            }else throw new RuntimeException("Incorrect rand declaration");
        }
        return tt.toString();

    }
    private static String checkForThrees(String x){
        String expression=x,foo="";
        Pattern meek=Pattern.compile("threes\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\)");
        Matcher matcher=meek.matcher(expression);
        StringBuffer tt=new StringBuffer(expression);
        int nextStart=0,nextEnd=0;
        int ink=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            ink++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        int k=0, j=0;
        if(x.contains("threes")){
            if(x.matches(".*threes\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\).*")){
                //Pattern meek=Pattern.compile("gaussian\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
                Matcher matcher_=meek.matcher(x);
                while(matcher_.find()){
                    int start=matcher_.start();
                    int end=matcher_.end();
                    String manipulate=matcher_.group().trim();
                    String mani_holder=manipulate;
                    manipulate=manipulate.replace("threes(", "");
                    manipulate=manipulate.substring(0, manipulate.length()-1);
                    int replaceLength=0,findLength=0;
                    j++;
                    int distance=acc.get(k);
                    k++;
                    
                    
                    
                    if(j==1){
                        findLength=mani_holder.length();
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.threes(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on threes");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on threes");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, threes accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.threes(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on threes");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on rand");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, threes accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.threes(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on threes");
                                               }
                                        }
                                        
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on threes");
                                    }
                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, threes accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(start,end,foo));

                        nextStart=start+(replaceLength)+distance;
                        //nextEnd=nextStart+findLength;
                    }
                    if(j>1){
                        findLength=mani_holder.length();
                        nextEnd=nextStart+findLength;
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.threes(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on threes");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on threes");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, threes accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.threes(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on threes");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on threes");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, threes accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.threes(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on threes");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on threes");
                                    }

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, threes accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo));
                        nextStart+=replaceLength+distance;
                        nextEnd=nextStart+findLength;
                    }
                }
            }else throw new RuntimeException("Incorrect rand declaration");
        }
        return tt.toString();

    }
    private static String checkForFours(String x){
        String expression=x,foo="";
        Pattern meek=Pattern.compile("fours\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\)");
        Matcher matcher=meek.matcher(expression);
        StringBuffer tt=new StringBuffer(expression);
        int nextStart=0,nextEnd=0;
        int ink=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            ink++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        int k=0, j=0;
        if(x.contains("fours")){
            if(x.matches(".*fours\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\).*")){
                //Pattern meek=Pattern.compile("gaussian\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
                Matcher matcher_=meek.matcher(x);
                while(matcher_.find()){
                    int start=matcher_.start();
                    int end=matcher_.end();
                    String manipulate=matcher_.group().trim();
                    String mani_holder=manipulate;
                    manipulate=manipulate.replace("fours(", "");
                    manipulate=manipulate.substring(0, manipulate.length()-1);
                    int replaceLength=0,findLength=0;
                    j++;
                    int distance=acc.get(k);
                    k++;
                    
                    
                    
                    if(j==1){
                        findLength=mani_holder.length();
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.fours(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on fours");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on fours");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, fours accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.fours(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on fours");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on rand");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, fours accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.fours(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on fours");
                                               }
                                        }
                                        
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on fours");
                                    }
                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, fours accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(start,end,foo));

                        nextStart=start+(replaceLength)+distance;
                        //nextEnd=nextStart+findLength;
                    }
                    if(j>1){
                        findLength=mani_holder.length();
                        nextEnd=nextStart+findLength;
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.fours(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on fours");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on fours");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, fours accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.fours(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on fours");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on fours");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, fours accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.fours(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on fours");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on fours");
                                    }

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, fours accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo));
                        nextStart+=replaceLength+distance;
                        nextEnd=nextStart+findLength;
                    }
                }
            }else throw new RuntimeException("Incorrect rand declaration");
        }
        return tt.toString();

    }
    private static String checkForFives(String x){
        String expression=x,foo="";
        Pattern meek=Pattern.compile("fives\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\)");
        Matcher matcher=meek.matcher(expression);
        StringBuffer tt=new StringBuffer(expression);
        int nextStart=0,nextEnd=0;
        int ink=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            ink++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        int k=0, j=0;
        if(x.contains("fives")){
            if(x.matches(".*fives\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\).*")){
                //Pattern meek=Pattern.compile("gaussian\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
                Matcher matcher_=meek.matcher(x);
                while(matcher_.find()){
                    int start=matcher_.start();
                    int end=matcher_.end();
                    String manipulate=matcher_.group().trim();
                    String mani_holder=manipulate;
                    manipulate=manipulate.replace("fives(", "");
                    manipulate=manipulate.substring(0, manipulate.length()-1);
                    int replaceLength=0,findLength=0;
                    j++;
                    int distance=acc.get(k);
                    k++;
                    
                    
                    
                    if(j==1){
                        findLength=mani_holder.length();
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.fives(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on fives");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on fives");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, fives accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.fives(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on fives");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on rand");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, fives accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.fives(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on fives");
                                               }
                                        }
                                        
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on fives");
                                    }
                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, fives accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(start,end,foo));

                        nextStart=start+(replaceLength)+distance;
                        //nextEnd=nextStart+findLength;
                    }
                    if(j>1){
                        findLength=mani_holder.length();
                        nextEnd=nextStart+findLength;
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.fives(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on fives");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on fives");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, fives accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.fives(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on fives");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on fives");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, fives accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.fives(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on fives");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on fives");
                                    }

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, fives accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo));
                        nextStart+=replaceLength+distance;
                        nextEnd=nextStart+findLength;
                    }
                }
            }else throw new RuntimeException("Incorrect rand declaration");
        }
        return tt.toString();

    }
    private static String checkForSixs(String x){
        String expression=x,foo="";
        Pattern meek=Pattern.compile("sixs\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\)");
        Matcher matcher=meek.matcher(expression);
        StringBuffer tt=new StringBuffer(expression);
        int nextStart=0,nextEnd=0;
        int ink=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            ink++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        int k=0, j=0;
        if(x.contains("sixs")){
            if(x.matches(".*sixs\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\).*")){
                //Pattern meek=Pattern.compile("gaussian\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
                Matcher matcher_=meek.matcher(x);
                while(matcher_.find()){
                    int start=matcher_.start();
                    int end=matcher_.end();
                    String manipulate=matcher_.group().trim();
                    String mani_holder=manipulate;
                    manipulate=manipulate.replace("sixs(", "");
                    manipulate=manipulate.substring(0, manipulate.length()-1);
                    int replaceLength=0,findLength=0;
                    j++;
                    int distance=acc.get(k);
                    k++;
                    
                    
                    
                    if(j==1){
                        findLength=mani_holder.length();
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.sixs(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on sixs");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on sixs");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, sixs accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.sixs(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on sixs");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on rand");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, sixs accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.sixs(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on sixs");
                                               }
                                        }
                                        
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on sixs");
                                    }
                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, sixs accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(start,end,foo));

                        nextStart=start+(replaceLength)+distance;
                        //nextEnd=nextStart+findLength;
                    }
                    if(j>1){
                        findLength=mani_holder.length();
                        nextEnd=nextStart+findLength;
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.sixs(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on sixs");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on sixs");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, sixs accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.sixs(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on sixs");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on sixs");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, sixs accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.sixs(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on sixs");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on sixs");
                                    }

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, sixs accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo));
                        nextStart+=replaceLength+distance;
                        nextEnd=nextStart+findLength;
                    }
                }
            }else throw new RuntimeException("Incorrect rand declaration");
        }
        return tt.toString();

    }
    private static String checkForSevens(String x){
        String expression=x,foo="";
        Pattern meek=Pattern.compile("sevens\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\)");
        Matcher matcher=meek.matcher(expression);
        StringBuffer tt=new StringBuffer(expression);
        int nextStart=0,nextEnd=0;
        int ink=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            ink++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        int k=0, j=0;
        if(x.contains("sevens")){
            if(x.matches(".*sevens\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\).*")){
                //Pattern meek=Pattern.compile("gaussian\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
                Matcher matcher_=meek.matcher(x);
                while(matcher_.find()){
                    int start=matcher_.start();
                    int end=matcher_.end();
                    String manipulate=matcher_.group().trim();
                    String mani_holder=manipulate;
                    manipulate=manipulate.replace("sevens(", "");
                    manipulate=manipulate.substring(0, manipulate.length()-1);
                    int replaceLength=0,findLength=0;
                    j++;
                    int distance=acc.get(k);
                    k++;
                    
                    
                    
                    if(j==1){
                        findLength=mani_holder.length();
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.sevens(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on sevens");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on sevens");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, sevens accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.sevens(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on sevens");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on rand");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, sevens accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.sevens(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on sevens");
                                               }
                                        }
                                        
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on sevens");
                                    }
                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, sevens accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(start,end,foo));

                        nextStart=start+(replaceLength)+distance;
                        //nextEnd=nextStart+findLength;
                    }
                    if(j>1){
                        findLength=mani_holder.length();
                        nextEnd=nextStart+findLength;
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.sevens(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on sevens");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on sevens");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, sevens accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.sevens(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on sevens");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on sevens");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, sevens accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.sevens(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on sevens");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on sevens");
                                    }

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, sevens accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo));
                        nextStart+=replaceLength+distance;
                        nextEnd=nextStart+findLength;
                    }
                }
            }else throw new RuntimeException("Incorrect rand declaration");
        }
        return tt.toString();

    }
    private static String checkForEights(String x){
        String expression=x,foo="";
        Pattern meek=Pattern.compile("eights\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\)");
        Matcher matcher=meek.matcher(expression);
        StringBuffer tt=new StringBuffer(expression);
        int nextStart=0,nextEnd=0;
        int ink=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            ink++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        int k=0, j=0;
        if(x.contains("eights")){
            if(x.matches(".*eights\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\).*")){
                //Pattern meek=Pattern.compile("gaussian\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
                Matcher matcher_=meek.matcher(x);
                while(matcher_.find()){
                    int start=matcher_.start();
                    int end=matcher_.end();
                    String manipulate=matcher_.group().trim();
                    String mani_holder=manipulate;
                    manipulate=manipulate.replace("eights(", "");
                    manipulate=manipulate.substring(0, manipulate.length()-1);
                    int replaceLength=0,findLength=0;
                    j++;
                    int distance=acc.get(k);
                    k++;
                    
                    
                    
                    if(j==1){
                        findLength=mani_holder.length();
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.eights(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on eights");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on eights");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, eights accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.eights(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on eights");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on rand");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, eights accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.eights(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on eights");
                                               }
                                        }
                                        
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on eights");
                                    }
                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, eights accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(start,end,foo));

                        nextStart=start+(replaceLength)+distance;
                        //nextEnd=nextStart+findLength;
                    }
                    if(j>1){
                        findLength=mani_holder.length();
                        nextEnd=nextStart+findLength;
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.eights(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on eights");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on eights");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, eights accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.eights(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on eights");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on eights");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, eights accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.eights(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on eights");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on eights");
                                    }

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, eights accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo));
                        nextStart+=replaceLength+distance;
                        nextEnd=nextStart+findLength;
                    }
                }
            }else throw new RuntimeException("Incorrect rand declaration");
        }
        return tt.toString();

    }
    private static String checkForNines(String x){
        String expression=x,foo="";
        Pattern meek=Pattern.compile("nines\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\)");
        Matcher matcher=meek.matcher(expression);
        StringBuffer tt=new StringBuffer(expression);
        int nextStart=0,nextEnd=0;
        int ink=0;
        LinkedList<Integer> starter=new LinkedList<>();
        LinkedList<Integer> ender=new LinkedList<>();
        while(matcher.find()){
            int start=matcher.start();
            int end=matcher.end();
            starter.add(start);
            ender.add(end);
            ink++;
        }
        LinkedList<Integer> acc=new LinkedList<>();
        for(int ww=0;ww<starter.size()-1;ww++){
            acc.add(Math.abs(starter.get(ww+1)-ender.get(ww)));
        }
        acc.add(0);
        int k=0, j=0;
        if(x.contains("nines")){
            if(x.matches(".*nines\\([A-Za-z0-9\\.\\*\\-\\+\\/\\(\\)\\,]+\\).*")){
                //Pattern meek=Pattern.compile("gaussian\\([A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\,[A-Za-z0-9\\.\\*\\-\\+\\/\\[\\]\\(\\)\\;\\,]+\\)");
                Matcher matcher_=meek.matcher(x);
                while(matcher_.find()){
                    int start=matcher_.start();
                    int end=matcher_.end();
                    String manipulate=matcher_.group().trim();
                    String mani_holder=manipulate;
                    manipulate=manipulate.replace("nines(", "");
                    manipulate=manipulate.substring(0, manipulate.length()-1);
                    int replaceLength=0,findLength=0;
                    j++;
                    int distance=acc.get(k);
                    k++;
                    
                    
                    
                    if(j==1){
                        findLength=mani_holder.length();
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.nines(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on nines");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on nines");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, nines accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.nines(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on nines");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on rand");
                                    }


                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, nines accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.nines(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on nines");
                                               }
                                        }
                                        
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on nines");
                                    }
                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, nines accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(start,end,foo));

                        nextStart=start+(replaceLength)+distance;
                        //nextEnd=nextStart+findLength;
                    }
                    if(j>1){
                        findLength=mani_holder.length();
                        nextEnd=nextStart+findLength;
                        
                        if(manipulate.contains(",(")){
                            Pattern comma_meek=Pattern.compile("\\,\\(");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split(",\\(");
                                    String y=com[0];
                                    String z=com[1];
                                    String temp="("+z;
                                    z=temp;
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.nines(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on nines");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on nines");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, nines accepts only two variables");
                        }else if(manipulate.contains("),")){
                            Pattern comma_meek=Pattern.compile("\\)\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                 try{
                                    String[] com=manipulate.split("\\),");
                                    String y=com[0];
                                    String z=com[1];
                                    y=y+")";
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.nines(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on nines");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on nines");
                                    }

                                 }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error on line near ,");
                                }
                            }
                            else throw new RuntimeException("Incorrect command, nines accepts only two variables");
                        }else{
                            Pattern comma_meek=Pattern.compile("\\,");
                            Matcher comma_matcher_=comma_meek.matcher(manipulate);
                            int i=0;
                            while(comma_matcher_.find()){
                                i++;
                            }
                            if(i==1){
                                try{
                                    String[] com=manipulate.split(",");
                                    String y=com[0];
                                    String z=com[1];
                                    y=format(evaluate(y).getArray());
                                    z=format(evaluate(z).getArray());
                                    try{
                                        Matrix jeep=evaluate(y);
                                        Matrix jeep2=evaluate(z);
                                        
                                        if(jeep.getArray().length>1 || jeep.getArray()[0].length>1 || jeep2.getArray().length>1 || jeep2.getArray()[0].length>1){
                                                throw new RuntimeException("Must be a 1X1 matrix");
                                        }else{
                                               try{
                                                   foo=format(Matrix.nines(jeep,jeep2).getArray());
                                               }catch(Exception tp){
                                                   throw new RuntimeException("Error check guide for help on nines");
                                               }
                                        }
                                    }catch(Exception pp){
                                        throw new RuntimeException("Error check guide for help on nines");
                                    }

                                }catch(Exception ecp){
                                    throw new RuntimeException(ecp.getMessage()+" Error one line near ,");
                                }

                            }else throw new RuntimeException("Incorrect command, nines accepts only two variables");
                        }
                        
                        
                        
                        replaceLength=foo.length();
                        tt=new StringBuffer(tt.replace(nextStart,nextEnd,foo));
                        nextStart+=replaceLength+distance;
                        nextEnd=nextStart+findLength;
                    }
                }
            }else throw new RuntimeException("Incorrect rand declaration");
        }
        return tt.toString();

    }
    
}

