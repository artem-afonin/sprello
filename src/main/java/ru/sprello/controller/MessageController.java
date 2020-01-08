package ru.sprello.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sprello.model.Message;
import ru.sprello.repo.MessageRepository;

@RestController
@RequestMapping("api/messages")
public class MessageController {
    private static final Logger LOG = Logger.getLogger(MessageController.class);
    private final MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public Iterable<Message> getAll() {
        LOG.info("GET[ALL] messages");
        return messageRepository.findAll();
    }

    @GetMapping("{id}")
    public Message geById(@PathVariable("id") Message message) {
        LOG.info("GET[ID] " + message);
        return message; // Spring находит message по "id" и его возвращаем
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        messageRepository.save(message);
        LOG.info("POST " + message);
        return message; // метод POST возвращает добавленный элемент
    }

    @PutMapping("{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message messageFromUsr
    ) {
        // копируем все поля от юзера в бд, кроме "id"
        BeanUtils.copyProperties(messageFromUsr, messageFromDb, "id");
        messageRepository.save(messageFromUsr);
        LOG.info("PUT " + messageFromUsr);
        return messageFromUsr;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        messageRepository.delete(message);
        LOG.info("DELETE " + message);
    }
}
