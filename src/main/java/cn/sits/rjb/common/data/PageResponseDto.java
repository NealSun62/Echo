package cn.sits.rjb.common.data;

import java.io.Serializable;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.common.data
 * @date 2020/02/27 15:41
 */
public class PageResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    // 总数据量
    private Integer totalSize;
    // 总的分页数
    private Integer totalPage;

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
