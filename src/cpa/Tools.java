package cpa;

import java.awt.Point;
import java.util.ArrayList;

public class Tools {

	public ArrayList<Point> enveloppeConvexeJarvis(ArrayList<Point> points) {
		if (points.size() < 3) {
			return null;
		}

		ArrayList<Point> enveloppe = new ArrayList<Point>();
		Point a, b, c, start;
		a = points.get(0);
		b = points.get(0);
		c = points.get(0);

		// Find first point
		for (Point p : points) {
			if (p.x < a.x)
				a = p;
		}

		boolean prevSide = true;
		boolean side = true;
		boolean init, ok;
		double val;

		// Find first segment
		for (Point p : points) {
			if (p.equals(a))
				continue;
			init = false;
			ok = true;

			for (Point pt : points) {
				if (pt.equals(p) || pt.equals(a))
					continue;
				val = produitVectoriel(a, p, a, pt);
				if (val == 0)
					continue;
				else if (!init) {
					prevSide = val > 0;
					init = true;
				} else {
					side = val > 0;
					if (prevSide != side)
						ok = false;
					prevSide = side;
				}

				if (!ok)
					break;
			}
			if (ok) {
				b = p;
				break;
			}

		}

		enveloppe.add(a);
		enveloppe.add(b);
		start = a;
		double minScal;
		int cpt = 0;

		while (!c.equals(start) && cpt < 200) {
			minScal = Double.MIN_VALUE;

			for (Point p : points) {
				if (p.equals(a) || p.equals(b))
					continue;

				val = angleScalaire(a, b, p);
				if (val > minScal) {
					minScal = val;
					c = p;
				}
			}

			if (!enveloppe.contains(c))
				enveloppe.add(c);
			a = b;
			b = c;
			cpt++;
		}

		return enveloppe;
	}

	private double distanceDroite(Point d1, Point d2, Point x) {
		return Math.abs(produitVectoriel(d1, d2, d1, x));
	}

	private double produitVectoriel(Point a, Point b, Point c, Point d) {
		return ((b.x - a.x) * (d.y - c.y)) - ((b.y - a.y) * (d.x - c.x));
	}

	private double angleScalaire(Point a, Point b, Point c) {
		return ((b.x - a.x) * (c.x - b.x) + (b.y - a.y) * (c.y - b.y))
				/ b.distance(c);
	}

}
