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

import java.util.List;

import edu.harvard.hul.ois.ots.schemas.MIX.ColorProfile;
import edu.harvard.hul.ois.ots.schemas.MIX.Component;
import edu.harvard.hul.ois.ots.schemas.MIX.IccProfile;
import edu.harvard.hul.ois.ots.schemas.MIX.PhotometricInterpretation;
import edu.harvard.hul.ois.ots.schemas.MIX.ReferenceBlackWhite;
import edu.harvard.hul.ois.ots.schemas.MIX.YCbCr;
import edu.harvard.hul.ois.ots.schemas.MIX.YCbCrSubSampling;


public class PhotometricInterpretationTest extends junit.framework.TestCase {

    public void testComposition () throws Exception {
        PhotometricInterpretation pi = new PhotometricInterpretation ();
        pi.setColorSpace("RGB");
        ColorProfile cp = new ColorProfile ();
        IccProfile iccp = new  IccProfile();
        iccp.setIccProfileName ("Profile1");
        cp.setIccProfile(iccp);
        pi.setColorProfile (cp);
        
        cp = pi.getColorProfile ();
        iccp = cp.getIccProfile ();
        assertEquals ("Profile1", iccp.getIccProfileName().toString());
        assertEquals ("RGB", pi.getColorSpace().toString ());
        
        YCbCr y = new YCbCr ();
        YCbCrSubSampling ss = new YCbCrSubSampling ();
        y.setYCbCrSubSampling(ss);
        pi.setYCbCr(y);
        
        y = pi.getYCbCr();
        ss = y.getYCbCrSubSampling();
        assertNotNull (ss);
        
        ReferenceBlackWhite rbw = new ReferenceBlackWhite ();
        Component c1 = new Component ();
        rbw.addComponent(c1);
        pi.addReferenceBlackWhite (rbw);
        
        List<ReferenceBlackWhite> rbws = pi.getReferenceBlackWhites ();
        assertTrue (rbws.size() == 1);
        List<Component> comps = rbws.get(0).getComponents();
        assertTrue (comps.size() == 1);
    }
}
