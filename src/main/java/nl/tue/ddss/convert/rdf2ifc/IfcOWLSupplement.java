/*******************************************************************************
 * Copyright (C) 2017 Chi Zhang
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package nl.tue.ddss.convert.rdf2ifc;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

/**
 * vocabulary used in ifcOWL supplyment files.
 * @author Chi
 *
 */
public class IfcOWLSupplement {
	public static final String uri = "http://www.tue.nl/ddss/ifcOWL_supplementary#";
	
	 public static String getURI()
     { return uri; }

 protected static final Resource resource( String local )
     { return ResourceFactory.createResource( uri + local ); }

 protected static final Property property( String local )
     { return ResourceFactory.createProperty( uri, local ); }




 public static final Property isIfcEntity = property("isIfcEntity");
 public static final Property isTopLevelIfcEntity=property("isTopLevelIfcEntity");
 public static final Property index=property("index");
 public static final Property isListOrArray=property("isListOrArray");
 public static final Property isSet=property("isSet");
 public static final Property isOptional=property("isOptional");
 public static final Property hasDeriveAttribute=property("hasDeriveAttribute");
 

}
