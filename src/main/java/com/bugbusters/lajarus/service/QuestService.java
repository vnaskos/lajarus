package com.bugbusters.lajarus.service;

import com.bugbusters.lajarus.entity.PlayerEntity;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugbusters.lajarus.entity.QuestEntity;
import com.bugbusters.lajarus.repository.QuestRepository;
import com.bugbusters.lajarus.repository.PlayerRepository;

/**
 * Type: Service This Service is the middleman between the QuestController and
 * the QuestEntity with is connected with the Database with the support of the
 * spring framework, revives information from the controller and modifies them
 * for the needs of the client.
 */
@Service
public class QuestService {

    @Autowired
    private QuestRepository questRepository;

    @Autowired
    private PlayerRepository playerRepository;

    /**
     * Get quest by id
     *
     * @param id quest id
     * @return quest
     * @throws Exception
     */
    public QuestEntity getQuestById(long id) throws Exception {
        QuestEntity questEntity = questRepository.getQuestById(id);

        if (questEntity == null) {
            throw new Exception("Quest " + id + " does not exist");
        }

        return questEntity;
    }

    /**
     * Get all quests
     *
     * @return list of quests
     */
    public List<QuestEntity> getAll() {
        return questRepository.findAll();
    }

    /**
     * Create a new quest For administrative purposes
     *
     * @param questEntity
     */
    public void createQuest(QuestEntity questEntity) {
        questRepository.saveAndFlush(questEntity);
    }

    /**
     * Delete a quest
     *
     * @param id quest ID
     */
    public void deleteQuest(long id) {
        questRepository.deleteQuest(id);
    }

    /**
     * Get a list of all quests near the given player
     *
     * @param name player name
     * @return list of quests
     */
    public List<QuestEntity> getNearbyQuests(String name) {
        PlayerEntity player = playerRepository.findPlayerByName(name);
        List<QuestEntity> nearbyQuests = questRepository.findNearByQuests(
                player.getLatitude(), player.getLongitude(), 0.2);
        return nearbyQuests;
    }

    /**
     * Check if quest exists
     * 
     * @param name quest name
     * @return boolean
     */
    public boolean isThereQuestByName(String name) {
        QuestEntity questEntity = questRepository.isThereQuestByName(name);
        return questEntity != null;
    }

}
