package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeControllerTest {
    private HomeController homeController;

    @BeforeEach
    void SetUp() {
        homeController = new HomeController();
    }

    @Test
    void testHome() {
        String pageView = homeController.homePage();
        assertEquals("WelcomePage", pageView);
    }
}