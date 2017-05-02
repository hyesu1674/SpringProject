package yjc.wdb.bbs.bean;

public class Criteria {
	private int page = 1;
	private int recordsPerPage = 10;
	
	final static public int DISPLAY_PAGE_NUM = 10;
	
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int totalCount;
	
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

		
	public void calculate(){
		// startPage, endPage, prev, next °è»ê
		endPage = (int)Math.ceil(page/(double)DISPLAY_PAGE_NUM)*DISPLAY_PAGE_NUM;
		startPage = endPage - DISPLAY_PAGE_NUM + 1;
		
		if((endPage * recordsPerPage) > totalCount)
	         endPage = (totalCount / recordsPerPage) + 1;
		
		prev = startPage > 1 ? true : false;
		next = endPage * recordsPerPage < totalCount ? true : false;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calculate();
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
		return (page-1)*recordsPerPage;
	}
	
	public void setPage(int page){
		if(page <= 0)
			this.page = 1;
		else this.page = page;
	}
	
	public int getRecordsPerPage(){
		return recordsPerPage;
	}
	
	public void setRecordsPerPage(int recordsPerPage){
		if(recordsPerPage <= 0 || recordsPerPage > 100){
			this.recordsPerPage = 10;
		} else{
			this.recordsPerPage = recordsPerPage;
		}
	}
	
	public String toString(){
		return "Criteria [page = " + page + ", " + "recordsPerPage = " + recordsPerPage + "]";
	}
}
