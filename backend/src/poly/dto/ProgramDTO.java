package poly.dto;

public class ProgramDTO {
    private String user_id;
    private String program_name;
    private String program_score;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProgram_name() {
        return program_name;
    }

    public void setProgram_name(String program_name) {
        this.program_name = program_name;
    }

    public String getProgram_score() {
        return program_score;
    }

    public void setProgram_score(String program_score) {
        this.program_score = program_score;
    }
}
