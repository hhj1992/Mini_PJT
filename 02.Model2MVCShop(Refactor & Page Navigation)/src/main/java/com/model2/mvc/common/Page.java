package com.model2.mvc.common;


public class Page {
   
   ///Field
   private int currentPage;      // 현재페이지
   private int totalCount;      // 쿼리를 조회한 결과의 개수
   private int pageUnit;         // 페이지 , 여기서는 5페이지씩
   private int pageSize;         // 한페이지당 표출할 개수
   private int maxPage;         // 페이징중 맨 마지막페이지
   private int beginUnitPage;   // 시작 페이징
   private int endUnitPage;   // 종료페이징
   
   ///Constructor
   private Page() {
   }
   public Page( int currentPage, int totalCount,   int pageUnit, int pageSize ) {
      this.totalCount = totalCount;
      this.pageUnit = pageUnit; //5페이지씩 
      this.pageSize = pageSize; //한페이지에 표출할개수 -> 3 
                                                //32/3 +1  
      //pagesize가 0일경우에는 totalCount로, 아닐경우에는 맨마지막페이지 (전체페이지-1)/3  +1
      this.maxPage = (pageSize == 0) ? totalCount :  (totalCount-1)/pageSize +1;
   
      //현재페이지 = 현재페이지가 마지막페이지 보다 클경우에는 현재페이지값으로, 아닐경우에는 매개변수로 넘어온 현재페이지값으로
      this.currentPage = ( currentPage > maxPage) ? maxPage : currentPage;
      
      //1~5페이지일경우  1~4/5 = 0 * 5 +1 => 시작페이지는 1
      //6~9페이지일경우  6~9/5 = 1 * 5 +1 => 시작페이지는 6
      this.beginUnitPage = ( (currentPage-1) / pageUnit ) * pageUnit +1 ;
      
      //마지막페이지가 5보다 작거나 같을경우
      if( maxPage <= pageUnit ){
         this.endUnitPage = maxPage;
      }
      //마지막페이지가 5보다 클경우
      else{
         //ex)마지막페이지가 6일경우, 6 + 4 = 10 10까지한이유는 페이지를 5개씩끊을경우 6,7,8,9,10 이기때문에 defalut로 10페이지까지고정
         this.endUnitPage = beginUnitPage + (pageUnit -1); //10
         
         //ex)마지막페이지가 6이고, endUnitPage가 10이므로, endUnitPage를 마지막페이지값인 6으로 변경한다.
         if( maxPage <= endUnitPage){
            this.endUnitPage = maxPage;
         }
      }
   }
   
   ///Mehtod
   public int getCurrentPage() {
      return currentPage;
   }
   public void setCurrentPage(int currentPage) {
      this.currentPage = currentPage;
   }
   public int getTotalCount() {
      return totalCount;
   }
   public void setTotalCount(int totalCount) {
      this.totalCount = totalCount;
   }
   public int getPageUnit() {
      return pageUnit;
   }
   public void setPageUnit(int pageUnit) {
      this.pageUnit = pageUnit;
   }
   public int getPageSize() {
      return pageSize;
   }
   public void setPageSize(int pageSize) {
      this.pageSize = pageSize;
   }
   public int getMaxPage() {
      return maxPage;
   }
   public void setMaxPage(int maxPage) {
      this.maxPage = maxPage;
   }
   public int getBeginUnitPage() {
      return beginUnitPage;
   }
   public void setBeginUnitPage(int beginUnitPage) {
      this.beginUnitPage = beginUnitPage;
   }
   public int getEndUnitPage() {
      return endUnitPage;
   }
   public void setEndUnitPage(int endUnitPage) {
      this.endUnitPage = endUnitPage;
   }
   @Override
   public String toString() {
      return "Page [currentPage=" + currentPage + ", totalCount="
            + totalCount + ", pageUnit=" + pageUnit + ", pageSize="
            + pageSize + ", maxPage=" + maxPage + ", beginUnitPage="
            + beginUnitPage + ", endUnitPage=" + endUnitPage + "]";
   }
}