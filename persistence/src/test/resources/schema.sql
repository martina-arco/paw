
CREATE TABLE IF NOT EXISTS PLAYER (
  playerid            IDENTITY PRIMARY KEY,
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
  formationid       IDENTITY PRIMARY KEY,
  pressure          INTEGER NOT NULL,
  attitude          INTEGER NOT NULL,
  penaltyTaker      INTEGER REFERENCES PLAYER ON DELETE SET NULL,
  freekickTaker     INTEGER REFERENCES PLAYER ON DELETE SET NULL,
  captain           INTEGER REFERENCES PLAYER ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS USERS (
  userid          IDENTITY PRIMARY KEY,
  username        VARCHAR(63) NOT NULL,
  password        VARCHAR(255) NOT NULL,
  mail            VARCHAR(255) NOT NULL,
  currentDay      DATE NOT NULL,
  team            INTEGER
);

CREATE TABLE IF NOT EXISTS LEAGUE (
  userid          INTEGER REFERENCES USERS ON DELETE CASCADE,
  leagueid        IDENTITY PRIMARY KEY,
  name            VARCHAR(63) NOT NULL,
  prize           INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS TEAM (
  teamid          IDENTITY PRIMARY KEY,
  name            VARCHAR(63) NOT NULL,
  fanCount        INTEGER NOT NULL,
  fanTrust        INTEGER NOT NULL,
  boardTrust      INTEGER NOT NULL,
  league          INTEGER REFERENCES LEAGUE ON DELETE CASCADE,
  formation       INTEGER REFERENCES FORMATION ON DELETE SET NULL,
  money           INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS STADIUM (
  stadiumid         IDENTITY PRIMARY KEY,
  lowClass          INTEGER NOT NULL,
  lowClassPrice     INTEGER NOT NULL,
  mediumClass       INTEGER NOT NULL,
  mediumClassPrice  INTEGER NOT NULL,
  highClass         INTEGER NOT NULL,
  highClassPrice    INTEGER NOT NULL,
  name              VARCHAR(63) NOT NULL,
  team              INTEGER NOT NULL REFERENCES TEAM ON DELETE CASCADE
);

ALTER TABLE USERS ADD CONSTRAINT IF NOT EXISTS users_team_fk FOREIGN KEY (team) REFERENCES TEAM(teamid) ON DELETE SET NULL;
ALTER TABLE PLAYER ADD CONSTRAINT IF NOT EXISTS player_team_fk FOREIGN KEY (team) REFERENCES TEAM(teamid) ON DELETE CASCADE;

CREATE TABLE IF NOT EXISTS RECEIPT (
  receiptid       IDENTITY PRIMARY KEY,
  amount          INTEGER NOT NULL,
  type            VARCHAR(63) NOT NULL,
  team            INTEGER NOT NULL REFERENCES TEAM ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS MATCH (
  matchid         IDENTITY PRIMARY KEY,
  day             DATE NOT NULL,
  home            INTEGER NOT NULL REFERENCES TEAM ON DELETE CASCADE,
  away            INTEGER NOT NULL REFERENCES TEAM ON DELETE CASCADE,
  league          INTEGER NOT NULL REFERENCES LEAGUE ON DELETE CASCADE,
  played          BOOLEAN NOT NULL DEFAULT FALSE,
  homeScore       INTEGER NOT NULL DEFAULT 0,
  awayScore       INTEGER NOT NULL DEFAULT 0,
  homePts         INTEGER NOT NULL DEFAULT 0,
  awayPts         INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS PLAYERSTATS (
  playerstatsid   IDENTITY PRIMARY KEY,
  match           INTEGER REFERENCES MATCH ON DELETE CASCADE,
  player          INTEGER REFERENCES PLAYER ON DELETE CASCADE,
  performance     INTEGER NOT NULL,
  saves           INTEGER NOT NULL,
  passes          INTEGER NOT NULL,
  tackles         INTEGER NOT NULL,
  assists         INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS EVENT (
  eventid         IDENTITY PRIMARY KEY,
  match           INTEGER REFERENCES MATCH ON DELETE CASCADE,
  player1         INTEGER REFERENCES PLAYER ON DELETE CASCADE,
  player2         INTEGER REFERENCES PLAYER ON DELETE CASCADE,
  type            VARCHAR(63) NOT NULL,
  minute          INTEGER NOT NULL
);


CREATE TABLE IF NOT EXISTS PLAYSAS (
  playsasid       IDENTITY PRIMARY KEY,
  player          INTEGER REFERENCES PLAYER ON DELETE CASCADE,
  formation       INTEGER REFERENCES FORMATION ON DELETE CASCADE,
  type            VARCHAR(63) NOT NULL,
  x               INTEGER,
  y               INTEGER
);