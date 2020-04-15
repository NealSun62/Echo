package cn.sits.rjb.candidate.model.po;

import java.sql.Timestamp;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.excel.model.po
 * @date 2020/03/19 15:47
 */
public class CandidateInfo {
    private int candidateId;
    private String candidateName;
    private String telephone;
    private String description;
    private int isDelete;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String companyNow;
    private String companyBefore;
    private String post;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getCompanyNow() {
        return companyNow;
    }

    public void setCompanyNow(String companyNow) {
        this.companyNow = companyNow;
    }

    public String getCompanyBefore() {
        return companyBefore;
    }

    public void setCompanyBefore(String companyBefore) {
        this.companyBefore = companyBefore;
    }
}
