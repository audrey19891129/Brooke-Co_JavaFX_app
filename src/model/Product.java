package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import javafx.scene.control.CheckBox;

public class Product {
	private int bought_quantity, sold_quantity, minimum, warehouse_code, purchase_id;
	private int inventory;
	private String pcode, type, category, picture, title, genre, state;
	private double price;
	private CheckBox idSelection;
	
	public Product(String pcode, String category, String type, String title, int inventory, int minimum) {
		this.pcode = pcode;
		this.category = category;
		this.type = type;
		this.title = title;
		this.inventory = inventory;
		this.minimum = minimum;
	}
	
	public Product(String pcode, String type, String category, double price, String picture, String title,
			String genre, int bought_quantity, int sold_quantity, String state, int warehouse_code, int minimum, int inventory) {
		this.pcode = pcode;
		this.type = type;
		this.category = category;
		this.price = price;
		this.picture = picture;
		this.title = title;
		this.genre = genre;
		this.bought_quantity = bought_quantity;
		this.sold_quantity = sold_quantity;
		this.state = state;
		this.warehouse_code = warehouse_code;
		this.minimum = minimum;
		this.setInventory(inventory);
	}
	
	public Product(String pcode, String type, String category, double price, String picture, String title,
			String genre, int bought_quantity, int sold_quantity, String state, int warehouse_code, int minimum) {
		this.pcode = pcode;
		this.type = type;
		this.category = category;
		this.price = price;
		this.picture = picture;
		this.title = title;
		this.genre = genre;
		this.bought_quantity = bought_quantity;
		this.sold_quantity = sold_quantity;
		this.state = state;
		this.warehouse_code = warehouse_code;
		this.minimum = minimum;
	}	
	
	public Product(String pcode, String category, String type, String genre, String title, double price, String picture, int inventory) {
		this.pcode = pcode;
		this.category = category;
		this.type = type;
		this.genre = genre;
		this.title = title;
		this.price = price;
		this.picture = picture;
		this.setInventory(inventory);
	}
	
	public Product(String pcode, String category, String type, String genre, String title, double price, String picture, String state) {
		this.pcode = pcode;
		this.category = category;
		this.type = type;
		this.genre = genre;
		this.title = title;
		this.price = price;
		this.picture = picture;
		this.state = state;
	}
	
	public Product(String pcode, String category, String type, String genre, String title, double price, String picture, 
			String state, int purchase_id, int bought_quantity) {
		this.pcode = pcode;
		this.category = category;
		this.type = type;
		this.genre = genre;
		this.title = title;
		this.price = price;
		this.picture = picture;
		this.state = state;
		this.purchase_id = purchase_id;
		this.bought_quantity = bought_quantity;
	}
	
	public Product(String pcode, String title, String type, String category, String genre, double price,
			String picture, int inventory, int minimum, int warehouse_code, String state) {
		this.pcode = pcode;
		this.title = title;
		this.type = type;
		this.category = category;
		this.genre = genre;
		this.price = price;
		this.picture = picture;
		this.setInventory(inventory);
		this.minimum = minimum;
		this.warehouse_code = warehouse_code;
		this.state = state;
		//this.idSelection = new CheckBox();
	}

