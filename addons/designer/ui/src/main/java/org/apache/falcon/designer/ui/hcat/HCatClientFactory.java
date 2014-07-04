/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.falcon.designer.ui.hcat;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hcatalog.api.HCatClient;
import org.apache.hcatalog.cli.SemanticAnalysis.HCatSemanticAnalyzer;
import org.apache.hcatalog.common.HCatException;

public class HCatClientFactory {

  private static HCatClient hcatInstance = null;

  public static HCatClient getHCatClient(String metastoreUrl)
      throws HCatException {

    if (hcatInstance == null) {
      HiveConf hcatConf = new HiveConf();
      
      hcatConf.set("hive.metastore.local", "false");
      hcatConf.setVar(HiveConf.ConfVars.METASTOREURIS, metastoreUrl);
      hcatConf.setIntVar(HiveConf.ConfVars.METASTORETHRIFTCONNECTIONRETRIES, 3);
      hcatConf.set(HiveConf.ConfVars.SEMANTIC_ANALYZER_HOOK.varname,
          HCatSemanticAnalyzer.class.getName());
      hcatConf.set(HiveConf.ConfVars.HIVE_SUPPORT_CONCURRENCY.varname, "false");
      hcatConf.set(HiveConf.ConfVars.PREEXECHOOKS.varname, "");
      hcatConf.set(HiveConf.ConfVars.POSTEXECHOOKS.varname, "");
      
      hcatInstance = HCatClient.create(hcatConf);
      
    }
    return hcatInstance;

  }

}
