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

package edu.harvard.hul.ois.ots.schemas.MIX;

import edu.harvard.hul.ois.ots.schemas.MIX.TargetData;
import edu.harvard.hul.ois.ots.schemas.MIX.TargetID;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

import java.util.List;

public class TargetDataTest extends junit.framework.TestCase {

    public void testComposition () throws Exception  {
        TargetData td = new TargetData ();
        TargetID id = new TargetID();
        id.setTargetName ("Robin Hood");
        td.addTargetID(id);
        id = new TargetID();
        id.setTargetName ("Little John");
        td.addTargetID(id);
        
        List<TargetID> ids = td.getTargetIDs();
        assertTrue (ids.size() == 2);
        id = ids.get(0);
        assertEquals ("Robin Hood", id.getTargetName().toString());
        id = ids.get(1);
        assertEquals ("Little John", id.getTargetName().toString());
        
        td.addTargetType ("external");
        List<StringElement> types = td.getTargetTypes();
        assertTrue (types.size() == 1);
        StringElement targetType = types.get(0);
        assertEquals ("external", targetType.toString ());
        
        boolean exCaught = false;
        try {
            td.addTargetType ("this isn't allowed");
        }
        catch (XmlContentException e) {
            exCaught = true;
        }
        assertTrue (exCaught);
        
        td.addExternalTarget ("http://xyz.example");
        List<StringElement> targets = td.getExternalTargets();
        StringElement target = targets.get(0);
        assertEquals ("http://xyz.example", target.toString());
    }
}
