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

import edu.harvard.hul.ois.ots.schemas.ModsMD.Language;
import edu.harvard.hul.ois.ots.schemas.ModsMD.Location;
import edu.harvard.hul.ois.ots.schemas.ModsMD.Mods;
import edu.harvard.hul.ois.ots.schemas.ModsMD.Name;
import edu.harvard.hul.ois.ots.schemas.ModsMD.OriginInfo;
import edu.harvard.hul.ois.ots.schemas.ModsMD.PhysicalDescription;
import edu.harvard.hul.ois.ots.schemas.ModsMD.Place;
import edu.harvard.hul.ois.ots.schemas.ModsMD.RecordInfo;
import edu.harvard.hul.ois.ots.schemas.ModsMD.Role;
import edu.harvard.hul.ois.ots.schemas.ModsMD.Subject;
import edu.harvard.hul.ois.ots.schemas.ModsMD.TitleInfo;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.custommonkey.xmlunit.*;


public class ReadModsTest extends XMLTestCase {
    public void testReadFilk () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader
                (new InputStreamReader (new FileInputStream("test_xml/concertino2006.xml"), "UTF-8"));
//        String originalFile = readFileAsString("test_xml/concertino2006.xml");
        xmlr.nextTag();
        Mods mods = new Mods(xmlr,true);
        List<RecordInfo> recInfos = mods.getRecordInfos();
        assertNotNull (recInfos);
        recInfos.get(0);
        
    }

    public void testReadVC () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader
                (new InputStreamReader (new FileInputStream("test_xml/vcmods.xml"), "UTF-8"));
//        String originalFile = readFileAsString("test_xml/vcmods.xml");
        xmlr.nextTag();
        Mods mods = new Mods(xmlr,true);
        List<TitleInfo> titleInfos = mods.getTitleInfos ();
        TitleInfo tinfo = titleInfos.get(0);
        List<StringElement> nonsorts = tinfo.getNonSorts ();
        StringElement nonsort = nonsorts.get (0);
        assertEquals ("The ", nonsort.toString ());    // Whitespace must stay!
        
        List<Name> names = mods.getNames ();
        Name nam = names.get(0);
        assertEquals ("personal", nam.getAttribute ("type"));
        List<Role> roles = nam.getRoles ();
        Role role = roles.get(0);
        List<StringElement> roleTerms = role.getRoleTerms();
        StringElement term = roleTerms.get(0);
        assertEquals ("creator", term.toString ());
        
        List<StringElement> tors = mods.getTypesOfResource();
        StringElement tor = tors.get(0);
        assertEquals ("text", tor.toString ());
        
        List<OriginInfo> originInfos = mods.getOriginInfos();
        OriginInfo oInfo = originInfos.get(0);
        List<Place> places = oInfo.getPlaces ();
        Place place = places.get(0);
        List<StringElement> pterms = place.getPlaceTerms ();
        StringElement pterm = pterms.get(0);
        assertTrue (pterm.toString().contains ("mau"));
        place = places.get(1);
        pterms = place.getPlaceTerms ();
        pterm = pterms.get(0);
        assertTrue (pterm.toString().contains ("Springfield"));
        
        List<StringElement> pubs = oInfo.getPublishers ();
        StringElement pub = pubs.get(0);
        assertTrue (pub.toString().contains ("Daily"));
        
        List<StringElement> dtiss = oInfo.getDatesIssued ();
        StringElement dtis = dtiss.get(0);
        assertTrue (dtis.toString ().contains ("Tuesday"));
        
        List<StringElement> issuances = oInfo.getIssuances ();
        StringElement issuance = issuances.get(0);
        assertTrue (issuance.toString().contains ("monographic"));
        
        List<Language> languages = mods.getLanguages ();
        Language language = languages.get(0);
        List<StringElement> langTerms = language.getLanguageTerms ();
        StringElement langTerm = langTerms.get(0);
        assertEquals ("eng", langTerm.toString ());
        
        List<PhysicalDescription> phdescs = mods.getPhysicalDescriptions();
        PhysicalDescription phdesc = phdescs.get(0);
        List<StringElement> forms = phdesc.getForms ();
        StringElement form = forms.get(0);
        assertEquals ("marcform", form.getAttribute ("authority"));
        List<StringElement> extents = phdesc.getExtents ();
        StringElement extent = extents.get(0);
        assertTrue (extent.toString().contains ("sheet"));
        
        List<StringElement> notes = mods.getNotes ();
        StringElement note = notes.get(0);
        note = notes.get(1);
        assertTrue (note.toString().contains ("We print"));
        
        List<Subject> subjects = mods.getSubjects ();
        Subject subj = subjects.get(0);
        List<StringElement> topics = subj.getTopics ();
        topics.get(0);
        List<StringElement> geographics = subj.getGeographics ();
        StringElement geographic = geographics.get(0);
        assertEquals ("Massachusetts", geographic.toString ());
        geographic = geographics.get(1);
        assertEquals ("Russell", geographic.toString ());
        
        List<Location> locs = mods.getLocations ();
        Location loc = locs.get(0);
        List<StringElement> physLocs = loc.getPhysicalLocations();
        StringElement physLoc = physLocs.get(0);
        assertEquals ("Law School", physLoc.toString ());
        
        List<RecordInfo>recInfos = mods.getRecordInfos ();
        RecordInfo recInfo = recInfos.get(0);
        List<StringElement> rcss = recInfo.getRecordContentSources();
        StringElement rcs = rcss.get(0);
        assertEquals ("marcorg", rcs.getAttribute ("authority"));
        List<StringElement> rcds = recInfo.getRecordCreationDates();
        StringElement rcd = rcds.get(0);
        assertEquals ("marc", rcd.getAttribute ("encoding"));
        List<StringElement> rchds = recInfo.getRecordChangeDates();
        StringElement rchd = rchds.get(0);
        assertEquals ("iso8601", rchd.getAttribute ("encoding"));
        List<StringElement> rcids = recInfo.getRecordIdentifiers();
        StringElement rcid = rcids.get(0);
        assertEquals ("HOLLIS", rcid.getAttribute ("source"));
    }
/*
    private static String readFileAsString(String filePath) throws java.io.IOException{
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }
*/
}
