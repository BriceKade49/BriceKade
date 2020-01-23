import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(urlPatterns={"/login"})
public class Login extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setContentType("text/plain");
        var pw = resp.getWriter();
        var username = req.getParameter("username");
        var password = req.getParameter("password");
        if( username == null || password == null)
        {
            pw.printf("No username provided");
        } 
        else 
        {
            var sess = req.getSession();
            boolean status = false;
            //login checks dbuser
            for(int i= 0;SignUp.ArrayUsers.size()>i;i++){
                if(username.equals(SignUp.ArrayUsers.get(i).un)&&password.equals(SignUp.ArrayUsers.get(i).pw)){
                    status = true;
                    sess.setAttribute("user", username);
                }
            }
            System.out.print(status+"\n");
            if(status){
        
                pw.printf("Logged in as: " + username +"\n");
            }
            else{
                pw.printf("Incorrect username/password\n");
            }
            //signup adds to dbuser
        }
    }

}
