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
package edu.harvard.hul.ois.ots.schemas.premis;

import java.util.Date;


import org.custommonkey.xmlunit.XMLTestCase;


public class PremisDateTest extends XMLTestCase{

	public void testDate () throws Exception {
  
    	
    	Date date = new Date(Long.parseLong("1241018981000"));
    	PremisDate pDate = new PremisDate(date);

    	assertEquals("2009-04-29T11:29:41-04:00", pDate.toString());
        

    }
    
  
}
