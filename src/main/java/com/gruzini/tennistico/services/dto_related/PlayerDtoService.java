package com.gruzini.tennistico.services.dto_related;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.mappers.PlayerDtoMapper;
import com.gruzini.tennistico.models.dto.PlayerDto;
import com.gruzini.tennistico.services.entity_related.PlayerService;
import com.gruzini.tennistico.services.entity_related.UserService;
import org.springframework.cglib.core.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlayerDtoService {
   private final UserService userService;
   private final PlayerService playerService;
   private final PlayerDtoMapper playerDtoMapper;

   public PlayerDtoService(final UserService userService,
                           final PlayerService playerService,
                           final PlayerDtoMapper playerDtoMapper) {
      this.userService = userService;
      this.playerService = playerService;
      this.playerDtoMapper = playerDtoMapper;
   }

   public PlayerDto getPlayerDtoByEmail(final String email){
      final Player player = userService.getByEmail(email).getPlayer();
      return playerDtoMapper.toPlayerDto(player);
   }

   public Page<PlayerDto> getPlayerDtosPage(final Pageable pageable){
      final Page<Player> playersPage = playerService.getPlayersPage(pageable);
      return playersPage.map(new Function<Player, PlayerDto> () {
         @Override
         public PlayerDto apply(Player player){
            PlayerDto playerDto = new PlayerDto();
            playerDto = playerDtoMapper.toPlayerDto(player);
            return playerDto;
         }
      });
   }
}
