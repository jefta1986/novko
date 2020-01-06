(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"],{

/***/ "./src/$$_lazy_route_resource lazy recursive":
/*!**********************************************************!*\
  !*** ./src/$$_lazy_route_resource lazy namespace object ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncaught exception popping up in devtools
	return Promise.resolve().then(function() {
		var e = new Error("Cannot find module '" + req + "'");
		e.code = 'MODULE_NOT_FOUND';
		throw e;
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "./src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "./src/app/admin/admin.component.css":
/*!*******************************************!*\
  !*** ./src/app/admin/admin.component.css ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiIuLi9hZG1pbi9hZG1pbi5jb21wb25lbnQuY3NzIn0= */"

/***/ }),

/***/ "./src/app/admin/admin.component.html":
/*!********************************************!*\
  !*** ./src/app/admin/admin.component.html ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<app-navigation></app-navigation>"

/***/ }),

/***/ "./src/app/admin/admin.component.ts":
/*!******************************************!*\
  !*** ./src/app/admin/admin.component.ts ***!
  \******************************************/
/*! exports provided: AdminComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AdminComponent", function() { return AdminComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var AdminComponent = /** @class */ (function () {
    function AdminComponent() {
    }
    AdminComponent.prototype.ngOnInit = function () {
    };
    AdminComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-admin',
            template: __webpack_require__(/*! ./admin.component.html */ "./src/app/admin/admin.component.html"),
            styles: [__webpack_require__(/*! ./admin.component.css */ "./src/app/admin/admin.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
    ], AdminComponent);
    return AdminComponent;
}());



/***/ }),

/***/ "./src/app/app-constants.ts":
/*!**********************************!*\
  !*** ./src/app/app-constants.ts ***!
  \**********************************/
/*! exports provided: AppConstants */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppConstants", function() { return AppConstants; });
var AppConstants = /** @class */ (function () {
    function AppConstants() {
    }
    AppConstants.baseUrl = "http://localhost:8080/";
    AppConstants.isLoggedInUser = "isLoggedInUser";
    AppConstants.isLoggedInAdmin = "isLoggedInAdmin";
    AppConstants.role = "role";
    AppConstants.roleUser = "ROLE_USER";
    AppConstants.roleAdmin = "ROLE_ADMIN";
    AppConstants.trueString = "true";
    return AppConstants;
}());



/***/ }),

/***/ "./src/app/app-routing.module.ts":
/*!***************************************!*\
  !*** ./src/app/app-routing.module.ts ***!
  \***************************************/
/*! exports provided: AppRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppRoutingModule", function() { return AppRoutingModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _login_login_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./login/login.component */ "./src/app/login/login.component.ts");
/* harmony import */ var _home_home_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./home/home.component */ "./src/app/home/home.component.ts");
/* harmony import */ var _cart_cart_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./cart/cart.component */ "./src/app/cart/cart.component.ts");
/* harmony import */ var _admin_admin_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./admin/admin.component */ "./src/app/admin/admin.component.ts");
/* harmony import */ var _guards_login_guard__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./guards/login.guard */ "./src/app/guards/login.guard.ts");
/* harmony import */ var _guards_auth_guard_user_guard__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./guards/auth-guard-user.guard */ "./src/app/guards/auth-guard-user.guard.ts");
/* harmony import */ var _guards_auth_guard_admin_guard__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./guards/auth-guard-admin.guard */ "./src/app/guards/auth-guard-admin.guard.ts");
/* harmony import */ var _registration_registration_component__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ./registration/registration.component */ "./src/app/registration/registration.component.ts");
/* harmony import */ var _category_category_component__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! ./category/category.component */ "./src/app/category/category.component.ts");
/* harmony import */ var _subcategory_subcategory_component__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! ./subcategory/subcategory.component */ "./src/app/subcategory/subcategory.component.ts");













var routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: 'login', component: _login_login_component__WEBPACK_IMPORTED_MODULE_3__["LoginComponent"], canActivate: [_guards_login_guard__WEBPACK_IMPORTED_MODULE_7__["LoginGuard"]] },
    { path: 'home', component: _home_home_component__WEBPACK_IMPORTED_MODULE_4__["HomeComponent"], canActivate: [_guards_auth_guard_user_guard__WEBPACK_IMPORTED_MODULE_8__["AuthGuardUserGuard"]] },
    { path: 'cart', component: _cart_cart_component__WEBPACK_IMPORTED_MODULE_5__["CartComponent"], canActivate: [_guards_auth_guard_user_guard__WEBPACK_IMPORTED_MODULE_8__["AuthGuardUserGuard"]] },
    { path: 'admin', component: _admin_admin_component__WEBPACK_IMPORTED_MODULE_6__["AdminComponent"], canActivate: [_guards_auth_guard_admin_guard__WEBPACK_IMPORTED_MODULE_9__["AuthGuardAdminGuard"]] },
    { path: 'registration', component: _registration_registration_component__WEBPACK_IMPORTED_MODULE_10__["RegistrationComponent"], canActivate: [_guards_auth_guard_admin_guard__WEBPACK_IMPORTED_MODULE_9__["AuthGuardAdminGuard"]] },
    { path: 'category', component: _category_category_component__WEBPACK_IMPORTED_MODULE_11__["CategoryComponent"], canActivate: [_guards_auth_guard_admin_guard__WEBPACK_IMPORTED_MODULE_9__["AuthGuardAdminGuard"]] },
    { path: 'subcategory', component: _subcategory_subcategory_component__WEBPACK_IMPORTED_MODULE_12__["SubcategoryComponent"], canActivate: [_guards_auth_guard_admin_guard__WEBPACK_IMPORTED_MODULE_9__["AuthGuardAdminGuard"]] },
];
var AppRoutingModule = /** @class */ (function () {
    function AppRoutingModule() {
    }
    AppRoutingModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forRoot(routes, { useHash: true })],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]]
        })
    ], AppRoutingModule);
    return AppRoutingModule;
}());



/***/ }),

/***/ "./src/app/app.component.css":
/*!***********************************!*\
  !*** ./src/app/app.component.css ***!
  \***********************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiIuLi9hcHAuY29tcG9uZW50LmNzcyJ9 */"

/***/ }),

/***/ "./src/app/app.component.html":
/*!************************************!*\
  !*** ./src/app/app.component.html ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<router-outlet></router-outlet>\r\n"

/***/ }),

/***/ "./src/app/app.component.ts":
/*!**********************************!*\
  !*** ./src/app/app.component.ts ***!
  \**********************************/
/*! exports provided: AppComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppComponent", function() { return AppComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var AppComponent = /** @class */ (function () {
    function AppComponent() {
        this.title = 'shop';
    }
    AppComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-root',
            template: __webpack_require__(/*! ./app.component.html */ "./src/app/app.component.html"),
            styles: [__webpack_require__(/*! ./app.component.css */ "./src/app/app.component.css")]
        })
    ], AppComponent);
    return AppComponent;
}());



/***/ }),

/***/ "./src/app/app.module.ts":
/*!*******************************!*\
  !*** ./src/app/app.module.ts ***!
  \*******************************/
/*! exports provided: AppModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppModule", function() { return AppModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _app_routing_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./app-routing.module */ "./src/app/app-routing.module.ts");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./app.component */ "./src/app/app.component.ts");
/* harmony import */ var _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/platform-browser/animations */ "./node_modules/@angular/platform-browser/fesm5/animations.js");
/* harmony import */ var _material_material_module__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./material/material.module */ "./src/app/material/material.module.ts");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _login_login_component__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./login/login.component */ "./src/app/login/login.component.ts");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _navigation_navigation_component__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ./navigation/navigation.component */ "./src/app/navigation/navigation.component.ts");
/* harmony import */ var _home_home_component__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! ./home/home.component */ "./src/app/home/home.component.ts");
/* harmony import */ var _side_panel_side_panel_component__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! ./side-panel/side-panel.component */ "./src/app/side-panel/side-panel.component.ts");
/* harmony import */ var _cart_cart_component__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! ./cart/cart.component */ "./src/app/cart/cart.component.ts");
/* harmony import */ var _services_auth_service__WEBPACK_IMPORTED_MODULE_14__ = __webpack_require__(/*! ./services/auth.service */ "./src/app/services/auth.service.ts");
/* harmony import */ var angular2_cookie_services_cookies_service__WEBPACK_IMPORTED_MODULE_15__ = __webpack_require__(/*! angular2-cookie/services/cookies.service */ "./node_modules/angular2-cookie/services/cookies.service.js");
/* harmony import */ var angular2_cookie_services_cookies_service__WEBPACK_IMPORTED_MODULE_15___default = /*#__PURE__*/__webpack_require__.n(angular2_cookie_services_cookies_service__WEBPACK_IMPORTED_MODULE_15__);
/* harmony import */ var _auth_interceptor__WEBPACK_IMPORTED_MODULE_16__ = __webpack_require__(/*! ./auth-interceptor */ "./src/app/auth-interceptor.ts");
/* harmony import */ var _admin_admin_component__WEBPACK_IMPORTED_MODULE_17__ = __webpack_require__(/*! ./admin/admin.component */ "./src/app/admin/admin.component.ts");
/* harmony import */ var _registration_registration_component__WEBPACK_IMPORTED_MODULE_18__ = __webpack_require__(/*! ./registration/registration.component */ "./src/app/registration/registration.component.ts");
/* harmony import */ var _category_category_component__WEBPACK_IMPORTED_MODULE_19__ = __webpack_require__(/*! ./category/category.component */ "./src/app/category/category.component.ts");
/* harmony import */ var _subcategory_subcategory_component__WEBPACK_IMPORTED_MODULE_20__ = __webpack_require__(/*! ./subcategory/subcategory.component */ "./src/app/subcategory/subcategory.component.ts");
/* harmony import */ var _dialogs_addcategorydialog_addcategorydialog_component__WEBPACK_IMPORTED_MODULE_21__ = __webpack_require__(/*! ./dialogs/addcategorydialog/addcategorydialog.component */ "./src/app/dialogs/addcategorydialog/addcategorydialog.component.ts");
/* harmony import */ var _dialogs_add_subcategory_dialog_add_subcategory_dialog_component__WEBPACK_IMPORTED_MODULE_22__ = __webpack_require__(/*! ./dialogs/add-subcategory-dialog/add-subcategory-dialog.component */ "./src/app/dialogs/add-subcategory-dialog/add-subcategory-dialog.component.ts");























