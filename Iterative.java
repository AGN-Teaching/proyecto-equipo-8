import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Clase Iterative que gestiona un menú y permite realizar operaciones en un archivo Excel.
 */
public class Iterative extends MenuManager 
    {
        private static final String FILE_NAME = "menu_taqueria.xlsx";
        private static Workbook workbook;
        private static FileInputStream fis;
        private static FileOutputStream fos;

        /**
         * Método principal que muestra un menú interactivo y gestiona las opciones del usuario.
         */
        public static void main(String[] args) 
            {
                Scanner scanner = new Scanner(System.in);
                while (true) 
                    {
                        System.out.println("\n1. Cargar archivo");
                        System.out.println("2. Leer e imprimir archivo");
                        System.out.println("3. Agregar artículo");
                        System.out.println("4. Modificar artículo");
                        System.out.println("5. Eliminar artículo");
                        System.out.println("6. Salir");
                        System.out.println("\nSeleccione una opción: ");
                        String option = scanner.nextLine();
                        System.out.println("\n");

                        switch (option) 
                            {
                                case "1":
                                    loadFile();
                                    break;
                                case "2":
                                    readFile();
                                    break;
                                case "3":
                                    addArticle(scanner);
                                    break;
                                case "4":
                                    modifyArticle(scanner);
                                    break;
                                case "5":
                                    deleteArticle(scanner);
                                    break;
                                case "6":
                                    closeResources();
                                    System.exit(0);
                                default:
                                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
                            }
                    }
            }

    /**
     * Carga del archivo de Excel desde el sistema de archivos (que se encuentra en la carpeta del proyecto)
     */
        public static void loadFile() 
            {
                try 
                    {
                        fis = new FileInputStream(FILE_NAME);
                        workbook = new XSSFWorkbook(fis);
                        System.out.println("Archivo cargado exitosamente.");
                    } 
                catch (IOException e) 
                    {
                        e.printStackTrace();
                    }
            }

        /**
        * Método que lee y muestra el contenido del archivo Excel en la consola.
        */
        public static void readFile() 
        {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) 
                {
                    Cell idCell = row.getCell(0);
                    if (idCell != null) 
                        {
                            if (idCell.getCellTypeEnum() == CellType.STRING) 
                                {
                                    System.out.print(idCell.getStringCellValue() + "\t\t\t");
                                } 
                                else if (idCell.getCellTypeEnum() == CellType.NUMERIC) 
                                    {
                                        System.out.print((int) idCell.getNumericCellValue() + "\t\t\t");
                                    }
                
                            for (int i = 1; i < row.getLastCellNum(); i++) 
                                {
                                    Cell cell = row.getCell(i);
                                    if (cell != null) 
                                        {
                                            System.out.print(cell.toString() + "\t\t\t");
                                        }
                                }
                            System.out.println();
                        }
                }
        }

        /**
        * Método que agrega un nuevo artículo al archivo de Excel
        */
        public static void addArticle(Scanner scanner) 
            {
                Sheet sheet = workbook.getSheetAt(0);
                int lastDataRow = getLastDataRow(sheet);
                Row newRow = sheet.createRow(lastDataRow + 1);
            
                // Se obtiene el ID del último artículo y añade 1 para el nuevo artículo
                int lastId = (int) sheet.getRow(lastDataRow).getCell(0).getNumericCellValue();
                Cell idCell = newRow.createCell(0, CellType.NUMERIC);
                idCell.setCellValue(lastId + 1); // ID
                CellStyle style = workbook.createCellStyle();
                style.setDataFormat((short)1); //Conversión a entero
                idCell.setCellStyle(style);
            
                System.out.print("Introduce el nombre del nuevo artículo: ");
                String name = scanner.nextLine();
                newRow.createCell(1).setCellValue(name);
            
                System.out.print("Introduce el precio del nuevo artículo: ");
                double price = scanner.nextDouble();
                newRow.createCell(2).setCellValue(price);
            
                scanner.nextLine(); 
                System.out.print("Introduce la descripción del nuevo artículo: ");
                String description = scanner.nextLine();
                newRow.createCell(3).setCellValue(description);
            
                saveChanges();
            }

        /**
        * Método que modifica un artículo ya existente en el archivo de Excel
        */

        public static void modifyArticle(Scanner scanner) 
            {
                Sheet sheet = workbook.getSheetAt(0);

                System.out.print("Introduce el ID del artículo a modificar: ");
                int id = scanner.nextInt();

                Row row = sheet.getRow(id - 1);

                
                if (row != null) 
                    {
                        scanner.nextLine(); 
                        System.out.print("Introduce el nuevo nombre del artículo: ");
                        String name = scanner.nextLine();
                        row.getCell(1).setCellValue(name);

                        System.out.print("Introduce el nuevo precio del artículo: ");
                        double price = scanner.nextDouble();
                        row.getCell(2).setCellValue(price);

                        scanner.nextLine(); 
                        System.out.print("Introduce la nueva descripción del artículo: ");
                        String description = scanner.nextLine();
                        row.getCell(3).setCellValue(description);

                        saveChanges();
                    } 
                else 
                    {
                        System.out.println("No se encontró un artículo con ese ID.");
                    }
            }

        /**
        * Método que elimina un artículo del archivo de Excel
        */
        public static void deleteArticle(Scanner scanner) 
            {
                System.out.print("Introduce el ID del artículo a eliminar: ");
                int idToDelete = scanner.nextInt();
                scanner.nextLine();
            
                Sheet sheet = workbook.getSheetAt(0);
                Row rowToDelete = null;
                
                // Función que busca la fila con el ID del artículo a eliminar
                for (Row row : sheet) 
                    {
                        Cell idCell = row.getCell(0);
                        if (idCell.getCellTypeEnum() == CellType.NUMERIC) 
                            {
                                if ((int) idCell.getNumericCellValue() == idToDelete) 
                                    {
                                        rowToDelete = row;
                                        break;
                                    }
                            } 
                        else
                            {
                                System.out.println("La celda contiene un valor no numérico.");
                            }
                    }
                
                if (rowToDelete != null) 
                    {
                        int rowIndex = rowToDelete.getRowNum();
                        sheet.removeRow(rowToDelete);
                        if (rowIndex >= 0 && rowIndex < sheet.getLastRowNum()) 
                            {
                                sheet.shiftRows(rowIndex + 1, sheet.getLastRowNum(), -1);
                            }
                        reorganizeIDs(sheet); // Reorganiza los IDs después de eliminar una fila
                        saveChanges();
                        System.out.println("Artículo eliminado exitosamente.");
                    } 
                else 
                    {
                        System.out.println("No se encontró un artículo con ese ID.");
                    }
            }

        /**
        * Método para ordenar los ID de cada artículo despúes de alguna modificación
        */
        public static void reorganizeIDs(Sheet sheet) 
            {
                int id = 1;
                for (Row row : sheet) 
                    {
                        row.getCell(0).setCellValue(id++);
                    }
            }

        /**
        * Método para guardar los cambios en el archivo de Excel2
        */
        public static void saveChanges() 
            {
                try 
                    {
                        fos = new FileOutputStream(FILE_NAME);
                        workbook.write(fos);
                        fos.close();
                        System.out.println("Cambios guardados exitosamente.");
                    } 
                catch (IOException e) 
                    {
                        e.printStackTrace();
                    }
            }

        /**
        * Método que cierra los recursos utilizados, como el flujo de entrada y salida y el libro de trabajo Excel.
        */
        public static void closeResources() 
            {
                try 
                    {
                        if (fis != null) fis.close();
                        if (fos != null) fos.close();
                        if (workbook != null) workbook.close();
                    } 
                catch (IOException e) 
                    {
                        e.printStackTrace();
                    }
            }
    }
