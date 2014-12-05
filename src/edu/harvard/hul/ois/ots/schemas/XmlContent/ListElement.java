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

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;


import java.util.ArrayList;
import java.util.List;

/** ListElement is used for repeatable elements. A ListElement encapsulates
 *  a List of GenericElements, all of the same class.
 */
public class ListElement extends GenericElement {

    private List<GenericElement> theList;
    
    public ListElement () {
        theList = new ArrayList<GenericElement> ();
    }

    public ListElement (String name) {
        this ();
        this.name = name;
    }

//    public ListElement (List<GenericElement> lst) {
//        theList = lst;
//    }

    public ListElement (List<GenericElement> lst, String name) {
        theList = lst;
        this.name = name;
    }

    /** Output method which adds all the list members to a parent element.  */
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        for (GenericElement elem : theList) {
            elem.output (ns, parent);
        }
    }


    
    /** Returns the value as a List; generic. */
    @Override
    public Object toValue () {
        return theList;
    }
    
    /** Returns the value as a List; type-specific. */
    public List<GenericElement> toList () {
        return theList;
    }
    
    public void add (GenericElement elem) {
        theList.add(elem);
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

}
