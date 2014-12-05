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

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

public class FileGrp {

	private String id;
	private String use;
	private List<String> admID;
	private List<FileFLocat> files;
	
	public FileGrp() {
		files = new ArrayList<FileFLocat>();
		admID = new ArrayList<String>();
	}
	
	public String getID() {
		return id;
	}
	public void setID(String id) {
		this.id = id;
	}
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
	public List<FileFLocat> getFiles() {
		return files;
	}
	public void setFiles(List<FileFLocat> files) {
		this.files = files;
	}
	public void addFile(FileFLocat file) {
		files.add(file);
	}
	public List<String> getAdmID() {
		return admID;
	}
	public void setAdmID(List<String> admID) {
		this.admID = admID;
	}
	
	/**
	 * outputs all files in the filegrp
	 * @param writer
	 * @throws XMLStreamException
	 */
	public void output(XMLStreamWriter writer) throws XMLStreamException {
		output(writer,null);
	}
	
	/**
	 * outputs only files that are in the list of filtered identifiers
	 * @param writer
	 * @param filterIDs
	 * @throws XMLStreamException
	 */
	public void output(XMLStreamWriter writer, List<String> filterIDs) throws XMLStreamException {
		SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
		SMOutputElement filegrp = frag.addElement(METS.ns, "fileGrp");
		if(id != null) {
			filegrp.addAttribute("ID",id);
		}
		if(use != null) {
			filegrp.addAttribute("USE",use);
		}
		for(FileFLocat file : files) {
			boolean outputIt = true;
			//if we are filtering the output, and if filter doesn't contain the current ID then don't output
			if(filterIDs != null && !filterIDs.contains(file.getFileID())) {
				outputIt = false;
			}
			if(outputIt) {
				SMOutputElement file_e = filegrp.addElement(METS.ns,"file");
				if(file.getFileID() != null) {
					file_e.addAttribute("ID",file.getFileID());
				}
				
				//only output ADMIDs if a list of filter IDs is not being used, since AmdSec 
				 //sections won't be output
				if(filterIDs == null) {
					if(file.getAdmID() != null) {
						String admids = "";
						for(String admid : file.getAdmID()) {
							admids = admids + " " + admid;
						}
						admids = admids.trim();
						file_e.addAttribute("ADMID",admids);
					}
				}
				if(file.getMime() != null) {
					file_e.addAttribute("MIMETYPE",file.getMime());
				}
				SMOutputElement flocat = file_e.addElement(METS.ns,"FLocat");
				if(file.getFlocatID() != null) {
					flocat.addAttribute("ID",file.getFlocatID());
				}
				if(file.getLoctype() != null) {
					flocat.addAttribute("LOCTYPE",file.getLoctype());
				}
				if(file.getOtherloctype() != null) {
					flocat.addAttribute("OTHERLOCTYPE",file.getOtherloctype());
				}
				if(file.getXlink() != null) {
					file.getXlink().output(writer);
				}
			}
		}
		frag.closeRoot();
	}
}
