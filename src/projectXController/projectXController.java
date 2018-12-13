package projectXController;

import LinearAlgebra.GaussJordanElimination;
import LinearAlgebra.GaussianElimination;
import static LinearAlgebra.Simplex.getpivot;
import static LinearAlgebra.Simplex.result_;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import numerical_linear_algebra.CholeskyDecomposition;
import numerical_linear_algebra.Console;
import static numerical_linear_algebra.Console.twoParametersManipulation;
import numerical_linear_algebra.LUDecomposition;
import numerical_linear_algebra.Matrix;
import numerical_linear_algebra.QRDecomposition;
import numerical_linear_algebra.ammend;
import numerical_linear_algebra.areaChart;
import numerical_linear_algebra.lineChart;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author DAVID
 */
public class projectXController {
    
    private PreloaderController matrix;
    @FXML
    private VBox outputContainer;
    @FXML
    private ScrollPane outputScroll;
    @FXML
    private ComboBox searchChoice;
    @FXML
    private TextField searchFunction;
    private LinkedList<ActionEvent> action=new LinkedList<>();
    @FXML
    private VBox searchOutput;
    
    ObservableList<Button> allButton = FXCollections.observableArrayList(); 
    ObservableList<Button> allMatrix = FXCollections.observableArrayList(); 
    ObservableList<Button> allRedDuc = FXCollections.observableArrayList(); 
    ObservableList<Button> allSystem = FXCollections.observableArrayList();
    ObservableList<Button> allLinear = FXCollections.observableArrayList();
    ObservableList<Button> allVariable = FXCollections.observableArrayList();
    ListView list = new ListView();
    @FXML
    private VBox matrixAccordion;
    @FXML
    private VBox variableOutput;
    @FXML
    private VBox systemAccordion;
    @FXML
    private VBox reduction_and_decomposition_Accordion;
    @FXML
    private VBox linearProgramming;
    @FXML
    private VBox actionOutput;
    @FXML
    private TitledPane quadCalculator;
    @FXML
    private TextField quadText;
    @FXML
    private Label quadOutput;
    @FXML
    private JFXButton sd;
    @FXML
    private JFXButton one;
    @FXML
    private JFXButton four;
    @FXML
    private JFXButton seven;
    @FXML
    private JFXButton sinInverse;
    @FXML
    private JFXButton cosh;
    @FXML
    private JFXButton log;
    @FXML
    private JFXButton zero;
    @FXML
    private JFXButton two;
    @FXML
    private JFXButton five;
    @FXML
    private JFXButton eight;
    @FXML
    private JFXButton cosInverse;
    @FXML
    private JFXButton tanh;
    @FXML
    private JFXButton In;
    @FXML
    private JFXButton equals;
    @FXML
    private JFXButton minus;
    @FXML
    private JFXButton divide;
    @FXML
    private JFXButton clear;
    @FXML
    private JFXButton closeBracket;
    @FXML
    private JFXButton tan;
    @FXML
    private JFXButton sinh;
    @FXML
    private JFXButton xinverse;
    @FXML
    private JFXButton dot;
    @FXML
    private JFXButton answer;
    @FXML
    private JFXButton plus;
    @FXML
    private JFXButton times;
    @FXML
    private JFXButton del;
    @FXML
    private JFXButton openBracket;
    @FXML
    private JFXButton cos;
    @FXML
    private JFXButton squareRoot;
    @FXML
    private JFXButton next;
    @FXML
    private JFXButton tanInverse;
    @FXML
    private JFXButton nine;
    @FXML
    private JFXButton six;
    @FXML
    private JFXButton three;
    @FXML
    private JFXButton back;
    @FXML
    private JFXButton anyRoot;
    @FXML
    private JFXButton sin;
    @FXML
    private JFXButton slash;
    @FXML
    private JFXButton factorial;
    @FXML
    private JFXButton xsquare;
    @FXML
    private JFXButton xpowy;
    @FXML
    private JFXButton logbasex;
    @FXML
    private JFXButton exp;
    @FXML
    private JFXButton pi;
    @FXML
    private ComboBox<String> fontTextEditor;
    @FXML
    private ComboBox<String> fontSizeEditor;
    @FXML
    private JFXButton undo;
    @FXML
    private JFXButton redo;
    @FXML
    private JFXButton clearVariables;
    @FXML
    private Button enterCommand;
    @FXML
    private JFXButton quadCalc;
    @FXML
    private TitledPane vary;
    @FXML
    private Accordion accordion;

