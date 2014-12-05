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
package edu.harvard.hul.ois.ots.schemas.ModsMD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import edu.harvard.hul.ois.ots.schemas.XmlContent.GenericElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

/** This class implements a simple command-line application to read a
 *  Mods file and say if it's OK or not. */
public class ModsFileReader {

    /**
     * This takes a file and reads it, and reports if there's a problem.
     * @param args
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println ("Usage: java ModsFileReader <filepath>");
            return;
        }
        InputStreamReader rdr = null;
        Mods mods = null;
        XMLStreamReader xmlr = null;
        boolean fileOK = true;
        String path = args[0];
        try {
        	rdr = new InputStreamReader (new FileInputStream (path), "UTF-8");
        }
        catch (FileNotFoundException e) {
            System.out.println ("File not found: " + path);
            return;
        }
        catch (UnsupportedEncodingException e) {
            System.out.println ("This shouldn't happen");
            return;
        }
        try {
            XMLInputFactory xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(rdr);
            //move to root tag
            xmlr.nextTag();
            mods = new Mods (xmlr);
        }
        catch (XMLStreamException e) {
            System.out.println ("XMLStreamException");
            String msg = e.getMessage ();
            if (msg != null) {
                System.out.println (msg);
                fileOK = false;
            }
        }
        catch (XmlContentException e) {
            System.out.println ("Error reading file " + path);
            System.out.println (e.getMessage ());
            e.printStackTrace ();
            fileOK = false;
        }
        try {
            xmlr.close ();
        } catch (XMLStreamException e) {
            System.out.println ("Error closing file " + path);
            e.printStackTrace();
        }
        if (fileOK) {
            System.out.println ("File " + path + " is OK."); 
            ModsFileReader mfr = new ModsFileReader ();
            mfr.reportContent (mods);
        }
    }
    
    /** Put out some information about the file */
    private void reportContent (Mods mods) {
        System.out.println (mods.getName ());
        Map<String, String> attrs = mods.getAttributes ();
        Iterator<String> attrIter = attrs.keySet().iterator();
        while (attrIter.hasNext ()) {
            String key = attrIter.next ();
            System.out.println (" " + key + "=" + attrs.get (key));
        }
        reportContentLevel (mods, 1);
    }
    
    private void reportContentLevel (ModsElement elem, int level) {
        StringBuffer indentStrBuf = new StringBuffer ();
        for (int i = 0; i < level; i++) {
            indentStrBuf.append ('\t');
        }
        String indentStr = indentStrBuf.toString ();
        List<GenericElement> elements = elem.getAllElements ();
        for (GenericElement subElem : elements) {
            System.out.println (indentStr + subElem.getName ());
            Map<String, String> attrs = subElem.getAttributes ();
            Iterator<String> attrIter = attrs.keySet().iterator();
            while (attrIter.hasNext ()) {
                String key = attrIter.next ();
                System.out.println (indentStr + " " + key + "=" + attrs.get (key));
            }
            if (subElem instanceof ModsElement) {
                reportContentLevel ((ModsElement) subElem, level + 1);
            }
        }
    }

}
