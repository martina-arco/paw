requirejs.config({
  baseUrl: 'scripts',
  // paths: maps ids with paths (no extension)
  paths: {
    angular: 'angular.min',
    'angular-route': 'angular-route.min',
    'angular-translate': 'angular-translate/angular-translate',
    jquery: 'jquery.min',
    requirejs: 'require',
    popper: '../assets/js/popper.min',
    plugins: '../assets/js/plugins.js',
    main: '../assets/js/main.js'
  },
  // shim: makes external libraries reachable
  shim: {
    angular: {
      exports: 'angular',
      deps: [
        'jquery'
      ]
    },
    'angular-route': {
      deps: [
        'angular'
      ]
    },
    'angular-translate': {
      deps: [
        'angular'
      ]
    },
  },

  packages: [  ]
});

require([
    'angular',
    'footballManager',
    'controllers/IndexCtl'
  ],
  function() {
    angular.bootstrap(document, ['footballManager']);
  }
);
