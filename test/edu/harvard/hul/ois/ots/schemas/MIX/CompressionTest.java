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

import edu.harvard.hul.ois.ots.schemas.MIX.Compression;
import edu.harvard.hul.ois.ots.schemas.XmlContent.Rational;


public class CompressionTest extends  junit.framework.TestCase {

    public void testComposition () throws Exception {
        Compression cmp = new Compression ();
        cmp.setCompressionScheme ("flate");
        cmp.setCompressionSchemeLocalList ("http://example");
        cmp.setCompressionSchemeLocalValue ("xyz");
        cmp.setCompressionRatio(new Rational (2, 3));
        
        assertEquals ("flate", cmp.getCompressionScheme().toString());
        assertEquals ("http://example", cmp.getCompressionSchemeLocalList().toString());
        assertEquals ("xyz", cmp.getCompressionSchemeLocalValue ().toString());
        Rational ratio = cmp.getCompressionRatio ().toRational();
        assertNotNull (ratio);
        assertEquals (2,ratio.getNumerator());
        assertEquals (3, ratio.getDenominator());
    }

}
