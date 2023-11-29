package com.futura.Purple.Excel;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.futura.Purple.Entity.BonusRate;
import com.futura.Purple.Entity.DeathClaimParam;
import com.futura.Purple.Entity.GSVCashValue;
import com.futura.Purple.Entity.GsvFactor;
import com.futura.Purple.Entity.GuaranteedBonusRate;
import com.futura.Purple.Entity.MedicalDetails;
import com.futura.Purple.Entity.MortFlagMaster;
import com.futura.Purple.Entity.MortalityRates;
import com.futura.Purple.Entity.PurpleNav;
import com.futura.Purple.Entity.SsvFactor;
import com.futura.Purple.Entity.StamDutyMaster;
import com.futura.Purple.Entity.UinMaster;

public class Helper {
	
	//check that file is of excel type or not
    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }

    }


    //convert excel to list of products

    public static List<StamDutyMaster> convertExcelToListOfStampDuty(InputStream is) {
        List<StamDutyMaster> list = new ArrayList<>();

        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("Sheet1");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                StamDutyMaster sd = new StamDutyMaster();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                        case 0:
                            sd.setCompanyId((long) cell.getNumericCellValue());
                            break;
                        case 1:
                            sd.setUinNumber(cell.getStringCellValue());
                            break;
                        case 2:
                           sd.setCoverName(cell.getStringCellValue());
                            break;
                        case 3:
                            sd.setSdRate((double) cell.getNumericCellValue());
                            break;
                        case 4:
                            sd.setGstRate((float) cell.getNumericCellValue());
                            break;
                        case 5:
                        	Date s = cell.getDateCellValue();
                       	 	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
                       	 	String start = sdf1.format(s);
                       	 	sd.setStartDate(start);
                            break;
                        case 6:
                        	Date e = cell.getDateCellValue();
                        	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                       	 	String end = sdf2.format(e);
                       	 	sd.setEndDate(end);
                            break;
                        case 7:
                            sd.setValidFlag((int) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;

                }

                list.add(sd);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
    
//    public static List<MortFlagMaster> convertExcelToListOfMortMaster(InputStream is) {
//        List<MortFlagMaster> list = new ArrayList<>();
//
//        try {
//
//
//            XSSFWorkbook workbook = new XSSFWorkbook(is);
//
//            XSSFSheet sheet = workbook.getSheet("Sheet1");
//
//            int rowNumber = 0;
//            Iterator<Row> iterator = sheet.iterator();
//
//            while (iterator.hasNext()) {
//                Row row = iterator.next();
//
//                if (rowNumber == 0) {
//                    rowNumber++;
//                    continue;
//                }
//
//                Iterator<Cell> cells = row.iterator();
//
//                int cid = 0;
//
//                MortFlagMaster mFlag = new MortFlagMaster();
//
//                while (cells.hasNext()) {
//                    Cell cell = cells.next();
//
//                    switch (cid) {
//                        case 0:
//                        	mFlag.setCompanyId((long) cell.getNumericCellValue());
//                            break;
//                        case 1:
//                        	mFlag.setUinNumber(cell.getStringCellValue());
//                            break;
//                        case 2:
//                        	mFlag.setCoverName(cell.getStringCellValue());
//                            break;
//                        case 3:
//                        	mFlag.setMortFlag(cell.getStringCellValue());
//                            break;
//                        case 4:
//                        	mFlag.setGstRate((float) cell.getNumericCellValue());
//                            break;
//                        case 5:
//                        	 Date s = cell.getDateCellValue();
//                        	 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
//                        	 String start = sdf1.format(s);
//                        	 mFlag.setStartDate(start);
//                             break;
//                        case 6:
//                        	Date e = cell.getDateCellValue();
//                        	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
//                       	 	String end = sdf2.format(e);
//                       	    mFlag.setEndDate(end);
//                            break;
//                        case 7:
//                        	mFlag.setValidFlag((int) cell.getNumericCellValue());
//                            break;
//                        default:
//                            break;
//                    }
//                    cid++;
//
//                }
//
//                list.add(mFlag);
//
//
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//
//    }
    
    
    public static List<MortFlagMaster> convertExcelToListOfMortMaster(InputStream is) {
    	  List<MortFlagMaster> list = new ArrayList<>();

    	  try {


    	      XSSFWorkbook workbook = new XSSFWorkbook(is);

    	      XSSFSheet sheet = workbook.getSheet("Sheet1");

    	      int rowNumber = 0;
    	      Iterator<Row> iterator = sheet.iterator();

    	      while (iterator.hasNext()) {
    	          Row row = iterator.next();

    	          if (rowNumber == 0) {
    	              rowNumber++;
    	              continue;
    	          }

    	          Iterator<Cell> cells = row.iterator();

    	          int cid = 0;

    	          MortFlagMaster mFlag = new MortFlagMaster();

    	          while (cells.hasNext()) {
    	              Cell cell = cells.next();

    	              switch (cid) {
    	              		case 0:
    	              			mFlag.setCompanyId((long) cell.getNumericCellValue());
    	              			break;
    	              		case 1:
    	              			mFlag.setUinNumber(cell.getStringCellValue());
    	              			break;
    	              		case 2:
    	              			mFlag.setCoverName(cell.getStringCellValue());
    	              			break;
    	              		case 3:
    	              			mFlag.setMortFlag(cell.getStringCellValue());
    	              			break;
    	              		case 4:
    	              			mFlag.setGstRate((float) cell.getNumericCellValue());
    	              			break;
    	              		case 5:
    	              			Date s = cell.getDateCellValue();
    	              			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
    	              			String start = sdf1.format(s);
    	              			mFlag.setStartDate(start);
    	              			break;
    	              		case 6:
    	                    	Date e = cell.getDateCellValue();
    	                    	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
    	                   	 	String end = sdf2.format(e);
    	                   	 	mFlag.setEndDate(end);
    	                        break;
    	              		case 7:
    	              			mFlag.setValidFlag((int) cell.getNumericCellValue());
    	              			break;
    	              		default:
    	              			break;
    	              }
    	              cid++;

    	          }

    	          list.add(mFlag);


    	      }


    	  } catch (Exception e) {
    	      e.printStackTrace();
    	  }
    	  return list;

    	}
    
    public static List<MortalityRates> convertExcelToListOfMortalityRates(InputStream is) {
        List<MortalityRates> list = new ArrayList<>();

        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("Sheet1");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                MortalityRates mortRate = new MortalityRates();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                    	case 0:
                    	mortRate.setCompanyId((long) cell.getNumericCellValue());
                        break;    
                    	case 1:
                        	mortRate.setPlan(cell.getStringCellValue());
                            break;
                        case 2:
                        	mortRate.setPlanName(cell.getStringCellValue());
                            break;
                        case 3:
                        	mortRate.setUinNumber(cell.getStringCellValue());
                            break;
                        case 4:
                        	double d = cell.getNumericCellValue();
                        	String term = String.valueOf((int) d);
                        	mortRate.setPremTerm(term);
                            break;
                        case 5:
                        	mortRate.setAge((int) cell.getNumericCellValue());
                            break;
                        case 6:
                        	mortRate.setRates((float) cell.getNumericCellValue());
                            break;
                        case 7:
                        	 Date s = cell.getDateCellValue();
                        	 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
                        	 String start = sdf1.format(s);
                        	 mortRate.setStartDate(start);
                             break;
                        case 8:
                        	Date e = cell.getDateCellValue();
                        	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                       	 	String end = sdf2.format(e);
                       	 	mortRate.setEndDate(end);
                            break;
                        case 9:
                        	mortRate.setGender(cell.getStringCellValue());
                            break;
                        case 10:
                        	mortRate.setSmoker(cell.getStringCellValue());
                            break;
                        case 11:
                        	mortRate.setValidFlag((int) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;

                }

                list.add(mortRate);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
    
    public static List<MedicalDetails> convertExcelToListOfMedicalDetails(InputStream is) {
        List<MedicalDetails> list = new ArrayList<>();

        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("Sheet1");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                MedicalDetails med = new MedicalDetails();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                    	case 0:
                    		med.setCompanyId((long) cell.getNumericCellValue());
                        break;    
                    	case 1:
                    		med.setCompanyName(cell.getStringCellValue());
                            break;
                        case 2:
                        	med.setTpaCode(cell.getStringCellValue());
                            break;
                        case 3:
                        	med.setProdName(cell.getStringCellValue());
                            break;
                        case 4:
                        	med.setMedicalCategory(cell.getStringCellValue());
                            break;
                        case 5:
                        	med.setMedicalCenter(cell.getStringCellValue());
                            break;
                        case 6:
                        	med.setMfRate((float) cell.getNumericCellValue());
                            break;
                        case 7:
                        	 Date s = cell.getDateCellValue();
                        	 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
                        	 String start = sdf1.format(s);
                        	 med.setStartDate(start);
                             break;
                        case 8:
                        	Date e = cell.getDateCellValue();
                        	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                       	 	String end = sdf2.format(e);
                       	 	med.setEndDate(end);
                            break;
                        case 9:
                        	med.setValidFlag((int) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;

                }

                list.add(med);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
    
    
    public static List<PurpleNav> convertExcelToListOfPurpleNav(InputStream is) {
        List<PurpleNav> list = new ArrayList<>();

        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("Sheet1");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                PurpleNav nav = new PurpleNav();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                    	case 0:
                    		nav.setCompanyId((long) cell.getNumericCellValue());
                        break;    
                    	case 1:
                    		nav.setFundCode(cell.getStringCellValue());
                            break;
                        case 2:
                        	nav.setFundName(cell.getStringCellValue());
                            break;
                        case 3:
                        	Date n = cell.getDateCellValue();
	                       	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	                       	String navD = sdf1.format(n);
	                       	nav.setNavDate(navD);
                            break;
                        case 4:
                        	nav.setRate((double) cell.getNumericCellValue());
                            break;
                        case 5:
                        	nav.setValidFlag((int) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;

                }

                list.add(nav);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
    
    public static List<GsvFactor> convertExcelToListOfGSVFactor(InputStream is) {
        List<GsvFactor> list = new ArrayList<>();

        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("Sheet1");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                GsvFactor gsv = new GsvFactor();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                    	case 0:
                    		gsv.setCompanyId((long) cell.getNumericCellValue());
                        break;    
                    	case 1:
                    		gsv.setUinNumber(cell.getStringCellValue());
                            break;
                        case 2:
                        	gsv.setPlanName(cell.getStringCellValue());
                            break;
                        case 3:
                        	gsv.setPlanCode(cell.getStringCellValue());
                            break;
                        case 4:
                        	gsv.setPolicyTerm((double) cell.getNumericCellValue());
                            break;
                        case 5:
                        	gsv.setPremiumTerm((double) cell.getNumericCellValue());
                            break;
                        case 6:
                        	gsv.setPremiumType(cell.getStringCellValue());
                            break;
                        case 7:
                        	gsv.setPolicyDuration((double) cell.getNumericCellValue());
                            break;
                        case 8:
                        	gsv.setRate((double) cell.getNumericCellValue());
                            break;
                        case 9:
	                       	Date s = cell.getDateCellValue();
	                       	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	                       	String start = sdf1.format(s);
	                       	gsv.setStartDate(start);
                            break;
                        case 10:
                        	Date e = cell.getDateCellValue();
                        	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                       	 	String end = sdf2.format(e);
                       	 	gsv.setEndDate(end);
                            break;
                        case 11:
                        	gsv.setValidFlag((int) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;

                }

                list.add(gsv);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
    
    public static List<SsvFactor> convertExcelToListOfSSVFactor(InputStream is) {
        List<SsvFactor> list = new ArrayList<>();

        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("Sheet1");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                SsvFactor ssv = new SsvFactor();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                    	case 0:
                    		ssv.setCompanyId((long) cell.getNumericCellValue());
                        break;    
                    	case 1:
                    		ssv.setUinNumber(cell.getStringCellValue());
                            break;
                        case 2:
                        	ssv.setPlanName(cell.getStringCellValue());
                            break;
                        case 3:
                        	ssv.setPlanCode(cell.getStringCellValue());
                            break;
                        case 4:
                        	ssv.setPolicyTerm((double) cell.getNumericCellValue());
                            break;
                        case 5:
                        	ssv.setPremiumTerm((double) cell.getNumericCellValue());
                            break;
                        case 6:
                        	ssv.setPremiumType(cell.getStringCellValue());
                            break;
                        case 7:
                        	ssv.setPolicyDuration((double) cell.getNumericCellValue());
                            break;
                        case 8:
                        	ssv.setRate((double) cell.getNumericCellValue());
                            break;
                        case 9:
	                       	Date s = cell.getDateCellValue();
	                       	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	                       	String start = sdf1.format(s);
	                       	ssv.setStartDate(start);
                            break;
                        case 10:
                        	Date e = cell.getDateCellValue();
                        	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                       	 	String end = sdf2.format(e);
                       	 	ssv.setEndDate(end);
                            break;
                        case 11:
                        	ssv.setValidFlag((int) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;

                }

                list.add(ssv);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
    
    public static List<GSVCashValue> convertExcelToListOfCashValueBonus(InputStream is) {
        List<GSVCashValue> list = new ArrayList<>();

        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("Sheet1");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                GSVCashValue cvb = new GSVCashValue();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                    	case 0:
                    		cvb.setCompanyId((long) cell.getNumericCellValue());
                        break;    
                    	case 1:
                    		cvb.setUinNumber(cell.getStringCellValue());
                            break;
                        case 2:
                        	cvb.setPlanName(cell.getStringCellValue());
                            break;
                        case 3:
                        	cvb.setPlanCode(cell.getStringCellValue());
                            break;
                        case 4:
                        	cvb.setYearsToMaturity((double) cell.getNumericCellValue());
                            break;
                        case 5:
                        	cvb.setCvbRate((double) cell.getNumericCellValue());
                            break;
                        case 6:
	                       	Date s = cell.getDateCellValue();
	                       	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	                       	String start = sdf1.format(s);
	                       	cvb.setStartDate(start);
                            break;
                        case 7:
                        	Date e = cell.getDateCellValue();
                        	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                       	 	String end = sdf2.format(e);
                       	 	cvb.setEndDate(end);
                            break;
                        case 8:
                        	cvb.setValidFlag((int) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;

                }

                list.add(cvb);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
    
    public static List<UinMaster> convertExcelToListOfUINMaster(InputStream is) {
        List<UinMaster> list = new ArrayList<>();

        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("Sheet1");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                UinMaster uin = new UinMaster();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                    	case 0:
                    		uin.setCompanyId((long) cell.getNumericCellValue());
                        break;    
                    	case 1:
                    		uin.setUinNumber(cell.getStringCellValue());
                            break;
                        case 2:
                        	uin.setPlanName(cell.getStringCellValue());
                            break;
                        case 3:
                        	uin.setPlanCode(cell.getStringCellValue());
                            break;
                        case 4:
                        	uin.setGsvFactor(cell.getStringCellValue());
                            break;
                        case 5:
                        	uin.setGsvCashValue(cell.getStringCellValue());
                            break;
                        case 6:
                        	uin.setSsvFactor(cell.getStringCellValue());
                            break;
                        case 7:
                        	uin.setProductType(cell.getStringCellValue());
                            break;
                        case 8:
                        	uin.setFlcEligibility(cell.getStringCellValue());
                            break;
                        case 9:
                        	uin.setSurrenderChargeRate((double) cell.getNumericCellValue());
                            break;
                        case 10:
	                       	Date s = cell.getDateCellValue();
	                       	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	                       	String start = sdf1.format(s);
	                       	uin.setStartDate(start);
                            break;
                        case 11:
                        	Date e = cell.getDateCellValue();
                        	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                       	 	String end = sdf2.format(e);
                       	 	uin.setEndDate(end);
                            break;
                        case 12:
                        	uin.setValidFlag((int) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;

                }

                list.add(uin);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
    
    public static List<BonusRate> convertExcelToListOfBonusRate(InputStream is) {
        List<BonusRate> list = new ArrayList<>();

        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("Sheet1");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                BonusRate bonus = new BonusRate();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                    	case 0:
                    		bonus.setCompanyId((long) cell.getNumericCellValue());
                        break;    
                    	case 1:
                    		bonus.setUinNumber(cell.getStringCellValue());
                            break;
                        case 2:
                        	bonus.setPlanName(cell.getStringCellValue());
                            break;
                        case 3:
                        	bonus.setPlanCode(cell.getStringCellValue());
                            break;
                        case 4:
                        	Date f = cell.getDateCellValue();
	                       	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	                       	String fy = sdf1.format(f);
	                       	bonus.setFinancialYear(fy);
                            break;
                        case 5:
                        	bonus.setBonusRate((double) cell.getNumericCellValue());
                            break;
                        case 6:
                        	bonus.setBonusType(cell.getStringCellValue());
                            break;
                        case 7:
	                       	Date s = cell.getDateCellValue();
	                       	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
	                       	String start = sdf2.format(s);
	                       	bonus.setStartDate(start);
                            break;
                        case 8:
                        	Date e = cell.getDateCellValue();
                        	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd");
                       	 	String end = sdf3.format(e);
                       	 	bonus.setEndDate(end);
                            break;
                        case 9:
                        	bonus.setValidFlag((int) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;

                }

                list.add(bonus);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
    
    public static List<GuaranteedBonusRate> convertExcelToListOfGuaranteedBonusRate(InputStream is) {
        List<GuaranteedBonusRate> list = new ArrayList<>();

        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("Sheet1");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                GuaranteedBonusRate bonus = new GuaranteedBonusRate();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                    	case 0:
                    		bonus.setCompanyId((long) cell.getNumericCellValue());
                        break;    
                    	case 1:
                    		bonus.setUinNumber(cell.getStringCellValue());
                            break;
                        case 2:
                        	bonus.setPlanName(cell.getStringCellValue());
                            break;
                        case 3:
                        	bonus.setPlanCode(cell.getStringCellValue());
                            break;
                        case 4:
                        	Date f = cell.getDateCellValue();
	                       	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	                       	String fy = sdf1.format(f);
	                       	bonus.setFinancialYear(fy);
                            break;
                        case 5:
                        	bonus.setBonusRate((double) cell.getNumericCellValue());
                            break;
                        case 6:
                        	bonus.setBonusType(cell.getStringCellValue());
                            break;
                        case 7:
	                       	Date s = cell.getDateCellValue();
	                       	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
	                       	String start = sdf2.format(s);
	                       	bonus.setStartDate(start);
                            break;
                        case 8:
                        	Date e = cell.getDateCellValue();
                        	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd");
                       	 	String end = sdf3.format(e);
                       	 	bonus.setEndDate(end);
                            break;
                        case 9:
                        	bonus.setValidFlag((int) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;

                }

                list.add(bonus);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
    
    public static List<DeathClaimParam> convertExcelToListOfDeathClaimParam(InputStream is) {
        List<DeathClaimParam> list = new ArrayList<>();

        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("Sheet1");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                DeathClaimParam param = new DeathClaimParam();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                    	case 0:
                    		param.setCompanyId((long) cell.getNumericCellValue());
                        break;    
                    	case 1:
                    		param.setUinNumber(cell.getStringCellValue());
                            break;
                        case 2:
                        	param.setCauseOfDeath(cell.getStringCellValue());
                            break;
                        case 3:
                        	param.setFromMonth((long) cell.getNumericCellValue());
                            break;
                        case 4:
                        	param.setToMonth((long) cell.getNumericCellValue());
                            break;
                        case 5:
                        	param.setPolicyStatus(cell.getStringCellValue());
                            break;
                        case 6:
	                       	Date s = cell.getDateCellValue();
	                       	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
	                       	String start = sdf2.format(s);
	                       	param.setStartDate(start);
                            break;
                        case 7:
                        	Date e = cell.getDateCellValue();
                        	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd");
                       	 	String end = sdf3.format(e);
                       	 	param.setEndDate(end);
                            break;
                        case 8:
                        	param.setFactor((double) cell.getNumericCellValue());
                            break;
                        case 9:
                        	param.setCalculationMethod(cell.getStringCellValue());
                            break;
                        case 10:
                        	param.setValidFlag((int) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;

                }

                list.add(param);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

}