var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["NgModule"])({
            declarations: [
                _app_component__WEBPACK_IMPORTED_MODULE_4__["AppComponent"],
                _login_login_component__WEBPACK_IMPORTED_MODULE_8__["LoginComponent"],
                _navigation_navigation_component__WEBPACK_IMPORTED_MODULE_10__["NavigationComponent"],
                _home_home_component__WEBPACK_IMPORTED_MODULE_11__["HomeComponent"],
                _side_panel_side_panel_component__WEBPACK_IMPORTED_MODULE_12__["SidePanelComponent"],
                _cart_cart_component__WEBPACK_IMPORTED_MODULE_13__["CartComponent"],
                _admin_admin_component__WEBPACK_IMPORTED_MODULE_17__["AdminComponent"],
                _registration_registration_component__WEBPACK_IMPORTED_MODULE_18__["RegistrationComponent"],
                _category_category_component__WEBPACK_IMPORTED_MODULE_19__["CategoryComponent"],
                _subcategory_subcategory_component__WEBPACK_IMPORTED_MODULE_20__["SubcategoryComponent"],
                _dialogs_addcategorydialog_addcategorydialog_component__WEBPACK_IMPORTED_MODULE_21__["AddcategorydialogComponent"],
                _dialogs_add_subcategory_dialog_add_subcategory_dialog_component__WEBPACK_IMPORTED_MODULE_22__["AddSubcategoryDialogComponent"]
            ],
            imports: [
                _angular_platform_browser__WEBPACK_IMPORTED_MODULE_1__["BrowserModule"],
                _app_routing_module__WEBPACK_IMPORTED_MODULE_3__["AppRoutingModule"],
                _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_5__["BrowserAnimationsModule"],
                _material_material_module__WEBPACK_IMPORTED_MODULE_6__["MaterialModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_9__["FormsModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_9__["ReactiveFormsModule"],
                _angular_common_http__WEBPACK_IMPORTED_MODULE_7__["HttpClientModule"]
            ],
            providers: [
                _services_auth_service__WEBPACK_IMPORTED_MODULE_14__["AuthService"],
                {
                    provide: _angular_common_http__WEBPACK_IMPORTED_MODULE_7__["HTTP_INTERCEPTORS"],
                    useClass: _auth_interceptor__WEBPACK_IMPORTED_MODULE_16__["AuthInterceptor"],
                    multi: true
                },
                angular2_cookie_services_cookies_service__WEBPACK_IMPORTED_MODULE_15__["CookieService"]
            ],
            entryComponents: [_dialogs_addcategorydialog_addcategorydialog_component__WEBPACK_IMPORTED_MODULE_21__["AddcategorydialogComponent"], _dialogs_add_subcategory_dialog_add_subcategory_dialog_component__WEBPACK_IMPORTED_MODULE_22__["AddSubcategoryDialogComponent"]],
            bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_4__["AppComponent"]]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "./src/app/auth-interceptor.ts":
/*!*************************************!*\
  !*** ./src/app/auth-interceptor.ts ***!
  \*************************************/
/*! exports provided: AuthInterceptor */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AuthInterceptor", function() { return AuthInterceptor; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs/operators */ "./node_modules/rxjs/_esm5/operators/index.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");





var AuthInterceptor = /** @class */ (function () {
    function AuthInterceptor(_router) {
        this._router = _router;
    }
    AuthInterceptor.prototype.intercept = function (request, next) {
        request = request.clone({
            withCredentials: true
        });
        return next.handle(request).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["tap"])(function () { }, function (err) {
            if (err instanceof _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpErrorResponse"]) {
                if (err.status == 401 && (request.url.includes('login') || request.url.includes('logout'))) {
                    return;
                }
                // this._router.navigate(['login']);
                // AuthService.emptyLocalStorage();
            }
        }, function () { }));
    };
    AuthInterceptor = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])(),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_4__["Router"]])
    ], AuthInterceptor);
    return AuthInterceptor;
}());



/***/ }),

/***/ "./src/app/cart/cart.component.css":
/*!*****************************************!*\
  !*** ./src/app/cart/cart.component.css ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiIuLi9jYXJ0L2NhcnQuY29tcG9uZW50LmNzcyJ9 */"

/***/ }),

/***/ "./src/app/cart/cart.component.html":
/*!******************************************!*\
  !*** ./src/app/cart/cart.component.html ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<app-navigation></app-navigation>\r\n<div class=\"container body-container\">\r\n  <div class=\"row\">\r\n    <div class=\"col-lg-3\">\r\n      <app-side-panel></app-side-panel>\r\n    </div>\r\n    <div class=\"col-lg-9\">\r\n      <div id=\"carouselExampleIndicators\" class=\"carousel slide my-4\" data-ride=\"carousel\">\r\n        <ol class=\"carousel-indicators\">\r\n          <li data-target=\"#carouselExampleIndicators\" data-slide-to=\"0\" class=\"\"></li>\r\n          <li data-target=\"#carouselExampleIndicators\" data-slide-to=\"1\" class=\"\"></li>\r\n          <li data-target=\"#carouselExampleIndicators\" data-slide-to=\"2\" class=\"active\"></li>\r\n        </ol>\r\n        <div class=\"carousel-inner\" role=\"listbox\">\r\n          <div class=\"carousel-item\">\r\n            <img class=\"d-block img-fluid\" src=\"http://placehold.it/900x350\" alt=\"First slide\">\r\n          </div>\r\n          <div class=\"carousel-item\">\r\n            <img class=\"d-block img-fluid\" src=\"http://placehold.it/900x350\" alt=\"Second slide\">\r\n          </div>\r\n          <div class=\"carousel-item active\">\r\n            <img class=\"d-block img-fluid\" src=\"http://placehold.it/900x350\" alt=\"Third slide\">\r\n          </div>\r\n        </div>\r\n        <a class=\"carousel-control-prev\" href=\"#carouselExampleIndicators\" role=\"button\" data-slide=\"prev\">\r\n          <span class=\"carousel-control-prev-icon\" aria-hidden=\"true\"></span>\r\n          <span class=\"sr-only\">Previous</span>\r\n        </a>\r\n        <a class=\"carousel-control-next\" href=\"#carouselExampleIndicators\" role=\"button\" data-slide=\"next\">\r\n          <span class=\"carousel-control-next-icon\" aria-hidden=\"true\"></span>\r\n          <span class=\"sr-only\">Next</span>\r\n        </a>\r\n      </div>\r\n    </div>\r\n  </div>"

/***/ }),

/***/ "./src/app/cart/cart.component.ts":
/*!****************************************!*\
  !*** ./src/app/cart/cart.component.ts ***!
  \****************************************/
/*! exports provided: CartComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CartComponent", function() { return CartComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var CartComponent = /** @class */ (function () {
    function CartComponent() {
    }
    CartComponent.prototype.ngOnInit = function () {
    };
    CartComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-cart',
            template: __webpack_require__(/*! ./cart.component.html */ "./src/app/cart/cart.component.html"),
            styles: [__webpack_require__(/*! ./cart.component.css */ "./src/app/cart/cart.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
    ], CartComponent);
    return CartComponent;
}());



/***/ }),

/***/ "./src/app/category/category.component.css":
/*!*************************************************!*\
  !*** ./src/app/category/category.component.css ***!
  \*************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiIuLi9jYXRlZ29yeS9jYXRlZ29yeS5jb21wb25lbnQuY3NzIn0= */"

/***/ }),

/***/ "./src/app/category/category.component.html":
/*!**************************************************!*\
  !*** ./src/app/category/category.component.html ***!
  \**************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<app-navigation></app-navigation>\r\n<button mat-mini-fab color=\"primary\" (click)=\"openDialog()\">+</button>\r\n<div class=\"table-responsive\">\r\n    <table class=\"table table-hover\">\r\n        <thead>\r\n          <tr>\r\n            <th scope=\"col\">ID</th>\r\n            <th scope=\"col\">Name</th>\r\n            <th scope=\"col\"></th>\r\n            <th scope=\"col\"></th>\r\n          </tr>\r\n        </thead>\r\n        <tbody>\r\n          <tr *ngFor=\"let category of allCategories\">\r\n            <th scope=\"row\">{{category.id}}</th>\r\n            <td>{{category.name}}</td>\r\n            <td>edit</td>\r\n            <td>remove</td>\r\n          </tr>\r\n        </tbody>\r\n      </table>\r\n  </div>"

/***/ }),

/***/ "./src/app/category/category.component.ts":
/*!************************************************!*\
  !*** ./src/app/category/category.component.ts ***!
  \************************************************/
/*! exports provided: CategoryComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CategoryComponent", function() { return CategoryComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _services_category_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../services/category.service */ "./src/app/services/category.service.ts");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _dialogs_addcategorydialog_addcategorydialog_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../dialogs/addcategorydialog/addcategorydialog.component */ "./src/app/dialogs/addcategorydialog/addcategorydialog.component.ts");





var CategoryComponent = /** @class */ (function () {
    function CategoryComponent(_categoryService, _dialog) {
        this._categoryService = _categoryService;
        this._dialog = _dialog;
        this.allCategories = [];
    }
    CategoryComponent.prototype.openDialog = function () {
        var dialogRef = this._dialog.open(_dialogs_addcategorydialog_addcategorydialog_component__WEBPACK_IMPORTED_MODULE_4__["AddcategorydialogComponent"], {
            width: '250px',
            data: {}
        });
        dialogRef.afterClosed().subscribe(function (result) {
            console.log('The dialog was closed');
        });
    };
    CategoryComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._categoryService.getAllCategories().subscribe(function (res) {
            _this.allCategories = res;
        });
    };
    CategoryComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-category',
            template: __webpack_require__(/*! ./category.component.html */ "./src/app/category/category.component.html"),
            styles: [__webpack_require__(/*! ./category.component.css */ "./src/app/category/category.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_services_category_service__WEBPACK_IMPORTED_MODULE_2__["CategoryService"], _angular_material__WEBPACK_IMPORTED_MODULE_3__["MatDialog"]])
    ], CategoryComponent);
    return CategoryComponent;
}());



/***/ }),

/***/ "./src/app/dialogs/add-subcategory-dialog/add-subcategory-dialog.component.css":
/*!*************************************************************************************!*\
  !*** ./src/app/dialogs/add-subcategory-dialog/add-subcategory-dialog.component.css ***!
  \*************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJhZGQtc3ViY2F0ZWdvcnktZGlhbG9nL2FkZC1zdWJjYXRlZ29yeS1kaWFsb2cuY29tcG9uZW50LmNzcyJ9 */"

/***/ }),

/***/ "./src/app/dialogs/add-subcategory-dialog/add-subcategory-dialog.component.html":
/*!**************************************************************************************!*\
  !*** ./src/app/dialogs/add-subcategory-dialog/add-subcategory-dialog.component.html ***!
  \**************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<p>\n  add-subcategory-dialog works!\n</p>\n"

/***/ }),

/***/ "./src/app/dialogs/add-subcategory-dialog/add-subcategory-dialog.component.ts":
/*!************************************************************************************!*\
  !*** ./src/app/dialogs/add-subcategory-dialog/add-subcategory-dialog.component.ts ***!
  \************************************************************************************/
/*! exports provided: AddSubcategoryDialogComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AddSubcategoryDialogComponent", function() { return AddSubcategoryDialogComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var src_app_models_subcategory__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/models/subcategory */ "./src/app/models/subcategory.ts");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var src_app_services_category_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! src/app/services/category.service */ "./src/app/services/category.service.ts");






var AddSubcategoryDialogComponent = /** @class */ (function () {
    function AddSubcategoryDialogComponent(_categoryService, _snackBar, _dialogRef) {
        this._categoryService = _categoryService;
        this._snackBar = _snackBar;
        this._dialogRef = _dialogRef;
        this.subcategory = new src_app_models_subcategory__WEBPACK_IMPORTED_MODULE_2__["Subcategory"]();
        this.subcategoryForm = new _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormGroup"]({
            name: new _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormControl"]('', [_angular_forms__WEBPACK_IMPORTED_MODULE_3__["Validators"].required]),
        });
    }
    AddSubcategoryDialogComponent.prototype.ngOnInit = function () {
    };
    AddSubcategoryDialogComponent.prototype.addCategory = function (categoryForm) {
        // this.subcategory.setName = this.subcategoryForm.get('name').value;
        // this._categoryService.addCategory(this.subcategory).subscribe(res=>{},err=>{
        //   this._snackBar.open("Something went wrong,try again!", 'Error', {
        //     duration: 4000,
        //     panelClass: ['my-snack-bar-error']
        //   });
        // },()=>{
        //   this._snackBar.open("Category added!", 'Success', {
        //     duration: 4000,
        //     panelClass: ['my-snack-bar']
        //   });
        //   this._dialogRef.close();
        // });
    };
    AddSubcategoryDialogComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-add-subcategory-dialog',
            template: __webpack_require__(/*! ./add-subcategory-dialog.component.html */ "./src/app/dialogs/add-subcategory-dialog/add-subcategory-dialog.component.html"),
            styles: [__webpack_require__(/*! ./add-subcategory-dialog.component.css */ "./src/app/dialogs/add-subcategory-dialog/add-subcategory-dialog.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [src_app_services_category_service__WEBPACK_IMPORTED_MODULE_5__["CategoryService"], _angular_material__WEBPACK_IMPORTED_MODULE_4__["MatSnackBar"], _angular_material__WEBPACK_IMPORTED_MODULE_4__["MatDialogRef"]])
    ], AddSubcategoryDialogComponent);
    return AddSubcategoryDialogComponent;
}());



/***/ }),

