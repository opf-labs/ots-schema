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

public class EncodingAgent {
	
	private String value;
	private String role;
	
	public EncodingAgent() { }
	
	public EncodingAgent(String value, String role) throws InvalidTextMDValue {
		this.value = value;
		setRole(role);
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) throws InvalidTextMDValue {
		if(EnumeratedValues.ROLE.contains(role)) {
			this.role = role;
		}
		else {
			throw new InvalidTextMDValue(role);
		}
	}
}
