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
package nl.tue.ddss.bimsparql.pfunction.pdt;

import java.util.HashMap;
import java.util.List;

import com.hp.hpl.jena.graph.Node;

import nl.tue.ddss.bimsparql.geometry.Geometry;
import nl.tue.ddss.bimsparql.geometry.GeometryException;
import nl.tue.ddss.bimsparql.geometry.TriangulatedSurface;
import nl.tue.ddss.bimsparql.geometry.algorithm.Area;
import nl.tue.ddss.bimsparql.geometry.algorithm.Stitching;
import nl.tue.ddss.bimsparql.pfunction.FunctionBaseProductNumericalValue;

public class HasWallAreaPF extends FunctionBaseProductNumericalValue{


	public HasWallAreaPF(HashMap<Node, Geometry> hashmap) {
		super(hashmap);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected double computeValue(Geometry geometry) {
		List<TriangulatedSurface> surfaces=null;
		try {
			surfaces = new Stitching().stitches(geometry,0.1);
		} catch (GeometryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Double.NaN;
		}
		TriangulatedSurface largest=null;
		for (TriangulatedSurface surface:surfaces){
			if(largest!=null){
				if(Area.area(surface)>Area.area(largest)){
					largest=surface;
				}
			}else{
				largest=surface;
			}
		}
		return Area.area(largest);
	}



}
