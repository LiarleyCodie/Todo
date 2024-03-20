package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.JavaBeans;
import model.DAO;

public class Index extends HttpServlet
{
    private static final long serialVersionUID = 4985700966848025531L;

    private DAO                  dao = new DAO();
    private ArrayList<JavaBeans> tasks;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        tasks = dao.getTasks();

        req.setAttribute("tasks", tasks);
        RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
        rd.forward(req, res);
    }

}
