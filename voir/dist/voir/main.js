(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"],{

/***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/app.component.html":
/*!**************************************************************************!*\
  !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/app.component.html ***!
  \**************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("<div class=\"container\">\n  <h1>{{title}}</h1>\n  <div class=\"row\">\n    <nav>\n      <a routerLink=\"/productList\" class=\"col-xs-4\">Liste des produits</a>\n      <a routerLink=\"/productTable\" class=\"col-xs-4\">Table des produits</a>\n    </nav>\n  </div>\n</div>\n<router-outlet></router-outlet>\n<app-messages></app-messages>\n");

/***/ }),

/***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/messages/messages.component.html":
/*!****************************************************************************************!*\
  !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/messages/messages.component.html ***!
  \****************************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("<div class=\"container\">\n  <div *ngIf=\"messageService.messages.length\">\n\n    <h2>Messages</h2>\n    <button class=\"clear\" (click)=\"messageService.clear()\">clear</button>\n    <div *ngFor='let message of messageService.messages'> {{message}} </div>\n\n  </div>\n</div>\n");

/***/ }),

/***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/product-form/product-form.component.html":
/*!************************************************************************************************!*\
  !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/product-form/product-form.component.html ***!
  \************************************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("<div class=\"container\">\n  <div class=\"form-horizontal\">\n    <div>\n      <h1>Fiche produit - {{product.name}}</h1>\n    </div>\n    <form #productForm=\"ngForm\" (ngSubmit)=\"onSubmit()\" appIdentityRevealed>\n      <mat-tab-group>\n        <mat-tab>\n          <ng-template mat-tab-label>\n            <mat-icon>face</mat-icon>Identification\n          </ng-template>\n          <div class=\"form-group\">\n            <label class=\"control-label col-sm-2\" for=\"identifiant\">Identifiant 1</label>\n            <div class=\"col-sm-6\">\n              <input type=\" text\" class=\"form-control\" id=\"identifiant\" [(ngModel)]=\"product.id\" name=\"identifiant\"\n                required>\n            </div>\n          </div>\n          <div class=\"form-group\">\n            <label class=\"control-label col-sm-2\" for=\"code\">Code</label>\n            <div class=\"col-sm-6\">\n              <input type=\"text\" class=\"form-control\" id=\"code\" [(ngModel)]=\"product.code\" name=\"code\" required>\n            </div>\n          </div>\n          <div class=\"form-group\">\n            <label class=\"control-label col-sm-2\" for=\"name\">Name</label>\n            <div class=\"col-sm-6\">\n              <input type=\"text\" class=\"form-control\" id=\"name\" [(ngModel)]=\"product.name\" name=\"name\" required>\n            </div>\n          </div>\n        </mat-tab>\n        <mat-tab>\n          <ng-template mat-tab-label>\n            <mat-icon>money</mat-icon> Prix\n          </ng-template>\n          <div class=\"form-group\">\n            <label class=\"control-label col-sm-2\">Product tags</label>\n            <div class=\"col-sm-6\">\n              <mat-form-field class=\"chip-list\">\n                <mat-chip-list #chipList aria-label=\"Product category tag selection\">\n                  <mat-chip *ngFor=\"let categoryTag of product.categoryTags\">\n                    {{categoryTag.tag}}\n                    <mat-icon matChipRemove *ngIf=\"removable\">cancel</mat-icon>\n                  </mat-chip>\n                  <input placeholder=\"Catégories ...\" [matChipInputFor]=\"chipList\"\n                    [matChipInputSeparatorKeyCodes]=\"separatorKeysCodes\" [matChipInputAddOnBlur]=\"addOnBlur\"\n                    (matChipInputTokenEnd)=\"add($event)\">\n                </mat-chip-list>\n              </mat-form-field>\n            </div>\n          </div>\n          <div class=\"form-group\">\n            <label class=\"control-label col-sm-2\" for=\"vatRateOnPlace\">TVA sur place</label>\n            <div class=\"col-sm-6\">\n              <input type=\"text\" class=\"form-control\" id=\"vatRateOnPlace\" [(ngModel)]=\"product.vatRateOnPlace\"\n                name=\"vatRateOnPlace\">\n            </div>\n          </div>\n          <div class=\"form-group\">\n            <label class=\"control-label col-sm-2\" for=\"vatRateTakeAway\">TVA à emporter</label>\n            <div class=\"col-sm-6\">\n              <input type=\"text\" class=\"form-control\" id=\"vatRateTakeAway\" [(ngModel)]=\"product.vatRateTakeAway\"\n                name=\"vatRateTakeAway\">\n            </div>\n          </div>\n          <div class=\"form-group\">\n            <label class=\"control-label col-sm-2\" for=\"mini\">Mini</label>\n            <div class=\"col-sm-6\">\n              <input type=\"text\" class=\"form-control\" id=\"mini\" [(ngModel)]=\"product.mini\" name=\"mini\">\n            </div>\n          </div>\n          <div class=\"form-group\">\n            <label class=\"control-label col-sm-2\" for=\"normal\">Normal</label>\n            <div class=\"col-sm-6\">\n              <input type=\"text\" class=\"form-control\" id=\"normal\" [(ngModel)]=\"product.normal\" name=\"normal\">\n            </div>\n          </div>\n          <div class=\"form-group\">\n            <label class=\"control-label col-sm-2\" for=\"geant\">Géant</label>\n            <div class=\"col-sm-6\">\n              <input type=\"text\" class=\"form-control\" id=\"geant\" [(ngModel)]=\"product.geant\" name=\"geant\">\n            </div>\n          </div>\n          <div class=\"form-group\">\n            <label class=\"control-label col-sm-2\" for=\"fitmini\">Fit. mini</label>\n            <div class=\"col-sm-6\">\n              <input type=\"text\" class=\"form-control\" id=\"fitmini\" [(ngModel)]=\"product.fitmini\" name=\"fitmini\">\n            </div>\n          </div>\n          <div class=\"form-group\">\n            <label class=\"control-label col-sm-2\" for=\"fitnormal\">Fit. norm.</label>\n            <div class=\"col-sm-6\">\n              <input type=\"text\" class=\"form-control\" id=\"fitnormal\" [(ngModel)]=\"product.fitnormal\" name=\"fitnormal\">\n            </div>\n          </div>\n          <div class=\"form-group\">\n            <label class=\"control-label col-sm-2\" for=\"fitgeant\">Fit. geant</label>\n            <div class=\"col-sm-6\">\n              <input type=\"text\" class=\"form-control\" id=\"fitgeant\" [(ngModel)]=\"product.fitgeant\" name=\"fitgeant\">\n            </div>\n          </div>\n        </mat-tab>\n        <mat-tab>\n          <ng-template mat-tab-label>\n            <mat-icon>description</mat-icon> Descriptions\n          </ng-template>\n          <div class=\"form-group\">\n            <label class=\"control-label col-sm-2\" for=\"image\">Image</label>\n            <div class=\"col-sm-6\">\n              <input type=\"text\" class=\"form-control\" id=\"image\" [(ngModel)]=\"product.image\" name=\"image\">\n            </div>\n          </div>\n          <div class=\"form-group\">\n            <label class=\"control-label col-sm-2\" for=\"label\">Etiquette</label>\n            <div class=\"col-sm-6\">\n              <input type=\"text\" class=\"form-control\" id=\"label\" [(ngModel)]=\"product.label\" name=\"label\" required>\n            </div>\n          </div>\n          <div class=\"form-group\">\n            <label class=\"control-label col-sm-2\" for=\"ticketLabel \">Etiquette ticket</label>\n            <div class=\"col-sm-6\">\n              <input type=\"text\" class=\"form-control\" id=\"ticketLabel\" [(ngModel)]=\"product.ticketLabel\"\n                name=\"ticketLabel\" required>\n            </div>\n          </div>\n          <div class=\"form-group\">\n            <label class=\"control-label col-sm-2\" for=\"htmlKeyLabel\">Etiquette touche (html)</label>\n            <div class=\"col-sm-6\">\n              <input type=\"text\" class=\"form-control\" id=\"htmlKeyLabel\" [(ngModel)]=\"product.htmlKeyLabel\"\n                name=\"htmlKeyLabel\" required>\n            </div>\n          </div>\n          <div class=\"form-group\">\n            <label class=\"control-label col-sm-2\" for=\"webDetail\">Web description</label>\n            <div class=\"col-sm-6\">\n              <textarea type=\"text\" class=\"form-control\" id=\"webDetail\" [(ngModel)]=\"product.webDetail\"\n                name=\"webDetail\"></textarea>\n            </div>\n          </div>\n          <div class=\"form-group\">\n            <label class=\"control-label col-sm-2\" for=\"afficheDetail\">Affiche détail</label>\n            <div class=\"col-sm-6\">\n              <textarea type=\"text\" class=\"form-control\" id=\"afficheDetail\" [(ngModel)]=\"product.afficheDetail\"\n                name=\"afficheDetail\"></textarea>\n            </div>\n          </div>\n          <div class=\"form-group\">\n            <label class=\"control-label col-sm-2\" for=\"canMerge\">Peut être groupé</label>\n            <div class=\"col-sm-6\">\n              <input type=\"text\" class=\"form-control\" id=\"canMerge\" [(ngModel)]=\"product.canMerge\" name=\"canMerge\">\n            </div>\n          </div>\n        </mat-tab>\n      </mat-tab-group>\n\n      <div class=\"row\">\n        <button type=\"submit\" class=\"btn btn-success col-sm-2\" [disabled]=\"!productForm.valid\">Submit</button>\n        <button class=\"btn btn-fail col-sm-2\" (click)=\"goBack()\">go back</button>\n      </div>\n    </form>\n    {{diagnostic}}\n  </div>\n</div>\n");

/***/ }),

/***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/product-list/product-list.component.html":
/*!************************************************************************************************!*\
  !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/product-list/product-list.component.html ***!
  \************************************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("<div class=\"container\">\n  <h2>Liste des produits</h2>\n  <ul class=\"products\">\n    <li *ngFor=\"let product of products\">\n      <a routerLink=\"/detail/{{product.id}}\">\n        <span class=\"badge\">{{product.id}}</span> {{product.name}}\n      </a>\n      <button class=\"delete\" title=\"delete product\" (click)=\"delete(product)\">x</button>\n    </li>\n  </ul>\n  <label>Product name:\n    <input #productName />\n  </label>\n  <!-- (click) passes input value to add() and then clears the input -->\n  <button (click)=\"add(productName.value); productName.value=''\">\n    add\n  </button>\n</div>\n");

/***/ }),

/***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/product-table/product-table.component.html":
/*!**************************************************************************************************!*\
  !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/product-table/product-table.component.html ***!
  \**************************************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("<div class=\"container\">\n  <div class=\"row\">\n    <mat-form-field fxFlex=\"40%\">\n      <input matInput type=\"text\" (keyup)=\"doFilter($event.target.value)\" placeholder=\"Filtre\">\n    </mat-form-field>\n    <mat-paginator [pageSizeOptions]=\"[5, 15, 50, 200]\" showFirstLastButtons></mat-paginator>\n  </div>\n  <div class=\"wrapper\">\n    <table mat-table [dataSource]=\"dataSource\" matSort class=\"mat-elevation-z8\">\n      <ng-container matColumnDef=\"id\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> No. </th>\n        <td mat-cell *matCellDef=\"let element\"> {{element.id}} </td>\n      </ng-container>\n\n      <ng-container matColumnDef=\"name\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> Name </th>\n        <td mat-cell *matCellDef=\"let element\">\n          <a routerLink=\"/detail/{{element.id}}\"> {{element.name}} </a>\n        </td>\n      </ng-container>\n\n      <ng-container matColumnDef=\"mini\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> Mini </th>\n        <td mat-cell *matCellDef=\"let element\"> {{element.mini}} </td>\n      </ng-container>\n\n      <ng-container matColumnDef=\"normal\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> normal </th>\n        <td mat-cell *matCellDef=\"let element\"> {{element.normal}} </td>\n      </ng-container>\n\n      <ng-container matColumnDef=\"geant\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> geant </th>\n        <td mat-cell *matCellDef=\"let element\"> {{element.geant}} </td>\n      </ng-container>\n\n      <ng-container matColumnDef=\"fitmini\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> fitmini </th>\n        <td mat-cell *matCellDef=\"let element\"> {{element.fitmini}} </td>\n      </ng-container>\n\n      <ng-container matColumnDef=\"fitnormal\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> fitnormal </th>\n        <td mat-cell *matCellDef=\"let element\"> {{element.fitnormal}} </td>\n      </ng-container>\n\n      <ng-container matColumnDef=\"fitgeant\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> fitgeant </th>\n        <td mat-cell *matCellDef=\"let element\"> {{element.fitgeant}} </td>\n      </ng-container>\n\n      <ng-container matColumnDef=\"code\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> Code </th>\n        <td mat-cell *matCellDef=\"let element\"> {{element.code}} </td>\n      </ng-container>\n\n      <ng-container matColumnDef=\"label\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> label </th>\n        <td mat-cell *matCellDef=\"let element\"> {{element.label}} </td>\n      </ng-container>\n\n      <ng-container matColumnDef=\"ticketLabel\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> ticketLabel </th>\n        <td mat-cell *matCellDef=\"let element\"> {{element.ticketLabel}} </td>\n      </ng-container>\n\n      <ng-container matColumnDef=\"htmlKeyLabel\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> htmlKeyLabel </th>\n        <td mat-cell *matCellDef=\"let element\"> {{element.htmlKeyLabel}} </td>\n      </ng-container>\n\n      <ng-container matColumnDef=\"hiddenIndex\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> tags </th>\n        <td mat-cell *matCellDef=\"let element\"> {{element.hiddenIndex}} </td>\n      </ng-container>\n\n      <ng-container matColumnDef=\"image\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> image </th>\n        <td mat-cell *matCellDef=\"let element\"> {{element.image}} </td>\n      </ng-container>\n\n      <ng-container matColumnDef=\"vatRateOnPlace\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> vatRateOnPlace </th>\n        <td mat-cell *matCellDef=\"let element\"> {{element.vatRateOnPlace}} </td>\n      </ng-container>\n\n      <ng-container matColumnDef=\"vatRateTakeAway\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> vatRateTakeAway </th>\n        <td mat-cell *matCellDef=\"let element\"> {{element.vatRateTakeAway}} </td>\n      </ng-container>\n\n      <ng-container matColumnDef=\"webDetail\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> webDetail </th>\n        <td mat-cell *matCellDef=\"let element\"> {{element.webDetail}} </td>\n      </ng-container>\n\n      <ng-container matColumnDef=\"afficheDetail\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> afficheDetail </th>\n        <td mat-cell *matCellDef=\"let element\"> {{element.afficheDetail}} </td>\n      </ng-container>\n\n      <ng-container matColumnDef=\"canMerge\">\n        <th mat-header-cell *matHeaderCellDef mat-sort-header> canMerge </th>\n        <td mat-cell *matCellDef=\"let element\"> {{element.canMerge}} </td>\n      </ng-container>\n\n      <tr mat-header-row *matHeaderRowDef=\"displayedColumns\"></tr>\n      <tr mat-row *matRowDef=\"let row; columns: displayedColumns;\"></tr>\n    </table>\n  </div>\n</div>\n");

/***/ }),

/***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/reading-json-files/reading-json-files.component.html":
/*!************************************************************************************************************!*\
  !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/reading-json-files/reading-json-files.component.html ***!
  \************************************************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("<p>\n  reading-json-files works!\n</p>\n");

/***/ }),

/***/ "./node_modules/tslib/tslib.es6.js":
/*!*****************************************!*\
  !*** ./node_modules/tslib/tslib.es6.js ***!
  \*****************************************/
