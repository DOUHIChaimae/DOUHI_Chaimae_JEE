package ma.enset.ext;

import ma.enset.dao.IDao;

public class DaoImpl2 implements IDao {
    @Override
    public double getDate() {
        System.out.println("version bdd");
        return 20;
    }
}
