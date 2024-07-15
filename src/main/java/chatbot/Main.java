package chatbot;

import chatbot.biz.ChatbotApiService;
import chatbot.biz.NaverApiService;
import chatbot.model.SearchResult;
import chatbot.model.UpdateResponse;
import chatbot.model.UpdateResponse.Update;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		// Naver API의 기본 URL 설정
		String baseUrl = "https://openapi.naver.com/";

		// Retrofit 인스턴스 생성
		// GsonConverterFactory를 사용하여 JSON 데이터를 객체로 변환
		Retrofit retrofit = new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl(baseUrl)
				.build();

		// Naver API를 사용하기 위한 클라이언트 ID와 클라이언트 시크릿
		String clientID = "";
		String clientSecret = "";

		// NaverApiService 인터페이스의 구현체를 생성
		NaverApiService service = retrofit.create(NaverApiService.class);
		SearchResult searchResult = null;





		// 텔레그램 봇의 토큰을 설정
		// https://web.telegram.org/a
		// https://telegram.me/BotFather
		final String TOKEN = ""; // 여기에 텔레그램 봇 토큰을 입력하세요

		// Retrofit 인스턴스를 생성하여 텔레그램 API와 통신 설정
		Retrofit retrofitChat = new Retrofit.Builder()
                .baseUrl("https://api.telegram.org/bot" + TOKEN + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

		// ChatbotApiService 인터페이스의 구현체를 생성
        ChatbotApiService serviceChat = retrofitChat.create(ChatbotApiService.class);
		
    	try { 
    		// 처음으로 업데이트를 가져와서 마지막 업데이트 ID를 설정
    		UpdateResponse response = serviceChat.getUpdates(0).execute().body();
    		long lastId = 0;
    		if (response.result.size() != 0) {    			
    			lastId = response.result.get(response.result.size() - 1).updateId;
    		}

    		while (true) {
				// 1초마다 새로운 업데이트를 체크
				TimeUnit.SECONDS.sleep(1);
				
				// 새로운 업데이트를 가져옴
				response = serviceChat.getUpdates(lastId + 1).execute().body();
				for (Update update : response.result) {
					long id = update.message.from.id;
					String text = update.message.text;
					
					// 메시지의 사용자 ID와 내용을 출력

					StringBuilder result = new StringBuilder();
                    result.append("검색어 : ").append(text).append("\n\n");
					try {
						// Naver API를 호출하여 검색 결과를 가져옴
						// execute() 메서드를 사용하여 동기적으로 API 호출
						searchResult = service.search(clientID, clientSecret, "blog", text, 100, 1).execute().body();

						int c = 0;
						for (int i=0; i<3; i++) {
							int r = (int) (Math.random() * ((searchResult.items.size())));
							if(r == c || r == i){
								r = i;
							}else {
								c = r;
							}
							// 제목에서 HTML 태그(<b>, </b>) 제거
							String title = searchResult.items.get(r).getTitle()
									.replace("<b>", "")
									.replace("</b>", "");

							String link = searchResult.items.get(i).link;

							result.append("블로그 제목 : ").append(title).append("\n");
							result.append("링크 : ").append(link).append("\n");
							result.append("------------------------------------------\n");
						}
					} catch (IOException e) {
						// 예외 발생 시 스택 트레이스를 출력
						e.printStackTrace();
					}
					
					// 사용자가 보낸 메시지에 답장 전송
					serviceChat.sendMessage(id + "", result.toString()).execute().body();
					
					// 마지막 업데이트 ID를 갱신
					lastId = update.updateId;
				}
    		}
		} catch (Exception e) {
			// 예외 발생 시 스택 트레이스를 출력
			e.printStackTrace();
		}
	}
}
