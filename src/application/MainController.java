package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import javafx.collections.*;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import model.*;

public class MainController implements Initializable {
	public static String result, code, title, name, lastname, bought, date, stat;
	public static double total;
	private double xOffset = 0;
    private double yOffset = 0;
    public static AnchorPane main;
	static Stage NewPage;
	Alert alert;
	
	@FXML
	public AnchorPane mainMenuPage;

	@FXML 
	 public Button btnSectionAll;
	 
	 @FXML
	 private Button btnSectionLivre;
	 
	 @FXML
	 private Button btnSectionFilm;
	 
	 @FXML
	 private Button btnSectionJeuxVideo;
	 
	 @FXML 
	 public Button btnNewBook;
	 
	 @FXML
	 private Button btnDeleteBook;
	 
	 @FXML
	 private Button btnEditBook;
	 
	 @FXML
	 private ComboBox<String> cb;
	 
	 @FXML 
	 public Button btnNewMovie;
	 
	 @FXML
	 private Button btnDeleteMovie;
	 
	 @FXML
	 private Button btnEditMovie;
	 
	 @FXML 
	 public Button btnNewGame;
	 
	 @FXML
	 private Button btnDeleteGame;
	 
	 @FXML
	 private Button btnEditGame;
	 
	 @FXML
	 private Button btnInactive;
	 
	 @FXML 
	 private VBox booksButtons;
	 
	 @FXML 
	 private VBox moviesButtons;
	 
	 @FXML 
	 private VBox gamesButtons;
	 
	 @FXML 
	 private Pane paneNewProduct;
	 
	 @FXML 
	 private Label lblUserName;
	 
	 @FXML
	 private Label lblLoading;
	 
	 @FXML
	 private Label lblNumeroCheck;
	 
	 @FXML
	 private Label lblAjouter;
	 
	 @FXML
	 private Label lblRecommencer;
	 
	 @FXML
	 private Label lblDash;
	 
	 @FXML
	 private Label lblWebSite;
	 
	 @FXML
	 private Label lblInv;
	 
	 @FXML
	 private Label lblMes;
	 
	 @FXML
	 private Label lblHis;
	 
	 @FXML
	 private Label lblAid;
	 
	 @FXML
	 private Label lblPar;
	 
	 @FXML
	 private Label lblTitreTable;
	 
	 @FXML
	 private Label lblTotal;
	 
	 @FXML
	 private Label lblCount;
	 
	 @FXML
	 private Label lblUser;
	 
	 @FXML
	 private Label lblUrgent;
	 
	 @FXML
	 private Label lblWarning;
	 
	 @FXML
	 private Label lblOrder;
	 
	 @FXML
	 private Label lblConfirmation;
	 
	 @FXML 
	 private ProgressBar barProgress;
	 
	 @FXML 
	 private BorderPane idWebSitePage;
	 
	 @FXML 
	 private BorderPane idInventairePage;
	 
	 @FXML 
	 private BorderPane idDashboardPage;
	 
	 @FXML 
	 private BorderPane idMessagePage;
	 
	 @FXML
	 private BorderPane idHistoriquePage;
	 
	 @FXML
	 private BorderPane idAidePage;
	 
	 @FXML
	 private BorderPane idParametrePage;
	 
	 @FXML
	 private TextField txtSearchPI;
	 
	 @FXML
	 private TextField txtSearchProduct;	
	 
	 @FXML
	 private ComboBox<String> cbSorted;
	 
	 @FXML
	 private RadioButton rbDark;
	 
	 @FXML
	 private RadioButton rbCloud;
	 
	 @FXML
	 private RadioButton rbBlackWhite;
	 
	 @FXML
	 private RadioButton rbLivingCoral;
	 
	 @FXML
	 private RadioButton rbRainbow;
	 
	 public ToggleGroup theme;
	 
	 @FXML
	 private GridPane gLeftPane;
	 
	 @FXML
	 private GridPane gTopPane;
	 
	 
	 /*------------------- TOP TEN BOOKS --------------------------*/
	 @FXML 
	 private TableView<Book> tvTopBook;
	 
	 @FXML 
	 private TableColumn<Book, Integer> tcTopNumberBook;
	 
	 @FXML 
	 private TableColumn<Book, String> tcTopBookPcode;
	 
	 @FXML 
	 private TableColumn<Book, String> tcTopBookTitle;
	 
	 @FXML 
	 private TableColumn<Book, String> tcTopBookCategory;
	 
	 @FXML 
	 private TableColumn<Book, String> tcTopBookSold;
	 
	 
	 /*------------------- TOP TEN MOVIES --------------------------*/
	 @FXML 
	 private TableView<Movie> tvTopMovie;
	 
	 @FXML 
	 private TableColumn<Movie, Integer> tcTopNumberMovie;
	 
	 @FXML 
	 private TableColumn<Movie, String> tcTopMoviePcode;
	 
	 @FXML 
	 private TableColumn<Movie, String> tcTopMovieTitle;
	 
	 @FXML 
	 private TableColumn<Movie, String> tcTopMovieCategory;
	 
	 @FXML 
	 private TableColumn<Movie, String> tcTopMovieSold;
	 
	 
	 /*------------------- TOP TEN GAMES --------------------------*/
	 @FXML 
	 private TableView<Game> tvTopGame;
	 
	 @FXML 
	 private TableColumn<Game, Integer> tcTopNumberGame;
	 
	 @FXML 
	 private TableColumn<Game, String> tcTopGamePcode;
	 
	 @FXML 
	 private TableColumn<Game, String> tcTopGameTitle;
	 
	 @FXML 
	 private TableColumn<Game, String> tcTopGameCategory;
	 
	 @FXML 
	 private TableColumn<Game, String> tcTopGameSold;
	 
	 
	 	 		 
	 /*---------------------- BOOKS TABLE ---------------------------------*/
	 @FXML 
	 private TableView<Book> tvBook;
	 
	// @FXML
	// private TableColumn<Book, CheckBox> tcSelectionBook;
	 
	// @FXML
	// private TableColumn<Book, Integer> tcIdBook;
	 
	 @FXML
	 private TableColumn<Book, String> tcPcodeBook;
	 
	 @FXML
	 private TableColumn<Book, String> tcTitleBook;
	 
	 @FXML
	 private TableColumn<Book, String> tcAuthorsBook;
	 
	 @FXML
	 private TableColumn<Book, String> tcPubcoBook;
	 
	 @FXML
	 private TableColumn<Book, String> tcPubdateBook;
	 
	 // @FXML
	 //private TableColumn<Book, String> tcTypeBook;
	 
	 @FXML
	 private TableColumn<Book, String> tcCategoryBook;
	 
	 @FXML
	 private TableColumn<Book, String> tcGenreBook;
	 
	 @FXML
	 private TableColumn<Book, Double> tcPriceBook;
	 
	// @FXML
	// private TableColumn<Book, String> tcPictureBook;
	 
	 @FXML
	 private TableColumn<Book, Long> tcInventoryBook;
	 
	// @FXML
	// private TableColumn<Book, Integer> tcMinimumBook;
	 
	// @FXML
	// private TableColumn<Book, Integer> tcWarehouseBook;
	 
	 @FXML
	 private TableColumn<Book, String> tcStateBook;
	 
	 /*---------------------- MOVIES TABLE ---------------------------------*/
	 @FXML
	 private TableView<Movie> tvMovie;
	 
	 @FXML
	 private TableColumn<Movie, String> tcPcodeMovie;
	 
	 @FXML
	 private TableColumn<Movie, String> tcTitleMovie;
	 
	 @FXML
	 private TableColumn<Movie, String> tcActorsMovie;
	 
	 @FXML
	 private TableColumn<Movie, String> tcDirectorMovie;
	 
	 @FXML
	 private TableColumn<Movie, String> tcRelYearMovie;
	 
	 @FXML
	 private TableColumn<Movie, String> tcCategoryMovie;
	 
	 @FXML
	 private TableColumn<Movie, String> tcGenreMovie;
	 
	 @FXML
	 private TableColumn<Movie, Double> tcPriceMovie;
	  
	 @FXML
	 private TableColumn<Movie, String> tcStateMovie;
	 
	 /*---------------------- GAMES TABLE ---------------------------------*/
	 @FXML
	 private TableView<Game> tvGame;	
	 
	 @FXML
	 private TableColumn<Game, String> tcPcodeGame;
	 
	 @FXML
	 private TableColumn<Game, String> tcTitleGame;
	 
	 @FXML
	 private TableColumn<Game, String> tcConsoleGame;
	 
