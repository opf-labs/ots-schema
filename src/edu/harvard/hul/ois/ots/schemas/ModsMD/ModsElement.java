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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.stream.XMLStreamException;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.GenericElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;
import edu.harvard.hul.ois.ots.schemas.XmlContent.Rational;
import edu.harvard.hul.ois.ots.schemas.XmlContent.RationalElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.RealElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

/** All MODS elements subclass this class. It adds code to preserve
 *  the order of elements. */
public abstract class ModsElement extends GenericElement {

    public static final String XMLNS = "http://www.loc.gov/mods/v3";
    public static final String SCHEMA_PREFIX = "mods";
    public static final String SCHEMA_LOCATION = "http://www.loc.gov/standards/mods/v3/mods-3-3.xsd";    

    protected static final NamespaceSchemaContext modsNSC = 
        new NamespaceSchemaContext (XMLNS, SCHEMA_PREFIX, SCHEMA_LOCATION);

    
    protected List<GenericElement> allElements;

    /** Superclass constructor. Sets up allElements. If the 
     *  superclass constructor isn't invoked, then the constructor
     *  needs call initAllElements. */
    public ModsElement () {
        super ();
        initAllElements();
    }
    
    /** Initialize the allElements list. This needs to be called by
     *  any constructor that doesn't invoke ModsElement(). */
    protected void initAllElements () {
        allElements = new ArrayList<GenericElement> ();
    }
    
    /** This method returns all the elements under a given element,
     *  in the order in which they were parsed or added. */
    public List<GenericElement> getAllElements () {
        return allElements;
    }
    
    private void addToAllElements (GenericElement elem) {
        allElements.add (elem);
    }
    
    /** Add an element to a List. Calling this does restriction checking. 
     * @throws XmlContentException */
    protected void addToList (GenericElement elem, String name) throws XmlContentException {
        super.addToList (elem, name);
        addToAllElements (elem);
    }
    
    /** Set a field from a GenericElement. */
    @Override
    protected GenericElement setField (String name, GenericElement value) throws XmlContentException {
        GenericElement elem = super.setField (name, value);
        addToAllElements (elem);
        return elem;
    }
    
    /** Set a field from a Double. */
    @Override
    protected RealElement setField (String name, Double value) throws XmlContentException {
        RealElement elem = super.setField (name, value);
        addToAllElements (elem);
        return elem;
    }
    
    /** Set a field from a String. */
    @Override
    protected StringElement setField (String name, String value) throws XmlContentException {
        StringElement elem = super.setField (name, value);
        addToAllElements (elem);
        return elem;
    }
    
    
    /** Set a field from a Rational. */
    @Override
    public RationalElement setField (String name, Rational value) throws XmlContentException {
        RationalElement rat = super.setField (name, value);
        addToAllElements(rat);
        return rat;
    }
    
    /** This output method works for all mods elements */
    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        
        //output any attributes
        Iterator<String> attrIter = attributes.keySet().iterator();
        while (attrIter.hasNext ()) {
            String key = attrIter.next ();
            String value = attributes.get (key);
            mainElem.addAttribute (key, value);
        }
        
        List<GenericElement> elements = getAllElements();
        for (GenericElement elem : elements) {
            elem.output (ns, mainElem);
        }
    }
    
    /** Returns a list of all namespace schema contexts in or under
     *  this element. Returns null if not overridden. */
    @Override
    public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts() {
        ArrayList<NamespaceSchemaContext> lst = new ArrayList<NamespaceSchemaContext>(1);
        lst.add (modsNSC);
        return lst;
    }

    /** Returns the namespace schema context for this element. 
     *  Must be overridden unless the namespace context is null. */
    @Override
    public NamespaceSchemaContext getNamespaceSchemaContext() {
        return modsNSC;
    }

}
