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

import edu.harvard.hul.ois.ots.schemas.XmlContent.IntegerElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class FileData extends MediaData {

    public static final String ELEMENT_BITSPERSAMPLE_NAME = "bitsPerSample";
    public static final String ELEMENT_BYTEORDER_NAME = "byteOrder";
    public static final String ELEMENT_COLOR_NAME = "color";
    public static final String ELEMENT_OTHERCOLOR_NAME = "otherColor";
    public static final String ELEMENT_MESSAGEDIGEST_NAME = "messageDigest";
    public static final String ELEMENT_COMPRESSION_NAME = "compression";
    public static final String ELEMENT_TRACK_NAME = "track";
    public static final String ELEMENT_DATARATEUNIT_NAME = "dataRateUnit";
    public static final String ELEMENT_DATARATEMODE_NAME = "dataRateMode";
    public static final String ELEMENT_FRAME_NAME = "frame";
    public static final String ELEMENT_FRAMERATE_NAME = "frameRate";
    public static final String ELEMENT_SAMPLERATE_NAME = "sampleRate";
    public static final String ELEMENT_LOCATION_NAME = "location";
    public static final String ELEMENT_FORMAT_NAME = "format";
    public static final String ELEMENT_SAMPLING_NAME = "sampling";
    public static final String ELEMENT_SIGNALFORMAT_NAME = "signalFormat";
    public static final String ELEMENT_SOUND_NAME = "sound";


    public FileData(String name) {
        super(name);
    }


    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement element = outputElement(ns, name, parent);
        super.output(ns, element);
        outputList(ns, ELEMENT_BITSPERSAMPLE_NAME, element);
        outputList(ns, ELEMENT_BYTEORDER_NAME, element);
        outputGenericElement(ns, ELEMENT_COLOR_NAME, element);
        outputGenericElement(ns, ELEMENT_OTHERCOLOR_NAME, element);
        outputList(ns, ELEMENT_MESSAGEDIGEST_NAME, element);
        outputList(ns, ELEMENT_COMPRESSION_NAME, element);
        outputList(ns, ELEMENT_DATARATEUNIT_NAME, element);
        outputList(ns, ELEMENT_DATARATEMODE_NAME, element);
        outputGenericElement(ns, ELEMENT_FRAME_NAME, element);
        outputGenericElement(ns, ELEMENT_FRAMERATE_NAME, element);
        outputGenericElement(ns, ELEMENT_SAMPLERATE_NAME, element);
        outputList(ns, ELEMENT_LOCATION_NAME, element);
        outputList(ns, ELEMENT_FORMAT_NAME, element);
        outputList(ns, ELEMENT_SAMPLING_NAME, element);
        outputList(ns, ELEMENT_SIGNALFORMAT_NAME, element);
        outputList(ns, ELEMENT_SOUND_NAME, element);
    }


    public boolean validate() {
        return false;
    }


    public List<IntegerElement> getBitsPerSample() {
        return genericList(getList(ELEMENT_BITSPERSAMPLE_NAME));
    }


    public void addBitsPerSample(IntegerElement bitsPerSample)
            throws XmlContentException {
        addToList(bitsPerSample, ELEMENT_BITSPERSAMPLE_NAME);
    }


    public List<ByteOrder> getByteOrder() {
        return genericList(getList(ELEMENT_BYTEORDER_NAME));
    }


    public void addByteOrder(ByteOrder byteOrder)
            throws XmlContentException {
        addToList(byteOrder, ELEMENT_BYTEORDER_NAME);
    }


    public Color getColor() {
        return (Color) getField(ELEMENT_COLOR_NAME);
    }


    public void setColor(Color color)
            throws XmlContentException {
        setField(ELEMENT_COLOR_NAME, color);
    }


    public StringElement getOtherColor() {
        return (StringElement) getField(ELEMENT_OTHERCOLOR_NAME);
    }


    public void setOtherColor(StringElement otherColor)
            throws XmlContentException {
        setField(ELEMENT_OTHERCOLOR_NAME, otherColor);
    }


    public List<Compression> getCompression() {
        return genericList(getList(ELEMENT_COMPRESSION_NAME));
    }


    public void addCompression(Compression compression)
            throws XmlContentException {
        addToList(compression, ELEMENT_COMPRESSION_NAME);
    }


    public List<StringElement> getDataRateUnit() {
        return genericList(getList(ELEMENT_DATARATEUNIT_NAME));
    }


    public void addDataRateUnit(StringElement dataRateUnit)
            throws XmlContentException {
        addToList(dataRateUnit, ELEMENT_DATARATEUNIT_NAME);
    }


    public List<DataRateMode> getDataRateMode() {
        return genericList(getList(ELEMENT_DATARATEMODE_NAME));
    }


    public void addDataRateMode(DataRateMode dataRateMode)
            throws XmlContentException {
        addToList(dataRateMode, ELEMENT_DATARATEMODE_NAME);
    }


    public Frame getFrame() {
        return (Frame) getField(ELEMENT_FRAME_NAME);
    }


    public void setFrame(Frame frame)
            throws XmlContentException {
        setField(ELEMENT_FRAME_NAME, frame);
    }


    public VariableRate getFrameRate() {
        return (VariableRate) getField(ELEMENT_FRAMERATE_NAME);
    }


    public void setFrameRate(VariableRate frameRate)
            throws XmlContentException {
        setField(ELEMENT_FRAMERATE_NAME, frameRate);
    }


    public VariableRate getSampleRate() {
        return (VariableRate) getField(ELEMENT_SAMPLERATE_NAME);
    }


    public void setSampleRate(VariableRate sampleRate)
            throws XmlContentException {
        setField(ELEMENT_SAMPLERATE_NAME, sampleRate);
    }


    public List<Format> getFormat() {
        return genericList(getList(ELEMENT_FORMAT_NAME));
    }


    public void addFormat(Format format)
            throws XmlContentException {
        addToList(format, ELEMENT_FORMAT_NAME);
    }


    public List<StringElement> getSampling() {
        return genericList(getList(ELEMENT_SAMPLING_NAME));
    }


    public void addSampling(StringElement sampling)
            throws XmlContentException {
        addToList(sampling, ELEMENT_SAMPLING_NAME);
    }


    public List<StringElement> getSignalFormat() {
        return genericList(getList(ELEMENT_SIGNALFORMAT_NAME));
    }


    public void addSignalFormat(StringElement signalFormat)
            throws XmlContentException {
        addToList(signalFormat, ELEMENT_SIGNALFORMAT_NAME);
    }


    public List<Sound> getSound() {
        return genericList(getList(ELEMENT_SOUND_NAME));
    }


    public void addSound(Sound sound)
            throws XmlContentException {
        addToList(sound, ELEMENT_SOUND_NAME);
    }

}
