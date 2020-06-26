package com.gruzini.tennistico.services.dto_related;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.mappers.PlayerDtoMapper;
import com.gruzini.tennistico.models.dto.PlayerDto;
import com.gruzini.tennistico.services.entity_related.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlayerDtoService {
   private final UserService userService;
   private final PlayerDtoMapper playerDtoMapper;

   public PlayerDtoService(final UserService userService, final PlayerDtoMapper playerDtoMapper) {
      this.userService = userService;
      this.playerDtoMapper = playerDtoMapper;
   }

   public PlayerDto getPlayerDtoByEmail(final String email){
      final Player player = userService.getByEmail(email).getPlayer();
      return playerDtoMapper.toPlayerDto(player);
   }
}
