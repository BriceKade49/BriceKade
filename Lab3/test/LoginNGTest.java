/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.eclipse.jetty.util.log.AbstractLogger;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.StdErrLog;
/**
 *
 * @author Brice
 */
public class LoginNGTest {
    
    public LoginNGTest() {
    }
 static CookieManager cookieManager = new CookieManager();
 
@BeforeClass
public static void setupSession(){
    CookieHandler.setDefault(cookieManager);
}
@BeforeClass   
public static void startJetty() throws Exception {
    String[] args = new String[]{
        "jetty.home=../jetty",
        "STOP.PORT=2021", "STOP.KEY=AutomaticTofu"
    };
    var LG = new StdErrLog();
    LG.setLevel(AbstractLogger.LEVEL_OFF);
    Log.setLog(LG);
    org.eclipse.jetty.start.Main.main(args);
    CookieHandler.setDefault(cookieManager);
}
    /**
     * Test of doGet method, of class Login.
     * @throws java.lang.Exception
     */
    //http://localhost:2020/srv/login?username=bob&password=123
 
@Test
public void testLogin1() throws Exception
{
    String txt = utils.fetch( "/srv/signup?username=bob&password=123" ) ;
    assertTrue( txt.contains("Logged in as bob"));
    txt = utils.fetch( "/srv/login?username=bob&password=123" ) ;
    assertTrue( txt.contains("Logged in as: bob"));
}
@Test
public void testLogin2() throws Exception{
    String txt = utils.fetch( "/srv/signup?username=bob&password=123" ) ;
    assertTrue( txt.contains("Logged in as bob"));
    txt = utils.fetch( "/srv/login?username=bob&password=321" ) ;
    assertTrue( txt.contains("Incorrect username/password\n"));
}
@Test
public void testLogin3() throws Exception{
    String txt = utils.fetch( "/srv/signup?username=bob&password=123" ) ;
    assertTrue( txt.contains("Logged in as bob"));
    txt = utils.fetch( "/srv/login?username=bob" ) ;
    assertTrue( txt.contains("Incorrect username/password\n"));
}
    
@Test
public void testLogin4() throws Exception{
    String txt = utils.fetch( "/srv/signup?username=bob&password=123" ) ;
    assertTrue( txt.contains("Logged in as bob"));
    txt = utils.fetch( "/srv/login?username=&password=123" ) ;
    assertTrue( txt.contains("Incorrect username/password\n"));
}
@Test
public void testLogin5() throws Exception{
    String txt = utils.fetch( "/srv/signup?username=bob&password=123" ) ;
    assertTrue( txt.contains("Logged in as bob"));
    txt = utils.fetch( "/srv/login?username=&password=" ) ;
    assertTrue( txt.contains("Incorrect username/password\n"));
}
@Test
public void testLogin6() throws Exception{
    String txt = utils.fetch( "/srv/signup?username=bob&password=123" ) ;
    assertTrue( txt.contains("Logged in as bob"));
    txt = utils.fetch( "/srv/login?username=bbo&password=123" ) ;
    assertTrue( txt.contains("Incorrect username/password\n"));
}
/*
    @Test
    public void test(){
        
    }
*/
}