	 @FXML
	 private TableColumn<Game, String> tcCompanyGame;
	 
	 @FXML
	 private TableColumn<Game, String> tcRelDateGame;
	 
	 @FXML
	 private TableColumn<Game, String> tcCategoryGame;
	 
	 @FXML
	 private TableColumn<Game, String> tcGenreGame;
	 
	 @FXML
	 private TableColumn<Game, Double> tcPriceGame;
	  
	 @FXML
	 private TableColumn<Game, String> tcStateGame;
	 
	 /*---------------------- ALL PRODUCTS TABLE ---------------------------------*/ 
	 @FXML
	 private TableView<Product> tvAllProducts;
	 
	 @FXML
	 private TableColumn<Product, String> tcPcodeAll;
	 
	 @FXML	 
	 private TableColumn<Product, String> tcCategoryAll;
	 
	 @FXML
	 private TableColumn<Product, String> tcTypeAll;
	 
	 @FXML
	 private TableColumn<Product, String> tcGenreAll;
	 
	 @FXML
	 private TableColumn<Product, String> tcTitleAll;
	 
	 @FXML
	 private TableColumn<Product, Double> tcPriceAll;
	 
	 @FXML
	 private TableColumn<Product, String> tcPictureAll;
	 
	 @FXML
	 private TableColumn<Product, String> tcStateAll;
	 
	/*------------------------BAR CHART----------------------------*/ 
	 @FXML
	 private BarChart<String, Integer> bcBook;
	 
	 @FXML
	 private CategoryAxis xBook;
	 
	 @FXML
	 private NumberAxis yBook;
	 
	 @FXML
	 private BarChart<String, Integer> bcMovie;
	 
	 @FXML
	 private CategoryAxis xMovie;
	 
	 @FXML
	 private NumberAxis yMovie;
	 
	 @FXML
	 private BarChart<String, Integer> bcGame;
	 
	 @FXML
	 private CategoryAxis xGame;
	 
	 @FXML
	 private NumberAxis yGame;
	 
	 /*------------------------PRODUCT INACTIVE----------------------------*/ 
	 @FXML
	 private TableView<Product> tvProductInactive;
	 
	 @FXML
	 private TableColumn<Product, String> tcPcodeInactive;
	 
	 @FXML
	 private TableColumn<Product, String> tcTitleInactive;
	 
	 @FXML	 
	 private TableColumn<Product, String> tcCategoryInactive;
	 
	 @FXML
	 private TableColumn<Product, String> tcTypeInactive;
	 
	 @FXML
	 private TableColumn<Product, String> tcGenreInactive;
	 
	 @FXML
	 private TableColumn<Product, Double> tcPriceInactive;
	 
	 @FXML
	 private TableColumn<Product, String> tcStateInactive; 	 
	 
	 /*------------------------PRODUCT PENDING----------------------------*/ 
	 @FXML
	 private TableView<Product> tvProductPending;
	 
	 @FXML
	 private TableColumn<Product, String> tcPcodePending;
	 
	 @FXML
	 private TableColumn<Product, String> tcTitlePending;
	 
	 @FXML	 
	 private TableColumn<Product, String> tcCategoryPending;
	 
	 @FXML
	 private TableColumn<Product, String> tcTypePending;
	 
	 @FXML
	 private TableColumn<Product, String> tcGenrePending;
	 
	 @FXML
	 private TableColumn<Product, Double> tcPricePending;
	 
	 @FXML
	 private TableColumn<Product, String> tcStatePending; 
	 
	 /*------------------------ ORDERS COMPANY ----------------------------*/ 
	 @FXML
	 private TableView<Order_Company> tvOrdersCompany;
	 
	 @FXML
	 private TableColumn<Order_Company, Integer> tcOrderConfirmation;
	 
	 @FXML
	 private TableColumn<Order_Company, Integer> tcOrderPurchaseId;
	 	 
	 @FXML
	 private TableColumn<Order_Company, String> tcOrderValidation;
	 
	 @FXML	 
	 private TableColumn<Order_Company, Double> tcOrderAmount;
	 
	 @FXML
	 private TableColumn<Order_Company, String> tcOrderDate;
	 
	 @FXML
	 private TableColumn<Order_Company, String> tcOrderPcode;
	 
	 @FXML
	 private TableColumn<Order_Company, Integer> tcOrderBought;
	 
	 @FXML
	 private TableColumn<Order_Company, String> tcOrderFirstname; 
	 
	 @FXML
	 private TableColumn<Order_Company, String> tcOrderLastName;
	 
	 @FXML
	 private TableColumn<Order_Company, String> tcOrderStatus; 
	 
	 /*------------------------ BOX INVENTORY ----------------------------*/ 
	 @FXML
	 private TableView<Stock_In> tvStockInventory;
	 
	 @FXML
	 private TableColumn<Stock_In, String> tcStockPcode;
	 
	 @FXML
	 private TableColumn<Stock_In, String> tcStockProvider;
	 
	 @FXML
	 private TableColumn<Stock_In, Integer> tcStockPurchaseId;
	 
	 @FXML
	 private TableColumn<Stock_In, Integer> tcStockBoughtQuant;
	 
	 @FXML
	 private TableColumn<Stock_In, Double> tcStockUnitPrice;
	 
	 @FXML
	 private TableColumn<Stock_In, String> tcStockReceivedDate;
	 
	 @FXML
	 private TableColumn<Stock_In, Integer> tcStockLeftQuant;
	 
	 /*------------------------ ORDERS COMPANY ----------------------------*/ 
	 @FXML
	 private TableView<Order_Client> tvClientsOrders;
	 
	 @FXML
	 private TableColumn<Order_Client, Integer> tcClientOrderId;
	 
	 @FXML
	 private TableColumn<Order_Client, String> tcClientEmail;
	 
	 @FXML
	 private TableColumn<Order_Client, String> tcClientUsername;
	 	 
	 @FXML
	 private TableColumn<Order_Client, String> tcClientFirstname;
	 
	 @FXML	 
	 private TableColumn<Order_Client, String> tcClientLastname;
	 
	 @FXML
	 private TableColumn<Order_Client, String> tcClientOrderDate;
	 
	 @FXML
	 private TableColumn<Order_Client, String> tcClientStatus;
	 
	 @FXML
	 private TableColumn<Order_Client, Double> tcClientSubtotal;
	 
	 /*------------------------ SHIPPING ----------------------------*/ 
	 @FXML
	 private TableView<Order_Client> tvShipping;
	 
	 @FXML
	 private TableColumn<Order_Client, Integer> tcShippingOrder;
	 
	 @FXML
	 private TableColumn<Order_Client, String> tcShippingEmail;
	 
	 @FXML
	 private TableColumn<Order_Client, String> tcShippingUsername;
	 	 
	 @FXML
	 private TableColumn<Order_Client, String> tcShippingFirstname;
	 
	 @FXML	 
	 private TableColumn<Order_Client, String> tcShippingLastname;
	 
	 @FXML
	 private TableColumn<Order_Client, String> tcShippingOrderDate;
	 
	 @FXML
	 private TableColumn<Order_Client, String> tcShippingStatus;
	 
	 @FXML
	 private TableColumn<Order_Client, Double> tcShippingSubtotal;
	 
	 @FXML
	 private TableColumn<Order_Client, String> tcShippingDate;
	 
	 /*------------------------ INVENTORY ----------------------------*/ 
	 @FXML
	 private TableView<Product> tvProductInventory;
	 
	 @FXML
	 private TableColumn<Product, String> tcProductPcode;
	 
	 @FXML
	 private TableColumn<Product, String> tcProductCategory;
	 
	 @FXML
	 private TableColumn<Product, String> tcProductType;
	 	 
	 @FXML
	 private TableColumn<Product, String> tcProductTitle;
	 
	 @FXML	 
	 private TableColumn<Product, Integer> tcProductInventory;
	 
	 @FXML
	 private TableColumn<Product, Integer> tcProductMinimum;	 	 
	 
	 /*------------------------ ALERT MESSAGE ----------------------------*/ 
	 @FXML
	 private TableView<AlertMessage> tvAlertInbox;
	 
	 @FXML
	 private TableColumn<AlertMessage, Integer> tcAlertNumber;
	 
	 @FXML
	 private TableColumn<AlertMessage, String> tcAlertLevel;
	 
	 @FXML
	 private TableColumn<AlertMessage, String> tcAlertMessage;
	 
	 
	 /*------------------------ HISTORY ----------------------------*/ 
	 @FXML
	 private TableView<History> tvHistory;
	 
