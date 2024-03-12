/*
 * LevelPheromonePolicy.java
 * (this file is part of MYRA)
 * 
 * Copyright 2008-2015 Fernando Esteban Barril Otero
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package myra.rule.pittsburgh;

import myra.classification.rule.ClassificationRule;
import myra.rule.Graph;
import myra.rule.Rule;
import myra.rule.Graph.Entry;
import myra.rule.RuleList;
import myra.util.MapSort;

/**
 * This class extends Level Pheromone Policy which is responsible for
 * maintaining the pheromone values of the construction graph.
 * 
 * This class is altered so that the levels in the matrix are re-arranged
 * according to the rule coverage.
 * 
 * @author Zdenek Plesek
 */
public class OrderedLevelPheromonePolicy extends LevelPheromonePolicy {

    /**
     * Updates the pheromone values, increasing the pheromone according to the
     * rules of the specified <code>RuleList</code> instance. Evaporation is
     * also performed as part of the update procedure.
     * 
     * @param graph
     *            the construction graph.
     * @param list
     *            the list of rules.
     */
    @Override
    public void update(Graph graph, RuleList list) {
        super.update(graph, list);
        //System.out.println("Re-ordering policy triggered");

        // Get coverage of all rules as an array
        Rule[] rules = list.rules();
        float[] coverage = new float [rules.length];
        for(int i = 0; i<rules.length;i++){
            ClassificationRule c = (ClassificationRule) rules[i];
            float size = 0;
            for (int j = 0; j<c.covered().length; j++){
                coverage[i]+=c.covered()[j];
                size+=c.covered()[j];
                size+=c.uncovered()[j];
            }
            coverage[i]=coverage[i]/size;
        }
        // Get indices of the rule in the new ordering, i.e. mapping from the coverage array to a sorted array
        int[] mapping = MapSort.sort(coverage);
        // System.out.println("New mapping:");
        //for(int i = 0; i<mapping.length; i++){
        //    System.out.print(mapping[i]+" ");
        //}
        // Pass this mapping to all entries, and have them change order of values in them according to the mapping
        Entry[][] matrix = graph.matrix();
        for(int i = 0; i<graph.size(); i++){
            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j]!=null)
                matrix[i][j].reorder(mapping);
            }
        }
    }
}