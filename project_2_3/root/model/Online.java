package root.model;

public class Online {

    private String gameMode = "ai";
    private String game;

    public void setGame(String game) {
        this.game = game;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getGame() {
        return game;
    }
}
