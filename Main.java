package com.littlepay.au;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import au.com.bytecode.opencsv.CSVWriter;

public class Main {

	public static void main(String[] args) throws IOException {

		String line = "";
		String splitBy = ",";
		int count = 0;

		ArrayList<Taps> tapslist = new ArrayList<Taps>();

		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader("D:\\Srikanth Kandula\\Littlepay\\Input\\taps.csv"));
			while ((line = br.readLine()) != null) // returns a Boolean value
			{
				String[] csvArray = line.split(splitBy); // use comma as separator

				if (count > 0 && !line.isEmpty()) {

					Taps taps = new Taps();
					taps.setId((Integer.valueOf(csvArray[0])));
					taps.setDateTimeUTC((csvArray[1]));
					taps.setTapType(csvArray[2]);
					taps.setStopId(csvArray[3]);
					taps.setCompanyId(csvArray[4]);
					taps.setBusId(csvArray[5]);
					taps.setPan(csvArray[6]);
					tapslist.add(taps);

				}
				count = count + 1;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		Trips trips = new Trips();
		trips.setStarted(tapslist.get(0).getDateTimeUTC());
		if(tapslist.size() > 1)
		trips.setFinished(tapslist.get(1).getDateTimeUTC());
		trips.setFromStopId(tapslist.get(0).getStopId());
		if(tapslist.size() > 1)
		trips.setToStopId(tapslist.get(1).getStopId());
		trips.setCompanyId(tapslist.get(0).getCompanyId());
		trips.setBusId(tapslist.get(0).getBusId());
		trips.setPan(tapslist.get(0).getPan());

		Collections.sort(tapslist, Comparator.comparing((Taps t) -> t.getStopId()));

		if ( (tapslist.size() > 1)&& tapslist.get(0).getStopId() != null && tapslist.get(1).getStopId() != null ) {
			trips.setStatus("Completed");
			long time = TimeCalculate.findDifference(tapslist.get(0).getDateTimeUTC(),
					tapslist.get(1).getDateTimeUTC());

			trips.setDurationSecs(String.valueOf(time));
			if (tapslist.get(1).getStopId().charAt(4) - tapslist.get(0).getStopId().charAt(4) == 2) {
				trips.setChargeAmount("$7.30");
			} else if (tapslist.get(1).getStopId().charAt(4) - tapslist.get(0).getStopId().charAt(4) == 1) {
				if (tapslist.get(1).getStopId().equalsIgnoreCase("stop3")) {
					trips.setChargeAmount("$5.50");
				} else {
					trips.setChargeAmount("$3.25");
				}
			} else {
				trips.setChargeAmount("$0.00");
				trips.setStatus("Cancelled");
				trips.setDurationSecs(String.valueOf(000));
			}

		} else {
			trips.setDurationSecs(String.valueOf(-000));
			trips.setStatus("Incomplete");
			if (tapslist.get(0).getStopId().charAt(4) == '2') {
				trips.setChargeAmount("$5.50");
			} else {
				trips.setChargeAmount("$7.30");
			}
		}
		String s[] = {"Started", "Finished", "DurationSecs", "FromStopId", "ToStopId", "ChargeAmount", "CompanyId", "BusID", "PAN", "Status" };
		CSVWriter writer = new CSVWriter(new FileWriter("D:\\Srikanth Kandula\\Littlepay\\output\\trips.csv"));
		writer.writeNext(s);
		String trip[] = {trips.getStarted(), trips.getFinished(), trips.getDurationSecs(), trips.getFromStopId(), trips.getToStopId(), trips.getChargeAmount(), trips.getCompanyId(), trips.getBusId(), trips.getPan(), trips.getStatus() };
		writer.writeNext(trip);
		writer.close();
	}

}