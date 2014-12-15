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

public class Format extends GenericElement {

    public static final String ELEMENT_ANNOTATION_NAME = "annotation";
    public static final String ELEMENT_CREATORAPP_NAME = "creatorApp";
    public static final String ELEMENT_CREATORLIB_NAME = "creatorLib";
    public static final String ELEMENT_CREATORLIBDATE_NAME = "creatorLibDate";
    public static final String ELEMENT_CREATORLIBSETTINGS_NAME = "creatorLibSettings";
    public static final String ELEMENT_FORMATNAME_NAME = "name";
    public static final String ELEMENT_ENCODINGDATE_NAME = "encodingDate";
    public static final String ELEMENT_TAGGEDDATE_NAME = "taggedDate";
    public static final String ELEMENT_COMMERCIALNAME_NAME = "commercialName";
    public static final String ELEMENT_MIMETYPE_NAME = "mimetype";
    public static final String ELEMENT_PROFILE_NAME = "profile";
    public static final String ELEMENT_SETTINGS_NAME = "settings";
    public static final String ELEMENT_VERSION_NAME = "version";


    public Format(String name) {
        this.name = name;
    }


    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement element = outputElement(ns, name, parent);
        outputGenericElement(ns, ELEMENT_ANNOTATION_NAME, element);
        outputGenericElement(ns, ELEMENT_CREATORAPP_NAME, element);
        outputGenericElement(ns, ELEMENT_CREATORLIB_NAME, element);
        outputGenericElement(ns, ELEMENT_CREATORLIBDATE_NAME, element);
        outputGenericElement(ns, ELEMENT_CREATORLIBSETTINGS_NAME, element);
        outputGenericElement(ns, ELEMENT_FORMATNAME_NAME, element);
        outputGenericElement(ns, ELEMENT_ENCODINGDATE_NAME, element);
        outputGenericElement(ns, ELEMENT_TAGGEDDATE_NAME, element);
        outputGenericElement(ns, ELEMENT_COMMERCIALNAME_NAME, element);
        outputGenericElement(ns, ELEMENT_MIMETYPE_NAME, element);
        outputGenericElement(ns, ELEMENT_PROFILE_NAME, element);
        outputGenericElement(ns, ELEMENT_SETTINGS_NAME, element);
        outputGenericElement(ns, ELEMENT_VERSION_NAME, element);
    }


    public boolean validate() {
        return false;
    }


    public StringElement getAnnotation() {
        return (StringElement) getField(ELEMENT_ANNOTATION_NAME);
    }


    public void setAnnotation(StringElement annotation)
            throws XmlContentException {
        setField(ELEMENT_ANNOTATION_NAME, annotation);
    }


    public StringElementVersion getCreatorApp() {
        return (StringElementVersion) getField(ELEMENT_CREATORAPP_NAME);
    }


    public void setCreatorApp(StringElementVersion creatorApp)
            throws XmlContentException {
        setField(ELEMENT_CREATORAPP_NAME, creatorApp);
    }


    public StringElementVersion getCreatorLib() {
        return (StringElementVersion) getField(ELEMENT_CREATORLIB_NAME);
    }


    public void setCreatorLib(StringElementVersion creatorLib)
            throws XmlContentException {
        setField(ELEMENT_CREATORLIB_NAME, creatorLib);
    }


    public StringElement getCreatorLibDate() {
        return (StringElement) getField(ELEMENT_CREATORLIBDATE_NAME);
    }


    public void setCreatorLibDate(StringElement creatorLibDate)
            throws XmlContentException {
        setField(ELEMENT_CREATORLIBDATE_NAME, creatorLibDate);
    }


    public StringElement getCreatorLibSettings() {
        return (StringElement) getField(ELEMENT_CREATORLIBSETTINGS_NAME);
    }


    public void setCreatorLibSettings(StringElement creatorLibSettings)
            throws XmlContentException {
        setField(ELEMENT_CREATORLIBSETTINGS_NAME, creatorLibSettings);
    }


    public StringElement getFormatName() {
        return (StringElement) getField(ELEMENT_FORMATNAME_NAME);
    }


    public void setFormatName(StringElement name)
            throws XmlContentException {
        setField(ELEMENT_FORMATNAME_NAME, name);
    }


    public StringElement getEncodingDate() {
        return (StringElement) getField(ELEMENT_ENCODINGDATE_NAME);
    }


    public void setEncodingDate(StringElement encodingDate)
            throws XmlContentException {
        setField(ELEMENT_ENCODINGDATE_NAME, encodingDate);
    }


    public StringElement getTaggedDate() {
        return (StringElement) getField(ELEMENT_TAGGEDDATE_NAME);
    }


    public void setTaggedDate(StringElement taggedDate)
            throws XmlContentException {
        setField(ELEMENT_TAGGEDDATE_NAME, taggedDate);
    }


    public StringElement getCommercialName() {
        return (StringElement) getField(ELEMENT_COMMERCIALNAME_NAME);
    }


    public void setCommercialName(StringElement commercialName)
            throws XmlContentException {
        setField(ELEMENT_COMMERCIALNAME_NAME, commercialName);
    }


    public StringElement getMimetype() {
        return (StringElement) getField(ELEMENT_MIMETYPE_NAME);
    }


    public void setMimetype(StringElement mimetype)
            throws XmlContentException {
        setField(ELEMENT_MIMETYPE_NAME, mimetype);
    }


    public StringElement getProfile() {
        return (StringElement) getField(ELEMENT_PROFILE_NAME);
    }


    public void setProfile(StringElement profile)
            throws XmlContentException {
        setField(ELEMENT_PROFILE_NAME, profile);
    }


    public StringElement getSettings() {
        return (StringElement) getField(ELEMENT_SETTINGS_NAME);
    }


    public void setSettings(StringElement settings)
            throws XmlContentException {
        setField(ELEMENT_SETTINGS_NAME, settings);
    }


    public StringElement getVersion() {
        return (StringElement) getField(ELEMENT_VERSION_NAME);
    }


    public void setVersion(StringElement version)
            throws XmlContentException {
        setField(ELEMENT_VERSION_NAME, version);
    }

}
