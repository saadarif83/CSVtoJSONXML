/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSVtoJSONXML;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Saad
 */
public class XMLWriter implements Constants{
    private Logger log;
    
    public XMLWriter(Logger log) {        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(XML_FILENAME))) {
            this.setLog(log);
            this.getLog().log(Level.INFO, "XML File Created");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void xmlBodyAppend(Parser parser) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(XML_FILENAME, true))) {
            List<String> header = parser.getHeader();
            List<String> element = parser.getElement();
            
            String xmlContent = genXmlObject(header, element);
            
            bw.write(xmlContent);
            this.getLog().log(Level.INFO, "Object written to XML file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String genXmlObject(List<String> header, List<String> element) {
        String xmlContent = "<object>\n";
        String tempKey = "";
        String tempValue = "";
        for (int i = 0; i < header.size(); i++) {
            tempKey = header.get(i);
            tempKey = tempKey.replaceAll("[^a-zA-Z0-9]", "");
            tempValue = element.get(i);
            xmlContent = xmlContent + "   <" + tempKey + ">"; //open tag
            xmlContent = xmlContent + tempValue;
            xmlContent = xmlContent + "</" + tempKey + ">\n"; //close tag
        }
        xmlContent = xmlContent + "</object>\n";
        return xmlContent;
    }
    public Logger getLog() {
        return log;
    }

    public void setLog(Logger log) {
        this.log = log;
    }    
     
}
