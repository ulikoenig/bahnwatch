package de.ulikoenig.bahnwatch;

import java.util.SortedSet;

public class Train {
	private final int zugnummer;
	private final TrainStop start;
	private final TrainStop end;
	private final SortedSet<TrainStop> trainStops;

	public Train(int zugnummer, SortedSet<TrainStop> trainStops) {
		super();
		this.zugnummer = zugnummer;
		this.start = trainStops.first();
		this.end = trainStops.last();
		this.trainStops = trainStops;
	}

	public int getZugnummer() {
		return zugnummer;
	}

	public TrainStop getStart() {
		return start;
	}

	public TrainStop getEnd() {
		return end;
	}

	public SortedSet<TrainStop> getTrainStops() {
		return trainStops;
	}
	
	public int getStopCount() {
		return trainStops.size();
	}

}
