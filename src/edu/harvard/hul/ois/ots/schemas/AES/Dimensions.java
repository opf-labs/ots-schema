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

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamException;

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

/**
 *  The base dimensions is a multivalent element, which can be a
 *  baseDimensionsType, wireDimensionsType, 
 *  analogDiscDimensionsType, opticalDiscDimensionsType,
 *  cylinderDimensionsType, shellDimensionsType, or the catchall
 *  dimensionsType (which isn't recursive, this is a different
 *  type from the base). 
 *  
 *  The actual type is determined by the type attribute. So we
 *  read that first, and then read the element into the 
 *  appropriate contained element. All such types are implemented
 *  as subclasses of DimensionsSubtype.
 *  
 *  @see DimensionsSubtype
 */
public class Dimensions extends AESObject {

    enum dimTypeEnum {
        TAPE,
        WIRE,
        ANALOG_DISC,
        OPTICAL_DISC,
        SHELL,
        CYLINDER,
        GENERIC, 
        NONE
    };

    /* Which type of dimension is this? */
    private dimTypeEnum dimType;

    /* The object which instantiates the appropriate subtype */
    DimensionsSubtype content;

    /** Constructor from XML reader */
    public Dimensions (XMLStreamReader reader) 
                throws XmlContentException, XMLStreamException {
        parse (reader);
        if (content != null) {
            content.getAttributesFrom (this);
        }
    }
    
    
    /** Constructor with type specified */
    public Dimensions (String type) throws XmlContentException {
        dimType = typeToEnum(type);
        if (dimType != null) {
            switch (dimType) {
            case TAPE:
                content = new TapeDimensions ();
                break;
            case WIRE:
                content = new WireDimensions();
                break;
            case ANALOG_DISC:
                content = new AnalogDiscDimensions();
                break;
            case OPTICAL_DISC:
                content = new OpticalDiscDimensions();
                break;
            case SHELL:
                content = new ShellDimensions();
                break;
            case CYLINDER:
                content = new CylinderDimensions();
                break;
            case GENERIC:
                content = new GenericDimensions();
                break;
            case NONE:
            default:
                throw new XmlContentException("Unknown dimensions type " + type);   
            }
        }
    }

    /** Returns the DimensionsSubtype object for this Dimensions.
     *  It's necessary to to field get and set operations on the value
     *  this function returns rather than on the Dimensions object
     *  itself. 
     */
    public DimensionsSubtype getContent () {
        return content;
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        if (content != null) {
            content.output (ns,parent);
        }
    }

    /** The dispatch routine first checks the type
     *  attribute, then calls an appropriate subdispatcher based on
     *  the type.
     */
    protected void dispatch(String localName, XMLStreamReader reader)
               throws XMLStreamException, XmlContentException {
        String type = getAttribute ("type");
        if (GenericDimensions.TYPENAME.equals (type)) {
            dispatchGenericDimensions(localName, reader);
        }
        else if (TapeDimensions.TYPENAME.equals (type)) {
            dispatchTapeDimensions(localName, reader);
        }
        else if (AnalogDiscDimensions.TYPENAME.equals (type)) {
            dispatchAnalogDiscDimensions(localName, reader);
        }
        else if (OpticalDiscDimensions.TYPENAME.equals (type)) {
            dispatchOpticalDiscDimensions(localName, reader);
        }
        else if (CylinderDimensions.TYPENAME.equals (type)) {
            dispatchCylinderDimensions(localName, reader);
        }
        else if (ShellDimensions.TYPENAME.equals (type)) {
            dispatchShellDimensions(localName, reader);
        }
        else if (WireDimensions.TYPENAME.equals (type)) {
            dispatchWireDimensions(localName, reader);
        }
        else {
            throw new XmlContentException("Unknown dimensions type " + type);   
        }
    }

    private void dispatchGenericDimensions(String localName, XMLStreamReader reader)
                  throws XMLStreamException, XmlContentException {
        dimType = dimTypeEnum.GENERIC;
        if (content == null) {
            content = new GenericDimensions ();
        }
        content.dispatch (localName, reader);
    }

