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
package edu.harvard.hul.ois.ots.schemas.XmlContent;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import static javax.xml.stream.XMLStreamConstants.*;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputContext;
import org.codehaus.staxmate.out.SMOutputElement;
import org.jdom.Document;
import org.jdom.Element;

public abstract class GenericElement implements XmlContent, XmlContentParser {

    protected Map<String, GenericElement> fields;
    protected Map<String, String> attributes;
    protected StringBuffer textBuf;
    protected HashMap<String, Restriction> restrictions;
    protected String name;
    
    
    public GenericElement () {
        attributes = new HashMap<String, String>();
        fields = new HashMap<String, GenericElement> ();
        restrictions = new HashMap<String, Restriction> ();
    }

    /** This parse function can be used directly by most or all 
     *  subclasses. It calls dispatch() when a starting element
     *  is found, and endParse() when a closing element is found.
     *  Subclasses will have to implement dispatch, but only a
     *  few special cases should have to implement endParse.
     */
    @Override
    public XmlContent parse(XMLStreamReader reader)
                throws XMLStreamException, XmlContentException {
        String topName = reader.getLocalName ();
        addAttributes (reader);
        for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {
            switch(event) {
            case START_ELEMENT:
                String localName = reader.getLocalName();
                dispatch (localName, reader);
                break;
            case END_ELEMENT:
                // Caution: This works only if any recursive references
                // to the name are eaten by dispatch. 
                if(reader.getLocalName().equals(topName)) {
                    endParse ();
                    return this;
                }
            }
        }
        return null;
    }
    
    /** A hook for whatever may need to be done with the closing element is encountered.
     *  By default, does nothing. */
    protected void endParse () {
    }
    

    /** This checks if the element being passed (element) matches a specified
     *  element. If not, it returns false and does nothing. Otherwise it creates
     *  a GenericElement of the appropriate type and stores it as a field of the 
     *  parent, with target as its name.
     *  
     *  @param reader       The reader of the XML we're parsing
     *  @param parent       The GenericElement to which this element belongs as a field
     *  @param element      The element name found in the XML
     *  @param target       The name the caller wants to match
     *  @param klasse       The class of the element to be created
     *  
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean match (XMLStreamReader reader, 
            GenericElement parent, 
            String element, 
            String target,
            Class klasse) 
                    throws XMLStreamException, XmlContentException {
        if (target.equals(element)) {
    //        parent.setField (target, new BasicDigitalObjectInformation(reader)); 
            try {
                //Class<GenericElement> cls = 
                //    (Class<GenericElement>) Class.forName (klasse);
                Constructor[] cons = klasse.getConstructors ();
                for (int i = 0; i < cons.length; i++) {
                    Constructor<GenericElement> con = cons[i];
                    Class[] args = con.getParameterTypes ();
                    if (args.length == 1) {
                        Class arg = args[0];
                        String name =arg.getName ();
                        if ("javax.xml.stream.XMLStreamReader".equals (name)) {
                            GenericElement inst = con.newInstance (
                                    new Object[] { reader });
                            inst.name = target;
                            parent.setField (target, inst);
                            return true;
                        }
                    }
                }
            }
            catch (Exception e) {
                if (e instanceof InvocationTargetException ) {
                    Throwable cause = e.getCause ();
                    if (cause instanceof XmlContentException) {
                        throw (XmlContentException) cause;
                    }
                    else 
                        throw new XmlContentException (cause.getMessage (), cause);
                }
                // I'm not sure if an XmlContentException will ever be thrown.
                if (e instanceof XmlContentException) 
                    throw (XmlContentException) e;
                // Turn all other exceptions into an XmlContentException
                else
                    throw new XmlContentException (e);
            }
            return false;
        }
        else {
            return false;
        }
    }

    
    /** This variant of match adds the element to a List rather than setting it
     *  as the field value. The List is the field value.
     *  
     *  @param reader       The reader of the XML we're parsing
     *  @param parent       The GenericElement to which this element belongs as a field
     *  @param element      The element name found in the XML
     *  @param target       The name the caller wants to match
     *  @param klasse       The class of the element to be created
     *  
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean matchList (XMLStreamReader reader, 
            GenericElement parent, 
            String element, 
            String target,
            Class klasse) 
                    throws XMLStreamException, XmlContentException {
        if (target.equals(element)) {
    //        parent.setField (target, new BasicDigitalObjectInformation(reader)); 
            try {
                Constructor[] cons = klasse.getConstructors ();
                for (int i = 0; i < cons.length; i++) {
                    Constructor<GenericElement> con = cons[i];
                    Class[] args = con.getParameterTypes ();
                    if (args.length == 1) {
                        Class arg = args[0];
                        String name =arg.getName ();
                        if ("javax.xml.stream.XMLStreamReader".equals (name)) {
                            GenericElement inst = (GenericElement) con.newInstance (
                                    new Object[] { reader });
                            inst.name = target;
                            parent.addToList(inst, target);
                            return true;
                        }
                    }
                }
            }
            catch (InvocationTargetException e) {
                Throwable t = e.getTargetException();
                throw new XmlContentException (t.getMessage(), e);
            }
            catch (Exception e) {
                return false;
            }
            return false;
        }
        else {
            return false;
        }
    }



    /** GenericElements other than the top-level one don't need to implement output, or
     *  rather they need to implement it relative to a parent rather than a writer,
     *  so this stubs out the requirement. */
    @Override
    public void output(XMLStreamWriter writer) throws XMLStreamException {
    }

