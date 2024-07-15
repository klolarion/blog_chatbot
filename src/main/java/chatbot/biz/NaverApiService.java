package chatbot.biz;

import chatbot.model.SearchResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NaverApiService {
    // 네이버 검색 API의 엔드포인트를 정의하는 인터페이스
    // @GET 어노테이션을 사용하여 HTTP GET 요청을 보냄
    @GET("v1/search/{category}.json")
    Call<SearchResult> search(
        // API 호출 시 필요한 클라이언트 ID를 HTTP 헤더에 추가
        @Header("X-Naver-Client-Id") String clientId,
        // API 호출 시 필요한 클라이언트 시크릿을 HTTP 헤더에 추가
        @Header("X-Naver-Client-Secret") String clientSecret,
        // 검색 카테고리를 경로 매개변수로 받음
        @Path("category") String category,
        // 검색 쿼리를 쿼리 매개변수로 받음
        @Query("query") String query,
        // 검색 결과의 출력 건수를 쿼리 매개변수로 받음
        @Query("display") int display,
        // 검색 시작 위치를 쿼리 매개변수로 받음
        @Query("start") int start
    );
}
