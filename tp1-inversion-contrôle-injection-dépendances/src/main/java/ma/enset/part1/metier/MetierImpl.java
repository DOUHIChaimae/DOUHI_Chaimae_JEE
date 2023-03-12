package ma.enset.part1.metier;

import ma.enset.part1.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetierImpl implements IMetier {
    @Autowired
    private IDao dao;

    @Override
    public double calcul() {
        double temp = dao.getDate();
        double res = temp + Math.random() + 10;
        return res;
    }

    //setDao() to inject in dao variable an object of a clss that implements IDao interface
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
