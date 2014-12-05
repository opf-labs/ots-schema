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

import edu.harvard.hul.ois.ots.schemas.MIX.GPSData;
import edu.harvard.hul.ois.ots.schemas.MIX.GPSDestLatitude;
import edu.harvard.hul.ois.ots.schemas.MIX.GPSDestLongitude;
import edu.harvard.hul.ois.ots.schemas.MIX.GPSLatitude;
import edu.harvard.hul.ois.ots.schemas.MIX.GPSLongitude;
import edu.harvard.hul.ois.ots.schemas.XmlContent.Rational;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;


public class GPSDataTest extends  junit.framework.TestCase {
    public void testComposition () throws Exception  {
        GPSData g = new GPSData ();
        g.setGpsVersionID("99.9");
        
        g.setGpsLatitudeRef ("N");
        GPSLatitude lat = new GPSLatitude ();
        lat.setDegrees(new Rational (45, 1));
        lat.setMinutes (new Rational (10, 1));
        lat.setSeconds (new Rational (0, 1));
        g.setGPSLatitude (lat);

        // Check that invalid values are being caught
        int nCaught = 0;
        try {
            g.setGpsLongitudeRef("Invalid");
        }
        catch (XmlContentException e) {
            ++nCaught;
        }
        try {
            g.setGpsMeasureMode("Also Invalid");
        }
        catch (XmlContentException e) {
            ++nCaught;
        }
        try {
            g.setGpsTrackRef("This is invalid too");
        }
        catch (XmlContentException e) {
            ++nCaught;
        }
        try {
            g.setGpsDestBearingRef("More improper text");
        }
        catch (XmlContentException e) {
            ++nCaught;
        }
        assertTrue (nCaught == 4);
        
        g.setGpsLongitudeRef ("E");
        GPSLongitude lon = new GPSLongitude ();
        lon.setDegrees(new Rational (45, 1));
        g.setGPSLongitude (lon);
        
        g.setGpsAltitudeRef("Sea level");
        g.setGpsAltitude(new Rational (1000, 2));
        
        g.setGpsTimeStamp ("2009");
        g.setGpsSatellites ("Sputnik");
        g.setGpsStatus("A");
        
        g.setGpsMeasureMode("2-dimensional measurement");
        g.setGpsDOP(new Rational (1, 1));
        g.setGpsSpeedRef("K");
        g.setGpsSpeed(new Rational (90, 1));
        g.setGpsTrackRef("T");
        g.setGpsTrack(new Rational (75, 2));
        g.setGpsImgDirectionRef("M");
        g.setGpsImgDirection(new Rational (85,5));
        g.setGpsMapDatum ("wxyz");

        g.setGpsDestLatitudeRef ("N");
        GPSDestLatitude dlat = new GPSDestLatitude ();
        dlat.setDegrees(new Rational (43, 1));
        dlat.setMinutes (new Rational (20, 1));
        g.setGPSDestLatitude (dlat);

        g.setGpsDestLongitudeRef ("E");
        GPSDestLongitude dlon = new GPSDestLongitude ();
        dlon.setDegrees(new Rational (83, 1));
        dlon.setMinutes (new Rational (90, 0));
        g.setGPSDestLongitude (dlon);

        g.setGpsDestBearingRef ("T");
        g.setGpsDestBearing (new Rational (44, 55));
        
        g.setGpsDestDistanceRef ("K");
        g.setGpsDestDistance(new Rational (18, 75));
        
        g.setGpsProcessingMethod("Crystal Ball");
        g.setGpsAreaInformation("Area info");
        g.setGpsDateStamp("2004-01-02");
        g.setGpsDifferential("Differential correction applied");

        
        // Now check if it's all there the way we put it
        assertEquals ("99.9", g.getGpsVersionID().toString());
        
        assertEquals ("N", g.getGpsLatitudeRef ().toString());
        lat = g.getGPSLatitude ();
        assertEquals (new Rational (45, 1), lat.getDegrees ().toRational());
        assertEquals (new Rational (10, 1), lat.getMinutes ().toRational());
        assertEquals (new Rational (0, 1), lat.getSeconds ().toRational());
        g.setGPSLatitude (lat);

        assertEquals ("E", g.getGpsLongitudeRef ().toString());
        lon = g.getGPSLongitude ();
        assertEquals(new Rational (45, 1), lon.getDegrees().toRational());
        assertNull (lon.getMinutes ());
        assertNull (lon.getSeconds ());
        
        assertEquals ("Sea level", g.getGpsAltitudeRef().toString());
        assertEquals (new Rational (1000, 2), g.getGpsAltitude().toRational());
        
        assertEquals ("2009", g.getGpsTimeStamp ().toString());
        assertEquals ("Sputnik", g.getGpsSatellites ().toString());
        assertEquals ("A", g.getGpsStatus().toString());
        
        assertEquals ("2-dimensional measurement", g.getGpsMeasureMode().toString());
        assertEquals (new Rational (1, 1), g.getGpsDOP().toRational());
        assertEquals ("K", g.getGpsSpeedRef().toString());
        assertEquals (new Rational (90, 1), g.getGpsSpeed().toRational());
        assertEquals ("T", g.getGpsTrackRef().toString());
        assertEquals (new Rational (75, 2), g.getGpsTrack().toRational());
        assertEquals ("M", g.getGpsImgDirectionRef().toString());
        assertEquals (new Rational (85,5), g.getGpsImgDirection().toRational());
        assertEquals ("wxyz", g.getGpsMapDatum ().toString());

        assertEquals ("N", g.getGpsDestLatitudeRef ().toString());
        dlat = g.getGPSDestLatitude ();
        assertEquals (new Rational (43, 1), dlat.getDegrees().toRational());
        assertEquals (new Rational (20, 1), dlat.getMinutes ().toRational());
        assertNull (dlat.getSeconds ());

        assertEquals ("E", g.getGpsDestLongitudeRef ().toString());
        dlon = g.getGPSDestLongitude ();
        assertEquals (new Rational (83, 1), dlon.getDegrees().toRational());
        assertEquals (new Rational (90, 0), dlon.getMinutes ().toRational());
        assertNull (dlon.getSeconds ());

        assertEquals ("T", g.getGpsDestBearingRef ().toString());
        assertEquals (new Rational (44, 55), g.getGpsDestBearing ().toRational());
        
        assertEquals ("K", g.getGpsDestDistanceRef ().toString());
        assertEquals (new Rational (18, 75), g.getGpsDestDistance().toRational());
        
        assertEquals ("Crystal Ball", g.getGpsProcessingMethod().toString());
        assertEquals ("Area info", g.getGpsAreaInformation().toString());
        assertEquals ("2004-01-02", g.getGpsDateStamp().toString());
        assertEquals ("Differential correction applied", g.getGpsDifferential().toString());

    }

}
