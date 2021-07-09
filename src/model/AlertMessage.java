package model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

public class AlertMessage {
	public int number;
	public String level, message;
	
	public AlertMessage() { 
		
	}
	
	public AlertMessage(int number, String level, String message) {
		this.number = number;
		this.level = level;
		this.message = message;
	}
	
	public static ArrayList<AlertMessage> getListAlertMessage() throws ClientProtocolException, IOException, JSONException{
		int n = 1;
		ArrayList<AlertMessage> list = new ArrayList<AlertMessage>();
		ArrayList<Product> pr = Product.getListProductInventory();
		ArrayList<Product> pro = Product.getListProduct();
		ArrayList<Product> prod = Product.getListProductInactive();
		ArrayList<Product> prodt = Product.getListProductPending();
		ArrayList<Order_Client> cl = Order_Client.getListOrderClient();
		ArrayList<Order_Client> clt = Order_Client.getListShipping();
		
		for(Product item : pr) {
			if(item.getInventory() < item.getMinimum()) {
				list.add(new AlertMessage(n++,"URGENT","The Product : [ " + item.getPcode() + " ]. The inventory is below minimum, Please place a order as soon as possible."));
			}		
		}
		for(Product item : pro) {
			if(item.getPicture().equals("images/noPicture.jpg")) {
				list.add(new AlertMessage(n++,"WARNING","No Picture for the Product : [ " + item.getPcode() + " ]. Please update the Picture for the product."));
			}
		}
		for(Product item : prod) {
			list.add(new AlertMessage(n++,"WARNING", "The New Product : [ " + item.getPcode() + " ] need a order. Please Place a Order for the new product." ));
		}
		for(Product item : prodt) {
			list.add(new AlertMessage(n++,"NEED CONFIRMATION", "The Product : [ " + item.getPcode() + " ] need your Approval. Please verify in Section [ Product Arrived ]."));
		}
		for(Order_Client client : cl) {
			list.add(new AlertMessage(n++,"ORDER"," The order number : [ " + client.getOrder_id() + " ] need to be shipped. Please proceed with the order for shipment."));
		}
		for(Order_Client client : clt) {
			String date = client.getShipping_date();
			String[] dates = date.split("-",5);
			int year = Integer.valueOf(dates[0]);
			int month = Integer.valueOf(dates[1]);
			int day = Integer.valueOf(dates[2]);
			
			LocalDate date1 = LocalDate.of(year,month,day);
			LocalDate date2 = LocalDate.now();
			
			long days = ChronoUnit.DAYS.between(date1, date2);
			if(days > 5) {
				if(client.getStatus().equals("arrived")) {
					
				}else {
					list.add(new AlertMessage(n++,"WARNING"," The order number : [ " + client.getOrder_id() + " ] has not been received by the client yet. Please verify the shipping status."));
				}			
			}
		}
		return list;
	}
	
	public static int getWarning() throws ClientProtocolException, IOException, JSONException {
		ArrayList<AlertMessage> am = AlertMessage.getListAlertMessage();
		int warning = 0;
		for(AlertMessage warn : am) {
			if(warn.getLevel().equals("WARNING")) {
				warning++;
			}
		}
		return warning;
	}
	
	public static int getUrgent() throws ClientProtocolException, IOException, JSONException {
		ArrayList<AlertMessage> am = AlertMessage.getListAlertMessage();
		int urgent = 0;
		for(AlertMessage warn : am) {
			if(warn.getLevel().equals("URGENT")) {
				urgent++;
			}
		}
		return urgent;
	}
	
	public static int getCofirmation() throws ClientProtocolException, IOException, JSONException {
		ArrayList<AlertMessage> am = AlertMessage.getListAlertMessage();
		int urgent = 0;
		for(AlertMessage warn : am) {
			if(warn.getLevel().equals("NEED CONFIRMATION")) {
				urgent++;
			}
		}
		return urgent;
	}
	
	public static int getOrder() throws ClientProtocolException, IOException, JSONException {
		ArrayList<AlertMessage> am = AlertMessage.getListAlertMessage();
		int urgent = 0;
		for(AlertMessage warn : am) {
			if(warn.getLevel().equals("ORDER")) {
				urgent++;
			}
		}
		return urgent;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return number + ", " + level + " , " + message;
	}
	
	
	
}
