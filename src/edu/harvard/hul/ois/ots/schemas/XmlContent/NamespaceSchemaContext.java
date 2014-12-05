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


public class NamespaceSchemaContext {
	
	private String schemaLocation;
	private String namespaceURI;
	private String prefix;
		
	public NamespaceSchemaContext(String namespaceURI, String prefix, String schemaLocation) {
		this.schemaLocation = schemaLocation;
		this.namespaceURI = namespaceURI;
		setPrefix(prefix);
	}
	
	public String getSchemaLocation() {
		return schemaLocation;
	}

	public String getNamespaceURI() {
		return namespaceURI;
	}

	public String getPrefix() {
		return prefix;
	}
	
	public void setSchemaLocation(String schemaLocation) {
		this.schemaLocation = schemaLocation;
	}

	public void setNamespaceURI(String namespaceURI) {
		this.namespaceURI = namespaceURI;
	}

	public void setPrefix(String prefix) {
		//force empty prefix to be null
		if(prefix != null && prefix.length() == 0) {
			prefix = null;
		}
		this.prefix = prefix;
	}

	public boolean equals(Object namespaceSchemaContext) {

		NamespaceSchemaContext context = (NamespaceSchemaContext) namespaceSchemaContext;

		//if both values are null, or if both values are not null and match then return true
		if((this.schemaLocation == null && context.schemaLocation==null) ||
				((this.schemaLocation != null && context.schemaLocation != null) && 
				this.schemaLocation.equals(context.schemaLocation))) {
			//continue
		}
		else {
			return false;
		}
		
		if((this.namespaceURI == null && context.namespaceURI==null) ||
				((this.namespaceURI != null && context.namespaceURI != null) && 
				this.namespaceURI.equals(context.namespaceURI))) {
			//continue
		}
		else {
			return false;
		}
		
		if((this.prefix == null && context.prefix==null) ||
				((this.prefix != null && context.prefix != null) && 
				this.prefix.equals(context.prefix))) {
			//continue
		}
		else {
			return false;
		}
		
		return true;
	}	
}
