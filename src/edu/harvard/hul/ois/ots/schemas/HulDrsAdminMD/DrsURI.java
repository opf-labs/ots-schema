package edu.harvard.hul.ois.ots.schemas.HulDrsAdminMD;

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

public class DrsURI {
	
	public static enum URIType {SDS,IDS,PDS,FDS};
	
	private URIType type;
	private String value;
	
	public DrsURI() { }
	
	public DrsURI(URIType type, String value) {
		super();
		this.type = type;
		this.value = value;
	}
	
	public DrsURI(XMLStreamReader reader) throws XMLStreamException {
		this();
		parse(reader);
	}
	
	public URIType getType() {
		return type;
	}
	public void setType(URIType type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public void output(SMOutputElement parent, SMNamespace ns) throws XMLStreamException {
		SMOutputElement uriElem = parent.addElement(ns, "URI");
		if(type != null) {
			uriElem.addElementWithCharacters(ns, "uriType", type.toString());
		}
		if(value != null) {
			uriElem.addElementWithCharacters(ns, "uriValue", value);
		}	
	}
	
	public DrsURI parse(XMLStreamReader reader) throws XMLStreamException {
		int event = reader.getEventType();
		String localName = "";
		while(!(event == END_ELEMENT && localName.equals("URI"))) {
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();
				if(localName.equals("uriType")) {
					type = URIType.valueOf(reader.getElementText());
				}
				else if(localName.equals("uriValue")) {
					value = reader.getElementText();
				}
				break;
			case END_ELEMENT:
				localName = reader.getLocalName();
				break;
			}
		}
		return this;
	}
	
}
