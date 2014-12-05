/**
 * @author Gary McGath
 *
 * Copyright (c) 
 * 2010 by the President and Fellows of Harvard College
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA.
 *
 * Contact information
 *
 * Office for Information Systems
 * Harvard University Library
 * Harvard University
 * Cambridge, MA  02138
 * (617)495-3724
 * hulois@hulmail.harvard.edu
 */
package edu.harvard.hul.ois.ots.schemas.AES;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

/** The class Layer is used for many different elements.,
 *  so the name must be given to the constructor. */
public class Layer extends AESObject {

    private final static String ELEM_THICKNESS = "thickness";
    private final static String ATT_COMPOSITION = "composition";
    private final static String ATT_ROLE = "role";
    private final static String ATT_ORDER = "order";

    /* Permitted values for attribute role */
    private final static String[] VALUES_ROLE = { "LABEL_LAYER",
       "PROTECTIVE_LAYER",
       "DATA_LAYER",
       "PROTECTIVE_DATA_LAYER",
       "SUPPORT_LAYER" };

    /**  Constructor for this element with just reader
     */
    public Layer (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        parse (reader);
    }

    /**  Constructor for this element
     */
    public Layer (XMLStreamReader reader, String name) 
           throws XMLStreamException, XmlContentException {
        this.name = name;
        parse (reader);
    }
    
    /** Constructor with name only. */
    public Layer (String name) {
        super();
        this.name = name;
//        defineRestrictions();
    }
    
    /** Return the role attribute */
    public String getRole() {
        return getAttribute(ATT_ROLE);
    }

    /** Set the role attribute. Sets it only if the value is allowed. */
    public void setRole(String t) throws XmlContentException {
        boolean ok = false;
        for (String s : VALUES_ROLE) {
            if (s.equals(t)) {
                ok = true;
                break;
            }
        }
        if (ok) {
            setAttribute(ATT_ROLE, t);
        }
        else {
            throw new XmlContentException ("Invalid unit attribute: " + t);
        }
    }

    /** Return the composition attribute */
    public String getComposition() {
        return getAttribute(ATT_COMPOSITION);
    }

    /** Set the composition attribute.  */
    public void setComposition(String t) {
        setAttribute(ATT_COMPOSITION, t);
    }
    
    /** Return the order attribute. If a non-integer value is found,
     *  returns null. */
    public Integer getOrder () {
        try {
            return Integer.parseInt (getAttribute (ATT_ORDER));
        }
        catch (Exception e) {
            // Not an integer value
            return null;
        }
    }
    
    /** Set the order attribute */
    public void setOrder (Integer i) {
        setAttribute (ATT_ORDER, i.toString ());
    }

    public Measurement getThickness () {
        return (Measurement) getField(ELEM_THICKNESS);
    }
    
    public void setThickness (Measurement m) throws XmlContentException {
        setField (ELEM_THICKNESS, m);
    }
    
    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = outputElement (ns, name, parent); 
        outputGenericElement (ns, ELEM_THICKNESS, mainElem);
    }

    protected void dispatch(String localName, XMLStreamReader reader)
    throws XMLStreamException, XmlContentException {
        if (match(reader, this, localName, ELEM_THICKNESS, Measurement.class))
            return;
        throw new XmlContentException("Unknown element name " + localName);
    }

}
