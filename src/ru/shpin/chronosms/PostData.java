package ru.shpin.chronosms;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ArrayList;


class PostData extends AsyncTask<String, Void, PostData> {

	public String sender;
	public String message;

    protected PostData doInBackground(String... url) {
                      
    	// Create a new HttpClient and Post Header
    	HttpClient httpclient = new DefaultHttpClient();
    	httpclient.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
    	httpclient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
    	HttpPost httppost = new HttpPost("http://chrono.shpin.ru/int.php");

    	try {
    		// Add your data
    			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
    			nameValuePairs.add(new BasicNameValuePair("sender", sender));
    			nameValuePairs.add(new BasicNameValuePair("message", message));
    			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));

    			// Execute HTTP Post Request
    			HttpResponse response = httpclient.execute(httppost);
                                
    	} catch (ClientProtocolException e) {
    		// TODO Auto-generated catch block
    	} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return null;
		
    }

}

