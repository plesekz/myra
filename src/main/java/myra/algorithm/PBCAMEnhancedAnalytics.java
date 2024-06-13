/*
 * PittsburghContinuousAntMiner.java
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

package myra.algorithm;

import myra.classification.ClassificationModel;
import myra.classification.rule.ClassificationRule;
import myra.datamining.Dataset;
import myra.datamining.Model;
import myra.rule.RuleList;
import myra.util.Logger;

public class PBCAMEnhancedAnalytics extends PittsburghContinuousAntMiner {

    @Override
    protected void logRules(Dataset dataset, RuleList list){
        super.logRules(dataset, list);
        Logger.log(">>> Coverage over dataset per rule%n");

       // float scalingTerm = 0;
        float meanRule = 0;
        float[] totalCov = new float[list.rules().length];
        for (int j = 0; j < list.rules().length; j++) {
            ClassificationRule r = (ClassificationRule) list.rules()[j];
            for(int i = 0; i<r.covered().length;i++){
                totalCov[j]+=r.covered()[i];
            }
        //    scalingTerm+= totalCov[j]/(dataset.size()*Math.pow(2, j+1));
            meanRule+= (j+1)*totalCov[j];
            Logger.log("%2s. %5s %n", (j+1), totalCov[j]/dataset.size());
        }
        meanRule/=dataset.size();
        boolean total_monotonicity = true;
        float granular_monotonicity = 1.0f;
        for (int i = 1; i < list.rules().length; i++){
            if (totalCov[i]>totalCov[i-1])
                total_monotonicity=false;
            else
                granular_monotonicity+=1.0f;
        }
        granular_monotonicity/=list.rules().length;

     //   Logger.log("Scaling term: %5s%n", scalingTerm);
        Logger.log("Mean i-th rule used for classification: %2s%n",meanRule);
        Logger.log("Total monotonicity: %b%n", total_monotonicity);
        Logger.log("Granular monotonicity: %2s%n", granular_monotonicity);
    }

    @Override
    protected void test(Dataset dataset, Model model){
        super.test(dataset, model);

        if (((ClassificationModel) model).raw() instanceof RuleList) {
            RuleList rl = (RuleList) ((ClassificationModel) model).raw();
            rl.apply(dataset);
            logRules(dataset, rl);
        }
    }
    public static void main(String[] args) throws Exception {
        PBCAMEnhancedAnalytics algorithm =
                new PBCAMEnhancedAnalytics();
        algorithm.run(args);
    }
}
