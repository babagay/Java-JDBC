package Facade;

import Entity.Developer;
import SQL.Parameter;

import java.util.ArrayList;

public class Developers {

    private static Repository.Developers developers = new Repository.Developers();

    public static ArrayList<Developer> getById(int id)
    {
        String SQL = "SELECT id, name, age, address, salary, sex FROM developers WHERE id = ?";

        ArrayList<Parameter> params = new ArrayList<>();
        params.add( new Parameter<Integer>( id ) );

        return (ArrayList<Developer>) developers.find( SQL, params );
    }

    public static ArrayList<Developer> getByAgeName(int age, String name)
    {
        String SQL = "SELECT id, name, age, address, salary, sex FROM developers WHERE age = ? AND name = ?";
        ArrayList<Parameter> params = new ArrayList<>();
        params.add( new Parameter<Integer>( 36 ) );
        params.add( new Parameter<String>( "Alex P" ) );

        return (ArrayList<Developer>) developers.find( SQL, params );
    }

    /**
     * Вычислить общую ЗП только Java разработчиков
     */
    public static int getSalaryTotalByDevs(String programLang)
    {
        ArrayList<Parameter> params = new ArrayList<>();
        params.add( new Parameter<String>( programLang ) );

        String SQL =
                "SELECT SUM(dev.salary) java_total\n" +
                "FROM\n" +
                "  (\n" +
                "    SELECT d.id\n" +
                "    FROM skills s\n" +
                "      INNER JOIN developers_to_skills d2s ON s.id = d2s.SKILL_ID AND s.title = ?\n" +
                "      JOIN developers d ON d.id = d2s.developer_id\n" +
                "    GROUP BY d.id\n" +
                "  ) t1\n" +
                "  JOIN developers dev ON dev.id = t1.id;";

        return (int) developers.find( SQL, params, "SINGLE_INT" );
    }

    /**
     * Вычислить среднюю ЗП программистов в самом дешевом проекте
     */
    public static int getAvgSalaryByDevsInCheapestProject()
    {
        ArrayList<Parameter> params = new ArrayList<>();
        params.add( new Parameter<Integer>( 0 ) );

        String SQL = "SELECT AVG(d.salary) avg_devsalary_on_cheapestproject\n" +
                "FROM developers d\n" +
                "  JOIN developers_to_projects d2p ON d.id = d2p.developer_id\n" +
                "\n" +
                "WHERE d.id > ? AND d2p.project_id = (\n" +
                "  SELECT id cheapest_project_id\n" +
                "  FROM projects pr\n" +
                "  WHERE pr.cost =\n" +
                "        (\n" +
                "          SELECT min(p.cost)\n" +
                "          FROM projects p\n" +
                "        )\n" +
                ");";

        return (int) developers.find( SQL, params, "SINGLE_INT" );
    }



}
