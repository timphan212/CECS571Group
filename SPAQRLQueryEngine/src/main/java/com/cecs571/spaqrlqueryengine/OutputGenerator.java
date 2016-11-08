package com.cecs571.spaqrlqueryengine;
import com.webfirmframework.wffweb.tag.html.tables.Table;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
public class OutputGenerator {
    private String fileName;
    private String fileURI;
    private HTMLFactory html;
    public OutputGenerator(String fileName) {
        this.fileName = fileName;
        setFileURI();
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
        setFileURI();
    }
    public void writeToFile(ResultSet resultSet, String[] queryVars) {
        generateHTML(resultSet, queryVars);
        try (OutputStreamWriter fileWriter = new OutputStreamWriter(
                new FileOutputStream(new File(fileURI)))) {
            fileWriter.write(html.toHtmlString());
        } catch (IOException ex) {
            Logger.getLogger(OutputGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void openFileInDefaultApp() {
        try {
            if (fileName == null || fileName.equals("")) {
                throw new FileNotFoundException("Define file name with 'setFileName' method: ");
            }
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(fileURI));
        } catch (IOException ex) {
            Logger.getLogger(OutputGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void generateHTML(ResultSet resultSet, String[] queryVars) {
        html = new HTMLFactory();
        Table table = html.addTable();
        int i = 0, numOfVars = queryVars.length;
        html.addHeader(table, queryVars);
        String[] row = new String[numOfVars];
        while (resultSet.hasNext()) {
            QuerySolution sol = resultSet.nextSolution();
            for (String var : queryVars) {
                row[i] = sol.get(var).toString();
                i = (i + 1) % numOfVars;
            }
            html.addRow(table, row);
        }
    }
    private void setFileURI() {
        if (this.fileName.contains(".")) {
            this.fileURI = QueryEngine.currentWorkingPath + "/" + fileName;
        } else {
            this.fileURI = QueryEngine.currentWorkingPath + "/" + fileName + OutputFormat.HTML;
        }
    }
}
