package br.ufrn.bti.banco1000.view;

import java.util.Scanner;

import br.ufrn.bti.banco1000.controller.UsuarioController;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public abstract class GenericView {
	protected static Scanner leitor;
	protected static UsuarioController usuarioController = UsuarioView.usuarioController;
}
