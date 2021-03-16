package org.acse.utitities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class XLUtils {

    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;

    private static String[] columns = {"FormName","Test_Id", "Test_Scenario", "ExpectResult", "FinalResult"};

    public static int getRowCount(String xlfile, String xlsheet) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        int rowcount = ws.getLastRowNum();
        wb.close();
        fi.close();
        return rowcount;
    }

    public static int getCellCount(String xlfile, String xlsheet, int rownum) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);
        int cellcount = row.getLastCellNum();
        wb.close();
        fi.close();
        return cellcount;
    }

    public static String getCellData(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);
        cell = row.getCell(colnum);
        String data;
        try {
            DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(cell);
            return cellData;
        } catch (Exception e) {
            data = "";
        }
        wb.close();
        fi.close();
        return data;
    }

    public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data)
            throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);
        cell = row.createCell(colnum);
        cell.setCellValue(data);
        fo = new FileOutputStream(xlfile);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }

    public static String createOutPutExcel(String excellLocation,String sheetName) throws IOException
    {
        String timeStamp = new SimpleDateFormat("YYYY-MM-dd_HH-mm-ss").format(new Date());// time stamp
        String pathName = "";
        OutputStream fileOut = null;
        try {

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(sheetName);

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.VIOLET.getIndex());


            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Create a Row
            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write the output to a file
            pathName = excellLocation+"_"+timeStamp+".xlsx";
            FileOutputStream fOut = new FileOutputStream(pathName);
            workbook.write(fOut);
            fOut.close();


        } catch (Exception e) {

            e.printStackTrace();
        }


        System.out.println("Excel created Successfully ");
        return pathName;
    }


    public void printOutPutExcel(HashMap<String, ArrayList<String>> finalResultMaps, String outPutExcellLocation, String writeSheetName) throws IOException {


        try {

            String formName ="";
            String TC_No="";
            String scenario ="";
            String expectedResult ="";
            String actualResult = "";

            fi = new FileInputStream(outPutExcellLocation);
            wb = new XSSFWorkbook(fi);
            ws = wb.getSheet(writeSheetName);
            ArrayList<String> testDetails = new ArrayList<String>();
            ArrayList<String> TC = new ArrayList<String>();



            for(Map.Entry<String, ArrayList<String>> m:finalResultMaps.entrySet()){



                TC_No =m.getKey();
                testDetails = m.getValue();

                formName = testDetails.get(0);
                scenario = testDetails.get(1);
                expectedResult = testDetails.get(2);
                actualResult = testDetails.get(3);
                //actualResult = Boolean.parseBoolean(testDetails.get(4));
                System.out.println("TC_No :"+TC_No+'\n'+"SheetName :"+writeSheetName+'\n'+"FormName :"+formName+'\n'+"scenario :"+scenario +'\n'+"expectedResult :"+expectedResult+'\n'+"actualResult :"+actualResult);


                int lastRow = ws.getLastRowNum()+1;


                CellStyle style = wb.createCellStyle();

                for(int i=lastRow ; i <= lastRow; i++)
                {

                    XSSFRow rows = ws.createRow(i);
                    //XSSFRow row = ws.getRow(i);


                    Iterator<Cell> cell = rows.cellIterator();

                    if(cell.hasNext() == false)
                    {

                        rows.createCell(0).setCellValue(formName);
                        rows.createCell(1).setCellValue(TC_No);
                        rows.createCell(2).setCellValue(scenario);
                        rows.createCell(3).setCellValue(expectedResult);

                        //Styles

                        if (actualResult.equalsIgnoreCase("true")) {
                            style.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
                            style.setFillPattern(FillPatternType.BIG_SPOTS);
                            XSSFCell cells = rows.createCell(4);
                            cells.setCellValue("PASS");
                            cells.setCellStyle(style);
                        } else if (actualResult.equalsIgnoreCase("false")) {
                            style.setFillBackgroundColor(IndexedColors.RED.getIndex());
                            style.setFillPattern(FillPatternType.BIG_SPOTS);
                            XSSFCell cells = rows.createCell(4);
                            cells.setCellValue("FAIL");
                            cells.setCellStyle(style);
                        }

                    }
                }


            }

            finalResultMaps.remove(TC_No);

            fi.close();
            System.out.println("Result Updated");

            fo = new FileOutputStream(new File(outPutExcellLocation));
            wb.write(fo);
            fo.close();


        }
        catch (Exception e) {

            e.printStackTrace();
        }




    }


}
