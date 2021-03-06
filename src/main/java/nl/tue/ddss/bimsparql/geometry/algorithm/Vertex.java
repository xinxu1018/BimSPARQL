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
package nl.tue.ddss.bimsparql.geometry.algorithm;


import java.util.ArrayList;
import java.util.List;

import nl.tue.ddss.bimsparql.geometry.Point3d;

public class Vertex {
	
	public Point3d pnt;
	public int index;
	public List<Edge> edges;
	
	public Vertex(Point3d pt, int index) {
		this.pnt=pt;
		this.index=index;
		this.edges=new ArrayList<Edge>();
	}



}
