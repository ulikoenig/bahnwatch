package de.ulikoenig.bahnwatch;

public class TrainOutOfServiceException extends RuntimeException {

	public TrainOutOfServiceException(String text) {
		super(text);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
