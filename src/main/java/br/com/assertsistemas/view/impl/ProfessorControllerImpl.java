package br.com.assertsistemas.view.impl;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.assertsistemas.dao.impl.ProfessorDAOImpl;
import br.com.assertsistemas.dao.impl.UsuarioDAOImpl;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Professor;
import br.com.assertsistemas.entity.Tipo;
import br.com.assertsistemas.entity.Usuario;
import br.com.assertsistemas.service.ProfessorService;
import br.com.assertsistemas.service.impl.ProfessorServiceImpl;
import br.com.assertsistemas.util.Validacao;
import br.com.assertsistemas.view.ProfessorController;

public class ProfessorControllerImpl implements ProfessorController {

	ProfessorService professorService;

	public ProfessorControllerImpl() {
		this.professorService = new ProfessorServiceImpl();
	}

	public Professor cadastroProfessor() throws SQLException {
		Professor professor = new Professor();
		Validacao valid = new Validacao();
		Usuario usuario = new Usuario();
		
		usuario.setTipoUsuario(Tipo.PROFESSOR);
		
		String nomeProfessor = JOptionPane.showInputDialog("Digite o nome para cadastro:");
		if (valid.validNomeSobrenome(nomeProfessor)) {
			professor.setNome(nomeProfessor);
		} else {
			professor.setNome(valid.repeticao("nome"));
		}
		String sexo = JOptionPane.showInputDialog("Digite o gênero sexual (SOMENTE M/F):");
		if (valid.validChar(sexo)) {
			professor.setSexo(sexo.toString().charAt(0));
		} else {
			professor.setSexo(valid.repeticao("sexo").charAt(0));
		}
		String idade = JOptionPane.showInputDialog("Digite a idade:");
		if (valid.validIntLong(idade)) {
			int idade1 = Integer.valueOf(idade);
			professor.setIdade(idade1);
		} else {
			String a = JOptionPane.showInputDialog("Digite somente números inteiros!");
			int idade2 = Integer.valueOf(a);
			professor.setIdade(idade2);
		}
		String qualificacao = JOptionPane.showInputDialog("Digite o maior título (qualificação - doutor, mestre, etc)");
		if (valid.validNomeSobrenome(qualificacao)) {
			professor.setQualificacao(qualificacao);
		} else {
			professor.setQualificacao((valid.repeticao("qualificacao").trim()));
		}
		String login = JOptionPane.showInputDialog("Insira um login para cadastro:");
		usuario.setLogin(login);

		String senha = JOptionPane.showInputDialog("Cadastre uma senha para o seu login:");
		usuario.setSenha(senha);

		
		Integer usuarioId = new UsuarioDAOImpl().insert(usuario);
		new ProfessorDAOImpl().insert(professor, usuarioId);
		JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
		return professor;
	}

	@Override
	public boolean insert(Professor professor, int usuarioId) throws SQLException {
		professorService.insert(professor, usuarioId);
		return false;
	}

	@Override
	public boolean update(Professor professor) {
		professorService.update(professor);
		return false;
	}

	@Override
	public boolean delete(int professorId) {
		professorService.delete(professorId);
		return false;
	}

	@Override
	public List<Professor> findAll() {
		professorService.findAll();
		return null;
	}

	@Override
	public Professor findById(int professorId) {
		professorService.findById(professorId);
		return null;
	}

	@Override
	public List<Professor> findByDisciplina(Disciplina disciplina) {
		professorService.findByDisciplina(disciplina);
		return null;
	}

}
