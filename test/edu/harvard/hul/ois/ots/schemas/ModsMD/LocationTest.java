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

import edu.harvard.hul.ois.ots.schemas.ModsMD.HoldingExternal;
import edu.harvard.hul.ois.ots.schemas.ModsMD.HoldingSimple;
import edu.harvard.hul.ois.ots.schemas.ModsMD.Location;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;

public class LocationTest extends junit.framework.TestCase {

    public void testComposition () throws Exception {
        Location loc = new Location ();
        StringElement elem = loc.addPhysicalLocation ("Earth");
        elem = loc.addURL("http://foo.foo");
        loc.addURL("http://bar.bar");
        loc.setHoldingSimple (new HoldingSimple ());
        loc.setHoldingExternal(new HoldingExternal ());
        List<StringElement> lst = loc.getPhysicalLocations ();
        assertTrue (lst.size () == 1);
        elem = lst.get(0);
        assertEquals ("physicalLocation", elem.getName ());
        assertEquals ("Earth", elem.toString ());
        
        lst = loc.getURLs ();
        assertTrue (lst.size () == 2);
        
        HoldingSimple hs = loc.getHoldingSimple ();
        assertNotNull (hs);
        
        HoldingExternal he = loc.getHoldingExternal ();
        assertNotNull (he);
    }
}
