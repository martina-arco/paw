CREATE TABLE IF NOT EXISTS STADIUM (
    stadiumid         SERIAL PRIMARY KEY,
    lowClass          INTEGER NOT NULL,
    lowClassPrice     INTEGER NOT NULL,
    mediumClass       INTEGER NOT NULL,
    mediumClassPrice  INTEGER NOT NULL,
    highClass         INTEGER NOT NULL,
    highClassPrice    INTEGER NOT NULL,
    name              VARCHAR(63) NOT NULL
);

CREATE TABLE IF NOT EXISTS PLAYER (
    playerid            SERIAL PRIMARY KEY,
    team                INTEGER NOT NULL,
    name                VARCHAR(63) NOT NULL,
    age                 INTEGER NOT NULL,
    fitness             INTEGER NOT NULL,
    value               INTEGER NOT NULL,
    potential           INTEGER NOT NULL,
    skilllevel          INTEGER NOT NULL,
    goalkeeping         INTEGER NOT NULL,
    defending           INTEGER NOT NULL,
    passing             INTEGER NOT NULL,
    finishing           INTEGER NOT NULL,
    salary              INTEGER NOT NULL,
    contractExpiration  DATE NOT NULL,
    youth               BOOL NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS FORMATION (
    formationid       SERIAL PRIMARY KEY,
    pressure          INTEGER NOT NULL,
    attitude          INTEGER NOT NULL,
    penaltyTaker      INTEGER REFERENCES PLAYER ON DELETE SET NULL,
    freekickTaker     INTEGER REFERENCES PLAYER ON DELETE SET NULL,
    captain           INTEGER REFERENCES PLAYER ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS USERS (
    userid          SERIAL PRIMARY KEY,
    username        VARCHAR(63) NOT NULL,
    password        VARCHAR(255) NOT NULL,
    mail            VARCHAR(255) NOT NULL,
    currentDay      DATE NOT NULL,
    team            INTEGER
);

CREATE TABLE IF NOT EXISTS LEAGUE (
    userid          INTEGER REFERENCES USERS,
    leagueid        SERIAL PRIMARY KEY,
    name            VARCHAR(63) NOT NULL,
    prize           INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS TEAM (
    teamid          SERIAL PRIMARY KEY,
    name            VARCHAR(63) NOT NULL,
    fanCount        INTEGER NOT NULL,
    fanTrust        INTEGER NOT NULL,
    boardTrust      INTEGER NOT NULL,
    league          INTEGER REFERENCES LEAGUE ON DELETE SET NULL,
    stadium         INTEGER REFERENCES STADIUM ON DELETE SET NULL,
    formation       INTEGER REFERENCES FORMATION ON DELETE SET NULL
);

ALTER TABLE USERS DROP CONSTRAINT IF EXISTS users_team_fk;
ALTER TABLE USERS ADD CONSTRAINT users_team_fk FOREIGN KEY (team) REFERENCES TEAM(teamid);
ALTER TABLE PLAYER DROP CONSTRAINT IF EXISTS player_team_fk;
ALTER TABLE PLAYER ADD CONSTRAINT player_team_fk FOREIGN KEY (team) REFERENCES TEAM(teamid);

CREATE TABLE IF NOT EXISTS RECEIPT (
    receiptid       SERIAL PRIMARY KEY,
    amount          REAL NOT NULL,
    type            VARCHAR(63) NOT NULL,
    team            INTEGER REFERENCES TEAM ON DELETE CASCADE NOT NULL
);

/*CREATE TABLE IF NOT EXISTS YOUTH_ACADEMY (
    youthacademyid  SERIAL PRIMARY KEY,
    player          INTEGER REFERENCES PLAYER ON DELETE CASCADE NOT NULL,
    team            INTEGER REFERENCES TEAM ON DELETE CASCADE NOT NULL
);

CREATE TABLE IF NOT EXISTS CONTRACT (
    contractid      SERIAL PRIMARY KEY,
    salary          REAL NOT NULL,
    expiration      DATE NOT NULL,
    player          INTEGER REFERENCES PLAYER ON DELETE CASCADE
);*/

CREATE TABLE IF NOT EXISTS MATCH (
    matchid         SERIAL PRIMARY KEY,
    day             DATE NOT NULL,
    home            INTEGER REFERENCES TEAM ON DELETE CASCADE NOT NULL,
    away            INTEGER REFERENCES TEAM ON DELETE CASCADE NOT NULL,
    league          INTEGER REFERENCES LEAGUE ON DELETE CASCADE NOT NULL,
    played          BOOLEAN NOT NULL DEFAULT FALSE,
    homeScore       INTEGER NOT NULL DEFAULT 0,
    awayScore       INTEGER NOT NULL DEFAULT 0,
    homePts         INTEGER NOT NULL DEFAULT 0,
    awayPts         INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS PLAYERSTATS (
    playerstatsid   SERIAL PRIMARY KEY,
    match           INTEGER REFERENCES MATCH ON DELETE CASCADE,
    player          INTEGER REFERENCES PLAYER ON DELETE CASCADE,
    performance     INTEGER NOT NULL,
    saves           INTEGER NOT NULL,
    passes          INTEGER NOT NULL,
    tackles         INTEGER NOT NULL,
    assists         INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS EVENT (
    eventid         SERIAL PRIMARY KEY,
    match           INTEGER REFERENCES MATCH ON DELETE CASCADE,
    player1         INTEGER REFERENCES PLAYER ON DELETE CASCADE,
    player2         INTEGER REFERENCES PLAYER ON DELETE CASCADE,
    type            VARCHAR(63) NOT NULL,
    minute          INTEGER NOT NULL
);


CREATE TABLE IF NOT EXISTS PLAYSAS (
    playsasid       SERIAL PRIMARY KEY,
    player          INTEGER REFERENCES PLAYER ON DELETE CASCADE,
    formation       INTEGER REFERENCES FORMATION ON DELETE CASCADE,
    type            VARCHAR(63) NOT NULL,
    x               INTEGER,
    y               INTEGER
);