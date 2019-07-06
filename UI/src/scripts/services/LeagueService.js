define(['footballManager'], function(footballManager) {

    footballManager.service('LeagueService', function($http) {
        this.url = "http://localhost:8080/webapp_war_exploded/league/";

        this.getLeagueInformation = function () {
            return $http.get(this.url);
        };
    })
});
