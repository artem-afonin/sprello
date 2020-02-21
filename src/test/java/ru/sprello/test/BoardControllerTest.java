package ru.sprello.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.sprello.controller.board.BoardController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BoardControllerTest {
    @Autowired
    private BoardController controller;

    @Autowired
    private MockMvc mockMvc;

    /*@Test
    public void controllerLoads() {
        org.assertj.core.api.Assertions.assertThat(controller).isNotNull();
    }
    @Test
    public void addition() {
        Assert.assertEquals(2, 1 + 1);
    }*/
}
