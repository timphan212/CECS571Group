package com.cecs571.spaqrlqueryengine;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;

public class QueryEngine {

    

    /**
     * Load the model from the rdf located at the file path
     *
     * @param rdfFile the file to load the model for
     * @return a model object
     */
    public Model loadModel(File rdfFile) {
        URI uri = null;
        
        try {
            uri = new URI(rdfFile.toURI().toString());
        } catch (URISyntaxException ex) {
            Logger.getLogger(QueryEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return FileManager.get().loadModel(uri.toString());
    }

    /**
     * Execute the query passed through the parameters using the model
     *
     * @param query the SPARQL query to execute
     * @param model the model created from the rdf
     */
    public void executeQuery(String query, ArrayList<Model> model) {
        Query q = QueryFactory.create(query);
        QueryExecution qe = QueryExecutionFactory.create(q, model.get(0));
        ResultSet resultSet = qe.execSelect();

        // List the projection variables
        List<String> queryVars = q.getResultVars();

        // generate an output file for the resultset
        OutputGenerator outputGen = new OutputGenerator("result");
        outputGen.writeToFile(resultSet, queryVars.toArray(new String[queryVars.size()]));

        // open last generated output file
        outputGen.openFileInDefaultApp();

        // close query execution
        qe.close();
    }
}
