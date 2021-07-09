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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
import model.Book;
import model.History;


public class DoubleClickBookController {
	ObservableList<String> list = FXCollections.observableArrayList("programming", "Literature", "Geography");
	Alert alert;
	
	// DISPLAY INFO SECTION
	@FXML
	private ImageView imgBook;
	
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
	private Label lblPubCo;
	
	@FXML
	private Label lblAuthor;
	
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
	private TextField txtPubCo;
	
	@FXML
	private TextField txtAuthor;
	
	@FXML
	private ComboBox<String> cmbCategory;
	
	
	
	public void initialize() throws JSONException, IOException {
		cmbCategory.setItems(list);
		ArrayList<Book> list = Book.getListBook();
		for(Book bk : list) {
			if(MainController.code.equals(bk.getPcode())) {
				if(!bk.getPicture().contains("http")) {
					imgBook.setImage(new Image(getClass().getResourceAsStream(bk.getPicture())));
				}else {
					imgBook.setImage(new Image(bk.getPicture()));
				}
				
				lblPcode.setText(bk.getPcode());
				lblTitle.setText(bk.getTitle());
				lblType.setText(bk.getType());
				lblPrice.setText(String.valueOf(bk.getPrice()));
				lblGenre.setText(bk.getGenre());
				lblCategory.setText(bk.getCategory());
				lblPubCo.setText(bk.getPubco());
				lblAuthor.setText(bk.getAuthors());
				lblDate.setText(bk.getPubDate());
				
				lblPcodeEdit.setText(bk.getPcode());
				txtTitle.setText(bk.getTitle());
				txtPicture.setText(bk.getPicture());
				txtPrice.setText(String.valueOf(bk.getPrice()));
				txtGenre.setText(bk.getGenre());
				cmbCategory.setValue(bk.getCategory());
				txtPubCo.setText(bk.getPubco());
				txtAuthor.setText(bk.getAuthors());
				lblDateEdit.setText(bk.getPubDate());
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
		alert.setContentText("Do you agree to Edit the Book ?");
		alert.initStyle(StageStyle.UNDECORATED);	
		alert.initOwner(MainController.NewPage);
		Optional<ButtonType> press = alert.showAndWait();
			if(press.get() == ButtonType.OK) {
				ArrayList<Book> list = Book.getListBook();
				for(Book bk : list) {
					if(lblPcodeEdit.getText().equals(bk.getPcode())) {
						Book.Edit
						(lblPcodeEdit.getText(), 
						txtPicture.getText(), 
						txtTitle.getText(), 
						cmbCategory.getValue(), 
						txtGenre.getText(), 
						new Double(txtPrice.getText()), 
						bk.getWarehouse_code(), 
						bk.getMinimum(), 
						bk.getState(), 
						bk.getBought_quantity(), 
						bk.getSold_quantity(), 
						txtAuthor.getText(), 
						txtPubCo.getText(), 
						lblDateEdit.getText());
						
						alert = new Alert(AlertType.INFORMATION);
						alert.setHeaderText("INFO");
						alert.setContentText(Book.info);
						alert.initStyle(StageStyle.UNDECORATED);
						alert.initOwner(MainController.NewPage);
						alert.showAndWait();
						MainController.main.setDisable(false);
				    	MainController.NewPage.close();
				    	break;
					}
				}
			}
			History.addToHistory(LoginController.employee.getId(), "EDIT", "The Book [ " + lblPcodeEdit.getText() + " ]");
		}
							
		
		
	
	@FXML
	void Return(ActionEvent event) {
		HBox1.setVisible(true);
		detailPage.setVisible(true);
		HBox2.setVisible(false);
		editPage.setVisible(false);
	}
}
