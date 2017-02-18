package org.wang.tools.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.wang.tools.vo.Table;
import org.wang.tools.vo.Tables;

public class DataDictionaryExcel{
	
	public static int rowNo = 0 ;
	
	public static HSSFWorkbook generateTableDataDictionary( HSSFWorkbook workBook , List<Table> tables){
		
		HSSFSheet sheet = workBook.createSheet(); // 创建一个工作薄对象 
		HSSFCell cell = null;
		sheet.setColumnWidth(0, 4000); 
		sheet.setColumnWidth(1, 4000); 
		sheet.setColumnWidth(2, 4000); 
		HSSFCellStyle style = workBook.createCellStyle(); // 创建样式对象 
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);// 水平居中 
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中 
		HSSFFont font = workBook.createFont();// 创建字体对象 
		font.setFontHeightInPoints((short) 12); 
		font.setFontName("黑体");// 设置字体 
		style.setFont(font); 


		HSSFCellStyle titleStyle = workBook.createCellStyle();// 创建表头样式对象 
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);// 水平居中 
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中 
		titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
		titleStyle.setFillForegroundColor(IndexedColors.GREY_80_PERCENT.getIndex());
		HSSFFont titleFont = workBook.createFont();// 创建表头字体对象 
		titleFont.setFontHeightInPoints((short) 15); 
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置粗体 
		titleFont.setFontName("黑体");// 设置字体 
		titleFont.setColor(HSSFColor.WHITE.index);
		titleStyle.setFont(titleFont); 
		
		String[] bt = new String[] { "字段名称", "字段类型", "业务意义"}; 
		HSSFRow tableRow = null ;			

		for(Table t : tables){
			
			tableRow = sheet.createRow( rowNo = rowNo + 3); // 创建一个行对象 
			tableRow.setHeightInPoints(18); 
            cell = tableRow.createCell(0);
             cell = tableRow.createCell(1);
			cell.setCellStyle(titleStyle); 
			cell.setCellValue(new HSSFRichTextString("表名")); 
			cell = tableRow.createCell(2);
			cell.setCellStyle(titleStyle); 
			cell.setCellValue(new HSSFRichTextString(t.getTableName())); 
			
			tableRow = sheet.createRow( rowNo = rowNo + 1); // 创建一个行对象 
			tableRow.setHeightInPoints(18); 
			for (int i = 1; i <= bt.length; i++) { 
				cell = tableRow.createCell(0);
				
				cell = tableRow.createCell(i); 
				cell.setCellStyle(titleStyle); 
				cell.setCellValue(new HSSFRichTextString(bt[i-1])); 
			} 
			
			for(Tables c : t.getColumns()){
				tableRow = sheet.createRow(rowNo = rowNo + 1); // 创建一个行对象 
				tableRow.setHeightInPoints(16);
				style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
				
				if(0!=rowNo%2){
					style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
				}else{
					style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
				}
				cell = tableRow.createCell(0); 
				cell = tableRow.createCell(1); 
				cell.setCellStyle(style);
				cell.setCellValue(new HSSFRichTextString(c.getField())); 
				tableRow.setHeightInPoints(16);
				cell = tableRow.createCell(2); 
				cell.setCellStyle(style);
				cell.setCellValue(new HSSFRichTextString(c.getType())); 
				tableRow.setHeightInPoints(16);
				cell = tableRow.createCell(3); 
				cell.setCellStyle(style);
				cell.setCellValue(new HSSFRichTextString(c.getComment())); 
				rowNo++ ;
			}
		}
		return workBook ;  
	}
	
	public static void generateDataDictionaryExcel(List<Table> tables 
			 																   , String url ){
		
		HSSFWorkbook workBook = new HSSFWorkbook(); // 创建 一个excel文档对象 
		
		generateTableDataDictionary(workBook , tables);
		
		try {
			@SuppressWarnings("resource")
			FileOutputStream fos  = new FileOutputStream(new File(url));
			workBook.write(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
				
	}

}