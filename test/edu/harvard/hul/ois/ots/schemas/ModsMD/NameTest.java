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

import java.util.List;

import edu.harvard.hul.ois.ots.schemas.ModsMD.Name;
import edu.harvard.hul.ois.ots.schemas.ModsMD.Role;
import edu.harvard.hul.ois.ots.schemas.XmlContent.GenericElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;


public class NameTest extends junit.framework.TestCase {

    public void testComposition () throws Exception {
        Name nm = new Name ();
        nm.setLang ("eng");
        nm.setXMLLang("deu");
        StringElement first = nm.addNamePart ("Tom");
        first.setAttribute ("type", "given");
        StringElement last = nm.addNamePart ("Smith");
        last.setAttribute ("type", "family");
        nm.addDisplayForm ("Tom Smith");
        nm.addAffiliation ("OVFF");
        Role rol = new Role ();
        rol.addRoleTerm ("composer");
        nm.addRole (rol);
        nm.addDescription ("indescribable");
        
        assertEquals ("eng", nm.getLang ());
        assertEquals ("deu", nm.getAttribute ("xml:lang"));
        assertEquals ("eng", nm.getAttribute ("lang"));
        List<GenericElement> elems = nm.getAllElements();
        assertTrue (elems.size () == 6);
        StringElement elem = (StringElement) elems.get(0);
        assertEquals ("namePart", elem.getName ());
        assertEquals ("given", elem.getAttribute ("type"));
        assertEquals ("Tom", elem.toString ());
        
        elem = (StringElement) elems.get(1);
        assertEquals ("namePart", elem.getName ());
        assertEquals ("family", elem.getAttribute ("type"));
        assertEquals ("Smith", elem.toString ());
        
        elem = (StringElement) elems.get(2);
        assertEquals ("displayForm", elem.getName ());
        assertEquals ("Tom Smith", elem.toString ());
        
        elem = (StringElement) elems.get(3);
        assertEquals ("affiliation", elem.getName ());
        assertEquals ("OVFF", elem.toString ());

        rol = (Role) elems.get(4);
        assertEquals ("role", rol.getName ());
        elem = rol.getRoleTerms ().get(0);
        assertEquals ("composer", elem.toString ());

        elem = (StringElement) elems.get(5);
        assertEquals ("description", elem.getName ());
        assertEquals ("indescribable", elem.toString ());
        
        List<StringElement> lst = nm.getNameParts ();
        assertTrue (lst.size() == 2);
        elem = lst.get(1);
        assertEquals ("namePart", elem.getName ());
        lst = nm.getDisplayForms ();
        assertTrue (lst.size() == 1);
        elem = lst.get(0);
        
        lst = nm.getAffiliations ();
        assertTrue (lst.size () == 1);
        elem = lst.get(0);
        assertEquals ("affiliation", elem.getName ());
        List<Role> roles = nm.getRoles ();
        assertTrue (roles.size () == 1);
        Role role = roles.get(0);
        assertEquals ("role", role.getName ());
        lst = nm.getDescriptions ();
        assertTrue (lst.size () == 1);
        elem = lst.get(0);
        assertEquals ("description", elem.getName ());
        assertEquals ("indescribable", elem.toString ());
    }
}
