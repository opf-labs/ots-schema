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

import java.util.Hashtable;
import java.util.Map;

public class ParserContainer {
	
	private static Map<String,XmlContentParser> delegates = new Hashtable<String,XmlContentParser>();
	
	public static void registerParser(String name,XmlContentParser parser) {
		delegates.put(name,parser);
	}
	
	public static XmlContentParser getParser(String name) {
		XmlContentParser parser =  delegates.get(name);
		if(parser != null) {
			return parser;
		}
		else {
			return new AnyXml();
		}
	}
}
