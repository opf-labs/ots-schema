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

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;


/** Any MIX element which simply absorbs and disgorges XML without
 *  analysis should subclass this. */
public abstract class AnonymousElement extends GenericElement {

    private AnyXml content;
    
    /**  Constructor from XML
     */
    public AnonymousElement (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        parse(reader);
    }

    /** No-argument constructor. Not very useful, since we should
     *  only create one of these when parsing, but here for
     *  completeness. */
    public AnonymousElement () {
        super ();
        content = new AnyXml ();
    }

    @Override
    public XmlContent parse(XMLStreamReader reader)
              throws XMLStreamException, XmlContentException {
        content =   new AnyXml();
        content.parse (reader);
        return null;
    }

    /** Returns a list of all namespace schema contexts in or under
     *  this element.  */
    @Override
    public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts() {
        return content.getAllNamespaceSchemaContexts();
    }

    /** Returns the namespace schema context for this element. 
     *  Must be overridden unless the namespace context is null. */
    @Override
    public NamespaceSchemaContext getNamespaceSchemaContext() {
        return content.getNamespaceSchemaContext();
    }
    
    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        if (content.getXml() != null) {
            outputAnyXml (content.getXml(), parent);
        }
    }

    /** Anonymous content is always valid */
    @Override
    public boolean validate() {
        return true;
    }

}