    private void search() {
            searchFunction.textProperty().addListener(
            new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, 
                                    Object oldVal, Object newVal) {
                    handleSearchByKey((String)oldVal, (String)newVal);
                }
            });
            
            searchChoice.setOnAction(e->{
                searchStreamLining();
            });
    }
    
    public void handleSearchByKey(String oldVal, String newVal) {
        if(searchChoice.getValue()=="Matrix Algebra"){
            if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
                list.refresh();
                list.setItems( allMatrix );
            }
            String[] parts = newVal.toUpperCase().split(" ");
            ObservableList<Button> subentries = FXCollections.observableArrayList();
            for (Iterator it = list.getItems().iterator(); it.hasNext();) {
                Button entry = (Button) it.next();
                boolean match = true;
                String entryText = entry.getText();
                for ( String part: parts ) {
                    if ( ! entryText.toUpperCase().contains(part) ) {
                        match = false;
                        break;
                    }
                }
                if ( match ) {
                    subentries.add(entry);
                }
            }
            list.setItems(subentries);
        }else if(searchChoice.getValue()=="System Of Linear Equation"){
            if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
                list.refresh();
                list.setItems( allSystem );
            }
            String[] parts = newVal.toUpperCase().split(" ");
            ObservableList<Button> subentries = FXCollections.observableArrayList();
            for (Iterator it = list.getItems().iterator(); it.hasNext();) {
                Button entry = (Button) it.next();
                boolean match = true;
                String entryText = entry.getText();
                for ( String part: parts ) {
                    if ( ! entryText.toUpperCase().contains(part) ) {
                        match = false;
                        break;
                    }
                }
                if ( match ) {
                    subentries.add(entry);
                }
            }
            list.setItems(subentries);
        } 
        else if(searchChoice.getValue()=="Matrix Reduction and Decomposition"){
            if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
                list.refresh();
                list.setItems( allRedDuc );
            }
            String[] parts = newVal.toUpperCase().split(" ");
            ObservableList<Button> subentries = FXCollections.observableArrayList();
            for (Iterator it = list.getItems().iterator(); it.hasNext();) {
                Button entry = (Button) it.next();
                boolean match = true;
                String entryText = entry.getText();
                for ( String part: parts ) {
                    if ( ! entryText.toUpperCase().contains(part) ) {
                        match = false;
                        break;
                    }
                }
                if ( match ) {
                    subentries.add(entry);
                }
            }
            list.setItems(subentries);
        }else if(searchChoice.getValue()=="All"){
            
                if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
                    list.refresh();
                    list.setItems( allButton );
            }
            String[] parts = newVal.toUpperCase().split(" ");
            ObservableList<Button> subentries = FXCollections.observableArrayList();
            for (Iterator it = list.getItems().iterator(); it.hasNext();) {
                Button entry = (Button) it.next();
                boolean match = true;
                String entryText = entry.getText();
                for ( String part: parts ) {
                    if ( ! entryText.toUpperCase().contains(part) ) {
                        match = false;
                        break;
                    }
                }
                if ( match ) {
                    subentries.add(entry);
                }
            }
            list.setItems(subentries);
        }else if(searchChoice.getValue()=="Linear Programming"){
                if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
                    list.refresh();
                    list.setItems( allLinear );
            }
            String[] parts = newVal.toUpperCase().split(" ");
            ObservableList<Button> subentries = FXCollections.observableArrayList();
            for (Iterator it = list.getItems().iterator(); it.hasNext();) {
                Button entry = (Button) it.next();
                boolean match = true;
                String entryText = entry.getText();
                for ( String part: parts ) {
                    if ( ! entryText.toUpperCase().contains(part) ) {
                        match = false;
                        break;
                    }
                }
                if ( match ) {
                    subentries.add(entry);
                }
            }
            list.setItems(subentries);
        }else if(searchChoice.getValue()=="Variables"){
                if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
                    list.refresh();
                    list.setItems( allVariable );
            }
            String[] parts = newVal.toUpperCase().split(" ");
            ObservableList<Button> subentries = FXCollections.observableArrayList();
            for (Iterator it = list.getItems().iterator(); it.hasNext();) {
                Button entry = (Button) it.next();
                boolean match = true;
                String entryText = entry.getText();
                for ( String part: parts ) {
                    if ( ! entryText.toUpperCase().contains(part) ) {
                        match = false;
                        break;
                    }
                }
                if ( match ) {
                    subentries.add(entry);
                }
            }
            list.setItems(subentries);
        }
    }
    String[] allFunctions={"Addition","Subtraction","Plot Area","Plot Line","Multiplication","Division","Inverse","Transpose","Determinant","Rank","Trace","Power","Norm 1","Norm 2","Norm F","Norm Inf","EigenValues","EigenVectors","Echelon/Gaussian Method","Lower Triangular Factor","Upper Triangular Factor","Inversion Method","Gaussian Elimination","Jordan Elimation","Cholesky Decomposition","LU Decomposition","QR Decomposition","Simplex"};
    String[] matrixAlgebraFunction={"Addition","Subtraction","Plot Area","Plot Line","Multiplication","Division","Inverse","Transpose","Determinant","Rank","Trace","Power","Norm 1","Norm 2","Norm F","Norm Inf","EigenValues","EigenVectors"};
    String[] redducAlgebraFunction={"Echelon/Gaussian Method","Lower Triangular Factor","Upper Triangular Factor"};
    String[] systemAlgebraFunction={"Jordan Elimation","Inversion Method","Gaussian Elimination","Cholesky Decomposition","LU Decomposition","QR Decomposition",};
    String[] linearAlgebraFunction={"Simplex"};
    public void matrix_algebra(){
        ObservableList<Button> matrixButton = FXCollections.observableArrayList();    
        ListView list = new ListView();
        for(String temp:matrixAlgebraFunction){
                Button but=new Button(temp);
                but.setOnAction(new action(but));
                matrixButton.add(but);
        }
        list.setPadding(new Insets(0,0,70,0));
        list.setItems( matrixButton );
        matrixAccordion.getChildren().add(list);
    }
    public void system_algebra(){
        ObservableList<Button> matrixButton = FXCollections.observableArrayList();    
        ListView list = new ListView();
        for(String temp:systemAlgebraFunction){
                Button but=new Button(temp);
                but.setOnAction(new action(but));
                matrixButton.add(but);
         }
        list.setPadding(new Insets(0,0,70,0));
        list.setItems( matrixButton );
        systemAccordion.getChildren().add(list);
    }
    public void reduction_and_decomposition(){
        ObservableList<Button> matrixButton = FXCollections.observableArrayList();    
        ListView list = new ListView();
        for(String temp:redducAlgebraFunction){
                Button but=new Button(temp);
                but.setOnAction(new action(but));
                matrixButton.add(but);
        }
        list.setPadding(new Insets(0,0,70,0));
        list.setItems( matrixButton );
        reduction_and_decomposition_Accordion.getChildren().add(list);
    }
    public void linearProgramming(){
        ObservableList<Button> matrixButton = FXCollections.observableArrayList();    
        ListView list = new ListView();
        for(String temp:linearAlgebraFunction){
                Button but=new Button(temp);
                but.setOnAction(new action(but));
                matrixButton.add(but);
        }
        list.setPadding(new Insets(0,0,70,0));
        list.setItems( matrixButton );
        linearProgramming.getChildren().add(list);
    }
    private Map<String,String> variableList=new TreeMap<>();
    private ListView variableLists=new ListView(),assignmentList=new ListView(),formatList=new ListView();
    public void variable_list(){
        variableList=Console.getVariableList();        
        ObservableList<String> variable = FXCollections.observableArrayList();    
        ObservableList<String> assignment = FXCollections.observableArrayList();    
        ObservableList<String> format = FXCollections.observableArrayList();
        variableOutput.getChildren().clear();
        Button but;
        allVariable.clear();
        variable.add("Variables");assignment.add("Values");format.add("Dimension");
        for (Iterator it = this.variableList.keySet().iterator(); it.hasNext();) {
            String xx=it.next().toString();
            but=new Button();
            but.setText(xx);
            but.setOnAction(new action(but));
            allVariable.add(but);
            variable.add(xx);
            assignment.add(this.variableList.get(xx));
            String form="";
            form=Console.evaluate(this.variableList.get(xx)).getArray().length+" X "+Console.evaluate(this.variableList.get(xx)).getArray()[0].length;
            //String form=Console.evaluate(this.variableList.get(xx)).getArray().length+" X "+Console.evaluate(this.variableList.get(xx)).getArray()[0].length;
            format.add(form);
        }
        searchStreamLining();
        variableLists.refresh();
        variableLists.setItems( variable );
        assignmentList.setItems( assignment );
        formatList.setItems(format);
        assignmentList.setPrefHeight(278);
        formatList.setPrefHeight(278);
        variableLists.setPrefHeight(278);
        variableLists.setPadding(new Insets(0,0,55,0));
        assignmentList.setPadding(new Insets(0,0,55,0));
        formatList.setPadding(new Insets(0,0,55,0));
        HBox hbox=new HBox();
        hbox.getChildren().addAll(variableLists,assignmentList,formatList);
        variableOutput.getChildren().add(hbox);
    }
    public void searchStreamLining(){
        if(searchChoice.getValue()=="All"){
            list.refresh();
            list.setItems( allButton );
        }else if(searchChoice.getValue()=="Matrix Algebra"){
            list.refresh();
            list.setItems( allMatrix );
        }else if(searchChoice.getValue()=="System Of Linear Equation"){
            list.refresh();
            list.setItems( allSystem );
        }else if(searchChoice.getValue()=="Matrix Reduction and Decomposition"){
            list.refresh();
            list.setItems( allRedDuc);
        }else if(searchChoice.getValue()=="Linear Programming"){
            list.refresh();
            list.setItems( allLinear );
        }else if(searchChoice.getValue()=="Variables"){
            list.refresh();
            list.setItems( allVariable );
        }
    }
    
    public void setMain(PreloaderController matrix){
        this.matrix=matrix;
        accordion.setExpandedPane(vary);
        Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            cmd.requestFocus();
                        }
                    });
        variableLists.setEditable(true);
        variableLists.setOnEditStart(e->{
            
        });
        assignmentList.setEditable(true);
        formatList.setEditable(true);
        searchChoice.setItems(FXCollections.observableArrayList("All",new Separator(), "Matrix Algebra","System Of Linear Equation","Matrix Reduction and Decomposition","Linear Programming", "Variables"));
        ObservableList<String> font= FXCollections.observableArrayList("Century Gothic","Helvetica", "Verdana","Times New Roman","Britannic Bold","San-serif", "Papyrus");
        fontTextEditor.setItems(font);
        font=FXCollections.observableArrayList("8","9","10","11","12","14","16","18","20","22","24","26","28","36","48","72");
        fontSizeEditor.setItems(font);
        fontTextEditor.setValue("Century Gothic");
        fontSizeEditor.setValue("10");
        searchChoice.setValue("All");
        searchFunction.setFont(Font.font("Century Gothic", FontWeight.LIGHT, 12));
        searchOutput.getChildren().add(list);
        search();
        matrix_algebra();
        system_algebra();
        reduction_and_decomposition();
        variable_list();
        quadCalculator();
            for(String temp:allFunctions){
                Button but=new Button(temp);
                but.setOnAction(new action(but));
                allButton.add(but);
            }
            //functions
            
            for(String temp:matrixAlgebraFunction){
                Button but=new Button(temp);
                but.setOnAction(new action(but));
                allMatrix.add(but);
            }
            for(String temp:redducAlgebraFunction){
                Button but=new Button(temp);
                but.setOnAction(new action(but));
                allRedDuc.add(but);
            }
            for(String temp:systemAlgebraFunction){
                Button but=new Button(temp);
                but.setOnAction(new action(but));
                allSystem.add(but);
            }
            for(String temp:linearAlgebraFunction){
                Button but=new Button(temp);
                but.setOnAction(new action(but));
                allLinear.add(but);
            }
            
        list.setItems( allButton );
        
        Image image = new Image(getClass().getResourceAsStream("/images/if_arrow_redo_66679.png"));
        redo.setGraphic(new ImageView(image));
        image=new Image(getClass().getResourceAsStream("/images/if_arrow_undo_66682.png"));
        undo.setGraphic(new ImageView(image));
        image=new Image(getClass().getResourceAsStream("/images/if_Cancel_1493282.png"));
        clearVariables.setGraphic(new ImageView(image));
    }
    
    @FXML
    private TextArea cmd;
    
     LinkedList<String> cmdList=new LinkedList<>();
     int ii=0;
    public void loadcsv(String command,Text label){
        boolean variable_checker=false;
            String variableName="",value="";
            String realValue="[";
            if(command.contains("=")){
                variableName=command.substring(0, command.indexOf("="));
                value=command.substring(command.indexOf("=")+1,command.length());
                variable_checker=true;
            }else{
                value=command;
            }
            
            String file_location=value.substring(value.indexOf("(")+1, value.lastIndexOf(")"));
            file_location=file_location.replace("\"", "");
            file_location=file_location.replace("\'", "");
            Scanner fromFile;
            try {
                fromFile = new Scanner(new File(file_location));
                while(fromFile.hasNext()){
                    realValue+=fromFile.next()+";";
                }
                 realValue=realValue.substring(0,realValue.lastIndexOf(";"));
                 realValue=realValue+"]";
                 if(variable_checker){
                     //loadcsv("C:\Users\vicksemmanuel\Desktop\data.csv")
                     try{
                         label.setText(Matrix.display(Console.evaluate(realValue)));
                        Console.getVariableList().put(variableName,Console.format(Console.evaluate(realValue).getArray()));
                        variable_list();
                        cmdList.add(cmd.getText());
                        ii=cmdList.size();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                cmd.requestFocus();
                            }
                        });
                     }catch(Exception load){
                         label.setText("Incorrect data format");
                         Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                cmd.requestFocus();
                            }
                        });
                     }
                 }else{
                     try{
                         label.setText(Matrix.display(Console.evaluate(realValue)));
                         variable_list();
                            cmdList.add(cmd.getText());
                            ii=cmdList.size();
                     }catch(Exception load){
                         label.setText("Incorrect data format");
                         Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                cmd.requestFocus();
                            }
                        });
                     }
                 }
            } catch (FileNotFoundException ex) {
                Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                cmd.requestFocus();
                            }
                        });
                label.setText("Location not set");
            }
    }
    public void plotline(String command,Text label) throws Exception{
        String exp=command;
        lineChart line;
        if(exp.matches("^plotline\\(.*\\)$")){
            exp=exp.replace("plotline(", "");
            exp=exp.substring(0,exp.lastIndexOf(")"));
            if(exp.length()>0){
                String[] ammend = null;
                    if(exp.contains(",")){
                        ammend=exp.split("\\,");
                        if(ammend.length==2){
                            double[][] plot_=Console.evaluate(ammend[0]).getArray();
                            double[][] plot=Console.evaluate(ammend[1]).getArray();
                            line=new lineChart(plot_,plot,"Portfolio","x_Label","y_Label","BOTTOM","TOP","false","Figure");
                        }else{
                            label.setText("Check guide, plotarea only takes two parameter");
                        }
                    }else{
                        try{
                            double[][] plot_=Console.evaluate(exp).getArray();
                            line=new lineChart(plot_,"Portfolio","x_Label","y_Label","BOTTOM","TOP","false","Figure");
                        }catch(Exception k){
                            throw new RuntimeException("Error");
                        }
                    }
                
            }else{
                throw new RuntimeException("Error, No parameter exists");
            }
        }else{
            label.setText("Incorrect plotline declaration");
        }
    }
    
    public void plotarea(String command,Text label) throws Exception{
        String exp=command;
        areaChart line;
        if(exp.matches("^plotarea\\(.*\\)$")){
            exp=exp.replace("plotarea(", "");
            exp=exp.substring(0,exp.lastIndexOf(")"));
            if(exp.length()>0){
                String[] ammend = null;
                    if(exp.contains(",")){
                        ammend=exp.split("\\,");
                        if(ammend.length==2){
                            double[][] plot_=Console.evaluate(ammend[0]).getArray();
                            double[][] plot=Console.evaluate(ammend[1]).getArray();
                            line=new areaChart(plot_,plot,"Portfolio","x_Label","y_Label","BOTTOM","TOP","false","Figure");
                        }else{
                            label.setText("Check guide, plotarea only takes two parameter");
                        }
                    }else{
                        try{
                            double[][] plot_=Console.evaluate(exp).getArray();
                            line=new areaChart(plot_,"Portfolio","x_Label","y_Label","BOTTOM","TOP","false","Figure");
                        }catch(Exception k){
                            throw new RuntimeException("Error");
                        }
                    }
                
            }else{
                throw new RuntimeException("Error, No parameter exists");
            }
        }else{
            label.setText("Incorrect plotarea declaration");
        }
    }
    
    @FXML
    private void executeCommand(ActionEvent e){
        String command=cmd.getText();
        Text label=new Text();
        String com=command.toUpperCase();
        label.setFont(Font.font("Century Gothic", FontWeight.LIGHT, 10));
        if(com.matches("EXIT([;]{1})?")||com.matches("BYE([;]{1})?")||command.matches("exit([;]{1})?")||command.matches("bye([;]{1})?")){
            label.setText("exit");
            System.exit(0);
        }else if(com.matches("^([A-Za-z]+[\\=]{1})?LOADCSV\\(([\']{1}|[\"]{1})?(.*)?([\']{1}|[\"]{1})?\\)$")){
            loadcsv(command,label);
            cmdList.add(cmd.getText());
        }else if(com.contains("PLOTLINE")){
            try {//plot(A,"Randoms","xAxis","yAxis","LEFT","BOTTOM","true","Map")
                plotline(command,label);
                cmdList.add(cmd.getText());
            } catch (Exception ex) {
                label.setText("Error, check Matrix dimension \t");
            }
        }else if(com.contains("PLOTAREA")){
            try {//plot(A,"Randoms","xAxis","yAxis","LEFT","BOTTOM","true","Map")
                plotarea(command,label);
                cmdList.add(cmd.getText());
            } catch (Exception ex) {
                label.setText("Error, check Matrix dimension \t");
            }
        }else{
            try{
                //Console.updateValues();   
                label.setText(Matrix.display(Console.evaluate(twoParametersManipulation(command))));
                        label.setFont(Font.font(fontTextEditor.getValue(), FontWeight.LIGHT, Double.parseDouble(fontSizeEditor.getValue())));
                        if(!Console.getVariableName().equalsIgnoreCase("false")){
                            //System.out.println(Console.getVariableName()+"="+Console.format(Console.evaluate(command).getArray()));
                            Console.getVariableList().put(Console.getVariableName(),Console.format(Console.evaluate(twoParametersManipulation(command)).getArray()));
                        }
                //Console.updateValues();
                variable_list();
                cmdList.add(cmd.getText());
                ii=cmdList.size();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        cmd.requestFocus();
                    }
                });
            }catch(Exception error){
                label.setText(error.getMessage());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        cmd.requestFocus();
                    }
                });
            }
        }
            
        outputContainer.getChildren().add(label);
        outputScroll.vvalueProperty().bind(outputContainer.heightProperty());
    }
    
    
    @FXML
    private void executeCommandThroughEnter(KeyEvent e){
        switch(e.getCode()){
            case ENTER:
                cmd.setEditable(false);
                String command=cmd.getText();
                Text label=new Text();
                String com=command.toUpperCase();
                label.setFont(Font.font("Century Gothic", FontWeight.LIGHT, 10));
                if(com.matches("EXIT([;]{1})?")||com.matches("BYE([;]{1})?")||command.matches("exit([;]{1})?")||command.matches("bye([;]{1})?")){
                    label.setText("exit");
                    System.exit(0);
                }else if(com.matches("^([A-Za-z]+[\\=]{1})?LOADCSV\\(([\']{1}|[\"]{1})?(.*)?([\']{1}|[\"]{1})?\\)$")){
                    loadcsv(command,label);
                    cmdList.add(cmd.getText());
                }else if(com.contains("PLOTLINE")){
                    try {//plot(A,"Randoms","xAxis","yAxis","LEFT","BOTTOM","true","Map")
                        plotline(command,label);
                        cmdList.add(cmd.getText());
                    } catch (Exception ex) {
                        label.setText("Error, check Matrix dimension \t");
                    }
                }else if(com.contains("PLOTAREA")){
                    try {//plot(A,"Randoms","xAxis","yAxis","LEFT","BOTTOM","true","Map")
                        plotarea(command,label);
                        cmdList.add(cmd.getText());
                    } catch (Exception ex) {
                        label.setText("Error, check Matrix dimension \t");
                    }
                }else if(com.contains("PLOTAREA")){
                            try {//plot(A,"Randoms","xAxis","yAxis","LEFT","BOTTOM","true","Map")
                                plotarea(command,label);
                                cmdList.add(cmd.getText());
                            } catch (Exception ex) {
                                label.setText("Error, check Matrix dimension \t");
                            }
                }else{
                    try{    
                        //Console.updateValues();
                        label.setText(Matrix.display(Console.evaluate(twoParametersManipulation(command))));
                        label.setFont(Font.font(fontTextEditor.getValue(), FontWeight.LIGHT, Double.parseDouble(fontSizeEditor.getValue())));
                        if(!Console.getVariableName().equalsIgnoreCase("false")){
                            //System.out.println(Console.getVariableName()+"="+Console.format(Console.evaluate(command).getArray()));
                            Console.getVariableList().put(Console.getVariableName(),Console.format(Console.evaluate(twoParametersManipulation(command)).getArray()));
                        }
                        //Console.updateValues();
                        variable_list();
                        cmdList.add(cmd.getText());
                        ii=cmdList.size();
                        outputContainer.getChildren().add(label);
                        outputScroll.vvalueProperty().bind(outputContainer.heightProperty());
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                cmd.requestFocus();
                            }
                        });
                    }catch(Exception error){
                        label.setText(error.getMessage());
                        outputContainer.getChildren().add(label);
                        outputScroll.vvalueProperty().bind(outputContainer.heightProperty());
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                cmd.requestFocus();
                            }
                        });
                    }
                }
                cmd.setEditable(true);
                break;
        }
    }
    
    @FXML
    private void undo(ActionEvent event) {
                try{
                    if(ii>0){
                        cmd.setText(cmdList.get(--ii));
                    }else if(ii==-1){
                         cmd.setText(cmdList.get(0));
                    }
                }catch(Exception ee){
                    
                }
    }

    @FXML
    private void redo(ActionEvent event) {
                try{
                    if(ii<cmdList.size()-1){
                        cmd.setText(cmdList.get(++ii));
                    }else if(ii==cmdList.size()){
                        cmd.setText(cmdList.get(cmdList.size()-1));
                    }
                }catch(Exception ee){
                    
                }
    }
    
    
    @FXML
    private void clear(ActionEvent e){
        String command=cmd.getText();
        cmd.clear();
    }

    @FXML
    private void handleThis(MouseEvent event) {
        Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            quadText.requestFocus();
                        }
                    });
    }

    @FXML
    private void handleThisToo(MouseEvent event) {
        Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            cmd.requestFocus();
                        }
                    });
    }
    LinkedList<String> operationList=new LinkedList<>();
    int i=0;
    @FXML
    private void opp(ActionEvent event) {
            if(event.getSource()==one){
                String x=quadText.getText()+""+one.getText();
                quadText.setText(x);
            }else if(event.getSource()==two){
                String x=quadText.getText()+""+two.getText();
                quadText.setText(x);
            }
            else if(event.getSource()==three){
                String x=quadText.getText()+""+three.getText();
                quadText.setText(x);
            }
            else if(event.getSource()==four){
                String x=quadText.getText()+""+four.getText();
                quadText.setText(x);
            }
            else if(event.getSource()==five){
                String x=quadText.getText()+""+five.getText();
                quadText.setText(x);
            }else if(event.getSource()==six){
                String x=quadText.getText()+""+six.getText();
                quadText.setText(x);
            }else if(event.getSource()==seven){
                String x=quadText.getText()+""+seven.getText();
                quadText.setText(x);
            }else if(event.getSource()==eight){
                String x=quadText.getText()+""+eight.getText();
                quadText.setText(x);
            }else if(event.getSource()==nine){
                String x=quadText.getText()+""+nine.getText();
                quadText.setText(x);
            }else if(event.getSource()==zero){
                String x=quadText.getText()+""+zero.getText();
                quadText.setText(x);
            }else if(event.getSource()==openBracket){
                String x=quadText.getText()+""+openBracket.getText();
                quadText.setText(x);
            }else if(event.getSource()==closeBracket){
                String x=quadText.getText()+""+closeBracket.getText();
                quadText.setText(x);
            }else if(event.getSource()==sin){
                String x=sin.getText()+"("+quadText.getText()+")";
                quadText.setText(x);
            }else if(event.getSource()==cos){
                String x=cos.getText()+"("+quadText.getText()+")";
                quadText.setText(x);
            }else if(event.getSource()==tan){
                String x=tan.getText()+"("+quadText.getText()+")";
                quadText.setText(x);
            }else if(event.getSource()==tanh){
                String x=tanh.getText()+"("+quadText.getText()+")";
                quadText.setText(x);
            }else if(event.getSource()==sinh){
                String x=sinh.getText()+"("+quadText.getText()+")";
                quadText.setText(x);
            }else if(event.getSource()==cosh){
                String x=cosh.getText()+"("+quadText.getText()+")";
                quadText.setText(x);
            }else if(event.getSource()==tanInverse){
                String x=" atan("+quadText.getText()+")";
                quadText.setText(x);
            }else if(event.getSource()==cosInverse){
                String x=" acos("+quadText.getText()+")";
                quadText.setText(x);
            }else if(event.getSource()==sinInverse){
                String x=" asin("+quadText.getText()+")";
                quadText.setText(x);
            }else if(event.getSource()==times){
                String x=quadText.getText()+""+times.getText();
                quadText.setText(x);
            }else if(event.getSource()==divide){
                String x=quadText.getText()+"/";
                quadText.setText(x);
            }else if(event.getSource()==plus){
                String x=quadText.getText()+""+plus.getText();
                quadText.setText(x);
            }else if(event.getSource()==minus){
                String x=quadText.getText()+""+minus.getText();
                quadText.setText(x);
            }else if(event.getSource()==slash){
                String x=quadText.getText()+""+slash.getText();
                quadText.setText(x);
            }else if(event.getSource()==xsquare){
                String x=quadText.getText()+"^2";
                quadText.setText(x);
            }else if(event.getSource()==xpowy){
                String x=quadText.getText()+"^y";
                quadText.setText(x);
            }else if(event.getSource()==squareRoot){
                String x="sqrt("+quadText.getText()+")";
                quadText.setText(x);
            }else if(event.getSource()==answer){
                String x=quadText.getText()+""+quadOutput.getText();
                quadText.setText(x);
            }else if(event.getSource()==equals){
                operationList.add(quadText.getText());
                i=operationList.size();
                quadText.setText(quadOutput.getText());
            }else if(event.getSource()==back){
                String x=quadText.getText();
                try{
                    if(i>0){
                        quadText.setText(operationList.get(--i));
                    }else if(i==-1){
                        quadText.setText(operationList.get(0));
                    }
                }catch(Exception ee){
                    
                }
            }else if(event.getSource()==next){
                String x=quadText.getText();
                try{
                    if(i<operationList.size()-1){
                        quadText.setText(operationList.get(++i));
                    }else if(i==operationList.size()){
                        quadText.setText(operationList.get(operationList.size()-1));
                    }
                }catch(Exception ee){
                    
                }
            }else if(event.getSource()==dot){
                String x=quadText.getText()+""+dot.getText();
                quadText.setText(x);
            }else if(event.getSource()==del){
                String x=quadText.getText();
                if(x.length()>0){
                    quadText.setText(x.substring(0, x.length()-1));
                }
            }
            else if(event.getSource()==clear){
                quadText.setText("");
            }else if(event.getSource()==log){
                String x=log.getText()+"("+quadText.getText()+")";
                quadText.setText(x);
            }else if(event.getSource()==In){
                String x="ln("+quadText.getText()+")";
                quadText.setText(x);
            }else if(event.getSource()==xinverse){
                String x="("+quadText.getText()+")^-1";
                quadText.setText(x);
            }
            else if(event.getSource()==logbasex){
                String x=quadText.getText()+"b";
                quadText.setText(x);
            }else if(event.getSource()==sd) {
                String x=quadOutput.getText();
                if(x.length()>0){
                    try{
                        quadOutput.setText(Console.decimal2Fraction(Double.parseDouble(x)));
                    }catch(Exception e){
                        
                    }
                }
            }
            else if(event.getSource()==factorial) {
                String x="("+quadText.getText()+")!";
                quadText.setText(x);
            }else if(event.getSource()==exp) {
                String x=exp.getText()+"("+quadText.getText()+")";
                quadText.setText(x);
            }else if(event.getSource()==pi) {
                String x=quadText.getText()+""+Math.PI;
                quadText.setText(x);
            }
            else if(event.getSource()==anyRoot) {
                String x=anyRoot.getText()+"("+quadText.getText()+")";
                quadText.setText(x);
            }
    }
    private void quadCalculator(){
        quadText.textProperty().addListener(
            new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, 
                                    Object oldVal, Object newVal) {
                    handleQuadCalculatorKey(quadText.getText());
                }
            });
    }
    private void handleQuadCalculatorKey(String values){
         String command=values;
            try{
                //Console.updateValues();
                quadOutput.setText(Double.toString(Console.eval(command)));
                //Console.updateValues();
                variable_list();
            }catch(Exception error){
                
        }
    }

    @FXML
    private void oppKey(KeyEvent event) {
        switch(event.getCode()){
                case RIGHT:
                    String x=quadText.getText();
                    try{
                        if(i<operationList.size()-1){
                            quadText.setText(operationList.get(++i));
                        }else if(i==operationList.size()){
                            quadText.setText(operationList.get(operationList.size()-1));
                        }
                    }catch(Exception ee){

                    }
                break;
                case LEFT:
                    x=quadText.getText();
                    try{
                        if(i>0){
                            quadText.setText(operationList.get(--i));
                        }else if(i==-1){
                            quadText.setText(operationList.get(0));
                        }
                    }catch(Exception ee){

                    }
                break;
                case ENTER:
                    operationList.add(quadText.getText());
                    i=operationList.size();
                    quadText.setText(quadOutput.getText());
                break;
        }
    }

    @FXML
    private void showCalc(ActionEvent event) {
        actionOutput.getChildren().removeAll(actionOutput.getChildren());
        actionOutput.setPadding(new Insets(0,0,0,0));
        actionOutput.getChildren().add(quadCalculator);
    }

    @FXML
    private void changeStyle(ActionEvent event) {
        try{
            cmd.setFont(Font.font(fontTextEditor.getValue(), FontWeight.LIGHT, Double.parseDouble(fontSizeEditor.getValue())));
        }catch(Exception e){}
    }

    @FXML
    private void clearVariables(ActionEvent event) {
        Console.getVariableList().clear();
        variable_list();
    }

    @FXML
    private void closeQuad(ActionEvent event) {
        System.exit(0);
    }


    @FXML
    private void quickGuide(ActionEvent event) throws IOException {
        matrix.webWindow();
    }

    @FXML
    private void deleteQuad(ActionEvent event) {
    }

   
   class action implements EventHandler<ActionEvent>{
       private Button btn;
       public action(Button btn){
           this.btn=btn;
       }
       private void handle(String operation){
           JFXButton newMatrix=new JFXButton("New Matrix");
           newMatrix.setId("newMatrix");
           newMatrix.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                        VBox vbox=new VBox();
                        vbox.setId("vbox");
                        vbox.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                        newMatrix.setOnAction(e->{
                            vbox.getChildren().clear();
                            vbox.setSpacing(10);
                            vbox.setPadding(new Insets(20,20,20,20));
                            TextField mText=new TextField();
                            Label mLabel=new Label("Enter the Number of Rows, m");
                            mLabel.setId("mLabel");
                            mLabel.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                            Label nLabel=new Label("Enter the Number of Cols, n");
                            nLabel.setId("nLabel");
                            nLabel.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                            TextField nText=new TextField();
                            nText.setPrefWidth(50);
                            mText.setPrefWidth(50);
                            JFXButton joinMatrix=new JFXButton("Create Matrix");
                            joinMatrix.setId("create_matrix");
                            joinMatrix.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                            joinMatrix.setOnAction(ex->{
                                try{
                                    int x,y;
                                    x=Integer.parseInt(mText.getText());
                                    y=Integer.parseInt(nText.getText());
                                   if(y>0 && x>0){
                                       if(x<=7 && y<=20){
                                            Map<String,String> variables=new TreeMap<>();
                                            for(int ii=0;ii<x;ii++){
                                                HBox mat=new HBox();
                                                mat.setSpacing(10);
                                                for(int jj=0;jj<y;jj++){
                                                    TextField tf=new TextField();
                                                    tf.setPrefWidth(80);
                                                    tf.setId(ii+""+jj);
                                                    tf.textProperty().addListener(
                                                    new ChangeListener() {
                                                        @Override
                                                        public void changed(ObservableValue observable, 
                                                                            Object oldVal, Object newVal) {
                                                            variables.put(tf.getId(), tf.getText());
                                                        }
                                                    });

                                                    mat.getChildren().add(tf);
                                                }
                                                vbox.getChildren().add(mat);
                                            }
                                            JFXButton solve=null;

                                            if(operation.equals("transpose")){
                                                solve=new JFXButton("Transpose");
                                            }else if(operation.equals("inverse")){
                                                solve=new JFXButton("Inverse");
                                            }else if(operation.equals("echelon")){
                                                solve=new JFXButton("Echelon/Gaussian Method");
                                            }else if(operation.equals("determinant")){
                                                solve=new JFXButton("Determinant");
                                            }else if(operation.equals("trace")){
                                                solve=new JFXButton("Trace");
                                            }else if(operation.equals("rank")){
                                                solve=new JFXButton("Rank");
                                            }else if(operation.equals("norm1")){
                                                solve=new JFXButton("Norm 1");
                                            }else if(operation.equals("norm2")){
                                                solve=new JFXButton("Norm 2");
                                            }else if(operation.equals("normF")){
                                                solve=new JFXButton("Norm F");
                                            }else if(operation.equals("normInf")){
                                                solve=new JFXButton("Norm Inf");
                                            }else if(operation.equals("eigValues")){
                                                solve=new JFXButton("EigenValues");
                                            }else if(operation.equals("eigVectors")){
                                                solve=new JFXButton("EigenVectors");
                                            }else if(operation.equals("simplex")){
                                                 solve=new JFXButton("Simplex");
                                            }else if(operation.equals("upper")){
                                                 solve=new JFXButton("Upper Triangular Factor");
                                            }else if(operation.equals("lower")){
                                                 solve=new JFXButton("Lower Triangular Factor");
                                            }else if(operation.equals("line")){
                                                 solve=new JFXButton("Plot Line");
                                            }else if(operation.equals("area")){
                                                 solve=new JFXButton("Plot Area");
                                            }
                                            solve.setId("solve");
                                            solve.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                                            solve.setOnAction(ep->{
                                                double[][] transpose=new double[x][y];
                                                for(int ii=0;ii<x;ii++){
                                                    for(int jj=0;jj<y;jj++){
                                                        try{
                                                            transpose[ii][jj]=Console.eval(Double.toString(Double.parseDouble(variables.get(ii+""+jj))));
                                                        }catch(Exception ec){

                                                        }
                                                    }
                                                }
                                                Matrix trans=new Matrix(transpose);
                                                Text label=new Text();
                                                if(operation.equals("transpose")){
                                                    try{
                                                        label.setText(Matrix.display(trans.transpose()));
                                                    }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                    }
                                                }if(operation.equals("line")){
                                                    try{
                                                        lineChart line=new lineChart(transpose,"Portfolio","x_Label","y_Label","BOTTOM","TOP","false","Figure");
                                                    }catch(Exception t){
                                                        label.setText("Error, check dimension");
                                                    }
                                                }if(operation.equals("area")){
                                                    try{
                                                        areaChart line=new areaChart(transpose,"Portfolio","x_Label","y_Label","BOTTOM","TOP","false","Figure");
                                                    }catch(Exception t){
                                                            label.setText("Error, check dimension");
                                                    }
                                                }else if(operation.equals("inverse")){
                                                    try{
                                                        label.setText(Matrix.display(trans.inverse()));
                                                    }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                }else if(operation.equals("determinant")){
                                                    try{
                                                        label.setText(Double.toString(trans.det()));
                                                    }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                }else if(operation.equals("rank")){
                                                    try{
                                                        label.setText(Double.toString(trans.rank()));
                                                    }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                }else if(operation.equals("trace")){
                                                    try{
                                                        label.setText(Double.toString(trans.trace()));
                                                    }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                }else if(operation.equals("norm1")){
                                                    try{
                                                        label.setText(Double.toString(trans.norm1()));
                                                    }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                }else if(operation.equals("norm2")){
                                                    try{
                                                        label.setText(Double.toString(trans.norm2()));
                                                    }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                }else if(operation.equals("normF")){
                                                    try{
                                                        label.setText(Double.toString(trans.normF()));
                                                    }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                }else if(operation.equals("normInf")){
                                                    try{
                                                        label.setText(Double.toString(trans.normInf()));
                                                    }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                }else if(operation.equals("upper")){
                                                    try{
                                                        LUDecomposition A=new LUDecomposition (trans);
                                                        label.setText(Matrix.display((A.getU())));
                                                    }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                }else if(operation.equals("echelon")){
                                                    try{
                                                        label.setText(Matrix.display((ammend.rowSwap(transpose))));
                                                    }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                }else if(operation.equals("lower")){
                                                    try{
                                                        LUDecomposition A=new LUDecomposition (trans);
                                                        label.setText(Matrix.display((A.getL())));
                                                    }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                }else if(operation.equals("simplex")){                                                  
                                                    try{
                                                       label.setText(Matrix.display(new Matrix(result_(transpose))));
                                                    }catch(Exception t){
                                                        throw new RuntimeException("Pivot Column: "+getpivot());
                                                    }
                                                }else if(operation.equals("eigValues")){
                                                    try{
                                                        if(x==y){
                                                            label.setText(Matrix.display(trans.eig().getD()));
                                                        }else{
                                                            label.setText("Matrix must be square");
                                                        }
                                                    }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                }else if(operation.equals("eigVectors")){
                                                    try{
                                                        if(x==y){
                                                            label.setText(Matrix.display(trans.eig().getV()));
                                                        }else{
                                                            label.setText("Matrix must be square");
                                                        }
                                                    }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                    }
                                                }
                                                label.setFont(Font.font(fontTextEditor.getValue(), FontWeight.LIGHT, Double.parseDouble(fontSizeEditor.getValue())));
                                                outputContainer.getChildren().add(label);
                                                outputScroll.vvalueProperty().bind(outputContainer.heightProperty());

                                            });

                                            vbox.getChildren().add(solve);
                                       }else{
                                            Alert alert=new Alert(Alert.AlertType.WARNING);
                                            alert.setTitle("");
                                            alert.setContentText("Adjust Matrix Dimension");
                                            alert.showAndWait();
                                       }
                                   }else{
                                        Alert alert=new Alert(Alert.AlertType.WARNING);
                                        alert.setTitle("");
                                        alert.setContentText("Adjust Matrix Dimension");
                                        alert.showAndWait();
                                   }
                                }catch(Exception exception){
                                    
                                }
                            });

                            HBox mbox=new HBox();
                            mbox.setSpacing(10);
                            mbox.getChildren().addAll(mLabel,mText);
                            HBox nbox=new HBox();
                            nbox.setSpacing(10);
                            nbox.setPadding(new Insets(0,0,0,10));
                            mbox.setPadding(new Insets(0,0,0,10));
                            nbox.getChildren().addAll(nLabel,nText);
                            vbox.setFillWidth(false);
                            vbox.getChildren().addAll(mbox,nbox,joinMatrix);
                        });
                        actionOutput.getChildren().removeAll(actionOutput.getChildren());
                        actionOutput.setPadding(new Insets(20,0,0,10));
                        actionOutput.getChildren().addAll(newMatrix,vbox);
       }
       private void handleBigO(String operation){
           JFXButton newMatrix=new JFXButton("New Matrix");
           newMatrix.setId("newMatrix");
           newMatrix.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
           LinkedList<Integer> list=new LinkedList<>();
           int i=1;
           Collection<Map<String,String>> var=new LinkedList<>();
                        VBox vbox=new VBox();
                        vbox.setId("vbox");
                        vbox.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                        newMatrix.setOnAction(e->{
                            var.clear();
                            list.clear();
                            vbox.getChildren().clear();
                            vbox.setSpacing(30);
                            vbox.setPadding(new Insets(20,20,20,20));
                            TextField mText=new TextField();
                            Label mLabel=new Label("Enter the Number of Rows, m");
                            mLabel.setId("mLabel");
                            mLabel.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                            Label nLabel=new Label("Enter the Number of Cols, n");
                            nLabel.setId("nLabel");
                            nLabel.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                            TextField nText=new TextField();
                            nText.setPrefWidth(50);
                            mText.setPrefWidth(50);
                            JFXButton joinMatrix=new JFXButton("Create Matrix");
                            joinMatrix.setId("create_matrix");
                            joinMatrix.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                            JFXButton solve=new JFXButton("");
                            if(operation.equals("addition")){
                                solve.setText("Addition");
                            }else if(operation.equals("subtraction")){
                                solve.setText("Subtraction");
                            }       
                            solve.setId("solve");
                            solve.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                            joinMatrix.setOnAction(ex->{
                                try{
                                    int x,y;
                                    x=Integer.parseInt(mText.getText());
                                    y=Integer.parseInt(nText.getText());
                                    if(y<=7 && x<=20){
                                        if(x>0 && y>0){
                                            nText.setDisable(true);
                                            mText.setDisable(true);
                                            list.add(i);
                                            Map<String,String> variables=new TreeMap<>();
                                            for(int ii=0;ii<x;ii++){
                                                HBox mat=new HBox();
                                                mat.setSpacing(10);
                                                for(int jj=0;jj<y;jj++){
                                                    TextField tf=new TextField();
                                                    tf.setPrefWidth(80);
                                                    tf.setId(ii+""+jj);
                                                    tf.textProperty().addListener(
                                                    new ChangeListener() {
                                                        @Override
                                                        public void changed(ObservableValue observable, 
                                                                            Object oldVal, Object newVal) {
                                                            variables.put(tf.getId(), tf.getText());
                                                        }
                                                    });

                                                    mat.getChildren().add(tf);
                                                }

                                                vbox.getChildren().add(mat);

                                            }
                                            var.add(variables);
                                             Label delete=new Label("     ");
                                            vbox.getChildren().add(delete);

                                            if(list.size()>1){
                                            solve.setOnAction(ep->{

                                                LinkedList<double[][]> fold=new LinkedList<>();
                                                for(Map tt:var){
                                                    double[][] opp=new double[x][y];
                                                    for(int ii=0;ii<x;ii++){
                                                            for(int jj=0;jj<y;jj++){
                                                                try{
                                                                    opp[ii][jj]=Console.eval(Double.toString(Double.parseDouble((String) tt.get(ii+""+jj))));
                                                                }catch(Exception ec){

                                                                }
                                                            }
                                                        }
                                                    fold.add(opp);
                                                }

                                                Matrix trans=new Matrix(new double[x][y]);
                                                Text label=new Text();
                                                if(operation.equals("addition")){
                                                    for(int z=0;z<fold.size();z++){
                                                        try{
                                                            trans=trans.plusEquals(new Matrix(fold.get(z)));
                                                        }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                    }
                                                }else if(operation.equals("subtraction")){
                                                    for(int z=0;z<fold.size();z++){
                                                        try{
                                                            trans=trans.uminus();
                                                            trans=trans.minusEquals(new Matrix(fold.get(z)));
                                                        }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                    }
                                                }
                                                label.setText(Matrix.display(trans));
                                                label.setFont(Font.font(fontTextEditor.getValue(), FontWeight.LIGHT, Double.parseDouble(fontSizeEditor.getValue())));
                                                outputContainer.getChildren().add(label);
                                                outputScroll.vvalueProperty().bind(outputContainer.heightProperty());

                                            });
                                            }
                                        }else{
                                            Alert alert=new Alert(Alert.AlertType.WARNING);
                                            alert.setTitle("");
                                            alert.setContentText("Adjust Matrix Dimension");
                                            alert.showAndWait();
                                        }
                                    }else{
                                        Alert alert=new Alert(Alert.AlertType.WARNING);
                                        alert.setTitle("");
                                        alert.setContentText("Adjust Matrix Dimension");
                                        alert.showAndWait();
                                    }
                                }catch(Exception exception){
                                    System.out.println(exception.getMessage());
                                }
                            });

                            HBox mbox=new HBox();
                            mbox.setSpacing(10);
                            mbox.getChildren().addAll(mLabel,mText);
                            HBox nbox=new HBox();
                            HBox holdSolver=new HBox();
                            holdSolver.getChildren().addAll(joinMatrix,solve);
                            holdSolver.setSpacing(10);
                            holdSolver.setId("hsolve");
                            holdSolver.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                            nbox.setSpacing(10);
                            nbox.getChildren().addAll(nLabel,nText);
                            vbox.setFillWidth(false);
                            vbox.getChildren().addAll(mbox,nbox,holdSolver);
                        });
                        actionOutput.getChildren().removeAll(actionOutput.getChildren());
                        actionOutput.setPadding(new Insets(20,0,0,10));
                        actionOutput.getChildren().addAll(newMatrix,vbox);
       }
       private void handleMultDiv(String operation){
           JFXButton newMatrix=new JFXButton("New Matrix");
           newMatrix.setId("newMatrix");
           newMatrix.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
           LinkedList<Integer> list=new LinkedList<>();
           int i=1;
           Collection<Map<String,String>> var=new LinkedList<>();
                        VBox vbox=new VBox();
                        vbox.setId("vbox");
                        vbox.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                        newMatrix.setOnAction(e->{
                            var.clear();
                            list.clear();
                            vbox.getChildren().clear();
                            vbox.setSpacing(30);
                            vbox.setPadding(new Insets(20,20,20,20));
                            TextField mText=new TextField();
                            Label mLabel=new Label("Enter the Number of Rows, m");
                            mLabel.setId("mLabel");
                            mLabel.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                            Label nLabel=new Label("Enter the Number of Cols, n");
                            nLabel.setId("nLabel");
                            nLabel.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                            TextField nText=new TextField();
                            nText.setPrefWidth(50);
                            mText.setPrefWidth(50);
                            JFXButton joinMatrix=new JFXButton("Create Matrix");
                            joinMatrix.setId("create_matrix");
                            joinMatrix.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                            JFXButton solve=new JFXButton("");
                            solve.setId("solve");
                            solve.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                            
                            if(operation.equals("multiplication")){
                                solve.setText("Multiplication");
                            }else if(operation.equals("division")){
                                solve.setText("Division");
                            }else if(operation.equals("inversion")){
                                solve.setText("Inversion Method");
                            }else if(operation.equals("jordan")){
                                solve.setText("Jordan Elimination");
                            }else if(operation.equals("gaussian")){
                                solve.setText("Gaussian Elimination");
                            }else if(operation.equals("lu")){
                                solve.setText("LU Decomposition");
                            }else if(operation.equals("cholesky")){
                                solve.setText("Cholesky Decomposition");
                            }else if(operation.equals("qr")){
                                solve.setText("QR Decomposition");
                            }      
                            
                            LinkedList<Integer> x=new LinkedList<>();
                            LinkedList<Integer> y=new LinkedList<>();
                            joinMatrix.setOnAction(ex->{
                                list.add(i);
                                try{
                                    int pue=Integer.parseInt(mText.getText());
                                    int que=Integer.parseInt(nText.getText());
                                    if(pue>0 && que>0){
                                        if(pue<=7 && que<=20){
                                            x.add(pue);
                                            y.add(que);
                                            Map<String,String> variables=new TreeMap<>();
                                            for(int ii=0;ii<pue;ii++){
                                                HBox mat=new HBox();
                                                mat.setSpacing(10);
                                                for(int jj=0;jj<que;jj++){
                                                    TextField tf=new TextField();
                                                    tf.setPrefWidth(80);
                                                    tf.setId(ii+""+jj);
                                                    tf.textProperty().addListener(
                                                    new ChangeListener() {
                                                        @Override
                                                        public void changed(ObservableValue observable, 
                                                                            Object oldVal, Object newVal) {
                                                            variables.put(tf.getId(), tf.getText());
                                                        }
                                                    });

                                                    mat.getChildren().add(tf);
                                                }

                                                vbox.getChildren().add(mat);

                                            }
                                            var.add(variables);
                                            Label delete=new Label("      ");
                                            vbox.getChildren().add(delete);

                                            if(list.size()>1){
                                                solve.setOnAction(ep->{

                                                LinkedList<double[][]> fold=new LinkedList<>();
                                                int count=0;
                                                for(Map tt:var){
                                                    int xx=x.get(count);
                                                    int yy=y.get(count);
                                                    double[][] opp=new double[xx][yy];
                                                    for(int ii=0;ii<xx;ii++){
                                                            for(int jj=0;jj<yy;jj++){
                                                                try{
                                                                    opp[ii][jj]=Console.eval(Double.toString(Double.parseDouble((String) tt.get(ii+""+jj))));
                                                                }catch(Exception ec){

                                                                }
                                                            }
                                                        }
                                                    fold.add(opp);
                                                    count++;
                                                }

                                                Matrix trans=new Matrix(fold.get(0));
                                                Text label=new Text();
                                                if(operation.equals("multiplication")){
                                                    for(int z=1;z<fold.size();z++){
                                                        try{
                                                            label.setText(Matrix.display(trans.times(new Matrix(fold.get(z)))));
                                                        }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                    }
                                                }else if(operation.equals("division")){
                                                    for(int z=1;z<fold.size();z++){
                                                        try{
                                                            label.setText(Matrix.display(trans.arrayRightDivide(new Matrix(fold.get(z)))));
                                                        }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                    }
                                                }else if(operation.equals("gaussian")){
                                                    for(int z=1;z<fold.size();z++){
                                                        try{
                                                            double[][] tv={GaussianElimination.lsolve(trans.getArray(), fold.get(z)[0])};
                                                            label.setText(Matrix.display(new Matrix(tv).transpose()));
                                                        }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                    }
                                                }else if(operation.equals("inversion")){
                                                    for(int z=1;z<fold.size();z++){
                                                        try{
                                                            double[][] hus=ammend.inversion(trans, new Matrix(fold.get(z))).getArray();
                                                            label.setText(Matrix.display(new Matrix(hus)));
                                                        }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                    }
                                                }else if(operation.equals("jordan")){
                                                    for(int z=1;z<fold.size();z++){
                                                        try{
                                                            GaussJordanElimination gaussJordanElimination = new GaussJordanElimination(trans.getArray(),fold.get(z)[0]);
                                                            gaussJordanElimination.solve();
                                                            label.setText(Matrix.display(Console.evaluate(gaussJordanElimination.show())));
                                                        }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                    }
                                                }else if(operation.equals("lu")){
                                                    for(int z=1;z<fold.size();z++){
                                                        try{
                                                            LUDecomposition A=new LUDecomposition(trans);
                                                            label.setText(Matrix.display(Console.evaluate(Console.format(A.solve(new Matrix(fold.get(z))).getArray()))));
                                                        }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                    }
                                                }else if(operation.equals("qr")){
                                                    for(int z=1;z<fold.size();z++){
                                                        try{
                                                            QRDecomposition A=new QRDecomposition(trans);
                                                            label.setText(Matrix.display(Console.evaluate(Console.format(A.solve(new Matrix(fold.get(z))).getArray()))));
                                                        }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                    }
                                                }
                                                else if(operation.equals("cholesky")){
                                                    for(int z=1;z<fold.size();z++){
                                                        try{
                                                            CholeskyDecomposition A=new CholeskyDecomposition(trans);
                                                            label.setText(Matrix.display(Console.evaluate(Console.format(A.solve(new Matrix(fold.get(z))).getArray()))));
                                                        }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                    }
                                                }
                                                label.setFont(Font.font(fontTextEditor.getValue(), FontWeight.LIGHT, Double.parseDouble(fontSizeEditor.getValue())));
                                                outputContainer.getChildren().add(label);
                                                outputScroll.vvalueProperty().bind(outputContainer.heightProperty());

                                            });
                                            }
                                            if(list.size()==2){
                                                nText.setDisable(true);
                                                mText.setDisable(true);
                                                joinMatrix.setDisable(true);
                                            }
                                        }else{
                                        Alert alert=new Alert(Alert.AlertType.WARNING);
                                        alert.setTitle("");
                                        alert.setContentText("Adjust Matrix Dimension");
                                        alert.showAndWait();
                                    }
                                    }else{
                                        Alert alert=new Alert(Alert.AlertType.WARNING);
                                        alert.setTitle("");
                                        alert.setContentText("Adjust Matrix Dimension");
                                        alert.showAndWait();
                                    }
                                }catch(Exception exception){
                                    
                                }
                            });

                            HBox mbox=new HBox();
                            mbox.setSpacing(10);
                            mbox.getChildren().addAll(mLabel,mText);
                            HBox nbox=new HBox();
                            HBox holdSolver=new HBox();
                            holdSolver.getChildren().addAll(joinMatrix,solve);
                            holdSolver.setSpacing(10);
                            nbox.setSpacing(10);
                            nbox.setPadding(new Insets(0,0,0,10));
                            mbox.setPadding(new Insets(0,0,0,10));
                            nbox.getChildren().addAll(nLabel,nText);
                            vbox.setFillWidth(false);
                            vbox.getChildren().addAll(mbox,nbox,holdSolver);
                        });
                        actionOutput.getChildren().removeAll(actionOutput.getChildren());
                        actionOutput.setPadding(new Insets(20,0,0,10));
                        actionOutput.getChildren().addAll(newMatrix,vbox);
       }
       private void handlePower(String operation){
           JFXButton newMatrix=new JFXButton("New Matrix");
           newMatrix.setId("newMatrix");
           newMatrix.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                        VBox vbox=new VBox();
                        vbox.setId("vbox");
                        vbox.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                        newMatrix.setOnAction(e->{
                            vbox.getChildren().clear();
                            vbox.setSpacing(10);
                            vbox.setPadding(new Insets(20,20,20,20));
                            TextField mText=new TextField();
                            Label mLabel=new Label("Enter the Number of Rows, m");
                            mLabel.setId("mLabel");
                            mLabel.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                            Label nLabel=new Label("Enter the Number of Cols, n");
                            nLabel.setId("nLabel");
                            nLabel.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                            TextField nText=new TextField();
                            nText.setPrefWidth(50);
                            mText.setPrefWidth(50);
                            
                            Label nPow=new Label("Enter the Power, p");
                            nPow.setId("nLabel");
                            nPow.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                            TextField pText=new TextField();
                            pText.setPrefWidth(50);
                            
                            JFXButton joinMatrix=new JFXButton("Create Matrix");
                            joinMatrix.setId("create_matrix");
                            joinMatrix.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                            joinMatrix.setOnAction(ex->{
                                try{
                                    int x,y,z;
                                    x=Integer.parseInt(mText.getText());
                                    y=Integer.parseInt(nText.getText());
                                    z=Integer.parseInt(pText.getText());
                                   if(y>0 && x>0){
                                       if(x<=7 && y<=20){
                                            Map<String,String> variables=new TreeMap<>();
                                            for(int ii=0;ii<x;ii++){
                                                HBox mat=new HBox();
                                                mat.setSpacing(10);
                                                for(int jj=0;jj<y;jj++){
                                                    TextField tf=new TextField();
                                                    tf.setPrefWidth(80);
                                                    tf.setId(ii+""+jj);
                                                    tf.textProperty().addListener(
                                                    new ChangeListener() {
                                                        @Override
                                                        public void changed(ObservableValue observable, 
                                                                            Object oldVal, Object newVal) {
                                                            variables.put(tf.getId(), tf.getText());
                                                        }
                                                    });

                                                    mat.getChildren().add(tf);
                                                }
                                                vbox.getChildren().add(mat);
                                            }
                                            JFXButton solve=null;

                                            if(operation.equals("power")){
                                                solve=new JFXButton("Power");
                                            }
                                            solve.setId("solve");
                                            solve.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
                                            solve.setOnAction(ep->{
                                                double[][] transpose=new double[x][y];
                                                for(int ii=0;ii<x;ii++){
                                                    for(int jj=0;jj<y;jj++){
                                                        try{
                                                            transpose[ii][jj]=Console.eval(Double.toString(Double.parseDouble(variables.get(ii+""+jj))));
                                                        }catch(Exception ec){

                                                        }
                                                    }
                                                }
                                                Matrix trans=new Matrix(transpose);
                                                Matrix pow=new Matrix(new double[][]{{Integer.parseInt(pText.getText())}});
                                                Text label=new Text();
                                                if(operation.equals("power")){
                                                    try{
                                                        label.setText(Matrix.display(Matrix.pow(trans, pow)));
                                                    }catch(Exception t){
                                                            label.setText(t.getMessage());
                                                        }
                                                }
                                                label.setFont(Font.font(fontTextEditor.getValue(), FontWeight.LIGHT, Double.parseDouble(fontSizeEditor.getValue())));
                                                outputContainer.getChildren().add(label);
                                                outputScroll.vvalueProperty().bind(outputContainer.heightProperty());

                                            });

                                            vbox.getChildren().add(solve);
                                       }else{
                                            Alert alert=new Alert(Alert.AlertType.WARNING);
                                            alert.setTitle("");
                                            alert.setContentText("Adjust Matrix Dimension");
                                            alert.showAndWait();
                                       }
                                   }else{
                                        Alert alert=new Alert(Alert.AlertType.WARNING);
                                        alert.setTitle("");
                                        alert.setContentText("Adjust Matrix Dimension");
                                        alert.showAndWait();
                                   }
                                }catch(Exception exception){
                                    
                                }
                            });

                            HBox mbox=new HBox();
                            mbox.setSpacing(10);
                            mbox.getChildren().addAll(mLabel,mText);
                            HBox nbox=new HBox();
                            nbox.setSpacing(10);
                            nbox.setPadding(new Insets(0,0,0,10));
                            mbox.setPadding(new Insets(0,0,0,10));
                            nbox.getChildren().addAll(nLabel,nText);
                            HBox pbox=new HBox();
                            pbox.setSpacing(10);
                            pbox.setPadding(new Insets(0,0,0,10));
                            pbox.getChildren().addAll(nPow,pText);
                            vbox.setFillWidth(false);
                            vbox.getChildren().addAll(mbox,nbox,pbox,joinMatrix);
                        });
                        actionOutput.getChildren().removeAll(actionOutput.getChildren());
                        actionOutput.setPadding(new Insets(20,0,0,10));
                        actionOutput.getChildren().addAll(newMatrix,vbox);
       }
       
        @Override
        public void handle(ActionEvent event) {
            if(variableList.keySet().contains(btn.getText())){
                cmd.setText(cmd.getText()+""+variableList.get(btn.getText()));
            }else{
                switch(btn.getText()){
                    case "Addition":
                        handleBigO("addition");
                        break;
                    case "Subtraction":
                        handleBigO("subtraction");
                        break;
                    case "Multiplication":
                        handleMultDiv("multiplication");
                        break;
                    case "Division":
                        handleMultDiv("division");
                        break;
                    case "Transpose":
//                         WritableImage wi = new WritableImage(400,927);
//                         Image img1=actionOutput.snapshot(new SnapshotParameters(), wi);
//                         ImageView imgView=new ImageView(img1);
                        handle("transpose");
                        
                        break;
                    case "Inverse":
                        handle("inverse");
                        break;
                    case "Determinant":
                        handle("determinant");
                        break;
                    case "Rank":
                        handle("rank");
                        break;
                    case "Trace":
                        handle("trace");
                        break;
                    case "Power":
                        handlePower("power");
                        break;
                    case "Norm 1":
                        handle("norm1");
                        break;
                    case "Norm 2":
                        handle("norm2");
                        break;
                    case "Norm 3":
                        handle("normF");
                        break;
                    case "Norm 4":
                        handle("normInf");
                        break;
                    case "EigenValues":
                        handle("eigValues");
                        break;
                    case "EigenVectors":
                        handle("eigVectors");
                        break;
                    case "Inversion Method":
                        handleMultDiv("inversion");
                        break;
                    case "Gaussian Elimination":
                        handleMultDiv("gaussian");
                        break;
                    case "Jordan Elimation":
                        handleMultDiv("jordan");
                        break;
                    case "Cholesky Decomposition":
                        handleMultDiv("cholesky");
                        break;
                    case "LU Decomposition":
                        handleMultDiv("lu");
                        break;
                    case "QR Decomposition":
                        handleMultDiv("qr");
                        break;
                    case "Echelon/Gaussian Method":
                        handle("echelon");
                        break;
                    case "Lower Triangular Factor":
                        handle("lower");
                        break;
                    case "Upper Triangular Factor":
                        handle("upper");
                        break;
                    case "Simplex":
                        handle("simplex");
                        break;
                    case "Plot Area":
                        handle("area");
                        break;
                    case "Plot Line":
                        handle("line");
                        break;
                }
            }
            
        }
       
   } 
}
