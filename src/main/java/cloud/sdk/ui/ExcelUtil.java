package cloud.sdk.ui;

import cn.hutool.poi.excel.style.Align;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

public class ExcelUtil {

	private HSSFWorkbook wb = null; // ������

	/**
	 * ���캯��
	 * <p>
	 * ����������
	 * </p>
	 */
	public ExcelUtil() {
		wb = new HSSFWorkbook();
	}

	/**
	 * ���캯��
	 * <p>
	 * ͨ���ļ�����·����ȡ������
	 * </p>
	 */
	public ExcelUtil(String filePath) {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			wb = new HSSFWorkbook(new POIFSFileSystem(fis));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * ���캯��
	 * <p>
	 * ͨ���ļ�����ȡ������
	 * </p>
	 */
	public ExcelUtil(FileInputStream fis) {
		try {
			wb = new HSSFWorkbook(new POIFSFileSystem(fis));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * ��ù�����sheet
	 * <p>
	 * ������1��ʼ
	 * </p>
	 */
	public HSSFSheet getSheet(int sheetNum) {
		HSSFSheet result = null;

		try {
			if (sheetNum > 0) {
				result = wb.getSheetAt(sheetNum - 1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * ����������
	 * <p>
	 * �ڵ�ǰ���������Ĺ��������?��������
	 * </p>
	 */
	public HSSFSheet createSheet() {
		HSSFSheet result = null;

		try {
			result = wb.createSheet();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * ����������
	 * <p>
	 * �ڵ�ǰ���������Ĺ��������?��������
	 * </p>
	 */
	public HSSFSheet createSheet(String sheetName) {
		HSSFSheet result = null;

		try {
			result = wb.createSheet(sheetName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * ɾ��������
	 * <p>
	 * ������1��ʼ
	 * </p>
	 */
	public void deleteSheet(int sheetNum) {
		try {
			if (sheetNum > 0) {
				wb.removeSheetAt(sheetNum - 1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * ��ù���������?
	 * <p>
	 * ������1��ʼ
	 * </p>
	 */
	public String getSheetName(int sheetNum) {
		String result = null;

		try {
			if (sheetNum > 0) {
				result = wb.getSheetName(sheetNum);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * ���ù���������
	 */
	public void setSheetName(HSSFSheet sheet, String sheetName) {
		try {
			int sheetIdx = wb.getSheetIndex(sheet);
			wb.setSheetName(sheetIdx, sheetName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * ���ù���������
	 * <p>
	 * ������1��ʼ
	 * </p>
	 */
	public void setSheetName(int sheetNum, String sheetName) {
		try {
			if (sheetNum > 0) {
				wb.setSheetName(sheetNum - 1, sheetName);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * �����Զ��п�
	 * <p>
	 * ������1��ʼ
	 * </p>
	 */
	public void autoSizeColumn(HSSFSheet sheet, int col) {
		try {
			sheet.autoSizeColumn(col - 1);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * �����п�
	 * <p>
	 * ������1��ʼ
	 * </p>
	 */
	public void setColumnWidth(HSSFSheet sheet, int col, int width) {
		try {
			sheet.setColumnWidth(col - 1, width);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * �����и�
	 * <p>
	 * ������1��ʼ
	 * </p>
	 */
	public void setRowHeight(HSSFSheet sheet, int row, float height) {
		try {
			HSSFRow r = sheet.getRow(row - 1);
			if (r == null) {
				r = sheet.createRow(row - 1);
			}
			r.setHeightInPoints(height);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * ���ᴰ��
	 * <p>
	 * ������1��ʼ
	 * </p>
	 */
	public void freezePane(HSSFSheet sheet, int rowSplit, int colSplit) {
		try {
			sheet.createFreezePane(colSplit - 1, rowSplit - 1);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * ��ù�������ָ������cell�ķ��?
	 * <p>
	 * �С��м�������1��ʼ
	 * </p>
	 */
	public HSSFCellStyle getCellStyle(HSSFSheet sheet, int row, int col) {
		HSSFCellStyle result = null;

		try {
			HSSFCell cell = getCell(sheet, row, col);
			result = cell.getCellStyle();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * ���ù�������ָ������cell�ķ��?
	 * <p>
	 * �С��м�������1��ʼ
	 * </p>
	 */
	public void setCellStyle(HSSFSheet sheet, int row, int col,
			HSSFCellStyle style) {
		try {
			HSSFCell cell = getCell(sheet, row, col);
			cell.setCellStyle(style);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * ����cell��Ĭ�Ϸ��?
	 */
	public HSSFCellStyle createCellStyle() {
		return wb.createCellStyle();
	}

	/**
	 * ����cell�Ķ��뷽ʽ
	 * <p>
	 * align:left\center\right
	 * </p>
	 * <p>
	 * valign:top\center\middle\bottom
	 * </p>
	 */
	public void setCellAlignment(HSSFCellStyle style, String align,
			String valign) {
		try {
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setVerticalAlignment(VerticalAlignment.CENTER);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * ����cell�ı�����ɫ
	 */
	public void setCellColor(HSSFCellStyle style, String rgb) {
		try {
			style.setFillForegroundColor(getRGB(rgb));
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * ����cell���Ƿ��Զ�����
	 */
	public void setCellWrapText(HSSFCellStyle style, boolean wrapped) {
		try {
			style.setWrapText(wrapped);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * ����cell�ı߿�
	 */
	public void setCellBorder(HSSFCellStyle style, boolean left, boolean top,
			boolean right, boolean bottom) {
		try {
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * ����cell��������
	 */
	public void setFontStyle(HSSFCellStyle style, String fontName,
			int fontSize, String rgb, boolean bold, boolean italic) {
		try {
			HSSFFont font = wb.createFont();
			font.setFontName(fontName);
			font.setFontHeightInPoints((short) fontSize);
			font.setColor(getRGB(rgb));
			font.setItalic(italic);
			style.setFont(font);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * ��Ԫ��ϲ�?
	 * <p>
	 * �С��м�������1��ʼ
	 * </p>
	 */
	public void mergeCell(HSSFSheet sheet, int firstRow, int lastRow,
			int firstCol, int lastCol) {
		try {
			sheet.addMergedRegion(new CellRangeAddress(firstRow - 1,
					lastRow - 1, firstCol - 1, lastCol - 1));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * ����������ָ������cell������
	 * <p>
	 * �С��м�������1��ʼ
	 * </p>
	 */
	public String read(HSSFSheet sheet, int row, int col) {
		String result = null;

		try {
			HSSFCell cell = getCell(sheet, row, col);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * ����������ָ������cell������
	 * <p>
	 * �С��м�������1��ʼ
	 * </p>
	 */
	public DateTime readDateTime(HSSFSheet sheet, int row, int col) {
		DateTime result = null;

		try {
			HSSFCell cell = getCell(sheet, row, col);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * д��������ָ������cell������
	 * <p>
	 * �С��м�������1��ʼ
	 * </p>
	 */
	public void write(HSSFSheet sheet, int row, int col, HSSFCellStyle style,
			String value) {
		try {
			HSSFCell cell = getCell(sheet, row, col);
			if (value == null || value.trim().equals("")
					|| value.equalsIgnoreCase("null")) {
				value = "";
			}
			cell.setCellStyle(style);
			cell.setCellValue(value);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * д��������ָ������cell������
	 * <p>
	 * �С��м�������1��ʼ
	 * </p>
	 */
	public void write(HSSFSheet sheet, int row, int col, String value) {
		try {
			HSSFCell cell = getCell(sheet, row, col);
			if (value == null || value.trim().equals("")
					|| value.equalsIgnoreCase("null")) {
				value = "";
			}
			cell.setCellValue(value);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * д��������ָ������cell������
	 * <p>
	 * �С��м�������1��ʼ
	 * </p>
	 */
	public void write(HSSFSheet sheet, int row, int col, HSSFCellStyle style,
			int value) {
		try {
			HSSFCell cell = getCell(sheet, row, col);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellStyle(style);
			cell.setCellValue(value);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * д��������ָ������cell������
	 * <p>
	 * �С��м�������1��ʼ
	 * </p>
	 */
	public void write(HSSFSheet sheet, int row, int col, int value) {
		try {
			HSSFCell cell = getCell(sheet, row, col);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(value);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * д��������ָ������cell������
	 * <p>
	 * �С��м�������1��ʼ
	 * </p>
	 */
	public void write(HSSFSheet sheet, int row, int col, HSSFCellStyle style,
			double value) {
		try {
			HSSFCell cell = getCell(sheet, row, col);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellStyle(style);
			cell.setCellValue(value);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * д��������ָ������cell������
	 * <p>
	 * �С��м�������1��ʼ
	 * </p>
	 */
	public void write(HSSFSheet sheet, int row, int col, double value) {
		try {
			HSSFCell cell = getCell(sheet, row, col);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(value);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * д��������ָ������cell������
	 * <p>
	 * �С��м�������1��ʼ
	 * </p>
	 */
	public void writeFormula(HSSFSheet sheet, int row, int col,
			HSSFCellStyle style, String value) {
		try {
			HSSFCell cell = getCell(sheet, row, col);
			if (value == null || value.trim().equals("")
					|| value.equalsIgnoreCase("null")) {
				value = "";
			}
			cell.setCellStyle(style);
			cell.setCellFormula(value);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * д��������ָ������cell������
	 * <p>
	 * �С��м�������1��ʼ
	 * </p>
	 */
	public void writeFormula(HSSFSheet sheet, int row, int col, String value) {
		try {
			HSSFCell cell = getCell(sheet, row, col);
			if (value == null || value.trim().equals("")
					|| value.equalsIgnoreCase("null")) {
				value = "";
			}
			cell.setCellFormula(value);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * xls�ļ����?
	 */
	public void output(String filePath) {
		try {
			FileOutputStream fileOut = new FileOutputStream(filePath);
			wb.write(fileOut);
			fileOut.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private HSSFCell getCell(HSSFSheet sheet, int row, int col) {
		HSSFCell result = null;

		try {
			HSSFRow hRow = null;
			int iRow = row - 1;
			int iCell = col - 1;
			hRow = sheet.getRow(iRow);
			if (hRow == null) {
				hRow = sheet.createRow(iRow);
			}
			result = hRow.getCell(iCell);
			if (result == null) {
				result = hRow.createCell(iCell);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	private short getRGB(String str) {
		short result = 0;

		try {
			if (isRGB(str)) {
				// ����ɫת����ʮ������
				int[] color = new int[3];
				color[0] = Integer.parseInt(str.substring(1, 3), 16);
				color[1] = Integer.parseInt(str.substring(3, 5), 16);
				color[2] = Integer.parseInt(str.substring(5, 7), 16);
				// �Զ�����ɫ
				HSSFPalette palette = wb.getCustomPalette();
				result = palette.findColor((byte) color[0], (byte) color[1],
						(byte) color[2]).getIndex();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	private boolean isRGB(String str) {
		boolean result = false;

		try {
			if (str != null && str.startsWith("#")
					&& Integer.parseInt(str.substring(1, 3), 16) >= 0
					&& Integer.parseInt(str.substring(1, 3), 16) <= 255
					&& Integer.parseInt(str.substring(3, 5), 16) >= 0
					&& Integer.parseInt(str.substring(3, 5), 16) <= 255
					&& Integer.parseInt(str.substring(5, 7), 16) >= 0
					&& Integer.parseInt(str.substring(5, 7), 16) <= 255) {
				result = true;
			}
		} catch (Exception ex) {
		}

		return result;
	}

	public static void main(String[] args) {
		 ExcelUtil eu = new ExcelUtil("d:\\7�·ݼƻ�.xls");
		 HSSFSheet sheet = eu.getSheet(1);

		 for (int r = 8; r <= 59; r++) {
			 System.out.println("row:" + r + " col:1:" + eu.read(sheet, r, 1));
			 System.out.println("row:" + r + " col:6:" + eu.read(sheet, r, 6));
			 System.out.println("row:" + r + " col:9:" + eu.read(sheet, r, 9));
			 System.out.println("row:" + r + " col:11:" + eu.read(sheet, r, 11));
			 System.out.println("row:" + r + " col:13:" + eu.read(sheet, r, 13));
			 System.out.println("row:" + r + " col:15:" + eu.read(sheet, r, 15));
		 }
		// for (int r = 1; r <= 2; r++) {
		// String str = null;
		// for (int c = 1; c <= 36; c++) {
		// str = eu.read(sheet, r, c);
		// System.out.println("row:" + r + " col:" + c + ":" + str);
		// }
		// }
		//
		// HSSFCellStyle style1 = eu.getCellStyle(sheet, 2, 1);
		// eu.write(sheet, 5, 1, style1, "style1");
		// HSSFCellStyle style2 = eu.createCellStyle();
		// eu.setCellAlignment(style2, "center", "center");
		// eu.setCellBorder(style2, false, false, false, true);
		// eu.setCellColor(style2, "#0000FF");
		// eu.setFontStyle(style2, "����", 20, "#00FF00", true, true);
		// eu.write(sheet, 5, 3, style2, "style2");
		// ExcelUtil eu = new ExcelUtil();
		// HSSFSheet sheet = eu.createSheet();
		// HSSFCellStyle style2 = eu.createCellStyle();
		// eu.setCellWrapText(style2, false);
		// eu.setSheetName(sheet, "test1");
		// eu.mergeCell(sheet, 1, 2, 1, 2);
		// eu.write(sheet, 1, 1, "value");
		// eu.write(sheet, 5, 4, style2, "11111111111111111111111");
		// eu.write(sheet, 5, 5, style2, "222222");
		// eu.autoSizeColumn(sheet, 4);
		// eu.freezePane(sheet, 3, 3);
		// eu.output("d:\\workbook.xls");

		System.out.println("done");
	}

}
