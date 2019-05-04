import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Text; 
import javafx.scene.control.TextField;
  
/**
 * 
 * @author ShiFang
 * This class creates the user interface for getting the input values needed to send text reminders
 *  
 *
 */
public class Main extends Application {
	
	String ph;
	String ln;
	String dept;
    String arri;
	String wkd;
	String darrivt;
	String dtextt;
	
	@Override
	public void start(Stage stage) {   
		
		  GetData getData = new GetDataImpl();
		  
		  TextSender textSender = new TextSenderImpl();
		  
		  MBTAService service = new MBTAService(getData, textSender);
		
	      //creating label Phone number 
	      Text text1 = new Text("Phone number");       
	      
	      //creating label Desired line
	      Text text2 = new Text("Line"); 
	       
	      //creating label Departure station 
	      Text text3 = new Text("Departure"); 
	      
	      //creating label Destination station 
	      Text text4 = new Text("Arrival"); 
	      
	      //creating label Desired weekday 
	      //Text text5 = new Text("Desired weekday");
	      
	     // ObservableList<String> names = FXCollections.observableArrayList( 
		//         "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"); 
	    //  ListView<String> dayListView = new ListView<String>(names); 
	      
	      //creating label Desired arrival time
	      Text text6 = new Text("Desired arrival time");
	      
	      //creating label Desired text time
	      Text text7 = new Text("Desired text time");
	      
	           
	      TextField textField1 = new TextField();       
	      TextField textField2 = new TextField();   
	      TextField textField3 = new TextField();   
	      TextField textField4 = new TextField();   
	      //TextField textField5 = new TextField();   
	      TextField textField6 = new TextField();   
	      TextField textField7 = new TextField();   
	       
	      //Creating Buttons 
	      Button button1 = new Button("Submit"); 
	      Button button2 = new Button("Clear");  
	      
	      //Creating a Grid Pane 
	      GridPane gridPane = new GridPane();    
	      
	      //Setting size for the pane 
	      gridPane.setMinSize(400, 200); 
	      
	      //Setting the padding  
	      gridPane.setPadding(new Insets(10, 10, 10, 10)); 
	      
	      //Setting the vertical and horizontal gaps between the columns 
	      gridPane.setVgap(5); 
	      gridPane.setHgap(5);       
	      
	      //Setting the Grid alignment 
	      gridPane.setAlignment(Pos.CENTER); 
	       
	      //Arranging all the nodes in the grid 
	      gridPane.add(text1, 0, 0); 
	      gridPane.add(textField1, 1, 0); 
	      gridPane.add(text2, 0, 1);       
	      gridPane.add(textField2, 1, 1); 
	      
	      gridPane.add(text3, 0, 2); 
	      gridPane.add(textField3, 1, 2); 
	      gridPane.add(text4, 0, 3); 
	      gridPane.add(textField4, 1, 3); 
	      //gridPane.add(text5, 0, 4); 
	      //gridPane.add(textField5, 1, 4); 
	      gridPane.add(text6, 0, 5); 
	      gridPane.add(textField6, 1, 5); 
	      gridPane.add(text7, 0, 6); 
	      gridPane.add(textField7, 1, 6); 
	      
	      gridPane.add(button1, 0, 7); 
	      gridPane.add(button2, 1, 7); 
	       
	      //Styling nodes  
	      button1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 
	      button2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 
	       
	      text1.setStyle("-fx-font: normal bold 20px 'serif' "); 
	      text2.setStyle("-fx-font: normal bold 20px 'serif' ");  
	      
	      text3.setStyle("-fx-font: normal bold 20px 'serif' ");
	      text4.setStyle("-fx-font: normal bold 20px 'serif' ");
	      //text5.setStyle("-fx-font: normal bold 20px 'serif' ");
	      text6.setStyle("-fx-font: normal bold 20px 'serif' ");
	      text7.setStyle("-fx-font: normal bold 20px 'serif' ");
	      
	      gridPane.setStyle("-fx-background-color: BEIGE;"); 
	      
	      
	     //Adding a Label
	      final Label label = new Label();
	      GridPane.setConstraints(label, 0, 8);
	      GridPane.setColumnSpan(label, 2);
	      gridPane.getChildren().add(label);
	      
	      button1.setOnAction(new EventHandler<ActionEvent>() {
		      @Override
		      public void handle(ActionEvent e) {
		          if ((textField1.getText() != null && !textField1.getText().isEmpty())) {
		              label.setText(textField1.getText() + " " 
		                  + "thank you for your phone number!");
		              
		          // save values from text fields into variables
		          ph=textField1.getText();
		          ln=textField2.getText();
		          dept=textField3.getText();
		          arri=textField4.getText();
		          //wkd=textField5.getText();
		          darrivt=textField6.getText();
		          dtextt=textField7.getText();
		          
		          // create a Request object to hold information
		          Request request = new Request(ln, dept, arri, darrivt, dtextt);
		          
		          // add Request object into hashtable stored in MBTAService object
		          service.addEntry(ph, request);
		          
		          } else {
		              label.setText("You have not left a phone number.");
		          }
		       }
		   });
	      
	      button2.setOnAction(new EventHandler<ActionEvent>() {

		  //@Override
		      public void handle(ActionEvent e) {
			  textField1.clear();
			  textField2.clear();
			  textField3.clear();
			  textField4.clear();
			 // textField5.clear();
			  textField6.clear();
			  textField7.clear();
		      label.setText(null);
		          ph="";
		          ln="";
		          dept="";
		          arri="";
		         // wkd="";
		          darrivt="";
		          dtextt="";
		          
		      }
		  });
	      
	    	      
	      //Creating a scene object 
	      Scene scene = new Scene(gridPane); 
	       
	      //Setting title to the Stage 
	      stage.setTitle("Train Reminder App"); 
	         
	      //Adding scene to the stage 
	      stage.setScene(scene);
	      
	      //Displaying the contents of the stage 
	      stage.show(); 
	     
	   } 
	
	// runner 
	public static void main(String[] args) throws InterruptedException {
		//Application.launch(args);
		//new TextSenderImpl().sendText("16037276698", new MBTAReply("","","",""));
		
	}
}
