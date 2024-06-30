package solid.dependencyInversionPrinciple.task;

/**
 * 1. Определите прямые зависимости между высокоуровневыми и низкоуровневыми модулями в вашем коде.
 * 2. Создайте интерфейс или абстрактный класс для разделения этих двух модулей.
 * 3. Измените модуль высокого уровня, чтобы он зависел от абстракции, а не от модуля низкого уровня,
 * и обязательно реализуйте абстракцию в каждом модуле низкого уровня.
 * 4. Используйте внедрение зависимостей, чтобы предоставить модуль низкого уровня модулю высокого уровня,
 * чтобы модуль высокого уровня объединял модуль низкого уровня.
 */
public class LightBulb {
    void turnOn() {
        System.out.println("Light bulb turned on");
    }

    void turnOff() {
        System.out.println("Light bulb turned off");
    }
}

class Switch {
    private LightBulb lightBulb;
    private boolean isOn = false;

    Switch(LightBulb lightBulb) {
        this.lightBulb = lightBulb;
    }

    void toggle() {
        if (isOn) {
            lightBulb.turnOff();
            isOn = false;
            System.out.println("Switched off");
        } else {
            lightBulb.turnOn();
            isOn = true;
            System.out.println("Switched on");
        }
    }
}
