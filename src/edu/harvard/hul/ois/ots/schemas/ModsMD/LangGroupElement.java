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
package edu.harvard.hul.ois.ots.schemas.ModsMD;


/** This abstract class defines the attributes which are in the attributeGroup
 *  called "language". Any MODS element which declares that attributeGroup should
 *  subclass LangGroupElement rather than ModsElement. These include titleInfo,
 *  name, genre, originInfo, physicalDescription, targetAudience, subject,
 *  classification, identifier, recordInfo, unstructuredText, and source.
 */
public abstract class LangGroupElement extends ModsElement {

   
    public final static String ATT_LANG = "lang";
    public final static String ATT_XMLLANG = "xml:lang";
    public final static String ATT_SCRIPT = "script";
    public final static String ATT_TRANSLITERATION = "transliteration";
    
    /** Default constructor */
    public LangGroupElement () {
        super ();
    }
    
    /** Get the value of the no-namespace "lang" attribute. This is not the same as the xml:lang
     *  attribute. */
    public String getLang () {
        return getAttribute (ATT_LANG);
    }
    
    /** Set the value of the no-namespace "lang" attribute. This is not the same as the xml:lang
     *  attribute. */
    public void setLang (String value) {
        setAttribute (ATT_LANG, value);
    }

    /** Get the value of the xml:lang attribute. This is not the same as the no-namespace lang
     *  attribute. */
    public String getXMLLang () {
        return getAttribute (ATT_XMLLANG);
    }
    
    /** Set the value of the xml:lang attribute. This is not the same as the no-namespace lang
     *  attribute. */
    public void setXMLLang (String value) {
        setAttribute (ATT_XMLLANG, value);
    }

    /** Get the value of the no-namespace "script" attribute.  */
    public String getScript () {
        return getAttribute (ATT_SCRIPT);
    }
    
    /** Set the value of the no-namespace "lang" attribute.  */
    public void setScript (String value) {
        setAttribute (ATT_SCRIPT, value);
    }

    /** Get the value of the no-namespace "transliteration" attribute.  */
    public String getTransliteration () {
        return getAttribute (ATT_TRANSLITERATION);
    }
    
    /** Set the value of the no-namespace "lang" attribute.  */
    public void setTransliteration (String value) {
        setAttribute (ATT_TRANSLITERATION, value);
    }

}