/***/ "./src/app/dialogs/addcategorydialog/addcategorydialog.component.css":
/*!***************************************************************************!*\
  !*** ./src/app/dialogs/addcategorydialog/addcategorydialog.component.css ***!
  \***************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJhZGRjYXRlZ29yeWRpYWxvZy9hZGRjYXRlZ29yeWRpYWxvZy5jb21wb25lbnQuY3NzIn0= */"

/***/ }),

/***/ "./src/app/dialogs/addcategorydialog/addcategorydialog.component.html":
/*!****************************************************************************!*\
  !*** ./src/app/dialogs/addcategorydialog/addcategorydialog.component.html ***!
  \****************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<form class=\"container-fluid\" role=\"form\" [formGroup]=\"categoryForm\" (ngSubmit)=\"addCategory(categoryForm)\">\n  <h6>Add Category</h6>\n  <div class=\"form-group\">\n     <mat-form-field class=\"form-element\">\n        <input matInput placeholder=\"Name of the category\" formControlName=\"name\">\n        <mat-error *ngIf=\"categoryForm.get('name').touched && categoryForm.get('name').hasError('required')\">\n           This field is required!\n        </mat-error>\n     </mat-form-field>\n  </div>\n  <button mat-button color=\"primary\" type=\"submit\" class=\"form-control\" [disabled]=\"categoryForm.invalid\">Save</button>\n  <div class=\"form-group\">\n     <mat-error class=\"formError\" *ngIf=\"categoryForm.get('name').touched && categoryForm.invalid\">\n        Please enter valid credentials!\n     </mat-error>\n  </div>\n  </form>"

/***/ }),

/***/ "./src/app/dialogs/addcategorydialog/addcategorydialog.component.ts":
/*!**************************************************************************!*\
  !*** ./src/app/dialogs/addcategorydialog/addcategorydialog.component.ts ***!
  \**************************************************************************/
/*! exports provided: AddcategorydialogComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AddcategorydialogComponent", function() { return AddcategorydialogComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var src_app_models_category__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/app/models/category */ "./src/app/models/category.ts");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var src_app_services_category_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! src/app/services/category.service */ "./src/app/services/category.service.ts");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");






var AddcategorydialogComponent = /** @class */ (function () {
    function AddcategorydialogComponent(_categoryService, _snackBar, _dialogRef) {
        this._categoryService = _categoryService;
        this._snackBar = _snackBar;
        this._dialogRef = _dialogRef;
        this.category = new src_app_models_category__WEBPACK_IMPORTED_MODULE_2__["Category"]();
        this.categoryForm = new _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormGroup"]({
            name: new _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormControl"]('', [_angular_forms__WEBPACK_IMPORTED_MODULE_3__["Validators"].required]),
        });
    }
    AddcategorydialogComponent.prototype.ngOnInit = function () {
    };
    AddcategorydialogComponent.prototype.addCategory = function (categoryForm) {
        var _this = this;
        this.category.setName = this.categoryForm.get('name').value;
        this._categoryService.addCategory(this.category).subscribe(function (res) { }, function (err) {
            _this._snackBar.open("Something went wrong,try again!", 'Error', {
                duration: 4000,
                panelClass: ['my-snack-bar-error']
            });
        }, function () {
            _this._snackBar.open("Category added!", 'Success', {
                duration: 4000,
                panelClass: ['my-snack-bar']
            });
            _this._dialogRef.close();
        });
    };
    AddcategorydialogComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-addcategorydialog',
            template: __webpack_require__(/*! ./addcategorydialog.component.html */ "./src/app/dialogs/addcategorydialog/addcategorydialog.component.html"),
            styles: [__webpack_require__(/*! ./addcategorydialog.component.css */ "./src/app/dialogs/addcategorydialog/addcategorydialog.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [src_app_services_category_service__WEBPACK_IMPORTED_MODULE_4__["CategoryService"], _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatSnackBar"], _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatDialogRef"]])
    ], AddcategorydialogComponent);
    return AddcategorydialogComponent;
}());



/***/ }),

/***/ "./src/app/guards/auth-guard-admin.guard.ts":
/*!**************************************************!*\
  !*** ./src/app/guards/auth-guard-admin.guard.ts ***!
  \**************************************************/
/*! exports provided: AuthGuardAdminGuard */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AuthGuardAdminGuard", function() { return AuthGuardAdminGuard; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _services_auth_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../services/auth.service */ "./src/app/services/auth.service.ts");




var AuthGuardAdminGuard = /** @class */ (function () {
    function AuthGuardAdminGuard(_router) {
        this._router = _router;
    }
    AuthGuardAdminGuard.prototype.canActivate = function (next, state) {
        if (_services_auth_service__WEBPACK_IMPORTED_MODULE_3__["AuthService"].isAuthenticatedUser())
            this._router.navigate(['/home']);
        if (!_services_auth_service__WEBPACK_IMPORTED_MODULE_3__["AuthService"].isAuthenticatedAdmin()) {
            this._router.navigate(['/login']);
        }
        return true;
    };
    AuthGuardAdminGuard = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_2__["Router"]])
    ], AuthGuardAdminGuard);
    return AuthGuardAdminGuard;
}());



/***/ }),

/***/ "./src/app/guards/auth-guard-user.guard.ts":
/*!*************************************************!*\
  !*** ./src/app/guards/auth-guard-user.guard.ts ***!
  \*************************************************/
/*! exports provided: AuthGuardUserGuard */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AuthGuardUserGuard", function() { return AuthGuardUserGuard; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _services_auth_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../services/auth.service */ "./src/app/services/auth.service.ts");




var AuthGuardUserGuard = /** @class */ (function () {
    function AuthGuardUserGuard(_router) {
        this._router = _router;
    }
    AuthGuardUserGuard.prototype.canActivate = function (next, state) {
        if (_services_auth_service__WEBPACK_IMPORTED_MODULE_3__["AuthService"].isAuthenticatedAdmin())
            this._router.navigate(['/admin']);
        if (!_services_auth_service__WEBPACK_IMPORTED_MODULE_3__["AuthService"].isAuthenticatedUser()) {
            this._router.navigate(['/login']);
        }
        return true;
    };
    AuthGuardUserGuard = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_2__["Router"]])
    ], AuthGuardUserGuard);
    return AuthGuardUserGuard;
}());



/***/ }),

/***/ "./src/app/guards/login.guard.ts":
/*!***************************************!*\
  !*** ./src/app/guards/login.guard.ts ***!
  \***************************************/
/*! exports provided: LoginGuard */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoginGuard", function() { return LoginGuard; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _services_auth_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../services/auth.service */ "./src/app/services/auth.service.ts");




var LoginGuard = /** @class */ (function () {
    function LoginGuard(_router) {
        this._router = _router;
    }
    LoginGuard.prototype.canActivate = function (next, state) {
        if (_services_auth_service__WEBPACK_IMPORTED_MODULE_3__["AuthService"].isAuthenticatedAdmin()) {
            this._router.navigate(['/admin']);
        }
        else if (_services_auth_service__WEBPACK_IMPORTED_MODULE_3__["AuthService"].isAuthenticatedUser()) {
            this._router.navigate(['/home']);
        }
        return true;
    };
    LoginGuard = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_2__["Router"]])
    ], LoginGuard);
    return LoginGuard;
}());



/***/ }),

/***/ "./src/app/home/home.component.css":
/*!*****************************************!*\
  !*** ./src/app/home/home.component.css ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiIuLi9ob21lL2hvbWUuY29tcG9uZW50LmNzcyJ9 */"

/***/ }),

/***/ "./src/app/home/home.component.html":
/*!******************************************!*\
  !*** ./src/app/home/home.component.html ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<app-navigation></app-navigation>\r\n<div class=\"container body-container\">\r\n    <div class=\"row\">\r\n        <div class=\"col-lg-3\">\r\n            <app-side-panel></app-side-panel>\r\n        </div>\r\n        <div class=\"col-lg-9\">\r\n            <div id=\"carouselExampleIndicators\" class=\"carousel slide my-4\" data-ride=\"carousel\">\r\n                <ol class=\"carousel-indicators\">\r\n                    <li data-target=\"#carouselExampleIndicators\" data-slide-to=\"0\" class=\"\"></li>\r\n                    <li data-target=\"#carouselExampleIndicators\" data-slide-to=\"1\" class=\"\"></li>\r\n                    <li data-target=\"#carouselExampleIndicators\" data-slide-to=\"2\" class=\"active\"></li>\r\n                </ol>\r\n                <div class=\"carousel-inner\" role=\"listbox\">\r\n                    <div class=\"carousel-item\">\r\n                        <img class=\"d-block img-fluid\" src=\"http://placehold.it/900x350\" alt=\"First slide\">\r\n                    </div>\r\n                    <div class=\"carousel-item\">\r\n                        <img class=\"d-block img-fluid\" src=\"http://placehold.it/900x350\" alt=\"Second slide\">\r\n                    </div>\r\n                    <div class=\"carousel-item active\">\r\n                        <img class=\"d-block img-fluid\" src=\"http://placehold.it/900x350\" alt=\"Third slide\">\r\n                    </div>\r\n                </div>\r\n                <a class=\"carousel-control-prev\" href=\"#carouselExampleIndicators\" role=\"button\" data-slide=\"prev\">\r\n                    <span class=\"carousel-control-prev-icon\" aria-hidden=\"true\"></span>\r\n                    <span class=\"sr-only\">Previous</span>\r\n                </a>\r\n                <a class=\"carousel-control-next\" href=\"#carouselExampleIndicators\" role=\"button\" data-slide=\"next\">\r\n                    <span class=\"carousel-control-next-icon\" aria-hidden=\"true\"></span>\r\n                    <span class=\"sr-only\">Next</span>\r\n                </a>\r\n            </div>\r\n            <div class=\"row\">\r\n\r\n                    <div class=\"col-lg-4 col-md-6 mb-4\">\r\n                        <div class=\"card h-100\">\r\n                            <a href=\"#\"><img class=\"card-img-top\" src=\"http://placehold.it/700x400\" alt=\"\"></a>\r\n                            <div class=\"card-body\">\r\n                                <h4 class=\"card-title\">\r\n\t\t\t\t\t\t\t\t  <a href=\"#\">Item One</a>\r\n\t\t\t\t\t\t\t\t</h4>\r\n                                <h5>$24.99</h5>\r\n                                <p class=\"card-text\">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>\r\n                            </div>\r\n                            <div class=\"card-footer\">\r\n                                <small class=\"text-muted\">★ ★ ★ ★ ☆</small>\r\n                            </div>\r\n                        </div>\r\n                    </div>\r\n                    <div class=\"col-lg-4 col-md-6 mb-4\">\r\n                        <div class=\"card h-100\">\r\n                            <a href=\"#\"><img class=\"card-img-top\" src=\"http://placehold.it/700x400\" alt=\"\"></a>\r\n                            <div class=\"card-body\">\r\n                                <h4 class=\"card-title\">\r\n\t\t\t\t\t\t\t\t\t<a href=\"#\">Item Two</a>\r\n\t\t\t\t\t\t\t\t</h4>\r\n                                <h5>$24.99</h5>\r\n                                <p class=\"card-text\">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor sit amet.</p>\r\n                            </div>\r\n                            <div class=\"card-footer\">\r\n                                <small class=\"text-muted\">★ ★ ★ ★ ☆</small>\r\n                            </div>\r\n                        </div>\r\n                    </div>\r\n                    <div class=\"col-lg-4 col-md-6 mb-4\">\r\n                        <div class=\"card h-100\">\r\n                            <a href=\"#\"><img class=\"card-img-top\" src=\"http://placehold.it/700x400\" alt=\"\"></a>\r\n                            <div class=\"card-body\">\r\n                                <h4 class=\"card-title\">\r\n\t\t\t\t\t\t\t\t  <a href=\"#\">Item Three</a>\r\n\t\t\t\t\t\t\t\t</h4>\r\n                                <h5>$24.99</h5>\r\n                                <p class=\"card-text\">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>\r\n                            </div>\r\n                            <div class=\"card-footer\">\r\n                                <small class=\"text-muted\">★ ★ ★ ★ ☆</small>\r\n                            </div>\r\n                        </div>\r\n                    </div>\r\n                    <div class=\"col-lg-4 col-md-6 mb-4\">\r\n                        <div class=\"card h-100\">\r\n                            <a href=\"#\"><img class=\"card-img-top\" src=\"http://placehold.it/700x400\" alt=\"\"></a>\r\n                            <div class=\"card-body\">\r\n                                <h4 class=\"card-title\">\r\n\t\t\t\t\t\t\t\t  <a href=\"#\">Item Four</a>\r\n\t\t\t\t\t\t\t\t</h4>\r\n                                <h5>$24.99</h5>\r\n                                <p class=\"card-text\">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>\r\n                            </div>\r\n                            <div class=\"card-footer\">\r\n                                <small class=\"text-muted\">★ ★ ★ ★ ☆</small>\r\n                            </div>\r\n                        </div>\r\n                    </div>\r\n                    <div class=\"col-lg-4 col-md-6 mb-4\">\r\n                        <div class=\"card h-100\">\r\n                            <a href=\"#\"><img class=\"card-img-top\" src=\"http://placehold.it/700x400\" alt=\"\"></a>\r\n                            <div class=\"card-body\">\r\n                                <h4 class=\"card-title\">\r\n\t\t\t\t\t\t\t\t  <a href=\"#\">Item Five</a>\r\n\t\t\t\t\t\t\t\t</h4>\r\n                                <h5>$24.99</h5>\r\n                                <p class=\"card-text\">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor sit amet.</p>\r\n                            </div>\r\n                            <div class=\"card-footer\">\r\n                                <small class=\"text-muted\">★ ★ ★ ★ ☆</small>\r\n                            </div>\r\n                        </div>\r\n                    </div>\r\n                    <div class=\"col-lg-4 col-md-6 mb-4\">\r\n                        <div class=\"card h-100\">\r\n                            <a href=\"#\"><img class=\"card-img-top\" src=\"http://placehold.it/700x400\" alt=\"\"></a>\r\n                            <div class=\"card-body\">\r\n                                <h4 class=\"card-title\">\r\n\t\t\t\t\t\t\t\t  <a href=\"#\">Item Six</a>\r\n\t\t\t\t\t\t\t\t</h4>\r\n                                <h5>$24.99</h5>\r\n                                <p class=\"card-text\">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>\r\n                            </div>\r\n                            <div class=\"card-footer\">\r\n                                <small class=\"text-muted\">★ ★ ★ ★ ☆</small>\r\n                            </div>\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>"

/***/ }),

