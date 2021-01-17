package be.panidel.print;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

public class TestPrint2 {

	public static void main(String[] args) {

		FileInputStream textStream = null;
		try {
			textStream = new FileInputStream("c:/Users/franzph/Desktop/printtest.txt");
		} catch (FileNotFoundException ffne) {
			System.err.println("File not found");
		}
		if (textStream == null) {
			System.err.println("Stream is null");
			return;
		}
		System.out.println("File is found");
		// Set the document type
		DocFlavor myFormat = DocFlavor.INPUT_STREAM.TEXT_PLAIN_US_ASCII;
		// Create a Doc
		Doc myDoc = new SimpleDoc(textStream, myFormat, null);
		// Build a set of attributes
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		aset.add(new Copies(5));
//		aset.add(MediaSize.ISO.A4);
//		aset.add(Sides.DUPLEX);
		// discover the printers that can print the format according to the
		// instructions in the attribute set
		PrintService[] services = PrintServiceLookup.lookupPrintServices(
				myFormat, aset);
		// Create a print job from one of the print services
		if (services.length > 0) {
			DocPrintJob job = services[0].createPrintJob();
			try {
				job.print(myDoc, aset);
				System.out.println("Printed");
			} catch (PrintException pe) {
				System.err.println("Print exception");
			}
		} else {
			System.err.println("Service length <= 0");
		}
		System.out.println("End");
	}
}
