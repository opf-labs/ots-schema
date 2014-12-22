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

public class Compression extends GenericElement {

    private static final String ATTRIBUTE_ID_NAME = "ID";

    public static final String ELEMENT_CODECCREATORAPP_NAME = "codecCreatorApp";
    public static final String ELEMENT_CODECCREATORAPPVERSION_NAME = "codecCreatorAppVersion";
    public static final String ELEMENT_CODECNAME_NAME = "codecName";
    public static final String ELEMENT_CODECQUALITY_NAME = "codecQuality";


    public Compression(String name) {
        this.name = name;
    }


    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement element = outputElement(ns, name, parent);
        outputGenericElement(ns, ELEMENT_CODECCREATORAPP_NAME, element);
        outputGenericElement(ns, ELEMENT_CODECCREATORAPPVERSION_NAME, element);
        outputGenericElement(ns, ELEMENT_CODECNAME_NAME, element);
        outputGenericElement(ns, ELEMENT_CODECQUALITY_NAME, element);
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


    public StringElement getCodecCreatorApp() {
        return (StringElement) getField(ELEMENT_CODECCREATORAPP_NAME);
    }


    public void setCodecCreatorApp(StringElement codecCreatorApp)
            throws XmlContentException {
        setField(ELEMENT_CODECCREATORAPP_NAME, codecCreatorApp);
    }


    public StringElement getCodecCreatorAppVersion() {
        return (StringElement) getField(ELEMENT_CODECCREATORAPPVERSION_NAME);
    }


    public void setCodecCreatorAppVersion(StringElement codecCreatorAppVersion)
            throws XmlContentException {
        setField(ELEMENT_CODECCREATORAPPVERSION_NAME, codecCreatorAppVersion);
    }


    public StringElement getCodecName() {
        return (StringElement) getField(ELEMENT_CODECNAME_NAME);
    }


    public void setCodecName(StringElement codecName)
            throws XmlContentException {
        setField(ELEMENT_CODECNAME_NAME, codecName);
    }


    public CodecQuality getCodecQuality() {
        return (CodecQuality) getField(ELEMENT_CODECQUALITY_NAME);
    }


    public void setCodecQuality(CodecQuality codecQuality)
            throws XmlContentException {
        setField(ELEMENT_CODECQUALITY_NAME, codecQuality);
    }

}
