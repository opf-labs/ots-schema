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
package edu.harvard.hul.ois.ots.schemas.VideoMD;

import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;

/**
 * B&W
 * Color
 * Grayscale
 * B&W with grayscale sequences
 * B&W with color sequences
 * Grayscale with B&W sequences
 * Grayscale with color sequences
 * Color with B&W sequences
 * Color with grayscale sequences
 * Other
 */
public class Color extends StringElement {

    public Color(String content, String name) {
        super(content, name);
    }

    public boolean validate() {
        return true;
    }

}
