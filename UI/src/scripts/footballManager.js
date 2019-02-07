define([
    'routes',
    'services/dependencyResolverFor',
    'angular',
    'angular-route'
    ],
  function(config, dependencyResolverFor) {
    var footballManager = angular.module('footballManager', ['ngRoute']);
    footballManager
      .config(
        ['$routeProvider',
          '$controllerProvider',
          '$compileProvider',
          '$filterProvider',
          '$provide',
          function($routeProvider, $controllerProvider, $compileProvider, $filterProvider, $provide) {

            footballManager.controller = $controllerProvider.register;
            footballManager.directive = $compileProvider.directive;
            footballManager.filter = $filterProvider.register;
            footballManager.factory = $provide.factory;
            footballManager.service = $provide.service;

            // if (config.routes !== undefined) {
              angular.forEach(config.routes, function(route, path) {
                $routeProvider.when(path, {
                  templateUrl: route.templateUrl,
                  resolve: dependencyResolverFor(['controllers/' + route.controller]),
                  controller: route.controller,
                  gaPageTitle: route.gaPageTitle
                });
              });
            // }
            // if (config.defaultRoutePath !== undefined) {
              $routeProvider.otherwise({redirectTo: config.defaultRoutePath});
            // }
          }]);
    return footballManager;
  }
);

