/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSVtoJSONXML;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Saad
 */
public class Parser implements Constants{    
    
    List<String> header;
    List<String> element;    
    JSONWriter json;
    XMLWriter xml;

    private static final Logger LOG = Logger.getLogger(Parser.class.getName());

    public Parser() throws Exception{
        json = new JSONWriter(LOG);
        xml = new XMLWriter(LOG);
        parseCSVFile();        
    }
        
    public void parseCSVFile() throws Exception{
        
        Scanner sc = new Scanner(new File(INPUT_FILENAME)).useDelimiter(SPLITTER);        
        
        if(sc.hasNext()) {
            this.setHeader(parseLine(sc.nextLine()));
            LOG.log(Level.INFO, "Read CSV file headers");
        }
                   
        while (sc.hasNext()) {
            this.setElement(parseLine(sc.nextLine()));
            LOG.log(Level.INFO, "Object Read: " + this.getHeader().get(0) 
                    + ": " +  this.getElement().get(0) + ", " 
                    + this.getHeader().get(1) + ": " 
                    + this.getElement().get(1));
            json.jsonBodyAppend(this);  
            xml.xmlBodyAppend(this);
        }
        
        sc.close();        
    }
        
    public static List<String> parseLine(String line) {
        List<String> data = new ArrayList<>();
        String currentItem = "";
        String nextItem = "";
        
        Scanner sc = new Scanner(line).useDelimiter(SPLITTER);
        while (sc.hasNext()) {
            currentItem = sc.next();
            if(currentItem.contains(QUOTES)) {                
                while(sc.hasNext()) {
                    String newItem = sc.next();                                           
                    currentItem = currentItem + "," + newItem;
                    if(newItem.contains(QUOTES)) {
                        currentItem = currentItem.replace("\"", "");
                        break;                    
                    }
                }
            }                
            data.add(currentItem);
        }        
        return data;
    }     
    
    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }    
    
    public List<String> getElement() {
        return element;
    }

    public void setElement(List<String> element) {
        this.element = element;
    }
   
}
