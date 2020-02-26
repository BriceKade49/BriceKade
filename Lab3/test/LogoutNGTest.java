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
public class LogoutNGTest {
    
    public LogoutNGTest() {
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
String fetch(String... allurls) throws Exception{
    String str=null;
    byte[] returnedData=new byte[]{0};  //dummy
    for(String oneurl: allurls ){
        var url = new URL("http://localhost:2020"+oneurl);
        var conn = url.openConnection();
        conn.connect();
        var istr = conn.getInputStream();
        returnedData = istr.readAllBytes();
    }
    return new String(returnedData,0,returnedData.length);
}
    @AfterClass
public static void stopJetty() throws Exception {
    String[] args = new String[]{ "jetty.home=../jetty",
        "STOP.PORT=2021", "STOP.KEY=AutomaticTofu",
        "--stop"
    };
    org.eclipse.jetty.start.Main.main(args);
}

    @BeforeMethod
    public void clearCookies() {
        cookieManager.getCookieStore().removeAll();
    }
    
@Test
public void testLogout1() throws Exception
{
    String txt = utils.fetch( "/srv/signup?username=bob&password=123" ) ;
    assertTrue( txt.contains("Logged in as bob"));
    txt = utils.fetch( "/srv/login?username=bob&password=123" ) ;
    assertTrue( txt.contains("Logged in as: bob"));
    txt = utils.fetch( "/srv/logout" ) ;
    assertTrue( txt.contains("Logged out"));
}
}