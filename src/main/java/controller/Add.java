package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

@WebServlet("/add")
public class Add extends HttpServlet
{
    private static final long serialVersionUID = -2572861126256427412L;
    
    private DAO       dao = new DAO();
    private JavaBeans task;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
    {
//        res.getWriter().append("Hello, mandioca");
//        dao.testConnection();

        RequestDispatcher rd = req.getRequestDispatcher("add.html");
        rd.forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        String  title     = req.getParameter("title");
        String  _checkbox = req.getParameter("isDone");
        boolean isDone    = _checkbox != null && _checkbox.equals("on");

        if (checkEmpty(title))
        {
            res.sendRedirect("add?error=empty_title");
        }
        else
        {

            task = new JavaBeans(title, isDone);
    
            dao.create(task);
    
            res.sendRedirect(req.getContextPath() + "/");
        }
    }

    private boolean checkEmpty(String title)
    {
        return title != null && title.trim().isEmpty();
    }
}
