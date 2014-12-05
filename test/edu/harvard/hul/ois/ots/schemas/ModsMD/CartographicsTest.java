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

import edu.harvard.hul.ois.ots.schemas.ModsMD.Cartographics;
import edu.harvard.hul.ois.ots.schemas.XmlContent.GenericElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;


public class CartographicsTest extends junit.framework.TestCase {

    public void testComposition () throws Exception {
        Cartographics c = new Cartographics ();
        c.setScale ("scale1");
        c.setProjection ("proj1");
        c.addCoordinates ("coord1");
        c.addCoordinates ("coord2");
        
        StringElement elem = c.getScale ();
        assertEquals ("scale", elem.getName ());
        assertEquals ("scale1", elem.toString ());
        
        elem = c.getProjection ();
        assertEquals ("projection", elem.getName ());
        assertEquals ("proj1", elem.toString ());
        
        List<StringElement> coords = c.getCoordinates ();
        elem = coords.get(0);
        assertEquals ("coordinates", elem.getName ());
        assertEquals ("coord1", elem.toString ());
        elem = coords.get(1);
        assertEquals ("coord2", elem.toString ());
        
        List<GenericElement> lst = c.getAllElements();
        assertTrue (lst.size () == 4);
        elem = (StringElement) lst.get(0);
        assertEquals ("scale", elem.getName ());
        elem = (StringElement) lst.get(1);
        assertEquals ("projection", elem.getName ());
        elem = (StringElement) lst.get(2);
        assertEquals ("coord1", elem.toString ());
        elem = (StringElement) lst.get(3);
        assertEquals ("coord2", elem.toString ());
    }

}
