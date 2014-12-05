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

/** Class for the physicalProperties element. This can occur in many
 *  different places but always has the same name.
 */
public class PhysProps extends AESObject {

    private final static String ELEMENTNAME = "physicalProperties";
    
    private final static String ELEM_TAPESTRUCT = "tapeStructure";
    private final static String ELEM_OPTSTRUCT = "opticalStructure";
    private final static String ELEM_ANALOGDISCSTRUCT = "analogDiscStructure";
    private final static String ELEM_CYLSTRUCT = "cylinderStructure";
    private final static String ELEM_DIMS = "dimensions";
    private final static String ELEM_SHELLDIMS = "shellDimensions";

    /**  Constructor for this element
     */
    public PhysProps (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        this.name = ELEMENTNAME;
        parse (reader);
    }
    
    /** Constructor with no arguments. */
    public PhysProps () {
        super();
        this.name = ELEMENTNAME;
    }

    public TapeStructure getTapeStructure () {
        return (TapeStructure) getField (ELEM_TAPESTRUCT);
    }
    
    /** Sets the TapeStructure field. Enforces the choice restriction. */
    public void setTapeStructure (TapeStructure t) throws XmlContentException {
        setField (ELEM_TAPESTRUCT, t);
        setField (ELEM_OPTSTRUCT, (OpticalStructure) null);
        setField (ELEM_ANALOGDISCSTRUCT, (AnalogDiscStructure) null);
        setField (ELEM_CYLSTRUCT, (CylinderStructure) null);
    }

    public OpticalStructure getOpticalStructure () {
        return (OpticalStructure) getField (ELEM_OPTSTRUCT);
    }
    
    /** Sets the OpticalStructure field. Enforces the choice restriction. */
    public void setOpticalStructure (OpticalStructure t) throws XmlContentException {
        setField (ELEM_TAPESTRUCT, (TapeStructure) null);
        setField (ELEM_OPTSTRUCT, t);
        setField (ELEM_ANALOGDISCSTRUCT, (AnalogDiscStructure) null);
        setField (ELEM_CYLSTRUCT, (CylinderStructure) null);
    }

    public AnalogDiscStructure getAnalogDiscStructure () {
        return (AnalogDiscStructure) getField (ELEM_ANALOGDISCSTRUCT);
    }

    /** Sets the AnalogDiscStructure field. Enforces the choice restriction. */
    public void setAnalogDiscStructure (AnalogDiscStructure t) throws XmlContentException {
        setField (ELEM_TAPESTRUCT, (TapeStructure) null);
        setField (ELEM_OPTSTRUCT, (OpticalStructure) null);
        setField (ELEM_ANALOGDISCSTRUCT, t);
        setField (ELEM_CYLSTRUCT, (CylinderStructure) null);
    }

    public CylinderStructure getCylinderStructure () {
        return (CylinderStructure) getField (ELEM_CYLSTRUCT);
    }

    /** Sets the CylinderStructure field. Enforces the choice restriction. */
    public void setCylinderStructure (AnalogDiscStructure t) throws XmlContentException {
        setField (ELEM_TAPESTRUCT, (TapeStructure) null);
        setField (ELEM_OPTSTRUCT, (OpticalStructure) null);
        setField (ELEM_ANALOGDISCSTRUCT, (AnalogDiscStructure) null);
        setField (ELEM_CYLSTRUCT, t);
    }
    
    public Dimensions getDimensions () {
        return (Dimensions) getField (ELEM_DIMS);
    }
    
    public void setDimensions (Dimensions d) throws XmlContentException {
        setField (ELEM_DIMS, d);
    }
    
    public ShellDimensions getShellDimensions () {
        return (ShellDimensions) getField (ELEM_SHELLDIMS);
    }
    
    public void setShellDimensions (ShellDimensions d) throws XmlContentException {
        setField (ELEM_SHELLDIMS, d);
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = outputElement (ns, name, parent); 
        outputGenericElement (ns, ELEM_TAPESTRUCT, mainElem);
        outputGenericElement (ns, ELEM_OPTSTRUCT, mainElem);
        outputGenericElement (ns, ELEM_ANALOGDISCSTRUCT, mainElem);
        outputGenericElement (ns, ELEM_CYLSTRUCT, mainElem);
        outputGenericElement (ns, ELEM_DIMS, mainElem);
        outputGenericElement (ns, ELEM_SHELLDIMS, mainElem);
    }

    @Override
    public boolean validate() {
        // Enforce the choice element
        if (!isChoiceCompliant (new String[] {ELEM_TAPESTRUCT,
                ELEM_OPTSTRUCT,
                ELEM_ANALOGDISCSTRUCT,
                ELEM_CYLSTRUCT}))
            return false;
        return true;
    }

    protected void dispatch(String localName, XMLStreamReader reader)
    throws XMLStreamException, XmlContentException {
        if (match(reader, this, localName, ELEM_TAPESTRUCT, TapeStructure.class))
            return;
        if (match(reader, this, localName, ELEM_OPTSTRUCT, OpticalStructure.class))
            return;
        if (match(reader, this, localName, ELEM_ANALOGDISCSTRUCT, AnalogDiscStructure.class))
            return;
        if (match(reader, this, localName, ELEM_CYLSTRUCT, CylinderStructure.class))
            return;
        if (match(reader, this, localName, ELEM_DIMS, Dimensions.class))
            return;
        if (match(reader, this, localName, ELEM_SHELLDIMS, ShellDimensions.class))
            return;
        throw new XmlContentException("Unknown element name " + localName);
    }

}
