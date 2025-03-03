package br.com.nailtonvieira.crud_sboot.service;


import br.com.nailtonvieira.crud_sboot.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.nailtonvieira.crud_sboot.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

     public Usuario salvar(Usuario usuario) {
        if (usuario.getCep() != null) {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://viacep.com.br/ws/" + usuario.getCep() + "/json/";
            @SuppressWarnings("unchecked")
            Map<String, String> dadosCep = restTemplate.getForObject(url, Map.class);

            if (dadosCep != null) {
                usuario.setEndereco(dadosCep.get("logradouro"));
                usuario.setCidade(dadosCep.get("localidade"));
                usuario.setEstado(dadosCep.get("uf"));
            }
        }

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }


}
