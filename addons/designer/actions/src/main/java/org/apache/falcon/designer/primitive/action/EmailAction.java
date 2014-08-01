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
package org.apache.falcon.designer.primitive.action;

import org.apache.falcon.designer.action.configuration.EmailActionConfiguration;
import org.apache.falcon.designer.primitive.Action;
import org.apache.falcon.designer.primitive.Code;
import org.apache.falcon.designer.primitive.Message;

/**
 * EmailAction Primitive containing implementation to compile.
 */
public class EmailAction extends Action<EmailAction, EmailActionConfiguration> {
    // flow grouping .. show come from the flow
    private static final String NAMESPACE = "EmailAction";

    // flow name .. show come from the flow
    private static final String ENTITY = "1232";

    private EmailActionConfiguration emailConfig;

    public EmailAction(EmailActionConfiguration config) {
        this.emailConfig = config;
    }

    @Override
    public EmailActionConfiguration getConfiguration() {
        return emailConfig;
    }

    @Override
    public void setConfiguration(EmailActionConfiguration config) {
        this.emailConfig = config;

    }

    @Override
    public boolean hasOutput() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected EmailAction copy() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Message> validate() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Code doCompile() {
        getConfiguration().getBody();
        return null;
    }

    @Override
    protected EmailAction doOptimize() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getNamespace() {
        return NAMESPACE;
    }

    @Override
    public String getEntity() {
        return ENTITY;
    }

}
