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
    private void setFileURI() {
        if (this.fileName.contains(".")) {
            this.fileURI = QueryEngine.currentWorkingPath + "/" + fileName;
        } else {
            this.fileURI = QueryEngine.currentWorkingPath + "/" + fileName + OutputFormat.HTML;
        }
    }
}
