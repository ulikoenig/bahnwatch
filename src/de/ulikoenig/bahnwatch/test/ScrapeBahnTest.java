package de.ulikoenig.bahnwatch.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;

import org.junit.Test;

import de.ulikoenig.bahnwatch.ScrapeBahn;
import de.ulikoenig.bahnwatch.TrainDB;
import de.ulikoenig.bahnwatch.TrainInfo;
import de.ulikoenig.bahnwatch.TrainStop;

public class ScrapeBahnTest {

	private static final String SAMPLE_DIR = Paths.get(".").toAbsolutePath().normalize().toString()
			+ java.io.File.separator + "samples" + java.io.File.separator;

	@Test
	public void testScrapeBahn() {
		ScrapeBahn sc = new ScrapeBahn();
		TrainInfo ti = sc.scrapeBahn(21424);
		assertNotNull(ti);

		TrainStop[] ts = sortedSetToArray(ti.getTrainStops());
		assertEquals(new TrainStop("Hamburg Hbf", 16, 4), ts[0]);
		assertEquals(new TrainStop("Bad Oldesloe", 16, 29), ts[1]);
		assertEquals(new TrainStop("Reinfeld(Holst)", 16, 35), ts[2]);
		assertEquals(new TrainStop("L\\u00fcbeck Hbf", 16, 48), ts[3]);
		// assertEquals(new TrainStop("L�beck-D�nischburg IKEA", 17, 4), ts[4]);
	}

	private TrainStop[] sortedSetToArray(SortedSet<TrainStop> sortedSet) {
		return sortedSet.toArray(new TrainStop[sortedSet.size()]);
	}

	@Test
	public void testScrapeBahnLokal21428() {
		ScrapeBahn sc = new ScrapeBahn();
		TrainInfo ti = sc.scrapeBahn(SAMPLE_DIR + "2018-05-06-21428-problem.htm");
		assertNotNull(ti);
		// System.out.println(ti.toString());
		assertEquals(ti.getProblem(),
				"St\u00f6rung an einem Bahn\u00fcbergang: Auf der Strecke L\u00fcbeck Hbf - Hamburg Hbf zwischen L\u00fcbeck Hbf und Reinfeld(Holst). Es kommt zu Versp\u00e4tungen in beide Richtungen im Regionalverkehr der Deutschen Bahn.");
		SortedSet<TrainStop> stops = ti.getTrainStops();
		assertEquals(4, stops.size());
	}

	@Test
	public void testScrapeBahnDB() {
		ScrapeBahn sc = new ScrapeBahn();
		Set<Integer> trains = TrainDB.trains;
		for (Iterator<Integer> iterator = trains.iterator(); iterator.hasNext();) {
			int trainNumber = iterator.next();
			TrainInfo ti = sc.scrapeBahn(trainNumber);
			if (ti == null) {
				System.out.flush();
				System.err.println("Zugnummer" + trainNumber + " nicht gefunden");
				System.err.flush();
			} else {
				System.out.println(ti);
			}
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
