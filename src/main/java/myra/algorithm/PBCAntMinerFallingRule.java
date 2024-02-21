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
import static myra.rule.ListMeasure.DEFAULT_MEASURE;
import myra.classification.rule.OrderedPessimisticAccuracy;

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
    /**
     * <code><i>c</i>Ant-Miner<sub>PB</sub></code> entry point.
     * 
     * @param args
     *            command-line arguments.
     * 
     * @throws Exception
     *             If an error occurs &mdash; e.g., I/O error.
     */
    public static void main(String[] args) throws Exception {
        PBCAntMinerFallingRule algorithm =
                new PBCAntMinerFallingRule();
        algorithm.run(args);
    }
}