package de.ulikoenig.bahnwatch;

import java.util.Calendar;

public class TrainStop implements Comparable<TrainStop> {
	public TrainStop(String stopName, int hours, int minutes) {
		super();
		this.stopName = stopName;
		this.hours = hours;
		this.minutes = minutes;
	}

	private final int minutes;
	private final int hours;
	private final String stopName;

	public int getMinutes() {
		return minutes;
	}

	public int getHours() {
		return hours;
	}

	public String getStopName() {
		return stopName;
	}

	@Override
	public int compareTo(TrainStop arg0) {
		Calendar calThis = Calendar.getInstance();
		calThis.set(2000, 01, 01, hours, minutes, 0);
		Calendar calOther = Calendar.getInstance();
		calOther.set(2000, 01, 01, arg0.getHours(), arg0.getMinutes(), 0);
		if (calThis.compareTo(calOther) != 0) {
			return this.stopName.compareTo(arg0.stopName);
		} else {
			return calThis.compareTo(calOther);
		}
	}

	@Override
	public int hashCode() {
		Calendar calThis = Calendar.getInstance();
		calThis.set(2000, 01, 01, hours, minutes, 0);
		return calThis.hashCode() + stopName.hashCode();
	}

	@Override
	public String toString() {
		return stopName + " " + hours + ":" + String.format("%02d", minutes);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof TrainStop) {
			TrainStop other = (TrainStop) o;
			if (this.stopName.equals(other.getStopName()) //
					&& (this.hours == other.getHours()) //
					&& (this.minutes == other.getMinutes())) {
				return true;
			}
		}
		return false;
	}

}
