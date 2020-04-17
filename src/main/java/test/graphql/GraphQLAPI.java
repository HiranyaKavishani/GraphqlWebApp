/*
 *
 *  Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * /
 */

package test.graphql;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import org.apache.cxf.helpers.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@Path("/graphql")
public class GraphQLAPI {

	@POST
    @Consumes("application/json")
	@Produces("application/json")
	public Response getResults() {
		List<Language> languageList = new ArrayList<>();
		JSONParser jsonParser = new JSONParser();
		try {
			String data = IOUtils.toString(
					getClass().getClassLoader().getResourceAsStream("languages.json"),
					"UTF-8");
			JSONObject obj = (JSONObject) jsonParser.parse(data);
			JSONArray languagesList = (JSONArray) obj.get("languages");

			for (Object lang : languagesList) {
				JSONObject linkObj = (JSONObject) lang;
				Language language = new Language(linkObj.get("name").toString(), linkObj.get("code").toString());
                languageList.add(language);
			}

		} catch (IOException | ParseException ignored) {

		}
        String jsonObj = new Gson().toJson(languageList);
		return Response.ok().entity(jsonObj).build();
	}
}
