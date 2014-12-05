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

import java.util.List;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class HoldingSimple extends ModsElement {

    private final static String ELEM_COPYINFO = "copyInformation";

    /**  Constructor for this element
     */
    public HoldingSimple (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        name = "holdingSimple";
        defineRestrictions();
        parse (reader);
    }
    
    /** No-argument constructor */
    public HoldingSimple () {
        super();
        name = "holdingSimple";
        initAllElements();
        defineRestrictions();
    }
    
    /** Define restrictions */
    private void defineRestrictions () {
    }

    /** Get List of copyInformation objects */
    @SuppressWarnings("unchecked")
	public List<CopyInformation> getCopyInformations () {
        return (List<CopyInformation>) genericList(getList (ELEM_COPYINFO));
    }
    
    /** Add a copyInformation object 
     * @throws XmlContentException
     */
    public void addCopyInformation (CopyInformation x) throws XmlContentException {
        addToList(x, ELEM_COPYINFO);
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    protected void dispatch (String localName, XMLStreamReader reader) 
    throws XMLStreamException, XmlContentException {
        if (matchList (reader, this, 
             localName, ELEM_COPYINFO,
             StringElement.class)) {
            return;
        }
        throw new XmlContentException ("Unknown element name " + localName);
    }
}
