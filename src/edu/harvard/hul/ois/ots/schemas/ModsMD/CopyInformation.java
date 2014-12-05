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

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

/** CopyInformation is a subelement (in fact, the only subelement)
 *  of holdingSimpleType. It is defined as a sequence, not a repeatable
 *  choice, so its elements are kept in the correct order. 
 */
public class CopyInformation extends ModsElement {

    private final static String ELEM_FORM = "form";
    private final static String ELEM_SUBLOC = "subLocation";
    private final static String ELEM_SHELFLOC = "shelfLocator";
    private final static String ELEM_ELECTROLOC = "electronicLocator";
    private final static String ELEM_NOTE = "note";
    private final static String ELEM_ENUM = "enumerationAndChronology";

    /** Constructor from XML. */
    public CopyInformation(XMLStreamReader reader) 
            throws XMLStreamException, XmlContentException {
        name = "copyInformation";
        initAllElements();
        parse(reader);
    }
    
    /** No-argument constructor. This can be used when the caller is
     *  composing a Mods element from its constituent classes. */
    public CopyInformation () {
        super();
        name = "copyInformation";
    }

    /** Get List of form objects, which are StringElements */
    @SuppressWarnings("unchecked")
	public List<StringElement> getForms () {
        return (List<StringElement>)
        genericList(getList (ELEM_FORM));
    }
    
    /** Add a form object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addForm (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_FORM);
        addToList(se, ELEM_FORM);
        return se;
    }

    /** Get List of subLocation objects, which are StringElements */
    @SuppressWarnings("unchecked")
	public List<StringElement> getSubLocations () {
        return (List<StringElement>)
        genericList(getList (ELEM_SUBLOC));
    }
    
    /** Add a subLocation object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addSubLocation (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_SUBLOC);
        addToList(se, ELEM_SUBLOC);
        return se;
    }
    
    /** Get List of shelfLocator objects, which are StringElements */
    @SuppressWarnings("unchecked")
	public List<StringElement> getShelfLocators () {
        return (List<StringElement>)   genericList(getList (ELEM_SHELFLOC));
    }
    
    /** Add a shelfLocator object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addShelfLocator (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_SHELFLOC);
        addToList(se, ELEM_SHELFLOC);
        return se;
    }
    
    /** Get List of electronicLocator objects, which are StringElements */
    @SuppressWarnings("unchecked")
	public List<StringElement> getElectronicLocators () {
        return (List<StringElement>) genericList(getList (ELEM_ELECTROLOC));
    }
    
    /** Add a electronicLocator object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addElectronicLocator (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_ELECTROLOC);
        addToList(se, ELEM_ELECTROLOC);
        return se;
    }
    
    /** Get List of note objects, which are StringElements */
    @SuppressWarnings("unchecked")
	public List<StringElement> getNotes () {
        return (List<StringElement>) genericList(getList (ELEM_NOTE));
    }
    
    /** Add a note object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addNote (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_NOTE);
        addToList(se, ELEM_NOTE);
        return se;
    }
    
    /** Get List of enumerationAndChronology objects, which are StringElements */
    @SuppressWarnings("unchecked")
	public List<StringElement> getEnumerationsAndChronologies () {
        return (List<StringElement>) genericList(getList (ELEM_ENUM));
    }
    
    /** Add a enumerationAndChronology object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addEnumerationAndChronology (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_ENUM);
        addToList(se, ELEM_ENUM);
        return se;
    }
    


    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    /** Elements of copyInformation are output in the specified order,
     *  since it's defined as a Sequence. */
    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_FORM, mainElem);
        outputList (ns, ELEM_SUBLOC, mainElem);
        outputList (ns, ELEM_SHELFLOC, mainElem);
        outputList (ns, ELEM_ELECTROLOC, mainElem);
        outputList (ns, ELEM_NOTE, mainElem);
        outputList (ns, ELEM_ENUM, mainElem);
    }

    protected void dispatch (String localName, XMLStreamReader reader) 
                 throws XMLStreamException, XmlContentException {
        if (match (reader, this, 
             localName, ELEM_FORM,
             StringElement.class))
            return;
        if (matchList (reader, this, 
             localName, ELEM_SUBLOC,
             StringElement.class))
            return;
        if (matchList (reader, this, 
             localName, ELEM_SHELFLOC,
             StringElement.class))
            return;
        if (matchList (reader, this, 
             localName, ELEM_ELECTROLOC,
             Role.class))
            return;
        if (matchList (reader, this, 
             localName, ELEM_NOTE,
             StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_ENUM,
                StringElement.class))
             return;
        
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
