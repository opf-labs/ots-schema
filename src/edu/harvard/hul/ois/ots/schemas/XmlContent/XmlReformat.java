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
package edu.harvard.hul.ois.ots.schemas.XmlContent;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XmlReformat {
	
	public XmlReformat() { }
	
	public static enum Action {PRETTYPRINT,COMPACT};
	
	public static void main(String args[]) throws TransformerException {
		Source source = new StreamSource("large_pds_compact.xml");
		Result out = new StreamResult("large_pds_pp.xml");
		XmlReformat.convert(source, out, Action.PRETTYPRINT);
	}
	
	public static void convert(Source source, Result out, Action action) throws TransformerException {
		String prettyPrintXslt = "xslt/prettyprint.xslt";
		String compactXslt = "xslt/compactxml.xslt";
		
		long start = System.currentTimeMillis();
		TransformerFactory tFactory = TransformerFactory.newInstance ();
		Templates template = null;
		if(action == Action.PRETTYPRINT) {
			template = tFactory.newTemplates(new StreamSource(prettyPrintXslt));			
		}
		else if(action == Action.COMPACT) {
			template = tFactory.newTemplates(new StreamSource(compactXslt));		
		}
		Transformer transformer = template.newTransformer();

		transformer.transform(source,out);
		long end = System.currentTimeMillis();
		System.out.println("XSLT Took: " + (end - start) + " milliseconds");  
	
	}

}