/***/ "./src/app/home/home.component.ts":
/*!****************************************!*\
  !*** ./src/app/home/home.component.ts ***!
  \****************************************/
/*! exports provided: HomeComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "HomeComponent", function() { return HomeComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var HomeComponent = /** @class */ (function () {
    function HomeComponent() {
    }
    HomeComponent.prototype.ngOnInit = function () {
    };
    HomeComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-home',
            template: __webpack_require__(/*! ./home.component.html */ "./src/app/home/home.component.html"),
            styles: [__webpack_require__(/*! ./home.component.css */ "./src/app/home/home.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
    ], HomeComponent);
    return HomeComponent;
}());



/***/ }),

/***/ "./src/app/login/login.component.css":
/*!*******************************************!*\
  !*** ./src/app/login/login.component.css ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "/* \r\n  ##Device = Desktops\r\n  ##Screen = 1281px to higher resolution desktops\r\n*/\r\n\r\nmat-form-field{\r\n  width: 100%;\r\n}\r\n\r\nh4,h6{\r\n  text-align: center;\r\n}\r\n\r\n.formError{\r\n  text-align: center;\r\n}\r\n\r\n@media (min-width: 1281px) {\r\n  .container-fluid{\r\n    width: 40%;\r\n    padding-top: 15%;\r\n\r\n  }\r\n\r\n  }\r\n\r\n/* \r\n    ##Device = Laptops, Desktops\r\n    ##Screen = B/w 1025px to 1280px\r\n  */\r\n\r\n@media (min-width: 1025px) and (max-width: 1280px) {\r\n    .container-fluid{\r\n        width: 40%;\r\n        padding-top: 15%;\r\n\r\n      }\r\n    \r\n  }\r\n\r\n/* \r\n    ##Device = Tablets, Ipads (portrait)\r\n    ##Screen = B/w 768px to 1024px\r\n  */\r\n\r\n@media (min-width: 768px) and (max-width: 1024px) {\r\n    \r\n    .container-fluid{\r\n        width: 50%;\r\n        padding-top: 25%;\r\n\r\n      }\r\n    \r\n  }\r\n\r\n/* \r\n    ##Device = Tablets, Ipads (landscape)\r\n    ##Screen = B/w 768px to 1024px\r\n  */\r\n\r\n@media (min-width: 768px) and (max-width: 1024px) and (orientation: landscape) {\r\n    .container-fluid{\r\n        width: 50%;\r\n        padding-top: 25%;\r\n      }\r\n  }\r\n\r\n/* \r\n    ##Device = Low Resolution Tablets, Mobiles (Landscape)\r\n    ##Screen = B/w 481px to 767px\r\n  */\r\n\r\n@media (min-width: 481px) and (max-width: 767px) {\r\n    \r\n    .container-fluid{\r\n        width: 80%;\r\n        padding-top: 30%;\r\n      }  \r\n  }\r\n\r\n/* \r\n    ##Device = Most of the Smartphones Mobiles (Portrait)\r\n    ##Screen = B/w 320px to 479px\r\n  */\r\n\r\n@media (min-width: 320px) and (max-width: 480px) {\r\n    \r\n    .container-fluid{\r\n        width: 80%;\r\n        padding-top: 40%;\r\n      }\r\n    \r\n  }\r\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uL2xvZ2luL2xvZ2luLmNvbXBvbmVudC5jc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7OztFQUdFOztBQUVGO0VBQ0UsWUFBWTtDQUNiOztBQUVEO0VBQ0UsbUJBQW1CO0NBQ3BCOztBQUNEO0VBQ0UsbUJBQW1CO0NBQ3BCOztBQUVEO0VBQ0U7SUFDRSxXQUFXO0lBQ1gsaUJBQWlCOztHQUVsQjs7R0FFQTs7QUFFRDs7O0lBR0U7O0FBRUY7SUFDRTtRQUNJLFdBQVc7UUFDWCxpQkFBaUI7O09BRWxCOztHQUVKOztBQUVEOzs7SUFHRTs7QUFFRjs7SUFFRTtRQUNJLFdBQVc7UUFDWCxpQkFBaUI7O09BRWxCOztHQUVKOztBQUVEOzs7SUFHRTs7QUFFRjtJQUNFO1FBQ0ksV0FBVztRQUNYLGlCQUFpQjtPQUNsQjtHQUNKOztBQUVEOzs7SUFHRTs7QUFFRjs7SUFFRTtRQUNJLFdBQVc7UUFDWCxpQkFBaUI7T0FDbEI7R0FDSjs7QUFFRDs7O0lBR0U7O0FBRUY7O0lBRUU7UUFDSSxXQUFXO1FBQ1gsaUJBQWlCO09BQ2xCOztHQUVKIiwiZmlsZSI6Ii4uL2xvZ2luL2xvZ2luLmNvbXBvbmVudC5jc3MiLCJzb3VyY2VzQ29udGVudCI6WyIvKiBcclxuICAjI0RldmljZSA9IERlc2t0b3BzXHJcbiAgIyNTY3JlZW4gPSAxMjgxcHggdG8gaGlnaGVyIHJlc29sdXRpb24gZGVza3RvcHNcclxuKi9cclxuXHJcbm1hdC1mb3JtLWZpZWxke1xyXG4gIHdpZHRoOiAxMDAlO1xyXG59XHJcblxyXG5oNCxoNntcclxuICB0ZXh0LWFsaWduOiBjZW50ZXI7XHJcbn1cclxuLmZvcm1FcnJvcntcclxuICB0ZXh0LWFsaWduOiBjZW50ZXI7XHJcbn1cclxuXHJcbkBtZWRpYSAobWluLXdpZHRoOiAxMjgxcHgpIHtcclxuICAuY29udGFpbmVyLWZsdWlke1xyXG4gICAgd2lkdGg6IDQwJTtcclxuICAgIHBhZGRpbmctdG9wOiAxNSU7XHJcblxyXG4gIH1cclxuXHJcbiAgfVxyXG4gIFxyXG4gIC8qIFxyXG4gICAgIyNEZXZpY2UgPSBMYXB0b3BzLCBEZXNrdG9wc1xyXG4gICAgIyNTY3JlZW4gPSBCL3cgMTAyNXB4IHRvIDEyODBweFxyXG4gICovXHJcbiAgXHJcbiAgQG1lZGlhIChtaW4td2lkdGg6IDEwMjVweCkgYW5kIChtYXgtd2lkdGg6IDEyODBweCkge1xyXG4gICAgLmNvbnRhaW5lci1mbHVpZHtcclxuICAgICAgICB3aWR0aDogNDAlO1xyXG4gICAgICAgIHBhZGRpbmctdG9wOiAxNSU7XHJcblxyXG4gICAgICB9XHJcbiAgICBcclxuICB9XHJcbiAgXHJcbiAgLyogXHJcbiAgICAjI0RldmljZSA9IFRhYmxldHMsIElwYWRzIChwb3J0cmFpdClcclxuICAgICMjU2NyZWVuID0gQi93IDc2OHB4IHRvIDEwMjRweFxyXG4gICovXHJcbiAgXHJcbiAgQG1lZGlhIChtaW4td2lkdGg6IDc2OHB4KSBhbmQgKG1heC13aWR0aDogMTAyNHB4KSB7XHJcbiAgICBcclxuICAgIC5jb250YWluZXItZmx1aWR7XHJcbiAgICAgICAgd2lkdGg6IDUwJTtcclxuICAgICAgICBwYWRkaW5nLXRvcDogMjUlO1xyXG5cclxuICAgICAgfVxyXG4gICAgXHJcbiAgfVxyXG4gIFxyXG4gIC8qIFxyXG4gICAgIyNEZXZpY2UgPSBUYWJsZXRzLCBJcGFkcyAobGFuZHNjYXBlKVxyXG4gICAgIyNTY3JlZW4gPSBCL3cgNzY4cHggdG8gMTAyNHB4XHJcbiAgKi9cclxuICBcclxuICBAbWVkaWEgKG1pbi13aWR0aDogNzY4cHgpIGFuZCAobWF4LXdpZHRoOiAxMDI0cHgpIGFuZCAob3JpZW50YXRpb246IGxhbmRzY2FwZSkge1xyXG4gICAgLmNvbnRhaW5lci1mbHVpZHtcclxuICAgICAgICB3aWR0aDogNTAlO1xyXG4gICAgICAgIHBhZGRpbmctdG9wOiAyNSU7XHJcbiAgICAgIH1cclxuICB9XHJcbiAgXHJcbiAgLyogXHJcbiAgICAjI0RldmljZSA9IExvdyBSZXNvbHV0aW9uIFRhYmxldHMsIE1vYmlsZXMgKExhbmRzY2FwZSlcclxuICAgICMjU2NyZWVuID0gQi93IDQ4MXB4IHRvIDc2N3B4XHJcbiAgKi9cclxuICBcclxuICBAbWVkaWEgKG1pbi13aWR0aDogNDgxcHgpIGFuZCAobWF4LXdpZHRoOiA3NjdweCkge1xyXG4gICAgXHJcbiAgICAuY29udGFpbmVyLWZsdWlke1xyXG4gICAgICAgIHdpZHRoOiA4MCU7XHJcbiAgICAgICAgcGFkZGluZy10b3A6IDMwJTtcclxuICAgICAgfSAgXHJcbiAgfVxyXG4gIFxyXG4gIC8qIFxyXG4gICAgIyNEZXZpY2UgPSBNb3N0IG9mIHRoZSBTbWFydHBob25lcyBNb2JpbGVzIChQb3J0cmFpdClcclxuICAgICMjU2NyZWVuID0gQi93IDMyMHB4IHRvIDQ3OXB4XHJcbiAgKi9cclxuICBcclxuICBAbWVkaWEgKG1pbi13aWR0aDogMzIwcHgpIGFuZCAobWF4LXdpZHRoOiA0ODBweCkge1xyXG4gICAgXHJcbiAgICAuY29udGFpbmVyLWZsdWlke1xyXG4gICAgICAgIHdpZHRoOiA4MCU7XHJcbiAgICAgICAgcGFkZGluZy10b3A6IDQwJTtcclxuICAgICAgfVxyXG4gICAgXHJcbiAgfSJdfQ== */"

