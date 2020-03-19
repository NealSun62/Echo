package cn.sits.rjb.excel.model.dto;

import java.sql.Timestamp;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.candidate.model.dto
 * @date 2020/03/19 15:54
 */
public class DownloadCandidateInfoByKeywordResponseDto {
    private int candidateId;
    private String candidateName;
    private String telephone;
    private String description;
    private Timestamp updateTime;
    private String companyNow;
    private String companyBefore;

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