    /** This output method needs to be implemented by every class to generate
     *  its piece of the output. */
    public abstract void output (SMNamespace ns, SMOutputElement parent) throws XMLStreamException;
    
    /** Set a field in the object. Subclasses may want to check for
     *  acceptable names.
     *  @param name   Name of the element
     *  @param value  A GenericElement representing the field
     *  @return       The same GenericElement */
    protected GenericElement setField (String name, GenericElement value) throws XmlContentException {
        fields.put (name, value);
        checkRestriction (value, name);
        return value;
    }
    
    /** Set a field from a String. 
    *  @param name   Name of the element
    *  @param value  A String representing the field value
    *  @return       A newly created StringElement */
    protected StringElement setField (String name, String value) throws XmlContentException {
        StringElement s = new StringElement (value, name);
        fields.put (name, s);
        checkRestriction (s, name);
        return s;
    }

    /** Set a field from an Integer. 
    *  @param name   Name of the element
    *  @param value  An Integer representing the field value
    *  @return       A newly created IntegerElement */
    protected IntegerElement setField (String name, Integer value) throws XmlContentException {
        IntegerElement n = new IntegerElement (value, name);
        fields.put (name, n);
        checkRestriction (n, name);
        return n;
    }
    
    /** Set a field from a Double. 
    *  @param name   Name of the element
    *  @param value  A Double representing the field value
    *  @return       A newly created RealElement */
    protected RealElement setField (String name, Double value) throws XmlContentException {
        RealElement r = new RealElement (value, name);
        fields.put (name, r);
        checkRestriction (r, name);
        return r;
    }
    
    /** Add an element to a List. Calling this does restriction checking. 
     * @throws XmlContentException */
    protected void addToList (GenericElement elem, String name) throws XmlContentException {
        List<GenericElement> lst = getList (name);
        checkRestriction (elem, name);
        lst.add (elem);
    }
    
    
    /** Set a field from a Rational. 
    *  @param name   Name of the element
    *  @param value  A Rational representing the field value
    *  @return       A newly created RationalElement */
    public RationalElement setField (String name, Rational value) throws XmlContentException {
        RationalElement rat = new RationalElement (value, name);
        fields.put (name, rat);
        checkRestriction (rat, name);
        return rat;
    }
    
    /** Set a field from a List. While we want to be able to get back
     *  a List, we don't want to let anyone outside of this class create it.
     *  So we make this private, if only as a reminder. */
    /*
    private void setField (String name, List<GenericElement> value) {
        fields.put (name, new ListElement (value, name));
    }*/
    
    /** Define a restriction for this object. 
     *  @param name   name of the element being restricted
     *  @param values an array of primitive data types appropriate to
     *                the element being checked
     */
    public void addRestriction (String name, Object values) {
        restrictions.put (name, new Restriction (name, values));
    }
    
    /** Check if there is a restriction on the value of an element.
     *  Can this just check a restriction map and call checkContent
     *  wherever there is a name to go with. a restriction object? 
     *  Or make it an array of string-object pairs, just to be simple. */
    public void checkRestriction (GenericElement elem, String name) throws XmlContentException {
        Restriction r = restrictions.get (name);
        if (r != null) {
            r.checkRestriction (elem);
        }
    }
    
