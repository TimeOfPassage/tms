import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtilsTest {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/tmsapi/api/category/list");
        httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String respTxt = EntityUtils.toString(entity, "UTF-8");
            System.out.println(respTxt.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
