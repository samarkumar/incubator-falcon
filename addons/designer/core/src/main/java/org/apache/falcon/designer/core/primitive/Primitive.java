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

package org.apache.falcon.designer.core.primitive;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.falcon.designer.core.configuration.Configuration;

/**
 * All elements of the pipeline are essentially a primitive. These primitives
 * only have life during the pipeline design time.
 */
public abstract class Primitive<T extends Primitive, V extends Configuration>{

    public abstract void setConfiguration(V config);

    protected abstract T copy();

    @Nonnull
    public abstract V getConfiguration();

    /**
     * Perform a validation to see if the primitive configuration is consistent
     * with this primitive.
     * @return - Messages that need to be sent as feedback from the validation.
     *         As long as one of the messages returned has a message type.
     *         Returns null if there are no messages to return from the
     *         validation. {@link Message.Type} is {@link Message.Type#ERROR},
     *         the primitive cannot be compiled successfully.
     */
    @Nullable
    public abstract Iterable<Message> validate();

    /**
     * Compile the primitive and generate corresponding binary/source code The
     * compile method fails if there are one or more {@link Message.Type#ERROR}
     * messages from the validation phase.
     * @return - Code object generated for the primitive.
     * @throws CompilationException
     *             - Compilation issues as returned by validate (if any of the
     *             {@link Message} is of type {@link Message.Type#ERROR}
     */
    @Nonnull
    public Code compile() throws CompilationException {
        Iterable<Message> validationMessages = validate();
        boolean error = false;
        if (validationMessages != null) {
            for (Message message : validationMessages) {
                if (message.getType() == Message.Type.ERROR) {
                    error = true;
                    break;
                }
            }
        }
        if (error) {
            throw new CompilationException(validationMessages);
        } else {
            T optimized = optimize();
            return optimized.doCompile();
        }
    }

    /**
     * This method is invoked only when the primitive is confirmed to be valid.
     * This would generate binary or source code for this primitive and its
     * configuration.
     * @return - Code object generated by the primitive.
     */
    @Nonnull
    protected abstract Code doCompile();

    /**
     * This method is invoked only when the primitive is confirmed to be valid.
     * This would operate on the current instance and return back an optimized
     * version of the same type.
     * @return - Optimized object of the same type.
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public T optimize() {
        T copy = copy();
        return (T) copy.doOptimize();
    }

    protected abstract T doOptimize();

    public abstract String getNamespace();

    public abstract String getEntity();

}