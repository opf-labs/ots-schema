package edu.harvard.hul.ois.ots.schemas.AES;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringWriter;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.XMLUnit;



public class AESReadTest extends XMLTestCase {
    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new FileReader("test_xml/aes_sample.xml"));
        xmlr.nextTag();
        AudioObject aes = new AudioObject(xmlr,true);  
        aes.getFormat();
    }
    
    public void testReadWrite () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new FileReader("test_xml/aes_sample.xml"));
        xmlr.nextTag();
        AudioObject aes = new AudioObject(xmlr,false);  
        
		XMLOutputFactory f = XMLOutputFactory.newInstance();
		StringWriter stringWriter = new StringWriter();
		
		aes.getNamespaceSchemaContext().setPrefix(null);
		
		XMLStreamWriter sw = f.createXMLStreamWriter(stringWriter); 
		aes.output(sw);
		sw.close();
	    String newXml = stringWriter.toString();
	    stringWriter.close();
       
	    System.out.println(newXml);
	    
	    XMLUnit.setIgnoreWhitespace(true);
	    String originalFile = readFileAsString("test_xml/aes_sample.xml");
	    System.out.println(originalFile);
        assertXMLEqual(originalFile,newXml);
      }  
    
    
    private static String readFileAsString(String filePath) throws java.io.IOException{
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }
}
