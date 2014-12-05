/**********************************************************************
 * Copyright (c) 2010 by the President and Fellows of Harvard College
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA.
 *
 * Contact information
 *
 * Office for Information Systems
 * Harvard University Library
 * Harvard University
 * Cambridge, MA  02138
 * (617)495-3724
 * hulois@hulmail.harvard.edu
 **********************************************************************/

package edu.harvard.hul.ois.ots.schemas.AES;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.harvard.hul.ois.ots.schemas.StaxUtils.RootXmlHelper;
import edu.harvard.hul.ois.ots.schemas.XmlContent.DateElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.IntegerElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputDocument;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

public class AudioObject extends AESObject {

    private final static String ELEMENTNAME = "audioObject";
    
    private final static String ELEM_FORMAT = "format";
    private final static String ELEM_PHYSPROPS = "physicalProperties";
    private final static String ELEM_ASD = "appSpecificData";
    private final static String ELEM_ENCODING = "audioDataEncoding";
    private final static String ELEM_BYTEORDER = "byteOrder";
    private final static String ELEM_1STOFFSET = "firstSampleOffset";
    private final static String ELEM_AUDIOBLOCKSIZE = "audioDataBlockSize";
    private final static String ELEM_1STVALIDBYTE = "firstValidByteOfBlock";
    private final static String ELEM_LASTVALIDBYTE = "lastValidByteOfBlock";
    private final static String ELEM_USE = "use";
    private final static String ELEM_PRIMARYID = "primaryIdentifier";
    private final static String ELEM_SECONDARYID = "secondaryIdentifier";
    private final static String ELEM_FCHECKSUM = "fileChecksum";
    private final static String ELEM_SCHECKSUM = "soundDataChecksum";
    private final static String ELEM_OBJLINK = "objectLink";
    private final static String ELEM_FACE = "face";
    private final static String ELEM_FMTLIST = "formatList";
    private final static String ELEM_CREATIONDATE = "objectCreationDate";
    
    private final static String ATT_TITLE = "title";
    private final static String ATT_ADFLAG = "analogDigitalFlag";
    private final static String ATT_GEN = "generation";
    private final static String ATT_DISP = "disposition";
    private final static String ATT_SCHEMAVSN = "schemaVersion";

    protected static final NamespaceSchemaContext aesNSC = 
        new NamespaceSchemaContext (XMLNS, SCHEMA_PREFIX, SCHEMA_LOCATION);
    
    /* namespace schema context for AES60, which is imported for just
     * one silly obscure case */
    protected static final NamespaceSchemaContext aes60NSC =
        new NamespaceSchemaContext(AES60NS, AES60_PREFIX, AES60_SCHEMA_LOCATION);

    /* Permitted values for messageDigestAlgorithm */
    private final static String[] VALUES_BYTEORDER = { "LITTLE_ENDIAN",
            "BIG_ENDIAN" };
    

    /* Permitted values for attribute analogDigitalFlag */
    private final static String[] VALUES_ADFLAG = { "ANALOG",
            "PHYS_DIGITAL", "FILE_DIGITAL" };

    private boolean isRoot = false;

    /** Constructor from XML. */
    public AudioObject(XMLStreamReader reader) throws XMLStreamException,
            XmlContentException {
        name = ELEMENTNAME;
        defineRestrictions();
        parse(reader);
    }

    /**
     * Constructor from XML, with isRoot. The isRoot parameter affects whether
     * schema attributes are written out.
     */
    public AudioObject(XMLStreamReader reader, boolean isRoot)
            throws XMLStreamException, XmlContentException {
        name = ELEMENTNAME;
        this.isRoot = isRoot;
        defineRestrictions();
        parse(reader);
    }

    /**
     * No-argument constructor. This can be used when the caller is composing a
     * Mix element from its constituent classes.
     */
    public AudioObject() {
        super();
        isRoot = false;
        name = ELEMENTNAME;
    }

    public AudioObject(boolean isRoot) {
        this.isRoot = isRoot;
        name = ELEMENTNAME;
    }

    /** Define restrictions */
    private void defineRestrictions() {
        addRestriction(ELEM_BYTEORDER, VALUES_BYTEORDER);
    }

