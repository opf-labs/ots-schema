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

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


/** This is an abstract superclass for all the classes that implement
 *  instantiations of the abstract XML type baseDimensionsType.
 */
public abstract class DimensionsSubtype extends AESObject 
           implements SchemaSubtype  {

    private final static String ATT_DESCRIPTION = "description";

    protected abstract void dispatch(String localName, XMLStreamReader reader)
              throws XMLStreamException, XmlContentException;

    /** Return the description attribute, which is common to all subtypes */
    public String getDescription() {
        return getAttribute(ATT_DESCRIPTION);
    }

    /** Set the description attribute, which is common to all subtypes */
    public void setDescription(String t) {
        setAttribute(ATT_DESCRIPTION, t);
    }

    /** Acquire attributes from the owning FormatRegion. This assigns the same
     *  element, so be careful about modifying it. */
    public void getAttributesFrom (Dimensions d) {
        attributes = d.getAttributes();
    }


}
