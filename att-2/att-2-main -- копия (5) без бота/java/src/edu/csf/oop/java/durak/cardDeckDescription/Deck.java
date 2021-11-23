package edu.csf.oop.java.durak.cardDeckDescription;

import edu.csf.oop.java.durak.baseDescriptions.Denominations;
import edu.csf.oop.java.durak.baseDescriptions.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


public class Deck {

    private Stack<Card> cards;

    // создает стандартную перемешанную колоду из 36 карт
    public Deck() {
        cards = new Stack<Card>();

        ArrayList<Card> allCards = new ArrayList<Card>();
        for (String rank : Denominations.ranks) {
            for (String suit : Suit.suits) {
                allCards.add(new Card(rank, suit));
                Collections.shuffle(allCards); //смешать
            }
        }

        for (Card card : allCards) {//формируем stack из листа
            cards.push(card);
        }
    }

    // раздать карту, если стек пуст, то вернуть null (взять карту сверху колоды)
    public Card deal() {
        if (!isEmpty()) {
            return cards.pop();//ввытащить из стека верхний элемент
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return cards.empty();
    }

    public int size() {
        return cards.size();
    }

    // поместить карту, задавшую козырь в низ колоды для последующей раздачи (нужно 1 раз)
    public void reinsert(Card t) { cards.add(0, t); }

    @Override
    public String toString() {
        String ret = new String("[Bottom]\n");
        for (Card card : cards) {
            ret += card + "\n";
        }
        ret += "[Top]\n";
        return ret;
    }

}

