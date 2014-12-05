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
package edu.harvard.hul.ois.ots.schemas.MIX;

//import java.util.HashMap;
//import java.util.Map;

//import static javax.xml.stream.XMLStreamConstants.START_DOCUMENT;
import static javax.xml.stream.XMLStreamConstants.*;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import edu.harvard.hul.ois.ots.schemas.StaxUtils.RootXmlHelper;
import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContent;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputDocument;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

public class Mix extends MixElement {

    private final static String ELEM_BDOI = "BasicDigitalObjectInformation";
    private final static String ELEM_BII = "BasicImageInformation";
    private final static String ELEM_ICM = "ImageCaptureMetadata";
    private final static String ELEM_IAM = "ImageAssessmentMetadata";
    private final static String ELEM_CH = "ChangeHistory";
    private final static String ELEM_EXT = "Extension";

    private static final String TOP_ELEMENT = "mix";
    	
    private boolean isRoot = false;

    /** Constructor from XML. */
    public Mix(XMLStreamReader reader) 
            throws XMLStreamException, XmlContentException {
        name = "mix";
        parse(reader);
    }
    
    /** Constructor from XML, with isRoot. The isRoot parameter
     *  affects whether schema attributes are written out. */
    public Mix(XMLStreamReader reader, boolean isRoot) 
            throws XMLStreamException, XmlContentException {
        name = "mix";
        this.isRoot = isRoot;
        parse(reader);
    }
    
    /** No-argument constructor. This can be used when the caller is
     *  composing a Mix element from its constituent classes. */
    public Mix () {
        super();
        isRoot = false;
        name = "mix";
    }

    public Mix(boolean isRoot) { 
        this.isRoot = isRoot;
    }

	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	/** This function looks as if it could be invariant, calling a local dispatch
	 *  function. */
	@Override
	public XmlContent parse(XMLStreamReader reader)
			throws XMLStreamException, XmlContentException {
        reader.require(START_ELEMENT, XMLNS, TOP_ELEMENT);
        addAttributes (reader);
        // There are four big pieces to MIX.
        for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {
            switch(event) {
            case START_ELEMENT:
                String localName = reader.getLocalName();
                dispatch (localName, reader);
                break;
            case END_ELEMENT:
                if(reader.getLocalName().equals(TOP_ELEMENT)) {
                    return this;
                }
            }
        }
		return null;
	}


	/** Get the BasicDigitalObjectInformation object */
	public BasicDigitalObjectInformation getBasicDigitalObjectInformation () {
	    return (BasicDigitalObjectInformation) getField (ELEM_BDOI);
	}
	
	/** Set the BasicDigitalObjectInformation object 
	 * @throws XmlContentException */
	public void setBasicDigitalObjectInformation (BasicDigitalObjectInformation x) throws XmlContentException {
	    setField (ELEM_BDOI, x);
	}
	
    /** Get the BasicImageInformation object */
    public BasicImageInformation getBasicImageInformation () {
        return (BasicImageInformation) getField (ELEM_BII);
    }
    
    /** Set the BasicImageInformation object 
     * @throws XmlContentException */
    public void setBasicImageInformation (BasicImageInformation x) throws XmlContentException {
        setField (ELEM_BII, x);
    }
    
    /** Get the ImageCaptureMetadata object */
    public ImageCaptureMetadata getImageCaptureMetadata () {
        return (ImageCaptureMetadata) getField (ELEM_ICM);
    }
    
    /** Set the ImageCaptureMetadata object 
     * @throws XmlContentException */
    public void setImageCaptureMetadata (ImageCaptureMetadata x) throws XmlContentException {
        setField (ELEM_ICM, x);
    }
    
    /** Get the ImageAssessmentMetadata object */
    public ImageAssessmentMetadata getImageAssessmentMetadata () {
        return (ImageAssessmentMetadata) getField (ELEM_IAM);
    }
    
    /** Set the ImageAssessmentMetadata object 
     * @throws XmlContentException */
    public void setImageAssessmentMetadata (ImageAssessmentMetadata x) throws XmlContentException {
        setField (ELEM_IAM, x);
    }
    
    /** Get the ChangeHistory object */
    public ChangeHistory getChangeHistory () {
        return (ChangeHistory) getField (ELEM_CH);
    }
    
    /** Set the ChangeHistory object 
     * @throws XmlContentException */
    public void setChangeHistory (ChangeHistory x) throws XmlContentException {
        setField (ELEM_BII, x);
    }
    
