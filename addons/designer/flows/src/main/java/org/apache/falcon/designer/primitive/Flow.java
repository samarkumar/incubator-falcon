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

package org.apache.falcon.designer.primitive;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.falcon.designer.configuration.FlowConfig;
import org.apache.falcon.designer.flow.serde.FlowSerde;
import org.apache.falcon.designer.storage.Storage;
import org.apache.falcon.designer.storage.StorageException;
/**
 * Concrete implementation for a Flow.
 */
public class Flow extends Primitive<Flow, FlowConfig> {

    private FlowConfig process;

    public Flow(FlowConfig process) {
        this.process = process;
    }

    @Override
    protected Flow copy() {
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Flow doOptimize() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getNamespace() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getEntity() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void store(Storage storage) throws StorageException {
        // what is this entity and namespaces
        try {
            BufferedWriter o =
                new BufferedWriter(new OutputStreamWriter(storage.create(
                    getNamespace(), getEntity())));
            String serializedResource = FlowSerde.serialize(getConfiguration());
            o.write(serializedResource);
            o.close();
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }

    }

    @Override
    public void restore(Storage storage) throws StorageException {
        try {
            BufferedReader o =
                new BufferedReader(new InputStreamReader(storage.open(
                    getNamespace(), getEntity())));
            String configInString = o.readLine();
            setConfiguration(FlowSerde.deserialize(configInString));
            o.close();
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }

    }

    @Override
    public void delete(Storage storage) throws StorageException {
        storage.delete(getNamespace(), getEntity());
        setConfiguration(null);
    }

    @Override
    public void setConfiguration(FlowConfig config) {
        this.process = config;
    }

    @Override
    public FlowConfig getConfiguration() {
        return process;
    }

}
