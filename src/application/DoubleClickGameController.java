package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.json.JSONException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
import model.Game;
import model.History;

public class DoubleClickGameController {
	ObservableList<String> list = FXCollections.observableArrayList("PS4", "Wii U", "Xbox One");
	ObservableList<String> listG = FXCollections.observableArrayList("7th Generation", "Action", "Adventure", "Dance",
			"Fighting", "Horror", "Music & Party", "PlayStation Exclusive", "Racing", "Role-Playing", "RPG", "Shooters", "Simulation", "Sports");
	Alert alert;
	
	// DISPLAY INFO SECTION
	@FXML
	private ImageView imgGame;
	
	@FXML
	private AnchorPane detailPage;
	
	@FXML
	private HBox HBox1;
	
	@FXML
	private Label lblPcode;
	
	@FXML
	private Label lblTitle;
	
	@FXML
	private Label lblPicture;
	
	@FXML
	private Label lblType;
	
	@FXML
	private Label lblPrice;
	
	@FXML
	private Label lblGenre;
	
	@FXML
	private Label lblCategory;
	
	@FXML
	private Label lblConsole;
	
	@FXML
	private Label lblCompany;
	
	@FXML
	private Label lblDate;
	
	
	// EDIT SECTION
	@FXML
	private AnchorPane editPage;
	
	@FXML
	private HBox HBox2;
	
	@FXML
	private Label lblPcodeEdit;
	
	@FXML
	private Label lblDateEdit;
	
	@FXML
	private TextField txtTitle;
	
	@FXML
	private TextField txtPicture;
	
	@FXML
	private TextField txtPrice;
	
	@FXML
	private ComboBox<String> cmbGenre;
	
	@FXML
	private TextField txtConsole;
	
	@FXML
	private TextField txtCompany;
	
	@FXML
	private ComboBox<String> cmbCategory;
	
	
	
	public void initialize() throws JSONException, IOException {
		cmbGenre.setItems(listG);
		cmbCategory.setItems(list);
		cmbCategory.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
	           txtConsole.setText((String) newValue);
	    });
		ArrayList<Game> list = Game.getListGame();
		for(Game gm : list) {
			if(MainController.code.equals(gm.getPcode())) {
				if(!gm.getPicture().contains("http")) {
					imgGame.setImage(new Image(getClass().getResourceAsStream(gm.getPicture())));
				}else {
					imgGame.setImage(new Image(gm.getPicture()));
				}
				
				lblPcode.setText(gm.getPcode());
				lblTitle.setText(gm.getTitle());
				lblType.setText(gm.getType());
				lblPrice.setText(String.valueOf(gm.getPrice()));
				lblGenre.setText(gm.getGenre());
				lblCategory.setText(gm.getCategory());
				lblConsole.setText(gm.getConsole());
				lblCompany.setText(gm.getCompany());
				lblDate.setText(gm.getReldate());
				
				lblPcodeEdit.setText(gm.getPcode());
				txtTitle.setText(gm.getTitle());
				txtPicture.setText(gm.getPicture());
				txtPrice.setText(String.valueOf(gm.getPrice()));
				cmbGenre.setValue(gm.getGenre());
				cmbCategory.setValue(gm.getCategory());
				txtConsole.setText(gm.getConsole());
				txtCompany.setText(gm.getCompany());
				lblDateEdit.setText(gm.getReldate());
				break;
			}
		}
		
		
	}
	
	@FXML
    void closeWindow(MouseEvent event) {
    	MainController.main.setDisable(false);
    	MainController.NewPage.close();
    }
	
	@FXML
	void EditBook(ActionEvent event){
		HBox1.setVisible(false);
		detailPage.setVisible(false);
		HBox2.setVisible(true);
		editPage.setVisible(true);
	}
	
	@FXML
	void Submit(ActionEvent event) throws JSONException, IOException {
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Are You Sure?");
		alert.setContentText("Do you agree to Edit the Game ?");
		alert.initStyle(StageStyle.UNDECORATED);	
		alert.initOwner(MainController.NewPage);
		Optional<ButtonType> press = alert.showAndWait();
			if(press.get() == ButtonType.OK) {
				ArrayList<Game> list = Game.getListGame();
				for(Game gm : list) {
					if(lblPcodeEdit.getText().equals(gm.getPcode())) {
						Game.Edit
						(lblPcodeEdit.getText(), 
						txtPicture.getText(), 
						txtTitle.getText(), 
						cmbCategory.getValue(), 
						cmbGenre.getValue(), 
						new Double(txtPrice.getText()), 
						gm.getWarehouse_code(), 
						gm.getMinimum(), 
						gm.getState(), 
						gm.getBought_quantity(), 
						gm.getSold_quantity(), 
						txtConsole.getText(), 
						txtCompany.getText(), 
						lblDateEdit.getText());
						
						alert = new Alert(AlertType.INFORMATION);
						alert.setHeaderText("INFO");
						alert.setContentText(Game.info);
						alert.initStyle(StageStyle.UNDECORATED);
						alert.initOwner(MainController.NewPage);
						alert.showAndWait();
						MainController.main.setDisable(false);
				    	MainController.NewPage.close();
				    	break;
					}
				}
			}
			History.addToHistory(LoginController.employee.getId(), "EDIT", "The Game [ " + lblPcodeEdit.getText() + " ]");
		}
							
		
		
	
	@FXML
	void Return(ActionEvent event) {
		HBox1.setVisible(true);
		detailPage.setVisible(true);
		HBox2.setVisible(false);
		editPage.setVisible(false);
	}
}