	public static ArrayList<Product> getListProduct() throws ClientProtocolException, IOException, JSONException
	{
		ArrayList<Product> list = new ArrayList<Product>();
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("http://localhost:56023/StockServices.svc/Get_ListProduct");
		request.addHeader("accept", "application/json");
		HttpResponse response = client.execute(request);
			if(response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()), Charsets.UTF_8));
			String jsonContent = br.readLine();
			
			JSONTokener json = new JSONTokener(jsonContent);			
			JSONArray listeProduit = new JSONArray(json);
			for(int i = 0; i < listeProduit.length(); i++) {
				JSONObject obj = listeProduit.getJSONObject(i);
				
				String pcode = obj.getString("pcode");
				String category = obj.getString("category");
				String type = obj.getString("type");
				String genre = obj.getString("genre");
				String title = obj.getString("title");
				double price = obj.getDouble("price");
				String picture = obj.getString("picture");
				String state = obj.getString("state");
				
				
				Product pr = new Product(pcode,category,type,genre,title,price,picture,state);
				list.add(pr);
			}			
			return list;	
	}
	
	public static ArrayList<Product> getListProductInactive() throws ClientProtocolException, IOException, JSONException
	{
		ArrayList<Product> list = new ArrayList<Product>();
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("http://localhost:56023/StockServices.svc/Get_ListProductInactive");
		request.addHeader("accept", "application/json");
		HttpResponse response = client.execute(request);
			if(response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()), Charsets.UTF_8));
			String jsonContent = br.readLine();				
			
			JSONTokener json = new JSONTokener(jsonContent);			
			JSONArray listeProduit = new JSONArray(json);
			for(int i = 0; i < listeProduit.length(); i++) {
				JSONObject obj = listeProduit.getJSONObject(i);
				
				String pcode = obj.getString("pcode");
				String category = obj.getString("category");
				String type = obj.getString("type");
				String genre = obj.getString("genre");
				String title = obj.getString("title");
				double price = obj.getDouble("price");
				String picture = obj.getString("picture");
				String state = obj.getString("state");
				
				Product pr = new Product(pcode,category,type,genre,title,price,picture,state);
				list.add(pr);
			}			
			return list;	
	}
	
	public static ArrayList<Product> getListProductPending() throws ClientProtocolException, IOException, JSONException
	{
		ArrayList<Product> list = new ArrayList<Product>();
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("http://localhost:56023/StockServices.svc/Get_ListProductPending");
		request.addHeader("accept", "application/json");
		HttpResponse response = client.execute(request);
			if(response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()), Charsets.UTF_8));
			String jsonContent = br.readLine();			
			
			JSONTokener json = new JSONTokener(jsonContent);			
			JSONArray listeProduit = new JSONArray(json);
			for(int i = 0; i < listeProduit.length(); i++) {
				JSONObject obj = listeProduit.getJSONObject(i);
				
				String pcode = obj.getString("pcode");
				String category = obj.getString("category");
				String type = obj.getString("type");
				String genre = obj.getString("genre");
				String title = obj.getString("title");
				double price = obj.getDouble("price");
				String picture = obj.getString("picture");
				String state = obj.getString("state");
				int purchase_id = obj.getInt("inventory");
				int bought_quantity = obj.getInt("bought_quantity");
				
				Product pr = new Product(pcode,category,type,genre,title,price,picture,state, purchase_id, bought_quantity);
				list.add(pr);
			}			
			return list;	
	}
	
	public static ArrayList<Product> getListProductInventory() throws ClientProtocolException, IOException, JSONException
	{
		ArrayList<Product> list = new ArrayList<Product>();
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("http://localhost:56023/StockServices.svc/Get_ListProductInventory");
		request.addHeader("accept", "application/json");
		HttpResponse response = client.execute(request);
			if(response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()), Charsets.UTF_8));
			String jsonContent = br.readLine();			
			
			JSONTokener json = new JSONTokener(jsonContent);			
			JSONArray listeProduit = new JSONArray(json);
			for(int i = 0; i < listeProduit.length(); i++) {
				JSONObject obj = listeProduit.getJSONObject(i);
				
				String pcode = obj.getString("pcode");
				String category = obj.getString("category");
				String type = obj.getString("type");
				String title = obj.getString("title");
				int inventory = obj.getInt("inventory");
				int minimum = obj.getInt("minimum");
				
				Product pr = new Product(pcode,category,type,title,inventory,minimum);
				list.add(pr);
			}			
			return list;	
	}
	
	
	public int getBought_quantity() {
		return bought_quantity;
	}

	public void setBought_quantity(int bought_quantity) {
		this.bought_quantity = bought_quantity;
	}

	public int getSold_quantity() {
		return sold_quantity;
	}

	public void setSold_quantity(int sold_quantity) {
		this.sold_quantity = sold_quantity;
	}	

	public int getPurchase_id() {
		return purchase_id;
	}

	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getWarehouse_code() {
		return warehouse_code;
	}

	public void setWarehouse_code(int warehouse_code) {
		this.warehouse_code = warehouse_code;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public CheckBox getSelection() {
		return idSelection;
	}
	
	public void setSelection(CheckBox idSelection) {
		this.idSelection = idSelection;
	}

	@Override
	public String toString() {
		return "Product [bought_quantity=" + bought_quantity + ", sold_quantity=" + sold_quantity
				+ ", minimum=" + minimum + ", warehouse_code=" + warehouse_code + ", inventory=" + inventory
				+ ", pcode=" + pcode + ", type=" + type + ", category=" + category + ", picture=" + picture + ", title="
				+ title + ", genre=" + genre + ", state=" + state + ", price=" + price + "]";
	}	

	
}
