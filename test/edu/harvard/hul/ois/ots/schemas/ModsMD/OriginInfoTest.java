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

package edu.harvard.hul.ois.ots.schemas.ModsMD;

import java.util.List;

import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;


public class OriginInfoTest extends junit.framework.TestCase {

    public void testComposition () throws Exception {
        OriginInfo oi = new OriginInfo ();
        Place p = new Place ();
        oi.addPlace (p);
        oi.addPublisher ("O'Reilly");
        oi.addDateIssued ("2008");
        oi.addDateCreated ("2007");
        oi.addDateCaptured ("2007.5");
        oi.addDateValid ("1999");
        oi.addDateModified ("2002");
        oi.addCopyrightDate ("2010");
        oi.addDateOther ("2000 BC");
        oi.addEdition ("First");
        boolean exCaught = false;
        try {
            oi.addIssuance ("invalid");
        }
        catch (XmlContentException e) {
            exCaught = true;
        }
        assertTrue (exCaught);
        oi.addIssuance ("monographic");
        oi.addFrequency ("60Hz");
        
        List<Place> places = oi.getPlaces ();
        p = places.get(0);
        assertNotNull (p);
        
        List<StringElement> stlist = oi.getPublishers ();
        StringElement stelem = stlist.get(0);
        assertEquals ("O'Reilly", stelem.toString ());
        
        stlist = oi.getDatesIssued ();
        stelem = stlist.get(0);
        assertEquals ("2008", stelem.toString ());
        
        stlist = oi.getDatesCreated ();
        stelem = stlist.get(0);
        assertEquals ("2007", stelem.toString ());

        stlist = oi.getDatesCaptured ();
        stelem = stlist.get(0);
        assertEquals ("2007.5", stelem.toString ());

        stlist = oi.getDatesValid ();
        stelem = stlist.get(0);
        assertEquals ("1999", stelem.toString ());
        
        stlist = oi.getDatesModified ();
        stelem = stlist.get(0);
        assertEquals ("2002", stelem.toString ());
        
        stlist = oi.getCopyrightDates ();
        stelem = stlist.get(0);
        assertEquals ("2010", stelem.toString ());
        
        stlist = oi.getDatesOther ();
        stelem = stlist.get(0);
        assertEquals ("2000 BC", stelem.toString ());
        
        stlist = oi.getEditions ();
        stelem = stlist.get(0);
        assertEquals ("First", stelem.toString ());
    }
}
