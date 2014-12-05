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

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import static javax.xml.stream.XMLStreamConstants.*;

public class StringElement extends GenericElement {

    protected String text;

   /**  Constructor for this element from XML
    */
   public StringElement (XMLStreamReader reader) 
          throws XMLStreamException, XmlContentException {
       textBuf = new StringBuffer ();
       text = "";
       parse (reader);
   }

   /** Constructor from XML with name. This may be the better way to do it.*/
   public StringElement (XMLStreamReader reader, String name)  
          throws XMLStreamException, XmlContentException {
       this (reader);
       this.name = name;
   }

   
   /** Constructor from a string and a name.  */
   public StringElement (String t, String name) {
       this.name = name;
       text = t;
   }
   
   public void setText(String t) {
	   text = t;
   }

   /** Output method which adds to a parent element. 
    */
   @Override
   public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
       outputElement (ns, name, parent, text);
   }


   /** Returns the value as a String; generic. */
   @Override
   public Object toValue () {
       return text;
   }

   /** Returns the value as a String; type-specific. */
   @Override
   public String toString () {
       return text;
   }

   @Override
   public XmlContent parse(XMLStreamReader reader)
             throws XMLStreamException, XmlContentException {
       String topName = reader.getLocalName ();
       String nsURI = reader.getNamespaceURI();
       addAttributes (reader);
       for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {
           switch(event) {
           case CHARACTERS:
               textBuf.append (reader.getText ());
               break;
           case END_ELEMENT:
               // Caution: This works only if any recursive references
               // to the name are eaten by dispatch. Shouldn't be a problem in MIX.
               if(reader.getLocalName().equals(topName) &&
                       nsURI.equals(reader.getNamespaceURI())) {
                   text = textBuf.toString ();
                   return this;
               }
           }
       }
       return null;
   }
   
   /** Check the text content against an array of permitted values and
    *  throw an exception if none match. */
   public void checkContent (String[] values) throws XmlContentException {
       for (int i = 0; i < values.length; i++) {
           if (values[i].equals (text)) {
               return;
           }
       }
       throw new XmlContentException("Value '" + text + "' does not match permitted values");
   }


   @Override
   public boolean validate() {
       // TODO Auto-generated method stub
       return false;
   }

}
