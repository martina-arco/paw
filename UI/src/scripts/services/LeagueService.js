define(['footballManager'], function(footballManager) {

    footballManager.service('LeagueService', function($http) {
        this.url = "./api/v1/league/";

        this.getLeagueInformation = function () {
            return $http.get(this.url + 'league');
        };
    })
});
