package controller;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.DAO;

@WebServlet("/delete")
public class Delete extends HttpServlet
{
    private static final long serialVersionUID = 8044557060108095216L;
    
    private DAO dao = new DAO();
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        String id = req.getParameter("id");
        
        if (id != null)
        {
            dao.deleteTask(Integer.parseInt(id));
        }
        
        res.sendRedirect(req.getContextPath() + "/");
    }
}