    /** Return the title attribute */
    public String getTitle() {
        return getAttribute(ATT_TITLE);
    }

    public void setTitle(String t) {
        setAttribute(ATT_TITLE, t);
    }

    /** Return the analogDigitalFlag attribute */
    public String getAnalogDigitalFlag() {
        return getAttribute(ATT_ADFLAG);
    }

    /** Return the format element */
    public Format getFormat () {
        return (Format) getField (ELEM_FORMAT);
    }
    
    /** Set the format element 
     * @throws XmlContentException */
    public void setFormat (Format fmt) throws XmlContentException {
        setField (ELEM_FORMAT, fmt);
    }
    
    public PhysProps getPhysicalProperties () {
        return (PhysProps) getField (ELEM_PHYSPROPS);
    }
    
    public void setPhysicalProperties (PhysProps pp) throws XmlContentException {
        setField (ELEM_PHYSPROPS, pp);
    }
    
    @SuppressWarnings("unchecked")
	public List<AppSpecificData> getAppSpecificData () {
        return genericList (getList(ELEM_PHYSPROPS));
    }
    
    public void addAppSpecificData (AppSpecificData asd) throws XmlContentException {
        addToList(asd, ELEM_ASD);
    }
    
    public StringElement getAudioDataEncoding () {
        return (StringElement) getField (ELEM_ENCODING);
    }
    
    public void setAudioDataEncoding (String ade) throws XmlContentException {
        setField (ELEM_ENCODING, ade);
    }
    
    public StringElement getByteOrder () {
        return (StringElement) getField (ELEM_BYTEORDER);
    }
    
    public void setByteOrder (String b) throws XmlContentException {
        setField (ELEM_BYTEORDER, b);
    }
    
    public IntegerElement getFirstSampleOffset () {
        return (IntegerElement) getField (ELEM_1STOFFSET);
    }
    
    public void setFirstSampleOffset (int off) throws XmlContentException {
        setField (ELEM_1STOFFSET, off);
    }
    
    public IntegerElement getAudioDataBlockSize () {
        return (IntegerElement) getField (ELEM_AUDIOBLOCKSIZE);
    }
    
    public void setAudioDataBlockSize (int adbs) throws XmlContentException {
        setField (ELEM_AUDIOBLOCKSIZE, adbs);
    }
    
    public IntegerElement getFirstValidByteOfBlock () {
        return (IntegerElement) getField (ELEM_1STVALIDBYTE);
    }
    
    public void setFirstValidByteOfBlock (int n) throws XmlContentException {
        setField (ELEM_1STVALIDBYTE, n);
    }
    
    public IntegerElement getLastValidByteOfBlock () {
        return (IntegerElement) getField (ELEM_LASTVALIDBYTE);
    }
    
    public void setLastValidByteOfBlock (int n) throws XmlContentException {
        setField (ELEM_LASTVALIDBYTE, n);
    }
    
    @SuppressWarnings("unchecked")
	public List<Use> getUses () {
        return genericList (getList (ELEM_USE));
    }
    
    public void addUse (Use use) throws XmlContentException {
        addToList (use, ELEM_USE);
    }
    
    /** A minor kludge to allow clearing the Use list because we want to
     *  replace an existing value. */
    public void resetUses () {
        resetList (ELEM_USE);
    }
    
    public Identifier getPrimaryIdentifier () {
        return (Identifier) getField (ELEM_PRIMARYID);
    }
    
    public void setPrimaryIdentifier (Identifier id) throws XmlContentException {
        setField (ELEM_PRIMARYID, id);
    }
    
    @SuppressWarnings("unchecked")
	public List<Identifier> getSecondaryIdentifiers () {
        return genericList (getList (ELEM_SECONDARYID));
    }
    
    public void addSecondaryIdentifier (Identifier id) throws XmlContentException {
        addToList(id, ELEM_SECONDARYID);
    }
    
    public Checksum getFileChecksum () {
        return (Checksum) getField (ELEM_FCHECKSUM);
    }
    
    public void setFileChecksum (Checksum c) throws XmlContentException {
        setField (ELEM_FCHECKSUM, c);
    }
    
