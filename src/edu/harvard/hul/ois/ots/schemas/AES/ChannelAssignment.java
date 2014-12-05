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

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

/** The channelAssignment element has no content, only attributes */
public class ChannelAssignment extends AESObject {

    private final static String ELEMENTNAME = "channelAssignment";

    private final static String ATT_CHANNUM = "channelNum";
    private final static String ATT_LRPOS = "leftRightPosition";
    private final static String ATT_FRPOS = "frontRearPosition";
    
    public ChannelAssignment (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        this.name = ELEMENTNAME;
        parse (reader);
    }

    /** Constructor with no arguments. */
    public ChannelAssignment () {
        super();
        this.name = ELEMENTNAME;
    }

    /** Return the channelNum attribute */
    public Integer getChannelNum () {
        try {
            return Integer.parseInt(getAttribute (ATT_CHANNUM));
        }
        catch (Exception e) {
            return null;
        }
    }
    
    /** Set the channelNum attribute.  */
    public void setChannelNum (Integer i) {
        setAttribute (ATT_CHANNUM, i.toString());
    }

    /** Return the leftRightPosition attribute */
    public Double getLeftRightPosition () {
        try {
            return Double.parseDouble(getAttribute (ATT_LRPOS));
        }
        catch (Exception e) {
            return null;
        }
    }
    
    /** Set the leftRightPosition attribute.  */
    public void setLeftRightPosition (Double i) {
        setAttribute (ATT_LRPOS, i.toString());
    }

    /** Return the frontRearPosition attribute */
    public Double getFrontRearPosition () {
        try {
            return Double.parseDouble(getAttribute (ATT_FRPOS));
        }
        catch (Exception e) {
            return null;
        }
    }
    
    /** Set the frontRearPosition attribute.  */
    public void setFrontRearPosition (Double i) {
        setAttribute (ATT_FRPOS, i.toString());
    }


    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = outputElement (ns, name, parent); 
        // That's all, folks.
    }

}
