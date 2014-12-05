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

import edu.harvard.hul.ois.ots.schemas.ModsMD.Detail;
import edu.harvard.hul.ois.ots.schemas.ModsMD.Extent;
import edu.harvard.hul.ois.ots.schemas.ModsMD.Part;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;


public class PartTest extends junit.framework.TestCase {

    public void testComposition () throws Exception {
        Part p = new Part ();
        Detail dt = new Detail ();
        p.addDetail (dt);
        Extent ex = new Extent ();
        p.addExtent (ex);
        p.addDate("2009");
        p.addText ("Some text");
        
        List<Detail> dts = p.getDetails ();
        dt = dts.get(0);
        assertEquals ("detail", dt.getName ());
        
        List<Extent> exts = p.getExtents ();
        ex = exts.get(0);
        assertEquals ("extent", ex.getName ());
        
        List<StringElement> elems = p.getDates ();
        StringElement elem = elems.get(0);
        assertEquals ("date", elem.getName());
        
        elems = p.getTexts ();
        elem = elems.get(0);
        assertEquals ("text", elem.getName ());
    }
}
