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

import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputDocument;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

import edu.harvard.hul.ois.ots.schemas.StaxUtils.RootXmlHelper;
import edu.harvard.hul.ois.ots.schemas.XmlContent.GenericElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class Mods extends ModsElement {

    private final static String ELEM_TITLEINFO = "titleInfo";
    private final static String  ELEM_NAME = "name";
    private final static String ELEM_TYPEOFRES = "typeOfResource";
    private final static String  ELEM_GENRE = "genre";
    private final static String  ELEM_ORIGININFO = "originInfo";
    private final static String  ELEM_LANGUAGE = "language";
    private final static String  ELEM_PHYSDESC = "physicalDescription";
    private final static String  ELEM_ABSTRACT = "abstract";
    private final static String  ELEM_TOC = "tableOfContents";
    private final static String  ELEM_TARGET = "targetAudience";
    private final static String  ELEM_NOTE = "note";
    private final static String  ELEM_SUBJECT = "subject";
    private final static String  ELEM_CLASSIFICATION = "classification";
    private final static String ELEM_RELITEM = "relatedItem";
    private final static String  ELEM_IDENTIFIER = "identifier";
    private final static String  ELEM_LOCATION = "location";
    private final static String  ELEM_ACCESS = "accessCondition";
    private final static String  ELEM_PART = "part";
    private final static String  ELEM_EXT = "extension";
    private final static String  ELEM_RECINFO = "recordInfo";
    
    /* Permitted values for typeOfResource */
    public final static String[] VALUES_TYPEOFRES = 
    { "text",
    	"cartographic",
    	"notated music",
    	"sound recording-musical",
    	"sound recording-nonmusical",
    	"sound recording", 
    	"still image",
    	"moving image",
    	"three dimensional object",
    	"software, multimedia",
    	"mixed material",
    	"" };
    
    public enum TypeOfResourceCollection {yes};
    public enum TypeOfResourceManuscript {yes};

    private boolean isRoot = false;

    /** Constructor from XML, with isRoot. The isRoot parameter
     *  affects whether schema attributes are written out. */
    public Mods(XMLStreamReader reader, boolean isRoot) 
            throws XMLStreamException, XmlContentException {
        name = "mods";
        this.isRoot = isRoot;
        initAllElements();
        parse(reader);
    }

    /** Constructor from XML. */
    public Mods(XMLStreamReader reader) 
            throws XMLStreamException, XmlContentException {
        super();
        name = "mods";
        isRoot = false;
        defineRestrictions ();
        parse(reader);
    }
    
    /** No-argument constructor. This can be used when the caller is
     *  composing a Mix element from its constituent classes. */
    public Mods () {
        super();
        name = "mods";
        isRoot = false;
        defineRestrictions ();
    }
    
    /** Constructor with isRoot. The isRoot parameter
     *  affects whether schema attributes are written out. */
    public Mods(boolean isRoot) { 
        super();
        name = "mods";
        this.isRoot = isRoot;
    }
    
	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}


    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_TYPEOFRES, VALUES_TYPEOFRES);
    }



    /** Top-level output method. 
     *  The top level can be either mods or modsCollection. What do I need
     *  to do to make that work? 
     */
    @Override
    public void output(XMLStreamWriter writer) throws XMLStreamException {
        /*
    	if (isRoot) {
            SMOutputDocument doc = SMOutputFactory.createOutputDocument(writer,"1.0","UTF-8",false);    
            doc.setIndentation("\n",1,1);
            SMOutputContext context = doc.getContext ();
            SMNamespace xsi = context.getNamespace (XMLNS, SCHEMA_PREFIX);
            SMOutputElement root = doc.addElement(xsi, "mods");
            root.addAttribute("xmlns", XMLNS);
            root.addAttribute(xsi,"schemaLocation", XMLNS + " " + MODS_SCHEMALOCATION);     
            
            Iterator<String> attrIter = attributes.keySet().iterator();
            while (attrIter.hasNext ()) {
                String key = attrIter.next ();
                String value = attributes.get (key);
                root.addAttribute (key, value);
            }
            headlessOutput (xsi, root);
            doc.closeRoot ();
        }
        else {
            SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
            SMNamespace xsi = frag.getNamespace(XMLNS, SCHEMA_PREFIX);
            SMOutputElement root = frag.addElement(xsi, "mods");
            Iterator<String> attrIter = attributes.keySet().iterator();
            while (attrIter.hasNext ()) {
                String key = attrIter.next ();
                String value = attributes.get (key);
                root.addAttribute (key, value);
            }
            headlessOutput (xsi, root);
            frag.closeRoot ();
        }
        */
		if(isRoot) {
			SMOutputDocument doc = SMOutputFactory.createOutputDocument(writer);			
			SMNamespace modsNS = doc.getNamespace(XMLNS,modsNSC.getPrefix());
			SMOutputElement root = doc.addElement(modsNS,"mods");		
			RootXmlHelper.output(writer,getAllNamespaceSchemaContexts(),root);		
            Iterator<String> attrIter = attributes.keySet().iterator();
            while (attrIter.hasNext ()) {
                String key = attrIter.next ();
                String value = attributes.get (key);
                root.addAttribute (key, value);
            }
			headlessOutput(modsNS,root);
			doc.closeRoot();
		}
		else {
			SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
			SMNamespace modsNS = frag.getNamespace(XMLNS,modsNSC.getPrefix());
			SMOutputElement root = frag.addElement(modsNS,"mods");

            String value = attributes.get ("ID");
            if(value != null)
            	root.addAttribute ("ID", value);
            value = attributes.get ("version");
            if(value != null)
            	root.addAttribute ("version", value);

			headlessOutput(modsNS,root);
			frag.closeRoot();
		}
    }

    /** Use this instead of output to avoid double-output of mods element */
    public void headlessOutput(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        List<GenericElement> elements = getAllElements();
        for (GenericElement elem : elements) {
            elem.output (ns, parent);
        }
    }

    /** Get List of TitleInfo objects */
    @SuppressWarnings("unchecked")
    public List<TitleInfo> getTitleInfos () {
        return (List<TitleInfo>) genericList(getList (ELEM_TITLEINFO));
    }
    
    /** Add a TitleInfo object 
     * @throws XmlContentException */
    public void addTitleInfo (TitleInfo x) throws XmlContentException {
    	addToList(x, ELEM_TITLEINFO);
    }

    /** Get List of Name objects */
    @SuppressWarnings("unchecked")
    public List<Name> getNames () {
        return (List<Name>)
             genericList(getList (ELEM_NAME));
    }
    
    /** Add a Name object 
     * @throws XmlContentException */
    public void addName (Name x) throws XmlContentException {
    	addToList(x, ELEM_NAME);
    }

    /** Get List of typeOfResource objects, which are StringElements */
    @SuppressWarnings("unchecked")
    public List<StringElement> getTypesOfResource () {
        return (List<StringElement>)
        genericList(getList (ELEM_TYPEOFRES));
    }
    
    /** Add a typeOfResource object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addTypeOfResource (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_TYPEOFRES);
        addToList(se, ELEM_TYPEOFRES);
        return se;
    }

    /** Get List of genre objects, which are StringElements */
    @SuppressWarnings("unchecked")
    public List<StringElement> getGenres () {
        return (List<StringElement>)
        genericList(getList (ELEM_GENRE));
    }
    
    /** Add a genre object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addGenre (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_GENRE);
        addToList(se, ELEM_GENRE);
        return se;
    }

    /** Get List of OriginInfo objects */
    @SuppressWarnings("unchecked")
    public List<OriginInfo> getOriginInfos () {
        return (List<OriginInfo>) genericList(getList (ELEM_ORIGININFO));
    }
    
    /** Add an OriginInfo object 
     * @throws XmlContentException
     */
    public void addOriginInfo (OriginInfo x) throws XmlContentException {
        addToList(x, ELEM_ORIGININFO);
    }

    /** Get List of language objects, which are StringElements */
    @SuppressWarnings("unchecked")
    public List<Language> getLanguages () {
        return (List<Language>) genericList(getList (ELEM_LANGUAGE));
    }
    
    /** Add a language object 
     * @throws XmlContentException
     */
    public void addLanguage (Language x) throws XmlContentException {
        addToList(x, ELEM_LANGUAGE);
    }

    /** Get List of PhysicalDescription objects */
    @SuppressWarnings("unchecked")
    public List<PhysicalDescription> getPhysicalDescriptions () {
        return (List<PhysicalDescription>) genericList(getList (ELEM_PHYSDESC));
    }
    
    /** Add a PhysicalDescription object 
     * @throws XmlContentException
     */
    public void addPhysicalDescription (PhysicalDescription x) throws XmlContentException {
        addToList(x, ELEM_PHYSDESC);
    }

    /** Get List of abstract objects, which are StringElements */
    @SuppressWarnings("unchecked")
    public List<StringElement> getAbstracts () {
        return (List<StringElement>) genericList(getList (ELEM_ABSTRACT));
    }
    
    /** Add a abstract object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addAbstract (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_ABSTRACT);
        addToList(se, ELEM_ABSTRACT);
        return se;
    }
    
    /** Get List of tableOfContents objects, which are StringElements */
    @SuppressWarnings("unchecked")
    public List<StringElement> getTablesOfContents () {
        return (List<StringElement>) genericList(getList (ELEM_TOC));
    }
    
    /** Add a tableOfContents object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addTableOfContents (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_TOC);
        addToList(se, ELEM_TOC);
        return se;
    }

    /** Get List of targetAudience objects, which are StringElements */
    @SuppressWarnings("unchecked")
    public List<StringElement> getTargetAudiences () {
        return (List<StringElement>) genericList(getList (ELEM_TARGET));
    }
    
    /** Add a targetAudience object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addTargetAudience (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_TARGET);
        addToList(se, ELEM_TARGET);
        return se;
    }

    /** Get List of note objects, which are StringElements */
    @SuppressWarnings("unchecked")
    public List<StringElement> getNotes () {
        return (List<StringElement>) genericList(getList (ELEM_NOTE));
    }
    
    /** Add a note object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addNote (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_NOTE);
        addToList(se, ELEM_NOTE);
        return se;
    }

    /** Get List of Subject objects */
    @SuppressWarnings("unchecked")
    public List<Subject> getSubjects () {
        return (List<Subject>) genericList(getList (ELEM_SUBJECT));
    }
    
    /** Add a Subject object 
     * @throws XmlContentException
     */
    public void addSubject (Subject x) throws XmlContentException {
        addToList(x, ELEM_SUBJECT);
    }

    /** Get List of classification objects, which are StringElements */
    @SuppressWarnings("unchecked")
    public List<StringElement> getClassifications () {
        return (List<StringElement>) genericList(getList (ELEM_CLASSIFICATION));
    }

    /** Add a classification object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addClassification (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_CLASSIFICATION);
        addToList(se, ELEM_CLASSIFICATION);
        return se;
    }

    /** Get List of RelatedItem objects */
    @SuppressWarnings("unchecked")
    public List<RelatedItem> getRelatedItems () {
        return (List<RelatedItem>) genericList(getList (ELEM_RELITEM));
    }
    
    /** Add a RelatedItem object 
     * @throws XmlContentException */
    public void addRelatedItem (RelatedItem x) throws XmlContentException {
        addToList(x, ELEM_RELITEM);
    }

    /** Get List of identifier objects, which are StringElements */
    @SuppressWarnings("unchecked")
    public List<StringElement> getIdentifiers () {
        return (List<StringElement>)
        genericList(getList (ELEM_IDENTIFIER));
    }

    /** Add an identifier object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addIdentifier (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_IDENTIFIER);
        addToList(se, ELEM_IDENTIFIER);
        return se;
    }

    /** Get List of Location objects */
    @SuppressWarnings("unchecked")
    public List<Location> getLocations () {
        return (List<Location>) genericList(getList (ELEM_LOCATION));
    }
    
    /** Add a Location object 
     * @throws XmlContentException
     */
    public void addLocation (Location x) throws XmlContentException {
        addToList(x, ELEM_LOCATION);
    }

    /** Get List of accessCondition objects, which are StringElements */
    @SuppressWarnings("unchecked")
    public List<StringElement> getAccessConditions () {
        return (List<StringElement>) genericList(getList (ELEM_ACCESS));
    }

    /** Add an accessCondition object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addAccessCondition (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_ACCESS);
        addToList(se, ELEM_ACCESS);
        return se;
    }

    /** Get List of part objects */
    @SuppressWarnings("unchecked")
    public List<StringElement> getParts () {
        return (List<StringElement>) genericList(getList (ELEM_PART));
    }

    /** Add a Part object 
     * @throws XmlContentException
     */
    public void addPart (Part x) throws XmlContentException {
        addToList(x, ELEM_PART);
    }

    /** Get List of extension objects */
    @SuppressWarnings("unchecked")
    public List<StringElement> getExtensions () {
        return (List<StringElement>) genericList(getList (ELEM_EXT));
    }

    /** Add an extension object 
     * @throws XmlContentException
     */
    public void addExtension (Extension x) throws XmlContentException {
        addToList(x, ELEM_EXT);
    }

    /** Get List of RecordInfo objects */
    @SuppressWarnings("unchecked")
    public List<RecordInfo> getRecordInfos () {
        return (List<RecordInfo>) genericList(getList (ELEM_RECINFO));
    }

    /** Add an RecordInfo object 
     * @throws XmlContentException
     */
    public void addRecordInfo (RecordInfo x) throws XmlContentException {
        addToList(x, ELEM_RECINFO);
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }
    
    protected void dispatch (String localName, XMLStreamReader reader) 
                throws XMLStreamException, XmlContentException {
        if (matchList (reader, this, 
                 localName, ELEM_TITLEINFO,
                 TitleInfo.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_NAME,
                Name.class))
           return;
        if (matchList (reader, this, 
                localName, ELEM_TYPEOFRES,
                StringElement.class))
           return;
        if (matchList (reader, this, 
                localName, ELEM_GENRE,
                StringElement.class))
           return;
        if (matchList (reader, this, 
                localName, ELEM_ORIGININFO,
                OriginInfo.class))
           return;
        if (matchList (reader, this, 
                localName, ELEM_LANGUAGE,
                Language.class))
           return;
        if (matchList (reader, this, 
                localName, ELEM_PHYSDESC,
                PhysicalDescription.class))
           return;
        if (matchList (reader, this, 
                localName, ELEM_ABSTRACT,
                StringElement.class))
           return;
        if (matchList (reader, this, 
                localName, ELEM_TOC,
                StringElement.class))
           return;
        if (matchList (reader, this, 
                localName, ELEM_TARGET,
                StringElement.class))
           return;
        if (matchList (reader, this, 
                localName, ELEM_NOTE,
                StringElement.class))
           return;
        if (matchList (reader, this, 
                localName, ELEM_SUBJECT,
                Subject.class))
           return;
        if (matchList (reader, this, 
                localName, ELEM_CLASSIFICATION,
                StringElement.class))
           return;
        if (matchList (reader, this, 
                localName, ELEM_ACCESS,
                StringElement.class))
           return;
        if (matchList (reader, this, 
                localName, ELEM_PART,
                Part.class))
           return;
        if (matchList (reader, this,
                 localName, ELEM_RELITEM,
                 RelatedItem.class))
            return;
        if (matchList (reader, this,
                localName, ELEM_IDENTIFIER,
                StringElement.class))
           return;
        if (matchList (reader, this,
                localName, ELEM_LOCATION,
                Location.class))
           return;
        if (matchList (reader, this,
                localName, ELEM_ACCESS,
                StringElement.class))
           return;
        if (matchList (reader, this,
                localName, ELEM_PART,
                Part.class))
           return;
        if (matchList (reader, this,
                localName, ELEM_EXT,
                Extension.class))
           return;
        if (matchList (reader, this,
                localName, ELEM_RECINFO,
                RecordInfo.class))
           return;
        throw new XmlContentException ("Unknown element name " + localName);
    }
}