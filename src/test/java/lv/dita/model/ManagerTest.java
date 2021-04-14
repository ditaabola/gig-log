package lv.dita.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    @Test
    void setName() {
        Manager manager = new Manager();
        manager.setName("John");
        assertEquals("John", manager.getName());
    }

    @Test
    void getName() {
        Manager manager = new Manager("Jane", "Manager", "jane@manager.com");
        assertEquals("Jane", manager.getName());
    }

    @Test
    void getSurname() {
        Manager manager = new Manager("Jake", "Jamanger", "jake@manager.com");
        assertEquals("Jamanger", manager.getSurname());
    }

    @Test
    void getEmail() {
        Manager manager = new Manager("Jake", "Jamanger", "jake@manager.com");
        assertEquals("jake@manager.com", manager.getEmail());
    }
}