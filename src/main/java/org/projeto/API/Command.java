package org.projeto.API;

import java.sql.SQLException;

public interface Command {

    void execute() throws SQLException;
    String getName();

}
