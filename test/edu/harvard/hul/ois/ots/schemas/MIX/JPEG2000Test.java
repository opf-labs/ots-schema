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
import edu.harvard.hul.ois.ots.schemas.MIX.EncodingOptions;
import edu.harvard.hul.ois.ots.schemas.MIX.JPEG2000;
import junit.framework.TestCase;


public class JPEG2000Test extends TestCase {

    public void testComposition () throws Exception {
        JPEG2000 jp2 = new JPEG2000 ();
        jp2.setCodecCompliance (new CodecCompliance ());
        jp2.setEncodingOptions (new EncodingOptions ());
        CodecCompliance cc = jp2.getCodecCompliance();
        assertNotNull (cc);
        EncodingOptions eo = jp2.getEncodingOptions();
        assertNotNull (eo);
    }
}
