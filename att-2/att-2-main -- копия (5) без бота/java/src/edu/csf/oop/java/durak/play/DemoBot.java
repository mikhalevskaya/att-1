package edu.csf.oop.java.durak.play;
//package edu.csf.oop.java.durak;

import edu.csf.oop.java.durak.baseDescriptions.Denominations;
import edu.csf.oop.java.durak.cardDeckDescription.Card;
import edu.csf.oop.java.durak.cardDeckDescription.Deck;
import edu.csf.oop.java.durak.play.Player;
import edu.csf.oop.java.durak.play.Table;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class DemoBot {

    public static String TRUMP;
    private Player first;
    private Player second;
    private Deck deck;
    private int round; // натуральное число
    private Player attacker;
    private Player defender;
    private Table currentTable;
    private boolean roundInitiated;

    public Scanner sc = new Scanner(System.in);
    public Random r = new Random();

    // Set default trump to hearts
    public DemoBot() {run();}

    public void run() {
        boolean running = true;

        while (running) {
            setup();
            game();
            System.out.println("Игра закончена.");
            System.out.println("Играть снова? y/n");

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
    public void setup() {
        System.out.println("Играем в дурака!");

        System.out.println("\nИмя первого игрока: Мурзик");
        System.out.println("Имя второго игрока: Шарик");

        System.out.println("\nСоздание игры...");
        System.out.println("Перемешивание карт...");
        deck = new Deck();

        System.out.println("Раздача...");
        first = new Player(deck, "Мурзик");
        second = new Player(deck, "Шарик");

        System.out.println("Определение  козыря...");
        Card trumpCard = deck.deal();
        String trumpSuit = trumpCard.getSuit();
        TRUMP = trumpSuit;

        System.out.println("Козырь: " + TRUMP + "!\n");

        //Помещаем карту, назначившую козырь, в низ колоды
        deck.reinsert(trumpCard);

        //начинаем первый раунд
        round = 1;
        //игра готова
    }

    // работа экземпляра игры
    public void game() {
        System.out.println("Определение стартового атакующего...\n");
        if (r.nextInt(2) == 1) {
            setAttacker(first);
            setDefender(second);
        } else {
            setAttacker(second);
            setDefender(first);
        }

        System.out.println("Ход начинает: " + attacker + ".");
        System.out.println("Защищается: " + defender + ".\n");

        // создание раундов и игра до победы victoryAchieved()
        boolean gameOver = false;
        while (!gameOver) {
            boolean thisRound = round(); // Run a round

            if (victoryAchieved()) {
                // Победа была достигнута в какой-то момент
                // попадаем сюда сразу после выхода из раунда
                gameOver = true; // выход из цикла игры
            } else {
                // победа не достигнута
                // раздать карты (дополнить), начиная с атаковавшего
                attacker.replenish();
                defender.replenish();
                round++; // увеличить номер раунда
                if (thisRound) {
                    // атакующий взял раунд и ходит снова; переключения ролей не происходит
                    System.out.println("Этот раунд взял игрок "+attacker+"\n");

                } else {
                    // отбивавшийся взял раунд, роли меняются
                    switchRoles();
                    System.out.println("поменяться ролями\n");
                }
            }
        }

        System.out.println("Игра закончена!\n");
        System.out.println("Победитель - " + determineWinner() + "!\n");
        return;
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
        /// создает ссылки на атакующего и защищающегося

        // генерация заголовка
        String roundName = "Ход " + round;
        System.out.println(roundName + " начинается!");

        String headerLine = "==================== " + roundName + " ====================" + "\n";
        String headerContent = "Ходит: " + attacker + " | " + "отбивается: " + defender + "\n";
        String header = headerLine + headerContent + headerLine;

        roundInitiated = false; // раунд на начальной стадии

        System.out.println(header);

        turnPrompt(attacker);

        // инициация раунда и ассоциированного с ним стола

        int initialAttack=-1; // выбрать атакующую карту по принципу наименьшего не козырного номинала, при игре - playerInput(attacker);
        ArrayList<Card> list= attacker.getHand().getCards();

        // printTable(list);

        int min= Denominations.values.get(list.get(0).getRank());
        // System.out.println(min);
        //int index=0;//-1;//0
        for (int i=0;i<list.size();i++){

            if ((!(list.get(i).isTrump(TRUMP)))&&(Denominations.values.get(list.get(i).getRank()).compareTo(min)<0)) //{ System.out.println("!!!");continue;}
            //f (Denominations.values.get(list.get(i).getRank()).compareTo(min)<0)
            {
                min= Denominations.values.get(list.get(i).getRank());
                initialAttack=i; // был index до 12-11-21
                //System.out.println(" не козырь и меньше min "+Integer.toString(initialAttack)); // был index до 12-11-21
                //System.out.println("i="+Integer.toString(i));
            }
        }
        //System.out.println("found card number "+index+"\n");


        if (initialAttack==-1) {
            initialAttack+=1;
            //System.out.println("почему-то был 0");
        }// 12-11-21 добавлено на случай, если найден козырь (его брать "нельзя" по условию)
        initialAttack+=1;// было index+1 до 12-11-21
        initialAttack=playerInput(attacker,initialAttack);
        //System.out.println("found card number "+initialAttack+"\n");
        // System.out.println(initialAttack);
        //int k=sc.nextInt();

        Card initialAttackCard = attacker.useCard(initialAttack);
        announceCardPlayed(attacker, initialAttackCard);
        //  (карта сыграла: проверить на победу!)

        if (victoryAchieved()) {
            return true; // выйти из раунда (должна быть достигнута победа, проверять после каждого раунда, чтобы немедленно продолжить отсюда)
        }

        Table roundField = new Table(initialAttackCard); // Generate a Field
        currentTable = roundField;
        roundInitiated = true;

        while (!roundField.isCompleted()) {
            boolean defenderTurn = defenderResponse(roundField); // этот метод изменен  для автоматического выполнения!!!
            if (defenderTurn || victoryAchieved()) {
                // отбивающийся забирает карты, завершая раунд
                // ИЛИ, как результат защиты, была достигнута победа
                roundInitiated = false;
                currentTable = null;
                //System.out.println("defender response");
                return true;//false; // выход из раунда
            }

            // отбивающийся ответил, разыграв карту

            boolean attackerTurn = attackerResponse(roundField); // !!! записать этот метод для автоматического выполнения!!!
            if (attackerTurn || victoryAchieved()) {
                // атакующий завершает раунд
                // ИЛИ, как результат атаки, была достигнута победа
                roundInitiated = false;
                currentTable = null;
                return false;
                //return true; // выход из раунда
            }
        }

        return false;
    }

    public void printTable(ArrayList<Card> list){
        System.out.println("На столе");
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i)+" ");
        }
    }

    public void announceCardPlayed(Player p, Card c) {
        if (p.isAttacker()) {
            System.out.println("\n" + p + " сыграл " + c + ", инициализируя новую пару!");
        } else {
            System.out.println("\n" + p + " сыграл " + c + " в ответ!");
        }
    }

    // показывает, был ли раунд заверщен отбивающимся.
    // если да, то возвращает true; стол закрываеся; отбивавшийся забрал карты.
    // если не завершен, то возвращает false, карта разыгрывается.
    public boolean defenderResponse(Table table) {
        int defenderResponse = -1;
        //   defenderResponse = playerInput(defender);
        int i = 0;
        Card attCard = table.currentOpenPair().getAttacker();
        int nom = attCard.getValue();
        ArrayList<Card> list = defender.getHand().getCards();
        /*System.out.println("---- Defender has:-------");
        for (Card cL:list){
            System.out.println(cL.toString());
        }
        System.out.println("----===============-------");*/
        // turnPrompt(defender);
        boolean notFound = true;

        while ((notFound)&&(i<list.size()))
        //{   for (Card card : list) //присваиваем i, если найдена подходящая для отбоя карта
        {
            //System.out.println("card.getSuit() " + card.getSuit() + " attCard.getSuit() " + attCard.getSuit());
            Card card=list.get(i);
            //System.out.println("card.getSuit() "+card.getSuit()+" attCard.getSuit() "+attCard.getSuit());

            //System.out.println(card.getSuit().equals(attCard.getSuit()));
            //System.out.println(card.getValue()+ " " +(nom));
            if ((card.getSuit().equals(attCard.getSuit())) && (card.getValue() > nom)) { //одинаковая масть и у защищающегося больший номинал
                defenderResponse = i;
                notFound=false;
                break;
            } else  //разные масти
                if (card.getSuit().equals(TRUMP) /*== TRUMP*/) {
                    defenderResponse = i;
                    notFound=false;
                    break;
                } //карта защищающегося - козырь, а атакующего - нет
            i++;

            //   }
        }
        defenderResponse=defenderResponse+1; //чтобы был именно номер, а не индекс в листе
        defenderResponse = playerInput(defender,defenderResponse);
        // System.out.println("Defender response: "+defenderResponse);
        //Scanner sc=new Scanner(System.in);
        //int p=sc.nextInt();
        try{ // до 12-11-21 екн-catch отсутствовал
            //System.out.println("Defender response: "+defenderResponse+" "+Boolean.toString(defenderResponse != 0)+"\n");
            if (defenderResponse != 0) {
                //System.out.println("Defender response: "+Integer.toString(defenderResponse)+"\n");
                // это карта
                /** //turnPrompt(defender); /**new*/
                Card defenderResponseCard = defender.getCard(defenderResponse); // getCard() получение карты по введенному номеру
                //System.out.println(" отвечает картой: "+defenderResponseCard.toString());
                table.respond(defenderResponseCard);

                //System.out.println(defenderResponseCard.toString()+"\n");

                defender.useCard(defenderResponse); // useCard() сброс карты по номеру
                announceCardPlayed(defender, defenderResponseCard);
                return false;
            } else {
                // это не карта
                // это запрос "забрать все карты" и завершить раунд (стол).

                System.out.println("\n" + defender + " забирает все карты со стола и завершает раунд");
                ArrayList<Card> takenCards = table.fetchAllCards();
                for (Card card : takenCards) {
                    defender.takeCard(card);
                }
                table.endTable();
                return true;
            }} catch (IllegalArgumentException e){
            System.out.println(""); /**проблема отбоя*/
        }

        return true; // до 12-11-21 было закомментировано
    }

    // выводит опции игрока и приничает ввод
    // Обрабатывает неправильный ввод (не с точки зрения играбельных карт, а по доступным номерам в списке)
    public int playerInput(Player p, int playerSelection) {
        boolean isAttacker = !p.isAttacker();

        // System.out.println("атакующий ли  активен? "+isAttacker);
        // turnPrompt(p);

        //int playerSelection = 1;//-1;
        boolean properInput = false;
        boolean fits=false;
        while (!properInput) {

            //playerSelection = sc.nextInt();
            if (isAttacker) {

                if (roundInitiated) {
                    // валидный ввод: 0 до числа карт на руках включительно, подходящие для отбоя
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



    // автовыбор карты/завершения для ответа
 /*   public int autoSelect(Player def){
        int result=-1;
        boolean properSelection= false;

        for (Card c: def.getHand().getCards()){
           if (currentTable.currentOpenPair().getAttacker().trueCompareTo(c)<0)
           {result=def.getHand().getCards().indexOf(c)+1;}
        }

        if (result<0) result=0;
        System.out.println(result);

        return result;
    }*/

    // Returns a boolean.
    // Indicates whether or not round was ended by attacker.
    // If ended: returns true. Field has been closed. Round over. No one took cards.
    // If not ended: returns false. A card has been played in the field by the attacker.
    public boolean attackerResponse(Table table) {
        int attackerResponse = -1;

        ArrayList<Card> list= attacker.getHand().getCards();

        ArrayList<String> allOnTable=table.getPlayedRanks();

        int i=0;
        boolean notFound=true;
        while ((i<list.size())&&(notFound)){
            //for (int j=0;j<allOnTable.size();j++) {
            //if (list.get(i).getRank().compareTo(allOnTable.get(j))==0) {attackerResponse=i; found=true;  break; }
            //   }
            for (int j=0;j<allOnTable.size();j++)
                if (list.get(i).getRank().compareTo(table.getPlayedRanks().get(j))==0){
                    attackerResponse=i;
                    notFound=false;
                    break;
                }
            i++;
        }
        attackerResponse+=1;
        attackerResponse = playerInput(attacker,attackerResponse);
        if (attackerResponse != 0) {
            // This is a card.
            turnPrompt(attacker);
            Card attackerResponseCard = attacker.getCard(attackerResponse);
            table.attack(attackerResponseCard);

            attacker.useCard(attackerResponse);
            announceCardPlayed(attacker, attackerResponseCard);
            return false;
        } else {
            // This is not a card.
            // This is a request to end the round. No one takes any cards. The field closes.
            System.out.println("\n" + attacker + " завершает раунд");

            table.endTable();
            return true;
        }
        //return true; // Satisfy Java
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
        String tail = "=== Сообщение ===\n";

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
            tail += player + ", защищайтеь!\n";
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

    // Determines current attacker once game is initiated
    // If true: one is attacker, two is defender
    // If false: one is defender, two is attacker
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
} //конец класса демо-бота





