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

package edu.harvard.hul.ois.ots.schemas.premis;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.custommonkey.xmlunit.*;

import edu.harvard.hul.ois.ots.schemas.DocumentMD.DocumentMD;
import edu.harvard.hul.ois.ots.schemas.premis.Premis;
import edu.harvard.hul.ois.ots.schemas.premis.PremisParser;
import edu.harvard.hul.ois.ots.schemas.premis.object.ObjectFile;
import edu.harvard.hul.ois.ots.schemas.premis.object.PremisObject;


public class PremisObjectTest  extends XMLTestCase {

	 private String xmlPremisRoot =
		 "<premis:premis version=\"2.0\" xmlns:premis=\"info:lc/xmlns/premis-v2\" " + 
		 "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:docMD=\"http://www.fcla.edu/docmd\" " + 
		 "xsi:schemaLocation=\"info:lc/xmlns/premis-v2 http://www.loc.gov/standards/premis/premis.xsd http://www.fcla.edu/docmd http://www.fcla.edu/dls/md/docmd.xsd\">" +
		 "<premis:object xsi:type=\"premis:file\">" +
		 "	<premis:objectIdentifier>" +
		 "		<premis:objectIdentifierType/>" +
		 "		<premis:objectIdentifierValue/>" +
		 "	</premis:objectIdentifier>" +
		 "	<premis:objectCharacteristics>" +
		 "		<premis:compositionLevel>1</premis:compositionLevel>" +
		 "		<premis:fixity>" +
		 "			<premis:messageDigestAlgorithm>MD5</premis:messageDigestAlgorithm>" +
		 "			<premis:messageDigest>123</premis:messageDigest>" +
		 "			<premis:messageDigestOriginator/>" +
		 "		</premis:fixity>" +
		 "		<premis:size>123</premis:size>" +
		 "		<premis:format>" +
		 "			<premis:formatDesignation>" +
		 "				<premis:formatName/>" +
		 "			</premis:formatDesignation>" +
		 "		</premis:format>" +
		 "		<premis:creatingApplication>" +
		 "			<premis:creatingApplicationName>fred</premis:creatingApplicationName>" +
		 "			<premis:creatingApplicationVersion>2.0</premis:creatingApplicationVersion>" +
		 "			<premis:dateCreatedByApplication>2006-05-04T18:13:51.0Z</premis:dateCreatedByApplication>" +
		 "		</premis:creatingApplication>" +
		 "		<premis:inhibitors>" +
		 "			<premis:inhibitorType>password protection</premis:inhibitorType>" +
		 "			<premis:inhibitorTarget>all content</premis:inhibitorTarget>" +
		 "			<premis:inhibitorKey>fred</premis:inhibitorKey>" +
		 "		</premis:inhibitors>" +
		 "		<premis:objectCharacteristicsExtension>" +
		 "      <docMD:document>" +
		 "        <docMD:PageCount>3</docMD:PageCount>" +
		 "        <docMD:WordCount>34</docMD:WordCount>" +
		 "        <docMD:CharacterCount>4</docMD:CharacterCount>" +
		 "        <docMD:ParagraphCount>4</docMD:ParagraphCount>" +
		 "        <docMD:LineCount>66</docMD:LineCount>" +
		 "        <docMD:TableCount>0</docMD:TableCount>" +
		 "       <docMD:GraphicsCount>4</docMD:GraphicsCount>" +
		 "        <docMD:Language>234</docMD:Language>" +
		 "        <docMD:Language>blah</docMD:Language>" +
		 "        <docMD:Font FontName=\"BookmanOldStyle\" isEmbedded=\"false\"/>" +
		 "       <docMD:Font FontName=\"Arial,Bold\" isEmbedded=\"false\"/>" +
		 "        <docMD:Font FontName=\"TimesNewRoman,Italic\" isEmbedded=\"false\"/>" +
		 "        <docMD:Features>hasLayers</docMD:Features>" +
		 "        <docMD:Features>hasOutline</docMD:Features>" +
		 "      </docMD:document>" +
		 "		</premis:objectCharacteristicsExtension>" +
		 "	</premis:objectCharacteristics>" + 
		 "</premis:object>" +
		 "</premis:premis>";