/*! exports provided: __extends, __assign, __rest, __decorate, __param, __metadata, __awaiter, __generator, __exportStar, __values, __read, __spread, __spreadArrays, __await, __asyncGenerator, __asyncDelegator, __asyncValues, __makeTemplateObject, __importStar, __importDefault */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__extends", function() { return __extends; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__assign", function() { return __assign; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__rest", function() { return __rest; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__decorate", function() { return __decorate; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__param", function() { return __param; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__metadata", function() { return __metadata; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__awaiter", function() { return __awaiter; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__generator", function() { return __generator; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__exportStar", function() { return __exportStar; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__values", function() { return __values; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__read", function() { return __read; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__spread", function() { return __spread; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__spreadArrays", function() { return __spreadArrays; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__await", function() { return __await; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__asyncGenerator", function() { return __asyncGenerator; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__asyncDelegator", function() { return __asyncDelegator; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__asyncValues", function() { return __asyncValues; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__makeTemplateObject", function() { return __makeTemplateObject; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__importStar", function() { return __importStar; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__importDefault", function() { return __importDefault; });
/*! *****************************************************************************
Copyright (c) Microsoft Corporation. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License"); you may not use
this file except in compliance with the License. You may obtain a copy of the
License at http://www.apache.org/licenses/LICENSE-2.0

THIS CODE IS PROVIDED ON AN *AS IS* BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, EITHER EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION ANY IMPLIED
WARRANTIES OR CONDITIONS OF TITLE, FITNESS FOR A PARTICULAR PURPOSE,
MERCHANTABLITY OR NON-INFRINGEMENT.

See the Apache Version 2.0 License for specific language governing permissions
and limitations under the License.
***************************************************************************** */
/* global Reflect, Promise */

var extendStatics = function(d, b) {
    extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return extendStatics(d, b);
};

function __extends(d, b) {
    extendStatics(d, b);
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
}

var __assign = function() {
    __assign = Object.assign || function __assign(t) {
        for (var s, i = 1, n = arguments.length; i < n; i++) {
            s = arguments[i];
            for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p)) t[p] = s[p];
        }
        return t;
    }
    return __assign.apply(this, arguments);
}

function __rest(s, e) {
    var t = {};
    for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p) && e.indexOf(p) < 0)
        t[p] = s[p];
    if (s != null && typeof Object.getOwnPropertySymbols === "function")
        for (var i = 0, p = Object.getOwnPropertySymbols(s); i < p.length; i++) {
            if (e.indexOf(p[i]) < 0 && Object.prototype.propertyIsEnumerable.call(s, p[i]))
                t[p[i]] = s[p[i]];
        }
    return t;
}

function __decorate(decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
}

function __param(paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
}

function __metadata(metadataKey, metadataValue) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(metadataKey, metadataValue);
}

function __awaiter(thisArg, _arguments, P, generator) {
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
}

function __generator(thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (_) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
}

function __exportStar(m, exports) {
    for (var p in m) if (!exports.hasOwnProperty(p)) exports[p] = m[p];
}

function __values(o) {
    var m = typeof Symbol === "function" && o[Symbol.iterator], i = 0;
    if (m) return m.call(o);
    return {
        next: function () {
            if (o && i >= o.length) o = void 0;
            return { value: o && o[i++], done: !o };
        }
    };
}

function __read(o, n) {
    var m = typeof Symbol === "function" && o[Symbol.iterator];
    if (!m) return o;
    var i = m.call(o), r, ar = [], e;
    try {
        while ((n === void 0 || n-- > 0) && !(r = i.next()).done) ar.push(r.value);
    }
    catch (error) { e = { error: error }; }
    finally {
        try {
            if (r && !r.done && (m = i["return"])) m.call(i);
        }
        finally { if (e) throw e.error; }
    }
    return ar;
}

function __spread() {
    for (var ar = [], i = 0; i < arguments.length; i++)
        ar = ar.concat(__read(arguments[i]));
    return ar;
}

function __spreadArrays() {
    for (var s = 0, i = 0, il = arguments.length; i < il; i++) s += arguments[i].length;
    for (var r = Array(s), k = 0, i = 0; i < il; i++)
        for (var a = arguments[i], j = 0, jl = a.length; j < jl; j++, k++)
            r[k] = a[j];
    return r;
};

function __await(v) {
    return this instanceof __await ? (this.v = v, this) : new __await(v);
}

function __asyncGenerator(thisArg, _arguments, generator) {
    if (!Symbol.asyncIterator) throw new TypeError("Symbol.asyncIterator is not defined.");
    var g = generator.apply(thisArg, _arguments || []), i, q = [];
    return i = {}, verb("next"), verb("throw"), verb("return"), i[Symbol.asyncIterator] = function () { return this; }, i;
    function verb(n) { if (g[n]) i[n] = function (v) { return new Promise(function (a, b) { q.push([n, v, a, b]) > 1 || resume(n, v); }); }; }
    function resume(n, v) { try { step(g[n](v)); } catch (e) { settle(q[0][3], e); } }
    function step(r) { r.value instanceof __await ? Promise.resolve(r.value.v).then(fulfill, reject) : settle(q[0][2], r); }
    function fulfill(value) { resume("next", value); }
    function reject(value) { resume("throw", value); }
    function settle(f, v) { if (f(v), q.shift(), q.length) resume(q[0][0], q[0][1]); }
}

function __asyncDelegator(o) {
    var i, p;
    return i = {}, verb("next"), verb("throw", function (e) { throw e; }), verb("return"), i[Symbol.iterator] = function () { return this; }, i;
    function verb(n, f) { i[n] = o[n] ? function (v) { return (p = !p) ? { value: __await(o[n](v)), done: n === "return" } : f ? f(v) : v; } : f; }
}

function __asyncValues(o) {
    if (!Symbol.asyncIterator) throw new TypeError("Symbol.asyncIterator is not defined.");
    var m = o[Symbol.asyncIterator], i;
    return m ? m.call(o) : (o = typeof __values === "function" ? __values(o) : o[Symbol.iterator](), i = {}, verb("next"), verb("throw"), verb("return"), i[Symbol.asyncIterator] = function () { return this; }, i);
    function verb(n) { i[n] = o[n] && function (v) { return new Promise(function (resolve, reject) { v = o[n](v), settle(resolve, reject, v.done, v.value); }); }; }
    function settle(resolve, reject, d, v) { Promise.resolve(v).then(function(v) { resolve({ value: v, done: d }); }, reject); }
}

function __makeTemplateObject(cooked, raw) {
    if (Object.defineProperty) { Object.defineProperty(cooked, "raw", { value: raw }); } else { cooked.raw = raw; }
    return cooked;
};

function __importStar(mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (Object.hasOwnProperty.call(mod, k)) result[k] = mod[k];
    result.default = mod;
    return result;
}

function __importDefault(mod) {
    return (mod && mod.__esModule) ? mod : { default: mod };
}


/***/ }),

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

/***/ "./src/app/app.component.css":
/*!***********************************!*\
  !*** ./src/app/app.component.css ***!
  \***********************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2FwcC5jb21wb25lbnQuY3NzIn0= */");

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
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");


let AppComponent = class AppComponent {
    constructor() {
        this.title = 'voir';
    }
};
AppComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'app-root',
        template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./app.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/app.component.html")).default,
        styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./app.component.css */ "./src/app/app.component.css")).default]
    })
], AppComponent);



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
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm2015/platform-browser.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm2015/forms.js");
/* harmony import */ var _angular_material_table__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/material/table */ "./node_modules/@angular/material/esm2015/table.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm2015/material.js");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./app.component */ "./src/app/app.component.ts");
/* harmony import */ var _product_form_product_form_component__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./product-form/product-form.component */ "./src/app/product-form/product-form.component.ts");
/* harmony import */ var _product_list_product_list_component__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./product-list/product-list.component */ "./src/app/product-list/product-list.component.ts");
/* harmony import */ var _voir_routing_module__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./voir-routing.module */ "./src/app/voir-routing.module.ts");
/* harmony import */ var _messages_messages_component__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ./messages/messages.component */ "./src/app/messages/messages.component.ts");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm2015/http.js");
/* harmony import */ var angular_in_memory_web_api__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! angular-in-memory-web-api */ "./node_modules/angular-in-memory-web-api/index.js");
/* harmony import */ var src_data_layer_in_memory_data_service__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! src/data_layer/in-memory-data.service */ "./src/data_layer/in-memory-data.service.ts");
/* harmony import */ var _reading_json_files_reading_json_files_component__WEBPACK_IMPORTED_MODULE_14__ = __webpack_require__(/*! ./reading-json-files/reading-json-files.component */ "./src/app/reading-json-files/reading-json-files.component.ts");
/* harmony import */ var _product_table_product_table_component__WEBPACK_IMPORTED_MODULE_15__ = __webpack_require__(/*! ./product-table/product-table.component */ "./src/app/product-table/product-table.component.ts");
/* harmony import */ var _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_16__ = __webpack_require__(/*! @angular/platform-browser/animations */ "./node_modules/@angular/platform-browser/fesm2015/animations.js");

















let AppModule = class AppModule {
};
AppModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["NgModule"])({
        declarations: [
            _app_component__WEBPACK_IMPORTED_MODULE_6__["AppComponent"],
            _product_form_product_form_component__WEBPACK_IMPORTED_MODULE_7__["ProductFormComponent"],
            _product_list_product_list_component__WEBPACK_IMPORTED_MODULE_8__["ProductListComponent"],
            _messages_messages_component__WEBPACK_IMPORTED_MODULE_10__["MessagesComponent"],
            _reading_json_files_reading_json_files_component__WEBPACK_IMPORTED_MODULE_14__["ReadingJsonFilesComponent"],
            _product_table_product_table_component__WEBPACK_IMPORTED_MODULE_15__["ProductTableComponent"]
        ],
        imports: [
            _angular_platform_browser__WEBPACK_IMPORTED_MODULE_1__["BrowserModule"],
            _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
            _voir_routing_module__WEBPACK_IMPORTED_MODULE_9__["VoirRoutingModule"],
            _angular_common_http__WEBPACK_IMPORTED_MODULE_11__["HttpClientModule"],
            // The HttpClientInMemoryWebApiModule module intercepts HTTP requests
            // and returns simulated server responses.
            // Remove it when a real server is ready to receive requests.
            angular_in_memory_web_api__WEBPACK_IMPORTED_MODULE_12__["HttpClientInMemoryWebApiModule"].forRoot(src_data_layer_in_memory_data_service__WEBPACK_IMPORTED_MODULE_13__["InMemoryDataService"], { dataEncapsulation: false }),
            _angular_material_table__WEBPACK_IMPORTED_MODULE_4__["MatTableModule"],
            _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatPaginatorModule"],
            _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatSortModule"],
            _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatFormFieldModule"],
            _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatInputModule"],
            _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatChipsModule"],
            _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatIconModule"],
            _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatTabsModule"],
            _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_16__["BrowserAnimationsModule"]
        ],
        exports: [
            _angular_material_table__WEBPACK_IMPORTED_MODULE_4__["MatTableModule"],
            _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatPaginatorModule"],
            _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatSortModule"],
            _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatFormFieldModule"],
            _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatInputModule"],
            _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatChipsModule"],
            _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatIconModule"],
            _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatTabsModule"]
        ],
        providers: [],
        bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_6__["AppComponent"]]
    })
], AppModule);



/***/ }),

/***/ "./src/app/data_models/price-category.ts":
/*!***********************************************!*\
  !*** ./src/app/data_models/price-category.ts ***!
  \***********************************************/
/*! exports provided: PriceCategory */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "PriceCategory", function() { return PriceCategory; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");

var PriceCategory;
(function (PriceCategory) {
    PriceCategory["SdwMini"] = "SDWMINI";
    PriceCategory["SdwNormal"] = "SDWNORMAL";
    PriceCategory["SdwGeant"] = "SDWGEANT";
    PriceCategory["SdwFitMini"] = "FITMINI";
    PriceCategory["SdwFitNormal"] = "FITNORMAL";
    PriceCategory["SdwFitGeant"] = "FITGEANT";
})(PriceCategory || (PriceCategory = {}));


/***/ }),

/***/ "./src/app/data_models/product.ts":
/*!****************************************!*\
  !*** ./src/app/data_models/product.ts ***!
  \****************************************/
/*! exports provided: Product */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Product", function() { return Product; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _price_category__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./price-category */ "./src/app/data_models/price-category.ts");


class Product {
    constructor(id, label, ticketLabel, code, name, htmlKeyLabel, categoryTags, image, vatRateOnPlace, vatRateTakeAway, mini, normal, geant, fitmini, fitnormal, fitgeant, webDetail, afficheDetail, canMerge, hiddenIndex) {
        this.id = id;
        this.label = label;
        this.ticketLabel = ticketLabel;
        this.code = code;
        this.name = name;
        this.htmlKeyLabel = htmlKeyLabel;
        this.categoryTags = categoryTags;
        this.image = image;
        this.vatRateOnPlace = vatRateOnPlace;
        this.vatRateTakeAway = vatRateTakeAway;
        this.mini = mini;
        this.normal = normal;
        this.geant = geant;
        this.fitmini = fitmini;
        this.fitnormal = fitnormal;
        this.fitgeant = fitgeant;
        this.webDetail = webDetail;
        this.afficheDetail = afficheDetail;
        this.canMerge = canMerge;
        this.hiddenIndex = hiddenIndex;
        this.productCategoryTags = [{ tag: 'assiettes' }, { tag: 'autre' }, { tag: 'baguette' }, { tag: 'boisson' }, { tag: 'chèvre' }, { tag: 'classiques' }, { tag: 'dessert' }, { tag: 'mer' }, { tag: 'panini' }, { tag: 'poulet' }, { tag: 'salade' }, { tag: 'tartiner' }, { tag: 'salaison' }, { tag: 'sandwich' }, { tag: 'sud' }, { tag: 'suggestion' }, { tag: 'viennoiserie' }];
        this.id = id;
        this.label = label;
        this.ticketLabel = ticketLabel;
        this.code = code;
        this.name = name;
        this.htmlKeyLabel = htmlKeyLabel;
        this.categoryTags = categoryTags;
        this.image = image;
        this.vatRateOnPlace = vatRateOnPlace;
        this.vatRateTakeAway = vatRateTakeAway;
        this.mini = mini;
        this.normal = normal;
        this.geant = geant;
        this.fitmini = fitmini;
        this.fitnormal = fitnormal;
        this.fitgeant = fitgeant;
        this.webDetail = webDetail;
        this.afficheDetail = afficheDetail;
        this.canMerge = canMerge;
    }
    getTicketLabel(pricecategory) {
        if (_price_category__WEBPACK_IMPORTED_MODULE_1__["PriceCategory"].SdwMini === pricecategory) {
            return this.label + ' (mini)';
        }
        else if (_price_category__WEBPACK_IMPORTED_MODULE_1__["PriceCategory"].SdwNormal === pricecategory) {
            return this.label;
        }
        else if (_price_category__WEBPACK_IMPORTED_MODULE_1__["PriceCategory"].SdwGeant === pricecategory) {
            return this.label + ' (géant)';
        }
        else if (_price_category__WEBPACK_IMPORTED_MODULE_1__["PriceCategory"].SdwFitMini === pricecategory) {
            return this.label + ' (fit.mini)';
        }
        else if (_price_category__WEBPACK_IMPORTED_MODULE_1__["PriceCategory"].SdwFitNormal === pricecategory) {
            return this.label + ' (fitness)';
        }
        else if (_price_category__WEBPACK_IMPORTED_MODULE_1__["PriceCategory"].SdwFitGeant === pricecategory) {
            return this.label + ' (fit.géant)';
        }
        console.error('Not a category');
    }
    getPrice(pricecategory) {
        let price;
        if (_price_category__WEBPACK_IMPORTED_MODULE_1__["PriceCategory"].SdwMini === pricecategory) {
            price = this.mini;
        }
        else if (_price_category__WEBPACK_IMPORTED_MODULE_1__["PriceCategory"].SdwNormal === pricecategory) {
            price = this.normal;
        }
        else if (_price_category__WEBPACK_IMPORTED_MODULE_1__["PriceCategory"].SdwGeant === pricecategory) {
            price = this.geant;
        }
        else if (_price_category__WEBPACK_IMPORTED_MODULE_1__["PriceCategory"].SdwFitMini === pricecategory) {
            price = this.fitmini;
        }
        else if (_price_category__WEBPACK_IMPORTED_MODULE_1__["PriceCategory"].SdwFitNormal === pricecategory) {
            price = this.fitnormal;
        }
        else if (_price_category__WEBPACK_IMPORTED_MODULE_1__["PriceCategory"].SdwFitGeant === pricecategory) {
            price = this.fitgeant;
        }
        if (price === undefined) {
            price = this.normal;
        }
        return price;
    }
}


/***/ }),

/***/ "./src/app/messages/messages.component.css":
/*!*************************************************!*\
  !*** ./src/app/messages/messages.component.css ***!
  \*************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL21lc3NhZ2VzL21lc3NhZ2VzLmNvbXBvbmVudC5jc3MifQ== */");

/***/ }),

/***/ "./src/app/messages/messages.component.ts":
/*!************************************************!*\
  !*** ./src/app/messages/messages.component.ts ***!
  \************************************************/
/*! exports provided: MessagesComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MessagesComponent", function() { return MessagesComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var src_business_layer_message_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/business_layer/message.service */ "./src/business_layer/message.service.ts");



let MessagesComponent = class MessagesComponent {
    constructor(messageService) {
        this.messageService = messageService;
    }
    ngOnInit() {
    }
};
MessagesComponent.ctorParameters = () => [
    { type: src_business_layer_message_service__WEBPACK_IMPORTED_MODULE_2__["MessageService"] }
];
MessagesComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'app-messages',
        template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./messages.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/messages/messages.component.html")).default,
        styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./messages.component.css */ "./src/app/messages/messages.component.css")).default]
    }),
    tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [src_business_layer_message_service__WEBPACK_IMPORTED_MODULE_2__["MessageService"]])
], MessagesComponent);



/***/ }),

/***/ "./src/app/product-form/product-form.component.css":
/*!*********************************************************!*\
  !*** ./src/app/product-form/product-form.component.css ***!
  \*********************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = (".ng-valid[required],\n.ng-valid.required {\n  border-left: 5px solid #42A948;\n  /* green */\n}\n\n.ng-invalid:not(form) {\n  border-left: 5px solid #a94442;\n  /* red */\n}\n\n.chip-list {\n  width: 100%;\n}\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvcHJvZHVjdC1mb3JtL3Byb2R1Y3QtZm9ybS5jb21wb25lbnQuY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBOztFQUVFLDhCQUE4QjtFQUM5QixVQUFVO0FBQ1o7O0FBRUE7RUFDRSw4QkFBOEI7RUFDOUIsUUFBUTtBQUNWOztBQUVBO0VBQ0UsV0FBVztBQUNiIiwiZmlsZSI6InNyYy9hcHAvcHJvZHVjdC1mb3JtL3Byb2R1Y3QtZm9ybS5jb21wb25lbnQuY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLm5nLXZhbGlkW3JlcXVpcmVkXSxcbi5uZy12YWxpZC5yZXF1aXJlZCB7XG4gIGJvcmRlci1sZWZ0OiA1cHggc29saWQgIzQyQTk0ODtcbiAgLyogZ3JlZW4gKi9cbn1cblxuLm5nLWludmFsaWQ6bm90KGZvcm0pIHtcbiAgYm9yZGVyLWxlZnQ6IDVweCBzb2xpZCAjYTk0NDQyO1xuICAvKiByZWQgKi9cbn1cblxuLmNoaXAtbGlzdCB7XG4gIHdpZHRoOiAxMDAlO1xufVxuIl19 */");

/***/ }),

/***/ "./src/app/product-form/product-form.component.ts":
/*!********************************************************!*\
  !*** ./src/app/product-form/product-form.component.ts ***!
  \********************************************************/
/*! exports provided: ProductFormComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProductFormComponent", function() { return ProductFormComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _angular_cdk_keycodes__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/cdk/keycodes */ "./node_modules/@angular/cdk/esm2015/keycodes.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm2015/router.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm2015/common.js");
/* harmony import */ var src_business_layer_products_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! src/business_layer/products.service */ "./src/business_layer/products.service.ts");
/* harmony import */ var _data_models_product__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../data_models/product */ "./src/app/data_models/product.ts");







