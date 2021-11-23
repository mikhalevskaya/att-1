package edu.csf.oop.java.durak.play;


import edu.csf.oop.java.durak.cardDeckDescription.Card;
import java.util.ArrayList;

public class Table {
    // генерируется при начале фазы атаки

    // ArrayList из пар

    private ArrayList<Step> pairs;
    private ArrayList<String> playedRanks;
    private boolean completed;

    // пустой стол
    public Table() {
        pairs = new ArrayList<Step>();
        playedRanks = new ArrayList<String>();
        completed = false;
    }

    // стол, генерируемый новой атакой
    public Table(Card a) {
        pairs = new ArrayList<Step>();
        playedRanks = new ArrayList<String>();
        completed = false;

        Step initialPair = new Step(a);
        pairs.add(initialPair);

        String initialCardRank = a.getRank();
        playedRanks.add(initialCardRank);
    }

    public ArrayList<String> getPlayedRanks(){
        return playedRanks;
    }

    // генерирует новую пару с атакующей картой, добавляет на стол
    public void attack(Card a) {
        if (canAttack() && isValidAttack(a)) {
            Step newAttackPair = new Step(a);
            pairs.add(newAttackPair);
            playedRanks.add(newAttackPair.getAttacker().getRank());
        } else {
            throw new IllegalArgumentException("Вы не можете атаковать");
        }
    }

    // ответ на текущую окрытую пару
    public void respond(Card d) {
        if (anyOpenPairs()) {
            Step openPair = currentOpenPair();
            openPair.response(d); // обрабатывается в Pair
            playedRanks.add(d.getRank());
        }
    }

    // атака валидна, если атакующая карта - одна из уже участвовавших в текущем раунде
    public boolean isValidAttack(Card a) {
        String thisRank = a.getRank();
        for (String rank : playedRanks) {
            if (thisRank.equals(rank)) {
                return true;
            }
        }
        return false;
    }

    // вы можете атаковать, если нет открытых пар
    public boolean canAttack() {
        return !anyOpenPairs();
    }

    public boolean anyOpenPairs() {
        for (Step pair : pairs) {
            if (!pair.isCompleted()) {
                return true;
            }
        }
        return false;
    }

    public boolean isCompleted() {
        return completed;
    }

    // вохвращает ссылку на текущую открытцю пару:
    // предполагается одиночная открытая пара;
    // использованию этого метода должен предшествовать вызов anyOpenPairs(), чтобы определить логику игры
    public Step currentOpenPair() {
        Step ret = null;
        for (Step pair : pairs) {
            if (!pair.isCompleted()) {
                ret = pair;
            }
        }
        if (ret != null) {
            return ret;
        } else {
            throw new IllegalArgumentException("Нет открытых пар.");
        }
    }
    //проверка завершенности стола
    public void toggleCompleted() {
        completed = !completed;
    }
    // конец раунда
    // показывает результат раунда:
    // True: атака была успешна
    // False: защита была успешна
    // использовать в паре с fetchAllCards() один раз в конце раунда

    public boolean endTable() {
        toggleCompleted();
        return anyOpenPairs();
    }

    // возвращает ArrayList всех карт, ассоциированных со этим столом.
    // Следует использовать в сочетании с endTable() после окончания раунда
    public ArrayList<Card> fetchAllCards() {//в случае когда игрок не отбился, забирает карты со стола
        ArrayList<Card> ret = new ArrayList<Card>();
        for (Step pair : pairs) {
            ArrayList<Card> pairCards = pair.fetchAllCards();
            for (Card card: pairCards) {
                ret.add(card);
            }
        }
        return ret;
    }

    @Override
    public String toString() {
        String ret = new String("+++ Стол +++\n\n");
        for (Step pair : pairs) {
            ret += pair + "\n";
        }
        ret+=" Сыгравшие номиналы:\n";
        for (String s:playedRanks){
            ret+=s+" ";
        }
        ArrayList<Card> all=fetchAllCards();
        ret += "\n+++ Стол +++\n";
        return ret;
    }

}
