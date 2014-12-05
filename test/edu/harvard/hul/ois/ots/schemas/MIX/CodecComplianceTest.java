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

import edu.harvard.hul.ois.ots.schemas.MIX.CodecCompliance;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;


public class CodecComplianceTest extends  junit.framework.TestCase {

    public void testComposition () throws Exception {
        CodecCompliance cc = new CodecCompliance ();
        cc.setCodec ("abc");
        cc.setCodecVersion ("1.0");
        cc.setCodestreamProfile ("profile1");
        cc.setComplianceClass ("cclass");
        
        StringElement elem = cc.getCodec ();
        assertEquals ("codec", elem.getName ());
        assertEquals ("abc", elem.toString ());
        
        elem = cc.getCodecVersion ();
        assertEquals ("codecVersion", elem.getName());
        assertEquals ("1.0", elem.toString ());
        
        elem = cc.getCodestreamProfile ();
        assertEquals ("codestreamProfile", elem.getName ());
        assertEquals ("profile1", elem.toString ());
        
        elem = cc.getComplianceClass ();
        assertEquals ("complianceClass", elem.getName ());
        assertEquals ("cclass", elem.toString ());
    }
}
