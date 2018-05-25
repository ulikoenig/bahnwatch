package de.ulikoenig.bahnwatch;

import java.util.Calendar;

import de.ulikoenig.bahnwatch.util.Util;

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
	private int realMinutes = -1;
	private int realHours = -1;

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
		Calendar calThis = Util.getDayCalender(hours, minutes);
		Calendar calOther = Util.getDayCalender(arg0.getHours(), arg0.getMinutes());

		Calendar calThisLate = Util.getDayCalender(realHours, realMinutes);
		Calendar calOtherLate = Util.getDayCalender(arg0.getRealHours(), arg0.getRealMinutes());

		// if times are the the same
		if (calThis.compareTo(calOther) == 0) {

			// if late times are the same
			if (calThisLate.compareTo(calOtherLate) == 0) {
				return this.stopName.compareTo(arg0.stopName);
			} else {
				return calThisLate.compareTo(calOtherLate);
			}

		} else {
			// if times are NOT the same
			return calThis.compareTo(calOther);
		}
	}

	@Override
	public int hashCode() {
		Calendar realCalThis = Util.getDayCalender(realHours, realMinutes);
		Calendar calThis = Util.getDayCalender(hours, minutes);
		return realCalThis.hashCode() + calThis.hashCode() + stopName.hashCode();
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
					&& (this.minutes == other.getMinutes()) //
					&& (this.realHours == other.getRealHours()) //
					&& (this.realMinutes == other.getRealMinutes()) //
			) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Get minutes of late Train.
	 * 
	 * @return
	 */
	public int getRealMinutes() {
		return realMinutes;
	}

	public void setRealMinutes(int realMinutes) {
		this.realMinutes = realMinutes;
	}

	/**
	 * Get hour of late Train.
	 * 
	 * @return
	 */
	public int getRealHours() {
		return realHours;
	}

	public void setRealHours(int realHours) {
		this.realHours = realHours;
	}

}
