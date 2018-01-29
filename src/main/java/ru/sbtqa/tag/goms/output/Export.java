package ru.sbtqa.tag.goms.output;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.sbtqa.tag.goms.process.tokens.Token;
import ru.sbtqa.tag.goms.utils.Regex;
import ru.sbtqa.tag.goms.utils.Templates;

public class Export {

    private static final short DEFAULT_ROW_HEIGHT = 350;
    private static final int HEIGHT_COEFFICIENT = 20;

    private static final XSSFColor GREY = new XSSFColor(new Color(235, 235, 235));

    private static final Logger LOGGER = Logger.getLogger( Export.class.getName() );
    
    private enum COLUMNS {
        A(0), B(1), C(2), D(3), E(4), F(5), G(6);

        private final int index;

        COLUMNS(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }
    
    public static void writeIntoExcel(String filePath, Map<String, List<Token>> cache) {
        XSSFWorkbook book = new XSSFWorkbook();
        File file = new File(filePath);
        if (file.exists()) {
            FileInputStream excelFile;
            try {
                excelFile = new FileInputStream(file);
                // remove all of sheets from existing file
                try (Workbook existsBook = new XSSFWorkbook(excelFile)) {
                    // remove all of sheets from existing file
                    for (int i = existsBook.getNumberOfSheets() - 1; i >= 0; i--) {
                        existsBook.removeSheetAt(i);
                    }
                    try (FileOutputStream output = new FileOutputStream(file)) {
                        existsBook.write(output);
                    }
                }
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, e.getMessage(), e);
            }
        }

