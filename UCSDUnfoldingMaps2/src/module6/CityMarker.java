package module6;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

/** Implements a visual marker for cities on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Alena Ryzhkova
 * 
 */
public class CityMarker extends CommonMarker {
	
	PImage img;
	public static int ICON_SIZE = 9;
	
	public CityMarker(Location location, PImage img) {
		super(location);
		this.img = img;
	}
	
	
	public CityMarker(Feature city, PImage img) {
		super(((PointFeature)city).getLocation(), city.getProperties());
		this.img = img;
		// Cities have properties: "name" (city name), "country" (country name)
		// and "population" (population, in millions)
	}
	
	/**
	 * Implementation of method to draw marker on the map.
	 */
	@Override
	public void drawMarker(PGraphics pg, float x, float y, UnfoldingMap map) {
		//System.out.println("Drawing a city");
		// Save previous drawing style
		pg.pushStyle();
		pg.image(img, x, y, ICON_SIZE, ICON_SIZE*3/2);		
		// Restore previous drawing style
		pg.popStyle();
	}
	
	/** Show the title of the city if this marker is selected */
	public void showTitle(PGraphics pg, float x, float y)
	{
		String name = getCity() + " " + getCountry() + " ";
		String pop = "Pop: " + getPopulation() + " Million";
		
		pg.pushStyle();
		
		pg.fill(255, 255, 255);
		pg.textSize(12);
		pg.rectMode(PConstants.CORNER);
		pg.rect(x, y-ICON_SIZE-39, Math.max(pg.textWidth(name), pg.textWidth(pop)) + 6, 39);
		pg.fill(0, 0, 0);
		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		pg.text(name, x+3, y-ICON_SIZE-33);
		pg.text(pop, x+3, y - ICON_SIZE -18);
		
		pg.popStyle();
	}
	
	private String getCity()
	{
		return getStringProperty("name");
	}
	
	private String getCountry()
	{
		return getStringProperty("country");
	}
	
	private float getPopulation()
	{
		return Float.parseFloat(getStringProperty("population"));
	}
}
