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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XmlDateFormat {
	
	//2006-11-14T18:13:51.0Z
	//yyyy.MM.dd'T'HH:mm:ss.Z
	private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'Z'");
		
	public static Date parseDateTime(String date) throws ParseException {
		//SimpleDateFormat is not thread safe
		synchronized (dateTimeFormat) {
			return dateTimeFormat.parse(date);
		}	
	}
	
	public static Date parseDate(String date) throws ParseException {
		//SimpleDateFormat is not thread safe
		synchronized (dateFormat) {
			return dateFormat.parse(date);
		}	
	}
	
	public static String formatDateTime(Date date) {
		synchronized (dateTimeFormat) {
			return dateTimeFormat.format(date);
		}
	}
	
	public static String formatDate(Date date) {
		synchronized (dateFormat) {
			return dateFormat.format(date);
		}
	}

}
