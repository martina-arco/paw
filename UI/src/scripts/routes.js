define([], function() {
  return {
    defaultRoutePath: '/login',
    routes: {
      '/home': {
        templateUrl: 'views/home.html',
        controller: 'HomeCtl'
      },
      '/chooseTeam': {
        templateUrl: 'views/chooseTeam.html',
        controller: 'ChooseTeamCtl'
      },
      '/finance': {
        templateUrl: 'views/finance.html',
        controller: 'FinanceCtl'
      },
      '/formation': {
        templateUrl: 'views/formation.html',
        controller: 'FormationCtl'
      },
      '/league': {
        templateUrl: 'views/league.html',
        controller: 'LeagueCtl'
      },
      '/login': {
        templateUrl: 'views/login.html',
        controller: 'LoginCtl'
      },
      '/signUp':{
        templateUrl: 'views/signUp.html',
        controller: 'SignUpCtl'
      },
      '/match': {
        templateUrl: 'views/match.html',
        controller: 'MatchCtl'
      },
      '/matchEnd': {
        templateUrl: 'views/matchEnd.html',
        controller: 'MatchEndCtl'
      },
      '/transfer': {
        templateUrl: 'views/transfer.html',
        controller: 'TransferCtl'
      },
      '/404': {
        templateUrl: 'views/404.html',
        controller: 'IndexCtl'
      }
    }
  }
});
