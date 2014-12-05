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

import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;



public class ChecksumTest extends junit.framework.TestCase {

    public void testComposition () throws Exception  {
        Checksum cs = new Checksum ("fileChecksum");
        final String val = "13A4";
        final String kind = "MD5";
        final String dat = "2009-07-04";
        cs.setChecksumValue(val);
        cs.setChecksumKind(kind);
        cs.setChecksumCreateDate(dat);
        
        StringElement elem = cs.getChecksumValue ();
        assertEquals ("checksumValue", elem.getName());
        assertEquals (val, elem.toString ());

        elem = cs.getChecksumKind ();
        assertEquals ("checksumKind", elem.getName());
        assertEquals (kind, elem.toString ());

        elem = cs.getChecksumCreateDate ();
        assertEquals ("checksumCreateDate", elem.getName());
        assertEquals (dat, elem.toString ());

        assertTrue ("fileChecksum".equals (cs.getName()));
    }
}
