package com.qainfotech.tap.training.resourceio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.print.DocFlavor.URL;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class PropertiesOptionsIO{
	 Properties prop = new Properties();
	 FileInputStream input=null;
	 FileOutputStream output;
    public Object getOptionValue(String optionKey) throws IOException {
       // throw new UnsupportedOperationException("Not implemented.");
        
    	ClassLoader classLoader = getClass().getClassLoader();
        java.net.URL resource = classLoader.getResource("options.properties");
        prop.load(new FileReader(new File(resource.getFile())));
        
            //input=new FileInputStream("C:\\Users\\ashikasrivastava\\Downloads\\assignment-resource-io-master\\assignment-resource-io-master\\src\\test\\resources\\options.properties");
           // prop.load(input);
        
        
		return (Object)prop.getProperty(optionKey);
    }

    public void addOption(String optionKey, Object optionValue) throws IOException {
       // throw new UnsupportedOperationException("Not implemented.");
    	
    	 
    	     output=new FileOutputStream("options.properties", true);
    	     prop.store(output, null);
    	     prop.setProperty(optionKey, (String) optionValue);
    	     
    }
}
