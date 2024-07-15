package chatbot.biz;

import chatbot.model.MessageResponse;
import chatbot.model.UpdateResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ChatbotApiService {
    // 텔레그램 API에서 업데이트를 가져오는 엔드포인트를 정의
    @GET("getUpdates")
    Call<UpdateResponse> getUpdates(@Query("offset") long offset);

    // 텔레그램 API에서 메시지를 보내는 엔드포인트를 정의
    @GET("sendMessage")
    Call<MessageResponse> sendMessage(@Query("chat_id") String chatId, @Query("text") String text);
}
