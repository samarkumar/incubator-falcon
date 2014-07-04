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

import org.apache.falcon.designer.configuration.ActionConfiguration;

public class EmailActionConfiguration extends ActionConfiguration {

  private String toEmails;
  private String ccEmails;
  private String subjects;
  private String body;

  private static final String name = "Email";

  public String getToEmails() {
    return toEmails;
  }

  public void setToEmails(String toEmails) {
    this.toEmails = toEmails;
  }

  public String getCcEmails() {
    return ccEmails;
  }

  public void setCcEmails(String ccEmails) {
    this.ccEmails = ccEmails;
  }

  public String getSubjects() {
    return subjects;
  }

  public void setSubjects(String subjects) {
    this.subjects = subjects;
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
}
