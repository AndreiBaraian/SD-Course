package business.services;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import business.model.TicketModel;

public class CSVExporter implements Exporter {
	
	private String filename = "tickets";
	private static final String COMMA_DELIMETER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER = "id,seatRow,seatCol,showId";

	
	public CSVExporter(String identifierName) {
		filename = filename + identifierName + ".csv";
	}
	
	public void export(List<TicketModel> tickets) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(filename);
			writer.append(FILE_HEADER);
			writer.append(NEW_LINE_SEPARATOR);
			for(TicketModel t : tickets) {
				writer.append(Integer.toString(t.getId()));
				writer.append(COMMA_DELIMETER);
				writer.append(Integer.toString(t.getSeatRow()));
				writer.append(COMMA_DELIMETER);
				writer.append(Integer.toString(t.getSeatCol()));
				writer.append(COMMA_DELIMETER);
				writer.append(Integer.toString(t.getShowId()));
				writer.append(NEW_LINE_SEPARATOR);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
