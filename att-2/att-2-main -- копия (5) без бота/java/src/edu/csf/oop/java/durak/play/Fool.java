package edu.csf.oop.java.durak.play;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.slf4j.internal.Logger;
import edu.csf.oop.java.durak.ForJson;
import edu.csf.oop.java.durak.cardDeckDescription.Card;
import edu.csf.oop.java.durak.cardDeckDescription.Deck;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//import sun.security.rsa.RSAUtil;
//import org.slf4j.Logger;

public class Fool {

    @JsonProperty("TRUMP")
    public static String TRUMP;
    @JsonProperty("first")
    private Player first;
    @JsonProperty("second")
    private Player second;
    @JsonProperty("deck")
    private Deck deck;
    @JsonProperty("round")
    private int round; // натуральное число
    @JsonProperty("attacker")
    private Player attacker;
    @JsonProperty("defender")
    private Player defender;
    @JsonProperty("currentTable")
    private Table currentTable;
    @JsonProperty("roundInitiated")
    private boolean roundInitiated; // Если раунд идет и завершил свои начальные этапы
    @JsonIgnore
    public Scanner sc = new Scanner(System.in);
    @JsonIgnore
    public Random r = new Random();
    @JsonIgnore
    private static final Logger myLogger= (Logger) LoggerFactory.getLogger(Fool.class);
    // установить козырь по умолчанию
    public Fool() {
        run();
    }

    public void run() {
        boolean running = true;

        while (running) {
            setup();
            game();
            System.out.println("Игра окончена.");
            System.out.println("Начать снова? y/n");

            boolean validResponse = false;
            while (!validResponse) {
                String response = sc.nextLine();
                if (response.equals("y")) {
                    validResponse = true;
                    running = true;
                } else if (response.equals("n")) {
                    validResponse = true;
                    running = false;
                } else {
                    validResponse = false;
                }
            }
        }
    }

    // Настройка экземпляра игры
    public void setup() {
        //myLogger.info("Инициализация игры");

       // System.out.println("Играем в дурака!");


        System.out.println("\nИмя первого игрока: ");
        String fName = sc.nextLine();
        //myLogger.info("первый игрок: "+fName);

        System.out.println("\nИмя второго игрока: ");
        String sName = sc.nextLine();
        //myLogger.info("второй игрок: "+sName);

        System.out.println("\nСоздание игры...\n");
        System.out.println("Перемешивание карт...\n");
        deck = new Deck();

        System.out.println("Раздача...\n");
        first = new Player(deck, fName);
        second = new Player(deck, sName);

        System.out.println("Определение  козыря...\n");
        Card trumpCard = deck.deal();
        String trumpSuit = trumpCard.getSuit();
        TRUMP = trumpSuit;
        //myLogger.info("Козырь: "+TRUMP);

        System.out.println("Козырь: " + TRUMP + "!\n");

        //Помещаем карту, назначившую козырь, в низ колоды
        deck.reinsert(trumpCard);

        //начинаем первый раунд
        round = 1;

        //игра готова
    }

    // работа экземпляра игры
    public void game() {
        System.out.println("определяю начального атакующего...\n");
        if (r.nextInt(2) == 1) {
            setAttacker(first);
            setDefender(second);
        } else {
            setAttacker(second);
            setDefender(first);
        }

        System.out.println("Ход начинает: " + attacker + ".");
        System.out.println("Защищается: " + defender + ".\n");

        // создание раундов и игра до обеды victoryAchieved()
        boolean gameOver = false;
        while (!gameOver) {
            boolean thisRound = round(); // Run a round

            //myLogger.info("Раунд "+Integer.toString(round));
            if (victoryAchieved()) {
                // Победа была достигнута в какой-то момент
                // попадаем сюда сразу после выхода из раунда
                gameOver = true; // выход из цикла игры
            } else {
                // победа не достигнута
                // раздать карты (дополнить), начиная с атаковавшего
                attacker.replenish();
                defender.replenish();
                round++; // увеличить номер раунда (новый раунд)
                if (thisRound) {
                    // атакующий взял раунд и ходит снова; переключения ролей не происходит
                    System.out.println("Этот раунд взял игрок "+attacker+"\n");
                    //myLogger.info("Раунд взял игрок "+attacker);

                } else {
                    // отбивавшийся взял раунд, роли меняются
                    switchRoles();
                    System.out.println("поменяться ролями\n");
                }
                System.out.println("Выберете дальнейшее действие: \n 1-Сохранить \n 2-Загрузить \n Другое-Продолжить \n");
                Scanner scanner = new Scanner(System.in);
                int s = scanner.nextInt();
                ObjectMapper mapper = new ObjectMapper();
                switch(s){
                    case 1:
                        try {
                            mapper.writeValue(new File(".\\saves"+round+".json"), this);
                                    //att-2-main\\saves"+round+".json"), this);
                            //myLogger.info("Сохранено в файл .\\fool"+round+".json");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 2:
                        try {
                            System.out.println("Введите номер файла загрузки: \n");
                            int d = scanner.nextInt();
                            String str= "C:\\Users\\User\\Desktop\\att-2-main\\saves"+d;
                            ForJson f= mapper.readValue(str+".json", ForJson.class);
                            //myLogger.info("Загружено из файла .\\fool"+choise+".json");
                            round=f.round;
                            TRUMP = ForJson.TRUMP;
                            first = f.first;
                            second = f.second;
                            deck = f.deck;
                            attacker = f.attacker;
                            defender = f.defender;
                            currentTable = f.currentTable;
                            roundInitiated = f.roundInitiated;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());}
                        break;

                    default:

                }
            }
        }

        System.out.println("Игра закончена!\n");
        System.out.println("Победитель - " + determineWinner() + "!\n");
       //myLogger.info("Победитель - "+determineWinner());
        //  return;
    }

