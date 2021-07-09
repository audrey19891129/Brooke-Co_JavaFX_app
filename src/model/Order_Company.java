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

public class Order_Company {
	
	public double amount;
	public int confirmation, bought, purchase_id;
	public String validation, date, pcode, firstname, lastname, status;

	public Order_Company() {
		
	}
	
	public Order_Company(int confirmation, int purchase_id, String validation, double amount, String date, String pcode, int bought,
			String firstname, String lastname, String status) {
		this.confirmation = confirmation;
		this.purchase_id = purchase_id;
		this.validation = validation;
		this.amount = amount;
		this.date = date;
		this.pcode = pcode;
		this.bought = bought;
		this.firstname = firstname;
		this.lastname = lastname;
		this.status = status;
	}

	public static ArrayList<Order_Company> getListOrderCompany() throws ClientProtocolException, IOException, JSONException{
		ArrayList<Order_Company> list = new ArrayList<Order_Company>();
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("http://localhost:56023/StockServices.svc/Get_OrderCompany");
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
				
				int confirmation = obj.getInt("confirmation");
				int purchase_id = obj.getInt("purchase_id");
				String validation = obj.getString("validation");
				double amount = obj.getDouble("amount");
				String date = obj.getString("date");
				String pcode = obj.getString("pcode");
				int bought = obj.getInt("bought");
				String firstname = obj.getString("firstname");
				String lastname = obj.getString("lastname");
				String status = obj.getString("status");				
				
				Order_Company ord = new Order_Company(confirmation, purchase_id, validation, amount, date, pcode, bought, firstname, lastname, status);
				list.add(ord);
			}			
			return list;	
	}
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(int confirmation) {
		this.confirmation = confirmation;
	}
	
	public int getPurchase_id() {
		return purchase_id;
	}

	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}

	public int getBought() {
		return bought;
	}

	public void setBought(int bought) {
		this.bought = bought;
	}

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
