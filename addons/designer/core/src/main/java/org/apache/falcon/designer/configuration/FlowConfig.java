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

package org.apache.falcon.designer.configuration;

import java.util.Collection;
import java.util.Map;

import org.apache.falcon.designer.primitive.Primitive;

public class FlowConfig implements Configuration {

  private Node root;

  private String namespace;
  private String entity;

  public static enum Condition {
    CONDITION1, CONDITION2;
  }

  public static class Node {
    private Map<Condition, Node> successors;
    private Primitive<Primitive, Configuration> value;

    public Node(Primitive value) {
      this.value = value;

    }

    Node getNextNodeForCondition(Condition cond) {
      if (successors != null) {
        return successors.get(cond);
      } else {
        return null;
      }
    }

    public Primitive<Primitive, Configuration> getPrimitive() {
      return value;
    }

    Collection<Node> getAllNextNodeForCondition(Condition cond) {
      if (successors != null) {
        return successors.values();
      } else {
        return null;
      }
    }

    void addSuccessor(Condition cond, Node sucessor) {
      successors.put(cond, sucessor);
    }
  }

  @Override
  public String getCategory() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return null;
  }

  public Node getRoot() {
    return root;
  }

  public String getNamespace() {
    return namespace;
  }

  public void setNamespace(String namespace) {
    this.namespace = namespace;
  }

  public String getEntity() {
    return entity;
  }

  public void setEntity(String entity) {
    this.entity = entity;
  }

}
