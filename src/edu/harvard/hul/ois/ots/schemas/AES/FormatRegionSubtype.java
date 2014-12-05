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


import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

/** This is an abstract superclass for all the classes that implement
 *  instantiations of the abstract XML type baseFormatRegionType.
 *  
 *  It works with the FormatRegion class. A FormatRegion contains
 *  a FormatRegionSubtype object, which is returned by 
 *  FormatRegion.getContent(). A FormatRegionSubtype object is
 *  created when the FormatRegion constructor with an XMLStreamReader
 *  parses the formatRegion element.
 */
public abstract class FormatRegionSubtype extends AESObject
             implements SchemaSubtype {
	
	private final static String ELEMENTNAME = "formatRegion";
	
	public FormatRegionSubtype() {
		this.name = ELEMENTNAME;
	}

    /** This restriction set is used for the grooveOrientation element
     *  in a couple of subclasses */
    protected final static String[] VALUES_GRVORIENT =
    { "LATERAL", "VERTICAL", "STEREO" };

    /** This restriction set is used for the grooveCreationMethod element
     *  in a couple of subclasses */
    protected final static String[] VALUES_GRVCREATION =
    { "DIRECT_CUT", "PRESS_MOULDED" };

    /** Return the label attribute */
    public String getLabel () {
        return getAttribute ("label");
    }
    
    /** Set the label attribute.  */
    public void setLabel (String t) {
        setAttribute ("label", t);
    }

    /** Return the ownerRef attribute */
    public String getOwnerRef () {
        return getAttribute ("ownerRef");
    }
    
    /** Set the ownerRef attribute.  */
    public void setOwnerRef (String t) {
        setAttribute ("ownerRef", t);
    }
    
    /** Acquire attributes from the owning FormatRegion. This assigns the same
     *  element, so be careful about modifying it. */
    public void getAttributesFrom (FormatRegion fr) {
        attributes = fr.getAttributes();
    }


    protected abstract void dispatch(String localName, XMLStreamReader reader)
           throws XMLStreamException, XmlContentException;
    
}
