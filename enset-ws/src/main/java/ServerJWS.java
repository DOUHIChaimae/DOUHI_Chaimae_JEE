import jakarta.xml.ws.Endpoint;
import ma.enset.BanqueService;

public class ServerJWS {
    public static void main(String[] args) {
        String url =  "http://0.0.0.0:8087/";
        Endpoint.publish(url, new BanqueService());
        System.out.println("Web service déployé sur " + url);
    }
}
