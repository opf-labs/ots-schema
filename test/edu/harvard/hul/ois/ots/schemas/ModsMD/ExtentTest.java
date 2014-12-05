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

import edu.harvard.hul.ois.ots.schemas.ModsMD.Extent;
import edu.harvard.hul.ois.ots.schemas.XmlContent.IntegerElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;

public class ExtentTest extends junit.framework.TestCase {

    public void testComposition () throws Exception {
        Extent ex = new Extent ();
        ex.setStart ("st");
        ex.setEnd ("en");
        boolean exCaught = false;
        try {
            ex.setTotal (-150);
        }
        catch (Exception e) {
            exCaught = true;
        }
        assertTrue (exCaught);
        ex.setTotal (20);

        StringElement elem = ex.getStart ();
        assertEquals ("start", elem.getName ());
        assertEquals ("st", elem.toString ());
        
        elem = ex.getEnd ();
        assertEquals ("end", elem.getName ());
        assertEquals ("en", elem.toString ());
        
        IntegerElement ielem = ex.getTotal ();
        assertEquals ("total", ielem.getName ());
        assertEquals ((Integer) 20, ielem.toInteger()); 
        
    }
}
