package ma.enset.tp1.metier;

import ma.enset.tp1.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetierImpl implements IMetier {
    @Autowired
    IDao dao;

    @Override
    public double calcul() {
        double temp = dao.getDate();
        double res = temp + Math.random() + 10;
        return res;
    }

    /*Pour injecter dans la variable dao un objet d'une classe qui implémente
    l'interface IDao*/
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
