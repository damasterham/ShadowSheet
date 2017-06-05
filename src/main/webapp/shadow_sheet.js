/**
 * Created by DaMasterHam on 26-05-2017.
 */

var root = 'http://localhost:8080'
var shadowsheet = angular.module('shadowsheet', [])

shadowsheet.controller('RunnerController', function RunnerController($scope, $http)
{
    $scope.endpoints[
        {
            name: 'Create Runner',
            url: '/api/runners',
            method: 'POST'
        }];

    $scope.createRunner(function ($http)
    {
        $http.post(root + "/api/runners", "{}")
            .then(function(response) {
                $scope.runnerendpoints = response.data;
            });
    });
});

shadowsheet.controller('RunnerController',  function($scope, $http) {
        $http.get(root).
        then(function(response) {
            $scope.greeting = response.data;
        });
    });

