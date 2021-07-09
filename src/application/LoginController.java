package application;


import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.*;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.Duration;
import model.Employee;
import model.History;

public class LoginController{
	public double p;
	public boolean login;
	private double xOffset = 0;
    private double yOffset = 0; 
	static Stage MainStage;
	public static Employee employee;
	Alert alert;
	@FXML public BorderPane loginWindow;
	public static BorderPane logW;
	@FXML ProgressBar loadingBar;
	@FXML public Pane loadingPane;
	public static Pane loadP;
	@FXML Label btnLogin, btnCancel, lblTitleAgent, lblLoading;
	@FXML TextField txtCodeEmploye, txtPassword;
	@FXML ComboBox<String> cmbCode;
	ObservableList<String> list = FXCollections.observableArrayList("GEST");
	
	@FXML ImageView imgTest;
	
	
		//cmbCode.setItems(list);
		
		// Set Image URL
		/*Image image = null;
		image = new Image("https://img.archambault.ca/images/PG/903/903429-gf.jpg");		
		imgTest.setImage(image);*/
	
	public void validation(MouseEvent event) throws JSONException, IOException, InterruptedException{
		login = false;
		ArrayList<Employee> listEMP = Employee.getListEmployee();
		for(Employee emp : listEMP) {
			if(emp.getUsername().equals(txtCodeEmploye.getText())
					&& emp.getPassword().equals(txtPassword.getText()) 
					&& emp.getTitle().equals(lblTitleAgent.getText())) {
				employee = emp;
				login = true;
				loadingPane.setVisible(true);
				loginWindow.setVisible(false);
				txtCodeEmploye.clear();
				txtPassword.clear();
				logW = loginWindow;
				loadP = loadingPane;
			    Timeline timeline = new Timeline(
			        new KeyFrame(Duration.ZERO, new KeyValue(loadingBar.progressProperty(), 0)),
			        new KeyFrame(Duration.seconds(3), frame-> {
							try { main(); } catch (IOException e1) { e1.printStackTrace(); }
			        }, new KeyValue(loadingBar.progressProperty(), 1))    
			    );
			    timeline.play();
			    History.addToHistory(LoginController.employee.getId(), "LOGIN", "Welcome " + emp.getFirstname() + " " + emp.getLastname());
			}
		}
		if(login == false) {
			System.out.println("Error");
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Numero Employe Ou Mot de Pass Invalid");
			alert.setHeaderText("ERREUR : Numero / Mot de Pass invalid");
			alert.setContentText("Vérifier votre numero d'employe et Mot de Pass");
			alert.initStyle(StageStyle.UNDECORATED);
			alert.showAndWait();
		}
	}
	
	public void main() throws IOException {
		Login.logStage.close();
		AnchorPane root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		MainStage = new Stage();
		MainStage.initStyle(StageStyle.UNDECORATED);
		MainStage.setScene(scene);
		MainStage.show();
		MainStage.toBack();
		root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });			
		root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	MainStage.setX(event.getScreenX() - xOffset);
            	MainStage.setY(event.getScreenY() - yOffset);
            }
        });			
	}
		
	public void cancel(MouseEvent event) {
		txtCodeEmploye.clear();
		txtPassword.clear();
	}
	
	public void exit(MouseEvent event) {
		System.out.println("Fermeture de l'application");
		System.exit(0);
	}
	
	public void test(MouseEvent event) {
		System.out.println("Clicked !");
	}	
	
}
