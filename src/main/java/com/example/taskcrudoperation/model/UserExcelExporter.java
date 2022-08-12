package com.example.taskcrudoperation.model;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<UserEntity> listUsers;

    public UserExcelExporter(List<UserEntity> listUsers) {
        this.listUsers = listUsers;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "id", style);
        createCell(row, 1, "email", style);
        createCell(row, 2, "password", style);
        createCell(row, 3, "mobileno", style);
        createCell(row, 4, "age", style);
        createCell(row, 5, "isverified", style);
        createCell(row, 6, "gender", style);
        createCell(row, 7, "address", style);
        createCell(row, 8, "createdDateTime", style);
        createCell(row, 9, "updatedateTime", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (UserEntity user : listUsers) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, user.getId(), style);
            createCell(row, columnCount++, user.getEmail(), style);
            createCell(row, columnCount++, user.getPassword(), style);
            createCell(row, columnCount++, user.getMobileno().toString(), style);
            createCell(row, columnCount++, user.getAge(), style);
            createCell(row, columnCount++, user.getVerified(), style);
            createCell(row, columnCount++, user.getGender(), style);
            createCell(row, columnCount++, user.getAddress(), style);
            createCell(row, columnCount++, user.getCreatedDateTime().toString(), style);
            createCell(row, columnCount++, user.getUpdateDateTime().toString(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
