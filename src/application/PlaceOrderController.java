package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
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
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import model.History;
import model.Provider;

public class PlaceOrderController {
	
	ObservableList<String> list;
	public int id;
	
	@FXML
	private Button btnPlaceOrder;
	
	@FXML
	private Label lblPcode;
	
	@FXML
	private Label lblTitle;
	
	@FXML
	private Label lblType;
	
	@FXML
	private Label lblProvider;
	
	@FXML
	private Label lblAdress;
	
	@FXML
	private Label lblPhone;
	
	@FXML
	private Label lblUnit;
	
	@FXML
	private Label lblTotal;
	
	@FXML
	private ComboBox<String> cmbProvider;
	
	@FXML
	private TextField txtQuantity;
	
	@FXML
	public RadioButton rbVisa;
	
	@FXML
	private RadioButton rbMaster;
	
	@FXML
	private RadioButton rbDebit;
	
	public ToggleGroup cards;
	
	public void initialize() throws ClientProtocolException, IOException, JSONException {
		cards = new ToggleGroup();
		rbVisa.setToggleGroup(cards);
		rbMaster.setToggleGroup(cards);
		rbDebit.setToggleGroup(cards);
		
		btnPlaceOrder.setDisable(true);
		ArrayList<Provider> li = Provider.getListProvider();
		lblPcode.setText(MainController.code);
		lblTitle.setText(MainController.title);
		lblType.setText(MainController.result);
		txtQuantity.textProperty().addListener((observalble, oldValue, newValue) -> {
			double uni = Double.valueOf(lblUnit.getText());
			int qte = Integer.valueOf(newValue);
			double total = uni * qte;
			String tot = String.valueOf(Math.round(total * 100.0) / 100.0);
			lblTotal.setText(tot);
			if(total > 0) {
				btnPlaceOrder.setDisable(false);
			}else {
				btnPlaceOrder.setDisable(true);
			}
		});
		
		switch(lblType.getText()) {
		case "book" :
			list = FXCollections.observableArrayList("books1", "books2", "books3", "books4");
			cmbProvider.setItems(list);
			break;
		case "movie" :
			list = FXCollections.observableArrayList("movies1", "movies2");
			cmbProvider.setItems(list);
			break;
		case "game" :
			list = FXCollections.observableArrayList("games1", "games2", "games3");
			cmbProvider.setItems(list);
			break;
		}
		cmbProvider.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
	           for(Provider p : li) {
	        	   if(p.getName().equals(newValue)) {
	        		   lblProvider.setText(p.getName());
	        		   lblAdress.setText(p.getAdress());
	        		   lblPhone.setText(p.getPhone());
	        		   this.id = p.getId();
	        		   switch(newValue) {
	        		   case "books1" : lblUnit.setText("14.78"); txtQuantity.setText("0");
	        			   break;
	        		   case "books2" : lblUnit.setText("22.18"); txtQuantity.setText("0");
	        			   break;
	        		   case "books3" : lblUnit.setText("11.85"); txtQuantity.setText("0");
	        			   break;
	        		   case "books4" : lblUnit.setText("24.49"); txtQuantity.setText("0");
	        			   break;
	        		   case "games1" : lblUnit.setText("37.14"); txtQuantity.setText("0");
        			   break;
		    		   case "games2" : lblUnit.setText("34.95"); txtQuantity.setText("0");
		    			   break;
		    		   case "games3" : lblUnit.setText("40.02"); txtQuantity.setText("0");
		    			   break;
		    		   case "movies1" : lblUnit.setText("19.79"); txtQuantity.setText("0");
		    			   break;
		    		   case "movies2" : lblUnit.setText("17.49"); txtQuantity.setText("0");
	    			   break;
	        		   }
	        	   }
	           }
	    });
	}
	
	public void placeOrder(ActionEvent event) throws JSONException, IOException {
		int card_id = 0;
		RadioButton selectedRadioButton =(RadioButton)cards.getSelectedToggle();
		switch(selectedRadioButton.getText()) {
		case "MasterCard" :
			card_id = 4;
			break;
		case "Visa" :
			card_id = 5;
			break;
		case "Debit" :
			card_id = 6;
			break;
		}
		JSONObject obj = new JSONObject();
		
		obj.put("pcode", lblPcode.getText());
		obj.put("employee_id", LoginController.employee.getId());
		obj.put("provider_id", this.id);
		obj.put("bought_quantity", new Integer(txtQuantity.getText()));
		obj.put("unit_price_bought", new Double(lblUnit.getText()));		
		obj.put("amount", new Double(lblTotal.getText()));
		obj.put("card_id", card_id);
		
		System.out.println(obj);
		if(MainController.bought.equals("true")){
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost("http://localhost:56023/StockServices.svc/Post_BoughtToStock_In");
			StringEntity input = new StringEntity(obj.toString());
			input.setContentType("application/json");
			request.setEntity(input);
			HttpResponse response = client.execute(request);
				if(response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
				}
				System.out.println(obj);
				BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()), Charsets.UTF_8));
				String output = br.readLine();
				
				Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setContentText(output);
	    		alert.setHeaderText("Order Complete");
	    		alert.initOwner(MainController.NewPage);
	    		alert.showAndWait();
				MainController.main.setDisable(false);
		    	MainController.NewPage.close();
		    	History.addToHistory(LoginController.employee.getId(), "PLACE ORDER", "Current Product [ " + lblPcode.getText() + " ]");
		}else {
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost("http://localhost:56023/StockServices.svc/Post_AddToStock_In");
			StringEntity input = new StringEntity(obj.toString());
			input.setContentType("application/json");
			request.setEntity(input);
			HttpResponse response = client.execute(request);
				if(response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
				}
				BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()), Charsets.UTF_8));
				String output = br.readLine();
				
				Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setContentText(output);
	    		alert.setHeaderText("Order Complete");
	    		alert.initOwner(MainController.NewPage);
	    		alert.showAndWait();
				MainController.main.setDisable(false);
		    	MainController.NewPage.close();
		    	History.addToHistory(LoginController.employee.getId(), "PLACE ORDER", "New Product [ " + lblPcode.getText() + " ]");
		}		
	}
	
	public void close(MouseEvent event) {
		MainController.main.setDisable(false);
    	MainController.NewPage.close();
	}
	
	
}