    public Checksum getSoundDataChecksum () {
        return (Checksum) getField (ELEM_SCHECKSUM);
    }
    
    public void setSoundDataChecksum (Checksum c) throws XmlContentException {
        setField (ELEM_SCHECKSUM, c);
    }
    
    public StringElement getObjectLink () {
        return (StringElement) getField(ELEM_OBJLINK);
    }
    
    public void setObjectLink (String s) throws XmlContentException {
        setField (ELEM_OBJLINK, s);
    }
    
    @SuppressWarnings("unchecked")
	public List<Face> getFaces () {
        return genericList (getList (ELEM_FACE));
    }
    
    public void addFace (Face f) throws XmlContentException {
        addToList (f, ELEM_FACE);
    }
    
    public FormatList getFormatList () {
        return (FormatList) getField (ELEM_FMTLIST);
    }
    
    public void setFormatList (FormatList f) throws XmlContentException {
        setField (ELEM_FMTLIST, f);
    }
    
    public DateElement getObjectCreationDate () {
        return (DateElement) getField (ELEM_CREATIONDATE);
    }
    
    public void setObjectCreationDate (String dt) throws XmlContentException {
        setField (ELEM_CREATIONDATE, dt);
    }
    
    /** Set the analogDigitalFlag attribute. If the argument is not a permitted
     *  value, does nothing. */
    public void setAnalogDigitalFlag(String t) {
        boolean ok = false;
        for (String s : VALUES_ADFLAG) {
            if (s.equals (t)) {
                ok = true;
                break;
            }
        }
        if (ok)
            setAttribute(ATT_ADFLAG, t);
    }

    /** Return the generation attribute */
    public String getGeneration() {
        return getAttribute(ATT_GEN);
    }

    public void setGeneration(String t) {
        setAttribute(ATT_GEN, t);
    }

    /** Return the disposition attribute */
    public String getDisposition() {
        return getAttribute(ATT_DISP);
    }

    public void setDisposition(String t) {
        setAttribute(ATT_DISP, t);
    }

    /** Return the schemaVersion attribute */
    public String getSchemaVersion() {
        return getAttribute(ATT_SCHEMAVSN);
    }

    public void setSchemaVersion(String t) {
        setAttribute(ATT_SCHEMAVSN, t);
    }

    /** Top-level output method. */
    @Override
    public void output(XMLStreamWriter writer)
                   throws XMLStreamException {
        if(isRoot) {
            SMOutputDocument doc = SMOutputFactory.createOutputDocument(writer);            
            SMNamespace aesNS = doc.getNamespace(XMLNS,aesNSC.getPrefix());
            SMOutputElement root = doc.addElement(aesNS,"audioObject");       
            RootXmlHelper.output(writer,getAllNamespaceSchemaContexts(),root);      
            Iterator<String> attrIter = attributes.keySet().iterator();
            while (attrIter.hasNext ()) {
                String key = attrIter.next ();
                String value = attributes.get (key);
                // Don't write schema contexts again
                if (key.indexOf("schemaLocation") > 0) {
                    continue;
                }
                root.addAttribute (key, value);
            }
            headlessOutput(aesNS,root);
            doc.closeRoot();
        }
        else {
            SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
            SMNamespace aesNS = frag.getNamespace(XMLNS,aesNSC.getPrefix());
            SMOutputElement root = frag.addElement(aesNS,"audioObject");
    
            String value = attributes.get ("ID");
            if(value != null)
                root.addAttribute ("ID", value);
            value = attributes.get ("version");
            if(value != null)
                root.addAttribute ("version", value);
            value = attributes.get ("analogDigitalFlag");
            if(value != null)
                root.addAttribute ("analogDigitalFlag", value);
            value = attributes.get ("schemaVersion");
            if(value != null)
                root.addAttribute ("schemaVersion", value);
            value = attributes.get ("disposition");
            if(value != null)
                root.addAttribute ("disposition", value);
            value = attributes.get ("title");
            if(value != null)
                root.addAttribute ("title", value);    
            value = attributes.get ("generaion");
            if(value != null)
                root.addAttribute ("generation", value);
            
            headlessOutput(aesNS,root);
            frag.closeRoot();
        }
    }
    
