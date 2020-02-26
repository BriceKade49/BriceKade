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
import org.eclipse.jetty.util.log.AbstractLogger;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.StdErrLog;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Brice
 */
public class SignUpNGTest {
    
    public SignUpNGTest() {
    }

static CookieManager cookieManager = new CookieManager();

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

@BeforeClass
public static void setupSession(){
    CookieHandler.setDefault(cookieManager);
}

    @BeforeMethod
    public void clearCookies() {
        cookieManager.getCookieStore().removeAll();
    }
    /**
     * Test of doGet method, of class SignUp.
     * @throws java.lang.Exception
     */
    
    @Test
    public void testDoGet1() throws Exception {
        String txt = utils.fetch( "/srv/signup?username=bob" ) ;
        assertTrue( txt.contains("No username or password provided"));
    }
    
    
    @Test
    public void testDoGet2() throws Exception {
        String txt = utils.fetch( "/srv/signup?password=bob321" ) ;
        assertTrue( txt.contains("No username or password provided"));
    }
    
    @Test
    public void testDoGet3() throws Exception {
        String txt = utils.fetch( "/srv/signup" ) ;
        assertTrue( txt.contains("No username or password provided"));
    }
    
    @Test
    public void testDoGet4() throws Exception {
        String txt = utils.fetch( "/srv/signup?username=bob&password=123" ) ;
        assertTrue( txt.contains("Logged in as bob"));
    }
}
