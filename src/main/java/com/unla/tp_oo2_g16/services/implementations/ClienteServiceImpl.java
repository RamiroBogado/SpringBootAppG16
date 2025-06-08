package com.unla.tp_oo2_g16.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.unla.tp_oo2_g16.models.entities.Cliente;
import com.unla.tp_oo2_g16.repositories.ClienteRepository;
import com.unla.tp_oo2_g16.services.interfaces.ClienteServiceInterface;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteServiceInterface {
    
    private final ClienteRepository clienteRepository;
    
    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
    
    @Override
    public Cliente findById(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }
    
    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    @Override
    public void deleteById(Integer id) {
        clienteRepository.deleteById(id);
    }
    
    @Override
    public Cliente findByDni(String dni) {
        return clienteRepository.findByDni(dni);
    }
    
    @Override
    public List<Cliente> findClientesConcurrentes() {
        return clienteRepository.findByEsConcurrente(true);
    }
}