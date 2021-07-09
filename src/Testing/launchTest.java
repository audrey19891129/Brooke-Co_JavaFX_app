package Testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import model.*;

public class launchTest {

	public static void main(String[] args) throws ClientProtocolException, IOException, JSONException {		
		
//		String date = "2020-12-07";
//		String[] dates = date.split("-",5);
//		int year = Integer.valueOf(dates[0]);
//		int month = Integer.valueOf(dates[1]);
//		int day = Integer.valueOf(dates[2]);
//		
//		
//		LocalDate date1 = LocalDate.of(year,month,day);
//		LocalDate date2 = LocalDate.now();
//		
//		long days = ChronoUnit.DAYS.between(date1, date2);
//		System.out.println(days);
		
	//	History.addToHistory(5, "TESTING", "TESTING");
		
//		ArrayList<History> list = History.getHistory(5);
//		for(History h : list) {
//			System.out.println(h.toString());
//		}
		
//		ArrayList<AlertMessage> list = AlertMessage.getListAlertMessage();
//		for(AlertMessage am : list) {
//			System.out.println(am.toString());
//		}
//		System.out.println("\n");
//		System.out.println(AlertMessage.getWarning());
//		System.out.println(AlertMessage.getUrgent());
//		System.out.println(AlertMessage.getCofirmation());
//		System.out.println(AlertMessage.getOrder());
		
//		ArrayList<Product> list = Product.getListProductInventory();
//		for(Product pr : list) {
//			System.out.println(pr.getInventory());
//		}
		
//		ArrayList<Entry> list = Entry.getListEntry(2);
//		for(Entry et : list) {
//			System.out.println(et.getSubtotal());
//		}
//		
//		ArrayList<Order_Client> list = Order_Client.getListOrderClient();
//		for(Order_Client cl : list) {
//			System.out.println(cl.getOrder_date());
//		}
//		
//		ArrayList<Product> list = Product.getListProductPending();
//		for(Product lt : list) {
//			System.out.println(lt.getPurchase_id());
//		}
		
		//ArrayList<Order_Company> list = Order_Company.getListOrderCompany();
		
//		JSONObject obj = new JSONObject();
//		
//		obj.put("pcode", "ISBN:00002");
//		obj.put("picture", "image.jpeg");
//		obj.put("type", "book");
//		obj.put("title", "Test");
//		obj.put("category", "TEST");
//		obj.put("genre", "testing");
//		obj.put("price", new Double(12.25));
//		obj.put("warehousecode", new Integer(1));
//		obj.put("minimum", new Integer(1));
//		obj.put("state", "inactive");
//		obj.put("bought_quantity", new Integer(0));
//		obj.put("sold_quantity", new Integer(0));
//		obj.put("authors", "TESTING");
//		obj.put("pubCo", "TESTING");
//		obj.put("pubDate", "2015-08-10");
//		
//		
//		// EDIT BOOK
//		HttpClient client = HttpClientBuilder.create().build();
//		HttpPost request = new HttpPost("http://localhost:56023/StockServices.svc/Post_EditBook");
//		StringEntity input = new StringEntity(obj.toString());
//		input.setContentType("application/json");
//		request.setEntity(input);
//		HttpResponse response = client.execute(request);
//			if(response.getStatusLine().getStatusCode() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
//			}
//			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
//			String output;
//			System.out.println("Output from the Server .... \n");
//			while((output = br.readLine()) != null) {
//				System.out.println(output);
//			}
	
	}

}