    /** Check if the restriction of a choice schema element is observed.
     *  There can be no more than one of the named elements. */
    public boolean isChoiceCompliant (String[] names) {
        int cc = 0;
        for (String n : names) {
            if (getField (n) != null)
                ++cc;
        }
        return (cc <= 1);

    }

    /** Returns the name of the element */
    public String getName () {
        return name;
    }

    /** Returns a field value when the element is non-repeatable.
     *  @return a subclass of GenericElement appropriate to the element.   */
    public GenericElement getField (String name) {
        return (GenericElement) fields.get (name);
        //if (val == null)
        //    return null;
        //return val.toValue ();
    }
    
    /** Returns a field element without conversion. */
    public GenericElement getFieldElement (String name) {
        return (GenericElement) fields.get (name);
    }
    
    /** Adds an attribute to the element. If the attribute has a namespace,
     *  the value must be in prefix form, e.g., "xml:lang". */
    public void setAttribute (String name, String value) {
        attributes.put(name, value);
    }
    
    /** Returns an attribute of the element. An exact match is preferred,
     *  but a prefixless name will match any prefixed attribute if there's
     *  no exact match. */
    public String getAttribute (String name) {
        String wildPrefixMatch = null;
        String exactMatch = null;
        for (String key : attributes.keySet()) {
            String unqName;
            int idx = key.indexOf (":");
            if (idx > 0) {
                unqName = key.substring (idx + 1);
            }
            else {
                unqName = key;
            }
            if (name.equals (key)) {
                exactMatch = attributes.get(key);
            }
            if (name.equals (unqName)) {
                wildPrefixMatch = attributes.get(key);
//                return attributes.get (key);
            }
        }
        if (exactMatch != null) {
            return exactMatch;
        }
        else {
            return wildPrefixMatch;
        }
//        return attributes.get (unqName);
    }
    
    /** Returns the attribute map. This is a copy of the internal
     *  attribute map, so changes to it won't affect the element. */
    public Map<String, String> getAttributes () {
        return new HashMap<String, String>(attributes);
    }
    
    /** Adds an attribute to a named child of the element. This is useful
     *  with elements that represent simple data types and aren't
     *  returned to the user as GenericElements. */
    public void setChildAttribute (String child, String name, String value) {
        GenericElement elem = getFieldElement(child);
        elem.setAttribute(name, value);
    }
    
    /** Returns an attribute of a named child of the element. This is useful
     *  with elements that represent simple data types and aren't
     *  returned to the user as GenericElements. */
    public String getChildAttribute (String child, String name) {
        GenericElement elem = getFieldElement (child);
        return elem.getAttribute (name);
    }
    
    /** Converts the element to a value. This is used for elements which
     *  represent simple data types, like Strings or numbers. */
    public Object toValue () {
        return this;
    }
    

    /** Classes must implement this to handle START_ELEMENT cases. The default
     *  just discards the element. 
     * @throws XMLStreamException
     * @throws XmlContentException */
    protected void dispatch (String localName, XMLStreamReader reader) 
                 throws XMLStreamException, XmlContentException {
        // Do nothing
    }
    
    /** Gets a List value for a given field. Creates it if it doesn't
     *  already exist. Once created, the same List is returned
     *  each time it's called for a given element. This is used
     *  for repeatable elements.
     *  
     *  @see getFieldList */
    protected List<GenericElement> getList(String elemName) {
        ListElement lstElem = (ListElement) fields.get (elemName);
        if (lstElem == null) {
            List<GenericElement> lst = new ArrayList<GenericElement> ();
            lstElem = new ListElement (lst, elemName);
            fields.put (elemName, lstElem);
            return lst;
        }
        return lstElem.toList();
    }
    
    /** Reset the list value for a given field to the empty list. */
    protected void resetList (String elemName) {
        List<GenericElement> lst = new ArrayList<GenericElement> ();
        ListElement lstElem = new ListElement (lst, elemName);
        fields.put (elemName, lstElem);
    }

    
    
    /** When the parser encounters a START_ELEMENT, it will call this to
     *  process its attributes.  */
    protected void addAttributes (XMLStreamReader reader) throws IllegalStateException {
        int count = reader.getAttributeCount ();
        for (int i = 0; i < count; i++) {
            QName qn = reader.getAttributeName(i);
            String prefix = qn.getPrefix();
            String localName = qn.getLocalPart();
            String name;
            if (prefix != null && prefix.length() > 0) {
                name = prefix + ":" + localName;
            }
            else {
                name = localName;
            }
            String value = reader.getAttributeValue (i);
            attributes.put (name, value);
        }
    }
    
