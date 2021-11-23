package edu.csf.oop.java.durak.play;

import edu.csf.oop.java.durak.cardDeckDescription.Card;
import edu.csf.oop.java.durak.cardDeckDescription.Deck;

import java.util.ArrayList;

public class Player {

    private OnHand hand;
    private String name;
    private final int id;
    private Deck deck;
    private static int count = 0;
    private boolean attacker;

    // создает нового игрока с пустой "рукой"
    public Player() {
        count++;
        id = count;
        name = "Player " + id;
        hand = new OnHand();
        deck = new Deck();
        attacker = false;
    }

    // создает нового игрока с 6 картами из заданной колоды (параметр)
    public Player(Deck d) {
        count++;
        id = count;
        name = "Player " + id;
        hand = new OnHand();
        deck = d;
        dealCards(6);
        attacker = false;
    }

    // создает нового игрока с 6 картами из заданной колоды (параметр) и заданным именем
    public Player(Deck d, String n) {
        count++;
        id = count;
        name = n;
        hand = new OnHand();
        deck = d;
        dealCards(6);
        attacker = false;
    }

    // раздает карту из заданной колоды
    // если колода пуста, то ничего не происходит
    public void deal(Deck d) {
        if (!d.isEmpty()) {
            Card thisCard = d.deal();
            hand.add(thisCard);
        }
    }

    // раздает карту из ассоциированной колоды (переменной экземпляра).
    public void deal() {
        deal(deck);
    }

    // раздает n карт из заданной колоды
    public void dealCards(Deck d, int n) {
        for (int i = 0; i < n; i++) {
            deal(deck);
        }
    }

    // раздает n карт из ассоциированной колоды (instance variable).
    public void dealCards(int n) {
        for (int i = 0; i < n; i++) {
            deal();
        }
    }

    //взять карту(если не отбился)
    public void takeCard(Card c) {
        hand.add(c);
    }

    // убирает заданную карту из "руки"
    public void discard(Card c) {
        hand.remove(c);
    }

    public int cardsInHand() {
        return hand.size();
    }

    public OnHand getHand(){
        return hand;
    }

    // раздает (дополняет) карты до 6
    public void replenish() {
        int toDeal = hand.numberToDeal();
        dealCards(toDeal);
    }

    // проверка победы
    public boolean victoryAchieved() {
        return ((hand.size() <= 0) && (deck.isEmpty()));
    }

    public boolean isAttacker() {//являюсь ли атакующим?
        return attacker;
    }

    public void makeAttacker() {//стать атакующим
        attacker = true;
    }

    public void makeDefender() {//стать отбивающим
        attacker = false;
    }

    public void switchRole() {
        System.out.println("Изменение атакующей роли: "+attacker+" на "+!attacker+"\n");
        attacker = !attacker;

    }

    @Override
    public String toString() {
        return name;
    }

    // для цели изменения ролей
    // Ручной поиск на основе индекса ArrayList для выполнения переключения в командной строке
    //  i представляет натуральное число, указанное во время игры.(номер карты)
    // оно уменьшается на единицу до нуля и передается в этой форме

    public Card getCard(int i) {
        return hand.getCardByIndex(i - 1);
    }

    public Card useCard(int i) {
        return hand.useCardByIndex(i - 1);
    }

    // Счетчик соответствует (индексу массива + 1)
    public String cardList() {
        String ret = "\n=== Ваши карты ===\n";
        ArrayList<Card> cards = hand.getCards();
        int i = 1;
        for (Card card : cards) {
            ret += i + " ~ " + card + "\n";
            i += 1;
        }
        ret += "\n";
        return ret;
    }

}