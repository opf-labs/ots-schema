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
package edu.harvard.hul.ois.ots.schemas.DocumentMD;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputDocument;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

import edu.harvard.hul.ois.ots.schemas.XmlComponent;
import edu.harvard.hul.ois.ots.schemas.StaxUtils.RootXmlHelper;
import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;


/*
<?xml version="1.0" encoding="UTF-8"?>
<document xmlns="http://www.fcla.edu/docmd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.fcla.edu/docmd http://www.fcla.edu/dls/md/docmd.xsd">
	<PageCount>3</PageCount>
	<WordCount>34</WordCount>
	<CharacterCount>4</CharacterCount>
	<ParagraphCount>4</ParagraphCount>
	<LineCount>66</LineCount>
	<TableCount>0</TableCount>
	<GraphicsCount>4</GraphicsCount>
	<Language>234</Language>
	<Language>blah</Language>
	<Font FontName="BookmanOldStyle" isEmbedded="false"/>
	<Font FontName="Arial,Bold" isEmbedded="false"/>
	<Font FontName="TimesNewRoman,Italic" isEmbedded="false"/>
	<Features>hasLayers</Features>
	<Features>hasOutline</Features>
</document>
*/


public class DocumentMD implements XmlComponent {

	public static final String XMLNS = "http://www.fcla.edu/docmd";
	public static final String SCHEMA_LOCATION = "http://www.fcla.edu/dls/md/docmd.xsd";
	public static final String XML_DEFAULT_PREFIX = "docmd";
	
	private NamespaceSchemaContext nsSchemaContext = new NamespaceSchemaContext(XMLNS, XML_DEFAULT_PREFIX, SCHEMA_LOCATION);
	
	public enum Feature {isTagged,hasOutline,hasThumbnails,hasLayers,hasForms,
        				hasAnnotations,hasAttachments,useTransparency}
		
	private Integer pageCount;
	private Integer wordCount;
	private Integer characterCount;
	private Integer paragraphCount;
	private Integer lineCount;
	private Integer tableCount;
	private Integer graphicsCount;
	private List<String> languages;
	private List<Font> fonts;
	private List<Feature> features;
	private boolean isRoot;
	
	public DocumentMD() {
		languages = new ArrayList<String>();
		fonts = new ArrayList<Font>();
		features = new ArrayList<Feature>();
		isRoot = false;
	}
	
	public DocumentMD(boolean isRoot) {
		this();
		this.isRoot = isRoot;
	}
	
	public DocumentMD(XMLStreamReader reader) throws XMLStreamException {
		this();
		parse(reader);
	}
	
