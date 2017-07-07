/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSVtoJSONXML;

import CSVtoJSONXML.Parser;
import java.util.List;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Saad
 */
public class ParserTest {
    
    public ParserTest() {
        
    }
    
    @Test
    public void testParseLine() {
        String line = "1,Hydrogen,H,1.00794";        
        List<String> returnedString = Parser.parseLine(line);
        assertThat(returnedString, IsNull.notNullValue());
        assertEquals(returnedString.size(), 4);
        assertEquals(returnedString.get(1), "Hydrogen");       
    }
    
    @Test
    public void testCommaInQuotesInCSV() {
        String line = "2334,11,\"Gahn, Scheele\",1774,0.479";
        List<String> returnedString = Parser.parseLine(line);
        assertThat(returnedString, IsNull.notNullValue());
        assertEquals(returnedString.size(), 5);
        assertEquals(returnedString.get(2), "Gahn, Scheele");       
    }
}
