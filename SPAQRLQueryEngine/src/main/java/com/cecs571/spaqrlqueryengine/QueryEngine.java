package com.cecs571.spaqrlqueryengine;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;
public class QueryEngine {
    public static void main(String[] args) {
        // location of the rdf dataset
        URI uri = null;
        try {
            uri = new URI(Paths.get("").toAbsolutePath().toUri().toString() + "/res/rows.rdf");
        } catch (URISyntaxException ex) {
            Logger.getLogger(QueryEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
