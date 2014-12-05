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

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_DOCUMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

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
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class TextMD implements XmlComponent{
	
	public static final String XMLNS = "info:lc/xmlns/textMD-v3";
	public static final String SCHEMA_LOCATION = "http://www.loc.gov/standards/textMD/textMD-v3.01a.xsd";
	
	private List<Encoding> encodings = new ArrayList<Encoding>();
	private List<CharacterInfo> characterInfos = new ArrayList<CharacterInfo>();	
	private List<AltLanguage> altLanguages = new ArrayList<AltLanguage>();
	private List<MarkupBasis> markupBases = new ArrayList<MarkupBasis>();
	private List<MarkupLanguage> markupLanguages = new ArrayList<MarkupLanguage>();
	//string lists
	private List<String> languages = new ArrayList<String>();
	private List<String> fontScripts = new ArrayList<String>();
	private List<String> processingNotes = new ArrayList<String>();
	private List<String> printRequirements = new ArrayList<String>();
	private List<String> viewingRequirements = new ArrayList<String>();
	private List<String> textNotes = new ArrayList<String>();
	private List<String> pageOrders = new ArrayList<String>();
	private List<String> representationSequences = new ArrayList<String>();
	private List<String> lineOrientations = new ArrayList<String>();
	private List<String> lineLayouts = new ArrayList<String>();
	private List<String> characterFlows = new ArrayList<String>();
	
	public static final String XML_DEFAULT_PREFIX = "textMD";
	
	private NamespaceSchemaContext nsSchemaContext = new NamespaceSchemaContext(XMLNS, XML_DEFAULT_PREFIX, SCHEMA_LOCATION);
	
	private boolean isRoot;

	public TextMD() { 
		isRoot = false;
	}
	
	public TextMD(boolean isRoot) { 
		this.isRoot = isRoot;
	}
	
	public TextMD(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		this();
		parse(reader);
	}
	
	public TextMD(XMLStreamReader reader, boolean isRoot) throws XMLStreamException, XmlContentException {
		this(isRoot);
		parse(reader);
	}
	
	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
	
	public List<Encoding> getEncodings() {
		return encodings;
	}

	public void setEncodings(List<Encoding> encodings) {
		this.encodings = encodings;
	}
	
	public void addEncoding(Encoding encoding) {
		encodings.add(encoding);
	}

	public List<CharacterInfo> getCharacterInfos() {
		return characterInfos;
	}

	public void setCharacterInfos(List<CharacterInfo> characterInfos) {
		this.characterInfos = characterInfos;
	}
	
	public void addCharacterInfo(CharacterInfo info) {
		characterInfos.add(info);
	}

	public List<AltLanguage> getAltLanguages() {
		return altLanguages;
	}

	public void setAltLanguages(List<AltLanguage> altLanguages) {
		this.altLanguages = altLanguages;
	}
	
	public void addAltLanguage(AltLanguage altLangauge) {
		altLanguages.add(altLangauge);
	}

	public List<MarkupBasis> getMarkupBases() {
		return markupBases;
	}

	public void setMarkupBases(List<MarkupBasis> markupBases) {
		this.markupBases = markupBases;
	}

	public void addMarkupBasis(MarkupBasis basis) {
		markupBases.add(basis);
	}
	
	public List<MarkupLanguage> getMarkupLanguages() {
		return markupLanguages;
	}

	public void setMarkupLanguages(List<MarkupLanguage> markupLanguages) {
		this.markupLanguages = markupLanguages;
	}
	
	public void addMarkupLanguage(MarkupLanguage lang) {
		markupLanguages.add(lang);
	}

	public String[] getLanguages() {
		return languages.toArray(new String[]{});
	}

	public String removeLanguage(int index) {
		return languages.remove(index);
	}
	
	public void addLanguage(String language) throws InvalidTextMDValue {
		if(EnumeratedValues.LANGUAGE.contains(language)) {
			languages.add(language);
		}
		else {
			throw new InvalidTextMDValue(language);
		}
	}

	public List<String> getFontScripts() {
		return fontScripts;
	}

	public void setFontScripts(List<String> fontScripts) {
		this.fontScripts = fontScripts;
	}
	
	public void addFontScript(String fontScript) {
		fontScripts.add(fontScript);
	}

	public List<String> getProcessingNotes() {
		return processingNotes;
	}

	public void setProcessingNotes(List<String> processingNotes) {
		this.processingNotes = processingNotes;
	}
	
	public void addProcessingNote(String note) {
		processingNotes.add(note);
	}

	public List<String> getPrintRequirements() {
		return printRequirements;
	}

	public void setPrintRequirements(List<String> printRequirements) {
		this.printRequirements = printRequirements;
	}
	
	public void addPrintRequirement(String req) {
		printRequirements.add(req);
	}

	public List<String> getViewingRequirements() {
		return viewingRequirements;
	}

	public void setViewingRequirements(List<String> viewingRequirements) {
		this.viewingRequirements = viewingRequirements;
	}
	
	public void addViewingRequirement(String req) {
		viewingRequirements.add(req);
	}

	public List<String> getTextNotes() {
		return textNotes;
	}

	public void setTextNotes(List<String> textNotes) {
		this.textNotes = textNotes;
	}
	
	public void addTextNote(String note) {
		textNotes.add(note);
	}

	public String[] getPageOrders() {
		return pageOrders.toArray(new String[]{});
	}

	public String removePageOrder(int index) {
		return pageOrders.remove(index);
	}
	
	public void addPageOrder(String pageOrder) throws InvalidTextMDValue {
		if(EnumeratedValues.PAGE_ORDER.contains(pageOrder)) {
			pageOrders.add(pageOrder);
		}
		else {
			throw new InvalidTextMDValue(pageOrder);
		}
	}

	public String[] getRepresentationSequences() {
		return representationSequences.toArray(new String[]{});
	}

	public String removeRepresentationSequence(int index) {
		return representationSequences.remove(index);
	}
	
	public void addRepresentationSequence(String sequence) throws InvalidTextMDValue {
		if(EnumeratedValues.REPRESENTATION_SEQUENCE.contains(sequence)) {
			representationSequences.add(sequence);
		}
		else {
			throw new InvalidTextMDValue(sequence);
		}
	}

	public String[] getLineOrientations() {
		return lineOrientations.toArray(new String[]{});
	}

	public String removeLineOrientations(int index) {
		return lineOrientations.remove(index);
	}
	
	public void addLineOrientation(String lineOrientation) throws InvalidTextMDValue {
		if(EnumeratedValues.LINE_ORIENTATION.contains(lineOrientation)) {
			lineOrientations.add(lineOrientation);
		}
		else {
			throw new InvalidTextMDValue(lineOrientation);
		}
	}

	public String[] getLineLayouts() {
		return lineLayouts.toArray(new String[]{});
	}

	public String removeLineLayout(int index) {
		return lineLayouts.remove(index);
	}
	
	public void addLineLayout(String lineLayout) throws InvalidTextMDValue {
		if(EnumeratedValues.LINE_LAYOUT.contains(lineLayout)) {
			lineLayouts.add(lineLayout);
		}
		else {
			throw new InvalidTextMDValue(lineLayout);
		}
	}

	public String[] getCharacterFlows() {
		return characterFlows.toArray(new String[]{});
	}

	public String removeCharacterFlow(int index) {
		return characterFlows.remove(index);
	}
	
	public void addCharacterFlow(String characterFlow) throws InvalidTextMDValue {
		if(EnumeratedValues.CHARACTER_FLOW.contains(characterFlow)) {
			characterFlows.add(characterFlow);
		}
		else {
			throw new InvalidTextMDValue(characterFlow);
		}
	}
	
	@Override
	public void output(XMLStreamWriter writer) throws XMLStreamException {
		if(isRoot) {
			SMOutputDocument doc = SMOutputFactory.createOutputDocument(writer);			
			SMNamespace textmd = doc.getNamespace(XMLNS,nsSchemaContext.getPrefix());
			SMOutputElement root = doc.addElement(textmd,"textMD");		
			RootXmlHelper.output(writer,getAllNamespaceSchemaContexts(),root);		
			outputContents(writer,textmd,root);
			doc.closeRoot();
		}
		else {
			SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
			SMNamespace textmd = frag.getNamespace(XMLNS,nsSchemaContext.getPrefix());
			SMOutputElement root = frag.addElement(textmd,"textMD");
			outputContents(writer,textmd,root);
			frag.closeRoot();
		}

	}	
	
	private void outputContents(XMLStreamWriter writer,SMNamespace textmd, SMOutputElement root) throws XMLStreamException {	
		//output encoding
		for(Encoding encoding : encodings) {
			SMOutputElement encoding_e = root.addElement(textmd,"encoding");
			if(encoding.getQuality() != null) {
				encoding_e.addAttribute("QUALITY", encoding.getQuality());
			}
			for(EncodingPlatform platform : encoding.getEncodingPlatforms()) {
				SMOutputElement platform_e = encoding_e.addElement(textmd,"encoding_platform");
				if(platform.getLinebreak() != null) {
					platform_e.addAttribute("linebreak", platform.getLinebreak());
				}
				platform_e.addCharacters(platform.getValue());
			}
			for(EncodingSoftware software : encoding.getEncodingSoftwares()) {
				SMOutputElement software_e = encoding_e.addElement(textmd,"encoding_software");
				if(software.getVersion() != null) {
					software_e.addAttribute("version", software.getVersion());
				}
				software_e.addCharacters(software.getValue());			
			}
			for(EncodingAgent agent : encoding.getEncodingAgents()) {
				SMOutputElement agent_e = encoding_e.addElement(textmd,"encoding_agent");
				if(agent.getRole() != null) {
					agent_e.addAttribute("role", agent.getRole());
				}
				if(agent.getValue() != null) {
					agent_e.addCharacters(agent.getValue());
				}
			}
		}
		
		//output character_info
		for(CharacterInfo characterInfo : characterInfos) {
			SMOutputElement characterInfo_e = root.addElement(textmd,"character_info");
			if(characterInfo.getCharset() != null) {
				characterInfo_e.addElementWithCharacters(textmd, "charset",
						characterInfo.getCharset());
			}
			if(characterInfo.getByteOrder() != null) {
				characterInfo_e.addElementWithCharacters(textmd, "byte_order",
						characterInfo.getByteOrder());
			}
			if(characterInfo.getByteSize() != null) {
				characterInfo_e.addElementWithCharacters(textmd, "byte_size",
						characterInfo.getByteSize().toString());
			}
			if(characterInfo.getCharacterSizeValue() != null) {
				SMOutputElement characterSize_e = characterInfo_e.addElement(textmd,"character_size");
				if(characterInfo.getCharacterSizeEncoding() != null) {
					characterSize_e.addAttribute("encoding",
							characterInfo.getCharacterSizeEncoding());
				}
				characterSize_e.addCharacters(characterInfo.getCharacterSizeValue());
			}
			if(characterInfo.getLinebreak() != null) {
				characterInfo_e.addElementWithCharacters(textmd, "linebreak",
						characterInfo.getLinebreak());
			}
		}
		//output language
		for(String lang : languages) {
			root.addElementWithCharacters(textmd,"language",lang);
		}
		//output alt_language
		for(AltLanguage lang : altLanguages) {
			SMOutputElement lang_e = root.addElement(textmd,"alt_language");
			if(lang.getAuthority() != null) {
				lang_e.addAttribute("authority",lang.getAuthority());
			}
			lang_e.addCharacters(lang.getValue());
		}
		//output font_script
		for(String fontScript : fontScripts) {
			root.addElementWithCharacters(textmd,"font_script",fontScript);
		}
		//output markup_basis
		for(MarkupBasis mBasis : markupBases) {
			SMOutputElement markupBasis_e = root.addElement(textmd,"markup_basis");
			if(mBasis.getVersion() != null) {
				markupBasis_e.addAttribute("version",mBasis.getVersion());
			}
			markupBasis_e.addCharacters(mBasis.getValue());
		}
		//output markup_language
		for(MarkupLanguage mLang : markupLanguages) {
			SMOutputElement markupLang_e = root.addElement(textmd,"markup_language");
			if(mLang.getVersion() != null) {
				markupLang_e.addAttribute("version",mLang.getVersion());
			}
			markupLang_e.addCharacters(mLang.getValue());
		}
		//output processingNote
		for(String processingNote : processingNotes) {
			root.addElementWithCharacters(textmd,"processingNote",processingNote);
		}
		//output printRequirements
		for(String printReq : printRequirements) {
			root.addElementWithCharacters(textmd,"printRequirements",printReq);
		}
		//output viewingRequirements
		for(String viewReq : viewingRequirements) {
			root.addElementWithCharacters(textmd,"viewingRequirements",viewReq);
		}
		//output textNote
		for(String textNote : textNotes) {
			root.addElementWithCharacters(textmd,"textNote",textNote);
		}
		//output pageOrder
		for(String pageOrder : pageOrders) {
			root.addElementWithCharacters(textmd,"pageOrder",pageOrder);
		}	
		//output representationSequence
		for(String representationSequence : representationSequences) {
			root.addElementWithCharacters(textmd,"representationSequence",representationSequence);
		}
		//output lineOrientation
		for(String lineOrientation : lineOrientations) {
			root.addElementWithCharacters(textmd,"lineOrientation",lineOrientation);
		}	
		//output lineLayout
		for(String lineLayout : lineLayouts) {
			root.addElementWithCharacters(textmd,"lineLayout",lineLayout);
		}	
		//output characterFlow
		for(String characterFlow : characterFlows) {
			root.addElementWithCharacters(textmd,"characterFlow",characterFlow);
		}
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TextMD parse(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		int event = reader.getEventType();
		if(event == START_DOCUMENT) {
			reader.nextTag();
		}
		reader.require(START_ELEMENT, TextMD.XMLNS, "textMD");		
		String localName = "";
		while(!(event == END_ELEMENT && localName.equals("textMD"))) {	
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();	
				if(localName.equals("encoding")) {
					Encoding encoding = new Encoding();
					encoding.setQuality(reader.getAttributeValue(null,"QUALITY"));
					while(!(event == END_ELEMENT && localName.equals("encoding"))) {	
						event = reader.next();
						switch(event) {
						case START_ELEMENT:
							localName = reader.getLocalName();	
							if(localName.equals("encoding_platform")) {
								EncodingPlatform platform = new EncodingPlatform();
								platform.setLinebreak(reader.getAttributeValue(null,"linebreak"));
								platform.setValue(reader.getElementText());
								encoding.addEncodingPlatforms(platform);
							}
							else if(localName.equals("encoding_software")) {
								EncodingSoftware software = new EncodingSoftware();
								software.setVersion(reader.getAttributeValue(null,"version"));
								software.setValue(reader.getElementText());
								encoding.addEncodingSoftwares(software);				
							}
							else if(localName.equals("encoding_agent")) {
								EncodingAgent agent = new EncodingAgent();
								agent.setRole(reader.getAttributeValue(null,"role"));
								agent.setValue(reader.getElementText());
								encoding.addEncodingAgent(agent);				
							}
							break;
						case END_ELEMENT:
							localName = reader.getLocalName();
							break;								
						}
					}
					encodings.add(encoding);
				}
				else if(localName.equals("character_info")) {
					CharacterInfo info = new CharacterInfo();
					while(!(event == END_ELEMENT && localName.equals("character_info"))) {	
						event = reader.next();
						switch(event) {
						case START_ELEMENT:
							localName = reader.getLocalName();	
							if(localName.equals("charset")) {
								info.setCharset(reader.getElementText());
							}
							else if(localName.equals("byte_order")) {
								info.setByteOrder(reader.getElementText());			
							}
							else if(localName.equals("byte_size")) {
								info.setByteSize(new Integer(reader.getElementText()));				
							}
							else if(localName.equals("character_size")) {
								info.setCharacterSizeEncoding(reader.getAttributeValue(null,"encoding"));
								info.setCharacterSizeValue(reader.getElementText());
							}
							else if(localName.equals("linebreak")) {
								info.setLinebreak(reader.getElementText());				
							}
							break;
						case END_ELEMENT:
							localName = reader.getLocalName();
							break;								
						}
					}
					characterInfos.add(info);
				}
				else if(localName.equals("language")) {
					addLanguage(reader.getElementText());
				}
				else if(localName.equals("alt_language")) {
					AltLanguage altLang = new AltLanguage();
					altLang.setAuthority(reader.getAttributeValue(null,"authority"));
					altLang.setValue(reader.getElementText());
					altLanguages.add(altLang);
				}
				else if(localName.equals("font_script")) {
					fontScripts.add(reader.getElementText());
				}			
				else if(localName.equals("markup_basis")) {
					MarkupBasis mBasis = new MarkupBasis();
					mBasis.setVersion(reader.getAttributeValue(null,"version"));
					mBasis.setValue(reader.getElementText());
					markupBases.add(mBasis);
				}
				else if(localName.equals("markup_language")) {
					MarkupLanguage mLang = new MarkupLanguage();
					mLang.setVersion(reader.getAttributeValue(null,"version"));
					mLang.setValue(reader.getElementText());
					markupLanguages.add(mLang);
				}
				else if(localName.equals("processingNote")) {
					processingNotes.add(reader.getElementText());
				}
				else if(localName.equals("printRequirements")) {
					printRequirements.add(reader.getElementText());
				}
				else if(localName.equals("viewingRequirements")) {
					viewingRequirements.add(reader.getElementText());
				}
				else if(localName.equals("textNote")) {
					textNotes.add(reader.getElementText());
				}
				else if(localName.equals("pageOrder")) {
					addPageOrder(reader.getElementText());
				}
				else if(localName.equals("representationSequence")) {
					addRepresentationSequence(reader.getElementText());
				}
				else if(localName.equals("lineOrientation")) {
					addLineOrientation(reader.getElementText());
				}	
				else if(localName.equals("lineLayout")) {
					addLineLayout(reader.getElementText());
				}	
				else if(localName.equals("characterFlow")) {
					addCharacterFlow(reader.getElementText());
				}	
				break;
			case END_ELEMENT:
				localName = reader.getLocalName();
				break;								
			}
		}
		return this;
	}

	@Override
	public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts() {
		List<NamespaceSchemaContext> contexts = new ArrayList<NamespaceSchemaContext>();	
		//Add namespace schema context for this object
		contexts.add(nsSchemaContext);
		//TextMD cannot contain xml from other schemas
		return contexts;
	}

	@Override
	public NamespaceSchemaContext getNamespaceSchemaContext() {
		return nsSchemaContext;
	}

}
