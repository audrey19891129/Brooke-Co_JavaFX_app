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

public class Employee {
	private int id;
	private String firstname,lastname,title,username,password;
	public static boolean login;
	
	public Employee() {
		
	}
	
	public Employee(int id, String firstname, String lastname, String title, String username, String password) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.title = title;
		this.username = username;
		this.password = password;
	}	
	
	public static ArrayList<Employee> getListEmployee() throws JSONException, IOException{
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("http://localhost:56023/StockServices.svc/Get_ListEmployee");
		request.addHeader("accept", "application/json");
		HttpResponse response = client.execute(request);
			if(response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()), Charsets.UTF_8));
			String jsonContent = br.readLine();

			ArrayList<Employee> liste = new ArrayList<Employee>();
			JSONTokener json = new JSONTokener(jsonContent);
			JSONArray listeProduit = new JSONArray(json);
			for(int i = 0; i < listeProduit.length(); i++) {
				JSONObject obj = listeProduit.getJSONObject(i);
				
				int id = obj.getInt("id");
				String firstname = obj.getString("firstname");
				String lastname = obj.getString("lastname");
				String title = obj.getString("title");
				String username = obj.getString("username");
				String password = obj.getString("password");
				
				Employee emp = new Employee(id,firstname,lastname,title,username,password);
				liste.add(emp);				
			}		
			return liste;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", title=" + title
				+ ", username=" + username + ", password=" + password + "]";
	}	
	
	
	
}
