define(['./app'],function(app) {
  return app.config(['$routeProvider'], function config($routeProvider) {
    $routeProvider.when("/", {
        redirectTo: "index.html",
        controller: "IndexCtl"
      });
    $routeProvider.when("/home/:playerId", {
        templateUrl: "./views/home.html",
        controller: "HomeCtl"
      });
    $routeProvider.when("/chooseTeam", {
        templateUrl: "./views/chooseTeam.html",
        controller: "ChooseTeamCtl"
      });
    $routeProvider.when("/finance", {
        templateUrl: "./views/finance.html",
        controller: "FinanceCtl"
      });
    $routeProvider.when("/formation", {
        templateUrl: "./views/formation.html",
        controller: "FormationCtl"
      });
    $routeProvider.when("/league", {
        templateUrl: "./views/league.html",
        controller: "LeagueCtl"
      });
    $routeProvider.when("/login", {
        templateUrl: "./views/login.html",
        controller: "LoginCtl"
      });
    $routeProvider.when("/match", {
        templateUrl: "./views/match.html",
        controller: "MatchCtl"
      });
    $routeProvider.when("/matchEnd", {
        templateUrl: "./views/matchEnd.html",
        controller: "MatchEndCtl"
      });
    $routeProvider.when("/stadium", {
        templateUrl: "./views/stadium.html",
        controller: "StadiumCtl"
      });
    $routeProvider.when("/transfer", {
        templateUrl: "./views/transfer.html",
        controller: "TransferCtl"
      });
    $routeProvider.otherwise({redirectTo: './views/404.html'});
  });
});
