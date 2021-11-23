package edu.csf.oop.java.durak.cardDeckDescription;

import edu.csf.oop.java.durak.baseDescriptions.Denominations;
import edu.csf.oop.java.durak.baseDescriptions.Suit;
import edu.csf.oop.java.durak.play.Fool;

import java.util.Arrays;
import java.util.Random;

public class Card implements Comparable {

    private final String rank;
    private final String suit;

    private Random r = new Random();

    // создать рандомную карту
    public Card() {
        int randRankIndex = r.nextInt(9);//задаем рандомное число
        rank = Denominations.ranks[randRankIndex];//получить номенал, который назначен

        int randSuitIndex = r.nextInt(4);//задаем рандомную масть
        suit = Suit.suits[randSuitIndex];//получить масть, которая назначена
    }

    // создать карту конкретной масти и номинала
    public Card(String r, String s) {
        if (Arrays.asList(Denominations.ranks).contains(r) && Arrays.asList(Suit.suits).contains(s)) {
            rank = r;
            suit = s;
        } else {
            throw new IllegalArgumentException("Некорректный номинал или масть");
        }
    }

    @Override
    public String toString() {
        String ret = "<" + rank + ", " + suit + ">";
        return ret;
    }

    @Override
    // сравнене только по номиналу
    public int compareTo(Object o) {
        Card otherCard = (Card) o;//преобразование типа
        int thisValue = Denominations.values.get(rank);//величина текущей карты
        int otherValue = Denominations.values.get(otherCard.rank);//величина полученной карты
        return thisValue - otherValue;
    }

    // сравнение с учетом козырной масти
    // принимает козырную масть как параметр
    public int trueCompareTo(Object o, String t) {

        Card otherCard = (Card) o;//преобразование типа

        boolean thisCardIsTrump = this.isTrump(t);
        boolean otherCardIsTrump = otherCard.isTrump(t);

        int valueDifference = this.compareTo(o);

        if (thisCardIsTrump && otherCardIsTrump) {
            return valueDifference;
        } else if (thisCardIsTrump && !otherCardIsTrump) {
            return 1;
        } else if (!thisCardIsTrump && otherCardIsTrump) {
            return -1;
        } else if (sameSuit(o)) {
            return valueDifference;
        } else {
            // отличающийся случай; не может возникнуть согласно правилам
            throw new IllegalArgumentException("Другая масть");
        }
    }

    public int trueCompareTo(Object o) {
        return trueCompareTo(o, Fool.TRUMP);
    }

    // сравнивает масть с козырной, передаваемой как параметр, возвращает true, если масть совпадает с козырной
    public boolean isTrump(String t) {
        return suit.equals(t);
    }

    /*public boolean isTrump() {
        return suit.equals(Fool.TRUMP);
    }*/

    //проверка совпадения мастей
    public boolean sameSuit(Object o) {

        Card otherCard = (Card) o;

        String thisSuit = this.suit;
        String otherSuit = otherCard.suit;

        return thisSuit.equals(otherSuit);
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {return Denominations.values.get(rank);}}

// предполагается, что карты неизменны, потому и сеттеры н нужны

