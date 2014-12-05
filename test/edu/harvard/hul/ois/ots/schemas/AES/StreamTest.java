/**********************************************************************
 * Copyright (c) 2010 by the President and Fellows of Harvard College
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA.
 *
 * Contact information
 *
 * Office for Information Systems
 * Harvard University Library
 * Harvard University
 * Cambridge, MA  02138
 * (617)495-3724
 * hulois@hulmail.harvard.edu
 **********************************************************************/

package edu.harvard.hul.ois.ots.schemas.AES;

import java.util.List;


public class StreamTest  extends junit.framework.TestCase {

    public void testComposition () throws Exception {
        Stream strm = new Stream ();
        ChannelAssignment ca = new ChannelAssignment ();
        ca.setChannelNum(7);
        ca.setLeftRightPosition(40.0);
        strm.setChannelAssignment (ca);
        ConditionNote cn = new ConditionNote ();
        cn.setNote ("C sharp");
        TimeRange tr = new TimeRange ("timeRange");
        EditUnitNumber t = new EditUnitNumber (1, "duration");
        tr.setStartTime (t);
        cn.setTimeRange (tr);
        cn.setCreationDate ("2009");
        strm.addConditionNote(cn);
        
        ca = strm.getChannelAssignment();
        assertEquals (40.0, ca.getLeftRightPosition ());
        List<ConditionNote> cns = strm.getConditionNotes ();
        cn = cns.get(0);
        assertEquals ("C sharp", cn.getNote ().toString ());
        assertEquals ("2009", cn.getCreationDate().toString ());
    }
}
