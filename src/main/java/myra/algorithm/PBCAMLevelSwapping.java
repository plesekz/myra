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
import static myra.rule.pittsburgh.LevelPheromonePolicy.PHEROMONE_POLICY;
import myra.rule.pittsburgh.OrderedLevelPheromonePolicy;

/**
 * This class represents the <code><i>c</i>Ant-Miner<sub>PB</sub> with level swapping</code>
 * implementation, as described in the paper:
 * 
 * @author Zdenek Plesek
 */
public class PBCAMLevelSwapping extends PittsburghContinuousAntMiner {
    @Override
    protected void defaults() {
        // configuration not set via command line

        // default configuration values
        super.defaults();

        CONFIG.set(PHEROMONE_POLICY, new OrderedLevelPheromonePolicy());
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
        PBCAMLevelSwapping algorithm =
                new PBCAMLevelSwapping();
        algorithm.run(args);
    }
}