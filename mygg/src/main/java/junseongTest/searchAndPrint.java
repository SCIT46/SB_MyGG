package junseongTest;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mygg.sb.statics.util.UrlToJson;

public class searchAndPrint
	{

		JSONParser parser = new JSONParser();
		ArrayList<String> player;

		public JSONObject Search(String matchId) throws Exception
			{
				System.out.println("search 실행");
				JSONObject jsonObject;

				//String match_url = String.format("%s%s%s?api_key=%s", RiotApiConstants.RIOT_API_URL, RiotApiConstants.RIOT_API_MATCH,
				//		matchId, RiotApiConstants.API_KEY);

				String matchJSON = UrlToJson.urlToJson(UrlToJson.urlConvertor("matchInfo", matchId));
				jsonObject = (JSONObject) parser.parse(matchJSON);

				return jsonObject;
			}

		public void printData(JSONObject jsonObject)
			{
				for (Object key : jsonObject.keySet())
					{
						// key 값으로 받은 데이터를 value에 저장
						Object value = jsonObject.get(key);
					}
			}
		

		public String run()
			{
				try
					{
						JSONObject obj = Search("JP1_479210862");
						
						for(Object i : obj.keySet())
							{
								System.out.println(obj.get((String)	i));
							}
						
					} 
				catch (Exception e)
					{
						System.out.println(e);
					}
				
				return "냥";
			}
		
	}
