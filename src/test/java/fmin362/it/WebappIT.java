package fmin362.it;

import junit.framework.TestCase;

import java.net.URL;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class WebappIT extends TestCase
{
    private String baseUrl;
    
    public void setUp() throws Exception
    {
        super.setUp();
        String port = System.getProperty("servlet.port");
        this.baseUrl = "http://localhost:" + port + "/les3DTwitter_Maven";
    }

    public void testCallIndexPage() throws Exception
    {
        URL url = new URL(this.baseUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        assertEquals(200, connection.getResponseCode());
    }
    @Test
    public void testJDBCServlet() throws Exception
    {
        URL url = new URL( this.baseUrl + "/jdbc" );
        HttpURLConnection connection = ( HttpURLConnection ) url.openConnection();
        connection.connect();
        assertEquals( 200, connection.getResponseCode() );
    }
    @Test
    public void testJPAServlet() throws Exception
    {
        URL url = new URL( this.baseUrl + "/jpa" );
        HttpURLConnection connection = ( HttpURLConnection ) url.openConnection();
        connection.connect();
        assertEquals( 200, connection.getResponseCode() );
    }
    @Test
    public void testMessagesResource() throws Exception
    {
        URL url = new URL( this.baseUrl + "/resources/messages" );
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put( JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE );
        Client client = Client.create( clientConfig );
        WebResource webResource = client.resource( url.toURI() );
        List<Map<String,?>> result = webResource.get( List.class );
        System.out.println( result );
        assertTrue( result.size() > 0 );
    }


}