let ProductFormComponent = class ProductFormComponent {
    constructor(route, location, productsService) {
        this.route = route;
        this.location = location;
        this.productsService = productsService;
        this.submitted = false;
        this.visible = true;
        this.selectable = true;
        this.removable = true;
        this.addOnBlur = true;
        this.separatorKeysCodes = [_angular_cdk_keycodes__WEBPACK_IMPORTED_MODULE_2__["ENTER"], _angular_cdk_keycodes__WEBPACK_IMPORTED_MODULE_2__["COMMA"]];
    }
    ngOnInit() {
        this.getProduct();
    }
    onSubmit() {
        this.save();
        this.submitted = true;
    }
    getProduct() {
        const id = +this.route.snapshot.paramMap.get('id');
        this.productsService.getProduct(id).subscribe(product => { this.product = product; });
    }
    goBack() {
        this.location.back();
    }
    save() {
        this.productsService.updateProduct(this.product)
            .subscribe(() => this.goBack());
    }
    add(event) {
        const input = event.input;
        const value = event.value;
        // Add our fruit
        if ((value || '').trim()) {
            if (!this.product.categoryTags) {
                this.product.categoryTags = new Array();
            }
            this.product.categoryTags.push({ tag: value.trim() });
        }
        // Reset the input value
        if (input) {
            input.value = '';
        }
    }
    remove(productCategoryTag) {
        const index = this.product.categoryTags.indexOf(productCategoryTag);
        if (index >= 0) {
            this.product.categoryTags.splice(index, 1);
        }
    }
    getNewProduct() {
        const id = 0;
        const label = '';
        const ticketLabel = '';
        const code = '';
        const name = '';
        const htmlKeyLabel = '';
        const categoryTags = [];
        const image = '';
        const vatRateOnPlace = 0;
        const vatRateTakeAway = 0;
        const mini = 0;
        const normal = 0;
        const geant = 0;
        const fitmini = 0;
        const fitnormal = 0;
        const fitgeant = 0;
        const webDetail = '';
        const afficheDetail = '';
        const canMerge = false;
        const hiddenIndex = '';
        const product = new _data_models_product__WEBPACK_IMPORTED_MODULE_6__["Product"](id, label, ticketLabel, code, name, htmlKeyLabel, categoryTags, image, vatRateOnPlace, vatRateTakeAway, mini, normal, geant, fitmini, fitnormal, fitgeant, webDetail, afficheDetail, canMerge, hiddenIndex);
        this.submitted = false;
        return product;
    }
    get diagnostic() { return JSON.stringify(this.product); }
};
ProductFormComponent.ctorParameters = () => [
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_3__["ActivatedRoute"] },
    { type: _angular_common__WEBPACK_IMPORTED_MODULE_4__["Location"] },
    { type: src_business_layer_products_service__WEBPACK_IMPORTED_MODULE_5__["ProductsService"] }
];
ProductFormComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'app-product-form',
        template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./product-form.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/product-form/product-form.component.html")).default,
        styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./product-form.component.css */ "./src/app/product-form/product-form.component.css")).default]
    }),
    tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_3__["ActivatedRoute"],
        _angular_common__WEBPACK_IMPORTED_MODULE_4__["Location"],
        src_business_layer_products_service__WEBPACK_IMPORTED_MODULE_5__["ProductsService"]])
], ProductFormComponent);



/***/ }),

/***/ "./src/app/product-list/product-list.component.css":
/*!*********************************************************!*\
  !*** ./src/app/product-list/product-list.component.css ***!
  \*********************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = (".products {\n  margin: 0 0 2em 0;\n  list-style-type: none;\n  padding: 0;\n  width: 15em;\n}\n\n.products li {\n  position: relative;\n  cursor: pointer;\n  background-color: #EEE;\n  margin: .5em;\n  padding: .3em 0;\n  height: 1.6em;\n  border-radius: 4px;\n}\n\n.products li:hover {\n  color: #607D8B;\n  background-color: #DDD;\n  left: .1em;\n}\n\n.products a {\n  color: #333;\n  text-decoration: none;\n  position: relative;\n  display: block;\n  width: 250px;\n}\n\n.products a:hover {\n  color: #607D8B;\n}\n\n.products .badge {\n  display: inline-block;\n  font-size: small;\n  color: white;\n  padding: 0.8em 0.7em 0 0.7em;\n  background-color: #405061;\n  line-height: 1em;\n  position: relative;\n  left: -1px;\n  top: -4px;\n  height: 1.8em;\n  min-width: 16px;\n  text-align: right;\n  margin-right: .8em;\n  border-radius: 4px 0 0 4px;\n}\n\nbutton {\n  background-color: #eee;\n  border: none;\n  padding: 5px 10px;\n  border-radius: 4px;\n  cursor: pointer;\n  cursor: hand;\n  font-family: Arial;\n}\n\nbutton:hover {\n  background-color: #cfd8dc;\n}\n\nbutton.delete {\n  position: relative;\n  left: 194px;\n  top: -32px;\n  background-color: gray !important;\n  color: white;\n}\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvcHJvZHVjdC1saXN0L3Byb2R1Y3QtbGlzdC5jb21wb25lbnQuY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0VBQ0UsaUJBQWlCO0VBQ2pCLHFCQUFxQjtFQUNyQixVQUFVO0VBQ1YsV0FBVztBQUNiOztBQUVBO0VBQ0Usa0JBQWtCO0VBQ2xCLGVBQWU7RUFDZixzQkFBc0I7RUFDdEIsWUFBWTtFQUNaLGVBQWU7RUFDZixhQUFhO0VBQ2Isa0JBQWtCO0FBQ3BCOztBQUVBO0VBQ0UsY0FBYztFQUNkLHNCQUFzQjtFQUN0QixVQUFVO0FBQ1o7O0FBRUE7RUFDRSxXQUFXO0VBQ1gscUJBQXFCO0VBQ3JCLGtCQUFrQjtFQUNsQixjQUFjO0VBQ2QsWUFBWTtBQUNkOztBQUVBO0VBQ0UsY0FBYztBQUNoQjs7QUFFQTtFQUNFLHFCQUFxQjtFQUNyQixnQkFBZ0I7RUFDaEIsWUFBWTtFQUNaLDRCQUE0QjtFQUM1Qix5QkFBeUI7RUFDekIsZ0JBQWdCO0VBQ2hCLGtCQUFrQjtFQUNsQixVQUFVO0VBQ1YsU0FBUztFQUNULGFBQWE7RUFDYixlQUFlO0VBQ2YsaUJBQWlCO0VBQ2pCLGtCQUFrQjtFQUNsQiwwQkFBMEI7QUFDNUI7O0FBRUE7RUFDRSxzQkFBc0I7RUFDdEIsWUFBWTtFQUNaLGlCQUFpQjtFQUNqQixrQkFBa0I7RUFDbEIsZUFBZTtFQUNmLFlBQVk7RUFDWixrQkFBa0I7QUFDcEI7O0FBRUE7RUFDRSx5QkFBeUI7QUFDM0I7O0FBRUE7RUFDRSxrQkFBa0I7RUFDbEIsV0FBVztFQUNYLFVBQVU7RUFDVixpQ0FBaUM7RUFDakMsWUFBWTtBQUNkIiwiZmlsZSI6InNyYy9hcHAvcHJvZHVjdC1saXN0L3Byb2R1Y3QtbGlzdC5jb21wb25lbnQuY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLnByb2R1Y3RzIHtcbiAgbWFyZ2luOiAwIDAgMmVtIDA7XG4gIGxpc3Qtc3R5bGUtdHlwZTogbm9uZTtcbiAgcGFkZGluZzogMDtcbiAgd2lkdGg6IDE1ZW07XG59XG5cbi5wcm9kdWN0cyBsaSB7XG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcbiAgY3Vyc29yOiBwb2ludGVyO1xuICBiYWNrZ3JvdW5kLWNvbG9yOiAjRUVFO1xuICBtYXJnaW46IC41ZW07XG4gIHBhZGRpbmc6IC4zZW0gMDtcbiAgaGVpZ2h0OiAxLjZlbTtcbiAgYm9yZGVyLXJhZGl1czogNHB4O1xufVxuXG4ucHJvZHVjdHMgbGk6aG92ZXIge1xuICBjb2xvcjogIzYwN0Q4QjtcbiAgYmFja2dyb3VuZC1jb2xvcjogI0RERDtcbiAgbGVmdDogLjFlbTtcbn1cblxuLnByb2R1Y3RzIGEge1xuICBjb2xvcjogIzMzMztcbiAgdGV4dC1kZWNvcmF0aW9uOiBub25lO1xuICBwb3NpdGlvbjogcmVsYXRpdmU7XG4gIGRpc3BsYXk6IGJsb2NrO1xuICB3aWR0aDogMjUwcHg7XG59XG5cbi5wcm9kdWN0cyBhOmhvdmVyIHtcbiAgY29sb3I6ICM2MDdEOEI7XG59XG5cbi5wcm9kdWN0cyAuYmFkZ2Uge1xuICBkaXNwbGF5OiBpbmxpbmUtYmxvY2s7XG4gIGZvbnQtc2l6ZTogc21hbGw7XG4gIGNvbG9yOiB3aGl0ZTtcbiAgcGFkZGluZzogMC44ZW0gMC43ZW0gMCAwLjdlbTtcbiAgYmFja2dyb3VuZC1jb2xvcjogIzQwNTA2MTtcbiAgbGluZS1oZWlnaHQ6IDFlbTtcbiAgcG9zaXRpb246IHJlbGF0aXZlO1xuICBsZWZ0OiAtMXB4O1xuICB0b3A6IC00cHg7XG4gIGhlaWdodDogMS44ZW07XG4gIG1pbi13aWR0aDogMTZweDtcbiAgdGV4dC1hbGlnbjogcmlnaHQ7XG4gIG1hcmdpbi1yaWdodDogLjhlbTtcbiAgYm9yZGVyLXJhZGl1czogNHB4IDAgMCA0cHg7XG59XG5cbmJ1dHRvbiB7XG4gIGJhY2tncm91bmQtY29sb3I6ICNlZWU7XG4gIGJvcmRlcjogbm9uZTtcbiAgcGFkZGluZzogNXB4IDEwcHg7XG4gIGJvcmRlci1yYWRpdXM6IDRweDtcbiAgY3Vyc29yOiBwb2ludGVyO1xuICBjdXJzb3I6IGhhbmQ7XG4gIGZvbnQtZmFtaWx5OiBBcmlhbDtcbn1cblxuYnV0dG9uOmhvdmVyIHtcbiAgYmFja2dyb3VuZC1jb2xvcjogI2NmZDhkYztcbn1cblxuYnV0dG9uLmRlbGV0ZSB7XG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcbiAgbGVmdDogMTk0cHg7XG4gIHRvcDogLTMycHg7XG4gIGJhY2tncm91bmQtY29sb3I6IGdyYXkgIWltcG9ydGFudDtcbiAgY29sb3I6IHdoaXRlO1xufVxuIl19 */");

/***/ }),

/***/ "./src/app/product-list/product-list.component.ts":
/*!********************************************************!*\
  !*** ./src/app/product-list/product-list.component.ts ***!
  \********************************************************/
/*! exports provided: ProductListComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProductListComponent", function() { return ProductListComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var src_business_layer_products_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! src/business_layer/products.service */ "./src/business_layer/products.service.ts");



let ProductListComponent = class ProductListComponent {
    constructor(productService) {
        this.productService = productService;
    }
    ngOnInit() {
        this.productService.getProducts()
            .subscribe(products => this.products = products);
    }
    onSelect(product) {
        this.selectedProduct = product;
    }
    add(name) {
        name = name.trim();
        if (!name) {
            return;
        }
        this.productService.addProduct({ name })
            .subscribe(product => {
            this.products.push(product);
        });
    }
    delete(product) {
        this.products = this.products.filter(p => p !== product);
        this.productService.deleteProduct(product).subscribe();
    }
};
ProductListComponent.ctorParameters = () => [
    { type: src_business_layer_products_service__WEBPACK_IMPORTED_MODULE_2__["ProductsService"] }
];
ProductListComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'app-product-list',
        template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./product-list.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/product-list/product-list.component.html")).default,
        styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./product-list.component.css */ "./src/app/product-list/product-list.component.css")).default]
    }),
    tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [src_business_layer_products_service__WEBPACK_IMPORTED_MODULE_2__["ProductsService"]])
], ProductListComponent);



/***/ }),

/***/ "./src/app/product-table/product-table.component.css":
/*!***********************************************************!*\
  !*** ./src/app/product-table/product-table.component.css ***!
  \***********************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("table {\n  width: 100%;\n}\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvcHJvZHVjdC10YWJsZS9wcm9kdWN0LXRhYmxlLmNvbXBvbmVudC5jc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7RUFDRSxXQUFXO0FBQ2IiLCJmaWxlIjoic3JjL2FwcC9wcm9kdWN0LXRhYmxlL3Byb2R1Y3QtdGFibGUuY29tcG9uZW50LmNzcyIsInNvdXJjZXNDb250ZW50IjpbInRhYmxlIHtcbiAgd2lkdGg6IDEwMCU7XG59XG4iXX0= */");

/***/ }),

/***/ "./src/app/product-table/product-table.component.ts":
/*!**********************************************************!*\
  !*** ./src/app/product-table/product-table.component.ts ***!
  \**********************************************************/
/*! exports provided: ProductTableComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProductTableComponent", function() { return ProductTableComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm2015/material.js");
/* harmony import */ var src_business_layer_products_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! src/business_layer/products.service */ "./src/business_layer/products.service.ts");




let ProductTableComponent = class ProductTableComponent {
    constructor(productService) {
        this.productService = productService;
        this.displayedColumns = ['id', 'name', 'mini', 'normal', 'geant', 'fitmini', 'fitnormal', 'fitgeant', 'code', 'label', 'ticketLabel', 'htmlKeyLabel', 'hiddenIndex', 'image', 'vatRateOnPlace', 'vatRateTakeAway', 'webDetail', 'afficheDetail', 'canMerge'];
        this.doFilter = (value) => {
            this.dataSource.filter = value.trim().toLocaleLowerCase();
        };
    }
    ngOnInit() {
        this.productService.getProducts()
            .subscribe(products => {
            this.products = products;
            this.products.forEach(product => this.populateHiddenIndex(product));
            this.dataSource = new _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatTableDataSource"](this.products);
            this.dataSource.sort = this.sort;
            this.dataSource.paginator = this.paginator;
        });
    }
    populateHiddenIndex(product) {
        product.hiddenIndex = '';
        if (product.categoryTags) {
            product.categoryTags.forEach(categoryTag => product.hiddenIndex += ' ' + categoryTag.tag);
        }
    }
};
ProductTableComponent.ctorParameters = () => [
    { type: src_business_layer_products_service__WEBPACK_IMPORTED_MODULE_3__["ProductsService"] }
];
tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ViewChild"])(_angular_material__WEBPACK_IMPORTED_MODULE_2__["MatPaginator"], { static: false }),
    tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:type", _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatPaginator"])
], ProductTableComponent.prototype, "paginator", void 0);
tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ViewChild"])(_angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSort"], { static: false }),
    tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:type", _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSort"])
], ProductTableComponent.prototype, "sort", void 0);
ProductTableComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'app-product-table',
        template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./product-table.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/product-table/product-table.component.html")).default,
        styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./product-table.component.css */ "./src/app/product-table/product-table.component.css")).default]
    }),
    tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [src_business_layer_products_service__WEBPACK_IMPORTED_MODULE_3__["ProductsService"]])
], ProductTableComponent);



/***/ }),

/***/ "./src/app/reading-json-files/reading-json-files.component.css":
/*!*********************************************************************!*\
  !*** ./src/app/reading-json-files/reading-json-files.component.css ***!
  \*********************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL3JlYWRpbmctanNvbi1maWxlcy9yZWFkaW5nLWpzb24tZmlsZXMuY29tcG9uZW50LmNzcyJ9 */");

/***/ }),

/***/ "./src/app/reading-json-files/reading-json-files.component.ts":
/*!********************************************************************!*\
  !*** ./src/app/reading-json-files/reading-json-files.component.ts ***!
  \********************************************************************/
/*! exports provided: ReadingJsonFilesComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ReadingJsonFilesComponent", function() { return ReadingJsonFilesComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _assets_voir_products_json__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../assets/voir_products.json */ "./src/assets/voir_products.json");
var _assets_voir_products_json__WEBPACK_IMPORTED_MODULE_2___namespace = /*#__PURE__*/__webpack_require__.t(/*! ../../assets/voir_products.json */ "./src/assets/voir_products.json", 1);


// @ts-ignore

let ReadingJsonFilesComponent = class ReadingJsonFilesComponent {
    constructor() {
        console.log('Reading local json files');
        console.log(_assets_voir_products_json__WEBPACK_IMPORTED_MODULE_2__);
    }
    ngOnInit() {
    }
    getProductList() {
        console.log('getProductList');
        return _assets_voir_products_json__WEBPACK_IMPORTED_MODULE_2__;
    }
};
ReadingJsonFilesComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'app-reading-json-files',
        template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./reading-json-files.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/reading-json-files/reading-json-files.component.html")).default,
        styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./reading-json-files.component.css */ "./src/app/reading-json-files/reading-json-files.component.css")).default]
    }),
    tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
], ReadingJsonFilesComponent);



/***/ }),

/***/ "./src/app/voir-routing.module.ts":
/*!****************************************!*\
  !*** ./src/app/voir-routing.module.ts ***!
  \****************************************/
/*! exports provided: VoirRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "VoirRoutingModule", function() { return VoirRoutingModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm2015/router.js");
/* harmony import */ var _product_list_product_list_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./product-list/product-list.component */ "./src/app/product-list/product-list.component.ts");
/* harmony import */ var _product_form_product_form_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./product-form/product-form.component */ "./src/app/product-form/product-form.component.ts");
/* harmony import */ var _product_table_product_table_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./product-table/product-table.component */ "./src/app/product-table/product-table.component.ts");






