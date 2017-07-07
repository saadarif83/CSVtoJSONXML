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
public class JSONWriter implements Constants{
    private static boolean isObjectWritten;
    private Logger log;
    
    public JSONWriter(Logger log) {
        this.setIsObjectWritten(false);
        this.setLog(log);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(JSON_FILENAME))) {
            this.getLog().log(Level.INFO, "JSON File Created");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void jsonBodyAppend(Parser parser) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(JSON_FILENAME, true))) {
            List<String> header = parser.getHeader();
            List<String> element = parser.getElement();
            
            String jsonContent = genJsonObject(header, element);
            
            bw.write(jsonContent);
            this.getLog().log(Level.INFO, "Object written to JSON file.");
            this.setIsObjectWritten(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String genJsonObject(List<String> header, List<String> element) {        
        String jsonContent = "";
        if (getIsObjectWritten()) {
            jsonContent = ",\n{\n";
        } else {
            jsonContent = "{\n";
        }
        String tempKey = "";
        String tempValue = "";
        for (int i = 0; i < header.size(); i++) {
            tempKey = header.get(i);
            tempValue = element.get(i);
            jsonContent = jsonContent + "\"" + tempKey + "\":";
            if (tempValue.isEmpty()) {
                jsonContent += '\n';
            } else {

                if (isNumeric(tempValue)) {
                    jsonContent = jsonContent + tempValue + '\n';
                } else {
                    jsonContent = jsonContent + "\"" + tempValue + "\"\n";
                }
            }
        }
        jsonContent = jsonContent + "}";
        return jsonContent;
    }
    
    public static boolean isNumeric(String string) {
        return string.matches("-?\\d+(\\.\\d+)?");
    }
    
    public static boolean getIsObjectWritten() {
        return isObjectWritten;
    }

    public void setIsObjectWritten(boolean isObjectWritten) {
        this.isObjectWritten = isObjectWritten;
    }

    public Logger getLog() {
        return log;
    }

    public void setLog(Logger log) {
        this.log = log;
    }   
    
}
