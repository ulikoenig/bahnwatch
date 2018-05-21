package de.ulikoenig.bahnwatch;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TrainDB {
	public static final Set<Integer> trains;
	static {
        Set<Integer> tmpTrains = new HashSet<Integer>();
        tmpTrains.add(21424);
        tmpTrains.add(21418);
        tmpTrains.add(21420);
        tmpTrains.add(21422);
        tmpTrains.add(21424);
        tmpTrains.add(21426);
        tmpTrains.add(21428);
        tmpTrains.add(21430);
        tmpTrains.add(21472);
        tmpTrains.add(21474);
        tmpTrains.add(21476);
        tmpTrains.add(21478);

        //Lübeck -- Hamburg
        tmpTrains.add(21441); //00:16
        tmpTrains.add(21301); //04:13
        tmpTrains.add(21403); //05:08
        tmpTrains.add(21455); //05:43
        tmpTrains.add(21405); //06:08
        tmpTrains.add(21457); //06:43
        tmpTrains.add(21457); //06:43
        tmpTrains.add(21447); //07:00
        tmpTrains.add(21447); //07:00
        tmpTrains.add(21407); //07:08
        tmpTrains.add(21459); //07:43
        tmpTrains.add(21409); //08:08
        tmpTrains.add(21461); //08:43
        tmpTrains.add(21411); //09:08
        tmpTrains.add(21463); //09:43
        
        
        trains = Collections.unmodifiableSet(tmpTrains);
    }
}