const routes = [
    { path: 'productList', component: _product_list_product_list_component__WEBPACK_IMPORTED_MODULE_3__["ProductListComponent"] },
    { path: 'productTable', component: _product_table_product_table_component__WEBPACK_IMPORTED_MODULE_5__["ProductTableComponent"] },
    { path: 'detail/:id', component: _product_form_product_form_component__WEBPACK_IMPORTED_MODULE_4__["ProductFormComponent"] },
    { path: '', redirectTo: '/productTable', pathMatch: 'full' }
];
let VoirRoutingModule = class VoirRoutingModule {
};
VoirRoutingModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
        declarations: [],
        imports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forRoot(routes)],
        exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]]
    })
], VoirRoutingModule);



/***/ }),

/***/ "./src/assets/voir_products.json":
/*!***************************************!*\
  !*** ./src/assets/voir_products.json ***!
  \***************************************/
/*! exports provided: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, default */
/***/ (function(module) {

module.exports = JSON.parse("[{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"BAGFITNESS\",\"codeTva\":\"1\",\"geant\":2,\"htmlKeyLabel\":\"B. fitness\",\"id\":1,\"image\":\"null\",\"label\":\"Baguette Fitness\",\"mini\":0.5,\"name\":\"Baguette Fitness\",\"normal\":1,\"ticketLabel\":\"Baguette Fitness\",\"categoryTags\":[{\"tag\":\"baguette\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"BAGUETTE\",\"codeTva\":\"1\",\"fitgeant\":2,\"fitmini\":0.4,\"fitnormal\":1.5,\"geant\":1.2,\"htmlKeyLabel\":\"Baguette\",\"id\":2,\"image\":\"null\",\"label\":\"Baguette\",\"mini\":0.4,\"name\":\"Baguette\",\"normal\":1.3,\"ticketLabel\":\"Baguette\",\"categoryTags\":[{\"tag\":\"baguette\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"BAGUETTE\",\"codeTva\":\"1\",\"fitgeant\":2,\"fitmini\":0.4,\"fitnormal\":1.5,\"geant\":1.2,\"htmlKeyLabel\":\"Baguette\",\"id\":3,\"image\":\"null\",\"label\":\"Baguette\",\"mini\":0.4,\"name\":\"Baguette\",\"normal\":1.3,\"ticketLabel\":\"Baguette\",\"categoryTags\":[{\"tag\":\"baguette\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"BIERE\",\"codeTva\":\"3\",\"htmlKeyLabel\":\"Bière\",\"id\":4,\"image\":\"null\",\"label\":\"Bière\",\"name\":\"Bière\",\"normal\":2,\"ticketLabel\":\"Bière\",\"categoryTags\":[{\"tag\":\"boisson\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"BOULETTES\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Boulettes\",\"id\":5,\"image\":\"null\",\"label\":\"Boulettes\",\"name\":\"Boulettes\",\"normal\":1.6,\"ticketLabel\":\"Boulettes\",\"categoryTags\":[{\"tag\":\"salaison\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"CAFE\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Café\",\"id\":6,\"image\":\"null\",\"label\":\"Café\",\"name\":\"Café\",\"normal\":1.8,\"ticketLabel\":\"Café\",\"categoryTags\":[{\"tag\":\"boisson\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"CAMPAGNARD\",\"codeTva\":\"1\",\"geant\":6,\"htmlKeyLabel\":\"Campagnard\",\"id\":7,\"image\":\"null\",\"label\":\"Campagnard\",\"mini\":2,\"name\":\"Campagnard\",\"normal\":4,\"ticketLabel\":\"Campagnard\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"Fromage frais de chèvre, bacon fumé, poivre, huile vierge de noix\",\"canMerge\":true,\"code\":\"CHEVREBACON\",\"codeTva\":\"1\",\"geant\":6.2,\"htmlKeyLabel\":\"Chèvre<br>Bacon\",\"id\":8,\"image\":\"null\",\"label\":\"Chèvre Bacon\",\"mini\":2,\"name\":\"Chèvre Bacon\",\"normal\":4.2,\"ticketLabel\":\"Chèvre Bacon\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"chevre\"}],\"webDetail\":\"Fromage frais de chèvre, bacon fumé, poivre, huile vierge de noix\"},{\"afficheDetail\":\"Fromage frais de chèvre, miel, poivre, roquette\",\"canMerge\":true,\"code\":\"CHEVREMIEL\",\"codeTva\":\"1\",\"geant\":5.8,\"htmlKeyLabel\":\"Chèvre<br>Miel\",\"id\":9,\"image\":\"vegetarien\",\"label\":\"Chèvre Miel\",\"mini\":2,\"name\":\"Chèvre Miel\",\"normal\":3.8,\"ticketLabel\":\"Chèvre Miel\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"chevre\"}],\"webDetail\":\"Fromage frais de chèvre, miel, poivre, roquette\"},{\"afficheDetail\":\"Fromage frais de chèvre, raisins secs, miel, crème balsamique, roquette\",\"canMerge\":true,\"code\":\"CHEVREMIELRAISIN\",\"codeTva\":\"1\",\"geant\":6,\"htmlKeyLabel\":\"Chèvre Miel<br>Raisin\",\"id\":10,\"image\":\"vegetarien\",\"label\":\"Chèvre Miel Raisin\",\"mini\":2,\"name\":\"Chèvre Miel Raisin\",\"normal\":4,\"ticketLabel\":\"Chèvre Miel Raisin\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"chevre\"}],\"webDetail\":\"Fromage frais de chèvre, raisins secs, miel, crème balsamique, roquette\"},{\"afficheDetail\":\"Fromage frais de chèvre, raisins secs, crème balsamique, roquette\",\"canMerge\":true,\"code\":\"CHEVRERAISIN\",\"codeTva\":\"1\",\"geant\":5.8,\"htmlKeyLabel\":\"Chèvre<br>Raisin b.\",\"id\":11,\"image\":\"vegetarien\",\"label\":\"Chèvre Raisin Balsamique\",\"mini\":2,\"name\":\"Chèvre Raisin Balsamique\",\"normal\":3.8,\"ticketLabel\":\"Chèvre Raisin Balsamique\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"chevre\"}],\"webDetail\":\"Fromage frais de chèvre, raisins secs, crème balsamique, roquette\"},{\"afficheDetail\":\"Fromage frais de chèvre, tomatade, olives, poivre, huile d'olive\",\"canMerge\":true,\"code\":\"CHEVRESUD\",\"codeTva\":\"1\",\"geant\":6.2,\"htmlKeyLabel\":\"Chèvre<br>Sud\",\"id\":12,\"image\":\"vegetarien\",\"label\":\"Chèvre Sud\",\"mini\":2,\"name\":\"Chèvre Sud\",\"normal\":4.2,\"ticketLabel\":\"Chèvre Sud\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"chevre\"}],\"webDetail\":\"Fromage frais de chèvre, tomatade, olives, poivre, huile d'olive\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"CHOCCHTHE\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Hotcemel<br>The\",\"id\":13,\"image\":\"null\",\"label\":\"Chocolat Chaud The\",\"name\":\"Chocolat Chaud The\",\"normal\":2,\"ticketLabel\":\"Chocolat Chaud The\",\"categoryTags\":[{\"tag\":\"boisson\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"CHORIZO\",\"codeTva\":\"1\",\"geant\":4.8,\"htmlKeyLabel\":\"Chorizo\",\"id\":14,\"image\":\"null\",\"label\":\"Chorizo\",\"mini\":2,\"name\":\"Chorizo\",\"normal\":3.5,\"ticketLabel\":\"Chorizo\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"Boulette traiteur froide tranchée, œufs, tomates, cornichons, sauce au choix\",\"canMerge\":true,\"code\":\"CLUBBOULETTE\",\"codeTva\":\"1\",\"geant\":6,\"htmlKeyLabel\":\"Club<br>boulette\",\"id\":15,\"image\":\"null\",\"label\":\"Club Boulette\",\"mini\":2,\"name\":\"Club Boulette\",\"normal\":4,\"ticketLabel\":\"Club Boulette\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"}],\"webDetail\":\"Boulette traiteur froide tranchée, œufs, tomates, cornichons, sauce au choix\"},{\"afficheDetail\":\"Filet de poulet en tranches, fromage, œufs, tomates, cornichons, salade\",\"canMerge\":true,\"code\":\"CLUBDAGO\",\"codeTva\":\"1\",\"geant\":5.8,\"htmlKeyLabel\":\"Club<br>dago\",\"id\":16,\"image\":\"epice\",\"label\":\"Club Dago\",\"mini\":2,\"name\":\"Club Dago\",\"normal\":4,\"ticketLabel\":\"Club Dago\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"poulet\"}],\"webDetail\":\"Filet de poulet en tranches, fromage, œufs, tomates, cornichons, salade\"},{\"afficheDetail\":\"Poulet grillé, ananas, tomates, salade, sauce cocktail maison\",\"canMerge\":true,\"code\":\"CLUBEXOTIQUE\",\"codeTva\":\"1\",\"geant\":6,\"htmlKeyLabel\":\"Club<br>Exotique\",\"id\":17,\"image\":\"null\",\"label\":\"Club Exotique\",\"mini\":2,\"name\":\"Club Exotique\",\"normal\":4,\"ticketLabel\":\"Club Exotique\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"poulet\"}],\"webDetail\":\"Poulet grillé, ananas, tomates, salade, sauce cocktail maison\"},{\"afficheDetail\":\"Feta (fromage de brebis), tomates, cocombres, olives noires, origan, sel, poivre, huile d’olive\",\"canMerge\":true,\"code\":\"CLUBFETA\",\"codeTva\":\"1\",\"geant\":6.5,\"htmlKeyLabel\":\"Club Feta\",\"id\":18,\"image\":\"null\",\"label\":\"Club Feta\",\"mini\":2,\"name\":\"Club Feta\",\"normal\":4.5,\"ticketLabel\":\"Club Feta\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"sud\"}],\"webDetail\":\"Feta (fromage de brebis), tomates, cocombres, olives noires, origan, sel, poivre, huile d’olive\"},{\"afficheDetail\":\"Poulet grillé, tomatade, huile d'olive, copeaux de parmesan, roquette\",\"canMerge\":true,\"code\":\"CLUBITALIENNE\",\"codeTva\":\"1\",\"geant\":6.2,\"htmlKeyLabel\":\"Club<br>Italien\",\"id\":19,\"image\":\"null\",\"label\":\"Club à l'Italienne\",\"mini\":2,\"name\":\"Club à l'Italienne\",\"normal\":4.2,\"ticketLabel\":\"Club à l'Italienne\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"poulet\"}],\"webDetail\":\"Poulet grillé, tomatade, huile d'olive, copeaux de parmesan, roquette\"},{\"afficheDetail\":\"Jambon de Parme, mozzarella fraîche, tomates, poivre, sel, origan, huile d'olive, vinaigre balsamique, roquette\",\"canMerge\":true,\"code\":\"CLUBMOZZA\",\"codeTva\":\"1\",\"geant\":6.2,\"htmlKeyLabel\":\"Club Mozza\",\"id\":20,\"image\":\"null\",\"label\":\"Club Mozza\",\"mini\":2,\"name\":\"Club Mozza\",\"normal\":4.2,\"ticketLabel\":\"Club Mozza\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"sud\"}],\"webDetail\":\"Jambon de Parme, mozzarella fraîche, tomates, poivre, sel, origan, huile d'olive, vinaigre balsamique, roquette\"},{\"afficheDetail\":\"Thon naturel, huile d'olive, ail, poivre, câpres, anchois, olives noires, tomates\",\"canMerge\":true,\"code\":\"CLUBNICOIS\",\"codeTva\":\"1\",\"geant\":6.2,\"htmlKeyLabel\":\"Club<br>Niçois\",\"id\":21,\"image\":\"null\",\"label\":\"Club Niçois\",\"mini\":2,\"name\":\"Club Niçois\",\"normal\":4.2,\"ticketLabel\":\"Club Niçois\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"sud\"}],\"webDetail\":\"Thon naturel, huile d'olive, ail, poivre, câpres, anchois, olives noires, tomates\"},{\"afficheDetail\":\"Sauce au choix : poivre, andalouse, …\",\"canMerge\":true,\"code\":\"CLUBPOULETGRILLE\",\"codeTva\":\"1\",\"geant\":6,\"htmlKeyLabel\":\"C.Poulet<br>Grillé\",\"id\":22,\"image\":\"null\",\"label\":\"Club Poulet Grillé\",\"mini\":2,\"name\":\"Club Poulet Grillé\",\"normal\":4,\"ticketLabel\":\"Club Poulet Grillé\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"poulet\"}],\"webDetail\":\"Sauce au choix : poivre, andalouse, …\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"CRABE\",\"codeTva\":\"1\",\"geant\":4.5,\"htmlKeyLabel\":\"Crabe\",\"id\":23,\"image\":\"null\",\"label\":\"Crabe\",\"mini\":1.7,\"name\":\"Crabe\",\"normal\":3,\"ticketLabel\":\"Crabe\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"mer\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"CRABEEXOTIQUE\",\"codeTva\":\"1\",\"geant\":4.5,\"htmlKeyLabel\":\"Crabe exotique\",\"id\":24,\"image\":\"null\",\"label\":\"Crabe Exotique\",\"mini\":1.7,\"name\":\"Crabe Exotique\",\"normal\":3,\"ticketLabel\":\"Crabe Exotique\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"mer\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"CREVETCOCK\",\"codeTva\":\"1\",\"geant\":5.2,\"htmlKeyLabel\":\"Crevettes<br>cocktail\",\"id\":25,\"image\":\"null\",\"label\":\"Crevettes Cocktail\",\"mini\":1.7,\"name\":\"Crevettes Cocktail\",\"normal\":3.5,\"ticketLabel\":\"Crevettes Cocktail\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"mer\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"CREVETDIAB\",\"codeTva\":\"1\",\"geant\":5.2,\"htmlKeyLabel\":\"Crevettes<br>diable\",\"id\":26,\"image\":\"epice\",\"label\":\"Crevettes Diable\",\"mini\":1.7,\"name\":\"Crevettes Diable\",\"normal\":3.5,\"ticketLabel\":\"Crevettes Diable\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"mer\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"( ou prix du jour) Crevettes grises fraîches préparées maison, œufs, tomates, salade\",\"canMerge\":true,\"code\":\"CREVETGRISE\",\"codeTva\":\"1\",\"geant\":7,\"htmlKeyLabel\":\"Crevettes<br>Grises\",\"id\":27,\"image\":\"null\",\"label\":\"Crevettes Grises\",\"mini\":2,\"name\":\"Crevettes Grises\",\"normal\":5,\"ticketLabel\":\"Crevettes Grises\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"mer\"}],\"webDetail\":\"Crevettes grises fraîches préparées maison, œufs, tomates, salade\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"CREVETROSE\",\"codeTva\":\"1\",\"geant\":5.2,\"htmlKeyLabel\":\"Crevettes<br>roses\",\"id\":28,\"image\":\"null\",\"label\":\"Crevettes Roses\",\"mini\":1.7,\"name\":\"Crevettes Roses\",\"normal\":3.5,\"ticketLabel\":\"Crevettes Roses\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"mer\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"CROISSANT\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Croissant\",\"id\":29,\"image\":\"null\",\"label\":\"Croissant\",\"name\":\"Croissant\",\"normal\":1.2,\"ticketLabel\":\"Croissant\",\"categoryTags\":[{\"tag\":\"viennoiserie\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"Poulet chaud servi croustillant dans un sandwich, sauces et crudités au choix \",\"canMerge\":true,\"code\":\"CROQUANT\",\"codeTva\":\"1.0\",\"geant\":6.5,\"htmlKeyLabel\":\"Croquant\",\"id\":30,\"image\":\"null\",\"label\":\"Croquant\",\"mini\":2,\"name\":\"Croquant\",\"normal\":4.5,\"ticketLabel\":\"Croquant\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"}],\"webDetail\":\"Poulet chaud servi croustillant dans un sandwich, sauces et crudités au choix \"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"CUPVINAIG\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Cups<br>Vinaigrette\",\"id\":31,\"image\":\"null\",\"label\":\"Cups Vinaigrette\",\"name\":\"Cups Vinaigrette\",\"normal\":0.5,\"ticketLabel\":\"Cups Vinaigrette\",\"categoryTags\":[{\"tag\":\"autre\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"Jambon, fromage, mayo, œufs, tomates, salade, cornichons\",\"canMerge\":true,\"code\":\"DAGO\",\"codeTva\":\"1\",\"geant\":4.5,\"htmlKeyLabel\":\"Dago\",\"id\":32,\"image\":\"null\",\"label\":\"Dagobert\",\"mini\":1.7,\"name\":\"Dagobert\",\"normal\":3,\"ticketLabel\":\"Dagobert\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"Jambon, fromage, mayo, œufs, tomates, salade, cornichons\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"DAGOFUME\",\"codeTva\":\"1\",\"geant\":6,\"htmlKeyLabel\":\"Dago<br>Fumé\",\"id\":33,\"image\":\"null\",\"label\":\"Dago Fumé\",\"mini\":2,\"name\":\"Dago Fumé\",\"normal\":4,\"ticketLabel\":\"Dago Fumé\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"Avec Jambon d’ardenne\"},{\"afficheDetail\":\"Avec Jambon d’ardenne et sauce piquante maison\",\"canMerge\":true,\"code\":\"DAGOFUMEPIQUANT\",\"codeTva\":\"1\",\"geant\":6.2,\"htmlKeyLabel\":\"Dago<br>FuméPQT\",\"id\":34,\"image\":\"epice\",\"label\":\"Dago Fumé Piquant\",\"mini\":2,\"name\":\"Dago Fumé Piquant\",\"normal\":4.2,\"ticketLabel\":\"Dago Fumé Piquant\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"Avec Jambon d’ardenne et sauce piquante maison\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"DAGOPIQUANT\",\"codeTva\":\"1\",\"geant\":4.8,\"htmlKeyLabel\":\"Dago<br>piquant\",\"id\":35,\"image\":\"epice\",\"label\":\"Dago piquant\",\"mini\":2,\"name\":\"Dago piquant\",\"normal\":3.3,\"ticketLabel\":\"Dago piquant\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"Avec sauce piquante maison\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"EAUPLATE\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Eau\",\"id\":36,\"image\":\"null\",\"label\":\"Eau Plate\",\"name\":\"Eau Plate\",\"normal\":1.5,\"ticketLabel\":\"Eau Plate\",\"categoryTags\":[{\"tag\":\"boisson\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"EAUPTIL\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Bru\",\"id\":37,\"image\":\"null\",\"label\":\"Eau Pétillante\",\"name\":\"Eau Pétillante\",\"normal\":1.5,\"ticketLabel\":\"Eau Pétillante\",\"categoryTags\":[{\"tag\":\"boisson\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"EMMENTHAL\",\"codeTva\":\"1\",\"geant\":4.5,\"htmlKeyLabel\":\"Emmenthal\",\"id\":38,\"image\":\"null\",\"label\":\"Emmenthal\",\"mini\":1.7,\"name\":\"Emmenthal\",\"normal\":3.5,\"ticketLabel\":\"Emmenthal\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"Filet de poulet en tranches, sauce piquante maison, œufs, tomates, cornichons, carottes\",\"canMerge\":true,\"code\":\"FILETPOULET\",\"codeTva\":\"1\",\"geant\":5.8,\"htmlKeyLabel\":\"Filet<br>poulet\",\"id\":39,\"image\":\"epice\",\"label\":\"Filet Poulet\",\"mini\":2,\"name\":\"Filet Poulet\",\"normal\":4,\"ticketLabel\":\"Filet Poulet\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"poulet\"}],\"webDetail\":\"Filet de poulet en tranches, sauce piquante maison, œufs, tomates, cornichons, carottes\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"FROMAGE\",\"codeTva\":\"1\",\"geant\":4.5,\"htmlKeyLabel\":\"Fromage\",\"id\":40,\"image\":\"vegetarien\",\"label\":\"Fromage (Gouda)\",\"mini\":1.7,\"name\":\"Fromage (Gouda)\",\"normal\":3,\"ticketLabel\":\"Fromage (Gouda)\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"GARCAPRES\",\"codeTva\":\"1.0\",\"geant\":0.8,\"htmlKeyLabel\":\"Sup.<br>capres\",\"id\":41,\"image\":\"null\",\"label\":\"Sup. Câpres\",\"name\":\"Sup. Câpres\",\"normal\":0.4,\"ticketLabel\":\"Sup. Câpres\",\"categoryTags\":[{\"tag\":\"autre\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"GARCONCOMBRES\",\"codeTva\":\"1.0\",\"geant\":0.8,\"htmlKeyLabel\":\"Sup.<br>concombres\",\"id\":42,\"image\":\"null\",\"label\":\"Sup. Concombres\",\"name\":\"Sup. Concombres\",\"normal\":0.4,\"ticketLabel\":\"Sup. Concombres\",\"categoryTags\":[{\"tag\":\"autre\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"GARNITURE1\",\"codeTva\":\"1\",\"geant\":0.8,\"htmlKeyLabel\":\"Supplément\",\"id\":43,\"image\":\"null\",\"label\":\"Supplément 1\",\"name\":\"Supplément 1\",\"normal\":0.4,\"ticketLabel\":\"Supplément 1\",\"categoryTags\":[{\"tag\":\"autre\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"GARNITURE2\",\"codeTva\":\"1\",\"geant\":1,\"htmlKeyLabel\":\"Supplément\",\"id\":44,\"image\":\"null\",\"label\":\"Supplément 2\",\"name\":\"Supplément 2\",\"normal\":0.5,\"ticketLabel\":\"Supplément 2\",\"categoryTags\":[{\"tag\":\"autre\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"GARNITURE3\",\"codeTva\":\"1\",\"geant\":1.5,\"htmlKeyLabel\":\"Supplément\",\"id\":45,\"image\":\"null\",\"label\":\"Supplément 3\",\"name\":\"Supplément 3\",\"normal\":1,\"ticketLabel\":\"Supplément 3\",\"categoryTags\":[{\"tag\":\"autre\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"GARNITURE4\",\"codeTva\":\"1\",\"geant\":1.5,\"htmlKeyLabel\":\"Supplément\",\"id\":46,\"image\":\"null\",\"label\":\"Supplément 4\",\"name\":\"Supplément 4\",\"normal\":1,\"ticketLabel\":\"Supplément 4\",\"categoryTags\":[{\"tag\":\"autre\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"GAROIGNONH\",\"codeTva\":\"1\",\"geant\":0.8,\"htmlKeyLabel\":\"Sup.<br>oignon H.\",\"id\":47,\"image\":\"null\",\"label\":\"Sup. Oignon haché\",\"name\":\"Sup. Oignon haché\",\"normal\":0.4,\"ticketLabel\":\"Sup. Oignon haché\",\"categoryTags\":[{\"tag\":\"autre\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"GAROLIVES\",\"codeTva\":\"1\",\"geant\":1,\"htmlKeyLabel\":\"Sup.<br>olives\",\"id\":48,\"image\":\"null\",\"label\":\"Sup. Olives\",\"name\":\"Sup. Olives\",\"normal\":0.5,\"ticketLabel\":\"Sup. Olives\",\"categoryTags\":[{\"tag\":\"autre\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"GARPARMESAN\",\"codeTva\":\"1\",\"geant\":1.5,\"htmlKeyLabel\":\"Sup.<br>parmesan\",\"id\":49,\"image\":\"null\",\"label\":\"Sup. Parmesan\",\"name\":\"Sup. Parmesan\",\"normal\":1,\"ticketLabel\":\"Sup. Parmesan\",\"categoryTags\":[{\"tag\":\"autre\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"GARRAISIN\",\"codeTva\":\"1\",\"geant\":0.8,\"htmlKeyLabel\":\"Sup.<br>raisin\",\"id\":50,\"image\":\"null\",\"label\":\"Sup. Raisin\",\"name\":\"Sup. Raisin\",\"normal\":0.4,\"ticketLabel\":\"Sup. Raisin\",\"categoryTags\":[{\"tag\":\"autre\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"GARTARTARETOM\",\"codeTva\":\"1\",\"geant\":1,\"htmlKeyLabel\":\"Sup.<br>tartare tom.\",\"id\":51,\"image\":\"null\",\"label\":\"Sup. Tartare Tomates\",\"name\":\"Sup. Tartare Tomates\",\"normal\":0.5,\"ticketLabel\":\"Sup. Tartare Tomates\",\"categoryTags\":[{\"tag\":\"autre\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"GARTOMATES\",\"codeTva\":\"1\",\"geant\":0.8,\"htmlKeyLabel\":\"Sup.<br>tomates\",\"id\":52,\"image\":\"null\",\"label\":\"Sup. Tomates\",\"name\":\"Sup. Tomates\",\"normal\":0.4,\"ticketLabel\":\"Sup. Tomates\",\"categoryTags\":[{\"tag\":\"autre\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"GAUFRES\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Gaufres\",\"id\":53,\"image\":\"null\",\"label\":\"Gaufres\",\"name\":\"Gaufres\",\"normal\":1.5,\"ticketLabel\":\"Gaufres\",\"categoryTags\":[{\"tag\":\"viennoiserie\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"JAMBFROMAGE\",\"codeTva\":\"1\",\"geant\":4.5,\"htmlKeyLabel\":\"J/F\",\"id\":54,\"image\":\"null\",\"label\":\"Jambon Fromage\",\"mini\":1.7,\"name\":\"Jambon Fromage\",\"normal\":3,\"ticketLabel\":\"Jambon Fromage\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"JAMBON\",\"codeTva\":\"1\",\"geant\":4.5,\"htmlKeyLabel\":\"Jambon\",\"id\":55,\"image\":\"null\",\"label\":\"Jambon\",\"mini\":1.7,\"name\":\"Jambon\",\"normal\":3,\"ticketLabel\":\"Jambon\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"JAMBONFROMAGE\",\"codeTva\":\"1\",\"geant\":4.5,\"htmlKeyLabel\":\"J/F\",\"id\":56,\"image\":\"null\",\"label\":\"Jambon Fromage\",\"mini\":1.7,\"name\":\"Jambon Fromage\",\"normal\":3,\"ticketLabel\":\"Jambon Fromage\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"JAMBONFUME\",\"codeTva\":\"1\",\"geant\":4.8,\"htmlKeyLabel\":\"Jambon<br>Fumé\",\"id\":57,\"image\":\"null\",\"label\":\"Jambon Fumé\",\"mini\":2,\"name\":\"Jambon Fumé\",\"normal\":3.5,\"ticketLabel\":\"Jambon Fumé\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"JAMBONPOIR\",\"codeTva\":\"1\",\"geant\":4.5,\"htmlKeyLabel\":\"Jambon<br>poireau\",\"id\":58,\"image\":\"null\",\"label\":\"Salade Jambon poireau\",\"mini\":1.7,\"name\":\"Salade Jambon poireau\",\"normal\":3,\"ticketLabel\":\"Salade Jambon poireau\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"JUS\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Jus\",\"id\":59,\"image\":\"null\",\"label\":\"Jus\",\"name\":\"Jus\",\"normal\":2,\"ticketLabel\":\"Jus\",\"categoryTags\":[{\"tag\":\"boisson\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"KEBAB\",\"codeTva\":\"1\",\"geant\":4.8,\"htmlKeyLabel\":\"Kebab\",\"id\":60,\"image\":\"null\",\"label\":\"Kebab\",\"mini\":2,\"name\":\"Kebab\",\"normal\":3.5,\"ticketLabel\":\"Kebab\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"Dinde grillée, sauce à l’ail.\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"KEBABPIQUANT\",\"codeTva\":\"1\",\"geant\":4.8,\"htmlKeyLabel\":\"Kebab<br>Piquant\",\"id\":61,\"image\":\"epice\",\"label\":\"Kebab Piquant\",\"mini\":2,\"name\":\"Kebab Piquant\",\"normal\":3.5,\"ticketLabel\":\"Kebab Piquant\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"Dinde grillée, sauce à l’ail piquante Maison\"},{\"afficheDetail\":\"Jambon de Parme, tartare tomates, copeaux de parmesan, crème balsamique, roquette\",\"canMerge\":true,\"code\":\"LEPARMA1\",\"codeTva\":\"1\",\"geant\":6.2,\"htmlKeyLabel\":\"Parma 1\",\"id\":62,\"image\":\"null\",\"label\":\"Le Parma 1\",\"mini\":2,\"name\":\"Le Parma 1\",\"normal\":4.2,\"ticketLabel\":\"Le Parma 1\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"sud\"}],\"webDetail\":\"Jambon de Parme, tartare tomates, copeaux de parmesan, crème balsamique, roquette\"},{\"afficheDetail\":\"Jambon de Parme, tomatade, copeaux de parmesan, huile de truffes, roquette\",\"canMerge\":true,\"code\":\"LEPARMA2\",\"codeTva\":\"1\",\"geant\":6.2,\"htmlKeyLabel\":\"Parma 2\",\"id\":63,\"image\":\"null\",\"label\":\"Le Parma 2\",\"mini\":2,\"name\":\"Le Parma 2\",\"normal\":4.2,\"ticketLabel\":\"Le Parma 2\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"sud\"}],\"webDetail\":\"Jambon de Parme, tomatade, copeaux de parmesan, huile de truffes, roquette\"},{\"afficheDetail\":\"Jambon de Parme, tomatade, mozzarella fraîche, origan, huile d’olive, roquette\",\"canMerge\":true,\"code\":\"LEPARMA3\",\"codeTva\":\"1\",\"geant\":6.2,\"htmlKeyLabel\":\"Parma 3\",\"id\":64,\"image\":\"null\",\"label\":\"Le Parma 3\",\"mini\":2,\"name\":\"Le Parma 3\",\"normal\":4.2,\"ticketLabel\":\"Le Parma 3\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"sud\"}],\"webDetail\":\"Jambon de Parme, tomatade, mozzarella fraîche, origan, huile d’olive, roquette\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"MALIBU\",\"codeTva\":\"1\",\"geant\":4.5,\"htmlKeyLabel\":\"Malibu\",\"id\":65,\"image\":\"null\",\"label\":\"Crabe Malibu\",\"mini\":1.7,\"name\":\"Crabe Malibu\",\"normal\":3,\"ticketLabel\":\"Crabe Malibu\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"mer\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"Américain maison épicé, tabasco, moutarde, oignons hachés, cornichons, œufs, tomates\",\"canMerge\":true,\"code\":\"MARTINO\",\"codeTva\":\"1\",\"geant\":5.5,\"htmlKeyLabel\":\"Martino\",\"id\":66,\"image\":\"null\",\"label\":\"Martino\",\"mini\":2,\"name\":\"Martino\",\"normal\":3.5,\"ticketLabel\":\"Martino\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"}],\"webDetail\":\"Américain maison épicé, tabasco, moutarde, oignons hachés, cornichons, œufs, tomates\"},{\"afficheDetail\":\"Tartare de tomates, huile d'olive, ail, poivre, câpres, anchois, olives noires, copeaux de parmesan, roquette\",\"canMerge\":true,\"code\":\"MEDITERRANEEN\",\"codeTva\":\"1\",\"geant\":6,\"htmlKeyLabel\":\"Méditerranéen\",\"id\":67,\"image\":\"null\",\"label\":\"Méditerranéen\",\"mini\":2,\"name\":\"Méditerranéen\",\"normal\":4,\"ticketLabel\":\"Méditerranéen\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"sud\"}],\"webDetail\":\"Tartare de tomates, huile d'olive, ail, poivre, câpres, anchois, olives noires, copeaux de parmesan, roquette\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"MOUSCHOCOLAT\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Mousse<br>chocolat\",\"id\":68,\"image\":\"null\",\"label\":\"Mousse Chocolat\",\"name\":\"Mousse Chocolat\",\"normal\":1.8,\"ticketLabel\":\"Mousse Chocolat\",\"categoryTags\":[{\"tag\":\"dessert\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"Jambon de Parme, tartare de tomates, copeaux de parmesan, câpres, olives noires, anchois, huile d'olive, ail, poivre, roquette\",\"canMerge\":true,\"code\":\"NAPOLI\",\"codeTva\":\"1\",\"geant\":6.5,\"htmlKeyLabel\":\"Napoli\",\"id\":69,\"image\":\"null\",\"label\":\"Napoli\",\"mini\":2,\"name\":\"Napoli\",\"normal\":4.5,\"ticketLabel\":\"Napoli\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"sud\"}],\"webDetail\":\"Jambon de Parme, tartare de tomates, copeaux de parmesan, câpres, olives noires, anchois, huile d'olive, ail, poivre, roquette\"},{\"afficheDetail\":\"Saumon fumé, fromage frais, poivre, aneth, oignons hachés\",\"canMerge\":true,\"code\":\"NORDIQUE\",\"codeTva\":\"1\",\"geant\":6.5,\"htmlKeyLabel\":\"Nordique\",\"id\":70,\"image\":\"null\",\"label\":\"Nordique\",\"mini\":2,\"name\":\"Nordique\",\"normal\":4.5,\"ticketLabel\":\"Nordique\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"mer\"}],\"webDetail\":\"Saumon fumé, fromage frais, poivre, aneth, oignons hachés\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"NUTELLA\",\"codeTva\":\"1\",\"geant\":4.4,\"htmlKeyLabel\":\"Nutella\",\"id\":71,\"image\":\"null\",\"label\":\"Nutella\",\"mini\":1.7,\"name\":\"Nutella\",\"normal\":3,\"ticketLabel\":\"Nutella\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"Omelette, lardons fumés, oignons hachés, sel, poivre, œufs cuits durs, tomates\",\"canMerge\":true,\"code\":\"OMELETTE\",\"codeTva\":\"1\",\"geant\":6,\"htmlKeyLabel\":\"Omelette<br>lardons\",\"id\":72,\"image\":\"null\",\"label\":\"Omelette Lardons\",\"mini\":2,\"name\":\"Omelette Lardons\",\"normal\":4,\"ticketLabel\":\"Omelette Lardons\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"}],\"webDetail\":\"Omelette, lardons fumés, oignons hachés, sel, poivre, œufs cuits durs, tomates\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"PAINCHOC\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Pain<br>chocolat\",\"id\":73,\"image\":\"null\",\"label\":\"Pain Chocolat\",\"name\":\"Pain Chocolat\",\"normal\":1.2,\"ticketLabel\":\"Pain Chocolat\",\"categoryTags\":[{\"tag\":\"viennoiserie\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"Tomate, mozzarella, pecorino, parmesan, emmenthal, huile d'olive\",\"canMerge\":true,\"code\":\"PAN4FROM\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"4 fromages\",\"id\":74,\"image\":\"null\",\"label\":\"Panini 4 fromages\",\"name\":\"Panini 4 fromages\",\"normal\":4,\"ticketLabel\":\"Panini 4 fromages\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Tomate, mozzarella, pecorino, parmesan, emmenthal, huile d'olive\"},{\"afficheDetail\":\"Sauce bolognaise, emmenthal\",\"canMerge\":true,\"code\":\"PANBOLO\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Bolo\",\"id\":75,\"image\":\"null\",\"label\":\"Panini Bolognaise\",\"name\":\"Panini Bolognaise\",\"normal\":4,\"ticketLabel\":\"Panini Bolognaise\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Sauce bolognaise, emmenthal\"},{\"afficheDetail\":\"Sauce bolognaise, emmenthal, piments\",\"canMerge\":true,\"code\":\"PANBOLOPIQ\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Bolo<br>piquant\",\"id\":76,\"image\":\"null\",\"label\":\"Panini Bolo Piquant\",\"name\":\"Panini Bolo Piquant\",\"normal\":4,\"ticketLabel\":\"Panini Bolo Piquant\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Sauce bolognaise, emmenthal, piments\"},{\"afficheDetail\":\"Viande hachée pur boeuf préparée maison légèrement piquante, emmenthal\",\"canMerge\":true,\"code\":\"PANCHEEZEBURG\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Cheese\",\"id\":77,\"image\":\"null\",\"label\":\"Panini Cheeze Burger\",\"name\":\"Panini Cheeze Burger\",\"normal\":4.8,\"ticketLabel\":\"Panini Cheeze Burger\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Viande hachée pur boeuf préparée maison légèrement piquante, emmenthal\"},{\"afficheDetail\":\"chèvre frais, bacon, huile de noix, poivre\",\"canMerge\":true,\"code\":\"PANCHEVREBACON\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"chèvre<br>bacon\",\"id\":78,\"image\":\"null\",\"label\":\"Panini Chèvre Bacon\",\"name\":\"Panini Chèvre Bacon\",\"normal\":4.8,\"ticketLabel\":\"Panini Chèvre Bacon\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"chèvre frais, bacon, huile de noix, poivre\"},{\"afficheDetail\":\"Panini chèvre miel\",\"canMerge\":true,\"code\":\"PANCHEVREMIEL\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"chèvre<br>miel\",\"id\":79,\"image\":\"vegetarien\",\"label\":\"Panini Chèvre Miel\",\"name\":\"Panini Chèvre Miel\",\"normal\":4.8,\"ticketLabel\":\"Panini Chèvre Miel\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Panini chèvre miel\"},{\"afficheDetail\":\"chèvre frais, raisins secs, crème balsamique\",\"canMerge\":true,\"code\":\"PANCHEVRERAISIN\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"chèvre<br>raisin\",\"id\":80,\"image\":\"vegetarien\",\"label\":\"Panini Chèvre Raisin\",\"name\":\"Panini Chèvre Raisin\",\"normal\":4.8,\"ticketLabel\":\"Panini Chèvre Raisin\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"chèvre frais, raisins secs, crème balsamique\"},{\"afficheDetail\":\"Poulet grillé, sauce aigre-douce piquante, ananas\",\"canMerge\":true,\"code\":\"PANCHINOIS\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"chinois\",\"id\":81,\"image\":\"epice\",\"label\":\"Panini Chinois\",\"name\":\"Panini Chinois\",\"normal\":4.8,\"ticketLabel\":\"Panini Chinois\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Poulet grillé, sauce aigre-douce piquante, ananas\"},{\"afficheDetail\":\"Tomate, mozzarella, chorizo piquant, épices, huile d'olive\",\"canMerge\":true,\"code\":\"PANCHORIZO\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"chorizo\",\"id\":82,\"image\":\"epice\",\"label\":\"Panini Chorizo\",\"name\":\"Panini Chorizo\",\"normal\":4.8,\"ticketLabel\":\"Panini Chorizo\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Tomate, mozzarella, chorizo piquant, épices, huile d'olive\"},{\"afficheDetail\":\"Tomate, mozzarella, poulet croquant\",\"canMerge\":true,\"code\":\"PANCROQUANT\",\"codeTva\":\"1.0\",\"htmlKeyLabel\":\"Croquant\",\"id\":83,\"image\":\"null\",\"label\":\"Panini Croquant\",\"name\":\"Panini Croquant\",\"normal\":5.5,\"ticketLabel\":\"Panini Croquant\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Tomate, mozzarella, poulet croquant\"},{\"afficheDetail\":\"Jambon, mozzarella, sel, poivre, origan, huile d'olive\",\"canMerge\":true,\"code\":\"PANCROQUE\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Croque\",\"id\":84,\"image\":\"null\",\"label\":\"Panini Croque\",\"name\":\"Panini Croque\",\"normal\":4,\"ticketLabel\":\"Panini Croque\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Jambon, mozzarella, sel, poivre, origan, huile d'olive\"},{\"afficheDetail\":\"Tomate, mozzarella, poulet, ananas, épices, huile d'olive\",\"canMerge\":true,\"code\":\"PANEXOTIQUE\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"exotique\",\"id\":85,\"image\":\"null\",\"label\":\"Panini Exotique\",\"name\":\"Panini Exotique\",\"normal\":4.8,\"ticketLabel\":\"Panini Exotique\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Tomate, mozzarella, poulet, ananas, épices, huile d'olive\"},{\"afficheDetail\":\"Jambon, mozzarella, ananas, sel, poivre, origan, huile d'olive\",\"canMerge\":true,\"code\":\"PANHAWAI\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Hawai\",\"id\":86,\"image\":\"null\",\"label\":\"Panini Hawai\",\"name\":\"Panini Hawai\",\"normal\":4,\"ticketLabel\":\"Panini Hawai\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Jambon, mozzarella, ananas, sel, poivre, origan, huile d'olive\"},{\"afficheDetail\":\"Sauce tomate, mozzarella, jambon italien, copeaux de parmesan, origan, huile d'olive\",\"canMerge\":true,\"code\":\"PANITALIEN\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"italien\",\"id\":87,\"image\":\"null\",\"label\":\"Panini Italien\",\"name\":\"Panini Italien\",\"normal\":4.8,\"ticketLabel\":\"Panini Italien\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Sauce tomate, mozzarella, jambon italien, copeaux de parmesan, origan, huile d'olive\"},{\"afficheDetail\":\"Tomate, mozzarella, jambon blanc, épices, huile d'olive\",\"canMerge\":true,\"code\":\"PANJAMBON\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"jambon\",\"id\":88,\"image\":\"null\",\"label\":\"Panini Jambon\",\"name\":\"Panini Jambon\",\"normal\":4.8,\"ticketLabel\":\"Panini Jambon\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Tomate, mozzarella, jambon blanc, épices, huile d'olive\"},{\"afficheDetail\":\"Jambon de Parme, tartare de tomates, copeaux de parmesan, câpres, olives noires, anchois, huile d’olive, ail, poivre, roquette</td>\",\"canMerge\":true,\"code\":\"PANNAPOLI\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"napoli\",\"id\":89,\"image\":\"null\",\"label\":\"Panini Napoli\",\"name\":\"Panini Napoli\",\"normal\":5.5,\"ticketLabel\":\"Panini Napoli\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Jambon de Parme, tartare de tomates, copeaux de parmesan, câpres, olives noires, anchois, huile d’olive, ail, poivre, roquette</td>\"},{\"afficheDetail\":\"Fromage frais, saumon fumé, aneth, poivre\",\"canMerge\":true,\"code\":\"PANNORDIQUE\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"nordique\",\"id\":90,\"image\":\"null\",\"label\":\"Panini Nordique\",\"name\":\"Panini Nordique\",\"normal\":5.5,\"ticketLabel\":\"Panini Nordique\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Fromage frais, saumon fumé, aneth, poivre\"},{\"afficheDetail\":\"Tomates fraîches, mozzarella fraîche, jambon italien, sel, poivre, origan, huile d'olive\",\"canMerge\":true,\"code\":\"PANPARMA\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"parma\",\"id\":91,\"image\":\"null\",\"label\":\"Panini Parma\",\"name\":\"Panini Parma\",\"normal\":5.2,\"ticketLabel\":\"Panini Parma\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Tomates fraîches, mozzarella fraîche, jambon italien, sel, poivre, origan, huile d'olive\"},{\"afficheDetail\":\"Jambon de Parme, tartare tomates, copeaux de parmesan, crème balsamique, roquette\",\"canMerge\":true,\"code\":\"PANPARMA1\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"parma1\",\"id\":92,\"image\":\"null\",\"label\":\"Panini Parma 1\",\"name\":\"Panini Parma 1\",\"normal\":5.5,\"ticketLabel\":\"Panini Parma 1\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Jambon de Parme, tartare tomates, copeaux de parmesan, crème balsamique, roquette\"},{\"afficheDetail\":\"Jambon de Parme, tomatade, copeaux de parmesan, huile de truffes, roquette\",\"canMerge\":true,\"code\":\"PANPARMA2\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"parma2\",\"id\":93,\"image\":\"null\",\"label\":\"Panini Parma 2\",\"name\":\"Panini Parma 2\",\"normal\":5.5,\"ticketLabel\":\"Panini Parma 2\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Jambon de Parme, tomatade, copeaux de parmesan, huile de truffes, roquette\"},{\"afficheDetail\":\"Jambon de Parme, tomatade, mozzarella fraîche, origan, huile d’olive, roquette\",\"canMerge\":true,\"code\":\"PANPARMA3\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"parma3\",\"id\":94,\"image\":\"null\",\"label\":\"Panini Parma 3\",\"name\":\"Panini Parma 3\",\"normal\":5.5,\"ticketLabel\":\"Panini Parma 3\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Jambon de Parme, tomatade, mozzarella fraîche, origan, huile d’olive, roquette\"},{\"afficheDetail\":\"Jambon de Parme, tartare de tomates, mozzarella fraîche, origan, huile d’olive, roquette\",\"canMerge\":true,\"code\":\"PANPARMA4\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"parma4\",\"id\":95,\"image\":\"null\",\"label\":\"Panini Parma 4\",\"name\":\"Panini Parma 4\",\"normal\":5.5,\"ticketLabel\":\"Panini Parma 4\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Jambon de Parme, tartare de tomates, mozzarella fraîche, origan, huile d’olive, roquette\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"PANPHILAPARME\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Philaparem\",\"id\":96,\"image\":\"null\",\"label\":\"Panini Philaparem\",\"name\":\"Panini Philaparem\",\"normal\":4.5,\"ticketLabel\":\"Panini Philaparem\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"Tomate, mozzarella, poulet grillé, épices, huile d'olive\",\"canMerge\":true,\"code\":\"PANPOULET\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"poulet\",\"id\":97,\"image\":\"null\",\"label\":\"Panini Poulet\",\"name\":\"Panini Poulet\",\"normal\":4.8,\"ticketLabel\":\"Panini Poulet\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Tomate, mozzarella, poulet grillé, épices, huile d'olive\"},{\"afficheDetail\":\"Tomate, mozzarella, ail, huile d'olive, olives noires\",\"canMerge\":true,\"code\":\"PANROMANA\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Romana\",\"id\":98,\"image\":\"null\",\"label\":\"Panini Romana\",\"name\":\"Panini Romana\",\"normal\":4,\"ticketLabel\":\"Panini Romana\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Tomate, mozzarella, ail, huile d'olive, olives noires\"},{\"afficheDetail\":\"tomate, mozzarella, thon naturel, olives noires, épices, huile d'olive\",\"canMerge\":true,\"code\":\"PANTHON\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"thon\",\"id\":99,\"image\":\"null\",\"label\":\"Panini Thon\",\"name\":\"Panini Thon\",\"normal\":4.8,\"ticketLabel\":\"Panini Thon\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"tomate, mozzarella, thon naturel, olives noires, épices, huile d'olive\"},{\"afficheDetail\":\"Tomates fraîches, mozzarella fraîche, sel, poivre, origan, vinaigre balsamique, huile d'olive\",\"canMerge\":true,\"code\":\"PANTOMATEMOZZA\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"tomatte<br>mozza\",\"id\":100,\"image\":\"vegetarien\",\"label\":\"Panini Tomatte Mozza.\",\"name\":\"Panini Tomatte Mozza.\",\"normal\":4.5,\"ticketLabel\":\"Panini Tomatte Mozza.\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Tomates fraîches, mozzarella fraîche, sel, poivre, origan, vinaigre balsamique, huile d'olive\"},{\"afficheDetail\":\"Tomates séchées, copeaux de parmesan, huile d’olive, roquette, crudités.\",\"canMerge\":true,\"code\":\"PANVEGETARIEN\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Panini<br>Végé\",\"id\":101,\"image\":\"null\",\"label\":\"Panini Végétarien\",\"name\":\"Panini Végétarien\",\"normal\":4.5,\"ticketLabel\":\"Panini Végétarien\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Tomates séchées, copeaux de parmesan, huile d’olive, roquette, crudités.\"},{\"afficheDetail\":\"Jambon de Parme, tartare de tomates, mozzarella fraîche, origan, huile d’olive, roquette\",\"canMerge\":true,\"code\":\"PARMA4\",\"codeTva\":\"1\",\"geant\":6.2,\"htmlKeyLabel\":\"Parma 4\",\"id\":102,\"image\":\"null\",\"label\":\"Parma 4\",\"mini\":2,\"name\":\"Parma 4\",\"normal\":4.2,\"ticketLabel\":\"Parma 4\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"sud\"}],\"webDetail\":\"Jambon de Parme, tartare de tomates, mozzarella fraîche, origan, huile d’olive, roquette\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"PATECREME\",\"codeTva\":\"1\",\"geant\":4.5,\"htmlKeyLabel\":\"Paté<br>Crême\",\"id\":103,\"image\":\"null\",\"label\":\"Paté Crême\",\"mini\":1.7,\"name\":\"Paté Crême\",\"normal\":3.3,\"ticketLabel\":\"Paté Crême\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"Poulet grillé, sauce Caesar, copeaux de parmesan, roquette\",\"canMerge\":true,\"code\":\"PCAESAR\",\"codeTva\":\"1\",\"geant\":6.2,\"htmlKeyLabel\":\"Poulet<br>Caesar\",\"id\":104,\"image\":\"null\",\"label\":\"Poulet Caesar\",\"mini\":2,\"name\":\"Poulet Caesar\",\"normal\":4.2,\"ticketLabel\":\"Poulet Caesar\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"poulet\"}],\"webDetail\":\"Poulet grillé, sauce Caesar, copeaux de parmesan, roquette\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"PHILADELPHIA\",\"codeTva\":\"1\",\"geant\":4.8,\"htmlKeyLabel\":\"Philadelphia\",\"id\":105,\"image\":\"vegetarien\",\"label\":\"Philadelphia\",\"mini\":2,\"name\":\"Philadelphia\",\"normal\":3.3,\"ticketLabel\":\"Philadelphia\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"Jambon de Parme, philadelphia, tomates fraîches, sel, poivre, huile d'olive\",\"canMerge\":true,\"code\":\"PHILAPARME\",\"codeTva\":\"1\",\"geant\":6.2,\"htmlKeyLabel\":\"Phila<br>parme\",\"id\":106,\"image\":\"null\",\"label\":\"Philaparme\",\"mini\":2,\"name\":\"Philaparme\",\"normal\":4.2,\"ticketLabel\":\"Philaparme\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Jambon de Parme, philadelphia, tomates fraîches, sel, poivre, huile d'olive\"},{\"afficheDetail\":\"Jambon de Parme, philadelphia, tartare de tomates, sel, poivre, huile d'olive\",\"canMerge\":true,\"code\":\"PHILAPARME2\",\"codeTva\":\"1\",\"geant\":6.5,\"htmlKeyLabel\":\"Phila<br>parme2\",\"id\":107,\"image\":\"null\",\"label\":\"Philaparme 2\",\"mini\":2,\"name\":\"Philaparme 2\",\"normal\":4.5,\"ticketLabel\":\"Philaparme 2\",\"categoryTags\":[{\"tag\":\"panini\"}],\"webDetail\":\"Jambon de Parme, philadelphia, tartare de tomates, sel, poivre, huile d'olive\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"PIPGAUM\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Saucisses\",\"id\":108,\"image\":\"null\",\"label\":\"Pipes Gaumaises\",\"name\":\"Pipes Gaumaises\",\"normal\":1.6,\"ticketLabel\":\"Pipes Gaumaises\",\"categoryTags\":[{\"tag\":\"salaison\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"PITA\",\"codeTva\":\"1\",\"geant\":4.8,\"htmlKeyLabel\":\"Pita\",\"id\":109,\"image\":\"epice\",\"label\":\"Pita\",\"mini\":2,\"name\":\"Pita\",\"normal\":3.5,\"ticketLabel\":\"Pita\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"PITAPIQUANT\",\"codeTva\":\"1\",\"geant\":4.8,\"htmlKeyLabel\":\"Pita<br>Piquant\",\"id\":110,\"image\":\"null\",\"label\":\"Pita Piquant\",\"mini\":2,\"name\":\"Pita Piquant\",\"normal\":3.5,\"ticketLabel\":\"Pita Piquant\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"POULETANDALOU\",\"codeTva\":\"1\",\"geant\":4.6,\"htmlKeyLabel\":\"Poulet<br>Andalou\",\"id\":111,\"image\":\"null\",\"label\":\"Poulet Andalou\",\"mini\":1.7,\"name\":\"Poulet Andalou\",\"normal\":3.2,\"ticketLabel\":\"Poulet Andalou\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"poulet\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"Morceaux de poulet chaud\",\"canMerge\":true,\"code\":\"POULETCHAUD\",\"codeTva\":\"1.0\",\"geant\":9,\"htmlKeyLabel\":\"Poulet<br>chaud\",\"id\":112,\"image\":\"null\",\"label\":\"Poulet Chaud\",\"mini\":2,\"name\":\"Poulet Chaud\",\"normal\":6.5,\"ticketLabel\":\"Poulet Chaud\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"poulet\"}],\"webDetail\":\"Morceaux de poulet chaud\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"POULETCURRY\",\"codeTva\":\"1\",\"geant\":4.6,\"htmlKeyLabel\":\"Curry\",\"id\":113,\"image\":\"null\",\"label\":\"Poulet Curry\",\"mini\":1.7,\"name\":\"Poulet Curry\",\"normal\":3.2,\"ticketLabel\":\"Poulet Curry\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"poulet\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"POULETHAWAI\",\"codeTva\":\"1\",\"geant\":4.6,\"htmlKeyLabel\":\"Hawaï\",\"id\":114,\"image\":\"null\",\"label\":\"Poulet Hawaï\",\"mini\":1.7,\"name\":\"Poulet Hawaï\",\"normal\":3.2,\"ticketLabel\":\"Poulet Hawaï\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"poulet\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"POULETMAYO\",\"codeTva\":\"1\",\"geant\":4.6,\"htmlKeyLabel\":\"Poulet<br>mayo\",\"id\":115,\"image\":\"null\",\"label\":\"Poulet Mayonnaise\",\"mini\":1.7,\"name\":\"Poulet Mayonnaise\",\"normal\":3.2,\"ticketLabel\":\"Poulet Mayonnaise\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"poulet\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"POULETMEXIC\",\"codeTva\":\"1\",\"geant\":4.6,\"htmlKeyLabel\":\"Mexico\",\"id\":116,\"image\":\"epice\",\"label\":\"Poulet Mexico\",\"mini\":1.7,\"name\":\"Poulet Mexico\",\"normal\":3.2,\"ticketLabel\":\"Poulet Mexico\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"poulet\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"POULETMOUT\",\"codeTva\":\"1\",\"geant\":4.5,\"htmlKeyLabel\":\"Poulet<br>Moutarde\",\"id\":117,\"image\":\"null\",\"label\":\"Poulet Moutarde\",\"mini\":1.7,\"name\":\"Poulet Moutarde\",\"normal\":3.2,\"ticketLabel\":\"Poulet Moutarde\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"poulet\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"POULETNAPO\",\"codeTva\":\"1\",\"geant\":4.5,\"htmlKeyLabel\":\"Napoli\",\"id\":118,\"image\":\"null\",\"label\":\"Poulet Napoli\",\"mini\":1.7,\"name\":\"Poulet Napoli\",\"normal\":3.2,\"ticketLabel\":\"Poulet Napoli\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"poulet\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"POULETPIQ\",\"codeTva\":\"1\",\"geant\":4.6,\"htmlKeyLabel\":\"Poulet<br>maison\",\"id\":119,\"image\":\"epiceDouble\",\"label\":\"Poulet Maison\",\"mini\":1.7,\"name\":\"Poulet Maison\",\"normal\":3.2,\"ticketLabel\":\"Poulet Maison\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"poulet\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"POULETPOIVRE\",\"codeTva\":\"1\",\"geant\":4.6,\"htmlKeyLabel\":\"Poulet<br>Poivre\",\"id\":120,\"image\":\"null\",\"label\":\"Poulet Poivre\",\"mini\":1.7,\"name\":\"Poulet Poivre\",\"normal\":3.2,\"ticketLabel\":\"Poulet Poivre\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"poulet\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"RAVIERPESER\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Ravier a<br>peser\",\"id\":121,\"image\":\"null\",\"label\":\"Ravier à Peser\",\"name\":\"Ravier à Peser\",\"normal\":2,\"ticketLabel\":\"Ravier à Peser\",\"categoryTags\":[{\"tag\":\"salade\"},{\"tag\":\"tartiner\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"RAVIERPESERSPEC\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Ravier p.<br>spécial\",\"id\":122,\"image\":\"null\",\"label\":\"Ravier à Peser Spécial\",\"name\":\"Ravier à Peser Spécial\",\"normal\":3,\"ticketLabel\":\"Ravier à Peser Spécial\",\"categoryTags\":[{\"tag\":\"salade\"},{\"tag\":\"tartiner\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"Tranche de Rosbif, sauce et crudités au choix\",\"canMerge\":true,\"code\":\"ROSBIF\",\"codeTva\":\"1\",\"geant\":6.5,\"htmlKeyLabel\":\"Rosbif\",\"id\":123,\"image\":\"null\",\"label\":\"Rosbif\",\"mini\":2,\"name\":\"Rosbif\",\"normal\":4.5,\"ticketLabel\":\"Rosbif\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"}],\"webDetail\":\"Rôti de boeuf en tranche, sauce et crudités au choix\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"ROSBIFMIELMOUTARDE\",\"codeTva\":\"1\",\"geant\":6.5,\"htmlKeyLabel\":\"Rosbif miel<br>Moutarde\",\"id\":124,\"image\":\"null\",\"label\":\"Rosbif Miel Moutarde\",\"mini\":2,\"name\":\"Rosbif Miel Moutarde\",\"normal\":4.5,\"ticketLabel\":\"Rosbif Miel Moutarde\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"Tranche de Rosbif, copeaux de parmesan, huile d'olive, sel, poivre, roquette\",\"canMerge\":true,\"code\":\"ROSBIFPARMESAN\",\"codeTva\":\"1\",\"geant\":6.5,\"htmlKeyLabel\":\"Rosbif<br>Parmesan\",\"id\":125,\"image\":\"null\",\"label\":\"Rosbif Parmesan\",\"mini\":2,\"name\":\"Rosbif Parmesan\",\"normal\":4.5,\"ticketLabel\":\"Rosbif Parmesan\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"}],\"webDetail\":\"Tranche de Rosbif, copeaux de parmesan, huile d'olive, sel, poivre, roquette\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"ROTIMOUTARDE\",\"codeTva\":\"1\",\"geant\":6,\"htmlKeyLabel\":\"Roti<br>moutarde\",\"id\":126,\"image\":\"null\",\"label\":\"Roti Moutarde\",\"mini\":2,\"name\":\"Roti Moutarde\",\"normal\":4,\"ticketLabel\":\"Roti Moutarde\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"SAL3FROM\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"3<br>fromages\",\"id\":127,\"image\":\"null\",\"label\":\"Salade 3 Fromages\",\"name\":\"Salade 3 Fromages\",\"normal\":4.6,\"ticketLabel\":\"Salade 3 Fromages\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"salade composée, carottes, chou blanc, tom, concombre\",\"canMerge\":true,\"code\":\"SAL7CRUDITES\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"7 crudités\",\"id\":128,\"image\":\"vegetarien\",\"label\":\"Salades 7 Crudités\",\"name\":\"Salades 7 Crudités\",\"normal\":4,\"ticketLabel\":\"Salades 7 Crudités\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"salade composée, carottes, chou blanc, tom, concombre\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"SALADVIAND\",\"codeTva\":\"1\",\"geant\":4.5,\"htmlKeyLabel\":\"Salade<br>viande\",\"id\":129,\"image\":\"null\",\"label\":\"Salade de Viande\",\"mini\":1.7,\"name\":\"Salade de Viande\",\"normal\":3,\"ticketLabel\":\"Salade de Viande\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"fromage frais de chèvre, petits lardons, crème balsamique, poivre, lit de salade\",\"canMerge\":true,\"code\":\"SALCHEVREBACON\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Chèvre Lardons\",\"id\":130,\"image\":\"null\",\"label\":\"Salade Chèvre Lardons\",\"name\":\"Salade Chèvre Lardons\",\"normal\":5.8,\"ticketLabel\":\"Salade Chèvre Lardons\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"fromage frais de chèvre, petits lardons, crème balsamique, poivre, lit de salade\"},{\"afficheDetail\":\"chèvre frais, raisins secs, miel, sal.comp, carottes, chou blanc, œufs, tomates, roquette\",\"canMerge\":true,\"code\":\"SALCHEVREMIEL\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"chèvre<br>miel\",\"id\":131,\"image\":\"vegetarien\",\"label\":\"Salade Chèvre Miel\",\"name\":\"Salade Chèvre Miel\",\"normal\":4.8,\"ticketLabel\":\"Salade Chèvre Miel\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"chèvre frais, raisins secs, miel, sal.comp, carottes, chou blanc, œufs, tomates, roquette\"},{\"afficheDetail\":\"chèvre frais, raisins secs, crème balsamique, sal.comp, carottes, chou blanc, œufs, tomates, roquette\",\"canMerge\":true,\"code\":\"SALCHEVRERAISIN\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"chèvre<br>raisin\",\"id\":132,\"image\":\"vegetarien\",\"label\":\"Salade Chèvre Raisin\",\"name\":\"Salade Chèvre Raisin\",\"normal\":4.8,\"ticketLabel\":\"Salade Chèvre Raisin\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"chèvre frais, raisins secs, crème balsamique, sal.comp, carottes, chou blanc, œufs, tomates, roquette\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"SALCHINOISE\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Chinoise\",\"id\":133,\"image\":\"null\",\"label\":\"Salade Chinoise\",\"name\":\"Salade Chinoise\",\"normal\":4.8,\"ticketLabel\":\"Salade Chinoise\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"jambon de Parme, mozzarella fraîche, poivre, origan, huile d’olive, lit de salade\",\"canMerge\":true,\"code\":\"SALCLUBMOZZA\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Club<br>mozza\",\"id\":134,\"image\":\"null\",\"label\":\"Salade Club Mozza\",\"name\":\"Salade Club Mozza\",\"normal\":5.8,\"ticketLabel\":\"Salade Club Mozza\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"jambon de Parme, mozzarella fraîche, poivre, origan, huile d’olive, lit de salade\"},{\"afficheDetail\":\"crevettes roses, maïs, sal.comp, carottes, chou blanc, œufs, tomates\",\"canMerge\":true,\"code\":\"SALCREVROSE\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"crevettes<br>roses\",\"id\":135,\"image\":\"null\",\"label\":\"Salade Crevettes Roses\",\"name\":\"Salade Crevettes Roses\",\"normal\":5.8,\"ticketLabel\":\"Salade Crevettes Roses\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"crevettes roses, maïs, sal.comp, carottes, chou blanc, œufs, tomates\"},{\"afficheDetail\":\"petits lardons, jambon braisé, emmenthal, sal.comp, carottes, chou blanc, œufs, tomates\",\"canMerge\":true,\"code\":\"SALFERMIERE\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Fermière\",\"id\":136,\"image\":\"null\",\"label\":\"Salade Fermière\",\"name\":\"Salade Fermière\",\"normal\":4.8,\"ticketLabel\":\"Salade Fermière\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"petits lardons, jambon braisé, emmenthal, sal.comp, carottes, chou blanc, œufs, tomates\"},{\"afficheDetail\":\"crabe, crevettes roses, thon au naturel ,sal.comp, carottes, chou blanc, œufs, tomates\",\"canMerge\":true,\"code\":\"SALGOURM\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Gourmande\",\"id\":137,\"image\":\"null\",\"label\":\"Salade Gourmande\",\"name\":\"Salade Gourmande\",\"normal\":5.4,\"ticketLabel\":\"Salade Gourmande\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"crabe, crevettes roses, thon au naturel ,sal.comp, carottes, chou blanc, œufs, tomates\"},{\"afficheDetail\":\"feta fromage de brebis, concombre, olives noires, origan, huile d’olive, sal.comp, carottes, chou blanc, œufs, tomates, épices\",\"canMerge\":true,\"code\":\"SALGREC\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Grecque\",\"id\":138,\"image\":\"vegetarien\",\"label\":\"Salade Grecque\",\"name\":\"Salade Grecque\",\"normal\":5.4,\"ticketLabel\":\"Salade Grecque\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"feta fromage de brebis, concombre, olives noires, origan, huile d’olive, sal.comp, carottes, chou blanc, œufs, tomates, épices\"},{\"afficheDetail\":\"Dés de poulet grillés, maïs, ananas, sal.comp, carottes, chou blanc, œufs, tomates\",\"canMerge\":true,\"code\":\"SALHAWAY\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Hawaienne\",\"id\":139,\"image\":\"null\",\"label\":\"Salade Hawaienne\",\"name\":\"Salade Hawaienne\",\"normal\":4.8,\"ticketLabel\":\"Salade Hawaienne\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"Dés de poulet grillés, maïs, ananas, sal.comp, carottes, chou blanc, œufs, tomates\"},{\"afficheDetail\":\"jambon de Parme, copeaux de parmesan, crème balsamique, roquette, huile d’olive, lit de salade\",\"canMerge\":true,\"code\":\"SALITALIENNE\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Italienne\",\"id\":140,\"image\":\"null\",\"label\":\"Salade Italienne\",\"name\":\"Salade Italienne\",\"normal\":5.8,\"ticketLabel\":\"Salade Italienne\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"jambon de Parme, copeaux de parmesan, crème balsamique, roquette, huile d’olive, lit de salade\"},{\"afficheDetail\":\"Dés de poulet grillés, maïs, sal.comp, carottes, chou blanc, œufs, tomates\",\"canMerge\":true,\"code\":\"SALLOUISIANE\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Louisiane\",\"id\":141,\"image\":\"null\",\"label\":\"Salade Louisiane\",\"name\":\"Salade Louisiane\",\"normal\":4.8,\"ticketLabel\":\"Salade Louisiane\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"Dés de poulet grillés, maïs, sal.comp, carottes, chou blanc, œufs, tomates\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"SALMEXICAINE\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Mexicaine\",\"id\":142,\"image\":\"null\",\"label\":\"Salade Mexicaine\",\"name\":\"Salade Mexicaine\",\"normal\":5.6,\"ticketLabel\":\"Salade Mexicaine\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"mozzarella fraîche, concombre, olives noires, origan, huile d’olive , sal.comp, carottes, chou blanc, œufs, tomates\",\"canMerge\":true,\"code\":\"SALMOZZA\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Mozzarella\",\"id\":143,\"image\":\"vegetarien\",\"label\":\"Salade Tomates Mozzarella\",\"name\":\"Salade Tomates Mozzarella\",\"normal\":4.8,\"ticketLabel\":\"Salade Tomates Mozzarella\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"mozzarella fraîche, concombre, olives noires, origan, huile d’olive , sal.comp, carottes, chou blanc, œufs, tomates\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"SALNICOISE\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Niçoise\",\"id\":144,\"image\":\"null\",\"label\":\"Salade Niçoise\",\"name\":\"Salade Niçoise\",\"normal\":5.4,\"ticketLabel\":\"Salade Niçoise\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"jambon de Parme, copeaux de parmesan, crème balsamique, roquette, huile d’olive, lit de salade\",\"canMerge\":true,\"code\":\"SALPARMA\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Parma 1\",\"id\":145,\"image\":\"null\",\"label\":\"Salade Parme 1\",\"name\":\"Salade Parme 1\",\"normal\":5.8,\"ticketLabel\":\"Salade Parme 1\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"jambon de Parme, copeaux de parmesan, crème balsamique, roquette, huile d’olive, lit de salade\"},{\"afficheDetail\":\"mélange de pâtes-jambon dans une sauce préparée maison, sal.comp, carottes, chou blanc, œufs, tomates\",\"canMerge\":true,\"code\":\"SALPATEJAMB\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Pates<br>jambon\",\"id\":146,\"image\":\"null\",\"label\":\"Salade Pâtes Jambon\",\"name\":\"Salade Pâtes Jambon\",\"normal\":4.8,\"ticketLabel\":\"Salade Pâtes Jambon\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"mélange de pâtes-jambon dans une sauce préparée maison, sal.comp, carottes, chou blanc, œufs, tomates\"},{\"afficheDetail\":\"mélange de pâtes, crabe et crevettes roses dans une sauce cocktail au crabe préparée maison, sal.comp, carottes, chou blanc, œufs, tomates\",\"canMerge\":true,\"code\":\"SALPATFRUITMER\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Pates<br>fr mer\",\"id\":147,\"image\":\"null\",\"label\":\"Salade Pâtes Fruits de Mer\",\"name\":\"Salade Pâtes Fruits de Mer\",\"normal\":4.8,\"ticketLabel\":\"Salade Pâtes Fruits de Mer\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"mélange de pâtes, crabe et crevettes roses dans une sauce cocktail au crabe préparée maison, sal.comp, carottes, chou blanc, œufs, tomates\"},{\"afficheDetail\":\"mélange de salade composée, œufs, tomates\",\"canMerge\":true,\"code\":\"SALPELEMELE\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Pêle<br>mêle\",\"id\":148,\"image\":\"vegetarien\",\"label\":\"Salade Pêle Mêle\",\"name\":\"Salade Pêle Mêle\",\"normal\":3.8,\"ticketLabel\":\"Salade Pêle Mêle\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"mélange de salade composée, œufs, tomates\"},{\"afficheDetail\":\"pommes de terre, lardons, sauce moutardée maison, sal.comp, carottes, chou blanc, œufs, tomates\",\"canMerge\":true,\"code\":\"SALPOMTERRE\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Pomme<br>de terre\",\"id\":149,\"image\":\"null\",\"label\":\"Salade Pommes de Terre\",\"name\":\"Salade Pommes de Terre\",\"normal\":4.4,\"ticketLabel\":\"Salade Pommes de Terre\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"pommes de terre, lardons, sauce moutardée maison, sal.comp, carottes, chou blanc, œufs, tomates\"},{\"afficheDetail\":\"maïs, cubes d’emmenthal, salade composée, carottes, chou blanc, œufs, tomates\",\"canMerge\":true,\"code\":\"SALPRINTANIERE\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Printanière\",\"id\":150,\"image\":\"vegetarien\",\"label\":\"Salade Printanière\",\"name\":\"Salade Printanière\",\"normal\":4.4,\"ticketLabel\":\"Salade Printanière\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"maïs, cubes d’emmenthal, salade composée, carottes, chou blanc, œufs, tomates\"},{\"afficheDetail\":\"saumon fumé, câpres, oignons frais, aneth, lit de salade\",\"canMerge\":true,\"code\":\"SALSAUMFUME\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Saumon<br>fumé\",\"id\":151,\"image\":\"null\",\"label\":\"Salade Saumon Fumé\",\"name\":\"Salade Saumon Fumé\",\"normal\":6.5,\"ticketLabel\":\"Salade Saumon Fumé\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"saumon fumé, câpres, oignons frais, aneth, lit de salade\"},{\"afficheDetail\":\"préparation maison de taboulé et raisins secs, sal.comp, carottes, chou blanc, œufs, tomates\",\"canMerge\":true,\"code\":\"SALTABOULE\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Taboulé\",\"id\":152,\"image\":\"vegetarien\",\"label\":\"Salade Taboulé\",\"name\":\"Salade Taboulé\",\"normal\":4.8,\"ticketLabel\":\"Salade Taboulé\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"préparation maison de taboulé et raisins secs, sal.comp, carottes, chou blanc, œufs, tomates\"},{\"afficheDetail\":\"préparation maison de taboulé aux légumes et raisins secs, dés de poulet grillés, lit de salade\",\"canMerge\":true,\"code\":\"SALTABOULEPOULET\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Taboulé<br>Poulet\",\"id\":153,\"image\":\"null\",\"label\":\"Salade Taboulé Poulet\",\"name\":\"Salade Taboulé Poulet\",\"normal\":5.8,\"ticketLabel\":\"Salade Taboulé Poulet\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"préparation maison de taboulé aux légumes et raisins secs, dés de poulet grillés, lit de salade\"},{\"afficheDetail\":\"thon au naturel, maïs, sal.comp, carottes, chou blanc, œufs, tomates\",\"canMerge\":true,\"code\":\"SALTHON\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"thon\",\"id\":154,\"image\":\"null\",\"label\":\"Salade Thon\",\"name\":\"Salade Thon\",\"normal\":5.4,\"ticketLabel\":\"Salade Thon\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"thon au naturel, maïs, sal.comp, carottes, chou blanc, œufs, tomates\"},{\"afficheDetail\":\"thon au naturel ou préparé, pêches, maïs, sal.comp, carottes, chou blanc, œufs, tomates\",\"canMerge\":true,\"code\":\"SALTHONPECHE\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Sal Thon<br>Pêche\",\"id\":155,\"image\":\"null\",\"label\":\"Salade Thon Pêche\",\"name\":\"Salade Thon Pêche\",\"normal\":5.4,\"ticketLabel\":\"Salade Thon Pêche\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"thon au naturel ou préparé, pêches, maïs, sal.comp, carottes, chou blanc, œufs, tomates\"},{\"afficheDetail\":\"thon piquant au naturel ou préparé, pêches, maïs, sal.comp, carottes, chou blanc, œufs, tomates\",\"canMerge\":true,\"code\":\"SALTHONPECHPIQ\",\"codeTva\":\"1.0\",\"htmlKeyLabel\":\"Sal Thon<br>Pêche Piq\",\"id\":156,\"image\":\"epice\",\"label\":\"Salade Pêche au Thon Piquant\",\"name\":\"Salade Pêche au Thon Piquant\",\"normal\":5.4,\"ticketLabel\":\"Salade Pêche au Thon Piquant\",\"categoryTags\":[{\"tag\":\"salade\"}],\"webDetail\":\"thon piquant au naturel ou préparé, pêches, maïs, sal.comp, carottes, chou blanc, œufs, tomates\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"SAUCISSON\",\"codeTva\":\"1\",\"geant\":4.8,\"htmlKeyLabel\":\"Saucisson\",\"id\":157,\"image\":\"null\",\"label\":\"Saucisson\",\"mini\":1.7,\"name\":\"Saucisson\",\"normal\":3.5,\"ticketLabel\":\"Saucisson\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"Saumon fumé en tranches, beurre, oignons hachés, câpres\",\"canMerge\":true,\"code\":\"SAUMONFUME\",\"codeTva\":\"1\",\"geant\":6.5,\"htmlKeyLabel\":\"Saumon<br>fumé\",\"id\":158,\"image\":\"null\",\"label\":\"Saumon Fumé\",\"mini\":2,\"name\":\"Saumon Fumé\",\"normal\":4.5,\"ticketLabel\":\"Saumon Fumé\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"mer\"}],\"webDetail\":\"Saumon fumé en tranches, beurre, oignons hachés, câpres\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"SCAMPIAIL\",\"codeTva\":\"1\",\"geant\":6.5,\"htmlKeyLabel\":\"Scampi<br>ail\",\"id\":159,\"image\":\"null\",\"label\":\"Scampi à l'Ail\",\"mini\":2,\"name\":\"Scampi à l'Ail\",\"normal\":4.5,\"ticketLabel\":\"Scampi à l'Ail\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"mer\"}],\"webDetail\":\"Grosses crevettes tigrées dans une délicieuse sauce à l'ail\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"SCAMPIPIQ\",\"codeTva\":\"1\",\"geant\":6.5,\"htmlKeyLabel\":\"Scampi<br>piquant\",\"id\":160,\"image\":\"epice\",\"label\":\"Scampi à l'Ail Piquant\",\"mini\":2,\"name\":\"Scampi à l'Ail Piquant\",\"normal\":4.5,\"ticketLabel\":\"Scampi à l'Ail Piquant\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"mer\"}],\"webDetail\":\"Grosses crevettes tigrées dans une délicieuse sauce à l'ail pimentée maison\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"SOFTBOUTEILLE\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"0.5l\",\"id\":161,\"image\":\"null\",\"label\":\"Soft Bouteille\",\"name\":\"Soft Bouteille\",\"normal\":2,\"ticketLabel\":\"Soft Bouteille\",\"categoryTags\":[{\"tag\":\"boisson\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"SOFTCANETTE\",\"codeTva\":\"1\",\"htmlKeyLabel\":\"Canette\",\"id\":162,\"image\":\"null\",\"label\":\"Soft Canette\",\"name\":\"Soft Canette\",\"normal\":1.5,\"ticketLabel\":\"Soft Canette\",\"categoryTags\":[{\"tag\":\"boisson\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"Tartare de boeuf, huiled'olive, moutarde, sel, poivre, tomates, copeaux de parmesan\",\"canMerge\":true,\"code\":\"TARTAREITALIEN\",\"codeTva\":\"1\",\"geant\":6.5,\"htmlKeyLabel\":\"Tartare<br>Italien\",\"id\":163,\"image\":\"null\",\"label\":\"Tartare Italien\",\"mini\":2,\"name\":\"Tartare Italien\",\"normal\":4.5,\"ticketLabel\":\"Tartare Italien\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"sud\"}],\"webDetail\":\"Tartare de boeuf, huiled'olive, moutarde, sel, poivre, tomates, copeaux de parmesan\"},{\"afficheDetail\":\"Tartare de boeuf, tartare de tomates, huile d'olive, sel, poivre, copeaux de parmesan\",\"canMerge\":true,\"code\":\"TARTAREITALIEN2\",\"codeTva\":\"1\",\"geant\":7,\"htmlKeyLabel\":\"Tartare<br>Italien 2\",\"id\":164,\"image\":\"null\",\"label\":\"Tartare Italien 2\",\"mini\":2,\"name\":\"Tartare Italien 2\",\"normal\":4.8,\"ticketLabel\":\"Tartare Italien 2\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"sud\"}],\"webDetail\":\"Tartare de boeuf, tartare de tomates, huile d'olive, sel, poivre, copeaux de parmesan\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"THONCOCK\",\"codeTva\":\"1\",\"geant\":4.6,\"htmlKeyLabel\":\"Thon<br>cocktail\",\"id\":165,\"image\":\"null\",\"label\":\"Thon Cocktail\",\"mini\":1.7,\"name\":\"Thon Cocktail\",\"normal\":3.3,\"ticketLabel\":\"Thon Cocktail\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"mer\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"THONCOCKPIQ\",\"codeTva\":\"1\",\"geant\":4.6,\"htmlKeyLabel\":\"Thon cock<br>piquant\",\"id\":166,\"image\":\"null\",\"label\":\"Thon Cocktail Piquant\",\"mini\":1.7,\"name\":\"Thon Cocktail Piquant\",\"normal\":3.3,\"ticketLabel\":\"Thon Cocktail Piquant\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"mer\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"THONMAYO\",\"codeTva\":\"1\",\"geant\":4.6,\"htmlKeyLabel\":\"Thon\",\"id\":167,\"image\":\"null\",\"label\":\"Thon Mayo\",\"mini\":1.7,\"name\":\"Thon Mayo\",\"normal\":3.3,\"ticketLabel\":\"Thon Mayo\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"mer\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"THONPIQ\",\"codeTva\":\"1\",\"geant\":4.6,\"htmlKeyLabel\":\"Thon<br>piquant\",\"id\":168,\"image\":\"epice\",\"label\":\"Thon Piquant\",\"mini\":1.7,\"name\":\"Thon Piquant\",\"normal\":3.3,\"ticketLabel\":\"Thon Piquant\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"mer\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"null\",\"canMerge\":true,\"code\":\"THONPORT\",\"codeTva\":\"1\",\"geant\":4.5,\"htmlKeyLabel\":\"Thon<br>Portugais\",\"id\":169,\"image\":\"null\",\"label\":\"Thon Portugais\",\"mini\":1.7,\"name\":\"Thon Portugais\",\"normal\":3,\"ticketLabel\":\"Thon Portugais\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"null\"},{\"afficheDetail\":\"Tomates, mozzarella fraîche, sel, poivre, origan, huile d'olive, vinaigre balsamique\",\"canMerge\":true,\"code\":\"TOMATMOZZA\",\"codeTva\":\"1\",\"geant\":5.8,\"htmlKeyLabel\":\"Tomates<br>mozza1\",\"id\":170,\"image\":\"vegetarien\",\"label\":\"Tomates Mozzarella\",\"mini\":2,\"name\":\"Tomates Mozzarella\",\"normal\":3.8,\"ticketLabel\":\"Tomates Mozzarella\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"sud\"}],\"webDetail\":\"Tomates, mozzarella fraîche, sel, poivre, origan, huile d'olive, vinaigre balsamique\"},{\"afficheDetail\":\"Tartare de tomates, mozzarella fraîche, olives noires, huile de Truffes, sel, poivre, origan\",\"canMerge\":true,\"code\":\"TOMATMOZZA2\",\"codeTva\":\"1\",\"geant\":6.2,\"htmlKeyLabel\":\"Tomates<br>mozza2\",\"id\":171,\"image\":\"vegetarien\",\"label\":\"Tomates Mozzarella 2\",\"mini\":2,\"name\":\"Tomates Mozzarella 2\",\"normal\":4.2,\"ticketLabel\":\"Tomates Mozzarella 2\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"suggestion\"},{\"tag\":\"sud\"}],\"webDetail\":\"Tartare de tomates, mozzarella fraîche, olives noires, huile de Truffes, sel, poivre, origan\"},{\"afficheDetail\":\"100% pur boeuf\",\"canMerge\":true,\"code\":\"USADOUX\",\"codeTva\":\"1\",\"geant\":4.8,\"htmlKeyLabel\":\"USA<br>doux\",\"id\":172,\"image\":\"boeuf\",\"label\":\"Américain Doux\",\"mini\":1.7,\"name\":\"Américain Doux\",\"normal\":3.5,\"ticketLabel\":\"Américain Doux\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"100% pur boeuf\"},{\"afficheDetail\":\"100% pur boeuf\",\"canMerge\":true,\"code\":\"USAEPICE\",\"codeTva\":\"1\",\"geant\":4.5,\"htmlKeyLabel\":\"USA<br>épicé\",\"id\":173,\"image\":\"epice\",\"label\":\"Américain Epicé\",\"mini\":1.7,\"name\":\"Américain Epicé\",\"normal\":3.3,\"ticketLabel\":\"Américain Epicé\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"100% pur boeuf\"},{\"afficheDetail\":\"Crudités fraîches et sauce au choix\",\"canMerge\":true,\"code\":\"VEGETARIEN\",\"codeTva\":\"1\",\"geant\":4.5,\"htmlKeyLabel\":\"Végétarien\",\"id\":174,\"image\":\"vegetarien\",\"label\":\"Végétarien\",\"mini\":1.7,\"name\":\"Végétarien\",\"normal\":3,\"ticketLabel\":\"Végétarien\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"Crudités fraîches et sauce au choix\"},{\"afficheDetail\":\"Tomates séchées, copeaux de parmesan, huile d’olive, roquette, crudités.\",\"canMerge\":true,\"code\":\"VEGETARIEN2\",\"codeTva\":\"1\",\"geant\":4.8,\"htmlKeyLabel\":\"Végétarien 2\",\"id\":175,\"image\":\"vegetarien\",\"label\":\"Végétarien Italien\",\"mini\":2,\"name\":\"Végétarien Italien\",\"normal\":4,\"ticketLabel\":\"Végétarien Italien\",\"categoryTags\":[{\"tag\":\"sandwich\"},{\"tag\":\"classique\"}],\"webDetail\":\"Tomates séchées, copeaux de parmesan, huile d’olive, roquette, crudités.\"}]");

