package br.com.assertsistemas.view.impl;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.assertsistemas.dao.impl.AlunoDAOImpl;
import br.com.assertsistemas.dao.impl.UsuarioDAOImpl;
import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Tipo;
import br.com.assertsistemas.entity.Usuario;
import br.com.assertsistemas.service.AlunoService;
import br.com.assertsistemas.service.impl.AlunoServiceImpl;
import br.com.assertsistemas.util.Validacao;
import br.com.assertsistemas.view.AlunoController;

public class AlunoControllerImpl implements AlunoController {

	AlunoService alunoService;

	public AlunoControllerImpl() {
		this.alunoService = new AlunoServiceImpl();
	}

	public Aluno cadastroAluno() {
		Aluno aluno = new Aluno(0, null);
		Validacao valid = new Validacao();

		String nomeAluno = JOptionPane.showInputDialog("Digite o nome para cadastro:");
		if (valid.validNomeSobrenome(nomeAluno)) {
			aluno.setNome(nomeAluno);
		} else {
			aluno.setNome(valid.repeticao("nome"));
		}
		String sexo = JOptionPane.showInputDialog("Digite o gênero sexual (SOMENTE M/F):");
		if (valid.validChar(sexo)) {
			aluno.setSexo(sexo.toString().charAt(0));
		} else {
			aluno.setSexo(valid.repeticao("sexo").charAt(0));
		}
		String idade = JOptionPane.showInputDialog("Digite a idade:");
		if (valid.validIntLong(idade)) {
			int idade1 = Integer.valueOf(idade);
			aluno.setIdade(idade1);
		} else {
			String a = JOptionPane.showInputDialog("Digite somente números inteiros!");
			int idade2 = Integer.valueOf(a);
			aluno.setIdade(idade2);
		}
		String matricula = JOptionPane.showInputDialog("Digite a matrícula");
		if (valid.validIntLong(matricula)) {
			long matr = Long.valueOf(matricula);
			aluno.setMatricula(matr);
		} else {
			aluno.setMatricula(new Long(valid.repeticao("matricula").trim()));
		}
		String semestre = JOptionPane.showInputDialog("Insira o semestre sendo cursado:");
		if (valid.validIntLong(semestre)) {
			int sem = Integer.valueOf(semestre);
			aluno.setSemestre(sem);
		} else {
			aluno.setMatricula(new Integer(valid.repeticao("semestre").trim()));
		}

		Usuario usuario = new Usuario();
		usuario.setTipoUsuario(Tipo.ALUNO);

		String login = JOptionPane.showInputDialog("Insira um login para cadastro:");
		usuario.setLogin(login);

		String senha = JOptionPane.showInputDialog("Cadastre uma senha para o seu login:");
		usuario.setSenha(senha);

		Integer usuarioId = new UsuarioDAOImpl().insert(usuario);
		new AlunoDAOImpl().insert(aluno, usuarioId);
		JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");

		return aluno;
	}

	@Override
	public boolean insert(Aluno aluno) {
		alunoService.insert(aluno, 0);
		return false;
	}

	@Override
	public boolean update(Aluno aluno) {
		alunoService.update(aluno);
		return false;
	}

	@Override
	public boolean delete(int alunoId) {
		alunoService.delete(alunoId);
		return false;
	}

	@Override
	public List<Aluno> findAll() {
		alunoService.findAll();
		return null;
	}

	@Override
	public Aluno findById(int alunoId) {
		alunoService.findById(alunoId);
		return null;
	}

	@Override
	public List<Aluno> findByMatricula(long alunoMatricula) {
		alunoService.findByMatricula(alunoMatricula);
		return null;
	}
}
