
import java.net.URL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Brice
 */
public class utils 
{
    static String fetch(String... allurls) throws Exception
    {
        String str=null;
        byte[] returnedData=new byte[]{0};  //dummy
        for(String oneurl: allurls )
        {
        var url = new URL("http://localhost:2020"+oneurl);
        var conn = url.openConnection();
        conn.connect();
        var istr = conn.getInputStream();
        returnedData = istr.readAllBytes();
        }
        return new String(returnedData,0,returnedData.length);
    }
}