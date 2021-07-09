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
import model.History;
import model.Movie;

public class DoubleClickMovieController {
	ObservableList<String> list = FXCollections.observableArrayList("Action / Adventure", "Comedy", "Drama", "Science-Fiction");
	Alert alert;
	
	// DISPLAY INFO SECTION
	@FXML
	private ImageView imgMovie;
	
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
	private Label lblActors;
	
	@FXML
	private Label lblDirector;
	
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
	private TextField txtGenre;
	
	@FXML
	private TextField txtActors;
	
	@FXML
	private TextField txtDirector;
	
	@FXML
	private ComboBox<String> cmbCategory;
	
	
	
	public void initialize() throws JSONException, IOException {
		cmbCategory.setItems(list);
		cmbCategory.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
	           txtGenre.setText((String) newValue);
	    });
		ArrayList<Movie> list = Movie.getListMovie();
		for(Movie mv : list) {
			if(MainController.code.equals(mv.getPcode())) {
				if(!mv.getPicture().contains("http")) {
					imgMovie.setImage(new Image(getClass().getResourceAsStream(mv.getPicture())));
				}else {
					imgMovie.setImage(new Image(mv.getPicture()));
				}
				
				lblPcode.setText(mv.getPcode());
				lblTitle.setText(mv.getTitle());
				lblType.setText(mv.getType());
				lblPrice.setText(String.valueOf(mv.getPrice()));
				lblGenre.setText(mv.getGenre());
				lblCategory.setText(mv.getCategory());
				lblActors.setText(mv.getActors());
				lblDirector.setText(mv.getDirector());
				lblDate.setText(mv.getRelyear());
				
				lblPcodeEdit.setText(mv.getPcode());
				txtTitle.setText(mv.getTitle());
				txtPicture.setText(mv.getPicture());
				txtPrice.setText(String.valueOf(mv.getPrice()));
				txtGenre.setText(mv.getGenre());
				cmbCategory.setValue(mv.getCategory());
				txtActors.setText(mv.getActors());
				txtDirector.setText(mv.getDirector());
				lblDateEdit.setText(mv.getRelyear());
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
		alert.setContentText("Do you agree to Edit the Movie ?");
		alert.initStyle(StageStyle.UNDECORATED);	
		alert.initOwner(MainController.NewPage);
		Optional<ButtonType> press = alert.showAndWait();
			if(press.get() == ButtonType.OK) {
				ArrayList<Movie> list = Movie.getListMovie();
				for(Movie mv : list) {
					if(lblPcodeEdit.getText().equals(mv.getPcode())) {
						Movie.Edit
						(lblPcodeEdit.getText(), 
						txtPicture.getText(), 
						txtTitle.getText(), 
						cmbCategory.getValue(), 
						txtGenre.getText(), 
						new Double(txtPrice.getText()), 
						mv.getWarehouse_code(), 
						mv.getMinimum(), 
						mv.getState(), 
						mv.getBought_quantity(), 
						mv.getSold_quantity(), 
						txtActors.getText(), 
						txtDirector.getText(), 
						lblDateEdit.getText());
						
						alert = new Alert(AlertType.INFORMATION);
						alert.setHeaderText("INFO");
						alert.setContentText(Movie.info);
						alert.initStyle(StageStyle.UNDECORATED);
						alert.initOwner(MainController.NewPage);
						alert.showAndWait();
						MainController.main.setDisable(false);
				    	MainController.NewPage.close();
				    	break;
					}
				}
			}
			History.addToHistory(LoginController.employee.getId(), "EDIT", "The Movie [ " + lblPcodeEdit.getText() + " ]");
		}
							
		
		
	
	@FXML
	void Return(ActionEvent event) {
		HBox1.setVisible(true);
		detailPage.setVisible(true);
		HBox2.setVisible(false);
		editPage.setVisible(false);
	}
}
