package edu.csf.oop.java.durak.play;

import edu.csf.oop.java.durak.cardDeckDescription.Card;

import java.util.ArrayList;

public class Step {
    // пара атака/защита как часть игры

    private Card attacker;
    private Card defender;


    private boolean completed;

    public Step(Card a) {
        attacker = a;
        completed = false; // Completed охначает: получен ли ответ (есть ли защищающийся?)
    }

    // ответ защищающегося
    public void response(Card d) {
        setDefender(d);
        toggleCompleted();
    }

    public Card getAttacker() {
        return attacker;
    }

    public Card getDefender() {
        return defender;
    }

    public boolean isCompleted() {
        return completed;
    }

    // атакующего менять не предполагается

    // Это удачный отбой, если он побеждает
    public boolean isValidDefender(Card d) {
        try {
            if (d.trueCompareTo(attacker) > 0) {
                return true;
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
        return false;
    }

    // установка защитника
    // Только действительная карта, если она бьет атакующую карту
    public void setDefender(Card d) {
        if (isValidDefender(d)) {
            defender = d;
        } else {
            throw new IllegalArgumentException("Invalid defender");
        }
    }

    public void toggleCompleted() {//переключение завершено
        completed = !completed;
    }

    // если пара завершена, возвращает ArrayList с 2 картами (атакующая, отбивающаяся)
    // если пара завершена, возвращает ArrayList с 1 картой (атакующей)
    public ArrayList<Card> fetchAllCards() {
        ArrayList<Card> ret =new ArrayList<Card>(); // OnTable;
        if (completed) {
            ret.add(attacker);
            ret.add(defender);
        } else {
            ret.add(attacker);
        }
        return ret;
    }

    @Override
    public String toString() {
        String ret = new String("{Пара}\n");
        for (Card card : fetchAllCards()) {
            ret += card + "\n";
        }
        ret += "{Пара}\n";
        return ret;
    }
}

