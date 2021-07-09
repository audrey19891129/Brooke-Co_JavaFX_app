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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Game extends Product {
	public int number;
	public static String info;
	private String console, company, reldate;
	
	public Game(String pcode, String type, String category, double price, String picture, String title,
			String genre, int bought_quantity, int sold_quantity, String state, int warehouse_code, int minimum, int inventory,
			String console, String company, String reldate) {
		super(pcode, type, category, price, picture, title, genre, bought_quantity, sold_quantity, state,
		warehouse_code, minimum, inventory);
		this.console = console;
		this.company = company;
		this.reldate = reldate;
	}
	
	public Game(String pcode, String type, String category, double price, String picture, String title,
			String genre, int bought_quantity, int sold_quantity, String state, int warehouse_code, int minimum,
			String console, String company, String reldate) {
		super(pcode, type, category, price, picture, title, genre, bought_quantity, sold_quantity, state,
		warehouse_code, minimum);
		this.console = console;
		this.company = company;
		this.reldate = reldate;
	}
	
	public Game(int number, String pcode, String type, String category, double price, String picture, String title,
			String genre, int bought_quantity, int sold_quantity, String state, int warehouse_code, int minimum,
			String console, String company, String reldate) {
		super(pcode, type, category, price, picture, title, genre, bought_quantity, sold_quantity, state,
		warehouse_code, minimum);
		this.number = number;
		this.console = console;
		this.company = company;
		this.reldate = reldate;
	}
	
	public static ArrayList<Game> getListGame() throws JSONException, IOException{
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("http://localhost:56023/StockServices.svc/Get_ListGame");
		request.addHeader("accept", "application/json");
		HttpResponse response = client.execute(request);
			if(response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()), Charsets.UTF_8));
			String jsonContent = br.readLine();
			
			ArrayList<Game> liste = new ArrayList<Game>();
			JSONTokener json = new JSONTokener(jsonContent);
			JSONArray listeProduit = new JSONArray(json);
			for(int i = 0; i < listeProduit.length(); i++) {
				JSONObject obj = listeProduit.getJSONObject(i);
				
				String pcode = obj.getString("pcode");
				String title = obj.getString("title");
				String console = obj.getString("console");
				String company = obj.getString("company");
				String reldate = obj.getString("reldate");
				String type = obj.getString("type");
				String category = obj.getString("category");
				String genre = obj.getString("genre");
				double price = obj.getDouble("price");
				String picture = obj.getString("picture");
				int minimum = obj.getInt("minimum");
				int warehouse_code = obj.getInt("warehousecode");
				String state = obj.getString("state");
				int bought_quantity = obj.getInt("bought_quantity");
				int sold_quantity = obj.getInt("sold_quantity");
				
				Game gm = new Game(pcode, type, category, price, picture, title, genre, bought_quantity, sold_quantity,
						state, warehouse_code, minimum, console, company, reldate);
				liste.add(gm);				
			}		
			return liste;
	}
	
	public static ArrayList<Game> getTopTenGames() throws JSONException, IOException{
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("http://localhost:56023/StockServices.svc/Get_TopTenGames");
		request.addHeader("accept", "application/json");
		HttpResponse response = client.execute(request);
			if(response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()), Charsets.UTF_8));
			String jsonContent = br.readLine();
			
			ArrayList<Game> liste = new ArrayList<Game>();
			JSONTokener json = new JSONTokener(jsonContent);
			JSONArray listeProduit = new JSONArray(json);
			for(int i = 0; i < listeProduit.length(); i++) {
				JSONObject obj = listeProduit.getJSONObject(i);
				
				String pcode = obj.getString("pcode");
				String title = obj.getString("title");
				String console = obj.getString("console");
				String company = obj.getString("company");
				String reldate = obj.getString("reldate");
				String type = obj.getString("type");
				String category = obj.getString("category");
				String genre = obj.getString("genre");
				double price = obj.getDouble("price");
				String picture = obj.getString("picture");
				int minimum = obj.getInt("minimum");
				int warehouse_code = obj.getInt("warehousecode");
				String state = obj.getString("state");
				int bought_quantity = obj.getInt("bought_quantity");
				int sold_quantity = obj.getInt("sold_quantity");
				
				Game gm = new Game(new Integer(1+i), pcode, type, category, price, picture, title, genre, bought_quantity, sold_quantity,
						state, warehouse_code, minimum, console, company, reldate);
				liste.add(gm);				
			}		
			return liste;
	}
	
	public static void Edit(String pcode, String picture, String title, String category,
			String genre, double price, int warehousecode, int minimum, String state,
			int bought, int sold, String console, String company, String reldate) throws JSONException, ClientProtocolException, IOException {
		JSONObject obj = new JSONObject();
		
		obj.put("pcode", pcode);
		obj.put("picture", picture);
		obj.put("type", "book");
		obj.put("title", title);
		obj.put("category", category);
		obj.put("genre", genre);
		obj.put("price", new Double(price));
		obj.put("warehousecode", new Integer(warehousecode));
		obj.put("minimum", new Integer(minimum));
		obj.put("state", state);
		obj.put("bought_quantity", new Integer(bought));
		obj.put("sold_quantity", new Integer(sold));
		obj.put("console", console);
		obj.put("company", company);
		obj.put("reldate", reldate);
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost("http://localhost:56023/StockServices.svc/Post_EditGame");
		StringEntity input = new StringEntity(obj.toString());
		input.setContentType("application/json");
		request.setEntity(input);
		HttpResponse response = client.execute(request);
			if(response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()), Charsets.UTF_8));
			String output;
			System.out.println("Output from the Server .... \n");
			while((output = br.readLine()) != null) {
				System.out.println(output);
				info = output;
			}
	}

	
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getReldate() {
		return reldate;
	}

	public void setReldate(String reldate) {
		this.reldate = reldate;
	}

	@Override
	public String toString() {
		return super.toString() + "Game [console=" + console + ", company=" + company + ", reldate=" + reldate + "]";
	}	
	
	
}
