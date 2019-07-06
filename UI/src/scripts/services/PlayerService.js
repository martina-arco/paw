define(['footballManager'], function(footballManager) {

    footballManager.service('PlayerService', function($http) {
        this.url = "http://localhost:8080/webapp_war_exploded/";

        this.getPlayers = function () {
            return $http.get(this.url + 'players');
        };

        this.getCriteriaTypes = function () {
            return $http.get(this.url + 'players/criteriaTypes');
        };

        this.filterSearch = function (filters) {
            var body = JSON.stringify(filters);
            return $http.post(this.url + 'players/filters', body);
        };

        this.buyPlayer = function (playerId) {
            var body = JSON.stringify({playerId: playerId});
            return $http.post(this.url + 'player/buy', body);
        };

        this.sellPlayer = function (playerId) {
            var body = JSON.stringify({playerId: playerId});
            return $http.post(this.url + 'player/sell', body);
        }
    })
});
