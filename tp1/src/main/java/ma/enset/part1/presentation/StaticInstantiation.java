package ma.enset.part1.presentation;

import ma.enset.part1.ext.DaoImpl2;
import ma.enset.part1.metier.MetierImpl;

public class StaticInstantiation {
    public static void main(String[] args) {
        DaoImpl2 dao = new DaoImpl2();
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao);
        System.out.println(metier.calcul());
    }
}
