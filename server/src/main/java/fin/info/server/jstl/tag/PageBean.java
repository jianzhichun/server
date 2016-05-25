package fin.info.server.jstl.tag;

import java.util.List;

public class PageBean<T> {
	private int pageSize;
    private long totalrows;
    private int pageNum; 
    private int curPage;  
      
    private List<T> items;
      
    public PageBean(int pageSize, long totalRows, int curPage,List<T> items){  
        this.pageSize = pageSize;  
        this.totalrows = totalRows;  
        this.curPage = curPage;  
        this.pageNum = (int) Math.ceil((double)totalRows / pageSize);  
        this.items = items;  
    }

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalrows() {
		return totalrows;
	}

	public void setTotalrows(long totalrows) {
		this.totalrows = totalrows;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}
}
