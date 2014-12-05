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
package edu.harvard.hul.ois.ots.schemas.premis;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PremisDate {
	
	private static Pattern premisDate1 = Pattern.compile("\\d{2}(\\d{2}|\\?\\?|\\d(\\d|\\?))(-(\\d{2}|\\?\\?))?~?\\??");
	private static Pattern premisDate2 = Pattern.compile("\\d{6}(\\d{2}|\\?\\?)~?\\??");
	private static Pattern premisDate3 = Pattern.compile("\\d{8}T\\d{6}");
	private static Pattern premisDate4 = Pattern.compile("((\\d{4}(-\\d{2})?)|UNKNOWN)/((\\d{4}(-\\d{2})?)|UNKNOWN|OPEN)");
	private static Pattern schemaDate = Pattern.compile("\\d{4}-\\d{2}-\\d{2}(Z??|((\\+|-)\\d{2}:\\d{2}))");
	private static Pattern schemaDateTime = Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}((\\.\\d{1,})??)(Z??|((\\+|-)\\d{2}:\\d{2}))");

	private String value;
	
	public PremisDate() { }
	
	public PremisDate(String dateString) throws PremisDateException {
		setValue(dateString);
	}
	
	public PremisDate(Date date) throws PremisDateException {
		setValue(printDateTime(date));
	}
	
    // crazy hack because the 'Z' formatter produces an output incompatible with the xsd:dateTime
    public static String printDateTime(Date dt) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        DateFormat tzFormatter = new SimpleDateFormat("Z");
        String timezone = tzFormatter.format(dt);
        return formatter.format(dt) + timezone.substring(0, 3) + ":"
                + timezone.substring(3);
    }
	
	public void setValue(String dateString) throws PremisDateException {
		if(isValid(dateString)) {
			value = dateString;
		}
		else {
			throw new PremisDateException(dateString);
		}
	}
	
	/**
	 * Check if the input string matches any of the valid PREMIS date regular expressions
	 * @param dateString
	 * @return boolean
	 */
	public boolean isValid(String dateString) {
		Matcher m = schemaDateTime.matcher(dateString);
		if(m.matches()) {
			return true;
		}
		m = schemaDate.matcher(dateString);
		if(m.matches()) {
			return true;
		}
		m = premisDate1.matcher(dateString);
		if(m.matches()) {
			return true;
		}
		m = premisDate2.matcher(dateString);
		if(m.matches()) {
			return true;
		}
		m = premisDate3.matcher(dateString);
		if(m.matches()) {
			return true;
		}
		m = premisDate4.matcher(dateString);
		if(m.matches()) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return value;
	}

}
