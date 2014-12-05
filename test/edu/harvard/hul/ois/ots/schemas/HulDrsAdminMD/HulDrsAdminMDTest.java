/* 
 * Copyright 2010 Harvard University Library
 * 
 * This file is part of OTS-Schemas.
 * 
 * OTS-Schemas is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * OTS-Schemas is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with OTS-Schemas.  If not, see <http://www.gnu.org/licenses/>.
 */

package edu.harvard.hul.ois.ots.schemas.HulDrsAdminMD;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.OutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;
import org.custommonkey.xmlunit.*;

import edu.harvard.hul.ois.ots.schemas.HulDrsAdminMD.AdminFlag.FlagType;
import edu.harvard.hul.ois.ots.schemas.HulDrsAdminMD.DrsFileMD;
import edu.harvard.hul.ois.ots.schemas.HulDrsAdminMD.DrsObjectMD;
import edu.harvard.hul.ois.ots.schemas.HulDrsAdminMD.HulDrsAdminMD.Status;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;


public class HulDrsAdminMDTest  extends XMLTestCase {
    
    public void testFileMDRead () throws Exception {       
        File inputFile = new File("test_xml/hulDrsAdminFile.xml");       
		XMLInputFactory2 xmlif = (XMLInputFactory2)XMLInputFactory2.newInstance();
		
		XMLStreamReader2 xmlr = (XMLStreamReader2) xmlif.createXMLStreamReader(inputFile);
        
		DrsFileMD drsFileMD = new DrsFileMD(xmlr, true);   
		String processing = drsFileMD.getProcessing().get(1);
		assertNotNull(processing);
    }
    
    public void testFileMDReadWrite () throws Exception {
        File inputFile = new File("test_xml/hulDrsAdminFile.xml");       
        String originalFile = readFileAsString("test_xml/hulDrsAdminFile.xml");
		XMLInputFactory2 xmlif = (XMLInputFactory2)XMLInputFactory2.newInstance();
		XMLStreamReader2 xmlr = (XMLStreamReader2) xmlif.createXMLStreamReader(inputFile);
        
		DrsFileMD drsFileMD = new DrsFileMD(xmlr, true);   
		
		XMLOutputFactory f = XMLOutputFactory.newInstance();
		OutputStream out = new ByteArrayOutputStream();
		XMLStreamWriter sw = f.createXMLStreamWriter(out); 
		drsFileMD.output(sw);
		sw.close();
		
		String newXml = out.toString();
		out.close();
		
		System.out.println(newXml);
	    XMLUnit.setIgnoreWhitespace(true);
        assertXMLEqual(originalFile,newXml);
     }  
    

    public void testDeleteFlag() throws Exception {
        File inputFile = new File("test_xml/hulDrsAdminFile.xml");       
		XMLInputFactory2 xmlif = (XMLInputFactory2)XMLInputFactory2.newInstance();
		XMLStreamReader2 xmlr = (XMLStreamReader2) xmlif.createXMLStreamReader(inputFile);
        
		DrsFileMD drsFileMD = new DrsFileMD(xmlr, true);   
		
		AdminFlag flag = drsFileMD.getAdminFlags().get(0);
		flag.delete("junit test");
		
		int corruptedCurrentCnt = 0;
		int corruptedDeletedCnt = 0;
		int incorrectMetadatCnt = 0;
		
		for(AdminFlag f : drsFileMD.getAdminFlags()) {
			if(f.getFlagType() == FlagType.CORRUPTED && f.getStatus() == Status.current) {
				corruptedCurrentCnt++;
			}
			else if(f.getFlagType() == FlagType.CORRUPTED && f.getStatus() == Status.deleted) {
				corruptedDeletedCnt++;
			}
			else if(f.getFlagType() == FlagType.INCORRECT_METADATA) {
				incorrectMetadatCnt++;
			}
		}
		
		assertTrue(corruptedCurrentCnt == 0); //there should be 0 current corrupted flags
		assertTrue(corruptedDeletedCnt == 2); // there should now be 2 corrupted deleted flags
		assertTrue(incorrectMetadatCnt == 1); // incorrect metadata flag remains unchanged
		
     } 
     
    
    public void testAddDuplicateAdminFlag() throws Exception {
        File inputFile = new File("test_xml/hulDrsAdminFile.xml");       
		XMLInputFactory2 xmlif = (XMLInputFactory2)XMLInputFactory2.newInstance();
		XMLStreamReader2 xmlr = (XMLStreamReader2) xmlif.createXMLStreamReader(inputFile);
        
		DrsFileMD drsFileMD = new DrsFileMD(xmlr, true);   
		
		drsFileMD.getAccessFlagObject().getPreviousValues();
		
		try {
			drsFileMD.addAdminFlag(FlagType.INCORRECT_METADATA, "failure test", "junit");
		}
		catch(XmlContentException e) { //exception SHOULD be thrown
			assertTrue(true);
			return;
		}
		assertTrue(false);
     }
    
    public void testObjectMDRead () throws Exception {       
        File inputFile = new File("test_xml/hulDrsAdminObject.xml");       
		XMLInputFactory2 xmlif = (XMLInputFactory2)XMLInputFactory2.newInstance();
		XMLStreamReader2 xmlr = (XMLStreamReader2) xmlif.createXMLStreamReader(inputFile);
        
		DrsObjectMD drsObjMD = new DrsObjectMD(xmlr, true);   
		String capDefault = drsObjMD.getCaptionDefault();
		assertNotNull(capDefault);
		
		assertSame(drsObjMD.getRoles().get(0),DrsObjectMD.Role.LICENSE);
    }
    
    public void testObjectMDReadWrite () throws Exception {
        File inputFile = new File("test_xml/hulDrsAdminObject.xml");       
        String originalFile = readFileAsString("test_xml/hulDrsAdminObject.xml");
		XMLInputFactory2 xmlif = (XMLInputFactory2)XMLInputFactory2.newInstance();
		XMLStreamReader2 xmlr = (XMLStreamReader2) xmlif.createXMLStreamReader(inputFile);
        
		DrsObjectMD drsObjMD = new DrsObjectMD(xmlr, true);  

		XMLOutputFactory f = XMLOutputFactory.newInstance();
		OutputStream out = new ByteArrayOutputStream();
		XMLStreamWriter sw = f.createXMLStreamWriter(out); 
		drsObjMD.output(sw);
		sw.close();
		
		String newXml = out.toString();
		out.close();
		
		System.out.println(newXml);
	    XMLUnit.setIgnoreWhitespace(true);
        assertXMLEqual(originalFile,newXml);
     }  
    
    private static String readFileAsString(String filePath) throws java.io.IOException { 
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
    
    public void testURI () throws Exception {       
        File inputFile = new File("test_xml/hulDrsAdminFile.xml");       
		XMLInputFactory2 xmlif = (XMLInputFactory2)XMLInputFactory2.newInstance();
		
		XMLStreamReader2 xmlr = (XMLStreamReader2) xmlif.createXMLStreamReader(inputFile);
        
		DrsFileMD drsFileMD = new DrsFileMD(xmlr, true);   
		String processing = drsFileMD.getProcessing().get(1);
		assertNotNull(processing);
    }
}
