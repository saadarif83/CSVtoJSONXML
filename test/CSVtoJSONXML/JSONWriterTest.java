/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSVtoJSONXML;

import CSVtoJSONXML.JSONWriter;
import java.util.List;
import java.util.Arrays;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Saad
 */
public class JSONWriterTest {
    
    public JSONWriterTest() {
    }

    @Test
    public void testGenJSONObject() {
        List<String> header = Arrays.asList("Atomic Number","Element",
                "Symbol","Atomic Weight","Period");
        List<String> element = Arrays.asList("105","Dubnium","Db","262","7");
        
        String returnedString = JSONWriter.genJsonObject(header, element);
        assertThat(returnedString, IsNull.notNullValue());        
    }
    
}