/***/ }),

/***/ "./src/app/login/login.component.html":
/*!********************************************!*\
  !*** ./src/app/login/login.component.html ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<form class=\"container-fluid\" role=\"form\" [formGroup]=\"loginForm\" (ngSubmit)=\"login(loginForm)\">\r\n  <h4>Welcome to *SiteName*</h4>\r\n  <h6>Please enter your credentials to sign in</h6>\r\n  <div class=\"form-group\">\r\n    <mat-form-field class=\"form-element\">\r\n      <input matInput placeholder=\"Username\" formControlName=\"username\">\r\n      <mat-error *ngIf=\"loginForm.get('username').touched && loginForm.get('username').hasError('required')\">\r\n        This field is required!\r\n      </mat-error>\r\n    </mat-form-field>\r\n  </div>\r\n  <div class=\"form-group\">\r\n    <mat-form-field class=\"form-element\">\r\n      <input type=\"password\" matInput placeholder=\"Password\" formControlName=\"password\">\r\n      <mat-error *ngIf=\"loginForm.get('password').touched && loginForm.get('password').hasError('required')\">\r\n        This field is required!\r\n      </mat-error>\r\n    </mat-form-field>\r\n  </div>\r\n  <button mat-button color=\"primary\" type=\"submit\" class=\"form-control\" [disabled]=\"loginForm.invalid\">Sign in</button>\r\n  <div class=\"form-group\">\r\n      <mat-error class=\"formError\" *ngIf=\"(loginForm.get('username').touched && loginForm.get('password').touched && loginForm.invalid) || staticBadLogin\">\r\n          Please enter valid credentials!\r\n      </mat-error>\r\n  </div>\r\n</form>"

/***/ }),

/***/ "./src/app/login/login.component.ts":
/*!******************************************!*\
  !*** ./src/app/login/login.component.ts ***!
  \******************************************/
/*! exports provided: LoginComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoginComponent", function() { return LoginComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _services_auth_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../services/auth.service */ "./src/app/services/auth.service.ts");
/* harmony import */ var _models_user__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../models/user */ "./src/app/models/user.ts");





var LoginComponent = /** @class */ (function () {
    function LoginComponent(formBuilder, _authService) {
        this.formBuilder = formBuilder;
        this._authService = _authService;
        this.user = new _models_user__WEBPACK_IMPORTED_MODULE_4__["User"]();
        this.loginForm = new _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormGroup"]({
            username: new _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormControl"]('', [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required]),
            password: new _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormControl"]('', [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required])
        });
        LoginComponent_1.badLogin = false;
    }
    LoginComponent_1 = LoginComponent;
    LoginComponent.prototype.ngOnInit = function () {
    };
    LoginComponent.prototype.login = function () {
        this.user.setPassword = this.loginForm.get('password').value;
        this.user.setUsername = this.loginForm.get('username').value;
        this._authService.authenticate(this.user);
    };
    Object.defineProperty(LoginComponent.prototype, "staticBadLogin", {
        get: function () {
            return LoginComponent_1.badLogin;
        },
        enumerable: true,
        configurable: true
    });
    var LoginComponent_1;
    LoginComponent = LoginComponent_1 = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-login',
            template: __webpack_require__(/*! ./login.component.html */ "./src/app/login/login.component.html"),
            styles: [__webpack_require__(/*! ./login.component.css */ "./src/app/login/login.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormBuilder"], _services_auth_service__WEBPACK_IMPORTED_MODULE_3__["AuthService"]])
    ], LoginComponent);
    return LoginComponent;
}());



/***/ }),

/***/ "./src/app/material/material.module.ts":
/*!*********************************************!*\
  !*** ./src/app/material/material.module.ts ***!
  \*********************************************/
/*! exports provided: MaterialModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MaterialModule", function() { return MaterialModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");



var MaterialModule = /** @class */ (function () {
    function MaterialModule() {
    }
    MaterialModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatAutocompleteModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatButtonModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatButtonToggleModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatCardModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatCheckboxModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatChipsModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatDatepickerModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatDialogModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatExpansionModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatGridListModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatIconModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatInputModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatListModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatMenuModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatNativeDateModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatPaginatorModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatProgressBarModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatProgressSpinnerModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatRadioModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatRippleModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSelectModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSidenavModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSliderModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSlideToggleModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSnackBarModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSortModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatTableModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatTabsModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatToolbarModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatTooltipModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatStepperModule"],
            ],
            exports: [
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatAutocompleteModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatButtonModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatButtonToggleModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatCardModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatCheckboxModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatChipsModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatDatepickerModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatDialogModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatExpansionModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatGridListModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatIconModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatInputModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatListModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatMenuModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatNativeDateModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatPaginatorModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatProgressBarModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatProgressSpinnerModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatRadioModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatRippleModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSelectModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSidenavModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSliderModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSlideToggleModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSnackBarModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSortModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatTableModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatTabsModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatToolbarModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatTooltipModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatStepperModule"],
            ]
        })
    ], MaterialModule);
    return MaterialModule;
}());



/***/ }),

/***/ "./src/app/models/category.ts":
/*!************************************!*\
  !*** ./src/app/models/category.ts ***!
  \************************************/
