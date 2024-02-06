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

import static myra.Config.CONFIG;
import static myra.IterativeActivity.MAX_ITERATIONS;
import static myra.IterativeActivity.STAGNATION;
import static myra.Scheduler.COLONY_SIZE;
import static myra.datamining.IntervalBuilder.DEFAULT_BUILDER;
import static myra.datamining.IntervalBuilder.MAXIMUM_LIMIT;
import static myra.datamining.IntervalBuilder.MINIMUM_CASES;
import static myra.rule.Assignator.ASSIGNATOR;
import static myra.rule.Heuristic.DEFAULT_HEURISTIC;
import static myra.rule.Heuristic.DYNAMIC_HEURISTIC;
import static myra.rule.ListMeasure.DEFAULT_MEASURE;
import static myra.rule.ListPruner.DEFAULT_LIST_PRUNER;
import static myra.rule.Pruner.DEFAULT_PRUNER;
import static myra.rule.Rule.DEFAULT_RULE;
import static myra.rule.RuleFunction.DEFAULT_FUNCTION;
import static myra.rule.pittsburgh.FindRuleListActivity.UNCOVERED;
import static myra.rule.pittsburgh.LevelPheromonePolicy.EVAPORATION_FACTOR;
import static myra.rule.pittsburgh.LevelPheromonePolicy.P_BEST;

import myra.classification.attribute.BoundarySplit;
import myra.classification.attribute.MDLSplit;
import myra.classification.rule.ClassificationRule;
import myra.classification.rule.EntropyHeuristic;
import myra.classification.rule.MajorityAssignator;
import myra.classification.rule.OrderedPessimisticAccuracy;
import myra.classification.rule.function.SensitivitySpecificity;
import myra.rule.BacktrackPruner;
import myra.rule.ListPruner;

/**
 * This class represents the <code><i>c</i>Ant-Miner<sub>PB</sub> with ordered list</code>
 * implementation, as described in the paper:
 * 
 * @author Zdenek Plesek
 */
public class PBCAntMinerFallingRule extends PittsburghContinuousAntMiner {
    @Override
    protected void defaults() {
        super.defaults();

        // configuration not set via command line

        // default configuration values

        CONFIG.set(DEFAULT_MEASURE, new OrderedPessimisticAccuracy());
    }
}