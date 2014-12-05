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

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


import edu.harvard.hul.ois.ots.schemas.XmlContent.AnonymousElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

/** The Extension class allows unspecified additional information
 *  to be included in MIX. We just note its existence. */
public class Extension extends AnonymousElement {

    /**  Constructor from XML
     */
    public Extension (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        super(reader);
        name = "Extension";
    }
    
    /** No-argument constructor.  */
    public Extension () {
        super();
        name = "Extension";
    }

    /** This always returns true */
    @Override
    public boolean validate() {
        return true;
    }



}
