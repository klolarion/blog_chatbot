package chatbot.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult {
	// 검색 결과가 생성된 날짜와 시간을 저장
	@SerializedName("lastBuildDate")
	public String lastBuildDate;

	// 검색 결과의 총 개수를 저장
	@SerializedName("total")
	public int total;

	// 검색 시작 위치를 저장
	@SerializedName("start")
	public int start;

	// 검색 결과 출력 건수를 저장
	@SerializedName("display")
	public int display;

	// 검색 결과 항목들의 리스트를 저장
	@SerializedName("items")
	public List<Item> items;

	// 검색 결과의 각 항목을 나타내는 내부 클래스
	public static class Item {
		// 검색 결과 항목의 제목을 저장
		@SerializedName("title")
		public String title;

		// 검색 결과 항목의 링크를 저장
		@SerializedName("link")
		public String link;

		// 검색 결과 항목의 설명을 저장
		@SerializedName("description")
		public String description;

		// 제목을 반환하는 getter 메서드
		public String getTitle() {
			return title;
		}
	}
}
