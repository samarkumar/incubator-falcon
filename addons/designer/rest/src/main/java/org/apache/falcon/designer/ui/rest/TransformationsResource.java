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

package org.apache.falcon.designer.ui.rest;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;

/**
 * Jersey Resource for admin operations.
 */
@Path("transformations")
public class TransformationsResource {

  List<String> listOfTransformations = Arrays.asList("Project", "Filter",
      "Aggregate", "Partition", "Join", "Union", "Rollup");

  // TODO very dirty way.. should find a generic way.. too lazy to create a pojo
  @GET
  @Path("list")
  @Produces(MediaType.APPLICATION_JSON)
  public JSONArray getAllTransformations() {
    JSONArray jsonArray = new JSONArray(listOfTransformations);
    return jsonArray;
  }

}
