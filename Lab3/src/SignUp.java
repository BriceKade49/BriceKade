/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
//import dbuser.java;
/**
 *
 * @author Brice
 */
@WebServlet(urlPatterns={"/signup"})
public class SignUp extends HttpServlet
{
    static ArrayList<dbuser> ArrayUsers = new ArrayList<dbuser>();
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setContentType("text/plain");//resp is like a response, req is like a request
        var pw = resp.getWriter();
        var username = req.getParameter("username");
        var password = req.getParameter("password");
        if( username == null || password == null)
        {
            pw.printf("No username or password provided");
        } 
        else 
        {
            var sess = req.getSession();
            sess.setAttribute("username", username );
            pw.printf("Logged in as "+ username);
            
            sess.setAttribute("password", password );
            pw.printf("Your password "+ password);
            ArrayUsers.add(new dbuser(username,password));
        }
       
    }

}
