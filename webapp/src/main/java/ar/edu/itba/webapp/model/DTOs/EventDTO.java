package ar.edu.itba.webapp.model.DTOs;

import ar.edu.itba.model.Event;

public class EventDTO {
    private final String player1, player2, type;
    private final int minute;

    private static String typeToString(Event.Type type){
        switch (type){
            case PASS:
                return "PASS";
            case SAVE:
                return "SAVE";
            case HOMESCORE:
                return "HOMESCORE";
            case AWAYSCORE:
                return "AWAYSCORE";
            case ASSIST:
                return "ASSIST";
            case TACKLE:
                return "TACKLE";
            case RED_CARD:
                return "RED CARD";
            case SUBSTITUTE:
                return "SUBSITUTE";
            case YELLOW_CARD:
                return "YELLOW CARD";
            default:
                return "";
        }
    }

    public EventDTO(Event event){
        this(event.getP1().getName(), event.getP2()==null?"":event.getP2().getName(), typeToString(event.getType()), event.getMinute());
    }

    public EventDTO(String player1, String player2, String type, int minute) {
        this.player1 = player1;
        this.player2 = player2;
        this.type = type;
        this.minute = minute;
    }

    public int getMinute() {
        return minute;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public String getType() {
        return type;
    }
}
