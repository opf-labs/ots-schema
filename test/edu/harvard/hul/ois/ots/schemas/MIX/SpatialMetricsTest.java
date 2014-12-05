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

import edu.harvard.hul.ois.ots.schemas.MIX.SpatialMetrics;
import edu.harvard.hul.ois.ots.schemas.XmlContent.Rational;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;


public class SpatialMetricsTest extends junit.framework.TestCase  {

    public void testComposition () throws Exception  {
        SpatialMetrics sm = new SpatialMetrics ();
        boolean exCaught = false;
        try {
            sm.setSamplingFrequencyPlane("baddata");
        }
        catch (XmlContentException e) {
            exCaught = true;
        }
        assertTrue (exCaught);
        
        exCaught = false;
        try {
            sm.setSamplingFrequencyUnit("morebaddata");
        }
        catch (XmlContentException e) {
            exCaught = true;
        }
        assertTrue (exCaught);
        
        sm.setSamplingFrequencyPlane("object plane");
        sm.setSamplingFrequencyUnit("in.");
        Rational r1 = new Rational (4, 3);
        Rational r2 = new Rational (5, 6);
        sm.setXSamplingFrequency(r1);
        sm.setYSamplingFrequency(r2);
        
        assertEquals (r2, sm.getYSamplingFrequency().toRational());
        assertEquals (r1, sm.getXSamplingFrequency().toRational());
        assertEquals ("in.", sm.getSamplingFrequencyUnit().toString());
        assertEquals ("object plane", sm.getSamplingFrequencyPlane().toString());
    }
}
