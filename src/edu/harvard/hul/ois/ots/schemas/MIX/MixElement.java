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

import java.util.ArrayList;
import java.util.List;

import edu.harvard.hul.ois.ots.schemas.XmlContent.GenericElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;

/** This class is the superclass of all MIX GenericElement objects. */
public abstract class MixElement extends GenericElement {

    public static final String XMLNS = "http://www.loc.gov/mix/v20";
    public static final String SCHEMA_PREFIX = "mix";
    //public static final String SCHEMA_NAMESPACE_URI = "http://www.w3.org/2001/XMLSchema-instance";
    //public static final String MIX_SCHEMALOCATION = XMLNS + 
    //            " http://www.loc.gov/standards/mix/mix20/mix20.xsd";    
    public static final String SCHEMA_LOCATION = "http://www.loc.gov/standards/mix/mix20/mix20.xsd";    

    /* A static NamespaceSchemaContext that all methods can return using
     * getMixNamespaceSchemaContext */
    protected static final NamespaceSchemaContext mixNSC = 
        new NamespaceSchemaContext (XMLNS, SCHEMA_PREFIX, SCHEMA_LOCATION);

    /** Returns a list of all namespace schema contexts in or under
     *  this element. Returns null if not overridden. */
    @Override
    public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts() {
        ArrayList<NamespaceSchemaContext> lst = new ArrayList<NamespaceSchemaContext>(1);
        lst.add (mixNSC);
        return lst;
    }

    /** Returns the namespace schema context for this element. 
     *  Must be overridden unless the namespace context is null. */
    @Override
    public NamespaceSchemaContext getNamespaceSchemaContext() {
        return mixNSC;
    }
    
    /** Set the "use" attribute, which is allowed for just many
     *  MIX elements and should be harmless for the others. */
    public void setUse (String u) {
        setAttribute ("use", u);
    }
    
    /** Returns the "use" attribute, which is allowed for just many
     *  MIX elements and should be harmless for the others. */
    public String getUse () {
        return getAttribute ("use");
    }


}
