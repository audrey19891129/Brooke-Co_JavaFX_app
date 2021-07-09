package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import model.History;
import model.Product;

public class AddGameController {
	int wareCode;
	String picture;
	Alert alert;
	//ObservableList<Product> data = MainController.getDataP();
	ObservableList<String> list = FXCollections.observableArrayList("PS4", "Wii U", "Xbox One");
	ObservableList<String> wareHouse = FXCollections.observableArrayList(
			"500 Brunswick blvd Montreal H9E 1G9",
			"1025 Pierrefond bldv Montreal H9E 1G8",
			"126 Gouin O Montreal H9E 2G4"
			);
	ObservableList<String> pic = FXCollections.observableArrayList("no picture");
	ObservableList<String> listG = FXCollections.observableArrayList(
			"7th Generation", 
			"Action", "Adventure", 
			"Dance",
			"Fighting", 
			"Horror", 
			"Music & Party", 
			"PlayStation Exclusive", 
			"Racing", "Role-Playing", 
			"RPG", "Shooters", 
			"Simulation", 
			"Sports"
			);

	@FXML
	public TextField txtPcode;
	
	@FXML
	public TextField txtTitle;
	
	@FXML
	public TextField txtPrice;
	
	@FXML
	public ComboBox<String> cbPicture;
	
	@FXML
	public ComboBox<String> cbWareHouse;
	
	@FXML
	public TextField txtMinimum;
	
	@FXML
	public TextField txtConsole;
	
	@FXML
	public TextField txtCompany;
	
	@FXML
	public ComboBox<String> cmbCategory;
	
	@FXML
	public ComboBox<String> cmbGenre;
	
	@FXML
	public DatePicker txtDate;
	
	public void initialize() {
		cbWareHouse.setItems(wareHouse);
		cbPicture.setItems(pic);
		cmbCategory.setItems(list);
		cmbGenre.setItems(listG);
		cmbCategory.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
	           txtConsole.setText((String) newValue);
	    });
		txtDate.setConverter(new StringConverter<LocalDate>() {
			String pattern = "yyyy-MM-dd";
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
			{
				txtDate.setPromptText(pattern.toLowerCase());
			}
			@Override
			public LocalDate fromString(String string) {
				if(string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				}else {
					return null;
				}
			}
			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return dateFormatter.format(date);
				}else {
					return "";
				}
			}
		});
	}
	
	
	@FXML
    public void addGame(MouseEvent event) throws JSONException, IOException {
		switch(cbWareHouse.getValue()) {
		case "500 Brunswick blvd Montreal H9E 1G9":
			wareCode = 1;
			break;
		case "1025 Pierrefond bldv Montreal H9E 1G8":
			wareCode = 2;
			break;
		case "126 Gouin O Montreal H9E 2G4":
			wareCode = 3;
			break;
		}
		switch(cbPicture.getValue()) {
		case "no picture":
			picture = "images/noPicture.jpg";
			break;
		default:
				picture = cbPicture.getValue();
		}
		
		JSONObject obj = new JSONObject();
		obj.put("pcode", txtPcode.getText());
		obj.put("category", cmbCategory.getValue());
		obj.put("price", new Double(txtPrice.getText()));
		obj.put("picture", picture);
		obj.put("title", txtTitle.getText());
		obj.put("genre", cmbGenre.getValue());
		obj.put("warehousecode", wareCode);
		obj.put("minimum", new Integer(txtMinimum.getText()));
		obj.put("console", txtConsole.getText());
		obj.put("company", txtCompany.getText());
		obj.put("reldate", txtDate.getValue());
		
		System.out.println(obj);
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost("http://localhost:56023/StockServices.svc/Post_AddGame");
		StringEntity input = new StringEntity(obj.toString());
		input.setContentType("application/json");
		request.setEntity(input);
		HttpResponse response = client.execute(request);
			if(response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output;
			System.out.println("Output from the Server .... \n");
			while((output = br.readLine()) != null) {
				MainController.NewPage.close();
				System.out.println(output);
				alert = new Alert(AlertType.WARNING);
				alert.setTitle("BOOK ADDED");
				alert.setHeaderText("Warning !");
				alert.setContentText(output);
				alert.initStyle(StageStyle.UNDECORATED);
				alert.showAndWait();
				MainController.main.setDisable(false);
			}
			History.addToHistory(LoginController.employee.getId(), "CREATED", "New Game with code : [ " + txtPcode.getText() + " ]");
	}
	
	@FXML
    void clear(MouseEvent event) {
		
	}
	
	@FXML
    void closeWindow(MouseEvent event) {
    	MainController.main.setDisable(false);
    	MainController.NewPage.close();
    }
}
