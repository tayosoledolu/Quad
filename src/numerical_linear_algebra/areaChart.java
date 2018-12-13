/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numerical_linear_algebra;



import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
 
public class areaChart {
    double[][] x,y;
    Side sideX,sideY;
    String portfolio,xLabel,yLabel,xAxisPos,yAxisPos,title;
    boolean symbol; 
    public areaChart(double[][] x,String portfolio,String xLabel,String yLabel,String xAxisPos,String yAxisPos,String symbol,String title){
        this.x=x;
        this.portfolio=portfolio;
        this.xLabel=xLabel;
        this.yLabel=yLabel;
        this.xAxisPos=xAxisPos;
        this.yAxisPos=yAxisPos;
        if(symbol.equalsIgnoreCase("true")){
            this.symbol=true;
        }else{
            this.symbol=false;
        }
        if(xAxisPos.equalsIgnoreCase("right"))sideX=Side.RIGHT;
        else if(xAxisPos.equalsIgnoreCase("bottom"))sideX=Side.BOTTOM;
        else if(xAxisPos.equalsIgnoreCase("left"))sideX=Side.LEFT;
        else if(xAxisPos.equalsIgnoreCase("top"))sideX=Side.TOP;
        
        if(yAxisPos.equalsIgnoreCase("right"))sideY=Side.RIGHT;
        else if(yAxisPos.equalsIgnoreCase("bottom"))sideY=Side.BOTTOM;
        else if(yAxisPos.equalsIgnoreCase("left"))sideY=Side.LEFT;
        else if(yAxisPos.equalsIgnoreCase("top"))sideY=Side.TOP;
        
        this.title=title;
        show(this.x);
    }
    
 
public areaChart(double[][] x,double[][] y,String portfolio,String xLabel,String yLabel,String xAxisPos,String yAxisPos,String symbol,String title){
        this.x=x;
        this.y=y;
        this.portfolio=portfolio;
        this.xLabel=xLabel;
        this.yLabel=yLabel;
        this.xAxisPos=xAxisPos;
        this.yAxisPos=yAxisPos;
        if(symbol.equalsIgnoreCase("true")){
            this.symbol=true;
        }else{
            this.symbol=false;
        }
        if(xAxisPos.equalsIgnoreCase("right"))sideX=Side.RIGHT;
        else if(xAxisPos.equalsIgnoreCase("bottom"))sideX=Side.BOTTOM;
        else if(xAxisPos.equalsIgnoreCase("left"))sideX=Side.LEFT;
        else if(xAxisPos.equalsIgnoreCase("top"))sideX=Side.TOP;
        
        if(yAxisPos.equalsIgnoreCase("right"))sideY=Side.RIGHT;
        else if(yAxisPos.equalsIgnoreCase("bottom"))sideY=Side.BOTTOM;
        else if(yAxisPos.equalsIgnoreCase("left"))sideY=Side.LEFT;
        else if(yAxisPos.equalsIgnoreCase("top"))sideY=Side.TOP;
        
        this.title=title;
        show(this.x,this.y);
    }
 public void show(double[][] x){
        Stage stage=new Stage();
        stage.setTitle("Chart");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
         xAxis.setLabel(xLabel);
         yAxis.setLabel(yLabel);
        final AreaChart<Number,Number> lineChart = 
                new AreaChart<Number,Number>(xAxis,yAxis);
       
        lineChart.setTitle(title);
        
        for(int i=0;i<x[0].length;i++){
            XYChart.Series seriex = new XYChart.Series();
            if(portfolio.length()>0){
                seriex.setName(portfolio);
            }
            for(int j=0;j<x.length;j++){
                seriex.getData().add(new XYChart.Data(j,x[i][j]));
            }
            lineChart.getData().add(seriex);
        }
        
        lineChart.setCreateSymbols(symbol);
        xAxis.setSide(sideX);
        yAxis.setSide(sideY);
        
        Scene scene  = new Scene(lineChart,800,600);       
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/quad.JPG")));
        stage.setScene(scene);
        stage.show();
 }
 public void show(double[][] x,double[][] y){
        Stage stage=new Stage();
        stage.setTitle("Chart");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
         xAxis.setLabel(xLabel);
         yAxis.setLabel(yLabel);
        final AreaChart<Number,Number> lineChart = 
                new AreaChart<Number,Number>(xAxis,yAxis);
       
        lineChart.setTitle(title);
        
        for(int i=0;i<x[0].length;i++){
            XYChart.Series seriex = new XYChart.Series();
            if(portfolio.length()>0){
                seriex.setName(portfolio);
            }
            for(int j=0;j<x.length;j++){
                seriex.getData().add(new XYChart.Data(x[i][j],y[i][j]));
            }
            lineChart.getData().add(seriex);
        }
        
        lineChart.setCreateSymbols(symbol);
        xAxis.setSide(sideX);
        yAxis.setSide(sideY);
        
        Scene scene  = new Scene(lineChart,800,600);       
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/quad.JPG")));
        stage.setScene(scene);
        stage.show();
 }
}
