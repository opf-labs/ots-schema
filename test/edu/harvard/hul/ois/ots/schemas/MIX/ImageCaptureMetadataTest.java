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

import edu.harvard.hul.ois.ots.schemas.MIX.DigitalCameraCapture;
import edu.harvard.hul.ois.ots.schemas.MIX.GeneralCaptureInformation;
import edu.harvard.hul.ois.ots.schemas.MIX.ImageCaptureMetadata;
import edu.harvard.hul.ois.ots.schemas.MIX.ScannerCapture;
import edu.harvard.hul.ois.ots.schemas.MIX.SourceInformation;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;


public class ImageCaptureMetadataTest extends  junit.framework.TestCase {

    public void testComposition () throws Exception  {
        ImageCaptureMetadata icm = new ImageCaptureMetadata();
        icm.setSourceInformation (new SourceInformation ());
        icm.setScannerCapture (new ScannerCapture ());
        icm.setGeneralCaptureInformation(new GeneralCaptureInformation ());
        icm.setDigitalCameraCapture (new DigitalCameraCapture());
        boolean exThrown = false;
        try {
            icm.setOrientation("garbage");
        }
        catch (XmlContentException e) {
            exThrown = true;
        }
        assertTrue (exThrown);
        icm.setOrientation ("normal, rotated ccw 90°");
        icm.setMethodology ("nonsense");
        
        assertNotNull (icm.getSourceInformation());
        assertNotNull (icm.getGeneralCaptureInformation());
        assertNotNull (icm.getScannerCapture());
        assertNotNull (icm.getDigitalCameraCapture());
        assertEquals ("normal, rotated ccw 90°", icm.getOrientation().toString());
        assertEquals ("nonsense", icm.getMethodology().toString());
    }
}
