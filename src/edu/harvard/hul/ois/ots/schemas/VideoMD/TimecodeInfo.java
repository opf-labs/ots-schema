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
package edu.harvard.hul.ois.ots.schemas.VideoMD;

import javax.xml.stream.XMLStreamException;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.GenericElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class TimecodeInfo extends GenericElement {

    private static final String ATTRIBUTE_ID_NAME = "ID";

    public static final String ELEMENT_RECORDMETHOD_NAME = "timecodeRecordMethod";
    public static final String ELEMENT_TYPE_NAME = "timecodeType";
    public static final String ELEMENT_INITIALVALUE_NAME = "timecodeInitialValue";


    public TimecodeInfo(String name) {
        this.name = name;
    }


    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement element = outputElement(ns, name, parent);
        outputElement(ns, ELEMENT_RECORDMETHOD_NAME, element);
        outputElement(ns, ELEMENT_TYPE_NAME, element);
        outputElement(ns, ELEMENT_INITIALVALUE_NAME, element);
    }


    public boolean validate() {
        return false;
    }


    public String getID() {
        return getAttribute(ATTRIBUTE_ID_NAME);
    }


    public void setID(String id) {
        setAttribute(ATTRIBUTE_ID_NAME, id);
    }


    public StringElement getTimecodeRecordMethod() {
        return (StringElement) getField(ELEMENT_RECORDMETHOD_NAME);
    }


    public void setTimecodeRecordMethod(StringElement recordMethod)
            throws XmlContentException {
        setField(ELEMENT_RECORDMETHOD_NAME, recordMethod);
    }


    public StringElement getTimecodeType() {
        return (StringElement) getField(ELEMENT_TYPE_NAME);
    }


    public void setTimecodeType(StringElement type)
            throws XmlContentException {
        setField(ELEMENT_TYPE_NAME, type);
    }


    public StringElement getTimecodeInitialValue() {
        return (StringElement) getField(ELEMENT_INITIALVALUE_NAME);
    }


    public void setTimecodeInitialValue(StringElement initialValue)
            throws XmlContentException {
        setField(ELEMENT_INITIALVALUE_NAME, initialValue);
    }

}
