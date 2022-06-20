/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.example;

import java.util.concurrent.TimeUnit;

import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.test.main.junit5.CamelMainTestSupport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A unit test using the legacy approach checking that Camel supports binding via annotations.
 */
//@Disabled
class MainTest extends CamelMainTestSupport {

    @Test
    void should_support_binding_via_annotations() throws Exception {
		Thread.sleep(2000);
		template.asyncSendBody("direct:demo", "");
        NotifyBuilder notify = new NotifyBuilder(context)
            .whenCompleted(1).whenBodiesDone("Bye World").create();
        assertTrue(
            notify.matches(60, TimeUnit.SECONDS), "1 message should be completed"
        );
    }

    @Override
    protected Class<?> getMainClass() {
        return MyApplication.class;
    }
		
	@Override
	protected boolean useJmx() {
		return true;
	}
	
	// Can be omitted as default implementation is returnign false but writing it to emphasize that it must return false
	@Override
	public boolean isUseDebugger() {
		return false;
	}
	
}
