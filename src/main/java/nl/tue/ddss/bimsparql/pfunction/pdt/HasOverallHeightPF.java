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

import nl.tue.ddss.bimsparql.geometry.Geometry;
import nl.tue.ddss.bimsparql.geometry.algorithm.AABB;
import nl.tue.ddss.bimsparql.geometry.visitor.AABBVisitor;
import nl.tue.ddss.bimsparql.pfunction.FunctionBaseProductNumericalValue;

import com.hp.hpl.jena.graph.Node;

public class HasOverallHeightPF extends FunctionBaseProductNumericalValue{


	public HasOverallHeightPF(HashMap<Node, Geometry> hashmap) {
		super(hashmap);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected double computeValue(Geometry geometry) {
		double b=0;
		if(geometry==null){
			return Double.NaN;
		}
        AABBVisitor visitor=new AABBVisitor();
		geometry.accept(visitor);
		AABB aabb=visitor.getAABB();
		if(aabb!=null){
		b= aabb.getHeight();
		}
		return b;
	}






}
