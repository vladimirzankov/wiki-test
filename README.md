# Автоматизированное тестирование Wikipedia

Проект автоматизированного тестирования пользовательского интерфейса Wikipedia — веб-сайта и мобильного Android-приложения.

## Описание проекта

Данный проект содержит автоматизированные UI-тесты для двух платформ:

1. **Веб-тесты** — тестирование сайта [en.wikipedia.org](https://en.wikipedia.org) с использованием Selenium WebDriver
2. **Мобильные тесты** — тестирование Android-приложения Wikipedia Alpha с использованием Appium

### Покрытие тестами

#### Веб-тесты (сайт Wikipedia)

| Тестовый класс | Сценарии | Описание |
|----------------|----------|----------|
| `NavigationScenarioTest` | 4 теста | Проверка загрузки главной страницы, навигация по разделам (Contents, Current Events), переход на случайную статью |
| `ArticleScenarioTest` | 1 тест | Проверка структуры страницы статьи (заголовок, категории) |

#### Мобильные тесты (приложение Wikipedia Alpha)

| Тестовый класс | Сценарии | Описание |
|----------------|----------|----------|
| `WelcomeFlowScenarioTest` | 2 теста | Проверка запуска приложения и прохождения экрана приветствия |
| `ArticleSearchScenarioTest` | 3 теста | Поиск статей по ключевым словам, проверка результатов поиска |
| `ArticleViewScenarioTest` | 2 теста | Открытие статьи, проверка отображения контента, навигация назад |
| `LanguageSettingsScenarioTest` | 2 теста | Проверка доступности языковых настроек, поиск на языке по умолчанию |

---

## Технологический стек

| Компонент | Технология | Версия |
|-----------|------------|--------|
| Язык программирования | Java | 24 |
| Система сборки | Gradle | 8.x |
| Веб-автоматизация | Selenium WebDriver | 4.23.0 |
| Мобильная автоматизация | Appium Java Client | 9.3.0 |
| Тестовый фреймворк | TestNG | 7.8.0 |
| Управление драйверами | WebDriverManager | 5.6.3 |
| Логирование | SLF4J | 2.0.9 |

---

## Требования к окружению

### Обязательные компоненты

1. **Java Development Kit (JDK) 24** или выше
   - Скачать: https://www.oracle.com/java/technologies/downloads/
   - Проверка установки: `java -version`

2. **Gradle 8.x** (опционально, можно использовать встроенный wrapper)
   - Скачать: https://gradle.org/install/
   - Проверка установки: `gradle -version`

3. **Google Chrome** (последняя версия)
   - Скачать: https://www.google.com/chrome/
   - WebDriverManager автоматически загрузит соответствующий ChromeDriver

### Дополнительные компоненты для мобильного тестирования

4. **Node.js** (для установки Appium)
   - Скачать: https://nodejs.org/
   - Проверка установки: `node -v` и `npm -v`

5. **Appium Server**
   ```bash
   npm install -g appium
   ```
   - Проверка установки: `appium -v`

6. **Android Studio** с SDK Tools
   - Скачать: https://developer.android.com/studio
   - Установить Android SDK, Platform Tools, Build Tools
   - Настроить переменные окружения:
     ```bash
     export ANDROID_HOME=$HOME/Android/Sdk
     export PATH=$PATH:$ANDROID_HOME/emulator
     export PATH=$PATH:$ANDROID_HOME/platform-tools
     ```

7. **Android эмулятор**
   - Создать виртуальное устройство в Android Studio (AVD Manager)
   - Рекомендуемая конфигурация:
     - Устройство: Pixel 6 или аналогичное
     - API Level: 34 (Android 14)
     - Имя AVD: `Phone`

8. **APK-файл Wikipedia Alpha**
   - Файл `wikipedia-alpha.apk` включён в проект
   - Альтернативно скачать: https://github.com/wikimedia/apps-android-wikipedia/releases

---

## Структура проекта

```
src/test/
├── java/ru/zankov/wiki/
│   ├── android/
│   │   ├── scenarios/           # Тестовые классы для Android
│   │   │   ├── AndroidTestFoundation.java
│   │   │   ├── ArticleSearchScenarioTest.java
│   │   │   ├── ArticleViewScenarioTest.java
│   │   │   ├── LanguageSettingsScenarioTest.java
│   │   │   └── WelcomeFlowScenarioTest.java
│   │   └── screenobjects/       # Screen Object классы
│   │       ├── ArticleSearchScreenObject.java
│   │       ├── ArticleViewScreenObject.java
│   │       ├── WelcomeFlowScreenObject.java
│   │       └── WikiHomeScreenObject.java
│   ├── browser/
│   │   ├── scenarios/           # Тестовые классы для браузера
│   │   │   ├── WebTestFoundation.java
│   │   │   ├── NavigationScenarioTest.java
│   │   │   └── ArticleScenarioTest.java
│   │   └── pageobjects/         # Page Object классы
│   │       ├── WikipediaMainPageObject.java
│   │       ├── ArticlePageObject.java
│   │       └── SearchOutputPageObject.java
│   └── core/
│       ├── base/                # Базовые абстрактные классы
│       │   ├── AbstractPageObject.java
│       │   └── AbstractScreenObject.java
│       └── config/              # Конфигурация
│           └── SettingsProvider.java
└── resources/
    ├── config.properties        # Параметры конфигурации
    ├── web-tests.xml            # TestNG suite для веб-тестов
    └── mobile-tests.xml         # TestNG suite для мобильных тестов
```

---

## Архитектура

Проект построен на основе паттерна **Page Object Model** (для веб) и **Screen Object Model** (для мобильных приложений):

- **AbstractPageObject** / **AbstractScreenObject** — базовые классы с общими методами взаимодействия с элементами
- **PageObject** / **ScreenObject** классы — инкапсулируют локаторы и действия для каждого экрана
- **Scenario Tests** — тестовые классы, использующие Page/Screen Object для выполнения сценариев
- **SettingsProvider** — централизованное чтение конфигурации из `config.properties`

---

## Конфигурация

Параметры тестов настраиваются в файле `src/test/resources/config.properties`:

### Параметры веб-тестов

| Параметр | Описание | Значение по умолчанию |
|----------|----------|----------------------|
| `browser.target.url` | URL тестируемого сайта | `https://en.wikipedia.org` |
| `browser.implicit.timeout` | Неявное ожидание (сек) | `5` |
| `browser.page.timeout` | Таймаут загрузки страницы (сек) | `30` |

### Параметры мобильных тестов

| Параметр | Описание | Значение по умолчанию |
|----------|----------|----------------------|
| `android.os.name` | Название платформы | `Android` |
| `android.device.id` | ID устройства/эмулятора | `emulator-5554` |
| `android.app.package` | Пакет приложения | `org.wikipedia.alpha` |
| `android.app.activity` | Главная Activity | `org.wikipedia.main.MainActivity` |
| `android.automation.engine` | Движок автоматизации | `UiAutomator2` |
| `android.appium.endpoint` | URL Appium сервера | `http://127.0.0.1:4723` |

---

## Инструкция по запуску

### Запуск всех тестов

```bash
./gradlew clean test
```

### Запуск только веб-тестов

```bash
./gradlew clean test -Dtest.suite=src/test/resources/web-tests.xml
```

### Запуск только мобильных тестов

#### Шаг 1: Запуск Android эмулятора

```bash
emulator -avd Phone
```

Дождитесь полной загрузки эмулятора (появится домашний экран Android).

#### Шаг 2: Установка приложения (при первом запуске)

```bash
adb -s emulator-5554 install wikipedia-alpha.apk
```

Убедитесь, что приложение установлено:
```bash
adb shell pm list packages | grep wikipedia
```

#### Шаг 3: Запуск Appium сервера

В отдельном терминале выполните:
```bash
appium
```

Сервер должен запуститься на порту 4723. Вы увидите сообщение:
```
[Appium] Welcome to Appium v2.x.x
[Appium] Appium REST http interface listener started on http://0.0.0.0:4723
```

#### Шаг 4: Запуск мобильных тестов

В новом терминале выполните:
```bash
./gradlew test -Dtest.suite=src/test/resources/mobile-tests.xml
```

---

## Отчёты о тестировании

После выполнения тестов отчёты доступны по следующим путям:

| Тип отчёта | Путь |
|------------|------|
| HTML-отчёт TestNG | `build/reports/tests/test/index.html` |
| XML-результаты | `build/test-results/test/` |

Откройте HTML-отчёт в браузере для просмотра детальных результатов:
```bash
open build/reports/tests/test/index.html
```

---

## Возможные проблемы и их решение

### Веб-тесты

| Проблема | Решение |
|----------|---------|
| ChromeDriver не найден | WebDriverManager автоматически загружает драйвер. Убедитесь в наличии интернет-соединения |
| Тесты падают с timeout | Увеличьте значения `browser.implicit.timeout` и `browser.page.timeout` в config.properties |
| Браузер не открывается | Проверьте, что Chrome установлен и обновлён до последней версии |

### Мобильные тесты

| Проблема | Решение |
|----------|---------|
| Could not find device | Проверьте, что эмулятор запущен: `adb devices` |
| Appium session not created | Убедитесь, что Appium сервер запущен и доступен на порту 4723 |
| App not installed | Установите APK: `adb install wikipedia-alpha.apk` |
| ANDROID_HOME not set | Настройте переменные окружения для Android SDK |

---

## Зависимости

Все зависимости управляются через Gradle и загружаются автоматически:

```kotlin
dependencies {
    testImplementation("org.seleniumhq.selenium:selenium-java:4.23.0")
    testImplementation("io.appium:java-client:9.3.0")
    testImplementation("org.testng:testng:7.8.0")
    testImplementation("io.github.bonigarcia:webdrivermanager:5.6.3")
    testImplementation("org.slf4j:slf4j-api:2.0.9")
    testImplementation("org.slf4j:slf4j-simple:2.0.9")
}
```
