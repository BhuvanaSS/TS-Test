package Ebay;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Flatfilereader {	


	private static final String FILENAME = "C:\\Users\\prasanna\\Documents\\FlatFile.txt";

	public static void main(String[] args) {

		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}


