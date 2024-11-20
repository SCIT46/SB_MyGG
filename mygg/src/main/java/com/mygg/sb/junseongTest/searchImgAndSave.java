package com.mygg.sb.junseongTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.cj.xdevapi.JsonArray;

public class searchImgAndSave
	{
		public static void main(String[] args)
			{
				try
					{
						// 1. Riot API에서 최신 버전 가져오기
						HttpClient client = HttpClient.newHttpClient();
						HttpRequest request = HttpRequest.newBuilder()
								.uri(URI.create("https://ddragon.leagueoflegends.com/api/versions.json")).GET().build();
						HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

						// 2. JSON 파싱하여 최신 버전 추출
						com.google.gson.JsonArray versions = JsonParser.parseString(response.body()).getAsJsonArray();
						String latestVersion = ((JsonElement) versions.get(0)).getAsString(); // 최신 버전
						System.out.println("Latest Version: " + latestVersion);

						// 3. 최신 버전의 아이템 데이터 URL 생성
						String itemsUrl = "https://ddragon.leagueoflegends.com/cdn/" + latestVersion
								+ "/data/en_US/item.json";
						HttpRequest itemRequest = HttpRequest.newBuilder().uri(URI.create(itemsUrl)).GET().build();
						HttpResponse<String> itemResponse = client.send(itemRequest,
								HttpResponse.BodyHandlers.ofString());

						// 4. 아이템 데이터 JSON 객체로 파싱
						JsonObject itemsJson = JsonParser.parseString(itemResponse.body()).getAsJsonObject();
						JsonObject data = itemsJson.getAsJsonObject("data");

						// 5. 아이템 이미지 다운로드 및 저장
						// downloadItemImages(data, latestVersion);
						saveItemInfoToJson(data);
					} catch (IOException | InterruptedException e)
					{
						e.printStackTrace();
					}
			}

		// 아이템 이미지를 다운로드하고 지정된 폴더에 저장
		public static void downloadItemImages(JsonObject itemsData, String version)
			{
				String directoryPath = "./src/main/resources/static/Images/Items"; // 저장할 경로
				File directory = new File(directoryPath);
				if (!directory.exists())
					{
						directory.mkdirs(); // 경로가 없으면 디렉토리 생성
					}

				for (String itemId : itemsData.keySet())
					{
						JsonObject item = itemsData.getAsJsonObject(itemId);
						JsonObject image = item.getAsJsonObject("image");

						if (image != null)
							{
								String imageName = image.get("full").getAsString(); // 이미지 파일 이름 (예: "Dagger.png")
								String imageUrl = "https://ddragon.leagueoflegends.com/cdn/" + version + "/img/item/"
										+ imageName;
								String imageFilePath = directoryPath + "/" + itemId + ".png"; // 아이템 ID를 파일 이름으로 저장

								try
									{
										saveImageToFile(imageUrl, imageFilePath);
									} catch (IOException e)
									{
										System.out.println(
												"Failed to download image for item " + itemId + ": " + e.getMessage());
									}
							}
					}
			}

		// 이미지 URL로부터 이미지를 다운로드하여 지정된 파일로 저장
		public static void saveImageToFile(String imageUrl, String imageFilePath) throws IOException
			{
				URL url = new URL(imageUrl);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setDoOutput(true);

				// 이미지 다운로드
				try (InputStream inputStream = connection.getInputStream();
						OutputStream outputStream = new FileOutputStream(imageFilePath))
					{
						byte[] buffer = new byte[4096];
						int bytesRead;
						while ((bytesRead = inputStream.read(buffer)) != -1)
							{
								outputStream.write(buffer, 0, bytesRead);
							}
						System.out.println("Downloaded " + imageFilePath);
					}
			}

		public static void saveItemInfoToJson(JsonObject itemsData)
			{
				List<Map<String, String>> itemInfoList = new ArrayList<>();

				for (String itemId : itemsData.keySet())
					{
						JsonObject item = itemsData.getAsJsonObject(itemId);

						// 아이템 이름과 설명 추출
						String itemName = item.get("name").getAsString();
						String itemDescription = item.has("description") ? item.get("description").getAsString() : "";

						// 아이템 정보 객체 생성
						Map<String, String> itemInfo = new HashMap<>();
						itemInfo.put("id", itemId);
						itemInfo.put("name", itemName);
						itemInfo.put("description", itemDescription);

						itemInfoList.add(itemInfo);
					}

				// 아이템 정보 JSON 파일로 저장
				String outputPath = "./src/main/resources/static/Images/Items/items_info.json";
				File outputFile = new File(outputPath);

				try (Writer writer = new FileWriter(outputFile))
					{
						Gson gson = new GsonBuilder().setPrettyPrinting().create();
						gson.toJson(itemInfoList, writer);
						System.out.println("Item info saved to " + outputPath);
					} catch (IOException e)
					{
						System.out.println("Error saving item info JSON: " + e.getMessage());
					}

			}
	}