    /** I don't think this should ever be called. It does nothing. */
    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
    }
    
    
    public void headlessOutput(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        //outputAttrs (parent);
        outputGenericElement (ns, ELEM_FORMAT, parent);
        outputGenericElement (ns, ELEM_PHYSPROPS, parent);
        outputList (ns, ELEM_ASD, parent);
        outputGenericElement (ns, ELEM_ENCODING, parent);
        outputGenericElement (ns, ELEM_BYTEORDER, parent);
        outputGenericElement (ns, ELEM_1STOFFSET, parent);
        outputGenericElement (ns, ELEM_AUDIOBLOCKSIZE, parent);
        outputGenericElement (ns, ELEM_1STVALIDBYTE, parent);
        outputGenericElement (ns, ELEM_LASTVALIDBYTE, parent);
        outputList (ns, ELEM_USE, parent);
        outputGenericElement (ns, ELEM_PRIMARYID, parent);
        outputList (ns, ELEM_SECONDARYID, parent);
        outputGenericElement(ns, ELEM_FCHECKSUM, parent);
        outputGenericElement (ns, ELEM_SCHECKSUM, parent);
        outputGenericElement (ns, ELEM_OBJLINK, parent);
        outputList (ns, ELEM_FACE, parent);
        outputGenericElement (ns, ELEM_FMTLIST, parent);
        outputGenericElement (ns, ELEM_CREATIONDATE, parent);

    }

    @Override
    public boolean validate() {
        try {
            checkRestriction (getField (ELEM_BYTEORDER), ELEM_BYTEORDER);
        }
        catch (XmlContentException e) {
            return false;
        }
        return true;
    }

    protected void dispatch(String localName, XMLStreamReader reader)
            throws XMLStreamException, XmlContentException {
        if (match(reader, this, localName, ELEM_FORMAT, Format.class))
            return;
        if (match(reader, this, localName, ELEM_PHYSPROPS, PhysProps.class))
            return;
        if (matchList(reader, this, localName, ELEM_ASD, AppSpecificData.class))
            return;
        if (match(reader, this, localName, ELEM_ENCODING, StringElement.class))
            return;
        if (match(reader, this, localName, ELEM_BYTEORDER, StringElement.class))
            return;
        if (match(reader, this, localName, ELEM_1STOFFSET, IntegerElement.class))
            return;
        if (match(reader, this, localName, ELEM_AUDIOBLOCKSIZE,
                IntegerElement.class))
            return;
        if (match(reader, this, localName, ELEM_1STVALIDBYTE,
                IntegerElement.class))
            return;
        if (match(reader, this, localName, ELEM_LASTVALIDBYTE,
                IntegerElement.class))
            return;
        if (match(reader, this, localName, ELEM_LASTVALIDBYTE,
                IntegerElement.class))
            return;
        if (matchList(reader, this, localName, ELEM_USE, Use.class))
            return;
        if (match(reader, this, localName, ELEM_PRIMARYID, Identifier.class))
            return;
        if (matchList(reader, this, localName, ELEM_SECONDARYID,
                Identifier.class))
            return;
        if (match (reader, this, localName, ELEM_FCHECKSUM,
                Checksum.class))
            return;
        if (match (reader, this, localName, ELEM_SCHECKSUM,
                Checksum.class))
            return;
        if (match(reader, this, localName, ELEM_OBJLINK,
                StringElement.class))
            return;
        if (matchList (reader, this, localName, ELEM_FACE,
                Face.class))
            return;
        if (match (reader, this, localName, ELEM_FMTLIST,
                FormatList.class))
            return;
        throw new XmlContentException("Unknown element name " + localName);
    }

    /** Returns a list of all namespace schema contexts in or under
     *  this element. Returns null if not overridden. */
    @Override
    public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts() {
        ArrayList<NamespaceSchemaContext> lst = new ArrayList<NamespaceSchemaContext>(1);
        lst.add (aesNSC);
        return lst;
    }

    /** Returns the namespace schema context for this element. 
     *  Must be overridden unless the namespace context is null. */
    @Override
    public NamespaceSchemaContext getNamespaceSchemaContext() {
        return aesNSC;
    }
    
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

}
