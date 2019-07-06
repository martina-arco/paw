define(['footballManager', 'services/UserService'], function (footballManager) {

  footballManager.controller("SignUpCtl", function ($scope, UserService) {
    $scope.user = {username:'', password:'', mail:''};

    $scope.teamSelected = null;

    $scope.createUser = function () {
        UserService.createUser($scope.user).then(function (response) {
            console.log(response.data);
        });
    }

  });

});
