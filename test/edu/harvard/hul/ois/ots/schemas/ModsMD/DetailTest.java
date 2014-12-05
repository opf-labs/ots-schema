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
import edu.harvard.hul.ois.ots.schemas.XmlContent.GenericElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;

public class DetailTest extends junit.framework.TestCase {

    public void testComposition () throws Exception {
        Detail dt = new Detail ();
        dt.addNumber ("number1");
        dt.addTitle ("title1");
        dt.addCaption ("caption1");
        dt.addTitle ("title2");
        dt.setAttribute("type", "type2");
        
        assertEquals ("type2", dt.getAttribute ("type"));
        List<GenericElement> elems = dt.getAllElements ();
        assertTrue (elems.size() == 4);
        StringElement elem = (StringElement) elems.get(0);
        assertEquals ("number", elem.getName ());
        assertEquals ("number1", elem.toString ());
        
        elem = (StringElement) elems.get(1);
        assertEquals ("title", elem.getName ());
        assertEquals ("title1", elem.toString ());
        
        elem = (StringElement) elems.get(2);
        assertEquals ("caption", elem.getName ());
        assertEquals ("caption1", elem.toString ());
        
        elem = (StringElement) elems.get(3);
        assertEquals ("title", elem.getName ());
        assertEquals ("title2", elem.toString ());
        
        List<StringElement> lst = dt.getNumbers ();
        assertTrue (lst.size () == 1);
        lst = dt.getCaptions ();
        assertTrue (lst.size () == 1);
        lst = dt.getTitles ();
        assertTrue (lst.size () == 2);
        elem = lst.get(0);
        assertEquals ("title1", elem.toString ());
        elem = lst.get(1);
        assertEquals ("title2", elem.toString ());
    }
}
