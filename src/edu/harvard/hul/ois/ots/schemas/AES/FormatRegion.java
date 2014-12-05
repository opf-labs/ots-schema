/**********************************************************************
 * Copyright (c) 2010 by the President and Fellows of Harvard College
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA.
 *
 * Contact information
 *
 * Office for Information Systems
 * Harvard University Library
 * Harvard University
 * Cambridge, MA  02138
 * (617)495-3724
 * hulois@hulmail.harvard.edu
 **********************************************************************/

package edu.harvard.hul.ois.ots.schemas.AES;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

/**
 *  The base formatRegion is a multivalent element, which can be an 
 *  analogFormatRegion, digitalTapeFormatRegion, 
 *  analogDiscFormatRegion, opticalDiscFormatRegion,
 *  wireFormatRegion, cylinderFormatRegion, or the catchall
 *  formatRegion (which isn't recursive, this is a different
 *  type from the base). 
 *  
 *  The actual type is determined by the type attribute. So we
 *  read that first, and then read the element into the 
 *  appropriate contained element. All such types are implemented
 *  as subclasses of FormatRegionSubtype.
 *  
 *  If a FormatRegion is created programatically from scratch (i.e.,
 *  not by parsing XML), then it's necessary to use the constructor
 *  with the type string, and to call getContent() to access the
 *  fields for the specific subtype.
 *  
 *  @see FormatRegionSubtype
 */
public class FormatRegion extends AESObject {

    public enum regionTypeEnum {
        ANALOG_TAPE,
        DIGITAL_TAPE,
        ANALOG_DISC,
        OPTICAL_DISC,
        WIRE,
        CYLINDER,
        GENERIC,
        NONE
    };
    
    /* Which type of region is this? */
    private regionTypeEnum regionType;
    
    /* The object which instantiates the appropriate subtype */
    private FormatRegionSubtype content;
    
    /** Constructor from XML reader */
    public FormatRegion (XMLStreamReader reader) 
                throws XmlContentException, XMLStreamException {
        parse (reader);
        if (content != null) {
            content.getAttributesFrom (this);
        }
    }

    /** Constructor with type specified */
    public FormatRegion (regionTypeEnum type) {
        regionType = type;
        if (regionType != null) {
            switch (regionType) {
            case ANALOG_TAPE:
                content = new AnalogTapeFormatRegion ();
                break;
            case DIGITAL_TAPE:
                content = new DigitalTapeFormatRegion();
                break;
            case ANALOG_DISC:
                content = new AnalogDiscFormatRegion();
                break;
            case OPTICAL_DISC:
                content = new OpticalDiscFormatRegion();
                break;
            case WIRE:
                content = new WireFormatRegion();
                break;
            case CYLINDER:
                content = new CylinderFormatRegion();
                break;
            case GENERIC:
                content = new GenericFormatRegion();
                break;
            }
        }
    }
    
    
    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        setTypeAttribute ();  
        if (content != null) {
            content.output (ns, parent);
        }
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    /** Returns the FormatRegionSubtype object for this FormatRegion.
     *  It's necessary to to field get and set operations on the value
     *  this function returns rather than on the FormatRegion object
     *  itself. 
     */
    public FormatRegionSubtype getContent () {
        return content;
    }
    
    /** The dispatch routine first checks the type
     *  attribute, then calls an appropriate subdispatcher based on
     *  the type.
     */
    protected void dispatch(String localName, XMLStreamReader reader)
               throws XMLStreamException, XmlContentException {
        String type = getAttribute ("type");
        if (type == null) {
            throw new XmlContentException ("type attribute of formatRegion is missing");
        }
        switch (typeToEnum (type)) {
        case ANALOG_TAPE:
            dispatchAnalogTapeFormat(localName, reader);
            break;
        case DIGITAL_TAPE:
            dispatchDigitalTapeFormat(localName, reader);
            break;
        case ANALOG_DISC:
            dispatchAnalogDiscFormat(localName, reader);
            break;
        case OPTICAL_DISC:
            dispatchOpticalDiscFormat(localName, reader);
            break;
        case WIRE:
            dispatchWireFormat(localName, reader);
            break;
        case CYLINDER:
            dispatchCylinderFormat(localName, reader);
            break;
        case GENERIC:
            dispatchGenericFormat(localName, reader);
            break;
        case NONE:
        default:
            throw new XmlContentException("Unknown formatRegion type " + type);   
        }
    }
    
