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
package edu.harvard.hul.ois.ots.schemas.pds;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PdsDate {
	
	private static Pattern schemaDate = Pattern.compile("\\d{4}-\\d{2}-\\d{2}(Z??|((\\+|-)\\d{2}:\\d{2}))");
	private static Pattern pdsDate1 = Pattern.compile("\\d{4}");
	private static Pattern pdsDate2 = Pattern.compile("\\d{4}-\\d{2}");
	private static Pattern pdsDate3 = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
	
	private String fromDate;
	private String toDate;
	
	public PdsDate() { }
	
	public PdsDate(String fromDate, String toDate) throws PdsDateException {
		setFromDate(fromDate);
		setToDate(toDate);
	}
	
	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) throws PdsDateException {
		if(isValid(fromDate)) {
			this.fromDate = fromDate;
		}
		else {
			throw new PdsDateException(fromDate);
		}	
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) throws PdsDateException {	
		if(isValid(toDate)) {
			this.toDate = toDate;
		}
		else {
			throw new PdsDateException(toDate);
		}
	}
	
	/**
	 * Check if the input string matches any of the valid PREMIS date regular expressions
	 * @param dateString
	 * @return boolean
	 */
	public boolean isValid(String dateString) {
		Matcher m = schemaDate.matcher(dateString);
		if(m.matches()) {
			return true;
		}
		m = pdsDate1.matcher(dateString);
		if(m.matches()) {
			return true;
		}
		m = pdsDate2.matcher(dateString);
		if(m.matches()) {
			return true;
		}
		m = pdsDate3.matcher(dateString);
		if(m.matches()) {
			return true;
		}
		return false;
	}
}
