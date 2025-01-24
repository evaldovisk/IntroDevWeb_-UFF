package controller.admin.DTO;

public class TurmaRequest {
    private String codigoTurma;
    private String professor;
    private String disciplina;

    private String aluno1;
    private double nota1;

    private String aluno2;
    private double nota2;

    public TurmaRequest() {
    }

    public TurmaRequest(String codigoTurma, String professor, String disciplina, String aluno1, double nota1, String aluno2, double nota2) {
        this.codigoTurma = codigoTurma;
        this.professor = professor;
        this.disciplina = disciplina;
        this.aluno1 = aluno1;
        this.nota1 = nota1;
        this.aluno2 = aluno2;
        this.nota2 = nota2;
    }

    // Getters e Setters
    public String getCodigoTurma() {
        return codigoTurma;
    }

    public void setCodigoTurma(String codigoTurma) {
        this.codigoTurma = codigoTurma;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getAluno1() {
        return aluno1;
    }

    public void setAluno1(String aluno1) {
        this.aluno1 = aluno1;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public String getAluno2() {
        return aluno2;
    }

    public void setAluno2(String aluno2) {
        this.aluno2 = aluno2;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    @Override
    public String toString() {
        return "Turma{" +
                "codigoTurma='" + codigoTurma + '\'' +
                ", professor='" + professor + '\'' +
                ", disciplina='" + disciplina + '\'' +
                ", aluno1='" + aluno1 + '\'' +
                ", nota1=" + nota1 +
                ", aluno2='" + aluno2 + '\'' +
                ", nota2=" + nota2 +
                '}';
    }
}