/*! exports provided: Category */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Category", function() { return Category; });
var Category = /** @class */ (function () {
    function Category() {
    }
    Object.defineProperty(Category.prototype, "getName", {
        get: function () {
            return this.name;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Category.prototype, "getID", {
        get: function () {
            return this.id;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Category.prototype, "getSubcategories", {
        get: function () {
            return this.subcategories;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Category.prototype, "setName", {
        set: function (name) {
            this.name = name;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Category.prototype, "setSubcategories", {
        set: function (subcategories) {
            this.subcategories = subcategories;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Category.prototype, "setID", {
        set: function (id) {
            this.id = id;
        },
        enumerable: true,
        configurable: true
    });
    return Category;
}());



/***/ }),

/***/ "./src/app/models/subcategory.ts":
/*!***************************************!*\
  !*** ./src/app/models/subcategory.ts ***!
  \***************************************/
/*! exports provided: Subcategory */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Subcategory", function() { return Subcategory; });
var Subcategory = /** @class */ (function () {
    function Subcategory() {
    }
    Object.defineProperty(Subcategory.prototype, "getName", {
        get: function () {
            return this.name;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Subcategory.prototype, "getID", {
        get: function () {
            return this.id;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Subcategory.prototype, "getProducts", {
        get: function () {
            return this.products;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Subcategory.prototype, "setName", {
        set: function (name) {
            this.name = name;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Subcategory.prototype, "setProducts", {
        set: function (products) {
            this.products = products;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Subcategory.prototype, "setID", {
        set: function (id) {
            this.id = id;
        },
        enumerable: true,
        configurable: true
    });
    return Subcategory;
}());



/***/ }),

/***/ "./src/app/models/user.ts":
/*!********************************!*\
  !*** ./src/app/models/user.ts ***!
  \********************************/
/*! exports provided: User */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "User", function() { return User; });
var User = /** @class */ (function () {
    function User() {
    }
    Object.defineProperty(User.prototype, "getUsername", {
        get: function () {
            return this.username;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(User.prototype, "getPassword", {
        get: function () {
            return this.password;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(User.prototype, "getEmail", {
        get: function () {
            return this.email;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(User.prototype, "setUsername", {
        set: function (username) {
            this.username = username;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(User.prototype, "setPassword", {
        set: function (password) {
            this.password = password;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(User.prototype, "setEmail", {
        set: function (email) {
            this.email = email;
        },
        enumerable: true,
        configurable: true
    });
    return User;
}());



/***/ }),

/***/ "./src/app/navigation/navigation.component.css":
/*!*****************************************************!*\
  !*** ./src/app/navigation/navigation.component.css ***!
  \*****************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".cart-number{\r\n  color: #6610f2;\r\n  position: absolute;\r\n  opacity: 1!important;\r\n}\r\n.cart{\r\n  margin-top:11px;\r\n  color: black;\r\n}\r\nnav{\r\n  background-color: white!important;\r\n}\r\na{\r\n  color:black!important;\r\n  opacity: 0.5;\r\n  font-weight: bold;\r\n}\r\na:hover{\r\n  opacity: 1;\r\n}\r\n.navbar-toggler-icon {\r\n  background-image: url(\"data:image/svg+xml;charset=utf8,%3Csvg viewBox='0 0 32 32' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath stroke='rgba(0,0,0, 0.5)' stroke-width='2' stroke-linecap='round' stroke-miterlimit='10' d='M4 8h24M4 16h24M4 24h24'/%3E%3C/svg%3E\");\r\n}\r\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uL25hdmlnYXRpb24vbmF2aWdhdGlvbi5jb21wb25lbnQuY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0VBQ0UsZUFBZTtFQUNmLG1CQUFtQjtFQUNuQixxQkFBcUI7Q0FDdEI7QUFDRDtFQUNFLGdCQUFnQjtFQUNoQixhQUFhO0NBQ2Q7QUFFRDtFQUNFLGtDQUFrQztDQUNuQztBQUVEO0VBQ0Usc0JBQXNCO0VBQ3RCLGFBQWE7RUFDYixrQkFBa0I7Q0FDbkI7QUFFRDtFQUNFLFdBQVc7Q0FDWjtBQUNEO0VBQ0Usb1FBQW9RO0NBQ3JRIiwiZmlsZSI6Ii4uL25hdmlnYXRpb24vbmF2aWdhdGlvbi5jb21wb25lbnQuY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLmNhcnQtbnVtYmVye1xyXG4gIGNvbG9yOiAjNjYxMGYyO1xyXG4gIHBvc2l0aW9uOiBhYnNvbHV0ZTtcclxuICBvcGFjaXR5OiAxIWltcG9ydGFudDtcclxufVxyXG4uY2FydHtcclxuICBtYXJnaW4tdG9wOjExcHg7XHJcbiAgY29sb3I6IGJsYWNrO1xyXG59XHJcblxyXG5uYXZ7XHJcbiAgYmFja2dyb3VuZC1jb2xvcjogd2hpdGUhaW1wb3J0YW50O1xyXG59XHJcblxyXG5he1xyXG4gIGNvbG9yOmJsYWNrIWltcG9ydGFudDtcclxuICBvcGFjaXR5OiAwLjU7XHJcbiAgZm9udC13ZWlnaHQ6IGJvbGQ7XHJcbn1cclxuXHJcbmE6aG92ZXJ7XHJcbiAgb3BhY2l0eTogMTtcclxufVxyXG4ubmF2YmFyLXRvZ2dsZXItaWNvbiB7XHJcbiAgYmFja2dyb3VuZC1pbWFnZTogdXJsKFwiZGF0YTppbWFnZS9zdmcreG1sO2NoYXJzZXQ9dXRmOCwlM0Nzdmcgdmlld0JveD0nMCAwIDMyIDMyJyB4bWxucz0naHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmcnJTNFJTNDcGF0aCBzdHJva2U9J3JnYmEoMCwwLDAsIDAuNSknIHN0cm9rZS13aWR0aD0nMicgc3Ryb2tlLWxpbmVjYXA9J3JvdW5kJyBzdHJva2UtbWl0ZXJsaW1pdD0nMTAnIGQ9J000IDhoMjRNNCAxNmgyNE00IDI0aDI0Jy8lM0UlM0Mvc3ZnJTNFXCIpO1xyXG59Il19 */"

/***/ }),

/***/ "./src/app/navigation/navigation.component.html":
/*!******************************************************!*\
  !*** ./src/app/navigation/navigation.component.html ***!
  \******************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<nav class=\"navbar navbar-expand-md bg-success justify-content-between\">\r\n  <div class=\"container-fluid\">\r\n    <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\".dual-nav\">\r\n      <span class=\"navbar-toggler-icon\"></span>\r\n    </button>\r\n    <div class=\"navbar-collapse collapse dual-nav w-50 order-1 order-md-0\">\r\n      <div *ngIf=\"adminLoggedIn\">\r\n        <ul class=\"navbar-nav\">\r\n          <li class=\"nav-item active\">\r\n            <a class=\"nav-link pl-0\" href=\"#\">Add product <span class=\"sr-only\">Add product</span></a>\r\n          </li>\r\n          <li class=\"nav-item\">\r\n            <a class=\"nav-link\" [routerLink]=\"['/registration']\">Add user</a>\r\n          </li>\r\n          <li class=\"nav-item\">\r\n            <a class=\"nav-link\" [routerLink]=\"['/category']\">Category</a>\r\n          </li>\r\n          <li class=\"nav-item\">\r\n            <a class=\"nav-link\" [routerLink]=\"['/subcategory']\">Subcategory</a>\r\n          </li>\r\n        </ul>\r\n      </div>\r\n    </div>\r\n    <a href=\"/\" class=\"navbar-brand mx-auto d-block text-center order-0 order-md-1 w-25\">*SITE NAME*</a>\r\n    <div class=\"navbar-collapse collapse dual-nav w-50 order-2\">\r\n      <ul class=\"nav navbar-nav ml-auto\">\r\n        <li *ngIf=\"userLoggedIn\" class=\"nav-item\">\r\n          <a [routerLink]=\"['/cart']\">\r\n            <i class=\"fa fa-shopping-cart cart\"></i>\r\n            <strong><span class=\"cart-number\">1</span></strong>\r\n          </a>\r\n        </li>\r\n        <li class=\"nav-item\">\r\n            <a class=\"nav-link\" (click)=\"logout()\">\r\n              Logout\r\n            </a>\r\n          </li>\r\n      </ul>\r\n    </div>\r\n  </div>\r\n</nav>"

/***/ }),

/***/ "./src/app/navigation/navigation.component.ts":
/*!****************************************************!*\
  !*** ./src/app/navigation/navigation.component.ts ***!
  \****************************************************/
/*! exports provided: NavigationComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "NavigationComponent", function() { return NavigationComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _services_auth_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../services/auth.service */ "./src/app/services/auth.service.ts");



var NavigationComponent = /** @class */ (function () {
    function NavigationComponent(_authService) {
        this._authService = _authService;
        this.adminLoggedIn = false;
        this.userLoggedIn = false;
    }
    NavigationComponent.prototype.ngOnInit = function () {
        this.adminLoggedIn = _services_auth_service__WEBPACK_IMPORTED_MODULE_2__["AuthService"].isAuthenticatedAdmin();
        this.userLoggedIn = _services_auth_service__WEBPACK_IMPORTED_MODULE_2__["AuthService"].isAuthenticatedUser();
    };
    NavigationComponent.prototype.logout = function () {
        this._authService.logout();
    };
    NavigationComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-navigation',
            template: __webpack_require__(/*! ./navigation.component.html */ "./src/app/navigation/navigation.component.html"),
            styles: [__webpack_require__(/*! ./navigation.component.css */ "./src/app/navigation/navigation.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_services_auth_service__WEBPACK_IMPORTED_MODULE_2__["AuthService"]])
    ], NavigationComponent);
    return NavigationComponent;
}());



/***/ }),

/***/ "./src/app/registration/registration.component.css":
/*!*********************************************************!*\
  !*** ./src/app/registration/registration.component.css ***!
  \*********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "/* \r\n  ##Device = Desktops\r\n  ##Screen = 1281px to higher resolution desktops\r\n*/\r\n\r\nmat-form-field{\r\n    width: 100%;\r\n  }\r\n\r\nh4,h6{\r\n    text-align: center;\r\n  }\r\n\r\n.formError{\r\n    text-align: center;\r\n  }\r\n\r\n@media (min-width: 1281px) {\r\n    .container-fluid{\r\n      width: 40%;\r\n      padding-top: 5%;\r\n  \r\n    }\r\n  \r\n    }\r\n\r\n/* \r\n      ##Device = Laptops, Desktops\r\n      ##Screen = B/w 1025px to 1280px\r\n    */\r\n\r\n@media (min-width: 1025px) and (max-width: 1280px) {\r\n      .container-fluid{\r\n          width: 40%;\r\n          padding-top: 5%;\r\n  \r\n        }\r\n      \r\n    }\r\n\r\n/* \r\n      ##Device = Tablets, Ipads (portrait)\r\n      ##Screen = B/w 768px to 1024px\r\n    */\r\n\r\n@media (min-width: 768px) and (max-width: 1024px) {\r\n      \r\n      .container-fluid{\r\n          width: 50%;\r\n          padding-top: 5%;\r\n  \r\n        }\r\n      \r\n    }\r\n\r\n/* \r\n      ##Device = Tablets, Ipads (landscape)\r\n      ##Screen = B/w 768px to 1024px\r\n    */\r\n\r\n@media (min-width: 768px) and (max-width: 1024px) and (orientation: landscape) {\r\n      .container-fluid{\r\n          width: 50%;\r\n          padding-top: 5%;\r\n        }\r\n    }\r\n\r\n/* \r\n      ##Device = Low Resolution Tablets, Mobiles (Landscape)\r\n      ##Screen = B/w 481px to 767px\r\n    */\r\n\r\n@media (min-width: 481px) and (max-width: 767px) {\r\n      \r\n      .container-fluid{\r\n          width: 80%;\r\n          padding-top: 10%;\r\n        }  \r\n    }\r\n\r\n/* \r\n      ##Device = Most of the Smartphones Mobiles (Portrait)\r\n      ##Screen = B/w 320px to 479px\r\n    */\r\n\r\n@media (min-width: 320px) and (max-width: 480px) {\r\n      \r\n      .container-fluid{\r\n          width: 80%;\r\n          padding-top: 15%;\r\n        }\r\n      \r\n    }\r\n\r\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uL3JlZ2lzdHJhdGlvbi9yZWdpc3RyYXRpb24uY29tcG9uZW50LmNzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTs7O0VBR0U7O0FBRUY7SUFDSSxZQUFZO0dBQ2I7O0FBRUQ7SUFDRSxtQkFBbUI7R0FDcEI7O0FBQ0Q7SUFDRSxtQkFBbUI7R0FDcEI7O0FBRUQ7SUFDRTtNQUNFLFdBQVc7TUFDWCxnQkFBZ0I7O0tBRWpCOztLQUVBOztBQUVEOzs7TUFHRTs7QUFFRjtNQUNFO1VBQ0ksV0FBVztVQUNYLGdCQUFnQjs7U0FFakI7O0tBRUo7O0FBRUQ7OztNQUdFOztBQUVGOztNQUVFO1VBQ0ksV0FBVztVQUNYLGdCQUFnQjs7U0FFakI7O0tBRUo7O0FBRUQ7OztNQUdFOztBQUVGO01BQ0U7VUFDSSxXQUFXO1VBQ1gsZ0JBQWdCO1NBQ2pCO0tBQ0o7O0FBRUQ7OztNQUdFOztBQUVGOztNQUVFO1VBQ0ksV0FBVztVQUNYLGlCQUFpQjtTQUNsQjtLQUNKOztBQUVEOzs7TUFHRTs7QUFFRjs7TUFFRTtVQUNJLFdBQVc7VUFDWCxpQkFBaUI7U0FDbEI7O0tBRUoiLCJmaWxlIjoiLi4vcmVnaXN0cmF0aW9uL3JlZ2lzdHJhdGlvbi5jb21wb25lbnQuY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLyogXHJcbiAgIyNEZXZpY2UgPSBEZXNrdG9wc1xyXG4gICMjU2NyZWVuID0gMTI4MXB4IHRvIGhpZ2hlciByZXNvbHV0aW9uIGRlc2t0b3BzXHJcbiovXHJcblxyXG5tYXQtZm9ybS1maWVsZHtcclxuICAgIHdpZHRoOiAxMDAlO1xyXG4gIH1cclxuICBcclxuICBoNCxoNntcclxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcclxuICB9XHJcbiAgLmZvcm1FcnJvcntcclxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcclxuICB9XHJcbiAgXHJcbiAgQG1lZGlhIChtaW4td2lkdGg6IDEyODFweCkge1xyXG4gICAgLmNvbnRhaW5lci1mbHVpZHtcclxuICAgICAgd2lkdGg6IDQwJTtcclxuICAgICAgcGFkZGluZy10b3A6IDUlO1xyXG4gIFxyXG4gICAgfVxyXG4gIFxyXG4gICAgfVxyXG4gICAgXHJcbiAgICAvKiBcclxuICAgICAgIyNEZXZpY2UgPSBMYXB0b3BzLCBEZXNrdG9wc1xyXG4gICAgICAjI1NjcmVlbiA9IEIvdyAxMDI1cHggdG8gMTI4MHB4XHJcbiAgICAqL1xyXG4gICAgXHJcbiAgICBAbWVkaWEgKG1pbi13aWR0aDogMTAyNXB4KSBhbmQgKG1heC13aWR0aDogMTI4MHB4KSB7XHJcbiAgICAgIC5jb250YWluZXItZmx1aWR7XHJcbiAgICAgICAgICB3aWR0aDogNDAlO1xyXG4gICAgICAgICAgcGFkZGluZy10b3A6IDUlO1xyXG4gIFxyXG4gICAgICAgIH1cclxuICAgICAgXHJcbiAgICB9XHJcbiAgICBcclxuICAgIC8qIFxyXG4gICAgICAjI0RldmljZSA9IFRhYmxldHMsIElwYWRzIChwb3J0cmFpdClcclxuICAgICAgIyNTY3JlZW4gPSBCL3cgNzY4cHggdG8gMTAyNHB4XHJcbiAgICAqL1xyXG4gICAgXHJcbiAgICBAbWVkaWEgKG1pbi13aWR0aDogNzY4cHgpIGFuZCAobWF4LXdpZHRoOiAxMDI0cHgpIHtcclxuICAgICAgXHJcbiAgICAgIC5jb250YWluZXItZmx1aWR7XHJcbiAgICAgICAgICB3aWR0aDogNTAlO1xyXG4gICAgICAgICAgcGFkZGluZy10b3A6IDUlO1xyXG4gIFxyXG4gICAgICAgIH1cclxuICAgICAgXHJcbiAgICB9XHJcbiAgICBcclxuICAgIC8qIFxyXG4gICAgICAjI0RldmljZSA9IFRhYmxldHMsIElwYWRzIChsYW5kc2NhcGUpXHJcbiAgICAgICMjU2NyZWVuID0gQi93IDc2OHB4IHRvIDEwMjRweFxyXG4gICAgKi9cclxuICAgIFxyXG4gICAgQG1lZGlhIChtaW4td2lkdGg6IDc2OHB4KSBhbmQgKG1heC13aWR0aDogMTAyNHB4KSBhbmQgKG9yaWVudGF0aW9uOiBsYW5kc2NhcGUpIHtcclxuICAgICAgLmNvbnRhaW5lci1mbHVpZHtcclxuICAgICAgICAgIHdpZHRoOiA1MCU7XHJcbiAgICAgICAgICBwYWRkaW5nLXRvcDogNSU7XHJcbiAgICAgICAgfVxyXG4gICAgfVxyXG4gICAgXHJcbiAgICAvKiBcclxuICAgICAgIyNEZXZpY2UgPSBMb3cgUmVzb2x1dGlvbiBUYWJsZXRzLCBNb2JpbGVzIChMYW5kc2NhcGUpXHJcbiAgICAgICMjU2NyZWVuID0gQi93IDQ4MXB4IHRvIDc2N3B4XHJcbiAgICAqL1xyXG4gICAgXHJcbiAgICBAbWVkaWEgKG1pbi13aWR0aDogNDgxcHgpIGFuZCAobWF4LXdpZHRoOiA3NjdweCkge1xyXG4gICAgICBcclxuICAgICAgLmNvbnRhaW5lci1mbHVpZHtcclxuICAgICAgICAgIHdpZHRoOiA4MCU7XHJcbiAgICAgICAgICBwYWRkaW5nLXRvcDogMTAlO1xyXG4gICAgICAgIH0gIFxyXG4gICAgfVxyXG4gICAgXHJcbiAgICAvKiBcclxuICAgICAgIyNEZXZpY2UgPSBNb3N0IG9mIHRoZSBTbWFydHBob25lcyBNb2JpbGVzIChQb3J0cmFpdClcclxuICAgICAgIyNTY3JlZW4gPSBCL3cgMzIwcHggdG8gNDc5cHhcclxuICAgICovXHJcbiAgICBcclxuICAgIEBtZWRpYSAobWluLXdpZHRoOiAzMjBweCkgYW5kIChtYXgtd2lkdGg6IDQ4MHB4KSB7XHJcbiAgICAgIFxyXG4gICAgICAuY29udGFpbmVyLWZsdWlke1xyXG4gICAgICAgICAgd2lkdGg6IDgwJTtcclxuICAgICAgICAgIHBhZGRpbmctdG9wOiAxNSU7XHJcbiAgICAgICAgfVxyXG4gICAgICBcclxuICAgIH1cclxuIl19 */"

/***/ }),

/***/ "./src/app/registration/registration.component.html":
/*!**********************************************************!*\
  !*** ./src/app/registration/registration.component.html ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<app-navigation></app-navigation>\n<form class=\"container-fluid\" role=\"form\" [formGroup]=\"registrationForm\" (ngSubmit)=\"register(registrationForm)\">\n<h4>Registration of the new user</h4>\n<h6>Please fill all fields with the information about the user you want to add</h6>\n<div class=\"form-group\">\n   <mat-form-field class=\"form-element\">\n      <input matInput placeholder=\"Username\" formControlName=\"username\">\n      <mat-error *ngIf=\"registrationForm.get('username').touched && registrationForm.get('username').hasError('required')\">\n         This field is required!\n      </mat-error>\n   </mat-form-field>\n</div>\n<div class=\"form-group\">\n   <mat-form-field class=\"form-element\">\n      <input type=\"password\" matInput placeholder=\"Password\" formControlName=\"password\">\n      <mat-error *ngIf=\"registrationForm.get('password').touched && registrationForm.get('password').hasError('required')\">\n         This field is required!\n      </mat-error>\n   </mat-form-field>\n</div>\n<div class=\"form-group\">\n   <mat-form-field class=\"form-element\">\n      <input type=\"password\" matInput placeholder=\"Enter password again\" formControlName=\"repassword\">\n      <mat-error *ngIf=\"registrationForm.get('repassword').touched && registrationForm.get('repassword').hasError('required')\">\n         This field is required!\n      </mat-error>\n      <mat-error *ngIf=\"registrationForm.get('repassword').touched && registrationForm.get('repassword').errors?.notEquivalent\">\n         Passwords do not match!\n      </mat-error>\n   </mat-form-field>\n</div>\n<div class=\"form-group\">\n   <mat-form-field class=\"form-element\">\n      <input matInput placeholder=\"Email\" formControlName=\"email\">\n      <mat-error *ngIf=\"registrationForm.get('email').touched && registrationForm.get('email').hasError('required')\">\n         This field is required!\n      </mat-error>\n      <mat-error *ngIf=\"registrationForm.get('email').touched && registrationForm.get('email').hasError('email')\">\n         Enter valid email!\n      </mat-error>\n   </mat-form-field>\n</div>\n<div class=\"form-group\">\n    <mat-form-field>\n        <mat-label>Role</mat-label>\n        <mat-select [(value)]=\"selected\">\n          <mat-option value=\"ROLE_ADMIN\">Admin</mat-option>\n          <mat-option value=\"ROLE_USER\">User</mat-option>\n        </mat-select>\n    </mat-form-field>\n</div>\n<button mat-button color=\"primary\" type=\"submit\" class=\"form-control\" [disabled]=\"registrationForm.invalid\">Register</button>\n<div class=\"form-group\">\n   <mat-error class=\"formError\" *ngIf=\"registrationForm.get('username').touched && registrationForm.get('password').touched && registrationForm.get('email').touched && registrationForm.get('repassword').touched && registrationForm.invalid\">\n      Please enter valid credentials!\n   </mat-error>\n</div>\n</form>"

/***/ }),

/***/ "./src/app/registration/registration.component.ts":
/*!********************************************************!*\
  !*** ./src/app/registration/registration.component.ts ***!
  \********************************************************/
/*! exports provided: RegistrationComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "RegistrationComponent", function() { return RegistrationComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _models_user__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../models/user */ "./src/app/models/user.ts");
/* harmony import */ var _services_auth_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../services/auth.service */ "./src/app/services/auth.service.ts");





var RegistrationComponent = /** @class */ (function () {
    function RegistrationComponent(formBuilder, _authService) {
        this.formBuilder = formBuilder;
        this._authService = _authService;
        this.user = new _models_user__WEBPACK_IMPORTED_MODULE_3__["User"]();
        this.selected = 'ROLE_USER';
        this.registrationForm = new _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormGroup"]({
            username: new _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormControl"]('', [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required]),
            password: new _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormControl"]('', [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required]),
            repassword: new _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormControl"]('', [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required]),
            email: new _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormControl"]('', [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required, _angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].email])
        });
        this.registrationForm.setValidators(this.passwordMatchValidator());
    }
    RegistrationComponent.prototype.ngOnInit = function () {
    };
    RegistrationComponent.prototype.register = function (registrationForm) {
        this.user.setPassword = this.registrationForm.get('password').value;
        this.user.setUsername = this.registrationForm.get('username').value;
        this.user.setEmail = this.registrationForm.get('email').value;
        this._authService.register(this.user, this.selected);
    };
    RegistrationComponent.prototype.passwordMatchValidator = function () {
        return function (group) {
            var password = group.controls['password'];
            var repassword = group.controls['repassword'];
            if (password.value !== repassword.value) {
                repassword.setErrors({ notEquivalent: true });
            }
            else {
                repassword.setErrors(null);
            }
            return;
        };
    };
    RegistrationComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-registration',
            template: __webpack_require__(/*! ./registration.component.html */ "./src/app/registration/registration.component.html"),
            styles: [__webpack_require__(/*! ./registration.component.css */ "./src/app/registration/registration.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormBuilder"], _services_auth_service__WEBPACK_IMPORTED_MODULE_4__["AuthService"]])
    ], RegistrationComponent);
    return RegistrationComponent;
}());



/***/ }),

/***/ "./src/app/services/auth.service.ts":
/*!******************************************!*\
  !*** ./src/app/services/auth.service.ts ***!
  \******************************************/
/*! exports provided: AuthService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AuthService", function() { return AuthService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _app_constants__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../app-constants */ "./src/app/app-constants.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _login_login_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../login/login.component */ "./src/app/login/login.component.ts");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");







var AuthService = /** @class */ (function () {
    function AuthService(_http, _router, _snackBar) {
        this._http = _http;
        this._router = _router;
        this._snackBar = _snackBar;
    }
    AuthService_1 = AuthService;
    AuthService.prototype.authenticate = function (user) {
        var _this = this;
        var responseRole = '';
        this._http.get(_app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].baseUrl + 'login?username=' + user.getUsername + '&password=' + user.getPassword, { responseType: 'text' })
            .subscribe(function (res) {
            responseRole = res;
        }, function (err) {
            _login_login_component__WEBPACK_IMPORTED_MODULE_5__["LoginComponent"].badLogin = true;
        }, function () {
            if (responseRole == _app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].roleAdmin) {
                AuthService_1.insertAdminInLocalStorage();
            }
            else {
                AuthService_1.insertUserInLocalStorage();
            }
            if (AuthService_1.isAuthenticatedAdmin()) {
                _this._router.navigate(['admin']);
            }
            else if (AuthService_1.isAuthenticatedUser()) {
                _this._router.navigate(['home']);
            }
        });
    };
    AuthService.prototype.register = function (user, role) {
        var _this = this;
        this._http.post(_app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].baseUrl + 'registration?role=' + role, user, { responseType: 'text' })
            .subscribe(function (res) { }, function (err) {
            _this._snackBar.open("Something went wrong,try again!", 'Error', {
                duration: 4000,
                panelClass: ['my-snack-bar-error']
            });
        }, function () {
            _this._snackBar.open("User registrated!", 'Success', {
                duration: 4000,
                panelClass: ['my-snack-bar']
            });
        });
    };
    AuthService.prototype.logout = function () {
        var _this = this;
        var response = null;
        this._http.get(_app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].baseUrl + 'logout').subscribe(function (res) {
            console.log(res);
        }, function (err) {
            console.log(err);
        }, function () {
            AuthService_1.emptyLocalStorage();
            _this._router.navigate(["/login"]);
        });
    };
    AuthService.isAuthenticatedUser = function () {
        var isLoggedInUser = localStorage.getItem(_app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].isLoggedInUser);
        var role = this.getRole();
        if (isLoggedInUser == _app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].trueString && role == _app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].roleUser) {
            return true;
        }
        return false;
    };
    AuthService.isAuthenticatedAdmin = function () {
        var isLoggedInAdmin = localStorage.getItem(_app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].isLoggedInAdmin);
        var role = this.getRole();
        if (isLoggedInAdmin == _app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].trueString && role == _app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].roleAdmin) {
            return true;
        }
        return false;
    };
    AuthService.getRole = function () {
        return localStorage.getItem(_app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].role);
    };
    AuthService.emptyLocalStorage = function () {
        localStorage.removeItem(_app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].isLoggedInAdmin);
        localStorage.removeItem(_app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].isLoggedInUser);
        localStorage.removeItem(_app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].role);
    };
    AuthService.insertUserInLocalStorage = function () {
        localStorage.setItem(_app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].isLoggedInUser, _app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].trueString);
        localStorage.setItem(_app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].role, _app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].roleUser);
    };
    AuthService.insertAdminInLocalStorage = function () {
        localStorage.setItem(_app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].isLoggedInAdmin, _app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].trueString);
        localStorage.setItem(_app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].role, _app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].roleAdmin);
    };
    var AuthService_1;
    AuthService = AuthService_1 = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"], _angular_router__WEBPACK_IMPORTED_MODULE_4__["Router"], _angular_material__WEBPACK_IMPORTED_MODULE_6__["MatSnackBar"]])
    ], AuthService);
    return AuthService;
}());



