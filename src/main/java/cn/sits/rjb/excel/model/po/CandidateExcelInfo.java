package cn.sits.rjb.excel.model.po;

import lombok.Data;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.excel.model.po
 * @date 2020/03/19 15:47
 */
@Data
public class CandidateExcelInfo {
    private String candidateName;
    private String telephone;
    private String description;
    private String companyNow;
    private String companyBefore;
    private String post;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
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
