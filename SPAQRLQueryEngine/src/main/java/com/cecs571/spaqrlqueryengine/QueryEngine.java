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
        // create a model for the dataset
        Model model = FileManager.get().loadModel(uri.toString());
        // query string
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
                + "PREFIX socrata: <http://www.socrata.com/rdf/terms#> "
                + "PREFIX dcat: <http://www.w3.org/ns/dcat#> "
                + "PREFIX ods: <http://open-data-standards.github.com/2012/01/open-data-standards#> "
                + "PREFIX dcterm: <http://purl.org/dc/terms/> "
                + "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#> "
                + "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> "
                + "PREFIX foaf: <http://xmlns.com/foaf/0.1/> "
                + "PREFIX dsbase: <http://data.cityofchicago.org/resource/> "
                + "PREFIX ds: <http://data.cityofchicago.org/resource/crimes/> "
                + "PREFIX usps: <http://www.w3.org/2000/10/swap/pim/usps#> "
                + "SELECT * WHERE {"
                + " ?x socrata:rowID ?y ."
                + "}";
        // Create an application query using its factory method and the query string
        Query q = QueryFactory.create(query);
        // One execution (qe) of the query string
        QueryExecution qe = QueryExecutionFactory.create(q, model);
        // to count the number of retrieved triples
        int i = 0;
        //  Create a resultSet to store all the retrieved triples then
        //  iterate through the @resultSet.
        ResultSet resultSet = qe.execSelect();
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
