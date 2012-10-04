package com.example.foursquareassig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*make http request to foursquare and get the result 
 * as JSON object 
 */
public class JSONRequest {
	    
	private static String name2;
	private static String distance2;
	private static double latitude2;
	private static double longitude2;
	
	public Double [] latitude;
	public Double [] longitude;

	public JSONRequest() {
		// TODO Auto-generated constructor stub
	}

	public List<HashMap<String,String>> getJSONfromURL(String url){
		 
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String response = new String();
		 // Each row in the list stores country name, place and image
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
		try {
			response = httpClient.execute(httpGet, responseHandler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONObject object;
		try {
			object = new JSONObject(response);
			JSONObject response2 = object.getJSONObject("response");
			JSONArray venues = response2.getJSONArray("venues");
			latitude = new Double[venues.length()];
			longitude = new Double[venues.length()];
			
			for (int i = 0; i < venues.length(); i++) {
				
				JSONObject childJSONObject = venues.getJSONObject(i);				
				name2 = childJSONObject.getString("name");
				JSONObject location = childJSONObject.getJSONObject("location");
				distance2 = location.getString("distance");		
				latitude2 = location.getDouble("lat");
				longitude2 = location.getDouble("lng");
				HashMap<String, String> hm = new HashMap<String,String>();
			    hm.put("txt", name2);
	            hm.put("pl","Distance: "+ distance2 );
	            aList.add(hm);	
	            
	            latitude[i] = latitude2;
	            longitude[i] = longitude2;
	            
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aList;
	}
}



