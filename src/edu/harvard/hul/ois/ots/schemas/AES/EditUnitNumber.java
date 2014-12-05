/**
 * @author Gary McGath
 *
 * Copyright (c) 
 * 2011 by the President and Fellows of Harvard College
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

import edu.harvard.hul.ois.ots.schemas.XmlContent.LongElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/** This is an element definition imported from the AES60 schema which
 *  is used by the AES audio schema. */
public class EditUnitNumber extends LongElement {

    private final static String ATT_EDIT_RATE = "editRate";
    private final static String ATT_FAC_NUM = "factorNumerator";
    private final static String ATT_FAC_DENOM = "factorDenominator";
    
    public EditUnitNumber (XMLStreamReader rdr) 
        throws XmlContentException, XMLStreamException {
        super (rdr);    
    }
    
    
    public EditUnitNumber (int n, String name) {
        super (n, name);
    }
    
    public void setEditRate (int n) {
        setAttribute (ATT_EDIT_RATE, Integer.toString(n));
    }
    
    public String getEditRate () {
        return getAttribute (ATT_EDIT_RATE);
    }
    
    /** The values of factorNumerator and factorDenominator provide a
     * correction factor to the base number of frames or samples
     * per second. I don't know what that means either.
     */
    public void setFactorNumerator (int n) {
        setAttribute (ATT_FAC_NUM, Integer.toString(n));
    }
    
    public String getFactorNumerator () {
        return getAttribute (ATT_FAC_NUM);
    }
    
    
    public void setFactorDenominator (int n) {
        setAttribute (ATT_FAC_DENOM, Integer.toString(n));
    }
    
    public String getFactorDenominator () {
        return getAttribute (ATT_FAC_DENOM);
    }
}
