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

import edu.harvard.hul.ois.ots.schemas.ModsMD.CopyInformation;
import edu.harvard.hul.ois.ots.schemas.XmlContent.GenericElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;

public class CopyInformationTest extends junit.framework.TestCase {

    public void testComposition () throws Exception {
        CopyInformation ci = new CopyInformation ();
        ci.addForm ("form1");
        ci.addSubLocation ("sublocation1");
        ci.addShelfLocator ("shelf1");
        ci.addShelfLocator ("shelf2");
        ci.addElectronicLocator ("zap1");
        ci.addEnumerationAndChronology ("eandc");
        ci.addNote ("note1");
        
        List<GenericElement> elems = ci.getAllElements();
        assertTrue (elems.size() == 7);
        StringElement elem = (StringElement) elems.get(0);
        assertEquals ("form", elem.getName ());
        assertEquals ("form1", elem.toString ());
        elem = (StringElement) elems.get(1);
        assertEquals ("subLocation", elem.getName ());
        assertEquals ("sublocation1", elem.toString ());
        elem = (StringElement) elems.get(2);
        assertEquals ("shelfLocator", elem.getName ());
        assertEquals ("shelf1", elem.toString ());
        elem = (StringElement) elems.get(3);
        assertEquals ("shelfLocator", elem.getName ());
        assertEquals ("shelf2", elem.toString ());
        elem = (StringElement) elems.get(4);
        assertEquals ("electronicLocator", elem.getName ());
        assertEquals ("zap1", elem.toString ());
        elem = (StringElement) elems.get(5);
        assertEquals ("enumerationAndChronology", elem.getName ());
        assertEquals ("eandc", elem.toString ());
        elem = (StringElement) elems.get(6);
        assertEquals ("note", elem.getName ());
        assertEquals ("note1", elem.toString ());
        
        List<StringElement> lst = ci.getForms ();
        elem = lst.get(0);
        assertEquals ("form1", elem.toString ());
        
        lst = ci.getSubLocations ();
        elem = lst.get(0);
        assertEquals ("sublocation1", elem.toString ());
        
        lst = ci.getShelfLocators ();
        elem = lst.get(0);
        assertEquals ("shelf1", elem.toString ());
        elem = lst.get(1);
        assertEquals ("shelf2", elem.toString ());
        
        lst = ci.getElectronicLocators ();
        elem = lst.get(0);
        assertEquals ("zap1", elem.toString ());
        
        lst = ci.getEnumerationsAndChronologies ();
        elem = lst.get(0);
        assertEquals ("eandc", elem.toString ());

    }
}
