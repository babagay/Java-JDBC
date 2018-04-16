import DAO.Developers;

import java.util.Collection;

/**
 * http://www.postgresqltutorial.com/postgresql-jdbc/
 */
public class App {


    public static void main(String[] args)
    {

        Developers dev = new Developers();

        Collection developers = dev.getAll();

        developers.stream().forEach( System.out::println );

    }






}
