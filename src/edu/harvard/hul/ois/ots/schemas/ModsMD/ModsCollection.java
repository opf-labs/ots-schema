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
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputContext;
import org.codehaus.staxmate.out.SMOutputDocument;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class ModsCollection extends ModsElement {

    private final static String ELEM_MODS = "mods";

    private boolean isRoot = false;

    /** Constructor from XML, with isRoot. The isRoot parameter
     *  affects whether schema attributes are written out. */
    public ModsCollection(XMLStreamReader reader, boolean isRoot) 
            throws XMLStreamException, XmlContentException {
        name = "modsCollection";
        this.isRoot = isRoot;
        initAllElements();
        parse(reader);
    }

    /** Constructor from XML. */
    public ModsCollection(XMLStreamReader reader) 
            throws XMLStreamException, XmlContentException {
        super ();
        name = "modsCollection";
        isRoot = false;
        initAllElements();
        parse(reader);
    }
    
    /** Constructor with isRoot. The isRoot parameter
     *  affects whether schema attributes are written out. */
    public ModsCollection(boolean isRoot) { 
        super();
        name = "modsCollection";
        this.isRoot = isRoot;
    }

    /** No-argument constructor. This can be used when the caller is
     *  composing a Mix element from its constituent classes. */
    public ModsCollection () {
        super();
        name = "modsCollection";
        isRoot = false;
    }
    
    /** Get List of Mods objects */
    @SuppressWarnings("unchecked")
	public List<Mods> getModses () {
        return (List<Mods>) genericList(getList (ELEM_MODS));
    }

    /** Add a TitleInfo object 
     * @throws XmlContentException */
    public void addMods (Mods x) throws XmlContentException {
        addToList(x, ELEM_MODS);
    }

    /** Top-level output method. 
     *  The top level can be either mods or modsCollection. What do I need
     *  to do to make that work? 
     */
    @Override
    public void output(XMLStreamWriter writer) throws XMLStreamException {
        if (isRoot) {
            SMOutputDocument doc = SMOutputFactory.createOutputDocument(writer,"1.0","UTF-8",false);    
            doc.setIndentation("\n",1,1);
            SMOutputContext context = doc.getContext ();
            SMNamespace xsi = context.getNamespace (XMLNS, SCHEMA_PREFIX);
            SMOutputElement root = doc.addElement(xsi, "modsCollection");
            root.addAttribute("xmlns", XMLNS);
            root.addAttribute(xsi,"schemaLocation", XMLNS + " " + SCHEMA_LOCATION);     
            
            output (xsi, root);
            doc.closeRoot ();
        }
        else {
            SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
            SMNamespace xsi = frag.getNamespace(XMLNS, SCHEMA_PREFIX);
            SMOutputElement root = frag.addElement(xsi, "modsCollection");
            output (xsi, root);
            frag.closeRoot ();
        }
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    protected void dispatch (String localName, XMLStreamReader reader) 
                throws XMLStreamException, XmlContentException {
        if (matchList (reader, this, 
             localName, ELEM_MODS,
             Mods.class))
        return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
