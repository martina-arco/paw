define("footballManager", function(footballManager) {

    footballManager.service('MatchService', function($http) {
        this.url = "./api/matchEnd/";

        this.getMatches = function () {
            return $http.get(this.url + 'matches');
        };

        this.getMatch = function () {
            return $http.get(this.url + 'match');
        };
    })
});