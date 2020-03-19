package cn.sits.rjb.common.utils;

import java.io.Serializable;
import java.util.List;

public class TreeNode<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;//title  节点名
    private Boolean expand;//expand   (true or false)  展开   or  不展开
    private Boolean selected; //选中
    private Boolean checked;// 复选框选中
    private Boolean Leaf;
    private List<TreeNode<T>> children;
    private Object data;//实体类
    private boolean button;//是否按钮
    private String value;
    private String label;
    private String areaCode; //地区码
    private String areaName;  //地区名字
    private String parentCode;//父级code
    private String oneRiverOnePolicyText; //一河一策
    private String gbid;
    private String gbtype;
    private String gblength;
    private String gbname;
    private Long pid;//上级id
    private Long  inspectionTableId;//工程检查表id
    private Long inspectionRecordId;//关联记录表id
    private String checkDescription;//检查情况
    private String remarks;//备注
    private List<TreeNode<T>> list;
    private String name;
    private String icon;
    private String jump;
    private Boolean spread;

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public List<TreeNode<T>> getList() {
        return list;
    }

    public void setList(List<TreeNode<T>> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getJump() {
        return jump;
    }

    public void setJump(String jump) {
        this.jump = jump;
    }

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getInspectionRecordId() {
        return inspectionRecordId;
    }

    public void setInspectionRecordId(Long inspectionRecordId) {
        this.inspectionRecordId = inspectionRecordId;
    }

    public String getCheckDescription() {
        return checkDescription;
    }

    public void setCheckDescription(String checkDescription) {
        this.checkDescription = checkDescription;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getInspectionTableId() {
        return inspectionTableId;
    }

    public void setInspectionTableId(Long inspectionTableId) {
        this.inspectionTableId = inspectionTableId;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getGbid() {
        return gbid;
    }

    public void setGbid(String gbid) {
        this.gbid = gbid;
    }

    public String getGbtype() {
        return gbtype;
    }

    public void setGbtype(String gbtype) {
        this.gbtype = gbtype;
    }

    public String getGblength() {
        return gblength;
    }

    public void setGblength(String gblength) {
        this.gblength = gblength;
    }

    public String getGbname() {
        return gbname;
    }

    public void setGbname(String gbname) {
        this.gbname = gbname;
    }

    public TreeNode() {
    }

    public String getOneRiverOnePolicyText() {
        return oneRiverOnePolicyText;
    }

    public void setOneRiverOnePolicyText(String oneRiverOnePolicyText) {
        this.oneRiverOnePolicyText = oneRiverOnePolicyText;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Boolean getExpand() {
        return expand;
    }

    public void setExpand(Boolean expand) {
        this.expand = expand;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getLeaf() {
        return Leaf;
    }

    public void setLeaf(Boolean leaf) {
        Leaf = leaf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
