import java.time.Duration;
import java.util.HashMap;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
//import javax.swing.event.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;

/**
 * 
 * @author Shifang This class creates the user interface for getting the input
 *         values needed to send text reminders
 * 
 *
 */
public class Main extends Application {

	String ph;
	String ln;
	String dept;
	String direction;
	String dtextt;
	String dir;
	//HashMap<String, Request> req = new HashMap<String, Request>();

	@Override
	public void start(Stage stage) {

		GetData getData = new GetDataImpl();

		TextSender textSender = new TextSenderImpl();

		MBTAService service = new MBTAService(getData, textSender);

		// creating label Phone number
		Text text1 = new Text("Phone number");

		// creating label Desired line
		Text text2 = new Text("Line");

		HBox box = new HBox(20);
		box.setPadding(new Insets(10, 10, 10, 10));
		ObservableList<String> lineColor = FXCollections.observableArrayList("Red", "Blue", "Orange");

		final ObservableList<String> redStations = FXCollections.observableArrayList("Alewife", "Porter", "Harvard",
				"Kendall/MIT", "Charles/MGH", "Park Street", "South Station", "Andrew", "Ashmont");

		final ObservableList<String> blueStations = FXCollections.observableArrayList("WonderLand", "Beachmont",
				"Orient Heights", "Wood Island", "Airport", "Aquarium", "Government Center", "Bowdoin");

		final ObservableList<String> orangeStations = FXCollections.observableArrayList("Oak Grove", "Malden Center",
				"Assembly", "North Station", "State", "Chinatown", "Back Bay", "Massachusetts Avenue", "Jackson Square",
				"Forest Hills");

		final ObservableList<String> redDirection = FXCollections.observableArrayList("Alewife", "Ashmont");

		final ObservableList<String> blueDirection = FXCollections.observableArrayList("WonderLand", "Bowdoin");

		final ObservableList<String> orangeDirection = FXCollections.observableArrayList("Oak Grove", "Forest Hills");

		ComboBox comboBox1 = new ComboBox(lineColor);
		final ComboBox comboBox2 = new ComboBox();
		comboBox1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

			public void changed(ObservableValue ov, Object t, Object t1) {

				switch (t1.toString()) {
				case "Red":
					comboBox2.setItems(redStations);
					break;
				case "Blue":
					comboBox2.setItems(blueStations);
					break;
				case "Orange":
					comboBox2.setItems(orangeStations);
					break;

				}
			}
		});

		final ComboBox comboBox3 = new ComboBox();
		comboBox1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

			public void changed(ObservableValue ov, Object t, Object t1) {

				switch (t1.toString()) {
				case "Red":
					comboBox3.setItems(redDirection);
					break;
				case "Blue":
					comboBox3.setItems(blueDirection);
					break;
				case "Orange":
					comboBox3.setItems(orangeDirection);
					break;

				}
			}
		});

		// creating label Departure station
		Text text3 = new Text("Departure");

		// creating label Destination station
		Text text4 = new Text("Direction");

		// creating label Desired arrival time
		Text text6 = new Text("Desired text time");

		// creating label Desired text time
		// Text text7 = new Text("Desired text time");

		// Creating Text Filed for email
		TextField textField1 = new TextField();

		Text datePrompt = new Text();

		// Horizontal box for the hr/min/am-pm values
		HBox enterTime = new HBox();

		final ComboBox<String> trainHour = new ComboBox();
		trainHour.getItems().addAll("Hr", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
				"14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
		trainHour.getSelectionModel().select(0);

		final ComboBox<String> trainMin = new ComboBox();
		trainMin.getItems().addAll("Min", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				"30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
				"47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59");
		trainMin.getSelectionModel().select(0);

		enterTime.getChildren().addAll(trainHour, trainMin);

		// Creating Buttons
		Button button1 = new Button("Submit");
		Button button2 = new Button("Clear");

		// Creating a Grid Pane
		GridPane gridPane = new GridPane();

		// Setting size for the pane
		gridPane.setMinSize(400, 200);

		// Setting the padding
		gridPane.setPadding(new Insets(10, 10, 10, 10));

		// Setting the vertical and horizontal gaps between the columns
		gridPane.setVgap(5);
		gridPane.setHgap(5);

		// Setting the Grid alignment
		gridPane.setAlignment(Pos.CENTER);

		// Arranging all the nodes in the grid
		gridPane.add(text1, 0, 0);
		gridPane.add(textField1, 1, 0);
		gridPane.add(text2, 0, 1);
		gridPane.add(comboBox1, 1, 1);

		gridPane.add(text3, 0, 2);
		gridPane.add(comboBox2, 1, 2);
		gridPane.add(text4, 0, 3);
		gridPane.add(comboBox3, 1, 3);
		gridPane.add(text6, 0, 5);
		gridPane.add(enterTime, 1, 5);

		gridPane.add(button1, 0, 7);
		gridPane.add(button2, 1, 7);

		// Styling nodes
		button1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		button2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

		text1.setStyle("-fx-font: normal bold 20px 'serif' ");
		text2.setStyle("-fx-font: normal bold 20px 'serif' ");

		text3.setStyle("-fx-font: normal bold 20px 'serif' ");
		text4.setStyle("-fx-font: normal bold 20px 'serif' ");
		// text5.setStyle("-fx-font: normal bold 20px 'serif' ");
		text6.setStyle("-fx-font: normal bold 20px 'serif' ");


		gridPane.setStyle("-fx-background-color: BEIGE;");

		// Adding a Label
		final Label label = new Label();
		gridPane.setConstraints(label, 0, 8);
		gridPane.setColumnSpan(label, 2);
		gridPane.getChildren().add(label);

		button1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if ((textField1.getText() != null && !textField1.getText().isEmpty())) {
					label.setText(textField1.getText() + " " + "thank you for your phone number!");
					ph = textField1.getText();
					ln = comboBox1.getSelectionModel().getSelectedItem().toString();
					dept = comboBox2.getSelectionModel().getSelectedItem().toString();
					direction = comboBox3.getSelectionModel().getSelectedItem().toString();
					dtextt = trainHour.getSelectionModel().getSelectedItem().toString() + ":"
							+ trainMin.getSelectionModel().getSelectedItem().toString();

					// create a Request object to hold information
					Request request = new Request(ln, getId(dept), getDirection(direction), dtextt);

					// add Request object into hashtable stored in MBTAService object
					service.addEntry(ph, request);

				} else {
					label.setText("You have not left a phone number.");
				}

			}

		});

		button2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				textField1.clear();
				comboBox1.setValue("");
				comboBox2.setValue("");
				comboBox3.setValue("");
				trainHour.getSelectionModel().select(0);
				trainMin.getSelectionModel().select(0);
				label.setText(null);
				ph = "";
				ln = "";
				dept = "";
				direction = "";
				dtextt = "";
			}
		});

		// Creating a scene object
		Scene scene = new Scene(gridPane);

		// Setting title to the Stage
		stage.setTitle("Train Reminder App");

		// Adding scene to the stage
		stage.setScene(scene);

		// Displaying the contents of the stage
		stage.show();

		service.setPeriod(javafx.util.Duration.seconds(10));
		service.start();
	}

	public static String getId(String dept) {
		String id = "";
		if (dept.equals("Alewife")) {
			id = "place-alfcl";
		}
		if (dept.equals("Porter")) {
			id = "place-portr";
		}
		if (dept.equals("Harvard")) {
			id = "place-harsq";
		}
		if (dept.equals("Kendall/MIT")) {
			id = "place-knncl";
		}
		if (dept.equals("Charles/MGH")) {
			id = "place-chmnl";
		}
		if (dept.equals("Park Street")) {
			id = "place-pktrm";
		}
		if (dept.equals("South Station")) {
			id = "place-sstat";
		}
		if (dept.equals("Andrew")) {
			id = "place-andrw";
		}
		if (dept.equals("Ashmont")) {
			id = "place-asmnl";
		}

		if (dept.equals("WonderLand")) {
			id = "place-wondl";
		}
		if (dept.equals("Beachmont")) {
			id = "place-bmmnl";
		}
		if (dept.equals("Orient Heights")) {
			id = "place-orhte";
		}
		if (dept.equals("Wood Island")) {
			id = "place-wimnl";
		}
		if (dept.equals("Airport")) {
			id = "place-aport";
		}
		if (dept.equals("Aquarium")) {
			id = "place-aqucl";
		}
		if (dept.equals("Government Center")) {
			id = "place-gover";
		}
		if (dept.equals("Bowdoin")) {
			id = "place-bomnl";
		}

		if (dept.equals("Oak Grove")) {
			id = "place-ogmnl";
		}
		if (dept.equals("Malden Center")) {
			id = "place-mlmnl";
		}
		if (dept.equals("Assembly")) {
			id = "place-astao";
		}
		if (dept.equals("North Station")) {
			id = "place-north";
		}
		if (dept.equals("State")) {
			id = "place-state";
		}
		if (dept.equals("Chinatown")) {
			id = "place-chncl";
		}
		if (dept.equals("Back Bay")) {
			id = "place-bbsta";
		}
		if (dept.equals("Massachusetts Avenue")) {
			id = "place-masta";
		}
		if (dept.equals("Jackson Square")) {
			id = "place-jaksn";
		}
		if (dept.equals("Forest Hills")) {
			id = "place-forhl";
		}
		return id;
	}

	public static String getDirection(String arri) {
		String dir = "";
		if (arri.equals("Alewife") || arri.equals("Oak Grove") || arri.equals("Wonderland")) {
			dir = "1";
		}
		if (arri.equals("Ashmont") || arri.equals("Forest Hills") || arri.equals("Bowdoin")) {
			dir = "0";
		}
		return dir;
	}

	// runner
	public static void main(String[] args) throws InterruptedException {
		Application.launch(args);
	}
}
