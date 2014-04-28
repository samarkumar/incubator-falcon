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
