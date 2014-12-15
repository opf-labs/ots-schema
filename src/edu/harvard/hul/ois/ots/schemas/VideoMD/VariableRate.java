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
package edu.harvard.hul.ois.ots.schemas.VideoMD;

import edu.harvard.hul.ois.ots.schemas.XmlContent.LongElement;

public class VariableRate extends LongElement {

    private static final String ATTRIBUTE_MAXIMUM_NAME = "maximum";
    private static final String ATTRIBUTE_MINIMUM_NAME = "minimum";
    private static final String ATTRIBUTE_NOMINAL_NAME = "nominal";
    private static final String ATTRIBUTE_MODE_NAME = "mode";
    private static final String ATTRIBUTE_UNIT_NAME = "unit";


    public VariableRate(Long content, String name) {
        super(content, name);
    }


    public boolean validate() {
        return true;
    }


    public String getMaximum() {
        return getAttribute(ATTRIBUTE_MAXIMUM_NAME);
    }


    public void setMaximum(String maximum) {
        setAttribute(ATTRIBUTE_MAXIMUM_NAME, maximum);
    }


    public String getMinimum() {
        return getAttribute(ATTRIBUTE_MINIMUM_NAME);
    }


    public void setMinimum(String minimum) {
        setAttribute(ATTRIBUTE_MINIMUM_NAME, minimum);
    }


    public String getNominal() {
        return getAttribute(ATTRIBUTE_NOMINAL_NAME);
    }


    public void setNominal(String nominal) {
        setAttribute(ATTRIBUTE_NOMINAL_NAME, nominal);
    }


    public String getMode() {
        return getAttribute(ATTRIBUTE_MODE_NAME);
    }


    public void setMode(String mode) {
        setAttribute(ATTRIBUTE_MODE_NAME, mode);
    }


    public String getUnit() {
        return getAttribute(ATTRIBUTE_UNIT_NAME);
    }


    public void setUnit(String unit) {
        setAttribute(ATTRIBUTE_UNIT_NAME, unit);
    }

}
