package io.lindx.task.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.lindx.task.model.Role;
import io.lindx.task.repository.RoleRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  @Override
  public List<Role> getAllRoles() {
    return roleRepository.findAll();
  }
}
