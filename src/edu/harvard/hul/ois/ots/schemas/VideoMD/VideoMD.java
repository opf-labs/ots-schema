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

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputDocument;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

import edu.harvard.hul.ois.ots.schemas.StaxUtils.RootXmlHelper;
import edu.harvard.hul.ois.ots.schemas.XmlContent.GenericElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class VideoMD extends GenericElement {

    public static final String ROOT_ELEMENT_NAME = "VIDEOMD";

    private static final String ATTRIBUTE_ID_NAME = "ID";
    private static final String ATTRIBUTE_ANALOGDIGITALFLAG_NAME = "ANALOGDIGITALFLAG";

    public static final String ELEMENT_FILEDATA_NAME = "fileData";
    public static final String ELEMENT_PHYSICALDATA_NAME = "physicalData";
    public static final String ELEMENT_VIDEOINFO_NAME = "videoInfo";
    public static final String ELEMENT_CALIBRATIONINFO_NAME = "calibrationInfo";

    public static final String NAMESPACE_URI = "http://www.loc.gov/videoMD/";
    public static final String NAMESPACE_PREFIX = "videoMD";
    public static final String NAMESPACE_SCHEMA_LOCATION = "http://www.loc.gov/standards/amdvmd/videoMD.xsd";

    protected static final NamespaceSchemaContext videoMdNSC = new NamespaceSchemaContext(NAMESPACE_URI,
            NAMESPACE_PREFIX, NAMESPACE_SCHEMA_LOCATION);

    private boolean isRoot;


    public VideoMD() {
        this.isRoot = false;
        this.name = ROOT_ELEMENT_NAME;
    }


    public VideoMD(boolean isRoot) {
        this.isRoot = isRoot;
        this.name = ROOT_ELEMENT_NAME;
    }


    @Override
    public NamespaceSchemaContext getNamespaceSchemaContext() {
        return videoMdNSC;
    }


    @Override
    public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts() {
        return Arrays.asList(new NamespaceSchemaContext[] { videoMdNSC });
    }


    @Override
    public void setRoot(boolean flag) {
        this.isRoot = flag;
    }


    public boolean validate() {
        return false;
    }


    @Override
    public void output(XMLStreamWriter writer)
            throws XMLStreamException {
        if (isRoot) {
            SMOutputDocument doc = SMOutputFactory.createOutputDocument(writer);
            SMNamespace videoMdNS = doc.getNamespace(NAMESPACE_URI, NAMESPACE_PREFIX);
            SMOutputElement root = doc.addElement(videoMdNS, ROOT_ELEMENT_NAME);
            RootXmlHelper.output(writer, getAllNamespaceSchemaContexts(), root);
            Iterator attrIter = attributes.keySet().iterator();
            do {
                if (!attrIter.hasNext())
                    break;
                String key = (String) attrIter.next();
                String value = (String) attributes.get(key);
                if (key.indexOf("schemaLocation") <= 0)
                    root.addAttribute(key, value);
            } while (true);
            output(videoMdNS, root);
            doc.closeRoot();
        } else {
            SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
            SMNamespace videoMdNS = frag.getNamespace(NAMESPACE_URI, NAMESPACE_PREFIX);
            SMOutputElement root = frag.addElement(videoMdNS, ROOT_ELEMENT_NAME);
            String value = (String) attributes.get(ATTRIBUTE_ID_NAME);
            if (value != null)
                root.addAttribute(ATTRIBUTE_ID_NAME, value);
            value = (String) attributes.get(ATTRIBUTE_ANALOGDIGITALFLAG_NAME);
            if (value != null)
                root.addAttribute(ATTRIBUTE_ANALOGDIGITALFLAG_NAME, value);
            output(videoMdNS, root);
            frag.closeRoot();
        }
    }


    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        outputGenericElement(ns, ELEMENT_FILEDATA_NAME, parent);
        outputGenericElement(ns, ELEMENT_PHYSICALDATA_NAME, parent);
        outputGenericElement(ns, ELEMENT_VIDEOINFO_NAME, parent);
        outputGenericElement(ns, ELEMENT_CALIBRATIONINFO_NAME, parent);
    }


    public String getID() {
        return getAttribute(ATTRIBUTE_ID_NAME);
    }


    public void setID(String id) {
        setAttribute(ATTRIBUTE_ID_NAME, id);
    }


    public String getANALOGDIGITALFLAG() {
        return getAttribute(ATTRIBUTE_ANALOGDIGITALFLAG_NAME);
    }


    public void setANALOGDIGITALFLAG(String analogdigitalflag) {
        setAttribute(ATTRIBUTE_ANALOGDIGITALFLAG_NAME, analogdigitalflag);
    }


    public FileData getFileData() {
        return (FileData) getField(ELEMENT_FILEDATA_NAME);
    }


    public void setFileData(FileData fileData)
            throws XmlContentException {
        setField(ELEMENT_FILEDATA_NAME, fileData);
    }


    public VideoInfo getVideoInfo() {
        return (VideoInfo) getField(ELEMENT_VIDEOINFO_NAME);
    }


    public void setVideoInfo(VideoInfo videoInfo)
            throws XmlContentException {
        setField(ELEMENT_VIDEOINFO_NAME, videoInfo);
    }


    public CalibrationInfo getCalibrationInfo() {
        return (CalibrationInfo) getField(ELEMENT_CALIBRATIONINFO_NAME);
    }


    public void setCalibrationInfo(CalibrationInfo calibrationInfo)
            throws XmlContentException {
        setField(ELEMENT_CALIBRATIONINFO_NAME, calibrationInfo);
    }

}
