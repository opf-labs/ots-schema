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

public class CalibrationInfo extends GenericElement {

    private static final String ATTRIBUTE_ID_NAME = "ID";

    public static final String ELEMENT_IMAGEDATA_NAME = "imageData";
    public static final String ELEMENT_TARGETID_NAME = "targetId";
    public static final String ELEMENT_TARGETTYPE_NAME = "targetType";


    public CalibrationInfo(String name) {
        this.name = name;
    }


    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement element = outputElement(ns, name, parent);
        outputGenericElement(ns, ELEMENT_IMAGEDATA_NAME, element);
        outputGenericElement(ns, ELEMENT_TARGETID_NAME, element);
        outputGenericElement(ns, ELEMENT_TARGETTYPE_NAME, element);
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


    public StringElement getImageData() {
        return (StringElement) getField(ELEMENT_IMAGEDATA_NAME);
    }


    public void setImageData(StringElement imageData)
            throws XmlContentException {
        setField(ELEMENT_IMAGEDATA_NAME, imageData);
    }


    public StringElement getTargetId() {
        return (StringElement) getField(ELEMENT_TARGETID_NAME);
    }


    public void setTargetId(StringElement targetId)
            throws XmlContentException {
        setField(ELEMENT_TARGETID_NAME, targetId);
    }


    public StringElement getTargetType() {
        return (StringElement) getField(ELEMENT_TARGETTYPE_NAME);
    }


    public void setTargetType(StringElement targetId)
            throws XmlContentException {
        setField(ELEMENT_TARGETTYPE_NAME, targetId);
    }

}
