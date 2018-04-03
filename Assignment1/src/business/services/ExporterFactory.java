package business.services;

public class ExporterFactory {
	
	public static Exporter getExporter(String identifierName) {
		return new CSVExporter(identifierName);
	}

}
