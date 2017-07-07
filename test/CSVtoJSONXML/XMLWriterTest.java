/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSVtoJSONXML;

import CSVtoJSONXML.XMLWriter;
import java.util.List;
import java.util.Arrays;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Saad
 */
public class XMLWriterTest {
    
    public XMLWriterTest() {
    }

    @Test
    public void testGenXMLObject() {
        List<String> header = Arrays.asList("Atomic Number","Element",
                "Symbol","Atomic Weight","Period");
        List<String> element = Arrays.asList("105","Dubnium","Db","262","7");
        
        String returnedString = XMLWriter.genXmlObject(header, element);
        assertThat(returnedString, IsNull.notNullValue());        
    }
    
}
