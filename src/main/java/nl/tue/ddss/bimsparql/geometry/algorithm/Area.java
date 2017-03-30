package nl.tue.ddss.bimsparql.geometry.algorithm;

import javax.vecmath.Vector3d;


import nl.tue.ddss.bimsparql.geometry.Geometry;
import nl.tue.ddss.bimsparql.geometry.GeometryUtils;
import nl.tue.ddss.bimsparql.geometry.GeometryCollection;
import nl.tue.ddss.bimsparql.geometry.GeometryException;
import nl.tue.ddss.bimsparql.geometry.LineString;
import nl.tue.ddss.bimsparql.geometry.Plane;
import nl.tue.ddss.bimsparql.geometry.Point2d;
import nl.tue.ddss.bimsparql.geometry.Point3d;
import nl.tue.ddss.bimsparql.geometry.Polygon;
import nl.tue.ddss.bimsparql.geometry.PolyhedralSurface;
import nl.tue.ddss.bimsparql.geometry.Triangle;
import nl.tue.ddss.bimsparql.geometry.TriangulatedSurface;

public class Area {
	
	public static double area( Geometry g) throws GeometryException{
	    switch ( g.geometryTypeId() ) {
	    case TYPE_POINT:
	    case TYPE_LINESTRING:
	        return 0 ;

	    case TYPE_POLYGON:
	        return area( (Polygon)g) ;

	    case TYPE_TRIANGLE:
	        return area( (Triangle)g ) ;

	    case TYPE_MULTIPOINT:
	    case TYPE_MULTILINESTRING:
	    case TYPE_MULTIPOLYGON:
	    case TYPE_GEOMETRYCOLLECTION:
	        return area( (GeometryCollection)g) ;

	    case TYPE_TRIANGULATEDSURFACE:
	        return area( (TriangulatedSurface)g) ;

	    case TYPE_POLYHEDRALSURFACE:
	        return area( (PolyhedralSurface)g) ;

	    case TYPE_SOLID:
	    case TYPE_MULTISOLID:
	        return 0 ;
	    }

	    throw new GeometryException(String.format( "Unexpected geometry type (%s) in SFCGAL::algorithm::area", g.geometryType() ));
	}
	

	double area2D( Polygon g ) throws GeometryException
	{
	    double result = 0.0 ;

	    for ( int i = 0; i < g.numRings(); i++ ) {
	        double ringArea = Math.abs( area( g.ringN( i ) ) ) ;

	        if ( i == 0 ) {
	            //exterior ring
	            result += ringArea ;
	        }
	        else {
	            //interior ring
	            result -= ringArea ;
	        }
	    }

	    return result  ;
	}
	
	
	public double areaSimplePolygon2D(Polygon polygon) throws GeometryException{
		return areaSimplePolygon2D(polygon.exteriorRing());
	}
	
	double areaSimplePolygon2D(LineString ring) throws GeometryException{
		double result=0;
		int numPoints=ring.numPoints();
		if (ring.numPoints()<=3||!ring.pointN(0).equals(ring.pointN(ring.numPoints()))){
			throw new GeometryException("The polygon is not closed");
		}
		for (int i=0;i<numPoints-1;i++){
			Point2d p1=ring.pointN(i).asPoint2d();
			Point2d p2=ring.pointN((i+1)%(numPoints-1)).asPoint2d();
			result=result+p1.x*p2.y-p1.y*p2.x;
		}
		return Math.abs(result/2);
	}

	public static double area( Triangle g )
	{
	    Vector3d v1=GeometryUtils.vectorSubtract(g.p1.asPoint3d(),g.p0.asPoint3d());
	    Vector3d v2=GeometryUtils.vectorSubtract(g.p2.asPoint3d(),g.p0.asPoint3d());
	    Vector3d cross=new Vector3d();
	    cross.cross(v1, v2);
	    return cross.length()/2;
	}



	static double area( GeometryCollection g ) throws GeometryException
	{
	    double result = 0.0 ;

	    for ( int i = 0; i < g.numGeometries(); i++ ) {
	        result += area( g.geometryN( i ) ) ;
	    }

	    return result ;
	}


	public static double area( TriangulatedSurface g )
	{
	    double result = 0.0 ;

	    for ( int i = 0; i < g.numTriangles(); i++ ) {
	        result += area( g.triangleN( i ) ) ;
	    }

	    return result ;
	}


