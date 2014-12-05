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

/** This class encapsulates a rational number, stored as a numerator and a denominator. */
public class Rational {
    private int numerator;
    private int denominator;
    
    public Rational (int num, int denom) {
        numerator = num;
        denominator = denom;
    }
    
    public Rational (int n) {
        numerator = n;
        denominator = 1;
    }
    
    /** Constructor from a double. This always gives a denominator of 10000. If this isn't
     *  satisfactory, the numerator-denominator constructor should be used. */
    public Rational (double d) {
        numerator = (int) (d * 10000.0);
        denominator = 10000;
    }
    
    /** Returns the numerator portion. */
    public int getNumerator () {
        return numerator;
    }
    
    /** Returns the denominator portion. */
    public int getDenominator () {
        return denominator;
    }
    
    /** Returns the value converted to a double. */
    public double getDouble () {
        return (double) numerator / (double) denominator;
    }
    
    /** Equality check */
    @Override
    public boolean equals (Object r) {
        if (r == null || !(r instanceof Rational))
            return false;
        Rational rr = (Rational) r;
        return numerator == rr.numerator && denominator == rr.denominator;
    }
}
