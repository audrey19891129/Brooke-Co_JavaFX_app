package application;

import model.*;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TopTenController {
	
	@FXML
	ImageView bookImg;
	
	@FXML
	ImageView imgDetail;
	
	@FXML
	ImageView imgGeneral;
	
	@FXML
	private Label lblDetail;
	
	@FXML
	private Label lblGeneral;
	
	@FXML
	private Label lblTitle;
	
	@FXML
	private Label lblPcode;
	
	@FXML
	private Label lblType;
	
	@FXML
	private Label lblPrice;
	
	@FXML
	private Label lblGenre;
	
	@FXML
	private Label lblSold;
	
	@FXML
	private Label lblCategory;
	
	@FXML
	private Label lblInGeneral;
	
	@FXML
	private Label lblInDetail;
	
	@FXML
	private Label lblInDate;
	
	public void initialize() throws JSONException, IOException {
		String result = MainController.result;
		String code = MainController.code;
		ArrayList<Book> bk = Book.getTopTenBooks();
		ArrayList<Movie> mv = Movie.getTopTenMovies();
		ArrayList<Game> gm = Game.getTopTenGames();
		switch(result) {
		case "book" :
			for(Book b : bk) {
				if(code.equals(b.getPcode())) {		
					bookImg.setImage(new Image(b.getPicture()));
					
					lblTitle.setText(b.getTitle());
					lblPcode.setText(b.getPcode());
					lblType.setText(b.getType());
					lblPrice.setText(String.valueOf(b.getPrice()));
					lblGenre.setText(b.getGenre());
					lblSold.setText(String.valueOf(b.getSold_quantity()));
					lblCategory.setText(b.getCategory());
					lblInGeneral.setText(b.getPubco());
					lblInDetail.setText(b.getAuthors());
					lblInDate.setText(b.getPubDate());
					
					lblGeneral.setText("PubCo : ");
					imgGeneral.setImage(new Image(getClass().getResourceAsStream("/application/images/pubco.png")));
					lblDetail.setText("Authors : ");
					imgDetail.setImage(new Image(getClass().getResourceAsStream("/application/images/author.png")));
				}
			}break;
		case "movie" :
			for(Movie m : mv) {
				if(code.equals(m.getPcode())) {		
					bookImg.setImage(new Image(m.getPicture()));
					
					lblTitle.setText(m.getTitle());
					lblPcode.setText(m.getPcode());
					lblType.setText(m.getType());
					lblPrice.setText(String.valueOf(m.getPrice()));
					lblGenre.setText(m.getGenre());
					lblSold.setText(String.valueOf(m.getSold_quantity()));
					lblCategory.setText(m.getCategory());
					lblInGeneral.setText(m.getDirector());
					lblInDetail.setText(m.getActors());
					lblInDate.setText(m.getRelyear());
					
					lblGeneral.setText("Director : ");
					imgGeneral.setImage(new Image(getClass().getResourceAsStream("/application/images/director.png")));
					lblDetail.setText("Actors : ");
					imgDetail.setImage(new Image(getClass().getResourceAsStream("/application/images/actor.png")));
				}
			}break;
		case "game" :
			for(Game g : gm) {
				if(code.equals(g.getPcode())) {	
					bookImg.setImage(new Image(g.getPicture()));
					
					lblTitle.setText(g.getTitle());
					lblPcode.setText(g.getPcode());
					lblType.setText(g.getType());
					lblPrice.setText(String.valueOf(g.getPrice()));
					lblGenre.setText(g.getGenre());
					lblSold.setText(String.valueOf(g.getSold_quantity()));
					lblCategory.setText(g.getCategory());
					lblInGeneral.setText(g.getConsole());
					lblInDetail.setText(g.getCompany());
					lblInDate.setText(g.getReldate());
					
					lblGeneral.setText("Console : ");
					imgGeneral.setImage(new Image(getClass().getResourceAsStream("/application/images/console.png")));
					lblDetail.setText("Company : ");
					imgDetail.setImage(new Image(getClass().getResourceAsStream("/application/images/company.png")));
				}
			}break;
		}		
	}
	
	@FXML
	public void close(MouseEvent event) {
		MainController.main.setDisable(false);
    	MainController.NewPage.close();
	}
	
}
