package util;

import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.*;

import jxl.*;
import jxl.write.*;

/**
 *
 * @author joao.oliveira
 */
public class ExcelExporter {

   public void fillData(JTable table, File file) {

        try {

            WritableWorkbook workbook1 = Workbook.createWorkbook(file);
            WritableSheet sheet1 = workbook1.createSheet("First Sheet", 0);
            TableModel model = table.getModel();

            for (int i = 0; i < model.getColumnCount(); i++) {
                Label column = new Label(i, 0, model.getColumnName(i));
                sheet1.addCell(column);
            }
            int j = 0;
            for (int i = 0; i < model.getRowCount(); i++) {
                for (j = 0; j < model.getColumnCount(); j++) {
                    
                    Object value = model.getValueAt(i, j);
                    String valor = value != null ? value.toString() : "";
                    Label row = new Label(j, i + 1,valor);
                    sheet1.addCell(row);
                }
            }
            workbook1.write();
            workbook1.close();
        } catch (IOException | WriteException ex) {
            
         JOptionPane.showMessageDialog(null, "Erro: " + ex, "Erro", JOptionPane.ERROR_MESSAGE);
         
        }

    }
}