	public static double area( PolyhedralSurface g ) throws GeometryException
	{
	    double result = 0.0 ;

	    for ( int i = 0; i < g.numPolygons(); i++ ) {
	        result += area( g.polygonN( i ) ) ;
	    }

	    return result ;
	}



	double area3D( Geometry g) throws GeometryException
	{
	    switch ( g.geometryTypeId() ) {
	    case TYPE_POINT:
	    case TYPE_LINESTRING:
	        return 0 ;

	    case TYPE_POLYGON:
	        return area3D( (Polygon)g);

	    case TYPE_TRIANGLE:
	        return area3D( (Triangle)g);

	    case TYPE_MULTIPOINT:
	    case TYPE_MULTILINESTRING:
	    case TYPE_MULTIPOLYGON:
	    case TYPE_GEOMETRYCOLLECTION:
	        return area3D( (GeometryCollection)g );

	    case TYPE_TRIANGULATEDSURFACE:
	        return area3D( (TriangulatedSurface)g);

	    case TYPE_POLYHEDRALSURFACE:
	        return area3D( (PolyhedralSurface)g);

	    case TYPE_SOLID:
	    case TYPE_MULTISOLID:
	        return 0 ;
	    }

	   throw new GeometryException( "missing case in SFCGAL::algorithm::area3D" );
	}


	public double area3D( Polygon g ) throws GeometryException
	{
	    double result = 0.0 ;

	    if ( g.isEmpty() ) {
	        return result ;
	    }

	    Point3d a, b, c ;
	    Plane plane=GeometryUtils.getPlane(g);
        a=plane.p0;
        b=plane.p1;
        c=plane.p2;
	    

	    /*
	     * compute polygon basis (CGAL doesn't build an orthonormal basis so that computing
	     * the 2D area in this basis would lead to scale effects)
	     * ux = bc
	     * uz = bc^ba
	     * uy = uz^ux
	     *
	     * Note that the basis is rounded to double (CGAL::sqrt)
	     */
	    Vector3d ux = GeometryUtils.vectorSubtract(c,b) ;
	    Vector3d uz = new Vector3d();
	    uz.cross(ux, GeometryUtils.vectorSubtract(a, b));
	    ux.normalize();
	    uz.normalize();
	    Vector3d uy=new Vector3d();
	    uy.cross(uz, ux );

	    /*
	     * compute the area for each ring in the local basis
	     */
	    for ( int i = 0; i < g.numRings(); i++ ) {
	        LineString ring = g.ringN( i );

	        Polygon projectedPolygon=new Polygon();
            projectedPolygon.addRing(new LineString());
	        for ( int j = 0; j < ring.numPoints() - 1 ; j++ ) {
	            Point3d point = ring.pointN( j ).asPoint3d();
	            Point2d projectedPoint=new Point2d(
	                GeometryUtils.vectorSubtract(point,b).dot(ux),
	                		GeometryUtils.vectorSubtract(point,b).dot(uy)
	            );
	            projectedPolygon.exteriorRing().addPoint(projectedPoint);
	        }

	        if ( i == 0 ) {
	            //exterior ring
	            result += areaSimplePolygon2D(projectedPolygon) ;
	        }
	        else {
	            //interior ring
	            result -= areaSimplePolygon2D(projectedPolygon);
	        }
	    }

	    return result ;
	}
	
    
	


	///
	///
	///
	public double area3D( GeometryCollection g ) throws GeometryException
	{
	    double result = 0.0 ;

	    for ( int i = 0; i < g.numGeometries(); i++ ) {
	        result += area3D( g.geometryN( i ) ) ;
	    }

	    return result ;
	}


	public double area3D( PolyhedralSurface g ) throws GeometryException
	{
	    double area = 0.0 ;

	    for ( int i = 0; i < g.numPolygons(); i++ ) {
	        area += area3D( g.polygonN( i ) ) ;
	    }

	    return area ;
	}

	///
	///
	///
	public double area3D( TriangulatedSurface g ) throws GeometryException
	{
	    double result = 0.0 ;

	    for ( int i = 0; i < g.numGeometries(); i++ ) {
	        result += area3D( g.geometryN( i ) ) ;
	    }

	    return result ;
	}

}
