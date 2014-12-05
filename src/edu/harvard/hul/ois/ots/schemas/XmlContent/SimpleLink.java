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
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import edu.harvard.hul.ois.ots.schemas.XmlComponent;

public class SimpleLink implements XmlComponent {
	
	public enum Actuate {onLoad,onRequest,other,none}
	public static final String XMLNS = "http://www.w3.org/1999/xlink";
	public static final String PREFIX = "xlink";
		
	public static NamespaceSchemaContext nsSchemaContext = new NamespaceSchemaContext(XMLNS,PREFIX,null);
	
	private String type;
	private String href;
	private String role;
	private String arcRole;
	private String title;
	private String show;
	private Actuate actuate;
	
	public SimpleLink() { }
	
	public SimpleLink(XMLStreamReader reader) throws XMLStreamException {
		this();
		parse(reader);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getArcRole() {
		return arcRole;
	}
	public void setArcRole(String arcRole) {
		this.arcRole = arcRole;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public Actuate getActuate() {
		return actuate;
	}
	public void setActuate(Actuate actuate) {
		this.actuate = actuate;
	}

	public void output(XMLStreamWriter writer) throws XMLStreamException {
		if(type != null) {
			writer.writeAttribute(PREFIX,XMLNS,"type",type);
		}
		if(href != null) {
			writer.writeAttribute(PREFIX,XMLNS,"href",href.toString());
		}
		if(role != null) {
			writer.writeAttribute(PREFIX,XMLNS,"role",role);
		}
		if(arcRole != null) {
			writer.writeAttribute(PREFIX,XMLNS,"arcrole",arcRole);
		}
		if(title != null) {
			writer.writeAttribute(PREFIX,XMLNS,"title",title);
		}
		if(show != null) {
			writer.writeAttribute(PREFIX,XMLNS,"show",show);
		}
		if(actuate != null) {
			writer.writeAttribute(PREFIX,XMLNS,"actuate",actuate.toString());
		}
	}

	public SimpleLink parse(XMLStreamReader reader) throws XMLStreamException {
		if(reader.getAttributeCount() > 0) {
			type = reader.getAttributeValue(XMLNS, "type");
			href = reader.getAttributeValue(XMLNS, "href");
			role = reader.getAttributeValue(XMLNS, "role");
			arcRole = reader.getAttributeValue(XMLNS, "arcRole");
			title = reader.getAttributeValue(XMLNS, "title");
			show = reader.getAttributeValue(XMLNS, "show");
			String actuateVal =  reader.getAttributeValue(XMLNS, "actuate");
			if(actuateVal != null) {
				actuate = Actuate.valueOf(actuateVal);
			}
		}
		return this;
	}

	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NamespaceSchemaContext getNamespaceSchemaContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRoot(boolean root) {
		//do nothing
	}
}
