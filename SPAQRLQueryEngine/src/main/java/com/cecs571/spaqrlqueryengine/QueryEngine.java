package com.cecs571.spaqrlqueryengine;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;

public class QueryEngine {
    public static Model loadModel(String path) {
        return FileManager.get().loadModel(path);
    }
    
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
