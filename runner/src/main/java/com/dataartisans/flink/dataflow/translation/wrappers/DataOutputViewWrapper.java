/*
 * Copyright 2015 Data Artisans GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dataartisans.flink.dataflow.translation.wrappers;

import org.apache.flink.core.memory.DataOutputView;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Wrapper for {@link org.apache.flink.core.memory.DataOutputView}. We need this because
 * Flink writes data using a {@link org.apache.flink.core.memory.DataInputView} while
 * Dataflow {@link com.google.cloud.dataflow.sdk.coders.Coder}s expect an
 * {@link java.io.OutputStream}.
 */
public class DataOutputViewWrapper extends OutputStream {
	
	private DataOutputView outputView;

	public DataOutputViewWrapper(DataOutputView outputView) {
		this.outputView = outputView;
	}

	public void setOutputView(DataOutputView outputView) {
		this.outputView = outputView;
	}

	@Override
	public void write(int b) throws IOException {
		outputView.write(b);
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		outputView.write(b, off, len);
	}
}