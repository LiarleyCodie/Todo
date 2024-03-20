package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO
{
    private final String DRIVER   = "com.mysql.cj.jdbc.Driver";
    private final String URL      = "jdbc:mysql://" + System.getenv("DB_URL");
    private final String USER     = System.getenv("DB_USER");
    private final String PASSWORD = System.getenv("DB_PASSWORD");

    private Connection connect() throws Exception
    {
        Connection conn = null;

        Class.forName(DRIVER);
        conn = DriverManager.getConnection(URL, USER, PASSWORD);

        return conn;
    }

    public void create(JavaBeans task)
    {
        String query = "INSERT into tasks (title, done) VALUES (?, ?)";

        try
        {
            var conn = connect();

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, task.getTitle());
            pst.setBoolean(2, task.getIsDone());

            pst.executeUpdate();

            pst.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("[ Class DAO - create() Exception ]");
            e.printStackTrace();
        }
    }

    public ArrayList<JavaBeans> getTasks()
    {
        ArrayList<JavaBeans> tasks = new ArrayList<JavaBeans>();

        try
        {
            var conn = connect();

            final String QUERY = "SELECT * FROM tasks";

            PreparedStatement pst     = conn.prepareStatement(QUERY);
            ResultSet         dbTasks = pst.executeQuery();

            while (dbTasks.next())
            {
                int     id     = dbTasks.getInt(1);
                String  title  = dbTasks.getString(2);
                boolean isDone = dbTasks.getBoolean(3);

                JavaBeans task = new JavaBeans(id, title, isDone);
                tasks.add(task);
            }

            pst.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("[ class DAO - getTasks() exception ]");
            e.printStackTrace();
        }

        return tasks;
    }

    public void toggleTaskDone(int id)
    {
        Connection        conn = null;
        PreparedStatement pst  = null;
        try
        {
            conn = connect();

            String query = "SELECT * FROM tasks WHERE id = ?";
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet task       = pst.executeQuery();
            task.next();
            boolean   doneStatus = task.getBoolean("done");
            
            doneStatus = !doneStatus;
            pst.close();

            query = "UPDATE tasks SET done = ? WHERE id = ?";
            pst   = conn.prepareStatement(query);
            pst.setBoolean(1, doneStatus);
            pst.setInt(2, id);

            pst.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println("[ class DAO - toggleTaskDone() exception ]");
            e.printStackTrace();
        }
        finally
        {
            if (pst != null)
            {
                try
                {
                    pst.close();
                }
                catch (SQLException e)
                {
                    System.out.println("[ toggleTaskDone: Error closing PreparedStatement ]");
                    e.printStackTrace();
                }
            }
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                {
                    System.out.println("[ toggleTaskDone: Error closing connection ]");
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteTask(int id)
    {
        final String QUERY = "DELETE FROM tasks WHERE id = ?";
        try (
                Connection conn = connect();
                PreparedStatement pst = conn.prepareStatement(QUERY)
                )
        {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println("[ class DAO - deleteTask() exception ]");
            e.printStackTrace();
        }
    }
}