	public DocumentMD(XMLStreamReader reader, boolean isRoot) throws XMLStreamException {
		this(isRoot);
		parse(reader);
	}
	
	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
	
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getWordCount() {
		return wordCount;
	}
	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}
	public int getCharacterCount() {
		return characterCount;
	}
	public void setCharacterCount(int characterCount) {
		this.characterCount = characterCount;
	}
	public int getParagraphCount() {
		return paragraphCount;
	}
	public void setParagraphCount(int paragraphCount) {
		this.paragraphCount = paragraphCount;
	}
	public int getLineCount() {
		return lineCount;
	}
	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}
	public int getTableCount() {
		return tableCount;
	}
	public void setTableCount(int tableCount) {
		this.tableCount = tableCount;
	}
	public int getGraphicsCount() {
		return graphicsCount;
	}
	public void setGraphicsCount(int graphicsCount) {
		this.graphicsCount = graphicsCount;
	}
	public List<String> getLanguages() {
		return languages;
	}
	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}
	public void addLanguage(String language) {
		languages.add(language);
	}
	public List<Font> getFonts() {
		return fonts;
	}
	public void setFonts(List<Font> fonts) {
		this.fonts = fonts;
	}
	public void addFont(Font font) {
		fonts.add(font);
	}
	public List<Feature> getFeatures() {
		return features;
	}
	public void setFeatures(List<Feature> features) {
		this.features = features;
	}
	public void addFeature(Feature features) {
		this.features.add(features);
	}
	
	public DocumentMD parse(XMLStreamReader reader) throws XMLStreamException {	
		reader.require(START_ELEMENT, XMLNS, "document");
		int event = reader.getEventType();
		String localName = "";
		while(!(event == END_ELEMENT && localName.equals("document"))) {	
		//for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {		
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();
				if(localName.equals("PageCount")) {
					setPageCount(Integer.parseInt(reader.getElementText()));				
				}
				else if(localName.equals("WordCount")) {
					setWordCount(Integer.parseInt(reader.getElementText()));
				}
				else if(localName.equals("CharacterCount")) {
					setCharacterCount(Integer.parseInt(reader.getElementText()));
				}
				else if(localName.equals("ParagraphCount")) {
					setParagraphCount(Integer.parseInt(reader.getElementText()));
				}
				else if(localName.equals("LineCount")) {
					setLineCount(Integer.parseInt(reader.getElementText()));
				}
				else if(localName.equals("TableCount")) {
					setTableCount(Integer.parseInt(reader.getElementText()));
				}
				else if(localName.equals("GraphicsCount")) {
					setGraphicsCount(Integer.parseInt(reader.getElementText()));
				}
				else if(localName.equals("Language")) {
					addLanguage(reader.getElementText());
				}
				else if(localName.equals("Font")) {
					Font font = new Font();
					font.setName(reader.getAttributeValue(null,"FontName"));
					font.setEmbedded(new Boolean(reader.getAttributeValue(null,"isEmbedded")));
					addFont(font);
				}				
				else if(localName.equals("Features")) {
					String featureTxt = reader.getElementText();
					addFeature(Feature.valueOf(featureTxt));
				}			
				break;
			case END_ELEMENT:
				//return doc when we're at the end of the document element
				//if(reader.getLocalName().equals("document")) {
				//	return this;
				//}
				localName = reader.getLocalName();
				break;			
			}
		}
		return this;
	}
	
	/*
	public void marshall(XMLStreamWriter writer, OutputStream out) throws XMLStreamException {
		writer.writeStartElement("document");
		writer.writeDefaultNamespace(XMLNS);
		writer.writeNamespace(SCHEMA_PREFIX, SCHEMA_NAMESPACE_URI);
		writer.writeAttribute(SCHEMA_PREFIX, SCHEMA_NAMESPACE_URI, "schemaLocation", DOCMD_SCHEMALOCATION);
		if(pageCount != null) {
			writer.writeStartElement("PageCount");
			writer.writeCharacters(String.valueOf(pageCount));
			writer.writeEndElement();
		}
		if(wordCount != null) {
			writer.writeStartElement("WordCount");
			writer.writeCharacters(String.valueOf(wordCount));
			writer.writeEndElement();
		}
		if(characterCount != null) {
			writer.writeStartElement("CharacterCount");
			writer.writeCharacters(String.valueOf(characterCount));
			writer.writeEndElement();
		}		
		if(paragraphCount != null) {
			writer.writeStartElement("ParagraphCount");
			writer.writeCharacters(String.valueOf(paragraphCount));
			writer.writeEndElement();
		}		
		if(lineCount != null) {
			writer.writeStartElement("LineCount");
			writer.writeCharacters(String.valueOf(lineCount));
			writer.writeEndElement();
		}
		if(tableCount != null) {
			writer.writeStartElement("TableCount");
			writer.writeCharacters(String.valueOf(tableCount));
			writer.writeEndElement();
		}
		if(graphicsCount != null) {
			writer.writeStartElement("GraphicsCount");
			writer.writeCharacters(String.valueOf(graphicsCount));
			writer.writeEndElement();
		}	
		if(languages != null) {
			for(String language : languages) {
				writer.writeStartElement("Language");
				writer.writeCharacters(language);
				writer.writeEndElement();				
			}
		}
		if(fonts != null) {
			for(Font font : fonts) {
				writer.writeStartElement("Font");
				if(font.getName() != null) {
					writer.writeAttribute("FontName",font.getName());
				}
				if(font.isEmbedded() != null) {
					writer.writeAttribute("isEmbedded",font.isEmbedded().toString());
				}
				writer.writeEndElement();				
			}
		}
		if(features != null) {
			for(Feature feature : features) {
				writer.writeStartElement("Features");
				writer.writeCharacters(feature.toString());
				writer.writeEndElement();				
			}
		}
		writer.writeEndElement(); //close <document> element
	}
	*/
	public void output(XMLStreamWriter writer) throws XMLStreamException {
		if(isRoot) {
			SMOutputDocument doc = SMOutputFactory.createOutputDocument(writer);			
			SMNamespace docmdNS = doc.getNamespace(XMLNS,nsSchemaContext.getPrefix());
			SMOutputElement root = doc.addElement(docmdNS,"document");		
			RootXmlHelper.output(writer,getAllNamespaceSchemaContexts(),root);		
			outputContents(writer,docmdNS,root);
			doc.closeRoot();
		}
		else {
			SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
			SMNamespace docmdNS = frag.getNamespace(XMLNS,nsSchemaContext.getPrefix());
			SMOutputElement root = frag.addElement(docmdNS,"document");
			outputContents(writer,docmdNS,root);
			frag.closeRoot();
		}
	}
	
	private void outputContents(XMLStreamWriter writer,SMNamespace docmdNS, SMOutputElement root) throws XMLStreamException {		
		if(pageCount != null) {
			root.addElement(docmdNS,"PageCount").addCharacters(String.valueOf(pageCount));
		}
		if(wordCount != null) {
			root.addElement(docmdNS,"WordCount").addCharacters(String.valueOf(wordCount));
		}
		if(characterCount != null) {
			root.addElement(docmdNS,"CharacterCount").addCharacters(String.valueOf(characterCount));
		}		
		if(paragraphCount != null) {
			root.addElement(docmdNS,"ParagraphCount").addCharacters(String.valueOf(paragraphCount));
		}	
		if(lineCount != null) {
			root.addElement(docmdNS,"LineCount").addCharacters(String.valueOf(lineCount));
		}
		if(tableCount != null) {
			root.addElement(docmdNS,"TableCount").addCharacters(String.valueOf(tableCount));
		}
		if(graphicsCount != null) {
			root.addElement(docmdNS,"GraphicsCount").addCharacters(String.valueOf(graphicsCount));
		}	
		if(languages != null) {
			for(String language : languages) {
				root.addElement(docmdNS,"Language").addCharacters(language);			
			}
		}
		if(fonts != null) {
			for(Font font : fonts) {
				SMOutputElement f = root.addElement(docmdNS,"Font");
				if(font.getName() != null) {
					f.addAttribute("FontName", font.getName());
				}
				if(font.isEmbedded() != null) {
					f.addAttribute("isEmbedded", font.isEmbedded().toString());
				}			
			}
		}
		if(features != null) {
			for(Feature feature : features) {
				root.addElement(docmdNS,"Features").addCharacters(feature.toString());					
			}
		}		
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts() {
		List<NamespaceSchemaContext> contexts = new ArrayList<NamespaceSchemaContext>();	
		//Add namespace schema context for this object
		contexts.add(nsSchemaContext);
		//docmd cannot contain xml from other schemas
		return contexts;
	}

	@Override
	public NamespaceSchemaContext getNamespaceSchemaContext() {
		return nsSchemaContext;
	}
}

