import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { CartComponent } from './cart/cart.component';
import { AdminComponent } from './admin/admin.component';
import { LoginGuard } from './guards/login.guard';
import { AuthGuardUserGuard } from './guards/auth-guard-user.guard';
import { AuthGuardAdminGuard } from './guards/auth-guard-admin.guard';
import { RegistrationComponent } from './registration/registration.component';
import { CategoryComponent } from './category/category.component';
import { SubcategoryComponent } from './subcategory/subcategory.component';
import { AddProductComponent } from './add-product/add-product.component';
import { CommonGuard } from './guards/common.guard';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent,canActivate:[LoginGuard]},
  { path: 'home', component: HomeComponent,canActivate:[CommonGuard] },
  { path: 'cart', component: CartComponent ,canActivate:[AuthGuardUserGuard]},
  { path: 'admin', component: AdminComponent ,canActivate:[AuthGuardAdminGuard]},
  { path: 'registration', component: RegistrationComponent ,canActivate:[AuthGuardAdminGuard]},
  { path: 'category', component: CategoryComponent ,canActivate:[AuthGuardAdminGuard]},
  { path: 'subcategory', component: SubcategoryComponent ,canActivate:[AuthGuardAdminGuard]},
  { path: 'addproduct', component: AddProductComponent ,canActivate:[AuthGuardAdminGuard]},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
