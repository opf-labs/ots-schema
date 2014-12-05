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

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public interface XmlContent {
	
	public enum ContentType {mets,premis,premisobject,premisevent,premisagent,premisrights,huladmin,mods,audio,video,image,text,document}
	
	public static final String SCHEMA_INSTANCE_PREFIX = "xsi";
	
	/**
	 * return the namespace and schema context for this xml
	 * @return
	 */
	public NamespaceSchemaContext getNamespaceSchemaContext();
	
	/**
	 * return the namespace and schema context for this xml and any child XML sections
	 * belonging to a different schema. 
	 * @return
	 */	
	public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts();
	
	/**
	 * output this xml to the provided XMLStreamWriter
	 * @param writer
	 * @throws XMLStreamException
	 */
	public void output(XMLStreamWriter writer) throws XMLStreamException;
	
	/**
	 * validates this XML object
	 * @return
	 */
	public boolean validate();
	
	public void setRoot(boolean root);

}
