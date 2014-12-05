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


/** This class defines and applies a restriction on a GenericElement.
 *  Restrictions are of two kinds: Arrays of permitted values and 
 *  special-purpose restrictions (e.g., only non-negative values
 *  permitted).  */
public class Restriction {

    /** Magic constant for non-negative elements */
    public final static String NONNEGATIVE = "non-negative";
    
    /** The name of the element being restricted. */
    private String elemName;
    
    /** The object defining the permitted values. */
    private Object permittedValues;
    
    /** Constructor. 
     *  @param name     The name of the element
     *  @param values   An object defining the permitted values. This can
     *                  be an array of values of the appropriate type,
     *                  or a magic constant indicating a specific restriction. 
     */
    public Restriction (String name, Object values) {
        elemName = name;
        permittedValues = values;
    }
    
    protected String getName () {
        return elemName;
    }
    
    protected Object getPermittedValues () {
        return permittedValues;
    }
    
    protected void checkRestriction (GenericElement elem) throws XmlContentException {
        if (elem instanceof StringElement) {
            checkStringRestriction ((StringElement) elem, (String[]) permittedValues);
        }
        if (elem instanceof IntegerElement) {
            // Check for the magic non-negative restriction
            if (NONNEGATIVE.equals (permittedValues)) {
                checkNonNegativeIntegerRestriction ((IntegerElement) elem);
            }
            else
                checkIntegerRestriction ((IntegerElement) elem, (int[]) permittedValues);
        }
        if (elem instanceof RealElement) {
            // Check for the magic non-negative restriction
            if (NONNEGATIVE.equals (permittedValues)) {
                checkNonNegativeRealRestriction ((RealElement) elem);
            }
        }
    }
    
    /** Check if a string from a restricted set conforms. For this purpose, we
     *  strip off leading and trailing white space. */
    private void checkStringRestriction (StringElement elem, String[] values) 
                    throws XmlContentException {
        String estr = ((String) elem.toValue()).trim();
        for (String str : values) {
            if (str.equals (estr))
                return;
        }
        throw new XmlContentException ("Value " + estr + " not allowed");
    }

    /** Check if an integer from a restricted set conforms. */
    private void checkIntegerRestriction (IntegerElement elem, int[] values) 
                    throws XmlContentException {
        int en = (Integer) elem.toValue();
        for (int n : values) {
            if (n == en)
                return;
        }
        throw new XmlContentException 
               ("Value " + en + " not allowed in element " + elem.getName());
    }
    
    /** This is called if the restriction is the magic non-negative object 
     * @throws XmlContentException */
    private void checkNonNegativeIntegerRestriction (IntegerElement elem) 
                   throws XmlContentException {
        int en = (Integer) elem.toValue();
        if (en < 0) {
            throw new XmlContentException 
                  ("value" + en + " in element " + elem.getName () +
                          " must be non-negative");
        }
    }

    /** This is called if the restriction is the magic non-negative object 
     * @throws XmlContentException */
    private void checkNonNegativeRealRestriction (RealElement elem) 
                   throws XmlContentException {
        double en = (Double) elem.toValue();
        if (en < 0.0) {
            throw new XmlContentException 
                  ("value" + en + " in element " + elem.getName () +
                          " must be non-negative");
        }
    }

}
