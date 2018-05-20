
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import io.restassured.RestAssured;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HelloWorldServletIntegrationTest {

    private static String dockerContainterIP = System.getenv("DOCKER_MACHINE_IP") != null  ? System.getenv("DOCKER_MACHINE_IP") : "127.0.0.1" ;

    @Test
    public void testServletRersponse() throws IOException {
        URL obj = new URL ("http://"+ dockerContainterIP + ":8080/war-docker-demo/HelloWorld");

        RestAssured.when().get(obj)
                .then().statusCode(200);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        assertThat(response.toString(), is("<html><head><title>helloworld</title></head><body><h1>Hello World!</h1></body></html>"));
    }
}