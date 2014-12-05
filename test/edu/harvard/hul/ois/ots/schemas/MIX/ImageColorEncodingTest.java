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

import edu.harvard.hul.ois.ots.schemas.MIX.BitsPerSample;
import edu.harvard.hul.ois.ots.schemas.MIX.Colormap;
import edu.harvard.hul.ois.ots.schemas.MIX.ImageColorEncoding;
import edu.harvard.hul.ois.ots.schemas.MIX.PrimaryChromaticities;
import edu.harvard.hul.ois.ots.schemas.MIX.WhitePoint;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;


public class ImageColorEncodingTest extends  junit.framework.TestCase {

    public void testComposition () throws Exception  {
        ImageColorEncoding ice = new ImageColorEncoding ();
        ice.setBitsPerSample(new BitsPerSample());
        boolean exceptionSeen = false;
        try{
            ice.setSamplesPerPixel (-10);
        }
        catch (XmlContentException e) {
            exceptionSeen = true;
        }
        assertTrue (exceptionSeen);
        ice.setSamplesPerPixel (3);
        
        exceptionSeen = false;
        try {
            ice.addExtraSamples("nonsense");
        }
        catch (XmlContentException e) {
            exceptionSeen = true;
        }
        assertTrue (exceptionSeen);
        ice.addExtraSamples ("unspecified data");
        ice.setColormap(new Colormap());
        ice.addGrayResponseCurve(10);
        ice.addGrayResponseCurve(20);
        ice.addGrayResponseCurve(30);

        exceptionSeen = false;
        try {
            ice.setGrayResponseUnit("nonsense");
        }
        catch (XmlContentException e) {
            exceptionSeen = true;
        }
        assertTrue (exceptionSeen);
        String gruValue = "Number represents tenths of a unit";
        ice.setGrayResponseUnit(gruValue);
        
        ice.addWhitePoint (new WhitePoint());
        ice.addPrimaryChromaticities (new PrimaryChromaticities());
        
        List<Integer> grc = ice.getGrayResponseCurves();
        assertNotNull(grc);
        assertTrue (grc.size() == 3);
        assertNotNull (ice.getBitsPerSample());
        assertEquals ((Integer) 3, ice.getSamplesPerPixel ().toInteger());
        List<StringElement> exs = ice.getExtraSamples ();
        assertEquals ("unspecified data", exs.get(0).toString());
        assertEquals (gruValue, ice.getGrayResponseUnit().toString());
        List<WhitePoint> wp = ice.getWhitePoints();
        assertTrue (wp.size() == 1);
        List<PrimaryChromaticities> pc = ice.getPrimaryChromaticities();
        assertTrue (pc.size() == 1);
        
        // Do this after testing values, since the result of adding an illegal
        // value on the existing list is undefined.
        exceptionSeen = false;
        try {
            ice.addGrayResponseCurve(-1);
        }
        catch (XmlContentException e) {
            exceptionSeen = true;
        }
        assertTrue (exceptionSeen);
        
    }
}
