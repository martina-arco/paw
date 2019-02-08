define(['footballManager'], function(footballManager) {

   footballManager.service('MatchService', function($http) {
       this.url = "./api/v1/matches/";

       this.getMatches = function () {
           return $http.get(this.url + 'matches');
       };

       this.getMatch = function () {
         return $http.get(this.url + 'match');
       };

       this.getUpcomingMatch = function () {
         return $http.get(this.url + 'upcomingMatch');
       };

     this.getUpcomingMatches = function () {
       return $http.get(this.url + 'upcomingMatches');
     };
   })
});
