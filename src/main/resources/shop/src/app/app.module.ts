import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from './libs/material/material.module';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {LoginComponent} from './login/login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NavigationComponent} from './navigation/navigation.component';
import {HomeComponent} from './home/home.component';
import {CartComponent} from './cart/cart.component';
import {AuthService} from './services/auth.service';
import {AuthInterceptor} from './auth-interceptor';
import {AdminOrderComponent} from './admin-orders/admin-order.component';
import {AdminRegistrationComponent} from './admin-registration/admin-registration.component';
import {AdminCategoryComponent} from './admin-category/admin-category.component';
import {AdminSubcategoryComponent} from './admin-subcategory/admin-subcategory.component';
import {AdminAddCategory} from './admin-add-category/admin-add-category.component';
import {AdminAddSubcategory} from './admin-add-subcategory/admin-add-subcategory.component';
import {EditCategoryDialogComponent} from './dialogs/edit-category-dialog/edit-category-dialog.component';
import {EditSubcategoryDialogComponent} from './dialogs/edit-subcategory-dialog/edit-subcategory-dialog.component';
import {AdminAddProductComponent} from './admin-add-product/admin-add-product.component';
import {AdminProductComponent} from './admin-products/admin-product.component';
import {EditProductDialogComponent} from './dialogs/edit-product-dialog/edit-product-dialog.component';
import {SubcategoryProductsComponent} from './subcategory-products/subcategory-products.component';
import {SelectedProductComponent} from './selected-product/selected-product.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import 'hammerjs';
import {ProductModel} from './data/models/product.model';
import {ProductComponent} from './product/product.component';
import {InputIncrementComponent} from './input-increment/input-increment.component';
import {CommonLanguageGuard} from './common/common-language.guard';
import {CommonLanguageModel} from './common/common-language.model';
import {CommonLanguageService} from './common/common-language.service';
import { LoginNavigationComponent } from './login-navigation/login-navigation.component';
import {NgxDropzoneModule} from 'ngx-dropzone';
import { TitleComponent } from './common/title/title.component';
import {AdminUserComponent} from './admin-users/admin-user.component';
import {UsersModel} from './data/models/users.model';
import {OrdersModel} from './data/models/orders.model';
import {CategoriesModel} from './data/models/categories.model';
import {EditUserDialogComponent} from './dialogs/edit-user-dialog/edit-user-dialog.component';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavigationComponent,
    HomeComponent,
    CartComponent,
    AdminOrderComponent,
    AdminRegistrationComponent,
    AdminCategoryComponent,
    AdminSubcategoryComponent,
    AdminAddCategory,
    AdminAddSubcategory,
    EditCategoryDialogComponent,
    EditSubcategoryDialogComponent,
    EditUserDialogComponent,
    AdminAddProductComponent,
    AdminProductComponent,
    AdminUserComponent,
    EditProductDialogComponent,
    SubcategoryProductsComponent,
    SelectedProductComponent,
    ProductComponent,
    InputIncrementComponent,
    LoginNavigationComponent,
    TitleComponent,
  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
    NgxDropzoneModule
  ],
  providers: [
    AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    ProductModel,
    UsersModel,
    OrdersModel,
    CategoriesModel,
    CommonLanguageModel,
    CommonLanguageService,
    CommonLanguageGuard,
    RouterModule
  ],
  entryComponents: [
    AdminAddCategory,
    AdminAddSubcategory,
    EditCategoryDialogComponent,
    EditSubcategoryDialogComponent,
    EditUserDialogComponent,
    EditProductDialogComponent,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