/***/ }),

/***/ "./src/app/services/category.service.ts":
/*!**********************************************!*\
  !*** ./src/app/services/category.service.ts ***!
  \**********************************************/
/*! exports provided: CategoryService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CategoryService", function() { return CategoryService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _app_constants__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../app-constants */ "./src/app/app-constants.ts");




var CategoryService = /** @class */ (function () {
    function CategoryService(_http) {
        this._http = _http;
    }
    CategoryService.prototype.getAllCategories = function () {
        return this._http.get(_app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].baseUrl + "rest/categories");
    };
    CategoryService.prototype.addCategory = function (category) {
        return this._http.post(_app_constants__WEBPACK_IMPORTED_MODULE_3__["AppConstants"].baseUrl + "rest/categories", category, { responseType: 'text' });
    };
    CategoryService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"]])
    ], CategoryService);
    return CategoryService;
}());



/***/ }),

/***/ "./src/app/side-panel/side-panel.component.css":
/*!*****************************************************!*\
  !*** ./src/app/side-panel/side-panel.component.css ***!
  \*****************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "a{\r\n    color:black!important;\r\n    opacity: 0.5!important;\r\n}\r\na:visited, a:link, a:active {\r\n    text-decoration: none!important;\r\n}\r\na:hover{\r\n    text-decoration: none!important;\r\n    opacity: 1!important;\r\n}\r\n.panel-group{\r\n    margin-top: 10%;\r\n}\r\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uL3NpZGUtcGFuZWwvc2lkZS1wYW5lbC5jb21wb25lbnQuY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0lBQ0ksc0JBQXNCO0lBQ3RCLHVCQUF1QjtDQUMxQjtBQUNEO0lBQ0ksZ0NBQWdDO0NBQ25DO0FBQ0Q7SUFDSSxnQ0FBZ0M7SUFDaEMscUJBQXFCO0NBQ3hCO0FBQ0Q7SUFDSSxnQkFBZ0I7Q0FDbkIiLCJmaWxlIjoiLi4vc2lkZS1wYW5lbC9zaWRlLXBhbmVsLmNvbXBvbmVudC5jc3MiLCJzb3VyY2VzQ29udGVudCI6WyJhe1xyXG4gICAgY29sb3I6YmxhY2shaW1wb3J0YW50O1xyXG4gICAgb3BhY2l0eTogMC41IWltcG9ydGFudDtcclxufVxyXG5hOnZpc2l0ZWQsIGE6bGluaywgYTphY3RpdmUge1xyXG4gICAgdGV4dC1kZWNvcmF0aW9uOiBub25lIWltcG9ydGFudDtcclxufVxyXG5hOmhvdmVye1xyXG4gICAgdGV4dC1kZWNvcmF0aW9uOiBub25lIWltcG9ydGFudDtcclxuICAgIG9wYWNpdHk6IDEhaW1wb3J0YW50O1xyXG59XHJcbi5wYW5lbC1ncm91cHtcclxuICAgIG1hcmdpbi10b3A6IDEwJTtcclxufSJdfQ== */"

