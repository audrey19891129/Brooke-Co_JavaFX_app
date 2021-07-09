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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import model.History;
import model.Product;

public class AddBookController {
	int wareCode;
	String picture;
	Alert alert;
	//ObservableList<Product> data = MainController.getDataP();
	ObservableList<String> list = FXCollections.observableArrayList("programming", "Literature", "Geography");
	ObservableList<String> wareHouse = FXCollections.observableArrayList(
			"500 Brunswick blvd Montreal H9E 1G9",
			"1025 Pierrefond bldv Montreal H9E 1G8",
			"126 Gouin O Montreal H9E 2G4"
			);
	ObservableList<String> pic = FXCollections.observableArrayList("no picture");
	
	
	@FXML
	public Label lblISBN;
	
	@FXML
	public TextField txtPcode;
	
	@FXML
	public TextField txtTitle;
	
	@FXML
	public TextField txtPrice;
	
	@FXML
	public ComboBox<String> cbPicture;
	
	@FXML
	public TextField txtGenre;
	
	@FXML
	public ComboBox<String> cbWareHouse;
	
	@FXML
	public TextField txtMinimum;
	
	@FXML
	public TextField txtAuthor;
	
	@FXML
	public TextField txtPubco;
	
	@FXML
	public ComboBox<String> cmbCategory;
	
	@FXML
	public DatePicker txtDate;
	
	public void initialize() {
		cmbCategory.setItems(list);
		cbWareHouse.setItems(wareHouse);
		cbPicture.setItems(pic);
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
	    public void addBook(MouseEvent event) throws IOException, JSONException {
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
			obj.put("pcode", lblISBN.getText() + txtPcode.getText());
			obj.put("category", cmbCategory.getValue());
			obj.put("price", new Double(txtPrice.getText()));
			obj.put("picture", picture);
			obj.put("title", txtTitle.getText());
			obj.put("genre", txtGenre.getText());
			obj.put("warehousecode", wareCode);
			obj.put("minimum", new Integer(txtMinimum.getText()));
			obj.put("authors", txtAuthor.getText());
			obj.put("pubCo", txtPubco.getText());
			obj.put("pubDate", txtDate.getValue());
			
			System.out.println(obj);
			
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost("http://localhost:56023/StockServices.svc/Post_AddBook");
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
				History.addToHistory(LoginController.employee.getId(), "CREATED", "New Book with code : [ " + lblISBN.getText()
				+ txtPcode.getText() + " ] ");
	    }
		
		
	    @FXML
	    void clear(MouseEvent event) {
	    	txtPcode.setText("");
	    	txtTitle.setText("");
			cmbCategory.setValue("");
			txtPrice.setText("");
			txtGenre.setText("");
			txtMinimum.setText("");
			txtAuthor.setText("");
			txtPubco.setText("");	
			txtDate.setValue(null);
	    }

	    @FXML
	    void closeWindow(MouseEvent event) {
	    	MainController.main.setDisable(false);
	    	MainController.NewPage.close();
	    }

}