    // проверка победы
    public boolean victoryAchieved() {
        return ((first.victoryAchieved() || second.victoryAchieved()));
    }


    // определяет победителя
    // Следует вызывать только в том случае, если достигнута победа (true возвращает victoryAchieved)
    public Player determineWinner() {
        if (victoryAchieved()) {
            if (first.victoryAchieved()) {
                return first;
            } else {
                return second;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    // Выполняется один раунд с учетом ссылок на атакующего и защитника
    // Возвращает значение true, если атака удалась
    // Возвращает значение false, если защитник добился успеха
    public boolean round() {
        // создает ссылки на атакующего и защищающегося

        // генерация заголовка
        String roundName = "Ход " + round;
        String headerLine = "==================== " + roundName + " ====================" + "\n";
        String headerContent = "Ходит: " + attacker + " | " + "отбивается: " + defender + "\n";
        String header = "\n" + headerLine + headerContent + headerLine + "\n";

        roundInitiated = false; // раунд на начальной стадии

        System.out.println(header);
        System.out.println(roundName + " начинается!\n");

        // инициация раунда и ассоциированного с ним стола

        System.out.println(attacker + ", Ваш ход!");

        int initialAttack = playerInput(attacker);
        Card initialAttackCard = attacker.useCard(initialAttack);
        announceCardPlayed(attacker, initialAttackCard);
        //  (карта сыграла: проверить на победу!)

        if (victoryAchieved()) {
            return true; // выйти из раунда (должна быть достигнута победа, проверять после каждого раунда, чтобы немедленно продолжить отсюда)
        }

        Table roundField = new Table(initialAttackCard); // генерация стола
        currentTable = roundField;
        roundInitiated = true;

        while (!roundField.isCompleted()) {
            boolean defenderTurn = defenderResponse(roundField);//очередь отбивающегося
            if (defenderTurn || victoryAchieved()) {
                // отбивающийся забирает карты, завершая раунд
                // ИЛИ, как результат защиты, была достигнута победа
                roundInitiated = false;
                currentTable = null;
                return true; // выход из раунда
            }

            // отбивающийся ответил, разыграв карту
            boolean attackerTurn = attackerResponse(roundField);//очередь атакующего
            if (attackerTurn || victoryAchieved()) {
                // атакующий завершает раунд
                // ИЛИ, как результат атаки, была достигнута победа
                roundInitiated = false;
                currentTable = null;
                return false; // выход из раунда
            }
        }

        return false;
    }
    //вывод какие карт попали на стол
    public void announceCardPlayed(Player p, Card c) {
        if (p.isAttacker()) {
            System.out.println("\n" + p + " играет карту " + c + ", инициируя новую пару!");
        } else {
            System.out.println("\n" + p + " играет карту " + c + " в ответ!");
        }
    }

    // действие: выводит на консоль диалог
    public void turnPrompt(Player player) {
        boolean isAttacker = player.isAttacker();

        String prompt = new String("\n"); // StartHeaderline + Precontent + Content + Tail + EndHeaderline
        String promptStartLine = "\n============ Описание ============\n";
        String promptEndLine = "\n========== Конец описания ==========\n";
        String preContent = new String("Козырь: " + TRUMP + "\n");
        preContent += "Карт в колоде: " + deck.size() + "\n";
        preContent += "Карт в руке: " + player.cardsInHand() + "\n";
        String fieldString;
        String content = new String();
        String tail = new String("=== Сообщение ===\n");

        // компонент выбора карты (одинаков для всех)
        content += player.cardList();
        content += "=== Другие возможности ===\n";

        if (isAttacker) {
            // варианты действий атакующего
            if (roundInitiated) {
                // атакующий продолжает раунд
                preContent += "# Продолжение атаки #\n";
                content += "0 | Бито\n";
            } else {
                // атакующий инициирует раунд
                preContent += "# начало атаки #\n";
                content += "<нет>\n";
            }
            tail += player + ", Ваш ход!\n";
        } else {
            // отбивающийся в любое время
            preContent += "# защита #\n";
            content += "0 | Взять\n";
            tail += player + ", защищайтесь!\n";
        }

        tail += "Выберите карту из имеющихся, введя соответствующее число.\n";

        // сборка строк сообщений

        if (currentTable == null) {
            fieldString = "<стол пуст>\n";
        } else {
            fieldString = "" + currentTable;
        }

        prompt += promptStartLine + fieldString + preContent + content + tail + promptEndLine;
        System.out.println(prompt);
    }

    // выводит опции игрока и приничает ввод
    // Обрабатывает неправильный ввод (не с точки зрения играбельных карт, а по доступным номерам в списке)
    public int playerInput(Player p) {
        boolean isAttacker = p.isAttacker();
        turnPrompt(p);

        int playerSelection = -1;
        boolean properInput = false;
        while (!properInput) {
            playerSelection = sc.nextInt();
            if (isAttacker) {
                if (roundInitiated) {
                    // валидный ввод: 0 до числа карт на руках включительно
                    properInput = ((playerSelection >= 0) && (playerSelection <= p.cardsInHand()));
                } else {
                    // валидный ввод: 1 до числа карт на руках включительно
                    properInput = ((playerSelection >= 1) && (playerSelection <= p.cardsInHand()));
                }
            } else {
                // валидный ввод: 0 до числа карт на руках включительно
                properInput = ((playerSelection >= 0) && (playerSelection <= p.cardsInHand()));
            }
            if (!properInput) {
                System.out.println("Некорректный ввод. Введите, пожалуйста, приемлемое значение.");
            }
        }

        return playerSelection;
    }


    // показывает, был ли раунд заверщен отбивающимся.
    // если да, то возвращает true; стол закрываеся; отбивавшийся забрал карты.
    // если не завершен, то возвращает false, карта разыгрывается.
    public boolean defenderResponse(Table table) {
        int defenderResponse = -1;
        boolean properDefenderResponse = false;
        while (!properDefenderResponse) {
            try { // защита валидна? попробовать использовать.
                defenderResponse = playerInput(defender);
                if (defenderResponse != 0) {
                    // это карта
                    Card defenderResponseCard = defender.getCard(defenderResponse); // getCard() получение карты по введенному номеру
                    table.respond(defenderResponseCard);
                    properDefenderResponse = true;
                    defender.useCard(defenderResponse); // useCard() сброс карты по номеру
                    announceCardPlayed(defender, defenderResponseCard);
                    return false;
                } else {
                    // это не карта
                    // это запрос "забрать все карты" и завершить раунд (стол).
                    properDefenderResponse = true;
                    System.out.println("\n" + defender + " забирает все карты со стола и завершает раунд");
                    ArrayList<Card> takenCards = table.fetchAllCards();
                    for (Card card : takenCards) {
                        defender.takeCard(card);
                    }
                    table.endTable();
                    return true;
                }
            } catch (IllegalArgumentException e) { // неправильная защита
                System.out.println("\nзащищающийся не тот!");
                properDefenderResponse = false;
            }
        }
        return true;
    }

    // показывает, был ли раунд завершен атакующим
    // если завершен, то возвращает true; стол закрывается (карты - в отбой); раунд завершается; карты никто не забрал
    // если не завершен, то возвращает false; атакующий разыграл карту на столе.
    public boolean attackerResponse(Table table) {
        int attackerResponse = -1;
        boolean properAttackerResponse = false;
        while (!properAttackerResponse) {
            try { // валидное действие? пытаемся использовать.
                attackerResponse = playerInput(attacker);
                if (attackerResponse != 0) {
                    // это карта
                    Card attackerResponseCard = attacker.getCard(attackerResponse);
                    table.attack(attackerResponseCard);
                    properAttackerResponse = true;
                    attacker.useCard(attackerResponse);
                    announceCardPlayed(attacker, attackerResponseCard);
                    return false;
                } else {
                    // это не карта
                    // это запрос на завершение раунда; никто не забирает карты; стол закрывается.
                    System.out.println("\n" + attacker + " завершает раунд");
                    properAttackerResponse = true;
                    table.endTable();
                    return true;
                }
            } catch (IllegalArgumentException e) { // некорректная атакующая карат, выбрать другую
                System.out.println("некорректно выбрана атакующая карта!");
                properAttackerResponse = false;
            }
        }
        return true;
    }

    // Определяет текущего атакующего после запуска игры
    // Если верно: первый - нападающий, второй - отбивающийся
    //// Если ложно: первый - защитник, второй - нападающий
    public boolean whichAttacker() {
        return first.isAttacker();
    }

    public void setAttacker(Player p) {
        attacker = p;
        p.makeAttacker();
    }

    public void setDefender(Player p) {
        defender = p;
        p.makeDefender();
    }

    public void switchRoles() {
        Player temp = attacker;
        attacker = defender;
        defender = temp;
        first.switchRole();
        second.switchRole();
    }

    public Player getAttacker() {
        return attacker;
    }

    public Player getDefender() {
        return defender;
    }

    public boolean roundInitiated() {
        return roundInitiated;
    }
}
