define(['footballManager', 'services/UserService'], function (footballManager) {

  footballManager.controller("SignUpCtl", function ($scope, UserService) {
    $scope.user = {id:0, username:'', password:'', repeatPassword:'', mail:'', team:null, currentDate:null};

    $scope.teamSelected = null;

    $scope.createUser = function () {
        UserService.createUser($scope.user).then(function (response) {
            // $location.url("home");
        });
    }

  });

});
