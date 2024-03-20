package controller;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.DAO;

@WebServlet("/check")
public class Check extends HttpServlet
{
    private static final long serialVersionUID = -3397547736669647434L;
    
    private DAO dao = new DAO();
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        var id = req.getParameter("id");
        
        if (id != null)
        {
            dao.toggleTaskDone(Integer.parseInt(id));
        }

        res.sendRedirect(req.getContextPath() + "/");
    }
}
