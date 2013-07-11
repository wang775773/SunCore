package bupt.sse.monitoringsystem.msg.util.net;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class DoHttpTransmission {
	public static String doHttpPostTransmission(String url,
			Map<String, String> parameters) throws Exception {
		HttpClient httpClient = HttpClientFactory.getHttpClient();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		if (parameters != null && parameters.size() != 0) {
			for (Entry<String, String> entry : parameters.entrySet()) {
				nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
		}
		HttpEntity entity = new UrlEncodedFormEntity(nameValuePairs, "utf-8");
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(entity);
		HttpResponse response = httpClient.execute(httpPost);
		return new String(EntityUtils.toByteArray(response.getEntity()),
				"utf-8");
	}
}
