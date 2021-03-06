package org.jon.ivmark.footballcoupons.application.game.resources;

import io.dropwizard.auth.Auth;
import org.jon.ivmark.footballcoupons.api.game.NewGameDto;
import org.jon.ivmark.footballcoupons.application.auth.dto.User;
import org.jon.ivmark.footballcoupons.application.game.domain.GameService;
import org.jon.ivmark.footballcoupons.application.game.domain.valueobjects.GameId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/games")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class GameResource {

    private final GameService gameService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public GameResource(GameService gameService) {
        this.gameService = gameService;
    }

    @POST
    public Response createGame(@Auth User user, @Valid NewGameDto newGameDto) throws URISyntaxException {
        assertIsAdminUser(user);
        logger.info("Creating game named: '{}", newGameDto.game_name);
        GameId newGameId = GameId.randomGameId();
        gameService.createGame(newGameId, newGameDto.game_name);
        logger.info("Game created with id '{}'", newGameId.getValue());
        return Response.created(new URI(newGameId.getValue())).build();
    }

    private void assertIsAdminUser(User user) {
        if (!user.isAdmin()) {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
    }
}
