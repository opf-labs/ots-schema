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
package edu.harvard.hul.ois.ots.schemas.TextMD;

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class InvalidTextMDValue extends XmlContentException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -3622565872502544200L;

	/** Constructor with message */
    public InvalidTextMDValue (String elementName) {
        super (elementName + " is an invalid value");
    }

}
