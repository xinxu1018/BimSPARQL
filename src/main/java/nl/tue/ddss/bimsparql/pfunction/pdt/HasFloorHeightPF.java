package nl.tue.ddss.bimsparql.pfunction.pdt;

import java.util.HashMap;

import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.sparql.core.Var;
import com.hp.hpl.jena.sparql.engine.ExecutionContext;
import com.hp.hpl.jena.sparql.engine.QueryIterator;
import com.hp.hpl.jena.sparql.engine.binding.Binding;

import nl.tue.ddss.bimsparql.geometry.Geometry;
import nl.tue.ddss.bimsparql.pfunction.FunctionBaseProduct;

public class HasFloorHeightPF extends FunctionBaseProduct{
    
	public HasFloorHeightPF(HashMap<Node, Geometry> hashmap) {
		super(hashmap);
		// TODO Auto-generated constructor stub
	}


	protected QueryIterator verifyValue(Binding binding, Graph graph,
			Node product, Geometry geometry,Node object, ExecutionContext execCxt) {
		// TODO Auto-generated method stub
		return null;
	}


	protected QueryIterator getValue(Binding binding, Graph graph,
			Node product, Geometry geometry,Var alloc, ExecutionContext execCxt) {
		// TODO Auto-generated method stub
		return null;
	}

}
