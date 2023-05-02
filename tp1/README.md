# Compte rendu de TP1 : "Inversion de contrôle et Injection des dépendances"

## Sommaire

- [Créer l'interface IDao](#Créer-linterface-IDao)
- [Créer une implémentation de cette interface](#créer-une-implémentation-de-cette-interface)
- [Créer l'interface IMetier](#Créer-linterface-IMetier)
- [Créer une implémentation de cette interface en utilisant le couplage faible](#Créer-une-implémentation-de-cette-interface-en-utilisant-le-couplage-faible)
- [Faire l'injection des dépendances](#faire-linjection-des-dépendances) 
  - [Par instanciation statique](#Par-instanciation-statique)
  - [Par instanciation dynamique](#Par-instanciation-dynamique)
  - [En utilisant le Framework Spring](#En-utilisant-le-Framework-Spring)
    - [Version XML](#Version-XML)
    - [Version annotations](#Version-annotations)

## Créer l'interface IDao

```java
package ma.enset.tp1.part1.dao;

public interface IDao {
  double getDate();
}
```

## Créer une implémentation de cette interface

```java
package ma.enset.tp1.part1.dao;

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
```

## Créer l'interface IMetier

```java
package ma.enset.tp1.part1.metier;

public interface IMetier {
  double calcul();
}
```

## Créer une implémentation de cette interface en utilisant le couplage faible

```java
package ma.enset.tp1.part1.metier;
import ma.enset.tp1.part1.dao.IDao;
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

  //setDao() to inject in dao variable an object of a class that implements IDao interface
  public void setDao(IDao dao) {
    this.dao = dao;
  }
}

```

## Faire l'injection des dépendances
L'injection des dépendances nous permet de séparer le code métier et le code technique , pour le faire on peut travailler avec les méthodes suivantes :  
### Par instanciation statique
```java
package ma.enset.tp1.part1.presentation;
import ma.enset.tp1.part1.ext.DaoImpl2;
import ma.enset.tp1.part1.metier.MetierImpl;

public class StaticInstantiation {
  public static void main(String[] args) {
    DaoImpl2 dao = new DaoImpl2();
    MetierImpl metier = new MetierImpl();
    metier.setDao(dao);
    System.out.println(metier.calcul());
  }
}

```
### Par instanciation dynamique

```java
package ma.enset.tp1.part1.presentation;
import ma.enset.tp1.part1.dao.IDao;
import ma.enset.tp1.part1.metier.IMetier;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class DynamicInstantiation {
  public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    Scanner scanner = new Scanner(new File("tp1/config.txt"));
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

```

voici le contenu de config.txt : 

```txt
ma.enset.tp1.part1.dao.DaoImpl
ma.enset.tp1.part1.metier.MetierImpl
```

### En utilisant le Framework Spring

D'abord pour travailler avec le framework spring nous avons installé les dépendances correspondantes en utilisant le package manager maven qui lié à le fichier pom.xml suivant :

```XML
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>ma.enset</groupId>
    <artifactId>maven1</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <!--        la version de java utilise ; exemple 8-->
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-core -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.16</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>5.3.16</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.3.16</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework/spring-aop -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>5.3.16</version>
        </dependency>
    </dependencies>

</project>
```

## 🔗 Retrouvez moi sur :
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/chaimae-douhi/)


