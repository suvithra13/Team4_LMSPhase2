package com.lms.utilities;

public class PageUtils {
	
	private int currentPageFirstRecord = 0;
	private int currentPageLastRecord = 0;
	private int recordsPerPage = 0;
	private int totalRecords = 0;
	private int currentPage = 0;
	private int totalPages = 0;
	
	private boolean isFirstPage;
	private boolean isLastPage;
	
	public PageUtils(String recordsInfo) {
		if (recordsInfo != null) {
			String[] items = recordsInfo.split(" ");
			
			if (items.length == 7) { // This is fixed string format
				currentPageFirstRecord = Integer.parseInt(items[1]);
				currentPageLastRecord = Integer.parseInt(items[3]);
				totalRecords = Integer.parseInt(items[5]);
				recordsPerPage = currentPageLastRecord - currentPageFirstRecord + 1;
				isFirstPage = (currentPageFirstRecord == 1);
				isLastPage = (currentPageLastRecord == totalRecords);
				
				if (isLastPage) {
					totalPages = totalRecords / 5;
					if (totalRecords % 5 != 0) {
						totalPages += 1;
					}
					currentPage = currentPageFirstRecord / 5;
					if (currentPageFirstRecord % 5 != 0) {
						currentPage += 1;
					}
				} else {
					totalPages = totalRecords / recordsPerPage;
					if (totalRecords % recordsPerPage != 0) {
						totalPages += 1;
					}
					currentPage = currentPageFirstRecord / recordsPerPage;
					if (currentPage == 0) {
						currentPage = 1;
					} else if (currentPageFirstRecord % recordsPerPage != 0) {
						currentPage += 1;
					}
				}
			}
		}
	}
	
	public int getCurrentPageFirstRecord() {
		return currentPageFirstRecord;
	}

	public int getCurrentPageLastRecord() {
		return currentPageLastRecord;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public boolean isFirstPage() {
		return isFirstPage;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public static void main(String[] args) {
		String recordsInfo = "Showing 1 to 5 of 17 entries";
		
		PageUtils pageUtils = new PageUtils(recordsInfo);
		System.out.println(pageUtils);
		
		recordsInfo = "Showing 6 to 10 of 17 entries";
		pageUtils = new PageUtils(recordsInfo);
		System.out.println(pageUtils);
		
		recordsInfo = "Showing 16 to 17 of 17 entries";
		pageUtils = new PageUtils(recordsInfo);
		System.out.println(pageUtils);
	}

	@Override
	public String toString() {
		return "PageUtils [currentPageFirstRecord=" + currentPageFirstRecord + ", currentPageLastRecord="
				+ currentPageLastRecord + ", recordsPerPage=" + recordsPerPage + ", totalRecords=" + totalRecords
				+ ", currentPage=" + currentPage + ", totalPages=" + totalPages + ", isFirstPage=" + isFirstPage
				+ ", isLastPage=" + isLastPage + "]";
	}

}
