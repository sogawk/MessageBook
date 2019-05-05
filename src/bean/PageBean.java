package bean;

import java.util.List;

public class  PageBean<Message> {
    int totalData;//必
    int totalPage;
    int currentPage;//必
    int pageData;//必
    List<Message> list;
    int indexBegin;
    int indexNum;
    public PageBean(){}

    public PageBean(int totalData, int totalPage, int currentPage, int pageData, List<Message> list, int indexBegin, int indexNum) {
        this.totalData = totalData;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.pageData = pageData;
        this.list = list;
        this.indexBegin = indexBegin;
        this.indexNum = indexNum;
    }

    public PageBean(int totalData, int totalPage, int currentPage, int pageData, List<Message> list) {
        this.totalData = totalData;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.pageData = pageData;
        this.list = list;
    }

    public int getIndexBegin() {

        return indexBegin= (currentPage-1)*pageData;
    }

    public void setIndexBegin(int indexBegin) {
        this.indexBegin = indexBegin;
    }

    public int getindexNum() {
        return indexBegin= pageData;
    }

    public void setindexNum(int indexNum) {
        this.indexNum = indexNum;
    }

    public int getTotalData() {
        return totalData;
    }

    public void setTotalData(int totalData) {
        this.totalData = totalData;
    }

    public int getTotalPage() {
        if(totalData%pageData==0)totalPage=totalData/pageData;
        else totalPage=(totalData/pageData)+1;
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageData() {
        return pageData;
    }

    public void setPageData(int pageData) {
        this.pageData = pageData;
    }

    public List<Message> getList() {
        return list;
    }

    public void setList(List<Message> list) {
        this.list = list;
    }
}
