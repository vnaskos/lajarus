package com.bugbusters.lajarus.websocket;

import com.bugbusters.lajarus.entity.PlayerEntity;
import com.bugbusters.lajarus.entity.QuestEntity;
import com.bugbusters.lajarus.service.PlayerService;
import com.bugbusters.lajarus.service.QuestService;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 *
 * @author Vasilis Naskos
 */
@Component
public class GameHandler extends TextWebSocketHandler{

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    
    @Autowired
    private PlayerService playerService;
    
    @Autowired
    private QuestService questService;
    
    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        logger.error("error occured at sender " + session, throwable);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info(String.format("Session %s closed because of %s", session.getId(), status.getReason()));
    }
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("Connected ... " + session.getId());
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("message received: " + message.getPayload());
        
        JSONObject request = new JSONObject(message.getPayload());
        
        String action = request.getString("ACTION");
        String from = request.getString("FROM");
        
        switch (action) {
            case "NEARBY_PLAYERS":
                String players = getNearbyPlayers(from);
                session.sendMessage(new TextMessage(players));
                break;
            case "NEARBY_QUESTS":
                String quests = getNearbyQuests(from);
                session.sendMessage(new TextMessage(quests));
                break;
            case "UPDATE_LOCATION":
                String latitude = request.getString("latitude");
                String longitude = request.getString("longitude");
                playerService.updatePlayerLocation(from, latitude, longitude);
                break;
            default:
                break;
        }
    }

    private void broadcastMessage(String message) {
        sessions.forEach((s) -> {
            try {
                s.sendMessage(new TextMessage(message));
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    private String getNearbyPlayers(String from) {
        List<PlayerEntity> players = playerService.getNearbyPlayers(from);
        
        JSONObject response = new JSONObject();
        JSONArray jsonPlayers = new JSONArray();
        
        players.forEach(p -> {
            JSONObject jsonPlayer = new JSONObject();
            jsonPlayer.put("name", p.getName());
            jsonPlayer.put("lat", p.getLatitude());
            jsonPlayer.put("long", p.getLongitude());
            jsonPlayers.put(jsonPlayer);
        });
        
        response.put("ACTION", "NEARBY_PLAYERS");
        response.put("players", jsonPlayers);
        logger.info(response.toString());
        
        return response.toString();
    }
    
    private String getNearbyQuests(String from) {
        List<QuestEntity> quests = questService.getNearbyQuests(from);
        
        JSONObject response = new JSONObject();
        JSONArray jsonPlayers = new JSONArray();
        
        quests.forEach(q -> {
            JSONObject jsonQuest = new JSONObject();
            jsonQuest.put("name", q.getName());
            jsonQuest.put("desc", q.getDescription());
            jsonQuest.put("lat", q.getLatitude());
            jsonQuest.put("long", q.getLongitude());
            jsonPlayers.put(jsonQuest);
        });
        
        response.put("ACTION", "NEARBY_QUESTS");
        response.put("quests", jsonPlayers);
        logger.info(response.toString());
        
        return response.toString();
    }
    
}
