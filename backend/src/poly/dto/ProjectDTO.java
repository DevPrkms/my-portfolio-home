package poly.dto;

public class ProjectDTO {
    private String project_seq;
    private String project_name;
    private String project_day;
    private String project_contents;
    private String reg_dt;
    private String reg_id;
    private String chg_dt;
    private String chg_id;
    private String img_save_path;
    private String file_save_path;

    public String getImg_save_path() {
        return img_save_path;
    }

    public void setImg_save_path(String img_save_path) {
        this.img_save_path = img_save_path;
    }

    public String getFile_save_path() {
        return file_save_path;
    }

    public void setFile_save_path(String file_save_path) {
        this.file_save_path = file_save_path;
    }

    public String getProject_seq() {
        return project_seq;
    }

    public void setProject_seq(String project_seq) {
        this.project_seq = project_seq;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_day() {
        return project_day;
    }

    public void setProject_day(String project_day) {
        this.project_day = project_day;
    }

    public String getProject_contents() {
        return project_contents;
    }

    public void setProject_contents(String project_contents) {
        this.project_contents = project_contents;
    }

    public String getReg_dt() {
        return reg_dt;
    }

    public void setReg_dt(String reg_dt) {
        this.reg_dt = reg_dt;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public String getChg_dt() {
        return chg_dt;
    }

    public void setChg_dt(String chg_dt) {
        this.chg_dt = chg_dt;
    }

    public String getChg_id() {
        return chg_id;
    }

    public void setChg_id(String chg_id) {
        this.chg_id = chg_id;
    }
}