	 private String xmlObjectRoot =
		 "<premis:object xsi:type=\"premis:file\" xmlns:premis=\"info:lc/xmlns/premis-v2\" " + 
		 "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:docMD=\"http://www.fcla.edu/docmd\" " + 
		 "xsi:schemaLocation=\"info:lc/xmlns/premis-v2 http://www.loc.gov/standards/premis/premis.xsd http://www.fcla.edu/docmd http://www.fcla.edu/dls/md/docmd.xsd\">" +
		 "	<premis:objectIdentifier>" +
		 "		<premis:objectIdentifierType/>" +
		 "		<premis:objectIdentifierValue/>" +
		 "	</premis:objectIdentifier>" +
		 "	<premis:objectCharacteristics>" +
		 "		<premis:compositionLevel>1</premis:compositionLevel>" +
		 "		<premis:fixity>" +
		 "			<premis:messageDigestAlgorithm>MD5</premis:messageDigestAlgorithm>" +
		 "			<premis:messageDigest>123</premis:messageDigest>" +
		 "			<premis:messageDigestOriginator/>" +
		 "		</premis:fixity>" +
		 "		<premis:size>123</premis:size>" +
		 "		<premis:format>" +
		 "			<premis:formatDesignation>" +
		 "				<premis:formatName/>" +
		 "			</premis:formatDesignation>" +
		 "		</premis:format>" +
		 "		<premis:creatingApplication>" +
		 "			<premis:creatingApplicationName>fred</premis:creatingApplicationName>" +
		 "			<premis:creatingApplicationVersion>2.0</premis:creatingApplicationVersion>" +
		 "			<premis:dateCreatedByApplication>2006-05-04T18:13:51.0Z</premis:dateCreatedByApplication>" +
		 "		</premis:creatingApplication>" +
		 "		<premis:inhibitors>" +
		 "			<premis:inhibitorType>password protection</premis:inhibitorType>" +
		 "			<premis:inhibitorTarget>all content</premis:inhibitorTarget>" +
		 "			<premis:inhibitorKey>fred</premis:inhibitorKey>" +
		 "		</premis:inhibitors>" +
		 "		<premis:objectCharacteristicsExtension>" +
		 "      <docMD:document>" +
		 "        <docMD:PageCount>3</docMD:PageCount>" +
		 "        <docMD:WordCount>34</docMD:WordCount>" +
		 "        <docMD:CharacterCount>4</docMD:CharacterCount>" +
		 "        <docMD:ParagraphCount>4</docMD:ParagraphCount>" +
		 "        <docMD:LineCount>66</docMD:LineCount>" +
		 "        <docMD:TableCount>0</docMD:TableCount>" +
		 "       <docMD:GraphicsCount>4</docMD:GraphicsCount>" +
		 "        <docMD:Language>234</docMD:Language>" +
		 "        <docMD:Language>blah</docMD:Language>" +
		 "        <docMD:Font FontName=\"BookmanOldStyle\" isEmbedded=\"false\"/>" +
		 "       <docMD:Font FontName=\"Arial,Bold\" isEmbedded=\"false\"/>" +
		 "        <docMD:Font FontName=\"TimesNewRoman,Italic\" isEmbedded=\"false\"/>" +
		 "        <docMD:Features>hasLayers</docMD:Features>" +
		 "        <docMD:Features>hasOutline</docMD:Features>" +
		 "      </docMD:document>" +
		 "		</premis:objectCharacteristicsExtension>" +
		 "	</premis:objectCharacteristics>" + 
		 "</premis:object>";
    
    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(xmlPremisRoot));
        Premis premis = new Premis(xmlr,true);
        xmlr.close();
        ObjectFile objFile = (ObjectFile) premis.getObjects().get(0);
        String creatingAppName = objFile.getObjectCharacteristics().get(0).getCreatingApplications().get(0).getName();
        assertNotNull (creatingAppName);
        
        xmlr = xmlif.createXMLStreamReader(new StringReader(xmlObjectRoot));
        ObjectFile premisObject = new ObjectFile(xmlr);
        xmlr.close();
        String appName = premisObject.getObjectCharacteristics().get(0).getCreatingApplications().get(0).getName();
        assertNotNull (appName);
    }
    
    public void testReadWrite () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(xmlPremisRoot));
        PremisParser.registerParser("docMD:document",new DocumentMD());
        Premis premis = new Premis(xmlr,true);  
        premis.getNamespaceSchemaContext().setPrefix(null);
		XMLOutputFactory f = XMLOutputFactory.newInstance();
		StringWriter stringWriter = new StringWriter();
		
		premis.getNamespaceSchemaContext().setPrefix("premis");
		
		XMLStreamWriter sw = f.createXMLStreamWriter(stringWriter); 
		premis.output(sw);
		sw.close();
	    String newXml = stringWriter.toString();
	    stringWriter.close();
	           
	    XMLUnit.setIgnoreWhitespace(true);
        assertXMLEqual(xmlPremisRoot,newXml);
    }  
    
	public void testReadExternalFile() throws Exception {	
		// set up a parser
		XMLInputFactory xmlif = XMLInputFactory.newInstance();
		XMLStreamReader xmlr = xmlif.createXMLStreamReader(new FileReader("test_xml/premis_sample.xml"));
		String originalFile = readFileAsString("test_xml/premis_sample.xml");
		
		PremisParser.registerParser("docMD:document",new DocumentMD());
		PremisObject premis = new ObjectFile(xmlr,true);			
		OutputStream out = new ByteArrayOutputStream();
		XMLOutputFactory f = XMLOutputFactory.newInstance();	
		XMLStreamWriter sw = f.createXMLStreamWriter(out);
		premis.output(sw);

		String newXml = out.toString();
				     
		System.out.println(newXml);
		
	    XMLUnit.setIgnoreWhitespace(true);
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