    private void dispatchTapeDimensions(String localName, XMLStreamReader reader)
                  throws XMLStreamException, XmlContentException {
        dimType = dimTypeEnum.TAPE;
        if (content == null) {
            content = new TapeDimensions ();
        }
        content.dispatch (localName, reader);
    }

    private void dispatchAnalogDiscDimensions(String localName, XMLStreamReader reader)
                  throws XMLStreamException, XmlContentException {
        dimType = dimTypeEnum.ANALOG_DISC;
        if (content == null) {
            content = new AnalogDiscDimensions ();
        }
        content.dispatch (localName, reader);
    }

    private void dispatchOpticalDiscDimensions(String localName, XMLStreamReader reader)
                  throws XMLStreamException, XmlContentException {
        dimType = dimTypeEnum.OPTICAL_DISC;
        if (content == null) {
            content = new OpticalDiscDimensions ();
        }
        content.dispatch (localName, reader);
    }

    private void dispatchCylinderDimensions(String localName, XMLStreamReader reader)
                  throws XMLStreamException, XmlContentException {
        dimType = dimTypeEnum.CYLINDER;
        if (content == null) {
            content = new CylinderDimensions ();
        }
        content.dispatch (localName, reader);
    }

    private void dispatchShellDimensions(String localName, XMLStreamReader reader)
                  throws XMLStreamException, XmlContentException {
        dimType = dimTypeEnum.SHELL;
        if (content == null) {
            content = new ShellDimensions ();
        }
        content.dispatch (localName, reader);
    }

    private void dispatchWireDimensions(String localName, XMLStreamReader reader)
                  throws XMLStreamException, XmlContentException {
        dimType = dimTypeEnum.WIRE;
        if (content == null) {
            content = new WireDimensions ();
        }
        content.dispatch (localName, reader);
    }

    /** Given a type string, return the appropriate dimTypeEnum, or NONE
     *  if there's no match. */
    private dimTypeEnum typeToEnum(String type) {
        if (baseNamesEqual(TapeDimensions.TYPENAME, type)) {
            return dimTypeEnum.TAPE;
        }
        else if (baseNamesEqual(WireDimensions.TYPENAME, type)) {
            return dimTypeEnum.WIRE;
        }
        else if (baseNamesEqual(AnalogDiscDimensions.TYPENAME, type)) {
            return dimTypeEnum.ANALOG_DISC;
        }
        else if (baseNamesEqual(OpticalDiscDimensions.TYPENAME, type)) {
            return dimTypeEnum.OPTICAL_DISC;
        }
        else if (baseNamesEqual(ShellDimensions.TYPENAME, type)) {
            return dimTypeEnum.SHELL;
        }
        else if (baseNamesEqual(CylinderDimensions.TYPENAME, type)) {
            return dimTypeEnum.CYLINDER;
        }
        else if (baseNamesEqual(GenericDimensions.TYPENAME, type)) {
            return dimTypeEnum.GENERIC;
        }
        else
            return dimTypeEnum.NONE;
    }
    

    /** Set the necessary type attribute, based on the regionType */
    @SuppressWarnings("unused")
	private void setTypeAttribute () {
        if (content.getAttribute ("xsi:type") != null) {
            return;
        }
        String typeStr = null;
        switch (dimType) {
        case TAPE:
            typeStr = TapeDimensions.TYPENAME;
            break;
        case WIRE:
            typeStr = WireDimensions.TYPENAME;
            break;
        case ANALOG_DISC:
            typeStr = AnalogDiscDimensions.TYPENAME;
            break;
        case OPTICAL_DISC:
            typeStr = OpticalDiscDimensions.TYPENAME;
            break;
        case SHELL:
            typeStr = ShellDimensions.TYPENAME;
            break;
        case CYLINDER:
            typeStr = CylinderDimensions.TYPENAME;
            break;
        case GENERIC:
            typeStr = GenericDimensions.TYPENAME;
            break;
        }
        content.setAttribute ("xsi:type", typeStr);
    }

}
