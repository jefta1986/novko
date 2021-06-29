import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {HomeComponent} from './home/home.component';
import {CartComponent} from './cart/cart.component';
import {AdminUncheckedOrderComponent} from './admin-unchecked-orders/admin-unchecked-order.component';
import {LoginGuard} from './guards/login.guard';
import {AuthGuardAdminGuard} from './guards/auth-guard-admin.guard';
import {AdminRegistrationComponent} from './admin-registration/admin-registration.component';
import {AdminCategoryComponent} from './admin-category/admin-category.component';
import {AdminSubcategoryComponent} from './admin-subcategory/admin-subcategory.component';
import {AdminAddProductComponent} from './admin-add-product/admin-add-product.component';
import {CommonGuard} from './guards/common.guard';
import {AdminProductComponent} from './admin-products/admin-product.component';
import {SubcategoryProductsComponent} from './subcategory-products/subcategory-products.component';
import {SelectedProductComponent} from './selected-product/selected-product.component';
import {CommonLanguageGuard} from './common/common-language.guard';
import {AdminUserComponent} from './admin-users/admin-user.component';
import {AdminAddCategory} from './admin-add-category/admin-add-category.component';
import {AdminAddSubcategory} from './admin-add-subcategory/admin-add-subcategory.component';
import {AdminProductCodeComponent} from './admin-products-code/admin-product-code.component';
import {AdminOrdersComponent} from './admin-orders/admin-orders.component';
import {UserOrdersComponent} from './user-orders/user-orders.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [CommonLanguageGuard]
  },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [LoginGuard, CommonLanguageGuard]
  },
  {
    path: 'cart',
    component: CartComponent,
    canActivate: [CommonGuard, CommonLanguageGuard]
  },
  {
    path: 'orders',
    component: UserOrdersComponent,
    canActivate: [CommonGuard, CommonLanguageGuard]
  },
  {
    path: 'products/:subcategory',
    component: SubcategoryProductsComponent,
    canActivate: [CommonLanguageGuard]
  },
  {
    path: 'product/:code',
    component: SelectedProductComponent,
    canActivate: [CommonLanguageGuard]
  },
  {
    path: 'admin-unchecked-orders',
    component: AdminUncheckedOrderComponent,
    canActivate: [AuthGuardAdminGuard, CommonLanguageGuard]
  },
  {
    path: 'admin-orders',
    component: AdminOrdersComponent,
    canActivate: [AuthGuardAdminGuard, CommonLanguageGuard]
  },
  {
    path: 'admin-users/registration',
    component: AdminRegistrationComponent,
    canActivate: [AuthGuardAdminGuard, CommonLanguageGuard]
  },
  {
    path: 'admin-categories',
    component: AdminCategoryComponent,
    canActivate: [AuthGuardAdminGuard, CommonLanguageGuard]
  },
  {
    path: 'admin-categories/add-category',
    component: AdminAddCategory,
    canActivate: [AuthGuardAdminGuard, CommonLanguageGuard]
  },
  {
    path: 'admin-categories/admin-subcategory',
    component: AdminSubcategoryComponent,
    canActivate: [AuthGuardAdminGuard, CommonLanguageGuard]
  },
  {
    path: 'admin-categories/add-subcategory',
    component: AdminAddSubcategory,
    canActivate: [AuthGuardAdminGuard, CommonLanguageGuard]
  },
  {
    path: 'admin-unchecked-orders-admin-add-product',
    component: AdminAddProductComponent,
    canActivate: [AuthGuardAdminGuard, CommonLanguageGuard]
  },
  {
    path: 'admin-products',
    component: AdminProductComponent,
    canActivate: [AuthGuardAdminGuard, CommonLanguageGuard]
  },
  {
    path: 'admin-products/:code',
    component: AdminProductCodeComponent,
    canActivate: [AuthGuardAdminGuard, CommonLanguageGuard]
  },
  {
    path: 'admin-users',
    component: AdminUserComponent,
    canActivate: [AuthGuardAdminGuard, CommonLanguageGuard]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
