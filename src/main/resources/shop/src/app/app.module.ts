import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from  '@angular/common/http'
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NavigationComponent } from './navigation/navigation.component';
import { HomeComponent } from './home/home.component';
import { SidePanelComponent } from './side-panel/side-panel.component';
import { CartComponent } from './cart/cart.component';
import { AuthService } from './services/auth.service';
import { CookieService } from "angular2-cookie/services/cookies.service";
import { AuthInterceptor } from './auth-interceptor';
import { AdminComponent } from './admin/admin.component';
import { RegistrationComponent } from './registration/registration.component';
import { CategoryComponent } from './category/category.component';
import { SubcategoryComponent } from './subcategory/subcategory.component';
import { AddcategorydialogComponent } from './dialogs/addcategorydialog/addcategorydialog.component';
import { AddSubcategoryDialogComponent } from './dialogs/add-subcategory-dialog/add-subcategory-dialog.component';
import { EditCategoryDialogComponent } from './dialogs/edit-category-dialog/edit-category-dialog.component';
import { EditSubcategoryDialogComponent } from './dialogs/edit-subcategory-dialog/edit-subcategory-dialog.component';
import { AddProductComponent } from './add-product/add-product.component';
import { AllProductsComponent } from './all-products/all-products.component';
import { EditProductDialogComponent } from './dialogs/edit-product-dialog/edit-product-dialog.component';
import { SubcategoryProductsComponent } from './subcategory-products/subcategory-products.component';
import { SelectedProductComponent } from './selected-product/selected-product.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavigationComponent,
    HomeComponent,
    SidePanelComponent,
    CartComponent,
    AdminComponent,
    RegistrationComponent,
    CategoryComponent,
    SubcategoryComponent,
    AddcategorydialogComponent,
    AddSubcategoryDialogComponent,
    EditCategoryDialogComponent,
    EditSubcategoryDialogComponent,
    AddProductComponent,
    AllProductsComponent,
    EditProductDialogComponent,
    SubcategoryProductsComponent,
    SelectedProductComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    CookieService
  ],
  entryComponents: [AddcategorydialogComponent,AddSubcategoryDialogComponent
    ,EditCategoryDialogComponent,EditSubcategoryDialogComponent,EditProductDialogComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
