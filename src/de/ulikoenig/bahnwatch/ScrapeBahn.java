package de.ulikoenig.bahnwatch;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScrapeBahn {

	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0";

	public TrainInfo scrapeBahn(int zugNummer) {
		String source = getBahnUrl(zugNummer);
		Document doc;
		try {
			doc = Jsoup.connect(source).userAgent(USER_AGENT).get();
			return scrapeBahn(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String getBahnUrl(int trainNumber) {
		String stationName = "Hamburg+Hbf";
		return "https://reiseauskunft.bahn.de/bin/bhftafel.exe/dn?ld=3974&country=DEU&protocol=https:&rt=1&boardType=dep&GUIREQProduct_3=on&start=suchen&REQTrain_name="
				+ trainNumber + "&input=" + stationName;
	}

	public TrainInfo scrapeBahn(String sourceToScrape) {
		Document doc;
		try {
			File input = new File(sourceToScrape);
			if (input.exists()) {
				doc = Jsoup.parse(input, "UTF-8", "https://reiseauskunft.bahn.de");
				return scrapeBahn(doc);
			} else {
				System.err.println("Fehler, Datei " + input + " nicht gefunden.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private TrainInfo scrapeBahn(Document doc) {
		TrainInfo trainInfo = new TrainInfo();
		Element row1 = doc.getElementById("journeyRow_1");
		if (row1 != null) {
			trainInfo.setTime(row1.getElementsByClass("time"));
			trainInfo.setTrain(row1.getElementsByClass("train"));
			trainInfo.setRoute(row1.getElementsByClass("route"));
			trainInfo.setPlatfrom(row1.getElementsByClass("platform"));
			trainInfo.setRis(row1.getElementsByClass("ris"));
			return trainInfo;
		} else {
			Elements errormsg = doc.getElementsByClass("errormsg");
			if (errormsg != null) {
				throw new TrainOutOfServiceException(errormsg.text());
			} else {
				System.err.println("NoBahnError");
			}
			return null;
		}
	}
}
