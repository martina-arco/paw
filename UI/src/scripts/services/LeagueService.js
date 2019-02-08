define("footballManager", function(footballManager) {

    footballManager.service('LeagueService', function($http) {
        this.url = "./api/league/";

        this.getUpcomingMatches = function () {
            return $http.get(this.url + 'upcomingMatches');
        };

        this.getLeagueInformation = function () {
            return $http.get(this.url + 'leagueInformation');
        };
    })
});
