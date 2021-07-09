package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.History;
import model.Order_Company;
import model.Product;
import model.Stock_In;

public class ApprovedProductController {
	
	@FXML
	private ImageView imgProduct;
	
	@FXML
	private Label lblPcode;
	
	@FXML
	private Label lblTitle;
	
	@FXML
	private Label lblCategory;
	
	@FXML
	private Label lblType;
	
	@FXML
	private Label lblGenre;
	
	@FXML
	private Label lblPrice;
	
	@FXML
	private Label lblState;
	
	@FXML
	private Label lblBought;
	
	@FXML
	private Label lblPurchaseId;
	
	@FXML
	private Button btnApproved;
	
	public void initialize() throws ClientProtocolException, IOException, JSONException {
		ArrayList<Order_Company> ord = Order_Company.getListOrderCompany();
		ArrayList<Product> list = Product.getListProductPending();
		for(Product pr : list) {
			if(MainController.code.equals(pr.getPcode())) {	
				if(!pr.getPicture().contains("http")) {
					imgProduct.setImage(new Image(getClass().getResourceAsStream(pr.getPicture())));
				}else {
					imgProduct.setImage(new Image(pr.getPicture()));
				}				
				lblPcode.setText(pr.getPcode());
				lblTitle.setText(pr.getTitle());
				lblCategory.setText(pr.getCategory());
				lblType.setText(pr.getType());
				lblGenre.setText(pr.getGenre());
				lblPrice.setText(String.valueOf(pr.getPrice()));
				lblState.setText(pr.getState());
				lblPurchaseId.setText(String.valueOf(pr.getPurchase_id()));
				for(Order_Company a : ord) {
					if(a.getPcode().equals(pr.getPcode()) && a.getStatus().equals("pending")) {
						lblBought.setText(String.valueOf(a.getBought()));
						return;
					}
				}
			}
		}
	}	
	
	public void approved(ActionEvent event) throws JSONException, IOException {
		Dialog<String> dialog = new Dialog<>();
	    dialog.setTitle("Confirmation");
	    dialog.setHeaderText("Veuillez entree votre Mot de Passe pour confirmer que le produit est arrivée !");
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
	    if (result.isPresent()) {
	    	if(LoginController.employee.getPassword().equals(result.get())) {
	    		
	    		HttpPost request = new HttpPost();	    		
	    		JSONObject obj = new JSONObject();
	    		obj.put("pcode", lblPcode.getText());
	    		obj.put("bought_quantity", Integer.valueOf(lblBought.getText()));
	    		obj.put("purchase_id", Integer.valueOf(lblPurchaseId.getText()));
	    		
	    		MainController.NewPage.close();
	    		
	    		HttpClient client = HttpClientBuilder.create().build();
	    		if(MainController.stat.equals("active")) {
	    			request = new HttpPost("http://localhost:56023/StockServices.svc/Post_ApprovedBoughtStock_In");
	    		}else {
	    			request = new HttpPost("http://localhost:56023/StockServices.svc/Post_ApprovedStock_In");
	    		}
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
	    		alert.setHeaderText("Approved Product Complete");
	    		alert.initOwner(MainController.NewPage);
	    		alert.showAndWait();
	    		MainController.main.setDisable(false);
	    		History.addToHistory(LoginController.employee.getId(), "APPROVED", "Received Product : [ " + lblPcode.getText() + " ] to the Stock In");
	    		
	    	}else {
	    		Alert alert = new Alert(AlertType.ERROR);
	    		alert.setContentText("Erreur Vérifier votre mot de pass a nouveaux");
	    		alert.setHeaderText("Erreur Mot De Passe Incorrect");
	    		alert.initOwner(MainController.NewPage);
	    		alert.showAndWait();
	    	}
	    }
	}
	
	public void close(MouseEvent event) {
		MainController.main.setDisable(false);
    	MainController.NewPage.close();
	}
}
