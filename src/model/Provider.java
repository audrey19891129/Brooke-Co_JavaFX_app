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

public class Provider {
	public int id;
	public String name, phone, adress;
	
	
	public Provider(int id, String name, String phone, String adress) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.adress = adress;
	}
	
	public static ArrayList<Provider> getListProvider() throws ClientProtocolException, IOException, JSONException
	{
		ArrayList<Provider> list = new ArrayList<Provider>();
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("http://localhost:56023/StockServices.svc/Get_ListProvider");
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
				String name = obj.getString("name");
				String phone = obj.getString("phone");
				String adress = obj.getString("adress");
				
				Provider pr = new Provider(id,name,phone,adress);
				list.add(pr);
			}			
			return list;	
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAdress() {
		return adress;
	}


	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	
}
