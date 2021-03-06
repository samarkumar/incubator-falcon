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
package org.apache.falcon.designer.action.configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.falcon.designer.core.configuration.ActionConfiguration;

/**
 * Holds config data to construct a email Action. The email action allows
 * sending emails in Oozie from a workflow application. An email action must
 * provide to addresses, cc addresses (optional), a subject and a body .
 * Multiple reciepents of an email can be provided as comma separated addresses.
 */
@XmlRootElement(name = "EmailActionConfiguration")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class EmailActionConfiguration extends ActionConfiguration<EmailActionConfiguration> {

    private String to;
    private String cc;
    private String subject;
    private String body;
    private String name;


    public EmailActionConfiguration(){

    }

    public EmailActionConfiguration(String name) {
        this.name = name;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<EmailActionConfiguration> getConfigClass() {
        return EmailActionConfiguration.class;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
