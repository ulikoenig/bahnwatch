package de.ulikoenig.bahnwatch.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.SortedSet;

import org.junit.Test;

import de.ulikoenig.bahnwatch.ScrapeBahn;
import de.ulikoenig.bahnwatch.TrainInfo;
import de.ulikoenig.bahnwatch.TrainStop;

public class ScrapeBahnTest {

	 @Test
	 public void testScrapeBahn() {
	 ScrapeBahn sc = new ScrapeBahn();
	 TrainInfo ti = sc.scrapeBahn(21424);
	 System.out.println(ti.toString());
	 assertNotNull(ti);
	 }

	@Test
	public void testScrapeBahnLokal21428() {
		ScrapeBahn sc = new ScrapeBahn();
		TrainInfo ti = sc.scrapeBahn("/home/ukoenig/eclipse-workspace/BahnWatch/samples/2018-05-06-21428-problem.htm");
		assertNotNull(ti);
		// System.out.println(ti.toString());
		assertEquals(ti.getProblem(),
				"Störung an einem Bahnübergang: Auf der Strecke Lübeck Hbf - Hamburg Hbf zwischen Lübeck Hbf und Reinfeld(Holst). Es kommt zu Verspätungen in beide Richtungen im Regionalverkehr der Deutschen Bahn.");
		SortedSet<TrainStop> stops = ti.getTrainStops();
		assertEquals(4, stops.size());
	}
}
