package extraction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import representations.Variable;

public class DBReader {

    protected Set<Variable> variables;

/**
  * Constructeur de la classe
  * @param variables , qui est de type Set de Variable.
  *
  */

    public DBReader(Set<Variable> variables) {
        this.variables = variables;
    }



     /**
        *  Méhode perettant de importer la base de donnée.
        * @param filename , qui est un String.
        * @return res , qui est une base de donnée.
        *
        */
    public DataBase importDB (String filename) {
        try (BufferedReader reader = new BufferedReader (new FileReader (filename))) {
            DataBase res = this.readDB(reader);
            reader.close();
            return res;
        } catch (IOException e) {
        	System.out.println("Erreur lors de l'importation : " + e);
					return null;
        }
    }

    /**
    * Méthode perettant de lire la base de donnée.
    *
    *
    */

    public DataBase readDB(BufferedReader in) throws IOException {
        // Reading variables
        List<Variable> orderedVariables = new ArrayList<>();
        String variableLine = in.readLine();
        for (String variableName: variableLine.split(";")) {
            boolean found = false;
            for (Variable variable: this.variables) {
                if (variable.getNom().equals(variableName)) {
                    orderedVariables.add(variable);
                    found = true;
                    break;
                }
            }
            if ( ! found ) {
                throw new IOException("Unknown variable name: " + variableName);
            }
        }
        // Reading instances
        List<Map<Variable, String>> instances = new ArrayList<>();
        String line;
        int lineNb = 1;
        while ( (line = in.readLine()) != null ) {
            String [] parts = line.split(";");
            if (parts.length != orderedVariables.size()) {
                throw new IOException("Wrong number of fields on line " + lineNb);
            }
            Map<Variable, String> instance = new HashMap<> ();
            for (int i = 0; i < parts.length; i++) {
                instance.put(orderedVariables.get(i), parts[i]);
            }
            instances.add(instance);
            lineNb++;
        }
        return new DataBase(orderedVariables, instances);
    }

}
