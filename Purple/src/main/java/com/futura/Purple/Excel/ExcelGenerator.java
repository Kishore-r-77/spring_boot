package com.futura.Purple.Excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.futura.Purple.Entity.PurpleDetails;
import com.futura.Purple.Entity.TransactionPas;

public class ExcelGenerator {

	public static ByteArrayInputStream purpleToExcel(List<PurpleDetails> purples) throws IOException, ParseException {
		String[] columns = { "Transaction Date", "Policy No.", "Transaction No.", "UIN Number", "Premium Refund",
				"Total Premium", "Policy Deposit", "Penal Interest", "Gross Pay", "Medical Fee", "Stamp Duty",
				"Risk Premium Recovery", "Mortality Charge Refund", "Recoveries", "Net Pay", "Effective Date",
				"Fund Value", "Pass/Fail", "Pass/Fail Remarks", "Aprroval", "Aprroval Remarks" };
		SimpleDateFormat fromUser = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet("IPCA");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header-->
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(columns[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (PurpleDetails purple : purples) {
				if (purple.getUinNumber().contains("N")) {
					String trandate = purple.getTranDate();
					String tdate = myFormat.format(fromUser.parse(trandate));
					Row row = sheet.createRow(rowIdx++);

					row.createCell(0).setCellValue(tdate);
					row.createCell(1).setCellValue(purple.getPolicyNo().toString());
					row.createCell(2).setCellValue(purple.getTranNo().toString());
					row.createCell(3).setCellValue(purple.getUinNumber());
					row.createCell(4).setCellValue(purple.getTotlPremium().toString());
					row.createCell(5).setCellValue("NA");
					row.createCell(6).setCellValue(purple.getAvalSuspense().toString());
					row.createCell(7).setCellValue(purple.getPenalInterest().toString());
					row.createCell(8).setCellValue(purple.getGrossPayable().toString());
					row.createCell(9).setCellValue(purple.getMedicalFee().toString());
					row.createCell(10).setCellValue(purple.getStampDuty().toString());
					row.createCell(11).setCellValue(purple.getMortCharge().toString());
					row.createCell(12).setCellValue("NA");
					row.createCell(13).setCellValue(purple.getRecoveries().toString());
					row.createCell(14).setCellValue(purple.getNetPayable().toString());
					row.createCell(15).setCellValue("NA");
					row.createCell(16).setCellValue("NA");
					row.createCell(17).setCellValue(purple.getPfFlag());
					row.createCell(18).setCellValue(purple.getPfRemarks());
					row.createCell(19).setCellValue(purple.getApprovFlag());
					row.createCell(20).setCellValue(purple.getApprovRemarks());
				}

				if (purple.getUinNumber().contains("L")) {
					String trandate = purple.getTranDate();
					String tdate = myFormat.format(fromUser.parse(trandate));
					String effdate = purple.getEffDate();
					String edate = myFormat.format(fromUser.parse(effdate));
					Row row = sheet.createRow(rowIdx++);

					row.createCell(0).setCellValue(tdate);
					row.createCell(1).setCellValue(purple.getPolicyNo().toString());
					row.createCell(2).setCellValue(purple.getTranNo().toString());
					row.createCell(3).setCellValue(purple.getUinNumber());
					row.createCell(4).setCellValue("NA");
					row.createCell(5).setCellValue(purple.getTotlPremium().toString());
					row.createCell(6).setCellValue(purple.getAvalSuspense().toString());
					row.createCell(7).setCellValue(purple.getPenalInterest().toString());
					row.createCell(8).setCellValue(purple.getGrossPayable().toString());
					row.createCell(9).setCellValue(purple.getMedicalFee().toString());
					row.createCell(10).setCellValue(purple.getStampDuty().toString());
					row.createCell(11).setCellValue("NA");
					row.createCell(12).setCellValue(purple.getMortCharge().toString());
					row.createCell(13).setCellValue(purple.getRecoveries().toString());
					row.createCell(14).setCellValue(purple.getNetPayable().toString());
					row.createCell(15).setCellValue(edate);
					row.createCell(16).setCellValue(purple.getFundValue());
					row.createCell(17).setCellValue(purple.getPfFlag());
					row.createCell(18).setCellValue(purple.getPfRemarks());
					row.createCell(19).setCellValue(purple.getApprovFlag());
					row.createCell(20).setCellValue(purple.getApprovRemarks());
				}
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream transactionToExcel(List<TransactionPas> transactions)
			throws IOException, ParseException {
		String[] columns = { "Policy No.", "Transaction No.", "UIN Number", "Request Date", "Log Date",
				"Premium Refund", "Total Premium", "Policy Deposit", "Penal Interest", "Gross Pay", "Medical Fee",
				"Stamp Duty", "Risk Premium Recovery", "Mortality Charge Refund", "Total Recovery", "Net Pay",
				"Effective Date", "Fund Value", "Aprroval Date", "Medical Category", "Medical Center", "Medical TPA",
				"Interim Status" };
		SimpleDateFormat fromUser = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet("PAS");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header-->
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(columns[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (TransactionPas transaction : transactions) {
				if (transaction.getUinNumber().contains("N")) {
					String reqdate = transaction.getFlcReqDate();
					String rdate = myFormat.format(fromUser.parse(reqdate));
					String logdate = transaction.getFlcLogDate();
					String ldate = myFormat.format(fromUser.parse(logdate));
					String aprrovdate = transaction.getFlcApprovalDate();
					String adate = myFormat.format(fromUser.parse(aprrovdate));
					Row row = sheet.createRow(rowIdx++);

					row.createCell(0).setCellValue(transaction.getFlcPolicyNo().toString());
					row.createCell(1).setCellValue(transaction.getFlcTransNo().toString());
					row.createCell(2).setCellValue(transaction.getUinNumber());
					row.createCell(3).setCellValue(rdate);
					row.createCell(4).setCellValue(ldate);
					row.createCell(5).setCellValue(transaction.getFlcPremRefund().toString());
					row.createCell(6).setCellValue("NA");
					row.createCell(7).setCellValue(transaction.getFlcPolicyDop().toString());
					row.createCell(8).setCellValue(transaction.getPenalIntrest().toString());
					row.createCell(9).setCellValue(transaction.getGrossFlcPay().toString());
					row.createCell(10).setCellValue(transaction.getMedicalFee().toString());
					row.createCell(11).setCellValue(transaction.getStamDuty().toString());
					row.createCell(12).setCellValue(transaction.getRiskPremRecov().toString());
					row.createCell(13).setCellValue("NA");
					row.createCell(14).setCellValue(transaction.getTotalRecov().toString());
					row.createCell(15).setCellValue(transaction.getNetFlcPay().toString());
					row.createCell(16).setCellValue("NA");
					row.createCell(17).setCellValue("NA");
					row.createCell(18).setCellValue(adate);
					row.createCell(19).setCellValue(transaction.getMedicalCategory());
					row.createCell(20).setCellValue(transaction.getMedicalCenter());
					row.createCell(21).setCellValue(transaction.getMedicatTpaCode());
					row.createCell(22).setCellValue(transaction.getInterimStatus());
				}

				if (transaction.getUinNumber().contains("L")) {
					String reqdate = transaction.getFlcReqDate();
					String rdate = myFormat.format(fromUser.parse(reqdate));
					String logdate = transaction.getFlcLogDate();
					String ldate = myFormat.format(fromUser.parse(logdate));
					String aprrovdate = transaction.getFlcApprovalDate();
					String adate = myFormat.format(fromUser.parse(aprrovdate));
					String effdate = transaction.getEffDate();
					String edate = myFormat.format(fromUser.parse(effdate));
					Row row = sheet.createRow(rowIdx++);

					row.createCell(0).setCellValue(transaction.getFlcPolicyNo().toString());
					row.createCell(1).setCellValue(transaction.getFlcTransNo().toString());
					row.createCell(2).setCellValue(transaction.getUinNumber());
					row.createCell(3).setCellValue(rdate);
					row.createCell(4).setCellValue(ldate);
					row.createCell(5).setCellValue("NA");
					row.createCell(6).setCellValue(transaction.getFlcPremRefund().toString());
					row.createCell(7).setCellValue(transaction.getFlcPolicyDop().toString());
					row.createCell(8).setCellValue(transaction.getPenalIntrest().toString());
					row.createCell(9).setCellValue(transaction.getGrossFlcPay().toString());
					row.createCell(10).setCellValue(transaction.getMedicalFee().toString());
					row.createCell(11).setCellValue(transaction.getStamDuty().toString());
					row.createCell(12).setCellValue("NA");
					row.createCell(13).setCellValue(transaction.getRiskPremRecov().toString());
					row.createCell(14).setCellValue(transaction.getTotalRecov().toString());
					row.createCell(15).setCellValue(transaction.getNetFlcPay().toString());
					row.createCell(16).setCellValue(edate);
					row.createCell(17).setCellValue(transaction.getFundValue().toString());
					row.createCell(18).setCellValue(adate);
					row.createCell(19).setCellValue(transaction.getMedicalCategory());
					row.createCell(20).setCellValue(transaction.getMedicalCenter());
					row.createCell(21).setCellValue(transaction.getMedicatTpaCode());
					row.createCell(22).setCellValue(transaction.getInterimStatus());
				}
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
}