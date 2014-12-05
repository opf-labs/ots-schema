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

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import org.custommonkey.xmlunit.*;

import edu.harvard.hul.ois.ots.schemas.ModsMD.Cartographics;
import edu.harvard.hul.ois.ots.schemas.ModsMD.Language;
import edu.harvard.hul.ois.ots.schemas.ModsMD.Mods;
import edu.harvard.hul.ois.ots.schemas.ModsMD.Name;
import edu.harvard.hul.ois.ots.schemas.ModsMD.OriginInfo;
import edu.harvard.hul.ois.ots.schemas.ModsMD.RelatedItem;
import edu.harvard.hul.ois.ots.schemas.ModsMD.Role;
import edu.harvard.hul.ois.ots.schemas.ModsMD.Subject;
import edu.harvard.hul.ois.ots.schemas.ModsMD.TitleInfo;
import edu.harvard.hul.ois.ots.schemas.XmlContent.GenericElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;

public class ModsTest extends XMLTestCase {

    /* Sample for testing mods element */
    private String modsSample = 
        "<mods:mods xmlns:mods=\"http://www.loc.gov/mods/v3\" \n" +
        "   xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" >\n" + 
        "<mods:titleInfo type=\"abbreviated\"> \n" +
        "  <mods:title>Robinson Crusoe</mods:title> \n" +
        "  <mods:subTitle>The Life and strange and surprising Adventures of Robinson Crusoe</mods:subTitle> \n" +
        "  <mods:partNumber>1</mods:partNumber> \n" +
        "  <mods:partName>Volume 1</mods:partName> \n" +
        "  <mods:nonSort>The L</mods:nonSort> \n" +
        "</mods:titleInfo> \n" +
        "<mods:subject> \n" +
        "  <mods:name> \n" +
        "     <mods:namePart>Daniel Defoe</mods:namePart> \n" +
        "  </mods:name> \n" +
        "  <mods:temporal>1798</mods:temporal> \n" +
        "  <mods:hierarchicalGeographic> \n" +
        "    <mods:extraterrestrialArea>Mars</mods:extraterrestrialArea> \n"+
        "    <mods:country>Free Mars</mods:country> \n" +
        "  </mods:hierarchicalGeographic> \n" +
        "  <mods:genre>Historical novel</mods:genre>\n" +
        "</mods:subject> \n" +
        "<mods:relatedItem> \n" +
        "  <mods:titleInfo> \n" +
        "    <mods:title>Robinson Crusoe on Mars</mods:title> \n" +
        "  </mods:titleInfo> \n" +
        "</mods:relatedItem> \n" +
        "</mods:mods>";

    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(modsSample));
        //move to root tag
        xmlr.nextTag();
        Mods mods = new Mods (xmlr);
        List<TitleInfo> tis = mods.getTitleInfos();
        assertNotNull (tis);
        TitleInfo ti = tis.get(0);
        List<StringElement> titles = ti.getTitles ();
        assertNotNull (titles);
        StringElement title = titles.get(0);
        assertEquals ("Robinson Crusoe", title.toString());
        List<RelatedItem> relItems = mods.getRelatedItems ();     // Recursion! Wheee!!
        assertNotNull (relItems);
        Mods relItem = relItems.get(0);
        tis = relItem.getTitleInfos();
        ti = tis.get(0);
        assertNotNull (ti);
        titles = ti.getTitles ();
        title = titles.get(0);
        assertEquals ("Robinson Crusoe on Mars", title.toString());
    }
    
    /** This tests whether retrieval of elements in sequence works */
    public void testAllElements () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(modsSample));
        //move to root tag
        xmlr.nextTag();
        Mods mods = new Mods (xmlr);
        List<GenericElement> elems = mods.getAllElements();
        GenericElement e0 = elems.get(0);
        assertTrue (e0 instanceof TitleInfo);
        GenericElement e1 = elems.get(1);
        assertTrue (e1 instanceof Subject);
        GenericElement e2 = elems.get(2);
        assertTrue (e2 instanceof RelatedItem);
    }
    
    /** Test creating by composition and writing to XML. */
    public void testWrite () throws Exception {
        // Create a mix object
        Mods mods = new Mods (true);
        TitleInfo tinfo = new TitleInfo ();
        tinfo.setType("abbreviated");
        tinfo.addTitle ("When I Was a Boy");
        tinfo.addSubTitle ("A Subtitle");
        tinfo.addNonSort ("A ");
        mods.addTitleInfo (tinfo);
        
        Name nam = new Name ();
        nam.addNamePart ("Frank Hayes");
        Role role = new Role ();
        role.addRoleTerm ("composer");
        role.addRoleTerm ("lyricist");
        nam.addRole (role);
        mods.addName (nam);
        
        mods.addTypeOfResource ("notated music");
        mods.addGenre ("filk");
        
        OriginInfo oinfo = new OriginInfo ();
        oinfo.addPublisher ("Firebird Arts and Music");
        mods.addOriginInfo (oinfo);
        
        Language lng = new Language ();
        StringElement lterm = lng.addLanguageTerm ("eng");
        lterm.setAttribute ("authority", "iso639-2b");
        mods.addLanguage (lng);
        
        mods.addAbstract ("A study of early computing");
        mods.addTargetAudience ("geeks");
        mods.addNote ("B flat");
        mods.addClassification ("Unknown");
        
        Subject subj = new Subject ();
        Cartographics cart = new Cartographics ();
        cart.setScale ("1:1");
        cart.addCoordinates("Barefoot and uphill");
        cart.setProjection ("Both ways");
        subj.addCartographics(cart);
        mods.addSubject (subj);
        
        RelatedItem relItem = new RelatedItem ();
        tinfo = new TitleInfo ();
        tinfo.addTitle("Never Set the Cat on Fire");
        relItem.addTitleInfo (tinfo);
        mods.addRelatedItem (relItem);
        
        // Now write it out
        XMLOutputFactory f = XMLOutputFactory.newInstance();
        StringWriter stringWriter = new StringWriter();
        XMLStreamWriter sw = f.createXMLStreamWriter(stringWriter); 
        mods.output(sw);
        sw.close();
        String newXml = stringWriter.toString();
        stringWriter.close();
        System.out.println (newXml);
    }

    public void testComposition () throws Exception {
        new Mods ();
        
    }
}
