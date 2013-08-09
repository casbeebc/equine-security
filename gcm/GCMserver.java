import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Message.Builder;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.*;
import java.io.*;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


public class GCMserver {

	private static String API_KEY = "";
	
	public static String getRegistrationID() {
        String regID = null; 
        try {
            URL getRegistrationURL = new URL("http://equinesecurity.datamingle.com/getRegistrationID.php");
            HttpURLConnection uc = (HttpURLConnection)getRegistrationURL.openConnection();
            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                    uc.getInputStream()));
            StringBuilder stringbuilder = new StringBuilder();
            String line;
            while((line = in.readLine()) != null) {
                stringbuilder.append(line);
            }
                
            regID = stringbuilder.toString();
            in.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return regID;
	}
	
	public static void main(String[] args) {
		Sender sender = new Sender(API_KEY);
		Builder mb = new Message.Builder();
		mb.collapseKey("1");
		mb.timeToLive(3);
		mb.delayWhileIdle(true);
		mb.addData("message", "This is the payload of the message");
		Message message = mb.build();
		
		String regID = getRegistrationID();
		
		try {
			Result result = sender.send(message, regID, 3);
			
			if (result.getMessageId() != null)
			{
				String canonicalRegId = result.getCanonicalRegistrationId();
				if (canonicalRegId != null)
				{
				// same device has more than on registration ID: update database
					System.out.println("ERROR.... same device has more than on registration ID: update database");
				}
			} 
			else
			{
				String error = result.getErrorCodeName();				
				System.out.println("result code: " + error);
			}
		} catch (IOException e) {
			System.out.println("ERROR.... exception caught");
			e.printStackTrace();
		}
	}
}
