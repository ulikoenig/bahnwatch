package de.ulikoenig.bahnwatch;

public class Task implements Runnable {

	public Task(Train train, int stopNo) {
		super();
		this.train = train;
		this.stopNo = stopNo;
	}

	private final Train train;
	private final int stopNo;

	public void execute() {
		System.out.println("Task executed");
	}

	@Override
	public void run() {
		TrainStop[] stops = train.getTrainStops().toArray(new TrainStop[train.getStopCount()]);
		System.out.println("I am Train thread:" + train.getZugnummer()+" "+stops[stopNo-1].toString());
		if (train.getStopCount() > stopNo) {
			Tasker.getInstance().start(train, stopNo + 1);
		} else {
			Tasker.getInstance().stop();
		}
	}

}
