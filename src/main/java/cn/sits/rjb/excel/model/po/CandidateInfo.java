package cn.sits.rjb.excel.model.po;

import java.sql.Timestamp;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.excel.model.po
 * @date 2020/03/19 15:47
 */
public class CandidateInfo {
    private int candidate_id;
    private String candidate_name;
    private String telephone;
    private String description;
    private int is_delete;
    private Timestamp create_time;
    private Timestamp update_time;
    private String company_now;
    private String company_before;

    public int getCandidate_id() {
        return candidate_id;
    }

    public void setCandidate_id(int candidate_id) {
        this.candidate_id = candidate_id;
    }

    public String getCandidate_name() {
        return candidate_name;
    }

    public void setCandidate_name(String candidate_name) {
        this.candidate_name = candidate_name;
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

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public String getCompany_now() {
        return company_now;
    }

    public void setCompany_now(String company_now) {
        this.company_now = company_now;
    }

    public String getCompany_before() {
        return company_before;
    }

    public void setCompany_before(String company_before) {
        this.company_before = company_before;
    }
}
