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

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class AppSpecificData extends StringElement {

    private final static String ELEMENTNAME = "appSpecificData";

    private final static String ATT_APPVSN = "appVersion";
    
    public AppSpecificData (String content) {
        super(content, ELEMENTNAME);
    }
    
    public AppSpecificData (XMLStreamReader reader) 
            throws XMLStreamException, XmlContentException {
        super(reader);
        this.name = ELEMENTNAME;
        parse (reader);
    }

    /** Set the appVersion attribute */
    public String getAppVersion () {
        return getAttribute (ATT_APPVSN);
    }
    
    /** Return the appVersion attribute */
    public void setAppVersion (String vsn) {
        setAttribute (ATT_APPVSN, vsn);
    }
    
    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        super.output (ns, parent);

    }

    @Override
    public boolean validate() {
        return true;
    }


}
