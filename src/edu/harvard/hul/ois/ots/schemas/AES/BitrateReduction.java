/**
 * @author Gary McGath
 *
 * Copyright (c) 
 * 2010 by the President and Fellows of Harvard College
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
 */
package edu.harvard.hul.ois.ots.schemas.AES;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class BitrateReduction extends AESObject {

    private final static String ELEMENTNAME = "bitrateReduction";

    private final static String ELEM_NAME = "codecName";
    private final static String ELEM_NAMEVSN = "codecNameVersion";
    private final static String ELEM_CREATORAPP = "codecCreatorApplication";
    private final static String ELEM_CREATORAPPVSN = "codecCreatorApplicationVersion";
    private final static String ELEM_QUALITY = "codecQuality";
    private final static String ELEM_DATARATE = "dataRate";
    private final static String ELEM_DATARATEMODE = "dataRateMode";
    private final static String ELEM_PACKETLIST = "packetList";

    /* Permitted values for codecQuality */
    public final static String[] VALUES_QUALITY = { "LOSSY",
            "CODE_REGENERATING" };

    /* Permitted values for dataRateMode */
    public final static String[] VALUES_DATARATEMODE = { "FIXED",
            "VARIABLE" };

    public BitrateReduction (XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        this.name = ELEMENTNAME;
        defineRestrictions();
        parse (reader);
    }

    /** Constructor with no arguments. */
    public BitrateReduction () {
        super();
        this.name = ELEMENTNAME;
        defineRestrictions();
    }

    /** Define restrictions */
    private void defineRestrictions() {
        addRestriction(ELEM_QUALITY, VALUES_QUALITY);
        addRestriction(ELEM_DATARATEMODE, VALUES_DATARATEMODE);
    }
    
    public StringElement getCodecName () {
        return (StringElement) getField (ELEM_NAME);
    }
    
    public void setCodecName (String s) throws XmlContentException {
        setField (ELEM_NAME, s);
    }
    
    public StringElement getCodecNameVersion () {
        return (StringElement) getField (ELEM_NAMEVSN);
    }
    
    public void setCodecNameVersion (String s) throws XmlContentException {
        setField (ELEM_NAMEVSN, s);
    }
    
    public StringElement getCodecCreatorApplication () {
        return (StringElement) getField (ELEM_CREATORAPP);
    }
    
    public void setCodecCreatorApplication (String s) throws XmlContentException {
        setField (ELEM_CREATORAPP, s);
    }
    
    public StringElement getCodecCreatorApplicationVersion () {
        return (StringElement) getField (ELEM_CREATORAPPVSN);
    }
    
    public void setCodecCreatorApplicationVersion (String s) 
                      throws XmlContentException {
        setField (ELEM_CREATORAPPVSN, s);
    }
    
    public StringElement getCodecQuality () {
        return (StringElement) getField (ELEM_QUALITY);
    }
    
    public void setCodecQuality (String s) throws XmlContentException {
        setField (ELEM_QUALITY, s);
    }
    
    public StringElement getDataRate () {
        return (StringElement) getField (ELEM_DATARATE);
    }
    
    public void setDataRate (String s) throws XmlContentException {
        setField (ELEM_DATARATE, s);
    }
    
    public StringElement getDataRateMode () {
        return (StringElement) getField (ELEM_DATARATEMODE);
    }
    
    public void setDataRateMode (String s) throws XmlContentException {
        setField (ELEM_DATARATEMODE, s);
    }
    
    public PacketList getPacketList () {
        return (PacketList) getField (ELEM_PACKETLIST);
    }
    
    public void setPacketList (PacketList p) throws XmlContentException {
        setField (ELEM_PACKETLIST, p);
    }
    
    
    

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = outputElement (ns, name, parent); 
        outputGenericElement (ns, ELEM_NAME, mainElem);
        outputGenericElement (ns, ELEM_NAMEVSN, mainElem);
        outputGenericElement (ns, ELEM_CREATORAPP, mainElem);
        outputGenericElement (ns, ELEM_CREATORAPPVSN, mainElem);
        outputGenericElement (ns, ELEM_QUALITY, mainElem);
        outputGenericElement (ns, ELEM_DATARATE, mainElem);
        outputGenericElement (ns, ELEM_DATARATEMODE, mainElem);
        outputGenericElement (ns, ELEM_PACKETLIST, mainElem);
    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    @Override
    protected void dispatch (String localName, XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        if (match(reader, this, localName, ELEM_NAME, StringElement.class))
            return;
        if (match(reader, this, localName, ELEM_NAMEVSN, StringElement.class))
            return;
        if (match(reader, this, localName, ELEM_CREATORAPP, StringElement.class))
            return;
        if (match(reader, this, localName, ELEM_CREATORAPPVSN, StringElement.class))
            return;
        if (match(reader, this, localName, ELEM_QUALITY, StringElement.class))
            return;
        if (match(reader, this, localName, ELEM_DATARATE, StringElement.class))
            return;
        if (match(reader, this, localName, ELEM_DATARATEMODE, StringElement.class))
            return;
        if (match(reader, this, localName, ELEM_PACKETLIST, PacketList.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
