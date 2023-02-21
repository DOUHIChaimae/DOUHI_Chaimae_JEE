package ma.enset.tp1.dao;

import org.springframework.stereotype.Component;

@Component("dao")
public class DaoImpl implements IDao {
    @Override
    public double getDate() {
        System.out.println("version WS");
        double temp = Math.random()*40;
        return temp;
    }
}
