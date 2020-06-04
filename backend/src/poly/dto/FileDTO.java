package poly.dto;

public class FileDTO {
    private String file_seq;
    private String project_seq;
    private String original_file_name;
    private String stored_file_name;
    private String file_size;
    private String reg_dt;
    private String reg_id;
    private String chg_dt;
    private String chg_id;

    public String getFile_seq() {
        return file_seq;
    }

    public void setFile_seq(String file_seq) {
        this.file_seq = file_seq;
    }

    public String getProject_seq() {
        return project_seq;
    }

    public void setProject_seq(String project_seq) {
        this.project_seq = project_seq;
    }

    public String getOriginal_file_name() {
        return original_file_name;
    }

    public void setOriginal_file_name(String original_file_name) {
        this.original_file_name = original_file_name;
    }

    public String getStored_file_name() {
        return stored_file_name;
    }

    public void setStored_file_name(String stored_file_name) {
        this.stored_file_name = stored_file_name;
    }

    public String getFile_size() {
        return file_size;
    }

    public void setFile_size(String file_size) {
        this.file_size = file_size;
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