	 @FXML
	 private TableColumn<History, String> tcHistoryStat;
	 
	 @FXML
	 private TableColumn<History, String> tcHistoryMessage;
	 
	 @FXML
	 private TableColumn<History, String> tcHistoryDate;
	 
	 
	 /*------------------------------------------------------------*/ 
	 public static ObservableList<String> dataCB = FXCollections.observableArrayList("Product Code", "Category", "Genre", "Title", "Price");
	 
	 // DATA DASHBORD
	 public static ObservableList<Book> datatopTenBook;
	 public static ObservableList<Movie> datatopTenMovie;
	 public static ObservableList<Game> datatopTenGame; 	 
	 
	 //DATA WEBSITE
	 public static ObservableList<Product> dataAllProduct;
	 public static ObservableList<Book> dataBooks;
	 public static ObservableList<Movie> dataMovies;
	 public static ObservableList<Game> dataGames;
	 
	 //DATA INVENTORY
	 public static ObservableList<Product> dataProductInventory;
	 public static ObservableList<Stock_In> dataStock_In;
	 public static ObservableList<Order_Company> dataOrder_Company;
	 public static ObservableList<Product> dataInactive;
	 public static ObservableList<Product> dataPending;
	 
	 //DATA MESSAGE
	 public static ObservableList<Order_Client> dataOrder_Client;
	 public static ObservableList<AlertMessage> dataInboxAlert;
	 public static ObservableList<Order_Client> dataShipping;
	 
	 //DATA HISTORY
	 public static ObservableList<History> dataHistory;
	 
	 
	 // INIT START
	 @SuppressWarnings("unchecked")
	@Override
	 public void initialize(URL location, ResourceBundle resources) {
		lblUserName.setText(LoginController.employee.getFirstname());
		lblUser.setText(LoginController.employee.getFirstname());
		XYChart.Series<String, Integer> bookValue = new XYChart.Series<String, Integer>();
		XYChart.Series<String, Integer> movieValue = new XYChart.Series<String, Integer>();
		XYChart.Series<String, Integer> gameValue = new XYChart.Series<String, Integer>();
		try {
			for(Book bk : Book.getTopTenBooks()) {
				bookValue.getData().add(new XYChart.Data<String, Integer>(String.valueOf("#"+bk.getNumber()), bk.getSold_quantity()));	
			}
			bcBook.getData().addAll(bookValue);
			for(Movie mv : Movie.getTopTenMovies()) {
				movieValue.getData().add(new XYChart.Data<String, Integer>(String.valueOf("#"+ mv.getNumber()), mv.getSold_quantity()));
			}
			bcMovie.getData().addAll(movieValue);
			for(Game gm : Game.getTopTenGames()) {
				gameValue.getData().add(new XYChart.Data<String, Integer>(String.valueOf("#"+ gm.getNumber()), gm.getSold_quantity()));
			}
			bcGame.getData().addAll(gameValue);
			
			lblUrgent.setText(String.valueOf(AlertMessage.getUrgent()));
			lblWarning.setText(String.valueOf(AlertMessage.getWarning()));
			lblOrder.setText(String.valueOf(AlertMessage.getOrder()));
			lblConfirmation.setText(String.valueOf(AlertMessage.getCofirmation()));						
		} catch (JSONException | IOException e1) {
			e1.printStackTrace();
		}	
		
		if(!lblUrgent.getText().equals("0")) {
			alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("WARNING : some product are below the minimum");
    		alert.setContentText("Please check your [ INBOX ] for further Information");
    		alert.initOwner(LoginController.MainStage);
    		alert.show();
		}
		
		theme = new ToggleGroup();
		rbDark.setToggleGroup(theme);
		rbBlackWhite.setToggleGroup(theme);
		rbCloud.setToggleGroup(theme);
		rbBlackWhite.setToggleGroup(theme);
		rbLivingCoral.setToggleGroup(theme);
		rbRainbow.setToggleGroup(theme);
		rbDark.setSelected(true);		
	}

	 public static ObservableList<Product> getDataP() {
			return dataAllProduct;
		}
	 
	 public static ObservableList<Book> getDataB() {
			return dataBooks;
		}
	 
	 public static ObservableList<Movie> getDataM() {
			return dataMovies;
		}
	 
	 public static ObservableList<Game> getDataG() {
			return dataGames;
		}
	 
	 public static ObservableList<Book> getDataTopB() {
			return datatopTenBook;
		}
	 
	 public static ObservableList<Movie> getDataTopM() {
			return datatopTenMovie;
		}
	 
	 public static ObservableList<Game> getDataTopG() {
		 	return datatopTenGame;
	 	}
	 
	// ------------------ LEFT MAIN BUTTON -------------------
		
