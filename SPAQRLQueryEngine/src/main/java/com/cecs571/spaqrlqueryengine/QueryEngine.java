package com.cecs571.spaqrlqueryengine;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;

public class QueryEngine {
    /**
     * Load the model from the rdf located at the file path
     * @param path the file path
     * @return a model object
     */
    public static Model loadModel(String path) {
        return FileManager.get().loadModel(path);
    }
    
    /**
     * Execute the query passed through the parameters using the model
     * @param query the SPARQL query to execute
     * @param model the model created from the rdf
     */
    public static void executeQuery(String query, Model model) {
        Query q = QueryFactory.create(query);
        QueryExecution qe = QueryExecutionFactory.create(q, model);
        ResultSet resultSet = qe.execSelect();
        int i = 0;
        
        while (resultSet.hasNext()) {
            QuerySolution sol = resultSet.nextSolution();
            String x = sol.get("x").toString();
            String y = sol.get("y").toString();
            System.out.println(x + " - " + y);
            i++;
        }
        
        qe.close();
        System.out.println("Count = " + i);
    }
}
