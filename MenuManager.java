import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Esta clase se utiliza para gestionar el menú de la taquería.
 */

public class MenuManager 
    {
        private static final String FILE_NAME = "menu_taqueria.xlsx"; // Nombre del archivo de Excel que contiene el menú

        /**
         * Método principal que se ejecuta al iniciar la aplicación.
         */
        public static void main(String[] args) 
            {
                Workbook workbook = null;
                FileInputStream fis = null;
                FileOutputStream fos = null;

                try 
                    {
                        // Carga del archivo de Excel
                        fis = new FileInputStream(FILE_NAME);
                        workbook = new XSSFWorkbook(fis);

                        // Acceso a la hoja del menú
                        Sheet sheet = workbook.getSheetAt(0);

                        // Agregar un nuevo alimento al menú
                        int lastDataRow = getLastDataRow(sheet);
                        Row newRow = sheet.createRow(lastDataRow + 1);
                        newRow.createCell(0).setCellValue(lastDataRow + 2); // ID
                        newRow.createCell(1).setCellValue("Nuevo alimento"); // Nombre
                        newRow.createCell(2).setCellValue(12.5); // Precio
                        newRow.createCell(3).setCellValue("Descripción del nuevo alimento"); // Descripción

                        // Modificación de un alimento existente en el menú
                        Row row = sheet.getRow(0);
                        if (row != null) 
                            {
                                Cell cell = row.getCell(1);
                                if (cell != null) 
                                    {
                                        cell.setCellValue("Nombre modificado"); // Modificar el nombre del alimento
                                    }
                            }

                        // Eliminación de un alimento del menú 
                        int rowIndex = 1;
                        Row rowToRemove = sheet.getRow(rowIndex);
                        if (rowToRemove != null) 
                            {
                                sheet.removeRow(rowToRemove);
                                if (rowIndex <= sheet.getLastRowNum()) 
                                    {
                                        sheet.shiftRows(rowIndex + 1, sheet.getLastRowNum(), -1);
                                    }
                            }

                        // Funciones para guardar los cambios y cerrar el archivo
                        fos = new FileOutputStream(FILE_NAME);
                        workbook.write(fos);
                    } 
                    catch (Exception e) 
                        {
                            e.printStackTrace();
                        } 
                    finally 
                        {
                            try 
                                {
                                    if (fis != null) 
                                        {
                                            fis.close();
                                        }
                                    if (fos != null)
                                        {
                                            fos.close();
                                        }
                                    if (workbook != null)
                                        {
                                            workbook.close();
                                        }
                                } 
                                catch (IOException e) 
                                    {
                                        e.printStackTrace();
                                    }
                        }
            }

        /**
         * Este método se utiliza para obtener el índice de la última fila de datos en la hoja dada.
         *
         */

        public static int getLastDataRow(Sheet sheet) 
            {
                int lastDataRow = -1;
                for (Row row : sheet) 
                    {
                        for (Cell cell : row) 
                            {
                                if (cell.getCellTypeEnum() != CellType.BLANK) 
                                    {
                                        lastDataRow = row.getRowNum();
                                        break;
                                    }
                            }
                    }
                return lastDataRow;
            }
            
    }
