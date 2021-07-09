package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Entry {
	public int id, order_id, quantity;
	public String pcode;
	public double price, subtotal;
	
	public Entry(int id, int order_id, String pcode, int quantity, double price, double subtotal) {
		this.id = id;
		this.order_id = order_id;
		this.quantity = quantity;
		this.pcode = pcode;
		this.price = price;
		this.subtotal = subtotal;
	}
	
	public static ArrayList<Entry> getListEntry(int orderID) throws JSONException, IOException{
		JSONObject obj = new JSONObject();
		obj.put("order_id", orderID);					
			
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost("http://localhost:56023/StockServices.svc/Post_ReturnListEntry");
			StringEntity input = new StringEntity(obj.toString());
			input.setContentType("application/json");
			request.setEntity(input);
			HttpResponse response = client.execute(request);
				if(response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
				}
				BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()), Charsets.UTF_8));
				String output = br.readLine();
				
				ArrayList<Entry> list = new ArrayList<Entry>();
				JSONTokener json = new JSONTokener(output);
				JSONArray listeProduit = new JSONArray(json);
				for(int i = 0; i < listeProduit.length(); i++) {
					JSONObject out = listeProduit.getJSONObject(i);
					
					int id = out.getInt("id");
					int order_id = out.getInt("order_id");
					String pcode = out.getString("pcode");
					int quantity = out.getInt("quantity");
					double price = out.getDouble("price");
					double subtotal = out.getDouble("subtotal");
					
					list.add(new Entry(id,order_id,pcode,quantity,price,subtotal));
				}
				return list;
	}	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	
	
}
