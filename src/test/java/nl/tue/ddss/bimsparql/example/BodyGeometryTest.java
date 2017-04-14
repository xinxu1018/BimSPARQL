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
package nl.tue.ddss.bimsparql.example;


public class BodyGeometryTest extends QueryFunctionTest{
	
	
	
	public static void main(String[] args){		
         String query="SELECT ?wall ?g\n" + "WHERE{\n" +"?wall a ifcowl:IfcProduct .\n"+"?wall pdt:hasBodyGeometry ?g .\n"+ "}";
         executeQuery(query);
	}

}