    /** Output an element using Staxmate, with all attributes. */
    protected SMOutputElement outputElement 
              (SMNamespace ns, String name, SMOutputElement parent) 
              throws XMLStreamException { 
        SMOutputElement elem = parent.addElement (ns, name); 
        for (String key : attributes.keySet ()) {
            elem.addAttribute (null, key, attributes.get (key));
        }
        return elem;
    }
    
    /** Output an element using Staxmate, with all attributes and specified characters */
    protected SMOutputElement outputElement
              (SMNamespace ns, String name, SMOutputElement parent, String characters)
              throws XMLStreamException {
        SMOutputElement elem = outputElement (ns, name, parent);
        elem.addCharacters (characters);
        return elem;
    }
    
    /** Output an AnyXml object using Staxmate 
     * @throws XMLStreamException */
    protected SMOutputElement outputAnyXml (Document doc, SMOutputElement parent) throws XMLStreamException {
        // As far as I can tell, there's no solution but to take the whole
        // JDOM Document and feed it a chunk at a time to StaxMate.
        Element jElem = doc.getRootElement();
        return outputJDOM (jElem, parent);
    }
        
    @SuppressWarnings("rawtypes")
	private SMOutputElement outputJDOM (Element jElem, SMOutputElement parent) 
                      throws XMLStreamException {
        org.jdom.Namespace jns = jElem.getNamespace ();
        SMOutputContext ctx = parent.getContext();
        SMNamespace ns = ctx.getNamespace(jns.getURI(), jns.getPrefix());
        SMOutputElement elem = parent.addElement (ns, jElem.getName());
        Iterator jattIter = jElem.getAttributes().iterator();
        while (jattIter.hasNext ()) {
            org.jdom.Attribute jatt = (org.jdom.Attribute) jattIter.next ();
            String attName = jatt.getName ();
            String attValue = jatt.getValue ();
            org.jdom.Namespace jans = jatt.getNamespace ();
            SMNamespace ans = null;
            if (!org.jdom.Namespace.NO_NAMESPACE.equals (jans)) {
                ans = ctx.getNamespace (jans.getURI (), jans.getPrefix ());
            }
            elem.addAttribute (ans, attName, attValue);
        }
        
        // Two cases here: An element with text (or nothing) and one with
        // subelements. Ignore the messy mixed case.
        List children = jElem.getChildren();
        if (!children.isEmpty()) {
            Iterator iter = children.iterator ();
            while (iter.hasNext ()) {
                Element subElem = (Element) iter.next ();
                outputJDOM (subElem, elem);
            }
        }
        else {
            String text = jElem.getText ();
            elem.addCharacters(text);
        }
        return elem;
    }
    
    /** Output a List of GenericElements. The List may be of a subclass
     *  of GenericElement, so we can't specify the type in the parameters. */
    @SuppressWarnings("rawtypes")
	protected void outputList (SMNamespace ns, 
            String name, 
            SMOutputElement parent) throws XMLStreamException {
        List lst = getList (name);
        if (lst != null) {
            for (Object elem : lst) {
                ((GenericElement)elem).output (ns, parent);
            }
        }
    }
    

    /** Output a GenericElement as part of the output process. */
    protected void outputGenericElement 
            (SMNamespace ns, String name, SMOutputElement parent) 
            throws XMLStreamException {
        GenericElement g = getFieldElement (name);
        if (g != null) {
            g.output (ns, parent);
        }
    }

    /** We want to convert a List<GenericElement> into a List<SpecificElement>.
     *  This function, which is generic-agnostic, allows that. The returned
     *  value can be cast to a List<anything>. If the argument is null, it will
     *  return an empty List.
     *  
     *  It will generate a couple
     *  of compiler warnings about type safety. Ignore them. */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	protected List genericList (List<GenericElement> lst) {
        ArrayList newList = new ArrayList ();
        if (lst == null)
            return newList;
        for (GenericElement elem : lst) {
            newList.add (elem);
        }
        return newList;
    }
    
    /** Returns a list of all namespace schema contexts in or under
     *  this element. Returns null if not overridden. */
	@Override
	public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts() {
		return null;
	}

	/** Returns the namespace schema context for this element. 
	 *  Must be overridden unless the namespace context is null. */
	@Override
	public NamespaceSchemaContext getNamespaceSchemaContext() {
		return null;
	}
    
	public void setRoot(boolean isRoot) {
		//do nothing
	}
}