/***/ }),

/***/ "./src/business_layer/message.service.ts":
/*!***********************************************!*\
  !*** ./src/business_layer/message.service.ts ***!
  \***********************************************/
/*! exports provided: MessageService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MessageService", function() { return MessageService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");


let MessageService = class MessageService {
    constructor() {
        this.messages = [];
    }
    add(message) {
        this.messages.push(message);
    }
    clear() {
        this.messages = [];
    }
};
MessageService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
        providedIn: 'root'
    })
], MessageService);



/***/ }),

/***/ "./src/business_layer/products.service.ts":
/*!************************************************!*\
  !*** ./src/business_layer/products.service.ts ***!
  \************************************************/
/*! exports provided: ProductsService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProductsService", function() { return ProductsService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs */ "./node_modules/rxjs/_esm2015/index.js");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs/operators */ "./node_modules/rxjs/_esm2015/operators/index.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm2015/http.js");
/* harmony import */ var _message_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./message.service */ "./src/business_layer/message.service.ts");






let ProductsService = class ProductsService {
    constructor(http, messageService) {
        this.http = http;
        this.messageService = messageService;
        this.productsUrl = 'api/products'; // URL to web api
        this.httpOptions = {
            headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpHeaders"]({ 'Content-Type': 'application/json' })
        };
    }
    getProducts() {
        console.log('getProducts()');
        return this.http.get(this.productsUrl)
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["tap"])(_ => this.log('fetched products')), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])(this.handleError('getProducts', [])));
    }
    /** GET product by id. Will 404 if id not found */
    getProduct(id) {
        const url = `${this.productsUrl}/${id}`;
        return this.http.get(url).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["tap"])(_ => this.log(`fetched product id=${id}`)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])(this.handleError(`getProduct id=${id}`)));
    }
    /** PUT: update the product on the server */
    updateProduct(product) {
        return this.http.put(this.productsUrl, product, this.httpOptions).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["tap"])(_ => this.log(`updated product id=${product.id}`)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])(this.handleError('updateProduct')));
    }
    /** POST: add a new product to the server */
    addProduct(product) {
        return this.http.post(this.productsUrl, product, this.httpOptions).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["tap"])((newProduct) => this.log(`added product w/ id=${newProduct.id}`)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])(this.handleError('addProduct')));
    }
    /** DELETE: delete the product from the server */
    deleteProduct(product) {
        const id = typeof product === 'number' ? product : product.id;
        const url = `${this.productsUrl}/${id}`;
        return this.http.delete(url, this.httpOptions).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["tap"])(_ => this.log(`deleted product id=${id}`)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])(this.handleError('deleteProduct')));
    }
    /* GET products whose name contains search term */
    searchProducts(term) {
        if (!term.trim()) {
            // if not search term, return empty array.
            return Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])([]);
        }
        return this.http.get(`${this.productsUrl}/?name=${term}`).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["tap"])(_ => this.log(`found products matching "${term}"`)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["catchError"])(this.handleError('searchProducts', [])));
    }
    /**
     * Handle Http operation that failed.
     * Let the app continue.
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable result
     */
    handleError(operation = 'operation', result) {
        return (error) => {
            // TODO: send the error to remote logging infrastructure
            console.error(error); // log to console instead
            // TODO: better job of transforming error for user consumption
            this.log(`${operation} failed: ${error.message}`);
            // Let the app keep running by returning an empty result.
            return Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(result);
        };
    }
    /** Log a ProductService message with the MessageService */
    log(message) {
        this.messageService.add(`ProductService: ${message}`);
    }
};
ProductsService.ctorParameters = () => [
    { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpClient"] },
    { type: _message_service__WEBPACK_IMPORTED_MODULE_5__["MessageService"] }
];
ProductsService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
        providedIn: 'root'
    }),
    tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpClient"],
        _message_service__WEBPACK_IMPORTED_MODULE_5__["MessageService"]])
], ProductsService);



