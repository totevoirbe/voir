package be.panidel.pos.printer;

/*
 * Copyright (c) 2006 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * -Redistribution of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *
 * -Redistribution in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MIDROSYSTEMS, INC. ("SUN")
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL,
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of any
 * nuclear facility.
 */

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Date;

import org.apache.log4j.Logger;

import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.Tools;

//public class HelloWorldPrinter2 implements Printable, ActionListener {
public abstract class AbstractPrintReport implements Printable {

	private static final Logger log = Logger.getLogger("PrintReport");

	private PrinterJob printerJob;
	protected Date startDate;
	protected Date endDate;
	protected int orientation = PageFormat.PORTRAIT;

	public AbstractPrintReport(Date startDate, Date endDate)
			throws ParameterException {
		super();
		this.startDate = Tools.startOfCurentDay(startDate);
		if (endDate == null) {
			this.endDate = Tools.endOfDay(startDate);
		} else {
			this.endDate = Tools.endOfDay(endDate);
		}
		printerJob = PrinterJob.getPrinterJob();
	}

	public abstract void drawReport(Graphics2D g);

	public int print(Graphics g, PageFormat pf, int page)
			throws PrinterException {

		log.debug("print document");

		if (page > 0) { /* We have only one page, and 'page' is zero-based */
			log.debug("More then 1 page : " + page);
			return NO_SUCH_PAGE;
		}

		/*
		 * User (0,0) is typically outside the imageable area, so we must
		 * translate by the X and Y values in the PageFormat to avoid clipping
		 */
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());

		Font gFt = g.getFont();
		Font ft = new Font(gFt.getName(), gFt.getStyle(), 8);
		g.setFont(ft);

		drawReport(g2d);

		/* tell the caller that this page is part of the printed document */
		return PAGE_EXISTS;
	}

	public void doPrint() {

		PageFormat pf = printerJob.defaultPage();
		pf.setOrientation(orientation);
		printerJob.setPrintable(this, pf);
		boolean ok = printerJob.printDialog();
		if (ok) {
			try {
				log.debug("Print run");
				printerJob.print();
			} catch (PrinterException ex) {
				log.error("The print job did not successfully complete", ex);
			}
		} else {
			log.debug("Pint job cancelled");

		}
	}
}