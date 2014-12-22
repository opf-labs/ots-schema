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

import edu.harvard.hul.ois.ots.schemas.XmlContent.DecimalElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.GenericElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.IntegerElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class Frame extends GenericElement {

    private static final String ATTRIBUTE_ID_NAME = "ID";

    public static final String ELEMENT_PIXELSHORIZONTAL_NAME = "pixelsHorizontal";
    public static final String ELEMENT_PIXELSVERTICAL_NAME = "pixelsVertical";
    public static final String ELEMENT_FRAMERATE_NAME = "frameRate";
    public static final String ELEMENT_PAR_NAME = "PAR";
    public static final String ELEMENT_DAR_NAME = "DAR";
    public static final String ELEMENT_ROTATION_NAME = "rotation";


    public Frame(String name) {
        this.name = name;
    }


    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement element = outputElement(ns, name, parent);
        outputGenericElement(ns, ELEMENT_PIXELSHORIZONTAL_NAME, element);
        outputGenericElement(ns, ELEMENT_PIXELSVERTICAL_NAME, element);
        outputGenericElement(ns, ELEMENT_FRAMERATE_NAME, element);
        outputGenericElement(ns, ELEMENT_PAR_NAME, element);
        outputGenericElement(ns, ELEMENT_DAR_NAME, element);
        outputGenericElement(ns, ELEMENT_ROTATION_NAME, element);
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


    public IntegerElement getPixelsHorizontal() {
        return (IntegerElement) getField(ELEMENT_PIXELSHORIZONTAL_NAME);
    }


    public void setPixelsHorizontal(IntegerElement pixelsHorizontal)
            throws XmlContentException {
        setField(ELEMENT_PIXELSHORIZONTAL_NAME, pixelsHorizontal);
    }


    public IntegerElement getPixelsVertical() {
        return (IntegerElement) getField(ELEMENT_PIXELSVERTICAL_NAME);
    }


    public void setPixelsVertical(IntegerElement pixelsVertical)
            throws XmlContentException {
        setField(ELEMENT_PIXELSVERTICAL_NAME, pixelsVertical);
    }


    public DecimalElement getFrameRate() {
        return (DecimalElement) getField(ELEMENT_FRAMERATE_NAME);
    }


    public void setFrameRate(DecimalElement frameRate)
            throws XmlContentException {
        setField(ELEMENT_FRAMERATE_NAME, frameRate);
    }


    public StringElement getPAR() {
        return (StringElement) getField(ELEMENT_PAR_NAME);
    }


    public void setPAR(StringElement par)
            throws XmlContentException {
        setField(ELEMENT_PAR_NAME, par);
    }


    public StringElement getDAR() {
        return (StringElement) getField(ELEMENT_DAR_NAME);
    }


    public void setDAR(StringElement dar)
            throws XmlContentException {
        setField(ELEMENT_DAR_NAME, dar);
    }


    public DecimalElement getRotation() {
        return (DecimalElement) getField(ELEMENT_ROTATION_NAME);
    }


    public void setRotation(DecimalElement ratation)
            throws XmlContentException {
        setField(ELEMENT_ROTATION_NAME, ratation);
    }

}
