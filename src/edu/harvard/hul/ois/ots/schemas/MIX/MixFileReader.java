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

package edu.harvard.hul.ois.ots.schemas.MIX;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

/** This class implements a simple command-line application to read a
 *  MIX file and say if it's OK or not. */
public class MixFileReader {

    /**
     * This takes a file and reads it, and reports if there's a problem.
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        if (args.length < 1) {
            System.out.println ("Usage: java MixFileReader <filepath>");
            return;
        }
        FileReader rdr = null;
        XMLStreamReader xmlr = null;
        boolean fileOK = true;
        String path = args[0];
        try {
            rdr = new FileReader (path);
        }
        catch (FileNotFoundException e) {
            System.out.println ("File not found: " + path);
            return;
        }
        try {
            XMLInputFactory xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(rdr);
            //move to root tag
            xmlr.nextTag();
            new Mix (xmlr);
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
        }
    }

}
