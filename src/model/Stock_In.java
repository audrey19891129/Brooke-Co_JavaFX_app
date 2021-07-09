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

public class Stock_In {
	
	private int id, provide_id, purchase_id, bought_quantity, left_quantity;
	private String pcode, provider_name, received_date;
	private double unit_price_bought;
	
	public Stock_In() {
		
	}
	
	public Stock_In(int id, String pcode, String provider_name, int purchase_id, 
			int bought_quantity, double unit_price_bought, String received_date, int left_quantity) {
		super();
		this.id = id;
		this.purchase_id = purchase_id;
		this.bought_quantity = bought_quantity;
		this.left_quantity = left_quantity;
		this.pcode = pcode;
		this.provider_name = provider_name;
		this.received_date = received_date;
		this.unit_price_bought = unit_price_bought;
	}
	
	public static ArrayList<Stock_In> getListStock_In() throws ClientProtocolException, IOException, JSONException
	{
		ArrayList<Stock_In> list = new ArrayList<Stock_In>();
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("http://localhost:56023/StockServices.svc/Get_ListStock_In");
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
				
				int id = obj.getInt("id");
				String pcode = obj.getString("pcode");
				String provider_name = obj.getString("provider_name");
				int purchase_id = obj.getInt("purchase_id");
				int bought_quantity = obj.getInt("bought_quantity");
				double unit_price_bought = obj.getDouble("unit_price_bought");
				String received_date = obj.getString("received_date");
				int left_quantity = obj.getInt("left_quantity");
				
				Stock_In st = new Stock_In(id, pcode, provider_name, purchase_id, bought_quantity, unit_price_bought, received_date, left_quantity);
				list.add(st);
			}			
			return list;	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProvide_id() {
		return provide_id;
	}

	public void setProvide_id(int provide_id) {
		this.provide_id = provide_id;
	}

	public int getPurchase_id() {
		return purchase_id;
	}

	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}

	public int getBought_quantity() {
		return bought_quantity;
	}

	public void setBought_quantity(int bought_quantity) {
		this.bought_quantity = bought_quantity;
	}

	public int getLeft_quantity() {
		return left_quantity;
	}

	public void setLeft_quantity(int left_quantity) {
		this.left_quantity = left_quantity;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getProvider_name() {
		return provider_name;
	}

	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}

	public String getReceived_date() {
		return received_date;
	}

	public void setReceived_date(String received_date) {
		this.received_date = received_date;
	}

	public double getUnit_price_bought() {
		return unit_price_bought;
	}

	public void setUnit_price_bought(double unit_price_bought) {
		this.unit_price_bought = unit_price_bought;
	}
	
}
