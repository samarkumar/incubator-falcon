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

package org.apache.falcon.designer.core.schema;

import org.apache.falcon.designer.core.source.DataSource;
import org.apache.falcon.designer.core.storage.Storage;
import org.apache.falcon.designer.core.storage.StorageException;
import org.apache.falcon.designer.core.storage.Storeable;

import javax.annotation.Nonnull;

/**
 * Relational Data marker for now.
 */
public class RelationalData implements Storeable {

    //TODO To work out details as we go along.

    public RelationalData(RelationalSchema relationalSchema, DataSource dataSource) {

    }

    @Override
    public void store(@Nonnull Storage storage) throws StorageException {
        //TODO
    }

    @Override
    public void restore(@Nonnull Storage storage) throws StorageException {
        //TODO
    }

    @Override
    public void delete(@Nonnull Storage storage) throws StorageException {
        //TODO
    }
}