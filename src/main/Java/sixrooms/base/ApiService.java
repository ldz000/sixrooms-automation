package sixrooms.base;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: zh
 * Date: 2021/6/16 14:55
 */

public class ApiService {

    public HttpResult doGet(String url) throws Exception {
        return doGet(url, null);
    }

    public HttpResult doGet(String url, Map<String, Object> map) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        URIBuilder uriBuilder = new URIBuilder(url);
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        CloseableHttpResponse response = httpClient.execute(httpGet);
        Integer code = response.getStatusLine().getStatusCode();
        if (response.getEntity() != null) {
            String body = EntityUtils.toString(response.getEntity(), "utf-8");
            return new HttpResult(code, body);
        } else {
            return new HttpResult(code, null);
        }
    }

    public HttpResult doPost(String url) throws Exception{
        return doPost(url,null);
    }

    public HttpResult doPost(String url, Map<String, Object> map) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        if (map != null) {
            List<NameValuePair> params = new ArrayList<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "utf-8");
            httpPost.setEntity(entity);
        }
        CloseableHttpResponse response = httpClient.execute(httpPost);
        Integer code = response.getStatusLine().getStatusCode();// 响应的状态码
        if (response.getEntity() != null) {
            String body = EntityUtils.toString(response.getEntity(), "utf-8");
            HttpResult result = new HttpResult(code, body);
            return result;
        } else {
            HttpResult result = new HttpResult(code, null);
            return result;
        }
    }
}
