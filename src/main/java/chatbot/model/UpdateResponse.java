package chatbot.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateResponse {
    // API 호출이 성공했는지 여부를 나타내는 필드
    @SerializedName("ok")
    public boolean ok;

    // 업데이트 결과 리스트를 저장하는 필드
    @SerializedName("result")
    public List<Update> result;

    // 업데이트 정보를 담는 내부 클래스
    public static class Update {
        // 업데이트 ID를 저장하는 필드
        @SerializedName("update_id")
        public long updateId;

        // 메시지 정보를 담는 필드
        @SerializedName("message")
        public Message message;

        // 메시지 정보를 담는 내부 클래스
        public static class Message {
            // 메시지 ID를 저장하는 필드
            @SerializedName("message_id")
            public long messageId;

            // 메시지를 보낸 사용자 정보를 담는 필드
            @SerializedName("from")
            public User from;

            // 채팅 정보를 담는 필드
            @SerializedName("chat")
            public Chat chat;

            // 메시지가 전송된 날짜를 저장하는 필드 (UNIX timestamp)
            @SerializedName("date")
            public int date;

            // 메시지 텍스트를 저장하는 필드
            @SerializedName("text")
            public String text;

            // 메시지를 보낸 사용자 정보를 담는 내부 클래스
            public static class User {
                // 사용자 ID를 저장하는 필드
                @SerializedName("id")
                public long id;

                // 사용자의 이름을 저장하는 필드
                @SerializedName("first_name")
                public String firstName;

                // 사용자의 사용자 이름을 저장하는 필드
                @SerializedName("username")
                public String username;
            }

            // 채팅 정보를 담는 내부 클래스
            public static class Chat {
                // 채팅 ID를 저장하는 필드
                @SerializedName("id")
                public long id;

                // 채팅의 이름을 저장하는 필드
                @SerializedName("first_name")
                public String firstName;

                // 채팅의 사용자 이름을 저장하는 필드
                @SerializedName("username")
                public String username;
            }
        }
    }
}
