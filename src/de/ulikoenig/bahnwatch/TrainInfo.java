package de.ulikoenig.bahnwatch;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

/**
 * @author ukoenig
 *
 */
public class TrainInfo {
	private int trainNo = -1;
	private Elements time;
	private Elements train;
	private Elements platfrom;
	private Elements route;
	private SortedSet<TrainStop> trainStops;
	private Elements ris;
	private String problem;
	private String trainDetailsURL;

	@Override
	public String toString() {
		String result = "";
		if (trainNo > 0) {
			result += "Zugnummer: " + trainNo + " ";
		}
		if ((train != null) && !train.text().isEmpty()) {
			result += "Zug: " + train.text() + " ";
		}
		if ((platfrom != null) && !platfrom.text().isEmpty()) {
			result += "Bahnsteig: " + platfrom.text() + " ";
		}
		if ((route != null) && !route.text().isEmpty()) {
			result += "Route: " + route.text() + " ";
		}
		if ((ris != null) && (!ris.text().isEmpty())) {
			result += "RIS: " + ris.text() + " ";
		}
		return result;

	}

	public int getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}

	public Elements getTime() {
		return time;
	}

	public void setTime(Elements time) {
		this.time = time;
	}

	public Elements getTrain() {
		return train;
	}

	public void setTrain(Elements train) {
		this.train = train;
		this.trainDetailsURL= train.first().getElementsByAttribute("href").attr("href");
	}

	public Elements getPlatfrom() {
		return platfrom;
	}

	public void setPlatfrom(Elements platfrom) {
		this.platfrom = platfrom;
	}

	public Elements getRoute() {
		return route;
	}

	public void setRoute(Elements route) {
		this.route = route;
		this.trainStops = new TreeSet<TrainStop>();

		// Extract Stops
		Element route1 = route.first();
		String boldStation = route1.getElementsByClass("bold").first().text();
		String allStations = route1.text().replaceFirst(boldStation, "");

		// Stoerung
		Elements stoerung = route1.getElementsByClass("red bold");
		this.problem = stoerung.text();
		allStations = allStations.replace(problem, "");

		for (Iterator<Node> iterator = route1.childNodes().iterator(); iterator.hasNext();) {
			Node element = iterator.next();
			if (element instanceof TextNode) {
				TextNode textNode = (TextNode) element;
				String allStops = textNode.toString();
				if (!allStops.trim().isEmpty()) {
					// Stations aussortieren
					String[] stops = allStops.trim().split("([ ]*[\\d]*:[\\d]*[ -]*[ ]*)");
					for (int i = 0; i < stops.length; i++) {
						stops[i].replaceAll("\\d\\d:\\d\\d", "");
						String[] parts = allStops.split(stops[i].replace("(", "\\(").replace(")", "\\)"));
						parts = parts[1].split("-");
						String[] abfahrtZeit = parts[0].trim().split(":");

						trainStops.add(new TrainStop(stops[i], Integer.parseInt(abfahrtZeit[0]),
								Integer.parseInt(abfahrtZeit[1])));
					}
				}
			}
		}

	}

	public Elements getRis() {
		return ris;
	}

	public void setRis(Elements ris) {
		this.ris = ris;
	}

	public String getProblem() {
		return problem;
	}

	public void setTrainStops(SortedSet<TrainStop> trainStops) {
		this.trainStops = trainStops;
	}

	public SortedSet<TrainStop> getTrainStops() {
		return trainStops;
	}

}
