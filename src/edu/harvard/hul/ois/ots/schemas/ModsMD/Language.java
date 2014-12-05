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


public class Language extends ModsElement {

    private final static String ELEM_LANGTERM = "languageTerm";

    /**  Constructor for this element
     */
    public Language (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        name = "language";
        defineRestrictions();
        parse (reader);
    }
    
    /** No-argument constructor */
    public Language () {
        super();
        name = "language";
        initAllElements();
        defineRestrictions();
    }
    
    /** Define restrictions */
    private void defineRestrictions () {
    }
    
    /** Get List of languageTerm objects, which are StringElements */
    @SuppressWarnings("unchecked")
	public List<StringElement> getLanguageTerms () {
        return (List<StringElement>) genericList(getList (ELEM_LANGTERM));
    }
    
    /** Add a languageTerm object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addLanguageTerm (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_LANGTERM);
        addToList(se, ELEM_LANGTERM );
        return se;
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
             localName, ELEM_LANGTERM,
             StringElement.class)) {
            return;
        }
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
