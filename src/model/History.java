package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class History {
	public int id, employee_id;
	public String stat, message, date;
	
	public History() { }
	
	public History(int id, int employee_id, String stat, String message, String date) {
		this.id= id;
		this.employee_id = employee_id;
		this.stat = stat;
		this.message = message;
		this.date = date;
	}
	
	public static ArrayList<History> getHistory(int employee) throws JSONException, ClientProtocolException, IOException{		
		JSONObject obj = new JSONObject();
		obj.put("employee", employee);
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost("http://localhost:56023/StockServices.svc/Post_ListHistory");
		StringEntity input = new StringEntity(obj.toString());
		input.setContentType("application/json");
		request.setEntity(input);
		HttpResponse response = client.execute(request);
			if(response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()), Charsets.UTF_8));
			String output = br.readLine();
		
		ArrayList<History> list = new ArrayList<History>();
		JSONTokener json = new JSONTokener(output);
		JSONArray listHistory = new JSONArray(json);
		for(int i = 0; i < listHistory.length(); i++) {
			JSONObject out = listHistory.getJSONObject(i);
			
			int id = out.getInt("id");
			int employee_id = out.getInt("employee_id");
			String stat = out.getString("stat");
			String message = out.getString("message");
			String date = out.getString("date");
			
			list.add(new History(id,employee_id,stat,message,date));
		}
		return list;
	}
	
	public static void addToHistory(int employee_id, String stat, String message) throws JSONException, ClientProtocolException, IOException {
		JSONObject obj = new JSONObject();
		obj.put("employee_id", employee_id);
		obj.put("stat", stat);
		obj.put("message", message);
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost("http://localhost:56023/StockServices.svc/Post_AddToHistory");
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
			}
	}
	
	public static void DeleteHistory(int employee_id) throws JSONException, ClientProtocolException, IOException {
		JSONObject obj = new JSONObject();
		obj.put("employee_id", employee_id);
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost("http://localhost:56023/StockServices.svc/Post_DeleteHistory");
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
			}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "History [id=" + id + ", employee_id=" + employee_id + ", stat=" + stat + ", message=" + message
				+ ", date=" + date + "]";
	}

}
