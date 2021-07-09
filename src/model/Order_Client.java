package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Order_Client {
	public int order_id;
	public String email,username,name,lastname,order_date,status, shipping_date;
	public double subtotal;
	
	public Order_Client(int order_id, String email, String username, String name, String lastname, String order_date,
			String status, double subtotal) {
		super();
		this.order_id = order_id;
		this.email = email;
		this.username = username;
		this.name = name;
		this.lastname = lastname;
		this.order_date = order_date;
		this.status = status;
		this.subtotal = subtotal;
	}
	
	public Order_Client(int order_id, String email, String username, String name, String lastname, String order_date,
			String status, double subtotal, String shipping_date) {
		super();
		this.order_id = order_id;
		this.email = email;
		this.username = username;
		this.name = name;
		this.lastname = lastname;
		this.order_date = order_date;
		this.status = status;
		this.subtotal = subtotal;
		this.shipping_date = shipping_date;
	}
	
	public static ArrayList<Order_Client> getListOrderClient() throws JSONException, IOException{
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("http://localhost:56023/StockServices.svc/Get_OrderClient");
		request.addHeader("accept", "application/json");
		HttpResponse response = client.execute(request);
			if(response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()), Charsets.UTF_8));
			String jsonContent = br.readLine();
			
			ArrayList<Order_Client> liste = new ArrayList<Order_Client>();
			JSONTokener json = new JSONTokener(jsonContent);
			JSONArray listeProduit = new JSONArray(json);
			for(int i = 0; i < listeProduit.length(); i++) {
				JSONObject obj = listeProduit.getJSONObject(i);
				
				int order_id = obj.getInt("order_id");
				String email = obj.getString("email");
				String username = obj.getString("username");
				String name = obj.getString("name");
				String lastname = obj.getString("lastname");
				String order_date = obj.getString("order_date");
				String status = obj.getString("status");
				double subtotal = obj.getDouble("subtotal");
				
				Order_Client odc = new Order_Client(order_id,email,username,name,lastname,order_date,status,subtotal);
				liste.add(odc);				
			}		
			return liste;
	}	
	
	public static ArrayList<Order_Client> getListShipping() throws JSONException, IOException{
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("http://localhost:56023/StockServices.svc/Get_ShippingList");
		request.addHeader("accept", "application/json");
		HttpResponse response = client.execute(request);
			if(response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()), Charsets.UTF_8));
			String jsonContent = br.readLine();
			
			ArrayList<Order_Client> liste = new ArrayList<Order_Client>();
			JSONTokener json = new JSONTokener(jsonContent);
			JSONArray listeProduit = new JSONArray(json);
			for(int i = 0; i < listeProduit.length(); i++) {
				JSONObject obj = listeProduit.getJSONObject(i);
				
				int order_id = obj.getInt("order_id");
				String email = obj.getString("email");
				String username = obj.getString("username");
				String name = obj.getString("name");
				String lastname = obj.getString("lastname");
				String order_date = obj.getString("order_date");
				String status = obj.getString("status");
				double subtotal = obj.getDouble("subtotal");
				String shipping_date = obj.getString("shipping_date");
				
				Order_Client odc = new Order_Client(order_id,email,username,name,lastname,order_date,status,subtotal, shipping_date);
				liste.add(odc);				
			}		
			return liste;
	}	


	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public String getShipping_date() {
		return shipping_date;
	}

	public void setShipping_date(String shipping_date) {
		this.shipping_date = shipping_date;
	}

	@Override
	public String toString() {
		return "Order_Client [order_id=" + order_id + ", email=" + email + ", username=" + username + ", name=" + name
				+ ", lastname=" + lastname + ", order_date=" + order_date + ", status=" + status + ", shipping_date="
				+ shipping_date + ", subtotal=" + subtotal + "]";
	}
	
	
	

}
