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

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
//import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

//import edu.harvard.hul.ois.ots.XmlContent.XmlContent;

public class RationalElement extends GenericElement {

    private final static String ELEM_NUM = "numerator";
    private final static String ELEM_DEN = "denominator";

    private Integer numerator;
    private Integer denominator;
    
    /**  Constructor from XML
     */
    public RationalElement (XMLStreamReader reader) 
              throws XMLStreamException, XmlContentException {
        textBuf = new StringBuffer ();
        numerator = null;
        denominator = null;
        parse (reader);
    }
    
    /** Constructor from XML, with name */
    public RationalElement (XMLStreamReader reader, String name)
               throws XMLStreamException, XmlContentException {
        this(reader);
        this.name = name;
    }
    
    /** Constructor from a Rational */
//    public RationalElement (Rational r) {
//        numerator = r.getNumerator ();
//        denominator = r.getDenominator ();
//    }
    
    /** Constructor from a Rational and name */
    public RationalElement (Rational r, String name) {
        this.name = name;
        numerator = r.getNumerator ();
        denominator = r.getDenominator ();
    }
    

    
    /** Returns the value as a Rational; generic. */
    @Override
    public Object toValue () {
            if (numerator != null && denominator != null)   
                return new Rational(numerator, denominator);
            else return null;
    }
    
    /** Returns the value as a Rational; type-specific. */
    public Rational toRational () {
        return (Rational) toValue();
    }


    /** Output method which adds to a parent element.  */
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement elem = outputElement (ns, name, parent); 
        elem.addElementWithCharacters 
             (ns, "numerator", numerator.toString ());
        elem.addElementWithCharacters 
             (ns, "denominator", denominator.toString ());
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    /** Handle the completed element. */
    @Override
    protected void endParse () {
        numerator = (Integer) getField (ELEM_NUM).toValue();
        denominator = (Integer) getField (ELEM_DEN).toValue();
    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    @Override
    protected void dispatch (String localName, XMLStreamReader reader) 
                  throws XMLStreamException, XmlContentException {
        if (match (reader, this,
                localName, ELEM_NUM,
                IntegerElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_DEN,
                IntegerElement.class))
            return;
    }
}
    
