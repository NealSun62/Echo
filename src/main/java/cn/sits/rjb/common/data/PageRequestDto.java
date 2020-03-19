package cn.sits.rjb.common.data;

import java.io.Serializable;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.common.data
 * @date 2020/02/27 15:41
 */
public class PageRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;
    // 当前所处的页码
    private Integer pageNum;
    // 每页显示数量
    private Integer pageSize;
    // 刨除之前的数量
    private Integer offset;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
