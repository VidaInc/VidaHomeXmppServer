/*
 * Copyright 2014 Wolfram Rittmeyer.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.vidainc.home.server;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.json.simple.JSONValue;

/**
 * Handles an echo request.
 */
public class MessageProcessor implements PayloadProcessor {

	@Override
	public void handleMessage(CcsMessage msg) {
		PseudoDao dao = PseudoDao.getInstance();
		CcsClient client = CcsClient.getInstance();
		String msgId = dao.getUniqueMessageId();
		writeToFile(msg.getFrom(), msg.getPayload().get("message"));
	}

	private void writeToFile(String deviceRegId, String content) {
		// content.replaceAll("\\\"", "\"");
		try (Writer writer = new BufferedWriter((new OutputStreamWriter(
				new FileOutputStream(deviceRegId, true))))) {
			writer.write(content + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
