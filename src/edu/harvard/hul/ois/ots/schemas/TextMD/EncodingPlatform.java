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

public class EncodingPlatform {
	
	private String value;
	private String linebreak;
	
	public EncodingPlatform() { }
	
	public EncodingPlatform(String value, String linebreak) throws InvalidTextMDValue {
		this.value = value;
		setLinebreak(linebreak);
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLinebreak() {
		return linebreak;
	}
	public void setLinebreak(String linebreak) throws InvalidTextMDValue {
		if(EnumeratedValues.LINEBREAK.contains(linebreak)) {
			this.linebreak = linebreak;
		}
		else {
			throw new InvalidTextMDValue(linebreak);
		}
	}

}
