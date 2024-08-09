package jml.noteschallenge.notesapp.infra.security;

import jml.noteschallenge.notesapp.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByEmail(nombre);
        return (UserDetails) usuario;
    }
}
