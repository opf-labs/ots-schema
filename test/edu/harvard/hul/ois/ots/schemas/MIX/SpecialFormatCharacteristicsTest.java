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

import edu.harvard.hul.ois.ots.schemas.MIX.Djvu;
import edu.harvard.hul.ois.ots.schemas.MIX.JPEG2000;
import edu.harvard.hul.ois.ots.schemas.MIX.MrSID;
import edu.harvard.hul.ois.ots.schemas.MIX.SpecialFormatCharacteristics;


public class SpecialFormatCharacteristicsTest extends  junit.framework.TestCase {

    public void testComposition () throws Exception {
        SpecialFormatCharacteristics sfc = new SpecialFormatCharacteristics ();
        JPEG2000 jp2 = new JPEG2000 ();
        sfc.setJPEG2000(jp2);
        MrSID mrsid = new MrSID();
        sfc.setMrSID(mrsid);
        Djvu djvu = new Djvu ();
        sfc.setDjvu (djvu);
        
        jp2 = sfc.getJPEG2000();
        assertEquals ("JPEG2000", jp2.getName ());
        mrsid = sfc.getMrSID();
        assertEquals ("MrSID", mrsid.getName ());
        djvu = sfc.getDjvu();
        assertEquals ("Djvu", djvu.getName ());
    }
}
