
import controller.admin.TurmaController;
import entidade.Aluno;
import entidade.Administrador;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AlunoDAO;
import model.AdministradorDAO;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InicializacaoListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Aluno aluno = new Aluno();
        aluno.setId(-1);
        aluno.setNome("Disponivel");
        aluno.setEmail("Disponivel");
        aluno.setCelular("0000000");
        aluno.setCpf("0000000");
        aluno.setSenha("0000000");
        aluno.setEndereco("Disponivel");
        aluno.setCidade("Disponivel");
        aluno.setBairro("Disponivel");
        aluno.setCep("0000000");

        AlunoDAO alunoDAO = new AlunoDAO();
        try {
            if (alunoDAO.getAlunoByCpfSenha(aluno) == null) {
                alunoDAO.addFirstAluno(aluno);
                System.out.println("Aluno inserido com sucesso.");
            } else {
                System.out.println("Aluno já existe no banco.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Administrador administrador = new Administrador();
        administrador.setNome("Admin");
        administrador.setCpf("249.252.810-38");
        administrador.setSenha("111");
        administrador.setEndereco("Vazio");

        AdministradorDAO administradorDAO = new AdministradorDAO();
        try {


            if (administradorDAO.getAdministradorByCpf(administrador.getCpf()) == null) {
                administradorDAO.Inserir(administrador);
                System.out.println("Administrador inserido com sucesso.");
            } else {
                System.out.println("Administrador já existe no banco.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
 
    }
}
