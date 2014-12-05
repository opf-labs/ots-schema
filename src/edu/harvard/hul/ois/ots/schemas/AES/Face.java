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

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class Face extends AESObject {

    private final static String ELEMENTNAME = "face";

    private final static String ELEM_TIMELINE = "timeline";
    private final static String ELEM_REGION = "region";

    private final static String ATT_DIRECTION = "direction";
    private final static String ATT_AUDIOOBJREF = "audioObjectRef";
    private final static String ATT_LABEL = "label";
    
    /* Permitted values for attribute direction */
    private final static String[] VALUES_DIR = { "FRONT",
        "BACK",
        "FORWARD",
        "REVERSE",
        "A_PASS",
        "B_PASS",
        "C_PASS",
        "D_PASS",
        "NONE" };

    public Face (XMLStreamReader reader) 
                 throws XMLStreamException, XmlContentException {
        this.name = ELEMENTNAME;
        parse (reader);
    }
    
    public Face () {
        this.name = ELEMENTNAME;
    }
    
    /** Return the direction attribute */
    public String getDirection() {
        return getAttribute(ATT_DIRECTION);
    }

    /** Set the direction attribute. If the argument is not a permitted
     *  value, does nothing. */
    public void setDirection (String t) {
        boolean ok = false;
        for (String s : VALUES_DIR) {
            if (s.equals (t)) {
                ok = true;
                break;
            }
        }
        if (ok)
            setAttribute(ATT_DIRECTION, t);
    }
    
    /** Return the timeline */
    public TimeRange getTimeline () {
        return (TimeRange) getField(ELEM_TIMELINE);
    }
    
    /** Set the timeline as a TimeRange object 
     * @throws XmlContentException */
    public void setTimeline (TimeRange tr) throws XmlContentException {
        setField (ELEM_TIMELINE, tr);
    }
    
    /** Return the regions as a List of FaceRegions */
    @SuppressWarnings("unchecked")
	public List<FaceRegion> getRegions () {
        return genericList (getList(ELEM_REGION));
    }
    
    /** Add a region 
     * @throws XmlContentException */
    public void addRegion (FaceRegion fr) throws XmlContentException {
        addToList(fr, ELEM_REGION);
    }
    
    /** Return the audioObjectRef attribute */
    public String getAudioObjectRef () {
        return getAttribute (ATT_AUDIOOBJREF);
    }
    
    /** Set the audioObjectRef attribute. This is an IDREF, but we don't
     *  do any uniqueness checking. */
    public void setAudioObjectRef (String t) {
        setAttribute (ATT_AUDIOOBJREF, t);
    }

    /** Return the label attribute */
    public String getLabel () {
        return getAttribute (ATT_LABEL);
    }
    
    /** Set the label attribute.  */
    public void setLabel (String t) {
        setAttribute (ATT_LABEL, t);
    }


    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = outputElement (ns, name, parent); 
        outputGenericElement (ns, ELEM_TIMELINE, mainElem);
        outputList (ns, ELEM_REGION, mainElem);
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    protected void dispatch(String localName, XMLStreamReader reader)
                  throws XMLStreamException, XmlContentException {
        if (match(reader, this, localName, ELEM_TIMELINE, TimeRange.class))
            return;
        if (matchList(reader, this, localName, ELEM_REGION, FaceRegion.class))
            return;
        throw new XmlContentException("Unknown element name " + localName);
    }

}
