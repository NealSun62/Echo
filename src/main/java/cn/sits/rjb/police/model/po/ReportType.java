package cn.sits.rjb.police.model.po;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.police.model.dto
 * @date 2020/03/01 17:15
 */
public class ReportType {
    private String typeName;
    private int typeCount;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getTypeCount() {
        return typeCount;
    }

    public void setTypeCount(int typeCount) {
        this.typeCount = typeCount;
    }
}
