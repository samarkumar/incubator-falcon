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
package org.apache.falcon.designer.action.serde.impl;

import java.io.IOException;

import org.apache.falcon.designer.action.configuration.EmailActionConfiguration;
import org.apache.falcon.designer.action.serde.ActionSerDe;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class EmailActionSerde implements ActionSerDe<EmailActionConfiguration> {

  private ObjectMapper mapper = new ObjectMapper();

  @Override
  public String serialize(EmailActionConfiguration act) {

    String returnJsonString;
    try {
      returnJsonString = mapper.writeValueAsString(act);
    } catch (JsonGenerationException jsonEx) {
      return null;
    } catch (JsonMappingException e) {
      return null;
    } catch (IOException e) {
      return null;
    }
    return returnJsonString;
  }

  @Override
  public EmailActionConfiguration deserialize(String actString) {

    EmailActionConfiguration returnEmailAction;
    try {
      returnEmailAction = mapper.readValue(actString, EmailActionConfiguration.class);
    } catch (JsonParseException e) {
      return null;
    } catch (JsonMappingException e) {
      return null;
    } catch (IOException e) {
      return null;
    }
    return returnEmailAction;
  }
}
