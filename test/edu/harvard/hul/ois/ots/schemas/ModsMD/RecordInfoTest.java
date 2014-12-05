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

public class RecordInfoTest extends junit.framework.TestCase {

    public void testComposition () throws Exception {
        RecordInfo ri = new RecordInfo ();
        StringElement stelem = ri.addRecordContentSource ("abcd");
        stelem.setAttribute ("authority", "Vulcan High Council");
        ri.addRecordCreationDate ("2008");
        ri.addRecordChangeDate ("2009");
        ri.addRecordIdentifier ("xyz");
        ri.addLanguageOfCataloging("eng");
        ri.addRecordOrigin("2468");
        ri.addDescriptionStandard("9876");
        
        List<StringElement> stlist = ri.getRecordContentSources ();
        stelem = stlist.get(0);
        assertEquals ("abcd", stelem.toString ());
        assertEquals ("Vulcan High Council", stelem.getAttribute ("authority"));

        stlist = ri.getRecordCreationDates ();
        assertTrue (stlist.size() == 1);
        stelem = stlist.get(0);
        assertEquals ("2008", stelem.toString ());

        stlist = ri.getRecordChangeDates ();
        assertTrue (stlist.size() == 1);
        stelem = stlist.get(0);
        assertEquals ("2009", stelem.toString ());
        
        stlist = ri.getRecordIdentifiers ();
        assertTrue (stlist.size() == 1);
        stelem = stlist.get(0);
        assertEquals ("xyz", stelem.toString ());

        stlist = ri.getLanguagesOfCataloging();
        assertTrue (stlist.size() == 1);
        stelem = stlist.get(0);
        assertEquals ("eng", stelem.toString ());
        
        stlist = ri.getRecordOrigins ();
        assertTrue (stlist.size() == 1);
        stelem = stlist.get(0);
        assertEquals ("2468", stelem.toString ());
        
        stlist = ri.getDescriptionStandards ();
        assertTrue (stlist.size() == 1);
        stelem = stlist.get(0);
        assertEquals ("9876", stelem.toString ());



    }
}
