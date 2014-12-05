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
package edu.harvard.hul.ois.ots.schemas.METS;

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

import edu.harvard.hul.ois.ots.schemas.XmlContent.SimpleLink;

public class FileSec {
	
	private String id;
	private List <FileGrp> filegrps;
	
	public FileSec() {
		filegrps = new ArrayList<FileGrp>();
	}
	
	public FileSec(XMLStreamReader reader) throws XMLStreamException {
		this();
		parse(reader);
	}
	
	public String getID() {
		return id;
	}
	public void setID(String id) {
		this.id = id;
	}
	public List <FileGrp> getFilegrp() {
		return filegrps;
	}
	
	public FileGrp getFileGrp(String mime) {
		//replace / in mime string with dash
		mime = mime.replaceAll("/","-");
		for(FileGrp grp : filegrps) {
			if(grp.getUse().equals(mime)) {
				return grp;
			}
		}
		return null;
	}
	
	public void setFilegrp(List<FileGrp> filegrp) {
		this.filegrps = filegrp;
	}
	public void addFileGrp(FileGrp filegrp) {
		this.filegrps.add(filegrp);
	}
	
	private FileSec parse(XMLStreamReader reader) throws XMLStreamException {
		id = reader.getAttributeValue(null,"ID");
		int event = reader.getEventType();
		String localName = "";
		while(!(event == END_ELEMENT && localName.equals("fileSec"))) {
			//advance to a fileGrp
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();
				if(localName.equals("fileGrp")) {
					FileGrp filegrp = new FileGrp();
					filegrp.setID(reader.getAttributeValue(null,"ID"));
					filegrp.setUse(reader.getAttributeValue(null,"USE"));
					String fileGrpAdmids = reader.getAttributeValue(null,"ADMID");
					if(fileGrpAdmids != null) {
						List<String> fileGrpAdmidList = Arrays.asList(fileGrpAdmids.split(" "));
						filegrp.setAdmID(fileGrpAdmidList);
					}
					while(!(event == END_ELEMENT && localName.equals("fileGrp"))) {
						//advance to a file element
						event = reader.next();
						switch(event) {
						case START_ELEMENT:
							localName = reader.getLocalName();
							if(localName.equals("file")) {
								FileFLocat contentFile = new FileFLocat();
								contentFile.setFileID(reader.getAttributeValue(null,"ID"));
								contentFile.setMime(reader.getAttributeValue(null,"MIMETYPE"));
								String admids = reader.getAttributeValue(null,"ADMID");
								ArrayList<String> admidList = new ArrayList<String>();
								if (admids == null) {
								    admidList = new ArrayList<String> ();
								}
								else {
								    admidList.addAll(Arrays.asList(admids.split(" ")));
                                }
								contentFile.setAdmID(admidList);
								//advance to FLocat element
								reader.nextTag();
								contentFile.setFlocatID(reader.getAttributeValue(null,"ID"));
								contentFile.setLoctype(reader.getAttributeValue(null,"LOCTYPE"));
								contentFile.setOtherloctype(reader.getAttributeValue(null,"OTHERLOCTYPE"));
								SimpleLink xlink = new SimpleLink(reader);
								contentFile.setXlink(xlink);
								//add content file to FileGrp object and to files map	
								filegrp.addFile(contentFile);
							}
							break;
						case END_ELEMENT:
							localName = reader.getLocalName();
							break;
						}
					}
					this.filegrps.add(filegrp);
				}
				break;
			case END_ELEMENT:
				localName = reader.getLocalName();
				break;								
			}
		}
		return this;
	}
	
	/**
	 * outputs all files in the filesec
	 * @param writer
	 * @throws XMLStreamException
	 */
	public void output(XMLStreamWriter writer) throws XMLStreamException {
		output(writer,null);
	}
	
	/**
	 * only puts file elements for those with id references in the filterIDs list
	 * @param writer
	 * @param filterIDs
	 * @throws XMLStreamException
	 */
	public void output(XMLStreamWriter writer, List<String> filterIDs) throws XMLStreamException {
		SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
		SMOutputElement filesec = frag.addElement(METS.ns, "fileSec");
		if(id != null) {
			filesec.addAttribute("ID",id);
		}
		for(FileGrp filegrp : filegrps) {
			filegrp.output(writer,filterIDs);
		}
		frag.closeRoot();
	}
	
}
