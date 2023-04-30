import proxy.BanqueServiceService;
import proxy.BanqueWS;
import proxy.Compte;

public class ClientWs {
    public static void main(String[] args) {
        BanqueWS stub = new BanqueServiceService().getBanqueWSPort();
        System.out.println(stub.convert(7600));
        Compte compte = stub.getCompte(5);
        System.out.println(compte.getCode());
        System.out.println(compte.getSolde());
    }
}