/***/ }),

/***/ "./src/data_layer/in-memory-data.service.ts":
/*!**************************************************!*\
  !*** ./src/data_layer/in-memory-data.service.ts ***!
  \**************************************************/
/*! exports provided: InMemoryDataService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "InMemoryDataService", function() { return InMemoryDataService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _reading_json_files_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./reading-json-files.service */ "./src/data_layer/reading-json-files.service.ts");



let InMemoryDataService = class InMemoryDataService {
    constructor(readingJsonFiles) {
        this.readingJsonFiles = readingJsonFiles;
    }
    createDb() {
        console.log('create DB');
        const products = this.readingJsonFiles.getProductList();
        console.log('Db created');
        return { products };
    }
    // Overrides the genId method to ensure that a product always has an id.
    // If the products array is empty,
    // the method below returns the initial number (11).
    // if the products array is not empty, the method below returns the highest
    // product id + 1.
    genId(products) {
        return products.length > 0 ? Math.max(...products.map(product => product.id)) + 1 : 11;
    }
};
InMemoryDataService.ctorParameters = () => [
    { type: _reading_json_files_service__WEBPACK_IMPORTED_MODULE_2__["ReadingJsonFilesService"] }
];
InMemoryDataService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
        providedIn: 'root'
    }),
    tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_reading_json_files_service__WEBPACK_IMPORTED_MODULE_2__["ReadingJsonFilesService"]])
], InMemoryDataService);