    private void dispatchAnalogTapeFormat(String localName, XMLStreamReader reader)
                 throws XMLStreamException, XmlContentException {
        regionType = regionTypeEnum.ANALOG_TAPE;
        if (content == null) {
            content = new AnalogTapeFormatRegion ();
        }
        content.dispatch (localName, reader);
    }
    
    private void dispatchDigitalTapeFormat(String localName, XMLStreamReader reader)
                  throws XMLStreamException, XmlContentException {
        regionType = regionTypeEnum.DIGITAL_TAPE;
        if (content == null) {
            content = new DigitalTapeFormatRegion();
        }
        content.dispatch (localName, reader);
    }
    
    private void dispatchAnalogDiscFormat(String localName, XMLStreamReader reader)
                  throws XMLStreamException, XmlContentException {
        regionType = regionTypeEnum.ANALOG_DISC;
        if (content == null) {
            content = new AnalogDiscFormatRegion ();
        }
        content.dispatch (localName, reader);
    }
    
    private void dispatchOpticalDiscFormat(String localName, XMLStreamReader reader)
                  throws XMLStreamException, XmlContentException {
        regionType = regionTypeEnum.OPTICAL_DISC;
        if (content == null) {
            content = new OpticalDiscFormatRegion ();
        }
        content.dispatch (localName, reader);
    }
    
    private void dispatchWireFormat(String localName, XMLStreamReader reader)
                  throws XMLStreamException, XmlContentException {
        regionType = regionTypeEnum.WIRE;
        if (content == null) {
            content = new WireFormatRegion ();
        }
        content.dispatch (localName, reader);
    }
    
    private void dispatchCylinderFormat(String localName, XMLStreamReader reader)
    throws XMLStreamException, XmlContentException {
        regionType = regionTypeEnum.CYLINDER;
        if (content == null) {
            content = new CylinderFormatRegion ();
        }
        content.dispatch (localName, reader);
    }
    
    private void dispatchGenericFormat(String localName, XMLStreamReader reader)
    throws XMLStreamException, XmlContentException {
        regionType = regionTypeEnum.GENERIC;
        if (content == null) {
            content = new GenericFormatRegion ();
        }
        content.dispatch (localName, reader);
    }
    
    /** Given a type string, return the appropriate regionTypeEnum, or NONE
     *  if there's no match. (It's easier to deal with a return value
     *  that's always non-null in a switch statement.) */
    private regionTypeEnum typeToEnum(String type) {
        if (baseNamesEqual(AnalogTapeFormatRegion.TYPENAME, type)) {
            return regionTypeEnum.ANALOG_TAPE;
        }
        else if (baseNamesEqual(DigitalTapeFormatRegion.TYPENAME, type)) {
            return regionTypeEnum.DIGITAL_TAPE;
        }
        else if (baseNamesEqual(AnalogDiscFormatRegion.TYPENAME, type)) {
            return regionTypeEnum.ANALOG_DISC;
        }
        else if (baseNamesEqual(OpticalDiscFormatRegion.TYPENAME, type)) {
            return regionTypeEnum.OPTICAL_DISC;
        }
        else if (baseNamesEqual(WireFormatRegion.TYPENAME, type)) {
            return regionTypeEnum.WIRE;
        }
        else if (baseNamesEqual(CylinderFormatRegion.TYPENAME, type)) {
            return regionTypeEnum.CYLINDER;
        }
        else if (baseNamesEqual(GenericFormatRegion.TYPENAME, type)) {
            return regionTypeEnum.GENERIC;
        }
        else
            return regionTypeEnum.NONE;
    }
    
    /** Set the necessary type attribute, based on the regionType */
    private void setTypeAttribute () {
        if (content.getAttribute ("xsi:type") != null) {
            return;
        }
        String typeStr = null;
        switch (regionType) {
        case ANALOG_TAPE:
            typeStr = AnalogTapeFormatRegion.TYPENAME;
            break;
        case DIGITAL_TAPE:
            typeStr = DigitalTapeFormatRegion.TYPENAME;
            break;
        case ANALOG_DISC:
            typeStr = AnalogDiscFormatRegion.TYPENAME;
            break;
        case OPTICAL_DISC:
            typeStr = OpticalDiscFormatRegion.TYPENAME;
            break;
        case WIRE:
            typeStr = WireFormatRegion.TYPENAME;
            break;
        case CYLINDER:
            typeStr = CylinderFormatRegion.TYPENAME;
            break;
        case GENERIC:
            typeStr = GenericFormatRegion.TYPENAME;
            break;
        }
        content.setAttribute ("xsi:type", typeStr);
    }
}
