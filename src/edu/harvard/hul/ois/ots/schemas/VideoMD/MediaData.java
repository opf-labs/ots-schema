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

import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.GenericElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.LongElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public abstract class MediaData extends GenericElement {
    
    public static final String ELEMENT_TRACKING_NAME = "tracking";
    public static final String ELEMENT_DURATION_NAME = "duration";
    public static final String ELEMENT_LANGUAGE_NAME = "language";
    public static final String ELEMENT_SECURITY_NAME = "security";
    public static final String ELEMENT_SIZE_NAME = "size";
    public static final String ELEMENT_DATARATE_NAME = "dataRate";
    public static final String ELEMENT_TIMECODE_NAME = "timecode";
    public static final String ELEMENT_USE_NAME = "use";
    public static final String ELEMENT_OTHERUSE_NAME = "otherUse";
    
    public MediaData(String name) {
        this.name = name;
    }
    
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        outputList(ns, ELEMENT_TRACKING_NAME, parent);
        outputList(ns, ELEMENT_DURATION_NAME, parent);
        outputList(ns, ELEMENT_LANGUAGE_NAME, parent);
        outputGenericElement(ns, ELEMENT_SECURITY_NAME, parent);
        outputGenericElement(ns, ELEMENT_SIZE_NAME, parent);
        outputGenericElement(ns, ELEMENT_DATARATE_NAME, parent);
        outputList(ns, ELEMENT_TIMECODE_NAME, parent);
        outputList(ns, ELEMENT_USE_NAME, parent);
        outputList(ns, ELEMENT_OTHERUSE_NAME, parent);
    }


    public List<StringElement> getDuration() {
        return genericList(getList(ELEMENT_DURATION_NAME));
    }

    public void addDuration(StringElement duration) throws XmlContentException {
        addToList(duration, ELEMENT_DURATION_NAME);
    }

    public List<Language> getLanguage() {
        return genericList(getList(ELEMENT_LANGUAGE_NAME));
    }

    public void addLanguage(Language language) throws XmlContentException {
        addToList(language, ELEMENT_LANGUAGE_NAME);
    }

    public StringElement getSecurity() {
        return (StringElement) getField(ELEMENT_SECURITY_NAME);
    }

    public void setSecurity(StringElement security) throws XmlContentException {
        setField(ELEMENT_SECURITY_NAME, security);
    }

    public LongElement getSize() {
        return (LongElement) getField(ELEMENT_SIZE_NAME);
    }

    public void setSize(LongElement size) throws XmlContentException {
        setField(ELEMENT_SIZE_NAME, size);
    }

    public VariableRate getDataRate() {
        return (VariableRate) getField(ELEMENT_DATARATE_NAME);
    }

    public void setDataRate(VariableRate dataRate) throws XmlContentException {
        setField(ELEMENT_DATARATE_NAME, dataRate);
    }

    public List<TimecodeInfo> getTimecode() {
        return genericList(getList(ELEMENT_TIMECODE_NAME));
    }

    public void addTimecode(TimecodeInfo timecode) throws XmlContentException {
        addToList(timecode, ELEMENT_TIMECODE_NAME);
    }

    public List<Use> getUse() {
        return genericList(getList(ELEMENT_USE_NAME));
    }

    public void addUse(Use use) throws XmlContentException {
        addToList(use, ELEMENT_USE_NAME);
    }

    public List<StringElement> getOtherUse() {
        return genericList(getList(ELEMENT_OTHERUSE_NAME));
    }

    public void addOtherUse(StringElement otherUse) throws XmlContentException {
        addToList(otherUse, ELEMENT_OTHERUSE_NAME);
    }

}
