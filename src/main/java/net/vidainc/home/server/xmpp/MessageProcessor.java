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
package net.vidainc.home.server.xmpp;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.json.JSONArray;

/**
 * Handles an echo request.
 */
public class MessageProcessor implements PayloadProcessor {

	@Override
	public void handleMessage(CcsMessage msg) {
		String NAME = "UTEST"+".rm"+"1"+".txt";
		PseudoDao dao = PseudoDao.getInstance();
		CcsClient client = CcsClient.getInstance();
		String msgId = dao.getUniqueMessageId();
		writeToFile(NAME, msg.getPayload().get("message"));
	}

	private void writeToFile(String fileName, String content) {
		int numOfBeacons = 3;
		try (Writer writer = new BufferedWriter((new OutputStreamWriter(
				new FileOutputStream(fileName, true))))) {
			DataSet d = new DataSet(content);
//			if(d.getLen()  <1 || d.getLen() >3){return;}			else
			{
				for (int i=0;i<numOfBeacons;i++){
					if(d.getBeaconByMinor(i+14) == null){
						writer.write(Integer.toString(i+14)+"\t-99\t12\t");
					}
					else{
						writer.write(d.getBeaconByMinor(i+14).getUuids().getInt(2) + "\t"+
					d.getBeaconByMinor(i+14).getRssi()+"\t"+d.getBeaconByMinor(i+14).getDist()+"\t");
					}
				}
			}
			writer.write("\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
