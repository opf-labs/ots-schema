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
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class VideoInfo extends GenericElement {

    public static final String ELEMENT_ASPECTRATIO_NAME = "aspectRatio";
    public static final String ELEMENT_CLOSEDCAPTIONINGNOTE_NAME = "closedCaptioningNote";
    public static final String ELEMENT_CLOSEDCAPTIONINGTYPE_NAME = "closedCaptioningType";
    public static final String ELEMENT_DIMENSIONS_NAME = "dimensions";
    public static final String ELEMENT_DURATION_NAME = "duration";
    public static final String ELEMENT_FRAME_NAME = "frame";
    public static final String ELEMENT_NOTE_NAME = "note";


    public VideoInfo(String name) {
        this.name = name;
    }


    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement element = outputElement(ns, name, parent);
        outputList(ns, ELEMENT_ASPECTRATIO_NAME, element);
        outputList(ns, ELEMENT_CLOSEDCAPTIONINGNOTE_NAME, element);
        outputList(ns, ELEMENT_CLOSEDCAPTIONINGTYPE_NAME, element);
        outputList(ns, ELEMENT_DIMENSIONS_NAME, element);
        outputList(ns, ELEMENT_DURATION_NAME, element);
        outputList(ns, ELEMENT_FRAME_NAME, element);
        outputList(ns, ELEMENT_NOTE_NAME, element);
    }


    public boolean validate() {
        return false;
    }


    public List<StringElement> getAspectRatio() {
        return genericList(getList(ELEMENT_ASPECTRATIO_NAME));
    }


    public void addAspectRatio(StringElement aspectRatio)
            throws XmlContentException {
        addToList(aspectRatio, ELEMENT_ASPECTRATIO_NAME);
    }


    public List<StringElement> getClosedCaptioningNote() {
        return genericList(getList(ELEMENT_CLOSEDCAPTIONINGNOTE_NAME));
    }


    public void addClosedCaptioningNote(StringElement closedCaptioningNote)
            throws XmlContentException {
        addToList(closedCaptioningNote, ELEMENT_CLOSEDCAPTIONINGNOTE_NAME);
    }


    public List<StringElement> getClosedCaptioningType() {
        return genericList(getList(ELEMENT_CLOSEDCAPTIONINGTYPE_NAME));
    }


    public void addClosedCaptioningType(StringElement closedCaptioningType)
            throws XmlContentException {
        addToList(closedCaptioningType, ELEMENT_CLOSEDCAPTIONINGTYPE_NAME);
    }


    public List<StringElement> getDuration() {
        return genericList(getList(ELEMENT_DURATION_NAME));
    }


    public void addDuration(StringElement duration)
            throws XmlContentException {
        addToList(duration, ELEMENT_DURATION_NAME);
    }


    public List<Frame> getFrame() {
        return genericList(getList(ELEMENT_FRAME_NAME));
    }


    public void addFrame(Frame frame)
            throws XmlContentException {
        addToList(frame, ELEMENT_FRAME_NAME);
    }


    public List<StringElement> getNote() {
        return genericList(getList(ELEMENT_NOTE_NAME));
    }


    public void addNote(StringElement note)
            throws XmlContentException {
        addToList(note, ELEMENT_NOTE_NAME);
    }

}
