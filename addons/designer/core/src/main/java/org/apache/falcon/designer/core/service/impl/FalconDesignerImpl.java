package org.apache.falcon.designer.core.service.impl;

import java.util.List;

import org.apache.falcon.designer.core.configuration.FlowConfig;
import org.apache.falcon.designer.core.service.FalconDesigner;
import org.apache.falcon.designer.core.sysconfig.SystemConfiguration;

public class FalconDesignerImpl implements FalconDesigner {
    private final SystemConfiguration sysConfigs;

    public FalconDesignerImpl(SystemConfiguration sysConfigs) {
        this.sysConfigs = sysConfigs;
    }

@Override
    public List<String> getListOfFlowsNames() {
        // TODO Auto-generated method stub
        return null;
    }

@Override
    public List<Integer> getAllVersionsForAFlow(String flowName) {
        // TODO Auto-generated method stub
        return null;
    }

@Override
    public FlowConfig getAFlow(String flowName, int version) {
        // TODO Auto-generated method stub
        return null;
    }

@Override
    public FlowConfig createFlow(FlowConfig newFlow, Boolean overwrite) {
        // TODO Auto-generated method stub
        return null;
    }

@Override
    public String compileFlow(String flowName, Integer version) {
        // TODO Auto-generated method stub
        return null;
    }

@Override
    public String validateFlow(String flowName, Integer version) {
        // TODO Auto-generated method stub
        return null;
    }

@Override
    public  String buildFlow(String flowName, Integer version) {
        // TODO Auto-generated method stub
        return null;
    }

@Override
    public String deployFlow(String flowName, Integer version) {
        // TODO Auto-generated method stub
        return null;
    }

@Override
    public void deleteFlow(String flowName, Integer version) {
        // TODO Auto-generated method stub

    }


}
