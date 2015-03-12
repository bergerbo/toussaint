package cpa;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;


public class Toussaint {

	
	public ArrayList<Point> rectMin(ArrayList<Point> enveloppe){
		ArrayList<Point> rectmin = new ArrayList<>();
		Point ouest = enveloppe.get(0);
		Point est = enveloppe.get(0);
		
		for (Point p : enveloppe)
		{
			if (p.x < ouest.x)
				ouest = p;
			if( p.x > est.x)
				est = p;
		}
		
		Line2D.Float left = new Line2D.Float(ouest.x, ouest.y, ouest.x, ouest.y +1);
		Line2D.Float right = getParallel(left, est);
		
		int pass = 0;
		int index = 0;
		
		while (pass < 2){
			
			
			
			if(left.contains(est) || right.contains(ouest)){
				pass++;
			}
		}
		
		
		return rectmin;
	}
	
	private float getMinBox(ArrayList<Point> enveloppe, Line2D.Float left, Line2D.Float right){
		
		return 0;
	}
	
	private Line2D.Float getParallel(Line2D.Float d, Point p ){
		float x_off = d.x2 - d.x1;
		float y_off = d.y2 - d.y1;
		return new Line2D.Float(p.x, p.y, p.x + x_off, p.y + y_off);
	}
}
