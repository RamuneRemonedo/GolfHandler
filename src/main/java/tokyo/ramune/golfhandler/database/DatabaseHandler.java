package tokyo.ramune.golfhandler.database;

import tokyo.ramune.golfhandler.player.PlayerDatabaseHandler;

public class DatabaseHandler {

    public static void createTables() {
        PlayerDatabaseHandler.createTable();
    }
}
