package Facade;

import Entity.Project;
import SQL.Parameter;

import java.util.ArrayList;

public class Projects {

    private static Repository.Projects projects = new Repository.Projects();

    /**
     * Найти самый дорогой проект (исходя из salary всех разработчиков)
     */
    public static ArrayList<Project> getTheMostExpensiveProject()
    {
        ArrayList<Parameter> params = new ArrayList<>();
        params.add( new Parameter<Integer>( 0 ) );

        String SQL =
                "select id, t1.project title,\n" +
                "    CASE\n" +
                "        WHEN project_cost IS NULL THEN 0\n" +
                "        ELSE project_cost\n" +
                "    END AS \"cost\"\n" +
                "from\n" +
                "    (\n" +
                "        select p.id, p.title project,\n" +
                "               (\n" +
                "                   select SUM(salary)\n" +
                "                   from DEVELOPERS_TO_PROJECTS d2p\n" +
                "                       left join developers d on d.id = d2p.developer_id\n" +
                "                   where p.id = d2p.project_id\n" +
                "                         and p.id > ?\n" +
                "               ) project_cost\n" +
                "        from projects p\n" +
                "\n" +
                "    ) t1\n" +
                "order by cost desc\n" +
                "limit 1;";

       return (ArrayList<Project>) projects.find( SQL, params );
    }
}
