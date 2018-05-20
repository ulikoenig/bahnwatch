package de.ulikoenig.bahnwatch;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Tasker {
	static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

	private static Tasker instance;

	private Tasker() {
	}

	public static Tasker getInstance() {
		if (Tasker.instance == null) {
			Tasker.instance = new Tasker();
		}
		return Tasker.instance;
	}

	public static void main(String[] args) {
		Tasker tasker = getInstance();
//		SortedSet<TrainStop> trainStops = new TreeSet<TrainStop>(Collections.reverseOrder());
		SortedSet<TrainStop> trainStops = new TreeSet<TrainStop>();
		trainStops.add(new TrainStop("Hamburg Hbf", 0, 9));
//		trainStops.add(new TrainStop("Bad Oldesloe", 23, 46));
//		trainStops.add(new TrainStop("Reinfeld(Holst)", 23, 52));
		trainStops.add(new TrainStop("Lübeck Hbf", 0, 10));
		Train train = new Train(21438, trainStops);
		tasker.start(train);
	}

	private void start(Train train) {
		System.out.println("started:");
		start(train, 1);
	}

	public void start(Train train, int stopNo) {
		TrainStop[] stops = train.getTrainStops().toArray(new TrainStop[train.getStopCount()]);
		TrainStop stop = stops[stopNo-1];
		long delay = computeNextDelay(stop.getHours(), stop.getMinutes(), 0);
		executorService.schedule(new Task(train,stopNo), delay, TimeUnit.SECONDS);
	}

	private long computeNextDelay(int targetHour, int targetMin, int targetSec) {
		LocalDateTime localNow = LocalDateTime.now();
		ZoneId currentZone = ZoneId.systemDefault();
		ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);
		ZonedDateTime zonedNextTarget = zonedNow.withHour(targetHour).withMinute(targetMin).withSecond(targetSec);
		if (zonedNow.compareTo(zonedNextTarget) > 0)
			zonedNextTarget = zonedNextTarget.plusDays(1);
		Duration duration = Duration.between(zonedNow, zonedNextTarget);
		return duration.getSeconds();
	}

	public void stop() {
		System.out.println("stop");
		executorService.shutdown();
		try {
			executorService.awaitTermination(1, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			System.err.println("Fehler");
		}
	}

}
