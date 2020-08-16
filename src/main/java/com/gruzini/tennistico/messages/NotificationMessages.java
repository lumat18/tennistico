package com.gruzini.tennistico.messages;

public class NotificationMessages {

    public static final String NAME_OPENING_TAG = "<span class='player-modal' th:attr='data-id=${notification.getMatchId}' onclick='showPlayerModal(this.getAttribute(&#39data-id&#39))'>";
    public static final String SCORE_OPENING_TAG = "<span class='player-modal' th:attr='data-id=${notification.getMatchId}' onclick='showScoreModal(this.getAttribute(&#39data-id&#39))'>";
    public static final String CLOSING_TAG = "</span>";

    public static final String MATCH_CREATED = "You have created an open match!";
    public static final String JOIN_REQUEST = NAME_OPENING_TAG + "%s" + CLOSING_TAG + " requests to join your game!";
    public static final String JOIN_CONFIRMED = NAME_OPENING_TAG + "%s" + CLOSING_TAG + " has confirmed your join request. Match is set up. Good luck!";
    public static final String SCORE_TO_SUBMIT = "Your match vs " + NAME_OPENING_TAG + "%s" + CLOSING_TAG + " took place recently. Please submit score. Click and fill in";
    public static final String SCORE_TO_CONFIRM = NAME_OPENING_TAG + "%s" + CLOSING_TAG + " have submitted the score of the match. Was it " + SCORE_OPENING_TAG + "%s" + CLOSING_TAG + " ? Please confirm";
    public static final String MATCH_ARCHIVED = "Match vs " + NAME_OPENING_TAG + "%s" + CLOSING_TAG + " has been archived. Saved score was " + SCORE_OPENING_TAG + "%s" + CLOSING_TAG + " Check your ranking points!";
    public static final String JOIN_REQUEST_REJECTED = "Your join request was rejected by " + NAME_OPENING_TAG + "%s" + CLOSING_TAG + ". We are sorry.";
    public static final String SCORE_REJECTED = "Score you submitted was rejected by " + NAME_OPENING_TAG + "%s" + CLOSING_TAG + ". Please fill it in again.";

    //TODO: expand match info - add place, date etc
}
