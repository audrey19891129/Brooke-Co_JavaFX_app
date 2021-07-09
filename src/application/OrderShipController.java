package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.Book;
import model.Entry;
import model.History;

public class OrderShipController {

	@FXML
	private Label lblTotal;
	
	@FXML
	private TableView<Entry> tvEntryClient;
	
	@FXML
	private TableColumn<Entry, Integer> tcOrderId;
	
	@FXML
	private TableColumn<Entry, String> tcPcode;
	
	@FXML
	private TableColumn<Entry, Integer> tcQuantity;
	
	@FXML
	private TableColumn<Entry, Double> tcPrice;
	
	@FXML
	private TableColumn<Entry, Double> tcSubTotal;
	
	 public static ObservableList<Entry> dataEntry;
	
	public void initialize() throws NumberFormatException, JSONException, IOException {
		ArrayList<Entry> list = Entry.getListEntry(Integer.valueOf(MainController.code));
		
		dataEntry = FXCollections.observableArrayList(list);
		tcOrderId.setCellValueFactory(new PropertyValueFactory<>("order_id"));
		tcPcode.setCellValueFactory(new PropertyValueFactory<>("pcode"));
		tcQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		tcPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		tcSubTotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
		tvEntryClient.setItems(dataEntry);
		
		lblTotal.setText(String.valueOf(MainController.total));
		
	}
	
	public void shipping(ActionEvent event) throws NumberFormatException, JSONException, IOException {
		Dialog<String> dialog = new Dialog<>();
	    dialog.setTitle("Confirmation");
	    dialog.setHeaderText("Veuillez entree votre Mot de Passe pour confirmer la Livraison");
	    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
	    dialog.initOwner(MainController.NewPage);

	    PasswordField pwd = new PasswordField();
	    HBox content = new HBox();
	    content.setAlignment(Pos.CENTER_LEFT);
	    content.setSpacing(10);
	    content.getChildren().addAll(new Label("Votre Mot de Pass : "), pwd);
	    dialog.getDialogPane().setContent(content);
	    dialog.setResultConverter(dialogButton -> {
	        if (dialogButton == ButtonType.OK) {
	            return pwd.getText();
	        }
	        return null;
	    });

	    Optional<String> result = dialog.showAndWait();
	    if (LoginController.employee.getPassword().equals(result.get())) {
	    	JSONObject obj = new JSONObject();
	    	obj.put("receiver", "jeanmartinletourneau@gmail.com");
	    	obj.put("order_id", Integer.valueOf(MainController.code));
	    	obj.put("name", MainController.name);
	    	obj.put("lastname", MainController.lastname);
	    	
	    	HttpClient client = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost("http://localhost:56023/StockServices.svc/SendMail");
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
					System.out.println(output);
				}
	    	
	    	MainController.main.setDisable(false);
	    	MainController.NewPage.close();
	    	History.addToHistory(LoginController.employee.getId(), "SHIPPED", "The Order number #" + MainController.code);
	    	Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setContentText(output);
    		alert.setHeaderText("Success");
    		alert.initOwner(MainController.NewPage);
    		alert.showAndWait();
	    }else {
	    	Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Erreur Vérifier votre mot de pass a nouveaux");
    		alert.setHeaderText("Erreur : Mot De Passe Incorrect");
    		alert.initOwner(MainController.NewPage);
    		alert.showAndWait();
	    }
	}
	
	public void close(MouseEvent event) {
		MainController.main.setDisable(false);
    	MainController.NewPage.close();
	}
}