/***/ }),

/***/ "./src/data_layer/reading-json-files.service.ts":
/*!******************************************************!*\
  !*** ./src/data_layer/reading-json-files.service.ts ***!
  \******************************************************/
/*! exports provided: ReadingJsonFilesService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ReadingJsonFilesService", function() { return ReadingJsonFilesService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _assets_voir_products_json__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../assets/voir_products.json */ "./src/assets/voir_products.json");
var _assets_voir_products_json__WEBPACK_IMPORTED_MODULE_2___namespace = /*#__PURE__*/__webpack_require__.t(/*! ../assets/voir_products.json */ "./src/assets/voir_products.json", 1);


// @ts-ignore

let ReadingJsonFilesService = class ReadingJsonFilesService {
    constructor() {
        console.log('Reading local json files');
        console.log(_assets_voir_products_json__WEBPACK_IMPORTED_MODULE_2__);
    }
    getProductList() {
        console.log('getProductList');
        return _assets_voir_products_json__WEBPACK_IMPORTED_MODULE_2__;
    }
};
ReadingJsonFilesService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
        providedIn: 'root'
    }),
    tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
], ReadingJsonFilesService);



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
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

const environment = {
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
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var hammerjs__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! hammerjs */ "./node_modules/hammerjs/hammer.js");
/* harmony import */ var hammerjs__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(hammerjs__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm2015/core.js");
/* harmony import */ var _angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/platform-browser-dynamic */ "./node_modules/@angular/platform-browser-dynamic/fesm2015/platform-browser-dynamic.js");
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./app/app.module */ "./src/app/app.module.ts");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./environments/environment */ "./src/environments/environment.ts");






if (_environments_environment__WEBPACK_IMPORTED_MODULE_5__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["enableProdMode"])();
}
Object(_angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_3__["platformBrowserDynamic"])().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_4__["AppModule"])
    .catch(err => console.error(err));


/***/ }),

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! /home/tote/Desktop/angular-workspace/voir/src/main.ts */"./src/main.ts");


/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main.js.map