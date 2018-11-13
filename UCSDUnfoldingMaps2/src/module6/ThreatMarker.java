package module6;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.utils.GeoUtils;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import processing.core.PApplet;
import processing.core.PGraphics;

/** Implements a visual threat zone for earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Alena Ryzhkova
 *
 */

public class ThreatMarker extends EarthquakeMarker {
	
	public ThreatMarker(PointFeature feature) {
		super(feature);
	}
	
	@Override
	public void drawMarker(PGraphics pg, float x, float y, UnfoldingMap map) {
		Location centerLocation = this.getLocation();
		Location upperLocation = GeoUtils.getDestinationLocation(centerLocation, 0, (float)threatCircle());
		
		ScreenPosition center = map.getScreenPosition(centerLocation);
		ScreenPosition upper = map.getScreenPosition(upperLocation);
		radius = PApplet.dist(center.x, center.y, upper.x, upper.y);
		
		pg.noStroke();
		pg.fill(235, 30, 30, 50);
		pg.ellipse(x, y, 2*radius, 2*radius);
	}
	
	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		// NOP

	}

}
