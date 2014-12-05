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
package edu.harvard.hul.ois.ots.schemas.XmlContent;

/** This class defines Exceptions which may be thrown by the XML content
 *  packages. Subclasses will be defined as they prove useful. */
@SuppressWarnings("serial")
public class XmlContentException extends Exception {

    /** Constructor with no message */
    public XmlContentException () {
        super ();
    }
    
    /** Constructor with message */
    public XmlContentException (String message) {
        super (message);
    }
    
    /** Constructor with Throwable */
    public XmlContentException (Throwable cause) {
        super (cause);
    }
    
    /** Constructor with message and Throwable */
    public XmlContentException (String message, Throwable cause) {
        super (message, cause);
    }
    
}
