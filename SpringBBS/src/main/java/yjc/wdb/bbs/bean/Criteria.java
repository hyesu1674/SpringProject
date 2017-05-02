package yjc.wdb.bbs.bean;

public class Criteria {
	
	private int page = -1;
	private int recordsPerPage = 10;
	
	private int startPage;
	private int endPage;
	private boolean prev; // 이전페이지
	private boolean next; // 다음페이지
	private int totalCount;
	
	
	// 검색
	private String searchType;
	private String keyword;
	
	
	
	public String getSearchType() {
		return searchType;
	}


	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public int getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calculate();
	}

	public final static int DISPLAY_PAGE_NUM = 10;
	
	private void calculate(){
		/*
		 * StartPage, endPage, prev, next 계산 해줌.
		 * */
		
		endPage = (int)Math.ceil(page/(double)DISPLAY_PAGE_NUM)*DISPLAY_PAGE_NUM;
		
		/*
		 * endPage까지 나타낼 수 있는 글 수와
		 * 테이블에 들어있는 총 글 수를 비교한다.(totalCount)
		 * endPage까지 나타낼 수 있는 글 수는 : endPage * recordsPerPage
		 * (endPage*recordsPerPage) > totalCount
		 * 만약에 위의 조건이 참이라면
		 * endPage = (totalCount/recordsPerPage)+1;
		 * 
		 * */
		
		startPage = endPage - DISPLAY_PAGE_NUM + 1;
		
		if((endPage*recordsPerPage)>totalCount){
			endPage = (totalCount/recordsPerPage)+1;
		}
		
		prev = startPage > 1 ? true : false;
		next = endPage * recordsPerPage < totalCount ? true : false;
	}
	
	
	public int getStartPage() {
		return startPage;
	}


	public int getEndPage() {
		return endPage;
	}


	public boolean isPrev() {
		return prev;
	}


	public boolean isNext() {
		return next;
	}

	public int getPage() {
		return page;
	}
	
	public int getStartRecord(){
		/* page가 1이면 0*10 리턴
		 * page가 2이면 1*10 리턴
		 * */
		
		return (page-1) * recordsPerPage;
	}
	
	public void setPage(int page) {
		if(page <= 0)
			this.page = 1;
		else this.page = page;
		
		this.page = page;
	}
	public int getRecordsPerPage() {
		return recordsPerPage;
	}
	
	public void setRecordsPerPage(int recordsPerPage) {
		if(recordsPerPage <= 0 || recordsPerPage > 100){
			this.recordsPerPage = 10;
		} else{
			this.recordsPerPage = recordsPerPage;
		}
		this.recordsPerPage = recordsPerPage;
	}
	
	public String toString(){
		return "Criteria [page="+page+", "
				+"recordsPerPage=" + recordsPerPage+ "]";
	}

}