	public void dashboard(MouseEvent event) throws JSONException, IOException {
		idDashboardPage.setVisible(true);
		idWebSitePage.setVisible(false);
		idInventairePage.setVisible(false);
		idMessagePage.setVisible(false);
		idHistoriquePage.setVisible(false);
		idAidePage.setVisible(false);
		idParametrePage.setVisible(false);
		 if(idDashboardPage.isVisible()) {
			 lblDash.setStyle("-fx-background-color: #06d6a0;");
			 lblWebSite.setStyle("-fx-background-color: #2e3d42;");
			 lblInv.setStyle("-fx-background-color: #2e3d42;");
			 lblMes.setStyle("-fx-background-color: #2e3d42;");
			 lblHis.setStyle("-fx-background-color: #2e3d42;");
			// lblAid.setStyle("-fx-background-color: #2e3d42;");
			 lblPar.setStyle("-fx-background-color: #2e3d42;");
		 }
		 
		// TOP 10 BOOK MOST SELL
		datatopTenBook = FXCollections.observableArrayList(Book.getTopTenBooks());
		tcTopNumberBook.setCellValueFactory(new PropertyValueFactory<>("number"));
		tcTopBookPcode.setCellValueFactory(new PropertyValueFactory<>("pcode"));
		tcTopBookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		tcTopBookCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
		tcTopBookSold.setCellValueFactory(new PropertyValueFactory<>("sold_quantity"));
		tvTopBook.setItems(datatopTenBook);
		tvTopBook.setOnMouseClicked((MouseEvent e) -> {
			if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
				result = tvTopBook.getSelectionModel().getSelectedItem().getType();
				code = tvTopBook.getSelectionModel().getSelectedItem().getPcode();
				try {
					main = mainMenuPage;
					main.setDisable(true);
					LoginController.MainStage.sizeToScene();
					AnchorPane root = FXMLLoader.load(getClass().getResource("/application/TopTenDetailPage.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
					NewPage = new Stage();
					NewPage.initStyle(StageStyle.UNDECORATED);
					NewPage.setAlwaysOnTop(true);
					NewPage.toFront();
					NewPage.setScene(scene);
					NewPage.show();
				}
				catch(IOException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		// TOP 10 MOVIE MOST SELL
		datatopTenMovie = FXCollections.observableArrayList(Movie.getTopTenMovies());
		tcTopNumberMovie.setCellValueFactory(new PropertyValueFactory<>("number"));
		tcTopMoviePcode.setCellValueFactory(new PropertyValueFactory<>("pcode"));
		tcTopMovieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		tcTopMovieCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
		tcTopMovieSold.setCellValueFactory(new PropertyValueFactory<>("sold_quantity"));
		tvTopMovie.setItems(datatopTenMovie);
		tvTopMovie.setOnMouseClicked((MouseEvent e) -> {
			if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
				result = tvTopMovie.getSelectionModel().getSelectedItem().getType();
				code = tvTopMovie.getSelectionModel().getSelectedItem().getPcode();
				try {
					main = mainMenuPage;
					main.setDisable(true);
					LoginController.MainStage.sizeToScene();
					AnchorPane root = FXMLLoader.load(getClass().getResource("/application/TopTenDetailPage.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
					NewPage = new Stage();
					NewPage.initStyle(StageStyle.UNDECORATED);
					NewPage.setAlwaysOnTop(true);
					NewPage.toFront();
					NewPage.setScene(scene);
					NewPage.show();
				}
				catch(IOException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		
		// TOP 10 GAME MOST SELL
		datatopTenGame = FXCollections.observableArrayList(Game.getTopTenGames());
		tcTopNumberGame.setCellValueFactory(new PropertyValueFactory<>("number"));
		tcTopGamePcode.setCellValueFactory(new PropertyValueFactory<>("pcode"));
		tcTopGameTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		tcTopGameCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
		tcTopGameSold.setCellValueFactory(new PropertyValueFactory<>("sold_quantity"));
		tvTopGame.setItems(datatopTenGame);
		tvTopGame.setOnMouseClicked((MouseEvent e) -> {
			if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
				result = tvTopGame.getSelectionModel().getSelectedItem().getType();
				code = tvTopGame.getSelectionModel().getSelectedItem().getPcode();
				try {
					main = mainMenuPage;
					main.setDisable(true);
					LoginController.MainStage.sizeToScene();
					AnchorPane root = FXMLLoader.load(getClass().getResource("/application/TopTenDetailPage.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
					NewPage = new Stage();
					NewPage.initStyle(StageStyle.UNDECORATED);
					NewPage.setAlwaysOnTop(true);
					NewPage.toFront();
					NewPage.setScene(scene);
					NewPage.show();
				}
				catch(IOException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
			 
	 }
	 
	public void website(MouseEvent event) { 
		 idDashboardPage.setVisible(false);
		 idWebSitePage.setVisible(true);
		 idInventairePage.setVisible(false);
		 idMessagePage.setVisible(false);
		 idHistoriquePage.setVisible(false);
		 idAidePage.setVisible(false);
		 idParametrePage.setVisible(false);
		 if(idWebSitePage.isVisible()) {
			 lblDash.setStyle("-fx-background-color: #2e3d42;");
			 lblWebSite.setStyle("-fx-background-color: #06d6a0;");
			 lblInv.setStyle("-fx-background-color: #2e3d42;");
			 lblMes.setStyle("-fx-background-color: #2e3d42;");
			 lblHis.setStyle("-fx-background-color: #2e3d42;");
			 //lblAid.setStyle("-fx-background-color: #2e3d42;");
			 lblPar.setStyle("-fx-background-color: #2e3d42;");
		 }
	 }
	
	public void inventaire(MouseEvent event) { 
		 idDashboardPage.setVisible(false);
		 idWebSitePage.setVisible(false);
		 idInventairePage.setVisible(true);
		 idMessagePage.setVisible(false);
		 idHistoriquePage.setVisible(false);
		 idAidePage.setVisible(false);
		 idParametrePage.setVisible(false);
		 if(idInventairePage.isVisible()) {
			 lblDash.setStyle("-fx-background-color: #2e3d42;");
			 lblWebSite.setStyle("-fx-background-color: #2e3d42;");
			 lblInv.setStyle("-fx-background-color: #06d6a0;");
			 lblMes.setStyle("-fx-background-color: #2e3d42;");
			 lblHis.setStyle("-fx-background-color: #2e3d42;");
			 //lblAid.setStyle("-fx-background-color: #2e3d42;");
			 lblPar.setStyle("-fx-background-color: #2e3d42;");
		 }
	 }
	
	public void message(MouseEvent event) { 
		 idDashboardPage.setVisible(false);
		 idWebSitePage.setVisible(false);
		 idInventairePage.setVisible(false);
		 idMessagePage.setVisible(true);
		 idHistoriquePage.setVisible(false);
		 idAidePage.setVisible(false);
		 idParametrePage.setVisible(false);
		 if(idMessagePage.isVisible()) {
			 lblDash.setStyle("-fx-background-color: #2e3d42;");
			 lblWebSite.setStyle("-fx-background-color: #2e3d42;");
			 lblInv.setStyle("-fx-background-color: #2e3d42;");
			 lblMes.setStyle("-fx-background-color: #06d6a0;");
			 lblHis.setStyle("-fx-background-color: #2e3d42;");
			 //lblAid.setStyle("-fx-background-color: #2e3d42;");
			 lblPar.setStyle("-fx-background-color: #2e3d42;");
		 }
	 }
	
	public void historique(MouseEvent event) throws ClientProtocolException, JSONException, IOException { 
		 idDashboardPage.setVisible(false);
		 idWebSitePage.setVisible(false);
		 idInventairePage.setVisible(false);
		 idMessagePage.setVisible(false);
		 idHistoriquePage.setVisible(true);
		 idAidePage.setVisible(false);
		 idParametrePage.setVisible(false);
		 if(idHistoriquePage.isVisible()) {
			 lblDash.setStyle("-fx-background-color: #2e3d42;");
			 lblWebSite.setStyle("-fx-background-color: #2e3d42;");
			 lblInv.setStyle("-fx-background-color: #2e3d42;");
			 lblMes.setStyle("-fx-background-color: #2e3d42;");
			 lblHis.setStyle("-fx-background-color: #06d6a0;");
			 //lblAid.setStyle("-fx-background-color: #2e3d42;");
			 lblPar.setStyle("-fx-background-color: #2e3d42;");
		 }
		 
		 	dataHistory = FXCollections.observableArrayList(History.getHistory(LoginController.employee.getId()));
		 	tcHistoryStat.setCellValueFactory(new PropertyValueFactory<>("stat"));
			tcHistoryMessage.setCellValueFactory(new PropertyValueFactory<>("message"));
			tcHistoryDate.setCellValueFactory(new PropertyValueFactory<>("date"));
			tvHistory.setItems(dataHistory);
	 }
	
	public void aide(MouseEvent event) { 
		 idDashboardPage.setVisible(false);
		 idWebSitePage.setVisible(false);
		 idInventairePage.setVisible(false);
		 idMessagePage.setVisible(false);
		 idHistoriquePage.setVisible(false);
		 idAidePage.setVisible(true);
		 idParametrePage.setVisible(false);
		 if(idAidePage.isVisible()) {
			 lblDash.setStyle("-fx-background-color: #2e3d42;");
			 lblWebSite.setStyle("-fx-background-color: #2e3d42;");
			 lblInv.setStyle("-fx-background-color: #2e3d42;");
			 lblMes.setStyle("-fx-background-color: #2e3d42;");
			 lblHis.setStyle("-fx-background-color: #2e3d42;");
			 //lblAid.setStyle("-fx-background-color: #06d6a0;");
			 lblPar.setStyle("-fx-background-color: #2e3d42;");
		 }
	 }
	
	public void parametre(MouseEvent event) { 
		 idDashboardPage.setVisible(false);
		 idWebSitePage.setVisible(false);
		 idInventairePage.setVisible(false);
		 idMessagePage.setVisible(false);
		 idHistoriquePage.setVisible(false);
		 idAidePage.setVisible(false);
		 idParametrePage.setVisible(true);
		 if(idParametrePage.isVisible()) {
			 lblDash.setStyle("-fx-background-color: #2e3d42;");
			 lblWebSite.setStyle("-fx-background-color: #2e3d42;");
			 lblInv.setStyle("-fx-background-color: #2e3d42;");
			 lblMes.setStyle("-fx-background-color: #2e3d42;");
			 lblHis.setStyle("-fx-background-color: #2e3d42;");
			 //lblAid.setStyle("-fx-background-color: #2e3d42;");
			 lblPar.setStyle("-fx-background-color: #06d6a0;");
		 }
	 }
	
	public void signout(MouseEvent event) throws ClientProtocolException, JSONException, IOException {
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Confirm Logout");
		alert.setContentText("Are your sure you want to Logout?");
		alert.initStyle(StageStyle.UNDECORATED);	
		alert.initOwner(MainController.NewPage);
		Optional<ButtonType> press = alert.showAndWait();
			if(press.get() == ButtonType.OK) {
				Login.logStage.show();
				LoginController.MainStage.hide();
				LoginController.logW.setVisible(true);
				LoginController.loadP.setVisible(false);
				History.addToHistory(LoginController.employee.getId(), "SIGNOUT", "Have a wonderful day " +
				LoginController.employee.getFirstname() + " " + LoginController.employee.getLastname());
			}
	}
	
	// ------------------ TOP MAIN BUTTON -------------------
	
	public void exit(MouseEvent event) throws ClientProtocolException, JSONException, IOException {
		History.addToHistory(LoginController.employee.getId(), "SIGNOUT", "Have a wonderful day " +
				LoginController.employee.getFirstname() + " " + LoginController.employee.getLastname());
		System.out.println("Fermeture de l'application");
		System.exit(0);
	}
	
	public void minimize(MouseEvent event) {
		LoginController.MainStage.setIconified(true);
	}
		
	public void fullscreen(MouseEvent event) {
		LoginController.MainStage.setFullScreen(true);
	}
	
	
	
	public void deleteBook(ActionEvent event) {
		/*alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Are You Sure?");
		alert.setContentText("Do you agree to delete the Book ?");
		alert.initStyle(StageStyle.UNDECORATED);		
		Optional<ButtonType> press = alert.showAndWait();
			if(press.get() == ButtonType.OK) {
				System.out.println("OK");
				for(Product pro : dataBooks) {
					if(pro.getSelection().isSelected()) {

					}		
				}
			}				
		*/
	}
	
	public void deleteMovie(ActionEvent event) throws SQLException {
		/*alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Are You Sure?");
		alert.setContentText("Do you agree to delete the Movie ?");
		alert.initStyle(StageStyle.UNDECORATED);		
		Optional<ButtonType> press = alert.showAndWait();
			if(press.get() == ButtonType.OK) {
				
			}				
		*/
	}
	
	public void deleteGame(ActionEvent event) throws SQLException {
		/*alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Are You Sure?");
		alert.setContentText("Do you agree to delete the Game ?");
		alert.initStyle(StageStyle.UNDECORATED);		
		Optional<ButtonType> press = alert.showAndWait();
			if(press.get() == ButtonType.OK) {
				System.out.println("OK");
				for(Product pro : dataGames) {
					if(pro.getSelection().isSelected()) {

					}		
				}
			}				
		*/
	}
	
//	public void checkSelected(MouseEvent event) {
//		/*int i = 0;
//		for(Product pro : AddProductController.getData() /*data*/) {
//			if(pro.getSelection().isSelected()) {
//				i++;
//			}			
//		}
//		String l = new Integer(i).toString();
//		lblNumeroCheck.setText(l);
//	}
	
	
	// ------------------ WEBSITE BUTTON -------------------
	
	public void openNewBook(ActionEvent event) throws IOException {
		main = mainMenuPage;
		main.setDisable(true);
		LoginController.MainStage.sizeToScene();
		AnchorPane root = FXMLLoader.load(getClass().getResource("/application/AddNewBook.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		NewPage = new Stage();
		NewPage.initStyle(StageStyle.UNDECORATED);
		NewPage.setAlwaysOnTop(true);
		NewPage.toFront();
		NewPage.setScene(scene);
		NewPage.show();
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
            	NewPage.setX(event.getScreenX() - xOffset);
            	NewPage.setY(event.getScreenY() - yOffset);
            }
        });	
	}
	
	public void openNewMovie(ActionEvent event) throws IOException {
		main = mainMenuPage;
		main.setDisable(true);
		LoginController.MainStage.sizeToScene();
		AnchorPane root = FXMLLoader.load(getClass().getResource("/application/AddNewMovie.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		NewPage = new Stage();
		NewPage.initStyle(StageStyle.UNDECORATED);
		NewPage.setAlwaysOnTop(true);
		NewPage.toFront();
		NewPage.setScene(scene);
		NewPage.show();
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
            	NewPage.setX(event.getScreenX() - xOffset);
            	NewPage.setY(event.getScreenY() - yOffset);
            }
        });	
	}
	
	public void openNewGame(ActionEvent event) throws IOException {
		main = mainMenuPage;
		main.setDisable(true);
		LoginController.MainStage.sizeToScene();
		AnchorPane root = FXMLLoader.load(getClass().getResource("/application/AddNewGame.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		NewPage = new Stage();
		NewPage.initStyle(StageStyle.UNDECORATED);
		NewPage.setScene(scene);
		NewPage.show();
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
            	NewPage.setX(event.getScreenX() - xOffset);
            	NewPage.setY(event.getScreenY() - yOffset);
            }
        });	
	}
	
	public void sectionLivre(ActionEvent event) throws JSONException, IOException {
		txtSearchProduct.setText("");
		txtSearchProduct.setVisible(true);
		cbSorted.setVisible(false);
		// ALL BOOK
		dataBooks = FXCollections.observableArrayList(Book.getListBook()); 
		//tcSelectionBook.setCellValueFactory(new PropertyValueFactory<>("Selection"));
		//tcIdBook.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcPcodeBook.setCellValueFactory(new PropertyValueFactory<>("pcode"));
		tcTitleBook.setCellValueFactory(new PropertyValueFactory<>("Title"));
		tcAuthorsBook.setCellValueFactory(new PropertyValueFactory<>("Authors"));
		tcPubcoBook.setCellValueFactory(new PropertyValueFactory<>("Pubco"));
		tcPubdateBook.setCellValueFactory(new PropertyValueFactory<>("pubDate"));
		//tcTypeBook.setCellValueFactory(new PropertyValueFactory<>("Type"));
		tcCategoryBook.setCellValueFactory(new PropertyValueFactory<>("Category"));
		tcGenreBook.setCellValueFactory(new PropertyValueFactory<>("Genre"));
		tcPriceBook.setCellValueFactory(new PropertyValueFactory<>("Price"));
		//tcPictureBook.setCellValueFactory(new PropertyValueFactory<>("Picture"));
		//tcInventoryBook.setCellValueFactory(new PropertyValueFactory<>("Inventory"));
		//tcMinimumBook.setCellValueFactory(new PropertyValueFactory<>("Minimum"));
		//tcWarehouseBook.setCellValueFactory(new PropertyValueFactory<>("warehouse_code"));
		tcStateBook.setCellValueFactory(new PropertyValueFactory<>("State"));
	
		FilteredList<Book> searchProductTitle = new FilteredList<Book>(dataBooks, s -> true);
		SortedList<Book> sortedList = new SortedList<>(searchProductTitle);
		sortedList.comparatorProperty().bind(tvBook.comparatorProperty());
		tvBook.setItems(sortedList);
		txtSearchProduct.setPromptText("Enter the title here");
		txtSearchProduct.setOnKeyReleased(keyEvent ->{
			searchProductTitle.setPredicate(p -> p.getTitle().toLowerCase().contains(txtSearchProduct.getText().toLowerCase().trim()));
		});
		
		// Double Click PopUp Detail Page 
		tvBook.setOnMouseClicked((MouseEvent e) -> {
			if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
				code = tvBook.getSelectionModel().getSelectedItem().getPcode();
				try {
					main = mainMenuPage;
					main.setDisable(true);
					LoginController.MainStage.sizeToScene();
					AnchorPane root = FXMLLoader.load(getClass().getResource("/application/DoubleClickDetailBook.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
					NewPage = new Stage();
					NewPage.initStyle(StageStyle.UNDECORATED);
					NewPage.setAlwaysOnTop(true);
					NewPage.toFront();
					NewPage.setScene(scene);
					NewPage.show();
				}
				catch(IOException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		
		lblTotal.setVisible(true);
		int i = 0;
		for(@SuppressWarnings("unused") Book br : getDataB()) {i++;}
		String count = String.valueOf(i); lblCount.setText(count);
		
		lblTitreTable.setText("Books");
		tvBook.setVisible(true);
		tvMovie.setVisible(false);
		tvGame.setVisible(false);
		tvAllProducts.setVisible(false);
			if(tvBook.isVisible()) {
				btnSectionLivre.setStyle("-fx-background-color: #3a86ff;"); // changed
				btnSectionFilm.setStyle("-fx-background-color:  #e63946;");
				btnSectionJeuxVideo.setStyle("-fx-background-color:   #ef476f;");
				btnSectionAll.setStyle("-fx-background-color:    #023e8a;");
			}
			
			// Button for Livre
			booksButtons.setVisible(true);
			moviesButtons.setVisible(false);
			gamesButtons.setVisible(false);
			
	}
	
	public void sectionFilm(ActionEvent event) throws JSONException, IOException {
		txtSearchProduct.setText("");
		txtSearchProduct.setVisible(true);
		cbSorted.setVisible(false);
		// ALL MOVIE
		dataMovies = FXCollections.observableArrayList(Movie.getListMovie());
		tcPcodeMovie.setCellValueFactory(new PropertyValueFactory<>("pcode"));
		tcTitleMovie.setCellValueFactory(new PropertyValueFactory<>("Title"));
		tcActorsMovie.setCellValueFactory(new PropertyValueFactory<>("Actors"));
		tcDirectorMovie.setCellValueFactory(new PropertyValueFactory<>("Director"));
		tcRelYearMovie.setCellValueFactory(new PropertyValueFactory<>("relyear"));
		tcCategoryMovie.setCellValueFactory(new PropertyValueFactory<>("Category"));
		tcGenreMovie.setCellValueFactory(new PropertyValueFactory<>("Genre"));
		tcPriceMovie.setCellValueFactory(new PropertyValueFactory<>("Price"));
		/*tcInventoryMovie.setCellValueFactory(new PropertyValueFactory<>("Inventory"));*/
		tcStateMovie.setCellValueFactory(new PropertyValueFactory<>("State"));
		
		FilteredList<Movie> searchProductTitle = new FilteredList<Movie>(dataMovies, s -> true);
		SortedList<Movie> sortedList = new SortedList<>(searchProductTitle);
		sortedList.comparatorProperty().bind(tvMovie.comparatorProperty());
		tvMovie.setItems(sortedList);
		txtSearchProduct.setPromptText("Enter the title here");
		txtSearchProduct.setOnKeyReleased(keyEvent ->{
			searchProductTitle.setPredicate(p -> p.getTitle().toLowerCase().contains(txtSearchProduct.getText().toLowerCase().trim()));
		});
		
		// Double Click PopUp Detail Page 
		tvMovie.setOnMouseClicked((MouseEvent e) -> {
			if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
				code = tvMovie.getSelectionModel().getSelectedItem().getPcode();
				try {
					main = mainMenuPage;
					main.setDisable(true);
					LoginController.MainStage.sizeToScene();
					AnchorPane root = FXMLLoader.load(getClass().getResource("/application/DoubleClickDetailMovie.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
					NewPage = new Stage();
					NewPage.initStyle(StageStyle.UNDECORATED);
					NewPage.setAlwaysOnTop(true);
					NewPage.toFront();
					NewPage.setScene(scene);
					NewPage.show();
				}
				catch(IOException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		
		lblTotal.setVisible(true);
		int i = 0;
		for(@SuppressWarnings("unused") Movie mv : getDataM()) {i++;}
		String count = String.valueOf(i); lblCount.setText(count);
		
		lblTitreTable.setText("Movies");
		tvBook.setVisible(false);
		tvMovie.setVisible(true);
		tvGame.setVisible(false);
		tvAllProducts.setVisible(false);
			if(tvMovie.isVisible()) {
				btnSectionLivre.setStyle("-fx-background-color: #e76f51;");
				btnSectionFilm.setStyle("-fx-background-color:  #3a86ff;"); // changed
				btnSectionJeuxVideo.setStyle("-fx-background-color:  #ef476f;");
				btnSectionAll.setStyle("-fx-background-color:    #023e8a;");
			}
			
			// Button For Film
			booksButtons.setVisible(false);
			moviesButtons.setVisible(true);
			gamesButtons.setVisible(false);
	}
	
	public void sectionJeuxVideo(ActionEvent event) throws JSONException, IOException {
		txtSearchProduct.setText("");
		txtSearchProduct.setVisible(true);
		cbSorted.setVisible(false);
		// ALL GAME
		dataGames = FXCollections.observableArrayList(Game.getListGame());
		tcPcodeGame.setCellValueFactory(new PropertyValueFactory<>("pcode"));
		tcTitleGame.setCellValueFactory(new PropertyValueFactory<>("Title"));
		tcConsoleGame.setCellValueFactory(new PropertyValueFactory<>("Console"));
		tcCompanyGame.setCellValueFactory(new PropertyValueFactory<>("Company"));
		tcRelDateGame.setCellValueFactory(new PropertyValueFactory<>("reldate"));
		tcCategoryGame.setCellValueFactory(new PropertyValueFactory<>("Category"));
		tcGenreGame.setCellValueFactory(new PropertyValueFactory<>("Genre"));
		tcPriceGame.setCellValueFactory(new PropertyValueFactory<>("Price"));
		/*tcInventoryGame.setCellValueFactory(new PropertyValueFactory<>("Inventory"));*/
		tcStateGame.setCellValueFactory(new PropertyValueFactory<>("State"));
		
		FilteredList<Game> searchProductTitle = new FilteredList<Game>(dataGames, s -> true);
		SortedList<Game> sortedList = new SortedList<>(searchProductTitle);
		sortedList.comparatorProperty().bind(tvGame.comparatorProperty());
		tvGame.setItems(sortedList);
		txtSearchProduct.setPromptText("Enter the title here");
		txtSearchProduct.setOnKeyReleased(keyEvent ->{
			searchProductTitle.setPredicate(p -> p.getTitle().toLowerCase().contains(txtSearchProduct.getText().toLowerCase().trim()));
		});
		
		// Double Click PopUp Detail Page 
		tvGame.setOnMouseClicked((MouseEvent e) -> {
			if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
				code = tvGame.getSelectionModel().getSelectedItem().getPcode();
				try {
					main = mainMenuPage;
					main.setDisable(true);
					LoginController.MainStage.sizeToScene();
					AnchorPane root = FXMLLoader.load(getClass().getResource("/application/DoubleClickDetailGame.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
					NewPage = new Stage();
					NewPage.initStyle(StageStyle.UNDECORATED);
					NewPage.setAlwaysOnTop(true);
					NewPage.toFront();
					NewPage.setScene(scene);
					NewPage.show();
				}
				catch(IOException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});	
		
		lblTotal.setVisible(true);
		int i = 0;
		for(@SuppressWarnings("unused") Game gm : getDataG()) {i++;}
		String count = String.valueOf(i); lblCount.setText(count);
		
		lblTitreTable.setText("Video Games");
		tvBook.setVisible(false);
		tvMovie.setVisible(false);
		tvGame.setVisible(true);
		tvAllProducts.setVisible(false);
			if(tvGame.isVisible()) {
				btnSectionLivre.setStyle("-fx-background-color: #e76f51;");
				btnSectionFilm.setStyle("-fx-background-color:  #e63946;");
				btnSectionJeuxVideo.setStyle("-fx-background-color:  #3a86ff;"); // changed
				btnSectionAll.setStyle("-fx-background-color:    #023e8a;");
			}
			
			// Button for Jeuxvideo
			booksButtons.setVisible(false);
			moviesButtons.setVisible(false);
			gamesButtons.setVisible(true);
	}
	
	public void sectionAllProducts(ActionEvent event) throws ClientProtocolException, IOException, JSONException {
		txtSearchProduct.setText("");
		txtSearchProduct.setVisible(true);
		cbSorted.setVisible(true);
		
	//  ALL PRODUCT
	dataAllProduct = FXCollections.observableArrayList(Product.getListProduct());
	tcPcodeAll.setCellValueFactory(new PropertyValueFactory<>("pcode"));
	tcCategoryAll.setCellValueFactory(new PropertyValueFactory<>("category"));
	tcTypeAll.setCellValueFactory(new PropertyValueFactory<>("type"));
	tcGenreAll.setCellValueFactory(new PropertyValueFactory<>("genre"));
	tcTitleAll.setCellValueFactory(new PropertyValueFactory<>("title"));
	tcPriceAll.setCellValueFactory(new PropertyValueFactory<>("price"));
	tcPictureAll.setCellValueFactory(new PropertyValueFactory<>("picture"));
	tcStateAll.setCellValueFactory(new PropertyValueFactory<>("State"));
	cbSorted.setItems(dataCB);
	cbSorted.setValue("Product Code");	
	
	FilteredList<Product> searchProductAll = new FilteredList<Product>(dataAllProduct, s -> true);
	SortedList<Product> sortedList = new SortedList<>(searchProductAll);
	sortedList.comparatorProperty().bind(tvAllProducts.comparatorProperty());
	tvAllProducts.setItems(sortedList);
	txtSearchProduct.setPromptText("Enter text here");
	txtSearchProduct.setOnKeyReleased(keyEvent ->{
		switch(cbSorted.getValue()) {
		case "Product Code":
			searchProductAll.setPredicate(p -> p.getPcode().toLowerCase().contains(txtSearchProduct.getText().toLowerCase().trim()));
			break;
		case "Category":
			searchProductAll.setPredicate(p -> p.getCategory().toLowerCase().contains(txtSearchProduct.getText().toLowerCase().trim()));
			break;
		case "Genre":
			searchProductAll.setPredicate(p -> p.getGenre().toLowerCase().contains(txtSearchProduct.getText().toLowerCase().trim()));
			break;
		case "Title":
			searchProductAll.setPredicate(p -> p.getTitle().toLowerCase().contains(txtSearchProduct.getText().toLowerCase().trim()));
			break;
		case "Price":
			searchProductAll.setPredicate(p -> String.valueOf(p.getPrice()).toLowerCase().contains(txtSearchProduct.getText().toLowerCase().trim()));
			break;
		}
		
	});
	
	cbSorted.getSelectionModel().selectedItemProperty().addListener((observer, oldValue, newValue) -> {
		if(newValue != null) {
			txtSearchProduct.setText("");
			searchProductAll.setPredicate(null);
		}
	});
	
	tvAllProducts.setOnMouseClicked((MouseEvent e) -> {
		if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
			code = tvAllProducts.getSelectionModel().getSelectedItem().getPcode();
			System.out.println(code);
		}
	});
		lblTotal.setVisible(true);
		int i = 0;
		for(@SuppressWarnings("unused") Product pr : getDataP()) {i++;}
		String count = String.valueOf(i); lblCount.setText(count);
		
		lblTitreTable.setText("All Products");
		tvBook.setVisible(false);
		tvMovie.setVisible(false);
		tvGame.setVisible(false);
		tvAllProducts.setVisible(true);
			if(tvAllProducts.isVisible()) {
				btnSectionLivre.setStyle("-fx-background-color: #e76f51;");
				btnSectionFilm.setStyle("-fx-background-color:  #e63946;");
				btnSectionJeuxVideo.setStyle("-fx-background-color:  #ef476f;");
				btnSectionAll.setStyle("-fx-background-color:    #3a86ff;"); // changed
			}
		
		// Button None
			booksButtons.setVisible(false);
			moviesButtons.setVisible(false);
			gamesButtons.setVisible(false);
			
			
	}

	// ------------------ INVENTORY BUTTON -------------------
	public void sectionProductInactive(ActionEvent event) throws ClientProtocolException, IOException, JSONException {
		txtSearchPI.setText("");
		tvProductInactive.setVisible(true);
		tvStockInventory.setVisible(false);
		tvProductPending.setVisible(false);
		tvOrdersCompany.setVisible(false);
		tvProductInventory.setVisible(false);
		txtSearchPI.setVisible(false);
		
		//Product Inactive
		dataInactive = FXCollections.observableArrayList(Product.getListProductInactive());
		tcPcodeInactive.setCellValueFactory(new PropertyValueFactory<>("pcode"));
		tcCategoryInactive.setCellValueFactory(new PropertyValueFactory<>("category"));
		tcTypeInactive.setCellValueFactory(new PropertyValueFactory<>("type"));
		tcGenreInactive.setCellValueFactory(new PropertyValueFactory<>("genre"));
		tcTitleInactive.setCellValueFactory(new PropertyValueFactory<>("title"));
		tcPriceInactive.setCellValueFactory(new PropertyValueFactory<>("price"));
		tcStateInactive.setCellValueFactory(new PropertyValueFactory<>("State"));
		tvProductInactive.setItems(dataInactive);
		tvProductInactive.setOnMouseClicked((MouseEvent e) -> {
			if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
				code = (tvProductInactive.getSelectionModel().getSelectedItem().getPcode());
				result = (tvProductInactive.getSelectionModel().getSelectedItem().getType());
				title = (tvProductInactive.getSelectionModel().getSelectedItem().getTitle());
				stat = (tvProductInactive.getSelectionModel().getSelectedItem().getState());
				bought = "false";
				System.out.println(code + " " + result + " " + title);
				try {
					main = mainMenuPage;
					main.setDisable(true);
					LoginController.MainStage.sizeToScene();
					AnchorPane root = FXMLLoader.load(getClass().getResource("/application/PlaceOrderPage.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
					NewPage = new Stage();
					NewPage.initStyle(StageStyle.UNDECORATED);
					NewPage.setAlwaysOnTop(true);
					NewPage.toFront();
					NewPage.setScene(scene);
					NewPage.show();
				}
				catch(IOException ex) {
					System.out.println(ex.getMessage());
				}
				
			}
		});
	}
	
	public void sectionProductPending(ActionEvent event) throws ClientProtocolException, IOException, JSONException {
		txtSearchPI.setText("");
		tvProductInactive.setVisible(false);
		tvStockInventory.setVisible(false);
		tvProductPending.setVisible(true);
		tvOrdersCompany.setVisible(false);
		tvProductInventory.setVisible(false);
		txtSearchPI.setVisible(false);
		
		//Product Pending
		dataPending = FXCollections.observableArrayList(Product.getListProductPending());
		tcPcodePending.setCellValueFactory(new PropertyValueFactory<>("pcode"));
		tcCategoryPending.setCellValueFactory(new PropertyValueFactory<>("category"));
		tcTypePending.setCellValueFactory(new PropertyValueFactory<>("type"));
		tcGenrePending.setCellValueFactory(new PropertyValueFactory<>("genre"));
		tcTitlePending.setCellValueFactory(new PropertyValueFactory<>("title"));
		tcPricePending.setCellValueFactory(new PropertyValueFactory<>("price"));
		tcStatePending.setCellValueFactory(new PropertyValueFactory<>("State"));
		tvProductPending.setItems(dataPending);
		tvProductPending.setOnMouseClicked((MouseEvent e) -> {
			if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
				code = (tvProductPending.getSelectionModel().getSelectedItem().getPcode());
				result = (tvProductPending.getSelectionModel().getSelectedItem().getType());
				title = (tvProductPending.getSelectionModel().getSelectedItem().getTitle());
				stat = (tvProductPending.getSelectionModel().getSelectedItem().getState());
				bought = "true";

				try {
					main = mainMenuPage;
					main.setDisable(true);
					LoginController.MainStage.sizeToScene();
					AnchorPane root = FXMLLoader.load(getClass().getResource("/application/ApprovedProduct.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
					NewPage = new Stage();
					NewPage.initStyle(StageStyle.UNDECORATED);
					NewPage.setAlwaysOnTop(true);
					NewPage.toFront();
					NewPage.setScene(scene);
					NewPage.show();
				}
				catch(IOException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
	}
	
	public void sectionOrderCompany(ActionEvent event) throws ClientProtocolException, IOException, JSONException {
		txtSearchPI.setText("");
		tvProductInactive.setVisible(false);
		tvStockInventory.setVisible(false);
		tvProductPending.setVisible(false);
		tvOrdersCompany.setVisible(true);
		tvProductInventory.setVisible(false);
		txtSearchPI.setVisible(false);
		
		//Orders Company
		dataOrder_Company = FXCollections.observableArrayList(Order_Company.getListOrderCompany());
		tcOrderConfirmation.setCellValueFactory(new PropertyValueFactory<>("confirmation"));
		tcOrderPurchaseId.setCellValueFactory(new PropertyValueFactory<>("purchase_id"));
		tcOrderValidation.setCellValueFactory(new PropertyValueFactory<>("validation"));
		tcOrderAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		tcOrderDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		tcOrderPcode.setCellValueFactory(new PropertyValueFactory<>("pcode"));
		tcOrderBought.setCellValueFactory(new PropertyValueFactory<>("bought"));
		tcOrderFirstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
		tcOrderLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
		tcOrderStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		tvOrdersCompany.setItems(dataOrder_Company);
		tvOrdersCompany.setOnMouseClicked((MouseEvent e) -> {
			if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
				code = (tvOrdersCompany.getSelectionModel().getSelectedItem().getPcode());
				System.out.println(code);
			}
		});
	}
	
	public void sectionBoxInventory(ActionEvent event) throws ClientProtocolException, IOException, JSONException {
		txtSearchPI.setText("");
		tvProductInactive.setVisible(false);
		tvStockInventory.setVisible(true);
		tvProductPending.setVisible(false);
		tvOrdersCompany.setVisible(false);
		tvProductInventory.setVisible(false);
		txtSearchPI.setVisible(false);
		
		// Box Inventory
		dataStock_In = FXCollections.observableArrayList(Stock_In.getListStock_In());
		tcStockPcode.setCellValueFactory(new PropertyValueFactory<>("pcode"));
		tcStockProvider.setCellValueFactory(new PropertyValueFactory<>("provider_name"));
		tcStockPurchaseId.setCellValueFactory(new PropertyValueFactory<>("purchase_id"));
		tcStockBoughtQuant.setCellValueFactory(new PropertyValueFactory<>("bought_quantity"));
		tcStockUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price_bought"));
		tcStockReceivedDate.setCellValueFactory(new PropertyValueFactory<>("received_date"));
		tcStockLeftQuant.setCellValueFactory(new PropertyValueFactory<>("left_quantity"));
		tvStockInventory.setItems(dataStock_In);
		tvStockInventory.setOnMouseClicked((MouseEvent e) -> {
			if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
				code = (tvStockInventory.getSelectionModel().getSelectedItem().getPcode());
				System.out.println(code);
				//switch()
				/*try {
					main = mainMenuPage;
					main.setDisable(true);
					LoginController.MainStage.sizeToScene();
					AnchorPane root = FXMLLoader.load(getClass().getResource("/application/PlaceOrderPage.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
					NewPage = new Stage();
					NewPage.initStyle(StageStyle.UNDECORATED);
					NewPage.setAlwaysOnTop(true);
					NewPage.toFront();
					NewPage.setScene(scene);
					NewPage.show();
				}
				catch(IOException ex) {
					System.out.println(ex.getMessage());
				}*/
				
			}
		});
	}

	public void sectionInventory(ActionEvent event) throws ClientProtocolException, IOException, JSONException {
		txtSearchPI.setText("");
		tvProductInactive.setVisible(false);
		tvStockInventory.setVisible(false);
		tvProductPending.setVisible(false);
		tvOrdersCompany.setVisible(false);
		tvProductInventory.setVisible(true);
		txtSearchPI.setVisible(true);
		
		// Product Invenotry
		dataProductInventory = FXCollections.observableArrayList(Product.getListProductInventory());
		tcProductPcode.setCellValueFactory(new PropertyValueFactory<>("pcode"));
		tcProductTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		tcProductCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
		tcProductType.setCellValueFactory(new PropertyValueFactory<>("type"));
		tcProductInventory.setCellValueFactory(new PropertyValueFactory<>("inventory"));
		tcProductMinimum.setCellValueFactory(new PropertyValueFactory<>("minimum"));
		
		//SEARCH FUNCTION
		FilteredList<Product> searchProduct = new FilteredList<Product>(dataProductInventory, s -> true);
		SortedList<Product> sortedList = new SortedList<>(searchProduct);
		sortedList.comparatorProperty().bind(tvProductInventory.comparatorProperty());
		tvProductInventory.setItems(sortedList);
		txtSearchPI.setPromptText("Enter the pcode here");
		txtSearchPI.setOnKeyReleased(keyEvent ->{
			searchProduct.setPredicate(p -> p.getPcode().toLowerCase().contains(txtSearchPI.getText().toLowerCase().trim()));
		});
		
		tvProductInventory.setOnMouseClicked((MouseEvent e) -> {
			if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
				code = (tvProductInventory.getSelectionModel().getSelectedItem().getPcode());
				result = (tvProductInventory.getSelectionModel().getSelectedItem().getType());
				title = (tvProductInventory.getSelectionModel().getSelectedItem().getTitle());
				stat = (tvProductInventory.getSelectionModel().getSelectedItem().getState());
				bought = "true";
				System.out.println(code);
				try {
					main = mainMenuPage;
					main.setDisable(true);
					LoginController.MainStage.sizeToScene();
					AnchorPane root = FXMLLoader.load(getClass().getResource("/application/PlaceOrderPage.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
					NewPage = new Stage();
					NewPage.initStyle(StageStyle.UNDECORATED);
					NewPage.setAlwaysOnTop(true);
					NewPage.toFront();
					NewPage.setScene(scene);
					NewPage.show();
				}
				catch(IOException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
	}
	
	// ------------------ INBOX BUTTON -------------------
	public void sectionClientsOrders(ActionEvent event) throws JSONException, IOException {
		tvClientsOrders.setVisible(true);
		tvAlertInbox.setVisible(false);
		tvShipping.setVisible(false);
		
		dataOrder_Client = FXCollections.observableArrayList(Order_Client.getListOrderClient());
		tcClientOrderId.setCellValueFactory(new PropertyValueFactory<>("order_id"));
		tcClientEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tcClientUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
		tcClientFirstname.setCellValueFactory(new PropertyValueFactory<>("name"));
		tcClientLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
		tcClientOrderDate.setCellValueFactory(new PropertyValueFactory<>("order_date"));
		tcClientStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		tcClientSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
		tvClientsOrders.setItems(dataOrder_Client);
		tvClientsOrders.setOnMouseClicked((MouseEvent e) -> {
			if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
				code = String.valueOf(tvClientsOrders.getSelectionModel().getSelectedItem().getOrder_id());
				total = tvClientsOrders.getSelectionModel().getSelectedItem().getSubtotal();
				name = tvClientsOrders.getSelectionModel().getSelectedItem().getName();
				lastname = tvClientsOrders.getSelectionModel().getSelectedItem().getLastname();
				System.out.println(code);
				try {
					main = mainMenuPage;
					main.setDisable(true);
					LoginController.MainStage.sizeToScene();
					AnchorPane root = FXMLLoader.load(getClass().getResource("/application/ClientOrderToShip.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
					NewPage = new Stage();
					NewPage.initStyle(StageStyle.UNDECORATED);
					NewPage.setAlwaysOnTop(true);
					NewPage.toFront();
					NewPage.setScene(scene);
					NewPage.show();
				}
				catch(IOException ex) {
					System.out.println(ex.getMessage());
				}
				
			}
		});
		
	}

	public void sectionShipping(ActionEvent event) throws JSONException, IOException {
		tvClientsOrders.setVisible(true);
		tvAlertInbox.setVisible(false);
		tvShipping.setVisible(true);
		
		
		dataShipping = FXCollections.observableArrayList(Order_Client.getListShipping());
		tcShippingOrder.setCellValueFactory(new PropertyValueFactory<>("order_id"));
		tcShippingEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tcShippingUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
		tcShippingFirstname.setCellValueFactory(new PropertyValueFactory<>("name"));
		tcShippingLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
		tcShippingOrderDate.setCellValueFactory(new PropertyValueFactory<>("order_date"));
		tcShippingStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		tcShippingSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
		tcShippingDate.setCellValueFactory(new PropertyValueFactory<>("shipping_date"));
		tvShipping.setItems(dataShipping);
		tvShipping.setOnMouseClicked((MouseEvent e) -> {
			if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
				code = String.valueOf(tvShipping.getSelectionModel().getSelectedItem().getOrder_id());
				date = tvShipping.getSelectionModel().getSelectedItem().getShipping_date();
				System.out.println(code + " " + date);								
			}
		});
	}
	
	public void sectionInbox(ActionEvent event) throws ClientProtocolException, IOException, JSONException {
		tvClientsOrders.setVisible(false);
		tvAlertInbox.setVisible(true);
		tvShipping.setVisible(false);
		
		dataInboxAlert = FXCollections.observableArrayList(AlertMessage.getListAlertMessage());
		tcAlertNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
		tcAlertLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
		tcAlertMessage.setCellValueFactory(new PropertyValueFactory<>("message"));
		tvAlertInbox.setItems(dataInboxAlert);
	}

	// ------------------ HISTORY BUTTON -------------------
	public void clearHistory(ActionEvent event) throws ClientProtocolException, JSONException, IOException {
			History.DeleteHistory(LoginController.employee.getId());
			historique(null);
		}
	
	// ------------------ SETTING BUTTON -------------------
	public void applyTheme(ActionEvent event) {
		RadioButton selectedRadioButton =(RadioButton)theme.getSelectedToggle();
		switch(selectedRadioButton.getText()) {
		case "Dark Theme" :
			System.out.println(1);
			mainMenuPage.setStyle("-fx-background-color: #4b5056;");
			gLeftPane.setStyle("-fx-background-color: #2e3d42;");
			gTopPane.setStyle("-fx-background-color: #172121;");
			break;
		case "Cloud Theme" :
			System.out.println(2);
			mainMenuPage.setStyle("-fx-background-color: #a8dadc;");
			gLeftPane.setStyle("-fx-background-color: #abc4ff;");
			gTopPane.setStyle("-fx-background-color: #bee3db;");
			break;
		case "BlackWhite Theme" :
			System.out.println(3);
			mainMenuPage.setStyle("-fx-background-color: #ffffff;");
			gLeftPane.setStyle("-fx-background-color: #02040f;");
			gTopPane.setStyle("-fx-background-color: #e5dada;");
			break;
		case "Living Coral Theme" :
			System.out.println(4);
			mainMenuPage.setStyle("-fx-background-color: linear-gradient(to right, #c6ffdd, #fbd786, #f7797d);");
			gLeftPane.setStyle("-fx-background-color: #C6FFDD;");
			gTopPane.setStyle("-fx-background-color: #f7797d;");
			break;
		case "Rainbow Theme" :
			System.out.println(5);
			mainMenuPage.setStyle("-fx-background-color: linear-gradient(to right, red,orange,yellow,green,blue,indigo,violet);");
			gLeftPane.setStyle("-fx-background-color: linear-gradient(to bottom, red,orange,yellow,green,blue,indigo,violet);");
			gTopPane.setStyle("-fx-background-color: linear-gradient(to right, red,orange,yellow,green,blue,indigo,violet);");
			break;
		}
	}
	
}
