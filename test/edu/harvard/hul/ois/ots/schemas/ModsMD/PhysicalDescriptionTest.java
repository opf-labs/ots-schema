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

import edu.harvard.hul.ois.ots.schemas.ModsMD.PhysicalDescription;
import edu.harvard.hul.ois.ots.schemas.XmlContent.GenericElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;

public class PhysicalDescriptionTest extends junit.framework.TestCase {

    public void testComposition () throws Exception {
        PhysicalDescription pd = new PhysicalDescription ();
        pd.setLang ("eng");
        StringElement elem = pd.addForm ("f1");
        assertEquals ("form", elem.getName ());
        assertEquals ("f1", elem.toString ());
        
        elem = pd.addInternetMediaType ("imt");
        assertEquals ("internetMediaType", elem.getName ());
        assertEquals ("imt", elem.toString ());
        
        elem = pd.addReformattingQuality ("rfq");
        assertEquals ("reformattingQuality", elem.getName ());
        assertEquals ("rfq", elem.toString ());
        
        List<StringElement> lst = pd.getDigitalOrigins ();
        assertTrue (lst.isEmpty ());
        
        elem = pd.addDigitalOrigin ("do");
        assertEquals ("digitalOrigin", elem.getName ());
        assertEquals ("do", elem.toString ());
        
        elem = pd.addNote ("note1");
        assertEquals ("note", elem.getName ());
        assertEquals ("note1", elem.toString ());
        
        assertEquals ("eng", pd.getLang ());
        List<GenericElement> elems = pd.getAllElements ();
        
        assertTrue (elems.size () == 5);
        elem = (StringElement) elems.get(0);
        assertEquals ("form", elem.getName ());
        elem = (StringElement) elems.get(1);
        assertEquals ("internetMediaType", elem.getName ());
        elem = (StringElement) elems.get(2);
        assertEquals ("reformattingQuality", elem.getName ());
        elem = (StringElement) elems.get(3);
        assertEquals ("do", elem.toString ());  // just to be different
        elem = (StringElement) elems.get(4);
        assertEquals ("note1", elem.toString ());
        
    }

}
