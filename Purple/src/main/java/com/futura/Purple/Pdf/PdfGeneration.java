package com.futura.Purple.Pdf;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Stream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.futura.Purple.Entity.CoverDetailsPas;
import com.futura.Purple.Entity.FundDetailsPas;
import com.futura.Purple.Entity.PolicyDetailsPas;
import com.futura.Purple.Entity.PurpleDetails;
import com.futura.Purple.Entity.TransactionPas;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfTable;
import com.itextpdf.text.pdf.PdfNumber;

public class PdfGeneration {

	private static Logger logger = LoggerFactory.getLogger(PdfGeneration.class);

	public static ByteArrayInputStream nonUlip(PolicyDetailsPas policy, TransactionPas trans,
			List<CoverDetailsPas> covers, PurpleDetails purple, String companyName, BufferedImage i)
			throws MalformedURLException, IOException, ParseException {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		String prem = policy.getPremToDate();
		String req = trans.getFlcReqDate();
		String log = trans.getFlcLogDate();
		String approv = trans.getFlcApprovalDate();
		String papprov = purple.getApprovDate();
		String transd = purple.getTranDate();
		SimpleDateFormat fromUser = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");

		String premDate = myFormat.format(fromUser.parse(prem));
		String reqdate = myFormat.format(fromUser.parse(req));
		String logdate = myFormat.format(fromUser.parse(log));
		String approvDate = myFormat.format(fromUser.parse(approv));
		String transDate = myFormat.format(fromUser.parse(transd));
		String papprovDate = myFormat.format(fromUser.parse(papprov));
//		    System.out.println("Perm "+premDate);

		try {

			PdfWriter.getInstance(document, out);
			document.open();

			Font font1 = FontFactory.getFont(FontFactory.COURIER, 48, BaseColor.BLACK);
			Font font2 = FontFactory.getFont(FontFactory.COURIER, 18, BaseColor.BLACK);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(i, "png", baos);
			Image image1 = Image.getInstance(baos.toByteArray());

			image1.setAbsolutePosition(40f, 700f);

			image1.scaleAbsolute(80, 80);

			document.add(image1);

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			Paragraph para = new Paragraph(" " + companyName, font1);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			document.add(new Paragraph("Policy Details", font2));
			document.add(Chunk.NEWLINE);

			PdfPTable Poltable = new PdfPTable(4);

			PdfPCell PolNoheader = new PdfPCell();
			PolNoheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			PolNoheader.setBorderWidth(2);
			PolNoheader.setPhrase(new Phrase("Policy No.", headFont));
			Poltable.addCell(PolNoheader);

			PdfPCell BillFrheader = new PdfPCell();
			BillFrheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			BillFrheader.setBorderWidth(2);
			BillFrheader.setPhrase(new Phrase("Bill Frequency", headFont));
			Poltable.addCell(BillFrheader);

			PdfPCell InPheader = new PdfPCell();
			InPheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			InPheader.setBorderWidth(2);
			InPheader.setPhrase(new Phrase("Installment Premium", headFont));
			Poltable.addCell(InPheader);

			PdfPCell PreDateheader = new PdfPCell();
			PreDateheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			PreDateheader.setBorderWidth(2);
			PreDateheader.setPhrase(new Phrase("Premium Date", headFont));
			Poltable.addCell(PreDateheader);

			PdfPCell PolNo = new PdfPCell(new Phrase(policy.getChdrNum().toString()));
			PolNo.setPaddingLeft(4);
			PolNo.setVerticalAlignment(Element.ALIGN_MIDDLE);
			PolNo.setHorizontalAlignment(Element.ALIGN_CENTER);
			PolNo.setBorderWidth(2);
			PolNo.setPaddingRight(4);
			Poltable.addCell(PolNo);

			PdfPCell BillFr = new PdfPCell(new Phrase(policy.getBillFreq().toString()));
			BillFr.setPaddingLeft(4);
			BillFr.setVerticalAlignment(Element.ALIGN_MIDDLE);
			BillFr.setHorizontalAlignment(Element.ALIGN_CENTER);
			BillFr.setBorderWidth(2);
			BillFr.setPaddingRight(4);
			Poltable.addCell(BillFr);

			PdfPCell InP = new PdfPCell(new Phrase(policy.getInstallmentPremium().toString()));
			InP.setPaddingLeft(4);
			InP.setVerticalAlignment(Element.ALIGN_MIDDLE);
			InP.setHorizontalAlignment(Element.ALIGN_CENTER);
			InP.setBorderWidth(2);
			InP.setPaddingRight(4);
			Poltable.addCell(InP);

			PdfPCell PreDate = new PdfPCell(new Phrase(premDate));
			PreDate.setPaddingLeft(4);
			PreDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
			PreDate.setHorizontalAlignment(Element.ALIGN_CENTER);
			PreDate.setBorderWidth(2);
			PreDate.setPaddingRight(4);
			Poltable.addCell(PreDate);

			PdfPCell LifeAheader = new PdfPCell();
			LifeAheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			LifeAheader.setBorderWidth(2);
			LifeAheader.setPhrase(new Phrase("Life Assured Age", headFont));
			Poltable.addCell(LifeAheader);

			PdfPCell PolStatheader = new PdfPCell();
			PolStatheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			PolStatheader.setBorderWidth(2);
			PolStatheader.setPhrase(new Phrase("Policy Status", headFont));
			Poltable.addCell(PolStatheader);

			PdfPCell MedFheader = new PdfPCell();
			MedFheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			MedFheader.setBorderWidth(2);
			MedFheader.setPhrase(new Phrase("Medical Flag", headFont));
			Poltable.addCell(MedFheader);

			PdfPCell SmoFheader = new PdfPCell();
			SmoFheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			SmoFheader.setBorderWidth(2);
			SmoFheader.setPhrase(new Phrase("Smoker Flag", headFont));
			Poltable.addCell(SmoFheader);

			PdfPCell LifeA = new PdfPCell(new Phrase(policy.getAnbAtCcd().toString()));
			LifeA.setPaddingLeft(4);
			LifeA.setVerticalAlignment(Element.ALIGN_MIDDLE);
			LifeA.setHorizontalAlignment(Element.ALIGN_CENTER);
			LifeA.setPaddingRight(4);
			LifeA.setBorderWidth(2);
			Poltable.addCell(LifeA);

			PdfPCell PolStat = new PdfPCell(new Phrase(policy.getStatCode()));
			PolStat.setPaddingLeft(4);
			PolStat.setVerticalAlignment(Element.ALIGN_MIDDLE);
			PolStat.setHorizontalAlignment(Element.ALIGN_CENTER);
			PolStat.setPaddingRight(4);
			PolStat.setBorderWidth(2);
			Poltable.addCell(PolStat);

			PdfPCell MedF = new PdfPCell(new Phrase(policy.getMedicalFlag()));
			MedF.setPaddingLeft(4);
			MedF.setVerticalAlignment(Element.ALIGN_MIDDLE);
			MedF.setHorizontalAlignment(Element.ALIGN_CENTER);
			MedF.setPaddingRight(4);
			MedF.setBorderWidth(2);
			Poltable.addCell(MedF);

			PdfPCell SmoF = new PdfPCell(new Phrase(policy.getSmokerFlag()));
			SmoF.setPaddingLeft(4);
			SmoF.setVerticalAlignment(Element.ALIGN_MIDDLE);
			SmoF.setHorizontalAlignment(Element.ALIGN_CENTER);
			SmoF.setPaddingRight(4);
			SmoF.setBorderWidth(2);
			Poltable.addCell(SmoF);

			document.add(Poltable);

//			document.add(Chunk.NEWLINE);
			document.add(new Paragraph("Cover Details", font2));
			document.add(Chunk.NEWLINE);
			PdfPTable table2 = new PdfPTable(10);
			Stream.of("Plan Name", "Cover Code", "UIN No.", "Risk Commencement Date", "Commencement Date",
					"Sum Assured", "Risk Cess. Term", "Premium Cess. Term", "Cover Premium", "Cover Status")
					.forEach(headerTitle -> {
						PdfPCell header2 = new PdfPCell();
						header2.setHorizontalAlignment(10);
//			header2.setBackgroundColor(BaseColor.BLUE);
						header2.setHorizontalAlignment(Element.ALIGN_CENTER);
						header2.setBorderWidth(2);
						header2.setPhrase(new Phrase(headerTitle, headFont));
						table2.addCell(header2);
					});

			for (CoverDetailsPas cover : covers) {

				String tdate = cover.getRiskComDate();
				String cdate = cover.getDocDate();
				SimpleDateFormat older = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat newer = new SimpleDateFormat("dd/MM/yyyy");
				String trad = newer.format(older.parse(tdate));
				String cdoc = newer.format(older.parse(cdate));

				PdfPCell Plan = new PdfPCell(new Phrase(cover.getCntType()));
				Plan.setPaddingLeft(4);
				Plan.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Plan.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(Plan);

				PdfPCell Code = new PdfPCell(new Phrase(cover.getCrTable()));
				Code.setPaddingLeft(4);
				Code.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Code.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(Code);

				PdfPCell Uin = new PdfPCell(new Phrase(cover.getUinNumber()));
				Uin.setPaddingLeft(4);
				Uin.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Uin.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(Uin);

				PdfPCell Rd = new PdfPCell(new Phrase(trad));
				Rd.setPaddingLeft(4);
				Rd.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Rd.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(Rd);

				PdfPCell Cd = new PdfPCell(new Phrase(cdoc));
				Cd.setPaddingLeft(4);
				Cd.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Cd.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(Plan);

				PdfPCell Sum = new PdfPCell(new Phrase(cover.getSumAssured().toString()));
				Sum.setPaddingLeft(4);
				Sum.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Sum.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(Sum);

				PdfPCell Rct = new PdfPCell(new Phrase(cover.getRiskCessTerm().toString()));
				Rct.setPaddingLeft(4);
				Rct.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Rct.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(Rct);

				PdfPCell Pct = new PdfPCell(new Phrase(cover.getPremCessTerm().toString()));
				Pct.setPaddingLeft(4);
				Pct.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Pct.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(Pct);

				PdfPCell Cpr = new PdfPCell(new Phrase(cover.getCoverPremium().toString()));
				Cpr.setPaddingLeft(4);
				Cpr.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Cpr.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(Cpr);

				PdfPCell Cst = new PdfPCell(new Phrase(cover.getCoverStatus()));
				Cst.setPaddingLeft(4);
				Cst.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Cst.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(Cst);
			}

			document.add(table2);

//			document.add(Chunk.NEXTPAGE);
			document.add(new Paragraph("PAS Details", font2));
			document.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);

			PdfPCell ReqDateheader = new PdfPCell();
			ReqDateheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			ReqDateheader.setBorderWidth(2);
			ReqDateheader.setPhrase(new Phrase("Req Date", headFont));
			table.addCell(ReqDateheader);

			PdfPCell LogDateheader = new PdfPCell();
			LogDateheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			LogDateheader.setBorderWidth(2);
			LogDateheader.setPhrase(new Phrase("Log Date", headFont));
			table.addCell(LogDateheader);

			PdfPCell AppDateheader = new PdfPCell();
			AppDateheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			AppDateheader.setBorderWidth(2);
			AppDateheader.setPhrase(new Phrase("Approval Date", headFont));
			table.addCell(AppDateheader);

			PdfPCell Refundheader = new PdfPCell();
			Refundheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Refundheader.setBorderWidth(2);
			Refundheader.setPhrase(new Phrase("Premium Refund", headFont));
			table.addCell(Refundheader);

			PdfPCell ReqDate = new PdfPCell(new Phrase(reqdate));
			ReqDate.setPaddingLeft(4);
			ReqDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
			ReqDate.setHorizontalAlignment(Element.ALIGN_CENTER);
			ReqDate.setPaddingRight(4);
			ReqDate.setBorderWidth(2);
			table.addCell(ReqDate);

			PdfPCell LogDate = new PdfPCell(new Phrase(logdate));
			LogDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
			LogDate.setHorizontalAlignment(Element.ALIGN_CENTER);
			LogDate.setPaddingLeft(4);
			LogDate.setBorderWidth(2);
			table.addCell(LogDate);

			PdfPCell AppDate = new PdfPCell(new Phrase(approvDate));
			AppDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
			AppDate.setHorizontalAlignment(Element.ALIGN_CENTER);
			AppDate.setPaddingLeft(4);
			AppDate.setBorderWidth(2);
			table.addCell(AppDate);

			PdfPCell Refund = new PdfPCell(new Phrase(trans.getFlcPremRefund().toString()));
			Refund.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Refund.setHorizontalAlignment(Element.ALIGN_CENTER);
			Refund.setPaddingLeft(4);
			Refund.setBorderWidth(2);
			table.addCell(Refund);

			PdfPCell Depositheader = new PdfPCell();
			Depositheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Depositheader.setBorderWidth(2);
			Depositheader.setPhrase(new Phrase("Policy Deposit", headFont));
			table.addCell(Depositheader);

			PdfPCell Penalheader = new PdfPCell();
			Penalheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Penalheader.setBorderWidth(2);
			Penalheader.setPhrase(new Phrase("Penal Interest", headFont));
			table.addCell(Penalheader);

			PdfPCell Grossheader = new PdfPCell();
			Grossheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Grossheader.setBorderWidth(2);
			Grossheader.setPhrase(new Phrase("Gross Pay", headFont));
			table.addCell(Grossheader);

			PdfPCell MedicalFeeheader = new PdfPCell();
			MedicalFeeheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			MedicalFeeheader.setBorderWidth(2);
			MedicalFeeheader.setPhrase(new Phrase("Medical Fee", headFont));
			table.addCell(MedicalFeeheader);

			PdfPCell Deposit = new PdfPCell(new Phrase(trans.getFlcPolicyDop().toString()));
			Deposit.setPaddingLeft(4);
			Deposit.setBorderWidth(2);
			Deposit.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Deposit.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(Deposit);

			PdfPCell Penal = new PdfPCell(new Phrase(trans.getPenalIntrest().toString()));
			Penal.setPaddingLeft(4);
			Deposit.setBorderWidth(2);
			Penal.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Penal.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(Penal);

			PdfPCell Gross = new PdfPCell(new Phrase(trans.getGrossFlcPay().toString()));
			Gross.setPaddingLeft(4);
			Deposit.setBorderWidth(2);
			Gross.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Gross.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(Gross);

			PdfPCell MedicalFee = new PdfPCell(new Phrase(trans.getMedicalFee().toString()));
			MedicalFee.setPaddingLeft(4);
			Deposit.setBorderWidth(2);
			MedicalFee.setVerticalAlignment(Element.ALIGN_MIDDLE);
			MedicalFee.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(MedicalFee);

			PdfPCell Stampheader = new PdfPCell();
			Stampheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Stampheader.setBorderWidth(2);
			Stampheader.setPhrase(new Phrase("Stamp Duty", headFont));
			table.addCell(Stampheader);

			PdfPCell Riskheader = new PdfPCell();
			Riskheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Riskheader.setBorderWidth(2);
			Riskheader.setPhrase(new Phrase("Risk Premium", headFont));
			table.addCell(Riskheader);

			PdfPCell Recovheader = new PdfPCell();
			Recovheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Recovheader.setBorderWidth(2);
			Recovheader.setPhrase(new Phrase("Total Recovery", headFont));
			table.addCell(Recovheader);

			PdfPCell Netheader = new PdfPCell();
			Netheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Netheader.setBorderWidth(2);
			Netheader.setPhrase(new Phrase("Net Pay", headFont));
			table.addCell(Netheader);

			PdfPCell Stamp = new PdfPCell(new Phrase(trans.getStamDuty().toString()));
			Stamp.setPaddingLeft(4);
			Stamp.setBorderWidth(2);
			Stamp.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Stamp.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(Stamp);

			PdfPCell Risk = new PdfPCell(new Phrase(trans.getRiskPremRecov().toString()));
			Risk.setPaddingLeft(4);
			Risk.setBorderWidth(2);
			Risk.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Risk.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(Risk);

			PdfPCell Recov = new PdfPCell(new Phrase(trans.getTotalRecov().toString()));
			Recov.setPaddingLeft(4);
			Recov.setBorderWidth(2);
			Recov.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Recov.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(Recov);

			PdfPCell Net = new PdfPCell(new Phrase(trans.getNetFlcPay().toString()));
			Net.setPaddingLeft(5);
			Net.setBorderWidth(2);
			Net.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Net.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(Net);

//			PdfPCell MedicalCatheader = new PdfPCell();
//				MedicalCatheader.setHorizontalAlignment(Element.ALIGN_CENTER);
//				MedicalCatheader.setBorderWidth(2);
//				MedicalCatheader.setPhrase(new Phrase("Medical Category", headFont));
//				table.addCell(MedicalCatheader);
//				
//			PdfPCell MedicalCat = new PdfPCell(new Phrase
//			            (trans.getMedicalCategory()));
//				MedicalCat.setPaddingLeft(4);
//				MedicalCat.setVerticalAlignment(Element.ALIGN_MIDDLE);
//				MedicalCat.setHorizontalAlignment(Element.ALIGN_CENTER);
//				MedicalCat.setColspan(5);
//				table.addCell(MedicalCat);
//				
//			PdfPCell MedicalCentheader = new PdfPCell();
//				MedicalCentheader.setHorizontalAlignment(Element.ALIGN_CENTER);
//				MedicalCentheader.setBorderWidth(2);
//				MedicalCentheader.setPhrase(new Phrase("Medical Centre", headFont));
//				table.addCell(MedicalCentheader);
//				
//			PdfPCell MedicalCent = new PdfPCell(new Phrase
//			            (trans.getMedicalCenter()));
//				MedicalCent.setPaddingLeft(4);
//				MedicalCent.setVerticalAlignment(Element.ALIGN_MIDDLE);
//				MedicalCent.setHorizontalAlignment(Element.ALIGN_CENTER);
//				MedicalCent.setColspan(5);
//				table.addCell(MedicalCent);
//				
//			PdfPCell MedicalTpaheader = new PdfPCell();
//				MedicalTpaheader.setHorizontalAlignment(Element.ALIGN_CENTER);
//				MedicalTpaheader.setBorderWidth(2);
//				MedicalTpaheader.setPhrase(new Phrase("Medical TPA", headFont));
//				table.addCell(MedicalTpaheader);
//				
//			PdfPCell MedicalTpa = new PdfPCell(new Phrase
//			            (trans.getMedicatTpaCode()));
//				MedicalTpa.setPaddingLeft(4);
//				MedicalTpa.setVerticalAlignment(Element.ALIGN_MIDDLE);
//				MedicalTpa.setHorizontalAlignment(Element.ALIGN_CENTER);
//				MedicalTpa.setColspan(5);
//				table.addCell(MedicalTpa);

			document.add(table);

			document.add(Chunk.NEXTPAGE);
//				document.add(Chunk.NEWLINE);
			document.add(new Paragraph("LEAPS Details", font2));
			document.add(Chunk.NEWLINE);
			PdfPTable table1 = new PdfPTable(4);

			PdfPCell TranDateheader = new PdfPCell();
			TranDateheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			TranDateheader.setBorderWidth(2);
			TranDateheader.setPhrase(new Phrase("Transaction Date", headFont));
			table1.addCell(TranDateheader);

			PdfPCell Contractheader = new PdfPCell();
			Contractheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Contractheader.setBorderWidth(2);
			Contractheader.setPhrase(new Phrase("Policy No.", headFont));
			table1.addCell(Contractheader);

			PdfPCell Transactionheader = new PdfPCell();
			Transactionheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Transactionheader.setBorderWidth(2);
			Transactionheader.setPhrase(new Phrase("Transaction No.", headFont));
			table1.addCell(Transactionheader);

			PdfPCell PreRefheader = new PdfPCell();
			PreRefheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			PreRefheader.setBorderWidth(2);
			PreRefheader.setPhrase(new Phrase("Premium Refund", headFont));
			table1.addCell(PreRefheader);

			PdfPCell TranDate = new PdfPCell(new Phrase(transDate));
			TranDate.setPaddingLeft(4);
			TranDate.setBorderWidth(2);
			TranDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
			TranDate.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(TranDate);

			PdfPCell Contract = new PdfPCell(new Phrase(purple.getPolicyNo().toString()));
			Contract.setPaddingLeft(4);
			Contract.setBorderWidth(2);
			Contract.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Contract.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(Contract);

			PdfPCell Transaction = new PdfPCell(new Phrase(purple.getTranNo().toString()));
			Transaction.setPaddingLeft(4);
			Transaction.setBorderWidth(2);
			Transaction.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Transaction.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(Transaction);

			PdfPCell PreRef = new PdfPCell(new Phrase(purple.getTotlPremium().toString()));
			PreRef.setPaddingLeft(4);
			PreRef.setBorderWidth(2);
			PreRef.setVerticalAlignment(Element.ALIGN_MIDDLE);
			PreRef.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(PreRef);

			PdfPCell PolDepheader = new PdfPCell();
			PolDepheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			PolDepheader.setBorderWidth(2);
			PolDepheader.setPhrase(new Phrase("Policy Deposit", headFont));
			table1.addCell(PolDepheader);

			PdfPCell PenIntheader = new PdfPCell();
			PenIntheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			PenIntheader.setBorderWidth(2);
			PenIntheader.setPhrase(new Phrase("Penal Interest", headFont));
			table1.addCell(PenIntheader);

			PdfPCell GroPayheader = new PdfPCell();
			GroPayheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			GroPayheader.setBorderWidth(2);
			GroPayheader.setPhrase(new Phrase("Gross Pay", headFont));
			table1.addCell(GroPayheader);

			PdfPCell MFeeheader = new PdfPCell();
			MFeeheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			MFeeheader.setBorderWidth(2);
			MFeeheader.setPhrase(new Phrase("Medical Fee", headFont));
			table1.addCell(MFeeheader);

			PdfPCell PolDep = new PdfPCell(new Phrase(purple.getAvalSuspense().toString()));
			PolDep.setPaddingLeft(4);
			PolDep.setBorderWidth(2);
			PolDep.setVerticalAlignment(Element.ALIGN_MIDDLE);
			PolDep.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(PolDep);

			PdfPCell PenInt = new PdfPCell(new Phrase(purple.getPenalInterest().toString()));
			PenInt.setPaddingLeft(4);
			PenInt.setBorderWidth(2);
			PenInt.setVerticalAlignment(Element.ALIGN_MIDDLE);
			PenInt.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(PenInt);

			PdfPCell GroPay = new PdfPCell(new Phrase(purple.getGrossPayable().toString()));
			GroPay.setPaddingLeft(4);
			GroPay.setBorderWidth(2);
			GroPay.setVerticalAlignment(Element.ALIGN_MIDDLE);
			GroPay.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(GroPay);

			PdfPCell MFee = new PdfPCell(new Phrase(purple.getPenalInterest().toString()));
			MFee.setPaddingLeft(4);
			MFee.setBorderWidth(2);
			MFee.setVerticalAlignment(Element.ALIGN_MIDDLE);
			MFee.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(MFee);

			PdfPCell Dutyheader = new PdfPCell();
			Dutyheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Dutyheader.setBorderWidth(2);
			Dutyheader.setPhrase(new Phrase("Stamp Duty", headFont));
			table1.addCell(Dutyheader);

			PdfPCell Mortheader = new PdfPCell();
			Mortheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Mortheader.setBorderWidth(2);
			Mortheader.setPhrase(new Phrase("Risk Premium", headFont));
			table1.addCell(Mortheader);

			PdfPCell Recheader = new PdfPCell();
			Recheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Recheader.setBorderWidth(2);
			Recheader.setPhrase(new Phrase("Recoveries", headFont));
			table1.addCell(Recheader);

			PdfPCell NPayheader = new PdfPCell();
			NPayheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			NPayheader.setBorderWidth(2);
			NPayheader.setPhrase(new Phrase("Net Pay", headFont));
			table1.addCell(NPayheader);

			PdfPCell Duty = new PdfPCell(new Phrase(purple.getStampDuty().toString()));
			Duty.setPaddingLeft(4);
			Duty.setBorderWidth(2);
			Duty.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Duty.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(Duty);

			PdfPCell Mort = new PdfPCell(new Phrase(purple.getMortCharge().toString()));
			Mort.setPaddingLeft(4);
			Mort.setBorderWidth(2);
			Mort.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Mort.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(Mort);

			PdfPCell Rec = new PdfPCell(new Phrase(purple.getRecoveries().toString()));
			Rec.setPaddingLeft(4);
			Rec.setBorderWidth(2);
			Rec.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Rec.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(Rec);

			PdfPCell NPay = new PdfPCell(new Phrase(purple.getMortCharge().toString()));
			NPay.setPaddingLeft(4);
			NPay.setBorderWidth(2);
			NPay.setVerticalAlignment(Element.ALIGN_MIDDLE);
			NPay.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(NPay);

			PdfPCell Pfheader = new PdfPCell();
			Pfheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Pfheader.setBorderWidth(2);
			Pfheader.setPhrase(new Phrase("Pass/Fail", headFont));
			table1.addCell(Pfheader);

			PdfPCell PfRemheader = new PdfPCell();
			PfRemheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			PfRemheader.setBorderWidth(2);
			PfRemheader.setPhrase(new Phrase("Pass/Fail Remarks", headFont));
			table1.addCell(PfRemheader);

			PdfPCell Appheader = new PdfPCell();
			Appheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Appheader.setBorderWidth(2);
			Appheader.setPhrase(new Phrase("Approval", headFont));
			table1.addCell(Appheader);

			PdfPCell AppRemheader = new PdfPCell();
			AppRemheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			AppRemheader.setBorderWidth(2);
			AppRemheader.setPhrase(new Phrase("Approval Remarks", headFont));
			table1.addCell(AppRemheader);

			PdfPCell Pf = new PdfPCell(new Phrase(purple.getPfFlag()));
			Pf.setPaddingLeft(4);
			Pf.setBorderWidth(2);
			Pf.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Pf.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(Pf);

			PdfPCell PfRem = new PdfPCell(new Phrase(purple.getPfRemarks()));
			PfRem.setPaddingLeft(4);
			PfRem.setBorderWidth(2);
			PfRem.setVerticalAlignment(Element.ALIGN_MIDDLE);
			PfRem.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(PfRem);

			PdfPCell App = new PdfPCell(new Phrase(purple.getApprovFlag()));
			App.setPaddingLeft(4);
			App.setBorderWidth(2);
			App.setVerticalAlignment(Element.ALIGN_MIDDLE);
			App.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(App);

			PdfPCell AppRem = new PdfPCell(new Phrase(purple.getApprovRemarks()));
			AppRem.setPaddingLeft(4);
			AppRem.setBorderWidth(2);
			AppRem.setVerticalAlignment(Element.ALIGN_MIDDLE);
			AppRem.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(AppRem);

			PdfPCell PAppDateheader = new PdfPCell();
			PAppDateheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			PAppDateheader.setBorderWidth(2);
			PAppDateheader.setPhrase(new Phrase("Approval Date", headFont));
			table1.addCell(PAppDateheader);

			PdfPCell pfFlagUpdateeader = new PdfPCell();
			PreRefheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			PreRefheader.setBorderWidth(2);
			PreRefheader.setPhrase(new Phrase("Premium Refund", headFont));
			table1.addCell(PreRefheader);

			PdfPCell a = new PdfPCell();
			PreRefheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			PreRefheader.setBorderWidth(2);
			PreRefheader.setPhrase(new Phrase("Premium Refund", headFont));
			table1.addCell(PreRefheader);

			PdfPCell b = new PdfPCell();
			PreRefheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			PreRefheader.setBorderWidth(2);
			PreRefheader.setPhrase(new Phrase("Premium Refund", headFont));
			table1.addCell(PreRefheader);

			PdfPCell PAppDate = new PdfPCell(new Phrase(papprovDate));
			PAppDate.setPaddingLeft(4);
			PAppDate.setBorderWidth(2);
			PAppDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
			PAppDate.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(PAppDate);
			document.add(table1);

			document.close();

		} catch (DocumentException e) {
			logger.error(e.toString());
		}
		return new ByteArrayInputStream(out.toByteArray());
	}

	public static ByteArrayInputStream Ulip(PolicyDetailsPas policy, TransactionPas trans, List<CoverDetailsPas> covers,
			List<FundDetailsPas> funds, PurpleDetails purple, String companyName, BufferedImage i)
			throws MalformedURLException, IOException, ParseException {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		String prem = policy.getPremToDate();
		String req = trans.getFlcReqDate();
		String log = trans.getFlcLogDate();
		String approv = trans.getFlcApprovalDate();
		String papprov = purple.getApprovDate();
		String effd1 = trans.getEffDate();
		String effd2 = purple.getEffDate();
		String transd = purple.getTranDate();
		SimpleDateFormat fromUser = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");

		String premDate = myFormat.format(fromUser.parse(prem));
		String reqdate = myFormat.format(fromUser.parse(req));
		String logdate = myFormat.format(fromUser.parse(log));
		String approvDate = myFormat.format(fromUser.parse(approv));
		String transDate = myFormat.format(fromUser.parse(transd));
		String effDate1 = myFormat.format(fromUser.parse(effd1));
		String effDate2 = myFormat.format(fromUser.parse(effd2));
		String papprovDate = myFormat.format(fromUser.parse(papprov));
//		    System.out.println("Perm "+premDate);

		try {

			PdfWriter.getInstance(document, out);
			document.open();

			Font font1 = FontFactory.getFont(FontFactory.COURIER, 48, BaseColor.BLACK);
			Font font2 = FontFactory.getFont(FontFactory.COURIER, 18, BaseColor.BLACK);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(i, "png", baos);
			Image image1 = Image.getInstance(baos.toByteArray());

			image1.setAbsolutePosition(40f, 700f);

			image1.scaleAbsolute(80, 80);

			document.add(image1);

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			Paragraph para = new Paragraph(" " + companyName, font1);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			document.add(new Paragraph("Policy Details", font2));
			document.add(Chunk.NEWLINE);

			PdfPTable Poltable = new PdfPTable(4);

			PdfPCell PolNoheader = new PdfPCell();
			PolNoheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			PolNoheader.setBorderWidth(2);
			PolNoheader.setPhrase(new Phrase("Policy No.", headFont));
			Poltable.addCell(PolNoheader);

			PdfPCell BillFrheader = new PdfPCell();
			BillFrheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			BillFrheader.setBorderWidth(2);
			BillFrheader.setPhrase(new Phrase("Bill Frequency", headFont));
			Poltable.addCell(BillFrheader);

			PdfPCell InPheader = new PdfPCell();
			InPheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			InPheader.setBorderWidth(2);
			InPheader.setPhrase(new Phrase("Installment Premium", headFont));
			Poltable.addCell(InPheader);

			PdfPCell PreDateheader = new PdfPCell();
			PreDateheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			PreDateheader.setBorderWidth(2);
			PreDateheader.setPhrase(new Phrase("Premium Date", headFont));
			Poltable.addCell(PreDateheader);

			PdfPCell PolNo = new PdfPCell(new Phrase(policy.getChdrNum().toString()));
			PolNo.setPaddingLeft(4);
			PolNo.setVerticalAlignment(Element.ALIGN_MIDDLE);
			PolNo.setHorizontalAlignment(Element.ALIGN_CENTER);
			PolNo.setBorderWidth(2);
			PolNo.setPaddingRight(4);
			Poltable.addCell(PolNo);

			PdfPCell BillFr = new PdfPCell(new Phrase(policy.getBillFreq().toString()));
			BillFr.setPaddingLeft(4);
			BillFr.setVerticalAlignment(Element.ALIGN_MIDDLE);
			BillFr.setHorizontalAlignment(Element.ALIGN_CENTER);
			BillFr.setBorderWidth(2);
			BillFr.setPaddingRight(4);
			Poltable.addCell(BillFr);

			PdfPCell InP = new PdfPCell(new Phrase(policy.getInstallmentPremium().toString()));
			InP.setPaddingLeft(4);
			InP.setVerticalAlignment(Element.ALIGN_MIDDLE);
			InP.setHorizontalAlignment(Element.ALIGN_CENTER);
			InP.setBorderWidth(2);
			InP.setPaddingRight(4);
			Poltable.addCell(InP);

			PdfPCell PreDate = new PdfPCell(new Phrase(premDate));
			PreDate.setPaddingLeft(4);
			PreDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
			PreDate.setHorizontalAlignment(Element.ALIGN_CENTER);
			PreDate.setBorderWidth(2);
			PreDate.setPaddingRight(4);
			Poltable.addCell(PreDate);

			PdfPCell LifeAheader = new PdfPCell();
			LifeAheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			LifeAheader.setBorderWidth(2);
			LifeAheader.setPhrase(new Phrase("Life Assured Age", headFont));
			Poltable.addCell(LifeAheader);

			PdfPCell PolStatheader = new PdfPCell();
			PolStatheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			PolStatheader.setBorderWidth(2);
			PolStatheader.setPhrase(new Phrase("Policy Status", headFont));
			Poltable.addCell(PolStatheader);

			PdfPCell MedFheader = new PdfPCell();
			MedFheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			MedFheader.setBorderWidth(2);
			MedFheader.setPhrase(new Phrase("Medical Flag", headFont));
			Poltable.addCell(MedFheader);

			PdfPCell SmoFheader = new PdfPCell();
			SmoFheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			SmoFheader.setBorderWidth(2);
			SmoFheader.setPhrase(new Phrase("Smoker Flag", headFont));
			Poltable.addCell(SmoFheader);

			PdfPCell LifeA = new PdfPCell(new Phrase(policy.getAnbAtCcd().toString()));
			LifeA.setPaddingLeft(4);
			LifeA.setVerticalAlignment(Element.ALIGN_MIDDLE);
			LifeA.setHorizontalAlignment(Element.ALIGN_CENTER);
			LifeA.setPaddingRight(4);
			LifeA.setBorderWidth(2);
			Poltable.addCell(LifeA);

			PdfPCell PolStat = new PdfPCell(new Phrase(policy.getStatCode()));
			PolStat.setPaddingLeft(4);
			PolStat.setVerticalAlignment(Element.ALIGN_MIDDLE);
			PolStat.setHorizontalAlignment(Element.ALIGN_CENTER);
			PolStat.setPaddingRight(4);
			PolStat.setBorderWidth(2);
			Poltable.addCell(PolStat);

			PdfPCell MedF = new PdfPCell(new Phrase(policy.getMedicalFlag()));
			MedF.setPaddingLeft(4);
			MedF.setVerticalAlignment(Element.ALIGN_MIDDLE);
			MedF.setHorizontalAlignment(Element.ALIGN_CENTER);
			MedF.setPaddingRight(4);
			MedF.setBorderWidth(2);
			Poltable.addCell(MedF);

			PdfPCell SmoF = new PdfPCell(new Phrase(policy.getSmokerFlag()));
			SmoF.setPaddingLeft(4);
			SmoF.setVerticalAlignment(Element.ALIGN_MIDDLE);
			SmoF.setHorizontalAlignment(Element.ALIGN_CENTER);
			SmoF.setPaddingRight(4);
			SmoF.setBorderWidth(2);
			Poltable.addCell(SmoF);

			document.add(Poltable);

//			document.add(Chunk.NEWLINE);
			document.add(new Paragraph("Cover Details", font2));
			document.add(Chunk.NEWLINE);
			PdfPTable table2 = new PdfPTable(10);
			Stream.of("Plan Name", "Cover Code", "UIN No.", "Risk Commencement Date", "Commencement Date",
					"Sum Assured", "Risk Cess. Term", "Premium Cess. Term", "Cover Premium", "Cover Status")
					.forEach(headerTitle -> {
						PdfPCell header2 = new PdfPCell();
						header2.setHorizontalAlignment(10);
//			header2.setBackgroundColor(BaseColor.BLUE);
						header2.setHorizontalAlignment(Element.ALIGN_CENTER);
						header2.setBorderWidth(2);
						header2.setPhrase(new Phrase(headerTitle, headFont));
						table2.addCell(header2);
					});

			for (CoverDetailsPas cover : covers) {

				String tdate = cover.getRiskComDate();
				String cdate = cover.getDocDate();
				SimpleDateFormat older = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat newer = new SimpleDateFormat("dd/MM/yyyy");
				String trad = newer.format(older.parse(tdate));
				String cdoc = newer.format(older.parse(cdate));

				PdfPCell Plan = new PdfPCell(new Phrase(cover.getCntType()));
				Plan.setPaddingLeft(4);
				Plan.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Plan.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(Plan);

				PdfPCell Code = new PdfPCell(new Phrase(cover.getCrTable()));
				Code.setPaddingLeft(4);
				Code.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Code.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(Code);

				PdfPCell Uin = new PdfPCell(new Phrase(cover.getUinNumber()));
				Uin.setPaddingLeft(4);
				Uin.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Uin.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(Uin);

				PdfPCell Rd = new PdfPCell(new Phrase(trad));
				Rd.setPaddingLeft(4);
				Rd.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Rd.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(Rd);

				PdfPCell Cd = new PdfPCell(new Phrase(cdoc));
				Cd.setPaddingLeft(4);
				Cd.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Cd.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(Plan);

				PdfPCell Sum = new PdfPCell(new Phrase(cover.getSumAssured().toString()));
				Sum.setPaddingLeft(4);
				Sum.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Sum.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(Sum);

				PdfPCell Rct = new PdfPCell(new Phrase(cover.getRiskCessTerm().toString()));
				Rct.setPaddingLeft(4);
				Rct.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Rct.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(Rct);

				PdfPCell Pct = new PdfPCell(new Phrase(cover.getPremCessTerm().toString()));
				Pct.setPaddingLeft(4);
				Pct.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Pct.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(Pct);

				PdfPCell Cpr = new PdfPCell(new Phrase(cover.getCoverPremium().toString()));
				Cpr.setPaddingLeft(4);
				Cpr.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Cpr.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(Cpr);

				PdfPCell Cst = new PdfPCell(new Phrase(cover.getCoverStatus()));
				Cst.setPaddingLeft(4);
				Cst.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Cst.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(Cst);
			}

			document.add(table2);

			document.add(new Paragraph("Fund Details", font2));
			document.add(Chunk.NEWLINE);
			PdfPTable table3 = new PdfPTable(10);
			Stream.of("Fund Code", "Fund Name", "Effective Date", "Units", "Rate Applied", "Value")
					.forEach(headerTitle -> {
						PdfPCell header3 = new PdfPCell();
						header3.setHorizontalAlignment(10);
//					header2.setBackgroundColor(BaseColor.BLUE);
						header3.setHorizontalAlignment(Element.ALIGN_CENTER);
						header3.setBorderWidth(2);
						header3.setPhrase(new Phrase(headerTitle, headFont));
						table3.addCell(header3);
					});

			for (FundDetailsPas fund : funds) {

				String edate = fund.getNavDate();
				SimpleDateFormat older = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat newer = new SimpleDateFormat("dd/MM/yyyy");
				String trad = newer.format(older.parse(edate));

				PdfPCell FundC = new PdfPCell(new Phrase(fund.getFundCode()));
				FundC.setPaddingLeft(4);
				FundC.setVerticalAlignment(Element.ALIGN_MIDDLE);
				FundC.setHorizontalAlignment(Element.ALIGN_LEFT);
				table3.addCell(FundC);

				PdfPCell FundN = new PdfPCell(new Phrase(fund.getFundName()));
				FundN.setPaddingLeft(4);
				FundN.setVerticalAlignment(Element.ALIGN_MIDDLE);
				FundN.setHorizontalAlignment(Element.ALIGN_LEFT);
				table3.addCell(FundN);

				PdfPCell EffDate = new PdfPCell(new Phrase(trad));
				EffDate.setPaddingLeft(4);
				EffDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
				EffDate.setHorizontalAlignment(Element.ALIGN_LEFT);
				table3.addCell(EffDate);

				PdfPCell Units = new PdfPCell(new Phrase(fund.getUnits().toString()));
				Units.setPaddingLeft(4);
				Units.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Units.setHorizontalAlignment(Element.ALIGN_LEFT);
				table3.addCell(Units);

				PdfPCell RateApp = new PdfPCell(new Phrase(fund.getRateApp().toString()));
				RateApp.setPaddingLeft(4);
				RateApp.setVerticalAlignment(Element.ALIGN_MIDDLE);
				RateApp.setHorizontalAlignment(Element.ALIGN_LEFT);
				table3.addCell(RateApp);

				PdfPCell Value = new PdfPCell(new Phrase(fund.getValue().toString()));
				Value.setPaddingLeft(4);
				Value.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Value.setHorizontalAlignment(Element.ALIGN_LEFT);
				table3.addCell(Value);
			}

			document.add(table3);

//			document.add(Chunk.NEXTPAGE);
			document.add(new Paragraph("PAS Details", font2));
			document.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);

			PdfPCell ReqDateheader = new PdfPCell();
			ReqDateheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			ReqDateheader.setBorderWidth(2);
			ReqDateheader.setPhrase(new Phrase("Req Date", headFont));
			table.addCell(ReqDateheader);

			PdfPCell LogDateheader = new PdfPCell();
			LogDateheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			LogDateheader.setBorderWidth(2);
			LogDateheader.setPhrase(new Phrase("Log Date", headFont));
			table.addCell(LogDateheader);

			PdfPCell AppDateheader = new PdfPCell();
			AppDateheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			AppDateheader.setBorderWidth(2);
			AppDateheader.setPhrase(new Phrase("Approval Date", headFont));
			table.addCell(AppDateheader);

			PdfPCell Refundheader = new PdfPCell();
			Refundheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Refundheader.setBorderWidth(2);
			Refundheader.setPhrase(new Phrase("Total Premium", headFont));
			table.addCell(Refundheader);

			PdfPCell ReqDate = new PdfPCell(new Phrase(reqdate));
			ReqDate.setPaddingLeft(4);
			ReqDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
			ReqDate.setHorizontalAlignment(Element.ALIGN_CENTER);
			ReqDate.setPaddingRight(4);
			ReqDate.setBorderWidth(2);
			table.addCell(ReqDate);

			PdfPCell LogDate = new PdfPCell(new Phrase(logdate));
			LogDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
			LogDate.setHorizontalAlignment(Element.ALIGN_CENTER);
			LogDate.setPaddingLeft(4);
			LogDate.setBorderWidth(2);
			table.addCell(LogDate);

			PdfPCell AppDate = new PdfPCell(new Phrase(approvDate));
			AppDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
			AppDate.setHorizontalAlignment(Element.ALIGN_CENTER);
			AppDate.setPaddingLeft(4);
			AppDate.setBorderWidth(2);
			table.addCell(AppDate);

			PdfPCell Refund = new PdfPCell(new Phrase(trans.getFlcTotalPrem().toString()));
			Refund.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Refund.setHorizontalAlignment(Element.ALIGN_CENTER);
			Refund.setPaddingLeft(4);
			Refund.setBorderWidth(2);
			table.addCell(Refund);

			PdfPCell Depositheader = new PdfPCell();
			Depositheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Depositheader.setBorderWidth(2);
			Depositheader.setPhrase(new Phrase("Policy Deposit", headFont));
			table.addCell(Depositheader);

			PdfPCell Penalheader = new PdfPCell();
			Penalheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Penalheader.setBorderWidth(2);
			Penalheader.setPhrase(new Phrase("Penal Interest", headFont));
			table.addCell(Penalheader);

			PdfPCell Grossheader = new PdfPCell();
			Grossheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Grossheader.setBorderWidth(2);
			Grossheader.setPhrase(new Phrase("Gross Pay", headFont));
			table.addCell(Grossheader);

			PdfPCell MedicalFeeheader = new PdfPCell();
			MedicalFeeheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			MedicalFeeheader.setBorderWidth(2);
			MedicalFeeheader.setPhrase(new Phrase("Medical Fee", headFont));
			table.addCell(MedicalFeeheader);

			PdfPCell Deposit = new PdfPCell(new Phrase(trans.getFlcPolicyDop().toString()));
			Deposit.setPaddingLeft(4);
			Deposit.setBorderWidth(2);
			Deposit.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Deposit.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(Deposit);

			PdfPCell Penal = new PdfPCell(new Phrase(trans.getPenalIntrest().toString()));
			Penal.setPaddingLeft(4);
			Deposit.setBorderWidth(2);
			Penal.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Penal.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(Penal);

			PdfPCell Gross = new PdfPCell(new Phrase(trans.getGrossFlcPay().toString()));
			Gross.setPaddingLeft(4);
			Deposit.setBorderWidth(2);
			Gross.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Gross.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(Gross);

			PdfPCell MedicalFee = new PdfPCell(new Phrase(trans.getMedicalFee().toString()));
			MedicalFee.setPaddingLeft(4);
			Deposit.setBorderWidth(2);
			MedicalFee.setVerticalAlignment(Element.ALIGN_MIDDLE);
			MedicalFee.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(MedicalFee);

			PdfPCell Stampheader = new PdfPCell();
			Stampheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Stampheader.setBorderWidth(2);
			Stampheader.setPhrase(new Phrase("Stamp Duty", headFont));
			table.addCell(Stampheader);

			PdfPCell Riskheader = new PdfPCell();
			Riskheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Riskheader.setBorderWidth(2);
			Riskheader.setPhrase(new Phrase("Mortality Charge", headFont));
			table.addCell(Riskheader);

			PdfPCell Recovheader = new PdfPCell();
			Recovheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Recovheader.setBorderWidth(2);
			Recovheader.setPhrase(new Phrase("Total Recovery", headFont));
			table.addCell(Recovheader);

			PdfPCell Netheader = new PdfPCell();
			Netheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Netheader.setBorderWidth(2);
			Netheader.setPhrase(new Phrase("Net Pay", headFont));
			table.addCell(Netheader);

			PdfPCell Stamp = new PdfPCell(new Phrase(trans.getStamDuty().toString()));
			Stamp.setPaddingLeft(4);
			Stamp.setBorderWidth(2);
			Stamp.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Stamp.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(Stamp);

			PdfPCell Risk = new PdfPCell(new Phrase(trans.getMortChargeRefund().toString()));
			Risk.setPaddingLeft(4);
			Risk.setBorderWidth(2);
			Risk.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Risk.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(Risk);

			PdfPCell Recov = new PdfPCell(new Phrase(trans.getTotalRecov().toString()));
			Recov.setPaddingLeft(4);
			Recov.setBorderWidth(2);
			Recov.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Recov.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(Recov);

			PdfPCell Net = new PdfPCell(new Phrase(trans.getNetFlcPay().toString()));
			Net.setPaddingLeft(5);
			Net.setBorderWidth(2);
			Net.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Net.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(Net);

			PdfPCell EffDateheader = new PdfPCell();
			EffDateheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			EffDateheader.setBorderWidth(2);
			EffDateheader.setPhrase(new Phrase("Effective Date", headFont));
			table.addCell(EffDateheader);

			PdfPCell Fundheader = new PdfPCell();
			Fundheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Fundheader.setBorderWidth(2);
			Fundheader.setPhrase(new Phrase("Fund Value", headFont));
			table.addCell(Fundheader);

			PdfPCell EffDate = new PdfPCell(new Phrase(effDate1));
			EffDate.setPaddingLeft(5);
			EffDate.setBorderWidth(2);
			EffDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
			EffDate.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(EffDate);

			PdfPCell Fund = new PdfPCell(new Phrase(trans.getFundValue().toString()));
			Fund.setPaddingLeft(5);
			Fund.setBorderWidth(2);
			Fund.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Fund.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(Fund);

			document.add(table);

			document.add(Chunk.NEXTPAGE);
//				document.add(Chunk.NEWLINE);
			document.add(new Paragraph("LEAPS Details", font2));
			document.add(Chunk.NEWLINE);
			PdfPTable table1 = new PdfPTable(4);

			PdfPCell TranDateheader = new PdfPCell();
			TranDateheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			TranDateheader.setBorderWidth(2);
			TranDateheader.setPhrase(new Phrase("Transaction Date", headFont));
			table1.addCell(TranDateheader);

			PdfPCell Contractheader = new PdfPCell();
			Contractheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Contractheader.setBorderWidth(2);
			Contractheader.setPhrase(new Phrase("Policy No.", headFont));
			table1.addCell(Contractheader);

			PdfPCell Transactionheader = new PdfPCell();
			Transactionheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Transactionheader.setBorderWidth(2);
			Transactionheader.setPhrase(new Phrase("Transaction No.", headFont));
			table1.addCell(Transactionheader);

			PdfPCell PreRefheader = new PdfPCell();
			PreRefheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			PreRefheader.setBorderWidth(2);
			PreRefheader.setPhrase(new Phrase("Total Premium", headFont));
			table1.addCell(PreRefheader);

			PdfPCell TranDate = new PdfPCell(new Phrase(transDate));
			TranDate.setPaddingLeft(4);
			TranDate.setBorderWidth(2);
			TranDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
			TranDate.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(TranDate);

			PdfPCell Contract = new PdfPCell(new Phrase(purple.getPolicyNo().toString()));
			Contract.setPaddingLeft(4);
			Contract.setBorderWidth(2);
			Contract.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Contract.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(Contract);

			PdfPCell Transaction = new PdfPCell(new Phrase(purple.getTranNo().toString()));
			Transaction.setPaddingLeft(4);
			Transaction.setBorderWidth(2);
			Transaction.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Transaction.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(Transaction);

			PdfPCell PreRef = new PdfPCell(new Phrase(purple.getTotlPremium().toString()));
			PreRef.setPaddingLeft(4);
			PreRef.setBorderWidth(2);
			PreRef.setVerticalAlignment(Element.ALIGN_MIDDLE);
			PreRef.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(PreRef);

			PdfPCell PolDepheader = new PdfPCell();
			PolDepheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			PolDepheader.setBorderWidth(2);
			PolDepheader.setPhrase(new Phrase("Policy Deposit", headFont));
			table1.addCell(PolDepheader);

			PdfPCell PenIntheader = new PdfPCell();
			PenIntheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			PenIntheader.setBorderWidth(2);
			PenIntheader.setPhrase(new Phrase("Penal Interest", headFont));
			table1.addCell(PenIntheader);

			PdfPCell GroPayheader = new PdfPCell();
			GroPayheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			GroPayheader.setBorderWidth(2);
			GroPayheader.setPhrase(new Phrase("Gross Pay", headFont));
			table1.addCell(GroPayheader);

			PdfPCell MFeeheader = new PdfPCell();
			MFeeheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			MFeeheader.setBorderWidth(2);
			MFeeheader.setPhrase(new Phrase("Medical Fee", headFont));
			table1.addCell(MFeeheader);

			PdfPCell PolDep = new PdfPCell(new Phrase(purple.getAvalSuspense().toString()));
			PolDep.setPaddingLeft(4);
			PolDep.setBorderWidth(2);
			PolDep.setVerticalAlignment(Element.ALIGN_MIDDLE);
			PolDep.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(PolDep);

			PdfPCell PenInt = new PdfPCell(new Phrase(purple.getPenalInterest().toString()));
			PenInt.setPaddingLeft(4);
			PenInt.setBorderWidth(2);
			PenInt.setVerticalAlignment(Element.ALIGN_MIDDLE);
			PenInt.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(PenInt);

			PdfPCell GroPay = new PdfPCell(new Phrase(purple.getGrossPayable().toString()));
			GroPay.setPaddingLeft(4);
			GroPay.setBorderWidth(2);
			GroPay.setVerticalAlignment(Element.ALIGN_MIDDLE);
			GroPay.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(GroPay);

			PdfPCell MFee = new PdfPCell(new Phrase(purple.getPenalInterest().toString()));
			MFee.setPaddingLeft(4);
			MFee.setBorderWidth(2);
			MFee.setVerticalAlignment(Element.ALIGN_MIDDLE);
			MFee.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(MFee);

			PdfPCell Dutyheader = new PdfPCell();
			Dutyheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Dutyheader.setBorderWidth(2);
			Dutyheader.setPhrase(new Phrase("Stamp Duty", headFont));
			table1.addCell(Dutyheader);

			PdfPCell Mortheader = new PdfPCell();
			Mortheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Mortheader.setBorderWidth(2);
			Mortheader.setPhrase(new Phrase("Mortality Charges", headFont));
			table1.addCell(Mortheader);

			PdfPCell Recheader = new PdfPCell();
			Recheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Recheader.setBorderWidth(2);
			Recheader.setPhrase(new Phrase("Recoveries", headFont));
			table1.addCell(Recheader);

			PdfPCell NPayheader = new PdfPCell();
			NPayheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			NPayheader.setBorderWidth(2);
			NPayheader.setPhrase(new Phrase("Net Pay", headFont));
			table1.addCell(NPayheader);

			PdfPCell Duty = new PdfPCell(new Phrase(purple.getStampDuty().toString()));
			Duty.setPaddingLeft(4);
			Duty.setBorderWidth(2);
			Duty.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Duty.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(Duty);

			PdfPCell Mort = new PdfPCell(new Phrase(purple.getMortCharge().toString()));
			Mort.setPaddingLeft(4);
			Mort.setBorderWidth(2);
			Mort.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Mort.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(Mort);

			PdfPCell Rec = new PdfPCell(new Phrase(purple.getRecoveries().toString()));
			Rec.setPaddingLeft(4);
			Rec.setBorderWidth(2);
			Rec.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Rec.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(Rec);

			PdfPCell NPay = new PdfPCell(new Phrase(purple.getMortCharge().toString()));
			NPay.setPaddingLeft(4);
			NPay.setBorderWidth(2);
			NPay.setVerticalAlignment(Element.ALIGN_MIDDLE);
			NPay.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(NPay);

			PdfPCell Edateheader = new PdfPCell();
			Edateheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Edateheader.setBorderWidth(2);
			Edateheader.setPhrase(new Phrase("Effective Date", headFont));
			table1.addCell(Edateheader);

			PdfPCell FunValueheader = new PdfPCell();
			FunValueheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			FunValueheader.setBorderWidth(2);
			FunValueheader.setPhrase(new Phrase("Fund Value", headFont));
			table1.addCell(FunValueheader);

			PdfPCell Pfheader = new PdfPCell();
			Pfheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Pfheader.setBorderWidth(2);
			Pfheader.setPhrase(new Phrase("Pass/Fail", headFont));
			table1.addCell(Pfheader);

			PdfPCell PfRemheader = new PdfPCell();
			PfRemheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			PfRemheader.setBorderWidth(2);
			PfRemheader.setPhrase(new Phrase("Pass/Fail Remarks", headFont));
			table1.addCell(PfRemheader);

			PdfPCell Edate = new PdfPCell(new Phrase(effDate2));
			Edate.setPaddingLeft(4);
			Edate.setBorderWidth(2);
			Edate.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Edate.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(Edate);

			PdfPCell FunValue = new PdfPCell(new Phrase(purple.getFundValue().toString()));
			FunValue.setPaddingLeft(4);
			FunValue.setBorderWidth(2);
			FunValue.setVerticalAlignment(Element.ALIGN_MIDDLE);
			FunValue.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(FunValue);

			PdfPCell Pf = new PdfPCell(new Phrase(purple.getPfFlag()));
			Pf.setPaddingLeft(4);
			Pf.setBorderWidth(2);
			Pf.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Pf.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(Pf);

			PdfPCell PfRem = new PdfPCell(new Phrase(purple.getPfRemarks()));
			PfRem.setPaddingLeft(4);
			PfRem.setBorderWidth(2);
			PfRem.setVerticalAlignment(Element.ALIGN_MIDDLE);
			PfRem.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(PfRem);

			PdfPCell Appheader = new PdfPCell();
			Appheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			Appheader.setBorderWidth(2);
			Appheader.setPhrase(new Phrase("Approval", headFont));
			table1.addCell(Appheader);

			PdfPCell AppRemheader = new PdfPCell();
			AppRemheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			AppRemheader.setBorderWidth(2);
			AppRemheader.setPhrase(new Phrase("Approval Remarks", headFont));
			table1.addCell(AppRemheader);

			PdfPCell App = new PdfPCell(new Phrase(purple.getApprovFlag()));
			App.setPaddingLeft(4);
			App.setBorderWidth(2);
			App.setVerticalAlignment(Element.ALIGN_MIDDLE);
			App.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(App);

			PdfPCell AppRem = new PdfPCell(new Phrase(purple.getApprovRemarks()));
			AppRem.setPaddingLeft(4);
			AppRem.setBorderWidth(2);
			AppRem.setVerticalAlignment(Element.ALIGN_MIDDLE);
			AppRem.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(AppRem);

			PdfPCell PAppDateheader = new PdfPCell();
			PAppDateheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			PAppDateheader.setBorderWidth(2);
			PAppDateheader.setPhrase(new Phrase("Approval Date", headFont));
			table1.addCell(PAppDateheader);

			PdfPCell PAppDate = new PdfPCell(new Phrase(papprovDate));
			PAppDate.setPaddingLeft(4);
			PAppDate.setBorderWidth(2);
			PAppDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
			PAppDate.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(PAppDate);

			document.add(table1);

			document.close();

		} catch (DocumentException e) {
			logger.error(e.toString());
		}
		return new ByteArrayInputStream(out.toByteArray());
	}

}
