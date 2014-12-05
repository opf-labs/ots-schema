/**********************************************************************
 * Copyright (c) 2010 by the President and Fellows of Harvard College
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
 **********************************************************************/

package edu.harvard.hul.ois.ots.schemas.AES;


import java.util.Iterator;

import javax.xml.stream.XMLStreamException;

import edu.harvard.hul.ois.ots.schemas.XmlContent.GenericElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;

import org.codehaus.staxmate.out.SMOutputElement;

/** All AES elements subclass AESObject. An AESObject represents an
 *  objectType element, and has an ID attribute. */
public abstract class AESObject extends GenericElement {

    public static final String XMLNS = "http://www.aes.org/audioObject";
    public static final String SCHEMA_PREFIX = "aes";
    
    /** Public schema location. */
    public static final String SCHEMA_LOCATION = 
        "http://www.aes.org/standards/schemas/aes57-2010-11-09.xsd";
    
    /** Values for AES60 */
    public static final String AES60NS = "urn:ebu:metadata-schema:ebuCore_2010";
    public static final String AES60_PREFIX = "aes60";
    public static final String AES60_SCHEMA_LOCATION =
        "http://www.aes.org/standards/schemas/aes60-2010.xsd";
    
    /* A static NamespaceSchemaContext that all methods can return  */
    protected static final NamespaceSchemaContext aesNSC = 
        new NamespaceSchemaContext (XMLNS, SCHEMA_PREFIX, SCHEMA_LOCATION);



    /** Returns the namespace schema context for this element. 
     *  Must be overridden unless the namespace context is null. */
    @Override
    public NamespaceSchemaContext getNamespaceSchemaContext() {
        return aesNSC;
    }
    
    /** Set the ID value */
    public void setID(String id) {
        setAttribute ("ID", id);
    }
    
    /** Get the ID value */
    public String getID () {
        return getAttribute ("ID");
    }
    
    /** Write out attributes. This needs to be called by each element's
     *  output method if they aren't otherwise output. 
     *  If GenericElement.outputElement is called,
     *  that outputs attributes, so this shouldn't be called. */
    public void outputAttrs (SMOutputElement el) throws XMLStreamException {
        Iterator<String> attrIter = attributes.keySet().iterator();
        while (attrIter.hasNext ()) {
            String key = attrIter.next ();
            String value = attributes.get (key);
            el.addAttribute (key, value);
        }   
    }
    

    /** A generally useful method to remove the prefix from a name */
    public static String stripPrefix (String name) {
        int n = name.indexOf (':');
        if (n < 0) {
            return name;      // No prefix
        }
        else {
            return name.substring (n + 1);
        }
    }
    
    /** Compare two names minus prefixes */
    public static boolean baseNamesEqual (String name1, String name2) {
        return stripPrefix(name1).equals (stripPrefix (name2));
    }
}
