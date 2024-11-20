package com.mygg.sb.junseongTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.Paths;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.URL;

public class searchImgAndSave
	{
		public static void main(String[] args) throws Exception
			{
				// 1. Riot API 호출 - 패치 버전 가져오기
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder()
						.uri(URI.create("https://ddragon.leagueoflegends.com/api/versions.json")).GET().build();

				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

				// 2. JSON 파싱하여 최신 버전 추출
				JsonArray versions = JsonParser.parseString(response.body()).getAsJsonArray();
				String latestVersion = versions.get(0).getAsString(); // 최신 버전
				System.out.println("Latest Version: " + latestVersion);

				// 3. 아이템 데이터 가져오기
				String itemsUrl = "https://ddragon.leagueoflegends.com/cdn/" + latestVersion + "/data/en_US/item.json";
				HttpRequest itemRequest = HttpRequest.newBuilder().uri(URI.create(itemsUrl)).GET().build();
				HttpResponse<String> itemResponse = client.send(itemRequest, HttpResponse.BodyHandlers.ofString());

				// 4. JSON 파싱
				JsonObject itemsData = JsonParser.parseString(itemResponse.body()).getAsJsonObject();
				JsonObject items = itemsData.getAsJsonObject("data");

				// 5. 특정 아이템 ID로 이미지 URL 생성
				String itemId = "1001"; // 예: Boots
				String itemImage = items.getAsJsonObject(itemId).getAsJsonObject("image").get("full").getAsString();
				String imageUrl = "https://ddragon.leagueoflegends.com/cdn/" + latestVersion + "/img/item/" + itemImage;

				System.out.println("Item Image URL: " + imageUrl);

				// 6. 상대 경로로 폴더 만들고 이미지 저장
				String directoryPath = "./src/main/resources/static/Images/Items"; // 상대경로로 "Images/Items" 폴더 지정
				createDirectoryIfNotExists(directoryPath); // 폴더가 없으면 생성

				String savePath = directoryPath + "/" + itemImage; // 이미지 파일 경로
				downloadImage(imageUrl, savePath); // 이미지 다운로드 및 저장

				System.out.println("Image saved to: " + savePath);
			}

		// 폴더가 없다면 생성하는 메서드
		public static void createDirectoryIfNotExists(String directoryPath) throws IOException
			{
				Path path = Paths.get(directoryPath);
				if (Files.notExists(path))
					{
						Files.createDirectories(path); // 디렉토리 생성
						System.out.println("Directory created: " + directoryPath);
					}
			}

		// 이미지를 다운로드하고 저장하는 메서드
		public static void downloadImage(String imageUrl, String savePath) throws IOException
			{
				InputStream in = new URL(imageUrl).openStream();
				Files.copy(in, new File(savePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
				in.close();
			}
	}
