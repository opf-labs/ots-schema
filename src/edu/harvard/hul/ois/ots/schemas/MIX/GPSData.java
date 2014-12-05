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
package edu.harvard.hul.ois.ots.schemas.MIX;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.Rational;
import edu.harvard.hul.ois.ots.schemas.XmlContent.RationalElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class GPSData extends MixElement {
    //TODO lots of work remaining, this is big

    private final static String ELEM_VERS = "gpsVersionID";
    private final static String ELEM_LATREF = "gpsLatitudeRef";
    private final static String ELEM_LAT = "GPSLatitude";
    private final static String ELEM_LONGREF = "gpsLongitudeRef";
    private final static String ELEM_LONG = "GPSLongitude";
    private final static String ELEM_ALTREF = "gpsAltitudeRef";
    private final static String ELEM_ALT = "gpsAltitude";
    private final static String ELEM_TIMESTAMP = "gpsTimeStamp";
    private final static String ELEM_SATELLITES = "gpsSatellites";
    private final static String ELEM_STATUS = "gpsStatus";
    private final static String ELEM_MEASUREMODE = "gpsMeasureMode";
    private final static String ELEM_DOP = "gpsDOP";
    private final static String ELEM_SPEEDREF = "gpsSpeedRef";
    private final static String ELEM_SPEED = "gpsSpeed";
    private final static String ELEM_TRACKREF = "gpsTrackRef";
    private final static String ELEM_TRACK = "gpsTrack";
    private final static String ELEM_IMGDIRREF = "gpsImgDirectionRef";
    private final static String ELEM_IMGDIR = "gpsImgDirection";
    private final static String ELEM_MAPDATUM = "gpsMapDatum";
    private final static String ELEM_DESTLATREF = "gpsDestLatitudeRef";
    private final static String ELEM_DESTLAT = "GPSDestLatitude";
    private final static String ELEM_DESTLONGREF = "gpsDestLongitudeRef";
    private final static String ELEM_DESTLONG = "GPSDestLongitude";
    private final static String ELEM_DESTBEARREF = "gpsDestBearingRef";
    private final static String ELEM_DESTBEAR = "gpsDestBearing";
    private final static String ELEM_DESTDISTREF = "gpsDestDistanceRef";
    private final static String ELEM_DESTDIST = "gpsDestDistance";
    private final static String ELEM_PROCMETHOD = "gpsProcessingMethod";
    private final static String ELEM_AREAINFO = "gpsAreaInformation";
    private final static String ELEM_DATESTAMP = "gpsDateStamp";
    private final static String ELEM_DIFF = "gpsDifferential";

    /* Permitted values for gpsLatitudeRef */
    private final static String[] VALUES_LATREF = 
    { "N", "S" };

    /* Permitted values for gpsLatitudeRef */
    private final static String[] VALUES_LONGREF =  
    { "E", "W" };

    /* Permitted values for gpsAltitudeRef */
    private final static String[] VALUES_ALTREF =  
    { "Sea level", "Sea level reference (negative value)" };

    /* Permitted values for gpsStatus */
    private final static String[] VALUES_STATUS =  
    { "A", "V" };

    /* Permitted values for gpsMeasureMode */
    private final static String[] VALUES_MEASUREMODE =  
    { "2-dimensional measurement",
      "3-dimensional measurement" };

    /* Permitted values for gpsSpeedRef */
    private final static String[] VALUES_SPEEDREF =  
    { "K", "M", "N" };

    /* Permitted values for gpsTrackRef */
    private final static String[] VALUES_TRACKREF =  
    { "T", "M" };
    
    /* Permitted values for imgDirRef */
    private final static String[] VALUES_IMGDIRREF = 
    { "T", "M" };

    /* Permitted values for gpsDESTLatitudeRef */
    private final static String[] VALUES_DESTLATREF = VALUES_LATREF;

    /* Permitted values for gpsDESTLongitudeRef */
    private final static String[] VALUES_DESTLONGREF = VALUES_LONGREF;

    /* Permitted values for gpsDestBearRef */
    private final static String[] VALUES_DESTBEARREF = 
    { "T", "M" };

    /* Permitted values for gpsDestBearRef */
    private final static String[] VALUES_DESTDISTREF = 
    { "K", "M", "N" };

    /* Permitted values for gpsDifferential */
    private final static String[] VALUES_DIFF = 
    { "Measurement without differential correction",
      "Differential correction applied" };


    /**  Constructor from XML
     */
    public GPSData (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "GPSData";
        defineRestrictions();
        parse(reader);
    }
    
    /** No-argument constructor */
    public GPSData () {
        super();
        name = "GPSData";
        defineRestrictions();
    }

    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_LATREF, VALUES_LATREF);
        addRestriction(ELEM_LONGREF, VALUES_LONGREF);
        addRestriction(ELEM_ALTREF, VALUES_ALTREF);
        addRestriction(ELEM_STATUS, VALUES_STATUS);
        addRestriction(ELEM_MEASUREMODE, VALUES_MEASUREMODE);
        addRestriction(ELEM_SPEEDREF, VALUES_SPEEDREF);
        addRestriction(ELEM_TRACKREF, VALUES_TRACKREF);
        addRestriction(ELEM_IMGDIRREF, VALUES_IMGDIRREF);
        addRestriction(ELEM_DESTLATREF, VALUES_DESTLATREF);
        addRestriction(ELEM_DESTLONGREF, VALUES_DESTLONGREF);
        addRestriction(ELEM_DESTBEARREF, VALUES_DESTBEARREF);
        addRestriction(ELEM_DESTDISTREF, VALUES_DESTDISTREF);
        addRestriction(ELEM_DIFF, VALUES_DIFF);
    }


    public StringElement getGpsVersionID () {
        return (StringElement) getField (ELEM_VERS);
    }
    
    public void setGpsVersionID (String id) throws XmlContentException {
        setField (ELEM_VERS, id);
    }
    
    public StringElement getGpsLatitudeRef () {
        return (StringElement) getField (ELEM_LATREF);
    }
    
    public void setGpsLatitudeRef (String s) throws XmlContentException {
        setField (ELEM_LATREF, s);
    }

    public GPSLatitude getGPSLatitude () {
        return (GPSLatitude) getField (ELEM_LAT);
    }
    
    public void setGPSLatitude (GPSLatitude lat) throws XmlContentException {
        setField (ELEM_LAT, lat);
    }

    public StringElement getGpsLongitudeRef () {
        return (StringElement) getField (ELEM_LONGREF);
    }
    
    public void setGpsLongitudeRef (String s) throws XmlContentException {
        setField (ELEM_LONGREF, s);
    }

    public GPSLongitude getGPSLongitude () {
        return (GPSLongitude) getField (ELEM_LONG);
    }
    
    public void setGPSLongitude (GPSLongitude lon) throws XmlContentException {
        setField (ELEM_LONG, lon);
    }

    public StringElement getGpsAltitudeRef () {
        return (StringElement) getField (ELEM_ALTREF);
    }
    
    public void setGpsAltitudeRef (String s) throws XmlContentException {
        setField (ELEM_ALTREF, s);
    }

    public RationalElement getGpsAltitude () {
        return (RationalElement) getField (ELEM_ALT);
    }
    
    public void setGpsAltitude (Rational alt) throws XmlContentException {
        setField (ELEM_ALT, alt);
    }
    
    public StringElement getGpsTimeStamp () {
        return (StringElement) getField (ELEM_TIMESTAMP);
    }
    
    public void setGpsTimeStamp(String s) throws XmlContentException {
        setField (ELEM_TIMESTAMP, s);
    }

    public StringElement getGpsSatellites () {
        return (StringElement) getField (ELEM_SATELLITES);
    }
    
    public void setGpsSatellites(String s) throws XmlContentException {
        setField (ELEM_SATELLITES, s);
    }
    
    public StringElement getGpsStatus () {
        return (StringElement) getField (ELEM_STATUS);
    }
    
    public void setGpsStatus (String s) throws XmlContentException {
        setField (ELEM_STATUS, s);
    }

    public StringElement getGpsMeasureMode () {
        return (StringElement) getField (ELEM_MEASUREMODE);
    }
    
    public void setGpsMeasureMode(String s) throws XmlContentException {
        setField (ELEM_MEASUREMODE, s);
    }

    public RationalElement getGpsDOP () {
        return (RationalElement) getField (ELEM_DOP);
    }
    
    public void setGpsDOP (Rational dop) throws XmlContentException {
        setField (ELEM_DOP, dop);
    }
    
    public StringElement getGpsSpeedRef () {
        return (StringElement) getField (ELEM_SPEEDREF);
    }
    
    public void setGpsSpeedRef (String s) throws XmlContentException {
        setField (ELEM_SPEEDREF, s);
    }

    public RationalElement getGpsSpeed () {
        return (RationalElement) getField (ELEM_SPEED);
    }
    
    public void setGpsSpeed (Rational s) throws XmlContentException {
        setField (ELEM_SPEED, s);
    }
    
    public StringElement getGpsTrackRef () {
        return (StringElement) getField (ELEM_TRACKREF);
    }
    
    public void setGpsTrackRef (String s) throws XmlContentException {
        setField (ELEM_TRACKREF, s);
    }

    public RationalElement getGpsTrack () {
        return (RationalElement) getField (ELEM_TRACK);
    }
    
    public void setGpsTrack (Rational t) throws XmlContentException {
        setField (ELEM_TRACK, t);
    }
    
    public StringElement getGpsImgDirectionRef () {
        return (StringElement) getField (ELEM_IMGDIRREF);
    }
    
    public void setGpsImgDirectionRef (String s) throws XmlContentException {
        setField (ELEM_IMGDIRREF, s);
    }

    public RationalElement getGpsImgDirection () {
        return (RationalElement) getField (ELEM_IMGDIR);
    }
    
    public void setGpsImgDirection (Rational t) throws XmlContentException {
        setField (ELEM_IMGDIR, t);
    }

    public StringElement getGpsMapDatum () {
        return (StringElement) getField (ELEM_MAPDATUM);
    }
    
    public void setGpsMapDatum (String s) throws XmlContentException {
        setField (ELEM_MAPDATUM, s);
    }

    public StringElement getGpsDestLatitudeRef () {
        return (StringElement) getField (ELEM_DESTLATREF);
    }
    
    public void setGpsDestLatitudeRef (String s) throws XmlContentException {
        setField (ELEM_DESTLATREF, s);
    }

    public GPSDestLatitude getGPSDestLatitude () {
        return (GPSDestLatitude) getField (ELEM_DESTLAT);
    }
    
    public void setGPSDestLatitude (GPSDestLatitude lat) throws XmlContentException {
        setField (ELEM_DESTLAT, lat);
    }

    public StringElement getGpsDestLongitudeRef () {
        return (StringElement) getField (ELEM_DESTLONGREF);
    }
    
    public void setGpsDestLongitudeRef (String s) throws XmlContentException {
        setField (ELEM_DESTLONGREF, s);
    }

    public GPSDestLongitude getGPSDestLongitude () {
        return (GPSDestLongitude) getField (ELEM_DESTLONG);
    }
    
    public void setGPSDestLongitude (GPSDestLongitude lon) throws XmlContentException {
        setField (ELEM_DESTLONG, lon);
    }

    public StringElement getGpsDestBearingRef () {
        return (StringElement) getField (ELEM_DESTBEARREF);
    }
    
    public void setGpsDestBearingRef (String s) throws XmlContentException {
        setField (ELEM_DESTBEARREF, s);
    }

    public RationalElement getGpsDestBearing () {
        return (RationalElement) getField (ELEM_DESTBEAR);
    }
    
    public void setGpsDestBearing (Rational t) throws XmlContentException {
        setField (ELEM_DESTBEAR, t);
    }

    public StringElement getGpsDestDistanceRef () {
        return (StringElement) getField (ELEM_DESTDISTREF);
    }
    
    public void setGpsDestDistanceRef (String s) throws XmlContentException {
        setField (ELEM_DESTDISTREF, s);
    }

    public RationalElement getGpsDestDistance () {
        return (RationalElement) getField (ELEM_DESTDIST);
    }
    
    public void setGpsDestDistance (Rational t) throws XmlContentException {
        setField (ELEM_DESTDIST, t);
    }

    public StringElement getGpsProcessingMethod () {
        return (StringElement) getField (ELEM_PROCMETHOD);
    }
    
    public void setGpsProcessingMethod (String s) throws XmlContentException {
        setField (ELEM_PROCMETHOD, s);
    }

    public StringElement getGpsAreaInformation () {
        return (StringElement) getField (ELEM_AREAINFO);
    }
    
    public void setGpsAreaInformation (String s) throws XmlContentException {
        setField (ELEM_AREAINFO, s);
    }

    public StringElement getGpsDateStamp () {
        return (StringElement) getField (ELEM_DATESTAMP);
    }
    
    public void setGpsDateStamp (String s) throws XmlContentException {
        setField (ELEM_DATESTAMP, s);
    }

    public StringElement getGpsDifferential () {
        return (StringElement) getField (ELEM_DIFF);
    }
    
    public void setGpsDifferential (String s) throws XmlContentException {
        setField (ELEM_DIFF, s);
    }


    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_VERS, mainElem);
        outputGenericElement (ns, ELEM_LATREF, mainElem);
        outputGenericElement (ns, ELEM_LAT, mainElem);
        outputGenericElement (ns, ELEM_LONGREF, mainElem);
        outputGenericElement (ns, ELEM_LONG, mainElem);
        outputGenericElement (ns, ELEM_ALTREF, mainElem);
        outputGenericElement (ns, ELEM_ALT, mainElem);
        outputGenericElement (ns, ELEM_TIMESTAMP, mainElem);
        outputGenericElement (ns, ELEM_SATELLITES, mainElem);
        outputGenericElement (ns, ELEM_STATUS, mainElem);
        outputGenericElement (ns, ELEM_MEASUREMODE, mainElem);
        outputGenericElement (ns, ELEM_DOP, mainElem);
        outputGenericElement (ns, ELEM_SPEEDREF, mainElem);
        outputGenericElement (ns, ELEM_SPEED, mainElem);
        outputGenericElement (ns, ELEM_TRACKREF, mainElem);
        outputGenericElement (ns, ELEM_TRACK, mainElem);
        outputGenericElement (ns, ELEM_IMGDIRREF, mainElem);
        outputGenericElement (ns, ELEM_IMGDIR, mainElem);
        outputGenericElement (ns, ELEM_MAPDATUM, mainElem);
        outputGenericElement (ns, ELEM_DESTLATREF, mainElem);
        outputGenericElement (ns, ELEM_DESTLAT, mainElem);
        outputGenericElement (ns, ELEM_DESTLONGREF, mainElem);
        outputGenericElement (ns, ELEM_DESTLONG, mainElem);
        outputGenericElement (ns, ELEM_DESTBEARREF, mainElem);
        outputGenericElement (ns, ELEM_DESTBEAR, mainElem);
        outputGenericElement (ns, ELEM_DESTDISTREF, mainElem);
        outputGenericElement (ns, ELEM_DESTDIST, mainElem);
        outputGenericElement (ns, ELEM_PROCMETHOD, mainElem);
        outputGenericElement (ns, ELEM_AREAINFO, mainElem);
        outputGenericElement (ns, ELEM_DATESTAMP, mainElem);
        outputGenericElement (ns, ELEM_DIFF, mainElem);   
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    protected void dispatch (String localName, XMLStreamReader reader) 
                  throws XMLStreamException, XmlContentException {
        if (match (reader, this,
                localName, ELEM_VERS,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_LATREF,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_LAT,
                GPSLatitude.class))
            return;
        if (match (reader, this,
                localName, ELEM_LONGREF,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_LONG,
                GPSLongitude.class))
            return;
        if (match (reader, this,
                localName, ELEM_ALTREF,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_ALT,
                RationalElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_TIMESTAMP,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_SATELLITES,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_STATUS,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_MEASUREMODE,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_DOP,
                RationalElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_SPEEDREF,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_SPEED,
                RationalElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_TRACKREF,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_TRACK,
                RationalElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_IMGDIRREF,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_IMGDIR,
                RationalElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_MAPDATUM,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_DESTLATREF,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_DESTLAT,
                GPSDestLatitude.class))
            return;
        if (match (reader, this,
                localName, ELEM_DESTLONGREF,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_DESTLONG,
                GPSDestLongitude.class))
            return;
        if (match (reader, this,
                localName, ELEM_DESTBEARREF,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_DESTBEAR,
                RationalElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_DESTDISTREF,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_DESTDIST,
                RationalElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_PROCMETHOD,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_AREAINFO,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_DATESTAMP,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_DIFF,
                StringElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