/***/ }),

/***/ "./src/app/side-panel/side-panel.component.html":
/*!******************************************************!*\
  !*** ./src/app/side-panel/side-panel.component.html ***!
  \******************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"panel-group\" id=\"accordion\">\r\n  <div class=\"panel panel-default\">\r\n    <div class=\"panel-heading category\">\r\n      <h4 class=\"panel-title\">\r\n        <a data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapseOne\">Content</a>\r\n      </h4>\r\n    </div>\r\n    <div id=\"collapseOne\" class=\"panel-collapse collapse in\">\r\n      <div class=\"panel-body\">\r\n        <table class=\"table\">\r\n          <tr>\r\n            <td>\r\n              <a href=\"http://www.jquery2dotnet.com\">Articles</a>\r\n            </td>\r\n          </tr>\r\n          <tr>\r\n            <td>\r\n              <a href=\"http://www.jquery2dotnet.com\">News</a>\r\n            </td>\r\n          </tr>\r\n          <tr>\r\n            <td>\r\n              <a href=\"http://www.jquery2dotnet.com\">Newsletters</a>\r\n            </td>\r\n          </tr>\r\n          <tr>\r\n            <td>\r\n              <a href=\"http://www.jquery2dotnet.com\">Comments</a>\r\n            </td>\r\n          </tr>\r\n        </table>\r\n      </div>\r\n    </div>\r\n  </div>\r\n  <div class=\"panel panel-default\">\r\n    <div class=\"panel-heading\">\r\n      <h4 class=\"panel-title\">\r\n        <a data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapseTwo\">Modules</a>\r\n      </h4>\r\n    </div>\r\n    <div id=\"collapseTwo\" class=\"panel-collapse collapse\">\r\n      <div class=\"panel-body\">\r\n        <table class=\"table\">\r\n          <tr>\r\n            <td>\r\n              <a href=\"http://www.jquery2dotnet.com\">Orders</a>\r\n            </td>\r\n          </tr>\r\n          <tr>\r\n            <td>\r\n              <a href=\"http://www.jquery2dotnet.com\">Invoices</a>\r\n            </td>\r\n          </tr>\r\n          <tr>\r\n            <td>\r\n              <a href=\"http://www.jquery2dotnet.com\">Shipments</a>\r\n            </td>\r\n          </tr>\r\n          <tr>\r\n            <td>\r\n              <a href=\"http://www.jquery2dotnet.com\">Tex</a>\r\n            </td>\r\n          </tr>\r\n        </table>\r\n      </div>\r\n    </div>\r\n  </div>\r\n  <div class=\"panel panel-default\">\r\n    <div class=\"panel-heading\">\r\n      <h4 class=\"panel-title\">\r\n        <a data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapseThree\">Account</a>\r\n      </h4>\r\n    </div>\r\n    <div id=\"collapseThree\" class=\"panel-collapse collapse\">\r\n      <div class=\"panel-body\">\r\n        <table class=\"table\">\r\n          <tr>\r\n            <td>\r\n              <a href=\"http://www.jquery2dotnet.com\">Change Password</a>\r\n            </td>\r\n          </tr>\r\n          <tr>\r\n            <td>\r\n              <a href=\"http://www.jquery2dotnet.com\">Notifications</a>\r\n            </td>\r\n          </tr>\r\n          <tr>\r\n            <td>\r\n              <a href=\"http://www.jquery2dotnet.com\">Import/Export</a>\r\n            </td>\r\n          </tr>\r\n          <tr>\r\n            <td>\r\n              <a href=\"http://www.jquery2dotnet.com\" class=\"text-danger\">Delete Account</a>\r\n            </td>\r\n          </tr>\r\n        </table>\r\n      </div>\r\n    </div>\r\n  </div>\r\n  <div class=\"panel panel-default\">\r\n    <div class=\"panel-heading\">\r\n      <h4 class=\"panel-title\">\r\n        <a data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapseFour\">Reports</a>\r\n      </h4>\r\n    </div>\r\n    <div id=\"collapseFour\" class=\"panel-collapse collapse\">\r\n      <div class=\"panel-body\">\r\n        <table class=\"table\">\r\n          <tr>\r\n            <td>\r\n              <a href=\"http://www.jquery2dotnet.com\">Sales</a>\r\n            </td>\r\n          </tr>\r\n          <tr>\r\n            <td>\r\n              <a href=\"http://www.jquery2dotnet.com\">Customers</a>\r\n            </td>\r\n          </tr>\r\n          <tr>\r\n            <td>\r\n              <a href=\"http://www.jquery2dotnet.com\">Products</a>\r\n            </td>\r\n          </tr>\r\n          <tr>\r\n            <td>\r\n              <a href=\"http://www.jquery2dotnet.com\">Shopping Cart</a>\r\n            </td>\r\n          </tr>\r\n        </table>\r\n      </div>\r\n    </div>\r\n  </div>\r\n</div>"

/***/ }),

/***/ "./src/app/side-panel/side-panel.component.ts":
/*!****************************************************!*\
  !*** ./src/app/side-panel/side-panel.component.ts ***!
  \****************************************************/
/*! exports provided: SidePanelComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SidePanelComponent", function() { return SidePanelComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var SidePanelComponent = /** @class */ (function () {
    function SidePanelComponent() {
    }
    SidePanelComponent.prototype.ngOnInit = function () {
    };
    SidePanelComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-side-panel',
            template: __webpack_require__(/*! ./side-panel.component.html */ "./src/app/side-panel/side-panel.component.html"),
            styles: [__webpack_require__(/*! ./side-panel.component.css */ "./src/app/side-panel/side-panel.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
    ], SidePanelComponent);
    return SidePanelComponent;
}());



/***/ }),

/***/ "./src/app/subcategory/subcategory.component.css":
/*!*******************************************************!*\
  !*** ./src/app/subcategory/subcategory.component.css ***!
  \*******************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiIuLi9zdWJjYXRlZ29yeS9zdWJjYXRlZ29yeS5jb21wb25lbnQuY3NzIn0= */"

/***/ }),

/***/ "./src/app/subcategory/subcategory.component.html":
/*!********************************************************!*\
  !*** ./src/app/subcategory/subcategory.component.html ***!
  \********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<app-navigation></app-navigation>\n<button mat-mini-fab color=\"primary\" (click)=\"openDialog()\">+</button>\n<div class=\"table-responsive\">\n    <table class=\"table table-hover\">\n        <thead>\n          <tr>\n            <th scope=\"col\">ID</th>\n            <th scope=\"col\">Name</th>\n            <th scope=\"col\"></th>\n            <th scope=\"col\"></th>\n          </tr>\n        </thead>\n        <tbody>\n          <tr *ngFor=\"let subcategory of allSubcategories\">\n            <th scope=\"row\">{{subcategory.id}}</th>\n            <td>{{subcategory.name}}</td>\n            <td>edit</td>\n            <td>remove</td>\n          </tr>\n        </tbody>\n      </table>\n  </div>"

/***/ }),

/***/ "./src/app/subcategory/subcategory.component.ts":
/*!******************************************************!*\
  !*** ./src/app/subcategory/subcategory.component.ts ***!
  \******************************************************/
/*! exports provided: SubcategoryComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SubcategoryComponent", function() { return SubcategoryComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _services_category_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../services/category.service */ "./src/app/services/category.service.ts");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _dialogs_add_subcategory_dialog_add_subcategory_dialog_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../dialogs/add-subcategory-dialog/add-subcategory-dialog.component */ "./src/app/dialogs/add-subcategory-dialog/add-subcategory-dialog.component.ts");





var SubcategoryComponent = /** @class */ (function () {
    function SubcategoryComponent(_categoryService, _dialog) {
        this._categoryService = _categoryService;
        this._dialog = _dialog;
        this.allSubcategories = [];
    }
    SubcategoryComponent.prototype.openDialog = function () {
        var dialogRef = this._dialog.open(_dialogs_add_subcategory_dialog_add_subcategory_dialog_component__WEBPACK_IMPORTED_MODULE_4__["AddSubcategoryDialogComponent"], {
            width: '250px',
            data: {}
        });
        dialogRef.afterClosed().subscribe(function (result) {
            console.log('The dialog was closed');
        });
    };
    SubcategoryComponent.prototype.ngOnInit = function () {
        // this._categoryService.getAllCategories().subscribe(res=>{
        //     this.allSubcategories = res;
        // });
    };
    SubcategoryComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-subcategory',
            template: __webpack_require__(/*! ./subcategory.component.html */ "./src/app/subcategory/subcategory.component.html"),
            styles: [__webpack_require__(/*! ./subcategory.component.css */ "./src/app/subcategory/subcategory.component.css")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_services_category_service__WEBPACK_IMPORTED_MODULE_2__["CategoryService"], _angular_material__WEBPACK_IMPORTED_MODULE_3__["MatDialog"]])
    ], SubcategoryComponent);
    return SubcategoryComponent;
}());



/***/ }),

/***/ "./src/environments/environment.ts":
/*!*****************************************!*\
  !*** ./src/environments/environment.ts ***!
  \*****************************************/
/*! exports provided: environment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function() { return environment; });
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
var environment = {
    production: false
};
/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.


/***/ }),

/***/ "./src/main.ts":
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser-dynamic */ "./node_modules/@angular/platform-browser-dynamic/fesm5/platform-browser-dynamic.js");
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app/app.module */ "./src/app/app.module.ts");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./environments/environment */ "./src/environments/environment.ts");
/* harmony import */ var hammerjs__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! hammerjs */ "./node_modules/hammerjs/hammer.js");
/* harmony import */ var hammerjs__WEBPACK_IMPORTED_MODULE_4___default = /*#__PURE__*/__webpack_require__.n(hammerjs__WEBPACK_IMPORTED_MODULE_4__);





if (_environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["enableProdMode"])();
}
Object(_angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__["platformBrowserDynamic"])().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_2__["AppModule"])
    .catch(function (err) { return console.error(err); });


/***/ }),

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! d:\GITHUB\novko\src\main\resources\shop\src\main.ts */"./src/main.ts");


/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main.js.map