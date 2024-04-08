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

        float totalSum = 0;
        for (int j = 0; j < list.rules().length; j++) {
            ClassificationRule r = (ClassificationRule) list.rules()[j];
            float sum = 0;
            for(int i = 0; i<r.covered().length;i++){
                sum+=r.covered()[i];
            }
            totalSum+= sum/(dataset.size()*Math.pow(2, j+1));
            Logger.log("%5s %n", sum/dataset.size());
        }
        Logger.log("Weighted coverage sum: %5s", totalSum);
        Logger.log("%n");
    }

    @Override
    protected void test(Dataset dataset, Model model){
        super.test(dataset, model);

        if (((ClassificationModel) model).raw() instanceof RuleList) {
            logRules(dataset, (RuleList) ((ClassificationModel) model).raw());
        }
    }
}
