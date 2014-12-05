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
package edu.harvard.hul.ois.ots.schemas.HulDrsAdminMD;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.harvard.hul.ois.ots.schemas.XmlComponent;
import edu.harvard.hul.ois.ots.schemas.HulDrsAdminMD.AdminFlag.FlagType;
import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;


public abstract class HulDrsAdminMD implements XmlComponent {
	
	public static final String XMLNS = "http://hul.harvard.edu/ois/xml/ns/hulDrsAdmin";
	public static final String XML_DEFAULT_PREFIX = "hulDrsAdmin";
	public static final String SCHEMA_LOCATION = "http://hul.harvard.edu/ois/xml/xsd/drs/hulDrsAdmin.xsd";	
		
	protected GenericVersionedElement accessFlag = new GenericVersionedElement();
	
	public enum AccessFlag { N, R, P, B };
	public enum Status {current,superseded,deleted};
	
	protected List<AdminCategory> adminCategories;
	protected List<AdminFlag> adminFlags;
	
	protected Date insertionDate;
	protected Date latestModificationDate;
	protected String ownerSuppliedName;
	protected String status;
	protected List<DrsURI> URIs;
	protected String methodology;
	
	protected NamespaceSchemaContext nsSchemaContext = new NamespaceSchemaContext(XMLNS, XML_DEFAULT_PREFIX, SCHEMA_LOCATION);
		
	public HulDrsAdminMD() {
		adminCategories = new ArrayList<AdminCategory>();
		adminFlags = new ArrayList<AdminFlag>();
		URIs = new ArrayList<DrsURI>();
	}
	
	public AccessFlag getAccessFlag() {
		return AccessFlag.valueOf(accessFlag.getValue());
	}
	
	public GenericVersionedElement getAccessFlagObject() {
		return accessFlag;
	}
	
	
	/**
	 * returns the current access flag value as a char
	 * @return
	 */
	public char getAccessFlagAsChar() {
		return accessFlag.getValue().charAt(0);
	}
	
	public List<AdminFlag> getAdminFlags() {
		return adminFlags;
	}
	
	public void addAdminFlag(FlagType flagType, String flagNote, String agent) throws XmlContentException {
		//check that admin flagtype does not already exist as a current flag
		for(AdminFlag flag : adminFlags) {
			if(flag.getFlagType() == flagType && flag.getStatus() == Status.current) {
				throw new XmlContentException(flagType.toString() +" already exists with status=\"current\"");
			}
		}
		//if we get here then make the flag and add it
		AdminFlag aflag = new AdminFlag(flagType, flagNote, agent);
		adminFlags.add(aflag);
	}
	
	public void setAccessFlag(String value, String agent) {
		accessFlag.setValue(value, agent);
	}
	
	public List<AdminCategory> getAdminCategories() {
		return adminCategories;
	}

	public void setAdminCategoryID(List<AdminCategory> adminCategories) {
		this.adminCategories = adminCategories;
	}
	
	public void addAdminCategory(AdminCategory category) {
		adminCategories.add(category);
	}

	public String getMethodology() {
		return methodology;
	}

	public void setMethodology(String methodology) {
		this.methodology = methodology;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<DrsURI> getURIs() {
		return URIs;
	}

	public void setURIs(List<DrsURI> URIs) {
		this.URIs = URIs;
	}
	
	public void addURI(DrsURI uri) {
		URIs.add(uri);
	}

	public Date getInsertionDate() {
		return insertionDate;
	}

	public void setInsertionDate(Date insertionDate) {
		this.insertionDate = insertionDate;
	}

	public Date getLatestModificationDate() {
		return latestModificationDate;
	}

	public void setLatestModificationDate(Date latestModificationDate) {
		this.latestModificationDate = latestModificationDate;
	}

	public String getOwnerSuppliedName() {
		return ownerSuppliedName;
	}

	public void setOwnerSuppliedName(String ownerSuppliedName) {
		this.ownerSuppliedName = ownerSuppliedName;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
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
