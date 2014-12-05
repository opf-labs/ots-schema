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


public class SubjectTest  extends junit.framework.TestCase  {

    public void testComposition () throws Exception {
        Subject sj = new Subject ();
        sj.addTopic ("astronomy");
        sj.addGeographic ("Earth");
        sj.addTemporal ("shrimp");
        TitleInfo ti = new TitleInfo ();
        sj.addTitleInfo (ti);
        Name n = new Name ();
        sj.addName (n);
        sj.addGeographicCode ("02138");
        HierarchicalGeographic g = new HierarchicalGeographic();
        sj.addHierarchicalGeographic (g);
        Cartographics c = new Cartographics ();
        sj.addCartographics (c);
        sj.addOccupation ("astral engineer");
        sj.addGenre ("science fiction");
        
        List<StringElement> stlist = sj.getTopics ();
        StringElement stelem = stlist.get(0);
        assertEquals ("astronomy", stelem.toString ());
        
        stlist = sj.getGeographics ();
        stelem = stlist.get(0);
        assertEquals ("Earth", stelem.toString ());
        
        stlist = sj.getTemporals ();
        stelem = stlist.get(0);
        assertEquals ("shrimp", stelem.toString ());
        
        List<TitleInfo> tis = sj.getTitleInfos ();
        ti = tis.get(0);
        assertNotNull (ti);
        
        List<Name> names = sj.getNames ();
        n = names.get(0);
        assertNotNull (n);
        
        stlist = sj.getGeographicCodes ();
        stelem = stlist.get(0);
        assertEquals ("02138", stelem.toString ());

        List<HierarchicalGeographic> hgs = sj.getHierarchicalGeographics ();
        g = hgs.get(0);
        assertNotNull (g);
        
        List<Cartographics> cs = sj.getCartographics ();
        c = cs.get(0);
        assertNotNull (c);
        
        stlist = sj.getOccupations ();
        stelem = stlist.get(0);
        assertEquals ("astral engineer", stelem.toString ());

        stlist = sj.getGenres ();
        stelem = stlist.get(0);
        assertEquals ("science fiction", stelem.toString ());

    }
}
