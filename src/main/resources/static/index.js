angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    $scope.loadProducts = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title: null,
                minFilter: $scope.filter ? $scope.filter.minFilter : null,
                maxFilter: $scope.filter ? $scope.filter.maxFilter : null
            }
        }).then(function (response) {
            console.log(response.data)
            $scope.ProductsList = response.data.content;
        });
    };

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.changeQuantity = function (productId, delta) {
        $http({
            url: contextPath + '/products/change_quantity',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.addProduct = function () {
        $http.post(contextPath + '/products', $scope.product)
            .then(function (response) {
                $scope.product = null;
                $scope.loadProducts();
            });
    };

    $scope.showCart = function () {
        $http({
            url: contextPath + '/carts',
            method: 'GET',
        }).then(function (response) {
            console.log(response)
            $scope.CartList = response.data;
        });
    };

    $scope.addProductToCart = function (productId) {
        $http.get(contextPath + '/carts/' + productId)
            .then(function (response) {
                $scope.CartList = response.data;
            });
    };

    $scope.deleteProductFromCart = function (productId) {
        $http.delete(contextPath + '/carts/' + productId)
            .then(function (response) {
                $scope.CartList = response.data;
            });
    }

    $scope.loadProducts();
    $scope.showCart();
})
;