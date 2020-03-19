package cn.sits.rjb.common.utils;


// 分页对象
public class PageUtil <T>{

    // 数据总数
    private int totalCount;
    // 当前页数
    private int pageIndex = 1;
    // 页面尺寸  显示条数
    private int pageSize = 100;
    //条件对象
    private T checkModelData;


    public static Integer getTotalPage(Integer totalSize,Integer pageSize){
        if(totalSize==0||pageSize==0){
            return 0;
        }
        if(totalSize<=pageSize){
            return 1;
        }
        int totalPage = 1;
        int remain = totalSize%pageSize;
        if(remain==0){
            totalPage = (totalSize-remain)/pageSize;
        }
        if(remain>0){
            totalPage = (totalSize-remain)/pageSize+1;
        }
        return totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getCheckModelData() {
        return checkModelData;
    }

    public void setCheckModelData(T checkModelData) {
        this.checkModelData = checkModelData;
    }
}











