package ma.enset.tp1.presentation;

import ma.enset.tp1.dao.IDao;
import ma.enset.tp1.metier.IMetier;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class DynamicInstantiation {
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Scanner scanner = new Scanner(new File("config.txt"));
        String daoClassName = scanner.next();
        String metierClassName = scanner.next();
        Class cdao = Class.forName(daoClassName);
        IDao dao = (IDao) cdao.newInstance();
        Class cmetier = Class.forName(metierClassName);
        IMetier metierImpl = (IMetier) cmetier.newInstance();
        Method method = cmetier.getMethod("setDao", IDao.class);
        method.invoke(metierImpl,dao);
        System.out.println(metierImpl.calcul());


    }
}
