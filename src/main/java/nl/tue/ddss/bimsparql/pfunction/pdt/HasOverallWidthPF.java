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

import com.hp.hpl.jena.graph.Node;

import nl.tue.ddss.bimsparql.geometry.Geometry;
import nl.tue.ddss.bimsparql.geometry.algorithm.AABB;
import nl.tue.ddss.bimsparql.geometry.visitor.AABBVisitor;
import nl.tue.ddss.bimsparql.pfunction.FunctionBaseProductNumericalValue;

public class HasOverallWidthPF extends FunctionBaseProductNumericalValue{


	public HasOverallWidthPF(HashMap<Node, Geometry> hashmap) {
		super(hashmap);		// TODO Auto-generated constructor stub
	}

	@Override
	protected double computeValue(Geometry geometry) {
		// TODO Auto-generated method stub
		double b=0;
        AABBVisitor visitor=new AABBVisitor();
		geometry.accept(visitor);
		AABB aabb=visitor.getAABB();
		if(aabb!=null){
		b= aabb.getYLength();
		}
		return b;
	}

}
