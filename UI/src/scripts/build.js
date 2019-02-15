requirejs.config({
  baseUrl: 'scripts',
  // paths: maps ids with paths (no extension)
  paths: {
    angular: '../assets/js/angular.min',
    'angular-route': '../assets/js/angular-route.min',
    'angular-translate': 'angular-translate/angular-translate',
    jquery: '../assets/js/jquery',
    requirejs: 'require',
    popper: '../assets/js/popper.min',
    bootstrap: '../assets/js/bootstrap.bundle',
    collapse: '../assets/js/collapse',
    plugins: '../assets/js/plugins.js',
    main: '../assets/js/main.js',
    json3: '../assets/js/json3',
    'chart': '../assets/js/Chart.min',
    'angular-chart': '../assets/js/angular-chart',
    ngDialog: '../assets/js/ngDialog'
  },
  // shim: makes external libraries reachable
  shim: {
    angular: {
      exports: 'angular',
      deps: [
        'jquery'
      ]
    },
    'angular-chart': {
      deps: [
        'angular',
        'chart'
      ]
    },
    'angular-route': {
      deps: [
        'angular'
      ]
    },
    bootstrap: {
      deps: [
        'jquery'
      ]
    },
    ngDialog: {
      deps: [
        'angular'
      ]
    },
    collapse: {
      deps: [
        'angular',
        'jquery',
        'bootstrap'
      ]
    },
    'angular-translate': {
      deps: [
        'angular'
      ]
    }
  },

  packages: [  ]
});

require([
    'angular',
    'jquery',
    'bootstrap',
    'footballManager',
    'controllers/IndexCtl'
  ],
  function() {
    angular.bootstrap(document, ['footballManager']);
  }
);
