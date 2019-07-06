define(['footballManager'], function(footballManager) {

   footballManager.service('MatchService', function($http) {
       this.url = 'http://localhost:8080/webapp_war_exploded/matches/';

       this.getMatch = function () {
         return $http.get(this.url + 'current');
       };

       this.getUpcomingMatches = function () {
           return $http.get(this.url);
       };

       this.getUpcomingMatch = function () {
         return $http.get(this.url + 'next');
       };

       this.playMatch = function () {
         return $http.get(this.url + 'play');
       };

   })
});
