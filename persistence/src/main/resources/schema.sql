/*
CREATE TABLE IF NOT EXISTS users (
  userid SERIAL PRIMARY KEY,
  username varchar(100),
  password varchar(100)
);
*/
CREATE TABLE IF NOT EXISTS "USER" (
    userid          SERIAL PRIMARY KEY,
    name            Varchar(64) NOT NULL,
    password        Varchar(64) NOT NULL,
    mail            Varchar(64) NOT NULL,
    today           date NOT NULL
);

CREATE TABLE IF NOT EXISTS STADIUM (
    userid            SERIAL REFERENCES "USER",
    stadiumid         SERIAL PRIMARY KEY,
    lowClass          INTEGER NOT NULL,
    lowClassPrice     INTEGER NOT NULL,
    mediumClass       INTEGER NOT NULL,
    mediumClassPrice  INTEGER NOT NULL,
    highClass         INTEGER NOT NULL,
    highClassPrice    INTEGER NOT NULL,
    name              Varchar(64) NOT NULL
);


CREATE TABLE IF NOT EXISTS PLAYER (
    userid            SERIAL REFERENCES "USER",
    playerid          SERIAL PRIMARY KEY,
    name              Varchar(64) NOT NULL,
    age               INTEGER NOT NULL,
    fitness           INTEGER NOT NULL,
    value             INTEGER NOT NULL,
    potential         INTEGER NOT NULL,
    skilllevel        INTEGER NOT NULL,
    goalkeeping       INTEGER NOT NULL,
    defending         INTEGER NOT NULL,
    passing           INTEGER NOT NULL,
    finishing         INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS FORMATION (
    userid            SERIAL REFERENCES "USER",
    formationid       SERIAL PRIMARY KEY,
    pressure          INTEGER NOT NULL,
    attitude          INTEGER NOT NULL,
    penaltyTaker      SERIAL REFERENCES PLAYER ON DELETE SET NULL,
    freekickTaker     SERIAL REFERENCES PLAYER ON DELETE SET NULL,
    captain           SERIAL REFERENCES PLAYER ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS LEAGUE (
    userid          SERIAL REFERENCES "USER",
    leagueid        SERIAL PRIMARY KEY,
    name            Varchar(64) NOT NULL,
    price           REAL NOT NULL
);

CREATE TABLE IF NOT EXISTS TEAM (
    userid          SERIAL REFERENCES "USER",
    teamid          SERIAL PRIMARY KEY,
    name            Varchar(64) NOT NULL,
    fanCount        INTEGER NOT NULL,
    fanTrust        INTEGER NOT NULL,
    boardTrust      INTEGER NOT NULL,
    league          SERIAL REFERENCES LEAGUE ON DELETE SET NULL,
    stadium         SERIAL REFERENCES STADIUM ON DELETE SET NULL,
    formation       SERIAL REFERENCES FORMATION ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS RECEIPT (
    userid          SERIAL REFERENCES "USER",
    receiptid       SERIAL PRIMARY KEY,
    amount          REAL NOT NULL,
    type            INTEGER NOT NULL,
    team            SERIAL REFERENCES TEAM ON DELETE CASCADE NOT NULL
);

CREATE TABLE IF NOT EXISTS YOUTH_ACADEMY (
    userid          SERIAL REFERENCES "USER",
    youthacademyid  SERIAL PRIMARY KEY,
    player          SERIAL REFERENCES PLAYER ON DELETE CASCADE NOT NULL,
    team            SERIAL REFERENCES TEAM ON DELETE CASCADE NOT NULL
);

CREATE TABLE IF NOT EXISTS CONTRACT (
    userid          SERIAL REFERENCES "USER",
    playsforid      SERIAL PRIMARY KEY,
    salary          REAL NOT NULL,
    expiration      date NOT NULL,
    player          SERIAL REFERENCES PLAYER ON DELETE CASCADE,
    team            SERIAL REFERENCES TEAM ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS MATCH (
    userid          SERIAL REFERENCES "USER",
    matchid         SERIAL PRIMARY KEY,
    day             date NOT NULL,
    home            SERIAL REFERENCES TEAM ON DELETE CASCADE NOT NULL,
    away            SERIAL REFERENCES TEAM ON DELETE CASCADE NOT NULL,
    league          SERIAL REFERENCES LEAGUE ON DELETE CASCADE NOT NULL,
    played          BOOLEAN NOT NULL,
    homeScore       INTEGER,
    awayScore       INTEGER,
    homePts         INTEGER,
    awayPts         INTEGER
);

CREATE TABLE IF NOT EXISTS PLAYERSTATS (
    userid          SERIAL REFERENCES "USER",
    playerstatsid   SERIAL PRIMARY KEY,
    match           SERIAL REFERENCES MATCH ON DELETE CASCADE,
    player          SERIAL REFERENCES PLAYER ON DELETE CASCADE,
    performance     INTEGER NOT NULL,
    saves           INTEGER NOT NULL,
    passes          INTEGER NOT NULL,
    tackles         INTEGER NOT NULL,
    assists         INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS EVENT (
    userid          SERIAL REFERENCES "USER",
    eventid         SERIAL PRIMARY KEY,
    match           SERIAL REFERENCES MATCH ON DELETE CASCADE,
    player1         SERIAL REFERENCES PLAYER ON DELETE CASCADE,
    player2         SERIAL REFERENCES PLAYER ON DELETE CASCADE,
    eventType       INTEGER NOT NULL,
    minute          INTEGER NOT NULL
);


CREATE TABLE IF NOT EXISTS PLAYSAS (
    userid          SERIAL REFERENCES "USER",
    playsasid       SERIAL PRIMARY KEY,
    player          SERIAL REFERENCES PLAYER ON DELETE CASCADE,
    formation       SERIAL REFERENCES FORMATION ON DELETE CASCADE,
    type            INTEGER NOT NULL,
    x               INTEGER NOT NULL,
    y               INTEGER NOT NULL
);