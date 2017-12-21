package br.com.assertsistemas.service.impl;

import br.com.assertsistemas.dao.AlunoDAO;
import br.com.assertsistemas.dao.CoordenadorDAO;
import br.com.assertsistemas.dao.ProfessorDAO;
import br.com.assertsistemas.dao.UsuarioDAO;
import br.com.assertsistemas.dao.impl.AlunoDAOImpl;
import br.com.assertsistemas.dao.impl.CoordenadorDAOImpl;
import br.com.assertsistemas.dao.impl.ProfessorDAOImpl;
import br.com.assertsistemas.dao.impl.UsuarioDAOImpl;
import br.com.assertsistemas.entity.Usuario;
import br.com.assertsistemas.service.UsuarioService;
import br.com.assertsistemas.util.UsuarioLogadoUtil;


public class UsuarioServiceImpl implements UsuarioService {
	
	private UsuarioDAO usuarioDAO;

	public UsuarioServiceImpl() {
		usuarioDAO = new UsuarioDAOImpl();
	}
	
	public Object autentica(String login, String senha) {
		final Usuario usuario = usuarioDAO.findByLoginAndSenha(login, senha);
		final AlunoDAO alunodao = new AlunoDAOImpl();
		final ProfessorDAO professordao = new ProfessorDAOImpl();
		final CoordenadorDAO coordenadordao = new CoordenadorDAOImpl();

		if (usuario != null) {
			UsuarioLogadoUtil.usuario = usuario;
			
			switch (usuario.getTipoUsuario()) {
			case ALUNO:
				return alunodao.findByUsuarioId(usuario.getId());
			case PROFESSOR:
				return professordao.findByUsuarioId(usuario.getId());
			case COORDENADOR:
				return coordenadordao.findByUsuarioId(usuario.getId());
			default:
				break;
			}
			
		}
		
		return usuario;
	}
	

}
