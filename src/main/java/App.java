import Entity.Developer;
import Entity.Project;
import Facade.Projects;


import java.util.ArrayList;
import java.util.Collection;

/**
 * http://www.postgresqltutorial.com/postgresql-jdbc/
 *
 * todo
 * Connection pool
 */
public class App {


    public static void main(String[] args)
    {

        // Example get all
        Repository.Developers developers = new Repository.Developers();
        Collection<Developer> developersCollection = developers.getAll();
        // developersCollection.stream().forEach( System.out::println );

        // Example get by id
        Developer Alex = developers.get( 101 );
        /// System.out.println(Alex);

        // Example find
        ArrayList<Developer> list = Facade.Developers.getByAgeName( 36, "Alex P" );
        /// System.out.println( list.get( 0 ) );

        // Example calculate
        int resultInt = Facade.Developers.getSalaryTotalByDevs( "Java" );
        /// System.out.println(resultInt);

        // Example calculate
        int avgSalary = Facade.Developers.getAvgSalaryByDevsInCheapestProject();
        /// System.out.println( avgSalary );

        // Fetch all projects
        Repository.Projects projectsRepo = new Repository.Projects();
        Collection<Project> projectsAll = projectsRepo.getAll();
        /// System.out.println(projectsAll);

        // Find a project by condition
        ArrayList<Project> projectList = Projects.getTheMostExpensiveProject();
        System.out.println(projectList.get( 0 ));

    }






}