        try {
            for (Map.Entry<String, List<Token>> scenario : cache.entrySet()) {
                Sheet sheet = book.createSheet(scenario.getKey());
                sheet.setDisplayGridlines(false);
                sheet.setDefaultRowHeight(DEFAULT_ROW_HEIGHT);

                Row row;
                int rowCount = 0;
                int backgroundCount = 0;

                // MAIN HEADER
                row = sheet.createRow(rowCount);
                Cell mainHeader = row.createCell(COLUMNS.A.getIndex());
                sheet.addMergedRegion(
                        new CellRangeAddress(rowCount, rowCount, COLUMNS.A.getIndex(), COLUMNS.G.getIndex()));
                mainHeader.setCellValue(scenario.getKey());
                mainHeader.setCellStyle(getMainHeaderCellStyle(book));
                row.setHeight((short) (DEFAULT_ROW_HEIGHT * 1.5));

                rowCount++;

                // HEADERS TITLE
                row = sheet.createRow(rowCount);
                createHeaderCell(book, row, COLUMNS.A, "Тип");
                createHeaderCell(book, row, COLUMNS.B, "Время");
                createHeaderCell(book, row, COLUMNS.C, "");
                createHeaderCell(book, row, COLUMNS.D, "");
                createHeaderCell(book, row, COLUMNS.E, "Описание");
                createHeaderCell(book, row, COLUMNS.F, "Количество действий");
                createHeaderCell(book, row, COLUMNS.G, "Комментарий");
                row.setHeight((short) (DEFAULT_ROW_HEIGHT * 2));

                rowCount++;

                // CONTENT
                for (Token token : scenario.getValue()) {
                    row = sheet.createRow(rowCount);

                    // add Symbol
                    Cell symbol = row.createCell(COLUMNS.A.getIndex());
                    symbol.setCellValue(token.getOperator().getSymbol().toLowerCase());

                    // add Time
                    Cell time = row.createCell(COLUMNS.B.getIndex());
                    time.setCellValue((float) token.getOperator().getTime() / 1000 * token.getMultiplier());

                    // add Empties
                    Cell empty1 = row.createCell(COLUMNS.C.getIndex());
                    Cell empty2 = row.createCell(COLUMNS.D.getIndex());

                    // add Operator Name as Discription
                    Cell description1 = row.createCell(COLUMNS.E.getIndex());
                    description1.setCellValue(token.getOperator().getDescription());

                    // add Multiplier
                    Cell multiplier = row.createCell(COLUMNS.F.getIndex());
                    multiplier.setCellValue(token.getMultiplier());

                    // add step name as Description
                    Cell description2 = row.createCell(COLUMNS.G.getIndex());
                    // merge cell if it is open page
                    if ("O".equals(token.getOperator().getSymbol())) {
                        sheet.addMergedRegion(
                                new CellRangeAddress(rowCount, rowCount, COLUMNS.A.getIndex(), COLUMNS.G.getIndex()));
                        description2 = row.getCell(COLUMNS.A.getIndex());
                        description2.setCellStyle(getScreenCellStyle(book));
                        backgroundCount = -1;
                        String pageName = Regex.get(token.getStep(), Templates.REGEX_INQUOTES);
                        token.setStep("Экран " + pageName);
                    }
                    
                    description2.setCellValue(token.getStep());

                    boolean isPainted = false;
                    if ((backgroundCount & 1) == 0) {
                        isPainted = true;
                    }

                    if (backgroundCount >= 0) {
                        symbol.setCellStyle(getContentCellStyle(book, HorizontalAlignment.RIGHT, isPainted));
                        time.setCellStyle(getContentCellStyle(book, HorizontalAlignment.RIGHT, isPainted));
                        empty1.setCellStyle(getContentCellStyle(book, HorizontalAlignment.RIGHT, isPainted));
                        empty2.setCellStyle(getContentCellStyle(book, HorizontalAlignment.RIGHT, isPainted));
                        description1.setCellStyle(getContentCellStyle(book, HorizontalAlignment.LEFT, isPainted));
                        multiplier.setCellStyle(getContentCellStyle(book, HorizontalAlignment.RIGHT, isPainted));
                        description2.setCellStyle(getContentCellStyle(book, HorizontalAlignment.LEFT, isPainted));
                    }

                    backgroundCount++;
                    rowCount++;
                }

                // add Total scenario sum sec
                row = sheet.createRow(rowCount);
                row.setHeight((short) (DEFAULT_ROW_HEIGHT * 2));
                Cell totalsNameSec = row.createCell(COLUMNS.A.getIndex());
                totalsNameSec.setCellValue("Итого (Сек.):");
                totalsNameSec.setCellStyle(getHeaderCellStyle(book));
                Cell totals = row.createCell(COLUMNS.B.getIndex());
                totals.setCellType(CellType.FORMULA);
                String strFormula = "SUM(" + COLUMNS.B.toString() + "1:" + COLUMNS.B.toString() + rowCount + ")";
                totals.setCellFormula(strFormula);
                totals.setCellStyle(getScreenCellStyle(book));
                rowCount++;

                // add Total scenario sum min
                row = sheet.createRow(rowCount);
                row.setHeight((short) (DEFAULT_ROW_HEIGHT * 2));
                Cell totalsNameMin = row.createCell(COLUMNS.A.getIndex());
                totalsNameMin.setCellValue("Итого (Мин.):");
                totalsNameMin.setCellStyle(getHeaderCellStyle(book));
                Cell totalsMin = row.createCell(COLUMNS.B.getIndex());
                totalsMin.setCellType(CellType.FORMULA);
                strFormula = COLUMNS.B.toString() + rowCount + "/60";
                totalsMin.setCellFormula(strFormula);
                totalsMin.setCellStyle(getScreenCellStyle(book));

                sheet.autoSizeColumn(COLUMNS.A.getIndex());
                sheet.autoSizeColumn(COLUMNS.E.getIndex());
                sheet.autoSizeColumn(COLUMNS.F.getIndex());
                sheet.autoSizeColumn(COLUMNS.G.getIndex());

            }

            book.write(new FileOutputStream(file));
            book.close();
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    private static CellStyle getMainHeaderCellStyle(Workbook book) {
        CellStyle cellStyle = book.createCellStyle();

        Font font = book.createFont();
        font.setBold(false);
        font.setFontHeight((short) (16 * HEIGHT_COEFFICIENT));

        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFont(font);

        return cellStyle;
    }

    private static CellStyle getHeaderCellStyle(Workbook book) {
        CellStyle cellStyle = book.createCellStyle();

        Font font = book.createFont();
        font.setBold(false);
        font.setFontHeight((short) (11 * HEIGHT_COEFFICIENT));
        font.setColor(IndexedColors.BROWN.getIndex());

        cellStyle.setFont(font);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setWrapText(true);

        return cellStyle;
    }

    private static CellStyle getScreenCellStyle(Workbook book) {
        CellStyle cellStyle = book.createCellStyle();

        Font font = book.createFont();
        font.setBold(true);
        font.setFontHeight((short) (11 * HEIGHT_COEFFICIENT));

        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(BorderStyle.THIN);

        return cellStyle;
    }

    private static CellStyle getContentCellStyle(XSSFWorkbook book, HorizontalAlignment align, boolean isPainted) {
        XSSFCellStyle cellStyle = book.createCellStyle();

        Font font = book.createFont();
        font.setFontHeight((short) (11 * HEIGHT_COEFFICIENT));

        cellStyle.setFont(font);
        cellStyle.setAlignment(align);

        if (isPainted) {
            cellStyle.setFillForegroundColor(GREY);
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }
        return cellStyle;
    }

    private static void createHeaderCell(Workbook book, Row row, COLUMNS column, String text) {
        Cell symbol1 = row.createCell(column.getIndex());
        symbol1.setCellValue(text);
        symbol1.setCellStyle(getHeaderCellStyle(book));
    }
}
