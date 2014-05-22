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
package org.apache.falcon.rerun.service;

import org.apache.falcon.FalconException;
import org.apache.falcon.rerun.event.LaterunEvent;
import org.apache.falcon.rerun.event.RerunEvent.RerunType;
import org.apache.falcon.rerun.handler.AbstractRerunHandler;
import org.apache.falcon.rerun.handler.RerunHandlerFactory;
import org.apache.falcon.rerun.queue.ActiveMQueue;
import org.apache.falcon.service.FalconService;
import org.apache.falcon.util.StartupProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * A service implementation for Late Rerun initialized at startup.
 */
public class LateRunService implements FalconService {

    private static final Logger LOG = LoggerFactory.getLogger(LateRunService.class);

    @Override
    public String getName() {
        return LateRunService.class.getName();
    }

    @Override
    public void init() throws FalconException {
        AbstractRerunHandler<LaterunEvent, ActiveMQueue<LaterunEvent>> rerunHandler =
            RerunHandlerFactory.getRerunHandler(RerunType.LATE);
        ActiveMQueue<LaterunEvent> queue = new ActiveMQueue<LaterunEvent>(
                StartupProperties.get()
                    .getProperty("broker.url", "failover:(tcp://localhost:61616)?initialReconnectDelay=5000"),
                "falcon.late.queue");
        rerunHandler.init(queue);
    }

    @Override
    public void destroy() throws FalconException {
        LOG.info("LateRun thread destroyed");
    }

    private File getBasePath() {
        File basePath = new File(StartupProperties.get().getProperty(
                "rerun.recorder.path", "/tmp/falcon/rerun"));
        if ((!basePath.exists() && !basePath.mkdirs())
                || (basePath.exists() && !basePath.canWrite())) {
            throw new RuntimeException("Unable to initialize late recorder @"
                    + basePath);
        }

        return basePath;
    }
}