    /** Get the Extension object */
    public Extension getExtension () {
        return (Extension) getField (ELEM_EXT);
    }
    
    /** Set the Extension object 
     * @throws XmlContentException */
    public void setExtension (Extension x) throws XmlContentException {
        setField (ELEM_EXT, x);
    }
    
    /** Returns a list of all namespace schema contexts in or under
     *  this element. Returns null if not overridden. */
    @Override
    public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts() {
        List<NamespaceSchemaContext> lst = 
            new ArrayList<NamespaceSchemaContext> ();
        lst.add (MixElement.mixNSC);
        
        // There are only two places where there might be additional
        // NamespaceSchemaContexts. Drill down to them and merge in 
        // their lists.
        ChangeHistory ch = getChangeHistory();
        if (ch != null) {
            PreviousImageMetadata pim = ch.getPreviousImageMetadata();
            if (pim != null) {
                List<NamespaceSchemaContext> pimList = 
                    pim.getAllNamespaceSchemaContexts();
                mergeList (lst, pimList);
            }
        }
        Extension ext = getExtension ();
        if (ext != null) {
            List <NamespaceSchemaContext> extList =
                ext.getAllNamespaceSchemaContexts();
            mergeList (lst, extList);
        }
        return lst;
    }
    
    private void mergeList (List<NamespaceSchemaContext> dest,
                       List<NamespaceSchemaContext> src) {
        for (NamespaceSchemaContext nsc : src) {
            if (!dest.contains (nsc)) {
                dest.add (nsc);
            }
        }
    }

    @Override
    public void output(XMLStreamWriter writer) throws XMLStreamException {  	
		if(isRoot) {
			SMOutputDocument doc = SMOutputFactory.createOutputDocument(writer);			
			SMNamespace mixNS = doc.getNamespace(XMLNS,mixNSC.getPrefix());
			SMOutputElement root = doc.addElement(mixNS,TOP_ELEMENT);		
			RootXmlHelper.output(writer,getAllNamespaceSchemaContexts(),root);		
			output(mixNS,root);
			doc.closeRoot();
		}
		else {
			SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
			SMNamespace mixNS = frag.getNamespace(XMLNS,mixNSC.getPrefix());
			SMOutputElement root = frag.addElement(mixNS,TOP_ELEMENT);
			output(mixNS,root);
			frag.closeRoot();
		}
    }
    /*
    if(isRoot) {
        SMOutputDocument doc = SMOutputFactory.createOutputDocument(writer);            
        SMNamespace textmd = doc.getNamespace(XMLNS,nsSchemaContext.getPrefix());
        SMOutputElement root = doc.addElement(textmd,"textMD");     
        RootBuilder.output(writer,getAllNamespaceSchemaContexts(),root);        
        outputContents(writer,textmd,root);
        doc.closeRoot();
    }
    else {
        SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
        SMNamespace textmd = frag.getNamespace(XMLNS,nsSchemaContext.getPrefix());
        SMOutputElement root = frag.addElement(textmd,"textMD");
        outputContents(writer,textmd,root);
        frag.closeRoot();
    }*/

    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        outputGenericElement (ns, ELEM_BDOI, parent);
        outputGenericElement (ns, ELEM_BII, parent);
        outputGenericElement (ns, ELEM_ICM, parent);
        outputGenericElement (ns, ELEM_IAM, parent);
        outputGenericElement (ns, ELEM_CH, parent);
        outputGenericElement (ns, ELEM_EXT, parent);
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }
    	
	protected void dispatch (String localName, XMLStreamReader reader) 
	            throws XMLStreamException, XmlContentException {
	    if (match (reader, this, 
	            localName, ELEM_BDOI,
	            BasicDigitalObjectInformation.class))
	        return;
	    // Similarly for other top-level elements of MIX
        if (match (reader, this, 
                localName, ELEM_BII,
                BasicImageInformation.class))
            return;
        if (match (reader, this, 
                localName, ELEM_ICM,
                ImageCaptureMetadata.class))
            return;
        if (match (reader, this, 
                localName, ELEM_IAM,
                ImageAssessmentMetadata.class))
            return;
        if (match (reader, this, 
                localName, ELEM_CH,
                ChangeHistory.class))
            return;
        if (match (reader, this, 
                localName, ELEM_EXT,
                Extension.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
	}
	

}
