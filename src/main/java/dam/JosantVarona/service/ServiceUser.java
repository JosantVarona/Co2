package dam.JosantVarona.service;

import dam.JosantVarona.DAO.UsuarioDAO;
import dam.JosantVarona.model.Usuario;

public class ServiceUser {
   private UsuarioDAO dao = new UsuarioDAO();

    public boolean registerUser(Usuario usuario) {
        boolean result = false;
        if (usuario != null) {
            dao.insertUsuario(usuario);
            result = true;
            System.out.println("Usuario registrado");
        }
        return result;
    }
    public boolean userExists(String email) {
        boolean result = false;
        if (email != null && !email.isEmpty()) {
            Usuario us = dao.buscarUsuario(email);
            if (us == null) {
                result = true;
            }
        }
        return result;
    }
    public boolean userLogin(Usuario usuario) {
        boolean result = false;
        if (usuario.getEmail() != null && !usuario.getEmail().isEmpty()) {
            Usuario us = dao.buscarUsuario(usuario.getEmail());
            if (us.getEmail().equals(usuario.getEmail()) && us.getContraseña().equals(usuario.getContraseña())) {
                result = true;
            }
        }
        return result;
    }
}
