define(['footballManager'], function(footballManager) {

    footballManager.service('PlayerService', function($http) {
        this.url = "./api/players/";

        this.getPlayers = function () {
          return $http.get(this.url + 'players');
        };

        this.getPlayerInfo = function (playerId) {
          return $http.get(this.url + 'playerInfo?' + playerId);
        };
    })
});
