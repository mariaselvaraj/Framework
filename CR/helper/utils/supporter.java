package utils;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class supporter {
	
	public static String getHTTPResponseCode(String url) {
        @SuppressWarnings("deprecation")
		DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse;
        try {
            httpResponse = httpClient.execute(httpGet);
        } catch (IOException e) {
            // e.printStackTrace();
            return "0";
            // throw new RuntimeException("Unable to get HTTP status code", e);
        } finally {
            httpClient.close();
        }
        return String.valueOf(httpResponse.getStatusLine().getStatusCode());
    } 

}
