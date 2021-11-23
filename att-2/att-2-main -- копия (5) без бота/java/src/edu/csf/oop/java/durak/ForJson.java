package edu.csf.oop.java.durak;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.csf.oop.java.durak.cardDeckDescription.Deck;
import edu.csf.oop.java.durak.play.Player;
import edu.csf.oop.java.durak.play.Table;

public class ForJson {

    public static String TRUMP;
    public Player first;
    public Player second;
    public Deck deck;
    public int round; // натуральное число
    public Player attacker;
    public Player defender;
    public Table currentTable;
    public boolean roundInitiated; // Если раунд идет и завершил свои начальные этапы


}
