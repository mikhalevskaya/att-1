package edu.csf.oop.java.durak.play;

import edu.csf.oop.java.durak.cardDeckDescription.Card;

import java.util.ArrayList;

public class OnHand {
    private ArrayList<Card> cards;

    // пустая "рука"
    public OnHand() {
        cards = new ArrayList<Card>();
    }

    // в руке n случайных карт
    public OnHand(int n) {
        cards = new ArrayList<Card>();
        for (int i = 0; i < n; i++) {
            Card thisCard = new Card();
            cards.add(thisCard);
        }
    }

    public void add(Card c) {
        cards.add(c);
    }

    public void remove(Card c) {
        cards.remove(c);
    }

    public int size() {
        return cards.size();
    }

    public boolean needsToDeal() {// нужно ли что-то раздать
        if (size() > 6) {
            return true;
        } else {
            return false;
        }
    }

    public int numberToDeal() {//сколько карт нужно раздать
        if (needsToDeal()) {
            return 0;
        } else {
            return 6 - size();
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card getCardByIndex(int i) {
        return cards.get(i);
    }

    public Card useCardByIndex(int i) {
        return cards.remove(i);
    }

    @Override
    public String toString() {
        String ret = new String();
        for (Card c : cards) {
            ret += c + "\n";
        }
        return ret;
    }

}
