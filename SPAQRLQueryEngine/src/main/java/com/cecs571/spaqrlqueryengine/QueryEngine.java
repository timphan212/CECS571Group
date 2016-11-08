package com.cecs571.spaqrlqueryengine;

import java.nio.file.Paths;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;

public class QueryEngine {

    private String currentWorkingPath;

    /**
     * Class to process SPARQL queries
     */
    public QueryEngine() {
        // initialize the location for the project
        currentWorkingPath = Paths.get("").toAbsolutePath().toString();
    }

    /**
     * Load the model from the rdf located at the file path
     *
     * @param path the file path
     * @return a model object
     */
    public Model loadModel(String path) {
        return FileManager.get().loadModel(path);
    }

    /**
     * Execute the query passed through the parameters using the model
     *
     * @param query the SPARQL query to execute
     * @param model the model created from the rdf
     */
    public void executeQuery(String query, Model model) {
        Query q = QueryFactory.create(query);
        QueryExecution qe = QueryExecutionFactory.create(q, model);
        ResultSet resultSet = qe.execSelect();
        
        // Generate output file
        
        
        // close query execution
        qe.close();
    }

    /**
     * Get the project's location
     *
     * @return
     */
    public String getCurrentWorkingPath() {
        return currentWorkingPath;
    }
}
